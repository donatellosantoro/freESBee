package it.unibas.freesbeesla.test.monitoraggio;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import it.unibas.freesbee.client.cxf.sistemamonitoraggio.GuaranteeStateType;
import it.unibas.freesbee.client.cxf.sistemamonitoraggio.GuaranteeTermObj;
import it.unibas.freesbee.client.cxf.sistemamonitoraggio.ResponseServiceGuaranteeTermStateTypeSuper;
import it.unibas.freesbee.client.cxf.sistemamonitoraggio.ServiceGuaranteeTermStateClient;
import it.unibas.freesbeesla.ConfigurazioneStatico;
import it.unibas.freesbeesla.test.persistenza.DAOTest;
import it.unibas.freesbeesla.ws.web.persistenza.soap.DAOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.torque.TorqueException;

public class Test2Monitoraggio extends TestCase {

    protected Log logger = LogFactory.getLog(this.getClass());
    protected ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();
    protected ServiceGuaranteeTermStateClient port = null;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        URL wsdlURL = null;
        wsdlURL = new URL("http://" + conf.getWebservicesIndirizzo() + ":" + DAOTest.PORT + "/" + DAOTest.URL_WSDL_MONITORAGGIO_SLA);
//        wsdlURL = new URL("http://" + conf.getWebservicesIndirizzo() + ":" + DAOTest.PORT + "/" + "freesbee-Sla/ws/monitoraggio/guaranteetermstate/ServiceGuaranteeTermStateFreESBeee?wsdl");
        logger.info(wsdlURL);
        port = new ServiceGuaranteeTermStateClient(wsdlURL);
        DAOTest.inserisciSoggetti();
        DAOTest.inserisciDatiTest2();
    }

    public void test() {
        try {
            GuaranteeTermObj gto = new GuaranteeTermObj();
            XMLGregorianCalendar data = new XMLGregorianCalendarImpl(); // Gennaio = 1
            // 30 Marzo 2008
            data.setYear(2008);
            data.setMonth(3);
            data.setDay(30);
            gto.setDateFn(data);
            gto.setGuaranteeTermName("TempoMedioCorretto5_10");
            List<GuaranteeTermObj> listaSla = new ArrayList<GuaranteeTermObj>();
            listaSla.add(gto);
            ResponseServiceGuaranteeTermStateTypeSuper response = port.getServiceGuaranteeTermState(DAOTest.ICAR_SINCRONO_LOOPBACK_UNIBAS, DAOTest.FRUITORE_UNIBAS, DAOTest.EROGATORE_UNIBAS, listaSla);
            String stato = response.getResultGaranteeTermObjStateSuper().get(0).getGuaranteeState().value();
            logger.debug("Richiesta:  \n " + DAOTest.ICAR_SINCRONO_LOOPBACK_UNIBAS + " \n " + DAOTest.FRUITORE_UNIBAS + " \n " + DAOTest.EROGATORE_UNIBAS);
            logger.debug("Risposta: \n " + stato);
            assertTrue(stato.equalsIgnoreCase(GuaranteeStateType.VIOLATED.value()));
        } catch (Exception ex) {
            logger.error("Error code: " + ex);
            Assert.fail();
        }
    }

    @Override
    public void tearDown() throws DAOException, TorqueException {
        DAOTest.ripulisciTabelle();
    }
}



