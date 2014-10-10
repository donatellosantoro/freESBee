/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.unibas.freesbee.test;

import it.unibas.freesbee.monitoraggio.dbwsa.DAOWindowUtils;
import java.util.List;
import junit.framework.TestCase;
import org.joda.time.DateTime;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.IntervalTimeType;
import it.unibas.freesbee.monitoraggio.dbwsa.dbutilita.DBUtilita;
import it.unibas.freesbee.monitoraggio.exception.daoexception.DAOException;
import it.unibas.freesbee.monitoraggio.libreria.Monitoraggio;
import junit.framework.Assert;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *
 * @author Giuseppe De Vivo
 */
public class TestWindowUtilsDAO extends TestCase {
    
    private DateTime dataFinale = new DateTime(2008,3,29,0,0,0,0);
    private IntervalTimeType intervalType;
    private Log logger = LogFactory.getLog(this.getClass());
    private Monitoraggio monitor;
    private DBUtilita db;
    
    
    public TestWindowUtilsDAO(String testName) {
        super(testName);
        monitor = Monitoraggio.getInstance();
        db = DBUtilita.getInstance();
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
        //--- Test getWindowTimesDAO(..)
        /* Viene controllato il corretto caricamento delle date dal database
         * e la corretta corrispondenza dei risultati calcolati utilizzando rispettivamente PostgreSQL e MySQL
        */
        try {
            monitor.configuraDataBase("org.postgresql.Driver", "jdbc:postgresql:freesbee_sla", "postgres", "postgres");
            monitor.setIdService("1");
            monitor.setIdInitiator("10");
            monitor.setIdResponder("100");
            List<DateTime> listaDatePostgres1 = DAOWindowUtils.getWindowTimesDAO(5, 5, dataFinale, "tempoRisposta");
            logger.debug("Lunghezza lista postgres: " + listaDatePostgres1.size());
            for(DateTime data : listaDatePostgres1) {
                logger.debug("Lista date Postgresql :" + data.toString("yyyy-MM-dd HH:mm:ss"));
            }
            monitor.configuraDataBase("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/freesbee_sla", "root", "mysql");
            List<DateTime> listaDateMySQL1 = DAOWindowUtils.getWindowTimesDAO(5, 5, dataFinale, "tempoRisposta");
            logger.debug("Lunghezza lista  mysql: " + listaDateMySQL1.size());
            for(DateTime data : listaDateMySQL1) {
                logger.debug("Lista date MySQL :" + data.toString("yyyy-MM-dd HH:mm:ss"));
            }
            if(listaDatePostgres1.size() != listaDateMySQL1.size()) {
                logger.info("LUNGHEZZE DIVERSE DELLE DUE LISTE !!!!!!!!");
                fail();
            }else {
                for(int i = 0; i < listaDatePostgres1.size(); i++) {
                    Assert.assertEquals("Test_01", listaDatePostgres1.get(i), listaDateMySQL1.get(i));
                }
            }
        }catch(DAOException daoe) {
            logger.error(daoe);
            fail();            
        }
    }
    
    public void test_02() {
        try {
            monitor.configuraDataBase("org.postgresql.Driver", "jdbc:postgresql:freesbee_sla", "postgres", "postgres");
            monitor.setIdService("1");
            monitor.setIdInitiator("10");
            monitor.setIdResponder("100");            
            List<DateTime> listaDatePostgres2 = DAOWindowUtils.getWindowInterTimesDAO(intervalType.MONTH, 5, dataFinale, "tempoRisposta");
            for(DateTime data : listaDatePostgres2) {
                logger.debug("Lista date Postgresql :" + data.toString("yyyy-MM-dd HH:mm:ss"));
            }
            monitor.configuraDataBase("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/freesbee_sla", "root", "mysql");
            List<DateTime> listaDateMySQL2 = DAOWindowUtils.getWindowInterTimesDAO(intervalType.MONTH, 5, dataFinale, "tempoRisposta");
            for(DateTime data : listaDateMySQL2) {
                logger.debug("Lista date MySQL :" + data.toString("yyyy-MM-dd HH:mm:ss"));
            }
            if(listaDatePostgres2.size() != listaDateMySQL2.size()) {
                fail();
            }else {
                for(int i = 0; i < listaDatePostgres2.size(); i++) {
                    Assert.assertEquals("Test_02", listaDatePostgres2.get(i), listaDateMySQL2.get(i));
                }
            }
        }catch(DAOException daoe) {
            logger.error(daoe);
            fail();            
        }
    }
    
    public void test_03() {
        try {
            monitor.configuraDataBase("org.postgresql.Driver", "jdbc:postgresql:freesbee_sla", "postgres", "postgres");
            monitor.setIdService("1");
            monitor.setIdInitiator("10");
            monitor.setIdResponder("100");            
            List<DateTime> listaDatePostgres2 = DAOWindowUtils.getWindowInterTimesDAO(intervalType.DAY, 4, dataFinale, "tempoRisposta");
            for(DateTime data : listaDatePostgres2) {
                logger.debug("Lista date Postgresql :" + data.toString("yyyy-MM-dd HH:mm:ss"));
            }
            monitor.configuraDataBase("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/freesbee_sla", "root", "mysql");
            List<DateTime> listaDateMySQL2 = DAOWindowUtils.getWindowInterTimesDAO(intervalType.DAY, 4, dataFinale, "tempoRisposta");
            for(DateTime data : listaDateMySQL2) {
                logger.debug("Lista date MySQL :" + data.toString("yyyy-MM-dd HH:mm:ss"));
            }
            if(listaDatePostgres2.size() != listaDateMySQL2.size()) {
                fail();
            }else {
                for(int i = 0; i < listaDatePostgres2.size(); i++) {
                    Assert.assertEquals("Test_03", listaDatePostgres2.get(i), listaDateMySQL2.get(i));
                }
            }
        }catch(DAOException daoe) {
            logger.error(daoe);
            fail();            
        }
    }

}
