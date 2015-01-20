package it.unibas.icar.freesbee.processors;

import it.unibas.icar.freesbee.modello.Eccezione;
import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.utilita.CostantiBusta;
import java.util.List;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// Come ProcessControllaEccezioniEGov ma non solleva l'eccezione !
public class ProcessStampaEccezioniEGov implements Processor {

    private static Log logger = LogFactory.getLog(ProcessStampaEccezioniEGov.class);

    public ProcessStampaEccezioniEGov() {
    }

    public void process(Exchange exchange) throws Exception {
        //ContextStartup.aggiungiThread(this.getClass().getName());
        Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiBusta.MESSAGGIO);
        List<Eccezione> erroriGravi = messaggio.getListaEccezioni();
        if (!erroriGravi.isEmpty()) {
            logger.error("Ci sono stati degli errori GRAVI nel processamento del messaggio, quindi non lo inoltro.");
            String errori = "";
            for (Eccezione eccezione : erroriGravi) {
                errori += eccezione.toString();
            }
            logger.error("\n\n" + errori + "\n");
        }

    }
}
