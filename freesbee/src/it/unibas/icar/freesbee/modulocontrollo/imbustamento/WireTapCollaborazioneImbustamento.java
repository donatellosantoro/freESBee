package it.unibas.icar.freesbee.modulocontrollo.imbustamento;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import it.unibas.icar.freesbee.exception.FreesbeeException;
import it.unibas.icar.freesbee.modello.AccordoServizio;
import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOMessaggio;
import it.unibas.icar.freesbee.persistenza.hibernate.DAOUtilHibernate;
import it.unibas.icar.freesbee.processors.ProcessorErroreImbustamento;
import it.unibas.icar.freesbee.processors.ProcessorLogFactory;
import it.unibas.icar.freesbee.utilita.CostantiBusta;
import it.unibas.icar.freesbee.utilita.FreesbeeCamel;
import it.unibas.icar.freesbee.utilita.FreesbeeUtil;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;

@Singleton
public class WireTapCollaborazioneImbustamento extends RouteBuilder {

    private static Log logger = LogFactory.getLog(WireTapCollaborazioneImbustamento.class);
    @Inject
    private IDAOMessaggio daoMessaggio;
    @Inject
    private ProcessorErroreImbustamento processorErroreImbustamento;

    public void configure() throws Exception {
        this.from(FreesbeeCamel.SEDA_WIRETAP_COLLABORAZIONE_IMBUSTAMENTO)
//                .threads(ConfigurazioneStatico.getInstance().getCamelThreadPool(),
//                ConfigurazioneStatico.getInstance().getCamelThreadPoolMax())
            .process(ProcessorLogFactory.getInstance().getProcessorLog(this.getClass()))
            .doTry()
                .process(new ProcessorCorrelazioneMessaggi())
                .to(FreesbeeCamel.SEDA_WIRETAP_TRACCIAMENTO_IMBUSTAMENTO)
            .doCatch(Exception.class)
                .process(processorErroreImbustamento);
    }

    @SuppressWarnings("unchecked")
    private class ProcessorCorrelazioneMessaggi implements Processor {

        public void process(Exchange exchange) throws Exception {
            //ContextStartup.aggiungiThread(this.getClass().getName());
            Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiBusta.MESSAGGIO);
            if (messaggio.isNica()) {
                //SONO UN NICA. NON DEVO PREOCCUPARMI DELLA CORRELAZIONE DEI MESSAGGI
                return;
            }

            this.cercaIntestazioniCorrelazione(exchange, messaggio);
            SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
            IDAOMessaggio daoMessaggioCorr = daoMessaggio;
            try {
                sessionFactory.getCurrentSession().beginTransaction();
                if (messaggio.isCorrelato()) {
                    //CERCO LA BUSTA EGOV COLLEGATA
                    String relatesSil = messaggio.getSilRelatesTo();
                    Messaggio messaggioCollegato = daoMessaggioCorr.findByIDSil(relatesSil, Messaggio.TIPO_RICEVUTO);
                    if (messaggioCollegato == null) {
                        throw new FreesbeeException("Impossibile correlare il messaggio. Non e' stata ricevuta alcuna richiesta con id " + relatesSil);
                    }
                    Messaggio messaggioRisposta = daoMessaggioCorr.findByIDSilRelatesTo(relatesSil, Messaggio.TIPO_INVIATO);
                    if (messaggioRisposta != null) {
                        throw new FreesbeeException("Impossibile correlare il messaggio. E' stata gia' inviata una risposta per il messaggio " + relatesSil);
                    }
                    //Per correlare due messaggi asinconi si deve usare solo Collaborazione
                    //  il campo riferimento messaggio � da usare solo nelle risposte sincrone (o ack)
                    //messaggio.setRiferimentoMessaggio(messaggioCollegato.getIdMessaggio());
                    messaggio.setCollaborazione(messaggioCollegato.getIdMessaggio());
                }
                if (messaggio.isCorrelato() == false && messaggio.getSilRelatesTo() != null
                        && messaggio.getProfiloCollaborazione().equalsIgnoreCase(AccordoServizio.PROFILO_ASINCRONO_ASIMMETRICO)) {
                    if (logger.isDebugEnabled()) logger.debug("E'stata ricevuta una richiesta di stato per un servizio asincrono asimmetrico.");
                    Messaggio messaggioCollegato = daoMessaggioCorr.findByIDSil(messaggio.getSilRelatesTo(), Messaggio.TIPO_INVIATO);
                    if (messaggioCollegato == null) {
                        throw new FreesbeeException("Impossibile correlare il messaggio di stato. Non e' stato inviato nessun messaggio con idSil " + messaggio.getSilRelatesTo());
                    }
                    messaggio.setCollaborazione(messaggioCollegato.getIdMessaggio());
                }
                sessionFactory.getCurrentSession().getTransaction().commit();
            } catch (DAOException ex) {
                logger.error("Errore nella lettura dal database ");
                if (logger.isErrorEnabled()) logger.error(ex);
                throw new FreesbeeException("Errore nella lettura dal database.");
            } finally {
                try {
                    if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
                        sessionFactory.getCurrentSession().getTransaction().rollback();
                    }
                } catch (Throwable rbEx) {
                }
            }
        }

        private void cercaIntestazioniCorrelazione(Exchange exchange, Messaggio messaggio) throws FreesbeeException {
            String idSil = FreesbeeUtil.cercaIntestazioniIDWsa(exchange);
            if (idSil == null) {
                idSil = FreesbeeUtil.cercaIntestazioniIDIntegrationManager(exchange);
            }
            if (idSil == null) {
                idSil = FreesbeeUtil.cercaIntestazioniIDHttpHeader(exchange.getIn());
            }
            if (idSil == null) {
                if (logger.isDebugEnabled()) logger.debug("Non ho trovato le intestazioni di correlazione. Utilizzo l'id SPCoop");
                idSil = messaggio.getIdMessaggio();
//                throw new FreesbeeException("ID Messaggio asincrono mancante", 104);
            }
            messaggio.setIdSil(idSil);

            if (messaggio.isCorrelato() || messaggio.getProfiloCollaborazione().equalsIgnoreCase(AccordoServizio.PROFILO_ASINCRONO_ASIMMETRICO)) {
                String relatesSil = FreesbeeUtil.cercaIntestazioniIDRelatesToWsa(exchange);
                if (relatesSil == null) {
                    relatesSil = FreesbeeUtil.cercaIntestazioniIDRelatesToIntegrationManager(exchange);
                }
                if (relatesSil == null) {
                    relatesSil = FreesbeeUtil.cercaIntestazioniIDRelatesToHttpHeader(exchange.getIn());
                }
                if (relatesSil == null && messaggio.isCorrelato()) {
                    throw new FreesbeeException("Una richiesta ad un servizio correlato deve contenere l'intestazione di correlazione");
                }
                messaggio.setSilRelatesTo(relatesSil);
            }
        }
    }

    public ProcessorErroreImbustamento getProcessorErroreImbustamento() {
        return processorErroreImbustamento;
    }

    public void setProcessorErroreImbustamento(ProcessorErroreImbustamento processorErroreImbustamento) {
        this.processorErroreImbustamento = processorErroreImbustamento;
    }

    public IDAOMessaggio getDaoMessaggio() {
        return daoMessaggio;
    }

    public void setDaoMessaggio(IDAOMessaggio daoMessaggio) {
        this.daoMessaggio = daoMessaggio;
    }
}
