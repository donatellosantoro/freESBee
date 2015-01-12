package it.unibas.icar.freesbee.modulocontrollo.sbustamento;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import it.unibas.icar.freesbee.exception.FreesbeeException;
import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.modello.Servizio;
import it.unibas.icar.freesbee.modello.Soggetto;
import it.unibas.icar.freesbee.persistenza.IDAOServizio;
import it.unibas.icar.freesbee.persistenza.IDAOSoggetto;
import it.unibas.icar.freesbee.persistenza.hibernate.DAOUtilHibernate;
import it.unibas.icar.freesbee.processors.ProcessorIdentificaNica;
import it.unibas.icar.freesbee.processors.ProcessorLogFactory;
import it.unibas.icar.freesbee.processors.ProcessorSbloccaPollingConsumerPortaApplicativa;
import it.unibas.icar.freesbee.utilita.CostantiBusta;
import it.unibas.icar.freesbee.utilita.FreesbeeCamel;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;

@Singleton
public class ContentBasedRouterSbustamentoNica extends RouteBuilder {

    private static Log logger = LogFactory.getLog(ContentBasedRouterSbustamentoNica.class);
    @Inject
    private ProcessorIdentificaNica processorIdentificaNica;
    @Inject
    private IDAOSoggetto daoSoggetto;
    @Inject
    private IDAOServizio daoServizio;
    @Inject
    private ProcessorSbloccaPollingConsumerPortaApplicativa processorSbloccaPollingConsumerPortaApplicativa;

    public void configure() throws Exception {
        processorSbloccaPollingConsumerPortaApplicativa.setEccezione(true);
        this.from(FreesbeeCamel.SEDA_CONTENT_BASED_ROUTER_NICA)
                .process(ProcessorLogFactory.getInstance().getProcessorLog(this.getClass()))
                .process(processorIdentificaNica)
                .choice()
                .when(header(CostantiBusta.RUOLO_NICA)
                        .isEqualTo(true))
                .to(FreesbeeCamel.SEDA_ENRICHER_NICA)
                .otherwise()
                .to(FreesbeeCamel.SEDA_ENRICHER_IDENTIFICATORE_EROGATORE);

        this.from(FreesbeeCamel.SEDA_ENRICHER_NICA)
                .process(ProcessorLogFactory.getInstance().getProcessorLog(this.getClass()))
                .doTry()
                .process(new ProcessorEnricherConnettore())
                .to(FreesbeeCamel.SEDA_PREIMBUSTAMENTO_RICHIESTA)
                .doCatch(Exception.class)
                .process(processorSbloccaPollingConsumerPortaApplicativa);
    }

    private class ProcessorEnricherConnettore implements Processor {

        public void process(Exchange exchange) throws Exception {
            Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiBusta.MESSAGGIO);

            String erogatore = messaggio.getErogatore();
            String tipoErogatore = messaggio.getTipoErogatore();
            if (erogatore == null || tipoErogatore == null) {
                throw new FreesbeeException("Nessun erogatore specificato");
            }

            String servizio = messaggio.getServizio();
            String tipoServizio = messaggio.getTipoServizio();
            if (servizio == null || tipoServizio == null) {
                throw new FreesbeeException("Nessun servizio specificato");
            }

            SessionFactory sessionFactory = null;
            Soggetto soggettoErogatore = null;
            Servizio servizioErogatore = null;
            try {
                sessionFactory = DAOUtilHibernate.getSessionFactory();
                sessionFactory.getCurrentSession().beginTransaction();
                soggettoErogatore = daoSoggetto.findByNome(erogatore, tipoErogatore);
                servizioErogatore = daoServizio.findByNome(servizio, tipoServizio, soggettoErogatore);
                sessionFactory.getCurrentSession().getTransaction().commit();
            } catch (Exception ex) {
                logger.error("Errore nella lettura delle porte delegate " + ex);
                try {
                    if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
                        sessionFactory.getCurrentSession().getTransaction().rollback();
                    }
                } catch (Throwable rbEx) {
                    logger.error("Could not rollback transaction after exception!", rbEx);
                }
            }
            if (soggettoErogatore == null) {
                throw new FreesbeeException("Erogatore sconosciuto!");
            }

            if ((servizioErogatore.getUrlServizio() == null) || (servizioErogatore.getUrlServizio().equals(""))) {
                messaggio.setConnettoreErogatore(soggettoErogatore.getPortaDominio());
                messaggio.setMutuaAutenticazione(soggettoErogatore.isMutuaAutenticazione());
            } else {
                messaggio.setConnettoreErogatore(servizioErogatore.getUrlServizio());
                messaggio.setMutuaAutenticazione(servizioErogatore.isMutuaAutenticazione());
            }
        }
    }

    public ProcessorSbloccaPollingConsumerPortaApplicativa getProcessorSbloccaPollingConsumerPortaApplicativa() {
        return processorSbloccaPollingConsumerPortaApplicativa;
    }

    public void setProcessorSbloccaPollingConsumerPortaApplicativa(ProcessorSbloccaPollingConsumerPortaApplicativa processorSbloccaPollingConsumerPortaApplicativa) {
        this.processorSbloccaPollingConsumerPortaApplicativa = processorSbloccaPollingConsumerPortaApplicativa;
    }

    public ProcessorIdentificaNica getProcessorIdentificaNica() {
        return processorIdentificaNica;
    }

    public void setProcessorIdentificaNica(ProcessorIdentificaNica processorIdentificaNica) {
        this.processorIdentificaNica = processorIdentificaNica;
    }

    public IDAOSoggetto getDaoSoggetto() {
        return daoSoggetto;
    }

    public void setDaoSoggetto(IDAOSoggetto daoSoggetto) {
        this.daoSoggetto = daoSoggetto;
    }

    public IDAOServizio getDaoServizio() {
        return daoServizio;
    }

    public void setDaoServizio(IDAOServizio daoServizio) {
        this.daoServizio = daoServizio;
    }
}
