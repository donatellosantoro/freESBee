package it.unibas.icar.freesbee.xml;

import it.unibas.icar.freesbee.ws.web.WSSoggettoImpl;
import java.io.InputStream;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

public class ValidatoreXSDBustaEGov {

//    private static Log logger = LogFactory.getLog(ValidatoreXSDBustaEGov.class);
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ValidatoreXSDBustaEGov.class.getName());
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
            logger.error("Il file IntestazioniEGov.xsd non è valido.");
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new XmlException("Il file IntestazioniEGov.xsd non è valido.");
        }
        validatore = xmlSchema.newValidator();
    }
}
