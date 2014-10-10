package it.unibas.icar.freesbee.processors;

import it.unibas.icar.freesbee.exception.FreesbeeException;
import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.utilita.CostantiBusta;
import it.unibas.icar.freesbee.utilita.CostantiSOAP;
import it.unibas.icar.freesbee.xml.ValidatoreXSDBustaEGov;
import it.unibas.icar.freesbee.xml.XmlException;
import java.util.Map;
import javax.xml.namespace.QName;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Validator;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.DocumentFragment;
import org.xml.sax.SAXException;

public class SOAPProcessorValidaXSD implements Processor {

    private static Log logger = LogFactory.getLog(SOAPProcessorValidaXSD.class);

    public SOAPProcessorValidaXSD() {
    }

    public void process(Exchange exchange) throws Exception {
        //ContextStartup.aggiungiThread(this.getClass().getName());
        //new ProcessorLogFactory(this.getClass()).process(exchange);
        ProcessorLogFactory.getInstance().getProcessorLog(this.getClass()).process(exchange);
        Map<QName, DocumentFragment> mappaHeaders = (Map<QName, DocumentFragment>) exchange.getProperty(CostantiSOAP.SOAP_HEADERS);

        Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiBusta.MESSAGGIO);

        if (logger.isDebugEnabled()) {
            if (logger.isDebugEnabled()) logger.debug("Le intestazioni sono: " + mappaHeaders);
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
            Validator v = ValidatoreXSDBustaEGov.getValidatore();
            v.validate(new DOMSource(nodoIntestazione));
        } catch (SAXException e) {
            exchange.removeProperty(CostantiSOAP.SOAP_HEADERS);
            throw new FreesbeeException("Le intestazioni ricevute non sono valide rispetto all'XSD delle intestazioni EGov. " + e.getLocalizedMessage());
        } catch (XmlException e) {
            if (logger.isDebugEnabled()) e.printStackTrace();
            exchange.removeProperty(CostantiSOAP.SOAP_HEADERS);
            throw new FreesbeeException(e);
        }
    }
}
