package it.unibas.silvio.processor;

import it.unibas.silvio.modello.Configurazione;
import it.unibas.silvio.modello.Dati;
import it.unibas.silvio.modello.DatiDB;
import it.unibas.silvio.modello.DatiSQL;
import it.unibas.silvio.modello.IstanzaOperation;
import it.unibas.silvio.modello.Messaggio;
import it.unibas.silvio.persistenza.DAOException;
import it.unibas.silvio.persistenza.IDAOConfigurazione;
import it.unibas.silvio.persistenza.IDAODati;
import it.unibas.silvio.persistenza.SilvioException;
import it.unibas.silvio.sql.SQLUtil;
import it.unibas.silvio.transql.SQLAttachmentParser;
import it.unibas.silvio.util.CostantiSilvio;
import it.unibas.silvio.util.StringUtil;
import it.unibas.silvio.xml.XmlJDomUtil;
import java.io.File;
import java.util.List;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProcessorEnricherDatiParzialiCompletiErogatore implements Processor {

    private Log logger = LogFactory.getLog(this.getClass());
    private IDAOConfigurazione daoConfigurazione;
    private IDAODati daoDati;

    public ProcessorEnricherDatiParzialiCompletiErogatore(IDAOConfigurazione daoConfigurazione, IDAODati daoDati) {
        this.daoConfigurazione = daoConfigurazione;
        this.daoDati = daoDati;
    }

    public void process(Exchange exchange) throws Exception {
        Messaggio messaggioRichiesta = (Messaggio) exchange.getProperty(CostantiSilvio.MESSAGGIO_RICHIESTA);
        Messaggio messaggioRisposta = (Messaggio) exchange.getProperty(CostantiSilvio.MESSAGGIO_RISPOSTA);
        Dati datiCompleti = new Dati();
        IstanzaOperation istanzaOperation = messaggioRisposta.getIstanzaOperation();
        boolean istanzaTest = messaggioRichiesta.getParametriMessaggioRicevuto().isTest();
        Dati datiIstanza = istanzaOperation.getDatiRisposta();
        try {
            daoDati.makePersistent(datiIstanza);
        } catch (DAOException e) {
            throw new SilvioException("Impossibile leggere i dati della risposta");
        }
        if (datiIstanza == null) {
            logger.error("Non sono stati definiti i dati per generare la risposta.");
            throw new SilvioException("Impossibile eseguire l'istanza. Non sono stati definiti i dati per " +
                    "generare la risposta. Generare nuovamente l'istanza e riprovare");
        }
        if (!istanzaTest && !datiIstanza.isPayloadCostante()) { //SE L'ISTANZA E' DI TEST, CI POSSONO SOLO ESSERE DATI COSTANTI
            datiCompleti.setDatiSQL(elaboraDatiDB(datiIstanza.getDatiSQL(), istanzaOperation, messaggioRichiesta, messaggioRisposta));
            datiCompleti.setDatiCostanti(datiIstanza.getDatiCostanti());
        }
        if (datiIstanza.isPayloadCostante()) {
            datiCompleti.setPayloadCostante(datiIstanza.getPayloadCostante());
        }
        logger.info("Estratti correttamente i dati completi. " + datiCompleti);
        messaggioRisposta.setDatiCompletiRisposta(datiCompleti);
    }

    private DatiSQL elaboraDatiDB(DatiSQL datiSQL, IstanzaOperation istanzaOperation, Messaggio messaggioRichiesta, Messaggio messaggioRisposta) throws SilvioException {
        if (datiSQL == null) {
            return null;
        }
        //E' STATO SPECIFICATO UN FILE DI TRASFORMAZIONE PER CREARE LA SELECT DA ESEGUIRE PER ESTRARRE I DATI DELLA RISPOSTA
        try {
            DatiSQL datiSQLGenerati = new DatiSQL();
            Configurazione configurazione = daoConfigurazione.caricaConfigurazione();
            String fileXSLT = configurazione.getDirConfig() + File.separator +
                    istanzaOperation.getIstanzaPortType().getIstanzaAccordo().getCartellaFiles() +
                    istanzaOperation.getXSLTSoapToSQLErogatore();
            String query = XmlJDomUtil.transformToString(messaggioRichiesta.getMessaggio(), fileXSLT);
            SQLAttachmentParser selectAttachment = new SQLAttachmentParser();
            query = query.replaceAll("[\r\t\n]", "");
            query = query.replaceAll("[\\s]*\'", "'");
            query = selectAttachment.parse(query);
            messaggioRisposta.setSelectAttachment(selectAttachment.getSelectAttachment());
            messaggioRisposta.setQueryAttachment(datiSQL.getSelect());
            if (StringUtil.isVuota(query)) {
                return datiSQLGenerati;
            }
            List<DatiDB> listaDatiDB = SQLUtil.eseguiQuery(datiSQL, query);
            datiSQLGenerati.setSelect(datiSQL.getSelect());
            datiSQLGenerati.setListaDatiDB(listaDatiDB);
            return datiSQLGenerati;
        } catch (DAOException ex) {
            logger.error("Impossibile leggere la configurazione. " + ex);
            throw new SilvioException("Impossibile leggere la configurazione. " + ex);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Impossibile eseguire la trasformazione per estrarre la SELECT per la generazione del messaggio di risposta. " + ex);
            throw new SilvioException("Impossibile eseguire la trasformazione per estrarre la SELECT per la generazione del messaggio di risposta. " + ex);
        }
    }
}
