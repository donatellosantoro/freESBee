package it.unibas.silvio.erogatore;

import it.unibas.silvio.modello.ConfigurazioneStatico;
import it.unibas.silvio.persistenza.*;
import it.unibas.silvio.processor.ProcessorEnricherCorrelazioneRispostaSPC;
import it.unibas.silvio.processor.ProcessorEnricherHeaderRispostaSilvio;
import it.unibas.silvio.processor.ProcessorEnricherIntestazioniCorrelazioneRisposta;
import it.unibas.silvio.processor.ProcessorHibernateBegin;
import it.unibas.silvio.processor.ProcessorHibernateCommit;
import it.unibas.silvio.processor.ProcessorHibernateRollback;
import it.unibas.silvio.processor.ProcessorLog;
import it.unibas.silvio.processor.ProcessorMessageStoreRisposta;
import it.unibas.silvio.processor.ProcessorSendErogatore;
import it.unibas.silvio.util.CostantiCamel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpUscitaRispostaErogatore extends RouteBuilder{
    
    private Log logger = LogFactory.getLog(this.getClass());
    private IDAOMessaggio daoMessaggio;

    @Override
    public void configure() throws Exception {
        this.from(CostantiCamel.HTTP_USCITA_EROGATORE)   
            .thread(ConfigurazioneStatico.getInstance().getThreadPool())
            .tryBlock()             
                .process(new ProcessorLog(this.getClass()))
                .process(new ProcessorHibernateBegin())
                .process(new ProcessorEnricherIntestazioniCorrelazioneRisposta())
                .process(new ProcessorEnricherHeaderRispostaSilvio())
                .process(new ProcessorSendErogatore(getContext()))
                .process(new ProcessorEnricherCorrelazioneRispostaSPC())
                .process(new ProcessorMessageStoreRisposta(daoMessaggio))
                .process(new ProcessorHibernateCommit())
            .handle(Exception.class)  
                .process(new ProcessorHibernateRollback()) 
            .end();             
    }

    public IDAOMessaggio getDaoMessaggio() {
        return daoMessaggio;
    }

    public void setDaoMessaggio(IDAOMessaggio daoMessaggio) {
        this.daoMessaggio = daoMessaggio;
    }
}
