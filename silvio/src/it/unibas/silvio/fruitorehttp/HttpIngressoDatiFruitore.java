package it.unibas.silvio.fruitorehttp;

import it.unibas.silvio.modello.IstanzaPortType;
import it.unibas.silvio.modello.Messaggio;
import it.unibas.silvio.processor.ProcessorLog;
import it.unibas.silvio.processor.ProcessorPollingFruitore;
import it.unibas.silvio.util.CostantiCamel;
import it.unibas.silvio.util.CostantiSilvio;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpIngressoDatiFruitore extends RouteBuilder{
    
    private IstanzaPortType istanzaPortType;
    private Log logger = LogFactory.getLog(this.getClass());
    private String indirizzoHttp;
    private String portaAscolto;
    
    public HttpIngressoDatiFruitore(IstanzaPortType istanzaPortType,String portaAscolto){
        this.istanzaPortType = istanzaPortType;
        this.portaAscolto = portaAscolto;
    }
    
    @Override
    public void configure() throws Exception {
        String indirizzoAscolto = istanzaPortType.getURLAscolto();
        indirizzoHttp = portaAscolto + indirizzoAscolto;
        String indirizzo = "jetty:http://0.0.0.0:" + indirizzoHttp;
        if (logger.isInfoEnabled()) logger.info("Avvio un'istanza di fruizione all'indirizzo " + indirizzo);
        
        this.from(indirizzo)
            .process(new ProcessorLog(this.getClass()))
            .process(new ProcessorCreaMessaggio())
            .to(CostantiCamel.ENRICHER_OPERATION_FRUITORE)
            .process(new ProcessorPollingFruitore(getContext()));
    }
    
    private class ProcessorCreaMessaggio implements Processor{

        public void process(Exchange exchange) throws Exception {
            exchange.getIn().getHeaders().clear();
            Messaggio messaggio = new Messaggio();
            messaggio.creaIdentificatore();
            messaggio.setTipo(Messaggio.INVIATO);
            messaggio.setIstanzaPortType(istanzaPortType.getNome());
            messaggio.setChannelFruitore(exchange.getExchangeId());
            messaggio.setIndirizzo("http://localhost:" + indirizzoHttp);
            exchange.setProperty(CostantiSilvio.MESSAGGIO_RICHIESTA, messaggio);
            exchange.setProperty(CostantiSilvio.ISTANZA_PORTTYPE, istanzaPortType);
        }
        
    }
    
}
