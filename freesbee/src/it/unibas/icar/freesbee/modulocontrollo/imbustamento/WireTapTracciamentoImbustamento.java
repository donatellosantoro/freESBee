package it.unibas.icar.freesbee.modulocontrollo.imbustamento;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import it.unibas.icar.freesbee.exception.FreesbeeException;
import it.unibas.icar.freesbee.modello.Configurazione;
import it.unibas.icar.freesbee.modello.ConfigurazioneStatico;
import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.DBManager;
import it.unibas.icar.freesbee.persistenza.IDAOMessaggio;
import it.unibas.icar.freesbee.persistenza.hibernate.DAOUtilHibernate;
import it.unibas.icar.freesbee.processors.ProcessorErroreImbustamento;
import it.unibas.icar.freesbee.processors.ProcessorLogFactory;
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
public class WireTapTracciamentoImbustamento extends RouteBuilder {

    private static Log logger = LogFactory.getLog(WireTapTracciamentoImbustamento.class);
    @Inject
    private IDAOMessaggio daoMessaggio;
    @Inject
    private DBManager dbManager;
    @Inject
    private ProcessorErroreImbustamento processorErroreImbustamento;

    public void configure() throws Exception {
        this.from(FreesbeeCamel.SEDA_WIRETAP_TRACCIAMENTO_IMBUSTAMENTO)
                //                .threads(ConfigurazioneStatico.getInstance().getCamelThreadPool(),
                //                ConfigurazioneStatico.getInstance().getCamelThreadPoolMax())
                //.process(new ProcessorLog(this.getClass()))
                .process(ProcessorLogFactory.getInstance().getProcessorLog(this.getClass()))
                .doTry()
                .process(new ProcessorStore())
                .to(FreesbeeCamel.SEDA_ENRICHER_TEST)
                .doCatch(Exception.class)
                .process(processorErroreImbustamento);
    }

    private class ProcessorStore implements Processor {

        public void process(Exchange exchange) throws Exception {
//            if(true)return;
            Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiBusta.MESSAGGIO);
            settaTipoMessaggio(messaggio, exchange);
            String idMessaggio = messaggio.getIdMessaggio();
            String idSil = messaggio.getIdSil();
            SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
            ConfigurazioneStatico configurazioneStatico = ConfigurazioneStatico.getInstance();
            try {
                sessionFactory.getCurrentSession().beginTransaction();
                if (idMessaggio != null && daoMessaggio.findByIdMessaggio(idMessaggio, Messaggio.TIPO_INVIATO) != null) {
                    if (logger.isInfoEnabled()) logger.info("E' stato gia' inviato un messaggio con questo ID Egov!" + idMessaggio);
//                    messaggio.aggiungiEccezione(CostantiEccezioni.IDENTIFICATORE_DUPLICATO); //TODO: Se viene lanciato qui significa che e' un errore di richiesta, quindi da non aggiungere
                    throw new FreesbeeException("E' stato gia' inviato un messaggio con questo ID Egov! " + idMessaggio);
                }
//                if (idSil != null && daoMessaggio.findByIDSil(idSil, Messaggio.TIPO_INVIATO) != null) {
//                    if (logger.isInfoEnabled()) logger.info("E' stato gia' inviato un messaggio con questo ID SIL" + idSil);
//                    //TODO: Se utilizziamo freESBee fruitore e erogatore sulla stessa macchina possono essere generati due volte gli stessi ID.
//                    //throw new FreesbeeException("E' stato gia' inviato un messaggio con questo ID " + idSil, 110);
//                }
                if (logger.isInfoEnabled()) logger.info("Salvo il messaggio con ID-Sil: " + messaggio.getIdSil());
                daoMessaggio.makePersistent(messaggio);
                sessionFactory.getCurrentSession().getTransaction().commit();
                if (configurazioneStatico.isTracciaFile()) {
                    Configurazione configurazione = dbManager.getConfigurazione();
                    messaggio.setTempo(configurazione.getTempo());
                    TracciamentoFileUtil.creaFile(messaggio, configurazioneStatico.getPercorsoFile());
                }
            } catch (DAOException ex) {
                logger.error("Errore nella lettura dal database " + ex);
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
            String tipoImbusta = (String) exchange.getIn().getHeader(CostantiBusta.IMBUSTA_RICHIESTA_RISPOSTA);
            if (CostantiBusta.VALORE_IMBUSTA_RICHIESTA.equalsIgnoreCase(tipoImbusta)) {
                messaggio.setTipoMessaggio(Messaggio.TIPO_MESSAGGIO_RICHIESTA);
            }
            if (CostantiBusta.VALORE_IMBUSTA_RISPOSTA.equalsIgnoreCase(tipoImbusta)) {
                messaggio.setTipoMessaggio(Messaggio.TIPO_MESSAGGIO_RISPOSTA);
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

    public DBManager getDbManager() {
        return dbManager;
    }

    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }
}