package it.unibas.freesbeesla.test.monitoraggio;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import it.unibas.freesbee.client.cxf.sistemamonitoraggio.GuaranteeStateType;
import it.unibas.freesbee.client.cxf.sistemamonitoraggio.GuaranteeTermObj;
import it.unibas.freesbee.client.cxf.sistemamonitoraggio.ResponseServiceGuaranteeTermStateTypeSuper;
import it.unibas.freesbee.client.cxf.sistemamonitoraggio.SOAPFault_Exception;
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

public class Test1Monitoraggio extends TestCase {

    protected Log logger = LogFactory.getLog(this.getClass());
    protected ServiceGuaranteeTermStateClient port = null;
    protected ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        URL wsdlURL = null;
        wsdlURL = new URL("http://" + conf.getWebservicesIndirizzo() + ":" + DAOTest.PORT + "/" + DAOTest.URL_WSDL_MONITORAGGIO_SLA);
        logger.info(wsdlURL);
        port = new ServiceGuaranteeTermStateClient(wsdlURL);
        DAOTest.inserisciSoggetti();
        DAOTest.inserisciDatiTest1();
    }

    public void test() {
        try {
            GuaranteeTermObj gto = new GuaranteeTermObj();
            XMLGregorianCalendar data = new XMLGregorianCalendarImpl();
            List<GuaranteeTermObj> listaSla = new ArrayList<GuaranteeTermObj>();
            ResponseServiceGuaranteeTermStateTypeSuper response = null;
            String stato = "";

            data.setYear(2008);
            data.setMonth(4);
            data.setDay(28);
            data.setHour(20);
            data.setMinute(00);
            data.setSecond(18);
            gto.setDateFn(data);
            gto.setGuaranteeTermName("TempoRispostaMedio1M");
            listaSla.add(gto);

            gto = new GuaranteeTermObj();
            data = new XMLGregorianCalendarImpl();
            data.setYear(2008);
            data.setMonth(4);
            data.setDay(1);
            data.setHour(20);
            data.setMinute(00);
            data.setSecond(18);
            gto.setDateFn(data);
            gto.setGuaranteeTermName("TempoRispostaMedio1M");
            listaSla.add(gto);

            gto = new GuaranteeTermObj();
            data = new XMLGregorianCalendarImpl();
            data.setYear(2008);
            data.setMonth(4);
            data.setDay(20);
            data.setHour(20);
            data.setMinute(00);
            data.setSecond(18);
            gto.setDateFn(data);
            gto.setGuaranteeTermName("MaxTempoRisposta5");
            listaSla.add(gto);


            response = port.getServiceGuaranteeTermState(DAOTest.ICAR_SINCRONO_LOOPBACK, DAOTest.FRUITORE_UNICAR, DAOTest.EROGATORE_UNICAR, listaSla);


            stato = response.getResultGaranteeTermObjStateSuper().get(0).getGuaranteeState().value();
            logger.debug("\n\n");
            logger.debug("Richiesta:  \n " + DAOTest.ICAR_SINCRONO_LOOPBACK + " \n " + DAOTest.FRUITORE_UNICAR + " \n " + DAOTest.EROGATORE_UNICAR);
            logger.debug("Esito monitoraggio  :  SLA = " + response.getResultGaranteeTermObjStateSuper().get(0).getGuaranteeTermName());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + stato);
            logger.debug("Esito monitoraggio  :  TRESHOLD = " + response.getResultGaranteeTermObjStateSuper().get(0).getGuaranteeTermsResult().getThresholdOperator());
            logger.debug("Esito monitoraggio  :  Valore = " + response.getResultGaranteeTermObjStateSuper().get(0).getGuaranteeTermsResult().getThresholdValue());
            logger.debug("Esito monitoraggio  :  Risulstato = " + response.getResultGaranteeTermObjStateSuper().get(0).getGuaranteeTermsResult().getRisultatoFinale());
            logger.debug("\n\n");
            assertTrue(stato.equalsIgnoreCase(GuaranteeStateType.FUL_FILLED.value()));
            assertEquals(514.625, response.getResultGaranteeTermObjStateSuper().get(0).getGuaranteeTermsResult().getRisultatoFinale());

            stato = response.getResultGaranteeTermObjStateSuper().get(1).getGuaranteeState().value();
            logger.debug("\n\n");
            logger.debug("Richiesta:  \n " + DAOTest.ICAR_SINCRONO_LOOPBACK + " \n " + DAOTest.FRUITORE_UNICAR + " \n " + DAOTest.EROGATORE_UNICAR);
            logger.debug("Esito monitoraggio  :  SLA = " + response.getResultGaranteeTermObjStateSuper().get(1).getGuaranteeTermName());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + stato);
            logger.debug("Esito monitoraggio  :  TRESHOLD = " + response.getResultGaranteeTermObjStateSuper().get(1).getGuaranteeTermsResult().getThresholdOperator());
            logger.debug("Esito monitoraggio  :  Valore = " + response.getResultGaranteeTermObjStateSuper().get(1).getGuaranteeTermsResult().getThresholdValue());
            logger.debug("Esito monitoraggio  :  Risulstato = " + response.getResultGaranteeTermObjStateSuper().get(1).getGuaranteeTermsResult().getRisultatoFinale());
            logger.debug("\n\n");
            assertTrue(stato.equalsIgnoreCase(GuaranteeStateType.NOT_DETERMINED.value()));

            stato = response.getResultGaranteeTermObjStateSuper().get(2).getGuaranteeState().value();
            logger.debug("\n\n");
            logger.debug("Richiesta:  \n " + DAOTest.ICAR_SINCRONO_LOOPBACK + " \n " + DAOTest.FRUITORE_UNICAR + " \n " + DAOTest.EROGATORE_UNICAR);
            logger.debug("Esito monitoraggio  :  SLA = " + response.getResultGaranteeTermObjStateSuper().get(2).getGuaranteeTermName());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + stato);
            logger.debug("Esito monitoraggio  :  TRESHOLD = " + response.getResultGaranteeTermObjStateSuper().get(2).getGuaranteeTermsResult().getThresholdOperator());
            logger.debug("Esito monitoraggio  :  Valore = " + response.getResultGaranteeTermObjStateSuper().get(2).getGuaranteeTermsResult().getThresholdValue());
            logger.debug("Esito monitoraggio  :  Risulstato = " + response.getResultGaranteeTermObjStateSuper().get(2).getGuaranteeTermsResult().getRisultatoFinale());
            logger.debug("\n\n");
            assertTrue(stato.equalsIgnoreCase(GuaranteeStateType.VIOLATED.value()));
            assertEquals(829.0, response.getResultGaranteeTermObjStateSuper().get(2).getGuaranteeTermsResult().getRisultatoFinale());


            gto = new GuaranteeTermObj();
            data = new XMLGregorianCalendarImpl();
            listaSla = new ArrayList<GuaranteeTermObj>();
            response = null;

            data.setYear(2008);
            data.setMonth(4);
            data.setDay(19);
            data.setHour(20);
            data.setMinute(00);
            data.setSecond(18);
            gto.setDateFn(data);
            gto.setGuaranteeTermName("MaxTempoRisposta5");
            listaSla.add(gto);

            response = port.getServiceGuaranteeTermState(DAOTest.ICAR_SINCRONO_LOOPBACK_UNIBAS, DAOTest.FRUITORE_UNIBAS, DAOTest.EROGATORE_UNIBAS, listaSla);
            stato = response.getResultGaranteeTermObjStateSuper().get(0).getGuaranteeState().value();
            logger.debug("\n\n");
            logger.debug("Richiesta:  \n " + DAOTest.ICAR_SINCRONO_LOOPBACK_UNIBAS + " \n " + DAOTest.FRUITORE_UNIBAS + " \n " + DAOTest.EROGATORE_UNIBAS);
            logger.debug("Esito monitoraggio  :  SLA = " + response.getResultGaranteeTermObjStateSuper().get(0).getGuaranteeTermName());
            logger.debug("Esito monitoraggio  :  SODDISFATTO = " + stato);
            logger.debug("Esito monitoraggio  :  TRESHOLD = " + response.getResultGaranteeTermObjStateSuper().get(0).getGuaranteeTermsResult().getThresholdOperator());
            logger.debug("Esito monitoraggio  :  Valore = " + response.getResultGaranteeTermObjStateSuper().get(0).getGuaranteeTermsResult().getThresholdValue());
            logger.debug("Esito monitoraggio  :  Risulstato = " + response.getResultGaranteeTermObjStateSuper().get(0).getGuaranteeTermsResult().getRisultatoFinale());
            logger.debug("\n\n");

            assertTrue(stato.equalsIgnoreCase(GuaranteeStateType.VIOLATED.value()));
            assertEquals(1900.0, response.getResultGaranteeTermObjStateSuper().get(0).getGuaranteeTermsResult().getRisultatoFinale());

        } catch (SOAPFault_Exception ex) {
            logger.error("Error code: " + ex);
            Assert.fail();
        }
    }

    @Override
    public void tearDown() throws DAOException, TorqueException {
        DAOTest.ripulisciTabelle();
    }
}



