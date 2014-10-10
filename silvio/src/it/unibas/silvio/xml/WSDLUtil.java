package it.unibas.silvio.xml;

import com.ibm.wsdl.extensions.schema.SchemaImportImpl;
import com.ibm.wsdl.extensions.schema.SchemaReferenceImpl;
import com.ibm.wsdl.xml.WSDLReaderImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import javax.wsdl.Definition;
import javax.wsdl.Import;
import javax.wsdl.Types;
import javax.wsdl.WSDLException;
import javax.wsdl.extensions.schema.Schema;
import javax.wsdl.xml.WSDLReader;
import javax.xml.namespace.QName;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.helpers.XMLUtils;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class WSDLUtil {

    public static final QName QNAME_SCHEMA = new QName("http://www.w3.org/2001/XMLSchema", "schema", "xs");
    public static final String XSD_NAMESPACE = "http://www.w3.org/2001/XMLSchema";
    private static Log logger = LogFactory.getLog(WSDLUtil.class);

    public static org.w3c.dom.Element estraiDocSchema(String fileWSDL, String tipo) throws XmlException {
        try {
            org.w3c.dom.Document doc = XMLUtils.newDocument();
            org.w3c.dom.Element rootXSDElement = XMLUtils.createElementNS(doc, QNAME_SCHEMA);
            rootXSDElement.setPrefix("xs");

            Definition definition = readDefinition(fileWSDL);
            List<Schema> listaSchemi = WSDLUtil.getSchemas(definition);
            logger.info("Trovati " + listaSchemi.size() + " schemi");
            for (Schema schema : listaSchemi) {
                logger.info("Trovato uno schema XSD in " + schema.getDocumentBaseURI());
                elaboraSchema(schema, rootXSDElement);
            }

            if (tipo != null) {
                WSDLUtil.ripulisciSchema(rootXSDElement, tipo);
            }
            return rootXSDElement;
        } catch (Exception ex) {
            if (logger.isInfoEnabled()) {
                ex.printStackTrace();
            }
            logger.error("Impossibile caricare il WSDL e il suo XSD");
            throw new XmlException("Impossibile caricare il WSDL e il suo XSD");
        }
    }

    private static void elaboraSchema(Schema schema, Element rootXSDElement) throws DOMException {
        Element schemaElement = schema.getElement();
        elaboraNS(schemaElement, rootXSDElement);
        NodeList nodeList = schemaElement.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node clonedElement = nodeList.item(i).cloneNode(true);
            String nomeNodo = XmlUtil.eliminaPrefissoNS(clonedElement.getNodeName());
            String namespaceNodo = clonedElement.getNamespaceURI();
//            if (!nomeNodo.equalsIgnoreCase("import") || !XSD_NAMESPACE.equalsIgnoreCase(namespaceNodo)) {
//            if ((!nomeNodo.equalsIgnoreCase("import") || !XSD_NAMESPACE.equalsIgnoreCase(namespaceNodo)) && 
//                ((!nomeNodo.equalsIgnoreCase("include") || !XSD_NAMESPACE.equalsIgnoreCase(namespaceNodo)))) {
                rootXSDElement.getOwnerDocument().adoptNode(clonedElement);
                rootXSDElement.appendChild(clonedElement);
//            }
        }
//        elaboraImport(schema, rootXSDElement);
//        elaboraInclude(schema, rootXSDElement);
    }

    private static void elaboraNS(Element schemaElement, Element rootXSDElement) throws DOMException {
        String targetNamespace = schemaElement.getAttribute("targetNamespace");
        if (targetNamespace != null && !targetNamespace.isEmpty()) {
            logger.info("C'è un NS di default da includere " + targetNamespace);
            rootXSDElement.setAttribute("targetNamespace", targetNamespace);
        }

        String elementFormDefault = schemaElement.getAttribute("elementFormDefault");
        if (elementFormDefault != null && !elementFormDefault.isEmpty()) {
            rootXSDElement.setAttribute("elementFormDefault", elementFormDefault);
        }

        String attributeFormDefault = schemaElement.getAttribute("attributeFormDefault");
        if (attributeFormDefault != null && !attributeFormDefault.isEmpty()) {
            rootXSDElement.setAttribute("attributeFormDefault", attributeFormDefault);
        }


        copyNS(schemaElement, rootXSDElement);
        copyNS(schemaElement.getOwnerDocument().getDocumentElement(), rootXSDElement);
    }

    private static void copyNS(Node source, Element target) throws DOMException {
        NamedNodeMap attributi = source.getAttributes();
        if (attributi == null) {
            return;
        }
        for (int i = 0; i < attributi.getLength(); i++) {
            Node att = attributi.item(i);
            if (att.getNodeName() != null && att.getNodeName().toLowerCase().startsWith("xmlns") && !att.getNodeName().equalsIgnoreCase("xmlns:xs")) {
                if (!target.hasAttribute(att.getNodeName())) {
                    target.setAttribute(att.getNodeName(), att.getNodeValue());
                }
            }
        }
    }

    private static void elaboraImport(Schema schema, Element rootXSDElement) throws DOMException {
        Map<String, Vector> imports = schema.getImports();
        Set<String> nomiImport = imports.keySet();
        for (String nomeImp : nomiImport) {
            logger.info("Elaboro l'import " + nomeImp);
            Vector<SchemaImportImpl> listaImport = imports.get(nomeImp);
            for (SchemaImportImpl imp : listaImport) {
                logger.info("File import: " + imp.getSchemaLocationURI());
                Schema schemaImp = imp.getReferencedSchema();
                elaboraSchema(schemaImp, rootXSDElement);
            }
        }
    }

    private static void elaboraInclude(Schema schema, Element rootXSDElement) throws DOMException {
        List<SchemaReferenceImpl> listaInclude = schema.getIncludes();
        logger.info("Trovati " + listaInclude.size() + " import");
        for (SchemaReferenceImpl imp : listaInclude) {
            logger.info("File include: " + imp.getSchemaLocationURI());
            Schema schemaImp = imp.getReferencedSchema();
            elaboraSchema(schemaImp, rootXSDElement);
        }
//        Set<String> nomiImport = imports.keySet();
//        for (String nomeImp : nomiImport) {
//            logger.info("Elaboro l'import " + nomeImp);
//            Vector<SchemaImportImpl> listaImport = imports.get(nomeImp);
//            for (SchemaImportImpl imp : listaImport) {
//                logger.info("File import: " + imp.getSchemaLocationURI());
//                Schema schemaImp = imp.getReferencedSchema();
//                elaboraSchema(schemaImp, rootXSDElement);
//            }
//        }
    }

    public static void ripulisciSchema(Element schemaCompleto, String nomeOperation) {
        NodeList listaFigli = schemaCompleto.getChildNodes();
        for (int i = 0; i < listaFigli.getLength(); i++) {
            Node figlio = listaFigli.item(i);
            String nomeNodo = XmlUtil.eliminaPrefissoNS(figlio.getNodeName());
            String namespaceNodo = figlio.getNamespaceURI();
            if (nomeNodo.equalsIgnoreCase("element") && XSD_NAMESPACE.equalsIgnoreCase(namespaceNodo)) {
                NamedNodeMap attributi = figlio.getAttributes();
                Node nomeElement = attributi.getNamedItem("name");
                if (nomeElement == null || nomeElement.getTextContent() == null || !nomeElement.getTextContent().equals(nomeOperation)) {
                    schemaCompleto.removeChild(figlio);
                }
            }
        }
    }

    public static List<javax.wsdl.extensions.schema.Schema> getSchemas(Definition definition) {
        Types types = definition.getTypes();
        List<javax.wsdl.extensions.schema.Schema> schemaList =
                new ArrayList<javax.wsdl.extensions.schema.Schema>();
        if (types != null) {
            for (Object o : types.getExtensibilityElements()) {
                if (o instanceof javax.wsdl.extensions.schema.Schema) {
                    javax.wsdl.extensions.schema.Schema s =
                            (javax.wsdl.extensions.schema.Schema) o;
                    schemaList.add(s);
                }
            }
        }

        Map wsdlImports = definition.getImports();
        for (Object o : wsdlImports.values()) {
            if (o instanceof List) {
                for (Object p : (List) o) {
                    if (p instanceof Import) {
                        schemaList.addAll(getSchemas(((Import) p).getDefinition()));
                    }
                }
            }
        }
        return schemaList;
    }

    private static Definition readDefinition(String fileWSDL) throws WSDLException, IllegalArgumentException {
        WSDLReader wsdlReader = new WSDLReaderImpl();
        wsdlReader.setFeature("javax.wsdl.verbose", true);
        wsdlReader.setFeature("javax.wsdl.importDocuments", true);
        Definition definition = wsdlReader.readWSDL(fileWSDL);
        logger.info("Loaded definition: " + (definition != null ? "ok" : "null"));
        return definition;
    }
}
