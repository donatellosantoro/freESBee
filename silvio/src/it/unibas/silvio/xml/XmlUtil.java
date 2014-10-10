package it.unibas.silvio.xml;

import it.unibas.silvio.web.util.FileUtil;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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

    private static void writeTo(Node node, OutputStream os) throws TransformerException {
        Transformer it = TransformerFactory.newInstance().newTransformer();
        it.setOutputProperty(OutputKeys.METHOD, "xml");
        it.setOutputProperty(OutputKeys.INDENT, "yes");
        it.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", Integer.toString(1));
        it.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        it.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        it.transform(new DOMSource(node), new StreamResult(os));
    }

    public static String stampaDocument(Node node) throws XmlException {
        try {
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            writeTo(node, byteArray);
            return byteArray.toString();
        } catch (Exception ex) {
            throw new XmlException(ex);
        }
    }

    public static void salvaDocument(Node node, String nomeFile) throws FileNotFoundException, IOException, XmlException {
        String content = stampaDocument(node);
        new File(nomeFile).getParentFile().mkdirs();
        FileUtil.saveStringToFile(content, nomeFile);
    }
}
