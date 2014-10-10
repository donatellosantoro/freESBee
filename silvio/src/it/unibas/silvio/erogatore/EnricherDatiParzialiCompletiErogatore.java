package it.unibas.silvio.erogatore;

import it.unibas.silvio.modello.ConfigurazioneStatico;
import it.unibas.silvio.persistenza.IDAOConfigurazione;
import it.unibas.silvio.persistenza.IDAODati;
import it.unibas.silvio.processor.ProcessorEnricherAttachment;
import it.unibas.silvio.processor.ProcessorEnricherDatiParzialiCompletiErogatore;
import it.unibas.silvio.processor.ProcessorHibernateBegin;
import it.unibas.silvio.processor.ProcessorHibernateCommit;
import it.unibas.silvio.processor.ProcessorHibernateRollback;
import it.unibas.silvio.processor.ProcessorLog;
import it.unibas.silvio.processor.ProcessorSbloccaPollingConsumerErogatore;
import it.unibas.silvio.util.CostantiCamel;
import it.unibas.silvio.util.CostantiSilvio;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EnricherDatiParzialiCompletiErogatore extends RouteBuilder{
    
    private Log logger = LogFactory.getLog(this.getClass());
    private IDAOConfigurazione daoConfigurazione;
    private IDAODati daoDati;

    @Override
    public void configure() throws Exception {
        this.from(CostantiCamel.ENRICHER_DATI_PARZIALI_COMPLETI_EROGATORE)  
                .thread(ConfigurazioneStatico.getInstance().getThreadPool())
                .tryBlock()
                    .process(new ProcessorLog(this.getClass()))
                    .process(new ProcessorHibernateBegin())            
                    .process(new ProcessorEnricherDatiParzialiCompletiErogatore(daoConfigurazione,daoDati))
                    .process(new ProcessorEnricherAttachment(CostantiSilvio.MESSAGGIO_RISPOSTA))
                    .process(new ProcessorHibernateCommit())
                    .to(CostantiCamel.PRODUCER_COMPLETI_SOAP_EROGATORE)
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

    public IDAODati getDaoDati() {
        return daoDati;
    }

    public void setDaoDati(IDAODati daoDati) {
        this.daoDati = daoDati;
    }

    
}
