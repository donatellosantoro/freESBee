package test.it.unibas.freesbee.ge.comunicazione.e;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TestSuite5RichiesteConferma extends TestCase {

    protected static Log logger = LogFactory.getLog(TestSuite5RichiesteConferma.class);

    public static TestSuite suite() {
        logger.info("TestSuite - Verifica Invio Comunicazione di Conferma per Pubblicatori e Categorie");

        TestSuite suite = new TestSuite("TestSuite - Verifica Invio Comunicazione di Conferma per Pubblicatori e Categorie");
        suite.addTestSuite(SetUp.class);
        suite.addTestSuite(Inserimento1.class);
        suite.addTestSuite(Inserimento2.class);
        suite.addTestSuite(Inserimento3.class);
        suite.addTestSuite(Inserimento4.class);
        suite.addTestSuite(Inserimento5.class);
        suite.addTestSuite(Inserimento6.class);
        suite.addTestSuite(Inserimento7.class);
        suite.addTestSuite(Inserimento8.class);
        suite.addTestSuite(TearDown.class);

        return suite;
    }
}
