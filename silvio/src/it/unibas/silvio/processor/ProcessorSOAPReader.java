package it.unibas.silvio.processor;

import it.unibas.silvio.modello.Messaggio;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import it.unibas.silvio.persistenza.SilvioException;
import it.unibas.silvio.util.CostantiSOAP;
import it.unibas.silvio.util.CostantiSilvio;
import it.unibas.silvio.util.SilvioUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.Node;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeaderElement;
import org.apache.camel.Message;
import org.apache.cxf.helpers.XMLUtils;
import org.w3c.dom.Document;

public class ProcessorSOAPReader implements Processor {

    private Log logger = LogFactory.getLog(this.getClass());

    public void process(Exchange exchange) throws Exception {
        Message messageIn = exchange.getIn();
        Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiSilvio.MESSAGGIO_RICHIESTA);
        String messaggioSOAP = messaggio.getMessaggio();
        if (messaggioSOAP == null) {
            return;
        }

        logger.info("Leggo la busta soap \n" + messaggioSOAP);
        try {
            MessageFactory messageFactory = MessageFactory.newInstance(CostantiSOAP.SOAP_VERSION);
            MimeHeaders mimeHeaders = new MimeHeaders();
            copiaHeaders(messageIn, mimeHeaders);
            InputStream isMessaggio = new ByteArrayInputStream(messaggioSOAP.getBytes());
            SOAPMessage message = messageFactory.createMessage(mimeHeaders, isMessaggio);
            logger.info("La busta contiene " + message.countAttachments() + " attachments");
            SOAPPart soapPart = message.getSOAPPart();
            SOAPEnvelope envelope = soapPart.getEnvelope();
            //RIEMPIO L'HEADER
            if (envelope.getHeader() != null) {
                List<SOAPElement> lista = SilvioUtil.getSOAPHeaderList(exchange);
                for (Iterator itHeader = envelope.getHeader().examineAllHeaderElements(); itHeader.hasNext();) {
                    SOAPHeaderElement headerElement = (SOAPHeaderElement) itHeader.next();
                    logger.info("Header: " + headerElement.getNodeName() + ": " + headerElement.getTextContent());
                    lista.add(headerElement);
                }
            }

            for (Iterator itBody = envelope.getBody().getChildElements(); itBody.hasNext();) {
                Object objectElement = itBody.next();
                if (objectElement instanceof SOAPBodyElement) {
                    SOAPBodyElement soapElement = (SOAPBodyElement) objectElement;
                    String nomeMessaggio = soapElement.getNodeName();
                    logger.info("Body: " + nomeMessaggio);
                    exchange.setProperty(CostantiSilvio.NOME_MESSAGGIO, nomeMessaggio);
                }
            }

            if (message.countAttachments() > 0) {
                logger.info("Il messaggio ha degli attachment quindi devo estrarre solo l'envelope");
                Node nodeSoapPart = message.getSOAPPart().getEnvelope();
                String stringSoapPart = XMLUtils.toString(nodeSoapPart);
                messaggio.setMessaggio(stringSoapPart);
            }

            if (message.getSOAPBody() != null && message.getSOAPBody().hasChildNodes()) {
                Document docMessaggio = message.getSOAPBody().extractContentAsDocument();
                messaggio.setBodyMessaggio(docMessaggio);
            }
            logger.info("Messaggio SOAP elaborato correttamente");
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Impossibile leggere il messaggio SOAP " + ex);
            throw new SilvioException("Impossibile leggere il messaggio SOAP " + ex);
        }

    }

    private void copiaHeaders(Message messageIn, MimeHeaders mimeHeaders) {
        Map<String, Object> headers = messageIn.getHeaders();
        for (String key : headers.keySet()) {
            Object value = headers.get(key);
            if (value != null) {
                mimeHeaders.addHeader(key, value.toString());
                              logger.info("mime header" + key +" " +value.toString());

            }
        }
    }
}
