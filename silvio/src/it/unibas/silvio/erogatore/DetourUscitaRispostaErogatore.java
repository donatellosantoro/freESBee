package it.unibas.silvio.erogatore;

import it.unibas.silvio.processor.ProcessorLog;
import it.unibas.silvio.util.CostantiCamel;
import it.unibas.silvio.modello.*;
import it.unibas.silvio.persistenza.IDAOMessaggio;
import it.unibas.silvio.processor.ProcessorHibernateBegin;
import it.unibas.silvio.processor.ProcessorHibernateCommit;
import it.unibas.silvio.processor.ProcessorHibernateRollback;
import it.unibas.silvio.processor.ProcessorMessageStoreRisposta;
import it.unibas.silvio.processor.ProcessorSbloccaPollingConsumerErogatore;
import it.unibas.silvio.util.CostantiSilvio;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DetourUscitaRispostaErogatore extends RouteBuilder{
    
    private Log logger = LogFactory.getLog(this.getClass());
    private IDAOMessaggio daoMessaggio;

    @Override
    public void configure() throws Exception {
        this.from(CostantiCamel.DETOUR_USCITA_RISPOSTA_EROGATORE)     
            .thread(ConfigurazioneStatico.getInstance().getThreadPool())
            .process(new ProcessorLog(this.getClass()))
                .choice().when(header(CostantiSilvio.VALOREPROFILOCOLLABORAZIONE).isEqualTo(Operation.SINCRONO))
                    .to(CostantiCamel.GESTIONE_RISPOSTA_SINCRONA_EROGATORE)
                .otherwise()
                    .choice().when(header(CostantiSilvio.SBLOCCO_RICHIESTA_AUTOMATICA).isEqualTo(true))
                        .to(CostantiCamel.HTTP_USCITA_EROGATORE)
                    .otherwise()
                        .to(CostantiCamel.EVENT_DRIVEN_CONSUMER_SBLOCCO_RISPOSTA_MANUALE);
        
        
        this.from(CostantiCamel.GESTIONE_RISPOSTA_SINCRONA_EROGATORE)
                .thread(ConfigurazioneStatico.getInstance().getThreadPool())
                .tryBlock()
                    .process(new ProcessorHibernateBegin())
                    .process(new ProcessorMessageStoreRisposta(daoMessaggio))
                    .process(new ProcessorSbloccaPollingConsumerErogatore(false))
                    .process(new ProcessorHibernateCommit())
                .handle(Exception.class)            
                    .process(new ProcessorSbloccaPollingConsumerErogatore(true))
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
