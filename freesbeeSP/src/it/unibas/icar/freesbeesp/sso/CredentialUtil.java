package it.unibas.icar.freesbeesp.sso;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.opensaml.DefaultBootstrap;
import org.opensaml.xml.Configuration;
import org.opensaml.xml.ConfigurationException;
import org.opensaml.xml.XMLObjectBuilderFactory;
import org.opensaml.xml.security.credential.BasicCredential;
import org.opensaml.xml.security.credential.Credential;
import org.opensaml.xml.security.keyinfo.KeyInfoGenerator;
import org.opensaml.xml.security.keyinfo.KeyInfoHelper;
import org.opensaml.xml.security.x509.KeyStoreX509CredentialAdapter;
import org.opensaml.xml.signature.KeyInfo;
import org.opensaml.xml.signature.Signature;
import org.opensaml.xml.signature.SignatureConstants;
import org.opensaml.xml.signature.X509IssuerSerial;

public class CredentialUtil {

    private static Log logger = LogFactory.getLog(CredentialUtil.class);
    private static XMLObjectBuilderFactory builderFactory;

    public static XMLObjectBuilderFactory getSAMLBuilder() throws ConfigurationException {
        if (builderFactory == null) {
            // OpenSAML 2.3
            DefaultBootstrap.bootstrap();
            builderFactory = Configuration.getBuilderFactory();
        }
        return builderFactory;
    }

    public static Signature createSignature(String jksName, String password, String certificateName) throws Exception {
        Credential signingCredential = getSigningCredential(jksName, password, certificateName);
        KeyInfoGenerator kiFactory = Configuration.getGlobalSecurityConfiguration().getKeyInfoGeneratorManager().getDefaultManager().getFactory(signingCredential).newInstance();

        Signature signature = (Signature) Configuration.getBuilderFactory().getBuilder(Signature.DEFAULT_ELEMENT_NAME).buildObject(Signature.DEFAULT_ELEMENT_NAME);

        signature.setSigningCredential(signingCredential);
        signature.setSignatureAlgorithm(SignatureConstants.ALGO_ID_SIGNATURE_RSA_SHA1);
        signature.setCanonicalizationAlgorithm(SignatureConstants.ALGO_ID_C14N_EXCL_OMIT_COMMENTS);

        signature.setKeyInfo(kiFactory.generate(signingCredential));
        return signature;
    }

    public static Signature createsignaturePKCS(String pkcsName, String password, String certificateName) throws Exception {
        Credential signingCredential = getSigningCredentialPKCS(pkcsName, password, certificateName);
        Signature signature = (Signature) Configuration.getBuilderFactory().getBuilder(Signature.DEFAULT_ELEMENT_NAME).buildObject(Signature.DEFAULT_ELEMENT_NAME);
        signature.setSigningCredential(signingCredential);
        signature.setSignatureAlgorithm(SignatureConstants.ALGO_ID_SIGNATURE_RSA_SHA1);
        signature.setCanonicalizationAlgorithm(SignatureConstants.ALGO_ID_C14N_EXCL_OMIT_COMMENTS);
        signature.setKeyInfo(getKeyInfo(signingCredential));
        
        return signature;
    }

    private static KeyInfo getKeyInfo(Credential signingCredential) throws Exception {

        KeyInfo keyinfo = (KeyInfo) getSAMLBuilder().getBuilder(KeyInfo.DEFAULT_ELEMENT_NAME).buildObject(KeyInfo.DEFAULT_ELEMENT_NAME);

        X509Certificate cert = ((KeyStoreX509CredentialAdapter) signingCredential).getEntityCertificate();
        KeyInfoHelper.addCertificate(keyinfo, cert);
        X509IssuerSerial is = KeyInfoHelper.buildX509IssuerSerial(cert.getIssuerX500Principal().getName(), cert.getSerialNumber());
        keyinfo.getX509Datas().get(0).getX509IssuerSerials().add(is);
        return keyinfo;

    }

    public static Credential getSigningCredentialPKCS(String pkcsName, String password, String certificateName) throws Exception {
        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(new FileInputStream(pkcsName), password.toCharArray());
        KeyStoreX509CredentialAdapter ksca = new KeyStoreX509CredentialAdapter(ks, certificateName, password.toCharArray());

        return ksca;
    }

    public static Credential getSigningCredential(String jksName, String password, String certificateName) throws Exception {
//        InputStream isJKS = TestSAMLResponse.class.getResourceAsStream(jksName);
        InputStream isJKS = new FileInputStream(jksName);
        KeyStore keyStore = KeyStore.getInstance("JKS", "SUN");
        keyStore.load(isJKS, password.toCharArray());
        PrivateKey privateKey = (PrivateKey) keyStore.getKey(certificateName, password.toCharArray());
        Certificate cert = keyStore.getCertificate(certificateName);
        BasicCredential credential = new BasicCredential();
        credential.setPrivateKey(privateKey);
        if (cert != null) {
            credential.setPublicKey(cert.getPublicKey());
        }
        return credential;
    }
}
