package it.unibas.silvio.processor;

import it.unibas.silvio.modello.Messaggio;
import it.unibas.silvio.modello.Operation;
import it.unibas.silvio.persistenza.SilvioException;
import it.unibas.silvio.util.CostantiSOAP;
import it.unibas.silvio.util.CostantiSilvio;
import it.unibas.silvio.util.SilvioUtil;
import java.util.List;
import java.util.Map;
import javax.xml.soap.SOAPElement;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProcessorEnricherLeggiIntestazioniCorrelazione implements Processor {

    private Log logger = LogFactory.getLog(this.getClass());

    public void process(Exchange exchange) throws Exception {
        Messaggio messaggioRichiesta = (Messaggio) exchange.getProperty(CostantiSilvio.MESSAGGIO_RICHIESTA);
        String profiloCollaborazione = (String) exchange.getIn().getHeader(CostantiSilvio.VALOREPROFILOCOLLABORAZIONE);
        List<SOAPElement> lista = SilvioUtil.getSOAPHeaderList(exchange);

        if(cercaIntestazioniWSA(lista, messaggioRichiesta)){
            logger.info("Trovato correlazione wsa");
            messaggioRichiesta.getParametriMessaggioRicevuto().setTipoCorrelazione(CostantiSilvio.CORRELAZIONE_WSA);
        }else if(cercaIntestazioniSPC(exchange.getIn().getHeaders(), messaggioRichiesta)){
            logger.info("Trovato correlazione spcoop");
            messaggioRichiesta.getParametriMessaggioRicevuto().setTipoCorrelazione(CostantiSilvio.CORRELAZIONE_SPCOOP);
        }else{
            logger.info("Non sono state trovate le intestazioni di correlazione");
            if (profiloCollaborazione.equals(Operation.ASINCRONO)) {
                throw new SilvioException("Non sono state trovate le intestazioni di correlazione, indispensabili per il profilo di collaborazione asincrono");
            }
        }
    }

    private boolean cercaIntestazioniWSA(List<SOAPElement> lista, Messaggio messaggioRichiesta) {
        if (lista == null || messaggioRichiesta == null) {
            return false;
        }
        boolean trovato = false;
        for (SOAPElement headerSoap : lista) {
            if (headerSoap.getElementQName().equals(CostantiSOAP.WSA_MESSAGEID)) {
                messaggioRichiesta.setIdMessaggio(headerSoap.getTextContent());
                logger.info("Trovata l'intestazione WSA IdMessaggio : " + messaggioRichiesta.getIdMessaggio());
                trovato = true;
            }
            if (headerSoap.getElementQName().equals(CostantiSOAP.WSA_MESSAGERELATESTOID)) {
                messaggioRichiesta.setIdRelatesTo(headerSoap.getTextContent());
                logger.info("Trovata l'intestazione WSA IdRelatesTo : " + messaggioRichiesta.getIdRelatesTo());
                trovato = true;
            }
        }
        return trovato;
    }

    private boolean cercaIntestazioniSPC(Map<String, Object> headers, Messaggio messaggioRichiesta) {
        if (headers == null || messaggioRichiesta == null) {
            return false;
        }
        boolean trovato = false;
        String idMessaggio = (String) headers.get(CostantiSOAP.INTEGRAZIONE_ID_MESSAGGIO);
        String idRelatesTo = (String) headers.get(CostantiSOAP.INTEGRAZIONE_RIFERIMENTO_MESSAGGIO);

        if (idMessaggio != null) {
            messaggioRichiesta.setIdMessaggio(idMessaggio);
            logger.info("Trovata l'intestazione SPC IdMessaggio : " + messaggioRichiesta.getIdMessaggio());
            trovato = true;
        }
        if (idRelatesTo != null) {
            messaggioRichiesta.setIdRelatesTo(idRelatesTo);
            logger.info("Trovata l'intestazione SPC IdRelatesTo : " + messaggioRichiesta.getIdRelatesTo());
            trovato = true;
        }
        return trovato;
    }
}
