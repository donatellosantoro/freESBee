package it.unibas.silvio.erogatore;

import it.unibas.silvio.modello.ConfigurazioneStatico;
import it.unibas.silvio.persistenza.IDAOMessaggioSbloccoManuale;
import it.unibas.silvio.processor.ProcessorEnricherMessaggioSblocco;
import it.unibas.silvio.processor.ProcessorEstraiIDMessaggio;
import it.unibas.silvio.processor.ProcessorHibernateBegin;
import it.unibas.silvio.processor.ProcessorHibernateCommit;
import it.unibas.silvio.processor.ProcessorHibernateRollback;
import it.unibas.silvio.processor.ProcessorLog;
import it.unibas.silvio.util.CostantiCamel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpIngressoSbloccoAsincrono extends RouteBuilder{
    
    private Log logger = LogFactory.getLog(this.getClass());
    private IDAOMessaggioSbloccoManuale daoMessaggioSblocco;
    private String indirizzoHttp;

    @Override
    public void configure() throws Exception {
        ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();
        String port = conf.getAsincronoPort();
        indirizzoHttp = port + "/asincrono/";
        String indirizzoPublic = "jetty:http://0.0.0.0:" + indirizzoHttp;
        if (logger.isInfoEnabled()) logger.info("Avvio un'istanza per lo sblocco dei messaggi asincroni all'indirizzo " + indirizzoPublic);
        
        this.from(indirizzoPublic)
            .tryBlock()
                .process(new ProcessorLog(this.getClass()))
                .process(new ProcessorHibernateBegin())
                .process(new ProcessorEstraiIDMessaggio())
                .process(new ProcessorEnricherMessaggioSblocco(daoMessaggioSblocco))
                .process(new ProcessorHibernateCommit())
                .to(CostantiCamel.HTTP_USCITA_EROGATORE)            
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
