package com.ws.aci;

import com.sun.xacml.EvaluationCtx;
import com.sun.xacml.PDP;
import com.sun.xacml.PDPConfig;
import com.sun.xacml.attr.AnyURIAttribute;
import com.sun.xacml.attr.StringAttribute;
import com.sun.xacml.ctx.Attribute;
import com.sun.xacml.ctx.RequestCtx;
import com.sun.xacml.ctx.ResponseCtx;
import com.sun.xacml.ctx.Subject;
import com.sun.xacml.finder.AttributeFinder;
import com.sun.xacml.finder.PolicyFinder;
import com.sun.xacml.finder.impl.CurrentEnvModule;
import com.sun.xacml.finder.impl.FilePolicyModule;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Content;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.output.DOMOutputter;
import org.jdom.output.XMLOutputter;

public class SecurityInterceptor implements SOAPHandler<SOAPMessageContext> {

    private static Log logger = LogFactory.getLog(SecurityInterceptor.class);
    private static String URL_RISORSA = "https://sp.comune.pz/";

    public Set<QName> getHeaders() {
        return null;
    }

    public boolean handleMessage(SOAPMessageContext context) {
        try {
            SOAPMessage soapMsg = context.getMessage();
            SAXBuilder parser = new SAXBuilder();
            
            Document soapDocument = toDocument(soapMsg);
            System.out.println("SOAP DOCUMENT: \n" + XmlJDomUtil.stampaXML(soapDocument));

            System.out.println("Creo il PDP");
            PDP pdp = creaPDP();

            System.out.println("Converto da SAML a XACML");

            Element wsSecurityElement = estraiWSSecurityHeader(soapDocument);

            RequestCtx request = creaXacmlRequest(wsSecurityElement);

            ByteArrayOutputStream requestOs = new ByteArrayOutputStream();
            request.encode(requestOs);
            ByteArrayInputStream requestIs = new ByteArrayInputStream(requestOs.toByteArray());

            org.jdom.Document xacmlRequest = parser.build(requestIs);

            System.out.println("MESSAGGIO DI RICHIESTA: \n" + XmlJDomUtil.stampaXML(xacmlRequest));

            System.out.println("Valido la risposta sulla base della Policy");
            ResponseCtx response = pdp.evaluate(request);

            System.out.println("Analizzo la decisione");
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            response.encode(os);
            ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());

            org.jdom.Document xacmlDocument = parser.build(is);

            System.out.println("MESSAGGIO DI RISPOSTA: \n" + XmlJDomUtil.stampaXML(xacmlDocument));

            Element xacmlElementRoot = xacmlDocument.getRootElement();

            System.out.println("Estraggo l'esito");
            Element elementResult = xacmlElementRoot.getChild("Result");
            Element elementDecision = elementResult.getChild("Decision");

            String esito = elementDecision.getText();
            System.out.println("ESITO : " + esito);

            if (esito.equalsIgnoreCase(SSOCostanti.ESITO_PERMIT)) {
                soapDocument.removeContent(wsSecurityElement);
                System.out.println("SOAP DOCUMENT MODIFICATO: \n" + XmlJDomUtil.stampaXML(soapDocument));
            } else if (esito.equalsIgnoreCase(SSOCostanti.ESITO_DENY)) {
                throw new XacmlException("Impossibile accedere alla risorsa. \n Il portafoglio non contiene attributi validi per accedere alla risora");
            } else if (esito.equalsIgnoreCase(SSOCostanti.ESITO_INDETERMINATE)) {
                throw new XacmlException("Impossibile accedere alla risorsa. \n Assenza di alcuni valori nella richiesta. Non � stato possibile prendere una decisione");
            } else if (esito.equalsIgnoreCase(SSOCostanti.ESITO_NOT_APPLICABLE)) {
                throw new XacmlException("Impossibile accedere alla risorsa. \n Il servizio non pu� validare la richiesta");
            }

            if (logger.isInfoEnabled()) {
                logger.info("Trasformo il document in stringa");
            }
            XMLOutputter outputter = new XMLOutputter();
            String stringaDocumentOut = outputter.outputString(soapDocument);

            if (logger.isInfoEnabled()) {
                logger.info("Inserisco il document nel Message");
            }
//                Message messaggioOut = exchange.getOut();
            SOAPMessage messaggioOut = getSoapMessageFromString(stringaDocumentOut);
//                messaggioOut.setBody(stringaDocumentOut, String.class);
            copiaIntestazioni(soapMsg, messaggioOut);
            context.setMessage(messaggioOut);

        } catch (SOAPException e) {
            logger.error(e);
        } catch (XacmlException ex) {
            logger.error(ex);
        } catch (JDOMException ex) {
            logger.error(ex);
        } catch (IOException ex) {
            logger.error(ex);
        }

//        }
        //continue other handler chain
        return true;
    }

    private PDP creaPDP() throws XacmlException {
        FilePolicyModule policyModule = new FilePolicyModule();
        File filePolicy = new File(SecurityInterceptor.class.getResource("/policyAci.xml").getFile());
        String accordoServizio = "";
        if (filePolicy == null) {
            String messaggio = "Impossibile creare il PDP. Le possibili cause sono:\n"
                    + "\t- Il registro servizi non e' disponibile"
                    + "\t- L'accordo di servizio " + accordoServizio + " non esiste"
                    + "\t- Non � stata specificata alcuna policy per l'accordo di servizio " + accordoServizio;
            logger.error(messaggio);
            throw new XacmlException(messaggio);
        }
        System.out.println("Percorso del file temporaneo della policy: " + filePolicy.getAbsolutePath());
        policyModule.addPolicy(filePolicy.getAbsolutePath());

        CurrentEnvModule envModule = new CurrentEnvModule();

        PolicyFinder policyFinder = new PolicyFinder();
        Set policyModules = new HashSet();
        policyModules.add(policyModule);
        policyFinder.setModules(policyModules);

        AttributeFinder attrFinder = new AttributeFinder();
        List attrModules = new ArrayList();
        attrModules.add(envModule);
        attrFinder.setModules(attrModules);

        PDP pdp = new PDP(new PDPConfig(attrFinder, policyFinder, null));
        return pdp;
    }

    private Element estraiWSSecurityHeader(Document soapDocument) throws XacmlException {
        logger.debug("Carico il messaggio SOAP");
        SAXBuilder parser = new SAXBuilder();
        Element soapElementRoot = soapDocument.getRootElement();

        logger.debug("Estraggo l'Header dal messaggio SOAP");
        Namespace headerNamespace = Namespace.getNamespace("http://schemas.xmlsoap.org/soap/envelope/");
        Element elementHeader = soapElementRoot.getChild("Header", headerNamespace);
        if (elementHeader == null) {
            throw new XacmlException("Non e' stato allegato il portafoglio delle asserzioni nel messaggio SOAP");
        }

        Namespace wsSecurityNamespace = Namespace.getNamespace("wsse", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
        Element security = elementHeader.getChild("Security", wsSecurityNamespace);
        if (security == null) {
            throw new XacmlException("Non e' stato allegato il portafoglio delle asserzioni nel messaggio SOAP");
        }

        Namespace samlAssertionNamespace = Namespace.getNamespace("saml", "urn:oasis:names:tc:SAML:2.0:assertion");
        Element assertion = security.getChild("Assertion", samlAssertionNamespace);
        if (assertion == null) {
            throw new XacmlException("Non e' stato allegato il portafoglio delle asserzioni nel messaggio SOAP");
        }
        Content contentAssertion = assertion.detach();

        Element elementWSSecurity = new Element("Security", wsSecurityNamespace);
        if (elementWSSecurity == null) {
            throw new XacmlException("Non e' stato allegato il portafoglio delle asserzioni nel messaggio SOAP");
        }
        elementWSSecurity.addContent(contentAssertion);

        return elementWSSecurity;
    }

    private RequestCtx creaXacmlRequest(Element wsSecurityElement) {
        try {
            Set subject = creaHashSetSubject(wsSecurityElement);
            Set resource = creaHashSetResource();
            Set action = creaHashSetAction();
            RequestCtx request = new RequestCtx(subject, resource, action, new HashSet());

            return request;
        } catch (URISyntaxException ex) {
            logger.error(ex);
        }
        return null;
    }

    private org.w3c.dom.Document convertToDOM(org.jdom.Document jdomDoc) throws JDOMException {
        DOMOutputter outputter = new DOMOutputter();
        return outputter.output(jdomDoc);
    }

    private Set creaHashSetSubject(Element wsSecurityElement) throws URISyntaxException {
        HashSet attributes = new HashSet();

//        Namespace samlAssertionNamespace = Namespace.getNamespace("saml", "urn:oasis:names:tc:SAML:2.0:assertion");
        Namespace samlAssertionNamespace = Namespace.getNamespace("saml2", "urn:oasis:names:tc:SAML:2.0:assertion");
        Element elementSamlAssertion = wsSecurityElement.getChild("Assertion", samlAssertionNamespace);
        logger.debug("Estraggo e converto le informazioni sul Subject");
        //Element elementSamlSubject = elementSamlAssertion.getChild("Subject", samlAssertionNamespace);
       // Element elementSamlSubjectNameID = elementSamlSubject.getChild("NameID", samlAssertionNamespace);
        //String nameIdValue = elementSamlSubjectNameID.getText();
        //URI subjectId = new URI("urn:oasis:names:tc:xacml:1.0:subject:subject-id");
        //StringAttribute valoreSubjectId = new StringAttribute(nameIdValue);
        //attributes.add(new Attribute(subjectId, null, null, valoreSubjectId));

        logger.debug("Estraggo e converto tutti gli altri attributi");
        Element elementIssuer = elementSamlAssertion.getChild("Issuer", samlAssertionNamespace);
        Element elementAttributeStatement = elementSamlAssertion.getChild("AttributeStatement", samlAssertionNamespace);
        List<Element> listaSamlAssertion = elementAttributeStatement.getChildren("Attribute", samlAssertionNamespace);
        for (Element elementAttribute : listaSamlAssertion) {
            URI attributeId = new URI(elementAttribute.getAttributeValue("Name"));
            Element elementAttributeValue = elementAttribute.getChild("AttributeValue", samlAssertionNamespace);
            String attributeValue = elementAttributeValue.getText();
            StringAttribute valoreAttributeValue = new StringAttribute(attributeValue);
            String valoreIssuer = elementIssuer.getText();

            attributes.add(new Attribute(attributeId, valoreIssuer, null, valoreAttributeValue));
        }

        HashSet subjects = new HashSet();
        subjects.add(new Subject(attributes));

        return subjects;
    }

    private Set creaHashSetResource() throws URISyntaxException {
        HashSet resource = new HashSet();

//        AnyURIAttribute value = new AnyURIAttribute(new URI("http://server.example.com/"));
        AnyURIAttribute value = new AnyURIAttribute(new URI(URL_RISORSA));

        resource.add(new Attribute(new URI(EvaluationCtx.RESOURCE_ID), null, null, value));

        return resource;
    }

    private Set creaHashSetAction() throws URISyntaxException {
        HashSet action = new HashSet();

        URI actionId = new URI("urn:oasis:names:tc:xacml:1.0:action:action-id");

        action.add(new Attribute(actionId, null, null, new StringAttribute("commit")));

        return action;
    }

    public boolean handleFault(SOAPMessageContext context) {
        System.out.println("Server : handleFault()......");

        return true;
    }

    public void close(MessageContext context) {
        System.out.println("Server : close()......");
    }

    private void generateSOAPErrMessage(SOAPMessage msg, String reason) {
        try {
            SOAPBody soapBody = msg.getSOAPPart().getEnvelope().getBody();
            SOAPFault soapFault = soapBody.addFault();
            soapFault.setFaultString(reason);
            throw new SOAPFaultException(soapFault);
        } catch (SOAPException e) {
        }
    }

    private SOAPMessage getSoapMessageFromString(String xml) throws SOAPException, IOException {
        MessageFactory factory = MessageFactory.newInstance();
        SOAPMessage message = factory.createMessage(new MimeHeaders(), new ByteArrayInputStream(xml.getBytes(Charset.forName("UTF-8"))));
        return message;
    }

    private void copiaIntestazioni(SOAPMessage messaggioIn, SOAPMessage messaggioOut) throws SOAPException {
        SOAPHeader soapHeaderIn = messaggioIn.getSOAPHeader();
        Iterator<SOAPHeaderElement> headers = (Iterator<SOAPHeaderElement>) soapHeaderIn.getChildElements();
        while (headers.hasNext()) {
            SOAPHeaderElement nextHeader = headers.next();
            messaggioOut.getSOAPHeader().addChildElement(nextHeader);
        }
    }

    public Document toDocument(SOAPMessage soapMsg) throws SOAPException, IOException, JDOMException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        soapMsg.writeTo(out);
        String strMsg = new String(out.toByteArray());
        SAXBuilder sb = new SAXBuilder();
        Document doc = sb.build(new StringReader(strMsg));
        return doc;

    }

}
