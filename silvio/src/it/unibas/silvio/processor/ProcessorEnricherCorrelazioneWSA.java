package it.unibas.silvio.processor;

import it.unibas.silvio.modello.Messaggio;
import it.unibas.silvio.modello.ParametriEseguiIstanza;
import it.unibas.silvio.util.CostantiSilvio;
import it.unibas.silvio.util.SilvioUtil;
import java.util.List;
import javax.xml.soap.SOAPElement;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProcessorEnricherCorrelazioneWSA implements Processor {

    private Log logger = LogFactory.getLog(this.getClass());

    public void process(Exchange exchange) throws Exception {
        Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiSilvio.MESSAGGIO_RICHIESTA);
        ParametriEseguiIstanza parametriIstanza = messaggio.getParametriEseguiIstanza();
        if (parametriIstanza.getCorrelazione().equals(CostantiSilvio.CORRELAZIONE_SPCOOP)) {
            return;
        }
        
//      CREO LE INTESTAZIONI WSA
        SOAPElement elementMessageID = SilvioUtil.aggiungiIntestazioneWSAMessageId(messaggio.getIdMessaggio());
        SOAPElement elementTo = SilvioUtil.aggiungiIntestazioneWSATo();
        SOAPElement elementAction = SilvioUtil.aggiungiIntestazioneWSAAction(parametriIstanza.getNomeOperazione());
        
        List<SOAPElement> lista = SilvioUtil.getSOAPHeaderList(exchange);
        lista.add(elementMessageID);
        lista.add(elementAction);
        lista.add(elementTo);
    }
}
