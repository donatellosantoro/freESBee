package it.unibas.silvio.fruitore;

import it.unibas.silvio.modello.ConfigurazioneStatico;
import it.unibas.silvio.persistenza.IDAOIstanzaPortType;
import it.unibas.silvio.processor.ProcessorEnricherCorrelazioneWSA;
import it.unibas.silvio.processor.ProcessorEnricherOperation;
import it.unibas.silvio.processor.ProcessorHibernateBegin;
import it.unibas.silvio.processor.ProcessorHibernateCommit;
import it.unibas.silvio.processor.ProcessorHibernateRollback;
import it.unibas.silvio.processor.ProcessorLog;
import it.unibas.silvio.processor.ProcessorParametriEseguiIstanza;
import it.unibas.silvio.processor.ProcessorSbloccaPollingConsumerFruitore;
import it.unibas.silvio.util.CostantiCamel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EnricherOperationFruitore extends RouteBuilder{
    
    private Log logger = LogFactory.getLog(this.getClass());
    private IDAOIstanzaPortType daoIstanzaPortType;

    @Override
    public void configure() throws Exception {
        this.from(CostantiCamel.ENRICHER_OPERATION_FRUITORE)
                .thread(ConfigurazioneStatico.getInstance().getThreadPool())
                .tryBlock()
                    .process(new ProcessorLog(this.getClass()))
                    .process(new ProcessorHibernateBegin())
                    .process(new ProcessorParametriEseguiIstanza())
                    .process(new ProcessorEnricherOperation(daoIstanzaPortType))
                    .process(new ProcessorEnricherCorrelazioneWSA())
                    .process(new ProcessorHibernateCommit())
                    .to(CostantiCamel.ENRICHER_DATI_PARZIALI_COMPLETI_FRUITORE)
                .handle(Exception.class)            
                    .process(new ProcessorSbloccaPollingConsumerFruitore(true))
                    .process(new ProcessorHibernateRollback())        
                .end();
    }

    public IDAOIstanzaPortType getDaoIstanzaPortType() {
        return daoIstanzaPortType;
    }

    public void setDaoIstanzaPortType(IDAOIstanzaPortType daoIstanzaPortType) {
        this.daoIstanzaPortType = daoIstanzaPortType;
    }
    
    
    
}
