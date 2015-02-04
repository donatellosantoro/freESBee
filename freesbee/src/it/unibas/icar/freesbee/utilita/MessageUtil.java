package it.unibas.icar.freesbee.utilita;

import it.unibas.icar.freesbee.exception.FreesbeeErrorListener;
import it.unibas.icar.freesbee.exception.FreesbeeException;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
            logger.error("Si e' verificato un errore durante la conversione del messaggio in InputStream.");
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new FreesbeeException("Si e' verificato un errore durante la conversione del messaggio in InputStream.");
        }
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
            logger.error("Si e' verificato un errore durante la conversione del messaggio in String.");
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new FreesbeeException("Si e' verificato un errore durante la conversione del messaggio in String.");
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
            transformer.setErrorListener(new FreesbeeErrorListener());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            StreamResult result = new StreamResult(baos);
            transformer.transform(source, result);
            setStream(message, new ByteArrayInputStream(baos.toByteArray()));
        } catch (Exception ex) {
            logger.error("Si e' verificato un errore durante la conversione del messaggio in InputStream.");
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new FreesbeeException("Si e' verificato un errore durante la conversione del messaggio in InputStream.");
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
            logger.error("Si e' verificato un errore durante la lettura del messaggio.");
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new FreesbeeException("Si e' verificato un errore durante la lettura del messaggio.");
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
            logger.error("Si e' verificato un errore durante la copia del messaggio.");
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new FreesbeeException("Si e' verificato un errore durante la copia del messaggio.");
        }
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
