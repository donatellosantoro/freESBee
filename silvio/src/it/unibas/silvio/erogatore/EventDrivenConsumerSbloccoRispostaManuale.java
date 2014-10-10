package it.unibas.silvio.erogatore;

import it.unibas.silvio.modello.ConfigurazioneStatico;
import it.unibas.silvio.persistenza.IDAOMessaggioSbloccoManuale;
import it.unibas.silvio.processor.ProcessorEventDrivenConsumerSbloccoRisposta;
import it.unibas.silvio.processor.ProcessorHibernateBegin;
import it.unibas.silvio.processor.ProcessorHibernateCommit;
import it.unibas.silvio.processor.ProcessorHibernateRollback;
import it.unibas.silvio.processor.ProcessorLog;
import it.unibas.silvio.util.CostantiCamel;
import org.apache.camel.builder.RouteBuilder;

public class EventDrivenConsumerSbloccoRispostaManuale extends RouteBuilder{
    
    private IDAOMessaggioSbloccoManuale daoMessaggioSblocco;

    @Override
    public void configure() throws Exception {
        this.from(CostantiCamel.EVENT_DRIVEN_CONSUMER_SBLOCCO_RISPOSTA_MANUALE)
                .thread(ConfigurazioneStatico.getInstance().getThreadPool())
                    .tryBlock()
                        .process(new ProcessorLog(this.getClass()))
                        .process(new ProcessorHibernateBegin())
                        .process(new ProcessorEventDrivenConsumerSbloccoRisposta(daoMessaggioSblocco))
                        .process(new ProcessorHibernateCommit())
                    .handle(Exception.class)
                        .process(new ProcessorHibernateRollback()) 
                    .end();
    }

    public IDAOMessaggioSbloccoManuale getDaoMessaggioSblocco() {
        return daoMessaggioSblocco;
    }

    public void setDaoMessaggioSblocco(IDAOMessaggioSbloccoManuale daoMessaggioSblocco) {
        this.daoMessaggioSblocco = daoMessaggioSblocco;
    }   
       
}
