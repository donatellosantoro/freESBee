package it.unibas.silvio.processor;

import it.unibas.silvio.modello.Messaggio;
import it.unibas.silvio.modello.ParametriMessaggioRicevuto;
import it.unibas.silvio.util.CostantiSOAP;
import it.unibas.silvio.util.CostantiSilvio;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProcessorEnricherCorrelazioneRispostaSPC implements Processor {

    private Log logger = LogFactory.getLog(this.getClass());

    public void process(Exchange exchange) throws Exception {
        Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiSilvio.MESSAGGIO_RICHIESTA);
        ParametriMessaggioRicevuto parametri = messaggio.getParametriMessaggioRicevuto();
        if (parametri.getTipoCorrelazione().equals(CostantiSilvio.CORRELAZIONE_WSA)) {
            return;
        }
        //CERCO LE INTESTAZIONI SPC
        logger.info("Intestazioni SPC: " + exchange.getIn().getHeaders());
        String idMessaggio = (String) exchange.getIn().getHeader(CostantiSOAP.INTEGRAZIONE_ID_MESSAGGIO);
        messaggio.setIdMessaggio(idMessaggio);
    }
}
