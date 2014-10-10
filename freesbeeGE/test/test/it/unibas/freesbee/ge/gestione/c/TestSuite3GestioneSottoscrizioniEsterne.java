package test.it.unibas.freesbee.ge.gestione.c;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TestSuite3GestioneSottoscrizioniEsterne extends TestCase {

    protected static Log logger = LogFactory.getLog(TestSuite3GestioneSottoscrizioniEsterne.class);

    public static TestSuite suite() {
        logger.info("TestSuite - Gestione Sottoscrizioni Esterne");
        
        TestSuite suite = new TestSuite("TestSuite - Gestione Sottoscrizioni Esterne");

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
        suite.addTestSuite(Inserimento19.class);
        suite.addTestSuite(Inserimento20.class);
        suite.addTestSuite(Inserimento21.class);
        suite.addTestSuite(Inserimento22.class);
        suite.addTestSuite(Inserimento23.class);
        suite.addTestSuite(Inserimento24.class);
        suite.addTestSuite(Inserimento25.class);
        suite.addTestSuite(Inserimento26.class);
        suite.addTestSuite(Inserimento27.class);
        suite.addTestSuite(Eliminazione28.class);
        suite.addTestSuite(Eliminazione29.class);
        suite.addTestSuite(Eliminazione30.class);
        suite.addTestSuite(Eliminazione31.class);
        suite.addTestSuite(Eliminazione32.class);
        suite.addTestSuite(Eliminazione33.class);
        suite.addTestSuite(Eliminazione34.class);
        suite.addTestSuite(Eliminazione35.class);
        suite.addTestSuite(Eliminazione36.class);
        suite.addTestSuite(Eliminazione37.class);
        suite.addTestSuite(Eliminazione38.class);
        suite.addTestSuite(Eliminazione39.class);
        suite.addTestSuite(Eliminazione40.class);
        suite.addTestSuite(Eliminazione41.class);
        suite.addTestSuite(Eliminazione42.class);
        suite.addTestSuite(Eliminazione43.class);
        suite.addTestSuite(Eliminazione44.class);
        suite.addTestSuite(Eliminazione45.class);
        suite.addTestSuite(TearDown.class);

        return suite;
    }
}
