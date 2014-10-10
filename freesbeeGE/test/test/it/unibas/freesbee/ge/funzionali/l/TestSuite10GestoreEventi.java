package test.it.unibas.freesbee.ge.funzionali.l;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TestSuite10GestoreEventi extends TestCase {

    protected static Log logger = LogFactory.getLog(TestSuite10GestoreEventi.class);

    public static TestSuite suite() {
        logger.info("TestSuite - Gestore Eventi Interno");

        TestSuite suite = new TestSuite("TestSuite - Gestore Eventi Interno");
        suite.addTestSuite(SetUp.class);
        suite.addTestSuite(Pubblicazione0.class);
        suite.addTestSuite(Pubblicazione1.class);
        suite.addTestSuite(Pubblicazione2.class);
        suite.addTestSuite(Pubblicazione3.class);
        suite.addTestSuite(Pubblicazione4.class);
        suite.addTestSuite(Pubblicazione5.class);
        suite.addTestSuite(Pubblicazione6.class);
        suite.addTestSuite(Pubblicazione7.class);
        suite.addTestSuite(Pubblicazione8.class);
        suite.addTestSuite(Pubblicazione9.class);
        suite.addTestSuite(Pubblicazione10.class);
        suite.addTestSuite(Pubblicazione11.class);
        suite.addTestSuite(Pubblicazione12.class);
        suite.addTestSuite(Pubblicazione13.class);
        suite.addTestSuite(Pubblicazione14.class);
        suite.addTestSuite(Pubblicazione15.class);
        suite.addTestSuite(Pubblicazione16.class);
        suite.addTestSuite(Pubblicazione17.class);
        suite.addTestSuite(Pubblicazione18.class);
        suite.addTestSuite(Pubblicazione19.class);
        suite.addTestSuite(Pubblicazione20.class);
        suite.addTestSuite(Pubblicazione21.class);
        suite.addTestSuite(Pubblicazione22.class);
        suite.addTestSuite(Pubblicazione23.class);
        suite.addTestSuite(Pubblicazione24.class);
        suite.addTestSuite(Pubblicazione25.class);
        suite.addTestSuite(Prelievo26.class);
        suite.addTestSuite(Prelievo27.class);
        suite.addTestSuite(Prelievo28.class);
        suite.addTestSuite(Prelievo29.class);
        suite.addTestSuite(TearDown.class);

        return suite;
    }
}
