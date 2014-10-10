package it.unibas.icar.freesbeesp.sso.driver.shibboleth;

import it.unibas.icar.freesbeesp.sso.driver.ISSODriver;
import it.unibas.icar.freesbeesp.sso.SAMLResponse;
import it.unibas.icar.freesbeesp.sso.SSOSessionResponse;
import it.unibas.icar.freesbeesp.sso.SAMLResponseUtil;
import it.unibas.icar.freesbeesp.util.HttpUtil;
import it.unibas.icar.freesbeesp.sso.SSOCostanti;
import it.unibas.icar.freesbeesp.sso.driver.HtmlLoginFactory;
import it.unibas.icar.freesbeesp.sso.driver.IHtmlLogin;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.contrib.ssl.EasySSLProtocolSocketFactory;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.cxf.helpers.XMLUtils;
import org.jdom.Namespace;
import org.jdom.output.XMLOutputter;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ShibbolethDriver implements ISSODriver {

    private org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog(this.getClass());
    private String INDIRIZZO_DISCOVERY_SERVICE = "/DS/WAYF";
    
    public ShibbolethDriver() {
        try {
            Protocol protocol = new Protocol("https", new EasySSLProtocolSocketFactory(), 443);
            Protocol.registerProtocol("https", protocol);
        } catch (Exception ex) {
        }
    }

    public SSOSessionResponse createSession(String username, String password, String risorsa, String idpEntityID) throws Exception {
        InputStream isSamlResponse = null;
        SSOSessionResponse ssoSession = null;
        try {

            String indirizzoLogin = risorsa;
            if (idpEntityID != null) {
                //Accedo al Discovery Service
                indirizzoLogin = contactDiscoveryService(risorsa, idpEntityID);
            }

           if (logger.isDebugEnabled())  logger.debug("Indirizzo login: " + indirizzoLogin);
            IHtmlLogin loginStrategy = new HtmlLoginFactory().getInstance();
            isSamlResponse = loginStrategy.login(username, password, indirizzoLogin);

            if (logger.isDebugEnabled()) logger.info("Prelevo la SAML Response dall'IdP");
            SAMLResponse mySamlResponse = SAMLResponseUtil.extractSAMLResponse(isSamlResponse);
            ssoSession = this.getShibSession(mySamlResponse);

            if (logger.isDebugEnabled()) logger.debug("Decripto la SAML Response");
            org.jdom.Document mySamlResponseDecriptata = SAMLResponseUtil.decryptAssertion(mySamlResponse);

            if (logger.isDebugEnabled()) logger.debug("Estraggo le asserzioni dal messaggio SAML");
            org.jdom.Element samlElementRoot = mySamlResponseDecriptata.getRootElement();
            Namespace samlNamespace = Namespace.getNamespace(SSOCostanti.SAMLNAMESPACEPREFIX, SSOCostanti.SAMLNAMESPACE);
            org.jdom.Element elementAssertion = samlElementRoot.getChild("Assertion", samlNamespace);
            ssoSession.setElementAssertion(elementAssertion);

            if (logger.isDebugEnabled()) {
                logger.debug("Trasformo il document in stringa");
                XMLOutputter outputter = new XMLOutputter();
                String stringaSAMLResponse = outputter.outputString(elementAssertion.getDocument());
                logger.debug("SAML Response = " + stringaSAMLResponse);
            }
        } catch (Exception ex) {
            logger.error("Si e' verificato un errore : " + ex);
            throw ex;
        } finally {
            try {
                if (isSamlResponse != null) {
                    isSamlResponse.close();
                }
            } catch (IOException ex) {
            }
        }
        return ssoSession;
    }

    private String contactDiscoveryService(String address, String idpEntityID) throws Exception {
        try {
            HttpClient httpClient = new HttpClient();
            GetMethod method = new GetMethod(address);
            if (logger.isDebugEnabled()) logger.debug("Faccio una richiesta all'indirizzo " + address);
            httpClient.executeMethod(method);
            if (logger.isDebugEnabled()) logger.debug(HttpUtil.stampaMethod(method));
            String indirizzoDS = method.getURI().toString();
            if (logger.isDebugEnabled()) logger.debug("Indirizzo del Discovery Service " + indirizzoDS);
            indirizzoDS += "&returnIDParam=entityID&FedSelector=ALL&action=selection&cache=session";
            String idpEntityIDEnc = URLEncoder.encode(idpEntityID, "UTF-8");
            indirizzoDS += "&origin=" + idpEntityIDEnc;

            return indirizzoDS;
        } catch (Exception ex) {
            logger.error("Impossibile accedere al Discovery Service " + ex.getLocalizedMessage());
            throw new Exception("Impossibile accedere al Discovery Service " + ex.getLocalizedMessage());
        }
    }

    public Map<String, String> verificaDiscoveryService(String risorsa) {
        assert risorsa != null : "Impossibile effettuare il login. La risorsa da contattare e' null!";
        try {
            HttpClient httpClient = new HttpClient();
            GetMethod method = new GetMethod(risorsa);
            if (logger.isDebugEnabled()) logger.debug("Faccio una richiesta all'indirizzo " + risorsa);

            httpClient.executeMethod(method);
            if (logger.isDebugEnabled()) logger.debug(HttpUtil.stampaMethod(method));

            URI indirizzo = method.getURI();
            if (indirizzo.getPath().equalsIgnoreCase(INDIRIZZO_DISCOVERY_SERVICE)) {
                logger.info("E' presente un servizio di Discovery Service");
                Map<String, String> mappaIdP = new HashMap<String, String>();
                org.w3c.dom.Document docDS = XMLUtils.parse(method.getResponseBodyAsStream());
                NodeList nodeListSelect = docDS.getElementsByTagName("select");
                if (nodeListSelect != null) {
                    for (int i = 0; i < nodeListSelect.getLength(); i++) {
                        Node nodeSelect = nodeListSelect.item(i);
                        Node attributoId = nodeSelect.getAttributes().getNamedItem("id");
                        if (attributoId != null && attributoId.getNodeValue().equalsIgnoreCase("originIdp")) {
                            NodeList nodeListOptions = nodeSelect.getChildNodes();
                            for (int j = 0; j < nodeListOptions.getLength(); j++) {
                                Node nodeOption = nodeListOptions.item(j);
                                String nodeName = nodeOption.getNodeName();
                                String nodeValue = nodeOption.getTextContent();
                                if (nodeName.equalsIgnoreCase("option") && nodeOption.getAttributes() != null) {
                                    Node attributeValue = nodeOption.getAttributes().getNamedItem("value");
                                    if (attributeValue != null && nodeValue != null) {
                                        logger.info("Trovato nuovo IdP: " + nodeValue.trim() + " - " + attributeValue.getNodeValue());
                                        mappaIdP.put(attributeValue.getNodeValue(), nodeValue.trim());
                                    }
                                }
                            }
                        }
                    }
                }
                return mappaIdP;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.warn("Impossibile verificare la presenza del discovery service " + ex);
        }

        return null;
    }

    private SSOSessionResponse getShibSession(SAMLResponse samlResponse) throws Exception {
        try {
            SSOSessionResponse shibSession = new SSOSessionResponse();
            if (logger.isDebugEnabled()) logger.debug("Apro una session con " + samlResponse.getIndirizzo());
            HttpClient httpClient = new HttpClient();
            PostMethod postMethod = new PostMethod(samlResponse.getIndirizzo());
            postMethod.addParameter("SAMLResponse", samlResponse.getSamlResponse());
            postMethod.addParameter("RelayState", samlResponse.getRelayState());

            httpClient.executeMethod(postMethod);
            if (logger.isDebugEnabled()) logger.debug(HttpUtil.stampaMethod(postMethod));
            Header cookie = postMethod.getResponseHeader("Set-Cookie");
            if (cookie == null) {
                throw new Exception("Impossibile leggere la ShibSession");
            }
            String indirizzo = HttpUtil.getRedirect(postMethod);

            shibSession.setSession(cookie.getValue());
            if (logger.isDebugEnabled()) logger.debug("ShibSession - Session: " + shibSession.getSession());
            shibSession.setIndirizzo(indirizzo);
            if (logger.isDebugEnabled()) logger.debug("ShibSession - Indirizzo: " + shibSession.getIndirizzo());

            return shibSession;
        } catch (Exception ex) {
            logger.error("Impossibile inviare gli attributi SAML");
            throw new Exception("Impossibile inviare gli attributi SAML" + ex.getLocalizedMessage());
        }
    }
}
