package it.unibas.icar.freesbee.test.regione.fruitore;

import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.persistenza.hibernate.DAOUtilHibernate;
import it.unibas.icar.freesbee.test.qualificazione.UtilTest;
import it.unibas.icar.freesbee.test.qualificazione.fruitore.AbstractPATest;
import it.unibas.icar.freesbee.test.qualificazione.fruitore.TestQualificazione08;
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

public class TestAzioneScorretta extends TestCase {

//    private org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog(this.getClass());
    private static final Logger logger = LoggerFactory.getLogger(TestAzioneScorretta.class.getName());

    @Override
    public void tearDown() {
        DAOUtilHibernate.closeSessionFactory();
    }

    public void testQualificazioneAzioneScorretta() {
        AbstractPATest paTest = new TestAzioneScorretta.TestPA("/messaggiFruitore/7.riceviRispostaTestAsincronoSimmetricoRisposta.xml");
        try {
            String indirizzoPD = "http://localhost:8192/PD/PD_QualificazionePDD/?FRUITORE=PAGenerica&TIPO_FRUITORE=SPC" +
                                "&EROGATORE=CNIPA&TIPO_EROGATORE=SPC" +
                                "&SERVIZIO=QualificazionePDD&TIPO_SERVIZIO=SPC" +
                                "&AZIONE=riceviRispostaTestAsincronoSimmetrico";
//            String indirizzoPD = "http://localhost:8192/PD/PD_QualificazionePDD_Correlato/?FRUITORE=CNIPA&TIPO_FRUITORE=SPC"
//                    + "&EROGATORE=PAGenerica&TIPO_EROGATORE=SPC"
//                    + "&SERVIZIO=QualificazionePDD&TIPO_SERVIZIO=SPC"
//                    + "&AZIONE=riceviRispostaTestAsincronoSimmetrico";

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
            String tokenSessione = UtilTest.trovaNodoString("/soapenv:Envelope/soapenv:Body/p891:richiesta_RichiestaRispostaSincrona_start/p891:TokenSessione", soapRichiesta);
            logger.info("TokenSessione: " + tokenSessione);
            String risposta = UtilTest.invia(indirizzoPD, stringaRichiesta);
            logger.info("RISPOSTA:\n" + risposta);
            Document soapRisposta = UtilTest.leggiSOAP(risposta);

            Node nodoHeader = UtilTest.trovaNodo("/soapenv:Envelope/soapenv:Header", soapRisposta);
            Assert.assertNull("La risposta deve contenere solo il body, senza le intestazioni egov", nodoHeader);

            String faultString = UtilTest.trovaNodoString("/soapenv:Envelope/soapenv:Body/soapenv:Fault/faultstring", soapRisposta);
//        Assert.assertEquals("<![CDATA[ EGOV_IT_001 - Formato Busta non corretto]]>", faultString);
            Assert.assertEquals("EGOV_IT_001 - Formato Busta non corretto", faultString);

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
        }
    }
}
