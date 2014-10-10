package it.unibas.freesbeesla.test.tracciatura;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import it.unibas.freesbee.client.cxf.sistematracciatura.ResultType;
import it.unibas.freesbee.client.cxf.sistematracciatura.SOAPFault_Exception;
import it.unibas.freesbee.client.cxf.sistematracciatura.ServiceTermStateType;
import it.unibas.freesbee.client.cxf.sistematracciatura.TraceSystemPortTypeClient;
import it.unibas.freesbeesla.ConfigurazioneStatico;
import it.unibas.freesbeesla.test.persistenza.DAOTest;
import it.unibas.freesbeesla.tracciatura.modello.SlaObjectTrace;
import it.unibas.freesbeesla.ws.web.persistenza.soap.DAOException;
import it.unibas.freesbeesla.ws.web.persistenza.torque.DAOSlaObjectTraceTorque;
import java.net.URL;
import javax.xml.datatype.XMLGregorianCalendar;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.torque.TorqueException;

public class TestTracciatura extends TestCase {

    protected Log logger = LogFactory.getLog(this.getClass());
    protected TraceSystemPortTypeClient port;
    protected ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();
    protected static boolean insert = false;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        URL wsdlURL = null;
        wsdlURL = new URL("http://" + conf.getWebservicesIndirizzo() + ":" + DAOTest.PORT + "/" + DAOTest.URL_WSDL_TRACCIATURA);
        logger.debug(wsdlURL);
        port = new TraceSystemPortTypeClient(wsdlURL);
        this.inserisci();
    }

    public void testUpdate() {
        ResultType response = null;
        try {
            response = port.updateServiceTermState(DAOTest.ICAR_SINCRONO_LOOPBACK, DAOTest.FRUITORE_UNICAR, DAOTest.EROGATORE_UNICAR, ServiceTermStateType.READY_PROCESSING);
            logger.debug("Richiesta:  \n " + DAOTest.ICAR_SINCRONO_LOOPBACK + " \n " + DAOTest.FRUITORE_UNICAR + " \n " + DAOTest.EROGATORE_UNICAR + " \n " + ServiceTermStateType.READY_PROCESSING);
            logger.debug("Risposta: \n " + response.getCode() + "\n " + response.getDescription());
            assertEquals("001", response.getCode());

            response = port.updateServiceTermState(DAOTest.ICAR_SINCRONO_LOOPBACK, DAOTest.FRUITORE_UNICAR, DAOTest.EROGATORE_UNICAR, ServiceTermStateType.READY_PROCESSING);
            logger.debug("Richiesta:  \n " + DAOTest.ICAR_SINCRONO_LOOPBACK + " \n " + DAOTest.FRUITORE_UNICAR + " \n " + DAOTest.EROGATORE_UNICAR + " \n " + ServiceTermStateType.READY_PROCESSING);
            logger.debug("Risposta: \n " + response.getCode() + "\n " + response.getDescription());
            assertEquals(response.getCode(), "002");


            response = port.updateServiceTermState(DAOTest.ICAR_SINCRONO_LOOPBACK, DAOTest.FRUITORE_UNICAR, DAOTest.EROGATORE_UNICAR, ServiceTermStateType.READY_IDLE);
            logger.debug("Richiesta:  \n " + DAOTest.ICAR_SINCRONO_LOOPBACK + " \n " + DAOTest.FRUITORE_UNICAR + " \n " + DAOTest.EROGATORE_UNICAR + " \n " + ServiceTermStateType.READY_IDLE);
            logger.debug("Risposta: \n " + response.getCode() + "\n " + response.getDescription());
            assertEquals(response.getCode(), "003");

            response = port.updateServiceTermState(DAOTest.ICAR_SINCRONO_LOOPBACK, DAOTest.FRUITORE_UNICAR, DAOTest.EROGATORE_UNICAR, ServiceTermStateType.READY_IDLE);
            logger.debug("Richiesta:  \n " + DAOTest.ICAR_SINCRONO_LOOPBACK + " \n " + DAOTest.FRUITORE_UNICAR + " \n " + DAOTest.EROGATORE_UNICAR + " \n " + ServiceTermStateType.READY_IDLE);
            logger.debug("Risposta: \n " + response.getCode() + "\n " + response.getDescription());
            assertEquals(response.getCode(), "004");

        } catch (SOAPFault_Exception se) {
            logger.error("Error code: " + se.getMessage());
            Assert.fail();
        }

        try {
            response = port.updateServiceTermState(DAOTest.ICAR_SINCRONO_LOOPBACK, DAOTest.FRUITORE_UNICAR, DAOTest.EROGATORE_UNICAR, ServiceTermStateType.COMPLETED);
            logger.debug("Richiesta:  \n " + DAOTest.ICAR_SINCRONO_LOOPBACK + " \n " + DAOTest.FRUITORE_UNICAR + " \n " + DAOTest.EROGATORE_UNICAR + " \n " + ServiceTermStateType.COMPLETED);
            logger.debug("Risposta: \n " + response.getCode() + "\n " + response.getDescription());
            Assert.fail();
        } catch (SOAPFault_Exception sf) {
            assertEquals(sf.getFaultInfo().getCode(), "101");
        }

        try {
            response = port.updateServiceTermState(DAOTest.ICAR_SINCRONO_LOOPBACK, DAOTest.FRUITORE_UNICAR, DAOTest.EROGATORE_UNICAR, ServiceTermStateType.NOT_READY);
            logger.debug("Richiesta:  \n " + DAOTest.ICAR_SINCRONO_LOOPBACK + " \n " + DAOTest.FRUITORE_UNICAR + " \n " + DAOTest.EROGATORE_UNICAR + " \n " + ServiceTermStateType.NOT_READY);
            logger.debug("Risposta: \n " + response.getCode() + "\n " + response.getDescription());
            Assert.fail();
        } catch (SOAPFault_Exception sf) {
            assertEquals(sf.getFaultInfo().getCode(), "101");
        }
    }

    public void testInsert() {
        try {
            XMLGregorianCalendar data = new XMLGregorianCalendarImpl();
            data.setYear(2008);
            data.setMonth(4);
            data.setDay(28);
            data.setHour(20);
            data.setMinute(00);
            data.setSecond(18);
            ResultType response = port.insertServiceBasicMetric(DAOTest.ICAR_SINCRONO_LOOPBACK, DAOTest.FRUITORE_UNICAR, DAOTest.EROGATORE_UNICAR, "tempoRisposta", new Double(101), data);
            logger.debug("Richiesta:  \n " + DAOTest.ICAR_SINCRONO_LOOPBACK + " \n " + DAOTest.FRUITORE_UNICAR + " \n " + DAOTest.EROGATORE_UNICAR);
            logger.debug("Risposta: \n " + response.getCode() + "\n " + response.getDescription());
            DAOSlaObjectTraceTorque daoSLA = new DAOSlaObjectTraceTorque();
            SlaObjectTrace slaObjectTrace = daoSLA.doSelect(DAOTest.ICAR_SINCRONO_LOOPBACK, DAOTest.FRUITORE_UNICAR, DAOTest.EROGATORE_UNICAR, "tempoRisposta", new Double(101), data);
            assertNotNull(slaObjectTrace);
        } catch (Exception ex) {
            logger.error("Error code: " + ex);
            Assert.fail();
        }
    }

    private void inserisci() throws DAOException {
        try {
            logger.debug("insert " + TestTracciatura.insert);
            if (!TestTracciatura.insert) {
                DAOTest.inserisciSoggetti();
                DAOTest.inserisciDatiTestTracciatua();
                TestTracciatura.insert = true;
            } else {
                TestTracciatura.insert = false;
            }
        } catch (Exception e) {
            logger.error(e);
            TestTracciatura.insert = true;
            throw new DAOException(e);
        }
    }

    @Override
    public void tearDown() throws DAOException, TorqueException {
        if (!TestTracciatura.insert) {
            DAOTest.ripulisciTabelle();
        }
    }
}



