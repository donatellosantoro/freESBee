package it.unibas.icar.freesbeesp.sso.driver.shibboleth;

import it.unibas.icar.freesbeesp.exception.FreesbeeSPException;
import it.unibas.icar.freesbeesp.sso.driver.IHtmlLogin;
import it.unibas.icar.freesbeesp.util.FileUtil;
import it.unibas.icar.freesbeesp.util.HttpUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class HtmlLogin implements IHtmlLogin {

    private Log logger = LogFactory.getLog(this.getClass());
    private String LOGIN_FORM_USERNAME = "j_username";
    private String LOGIN_FORM_PASSWORD = "j_password";

    public InputStream login(String username, String password, String address) throws FreesbeeSPException {
        try {
            DatiLogin datiLogin = leggiDatiLogin(address);
            HttpClient httpClient = new HttpClient();

            PostMethod postMethod = new PostMethod(datiLogin.getIndirizzo());
            postMethod.addRequestHeader("Cookie", datiLogin.getCookie());
            postMethod.addParameter(LOGIN_FORM_USERNAME, username);
            postMethod.addParameter(LOGIN_FORM_PASSWORD, password);

            logger.debug("Faccio una richiesta all'indirizzo " + datiLogin.getIndirizzo());
            httpClient.executeMethod(postMethod);
            if (logger.isDebugEnabled()) logger.debug(HttpUtil.stampaMethod(postMethod));
            
            Header headerCookie = postMethod.getResponseHeader("Set-Cookie");
            if (logger.isDebugEnabled()) logger.debug("Cookie: " + datiLogin.getCookie());
            Header location = postMethod.getResponseHeader("Location");
            String uri = location.getValue();
            if (logger.isDebugEnabled()) logger.debug("URI REDIRECT: " + uri);

            GetMethod getMethod = new GetMethod(uri);
            getMethod.addRequestHeader("Cookie", datiLogin.getCookie());
            httpClient.executeMethod(getMethod);
            if (logger.isDebugEnabled()) {
                logger.debug(HttpUtil.stampaMethod(getMethod));

                Document response = HttpUtil.parseHttp(getMethod.getResponseBodyAsStream());
                NodeList formInputList = response.getElementsByTagName("input");
                Integer numeroTag = formInputList.getLength();
                logger.debug("NUMERO TAG INPUT:" + numeroTag.toString());
                Node formInputRelayState = formInputList.item(0);
                Node nodeActionRelayState = formInputRelayState.getAttributes().getNamedItem("value");
                String RelayState = nodeActionRelayState.getNodeValue();
                logger.debug("REALAYSTATE:" + RelayState);
                Node formInputSamlResponse = formInputList.item(1);
                Node nodeActionSamlResponse = formInputSamlResponse.getAttributes().getNamedItem("value");
                String SAMLResponse = nodeActionSamlResponse.getNodeValue();
                logger.debug("SAMLRESPONSE:" + SAMLResponse);
            }

            InputStream responseStream = getMethod.getResponseBodyAsStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            FileUtil.copyStream(responseStream, baos);
            String stringaRisposta = baos.toString();
            if (stringaRisposta == null || stringaRisposta.isEmpty()) {

                throw new FreesbeeSPException("Autenticazione fallita!");
            }
            return new ByteArrayInputStream(stringaRisposta.getBytes());
        } catch (Exception ex) {
            logger.error("Impossibile effettuare il login");
            throw new FreesbeeSPException("Impossibile effettuare il login. " + ex.getMessage());
        }
    }

    private DatiLogin leggiDatiLogin(String address) throws Exception {
        HttpClient httpClient = new HttpClient();
        GetMethod method = new GetMethod(address);

        if (logger.isDebugEnabled()) logger.debug("Faccio una richiesta all'indirizzo " + address);
        httpClient.executeMethod(method);

        if (logger.isDebugEnabled()) logger.debug(HttpUtil.stampaMethod(method));
        

        Document domResponse = HttpUtil.parseHttp(method.getResponseBodyAsStream());

        NodeList formInputList = domResponse.getElementsByTagName("form");
        if (formInputList.getLength() == 0) {
            throw new Exception("Probabilmente la risorsa " + address + " non e' correttamente configurata sul service provider.");
        }
        if (formInputList.getLength() != 1) {
            throw new Exception("La pagina di login non contiene una form di login valida");
        }
        Node formInput = formInputList.item(0);
        Node nodeAction = formInput.getAttributes().getNamedItem("action");
        if (nodeAction == null) {
            throw new Exception("La form per il login non e' valida");
        }
        String indirizzo = HttpUtil.completaUrl(nodeAction.getNodeValue(), method.getURI().toString());
        if (logger.isDebugEnabled()) logger.debug("Indirizzo login " + indirizzo);

        String cookie = method.getRequestHeader("Cookie").getValue();
        if (logger.isDebugEnabled()) logger.debug("Cookie " + cookie);

        return new DatiLogin(indirizzo, cookie);
    }

    private class DatiLogin {

        private String indirizzo;
        private String cookie;

        public DatiLogin(String indirizzo, String cookie) {
            this.indirizzo = indirizzo;
            this.cookie = cookie;
        }

        public String getCookie() {
            return cookie;
        }

        public void setCookie(String cookie) {
            this.cookie = cookie;
        }

        public String getIndirizzo() {
            return indirizzo;
        }

        public void setIndirizzo(String indirizzo) {
            this.indirizzo = indirizzo;
        }
    }
}
