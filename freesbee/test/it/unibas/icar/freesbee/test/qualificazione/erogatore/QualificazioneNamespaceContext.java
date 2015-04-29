package it.unibas.icar.freesbee.test.qualificazione.erogatore;

import it.unibas.icar.freesbee.test.qualificazione.UtilTest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

public class QualificazioneNamespaceContext implements NamespaceContext {

//    private Log logger = LogFactory.getLog(this.getClass());
    private static final Logger logger = LoggerFactory.getLogger(QualificazioneNamespaceContext.class.getName());
    private Map<String, String> namespaces = new HashMap<String, String>();

    public QualificazioneNamespaceContext() {
        namespaces.put("SOAP_ENV", "http://schemas.xmlsoap.org/soap/envelope/");
        namespaces.put("soapenv", "http://schemas.xmlsoap.org/soap/envelope/");
        namespaces.put("eGov_IT", "http://www.cnipa.it/schemas/2003/eGovIT/Busta1_0/");
        namespaces.put("p891", "http://sica.spcoop.it/servizi/QualificazionePDDWS/types");
    }

    public String getNamespaceURI(String prefix) {
        logger.debug("Richiesto il namespace per il prefisso " + prefix);
        String ns = namespaces.get(prefix);
        if (ns != null) {
            return ns;
        } else {
            return XMLConstants.NULL_NS_URI;
        }
    }

    public String getPrefix(String namespace) {
        logger.debug("Richiesto il prefisso per il namespace " + namespace);
        return null;
    }

    public Iterator getPrefixes(String namespace) {
        return null;
    }
}
