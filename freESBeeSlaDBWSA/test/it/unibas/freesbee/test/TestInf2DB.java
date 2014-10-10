/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unibas.freesbee.test;



import it.unibas.freesbee.monitoraggio.calcolo.core.GuaranteeTermsResult;
import it.unibas.freesbee.monitoraggio.exception.daoexception.DAOException;
import it.unibas.freesbee.monitoraggio.exception.inf2exception.INF2Exception;
import it.unibas.freesbee.monitoraggio.libreria.Monitoraggio;
import java.util.Calendar;
import java.util.GregorianCalendar;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;

/**
 *
 * @author Giuseppe De Vivo
 */
public class TestInf2DB extends TestCase {
    
    private static Log logger = LogFactory.getLog(TestInf2DB.class);
    private Monitoraggio monitor;
    private GuaranteeTermsResult risultatoMySQL;
    private GuaranteeTermsResult risultatoPostgreSQL;
    
    public TestInf2DB(String testName) {
        super(testName);
    }            

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        monitor = Monitoraggio.getInstance();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void test_01(){
        /* -- Monitoraggio con PostgreSQL --  */ 
        logger.info("-----------------------------------------------------------");
        logger.info("---------- UNIBAS TEST 1 * MONITORAGGIO POSTGRESQL --------");
        logger.info("-----------------------------------------------------------");
        monitor.configuraDataBase("org.postgresql.Driver", "jdbc:postgresql:freesbee_sla", "postgres", "postgres");
        try {
            //DateTime dataJoda = new DateTime(2008,3,30,0,0,0,0);
            //risultatoPostgreSQL = monitor.effettuaMonitoraggio("1", "10", "100", "MaxTempoRisposta5", dataJoda.toDate());
            Calendar data = new GregorianCalendar(2008,2,30);
            risultatoPostgreSQL = monitor.effettuaMonitoraggio("1", "10", "100", "MaxTempoRisposta5", data.getTime());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + risultatoPostgreSQL.isSoddisfatto());
        }catch(DAOException daoe) {
            logger.error("ECCEZIONE: " + daoe);
            fail();
        }catch(INF2Exception inf2e) {
            logger.error("ECCEZIONE: " + inf2e);
            fail();
        }
        
        /* -- Monitoraggio con MySQL -- */
        logger.info("-----------------------------------------------------------");
        logger.info("---------- UNIBAS TEST 1 * MONITORAGGIO MySQL -------------");
        logger.info("-----------------------------------------------------------");
        monitor.configuraDataBase("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/freesbee_sla", "root", "mysql");
        try {
            DateTime dataJoda = new DateTime(2008,3,30,0,0,0,0);
             risultatoMySQL = monitor.effettuaMonitoraggio("1", "10", "100", "MaxTempoRisposta5", dataJoda.toDate());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + risultatoMySQL.isSoddisfatto());
        }catch(DAOException daoe) {
            logger.error("ECCEZIONE: " + daoe);
            fail();
        }catch(INF2Exception inf2e) {
            logger.error("ECCEZIONE: " + inf2e);
            fail();
        }          
        logger.debug("Risultato postgres: " + risultatoPostgreSQL.getRisultatoFinale());
        logger.debug("Risultato MySQL: " + risultatoMySQL.getRisultatoFinale());
        Assert.assertEquals( "test_01 FALLITO", risultatoPostgreSQL.getRisultatoFinale(), risultatoMySQL.getRisultatoFinale());        
    }
    
    
    public void test_02() {
        /* -- Monitoraggio con PostgreSQL --  */ 
        logger.info("-----------------------------------------------------------");
        logger.info("----------UNIBAS TEST 2 * MONITORAGGIO POSTGRESQL ---------");
        logger.info("-----------------------------------------------------------");
        monitor.configuraDataBase("org.postgresql.Driver", "jdbc:postgresql:freesbee_sla", "postgres", "postgres");
        try {
            DateTime dataJoda = new DateTime(2008,3,30,0,0,0,0);
            risultatoPostgreSQL = monitor.effettuaMonitoraggio("1", "10", "100", "TempoRispostaMedio1M", dataJoda.toDate());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + risultatoPostgreSQL.isSoddisfatto());
        }catch(DAOException daoe) {
            logger.error("ECCEZIONE: " + daoe);
            fail();
        }catch(INF2Exception inf2e) {
            logger.error("ECCEZIONE: " + inf2e);
            fail();
        }
        
        /* -- Monitoraggio con MySQL -- */
        logger.info("-----------------------------------------------------------");
        logger.info("----------UNIBAS TEST 2 * MONITORAGGIO MySQL --------------");
        logger.info("-----------------------------------------------------------");
        monitor.configuraDataBase("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/freesbee_sla", "root", "mysql");
        try {
            DateTime dataJoda = new DateTime(2008,3,30,0,0,0,0);
            risultatoMySQL = monitor.effettuaMonitoraggio("1", "10", "100", "TempoRispostaMedio1M", dataJoda.toDate());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + risultatoMySQL.isSoddisfatto());
        }catch(DAOException daoe) {
            logger.error("ECCEZIONE: " + daoe);
            fail();
        }catch(INF2Exception inf2e) {
            logger.error("ECCEZIONE: " + inf2e);
            fail();
        }
        
        logger.debug("Risultato postgres: " + risultatoPostgreSQL.getRisultatoFinale());
        logger.debug("Risultato MySQL: " + risultatoMySQL.getRisultatoFinale());
        Assert.assertEquals( "test_02 FALLITO", risultatoPostgreSQL.getRisultatoFinale(), risultatoMySQL.getRisultatoFinale());        
    }
    
    public void test_03() {
        /* -- Monitoraggio con PostgreSQL --  */ 
        logger.info("-----------------------------------------------------------");
        logger.info("----------UNIBAS TEST 3 * MONITORAGGIO POSTGRESQL ---------");
        logger.info("-----------------------------------------------------------");
        monitor.configuraDataBase("org.postgresql.Driver", "jdbc:postgresql:freesbee_sla", "postgres", "postgres");
        try {
            DateTime dataJoda = new DateTime(2008,3,30,0,0,0,0);
            risultatoPostgreSQL = monitor.effettuaMonitoraggio("2", "20", "200", "TempoMedioCorretto5_10", dataJoda.toDate());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + risultatoPostgreSQL.isSoddisfatto());
        }catch(DAOException daoe) {
            logger.error("ECCEZIONE: " + daoe);
            fail();
        }catch(INF2Exception inf2e) {
            logger.error("ECCEZIONE: " + inf2e);
            fail();
        }

        /* -- Monitoraggio con MySQL -- */
        logger.info("-----------------------------------------------------------");
        logger.info("----------UNIBAS TEST 3 * MONITORAGGIO MySQL --------------");
        logger.info("-----------------------------------------------------------");
        monitor.configuraDataBase("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/freesbee_sla", "root", "mysql");
        try {
            DateTime dataJoda = new DateTime(2008,3,30,0,0,0,0);
            risultatoMySQL = monitor.effettuaMonitoraggio("2", "20", "200", "TempoMedioCorretto5_10", dataJoda.toDate());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + risultatoMySQL.isSoddisfatto());
        }catch(DAOException daoe) {
            logger.error("ECCEZIONE: " + daoe);
            fail();
        }catch(INF2Exception inf2e) {
            logger.error("ECCEZIONE: " + inf2e);
            fail();
        }   
        logger.debug("Risultato postgres: " + risultatoPostgreSQL.getRisultatoFinale());
        logger.debug("Risultato MySQL: " + risultatoMySQL.getRisultatoFinale());
        Assert.assertEquals( "test_03 FALLITO", risultatoPostgreSQL.getRisultatoFinale(), risultatoMySQL.getRisultatoFinale());        
    }
    
    public void test04_01() {
        /* -- Monitoraggio con PostgreSQL --  */ 
        logger.info("-----------------------------------------------------------");
        logger.info("----------UNIBAS TEST 4_01 * MONITORAGGIO POSTGRESQL ---------");
        logger.info("-----------------------------------------------------------");
        monitor.configuraDataBase("org.postgresql.Driver", "jdbc:postgresql:freesbee_sla", "postgres", "postgres");
        try {
            DateTime dataJoda = new DateTime(2008,3,30,0,0,0,0);
            risultatoPostgreSQL = monitor.effettuaMonitoraggio("4", "40", "400", "TempoRispostaMedio1MUniSomm", dataJoda.toDate());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + risultatoPostgreSQL.isSoddisfatto());
        }catch(DAOException daoe) {
            logger.error("ECCEZIONE: " + daoe);
            fail();
        }catch(INF2Exception inf2e) {
            logger.error("ECCEZIONE: " + inf2e);
            fail();
        }

        /* -- Monitoraggio con MySQL -- */
        logger.info("-----------------------------------------------------------");
        logger.info("----------UNIBAS TEST 4_01 * MONITORAGGIO MySQL --------------");
        logger.info("-----------------------------------------------------------");
        monitor.configuraDataBase("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/freesbee_sla", "root", "mysql");
        try {
            DateTime dataJoda = new DateTime(2008,3,30,0,0,0,0);
            risultatoMySQL = monitor.effettuaMonitoraggio("4", "40", "400", "TempoRispostaMedio1MUniSomm", dataJoda.toDate());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + risultatoMySQL.isSoddisfatto());
        }catch(DAOException daoe) {
            logger.error("ECCEZIONE: " + daoe);
            fail();
        }catch(INF2Exception inf2e) {
            logger.error("ECCEZIONE: " + inf2e);
            fail();
        }   
        logger.debug("Risultato postgres: " + risultatoPostgreSQL.getRisultatoFinale());
        logger.debug("Risultato MySQL: " + risultatoMySQL.getRisultatoFinale());
        Assert.assertEquals( "test_04_01 FALLITO", risultatoPostgreSQL.getRisultatoFinale(), risultatoMySQL.getRisultatoFinale());            
    }
    
    public void test4_02() {
        /* -- Monitoraggio con PostgreSQL --  */ 
        logger.info("-----------------------------------------------------------");
        logger.info("----------UNIBAS TEST 4_02 * MONITORAGGIO POSTGRESQL ---------");
        logger.info("-----------------------------------------------------------");
        monitor.configuraDataBase("org.postgresql.Driver", "jdbc:postgresql:freesbee_sla", "postgres", "postgres");
        try {
            DateTime dataJoda = new DateTime(2008,3,30,0,0,0,0);
            risultatoPostgreSQL = monitor.effettuaMonitoraggio("4", "40", "400", "MassimoTempoRispostaUniSomm", dataJoda.toDate());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + risultatoPostgreSQL.isSoddisfatto());
        }catch(DAOException daoe) {
            logger.error("ECCEZIONE: " + daoe);
            fail();
        }catch(INF2Exception inf2e) {
            logger.error("ECCEZIONE: " + inf2e);
            fail();
        }

        /* -- Monitoraggio con MySQL -- */
        logger.info("-----------------------------------------------------------");
        logger.info("----------UNIBAS TEST 4_02 * MONITORAGGIO MySQL --------------");
        logger.info("-----------------------------------------------------------");
        monitor.configuraDataBase("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/freesbee_sla", "root", "mysql");
        try {
            DateTime dataJoda = new DateTime(2008,3,30,0,0,0,0);
            risultatoMySQL = monitor.effettuaMonitoraggio("4", "40", "400", "MassimoTempoRispostaUniSomm", dataJoda.toDate());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + risultatoMySQL.isSoddisfatto());
        }catch(DAOException daoe) {
            logger.error("ECCEZIONE: " + daoe);
            fail();
        }catch(INF2Exception inf2e) {
            logger.error("ECCEZIONE: " + inf2e);
            fail();
        }   
        logger.debug("Risultato postgres: " + risultatoPostgreSQL.getRisultatoFinale());
        logger.debug("Risultato MySQL: " + risultatoMySQL.getRisultatoFinale());
        Assert.assertEquals( "test_04_02 FALLITO", risultatoPostgreSQL.getRisultatoFinale(), risultatoMySQL.getRisultatoFinale());            
    }

    public void test_4_03() {
        /* -- Monitoraggio con PostgreSQL --  */ 
        logger.info("-----------------------------------------------------------");
        logger.info("----------UNIBAS TEST 4_03 * MONITORAGGIO POSTGRESQL ---------");
        logger.info("-----------------------------------------------------------");
        monitor.configuraDataBase("org.postgresql.Driver", "jdbc:postgresql:freesbee_sla", "postgres", "postgres");
        try {
            DateTime dataJoda = new DateTime(2008,3,30,0,0,0,0);
            risultatoPostgreSQL = monitor.effettuaMonitoraggio("4", "40", "400", "TempoRispostaMedio1MUniUrg", dataJoda.toDate());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + risultatoPostgreSQL.isSoddisfatto());
        }catch(DAOException daoe) {
            logger.error("ECCEZIONE: " + daoe);
            fail();
        }catch(INF2Exception inf2e) {
            logger.error("ECCEZIONE: " + inf2e);
            fail();
        }

        /* -- Monitoraggio con MySQL -- */
        logger.info("-----------------------------------------------------------");
        logger.info("----------UNIBAS TEST 4_03 * MONITORAGGIO MySQL --------------");
        logger.info("-----------------------------------------------------------");
        monitor.configuraDataBase("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/freesbee_sla", "root", "mysql");
        try {
            DateTime dataJoda = new DateTime(2008,3,30,0,0,0,0);
            risultatoMySQL = monitor.effettuaMonitoraggio("4", "40", "400", "TempoRispostaMedio1MUniUrg", dataJoda.toDate());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + risultatoMySQL.isSoddisfatto());
        }catch(DAOException daoe) {
            logger.error("ECCEZIONE: " + daoe);
            fail();
        }catch(INF2Exception inf2e) {
            logger.error("ECCEZIONE: " + inf2e);
            fail();
        }   
        logger.debug("Risultato postgres: " + risultatoPostgreSQL.getRisultatoFinale());
        logger.debug("Risultato MySQL: " + risultatoMySQL.getRisultatoFinale());
        Assert.assertEquals( "test_04_03 FALLITO", risultatoPostgreSQL.getRisultatoFinale(), risultatoMySQL.getRisultatoFinale());            
    }
    
    public void test4_04() {
        /* -- Monitoraggio con PostgreSQL --  */ 
        logger.info("-----------------------------------------------------------");
        logger.info("----------UNIBAS TEST 4_04 * MONITORAGGIO POSTGRESQL ---------");
        logger.info("-----------------------------------------------------------");
        monitor.configuraDataBase("org.postgresql.Driver", "jdbc:postgresql:freesbee_sla", "postgres", "postgres");
        try {
            DateTime dataJoda = new DateTime(2008,3,30,0,0,0,0);
            risultatoPostgreSQL = monitor.effettuaMonitoraggio("4", "40", "400", "MassimoTempoRispostaUniUrg", dataJoda.toDate());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + risultatoPostgreSQL.isSoddisfatto());
        }catch(DAOException daoe) {
            logger.error("ECCEZIONE: " + daoe);
            fail();
        }catch(INF2Exception inf2e) {
            logger.error("ECCEZIONE: " + inf2e);
            fail();
        }

        /* -- Monitoraggio con MySQL -- */
        logger.info("-----------------------------------------------------------");
        logger.info("----------UNIBAS TEST 4_04 * MONITORAGGIO MySQL --------------");
        logger.info("-----------------------------------------------------------");
        monitor.configuraDataBase("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/freesbee_sla", "root", "mysql");
        try {
            DateTime dataJoda = new DateTime(2008,3,30,0,0,0,0);
            risultatoMySQL = monitor.effettuaMonitoraggio("4", "40", "400", "MassimoTempoRispostaUniUrg", dataJoda.toDate());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + risultatoMySQL.isSoddisfatto());
        }catch(DAOException daoe) {
            logger.error("ECCEZIONE: " + daoe);
            fail();
        }catch(INF2Exception inf2e) {
            logger.error("ECCEZIONE: " + inf2e);
            fail();
        }   
        logger.debug("Risultato postgres: " + risultatoPostgreSQL.getRisultatoFinale());
        logger.debug("Risultato MySQL: " + risultatoMySQL.getRisultatoFinale());
        Assert.assertEquals( "test_04_04 FALLITO", risultatoPostgreSQL.getRisultatoFinale(), risultatoMySQL.getRisultatoFinale());            
    }
    
    public void test4_05() {
        /* -- Monitoraggio con PostgreSQL --  */ 
        logger.info("-----------------------------------------------------------");
        logger.info("----------UNIBAS TEST 4_05 * MONITORAGGIO POSTGRESQL ---------");
        logger.info("-----------------------------------------------------------");
        monitor.configuraDataBase("org.postgresql.Driver", "jdbc:postgresql:freesbee_sla", "postgres", "postgres");
        try {
            DateTime dataJoda = new DateTime(2008,3,30,0,0,0,0);
            risultatoPostgreSQL = monitor.effettuaMonitoraggio("4", "40", "400", "TempoRispostaMedio1MUniLav", dataJoda.toDate());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + risultatoPostgreSQL.isSoddisfatto());
        }catch(DAOException daoe) {
            logger.error("ECCEZIONE: " + daoe);
            fail();
        }catch(INF2Exception inf2e) {
            logger.error("ECCEZIONE: " + inf2e);
            fail();
        }

        /* -- Monitoraggio con MySQL -- */
        logger.info("-----------------------------------------------------------");
        logger.info("----------UNIBAS TEST 4_05 * MONITORAGGIO MySQL --------------");
        logger.info("-----------------------------------------------------------");
        monitor.configuraDataBase("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/freesbee_sla", "root", "mysql");
        try {
            DateTime dataJoda = new DateTime(2008,3,30,0,0,0,0);
            risultatoMySQL = monitor.effettuaMonitoraggio("4", "40", "400", "TempoRispostaMedio1MUniLav", dataJoda.toDate());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + risultatoMySQL.isSoddisfatto());
        }catch(DAOException daoe) {
            logger.error("ECCEZIONE: " + daoe);
            fail();
        }catch(INF2Exception inf2e) {
            logger.error("ECCEZIONE: " + inf2e);
            fail();
        }   
        logger.debug("Risultato postgres: " + risultatoPostgreSQL.getRisultatoFinale());
        logger.debug("Risultato MySQL: " + risultatoMySQL.getRisultatoFinale());
        Assert.assertEquals( "test_04_05 FALLITO", risultatoPostgreSQL.getRisultatoFinale(), risultatoMySQL.getRisultatoFinale());            
    }
    
    public void test4_06() {
        /* -- Monitoraggio con PostgreSQL --  */ 
        logger.info("-----------------------------------------------------------");
        logger.info("----------UNIBAS TEST 4_06 * MONITORAGGIO POSTGRESQL ---------");
        logger.info("-----------------------------------------------------------");
        monitor.configuraDataBase("org.postgresql.Driver", "jdbc:postgresql:freesbee_sla", "postgres", "postgres");
        try {
            DateTime dataJoda = new DateTime(2008,3,30,0,0,0,0);
            risultatoPostgreSQL = monitor.effettuaMonitoraggio("4", "40", "400", "MassimoTempoRispostaUniLav", dataJoda.toDate());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + risultatoPostgreSQL.isSoddisfatto());
        }catch(DAOException daoe) {
            logger.error("ECCEZIONE: " + daoe);
            fail();
        }catch(INF2Exception inf2e) {
            logger.error("ECCEZIONE: " + inf2e);
            fail();
        }

        /* -- Monitoraggio con MySQL -- */
        logger.info("-----------------------------------------------------------");
        logger.info("----------UNIBAS TEST 4_06 * MONITORAGGIO MySQL --------------");
        logger.info("-----------------------------------------------------------");
        monitor.configuraDataBase("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/freesbee_sla", "root", "mysql");
        try {
            DateTime dataJoda = new DateTime(2008,3,30,0,0,0,0);
            risultatoMySQL = monitor.effettuaMonitoraggio("4", "40", "400", "MassimoTempoRispostaUniLav", dataJoda.toDate());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + risultatoMySQL.isSoddisfatto());
        }catch(DAOException daoe) {
            logger.error("ECCEZIONE: " + daoe);
            fail();
        }catch(INF2Exception inf2e) {
            logger.error("ECCEZIONE: " + inf2e);
            fail();
        }   
        logger.debug("Risultato postgres: " + risultatoPostgreSQL.getRisultatoFinale());
        logger.debug("Risultato MySQL: " + risultatoMySQL.getRisultatoFinale());
        Assert.assertEquals( "test_04_06 FALLITO", risultatoPostgreSQL.getRisultatoFinale(), risultatoMySQL.getRisultatoFinale());                    
    }
    
    public void test4_07() {
        /* -- Monitoraggio con PostgreSQL --  */ 
        logger.info("-----------------------------------------------------------");
        logger.info("----------UNIBAS TEST 4_07 * MONITORAGGIO POSTGRESQL ---------");
        logger.info("-----------------------------------------------------------");
        monitor.configuraDataBase("org.postgresql.Driver", "jdbc:postgresql:freesbee_sla", "postgres", "postgres");
        try {
            DateTime dataJoda = new DateTime(2008,3,30,0,0,0,0);
            risultatoPostgreSQL = monitor.effettuaMonitoraggio("4", "40", "400", "TempoRispostaMedio1MVarDatori", dataJoda.toDate());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + risultatoPostgreSQL.isSoddisfatto());
        }catch(DAOException daoe) {
            logger.error("ECCEZIONE: " + daoe);
            fail();
        }catch(INF2Exception inf2e) {
            logger.error("ECCEZIONE: " + inf2e);
            fail();
        }

        /* -- Monitoraggio con MySQL -- */
        logger.info("-----------------------------------------------------------");
        logger.info("----------UNIBAS TEST 4_07 * MONITORAGGIO MySQL --------------");
        logger.info("-----------------------------------------------------------");
        monitor.configuraDataBase("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/freesbee_sla", "root", "mysql");
        try {
            DateTime dataJoda = new DateTime(2008,3,30,0,0,0,0);
            risultatoMySQL = monitor.effettuaMonitoraggio("4", "40", "400", "TempoRispostaMedio1MVarDatori", dataJoda.toDate());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + risultatoMySQL.isSoddisfatto());
        }catch(DAOException daoe) {
            logger.error("ECCEZIONE: " + daoe);
            fail();
        }catch(INF2Exception inf2e) {
            logger.error("ECCEZIONE: " + inf2e);
            fail();
        }   
        logger.debug("Risultato postgres: " + risultatoPostgreSQL.getRisultatoFinale());
        logger.debug("Risultato MySQL: " + risultatoMySQL.getRisultatoFinale());
        Assert.assertEquals( "test_04_07 FALLITO", risultatoPostgreSQL.getRisultatoFinale(), risultatoMySQL.getRisultatoFinale());                    
    }
    
    public void test4_08() {
        /* -- Monitoraggio con PostgreSQL --  */ 
        logger.info("-----------------------------------------------------------");
        logger.info("----------UNIBAS TEST 4_08 * MONITORAGGIO POSTGRESQL ---------");
        logger.info("-----------------------------------------------------------");
        monitor.configuraDataBase("org.postgresql.Driver", "jdbc:postgresql:freesbee_sla", "postgres", "postgres");
        try {
            DateTime dataJoda = new DateTime(2008,3,30,0,0,0,0);
            risultatoPostgreSQL = monitor.effettuaMonitoraggio("4", "40", "400", "MassimoTempoRispostaVarDatori", dataJoda.toDate());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + risultatoPostgreSQL.isSoddisfatto());
        }catch(DAOException daoe) {
            logger.error("ECCEZIONE: " + daoe);
            fail();
        }catch(INF2Exception inf2e) {
            logger.error("ECCEZIONE: " + inf2e);
            fail();
        }

        /* -- Monitoraggio con MySQL -- */
        logger.info("-----------------------------------------------------------");
        logger.info("----------UNIBAS TEST 4_08 * MONITORAGGIO MySQL --------------");
        logger.info("-----------------------------------------------------------");
        monitor.configuraDataBase("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/freesbee_sla", "root", "mysql");
        try {
            DateTime dataJoda = new DateTime(2008,3,30,0,0,0,0);
            risultatoMySQL = monitor.effettuaMonitoraggio("4", "40", "400", "MassimoTempoRispostaVarDatori", dataJoda.toDate());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + risultatoMySQL.isSoddisfatto());
        }catch(DAOException daoe) {
            logger.error("ECCEZIONE: " + daoe);
            fail();
        }catch(INF2Exception inf2e) {
            logger.error("ECCEZIONE: " + inf2e);
            fail();
        }   
        logger.debug("Risultato postgres: " + risultatoPostgreSQL.getRisultatoFinale());
        logger.debug("Risultato MySQL: " + risultatoMySQL.getRisultatoFinale());
        Assert.assertEquals( "test_04_08 FALLITO", risultatoPostgreSQL.getRisultatoFinale(), risultatoMySQL.getRisultatoFinale());                    
    }
    
    
    public void test_05() {
        /* -- Monitoraggio con PostgreSQL --  */ 
        logger.info("-----------------------------------------------------------");
        logger.info("----------UNIBAS TEST 5 * MONITORAGGIO POSTGRESQL ---------");
        logger.info("-----------------------------------------------------------");
        monitor.configuraDataBase("org.postgresql.Driver", "jdbc:postgresql:freesbee_sla", "postgres", "postgres");
        try {
            DateTime dataJoda = new DateTime(2008,3,30,0,0,0,0);
            risultatoPostgreSQL = monitor.effettuaMonitoraggio("5", "50", "500", "prova5", dataJoda.toDate());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + risultatoPostgreSQL.isSoddisfatto());
        }catch(DAOException daoe) {
            logger.error("ECCEZIONE: " + daoe);
            fail();
        }catch(INF2Exception inf2e) {
            logger.error("ECCEZIONE: " + inf2e);
            fail();
        }

        /* -- Monitoraggio con MySQL -- */
        logger.info("-----------------------------------------------------------");
        logger.info("----------UNIBAS TEST 5 * MONITORAGGIO MySQL --------------");
        logger.info("-----------------------------------------------------------");
        monitor.configuraDataBase("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/freesbee_sla", "root", "mysql");
        try {
            DateTime dataJoda = new DateTime(2008,3,30,0,0,0,0);
            risultatoMySQL = monitor.effettuaMonitoraggio("5", "50", "500", "prova5", dataJoda.toDate());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + risultatoMySQL.isSoddisfatto());
        }catch(DAOException daoe) {
            logger.error("ECCEZIONE: " + daoe);
            fail();
        }catch(INF2Exception inf2e) {
            logger.error("ECCEZIONE: " + inf2e);
            fail();
        }   
        logger.debug("Risultato postgres: " + risultatoPostgreSQL.getRisultatoFinale());
        logger.debug("Risultato MySQL: " + risultatoMySQL.getRisultatoFinale());
        Assert.assertEquals( "test_05 FALLITO", risultatoPostgreSQL.getRisultatoFinale(), risultatoMySQL.getRisultatoFinale());        
    }
    
    public void test_06() {
        /* -- Monitoraggio con PostgreSQL --  */ 
        logger.info("-----------------------------------------------------------");
        logger.info("----------UNIBAS TEST 6 * MONITORAGGIO POSTGRESQL ---------");
        logger.info("-----------------------------------------------------------");
        monitor.configuraDataBase("org.postgresql.Driver", "jdbc:postgresql:freesbee_sla", "postgres", "postgres");
        try {
            DateTime dataJoda = new DateTime(2008,3,30,0,0,0,0);
            risultatoPostgreSQL = monitor.effettuaMonitoraggio("6", "60", "600", "prova6", dataJoda.toDate());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + risultatoPostgreSQL.isSoddisfatto());
        }catch(DAOException daoe) {
            logger.error("ECCEZIONE: " + daoe);
            fail();
        }catch(INF2Exception inf2e) {
            logger.error("ECCEZIONE: " + inf2e);
            fail();
        }

        /* -- Monitoraggio con MySQL -- */
        logger.info("-----------------------------------------------------------");
        logger.info("----------UNIBAS TEST 6 * MONITORAGGIO MySQL --------------");
        logger.info("-----------------------------------------------------------");
        monitor.configuraDataBase("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/freesbee_sla", "root", "mysql");
        try {
            DateTime dataJoda = new DateTime(2008,3,30,0,0,0,0);
            risultatoMySQL = monitor.effettuaMonitoraggio("6", "60", "600", "prova6", dataJoda.toDate());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + risultatoMySQL.isSoddisfatto());
        }catch(DAOException daoe) {
            logger.error("ECCEZIONE: " + daoe);
            fail();
        }catch(INF2Exception inf2e) {
            logger.error("ECCEZIONE: " + inf2e);
            fail();
        }   
        logger.debug("Risultato postgres: " + risultatoPostgreSQL.getRisultatoFinale());
        logger.debug("Risultato MySQL: " + risultatoMySQL.getRisultatoFinale());
        Assert.assertEquals( "test_06 FALLITO", risultatoPostgreSQL.getRisultatoFinale(), risultatoMySQL.getRisultatoFinale());
        
    }
    
    public void test_07() {
        /* -- Monitoraggio con PostgreSQL --  */ 
        logger.info("-----------------------------------------------------------");
        logger.info("----------UNIBAS TEST 7 * MONITORAGGIO POSTGRESQL ---------");
        logger.info("-----------------------------------------------------------");
        monitor.configuraDataBase("org.postgresql.Driver", "jdbc:postgresql:freesbee_sla", "postgres", "postgres");
        try {
            DateTime dataJoda = new DateTime(2008,3,30,0,0,0,0);
            risultatoPostgreSQL = monitor.effettuaMonitoraggio("7", "70", "700", "Prova7", dataJoda.toDate());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + risultatoPostgreSQL.isSoddisfatto());
        }catch(DAOException daoe) {
            logger.error("ECCEZIONE: " + daoe);
            fail();
        }catch(INF2Exception inf2e) {
            logger.error("ECCEZIONE: " + inf2e);
            fail();
        }

        /* -- Monitoraggio con MySQL -- */
        logger.info("-----------------------------------------------------------");
        logger.info("----------UNIBAS TEST 7 * MONITORAGGIO MySQL --------------");
        logger.info("-----------------------------------------------------------");
        monitor.configuraDataBase("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/freesbee_sla", "root", "mysql");
        try {
            DateTime dataJoda = new DateTime(2008,3,30,0,0,0,0);
            risultatoMySQL = monitor.effettuaMonitoraggio("7", "70", "700", "Prova7", dataJoda.toDate());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + risultatoMySQL.isSoddisfatto());
        }catch(DAOException daoe) {
            logger.error("ECCEZIONE: " + daoe);
            fail();
        }catch(INF2Exception inf2e) {
            logger.error("ECCEZIONE: " + inf2e);
            fail();
        }   
        logger.debug("Risultato postgres: " + risultatoPostgreSQL.getRisultatoFinale());
        logger.debug("Risultato MySQL: " + risultatoMySQL.getRisultatoFinale());
        Assert.assertEquals( "test_07 FALLITO", risultatoPostgreSQL.getRisultatoFinale(), risultatoMySQL.getRisultatoFinale());        
    }
    
    public void test_08() {
        /* -- Monitoraggio con PostgreSQL --  */ 
        logger.info("-----------------------------------------------------------");
        logger.info("----------UNIBAS TEST 8 * MONITORAGGIO POSTGRESQL ---------");
        logger.info("-----------------------------------------------------------");
        monitor.configuraDataBase("org.postgresql.Driver", "jdbc:postgresql:freesbee_sla", "postgres", "postgres");
        try {
            DateTime dataJoda = new DateTime(2008,3,30,0,0,0,0);
            risultatoPostgreSQL = monitor.effettuaMonitoraggio("8", "80", "800", "VelocitaMediaDatiRestituiti2W", dataJoda.toDate());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + risultatoPostgreSQL.isSoddisfatto());
        }catch(DAOException daoe) {
            logger.error("ECCEZIONE: " + daoe);
            fail();
        }catch(INF2Exception inf2e) {
            logger.error("ECCEZIONE: " + inf2e);
            fail();
        }

        /* -- Monitoraggio con MySQL -- */
        logger.info("-----------------------------------------------------------");
        logger.info("----------UNIBAS TEST 8 * MONITORAGGIO MySQL --------------");
        logger.info("-----------------------------------------------------------");
        monitor.configuraDataBase("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/freesbee_sla", "root", "mysql");
        try {
            DateTime dataJoda = new DateTime(2008,3,30,0,0,0,0);
            risultatoMySQL = monitor.effettuaMonitoraggio("8", "80", "800", "VelocitaMediaDatiRestituiti2W", dataJoda.toDate());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + risultatoMySQL.isSoddisfatto());
        }catch(DAOException daoe) {
            logger.error("ECCEZIONE: " + daoe);
            fail();
        }catch(INF2Exception inf2e) {
            logger.error("ECCEZIONE: " + inf2e);
            fail();
        }   
        logger.debug("Risultato postgres: " + risultatoPostgreSQL.getRisultatoFinale());
        logger.debug("Risultato MySQL: " + risultatoMySQL.getRisultatoFinale());
                boolean isRisultatUguali = false;
        if(risultatoPostgreSQL.getRisultatoFinale() == risultatoMySQL.getRisultatoFinale()){
            isRisultatUguali = true;
        }
        Assert.assertTrue("test_08 FALLITO", isRisultatUguali);    
        
    }
    
    public void test_09() {
        /* -- Monitoraggio con PostgreSQL --  */ 
        logger.info("-----------------------------------------------------------");
        logger.info("----------UNIBAS TEST 9 * MONITORAGGIO POSTGRESQL ---------");
        logger.info("-----------------------------------------------------------");
        monitor.configuraDataBase("org.postgresql.Driver", "jdbc:postgresql:freesbee_sla", "postgres", "postgres");
        try {
            DateTime dataJoda = new DateTime(2008,3,30,0,0,0,0);
            risultatoPostgreSQL = monitor.effettuaMonitoraggio("9", "90", "900", "Prova9", dataJoda.toDate());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + risultatoPostgreSQL.isSoddisfatto());
        }catch(DAOException daoe) {
            logger.error("ECCEZIONE: " + daoe);
        }catch(INF2Exception inf2e) {
            logger.error("ECCEZIONE: " + inf2e);
        }

        /* -- Monitoraggio con MySQL -- */
        logger.info("-----------------------------------------------------------");
        logger.info("----------UNIBAS TEST 9 * MONITORAGGIO MySQL --------------");
        logger.info("-----------------------------------------------------------");
        monitor.configuraDataBase("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/freesbee_sla", "root", "mysql");
        try {
            DateTime dataJoda = new DateTime(2008,3,30,0,0,0,0);
            risultatoMySQL= monitor.effettuaMonitoraggio("9", "90", "900", "Prova9", dataJoda.toDate());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + risultatoMySQL.isSoddisfatto());
        }catch(DAOException daoe) {
            logger.error("ECCEZIONE: " + daoe);
        }catch(INF2Exception inf2e) {
            logger.error("ECCEZIONE: " + inf2e);
        }   
        logger.debug("Risultato postgres: " + risultatoPostgreSQL.getRisultatoFinale());
        logger.debug("Risultato MySQL: " + risultatoMySQL.getRisultatoFinale());
        Assert.assertEquals("test_09 FALLITO", risultatoPostgreSQL.getRisultatoFinale(), risultatoMySQL.getRisultatoFinale());        
    }
    
    public void test_10() {
        /* -- Monitoraggio con PostgreSQL --  */ 
        logger.info("-----------------------------------------------------------");
        logger.info("----------UNIBAS TEST 10 * MONITORAGGIO POSTGRESQL ---------");
        logger.info("-----------------------------------------------------------");
        monitor.configuraDataBase("org.postgresql.Driver", "jdbc:postgresql:freesbee_sla", "postgres", "postgres");
        try {
            DateTime dataJoda = new DateTime(2008,3,30,0,0,0,0);
            risultatoPostgreSQL = monitor.effettuaMonitoraggio("10", "100", "1000", "Prova10", dataJoda.toDate());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + risultatoPostgreSQL.isSoddisfatto());
        }catch(DAOException daoe) {
            logger.error("ECCEZIONE: " + daoe);
        }catch(INF2Exception inf2e) {
            logger.error("ECCEZIONE: " + inf2e);
        }

        /* -- Monitoraggio con MySQL -- */
        logger.info("-----------------------------------------------------------");
        logger.info("----------UNIBAS TEST 10 * MONITORAGGIO MySQL --------------");
        logger.info("-----------------------------------------------------------");
        monitor.configuraDataBase("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/freesbee_sla", "root", "mysql");
        try {
            DateTime dataJoda = new DateTime(2008,3,30,0,0,0,0);
            risultatoMySQL= monitor.effettuaMonitoraggio("10", "100", "1000", "Prova10", dataJoda.toDate());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + risultatoMySQL.isSoddisfatto());
        }catch(DAOException daoe) {
            logger.error("ECCEZIONE: " + daoe);
        }catch(INF2Exception inf2e) {
            logger.error("ECCEZIONE: " + inf2e);
        }   
        logger.debug("Risultato postgres: " + risultatoPostgreSQL.getRisultatoFinale());
        logger.debug("Risultato MySQL: " + risultatoMySQL.getRisultatoFinale());
        Assert.assertEquals("test_010 FALLITO", risultatoPostgreSQL.getRisultatoFinale(), risultatoMySQL.getRisultatoFinale());                
    }
    
    public void test_11() {
        /* -- Monitoraggio con PostgreSQL --  */ 
        logger.info("-----------------------------------------------------------");
        logger.info("----------UNIBAS TEST 11 * MONITORAGGIO POSTGRESQL ---------");
        logger.info("-----------------------------------------------------------");
        monitor.configuraDataBase("org.postgresql.Driver", "jdbc:postgresql:freesbee_sla", "postgres", "postgres");
        try {
            DateTime dataJoda = new DateTime(2008,3,30,0,0,0,0);
            risultatoPostgreSQL = monitor.effettuaMonitoraggio("11", "110", "1100", "Prova11", dataJoda.toDate());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + risultatoPostgreSQL.isSoddisfatto());
        }catch(DAOException daoe) {
            logger.error("ECCEZIONE: " + daoe);
        }catch(INF2Exception inf2e) {
            logger.error("ECCEZIONE: " + inf2e);
        }

        /* -- Monitoraggio con MySQL -- */
        logger.info("-----------------------------------------------------------");
        logger.info("----------UNIBAS TEST 11 * MONITORAGGIO MySQL --------------");
        logger.info("-----------------------------------------------------------");
        monitor.configuraDataBase("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/freesbee_sla", "root", "mysql");
        try {
            DateTime dataJoda = new DateTime(2008,3,30,0,0,0,0);
            risultatoMySQL= monitor.effettuaMonitoraggio("11", "110", "1100", "Prova11", dataJoda.toDate());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + risultatoMySQL.isSoddisfatto());
        }catch(DAOException daoe) {
            logger.error("ECCEZIONE: " + daoe);
        }catch(INF2Exception inf2e) {
            logger.error("ECCEZIONE: " + inf2e);
        }   
        logger.debug("Risultato postgres: " + risultatoPostgreSQL.getRisultatoFinale());
        logger.debug("Risultato MySQL: " + risultatoMySQL.getRisultatoFinale());
        Assert.assertEquals("test_011 FALLITO", risultatoPostgreSQL.getRisultatoFinale(), risultatoMySQL.getRisultatoFinale());                
    }
    
    public void test_12() {
        /* -- Monitoraggio con PostgreSQL --  */ 
        logger.info("-----------------------------------------------------------");
        logger.info("----------UNIBAS TEST 12 * MONITORAGGIO POSTGRESQL ---------");
        logger.info("-----------------------------------------------------------");
        monitor.configuraDataBase("org.postgresql.Driver", "jdbc:postgresql:freesbee_sla", "postgres", "postgres");
        try {
            DateTime dataJoda = new DateTime(2008,3,30,0,0,0,0);
            risultatoPostgreSQL = monitor.effettuaMonitoraggio("12", "120", "1200", "Prova12", dataJoda.toDate());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + risultatoPostgreSQL.isSoddisfatto());
        }catch(DAOException daoe) {
            logger.error("ECCEZIONE: " + daoe);
        }catch(INF2Exception inf2e) {
            logger.error("ECCEZIONE: " + inf2e);
        }

        /* -- Monitoraggio con MySQL -- */
        logger.info("-----------------------------------------------------------");
        logger.info("----------UNIBAS TEST 12 * MONITORAGGIO MySQL --------------");
        logger.info("-----------------------------------------------------------");
        monitor.configuraDataBase("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/freesbee_sla", "root", "mysql");
        try {
            DateTime dataJoda = new DateTime(2008,3,30,0,0,0,0);
            risultatoMySQL= monitor.effettuaMonitoraggio("12", "120", "1200", "Prova12", dataJoda.toDate());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + risultatoMySQL.isSoddisfatto());
        }catch(DAOException daoe) {
            logger.error("ECCEZIONE: " + daoe);
        }catch(INF2Exception inf2e) {
            logger.error("ECCEZIONE: " + inf2e);
        }   
        logger.debug("Risultato postgres: " + risultatoPostgreSQL.getRisultatoFinale());
        logger.debug("Risultato MySQL: " + risultatoMySQL.getRisultatoFinale());
        Assert.assertEquals("test_012 FALLITO", risultatoPostgreSQL.getRisultatoFinale(), risultatoMySQL.getRisultatoFinale());                        
    }
    
    public void test_13() {
        /* -- Monitoraggio con PostgreSQL --  */ 
        logger.info("-----------------------------------------------------------");
        logger.info("----------UNIBAS TEST 13 * MONITORAGGIO POSTGRESQL ---------");
        logger.info("-----------------------------------------------------------");
        monitor.configuraDataBase("org.postgresql.Driver", "jdbc:postgresql:freesbee_sla", "postgres", "postgres");
        try {
            DateTime dataJoda = new DateTime(2008,3,30,0,0,0,0);
            risultatoPostgreSQL = monitor.effettuaMonitoraggio("13", "130", "1300", "Prova13", dataJoda.toDate());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + risultatoPostgreSQL.isSoddisfatto());
        }catch(DAOException daoe) {
            logger.error("ECCEZIONE: " + daoe);
        }catch(INF2Exception inf2e) {
            logger.error("ECCEZIONE: " + inf2e);
        }

        /* -- Monitoraggio con MySQL -- */
        logger.info("-----------------------------------------------------------");
        logger.info("----------UNIBAS TEST 13 * MONITORAGGIO MySQL --------------");
        logger.info("-----------------------------------------------------------");
        monitor.configuraDataBase("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/freesbee_sla", "root", "mysql");
        try {
            DateTime dataJoda = new DateTime(2008,3,30,0,0,0,0);
            risultatoMySQL= monitor.effettuaMonitoraggio("13", "130", "1300", "Prova13", dataJoda.toDate());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + risultatoMySQL.isSoddisfatto());
        }catch(DAOException daoe) {
            logger.error("ECCEZIONE: " + daoe);
        }catch(INF2Exception inf2e) {
            logger.error("ECCEZIONE: " + inf2e);
        }   
        logger.debug("Risultato postgres: " + risultatoPostgreSQL.getRisultatoFinale());
        logger.debug("Risultato MySQL: " + risultatoMySQL.getRisultatoFinale());
        Assert.assertEquals("test_013 FALLITO", risultatoPostgreSQL.getRisultatoFinale(), risultatoMySQL.getRisultatoFinale());           
    }
    
    public void test_14(){
        /* -- Monitoraggio con PostgreSQL --  */ 
        logger.info("-----------------------------------------------------------");
        logger.info("---------- UNIBAS TEST 14 * MONITORAGGIO POSTGRESQL --------");
        logger.info("-----------------------------------------------------------");
        monitor.configuraDataBase("org.postgresql.Driver", "jdbc:postgresql:freesbee_sla", "postgres", "postgres");
        try {
            //DateTime dataJoda = new DateTime(2008,3,30,0,0,0,0);
            //risultatoPostgreSQL = monitor.effettuaMonitoraggio("1", "10", "100", "MaxTempoRisposta5", dataJoda.toDate());
            Calendar data = new GregorianCalendar(2008,2,30);
            risultatoPostgreSQL = monitor.effettuaMonitoraggio("1", "10", "100", "MaxTempoRisposta5", data.getTime());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + risultatoPostgreSQL.isSoddisfatto());
        }catch(DAOException daoe) {
            logger.error("ECCEZIONE: " + daoe);
            fail();
        }catch(INF2Exception inf2e) {
            logger.error("ECCEZIONE: " + inf2e);
            fail();
        }
        
        /* -- Monitoraggio con MySQL -- */
        logger.info("-----------------------------------------------------------");
        logger.info("---------- UNIBAS TEST 14 * MONITORAGGIO Postgres (freESBee) -------------");
        logger.info("-----------------------------------------------------------");
        monitor.configuraDataBase("org.postgresql.Driver", "jdbc:postgresql:freesbee_sla", "freesbee", "**freesbee**");
        try {
            DateTime dataJoda = new DateTime(2008,3,30,0,0,0,0);
             risultatoMySQL = monitor.effettuaMonitoraggio("1", "10", "100", "MaxTempoRisposta5", dataJoda.toDate());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + risultatoMySQL.isSoddisfatto());
        }catch(DAOException daoe) {
            logger.error("ECCEZIONE: " + daoe);
            fail();
        }catch(INF2Exception inf2e) {
            logger.error("ECCEZIONE: " + inf2e);
            fail();
        }          
        logger.debug("Risultato postgres: " + risultatoPostgreSQL.getRisultatoFinale());
        logger.debug("Risultato MySQL: " + risultatoMySQL.getRisultatoFinale());
        Assert.assertEquals( "test_14 FALLITO", risultatoPostgreSQL.getRisultatoFinale(), risultatoMySQL.getRisultatoFinale());        
    }
    
    
        public void test_15_Nunzia(){
        /* -- Monitoraggio con PostgreSQL --  */ 
        System.out.println("-----------------------------------------------------------");
        System.out.println("---------- UNIBAS TEST 15 * MONITORAGGIO POSTGRESQL --------");
        System.out.println("-----------------------------------------------------------");
        monitor.configuraDataBase("org.postgresql.Driver", "jdbc:postgresql:freesbee_sla", "postgres", "postgres");
        try {
            Calendar data = new GregorianCalendar(2008,3,30);
            risultatoPostgreSQL = monitor.effettuaMonitoraggio("14", "140", "1400", "TempoMedioCorretto5_10", data.getTime());
            System.out.println("Esito monitoraggio  :  SODDISFATTO = " + risultatoPostgreSQL.isSoddisfatto());
        }catch(DAOException daoe) {
            System.out.println("ECCEZIONE: " + daoe);
            fail();
        }catch(INF2Exception inf2e) {
            System.out.println("ECCEZIONE: " + inf2e);
            fail();
        }
        assertFalse("test_15 FALLITO", risultatoPostgreSQL.isSoddisfatto()); 
    }
        
}
