package it.unibas.silvio.util;

import java.io.Reader;
import java.io.StringReader;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

public class SOAPUtil {


    public static String controllaFault(String messaggioSOAP) {
        try {
            MessageFactory messageFactory = MessageFactory.newInstance(CostantiSOAP.SOAP_VERSION);
            SOAPMessage message = messageFactory.createMessage();
            SOAPPart soapPart = message.getSOAPPart();
            Reader soapStringReader = new StringReader(messaggioSOAP);
            Source soapSource = new StreamSource(soapStringReader);
            soapPart.setContent(soapSource);
            message.saveChanges();
            SOAPEnvelope envelope = soapPart.getEnvelope();

            if (envelope != null && envelope.getBody() != null) {
                SOAPFault fault = envelope.getBody().getFault();
                if(fault!=null){
                    return fault.getFaultString();
                }
            }
        } catch (Exception ex) {}
        return null;
    }
}
