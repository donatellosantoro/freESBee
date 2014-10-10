package it.unibas.icar.freesbee.processors;

import it.unibas.icar.freesbee.exception.FreesbeeException;
import it.unibas.icar.freesbee.modello.ConfigurazioneStatico;
import it.unibas.icar.freesbee.utilita.CostantiSOAP;
import it.unibas.icar.freesbee.xml.XmlUtil;
import java.util.Map;
import javax.xml.namespace.QName;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Validator;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.DocumentFragment;

public class ProcessorValidaXSD implements Processor {

    private static Log logger = LogFactory.getLog(ProcessorValidaXSD.class);

    public ProcessorValidaXSD() {
    }

    public void process(Exchange exchange) throws Exception {
        //ContextStartup.aggiungiThread(this.getClass().getName());
        // new ProcessorLog(this.getClass()).process(exchange);
        ProcessorLogFactory.getInstance().getProcessorLog(this.getClass()).process(exchange);
        Map<QName, DocumentFragment> mappaHeaders = (Map<QName, DocumentFragment>) exchange.getProperty(CostantiSOAP.SOAP_HEADERS);

        if (logger.isDebugEnabled()) {
            if (logger.isDebugEnabled())
                logger.debug("Le intestazioni sono: " + mappaHeaders);
        }
        if (mappaHeaders == null) {
            logger.error("Non ho trovato intestazioni nel messaggio. Probabilmente il messaggio ricevuto non è una busta egov");
            exchange.removeProperty(CostantiSOAP.SOAP_HEADERS); //Il messaggio di errore da restituire dovrà contenere solo il body
            throw new FreesbeeException("Non ho trovato intestazioni nel messaggio. Probabilmente il messaggio ricevuto non e' una busta egov");
        }
        DocumentFragment nodoIntestazione = mappaHeaders.get(new QName(CostantiSOAP.NAMESPACE_EGOV, "Intestazione"));
        if (nodoIntestazione == null) {
            logger.error("Non ho trovato intestazioni egov. Probabilmente il messaggio ricevuto non è una busta egov");
            logger.error("Le intestazioni sono: " + mappaHeaders);
            exchange.removeProperty(CostantiSOAP.SOAP_HEADERS);
            throw new FreesbeeException("Non ho trovato intestazioni nel messaggio. Probabilmente il messaggio ricevuto non e' una busta egov");
        }


        try {
            Validator v = ConfigurazioneStatico.getInstance().getValidatore();
            v.validate(new DOMSource(nodoIntestazione));
        } catch (Exception e) {
            if (logger.isDebugEnabled()) e.printStackTrace();
            exchange.removeProperty(CostantiSOAP.SOAP_HEADERS);
            String stringaIntestazione = XmlUtil.stampaDocument(nodoIntestazione.getFirstChild());
            logger.error("Intestazione: " + stringaIntestazione);
            throw new FreesbeeException("Le intestazioni ricevute non sono valide rispetto all'XSD delle intestazioni EGov. " + e.getLocalizedMessage());
        }
    }
}
