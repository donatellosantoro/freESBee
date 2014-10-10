package it.unibas.silvio.processor;

import it.unibas.silvio.modello.Dati;
import it.unibas.silvio.modello.DatiDB;
import it.unibas.silvio.modello.DatiInterattivi;
import it.unibas.silvio.modello.DatiSQL;
import it.unibas.silvio.modello.DatoPrimitivo;
import it.unibas.silvio.modello.IstanzaOperation;
import it.unibas.silvio.modello.Messaggio;
import it.unibas.silvio.modello.ParametriEseguiIstanza;
import it.unibas.silvio.persistenza.DAOException;
import it.unibas.silvio.persistenza.IDAODati;
import it.unibas.silvio.persistenza.IDAOIstanzaAccordoDiCollaborazione;
import it.unibas.silvio.persistenza.SilvioException;
import it.unibas.silvio.sql.SQLUtil;
import it.unibas.silvio.sql.SelectParser;
import it.unibas.silvio.transql.SQLAttachmentParser;
import it.unibas.silvio.transql.SQLParserException;
import it.unibas.silvio.util.CostantiSilvio;
import it.unibas.silvio.util.StringUtil;
import java.util.List;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProcessorEnricherDatiParzialiCompletiFruitore implements Processor {

    private Log logger = LogFactory.getLog(this.getClass());
    private IDAOIstanzaAccordoDiCollaborazione daoIstanzaAccordo;
    private IDAODati daoDati;

    public ProcessorEnricherDatiParzialiCompletiFruitore(IDAOIstanzaAccordoDiCollaborazione daoIstanzaAccordo, IDAODati daoDati) {
        this.daoIstanzaAccordo = daoIstanzaAccordo;
        this.daoDati = daoDati;
    }

    public void process(Exchange exchange) throws Exception {
        Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiSilvio.MESSAGGIO_RICHIESTA);
        Dati datiCompleti = new Dati();
        ParametriEseguiIstanza parametriIstanza = messaggio.getParametriEseguiIstanza();
        IstanzaOperation istanzaOperation = messaggio.getIstanzaOperation();
        boolean istanzaTest = parametriIstanza.isTest();

        Dati datiIstanza = istanzaOperation.getDatiRichiesta();
        try {
            daoDati.makePersistent(datiIstanza);
        } catch (DAOException e) {
            throw new SilvioException("Impossibile leggere i dati della richiesta");
        }
        if (datiIstanza == null) {
            logger.error("Non sono stati definiti i dati per generare la richiesta.");
            throw new SilvioException("Impossibile eseguire l'istanza. Non sono stati definiti i dati per " +
                    "generare la richiesta. Generare nuovamente l'istanza e riprovare");
        }

        List<DatoPrimitivo> datiDB = parametriIstanza.getDatiDB();
        List<DatoPrimitivo> datiInterattivi = parametriIstanza.getDatiInterattivi();
        if (!istanzaTest && !datiIstanza.isPayloadCostante()) { //SE L'ISTANZA E' DI TEST, CI POSSONO SOLO ESSERE DATI COSTANTI
            datiCompleti.setDatiSQL(eseguiSelectParametriche(datiDB, datiIstanza.getDatiSQL(),messaggio));
            datiCompleti.setDatiInterattivi(riempiDatiInterattivi(datiInterattivi, datiIstanza.getDatiInterattivi()));
            datiCompleti.setDatiCostanti(datiIstanza.getDatiCostanti());
        }
        if (datiIstanza.isPayloadCostante()) {
            datiCompleti.setPayloadCostante(datiIstanza.getPayloadCostante());
        }
        logger.info("Estratti correttamente i dati completi. " + datiCompleti);
        messaggio.setDatiCompletiRichiesta(datiCompleti);
    }

    private DatiSQL eseguiSelectParametriche(List<DatoPrimitivo> datiIstanza, DatiSQL datiSQL,Messaggio messaggio) throws SilvioException {
        DatiSQL datiSQLCompleti = new DatiSQL();
        if (datiSQL == null || datiSQL.getSelect() == null) {
            return datiSQLCompleti;
        }
        String queryParametrica = datiSQL.getSelect().getQuery();
        SQLAttachmentParser selectAttachment = new SQLAttachmentParser();
        try {
            String select = SelectParser.completaQuery(queryParametrica, datiIstanza);
            if (select == null) {
                throw new SilvioException("Impossibile eseguire la query " + queryParametrica + " in quanto non sono stati forniti tutti i parametri");
            }
            select = selectAttachment.parse(select);
            messaggio.setSelectAttachment(selectAttachment.getSelectAttachment());
            messaggio.setQueryAttachment(datiSQL.getSelect());
            if (StringUtil.isVuota(select)) {
                return datiSQLCompleti;
            }
            List<DatiDB> listaDati = SQLUtil.eseguiQuery(datiSQL, select);
            datiSQLCompleti.setListaDatiDB(listaDati);
            return datiSQLCompleti;
        } catch (SQLParserException ex) {
            logger.warn("Impossibile analizzare la query " + queryParametrica + ". " + ex);
            throw new SilvioException("Impossibile analizzare la query " + queryParametrica + ". " + ex);
        }
    }

    private DatiInterattivi riempiDatiInterattivi(List<DatoPrimitivo> datiIstanza, DatiInterattivi datiInterattivi) {
        DatiInterattivi datiInterattiviCompleti = null;
        if (datiInterattivi != null) {
            datiInterattiviCompleti = new DatiInterattivi();
            for (DatoPrimitivo datoPrimitivo : datiIstanza) {
                if (datiInterattivi.esisteDati(datoPrimitivo.getNome())) {
                    datiInterattiviCompleti.getDatiInterattivi().add(datoPrimitivo);
                }
            }
        }
        return datiInterattiviCompleti;
    }
}
