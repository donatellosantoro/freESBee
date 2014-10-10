package it.unibas.silvio.processor;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import it.unibas.silvio.persistenza.SilvioException;
import it.unibas.silvio.util.CostantiSOAP;
import it.unibas.silvio.util.SilvioUtil;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.xml.soap.AttachmentPart;
import javax.xml.soap.MimeHeader;
import javax.xml.soap.Node;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPHeader;
import org.apache.cxf.helpers.XMLUtils;
import org.w3c.dom.Document;

public class ProcessorSOAPWriter implements Processor {

    private Log logger = LogFactory.getLog(this.getClass());
    private boolean eccezione;

    public ProcessorSOAPWriter(boolean eccezione) {
        this.eccezione = eccezione;
    }

    public ProcessorSOAPWriter() {
    }

    public void process(Exchange exchange) throws Exception {
        Message messageIn = exchange.getIn();
        try {
            MessageFactory messageFactory = MessageFactory.newInstance(CostantiSOAP.SOAP_VERSION);
            SOAPMessage message = messageFactory.createMessage();
            if (eccezione) {
                Exception e = (Exception) exchange.getIn().getHeader("caught.exception");
                logger.info("E' stata lanciata un'eccezione dall'erogatore: " + e);
                String stringaEccezione = e.getMessage();
                logger.info("Creo la busta soap contenente l'eccezione\n" + stringaEccezione);
                message.getSOAPBody().addFault(CostantiSOAP.FAULT_CODE, stringaEccezione);
            } else {
                String bodySOAP = messageIn.getBody(String.class);
                logger.info("Creo la busta soap contenente il body\n" + bodySOAP);
                SOAPHeader header = message.getSOAPHeader();
                List<SOAPElement> lista = SilvioUtil.getSOAPHeaderList(exchange);
                for (SOAPElement soapElement : lista) {
                    header.addChildElement(soapElement);
                }
                //CREO IL BODY            
                if (bodySOAP != null && !bodySOAP.isEmpty()) {
                    Document bodyDocument = XMLUtils.parse(bodySOAP);
                    message.getSOAPBody().addDocument(bodyDocument);
                }
                List<String> listaAttachment = SilvioUtil.getAttachmentList(exchange);
                aggiungiAttachment(listaAttachment, message);
            }
            message.saveChanges();
            for (Iterator it = message.getMimeHeaders().getAllHeaders(); it.hasNext();) {
                MimeHeader object = (MimeHeader) it.next();
                messageIn.setHeader(object.getName(), object.getValue());
            }   

            ByteArrayOutputStream streamSOAP = new ByteArrayOutputStream();
            message.writeTo(streamSOAP);
            messageIn.setBody(streamSOAP.toString(), String.class);
            logger.info("Messaggio SOAP creato correttamente");
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Impossibile creare il messaggio SOAP " + ex);
            throw new SilvioException("Impossibile creare il messaggio SOAP " + ex);
        }
    }

    private void aggiungiAttachment(List<String> listaAttachment, SOAPMessage message) {
        for (String attachment : listaAttachment) {
            logger.info("Devo allegare il file " + attachment);
            File fileAttachment = new File(attachment);
            if (!fileAttachment.exists()) {
                logger.warn("Il file da allegare " + attachment + " non esiste");
            } else if (!fileAttachment.canRead()) {
                logger.warn("Il file da allegare " + attachment + " non può essere letto");
            } else {
                DataSource ds = new FileDataSource(fileAttachment);
                DataHandler dataHandler = new DataHandler(ds);
                AttachmentPart attachmentPart = message.createAttachmentPart(dataHandler);
                message.addAttachmentPart(attachmentPart);
            }
        }
    }
}
