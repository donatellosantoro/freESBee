package it.unibas.icar.freesbee.xml;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;

public class ValidatoreXSDBustaEGov {

    private static org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog(ValidatoreXSDBustaEGov.class);
    private static Validator validatore;

    public static Validator getValidatore() throws XmlException {
        if (validatore == null) {
            caricaValidatore();
        }
        return validatore;
    }

    private static void caricaValidatore() throws XmlException { // TODO: Caricare tutto dal disco locale
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        InputStream streamXSD = ValidatoreXSDBustaEGov.class.getResourceAsStream("/IntestazioniEGov.xsd");
        if(streamXSD==null){
            logger.error("Impossibile caricare il file IntestazioniEGov.xsd");
            throw new XmlException("Impossibile caricare il file IntestazioniEGov.xsd");
        }
        StreamSource ss = new StreamSource(streamXSD);
        Schema xmlSchema;
        try {
            xmlSchema = schemaFactory.newSchema(ss);
        } catch (SAXException ex) {
            if (logger.isDebugEnabled()) ex.printStackTrace();
            logger.error("Il file IntestazioniEGov.xsd non è valido. " + ex.getLocalizedMessage());
            throw new XmlException("Il file IntestazioniEGov.xsd non è valido.");
        }
        validatore = xmlSchema.newValidator();
    }
}
