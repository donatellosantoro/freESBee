package it.unibas.silvio.fruitore;

import it.unibas.silvio.processor.ProcessorHibernateCommit;
import it.unibas.silvio.processor.ProcessorHibernateBegin;
import it.unibas.silvio.processor.ProcessorHibernateRollback;
import it.unibas.silvio.processor.ProcessorTransformerCompletiSoapFruitore;
import it.unibas.silvio.modello.ConfigurazioneStatico;
import it.unibas.silvio.persistenza.IDAOConfigurazione;
import it.unibas.silvio.processor.ProcessorLog;
import it.unibas.silvio.processor.ProcessorSbloccaPollingConsumerFruitore;
import it.unibas.silvio.util.CostantiCamel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProducerCompletiSoapFruitore extends RouteBuilder{
    
    private Log logger = LogFactory.getLog(this.getClass());
    private IDAOConfigurazione daoConfigurazione;

    @Override
    public void configure() throws Exception {
        this.from(CostantiCamel.PRODUCER_COMPLETI_SOAP_FRUITORE)                
                .thread(ConfigurazioneStatico.getInstance().getThreadPool())
                .tryBlock()
                    .process(new ProcessorLog(this.getClass()))
                    .process(new ProcessorHibernateBegin())
                    .process(new ProcessorTransformerCompletiSoapFruitore(daoConfigurazione))
                    .process(new ProcessorHibernateCommit())
                    .to(CostantiCamel.MESSAGE_STORE_RICHIESTA_FRUITORE)
                .handle(Exception.class)
                    .process(new ProcessorSbloccaPollingConsumerFruitore(true))
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
