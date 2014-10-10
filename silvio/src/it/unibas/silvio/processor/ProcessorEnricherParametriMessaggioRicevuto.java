package it.unibas.silvio.processor;

import it.unibas.silvio.modello.Messaggio;
import it.unibas.silvio.modello.ParametriMessaggioRicevuto;
import it.unibas.silvio.util.CostantiSOAP;
import it.unibas.silvio.util.CostantiSilvio;
import it.unibas.silvio.util.SilvioUtil;
import java.util.List;
import javax.xml.soap.SOAPElement;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProcessorEnricherParametriMessaggioRicevuto implements Processor{
    
    private Log logger = LogFactory.getLog(this.getClass());

    public void process(Exchange exchange) throws Exception {
        Messaggio messaggioRichiesta = (Messaggio) exchange.getProperty(CostantiSilvio.MESSAGGIO_RICHIESTA);
        List<SOAPElement> lista = SilvioUtil.getSOAPHeaderList(exchange);
        ParametriMessaggioRicevuto parametriMessaggioRicevuto = new ParametriMessaggioRicevuto();
        
        for (SOAPElement headerSOAP : lista) {
            if(headerSOAP.getElementQName().equals(CostantiSOAP.ISTANZA_TEST)){
                String valore = headerSOAP.getTextContent();
                if(valore.equals("true")){
                    logger.info("Ricevuto un messaggio di test");
                    parametriMessaggioRicevuto.setTest(true);
                } else {
                    parametriMessaggioRicevuto.setTest(false);
                }
            }
            if(headerSOAP.getElementQName().equals(CostantiSOAP.NOME_OPERATION)){                
                String istanzaOperation = headerSOAP.getTextContent();
                parametriMessaggioRicevuto.setNomeOperazione(istanzaOperation);
                logger.info("Il nome dell'operazione richiesta e' " + istanzaOperation);
            }
            if(headerSOAP.getElementQName().equals(CostantiSOAP.INDIRIZZO_ASCOLTO_RISPOSTA)){
                String indirizzoAscolto = headerSOAP.getTextContent();
                parametriMessaggioRicevuto.setIndirizzoRisposta(indirizzoAscolto);
                logger.info("Operazione asincrona. L'indirizzo di ascolto e' : " + indirizzoAscolto);
            }
        }
        
        messaggioRichiesta.setParametriMessaggioRicevuto(parametriMessaggioRicevuto);
    }

}
