package it.unibas.silvio.fruitore;

import it.unibas.silvio.modello.ConfigurazioneStatico;
import it.unibas.silvio.modello.Messaggio;
import it.unibas.silvio.processor.ProcessorLog;
import it.unibas.silvio.processor.ProcessorSbloccaPollingConsumerFruitore;
import it.unibas.silvio.util.CostantiCamel;
import it.unibas.silvio.util.CostantiSilvio;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RispostaAckFruitore extends RouteBuilder{
    
    private Log logger = LogFactory.getLog(this.getClass());

    @Override
    public void configure() throws Exception {
        this.from(CostantiCamel.RISPOSTA_ACK_FRUITORE)   
            .thread(ConfigurazioneStatico.getInstance().getThreadPool())
                .process(new ProcessorLog(this.getClass()))
                .process(new ProcessorAck())
                .process(new ProcessorSbloccaPollingConsumerFruitore(false));
    }
    
    private class ProcessorAck implements Processor{

        public void process(Exchange exchange) throws Exception {
            Messaggio messaggio = (Messaggio)exchange.getProperty(CostantiSilvio.MESSAGGIO_RICHIESTA);
            Messaggio messaggioAck = new Messaggio();
            messaggioAck.setChannelFruitore(messaggio.getChannelFruitore());
            messaggioAck.setTipo(Messaggio.ACK);
            exchange.setProperty(CostantiSilvio.MESSAGGIO_RISPOSTA, messaggioAck);
        }
        
    }

}
