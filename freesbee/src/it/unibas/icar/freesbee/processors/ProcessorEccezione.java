package it.unibas.icar.freesbee.processors;

import com.google.inject.Inject;
import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOMessaggio;
import it.unibas.icar.freesbee.persistenza.hibernate.DAOMessaggioHibernate;
import it.unibas.icar.freesbee.persistenza.hibernate.DAOUtilHibernate;
import it.unibas.icar.freesbee.utilita.CostantiBusta;
import it.unibas.icar.freesbee.utilita.CostantiSOAP;
import java.util.HashMap;
import java.util.Map;
import javax.xml.namespace.QName;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.servicemix.soap.SoapFault;
import org.apache.servicemix.soap.marshalers.SoapMessage;
import org.hibernate.SessionFactory;
import org.w3c.dom.DocumentFragment;

public class ProcessorEccezione implements Processor {

    private String tipoErrore = "SOAP_ENV:Client";
    private static Log logger = LogFactory.getLog(ProcessorEccezione.class);
    private boolean generaIntestazioniEGov;
    @Inject
    private ProcessorWrapper processorWrapper;
    @Inject
    private ProcessorEnricherRisposta processorEnricherRisposta;

    public ProcessorEccezione() {
    }

    public ProcessorEccezione(boolean generaIntestazioniEGov) {
        this.generaIntestazioniEGov = generaIntestazioniEGov;
    }

    @SuppressWarnings("unchecked")
    public void process(Exchange exchange) throws Exception {
        //ContextStartup.aggiungiThread(this.getClass().getName());
        Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiBusta.MESSAGGIO);
//        Throwable eccezione = (Exception) exchange.getIn().getHeader("caught.exception");
//        Throwable eccezione = exchange.getException();
        Exception eccezione = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
        if (logger.isDebugEnabled()) eccezione.printStackTrace();

        String stringaEccezione = eccezione.getMessage();
        logger.error("E' stata lanciata una eccezione di FreESBee : \n\n'" + stringaEccezione + "'\n\n");
        String messaggioEccezione = (String) exchange.getProperty(CostantiSOAP.SOAP_HEADER_MESSAGE_EXCEPTION);
        logger.error("Messaggio Eccezione: "+messaggioEccezione);
//        eccezione.printStackTrace();

//        int codiceErrore = 300;
//        if (eccezione instanceof FreesbeeException) {
//            logger.error("E' stata lanciata una eccezione di FreESBee " + eccezione.getMessage());
//            logger.error("Si stava processando il messaggio " + messaggio);
//            FreesbeeException freesbeeEx = (FreesbeeException) eccezione;
//            codiceErrore = freesbeeEx.getCodiceErroreEGov();
//            if (logger.isInfoEnabled()) {
//                logger.info("Il codice d'errore della busta è " + codiceErrore);
//            }
//        } else {
//            eccezione.printStackTrace();
//            logger.error("E' stato lanciato un eccezione generica " + eccezione);
//            logger.error("Si stava processando il messaggio " + messaggio);
//        }
        if (isGeneraIntestazioniEGov() == true) {
            //Il messaggio d'errore dovrà essere consegnato ad una porta di 
            //dominio, quindi bisogna preparare le intestazioni egov.

//            FactoryEccezioniEgov fac = FactoryEccezioniEgov.getInstance();
//            Eccezione ecc = fac.getEccezione(codiceErrore);
//            messaggio.getListaEccezioni().add(ecc);
//            ecc.setMessaggio(messaggio);
            messaggio.setRiferimentoMessaggio(messaggio.getIdMessaggio());
            salvaMessaggio(messaggio);
            processorEnricherRisposta.process(exchange);
            processorWrapper.process(exchange);
            Map<QName, DocumentFragment> mappaHeaderSoap = (Map<QName, DocumentFragment>) exchange.getProperty(CostantiSOAP.SOAP_HEADERS);
            Map<QName, DocumentFragment> mappaHeaderSoapFault = new HashMap<QName, DocumentFragment>();
            for (QName qname : mappaHeaderSoap.keySet()) {
                if (qname.getNamespaceURI().equalsIgnoreCase(CostantiSOAP.NAMESPACE_EGOV)) {
                    mappaHeaderSoapFault.put(qname, mappaHeaderSoap.get(qname));
                }
            }
            exchange.setProperty(CostantiSOAP.SOAP_HEADERS + ".fault", mappaHeaderSoapFault);
            SoapFault soapFault = new SoapFault(QName.valueOf(tipoErrore), stringaEccezione);

            SoapMessage soapMessage = new SoapMessage();
            soapMessage.setFault(soapFault);
            if (logger.isInfoEnabled()) logger.info("MappaHeaderSoapFault " + mappaHeaderSoapFault);
            soapMessage.setHeaders(mappaHeaderSoapFault);
        }
    }

    private void salvaMessaggio(Messaggio messaggio) {
        if (messaggio == null || messaggio.getId() <= 0) {
            return;
        }
        if (logger.isInfoEnabled()) logger.info("Aggiorniamo lo stato del messaggio a seguito di un'eccezione");
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        IDAOMessaggio daoMessaggioCorrente = new DAOMessaggioHibernate();
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            daoMessaggioCorrente.makePersistent(messaggio);
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (DAOException ex) {
            logger.warn("Impossibile aggiornare il messaggio nella base di dati a seguito di un'eccezione");
        } finally {
            try {
                if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
                    sessionFactory.getCurrentSession().getTransaction().rollback();
                }
            } catch (Throwable rbEx) {
            }
        }
    }

    public ProcessorWrapper getProcessorWrapper() {
        return processorWrapper;
    }

    public void setProcessorWrapper(ProcessorWrapper processorWrapper) {
        this.processorWrapper = processorWrapper;
    }

    public boolean isGeneraIntestazioniEGov() {
        return generaIntestazioniEGov;
    }

    public void setGeneraIntestazioniEGov(boolean generaIntestazioniEGov) {
        this.generaIntestazioniEGov = generaIntestazioniEGov;
    }

    public ProcessorEnricherRisposta getProcessorEnricherRisposta() {
        return processorEnricherRisposta;
    }

    public void setProcessorEnricherRisposta(ProcessorEnricherRisposta processorEnricherRisposta) {
        this.processorEnricherRisposta = processorEnricherRisposta;
    }
}
