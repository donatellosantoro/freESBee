
package it.unibas.freesbee.websla.utilita;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class MessaggiMonitoraggio {

    private MessaggiMonitoraggio() {
    }
    private static Log logger = LogFactory.getLog(MessaggiMonitoraggio.class);

    public static String requestServiceTermStateType(String idService, String idInitiator, String idResponder) throws SOAPException, IOException {
        SOAPMessage message = MessageFactory.newInstance().createMessage();
        message.getSOAPPart().getEnvelope().addNamespaceDeclaration("xsi", "http://www.w3.org/2001/XMLSchema-instance");
        message.getSOAPPart().getEnvelope().addNamespaceDeclaration("xsd", "http://www.w3.org/2001/XMLSchema");
        message.getSOAPPart().getEnvelope().addNamespaceDeclaration("soapenv", "http://schemas.xmlsoap.org/soap/envelope/");
        message.getSOAPPart().getEnvelope().addNamespaceDeclaration("icar", "http://icar.inf2.sistemamonitoraggio/");

        SOAPBodyElement myTemp = message.getSOAPBody().addBodyElement(new QName("", "getServiceTermState", "icar"));
        QName name = new QName("soapenv:encodingStyle");
        myTemp.addAttribute(name, "http://schemas.xmlsoap.org/soap/encoding/");

        name = new QName("richiesta");
        SOAPElement element1 = (SOAPElement) myTemp.addChildElement(name);
        name = new QName("xsi:type");
        element1.addAttribute(name, "RequestServiceTermStateType");
        element1.setParentElement(myTemp);

        name = new QName("IdService");
        SOAPElement element = (SOAPElement) myTemp.addChildElement(name);
        name = new QName("xsi:type");
        element.addAttribute(name, "string");
        element.setTextContent(idService);
        element.setParentElement(element1);

        name = new QName("IdInitiator");
        element = (SOAPElement) myTemp.addChildElement(name);
        name = new QName("xsi:type");
        element.addAttribute(name, "string");
        element.setTextContent(idInitiator);
        element.setParentElement(element1);

        name = new QName("IdResponder");
        element = (SOAPElement) myTemp.addChildElement(name);
        name = new QName("xsi:type");
        element.addAttribute(name, "string");
        element.setTextContent(idResponder);
        element.setParentElement(element1);


        ByteArrayOutputStream os = new ByteArrayOutputStream();
        message.writeTo(os);

        return os.toString();

    }

    public static String responseServiceTermStateType(String bodyRisposta) throws SOAPException {
        String response = "";
        if (bodyRisposta.contains("Completed") || bodyRisposta.contains("COMPLETED")) {
            response = "Completed";
        } else {
            if (bodyRisposta.contains("Ready_Idle") || bodyRisposta.contains("READY_IDLE")) {
                response = "Ready_Idle";
            } else {
                if (bodyRisposta.contains("Ready_Processing") || bodyRisposta.contains("READY_PROCESSING")) {
                    response = "Ready_Processing";
                } else {
                    if (bodyRisposta.contains("Not_Ready") || bodyRisposta.contains("NOT_READY")) {
                        response = "Not_Ready";
                    } else {
                        throw new SOAPException("Nessuno stato rilevato");
                    }
                }
            }
        }
        return response;
    }

    public static String requestServiceGuaranteeTermStateType(String idService, String idInitiator, String idResponder, String slaNome, String data) throws SOAPException, IOException {
        SOAPMessage message = MessageFactory.newInstance().createMessage();
        message.getSOAPPart().getEnvelope().addNamespaceDeclaration("xsi", "http://www.w3.org/2001/XMLSchema-instance");
        message.getSOAPPart().getEnvelope().addNamespaceDeclaration("xsd", "http://www.w3.org/2001/XMLSchema");
        message.getSOAPPart().getEnvelope().addNamespaceDeclaration("soapenv", "http://schemas.xmlsoap.org/soap/envelope/");
        message.getSOAPPart().getEnvelope().addNamespaceDeclaration("icar", "http://icar.inf2.sistemamonitoraggio/");

        SOAPBodyElement myTemp = message.getSOAPBody().addBodyElement(new QName("", "getServiceGuaranteeTermState", "icar"));
        QName name = new QName("soapenv:encodingStyle");
        myTemp.addAttribute(name, "http://schemas.xmlsoap.org/soap/encoding/");

        name = new QName("richiesta");
        SOAPElement element1 = (SOAPElement) myTemp.addChildElement(name);
        name = new QName("xsi:type");
        element1.addAttribute(name, "RequestServiceGuaranteeTermStateType");
        element1.setParentElement(myTemp);

        name = new QName("IdService");
        SOAPElement element = (SOAPElement) myTemp.addChildElement(name);
        name = new QName("xsi:type");
        element.addAttribute(name, "string");
        element.setTextContent(idService);
        element.setParentElement(element1);

        name = new QName("IdInitiator");
        element = (SOAPElement) myTemp.addChildElement(name);
        name = new QName("xsi:type");
        element.addAttribute(name, "string");
        element.setTextContent(idInitiator);
        element.setParentElement(element1);

        name = new QName("IdResponder");
        element = (SOAPElement) myTemp.addChildElement(name);
        name = new QName("xsi:type");
        element.addAttribute(name, "string");
        element.setTextContent(idResponder);
        element.setParentElement(element1);

        name = new QName("GuaranteeTermObj");
        element = (SOAPElement) myTemp.addChildElement(name);
        name = new QName("xsi:type");
        element.addAttribute(name, "GuaranteeTermObj");

        name = new QName("GuaranteeTermName");
        SOAPElement element2 = (SOAPElement) myTemp.addChildElement(name);
        name = new QName("xsi:type");
        element2.addAttribute(name, "string");
        element2.setTextContent(slaNome);
        element2.setParentElement(element);

        name = new QName("DateFn");
        element2 = (SOAPElement) myTemp.addChildElement(name);
        name = new QName("xsi:type");
        element2.addAttribute(name, "dateTime");
        element2.setTextContent(data);
        element2.setParentElement(element);

        element.setParentElement(element1);

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        message.writeTo(os);

        return os.toString();

    }

    public static String responseServiceGuaranteeTermStateType(String bodyRisposta) throws SOAPException {
        String response = "";
        if (bodyRisposta.contains("Not_Determined") || bodyRisposta.contains("NOT_DETERMINED")) {
            response = "Not_Determined";
        } else {
            if (bodyRisposta.contains("Fulfilled") || bodyRisposta.contains("FULFILLED")) {
                response = "Fulfilled";
            } else {
                if (bodyRisposta.contains("Violated") || bodyRisposta.contains("VIOLATED")) {
                    response = "Violated";
                } else {
                    throw new SOAPException("Nessuno esito rilevato");
                }
            }
        }
        return response;
    }
}
