package it.unibas.freesbee.ge.processor;

import java.io.Reader;
import java.io.StringReader;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.Iterator;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPEnvelope;

public class ProcessorSOAPReader implements Processor {

    private Log logger = LogFactory.getLog(this.getClass());

    public void process(Exchange exchange) throws Exception {
        //Leggo il body del messaggio
        String messaggioSOAP = exchange.getIn().getBody(String.class);
        if (messaggioSOAP == null) {
            return;
        }

        logger.debug("Leggo la busta soap \n" + messaggioSOAP);
        try {
            //Mi creo un soapMessage per analizzarlo
            MessageFactory messageFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
            SOAPMessage message = messageFactory.createMessage();
            SOAPPart soapPart = message.getSOAPPart();
            Reader soapStringReader = new StringReader(messaggioSOAP);
            Source soapSource = new StreamSource(soapStringReader);
            soapPart.setContent(soapSource);
            message.saveChanges();
            SOAPEnvelope envelope = soapPart.getEnvelope();

//            logger.info("*************************** 1");
//            //RIEMPIO L'HEADER
//            if (envelope.getHeader() != null) {
//                logger.info("*************************** 2");
//                List<SOAPElement> lista = getSOAPHeaderList(exchange);
//                for (Iterator itHeader = envelope.getHeader().examineAllHeaderElements(); itHeader.hasNext();) {
//                    SOAPHeaderElement headerElement = (SOAPHeaderElement) itHeader.next();
//                    logger.info("Header: " + headerElement.getNodeName() + ": " + headerElement.getTextContent());
//                    lista.add(headerElement);
//                }
//            }
    
            for (Iterator itBody = envelope.getBody().getChildElements(); itBody.hasNext();) {
                Object objectElement = itBody.next();
                if (objectElement instanceof SOAPBodyElement) {
                    SOAPBodyElement soapElement = (SOAPBodyElement) objectElement;
                    exchange.getIn().setBody(soapElement);
                    String nomeMessaggio = soapElement.getNodeName();
                    logger.info("OPERATION: " + nomeMessaggio);
                    exchange.setProperty("OPERATION", nomeMessaggio);
                }
            }



        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Impossibile leggere il messaggio SOAP " + ex);
            throw new Exception("Impossibile leggere il messaggio SOAP " + ex);
        }

    }

//    private static List<SOAPElement> getSOAPHeaderList(Exchange exchange) {
//        List<SOAPElement> lista = (List<SOAPElement>) exchange.getProperty("SOAP_HEADER_LIST");
//        if (lista == null) {
//            lista = new ArrayList<SOAPElement>();
//            exchange.setProperty("SOAP_HEADER_LIST", lista);
//        }
//        return lista;
//    }
}
