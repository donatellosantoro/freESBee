package it.unibas.icar.freesbeesp.sso;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.opensaml.xml.Configuration;
import org.opensaml.xml.security.credential.BasicCredential;
import org.opensaml.xml.security.credential.Credential;
import org.opensaml.xml.security.keyinfo.KeyInfoGenerator;
import org.opensaml.xml.signature.Signature;
import org.opensaml.xml.signature.SignatureConstants;

public class CredentialUtil {
    private static Log logger = LogFactory.getLog(CredentialUtil.class);

    public static Signature createSignature(String jksName,String password, String certificateName) throws Exception {
        Credential signingCredential = getSigningCredential(jksName,password,certificateName);
        KeyInfoGenerator kiFactory = Configuration.getGlobalSecurityConfiguration().getKeyInfoGeneratorManager().getDefaultManager().getFactory(signingCredential).newInstance();

        Signature signature = (Signature) Configuration.getBuilderFactory().getBuilder(Signature.DEFAULT_ELEMENT_NAME).buildObject(Signature.DEFAULT_ELEMENT_NAME);

        signature.setSigningCredential(signingCredential);
        signature.setSignatureAlgorithm(SignatureConstants.ALGO_ID_SIGNATURE_RSA_SHA1);
        signature.setCanonicalizationAlgorithm(SignatureConstants.ALGO_ID_C14N_EXCL_OMIT_COMMENTS);

        signature.setKeyInfo(kiFactory.generate(signingCredential));
        return signature;
    }

    public static Credential getSigningCredential(String jksName,String password, String certificateName) throws Exception {
//        InputStream isJKS = TestSAMLResponse.class.getResourceAsStream(jksName);
        InputStream isJKS = new FileInputStream(jksName);
        KeyStore keyStore = KeyStore.getInstance("JKS", "SUN");
        keyStore.load(isJKS, password.toCharArray());
        PrivateKey privateKey = (PrivateKey) keyStore.getKey(certificateName, password.toCharArray());
        logger.debug("PrivateKey: " + privateKey);
        Certificate cert = keyStore.getCertificate(certificateName);
        logger.debug("Certificato: " + cert);
        BasicCredential credential = new BasicCredential();
        credential.setPrivateKey(privateKey);
        if (cert != null) {
            credential.setPublicKey(cert.getPublicKey());
            if(logger.isDebugEnabled())logger.debug("setPublicKey: " + cert.getPublicKey());
        }
        return credential;
    }
}
