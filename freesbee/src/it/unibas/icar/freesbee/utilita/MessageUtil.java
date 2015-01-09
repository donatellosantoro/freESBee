package it.unibas.icar.freesbee.utilita;

import it.unibas.icar.freesbee.exception.FreesbeeException;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.camel.Message;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MessageUtil {

    private static Log logger = LogFactory.getLog(MessageUtil.class.getName());

    public static InputStream getStream(Message message) throws FreesbeeException {
        Object body = message.getBody();
        if (body == null) {
            return new ByteArrayInputStream("".getBytes());
        }
        try {
            InputStream stream;
            if (body instanceof String) {
                stream = IOUtils.toInputStream((String) body);
            } else {
                stream = (InputStream) body;
            }
//            if (stream instanceof org.apache.camel.converter.stream.InputStreamCache) {
            if (!(stream instanceof ByteArrayInputStream)) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                IOUtils.copy((InputStream) body, baos);
                stream = new ByteArrayInputStream(baos.toByteArray());
                setStream(message, stream);
            }
            return stream;
        } catch (IOException ex) {
            Logger.getLogger(MessageUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        logger.error("Impossibile convertire il messaggio in InputStream. Il body e' in " + body.getClass());
        throw new FreesbeeException("Errore interno. Impossibile convertire il messaggio in InputStream.");
    }

    public static String getString(Message message) throws FreesbeeException {
        if (message.getBody() == null) {
            return "";
        }
        try {
            InputStream stream = getStream(message);
            String string = IOUtils.toString(stream);
            stream.reset();
            return string;
        } catch (IOException ex) {
            throw new FreesbeeException("Errore interno. Impossibile convertire il messaggio in String." + ex.getLocalizedMessage());
        }
    }

    public static Source getSource(Message message) throws FreesbeeException {
        Source source = new StreamSource(getStream(message));
//        setSource(message, source);
        return source;
    }

    public static void setStream(Message message, InputStream stream) {
        message.setBody(stream);
    }

    public static void setString(Message message, String string) {
        setStream(message, IOUtils.toInputStream(string));
    }

    public static void setSource(Message message, Source source) throws FreesbeeException {
        try {
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            StreamResult result = new StreamResult(baos);
            transformer.transform(source, result);
            setStream(message, new ByteArrayInputStream(baos.toByteArray()));
        } catch (Exception ex) {
            throw new FreesbeeException("Errore interno. Impossibile convertire il messaggio in InputStream. " + ex.getLocalizedMessage());
        }
    }

    public static boolean isEmpty(Message message) throws FreesbeeException {
        InputStream stream = null;
        try {
            stream = getStream(message);
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String line = reader.readLine();
            while (line != null) {
                if (!line.trim().isEmpty()) {
                    //Controlliamo se il messaggio contiene almeno un carattere che non sia spazio bianco
                    return false;
                }
                line = reader.readLine();
            }
            return true;
        } catch (IOException ex) {
            logger.error("Impossibile leggere il messaggio. " + ex.getLocalizedMessage());
            throw new FreesbeeException("Errore interno. Impossibile leggere il messaggio. " + ex.getLocalizedMessage());
        } finally {
            try {
                if (stream != null) stream.reset();
            } catch (IOException ex) {
            }
        }
    }

    public static void copyMessage(Message src, Message dest) throws FreesbeeException {
        try {
            InputStream inStream = getStream(src);
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            IOUtils.copy(inStream, outStream);
            inStream.reset();
            setStream(dest, new ByteArrayInputStream(outStream.toByteArray()));
//            out.setBody(in.getBody());
        } catch (IOException ex) {
            throw new FreesbeeException("Errore interno. Impossibile copiare il messaggio. " + ex.getLocalizedMessage());
        }
    }

    public static String printXmlSource(Source source) throws FreesbeeException {
        StreamResult xmlOutput = null;
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            xmlOutput = new StreamResult(new StringWriter());
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(source, xmlOutput);
            return xmlOutput.getWriter().toString();
        } catch (Exception e) {
            throw new FreesbeeException("Errore nella scrittura del SOAP Fault");
        }
    }

    public static String printXmlSource(Source source, String tagName) throws FreesbeeException {
        String content = printXmlSource(source);
        String startTag = "<" + tagName + ">";
        String endTag = "</" + tagName + ">";
        int startIndex = content.indexOf(startTag);
        int endIndex = content.indexOf(endTag);
        if (startIndex != -1 && endIndex != -1 && endIndex > startIndex) {
            return content.substring(startIndex + 2 + tagName.length(), endIndex).trim();
        }
        return null;
    }
//    public static void copyHeaders(Message src, Message dest) {
//        Map<String, Object> header = new HashMap<String, Object>();
//        for (String key : src.getHeaders().keySet()) {
//            Object value = src.getHeader(key);
//            header.put(key, value);
//        }
//        dest.setHeaders(header);
//    }
}
