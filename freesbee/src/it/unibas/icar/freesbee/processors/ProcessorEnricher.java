package it.unibas.icar.freesbee.processors;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import it.unibas.icar.freesbee.exception.FreesbeeException;
import it.unibas.icar.freesbee.modello.AccordoServizio;
import it.unibas.icar.freesbee.modello.Configurazione;
import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.modello.PortaDelegata;
import it.unibas.icar.freesbee.persistenza.DBManager;
import it.unibas.icar.freesbee.processors.strategy.EnricherPortaDelegataFactory;
import it.unibas.icar.freesbee.processors.strategy.IEnricherPortaDelegataStrategy;
import it.unibas.icar.freesbee.utilita.CostantiBusta;
import it.unibas.icar.freesbee.utilita.CostantiSOAP;
import it.unibas.icar.freesbee.utilita.FreesbeeUtil;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.slf4j.LoggerFactory;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

@Singleton
public class ProcessorEnricher implements Processor {

//    private static Log logger = LogFactory.getLog(ProcessorEnricher.class);
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ProcessorEnricher.class.getName());
    @Inject
    private EnricherPortaDelegataFactory enricherFactory;
    @Inject
    private DBManager dbManager;

    public ProcessorEnricher() {
    }

    public void process(Exchange exchange) throws Exception {
        //ContextStartup.aggiungiThread(this.getClass().getName());
        Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiBusta.MESSAGGIO);
        String nomePortaDelegata = messaggio.getPortaDelegata();
//        if (logger.isInfoEnabled()) logger.info("Si sta trasformando il messaggio SOAP ricevuto sulla PD " + nomePortaDelegata + " in messaggio EGOV.");
        Configurazione configurazione = dbManager.getConfigurazione();
        PortaDelegata portaDelegata = dbManager.getPortaDelegata(nomePortaDelegata);
        portaDelegata = portaDelegata.clone();
        IEnricherPortaDelegataStrategy enricherPortaDelegata = enricherFactory.getInstance(configurazione);
        //ARRICCHIRE FRUITORE SE PD_DINAMICA
        if (portaDelegata.isFruitoreQueryString()) {
            arricchistiPortaDelegataDinamica(exchange, portaDelegata, enricherPortaDelegata);
        }
        //SE L'AZIONE NON è STATA DEFINITA LA CERCHIAMO NELLA SOAPAction
        if (portaDelegata.getAzione() == null && (portaDelegata.getStringaAzione() == null || portaDelegata.getStringaAzione().isEmpty())) {
            String soapAction = (String) exchange.getIn().getHeader("SOAPAction".toLowerCase());
            if (soapAction != null && !soapAction.trim().isEmpty() && !soapAction.equals("\"\"")) {
                portaDelegata.setStringaAzione(FreesbeeUtil.pulisciSoapAction(soapAction));
                enricherPortaDelegata.arricchisciAzioneDinamica(portaDelegata);
            }
        }
        enricherPortaDelegata.arricchisciMessaggio(portaDelegata, messaggio);
        if ((messaggio.getProfiloCollaborazione().equalsIgnoreCase(AccordoServizio.PROFILO_ASINCRONO_SIMMETRICO)
                || messaggio.getProfiloCollaborazione().equalsIgnoreCase(AccordoServizio.PROFILO_ASINCRONO_ASIMMETRICO))
                && !messaggio.isCorrelato()) {
            messaggio.setCollaborazione(messaggio.getIdMessaggio());
            if (logger.isDebugEnabled()) logger.debug("Settiamo l'intestazione collaborazione a " + messaggio.getCollaborazione());
        }
        if (logger.isDebugEnabled()) logger.debug(generaMessaggioLog(messaggio));
        exchange.getIn().setHeader(CostantiBusta.VALOREPROFILOCOLLABORAZIONE, messaggio.getProfiloCollaborazione());
        exchange.setProperty(CostantiBusta.VALOREPROFILOCOLLABORAZIONE, messaggio.getProfiloCollaborazione());
    }

    private String generaMessaggioLog(Messaggio messaggio) {
        StringBuilder sb = new StringBuilder();
        sb.append("Porta delegata contattata: ")
                .append(messaggio.getPortaDelegata())
                .append(" Ricevuto messaggio: ")
                .append(messaggio.getIdMessaggio())
                .append("\nFrom: ")
                .append(messaggio.getTipoFruitore())
                .append("/")
                .append(messaggio.getFruitore())
                .append(" --> ")
                .append(messaggio.getTipoErogatore())
                .append("/")
                .append(messaggio.getErogatore())
                .append("/")
                .append(messaggio.getServizio())
                .append("/")
                .append(messaggio.getAzione());
//        sb.append("\nInoltro il messaggio");
        return sb.toString();
    }

    private void arricchistiPortaDelegataDinamica(Exchange exchange, PortaDelegata portaDelegata, IEnricherPortaDelegataStrategy enricherPortaDelegata) throws FreesbeeException {
        if (logger.isInfoEnabled()) logger.info("E' stata contattata una porta delegata dinamica. Le informazioni sul fruitore vengono prelevate dalla query string.");
        Message messageIn = exchange.getIn();
        String queryString = (String) exchange.getIn().getHeader(CostantiSOAP.JETTY_QUERY_STRING);
        String queryFruitore = FreesbeeUtil.leggiDettagliPDDinamica(messageIn, queryString, CostantiSOAP.FRUITORE_QUERY_STRING);
        String queryTipoFruitore = FreesbeeUtil.leggiDettagliPDDinamica(messageIn, queryString, CostantiSOAP.FRUITORE_TIPO_QUERY_STRING);
        String queryErogatore = FreesbeeUtil.leggiDettagliPDDinamica(messageIn, queryString, CostantiSOAP.EROGATORE_QUERY_STRING);
        String queryTipoErogatore = FreesbeeUtil.leggiDettagliPDDinamica(messageIn, queryString, CostantiSOAP.EROGATORE_TIPO_QUERY_STRING);
        String queryServizio = FreesbeeUtil.leggiDettagliPDDinamica(messageIn, queryString, CostantiSOAP.SERVIZIO_QUERY_STRING);
        String queryTipoServizio = FreesbeeUtil.leggiDettagliPDDinamica(messageIn, queryString, CostantiSOAP.SERVIZIO_TIPO_QUERY_STRING);
        String queryAzione = FreesbeeUtil.leggiDettagliPDDinamica(messageIn, queryString, CostantiSOAP.AZIONE_QUERY_STRING);

        enricherPortaDelegata.arricchisciPortaDelegataDinamica(portaDelegata, queryFruitore, queryTipoFruitore, queryErogatore, queryTipoErogatore, queryServizio, queryTipoServizio, queryAzione);
    }

    public EnricherPortaDelegataFactory getEnricherFactory() {
        return enricherFactory;
    }

    public void setEnricherFactory(EnricherPortaDelegataFactory enricherFactory) {
        this.enricherFactory = enricherFactory;
    }

    public DBManager getDbManager() {
        return dbManager;
    }

    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }
}
