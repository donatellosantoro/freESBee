package it.unibas.icar.freesbee.test.core.portaDelegata;

import it.unibas.icar.freesbee.test.core.moduloControllo.sbustamento.TestSbustamento;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import javax.activation.DataHandler;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import junit.framework.Assert;
import junit.framework.TestCase;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.apache.servicemix.soap.marshalers.SoapMarshaler;
import org.apache.servicemix.soap.marshalers.SoapMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestAttachment extends TestCase {

//    private Log logger = LogFactory.getLog(this.getClass());
    private static final Logger logger = LoggerFactory.getLogger(TestAttachment.class.getName());

    public void testRead() {
        try {
            SoapMarshaler marshaler = new SoapMarshaler(true);

//            InputStream is = getClass().getResourceAsStream("/dati/soap.xml");
//            InputStream is = getClass().getResourceAsStream("/dati/soapAttachment.xml");
            InputStream is = getClass().getResourceAsStream("/dati/soapAttachment2.xml");

            Session session = Session.getDefaultInstance(new Properties());
            MimeMessage mime = new MimeMessage(session, is);
            logger.info("getContentType " + mime.getContentType());
            SoapMessage msg = marshaler.createReader().read(mime);

            logger.info("msg.hasAttachments()" + msg.hasAttachments());
            Map<String,DataHandler> attachments = msg.getAttachments();
            Set<String> parts = attachments.keySet();
            for (String s : parts) {
                DataHandler att = attachments.get(s);
                logger.info("Attachment " + s);
                logger.info(att.getContent().toString());
            }
            
            logger.info("msg.getSource()" + msg.getSource());
            logger.info("msg.getBodyName()" + msg.getBodyName());
            Assert.assertNotNull(msg.getBodyName());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getLocalizedMessage());
        }
    }
}
