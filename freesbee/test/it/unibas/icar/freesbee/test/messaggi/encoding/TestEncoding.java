package it.unibas.icar.freesbee.test.messaggi.encoding;

import it.unibas.icar.freesbee.test.qualificazione.UtilTest;
import it.unibas.icar.freesbee.test.qualificazione.fruitore.AbstractPATest;
import it.unibas.icar.freesbee.test.qualificazione.fruitore.TestQualificazione03Sincrono;
import it.unibas.icar.freesbee.test.regione.erogatore.TestBusteErrate;
import junit.framework.Assert;
import junit.framework.AssertionFailedError;
import junit.framework.TestCase;
import org.apache.cxf.helpers.XMLUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TestEncoding extends TestCase {

//    private org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog(this.getClass());
    private static final Logger logger = LoggerFactory.getLogger(TestEncoding.class.getName());

    public void testQualificazione01() {
        AbstractPATest paTest = new TestEncoding.TestPA("/messaggi/reali/01. testEncodingRichiestaRisposta.xml");
        
//        AbstractPATest paTest = new TestEncoding.TestPA("/messaggiFruitore/3.testSincronoRisposta.xml");
        
        try {
            String indirizzoPD = "http://localhost:8192/PD/PD_QualificazionePDD/?FRUITORE=PAGenerica&TIPO_FRUITORE=SPC" +
                                "&EROGATORE=CNIPA&TIPO_EROGATORE=SPC" +
                                "&SERVIZIO=QualificazionePDD&TIPO_SERVIZIO=SPC" +
                                "&AZIONE=start";
            
            // Per la richiesta usiamo lo stesso messaggio che ci aspettiamo come risposta cosi' da testare sia la parte di
            // imbustamento che quella di sbustamento
            String richiesta = UtilTest.leggiMessaggio("/messaggi/reali/01. testEncodingRichiestaRisposta.xml");
            logger.info("RICHIESTA:\n" + richiesta);
            
            paTest.startServlet();
            Document soapRichiesta = UtilTest.leggiSOAP(richiesta);
            Element soapElement = soapRichiesta.getDocumentElement();
            NodeList headerElements = soapElement.getElementsByTagNameNS("http://schemas.xmlsoap.org/soap/envelope/", "Header");
            if (headerElements.getLength() == 1) {
                Node headerEl = headerElements.item(0);
                soapElement.removeChild(headerEl);
            }
            String stringaRichiesta = XMLUtils.toString(soapRichiesta);
            String risposta = UtilTest.invia(indirizzoPD, stringaRichiesta);
            logger.info("RISPOSTA:\n" + risposta);
            Document soapRisposta = UtilTest.leggiSOAP(risposta);

            String rispostaOK = UtilTest.trovaNodoString("/SOAP_ENV:Envelope/SOAP_ENV:Body/p891:statisticheScartiAsAttachmentResponse/p891:statisticheScartiAsAttachmentReturn/p891:esitoOperazione/p891:codiceEsito", soapRisposta);
            Assert.assertEquals("0", rispostaOK);
        } catch (Exception ex) {
            logger.info("ERRORE: " + ex);
            Assert.fail(ex.getLocalizedMessage());
        } finally {
            paTest.stop();
        }

    }

    private class TestPA extends AbstractPATest {

        public TestPA(String fileMessaggioRisposta) {
            super(fileMessaggioRisposta);
        }

        @Override
        public void verificaRichiesta(String stringaRichiesta) throws AssertionFailedError {
        }
    }
    
}
