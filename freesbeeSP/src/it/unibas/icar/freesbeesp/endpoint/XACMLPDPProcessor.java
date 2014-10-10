package it.unibas.icar.freesbeesp.endpoint;

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
import it.unibas.icar.freesbeesp.exception.FreesbeeSPException;
import it.unibas.icar.freesbeesp.exception.XacmlException;
import it.unibas.icar.freesbeesp.modello.PolicyCache;
import it.unibas.icar.freesbeesp.util.XmlJDomUtil;
import it.unibas.icar.freesbeesp.sso.SSOCostanti;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
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

public class XACMLPDPProcessor implements Processor {

    private Log logger = LogFactory.getLog(this.getClass());
    private String accordoServizio;
    private String indirizzoRegistroServizi;

    public XACMLPDPProcessor(String accordoServizio, String indirizzoRegistroServizi) {
        this.accordoServizio = accordoServizio;
        this.indirizzoRegistroServizi = indirizzoRegistroServizi;
    }

    public void process(Exchange exchange) throws Exception {
        Message messaggioIn = exchange.getIn();
        String messaggio = (String) messaggioIn.getBody(String.class);

        if (messaggio == null || messaggio.equals("")) {
            logger.error("Non ho ricevuto nessun messaggio SOAP !");
            exchange.setException(new FreesbeeSPException("Non ho ricevuto nessun messaggio SOAP !"));
            return;
        }

        logger.debug("Carico il messaggio SOAP");
        SAXBuilder parser = new SAXBuilder();
        Document soapDocument = parser.build(new ByteArrayInputStream(messaggio.getBytes()));

        logger.info("SOAP DOCUMENT: \n" + XmlJDomUtil.stampaXML(soapDocument));

        logger.debug("Creo il PDP");
        PDP pdp = creaPDP();

        logger.debug("Converto da SAML a XACML");

        Element wsSecurityElement = estraiWSSecurityHeader(soapDocument);

        RequestCtx request = creaXacmlRequest(wsSecurityElement);

        ByteArrayOutputStream requestOs = new ByteArrayOutputStream();
        request.encode(requestOs);
        ByteArrayInputStream requestIs = new ByteArrayInputStream(requestOs.toByteArray());

        Document xacmlRequest = parser.build(requestIs);

        logger.info("MESSAGGIO DI RICHIESTA: \n" + XmlJDomUtil.stampaXML(xacmlRequest));

        logger.debug("Valido la risposta sulla base della Policy");
        ResponseCtx response = pdp.evaluate(request);

        logger.info("Analizzo la decisione");
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        response.encode(os);
        ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());

        Document xacmlDocument = parser.build(is);

        logger.info("MESSAGGIO DI RISPOSTA: \n" + XmlJDomUtil.stampaXML(xacmlDocument));

        Element xacmlElementRoot = xacmlDocument.getRootElement();

        logger.debug("Estraggo l'esito");
        Element elementResult = xacmlElementRoot.getChild("Result");
        Element elementDecision = elementResult.getChild("Decision");

        String esito = elementDecision.getText();
        logger.info("ESITO : " + esito);

        if (esito.equalsIgnoreCase(SSOCostanti.ESITO_PERMIT)) {
            soapDocument.removeContent(wsSecurityElement);
            logger.info("SOAP DOCUMENT MODIFICATO: \n" + XmlJDomUtil.stampaXML(soapDocument));
        } else if (esito.equalsIgnoreCase(SSOCostanti.ESITO_DENY)) {
            throw new XacmlException("Impossibile accedere alla risorsa. \n Il portafoglio non contiene attributi validi per accedere alla risora");
        } else if (esito.equalsIgnoreCase(SSOCostanti.ESITO_INDETERMINATE)) {
            throw new XacmlException("Impossibile accedere alla risorsa. \n Assenza di alcuni valori nella richiesta. Non e' stato possibile prendere una decisione");
        } else if (esito.equalsIgnoreCase(SSOCostanti.ESITO_NOT_APPLICABLE)) {
            throw new XacmlException("Impossibile accedere alla risorsa. \n Il servizio non puo' validare la richiesta");
        }

        if (logger.isInfoEnabled()) {
            logger.info("Trasformo il document in stringa");
        }
        XMLOutputter outputter = new XMLOutputter();
        String stringaDocumentOut = outputter.outputString(soapDocument);

        if (logger.isInfoEnabled()) {
            logger.info("Inserisco il document nel Message");
        }
        Message messaggioOut = exchange.getOut();

        messaggioOut.setBody(stringaDocumentOut, String.class);
        copiaIntestazioni(messaggioIn,messaggioOut);
    }

    public static void copiaIntestazioni(Message m1, Message m2) {
        Map<String, Object> intestazioni = m1.getHeaders();
        Set<String> setChiavi = intestazioni.keySet();
        for (String chiave : setChiavi) {
            Object valore = intestazioni.get(chiave);
            m2.getHeaders().put(chiave, valore);
        }
    }

    private PDP creaPDP() throws XacmlException {
        FilePolicyModule policyModule = new FilePolicyModule();
        File filePolicy = PolicyCache.getInstance().getPolicy(accordoServizio);
        if (filePolicy == null) {
            String messaggio = "Impossibile creare il PDP. Le possibili cause sono:\n" +
                    "\t- Il registro servizi non e' disponibile" +
                    "\t- L'accordo di servizio " + accordoServizio + " non esiste" +
                    "\t- Non e' stata specificata alcuna policy per l'accordo di servizio " + accordoServizio;
            logger.error(messaggio);
            throw new XacmlException(messaggio);
        }
        logger.info("Percorso del file temporaneo della policy: " + filePolicy.getAbsolutePath());
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

        Namespace samlAssertionNamespace = Namespace.getNamespace("saml", "urn:oasis:names:tc:SAML:2.0:assertion");
        Element elementSamlAssertion = wsSecurityElement.getChild("Assertion", samlAssertionNamespace);

        logger.debug("Estraggo e converto le informazioni sul Subject");
        Element elementSamlSubject = elementSamlAssertion.getChild("Subject", samlAssertionNamespace);
        Element elementSamlSubjectNameID = elementSamlSubject.getChild("NameID", samlAssertionNamespace);
        String nameIdValue = elementSamlSubjectNameID.getText();
        URI subjectId = new URI("urn:oasis:names:tc:xacml:1.0:subject:subject-id");
        StringAttribute valoreSubjectId = new StringAttribute(nameIdValue);
        attributes.add(new Attribute(subjectId, null, null, valoreSubjectId));

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

        AnyURIAttribute value = new AnyURIAttribute(new URI("http://server.example.com/"));

        resource.add(new Attribute(new URI(EvaluationCtx.RESOURCE_ID), null, null, value));

        return resource;
    }

    private Set creaHashSetAction() throws URISyntaxException {
        HashSet action = new HashSet();

        URI actionId = new URI("urn:oasis:names:tc:xacml:1.0:action:action-id");

        action.add(new Attribute(actionId, null, null, new StringAttribute("commit")));

        return action;
    }
}
