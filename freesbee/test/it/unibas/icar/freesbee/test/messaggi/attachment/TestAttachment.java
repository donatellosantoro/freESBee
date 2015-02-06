package it.unibas.icar.freesbee.test.messaggi.attachment;

import com.sun.mail.util.BASE64DecoderStream;
import it.unibas.icar.freesbee.test.messaggi.attachment.client.IWSVerificaInstallazione;
import it.unibas.icar.freesbee.test.messaggi.attachment.client.WSVerificaInstallazioneImplService;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.xml.namespace.QName;
import javax.xml.soap.AttachmentPart;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.ws.BindingProvider;
import junit.framework.TestCase;
import org.apache.axis.utils.XMLUtils;
import org.apache.commons.io.IOUtils;
import org.apache.servicemix.util.jaf.ByteArrayDataSource;

public class TestAttachment extends TestCase {

    private org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog(this.getClass());

    // Eseguire prima ant db-insert-test 
    private String indirizzoWS = "http://localhost:18191/ws/verificaInstallazione";
    private String indirizzoPD = "http://localhost:18192/PD/AttachmentTest/";
    private String fileZip = TestAttachment.class.getResource("/dati/testEncoding.zip").getFile();
    private static final QName SERVICE_NAME = new QName("http://icar.unibas.it/freesbee/", "WSVerificaInstallazioneImplService");

    public void tesSendZipToWS() {
        try {
            SOAPConnection connection = SOAPConnectionFactory.newInstance().createConnection();
            SOAPMessage message = MessageFactory.newInstance().createMessage();
            SOAPPart part = message.getSOAPPart();
            SOAPEnvelope envelope = part.getEnvelope();
            SOAPBody body = envelope.getBody();

            QName testAttachmentQName = new QName("http://icar.unibas.it/freesbee/", "testAttachmentEcho", "fre");
            SOAPBodyElement testAttachmentElement = body.addBodyElement(testAttachmentQName);

            DataHandler dh = new DataHandler(new FileDataSource(fileZip));

            ZipInputStream zipIn = new ZipInputStream(dh.getInputStream());
            ZipEntry zipEntry = zipIn.getNextEntry();
            assertNotNull(zipEntry);
            String toCompare = zipEntry.getName();
            AttachmentPart attachment = message.createAttachmentPart(dh);
            attachment.setContentId("provaContentID");
            String base64String = XMLUtils.base64encode(attachment.getRawContentBytes());

            QName argQname = new QName("arg0");
            SOAPElement argElement = testAttachmentElement.addChildElement(argQname);
            argElement.addTextNode(base64String);

            SOAPMessage result = connection.call(message, indirizzoWS);
            part = result.getSOAPPart();
            envelope = part.getEnvelope();
            body = envelope.getBody();
            if (!body.hasFault()) {
                assertTrue(result.countAttachments() == 1);
                AttachmentPart att = (AttachmentPart) result.getAttachments().next();
                zipIn = new ZipInputStream(att.getRawContent());
                zipEntry = zipIn.getNextEntry();
                assertNotNull(zipEntry);
                assertEquals(zipEntry.getName(), toCompare);
            }

        } catch (Exception e) {
            fail();
        }
    }

    public void testClientWS() {
        try {
            WSVerificaInstallazioneImplService ss = new WSVerificaInstallazioneImplService(new URL(indirizzoWS + "?wsdl"), SERVICE_NAME);
            IWSVerificaInstallazione port = ss.getWSVerificaInstallazioneImplPort();

//            BindingProvider bp = (BindingProvider) port;
//            SOAPBinding binding = (SOAPBinding) bp.getBinding();    // abilito il multipart
//            binding.setMTOMEnabled(true);
            DataHandler dh = new DataHandler(new FileDataSource(fileZip));

            ZipInputStream zipIn = new ZipInputStream(dh.getInputStream());
            ZipEntry zipEntry = zipIn.getNextEntry();
            assertNotNull(zipEntry);
            String toCompare = zipEntry.getName();
//            String messaggioInviare = "???º¬@åªºå?µ?å?å";
            String messaggioInviare = "èé";
//            String messaggioInviare = "salame";

            byte[] toByteArray = IOUtils.toByteArray(dh.getInputStream());

            it.unibas.icar.freesbee.test.messaggi.attachment.client.MessaggioInstallazione testAttachmentEchoMessage = port.testAttachmentEchoMessage(toByteArray, messaggioInviare);

            InputStream in = new ByteArrayInputStream(testAttachmentEchoMessage.getHandler());
            zipIn = new ZipInputStream(in);
            zipEntry = zipIn.getNextEntry();
            assertEquals(zipEntry.getName(), toCompare);
            assertEquals(testAttachmentEchoMessage.getMessaggio(), messaggioInviare);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            fail();
        }
    }

    public void testFindEncoding() {
        try {
            String messaggioInviare = "èé";
//            String messaggioInviare = "?????åæ¨?å?ªºå?";

            InputStream isOriginal = IOUtils.toInputStream(messaggioInviare);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = isOriginal.read(buffer)) > -1) {
                byteArrayOutputStream.write(buffer, 0, len);
            }
            byteArrayOutputStream.flush();
            SortedMap m = Charset.availableCharsets();
            Set k = m.keySet();
            Iterator i = k.iterator();
            while (i.hasNext()) {
                InputStream is = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                String n = (String) i.next();
                Charset e = (Charset) m.get(n);
                String supportedContentype = e.displayName();
                String encoded = IOUtils.toString(is, supportedContentype);
//                System.out.println(encoded);
//                System.out.println("-------------------");
                if (messaggioInviare.equals(encoded)) {
                    System.out.println(messaggioInviare);
                    System.out.println(encoded);
                    System.out.println("TROVATO: " + supportedContentype);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }

    }

    public void testClientPD() {
        try {

            WSVerificaInstallazioneImplService ss = new WSVerificaInstallazioneImplService();
            IWSVerificaInstallazione port = ss.getWSVerificaInstallazioneImplPort();

            BindingProvider bp = (BindingProvider) port;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, indirizzoPD);

//            SOAPBinding binding = (SOAPBinding) bp.getBinding();    // abilito il multipart
//            binding.setMTOMEnabled(true);
            DataHandler dh = new DataHandler(new FileDataSource(fileZip));
            String contentType = dh.getContentType();
            ZipInputStream zipIn = new ZipInputStream(dh.getInputStream());
            ZipEntry zipEntry = zipIn.getNextEntry();
            assertNotNull(zipEntry);
            String toCompare = zipEntry.getName();
//            String messaggioInviare = "èé";
            String messaggioInviare = "???º¬@åªºå?µ?å?å";
//            String messaggioInviare = "salame";
            byte[] toByteArray = IOUtils.toByteArray(dh.getInputStream());

            it.unibas.icar.freesbee.test.messaggi.attachment.client.MessaggioInstallazione testAttachmentEchoMessage = port.testAttachmentEchoMessage(toByteArray, messaggioInviare);

            InputStream in = new ByteArrayInputStream(testAttachmentEchoMessage.getHandler());

            BASE64DecoderStream decodeStream = new BASE64DecoderStream(in);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            IOUtils.copy(decodeStream, baos);
            ByteArrayDataSource rawData = new ByteArrayDataSource(baos.toByteArray(), contentType);
            dh = new DataHandler(rawData);

            zipIn = new ZipInputStream(dh.getInputStream());
            zipEntry = zipIn.getNextEntry();
            assertEquals(zipEntry.getName(), toCompare);
            assertEquals(testAttachmentEchoMessage.getMessaggio(), messaggioInviare);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            fail();
        }
    }

//    public void testSendZipAndMessageToWS() {
//        try {
//            SOAPConnection connection = SOAPConnectionFactory.newInstance().createConnection();
//            SOAPMessage message = MessageFactory.newInstance().createMessage();
//            SOAPPart part = message.getSOAPPart();
//            SOAPEnvelope envelope = part.getEnvelope();
//            SOAPBody body = envelope.getBody();
//
//            QName testAttachmentQName = new QName("http://icar.unibas.it/freesbee/", "testAttachmentEchoMessage", "fre");
//            SOAPBodyElement testAttachmentElement = body.addBodyElement(testAttachmentQName);
//
//            DataHandler dh = new DataHandler(new FileDataSource(fileZip));
//
//            ZipInputStream zipIn = new ZipInputStream(dh.getInputStream());
//            ZipEntry zipEntry = zipIn.getNextEntry();
//            assertNotNull(zipEntry);
//            String toCompare = zipEntry.getName();
//            AttachmentPart attachment = message.createAttachmentPart(dh);
//            attachment.setContentId("ContentID");
//
//            QName argQname = new QName("arg0");
//            SOAPElement argElement = testAttachmentElement.addChildElement(argQname);
//            argElement.addTextNode("file:ContentID");
//
//            QName argQname1 = new QName("arg1");
//            SOAPElement argElement1 = testAttachmentElement.addChildElement(argQname1);
//            argElement1.addTextNode("messaggio");
//
//            message.addAttachmentPart(attachment);
//
//            SOAPMessage result = connection.call(message, indirizzoWS);
//            part = result.getSOAPPart();
//            envelope = part.getEnvelope();
//            body = envelope.getBody();
//            if (!body.hasFault()) {
//                assertTrue(result.countAttachments() == 1);
//                AttachmentPart att = (AttachmentPart) result.getAttachments().next();
//                DataHandler dataHandler = att.getDataHandler();
//
//                MessaggioInstallazione unmarshal = JAXB.unmarshal(dataHandler.getInputStream(), MessaggioInstallazione.class);
//                System.out.println("Messaggio: " + unmarshal.getMessaggio());
//                System.out.println("Handler: " + unmarshal.getHandler());
//                zipIn = new ZipInputStream(dataHandler.getInputStream());
//                zipEntry = zipIn.getNextEntry();
//                assertNotNull(zipEntry);
//                assertEquals(zipEntry.getName(), toCompare);
//            }
//
//        } catch (Exception e) {
//            fail();
//        }
//    }
    public void tesSendZipToPD() {
        try {
            SOAPConnection connection = SOAPConnectionFactory.newInstance().createConnection();
            SOAPMessage message = MessageFactory.newInstance().createMessage();
            SOAPPart part = message.getSOAPPart();
            SOAPEnvelope envelope = part.getEnvelope();
            SOAPBody body = envelope.getBody();

            QName testAttachmentQName = new QName("http://icar.unibas.it/freesbee/", "testAttachmentEcho", "fre");
            SOAPBodyElement testAttachmentElement = body.addBodyElement(testAttachmentQName);

            DataHandler dh = new DataHandler(new FileDataSource(fileZip));

            ZipInputStream zipIn = new ZipInputStream(dh.getInputStream());
            ZipEntry zipEntry = zipIn.getNextEntry();
            assertNotNull(zipEntry);
            String toCompare = zipEntry.getName();
            AttachmentPart attachment = message.createAttachmentPart(dh);
            attachment.setContentId("provaContentID");
            String base64String = XMLUtils.base64encode(attachment.getRawContentBytes());

            QName argQname = new QName("arg0");
            SOAPElement argElement = testAttachmentElement.addChildElement(argQname);
            argElement.addTextNode(base64String);

            SOAPMessage result = connection.call(message, indirizzoPD);
            part = result.getSOAPPart();
            envelope = part.getEnvelope();
            body = envelope.getBody();
            if (!body.hasFault()) {
                assertTrue(result.countAttachments() == 1);
                AttachmentPart att = (AttachmentPart) result.getAttachments().next();
                zipIn = new ZipInputStream(att.getRawContent());
                zipEntry = zipIn.getNextEntry();
                assertNotNull(zipEntry);
                assertEquals(zipEntry.getName(), toCompare);
            }

        } catch (Exception e) {
            fail();
        }
    }

}
