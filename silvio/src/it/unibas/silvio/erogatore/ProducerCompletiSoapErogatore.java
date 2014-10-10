package it.unibas.silvio.erogatore;

import it.unibas.silvio.modello.ConfigurazioneStatico;
import it.unibas.silvio.modello.Messaggio;
import it.unibas.silvio.modello.Operation;
import it.unibas.silvio.persistenza.DAOException;
import it.unibas.silvio.persistenza.IDAOConfigurazione;
import it.unibas.silvio.persistenza.IDAOMessaggio;
import it.unibas.silvio.persistenza.SilvioException;
import it.unibas.silvio.processor.ProcessorHibernateBegin;
import it.unibas.silvio.processor.ProcessorHibernateCommit;
import it.unibas.silvio.processor.ProcessorHibernateRollback;
import it.unibas.silvio.processor.ProcessorLog;
import it.unibas.silvio.processor.ProcessorSbloccaPollingConsumerErogatore;
import it.unibas.silvio.processor.ProcessorTransformerCompletiSoapErogatore;
import it.unibas.silvio.util.CostantiCamel;
import it.unibas.silvio.util.CostantiSilvio;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProducerCompletiSoapErogatore extends RouteBuilder{
    
    private Log logger = LogFactory.getLog(this.getClass());
    private IDAOConfigurazione daoConfigurazione;

    @Override
    public void configure() throws Exception {
        this.from(CostantiCamel.PRODUCER_COMPLETI_SOAP_EROGATORE)
                .thread(ConfigurazioneStatico.getInstance().getThreadPool())
                .tryBlock()
                    .process(new ProcessorLog(this.getClass()))
                    .process(new ProcessorHibernateBegin())
                    .process(new ProcessorTransformerCompletiSoapErogatore(daoConfigurazione))
                    .process(new ProcessorHibernateCommit())
                    .to(CostantiCamel.DETOUR_USCITA_RISPOSTA_EROGATORE)
                .handle(Exception.class)            
                    .process(new ProcessorSbloccaPollingConsumerErogatore(true))
                    .process(new ProcessorHibernateRollback())        
                .end();    
    }

    public IDAOConfigurazione getDaoConfigurazione() {
        return daoConfigurazione;
    }

    public void setDaoConfigurazione(IDAOConfigurazione daoConfigurazione) {
        this.daoConfigurazione = daoConfigurazione;
    }

}
