package it.unibas.silvio.erogatore;

import it.unibas.silvio.processor.ProcessorHibernateBegin;
import it.unibas.silvio.processor.ProcessorHibernateCommit;
import it.unibas.silvio.processor.ProcessorHibernateRollback;
import it.unibas.silvio.processor.ProcessorLog;
import it.unibas.silvio.util.CostantiCamel;
import it.unibas.silvio.modello.*;
import it.unibas.silvio.persistenza.*;
import it.unibas.silvio.processor.ProcessorCorrelaMessaggio;
import it.unibas.silvio.processor.ProcessorEseguiInsertRichiestaErogatore;
import it.unibas.silvio.processor.ProcessorInviaMailErogatore;
import it.unibas.silvio.processor.ProcessorSbloccaPollingConsumerErogatore;
import it.unibas.silvio.processor.ProcessorUpdateSLA;
import it.unibas.silvio.util.CostantiSilvio;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MessageStoreDBErogatore extends RouteBuilder{
    
    private Log logger = LogFactory.getLog(this.getClass());
    private IDAOMessaggio daoMessaggio;
    private IDAOConfigurazione daoConfigurazione;

    @Override
    public void configure() throws Exception {
        this.from(CostantiCamel.MESSAGE_STORE_DB_EROGATORE)     
            .thread(ConfigurazioneStatico.getInstance().getThreadPool())
            .tryBlock()             
                .process(new ProcessorLog(this.getClass()))
                .process(new ProcessorHibernateBegin())
                .process(new ProcessorCorrelaMessaggio(daoMessaggio))
                .process(new ProcessorMessageStoreRichiesta())
                .process(new ProcessorUpdateSLA(ProcessorUpdateSLA.PROCESSING))
                .process(new ProcessorEseguiInsertRichiestaErogatore(daoConfigurazione))
                .process(new ProcessorInviaMailErogatore(daoConfigurazione))
                .process(new ProcessorHibernateCommit())
                .to(CostantiCamel.DETOUR_GESTIONE_RISPOSTA_EROGATORE)
            .handle(Exception.class)  
                .process(new ProcessorSbloccaPollingConsumerErogatore(true))
                .process(new ProcessorHibernateRollback()) 
            .end();       
    }
    
    private class ProcessorMessageStoreRichiesta implements Processor {

        public void process(Exchange exchange) throws Exception {
            Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiSilvio.MESSAGGIO_RICHIESTA);
            try {
                daoMessaggio.makePersistent(messaggio);
            } catch (DAOException ex) {
                logger.error("Impossibile salvare il messaggio inviato " + ex);
                throw new SilvioException("Impossibile salvare il messaggio inviato " + ex);
            }
        }
    }


    public IDAOMessaggio getDaoMessaggio() {
        return daoMessaggio;
    }

    public void setDaoMessaggio(IDAOMessaggio daoMessaggio) {
        this.daoMessaggio = daoMessaggio;
    }

    public IDAOConfigurazione getDaoConfigurazione() {
        return daoConfigurazione;
    }

    public void setDaoConfigurazione(IDAOConfigurazione daoConfigurazione) {
        this.daoConfigurazione = daoConfigurazione;
    }
    
    

    
}
