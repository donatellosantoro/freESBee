package it.unibas.freesbeesla.test.monitoraggio;

import it.unibas.freesbee.client.cxf.sistemamonitoraggio.ResponseServiceTermStateTypeSuper;
import it.unibas.freesbee.client.cxf.sistemamonitoraggio.SOAPFault_Exception;
import it.unibas.freesbee.client.cxf.sistemamonitoraggio.ServiceTermStateClient;
import it.unibas.freesbee.client.cxf.sistemamonitoraggio.ServiceTermStateType;
import it.unibas.freesbeesla.ConfigurazioneStatico;
import it.unibas.freesbeesla.test.persistenza.DAOTest;
import it.unibas.freesbeesla.ws.web.persistenza.soap.DAOException;
import java.net.URL;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.torque.TorqueException;

public class Test0Monitoraggio extends TestCase {

    protected Log logger = LogFactory.getLog(this.getClass());
    protected ServiceTermStateClient port = null;
    protected ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        URL wsdlURL = null;
        wsdlURL = new URL("http://" + conf.getWebservicesIndirizzo() + ":" + DAOTest.PORT + "/" + DAOTest.URL_WSDL_MONITORAGGIO_STATO);
        logger.info(wsdlURL);
        port = new ServiceTermStateClient(wsdlURL);
        DAOTest.inserisciSoggetti();
        DAOTest.inserisciDatiTestTracciatua();
    }

    public void test() {
        try {

            ResponseServiceTermStateTypeSuper response = port.getServiceTermState(DAOTest.ICAR_SINCRONO_LOOPBACK, DAOTest.FRUITORE_UNICAR, DAOTest.EROGATORE_UNICAR);
            String stato = response.getServiceTermState().value();
            logger.debug("Richiesta:  \n " + DAOTest.ICAR_SINCRONO_LOOPBACK + " \n " + DAOTest.FRUITORE_UNICAR + " \n " + DAOTest.EROGATORE_UNICAR);
            logger.debug("Risposta: \n " + stato);
            assertTrue(stato.equalsIgnoreCase(ServiceTermStateType.READY_IDLE.value()));
            assertEquals(0, response.getCount());


        } catch (SOAPFault_Exception ex) {
            logger.error("Error code: " + ex);
            ex.printStackTrace();
            Assert.fail();
        }
    }

    @Override
    public void tearDown() throws DAOException, TorqueException {
        DAOTest.ripulisciTabelle();
    }
}



