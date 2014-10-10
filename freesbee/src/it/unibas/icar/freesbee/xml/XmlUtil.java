package it.unibas.icar.freesbee.xml;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public class XmlUtil {

    public static String eliminaPrefissoNS(String s) {
        if (s != null && s.contains(":")) {
            return s.substring(s.indexOf(":") + 1);
        } else {
            return s;
        }
    }

    private static void writeTo(Node node, OutputStream os, boolean prolog) throws TransformerException {
        Transformer it = TransformerFactory.newInstance().newTransformer();
        it.setOutputProperty(OutputKeys.METHOD, "xml");
//        it.setOutputProperty(OutputKeys.INDENT, "yes");
//        it.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", Integer.toString(1));
        if (!prolog) {
            it.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        } else 
//            it.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
//        }
        it.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        it.transform(new DOMSource(node), new StreamResult(os));
    }

    public static String stampaDocument(Node node) throws XmlException {
        return stampaDocument(node, false);
    }

    public static String stampaDocument(Node node, boolean prolog) throws XmlException {
        try {
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            writeTo(node, byteArray,prolog);
            return byteArray.toString();
        } catch (Exception ex) {
            throw new XmlException(ex);
        }
    }
}
