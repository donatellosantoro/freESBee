package it.unibas.silvio.xml;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Attribute;
import org.jdom.Namespace;
import org.w3c.dom.DOMException;
import org.jdom.Document;
import org.jdom.Element;

public class XSDParser {

    private static Log logger = LogFactory.getLog(XSDParser.class);
    public static final Namespace XSD_NAMESPACE = Namespace.getNamespace("xs", "http://www.w3.org/2001/XMLSchema");

    public static void eliminaNamespace(Document schemaDocument) {
        if (schemaDocument == null) {
            throw new IllegalArgumentException("Impossibile ripulire uno schema null");
        }
        Element rootElement = schemaDocument.getRootElement();
        if (rootElement == null) {
            return;
        }
        String targetNamespace = rootElement.getAttributeValue("targetNamespace");
        if (targetNamespace == null || targetNamespace.isEmpty()) {
            //NON C'E' NESSUN NAMESPACE DI DEFAULT
            return;
        }
        List<String> prefissiEliminare = leggiPrefissiEliminare(rootElement, targetNamespace);
        eliminaNSDaAttributi(rootElement, prefissiEliminare);
    }
    
    public static String getTargetNamespace(Document schemaDocument) {
        if (schemaDocument == null) {
            throw new IllegalArgumentException("Impossibile ripulire uno schema null");
        }
        Element rootElement = schemaDocument.getRootElement();
        if (rootElement == null) {
            return null;
        }
        String targetNamespace = rootElement.getAttributeValue("targetNamespace");
        if (targetNamespace == null || targetNamespace.isEmpty()) {
            //NON C'E' NESSUN NAMESPACE DI DEFAULT
            return null;
        }
        return targetNamespace;
    }

    private static void eliminaNSDaAttributi(Element element, List<String> prefissiEliminare) {
        List<Attribute> listaAttributi = element.getAttributes();
        if (listaAttributi != null) {
            for (Attribute attributo : listaAttributi) {
                String valoreAttributo = attributo.getValue();
                int indexPrefisso = contienePrefisso(valoreAttributo, prefissiEliminare);
                if (indexPrefisso > 0) {
                    String attributoRipulito = valoreAttributo.substring(indexPrefisso + 1);
                    logger.info("Sostituisco il valore " + valoreAttributo + " con " + attributoRipulito);
                    attributo.setValue(attributoRipulito);
                }
            }
        }
        List<Element> figli = element.getChildren();
        if (figli != null) {
            for (Element figlio : figli) {
                eliminaNSDaAttributi(figlio, prefissiEliminare);
            }
        }
    }

    private static int contienePrefisso(String valoreAttributo, List<String> prefissiEliminare) {
        for (String string : prefissiEliminare) {
            if (valoreAttributo.startsWith(string)) {
                return string.length();
            }
        }
        return -1;
    }

    private static List<String> leggiPrefissiEliminare(Element rootElement, String targetNamespace) throws DOMException {
        List<String> prefissiEliminare = new ArrayList<String>();
        List<Namespace> listaNS = rootElement.getAdditionalNamespaces();
        for (Namespace namespace : listaNS) {
            if (namespace.getURI().equals(targetNamespace) && !namespace.getPrefix().isEmpty()) {
                logger.info("Dobbiamo eliminare il prefisso " + namespace.getPrefix());
                prefissiEliminare.add(namespace.getPrefix());
            }
        }
        return prefissiEliminare;
    }

    public static String estraiPrefisso(String nomeAttributo) {
        if (nomeAttributo == null || nomeAttributo.isEmpty() || !nomeAttributo.startsWith("xmlns:")) {
            return null;
        }
        if (nomeAttributo.matches("(?i)xmlns:[\\w]*")) {
            return nomeAttributo.replaceAll("(?i)xmlns:", "");
        }
        return null;
    }
}
