package it.unibas.icar.freesbee.test.qualificazione.erogatore;

import it.unibas.icar.freesbee.persistenza.hibernate.DAOUtilHibernate;
import it.unibas.icar.freesbee.test.qualificazione.UtilTest;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;

public class TestQualificazione08AsincronoAsimettrico extends TestCase {

//    protected Log logger = LogFactory.getLog(this.getClass());
    private static final Logger logger = LoggerFactory.getLogger(TestQualificazione08AsincronoAsimettrico.class.getName());

    @Override
    public void tearDown() {
        DAOUtilHibernate.closeSessionFactory();
    }

    public void test1() {
        String richiesta = UtilTest.leggiMessaggio("/messaggi/8. testAsincronoAsimmetricoRichiesta.xml");
        logger.info("RICHIESTA:\n" + richiesta);

        Document soapRichiesta = UtilTest.leggiSOAP(richiesta);
        String idMessaggioRichiesta = UtilTest.trovaNodoString("/soapenv:Envelope/soapenv:Header/eGov_IT:Intestazione/eGov_IT:IntestazioneMessaggio/eGov_IT:Messaggio/eGov_IT:Identificatore", soapRichiesta);
        if (idMessaggioRichiesta != null) {
            logger.info("Dobbiamo cancellare il messaggio " + idMessaggioRichiesta + " prima di eseguire il test");
            UtilTest.cancellaMessaggio(idMessaggioRichiesta);
        }

        String risposta = UtilTest.inviaPA(richiesta);
        logger.info("RISPOSTA:\n" + risposta);
        Document soapRisposta = UtilTest.leggiSOAP(risposta);

        String expIntestazione = "/SOAP_ENV:Envelope/SOAP_ENV:Header/eGov_IT:Intestazione/eGov_IT:IntestazioneMessaggio";

        String mittente = UtilTest.trovaNodoString(expIntestazione + "/eGov_IT:Mittente/eGov_IT:IdentificativoParte", soapRisposta);
        Assert.assertEquals("PAGenerica", mittente);

        String destinatario = UtilTest.trovaNodoString(expIntestazione + "/eGov_IT:Destinatario/eGov_IT:IdentificativoParte", soapRisposta);
        Assert.assertEquals("CNIPA", destinatario);

        String collaborazione = UtilTest.trovaNodoString(expIntestazione + "/eGov_IT:Collaborazione", soapRisposta);
        Assert.assertEquals("CNIPA_CNIPAspcoopit_0000007_2007-11-07_16:28", collaborazione);

        String servizio = UtilTest.trovaNodoString(expIntestazione + "/eGov_IT:Servizio ", soapRisposta);
        Assert.assertEquals("QualificazionePDD", servizio);

        String azione = UtilTest.trovaNodoString(expIntestazione + "/eGov_IT:Azione ", soapRisposta);
        Assert.assertEquals("testAsincronoAsimmetrico", azione);

        String identificatore = UtilTest.trovaNodoString(expIntestazione + "/eGov_IT:Messaggio/eGov_IT:Identificatore", soapRisposta);
        Assert.assertTrue(identificatore.startsWith("PAGenerica_PAGenericaSPCoopIT_"));

        String riferimentoMessaggio = UtilTest.trovaNodoString(expIntestazione + "/eGov_IT:Messaggio/eGov_IT:RiferimentoMessaggio", soapRisposta);
        Assert.assertEquals(idMessaggioRichiesta, riferimentoMessaggio);

        String profiloCollaborazione = UtilTest.trovaNodoString(expIntestazione + "/eGov_IT:ProfiloCollaborazione", soapRisposta);
        Assert.assertEquals("EGOV_IT_ServizioAsincronoAsimmetrico", profiloCollaborazione);

        String presaInCarico = UtilTest.trovaNodoString("/soapenv:Envelope/soapenv:Body/p891:risposta_RichiestaRispostaAsincrona_testAsincronoAsimmetrico/p891:PresaInCarico", soapRisposta);
        Assert.assertEquals("OK", presaInCarico);

        String token = UtilTest.trovaNodoString("/soapenv:Envelope/soapenv:Body/p891:risposta_RichiestaRispostaAsincrona_testAsincronoAsimmetrico/p891:TokenSessione", soapRisposta);
        Assert.assertEquals("1ab955960a3281e965c0393ad0071f1e", token);


    }
}
