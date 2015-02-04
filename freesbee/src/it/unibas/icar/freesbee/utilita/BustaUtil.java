package it.unibas.icar.freesbee.utilita;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.helpers.XMLUtils;
import org.apache.servicemix.jbi.jaxp.SourceTransformer;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class BustaUtil {

    private static Log logger = LogFactory.getLog(BustaUtil.class);

    public static void stampaSOAPHeader(Map<QName, DocumentFragment> map) {
        Set<QName> keys = map.keySet();
        for (QName qName : keys) {
            DocumentFragment df = map.get(qName);
            XMLUtils.printDOM(df);
        }
    }

    public static Node cercaNodo(NodeList nl, String nodoDiRiferimento) {
        Node nodoFiglio = null;
        for (int i = 0; i < nl.getLength(); i++) {
            if (nl != null && nl.item(i) != null && nl.item(i).getLocalName() != null && (nl.item(i).getLocalName()).equals(nodoDiRiferimento)) {
                nodoFiglio = nl.item(i);
            }
        }
        return nodoFiglio;
    }

    public static List<Node> cercaListaNodo(NodeList nl, String nodoDiRiferimento) {
        List<Node> listaNodi = new ArrayList<Node>();
        for (int i = 0; i < nl.getLength(); i++) {
            if (nl != null && nl.item(i) != null && nl.item(i).getLocalName() != null && (nl.item(i).getLocalName()).equals(nodoDiRiferimento)) {
                listaNodi.add(nl.item(i));
            }
        }
        return listaNodi;
    }

    public static void aggiungiAttributo(Element elementoDiRiferimento, String nome, String valore) {
        elementoDiRiferimento.setAttribute(nome, valore);
    }

    public static String getAttributo(Node nodoDiRiferimento, String nome) {
        if (nodoDiRiferimento == null || nodoDiRiferimento.getAttributes() == null || nodoDiRiferimento.getAttributes().getNamedItem(nome) == null) {
            return null;
        }
        String attributo = nodoDiRiferimento.getAttributes().getNamedItem(nome).getNodeValue();
        if ((attributo != null) || (!attributo.equals(""))) {
            return attributo;
        }
        return null;
    }

    public static String getAttributo(Node nodoDiRiferimento, String ns, String nome) {
        if (nodoDiRiferimento == null || nodoDiRiferimento.getAttributes() == null) {
            return null;
        }

        String attributo = nodoDiRiferimento.getAttributes().getNamedItemNS(ns, nome).getNodeValue();
        if ((attributo != null) || (!attributo.equals(""))) {
            return attributo;
        }
        return null;
    }

    public static Element appendHeader(Node nodoDiRiferimento, String namespace, String prefissoNamespace, String nomeHeader) {
        Document doc = nodoDiRiferimento.getOwnerDocument();

        Element elementoFiglio = doc.createElementNS(namespace, prefissoNamespace + ":" + nomeHeader);
        nodoDiRiferimento.appendChild(elementoFiglio);

        return elementoFiglio;
    }

    public static Element appendHeader(Element elementoDiRiferimento, String namespace, String prefissoNamespace, String nomeHeader) {
        Document doc = elementoDiRiferimento.getOwnerDocument();

        Element elementoFiglio = doc.createElementNS(namespace, prefissoNamespace + ":" + nomeHeader);
        elementoDiRiferimento.appendChild(elementoFiglio);

        return elementoFiglio;
    }

    public static DocumentFragment removeHeader(String namespace, String prefissoNamespace, String nomeHeader) {
        try {
            Document doc = new SourceTransformer().createDocument();
            DocumentFragment df = doc.createDocumentFragment();
            Element el = doc.createElementNS(namespace, prefissoNamespace + ":" + nomeHeader);

            el.setAttributeNS(CostantiSOAP.NAMESPACE_XMLNS, CostantiSOAP.PREFIX_NAMESPACE_XMLNS + ":" + CostantiSOAP.PREFIX_XMLSCHEMA, CostantiSOAP.NAMESPACE_XMLSCHEMA);
            if (logger.isDebugEnabled()) logger.debug(CostantiSOAP.NAMESPACE_XMLSCHEMA + " - " + CostantiSOAP.PREFIX_NAMESPACE_XMLNS + ":" + CostantiSOAP.PREFIX_XMLSCHEMA + " - " + CostantiSOAP.NAMESPACE_XMLSCHEMA);
            el.setAttributeNS(CostantiSOAP.NAMESPACE_XMLNS, CostantiSOAP.PREFIX_NAMESPACE_XMLNS + ":" + CostantiSOAP.PREFIX_SOAP_ENVELOPE, CostantiSOAP.NAMESPACE_SOAP_ENVELOPE);
            if (logger.isDebugEnabled()) logger.debug(CostantiSOAP.NAMESPACE_SOAP_ENVELOPE + " - " + CostantiSOAP.PREFIX_NAMESPACE_XMLNS + ":" + CostantiSOAP.PREFIX_SOAP_ENVELOPE + " - " + CostantiSOAP.NAMESPACE_SOAP_ENVELOPE);
            el.setAttributeNS(CostantiSOAP.NAMESPACE_XMLNS, CostantiSOAP.PREFIX_NAMESPACE_XMLNS + ":" + CostantiSOAP.PREFIX_XMLSCHEMA_INSTANCE, CostantiSOAP.NAMESPACE_XMLSCHEMA_INSTANCE);
            if (logger.isDebugEnabled()) logger.debug(CostantiSOAP.NAMESPACE_XMLSCHEMA_INSTANCE + " - " + CostantiSOAP.PREFIX_NAMESPACE_XMLNS + ":" + CostantiSOAP.PREFIX_XMLSCHEMA_INSTANCE + " - " + CostantiSOAP.NAMESPACE_XMLSCHEMA_INSTANCE);

            el.setAttributeNS(CostantiSOAP.NAMESPACE_SOAP_ENVELOPE, CostantiSOAP.PREFIX_SOAP_ENVELOPE + ":" + CostantiSOAP.PREFIX_ACTOR_EGOV, CostantiSOAP.ACTOR_EGOV);
            el.setAttributeNS(CostantiSOAP.NAMESPACE_SOAP_ENVELOPE, CostantiSOAP.PREFIX_SOAP_ENVELOPE + ":" + CostantiSOAP.PREFIX_MUSTUNDERSTAND, CostantiSOAP.MUSTUNDERSTAND_TRUE);

            df.appendChild(el);
            return df;
        } catch (ParserConfigurationException e) {
            logger.error("Si e' verificato un errore nel sistema.");
            if (logger.isDebugEnabled()) e.printStackTrace();
        }
        return null;
    }

    public static DocumentFragment createEmptyHeader(String namespace, String prefissoNamespace, String nomeHeader) {
        try {
            Document doc = new SourceTransformer().createDocument();
            DocumentFragment df = doc.createDocumentFragment();
            Element el = doc.createElementNS(namespace, prefissoNamespace + ":" + nomeHeader);

            el.setAttributeNS(CostantiSOAP.NAMESPACE_XMLNS, CostantiSOAP.PREFIX_NAMESPACE_XMLNS + ":" + CostantiSOAP.PREFIX_XMLSCHEMA, CostantiSOAP.NAMESPACE_XMLSCHEMA);
            if (logger.isDebugEnabled()) logger.debug(CostantiSOAP.NAMESPACE_XMLSCHEMA + " - " + CostantiSOAP.PREFIX_NAMESPACE_XMLNS + ":" + CostantiSOAP.PREFIX_XMLSCHEMA + " - " + CostantiSOAP.NAMESPACE_XMLSCHEMA);
            el.setAttributeNS(CostantiSOAP.NAMESPACE_XMLNS, CostantiSOAP.PREFIX_NAMESPACE_XMLNS + ":" + CostantiSOAP.PREFIX_SOAP_ENVELOPE, CostantiSOAP.NAMESPACE_SOAP_ENVELOPE);
            if (logger.isDebugEnabled()) logger.debug(CostantiSOAP.NAMESPACE_SOAP_ENVELOPE + " - " + CostantiSOAP.PREFIX_NAMESPACE_XMLNS + ":" + CostantiSOAP.PREFIX_SOAP_ENVELOPE + " - " + CostantiSOAP.NAMESPACE_SOAP_ENVELOPE);
            el.setAttributeNS(CostantiSOAP.NAMESPACE_XMLNS, CostantiSOAP.PREFIX_NAMESPACE_XMLNS + ":" + CostantiSOAP.PREFIX_XMLSCHEMA_INSTANCE, CostantiSOAP.NAMESPACE_XMLSCHEMA_INSTANCE);
            if (logger.isDebugEnabled()) logger.debug(CostantiSOAP.NAMESPACE_XMLSCHEMA_INSTANCE + " - " + CostantiSOAP.PREFIX_NAMESPACE_XMLNS + ":" + CostantiSOAP.PREFIX_XMLSCHEMA_INSTANCE + " - " + CostantiSOAP.NAMESPACE_XMLSCHEMA_INSTANCE);

            el.setAttributeNS(CostantiSOAP.NAMESPACE_SOAP_ENVELOPE, CostantiSOAP.PREFIX_SOAP_ENVELOPE + ":" + CostantiSOAP.PREFIX_ACTOR_EGOV, CostantiSOAP.ACTOR_EGOV);
            el.setAttributeNS(CostantiSOAP.NAMESPACE_SOAP_ENVELOPE, CostantiSOAP.PREFIX_SOAP_ENVELOPE + ":" + CostantiSOAP.PREFIX_MUSTUNDERSTAND, CostantiSOAP.MUSTUNDERSTAND_TRUE);

            df.appendChild(el);
            return df;
        } catch (ParserConfigurationException e) {
            logger.error("Si e' verificato un errore nel sistema.");
            if (logger.isDebugEnabled()) e.printStackTrace();
        }
        return null;
    }

    public static DocumentFragment createTextHeader(String namespace, String prefissoNamespace, String nomeHeader, String valoreHeader) {
        try {
            Document doc = new SourceTransformer().createDocument();
            DocumentFragment df = doc.createDocumentFragment();
            Element el = doc.createElementNS(namespace, prefissoNamespace + ":" + nomeHeader);
            el.appendChild(doc.createTextNode(valoreHeader));
            df.appendChild(el);
            NodeList nl = df.getChildNodes();
//            if (logger.isDebugEnabled()) logger.debug("createTextHeader - Nodi nella NodeList : " + nl.getLength());
            return df;
        } catch (ParserConfigurationException e) {
            logger.error("Si e' verificato un errore nel sistema.");
            if (logger.isDebugEnabled()) e.printStackTrace();
        }
        return null;
    }

    public static Element appendValues(Node nodoDiRiferimento, String namespace, String prefissoNamespace, String nomeHeader, String valoreHeader) {

        Document doc = nodoDiRiferimento.getOwnerDocument();

        Element elementoFiglio = doc.createElementNS(namespace, prefissoNamespace + ":" + nomeHeader);
        elementoFiglio.setTextContent(valoreHeader);
        nodoDiRiferimento.appendChild(elementoFiglio);

        return elementoFiglio;
    }

    public static Element appendValues(Element elementoDiRiferimento, String namespace, String prefissoNamespace, String nomeHeader, String valoreHeader) {

        Document doc = elementoDiRiferimento.getOwnerDocument();

        Element elementoFiglio = doc.createElementNS(namespace, prefissoNamespace + ":" + nomeHeader);
        elementoFiglio.setTextContent(valoreHeader);
        elementoDiRiferimento.appendChild(elementoFiglio);

        return elementoFiglio;
    }
}
