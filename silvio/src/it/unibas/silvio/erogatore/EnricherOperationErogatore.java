package it.unibas.silvio.erogatore;

import it.unibas.silvio.modello.ConfigurazioneStatico;
import it.unibas.silvio.persistenza.IDAOIstanzaPortType;
import it.unibas.silvio.processor.ProcessorEnricherLeggiIntestazioniCorrelazione;
import it.unibas.silvio.processor.ProcessorEnricherLeggiIntestazioniSLA;
import it.unibas.silvio.processor.ProcessorEnricherMessage;
import it.unibas.silvio.processor.ProcessorEnricherParametriMessaggioRicevuto;
import it.unibas.silvio.processor.ProcessorHibernateBegin;
import it.unibas.silvio.processor.ProcessorHibernateCommit;
import it.unibas.silvio.processor.ProcessorHibernateRollback;
import it.unibas.silvio.processor.ProcessorLog;
import it.unibas.silvio.processor.ProcessorSOAPReader;
import it.unibas.silvio.processor.ProcessorSbloccaPollingConsumerErogatore;
import it.unibas.silvio.util.CostantiCamel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EnricherOperationErogatore extends RouteBuilder{
    
    private Log logger = LogFactory.getLog(this.getClass());
    private IDAOIstanzaPortType daoIstanzaPortType;

    @Override
    public void configure() throws Exception {
        this.from(CostantiCamel.ENRICHER_OPERATION_EROGATORE)   
                .thread(ConfigurazioneStatico.getInstance().getThreadPool())
                .tryBlock()
                    .process(new ProcessorLog(this.getClass()))
                    .process(new ProcessorHibernateBegin())
                    .process(new ProcessorSOAPReader())
                    .process(new ProcessorEnricherParametriMessaggioRicevuto())
                    .process(new ProcessorEnricherMessage(daoIstanzaPortType))                    
                    .process(new ProcessorEnricherLeggiIntestazioniCorrelazione())                    
                    .process(new ProcessorEnricherLeggiIntestazioniSLA())
                    .process(new ProcessorHibernateCommit())
                    .to(CostantiCamel.MESSAGE_STORE_DB_EROGATORE)
                .handle(Exception.class)            
                    .process(new ProcessorSbloccaPollingConsumerErogatore(true))
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
