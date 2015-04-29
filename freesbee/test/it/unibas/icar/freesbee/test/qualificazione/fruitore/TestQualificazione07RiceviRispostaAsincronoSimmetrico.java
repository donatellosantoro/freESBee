package it.unibas.icar.freesbee.test.qualificazione.fruitore;

import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.persistenza.hibernate.DAOUtilHibernate;
import it.unibas.icar.freesbee.test.qualificazione.UtilTest;
import it.unibas.icar.freesbee.utilita.CostantiSOAP;
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

public class TestQualificazione07RiceviRispostaAsincronoSimmetrico extends TestCase {

//    private org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog(this.getClass());
    private static final Logger logger = LoggerFactory.getLogger(TestQualificazione07RiceviRispostaAsincronoSimmetrico.class.getName());

    @Override
    public void tearDown() {
        DAOUtilHibernate.closeSessionFactory();
    }

    public void testQualificazione07() {
        /*** IL CONNETTORE DEL SOGGETTO PAGenerica DEV'ESSERE http://localhost:18196/PA/  ***/

        AbstractPATest paTest = new TestPA("/messaggiFruitore/7.riceviRispostaTestAsincronoSimmetricoRisposta.xml");
        try {
            String indirizzoPD = "http://localhost:8192/PD/PD_QualificazionePDD_Correlato/?FRUITORE=CNIPA&TIPO_FRUITORE=SPC" +
                                "&EROGATORE=PAGenerica&TIPO_EROGATORE=SPC" +
                                "&SERVIZIO=QualificazionePDDCorrelato&TIPO_SERVIZIO=SPC" +
                                "&AZIONE=riceviRispostaTestAsincronoSimmetrico";
            
            String richiesta = UtilTest.leggiMessaggio("/messaggiFruitore/7.riceviRispostaTestAsincronoSimmetricoRichiesta.xml");
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
            String tokenSessione = UtilTest.trovaNodoString("/soapenv:Envelope/soapenv:Body/p891:segnalazione_NotificazioneRisposta_riceviRispostaTestAsincronoSimmetrico/p891:TokenSessione", soapRichiesta);
            logger.info("TokenSessione: " + tokenSessione);

            Messaggio messaggio = new Messaggio();
            messaggio.setTipo(Messaggio.TIPO_RICEVUTO);
            messaggio.setIdSil("IDTestQualificazioneAsincronoSimmetrico" + new Date().getTime());
            messaggio.setIdMessaggio("PAGenerica_PAGenericaSPCoopIT_0000001_2009-01-21_12:55");
            messaggio.setCollaborazione("PAGenerica_PAGenericaSPCoopIT_0000001_2009-01-21_12:55");
            UtilTest.salvaMessaggio(messaggio);

            Map<String, String> mappaIntestazioni = new HashMap<String, String>();
            mappaIntestazioni.put(CostantiSOAP.INTEGRAZIONE_RIFERIMENTO_MESSAGGIO, messaggio.getIdSil());
            String risposta = UtilTest.invia(indirizzoPD, stringaRichiesta,mappaIntestazioni);
            logger.info("RISPOSTA:\n" + risposta);
            Document soapRisposta = UtilTest.leggiSOAP(risposta);

            String rispostaOK = UtilTest.trovaNodoString("/soapenv:Envelope/soapenv:Body/p891:risposta_NotificazioneRisposta_riceviRispostaTestAsincronoSimmetrico/p891:PresaInCarico", soapRisposta);
            Assert.assertEquals("OK", rispostaOK);

            String token = UtilTest.trovaNodoString("/soapenv:Envelope/soapenv:Body/p891:risposta_NotificazioneRisposta_riceviRispostaTestAsincronoSimmetrico/p891:TokenSessione", soapRisposta);
            Assert.assertEquals(tokenSessione, token);

            UtilTest.cancellaMessaggio("PAGenerica_PAGenericaSPCoopIT_0000001_2009-01-21_12:55");
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
            TestQualificazione07RiceviRispostaAsincronoSimmetrico.assertEquals("CNIPA", mittente);

            String tipoMittente = UtilTest.trovaNodoString(expIntestazione + "/eGov_IT:Mittente/eGov_IT:IdentificativoParte/@tipo", soapRichiesta);
            TestQualificazione07RiceviRispostaAsincronoSimmetrico.assertEquals("SPC", tipoMittente);

            String destinatario = UtilTest.trovaNodoString(expIntestazione + "/eGov_IT:Destinatario/eGov_IT:IdentificativoParte", soapRichiesta);
            TestQualificazione07RiceviRispostaAsincronoSimmetrico.assertEquals("PAGenerica", destinatario);

            String tipoDestinatario = UtilTest.trovaNodoString(expIntestazione + "/eGov_IT:Destinatario/eGov_IT:IdentificativoParte/@tipo", soapRichiesta);
            TestQualificazione07RiceviRispostaAsincronoSimmetrico.assertEquals("SPC", tipoDestinatario);

            String servizio = UtilTest.trovaNodoString(expIntestazione + "/eGov_IT:Servizio ", soapRichiesta);
            TestQualificazione07RiceviRispostaAsincronoSimmetrico.assertEquals("QualificazionePDDCorrelato", servizio);

            String azione = UtilTest.trovaNodoString(expIntestazione + "/eGov_IT:Azione ", soapRichiesta);
            TestQualificazione07RiceviRispostaAsincronoSimmetrico.assertEquals("riceviRispostaTestAsincronoSimmetrico", azione);

            String identificatore = UtilTest.trovaNodoString(expIntestazione + "/eGov_IT:Messaggio/eGov_IT:Identificatore", soapRichiesta);
            TestQualificazione07RiceviRispostaAsincronoSimmetrico.assertTrue(identificatore.startsWith("CNIPA_CNIPASPCoopIT_"));

            String collaborazione = UtilTest.trovaNodoString(expIntestazione + "/eGov_IT:Collaborazione", soapRichiesta);
            TestQualificazione07RiceviRispostaAsincronoSimmetrico.assertEquals("PAGenerica_PAGenericaSPCoopIT_0000001_2009-01-21_12:55",collaborazione);
//            TestQualificazione07RiceviRispostaAsincronoSimmetrico.assertTrue(collaborazione.startsWith("PAGenerica_PAGenericaSPCoopIT_"));

            String profiloCollaborazione = UtilTest.trovaNodoString(expIntestazione + "/eGov_IT:ProfiloCollaborazione", soapRichiesta);
            TestQualificazione07RiceviRispostaAsincronoSimmetrico.assertEquals("EGOV_IT_ServizioAsincronoSimmetrico", profiloCollaborazione);

            String rispostaOK = UtilTest.trovaNodoString("/soapenv:Envelope/soapenv:Body/p891:segnalazione_NotificazioneRisposta_riceviRispostaTestAsincronoSimmetrico/p891:Esito", soapRichiesta);
            TestQualificazione07RiceviRispostaAsincronoSimmetrico.assertEquals("RISPOSTA_OK", rispostaOK);

            String token = UtilTest.trovaNodoString("/soapenv:Envelope/soapenv:Body/p891:segnalazione_NotificazioneRisposta_riceviRispostaTestAsincronoSimmetrico/p891:TokenSessione", soapRichiesta);
            TestQualificazione07RiceviRispostaAsincronoSimmetrico.assertEquals("1ab955960a3281e965c0393ad0071f1e", token);

        }
    }
}
