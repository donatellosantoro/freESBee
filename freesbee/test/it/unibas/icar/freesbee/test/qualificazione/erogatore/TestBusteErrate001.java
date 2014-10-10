package it.unibas.icar.freesbee.test.qualificazione.erogatore;

import it.unibas.icar.freesbee.test.qualificazione.UtilTest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class TestBusteErrate001 extends TestCase {

    protected Log logger = LogFactory.getLog(this.getClass());

    public void testBusta() {
        int testCorrente = 32;

        testBusta(testCorrente);

        for (int i = 2; i < testCorrente + 1; i++) {
            testBusta(i);
        }
    }

    private void testBusta(int id) {
        String stringId = new DecimalFormat("00").format(id);
        String richiesta = UtilTest.leggiMessaggio("/messaggi/BusteErrate/Errore001/" + stringId + "_REQ.xml", false);
        if (richiesta == null) {
            return;
        }
        logger.debug("RICHIESTA:\n" + richiesta);
        String risposta = UtilTest.inviaPA(richiesta);
        logger.debug("RISPOSTA:\n" + risposta);
        Document soapRisposta = UtilTest.leggiSOAP(risposta);

        Node nodoHeader = UtilTest.trovaNodo("/soapenv:Envelope/soapenv:Header", soapRisposta);
        Assert.assertNull("La risposta deve contenere solo il body, senza le intestazioni egov", nodoHeader);

        String faultString = UtilTest.trovaNodoString("/soapenv:Envelope/soapenv:Body/soapenv:Fault/faultstring", soapRisposta);
//        Assert.assertEquals("<![CDATA[ EGOV_IT_001 - Formato Busta non corretto]]>", faultString);
        Assert.assertEquals("EGOV_IT_001 - Formato Busta non corretto", faultString);
    }
}
