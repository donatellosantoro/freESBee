package it.unibas.silvio.erogatorehttp;

import it.unibas.silvio.modello.IstanzaPortType;
import it.unibas.silvio.modello.Messaggio;
import it.unibas.silvio.processor.ProcessorLog;
import it.unibas.silvio.processor.ProcessorPollingErogatore;
import it.unibas.silvio.processor.ProcessorUpdateSLA;
import it.unibas.silvio.util.CostantiCamel;
import it.unibas.silvio.util.CostantiSilvio;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpErogatoreIngressoDati extends RouteBuilder{

    private IstanzaPortType istanzaPortType;
    private Log logger = LogFactory.getLog(this.getClass());
    private String indirizzoHttp;
    private String portaAscolto;

    public HttpErogatoreIngressoDati(IstanzaPortType istanzaPortType,String portaAscolto) {
        this.istanzaPortType = istanzaPortType;
        this.portaAscolto = portaAscolto;
    }

    @Override
    public void configure() throws Exception {
        String indirizzoAscolto = istanzaPortType.getURLAscolto();
        indirizzoHttp = portaAscolto + indirizzoAscolto;
        String indirizzo = "jetty:http://0.0.0.0:" + indirizzoHttp;        
        if (logger.isInfoEnabled()) logger.info("Avvio un'istanza di erogazione all'indirizzo " + indirizzo);
        if (logger.isInfoEnabled()) logger.info("ErogazioneRisposta: " + istanzaPortType.isErogazioneRisposta());
        
        this.from(indirizzo)
                .process(new ProcessorLog(this.getClass()))
                .process(new ProcessorCreaMessaggio())
            .to(CostantiCamel.ENRICHER_OPERATION_EROGATORE)
                .process(new ProcessorPollingErogatore(getContext()))
                .process(new ProcessorUpdateSLA(ProcessorUpdateSLA.IDLE))
                .process(new ProcessorUpdateSLA(ProcessorUpdateSLA.INSERT));
    }
        
    
    private class ProcessorCreaMessaggio implements Processor{

        public void process(Exchange exchange) throws Exception {
            Messaggio messaggio = new Messaggio();
            messaggio.setTipo(Messaggio.RICEVUTO);
            messaggio.setIstanzaPortType(istanzaPortType.getNome());
            messaggio.setChannelErogatore(exchange.getExchangeId());
            messaggio.setIndirizzo("http://localhost:" + indirizzoHttp);
            messaggio.setMessaggio(exchange.getIn().getBody(String.class));
            exchange.setProperty(CostantiSilvio.MESSAGGIO_RICHIESTA, messaggio);
            exchange.setProperty(CostantiSilvio.ISTANZA_PORTTYPE, istanzaPortType);
        }
        
    }

}
