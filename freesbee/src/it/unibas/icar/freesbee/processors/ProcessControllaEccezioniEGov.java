package it.unibas.icar.freesbee.processors;

import it.unibas.guicefreesbee.ContextStartup;
import it.unibas.icar.freesbee.exception.FreesbeeException;
import it.unibas.icar.freesbee.modello.Eccezione;
import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.utilita.CostantiBusta;
import java.util.List;
import java.util.Map;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProcessControllaEccezioniEGov implements Processor {

    private static Log logger = LogFactory.getLog(ProcessControllaEccezioniEGov.class);

    public ProcessControllaEccezioniEGov() {
    }

    public void process(Exchange exchange) throws Exception {
        //ContextStartup.aggiungiThread(this.getClass().getName());
        Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiBusta.MESSAGGIO);
        List<Eccezione> erroriGravi = messaggio.getEccezioniGravi();
        if (!erroriGravi.isEmpty()) {
            logger.error("Ci sono stati degli errori GRAVI nel processamento del messaggio, quindi non lo inoltro al servizio applicativo.");
            String errori = "";
            for (Eccezione eccezione : erroriGravi) {
                errori += eccezione.toString();
            }
            throw new FreesbeeException("Errori nel processamento del messaggio: " + errori);
        }

    }
}
