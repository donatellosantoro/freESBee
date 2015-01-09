package it.unibas.sendersaml.controllo;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

public class SSOClient {

    private org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog(this.getClass());

    public String sendSoapSamlRequest(Map<String, String> mappaParametriPDDinamica, String risorsa, Element portafoglioAsserzioni, String ssoSession, String stringaRichiesta) {
        assert risorsa != null : "Impossibile inviare la richiesta! La risorsa da contattare e' null";
        assert portafoglioAsserzioni != null : "Impossibile inviare la richiesta! Il portafoglio delle asserzioni e' null";
        assert ssoSession != null : "Impossibile inviare la richiesta! La ssoSession e' null";
        assert stringaRichiesta != null : "Impossibile inviare la richiesta! La stringaRichiesta da inviare e' null";

        try {
            logger.debug("Creo l'elemento WSSecurity e ci aggiungo le asserzioni");
            Namespace wssecurityNamespace = Namespace.getNamespace(SSOCostanti.WSSECURITYNAMESPACEPREFIX, SSOCostanti.WSSECURITYNAMESPACE);
            Element elementWSSecurity = new Element("Security", wssecurityNamespace);
            elementWSSecurity.addContent(portafoglioAsserzioni.detach());

            SAXBuilder parser = new SAXBuilder();
            Document soapDocument = parser.build(new ByteArrayInputStream(stringaRichiesta.getBytes()));
            Element soapElementRoot = soapDocument.getRootElement();

            Namespace headerNamespace = verificaSOAP(soapElementRoot);
            if (headerNamespace == null) {
                throw new IllegalArgumentException("Il messaggio da inviare non e' una busta soap!");
            }

            logger.debug("Estraggo l'Header dal messaggio SOAP");
            Element elementHeader = soapElementRoot.getChild("Header", headerNamespace);
            if (elementHeader == null) {
                logger.debug("Estraggo il resto del messaggio SOAP");
                List<Element> elements = new ArrayList<Element>(soapElementRoot.getChildren());

                soapElementRoot.removeContent();

                logger.debug("Il messaggio SOAP non contiene nessun header e devo crearlo");
                elementHeader = new Element("Header", headerNamespace);
                soapElementRoot.addContent(elementHeader);

                for (Element element : elements) {
                    soapElementRoot.addContent((Element) element.clone());
                }
            }

            logger.debug("Aggiungo le asserzioni all'Header del messaggio SOAP");
            elementHeader.addContent(elementWSSecurity);

            logger.debug("Trasformo il document in stringa");
            XMLOutputter outputter = new XMLOutputter();
            String stringaDocumentOut = outputter.outputString(elementHeader.getDocument());

            HttpClient httpClient = new HttpClient();
            PostMethod postMethod = new PostMethod(risorsa);
            String ssoSessionString = ssoSession.substring(0, ssoSession.indexOf(";"));
            postMethod.setRequestHeader("Cookie", ssoSessionString);

            Set<String> chiaviPDDinamica = mappaParametriPDDinamica.keySet();
            Iterator iterator = chiaviPDDinamica.iterator();
            while (iterator.hasNext()) {
                String chiave = (String) iterator.next();
                if ((!chiave.equals("HOST")) && (!chiave.equals("USER-AGENT")) &&
                        (!chiave.equals("CONTENT-LENGTH")) && (!chiave.equals("CONTENT-TYPE"))) {
                    postMethod.setRequestHeader(chiave, mappaParametriPDDinamica.get(chiave));
                }
            }

            RequestEntity entity = new ByteArrayRequestEntity(stringaDocumentOut.getBytes());
            postMethod.setRequestEntity(entity);
            logger.debug("Inoltro la richiesta all'indirizzo " + risorsa);

            httpClient.executeMethod(postMethod);
            if (logger.isDebugEnabled()) {
                logger.debug(HttpUtil.stampaMethod(postMethod));
            }

            InputStream responseStream = postMethod.getResponseBodyAsStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            FileUtil.copyStream(responseStream, baos);
            String stringaRisposta = baos.toString();
            logger.debug("Risposta " + stringaRisposta);
            return stringaRisposta;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Impossibile inviare il messaggio " + ex.getMessage());
            String soapFault = "<?xml version='1.0' encoding='UTF-8'?><SOAP_ENV:Envelope xmlns:SOAP_ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"><SOAP_ENV:Body><SOAP_ENV:Fault><faultcode>SOAP_ENV:Client</faultcode><faultstring>";
            soapFault += ex.getMessage();
            soapFault += "</faultstring><detail>NOT_AUTHORIZED</detail></SOAP_ENV:Fault></SOAP_ENV:Body></SOAP_ENV:Envelope>";
            return soapFault;
        }
    }

    private Namespace verificaSOAP(Element soapElementRoot) {
        if (soapElementRoot == null) {
            return null;
        }
        if (!soapElementRoot.getName().equalsIgnoreCase("Envelope")) {
            return null;
        }
        return soapElementRoot.getNamespace();
    }
}
