/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unibas.freesbee.test;

import junit.framework.TestCase;
import org.joda.time.DateTime;
import it.unibas.freesbee.monitoraggio.calcolo.windowutils.WindowUtils;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Window;
import java.util.List;
import it.unibas.freesbee.monitoraggio.calcolo.windowutils.WindowIntervalMonth;
import it.unibas.freesbee.monitoraggio.calcolo.windowutils.WindowIntervalThreeMonths;
import it.unibas.freesbee.monitoraggio.calcolo.windowutils.WindowIntervalTwoWeeks;
import junit.framework.Assert;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Giuseppe De Vivo
 */
public class TestGestioneFinestreTemporali extends TestCase {

    private Log logger = LogFactory.getLog(this.getClass());

    public TestGestioneFinestreTemporali(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void test_01() {
        DateTime dataFine = new DateTime(2008, 3, 29, 0, 0, 0, 0);
        WindowUtils windowUtils = new WindowIntervalTwoWeeks(new Window(), dataFine);
        List<DateTime> arrayDate = windowUtils.getIntervalWeek();
        if (arrayDate == null) {
            fail();
        } else {
            for (DateTime data : arrayDate) {
                logger.debug("\nData classe test:  " + data.toString("yyyy-MM-dd HH:mm:ss"));
            }
        }
        // Alla data in posizione [0] devono essere tolti 7 giorni
        Assert.assertEquals("2008-03-29 00:00:00", arrayDate.get(0).toString("yyyy-MM-dd HH:mm:ss"));
        Assert.assertEquals("2008-03-22 00:00:00", arrayDate.get(1).toString("yyyy-MM-dd HH:mm:ss"));
    }

    public void test_02() {
        DateTime dataFine = new DateTime(2008, 3, 29, 0, 0, 0, 0);
        WindowUtils windowUtils = new WindowIntervalTwoWeeks(new Window(), dataFine);
        List<DateTime> arrayDate = windowUtils.getIntervalDay();
        if (arrayDate == null) {
            logger.debug("Lista vuota !!");
            fail();
        } else {
            for (DateTime data : arrayDate) {
                logger.debug("\nData classe test:  " + data.toString("yyyy-MM-dd HH:mm:ss"));
            }
        }
        // Alla data in posizione [0] devono essere tolti 14 giorni, controllo le settimane.
        Assert.assertEquals("2008-03-29 00:00:00", arrayDate.get(0).toString("yyyy-MM-dd HH:mm:ss"));
        Assert.assertEquals("2008-03-22 00:00:00", arrayDate.get(7).toString("yyyy-MM-dd HH:mm:ss"));
        Assert.assertEquals("2008-03-16 00:00:00", arrayDate.get(13).toString("yyyy-MM-dd HH:mm:ss"));
    }

    public void test_03() {
        DateTime dataFine = new DateTime(2008, 3, 29, 0, 0, 0, 0);
        WindowUtils windowUtils = new WindowIntervalMonth(new Window(), dataFine);
        List<DateTime> arrayDate = windowUtils.getIntervalHour();
        if (arrayDate == null) {
            logger.debug("Lista vuota !!");
            fail();
        } else {
            logger.debug("Oggetti in lista: " + arrayDate.size());
        }
    }

    public void test_04() {
        DateTime dataFine = new DateTime(2008, 3, 29, 0, 0, 0, 0);
        WindowUtils windowUtils = new WindowIntervalMonth(new Window(), dataFine);
        List<DateTime> arrayDate = windowUtils.getIntervalSixHours();
        if (arrayDate == null) {
            logger.debug("Lista vuota !!");
            fail();
        } else {
            logger.debug("Oggetti in lista: " + arrayDate.size());
        }
    }

    public void test_05() {
        DateTime dataFine = new DateTime(2008, 3, 29, 0, 0, 0, 0);
        WindowUtils windowUtils = new WindowIntervalThreeMonths(new Window(), dataFine);
        List<DateTime> arrayDate = windowUtils.getIntervalWeek();
        if (arrayDate == null) {
            logger.debug("Lista vuota !!");
            fail();
        } else {
            logger.debug("Oggetti in lista: " + arrayDate.size());
        }
    }
}
