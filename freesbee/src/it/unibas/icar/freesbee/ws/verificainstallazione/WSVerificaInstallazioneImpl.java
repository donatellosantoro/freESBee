package it.unibas.icar.freesbee.ws.verificainstallazione;

import com.google.inject.Singleton;
import com.sun.mail.util.BASE64DecoderStream;
import it.unibas.icar.freesbee.persistenza.SOAPFault;
import it.unibas.icar.freesbee.ws.tracciamentoTest.WSMessaggioImpl;
import java.io.ByteArrayOutputStream;
import javax.activation.DataHandler;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.JAXB;
import org.apache.commons.io.IOUtils;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.apache.servicemix.util.jaf.ByteArrayDataSource;
import org.slf4j.LoggerFactory;

@Singleton
@WebService(targetNamespace = "http://icar.unibas.it/freesbee/")
public class WSVerificaInstallazioneImpl implements IWSVerificaInstallazione {

//    private static Log logger = LogFactory.getLog(WSVerificaInstallazioneImpl.class);
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(WSVerificaInstallazioneImpl.class.getName());

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
            if (logger.isDebugEnabled()) logger.debug("Provo a decodificarlo...");
            BASE64DecoderStream decodeStream = new BASE64DecoderStream(attachment.getInputStream());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            IOUtils.copy(decodeStream, baos);
            ByteArrayDataSource rawData = new ByteArrayDataSource(baos.toByteArray(), attachment.getContentType());
            DataHandler dh = new DataHandler(rawData);
            if (logger.isDebugEnabled()) logger.debug("Decodifica terminata...");
            return dh;
        } catch (Exception ex) {
            //L'allegato non è in BASE64
            if (logger.isInfoEnabled()) logger.info("Il messaggio e' in chiaro... (" + ex.getLocalizedMessage() + ")");
            return attachment;
        }
    }

    @WebMethod(operationName = "testAttachmentEcho")
    public DataHandler testAttachmentEcho(DataHandler attachment) throws SOAPFault {
        if (logger.isDebugEnabled()) logger.debug("Ricevuto un messaggio");
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            IOUtils.copy(attachment.getInputStream(), baos);
            ByteArrayDataSource rawData = new ByteArrayDataSource(baos.toByteArray(), attachment.getContentType());
            DataHandler dh = new DataHandler(rawData);
            return dh;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
        return null;
    }

    @WebMethod(operationName="testAttachmentEchoMessage")
    public MessaggioInstallazione testAttachmentEchoMessage(DataHandler attachment, String message) throws SOAPFault {
        if (logger.isDebugEnabled()) logger.debug("Ricevuto un messaggio");
        try {
            if(logger.isDebugEnabled()) logger.debug("Messaggio: "+message);
            if(logger.isDebugEnabled()) logger.debug("Attachment: "+attachment);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            IOUtils.copy(attachment.getInputStream(), baos);
            ByteArrayDataSource rawData = new ByteArrayDataSource(baos.toByteArray(), attachment.getContentType());
            DataHandler dh = new DataHandler(rawData);
            MessaggioInstallazione vi = new MessaggioInstallazione(message, dh);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            JAXB.marshal(vi, out);
            return vi;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
        return null;
    }

}
