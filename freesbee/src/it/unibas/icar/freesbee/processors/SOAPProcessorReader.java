package it.unibas.icar.freesbee.processors;

import it.unibas.icar.freesbee.exception.FreesbeeException;
import it.unibas.icar.freesbee.utilita.CostantiBusta;
import it.unibas.icar.freesbee.utilita.CostantiSOAP;
import it.unibas.icar.freesbee.utilita.FreesbeeUtil;
import it.unibas.icar.freesbee.utilita.MessageUtil;
import it.unibas.icar.freesbee.xml.XmlUtil;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.helpers.XMLUtils;
import org.apache.servicemix.soap.marshalers.SoapMarshaler;
import org.apache.servicemix.soap.marshalers.SoapMessage;
import org.apache.servicemix.soap.marshalers.SoapReader;

public class SOAPProcessorReader implements Processor {

    private static Log logger = LogFactory.getLog(SOAPProcessorReader.class);
    private static SOAPProcessorReader singleton = new SOAPProcessorReader();

    private SOAPProcessorReader() {
    }

    public static SOAPProcessorReader getInstance() {
        return singleton;
    }

    public void process(Exchange exchange) throws Exception {
        //ContextStartup.aggiungiThread(this.getClass().getName());
        ProcessorLogFactory.getInstance().getProcessorLog(this.getClass()).process(exchange);
        if (MessageUtil.isEmpty(exchange.getIn())) {
            if (logger.isInfoEnabled())logger.info("Ricevuto un messaggio vuoto. Probabilemente è un ack di risposta");
            MessageUtil.setString(exchange.getIn(), "");
            return;
        }
        
        String contentType = (String) exchange.getIn().getHeader("Content-Type");
        if (logger.isInfoEnabled()) logger.info("contentType: " + contentType);
        SoapMarshaler soapMarshaler = new SoapMarshaler(true);
//        soapMarshaler.setSoap(true);
//        soapMarshaler.setUseDom(true);
        soapMarshaler.setSoapUri(CostantiSOAP.SOAP_VERSION);
//        soapMarshaler.setUseDom(true);      
        SoapReader soapReader = new SoapReader(soapMarshaler);
        SoapMessage soapMessage;
        try {
            if (contentType != null && startsWithCaseInsensitive(contentType, SoapMarshaler.MULTIPART_CONTENT)) {
                //E' UN MESSAGGIO CON ATTACHMENT
                InputStream bodyStream = MessageUtil.getStream(exchange.getIn());
                String stringaIntestazioni = FreesbeeUtil.estraiIntestazioniHTTP(exchange.getIn());
                InputStream intestazioniStream = new ByteArrayInputStream(stringaIntestazioni.getBytes());
                InputStream messaggioStream = new SequenceInputStream(intestazioniStream, bodyStream);
                if (logger.isDebugEnabled()) logger.debug("Ho ricevuto un messaggio soap con attachment");
                Session session = Session.getDefaultInstance(new Properties());
                MimeMessage mime = new MimeMessage(session, messaggioStream);
                soapMessage = soapReader.read(mime);
                //AGGIUNGO GLI ATTACHMENT ALLA MAPPA DELLE INTESTAZIONI
                exchange.setProperty(CostantiSOAP.SOAP_ATTACHMENT, soapMessage.getAttachments());
            } else {
                InputStream bodyStream = MessageUtil.getStream(exchange.getIn());        
                soapMessage = soapReader.read(bodyStream);
            }
            if (logger.isDebugEnabled()) logger.debug("soapMessage.getBodyName()" + soapMessage.getBodyName());
            if (logger.isDebugEnabled()) logger.debug("soapMessage.hasAttachments()" + soapMessage.hasAttachments());
            MessageUtil.setSource(exchange.getIn(), soapMessage.getSource());
            exchange.setProperty(CostantiSOAP.SOAP_HEADERS, soapMessage.getHeaders());
            if (soapMessage.getFault() != null) {
                exchange.setProperty(CostantiSOAP.SOAP_FAULT, soapMessage.getFault());
            }
        } catch (Exception e) {
            if (logger.isDebugEnabled()) e.printStackTrace();
            logger.error("Errore generico nel parsing del messaggio SOAP. " + e.getMessage());
            exchange.setException(new FreesbeeException(e.getMessage()));
            return;
        }

        if (logger.isDebugEnabled()) logger.debug("Il messaggio soap letto e': " + MessageUtil.getString(exchange.getIn()));
        exchange.setProperty(CostantiBusta.FIGLI_MULTIPLI, "false");
        InputStream messaggio = MessageUtil.getStream(exchange.getIn());
        verificaFigliMultipli(messaggio, exchange.getIn());
        messaggio.reset();
    }

    private void verificaFigliMultipli(InputStream messaggio, Message messaggioIn) {
        try {
            Document docMessaggio = XMLUtils.parse(messaggio);
            Element envElem = docMessaggio.getDocumentElement();
            if (envElem != null && envElem.getLocalName().compareToIgnoreCase("envelope") == 0) {
                NodeList envChild = envElem.getChildNodes();
                Node elementBody = null;
                for (int i = 0; i < envChild.getLength(); i++) {

                    if (envChild.item(i).getLocalName() != null && envChild.item(i).getLocalName().equalsIgnoreCase("body")) {
                        elementBody = envChild.item(i);
                    }
                }
                if (elementBody != null) {
                    NodeList bodyChild = elementBody.getChildNodes();
                    int numeroFigli = bodyChild.getLength();
                    if (logger.isDebugEnabled()) logger.debug("Il body ha " + numeroFigli + " figli");
                    if (numeroFigli > 1) {
                        if (logger.isDebugEnabled()) logger.debug("Il body ha " + numeroFigli + " figli");
                        messaggioIn.getExchange().setProperty(CostantiBusta.FIGLI_MULTIPLI, "true");
                        Properties props = new Properties();
                        props.put(OutputKeys.OMIT_XML_DECLARATION, "yes");

                        String stringaBody = "";
                        for (int i = 0; i < bodyChild.getLength(); i++) {
                            stringaBody += XmlUtil.stampaDocument(bodyChild.item(i));
                        }
                        if (logger.isDebugEnabled()) logger.debug(stringaBody);
                        MessageUtil.setString(messaggioIn, stringaBody);
                    }
                }
            }

        } catch (Exception e) {
            if (logger.isDebugEnabled()) logger.debug("Impossibile verificare se il body ha piu' figli " + e);
        }
    }

    static boolean startsWithCaseInsensitive(String s1, String s2) {
        return s1.regionMatches(true, 0, s2, 0, s2.length());
    }
}
