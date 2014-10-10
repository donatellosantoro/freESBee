package it.unibas.silvio.processor;

import it.unibas.silvio.modello.Messaggio;
import it.unibas.silvio.modello.Operation;
import it.unibas.silvio.modello.ParametriEseguiIstanza;
import it.unibas.silvio.util.CostantiSOAP;
import it.unibas.silvio.util.CostantiSilvio;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import it.unibas.silvio.persistenza.SilvioException;

public class ProcessorEnricherCorrelazioneRichiestaSPC implements Processor {

    private Log logger = LogFactory.getLog(this.getClass());

    public void process(Exchange exchange) throws Exception {
        String profiloCollaborazione = (String) exchange.getIn().getHeader(CostantiSilvio.VALOREPROFILOCOLLABORAZIONE);
        
        Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiSilvio.MESSAGGIO_RICHIESTA);
        Messaggio messaggioRisposta = (Messaggio) exchange.getProperty(CostantiSilvio.MESSAGGIO_RISPOSTA);
        ParametriEseguiIstanza parametriIstanza = messaggio.getParametriEseguiIstanza();
        if (parametriIstanza.getCorrelazione().equals(CostantiSilvio.CORRELAZIONE_WSA)) {
            return;
        }
        //CERCO LE INTESTAZIONI SPC
        logger.info("Intestazioni SPC: " + exchange.getIn().getHeaders());
        String idMessaggio = (String) exchange.getIn().getHeader(CostantiSOAP.INTEGRAZIONE_ID_MESSAGGIO);
        if (profiloCollaborazione.equals(Operation.ASINCRONO) && idMessaggio == null) {
            throw new SilvioException("Non sono state trovate le intestazioni SPC di correlazione");
        }
        messaggio.setIdMessaggio(idMessaggio);
        if (messaggioRisposta!=null) {
            messaggioRisposta.setIdRelatesTo(idMessaggio);
        }
    }
}
