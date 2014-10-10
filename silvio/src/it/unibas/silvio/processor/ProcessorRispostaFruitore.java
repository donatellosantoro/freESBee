package it.unibas.silvio.processor;

import it.unibas.silvio.modello.Configurazione;
import it.unibas.silvio.modello.DatiSQL;
import it.unibas.silvio.modello.IstanzaOperation;
import it.unibas.silvio.modello.Messaggio;
import it.unibas.silvio.modello.ParametriEseguiIstanza;
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

public class ProcessorRispostaFruitore implements Processor {

    private Log logger = LogFactory.getLog(this.getClass());
    private IDAOConfigurazione daoConfigurazione;

    public ProcessorRispostaFruitore(IDAOConfigurazione daoConfigurazione) {
        this.daoConfigurazione = daoConfigurazione;
    }

    public void process(Exchange exchange) throws Exception {
        Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiSilvio.MESSAGGIO_RISPOSTA);
        ParametriEseguiIstanza parametri = messaggio.getParametriEseguiIstanza();
        IstanzaOperation istanzaOperation = messaggio.getIstanzaOperation();
        if (istanzaOperation.isElaboraRisposta() && !messaggio.isFault() && !parametri.isTest()) {
            try {
                Configurazione configurazione = daoConfigurazione.caricaConfigurazione();
                String fileXSLT = configurazione.getDirConfig() + File.separator +
                        istanzaOperation.getIstanzaPortType().getIstanzaAccordo().getCartellaFiles() +
                        istanzaOperation.getXSLTDatiToSQLFruitore();
                String query = XmlJDomUtil.transformToString(messaggio.getMessaggio(), fileXSLT);
                query = query.replaceAll("[\r\t\n]", "");
                query = query.replaceAll("[\\s]*\'", "'");
                DatiSQL datiSql = istanzaOperation.getDatiRisposta().getDatiSQL();
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
