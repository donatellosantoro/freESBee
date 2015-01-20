package it.unibas.icar.freesbee.modulocontrollo.sbustamento;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import it.unibas.icar.freesbee.exception.FreesbeeException;
import it.unibas.icar.freesbee.modello.Configurazione;
import it.unibas.icar.freesbee.modello.ConfigurazioneStatico;
import it.unibas.icar.freesbee.modello.CostantiEccezioni;
import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.DBManager;
import it.unibas.icar.freesbee.persistenza.IDAOConfigurazione;
import it.unibas.icar.freesbee.persistenza.IDAOMessaggio;
import it.unibas.icar.freesbee.persistenza.hibernate.DAOConfigurazioneHibernate;
import it.unibas.icar.freesbee.persistenza.hibernate.DAOUtilHibernate;
import it.unibas.icar.freesbee.processors.ProcessorErroreSbustamento;
import it.unibas.icar.freesbee.processors.ProcessorLogFactory;
import it.unibas.icar.freesbee.processors.ProcessorWrapper;
import it.unibas.icar.freesbee.utilita.CostantiBusta;
import it.unibas.icar.freesbee.utilita.FreesbeeCamel;
import it.unibas.icar.freesbee.utilita.TracciamentoFileUtil;
import java.io.IOException;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;

@Singleton
public class WireTapTracciamentoSbustamento extends RouteBuilder {

    private static Log logger = LogFactory.getLog(WireTapTracciamentoSbustamento.class);
    @Inject
    private IDAOMessaggio daoMessaggio;
    @Inject
    private ProcessorWrapper processorWrapper;
    @Inject
    private ProcessorErroreSbustamento processorErroreSbustamento;
    @Inject
    private DBManager dbManager;

    public void configure() throws Exception {
        this.from(FreesbeeCamel.SEDA_WIRETAP_TRACCIAMENTO_SBUSTAMENTO)
//                .threads(ConfigurazioneStatico.getInstance().getCamelThreadPool(),
//                ConfigurazioneStatico.getInstance().getCamelThreadPoolMax())
                //.process(new ProcessorLog(this.getClass()))
            .process(ProcessorLogFactory.getInstance().getProcessorLog(this.getClass()))
            .doTry()
                .process(new ProcessorStore())
                .to(FreesbeeCamel.SEDA_CONTENT_BASED_ROUTER_SBUSTAMENTO)
            .doCatch(Exception.class)
                .process(processorErroreSbustamento);
    }

    private class ProcessorStore implements Processor {

        public void process(Exchange exchange) throws Exception {
//            if(true)return;
            //ContextStartup.aggiungiThread(this.getClass().getName());
            Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiBusta.MESSAGGIO);
            settaTipoMessaggio(messaggio, exchange);
            String idMessaggio = messaggio.getIdMessaggio();
            if (idMessaggio == null || idMessaggio.isEmpty()) {
                return;
            }
            String idSil = messaggio.getIdSil();
            SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
            try {
                //VERIFICA TRACCIATURA SU FILE
                ConfigurazioneStatico configurazioneStatico = ConfigurazioneStatico.getInstance();
                if (configurazioneStatico.isTracciaFile()) {
                    IDAOConfigurazione daoConfigurazione = new DAOConfigurazioneHibernate();
                    Configurazione configurazione = dbManager.getConfigurazione();
                    messaggio.setTempo(configurazione.getTempo());
                    TracciamentoFileUtil.creaFile(messaggio, configurazioneStatico.getPercorsoFile());
                }

                sessionFactory.getCurrentSession().beginTransaction();
                if (daoMessaggio.findByIdMessaggio(idMessaggio, Messaggio.TIPO_RICEVUTO) != null) {
//                    logger.error("E' stato gia' ricevuto un messaggio con ID-EGOV = " + idMessaggio);
                    messaggio.aggiungiEccezione(CostantiEccezioni.IDENTIFICATORE_DUPLICATO);
                    throw new FreesbeeException("E' stato gia' ricevuto un messaggio con ID-EGOV = " + idMessaggio);
//                    return;
                }
//                    if (idSil != null && daoMessaggio.findByIDSil(idSil, Messaggio.TIPO_RICEVUTO) != null) {
//                        if (logger.isInfoEnabled()) logger.info("E' stato gia' ricevuto un messaggio con questo ID " + idSil);
//                        //TODO: Se utilizziamo freESBee fruitore e erogatore sulla stessa macchina possono essere generati due volte gli stessi ID.
//                        //throw new FreesbeeException("E' stato gia' ricevuto un messaggio con questo ID " + idSil, 104);
//                    }
                
                if ((!messaggio.getListaEccezioni().isEmpty()) && (idSil == null)) {
//                    logger.error("E' stato ricevuto un messaggio con ID-SIL nullo.");
                    messaggio.aggiungiEccezione(CostantiEccezioni.IDENTIFICATORE_NON_VALORIZZATO);
                    throw new FreesbeeException("E' stato ricevuto un messaggio con ID-SIL nullo.");
//                    return;
                }
                
                if (daoMessaggio.findByIDSil(idSil, Messaggio.TIPO_RICEVUTO) != null) {
//                    logger.error("E' stato gia' ricevuto un messaggio con ID-SIL = " + idSil);
                    messaggio.aggiungiEccezione(CostantiEccezioni.IDENTIFICATORE_DUPLICATO);
                    throw new FreesbeeException("E' stato gia' ricevuto un messaggio con ID-SIL = " + idSil);
//                    return;
                }

                if (logger.isInfoEnabled()) logger.info("Salvo il messaggio con ID-SIL: " + messaggio.getIdSil());
                daoMessaggio.makePersistent(messaggio);
                sessionFactory.getCurrentSession().getTransaction().commit();
            } catch (DAOException ex) {
                if (logger.isDebugEnabled()) ex.printStackTrace();
                throw new FreesbeeException("Errore nella lettura dal database " + ex);
            } catch (IOException ioe) {
                logger.error("ERRORE! Impossibile creare il file di tracciatura");
                if (logger.isDebugEnabled()) ioe.printStackTrace();
            } finally {
                try {
                    if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
                        sessionFactory.getCurrentSession().getTransaction().rollback();
                    }
                } catch (Throwable rbEx) {
                }
            }
        }

        private void settaTipoMessaggio(Messaggio messaggio, Exchange exchange) {
            String tipoSbusta = (String) exchange.getIn().getHeader(CostantiBusta.SBUSTA_RICHIESTA_RISPOSTA);
            if (CostantiBusta.VALORE_SBUSTA_RICHIESTA.equalsIgnoreCase(tipoSbusta)) {
                messaggio.setTipoMessaggio(Messaggio.TIPO_MESSAGGIO_RICHIESTA);
            }
            if (CostantiBusta.VALORE_SBUSTA_RISPOSTA.equalsIgnoreCase(tipoSbusta)) {
                messaggio.setTipoMessaggio(Messaggio.TIPO_MESSAGGIO_RISPOSTA);
            }
        }
    }

    public IDAOMessaggio getDaoMessaggio() {
        return daoMessaggio;
    }

    public void setDaoMessaggio(IDAOMessaggio daoMessaggio) {
        this.daoMessaggio = daoMessaggio;
    }

    public ProcessorWrapper getProcessorWrapper() {
        return processorWrapper;
    }

    public void setProcessorWrapper(ProcessorWrapper processorWrapper) {
        this.processorWrapper = processorWrapper;
    }

    public ProcessorErroreSbustamento getProcessorErroreSbustamento() {
        return processorErroreSbustamento;
    }

    public void setProcessorErroreSbustamento(ProcessorErroreSbustamento processorErroreSbustamento) {
        this.processorErroreSbustamento = processorErroreSbustamento;
    }

    public DBManager getDbManager() {
        return dbManager;
    }

    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }
    
    
}