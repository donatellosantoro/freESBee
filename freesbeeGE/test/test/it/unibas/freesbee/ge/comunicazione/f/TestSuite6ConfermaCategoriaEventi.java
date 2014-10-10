package test.it.unibas.freesbee.ge.comunicazione.f;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TestSuite6ConfermaCategoriaEventi extends TestCase {

    protected static Log logger = LogFactory.getLog(TestSuite6ConfermaCategoriaEventi.class);

    public static TestSuite suite() {
        logger.info("TestSuite - Conferma Categorie di Eventi");

        TestSuite suite = new TestSuite("TestSuite - Conferma Categorie di Eventi");
        suite.addTestSuite(SetUp.class);
        suite.addTestSuite(Conferma1.class);
        suite.addTestSuite(Conferma2.class);
        suite.addTestSuite(Conferma3.class);
        suite.addTestSuite(Conferma4.class);
        suite.addTestSuite(Conferma5.class);
        suite.addTestSuite(Conferma6.class);
        suite.addTestSuite(Conferma7.class);
        suite.addTestSuite(Conferma8.class);
        suite.addTestSuite(Conferma9.class);
        suite.addTestSuite(Conferma10.class);
        suite.addTestSuite(Conferma11.class);
        suite.addTestSuite(Conferma12.class);
        suite.addTestSuite(Conferma13.class);
        suite.addTestSuite(Conferma14.class);
        suite.addTestSuite(Conferma15.class);
        suite.addTestSuite(Conferma16.class);
        suite.addTestSuite(Conferma17.class);
        suite.addTestSuite(Conferma18.class);
        suite.addTestSuite(Conferma19.class);
        suite.addTestSuite(TearDown.class);

        return suite;
    }
}
