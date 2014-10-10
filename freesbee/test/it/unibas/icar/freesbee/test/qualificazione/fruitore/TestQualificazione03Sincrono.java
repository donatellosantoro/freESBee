package it.unibas.icar.freesbee.test.qualificazione.fruitore;

import it.unibas.icar.freesbee.test.qualificazione.UtilTest;
import junit.framework.Assert;
import junit.framework.AssertionFailedError;
import junit.framework.TestCase;
import org.apache.cxf.helpers.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TestQualificazione03Sincrono extends TestCase {

    private org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog(this.getClass());

    public void testQualificazione03() {
        AbstractPATest paTest = new TestPA("/messaggiFruitore/3.testSincronoRisposta.xml");
        try {
            String indirizzoPD = "http://localhost:8192/PD/PD_QualificazionePDD/?FRUITORE=PAGenerica&TIPO_FRUITORE=SPC" +
                                "&EROGATORE=CNIPA&TIPO_EROGATORE=SPC" +
                                "&SERVIZIO=QualificazionePDD&TIPO_SERVIZIO=SPC" +
                                "&AZIONE=testSincrono";
            
            String richiesta = UtilTest.leggiMessaggio("/messaggiFruitore/3.testSincronoRichiesta.xml");
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
            String tokenSessione = UtilTest.trovaNodoString("/soapenv:Envelope/soapenv:Body/p891:richiesta_RichiestaRispostaSincrona_testSincrono/p891:TokenSessione", soapRichiesta);
            logger.info("TokenSessione: " + tokenSessione);

            String risposta = UtilTest.invia(indirizzoPD, stringaRichiesta);
            logger.info("RISPOSTA:\n" + risposta);
            Document soapRisposta = UtilTest.leggiSOAP(risposta);

            String rispostaOK = UtilTest.trovaNodoString("/soapenv:Envelope/soapenv:Body/p891:risposta_RichiestaRispostaSincrona_testSincrono/p891:Esito", soapRisposta);
            Assert.assertEquals("RISPOSTA_OK", rispostaOK);

            String token = UtilTest.trovaNodoString("/soapenv:Envelope/soapenv:Body/p891:risposta_RichiestaRispostaSincrona_testSincrono/p891:TokenSessione", soapRisposta);
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
            TestQualificazione03Sincrono.assertEquals("PAGenerica", mittente);

            String tipoMittente = UtilTest.trovaNodoString(expIntestazione + "/eGov_IT:Mittente/eGov_IT:IdentificativoParte/@tipo", soapRichiesta);
            TestQualificazione03Sincrono.assertEquals("SPC", tipoMittente);

            String destinatario = UtilTest.trovaNodoString(expIntestazione + "/eGov_IT:Destinatario/eGov_IT:IdentificativoParte", soapRichiesta);
            TestQualificazione03Sincrono.assertEquals("CNIPA", destinatario);

            String tipoDestinatario = UtilTest.trovaNodoString(expIntestazione + "/eGov_IT:Destinatario/eGov_IT:IdentificativoParte/@tipo", soapRichiesta);
            TestQualificazione03Sincrono.assertEquals("SPC", tipoDestinatario);

            String servizio = UtilTest.trovaNodoString(expIntestazione + "/eGov_IT:Servizio ", soapRichiesta);
            TestQualificazione03Sincrono.assertEquals("QualificazionePDD", servizio);

            String azione = UtilTest.trovaNodoString(expIntestazione + "/eGov_IT:Azione ", soapRichiesta);
            TestQualificazione03Sincrono.assertEquals("testSincrono", azione);

            String collaborazione = UtilTest.trovaNodoString(expIntestazione + "/eGov_IT:Collaborazione", soapRichiesta);
            TestQualificazione06AsincronoSimmetrico.assertNull(collaborazione);

            String identificatore = UtilTest.trovaNodoString(expIntestazione + "/eGov_IT:Messaggio/eGov_IT:Identificatore", soapRichiesta);
            TestQualificazione03Sincrono.assertTrue(identificatore.startsWith("PAGenerica_PAGenericaSPCoopIT_"));

            String profiloCollaborazione = UtilTest.trovaNodoString(expIntestazione + "/eGov_IT:ProfiloCollaborazione", soapRichiesta);
            TestQualificazione03Sincrono.assertEquals("EGOV_IT_ServizioSincrono", profiloCollaborazione);

            String token = UtilTest.trovaNodoString("/soapenv:Envelope/soapenv:Body/p891:richiesta_RichiestaRispostaSincrona_testSincrono/p891:TokenSessione", soapRichiesta);
            TestQualificazione03Sincrono.assertEquals("1ab955960a3281e965c0393ad0071f1e", token);

        }
    }
}
