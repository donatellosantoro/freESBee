package test.it.unibas.freesbee.ge.comunicazione.d;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TestSuite4ComunicazioneRimozionePubblicatore extends TestCase {

    protected static Log logger = LogFactory.getLog(TestSuite4ComunicazioneRimozionePubblicatore.class);

    public static TestSuite suite() {
        logger.info("TestSuite - Verifica Comunicazioni Rimozione Pubblicatore");

        TestSuite suite = new TestSuite("TestSuite - Verifica Comunicazioni Rimozione Pubblicatore");

        suite.addTestSuite(SetUp.class);
        suite.addTestSuite(Eliminazione1.class);
        suite.addTestSuite(Eliminazione2.class);
        suite.addTestSuite(Eliminazione3.class);
        suite.addTestSuite(Eliminazione4.class);
        suite.addTestSuite(Eliminazione5.class);
        suite.addTestSuite(Eliminazione6.class);
        suite.addTestSuite(Eliminazione7.class);
        suite.addTestSuite(Eliminazione8.class);
        suite.addTestSuite(Eliminazione9.class);
        suite.addTestSuite(Eliminazione10.class);
        suite.addTestSuite(Eliminazione11.class);
        suite.addTestSuite(Eliminazione12.class);
        suite.addTestSuite(Eliminazione13.class);
        suite.addTestSuite(Eliminazione14.class);
        suite.addTestSuite(Eliminazione15.class);
        suite.addTestSuite(Eliminazione16.class);
        suite.addTestSuite(Eliminazione17.class);
        suite.addTestSuite(Eliminazione18.class);
        suite.addTestSuite(Eliminazione19.class);
        suite.addTestSuite(Eliminazione20.class);
        suite.addTestSuite(Eliminazione21.class);
        suite.addTestSuite(Eliminazione22.class);
        suite.addTestSuite(Eliminazione23.class);
        suite.addTestSuite(Eliminazione24.class);
        suite.addTestSuite(Eliminazione25.class);
        suite.addTestSuite(Eliminazione26.class);
        suite.addTestSuite(Eliminazione27.class);
        suite.addTestSuite(Eliminazione28.class);
        suite.addTestSuite(Eliminazione29.class);
        suite.addTestSuite(TearDown.class);


        return suite;
    }
}
