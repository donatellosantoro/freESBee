package test.it.unibas.freesbee.ge.gestione.b;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TestSuite2GestioneSottoscrizioniInterne extends TestCase {

    protected static Log logger = LogFactory.getLog(TestSuite2GestioneSottoscrizioniInterne.class);

    public static TestSuite suite() {
        logger.info("TestSuite - Gestione Sottoscrizioni Interne");

        TestSuite suite = new TestSuite("TestSuite - Gestione Sottoscrizioni Interne");

        suite.addTestSuite(SetUp.class);
        suite.addTestSuite(Inserimento1.class);
        suite.addTestSuite(Inserimento2.class);
        suite.addTestSuite(Inserimento3.class);
        suite.addTestSuite(Inserimento4.class);
        suite.addTestSuite(Inserimento5.class);
        suite.addTestSuite(Inserimento6.class);
        suite.addTestSuite(Inserimento7.class);
        suite.addTestSuite(Inserimento8.class);
        suite.addTestSuite(Inserimento9.class);
        suite.addTestSuite(Inserimento10.class);
        suite.addTestSuite(Inserimento11.class);
        suite.addTestSuite(Inserimento12.class);
        suite.addTestSuite(Inserimento13.class);
        suite.addTestSuite(Inserimento14.class);
        suite.addTestSuite(Inserimento15.class);
        suite.addTestSuite(Inserimento16.class);
        suite.addTestSuite(Inserimento17.class);
        suite.addTestSuite(Inserimento18.class);
        suite.addTestSuite(Eliminazione19.class);
        suite.addTestSuite(Eliminazione20.class);
        suite.addTestSuite(Eliminazione21.class);
        suite.addTestSuite(Eliminazione22.class);
        suite.addTestSuite(Eliminazione23.class);
        suite.addTestSuite(Eliminazione24.class);
        suite.addTestSuite(TearDown.class);

        return suite;
    }
}
