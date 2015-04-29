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

public class TestQualificazione04IdentificativoParteErrato extends TestCase {

//    protected Log logger = LogFactory.getLog(this.getClass());
    private static final Logger logger = LoggerFactory.getLogger(TestQualificazione04IdentificativoParteErrato.class.getName());

    @Override
    public void tearDown() {
        DAOUtilHibernate.closeSessionFactory();
    }

    public void test1() {
        String richiesta = UtilTest.leggiMessaggio("/messaggi/4. IdentificativoParteErratoRichiesta.xml");
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
        Assert.assertEquals("CNIPAX", destinatario);

        String servizio = UtilTest.trovaNodoString(expIntestazione + "/eGov_IT:Servizio ", soapRisposta);
        Assert.assertEquals("QualificazionePDD", servizio);

        String azione = UtilTest.trovaNodoString(expIntestazione + "/eGov_IT:Azione ", soapRisposta);
        Assert.assertEquals("testSincrono", azione);

        String identificatore = UtilTest.trovaNodoString(expIntestazione + "/eGov_IT:Messaggio/eGov_IT:Identificatore", soapRisposta);
        Assert.assertTrue(identificatore.startsWith("PAGenerica_PAGenericaSPCoopIT_"));

        String riferimentoMessaggio = UtilTest.trovaNodoString(expIntestazione + "/eGov_IT:Messaggio/eGov_IT:RiferimentoMessaggio", soapRisposta);
        Assert.assertEquals(idMessaggioRichiesta, riferimentoMessaggio);

        String profiloCollaborazione = UtilTest.trovaNodoString(expIntestazione + "/eGov_IT:ProfiloCollaborazione", soapRisposta);
        Assert.assertEquals("EGOV_IT_ServizioSincrono", profiloCollaborazione);

        String codiceEccezione = UtilTest.trovaNodoString("/soapenv:Envelope/soapenv:Header/eGov_IT:Intestazione/eGov_IT:ListaEccezioni/eGov_IT:Eccezione/@codiceEccezione", soapRisposta);
        Assert.assertEquals("EGOV_IT_101", codiceEccezione);

        String contestoCodifica = UtilTest.trovaNodoString("/soapenv:Envelope/soapenv:Header/eGov_IT:Intestazione/eGov_IT:ListaEccezioni/eGov_IT:Eccezione/@contestoCodifica", soapRisposta);
        Assert.assertEquals("ErroreIntestazioneMessaggioSPCoop", contestoCodifica);

        String posizione = UtilTest.trovaNodoString("/soapenv:Envelope/soapenv:Header/eGov_IT:Intestazione/eGov_IT:ListaEccezioni/eGov_IT:Eccezione/@posizione", soapRisposta);
        Assert.assertEquals("Mittente/IdentificativoParte", posizione);

        String rilevanza = UtilTest.trovaNodoString("/soapenv:Envelope/soapenv:Header/eGov_IT:Intestazione/eGov_IT:ListaEccezioni/eGov_IT:Eccezione/@rilevanza", soapRisposta);
        Assert.assertEquals("GRAVE", rilevanza);

        String faultCode = UtilTest.trovaNodoString("/soapenv:Envelope/soapenv:Body/soapenv:Fault/faultcode", soapRisposta);
        Assert.assertEquals("SOAP_ENV:Client", faultCode);

        String faultString = UtilTest.trovaNodoString("/soapenv:Envelope/soapenv:Body/soapenv:Fault/faultstring", soapRisposta);
        Assert.assertEquals("EGOV_IT_001 - Formato Busta non corretto", faultString);
//        Assert.assertEquals("<![CDATA[ EGOV_IT_001 - Formato Busta non corretto]]>", faultString);


    }
}
