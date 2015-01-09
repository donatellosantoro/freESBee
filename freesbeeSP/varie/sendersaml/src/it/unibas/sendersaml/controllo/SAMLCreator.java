package it.unibas.sendersaml.controllo;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.joda.time.DateTime;
import org.opensaml.DefaultBootstrap;
import org.opensaml.common.SAMLObjectBuilder;
import org.opensaml.common.SAMLVersion;
import org.opensaml.common.impl.SecureRandomIdentifierGenerator;
import org.opensaml.saml2.core.Assertion;
import org.opensaml.saml2.core.Attribute;
import org.opensaml.saml2.core.AttributeStatement;
import org.opensaml.saml2.core.AttributeValue;
import org.opensaml.saml2.core.AuthnContext;
import org.opensaml.saml2.core.AuthnContextClassRef;
import org.opensaml.saml2.core.AuthnStatement;
import org.opensaml.saml2.core.Conditions;
import org.opensaml.saml2.core.Issuer;
import org.opensaml.saml2.core.NameID;
import org.opensaml.saml2.core.Subject;
import org.opensaml.saml2.core.SubjectConfirmation;
import org.opensaml.saml2.core.SubjectConfirmationData;
import org.opensaml.xml.Configuration;
import org.opensaml.xml.ConfigurationException;
import org.opensaml.xml.XMLObjectBuilder;
import org.opensaml.xml.XMLObjectBuilderFactory;
import org.opensaml.xml.io.MarshallerFactory;
import org.opensaml.xml.schema.XSAny;

public class SAMLCreator {

    private static XMLObjectBuilderFactory builderFactory;
    private static MarshallerFactory marshallerFactory;

    public static XMLObjectBuilderFactory getSAMLBuilder() throws ConfigurationException {
        if (builderFactory == null) {
            // OpenSAML 2.3
            DefaultBootstrap.bootstrap();
            builderFactory = Configuration.getBuilderFactory();
        }
        return builderFactory;
    }
    
    public static MarshallerFactory getMarshallerFactory() throws ConfigurationException {
        if (marshallerFactory == null) {
            // OpenSAML 2.3
            DefaultBootstrap.bootstrap();
            marshallerFactory = Configuration.getMarshallerFactory();
        }
        return marshallerFactory;
    }

    @SuppressWarnings("unchecked")
    public static Assertion createAssertion(String idpName, String nameID, String username, String ip) {
        SAMLInputContainer input = new SAMLInputContainer();
//            input.setStrIssuer("freesbeesp-guard");
        input.setStrIssuer(idpName);
//            input.setStrNameID("80002950766/JWB");  // utenza concordata con Sogei
        input.setStrNameID(nameID);  // utenza concordata con Sogei

        Map customAttributes = new HashMap();
//            customAttributes.put("User", "utenteok");  // ID operatore
        customAttributes.put("User", username);  // ID operatore
//            customAttributes.put("IP-User", "192.168.1.234");  // indirizzo IP operatore
        customAttributes.put("IP-User", ip);  // indirizzo IP operatore

        input.setAttributes(customAttributes);
        Assertion assertion = buildDefaultAssertion(input);

        return assertion;
    }

    public static Assertion buildDefaultAssertion(SAMLInputContainer input) {
        try {
            // ID generator
//            SecureRandomIdentifierGenerator idGenerator = new SecureRandomIdentifierGenerator();
            Calendar calendar = new GregorianCalendar();
            
            // Create the NameIdentifier
            SAMLObjectBuilder nameIdBuilder = (SAMLObjectBuilder) getSAMLBuilder().getBuilder(NameID.DEFAULT_ELEMENT_NAME);
            NameID nameId = (NameID) nameIdBuilder.buildObject();
            nameId.setValue(input.getStrNameID());
//	         nameId.setNameQualifier(input.getStrNameQualifier());
            nameId.setFormat(NameID.UNSPECIFIED);

            // Create the SubjectConfirmation
            SAMLObjectBuilder confirmationDataBuilder = (SAMLObjectBuilder) getSAMLBuilder().getBuilder(SubjectConfirmationData.DEFAULT_ELEMENT_NAME);
            SubjectConfirmationData confirmationData = (SubjectConfirmationData) confirmationDataBuilder.buildObject();
            DateTime now = new DateTime();
            confirmationData.setNotBefore(now);
            confirmationData.setNotOnOrAfter(now.plusHours(4));

            SAMLObjectBuilder subjectConfirmationBuilder = (SAMLObjectBuilder) getSAMLBuilder().getBuilder(SubjectConfirmation.DEFAULT_ELEMENT_NAME);
            SubjectConfirmation subjectConfirmation = (SubjectConfirmation) subjectConfirmationBuilder.buildObject();
//            subjectConfirmation.setMethod(SubjectConfirmation.METHOD_BEARER);
            subjectConfirmation.setMethod("urn:oasis:names:tc:SAML:2.0:cm:bearer");
            subjectConfirmation.setSubjectConfirmationData(confirmationData);

            // Create the Subject
            SAMLObjectBuilder subjectBuilder = (SAMLObjectBuilder) getSAMLBuilder().getBuilder(Subject.DEFAULT_ELEMENT_NAME);
            Subject subject = (Subject) subjectBuilder.buildObject();

            subject.setNameID(nameId);
            subject.getSubjectConfirmations().add(subjectConfirmation);

            // Create Authentication Statement
            SAMLObjectBuilder authStatementBuilder = (SAMLObjectBuilder) getSAMLBuilder().getBuilder(AuthnStatement.DEFAULT_ELEMENT_NAME);
            AuthnStatement authnStatement = (AuthnStatement) authStatementBuilder.buildObject();
            //authnStatement.setSubject(subject);
            //authnStatement.setAuthenticationMethod(strAuthMethod);
            DateTime now2 = new DateTime();
            authnStatement.setAuthnInstant(now2);
//	         authnStatement.setSessionIndex(input.getSessionId());
            authnStatement.setSessionNotOnOrAfter(now2.plusMinutes(input.getMaxSessionTimeoutInMinutes()));

            SAMLObjectBuilder authContextBuilder = (SAMLObjectBuilder) getSAMLBuilder().getBuilder(AuthnContext.DEFAULT_ELEMENT_NAME);
            AuthnContext authnContext = (AuthnContext) authContextBuilder.buildObject();

            SAMLObjectBuilder authContextClassRefBuilder = (SAMLObjectBuilder) getSAMLBuilder().getBuilder(AuthnContextClassRef.DEFAULT_ELEMENT_NAME);
            AuthnContextClassRef authnContextClassRef = (AuthnContextClassRef) authContextClassRefBuilder.buildObject();
            authnContextClassRef.setAuthnContextClassRef(AuthnContext.UNSPECIFIED_AUTHN_CTX);

            authnContext.setAuthnContextClassRef(authnContextClassRef);
            authnStatement.setAuthnContext(authnContext);

            // Builder Attributes
            SAMLObjectBuilder attrStatementBuilder = (SAMLObjectBuilder) getSAMLBuilder().getBuilder(AttributeStatement.DEFAULT_ELEMENT_NAME);
            AttributeStatement attrStatement = (AttributeStatement) attrStatementBuilder.buildObject();

            // Create the attribute statement
            Map attributes = input.getAttributes();
            if (attributes != null) {
                Set keySet = attributes.keySet();
                for (Object key : keySet) {
//	        		 Attribute attr = buildStringAttribute((String)key, (String)attributes.get(key));
                    Attribute attr = buildSimpleAttribute((String) key, (String) attributes.get(key));
                    attrStatement.getAttributes().add(attr);
                }
            }

            SAMLObjectBuilder conditionsBuilder = (SAMLObjectBuilder) getSAMLBuilder().getBuilder(Conditions.DEFAULT_ELEMENT_NAME);
            Conditions conditions = (Conditions) conditionsBuilder.buildObject();
            conditions.setNotBefore(now);
            conditions.setNotOnOrAfter(now.plusHours(4));

            // Create Issuer
            SAMLObjectBuilder issuerBuilder = (SAMLObjectBuilder) getSAMLBuilder().getBuilder(Issuer.DEFAULT_ELEMENT_NAME);
            Issuer issuer = (Issuer) issuerBuilder.buildObject();
            issuer.setValue(input.getStrIssuer());

            // Create the assertion
            SAMLObjectBuilder assertionBuilder = (SAMLObjectBuilder) getSAMLBuilder().getBuilder(Assertion.DEFAULT_ELEMENT_NAME);
            Assertion assertion = (Assertion) assertionBuilder.buildObject();
            assertion.setVersion(SAMLVersion.VERSION_20);
            assertion.setIssueInstant(now);
//            assertion.setID(idGenerator.generateIdentifier());
            assertion.setID(calendar.getTimeInMillis()+"");

            // Issuer
            assertion.setIssuer(issuer);

            // Subject
            assertion.setSubject(subject);

            // Conditions
            assertion.setConditions(conditions);

            // AuthnStatement
            assertion.getAuthnStatements().add(authnStatement);

            // AttributeStatement
            assertion.getAttributeStatements().add(attrStatement);

            return assertion;
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return null;
    }

    public static Attribute buildSimpleAttribute(String name, String value)
            throws ConfigurationException {

        SAMLObjectBuilder attrBuilder = (SAMLObjectBuilder) getSAMLBuilder().getBuilder(Attribute.DEFAULT_ELEMENT_NAME);
        Attribute attr = (Attribute) attrBuilder.buildObject();
        attr.setName(name);
        attr.setNameFormat(Attribute.UNSPECIFIED);

        XMLObjectBuilder xsAnyBuilder = getSAMLBuilder().getBuilder(XSAny.TYPE_NAME);
        XSAny attrValue = (XSAny) xsAnyBuilder.buildObject(AttributeValue.DEFAULT_ELEMENT_NAME);
        attrValue.setTextContent(value);

        attr.getAttributeValues().add(attrValue);
        return attr;
    }
}
