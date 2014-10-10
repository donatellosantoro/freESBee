package it.unibas.silvio.util.xml;

import it.unibas.silvio.modello.SilvioException;
import it.unibas.silvio.persistenza.DAOException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.cxf.helpers.XMLUtils;
import org.jdom.Document;
import org.jdom.input.DOMBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.jdom.transform.XSLTransformException;
import org.jdom.transform.XSLTransformer;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class XmlJDomUtil {

    private static Log logger = LogFactory.getLog(XmlJDomUtil.class);
    private static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    private static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
    private static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";

    public static String stampaXML(Document document) {
        if (document == null) {
            return "";
        }
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            XMLOutputter outputter = new XMLOutputter();
            outputter.setFormat(Format.getPrettyFormat());
            outputter.output(document, stream);
            return stream.toString();
        } catch (IOException ex) {
            logger.error(ex.getMessage());
            return null;
        }
    }

    public static String convertiDocumentToString(Document document) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        XMLOutputter outputter = new XMLOutputter();
        outputter.setFormat(Format.getPrettyFormat());
        outputter.output(document, stream);
        return stream.toString();
    }

    public static String formattaXML(String stringa){
        if(stringa==null){
            return "";
        }
        try {
            Document document = XmlJDomUtil.caricaXML(stringa);
            return stampaXML(document);
        } catch (XmlException ex) {
            return stringa;
        }
    }

    public static void salvaXML(String nomeFile, Document document) throws XmlException {
        FileOutputStream fileOS = null;
        try {
            String pathConformato = conformaPath(nomeFile);
            new File(pathConformato).getParentFile().mkdirs();
            fileOS = new FileOutputStream(pathConformato);
            XMLOutputter outputter = new XMLOutputter();
            outputter.setFormat(Format.getPrettyFormat());
            outputter.output(document, fileOS);
            fileOS.close();
            logger.info("File configurazione salvato in " + pathConformato);
        } catch (Exception ex) {
            logger.error("Impossibile salvare il file xml " + ex);
            throw new XmlException(ex);
        } finally {
            try {
                fileOS.close();
            } catch (IOException ex) {
            }
        }
    }

    public static Document caricaXML(String pathFile, boolean validate) throws XmlException {
        String pathFileConformato = conformaPath(pathFile);
        File file = new File(pathFileConformato);
        try {
            return caricaXML(new FileInputStream(file), validate, null);
        } catch (Exception ex) {
            throw new XmlException(ex);
        }
    }

    public static Document caricaXML(String messaggio) throws XmlException {
        if(messaggio==null){            
            throw new IllegalArgumentException("Impossibile creare il document. Messaggio nullo");
        }
        return caricaXML(new ByteArrayInputStream(messaggio.getBytes()), false, null);
    }
    
    public static Document caricaXML(InputStream isFile, boolean validate, InputStream isSource) throws XmlException {
        try {
            org.jdom.input.DOMBuilder dom = new DOMBuilder();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            factory.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
            if (isSource != null) {
                factory.setAttribute(JAXP_SCHEMA_SOURCE, isSource);
            }
            factory.setValidating(validate);
            DocumentBuilder builder = factory.newDocumentBuilder();
            builder.setErrorHandler(new SimpleErrorHandler());
            org.w3c.dom.Document document = builder.parse(isFile);
            return dom.build(document);
        } catch (Exception ex) {
            throw new XmlException(ex);
        }
    }

    public static void salvaXMLinClasspath(String path, String nomeFile, Document document) {
        try {
            URL url = XmlJDomUtil.class.getResource(path);
            String cartellaTest = url.getPath();
            String pathAssoluto = cartellaTest + nomeFile;
            String pathConformato = conformaPath(pathAssoluto);
            FileOutputStream fileOS = new FileOutputStream(pathConformato);
            XMLOutputter outputter = new XMLOutputter();
            outputter.output(document, fileOS);
            fileOS.close();
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }

    public static String transformToString(String messaggio, String fileXSLT) throws Exception {
        if(messaggio==null || fileXSLT==null){
            throw new IllegalArgumentException("Impossibile eseguire la trasformazione. Parametri nulli");
        }
        String messaggioRipulito = pulisciMessaggio(messaggio);
        Source xsltSource = new StreamSource(new File(fileXSLT));
        Source xmlSource = new StreamSource(new StringReader(messaggioRipulito));
        TransformerFactory transFact = TransformerFactory.newInstance();
        Transformer trans = transFact.newTransformer(xsltSource);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        trans.transform(xmlSource, new StreamResult(output));
        if(output==null){
            throw new SilvioException("La trasformazione ha prodotto un risultato null");
        }
        return output.toString();
    }
    

    public static Document transformToDocument(String fileXsl, Document documentXml) throws XSLTransformException, DAOException {
        logger.info("Eseguo la trasformazione XSLT del file " + fileXsl);        
        XSLTransformer transformer = new XSLTransformer(fileXsl);
        Document documentXmlTrasformato = transformer.transform(documentXml);
        logger.info("Trasformazione effettuata con successo");
        return documentXmlTrasformato;
    }
    
    private static String pulisciMessaggio(String messaggio){
        return messaggio.replaceAll("xmlns[\\s]*=[\\s]*\".*\"", "");
    }
    
    public static Document convert(org.w3c.dom.Document domDoc) throws XmlException {
        String stringaDocument = XMLUtils.toString(domDoc);
//        stringaDocument = pulisciMessaggio(stringaDocument);
        return XmlJDomUtil.caricaXML(stringaDocument);
    }
    
//    public static Document convert(org.w3c.dom.Document domDoc) throws XmlException {
//        DOMBuilder builder = new DOMBuilder();
//        return builder.build(domDoc);
//    }

    private static String conformaPath(String path) {
        String x = path.replace('\\', '/').trim();
        return x.replace("%20", " ");
    }
    private static class SimpleErrorHandler implements ErrorHandler {

        public void error(SAXParseException exception) throws SAXException {
            throw new SAXException(exception);
        }

        public void fatalError(SAXParseException exception) throws SAXException {
            throw new SAXException(exception);
        }

        public void warning(SAXParseException exception) throws SAXException {
            throw new SAXException(exception);
        }
    }
}
