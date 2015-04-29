package it.unibas.icar.freesbee.processors;

import it.unibas.icar.freesbee.exception.FreesbeeException;
import it.unibas.icar.freesbee.modello.Eccezione;
import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.portadelegatahttp.HttpPortaDelegata;
import it.unibas.icar.freesbee.utilita.CostantiBusta;
import java.util.List;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.LoggerFactory;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

public class ProcessorControllaEccezioniEGov implements Processor {

//    private static Log logger = LogFactory.getLog(ProcessorControllaEccezioniEGov.class);
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ProcessorControllaEccezioniEGov.class.getName());

    public ProcessorControllaEccezioniEGov() {
    }

    public void process(Exchange exchange) throws Exception {
        //ContextStartup.aggiungiThread(this.getClass().getName());
        Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiBusta.MESSAGGIO);
        List<Eccezione> erroriGravi = messaggio.getEccezioniGravi();
        if (!erroriGravi.isEmpty()) {
            logger.error("Si sono verificati degli errori nel processamento del messaggio.");
            String errori = "";
            for (Eccezione eccezione : erroriGravi) {
                errori += eccezione.toString();
            }
            throw new FreesbeeException("Errori riscontrati:\n\n" + errori);
        }

    }
}
