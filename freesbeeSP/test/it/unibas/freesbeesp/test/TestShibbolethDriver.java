package it.unibas.freesbeesp.test;

import it.unibas.icar.freesbeesp.sso.SSOClient;
import it.unibas.icar.freesbeesp.sso.SSOSessionResponse;
import it.unibas.icar.freesbeesp.sso.driver.ISSODriver;
import it.unibas.icar.freesbeesp.sso.driver.shibboleth.ShibbolethDriver;
import java.util.HashMap;
import java.util.Map;
import junit.framework.Assert;
import junit.framework.TestCase;

public class TestShibbolethDriver extends TestCase {

    private org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog(this.getClass());

    public void testSecure() {
        String stringaDriver = "it.unibas.icar.freesbeesp.sso.driver.shibboleth.ShibbolethDriver";
        ISSODriver ssoDriver;
        try {
            ssoDriver = (ISSODriver) Class.forName(stringaDriver).newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
            Assert.fail("Impossibile caricare il driver " + stringaDriver);
            return;
        }

        try {
            Map<String, String> mappaParametriPDDinamica = new HashMap<String, String>();
            String risorsa = "https://sp.example.unibas.org/ServizioIdentificazioneAssistito_2/";
//            String risorsa = "https://sp.example.unibas.org/secure/";
            String username = "b";
            String password = "b";
            String idp = "https://icarlab.unibas.it/idp/shibboleth";
            SSOSessionResponse shibSession = ssoDriver.createSession(username, password, risorsa, idp);
            Assert.assertNotNull(shibSession);
            Assert.assertNotNull(shibSession.getSession());
            Assert.assertNotNull(shibSession.getElementAssertion());

            String richiesta = "<env:Envelope xmlns:env=\"http://schemas.xmlsoap.org/soap/envelope/\"><env:Header/></env:Envelope>";
            String risposta = new SSOClient().sendSoapSamlRequest(mappaParametriPDDinamica, risorsa, shibSession.getElementAssertion(), shibSession.getSession(), richiesta);
            Assert.assertTrue("Test SOAP 1.1 con Header", risposta.contains("http://silvio.unibas.it/"));
//            Assert.assertTrue("Test SOAP 1.1 con Header", risposta.contains("PAGINA PROTETTA"));

            richiesta = "<env:Envelope xmlns:env=\"http://schemas.xmlsoap.org/soap/envelope/\"></env:Envelope>";
            risposta = new SSOClient().sendSoapSamlRequest(mappaParametriPDDinamica, risorsa, shibSession.getElementAssertion(), shibSession.getSession(), richiesta);
            Assert.assertTrue("Test SOAP 1.1 senza Header", risposta.contains("http://silvio.unibas.it/"));
//            Assert.assertTrue("Test SOAP 1.1 senza Header", risposta.contains("PAGINA PROTETTA"));

            richiesta = "<env:Envelope xmlns:env=\"http://www.w3.org/2003/05/soap-envelope/\"><env:Header/></env:Envelope>";
            risposta = new SSOClient().sendSoapSamlRequest(mappaParametriPDDinamica, risorsa, shibSession.getElementAssertion(), shibSession.getSession(), richiesta);
            Assert.assertTrue("Test SOAP 1.2 con Header", risposta.contains("http://silvio.unibas.it/"));
//            Assert.assertTrue("Test SOAP 1.2 con Header", risposta.contains("PAGINA PROTETTA"));

            richiesta = "<test/>";
            risposta = new SSOClient().sendSoapSamlRequest(mappaParametriPDDinamica, risorsa, shibSession.getElementAssertion(), shibSession.getSession(), richiesta);
            Assert.assertTrue("Test non soap", risposta.contains("Il messaggio da inviare non e' una busta soap!"));

        } catch (Exception ex) {
            ex.printStackTrace();
            Assert.fail(ex.getMessage());
        }
    }

    public void testDiscoveryService() {
        try {
            ShibbolethDriver clientShibboleth = new ShibbolethDriver();

            String risorsa = "https://sp.example.unibas.org/secure/";

            Map<String, String> mappaDiscoveryService = clientShibboleth.verificaDiscoveryService(risorsa);
            Assert.assertNotNull(mappaDiscoveryService);
            Assert.assertEquals(2, mappaDiscoveryService.size());
        } catch (Exception ex) {
            ex.printStackTrace();
            Assert.fail(ex.getMessage());
        }
    }
}
