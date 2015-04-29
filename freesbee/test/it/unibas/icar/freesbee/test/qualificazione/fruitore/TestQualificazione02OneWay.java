package it.unibas.icar.freesbee.test.qualificazione.fruitore;

import it.unibas.icar.freesbee.test.qualificazione.UtilTest;
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

public class TestQualificazione02OneWay extends TestCase {

//    private org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog(this.getClass());
    private static final Logger logger = LoggerFactory.getLogger(TestQualificazione02OneWay.class.getName());

    public void testQualificazione02() {
        AbstractPATest paTest = new TestPA("");
        try {
            String indirizzoPD = "http://localhost:8192/PD/PD_QualificazionePDD/?FRUITORE=PAGenerica&TIPO_FRUITORE=SPC" +
                                "&EROGATORE=CNIPA&TIPO_EROGATORE=SPC" +
                                "&SERVIZIO=QualificazionePDD&TIPO_SERVIZIO=SPC" +
                                "&AZIONE=testOneWay";
            
            String richiesta = UtilTest.leggiMessaggio("/messaggiFruitore/2.testOneWayRichiesta.xml");
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
            String tokenSessione = UtilTest.trovaNodoString("/soapenv:Envelope/soapenv:Body/p891:richiesta_RichiestaSenzaRisposta_testOneWay/p891:TokenSessione", soapRichiesta);
            logger.info("TokenSessione: " + tokenSessione);

            String risposta = UtilTest.invia(indirizzoPD, stringaRichiesta);
            logger.info("RISPOSTA:\n" + risposta);
            
            Assert.assertTrue(risposta.isEmpty());
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
            Document soapRichiesta = UtilTest.leggiSOAP(stringaRichiesta);

            String expIntestazione = "/SOAP_ENV:Envelope/SOAP_ENV:Header/eGov_IT:Intestazione/eGov_IT:IntestazioneMessaggio";

            String mittente = UtilTest.trovaNodoString(expIntestazione + "/eGov_IT:Mittente/eGov_IT:IdentificativoParte", soapRichiesta);
            TestQualificazione02OneWay.assertEquals("PAGenerica", mittente);

            String tipoMittente = UtilTest.trovaNodoString(expIntestazione + "/eGov_IT:Mittente/eGov_IT:IdentificativoParte/@tipo", soapRichiesta);
            TestQualificazione02OneWay.assertEquals("SPC", tipoMittente);

            String destinatario = UtilTest.trovaNodoString(expIntestazione + "/eGov_IT:Destinatario/eGov_IT:IdentificativoParte", soapRichiesta);
            TestQualificazione02OneWay.assertEquals("CNIPA", destinatario);

            String tipoDestinatario = UtilTest.trovaNodoString(expIntestazione + "/eGov_IT:Destinatario/eGov_IT:IdentificativoParte/@tipo", soapRichiesta);
            TestQualificazione02OneWay.assertEquals("SPC", tipoDestinatario);

            String servizio = UtilTest.trovaNodoString(expIntestazione + "/eGov_IT:Servizio ", soapRichiesta);
            TestQualificazione02OneWay.assertEquals("QualificazionePDD", servizio);

            String azione = UtilTest.trovaNodoString(expIntestazione + "/eGov_IT:Azione ", soapRichiesta);
            TestQualificazione02OneWay.assertEquals("testOneWay", azione);

            String identificatore = UtilTest.trovaNodoString(expIntestazione + "/eGov_IT:Messaggio/eGov_IT:Identificatore", soapRichiesta);
            TestQualificazione02OneWay.assertTrue(identificatore.startsWith("PAGenerica_PAGenericaSPCoopIT_"));

            String profiloCollaborazione = UtilTest.trovaNodoString(expIntestazione + "/eGov_IT:ProfiloCollaborazione", soapRichiesta);
            TestQualificazione02OneWay.assertEquals("EGOV_IT_MessaggioSingoloOneWay", profiloCollaborazione);

            String token = UtilTest.trovaNodoString("/soapenv:Envelope/soapenv:Body/p891:richiesta_RichiestaSenzaRisposta_testOneWay/p891:TokenSessione", soapRichiesta);
            TestQualificazione02OneWay.assertEquals("1ab955960a3281e965c0393ad0071f1e", token);

        }
    }
}
