package it.unibas.icar.freesbee.test.qualificazione.fruitore;

import it.unibas.icar.freesbee.test.qualificazione.UtilTest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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

public class TestQualificazione06AsincronoSimmetrico extends TestCase {

//    private org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog(this.getClass());
    private static final Logger logger = LoggerFactory.getLogger(TestQualificazione06AsincronoSimmetrico.class.getName());

    public void testQualificazione06() {
        AbstractPATest paTest = new TestPA("/messaggiFruitore/6.testAsincronoSimmetricoRisposta.xml");
        try {
            String indirizzoPD = "http://localhost:8192/PD/PD_QualificazionePDD/?FRUITORE=PAGenerica&TIPO_FRUITORE=SPC" +
                    "&EROGATORE=CNIPA&TIPO_EROGATORE=SPC" +
                    "&SERVIZIO=QualificazionePDD&TIPO_SERVIZIO=SPC" +
                    "&AZIONE=testAsincronoSimmetrico";

            String richiesta = UtilTest.leggiMessaggio("/messaggiFruitore/6.testAsincronoSimmetricoRichiesta.xml");
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
            String tokenSessione = UtilTest.trovaNodoString("/soapenv:Envelope/soapenv:Body/p891:richiesta_RichiestaRispostaAsincrona_testAsincronoSimmetrico/p891:TokenSessione", soapRichiesta);
            logger.info("TokenSessione: " + tokenSessione);
            
            Map<String, String> mappaIntestazioni = new HashMap<String, String>();
            mappaIntestazioni.put("SPCoopID", "IDTestQualificazioneAsincronoSimmetrico" + new Date().getTime());
            String risposta = UtilTest.invia(indirizzoPD, stringaRichiesta,mappaIntestazioni);
            logger.info("RISPOSTA:\n" + risposta);
            Document soapRisposta = UtilTest.leggiSOAP(risposta);

            String rispostaOK = UtilTest.trovaNodoString("/soapenv:Envelope/soapenv:Body/p891:risposta_RichiestaRispostaAsincrona_testAsincronoSimmetrico/p891:PresaInCarico", soapRisposta);
            Assert.assertEquals("OK", rispostaOK);

            String token = UtilTest.trovaNodoString("/soapenv:Envelope/soapenv:Body/p891:risposta_RichiestaRispostaAsincrona_testAsincronoSimmetrico/p891:TokenSessione", soapRisposta);
            Assert.assertEquals(tokenSessione, token);
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
            TestQualificazione06AsincronoSimmetrico.assertEquals("PAGenerica", mittente);

            String tipoMittente = UtilTest.trovaNodoString(expIntestazione + "/eGov_IT:Mittente/eGov_IT:IdentificativoParte/@tipo", soapRichiesta);
            TestQualificazione06AsincronoSimmetrico.assertEquals("SPC", tipoMittente);

            String destinatario = UtilTest.trovaNodoString(expIntestazione + "/eGov_IT:Destinatario/eGov_IT:IdentificativoParte", soapRichiesta);
            TestQualificazione06AsincronoSimmetrico.assertEquals("CNIPA", destinatario);

            String tipoDestinatario = UtilTest.trovaNodoString(expIntestazione + "/eGov_IT:Destinatario/eGov_IT:IdentificativoParte/@tipo", soapRichiesta);
            TestQualificazione06AsincronoSimmetrico.assertEquals("SPC", tipoDestinatario);

            String servizio = UtilTest.trovaNodoString(expIntestazione + "/eGov_IT:Servizio ", soapRichiesta);
            TestQualificazione06AsincronoSimmetrico.assertEquals("QualificazionePDD", servizio);

            String azione = UtilTest.trovaNodoString(expIntestazione + "/eGov_IT:Azione ", soapRichiesta);
            TestQualificazione06AsincronoSimmetrico.assertEquals("testAsincronoSimmetrico", azione);

            String identificatore = UtilTest.trovaNodoString(expIntestazione + "/eGov_IT:Messaggio/eGov_IT:Identificatore", soapRichiesta);
            TestQualificazione06AsincronoSimmetrico.assertTrue(identificatore.startsWith("PAGenerica_PAGenericaSPCoopIT_"));

            String collaborazione = UtilTest.trovaNodoString(expIntestazione + "/eGov_IT:Collaborazione", soapRichiesta);
            TestQualificazione06AsincronoSimmetrico.assertEquals(identificatore, collaborazione);

            String profiloCollaborazione = UtilTest.trovaNodoString(expIntestazione + "/eGov_IT:ProfiloCollaborazione", soapRichiesta);
            TestQualificazione06AsincronoSimmetrico.assertEquals("EGOV_IT_ServizioAsincronoSimmetrico", profiloCollaborazione);

            String token = UtilTest.trovaNodoString("/soapenv:Envelope/soapenv:Body/p891:richiesta_RichiestaRispostaAsincrona_testAsincronoSimmetrico/p891:TokenSessione", soapRichiesta);
            TestQualificazione06AsincronoSimmetrico.assertEquals("1ab955960a3281e965c0393ad0071f1e", token);

        }
    }
}
