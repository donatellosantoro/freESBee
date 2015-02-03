package it.unibas.icar.freesbee.ws.verificainstallazione;

import com.google.inject.Singleton;
import com.sun.mail.util.BASE64DecoderStream;
import it.unibas.icar.freesbee.persistenza.SOAPFault;
import java.io.ByteArrayOutputStream;
import javax.activation.DataHandler;
import javax.jws.WebMethod;
import javax.jws.WebService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.servicemix.util.jaf.ByteArrayDataSource;

@Singleton
@WebService(targetNamespace = "http://icar.unibas.it/freesbee/")
public class WSVerificaInstallazioneImpl implements IWSVerificaInstallazione {

    private static Log logger = LogFactory.getLog(WSVerificaInstallazioneImpl.class);

    @WebMethod(operationName = "verificaInstallazione")
    public String verificaInstallazione() throws SOAPFault {
        return "freESBee installato con successo!";
    }

    @WebMethod(operationName = "testAttachment")
    public DataHandler testAttachment(DataHandler attachment) throws SOAPFault {
        if (logger.isDebugEnabled()) logger.debug("Ricevuto un messaggio");
        if (logger.isDebugEnabled()) logger.debug("Name: " + attachment.getName());
        if (logger.isDebugEnabled()) logger.debug("ContentType: " + attachment.getContentType());
        try {
            if (logger.isDebugEnabled()) logger.debug("Decodifica in corso.");
            BASE64DecoderStream decodeStream = new BASE64DecoderStream(attachment.getInputStream());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            IOUtils.copy(decodeStream, baos);
            ByteArrayDataSource rawData = new ByteArrayDataSource(baos.toByteArray(), attachment.getContentType());
            DataHandler dh = new DataHandler(rawData);
            if (logger.isDebugEnabled()) logger.debug("Decodifica terminata.");
            return dh;
        } catch (Exception ex) {
            //L'allegato non è in BASE64
            logger.info("Il messaggio non e' codificato in BASE64");
            if (logger.isDebugEnabled()) logger.error(ex);
            return attachment;
        }
    }
}
