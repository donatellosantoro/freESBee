package test.it.unibas.freesbee.ge.funzionali.n;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TestSuite12Generale extends TestCase {

    protected static Log logger = LogFactory.getLog(TestSuite12Generale.class);

    public static TestSuite suite() {
        logger.info("TestSuite - Generale");
        TestSuite suite = new TestSuite("TestSuite - Generale");
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
     
        suite.addTestSuite(TearDown.class);

        return suite;
    }
}
