package test.it.unibas.freesbee.ge.gestione.a;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TestSuite1GestionePubblicatori extends TestCase {

    protected static Log logger = LogFactory.getLog(TestSuite1GestionePubblicatori.class);

    public static TestSuite suite() {
        logger.info("TestSuite - Gestione Pubblicatori");
        
        TestSuite suite = new TestSuite("TestSuite - Gestione Pubblicatori");

        suite.addTestSuite(SetUp.class);
        suite.addTestSuite(Inserimento0.class);
        suite.addTestSuite(Inserimento1.class);
        suite.addTestSuite(Inserimento2.class);
        suite.addTestSuite(Inserimento3.class);
        suite.addTestSuite(Inserimento4.class);
        suite.addTestSuite(Inserimento5.class);
        suite.addTestSuite(Inserimento6.class);
        suite.addTestSuite(Inserimento7.class);
        suite.addTestSuite(Eliminazione8.class);
        suite.addTestSuite(Eliminazione9.class);
        suite.addTestSuite(Eliminazione10.class);
        suite.addTestSuite(Eliminazione11.class);
        suite.addTestSuite(Eliminazione12.class);
        suite.addTestSuite(Eliminazione13.class);
        suite.addTestSuite(Eliminazione14.class);
        suite.addTestSuite(Eliminazione15.class);
        suite.addTestSuite(Eliminazione16.class);
        suite.addTestSuite(TearDown.class);

        return suite;
    }
}
