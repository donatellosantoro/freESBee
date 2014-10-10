package it.unibas.icar.freesbeesp.sso;

import it.unibas.icar.freesbeesp.exception.FreesbeeSPException;
import it.unibas.icar.freesbeesp.modello.ConfigurazioneStatico;
import it.unibas.icar.freesbeesp.sso.driver.HtmlLoginFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.helpers.XMLUtils;
import org.jdom.input.DOMBuilder;
import org.opensaml.DefaultBootstrap;
import org.opensaml.xml.util.Base64;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.opensaml.saml2.core.Assertion;
import org.opensaml.saml2.core.EncryptedAssertion;
import org.opensaml.saml2.core.EncryptedID;
import org.opensaml.saml2.core.NameID;
import org.opensaml.saml2.core.Response;
import org.opensaml.saml2.core.Subject;
import org.opensaml.saml2.core.impl.ResponseUnmarshaller;
import org.opensaml.saml2.encryption.Decrypter;
import org.opensaml.xml.encryption.InlineEncryptedKeyResolver;
import org.opensaml.xml.io.MarshallerFactory;
import org.opensaml.xml.security.credential.Credential;
import org.opensaml.xml.security.keyinfo.StaticKeyInfoCredentialResolver;
import org.opensaml.xml.signature.Signature;

public class SAMLResponseUtil {

    private static Log logger = LogFactory.getLog(SAMLResponseUtil.class);

    public static org.jdom.Document decryptAssertion(SAMLResponse mySAMLResponse) throws Exception {
        ConfigurazioneStatico configurazioneStatico = ConfigurazioneStatico.getInstance();

        DefaultBootstrap.bootstrap();
        MarshallerFactory marshallerFactory = org.opensaml.Configuration.getMarshallerFactory();
        ResponseUnmarshaller responseUnmarsh = new ResponseUnmarshaller();

        Response response = (Response) responseUnmarsh.unmarshall(XMLUtils.parse(mySAMLResponse.getSamlResponseDecoded()).getDocumentElement());

        if (!(new HtmlLoginFactory().getInstance() instanceof it.unibas.icar.freesbeesp.sso.driver.ibasho.HtmlLogin)) {
            Credential credential = CredentialUtil.getSigningCredential(configurazioneStatico.getPercorsoFileCertificatoSP(),
                    configurazioneStatico.getPasswordSP(),
                    configurazioneStatico.getNomeCertificatoSP());
            Decrypter decrypter = new Decrypter(null, new StaticKeyInfoCredentialResolver(credential), new InlineEncryptedKeyResolver());

            Signature signatureAss = CredentialUtil.createSignature(configurazioneStatico.getPercorsoFileCertificatoIdP(),
                    configurazioneStatico.getPasswordIdP(),
                    configurazioneStatico.getNomeCertificatoIdP());
            List<EncryptedAssertion> listEncAssertion = response.getEncryptedAssertions();
            for (EncryptedAssertion encryptedAssertion : listEncAssertion) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Cerco di decryptare l'asserzione " + encryptedAssertion.getElementQName());
                }
                Assertion assertion = decrypter.decrypt(encryptedAssertion);
                decriptaSubject(assertion.getSubject(), decrypter);
                if (logger.isDebugEnabled()) {
                    logger.debug("Asserzioni decriptate");
                }
                assertion.setSignature(signatureAss);
                marshallerFactory.getMarshaller(assertion).marshall(assertion);
                org.opensaml.xml.signature.Signer.signObject(signatureAss);
                response.getAssertions().add(assertion);
            }
            response.getEncryptedAssertions().clear();

            Signature signature = CredentialUtil.createSignature(configurazioneStatico.getPercorsoFileCertificatoIdP(),
                    configurazioneStatico.getPasswordIdP(),
                    configurazioneStatico.getNomeCertificatoIdP());
            response.setSignature(signature);
            if (logger.isDebugEnabled()) {
                logger.debug("Response signature: " + response.getSignature());
            }
            marshallerFactory.getMarshaller(response).marshall(response);

            org.opensaml.xml.signature.Signer.signObject(signature);
            String stringaSAMLResponse = XMLUtils.toString(response.getDOM());
            if (logger.isDebugEnabled()) {
                logger.debug("SAML Response decriptata: " + stringaSAMLResponse);
            }
        } else {
            // Se ibasho manda tutto in chiaro. firmiamo solo il messaggio
            Signature signatureAss = CredentialUtil.createsignaturePKCS(configurazioneStatico.getPercorsoFileCertificatoIdP(),
                    configurazioneStatico.getPasswordIdP(),
                    configurazioneStatico.getNomeCertificatoIdP());
            for (Assertion assertion : response.getAssertions()) {
                assertion.setSignature(signatureAss);
                marshallerFactory.getMarshaller(assertion).marshall(assertion);
                org.opensaml.xml.signature.Signer.signObject(signatureAss);
            }
            marshallerFactory.getMarshaller(response).marshall(response);
        }

        DOMBuilder builder = new DOMBuilder();
        org.jdom.Document jdomSAMLResponse = builder.build(response.getDOM().getOwnerDocument());

        return jdomSAMLResponse;
    }

    private static void decriptaSubject(Subject subject, Decrypter decrypter) throws Exception {
        EncryptedID encID = subject.getEncryptedID();
        NameID decID = (NameID) decrypter.decrypt(encID);
        if (logger.isInfoEnabled()) {
            logger.info("Subject decripted");
        }
        subject.setNameID(decID);
        subject.setEncryptedID(null);

        MarshallerFactory marshallerFactory = org.opensaml.Configuration.getMarshallerFactory();
        marshallerFactory.getMarshaller(subject).marshall(subject);
    }

    public static SAMLResponse extractSAMLResponse(InputStream xhtmlDocumentIS) throws FreesbeeSPException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document dom = db.parse(xhtmlDocumentIS);
            String samlResponse = null;
            String indirizzo = null;
            String relayState = null;

            NodeList formInputList = dom.getElementsByTagName("input");
            for (int i = 0; i < formInputList.getLength(); i++) {
                Node formInput = formInputList.item(i);
                NamedNodeMap inputAttributes = formInput.getAttributes();
                Node name = inputAttributes.getNamedItem("name");

                if (name != null && "SAMLResponse".equals(name.getNodeValue())) {
                    String encodedSAMLResponse = inputAttributes.getNamedItem("value").getNodeValue();
                    String decodedSAMLResponse = new String(Base64.decode(encodedSAMLResponse));
                    if (logger.isDebugEnabled()) {
                        logger.debug("SAML Response: " + decodedSAMLResponse);
                    }
                    samlResponse = encodedSAMLResponse;
                }
                if (name != null && "RelayState".equals(name.getNodeValue())) {
                    relayState = inputAttributes.getNamedItem("value").getNodeValue();
                }
            }

            NodeList formList = dom.getElementsByTagName("form");
            if (formList.getLength() != 1) {
                logger.error("Impossibile risalire alla risposta SAML. La risposta deve contenere un form, invece ne contiene " + formList.getLength());
                throw new SAXException("Impossibile risalire alla risposta SAML. La risposta deve contenere un form, invece ne contiene " + formList.getLength());
            }
            Node form = formList.item(0);
            NamedNodeMap inputAttributes = form.getAttributes();
            Node name = inputAttributes.getNamedItem("action");
            indirizzo = name.getNodeValue();

            if (samlResponse == null || indirizzo == null || relayState == null) {
                logger.error("Impossibile risalire alla risposta SAML.\nsamlResponse: " + samlResponse + "\nindirizzo: " + indirizzo + "\nrelayState: " + relayState);
                throw new SAXException("Impossibile risalire alla risposta SAML.\nsamlResponse: " + samlResponse + "\nindirizzo: " + indirizzo + "\nrelayState: " + relayState);
            }

            return new SAMLResponse(samlResponse, indirizzo, relayState);

        } catch (ParserConfigurationException e) {
            logger.error("Impossibile caricare la configurazione per il parser DOM: ", e);
            throw new FreesbeeSPException("Impossibile caricare la configurazione per il parser DOM: " + e.getLocalizedMessage());
        } catch (IOException e) {
            logger.error("Impossibile effettuare il parsing del documento XHTML: ", e);
            throw new FreesbeeSPException("Impossibile effettuare il parsing del documento XHTML: " + e.getLocalizedMessage());
        } catch (Exception e) {
            logger.error("Impossibile estrarre la SAMLResponse", e);
            throw new FreesbeeSPException("Impossibile estrarre la SAMLResponse.");
        }
    }

}
