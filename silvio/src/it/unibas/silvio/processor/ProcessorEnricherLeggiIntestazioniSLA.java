package it.unibas.silvio.processor;

import it.unibas.silvio.modello.Messaggio;
import it.unibas.silvio.modello.ParametriSLA;
import it.unibas.silvio.util.CostantiSOAP;
import it.unibas.silvio.util.CostantiSilvio;
import it.unibas.silvio.util.SLAUtil;
import it.unibas.silvio.util.StringUtil;
import java.util.Map;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProcessorEnricherLeggiIntestazioniSLA implements Processor {

    private Log logger = LogFactory.getLog(this.getClass());

    public void process(Exchange exchange) throws Exception {
        Messaggio messaggioRichiesta = (Messaggio) exchange.getProperty(CostantiSilvio.MESSAGGIO_RICHIESTA);
        Map<String, Object> headers = exchange.getIn().getHeaders();
        ParametriSLA parametriSLA = messaggioRichiesta.getIstanzaOperation().getParametriSLA();
        if (parametriSLA == null) {
            return;
        }

        if (headers != null && parametriSLA != null) {
            String idMittente = (String) headers.get(CostantiSOAP.SLA_MITTENTE);
            String idDestinatario = (String) headers.get(CostantiSOAP.SLA_DESTINATARIO);
            String idServizio = (String) headers.get(CostantiSOAP.SLA_SERVIZIO);
            if (StringUtil.isNonVuota(idMittente) && StringUtil.isNonVuota(idDestinatario) && StringUtil.isNonVuota(idServizio)) {
                messaggioRichiesta.setSlaMittente(idMittente);
                messaggioRichiesta.setSlaDestinatario(idDestinatario);
                messaggioRichiesta.setSlaServizio(idServizio);
            } else {
                logger.warn("Per questa istanza sono previsti degli SLA, ma la porta di dominio non mi ha inviato tutte le informazioni necessarie.");
            }
        } else {
            logger.warn("Per questa istanza sono previsti degli SLA, ma la porta di dominio non mi ha inviato tutte le informazioni necessarie.");

        }
    }

    
}
