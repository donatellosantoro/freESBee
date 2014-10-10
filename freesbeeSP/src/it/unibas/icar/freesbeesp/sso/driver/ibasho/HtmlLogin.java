package it.unibas.icar.freesbeesp.sso.driver.ibasho;

import it.unibas.icar.freesbeesp.exception.FreesbeeSPException;
import it.unibas.icar.freesbeesp.sso.driver.IHtmlLogin;
import it.unibas.icar.freesbeesp.util.FileUtil;
import it.unibas.icar.freesbeesp.util.HttpUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

public class HtmlLogin implements IHtmlLogin {

    private Log logger = LogFactory.getLog(this.getClass());
    private static final String LOGIN_FORM_USERNAME = "userid";
    private static final String LOGIN_FORM_PASSWORD = "password";
    private static final String RELAY_STATE = "RelayState";
    private static final String SAMLREQUEST = "SAMLRequest";
    private static final String SAMLRESPONSE = "SAMLResponse";
    private static final String GUANI_MODE = "guanxi:mode";
    private static final String SUBMIT = "submit";
    
    public static final String LOGIN_ERROR = "Potresti aver digitato il tuo nome utente o la password in modo errato";

    public InputStream login(String username, String password, String address) throws FreesbeeSPException {
        try {
            DatiLogin datiLogin = leggiDatiLogin(address);
            HttpClient httpClient = new HttpClient();
            String SAMLResponse = getSAMLResponse(datiLogin, username, password, httpClient);
            if (logger.isDebugEnabled()) {
                logger.debug("SAMLRequest: " + SAMLResponse);
                logger.debug("RelayState: " + datiLogin.getRelayState());
            }
            PostMethod postMethod = new PostMethod(datiLogin.getIndirizzo());
            postMethod.addRequestHeader("Cookie", datiLogin.getCookiesString());
            postMethod.addParameter(RELAY_STATE, datiLogin.getRelayState());
            postMethod.addParameter(SAMLRESPONSE, SAMLResponse);
            postMethod.addParameter(LOGIN_FORM_USERNAME, username);
            postMethod.addParameter(LOGIN_FORM_PASSWORD, password);
            postMethod.addParameter(SAMLREQUEST, datiLogin.getSAMLRequest());
            postMethod.addParameter(GUANI_MODE, datiLogin.getGuanxi());
            postMethod.addParameter(SUBMIT, "continua");

            httpClient.executeMethod(postMethod);

            InputStream responseStream = postMethod.getResponseBodyAsStream();

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

    private String getSAMLResponse(DatiLogin datiLogin, String username, String password, HttpClient httpClient) throws Exception {
        PostMethod postMethod = new PostMethod(datiLogin.getIndirizzo());
        postMethod.addRequestHeader("Cookie", datiLogin.getCookiesString());
        postMethod.addParameter(LOGIN_FORM_USERNAME, username);
        postMethod.addParameter(LOGIN_FORM_PASSWORD, password);
        postMethod.addParameter(RELAY_STATE, datiLogin.getRelayState()); // GUARD_-441674ef--146d6cebdb1---54c6  sembra GUARD_+cookie IBASHO_IDP_ibasho-idp_IBASHO_IDP_PROFILE_SHIBBOLETH
        postMethod.addParameter(SAMLREQUEST, datiLogin.getSAMLRequest());
        postMethod.addParameter(GUANI_MODE, datiLogin.getGuanxi());
        postMethod.addParameter(SUBMIT, "accedi");
        if (logger.isDebugEnabled()) {
            logger.debug("Faccio una richiesta all'indirizzo " + datiLogin.getIndirizzo());
        }
        httpClient.executeMethod(postMethod);
        if (logger.isDebugEnabled()) {
            logger.debug(HttpUtil.stampaMethod(postMethod));
        }
        Cookie[] cookies = httpClient.getState().getCookies();
        for (Cookie c : cookies) {
            String name = c.getName();
            String value = c.getValue();
            if (name.equals("IBASHO_IDP_ibasho-idp_IBASHO_IDP_PROFILE_SHIBBOLETH")) {
                datiLogin.setRelayState("GUARD_" + value);
            }
        }
        String xmlString = postMethod.getResponseBodyAsString();
        if (xmlString.contains(LOGIN_ERROR)) {
            throw new Exception("Nome utente o password errati");
        }
        int start = xmlString.lastIndexOf("<form");
        int end = xmlString.lastIndexOf("</form>");
        String cleanDocument = xmlString.substring(start, end + 7);
        cleanDocument = cleanDocument.replace("&nbsp;", "");
        org.jsoup.nodes.Document document = Jsoup.parse(cleanDocument);
        Elements inputs = document.select("input");
        Iterator<org.jsoup.nodes.Element> iterator = inputs.iterator();
        String SAMLResponse = "";
        while (iterator.hasNext()) {
            org.jsoup.nodes.Element input = iterator.next();
            if (input.attr("name").equals("SAMLResponse")) {
                SAMLResponse = input.attr("value");
            }
        }
        return SAMLResponse;
    }

    private DatiLogin leggiDatiLogin(String address) throws Exception {
        HttpClient httpClient = new HttpClient();
        GetMethod method = new GetMethod(address);
        logger.error("Faccio una richiesta all'indirizzo "+ address);
        if (logger.isDebugEnabled()) {
            logger.debug("Faccio una richiesta all'indirizzo " + address);
        }
        httpClient.executeMethod(method);
        if (logger.isDebugEnabled()) {
            logger.debug(HttpUtil.stampaMethod(method));
        }
        String xmlString = method.getResponseBodyAsString();
        int start = xmlString.lastIndexOf("<form");
        int end = xmlString.lastIndexOf("</form>");

        String cleanDocument = xmlString.substring(start, end + 7);

        cleanDocument = cleanDocument.replace("&nbsp;", "");
        org.jsoup.nodes.Document document = Jsoup.parse(cleanDocument);
        Elements inputs = document.select("input");
        Iterator<org.jsoup.nodes.Element> iterator = inputs.iterator();
        String SAMLRequest = "";
        String relayState = "";
        while (iterator.hasNext()) {
            org.jsoup.nodes.Element input = iterator.next();
            if (input.attr("name").equals("SAMLRequest")) {
                SAMLRequest = input.attr("value");
            }
            if (input.attr("name").equals("RelayState")) {
                relayState = input.attr("value");
            }
        }
        if (logger.isDebugEnabled()) {
            logger.debug("SAMLRequest: " + SAMLRequest);
            logger.debug("RelayState: " + relayState);
        }

        String indirizzo = "https://ibasho.basilicatanet.it/ibasho_idp/SSO";
        if (logger.isDebugEnabled()) logger.debug("Indirizzo login " + indirizzo);
       
        Cookie[] cookies = httpClient.getState().getCookies();
        List<String> cs = new ArrayList<String>();
        for (Cookie c : cookies) {
            String name = c.getName();
            String value = c.getValue();
            cs.add(name + "=" + value);
        }
        return new DatiLogin(indirizzo, cs, SAMLRequest, relayState);
    }

    private class DatiLogin {

        private String indirizzo;
        private List<String> cookies = new ArrayList<String>();
        private String guanxi = "authenticate";
        private String SAMLRequest;
        private String relayState;

        public DatiLogin(String indirizzo, List<String> cookie, String SAMLRequest, String relayState) {
            this.indirizzo = indirizzo;
            this.cookies = cookie;
            this.SAMLRequest = SAMLRequest;
            this.relayState = relayState;
        }

        public List<String> getCookies() {
            return cookies;
        }

        public void setCookies(List<String> cookies) {
            this.cookies = cookies;
        }

        public String getIndirizzo() {
            return indirizzo;
        }

        public void setIndirizzo(String indirizzo) {
            this.indirizzo = indirizzo;
        }

        public String getGuanxi() {
            return guanxi;
        }

        public void setGuanxi(String guanxi) {
            this.guanxi = guanxi;
        }

        public String getSAMLRequest() {
            return SAMLRequest;
        }

        public void setSAMLRequest(String SAMLRequest) {
            this.SAMLRequest = SAMLRequest;
        }

        public String getRelayState() {
            return relayState;
        }

        public void setRelayState(String relayState) {
            this.relayState = relayState;
        }

        public String getCookiesString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < cookies.size(); i++) {
                if (i != cookies.size() - 1) {
                    sb.append(cookies.get(i)).append("; ");
                } else {
                    sb.append(cookies.get(i));
                }
            }
            return sb.toString();
        }

    }

}
