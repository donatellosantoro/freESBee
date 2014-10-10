package test.it.unibas.freesbee.ge.funzionali.m;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TestSuite11Ripulitura extends TestCase {

    protected static Log logger = LogFactory.getLog(TestSuite11Ripulitura.class);

    public static TestSuite suite() {
        logger.info("TestSuite - Ripulitura Eventi Pubblicati");
        TestSuite suite = new TestSuite("TestSuite - Ripulitura Eventi Pubblicati");
        suite.addTestSuite(SetUp.class);
        suite.addTestSuite(Pubblicazione1.class);
        suite.addTestSuite(Pubblicazione2.class);
        suite.addTestSuite(Pubblicazione3.class);
        suite.addTestSuite(Pubblicazione4.class);
        suite.addTestSuite(TearDown.class);

        return suite;
    }
}
