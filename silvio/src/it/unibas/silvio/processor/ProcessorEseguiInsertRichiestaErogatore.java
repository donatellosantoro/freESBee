package it.unibas.silvio.processor;

import it.unibas.silvio.modello.Configurazione;
import it.unibas.silvio.modello.DatiSQL;
import it.unibas.silvio.modello.IstanzaOperation;
import it.unibas.silvio.modello.Messaggio;
import it.unibas.silvio.persistenza.IDAOConfigurazione;
import it.unibas.silvio.persistenza.SilvioException;
import it.unibas.silvio.transql.SQLExecutor;
import it.unibas.silvio.transql.SQLInsert;
import it.unibas.silvio.transql.SQLParser;
import it.unibas.silvio.transql.SQLParserException;
import it.unibas.silvio.util.CostantiSilvio;
import it.unibas.silvio.xml.XmlJDomUtil;
import java.io.File;
import java.util.List;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProcessorEseguiInsertRichiestaErogatore implements Processor {

    private Log logger = LogFactory.getLog(this.getClass());
    private IDAOConfigurazione daoConfigurazione;

    public ProcessorEseguiInsertRichiestaErogatore(IDAOConfigurazione daoConfigurazione) {
        this.daoConfigurazione = daoConfigurazione;
    }

    public void process(Exchange exchange) throws Exception {
        Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiSilvio.MESSAGGIO_RICHIESTA);
        IstanzaOperation istanzaOperation = messaggio.getIstanzaOperation();
        boolean istanzaTest = messaggio.getParametriMessaggioRicevuto().isTest();
        
        if(istanzaTest){
            logger.info("Ricevuto un messaggio di test. Non devo effettuare trasformazioni");
            return;
        }
        
        if (istanzaOperation.isElaboraRichiesta()) {
            try {
                Configurazione configurazione = daoConfigurazione.caricaConfigurazione();
                String fileXSLT = configurazione.getDirConfig() + File.separator +
                        istanzaOperation.getIstanzaPortType().getIstanzaAccordo().getCartellaFiles() +
                        istanzaOperation.getXSLTSoapToSQLInsertErogatore();
                logger.info("Eseguo la trasformazione per estrarre l'insert per l'inserimento dei dati della richiesta nel db");
                String query = XmlJDomUtil.transformToString(messaggio.getMessaggio(), fileXSLT);
                query = query.replaceAll("[\r\t\n]", "");
                query = query.replaceAll("[\\s]*\'", "'");
                query = query.trim();
                DatiSQL datiSql = istanzaOperation.getDatiRichiesta().getDatiSQL();
                logger.info("QUERY: '" + query + "'");
                eseguiQuery(datiSql, query);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Impossibile eseguire la trasformazione " + e);
                throw new SilvioException("Impossibile eseguire la trasformazione " + e);
            }
        }
    }


    private void eseguiQuery(DatiSQL datiSQL, String select) throws SilvioException, SQLParserException {
        SQLExecutor sqlExec = new SQLExecutor(datiSQL.getSelect());
        List<SQLInsert> listaInsert = SQLParser.parse(select);
        String risultato = sqlExec.esegui(listaInsert);
        logger.info("Risultato:\n" + risultato);
    }
}
