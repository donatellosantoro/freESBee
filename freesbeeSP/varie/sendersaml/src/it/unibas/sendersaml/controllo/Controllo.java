package it.unibas.sendersaml.controllo;

import java.util.HashMap;
import java.util.Map;
import org.jdom.Element;
import org.opensaml.saml2.core.Assertion;
import org.opensaml.xml.signature.Signature;

public class Controllo {

    public static void main(String args[]) {
        try {
            SAMLCreator.getSAMLBuilder();
            Assertion assertion = SAMLCreator.createAssertion("freesbeesp-guard", "80002950766/JWB", "utenteok", "192.168.2.123");
            System.out.println(assertion);
            Signature signature = CredentialUtil.createsignaturePKCS("/Users/michele/Desktop/Regione/SOGEI/Test_Sogei/testsogei/testSAMLsigner.p12", "sogei", "testsamlsigner");
//            Signature signature = CredentialUtil.createsignatureJKS("/Users/michele/Desktop/Regione/SOGEI/Test_Sogei/testsogei/keystoreRegioneBasilicataNICA.jks", "password", "RegioneBasilicataNICA.spcoop.gov.it (SPCoop CA1)");
            System.out.println(signature);
            assertion.setSignature(signature);
            SAMLCreator.getMarshallerFactory().getMarshaller(assertion).marshall(assertion);
            org.opensaml.xml.signature.Signer.signObject(signature);
            SSOClient client = new SSOClient();
            Map<String, String> mappa = new HashMap<String, String>();
            String ssoSession = "valoreACaso;0981";
            String richiesta = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:anag=\"http://anagrafica.verifica.anagrafica\">\n"
                    + "   <soapenv:Header/>\n"
                    + "   <soapenv:Body>\n"
                    + "      <anag:interrogazioneCodiceFiscalePersonaFisica>\n"
//                    + "         <anag:codiceFiscale>TRTCML64B59G942B</anag:codiceFiscale>\n"
                    + "         <anag:codiceFiscale>GLVLXA67A12Z154K</anag:codiceFiscale>\n"
                    + "      </anag:interrogazioneCodiceFiscalePersonaFisica>\n"
                    + "   </soapenv:Body>\n"
                    + "</soapenv:Envelope>";
            Element toJdom = DOMUtil.toJdom(assertion.getDOM());
//            String risposta = client.sendSoapSamlRequest(mappa, "http://pdd.regione.basilicata.it:8080/pdd/PD/PD_AgenziaEntrate/?AZIONE=InterrogazioneCodiceFiscalePersonaFisica", toJdom, ssoSession, richiesta);
            String risposta = client.sendSoapSamlRequest(mappa, "http://172.18.17.227:8081/PD/PD_AgenziaEntrate/?AZIONE=InterrogazioneCodiceFiscalePersonaFisica", toJdom, ssoSession, richiesta);
            System.out.println("#### Risposta: " + risposta);
        } catch (Exception exception) {
            System.out.println(exception);
            exception.printStackTrace();
        }
    }
}
