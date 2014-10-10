package test.it.unibas.freesbee.ge.comunicazione.h;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TestSuite8ComunicazioneRimozioneSottoscrizioneGE extends TestCase {

    protected static Log logger = LogFactory.getLog(TestSuite8ComunicazioneRimozioneSottoscrizioneGE.class);

    public static TestSuite suite() {
        logger.info("TestSuite - Verifica Comunicazioni Rimozione Sottoscrizione Gestore Eventi");
        TestSuite suite = new TestSuite("TestSuite - Verifica Comunicazioni Rimozione Sottoscrizione Gestore Eventi");

        suite.addTestSuite(SetUp.class);
        suite.addTestSuite(Eliminazione1.class);
        suite.addTestSuite(Eliminazione2.class);
        suite.addTestSuite(Eliminazione3.class);
        suite.addTestSuite(Eliminazione4.class);
        suite.addTestSuite(Richiesta5.class);
        suite.addTestSuite(Richiesta6.class);
        suite.addTestSuite(Richiesta7.class);
        suite.addTestSuite(Richiesta8.class);
        suite.addTestSuite(Richiesta9.class);
        suite.addTestSuite(Richiesta10.class);
        suite.addTestSuite(Richiesta11.class);
        suite.addTestSuite(Richiesta12.class);
        suite.addTestSuite(Richiesta13.class);
        suite.addTestSuite(Richiesta14.class);
        suite.addTestSuite(Richiesta15.class);
        suite.addTestSuite(Eliminazione16.class);
        suite.addTestSuite(Eliminazione17.class);
        suite.addTestSuite(TearDown.class);

        return suite;
    }
}
