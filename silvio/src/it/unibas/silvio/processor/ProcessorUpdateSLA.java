package it.unibas.silvio.processor;

import it.unibas.silvio.modello.Messaggio;
import it.unibas.silvio.modello.ParametriSLA;
import it.unibas.silvio.util.CostantiSilvio;
import it.unibas.silvio.util.SLAUtil;
import java.util.Date;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProcessorUpdateSLA implements Processor {

    public static final int PROCESSING = 1;
    public static final int IDLE = 2;
    public static final int INSERT = 3;
    private Log logger = LogFactory.getLog(this.getClass());
    private int type;

    public ProcessorUpdateSLA(int type) {
        this.type = type;
    }

    public void process(Exchange exchange) throws Exception {
        Messaggio messaggioRichiesta = (Messaggio) exchange.getProperty(CostantiSilvio.MESSAGGIO_RICHIESTA);

        if (messaggioRichiesta.getIstanzaOperation() == null) {
            return;
        }
        ParametriSLA parametriSLA = messaggioRichiesta.getIstanzaOperation().getParametriSLA();
        if (parametriSLA == null || !messaggioRichiesta.hasSLAInfo()) {
            return;
        }

        if (type == 1) {
            messaggioRichiesta.setSlaInizio(new Date().getTime());
            SLAUtil.updateServiceTermState_Processing(messaggioRichiesta, parametriSLA.getIndirizzoWS());
        } else if (type == 2) {
            SLAUtil.updateServiceTermState_Idle(messaggioRichiesta, parametriSLA.getIndirizzoWS());
        } else if (type == 3) {
            long slaInizio = messaggioRichiesta.getSlaInizio();
            if (slaInizio == 0) {
                logger.warn("Non è stato possibile aggiornare il tempo di risposta. La data d'invio del messaggio non è stata settata");
                return;
            }
            long slaFine = new Date().getTime();
            long duration = slaFine - slaInizio;
            SLAUtil.insertServiceBasicMetric(messaggioRichiesta, duration, parametriSLA.getIndirizzoWS());
        } else {
            logger.warn("Il tipo di update è sconosciuto: " + type);
        }
    }
}
