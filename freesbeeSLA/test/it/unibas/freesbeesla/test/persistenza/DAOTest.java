package it.unibas.freesbeesla.test.persistenza;

import it.unibas.freesbeesla.tracciatura.modello.Service;
import it.unibas.freesbeesla.tracciatura.modello.SlaObjectTrace;
import it.unibas.freesbeesla.tracciatura.modello.SoggettoSLA;
import it.unibas.freesbeesla.tracciatura.ws.server.stub.ServiceTermStateType;
import it.unibas.freesbeesla.ws.web.persistenza.soap.DAOException;
import it.unibas.freesbeesla.ws.web.persistenza.torque.DAOServizioTorque;
import it.unibas.freesbeesla.ws.web.persistenza.torque.DAOSlaObjectTraceTorque;
import it.unibas.freesbeesla.ws.web.persistenza.torque.DAOSoggettoSLATorque;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.GregorianCalendar;
import org.apache.torque.Torque;
import org.apache.torque.TorqueException;

public class DAOTest {

    private DAOTest() {
    }
    public static final String PORT = "8080";

    public static final String URL_WSDL_TRACCIATURA = "freesbee-Sla/ws/sistematracciatura/TraceSystem?wsdl";
    public static final String URL_WSDL_MONITORAGGIO_SLA = "freesbee-Sla/ws/monitoraggio/guaranteetermstate/ServiceGuaranteeTermState?wsdl";
    public static final String URL_WSDL_MONITORAGGIO_STATO = "freesbee-Sla/ws/monitoraggio/termstate/ServiceTermState?wsdl";

    public static final String ICAR_SINCRONO_LOOPBACK = "IcarSincronoLoopback";
    public static final String FRUITORE_UNICAR = "FruitoreUNICAR";
    public static final String EROGATORE_UNICAR = "ErogatoreUNICAR";
    public static final String ICAR_SINCRONO_LOOPBACK_UNIBAS = "IcarSincronoLoopbackUNIBAS";
    public static final String FRUITORE_UNIBAS = "FruitoreUNIBAS";
    public static final String EROGATORE_UNIBAS = "ErogatoreUNIBAS";

    public static void inserisciSoggetti() throws DAOException, TorqueException {
        Torque.init("torque-runtime.properties");

        DAOSoggettoSLATorque daoSoggetto = new DAOSoggettoSLATorque();
        SoggettoSLA soggetto = new SoggettoSLA();
        soggetto.setId(new Long(1));
        soggetto.setNomeSoggetto(FRUITORE_UNICAR);
        daoSoggetto.doInsert(soggetto);

        soggetto = new SoggettoSLA();
        soggetto.setId(new Long(2));
        soggetto.setNomeSoggetto(EROGATORE_UNICAR);
        daoSoggetto.doInsert(soggetto);

        soggetto = new SoggettoSLA();
        soggetto.setId(new Long(3));
        soggetto.setNomeSoggetto(FRUITORE_UNIBAS);
        daoSoggetto.doInsert(soggetto);

        soggetto = new SoggettoSLA();
        soggetto.setId(new Long(4));
        soggetto.setNomeSoggetto(EROGATORE_UNIBAS);
        daoSoggetto.doInsert(soggetto);
    }

    public static void ripulisciTabelle() throws TorqueException, DAOException {
        Torque.init("torque-runtime.properties");

        DAOServizioTorque daoServizio = new DAOServizioTorque();
        DAOSoggettoSLATorque daoSoggetto = new DAOSoggettoSLATorque();
        DAOSlaObjectTraceTorque daoSla = new DAOSlaObjectTraceTorque();
        daoSla.deleteAll();
        daoServizio.deleteAll();
        daoSoggetto.deleteAll();
    }

    public static void inserisciDatiTest1() throws DAOException, TorqueException, IOException {
        Torque.init("torque-runtime.properties");

        DAOServizioTorque daoServizio = new DAOServizioTorque();

        Service servizio = new Service();
        servizio.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        servizio.setInf2IdInitiator(FRUITORE_UNIBAS);
        servizio.setInf2IdResponder(EROGATORE_UNIBAS);
        servizio.setInf2IdAgreement("provaUNIBAS");
        servizio.setInf2IdState(ServiceTermStateType.READY_IDLE.value());
        StringBuffer out = caricaFile("/dati/WSAG_TEST_1.xml");
        servizio.setInf2SlaObject(out.toString());
        servizio.setInf2Active(new Long(1));
        servizio.setInf2CountPendingRequest(new Long(0));
        daoServizio.doInsert(servizio);


        servizio = new Service();
        servizio.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        servizio.setInf2IdInitiator(FRUITORE_UNICAR);
        servizio.setInf2IdResponder(EROGATORE_UNICAR);
        servizio.setInf2IdAgreement("agreementId");
        servizio.setInf2IdState(ServiceTermStateType.READY_IDLE.value());
        out = caricaFile("/dati/WSAG_TEST_1.xml");
        servizio.setInf2SlaObject(out.toString());
        servizio.setInf2Active(new Long(1));
        servizio.setInf2CountPendingRequest(new Long(0));
        daoServizio.doInsert(servizio);

        DAOSlaObjectTraceTorque daoSla = new DAOSlaObjectTraceTorque();
        SlaObjectTrace sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3, 18, 20, 20, 18).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(330));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(589));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3, 18, 20, 20, 23).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(524));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(992));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3, 18, 20, 20, 27).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(38));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(864));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3, 18, 20, 20, 32).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(726));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(92));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3, 18, 20, 20, 35).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(298));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(847));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3, 18, 20, 20, 39).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(629));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(900));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3, 18, 20, 20, 53).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(743));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(686));
        daoSla.doInsert(sla);
        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3, 18, 20, 58, 40).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(829));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(914));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 4, 5, 17, 21, 18).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(642));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(782));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 4, 5, 17, 21, 41).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(725));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(13));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 4, 5, 17, 22, 3).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(534));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(934));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 4, 5, 17, 21, 56).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(529));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(988));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 4, 5, 17, 23, 35).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(535));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(10));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 4, 5, 18, 9, 11).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(447));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(242));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 4, 5, 18, 40, 11).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(634));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(312));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 4, 5, 18, 40, 40).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(627));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(745));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 4, 5, 18, 57, 26).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(626));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(664));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 4, 5, 18, 59, 24).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(626));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(789));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 4, 5, 19, 0, 56).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(935));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(14));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 4, 5, 19, 4, 16).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(225));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(645));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 4, 5, 19, 4, 35).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(487));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(825));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 4, 5, 19, 6, 16).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(735));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(545));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 4, 5, 19, 6, 34).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(726));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(858));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 4, 5, 19, 6, 54).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(825));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(769));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 4, 5, 19, 7, 14).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(536));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(141));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 4, 5, 19, 7, 37).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(546));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(289));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 4, 5, 19, 8, 14).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(534));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(119));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 4, 5, 19, 9, 35).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(854));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(318));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 4, 5, 19, 9, 36).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(700));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(859));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 4, 5, 19, 9, 38).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(726));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(694));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 4, 5, 19, 9, 40).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(467));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(925));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 4, 5, 19, 10, 58).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(467));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(19));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 4, 5, 19, 11, 21).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(451));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(334));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 4, 5, 19, 11, 36).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(379));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(519));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 4, 5, 19, 11, 46).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(467));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(809));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 4, 5, 19, 12, 26).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(467));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(623));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 4, 5, 19, 12, 29).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(417));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(990));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 4, 5, 19, 12, 32).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(402));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(16));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 4, 5, 19, 12, 33).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(421));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(119));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 4, 5, 19, 16, 27).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(74));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(913));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 4, 5, 19, 16, 29).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(448));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(904));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 4, 5, 19, 16, 32).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(400));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(371));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 4, 5, 19, 16, 34).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(504));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(974));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 4, 5, 19, 18, 33).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(572));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(214));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 4, 5, 19, 18, 35).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(510));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(58));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 4, 5, 19, 18, 37).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(817));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(120));
        daoSla.doInsert(sla);


        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK);
        sla.setInf2IdInitiator(FRUITORE_UNICAR);
        sla.setInf2IdResponder(EROGATORE_UNICAR);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 4, 5, 19, 18, 38).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(587));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(888));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3, 18, 20, 20, 18).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(1500));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(589));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3, 18, 20, 20, 23).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(1000));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(992));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3, 18, 20, 20, 27).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(1200));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(864));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3, 18, 20, 20, 32).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(1600));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(92));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3, 18, 20, 20, 35).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(1550));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(847));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3, 18, 20, 20, 39).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(1900));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(900));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3, 18, 20, 20, 44).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(800));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(220));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3, 18, 20, 20, 53).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(1000));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(686));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3, 18, 20, 20, 40).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(750));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(914));
        daoSla.doInsert(sla);

    }

    public static void inserisciDatiTest2() throws TorqueException, DAOException, IOException {
        Torque.init("torque-runtime.properties");

        DAOServizioTorque daoServizio = new DAOServizioTorque();
        Service servizio = new Service();
        servizio.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        servizio.setInf2IdInitiator(FRUITORE_UNIBAS);
        servizio.setInf2IdResponder(EROGATORE_UNIBAS);
        servizio.setInf2IdAgreement("provaUNIBAS");
        servizio.setInf2IdState(ServiceTermStateType.READY_IDLE.value());
        StringBuffer out = caricaFile("/dati/WSAG_TEST_2.xml");
        servizio.setInf2SlaObject(out.toString());
        servizio.setInf2Active(new Long(1));
        servizio.setInf2CountPendingRequest(new Long(0));
        daoServizio.doInsert(servizio);

        DAOSlaObjectTraceTorque daoSla = new DAOSlaObjectTraceTorque();
        SlaObjectTrace sla = new SlaObjectTrace();

        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 12, 01, 44, 22).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(290));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 14, 01, 44, 25).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(290));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 18, 01, 44, 30).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(960));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 18, 01, 44, 35).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(290));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 20, 01, 55, 38).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(289));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 20, 01, 55, 42).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(299));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 22, 01, 55, 46).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(390));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 22, 01, 33, 50).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(390));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 22, 01, 33, 53).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(390));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 22, 01, 33, 56).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(390));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 8, 23, 23, 22).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(249));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 18, 23, 32, 35).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(259));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 18, 21, 00, 54).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(269));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 18, 12, 00, 54).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(279));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 23, 23, 00, 12).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(289));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 23, 23, 00, 54).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(299));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 23, 12, 00, 34).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(349));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 25, 22, 00, 44).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(319));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 25, 22, 00, 55).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(329));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 25, 22, 00, 22).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(339));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 1, 14, 00, 22).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(740));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 1, 22, 00, 25).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(750));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 01, 23, 12, 30).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(660));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 3, 12, 32, 35).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(170));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 5, 20, 12, 38).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(180));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 13, 20, 21, 42).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(390));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 13, 19, 18, 46).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(240));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 13, 18, 44, 50).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(910));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 13, 18, 33, 53).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(220));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 13, 22, 22, 56).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(130));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 25, 12, 02, 22).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(565));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 25, 12, 20, 25).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(210));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 26, 21, 12, 30).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(250));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 26, 21, 40, 35).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(290));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 26, 23, 20, 38).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(180));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 26, 12, 5, 42).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(490));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 27, 23, 3, 46).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(390));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 27, 12, 7, 50).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(380));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 27, 5, 1, 53).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(330));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 27, 3, 6, 56).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(230));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 22).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(240));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 25).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(250));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 30).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(260));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 35).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(270));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 38).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(280));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 42).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(290));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 46).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(340));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 50).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(310));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 53).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(320));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 56).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(330));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

    }

    public static void inserisciDatiTest3() throws TorqueException, DAOException, IOException {
        Torque.init("torque-runtime.properties");

        DAOServizioTorque daoServizio = new DAOServizioTorque();
        Service servizio = new Service();
        servizio.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        servizio.setInf2IdInitiator(FRUITORE_UNIBAS);
        servizio.setInf2IdResponder(EROGATORE_UNIBAS);
        servizio.setInf2IdAgreement("provaUNIBAS");
        servizio.setInf2IdState(ServiceTermStateType.READY_IDLE.value());
        StringBuffer out = caricaFile("/dati/WSAG_TEST_3.xml");
        servizio.setInf2SlaObject(out.toString());
        servizio.setInf2Active(new Long(1));
        servizio.setInf2CountPendingRequest(new Long(0));
        daoServizio.doInsert(servizio);


        DAOSlaObjectTraceTorque daoSla = new DAOSlaObjectTraceTorque();
        SlaObjectTrace sla = new SlaObjectTrace();

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 22).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(240));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 25, 1, 00, 25).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(250));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 22, 1, 00, 30).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(260));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 19, 1, 00, 35).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(270));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 23, 1, 00, 38).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(280));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 22, 1, 00, 42).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(290));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 20, 1, 00, 46).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(340));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 20, 1, 00, 50).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(310));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 20, 1, 00, 53).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(320));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 27, 1, 00, 56).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(342));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 27, 1, 00, 42).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(765));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 27, 1, 00, 46).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(274));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 27, 1, 00, 50).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(744));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 53).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(233));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 56).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(543));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoChiamata");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 22).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(32));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoChiamata");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 25, 1, 00, 25).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(55));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoChiamata");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 22, 1, 00, 30).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(897));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoChiamata");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 19, 1, 00, 35).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(456));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoChiamata");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 23, 1, 00, 38).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(123));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoChiamata");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 22, 1, 00, 42).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(678));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoChiamata");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 20, 1, 00, 46).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(823));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoChiamata");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 20, 1, 00, 50).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(234));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoChiamata");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 20, 1, 00, 53).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(345));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoChiamata");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 27, 1, 00, 56).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(678));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoChiamata");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 27, 1, 00, 42).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(534));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoChiamata");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 27, 1, 00, 46).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(341));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoChiamata");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 27, 1, 00, 50).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(99));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoChiamata");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 53).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(122));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoChiamata");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 56).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(223));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

    }

    public static void inserisciDatiTest4() throws TorqueException, DAOException, IOException {

        Torque.init("torque-runtime.properties");

        DAOServizioTorque daoServizio = new DAOServizioTorque();
        Service servizio = new Service();
        servizio.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        servizio.setInf2IdInitiator(FRUITORE_UNIBAS);
        servizio.setInf2IdResponder(EROGATORE_UNIBAS);
        servizio.setInf2IdAgreement("provaUNIBAS");
        servizio.setInf2IdState("Ready_Idle");
        StringBuffer out = caricaFile("/dati/WSAG_TEST_4.xml");
        servizio.setInf2SlaObject(out.toString());
        servizio.setInf2Active(new Long(1));
        servizio.setInf2CountPendingRequest(new Long(0));
        daoServizio.doInsert(servizio);


        DAOSlaObjectTraceTorque daoSla = new DAOSlaObjectTraceTorque();
        SlaObjectTrace sla = new SlaObjectTrace();

        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 12, 01, 44, 22).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(290));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 14, 01, 44, 25).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(290));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 18, 01, 44, 30).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(960));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 18, 01, 44, 35).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(290));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 20, 01, 55, 38).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(289));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 20, 01, 55, 42).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(299));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 22, 01, 55, 46).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(390));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 22, 01, 33, 50).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(390));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 22, 01, 33, 53).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(390));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 22, 01, 33, 56).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(390));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 8, 23, 23, 22).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(249));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 18, 23, 32, 35).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(259));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 18, 21, 00, 54).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(269));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 18, 12, 00, 54).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(279));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 23, 23, 00, 12).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(289));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 23, 23, 00, 54).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(299));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 23, 12, 00, 34).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(349));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 25, 22, 00, 44).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(319));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 25, 22, 00, 55).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(329));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 25, 22, 00, 22).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(339));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 1, 14, 00, 22).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(740));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 1, 22, 00, 25).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(750));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 01, 23, 12, 30).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(660));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 3, 12, 32, 35).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(170));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 5, 20, 12, 38).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(180));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 13, 20, 21, 42).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(390));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 13, 19, 18, 46).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(240));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 13, 18, 44, 50).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(910));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 13, 18, 33, 53).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(220));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 13, 22, 22, 56).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(130));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 25, 12, 02, 22).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(565));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 25, 12, 20, 25).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(210));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 26, 21, 12, 30).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(250));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 26, 21, 40, 35).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(290));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 26, 23, 20, 38).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(180));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 26, 12, 5, 42).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(490));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 27, 23, 3, 46).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(390));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 27, 12, 7, 50).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(380));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 27, 5, 1, 53).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(330));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 27, 3, 6, 56).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(230));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 22).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(240));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 25).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(250));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 30).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(260));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 35).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(270));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 38).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(280));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 42).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(290));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 46).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(340));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 50).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(310));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 53).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(320));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 56).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(330));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 29, 17, 55, 00).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(240));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 29, 17, 44, 00).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(250));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 29, 17, 35, 00).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(260));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 29, 17, 30, 00).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(270));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 29, 14, 40, 00).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(280));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 29, 14, 00, 00).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(290));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 29, 13, 04, 22).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(340));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 29, 11, 20, 00).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(310));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 29, 11, 00, 00).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(320));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 30, 00, 00, 00).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(330));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);


    }

    public static void inserisciDatiTest5() throws TorqueException, DAOException, IOException {
        Torque.init("torque-runtime.properties");

        DAOServizioTorque daoServizio = new DAOServizioTorque();
        Service servizio = new Service();
        servizio.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        servizio.setInf2IdInitiator(FRUITORE_UNIBAS);
        servizio.setInf2IdResponder(EROGATORE_UNIBAS);
        servizio.setInf2IdAgreement("provaUNIBAS");
        servizio.setInf2IdState(ServiceTermStateType.READY_IDLE.value());
        StringBuffer out = caricaFile("/dati/WSAG_TEST_5.xml");
        servizio.setInf2SlaObject(out.toString());
        servizio.setInf2Active(new Long(1));
        servizio.setInf2CountPendingRequest(new Long(0));
        daoServizio.doInsert(servizio);


        DAOSlaObjectTraceTorque daoSla = new DAOSlaObjectTraceTorque();
        SlaObjectTrace sla = new SlaObjectTrace();

        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 12, 01, 44, 22).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(290));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 14, 01, 44, 25).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(290));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 18, 01, 44, 30).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(960));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 18, 01, 44, 35).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(290));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 20, 01, 55, 38).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(289));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 20, 01, 55, 42).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(299));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 22, 01, 55, 46).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(390));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 22, 01, 33, 50).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(390));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 22, 01, 33, 53).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(390));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 22, 01, 33, 56).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(390));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 8, 23, 23, 22).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(249));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 18, 23, 32, 35).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(259));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 18, 21, 00, 54).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(269));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 18, 12, 00, 54).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(279));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 23, 23, 00, 12).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(289));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 23, 23, 00, 54).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(299));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 23, 12, 00, 34).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(349));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 25, 22, 00, 44).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(319));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 25, 22, 00, 55).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(329));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 25, 22, 00, 22).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(339));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 1, 14, 00, 22).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(740));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 1, 22, 00, 25).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(750));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 01, 23, 12, 30).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(660));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 3, 12, 32, 35).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(170));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 5, 20, 12, 38).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(180));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 13, 20, 21, 42).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(390));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 13, 19, 18, 46).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(240));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 13, 18, 44, 50).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(910));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 13, 18, 33, 53).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(220));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 13, 22, 22, 56).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(130));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 25, 12, 02, 22).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(565));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 25, 12, 20, 25).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(210));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 26, 21, 12, 30).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(250));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 26, 21, 40, 35).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(290));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 26, 23, 20, 38).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(180));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 26, 12, 5, 42).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(490));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 27, 23, 3, 46).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(390));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 27, 12, 7, 50).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(380));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 27, 5, 1, 53).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(330));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 27, 3, 6, 56).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(230));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 22).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(240));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 25).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(250));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 30).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(260));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 35).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(270));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 38).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(280));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 42).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(290));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 46).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(340));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 50).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(310));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 53).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(320));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 56).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(330));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 29, 17, 55, 00).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(240));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 29, 17, 44, 00).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(250));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 29, 17, 35, 00).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(260));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 29, 17, 30, 00).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(270));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 29, 14, 40, 00).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(280));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 29, 14, 00, 00).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(290));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 29, 13, 04, 22).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(340));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 29, 11, 20, 00).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(310));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 29, 11, 00, 00).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(320));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 30, 00, 00, 00).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(330));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);
    }

    public static void inserisciDatiTest6() throws TorqueException, IOException, DAOException {
        Torque.init("torque-runtime.properties");

        DAOServizioTorque daoServizio = new DAOServizioTorque();
        Service servizio = new Service();
        servizio.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        servizio.setInf2IdInitiator(FRUITORE_UNIBAS);
        servizio.setInf2IdResponder(EROGATORE_UNIBAS);
        servizio.setInf2IdAgreement("provaUNIBAS");
        servizio.setInf2IdState(ServiceTermStateType.READY_IDLE.value());
        StringBuffer out = caricaFile("/dati/WSAG_TEST_6.xml");
        servizio.setInf2SlaObject(out.toString());
        servizio.setInf2Active(new Long(1));
        servizio.setInf2CountPendingRequest(new Long(0));
        daoServizio.doInsert(servizio);


        DAOSlaObjectTraceTorque daoSla = new DAOSlaObjectTraceTorque();
        SlaObjectTrace sla = new SlaObjectTrace();

        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 12, 01, 44, 22).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(290));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 14, 01, 44, 25).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(290));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 18, 01, 44, 30).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(960));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 18, 01, 44, 35).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(290));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 20, 01, 55, 38).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(289));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 20, 01, 55, 42).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(299));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 22, 01, 55, 46).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(390));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 22, 01, 33, 50).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(390));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 22, 01, 33, 53).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(390));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 22, 01, 33, 56).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(390));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 8, 23, 23, 22).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(249));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 18, 23, 32, 35).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(259));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 18, 21, 00, 54).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(269));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 18, 12, 00, 54).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(279));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 23, 23, 00, 12).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(289));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 23, 23, 00, 54).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(299));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 23, 12, 00, 34).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(349));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 25, 22, 00, 44).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(319));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 25, 22, 00, 55).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(329));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 25, 22, 00, 22).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(339));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 1, 14, 00, 22).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(740));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 1, 22, 00, 25).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(750));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 01, 23, 12, 30).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(660));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 3, 12, 32, 35).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(170));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 5, 20, 12, 38).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(180));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 13, 20, 21, 42).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(390));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 13, 19, 18, 46).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(240));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 13, 18, 44, 50).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(910));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 13, 18, 33, 53).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(220));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 13, 22, 22, 56).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(130));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 25, 12, 02, 22).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(565));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 25, 12, 20, 25).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(210));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 26, 21, 12, 30).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(250));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 26, 21, 40, 35).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(290));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 26, 23, 20, 38).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(180));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 26, 12, 5, 42).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(490));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 27, 23, 3, 46).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(390));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 27, 12, 7, 50).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(380));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 27, 5, 1, 53).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(330));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 27, 3, 6, 56).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(230));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 22).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(240));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 25).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(250));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 30).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(260));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 35).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(270));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 38).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(280));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 42).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(290));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 46).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(340));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 50).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(310));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 53).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(320));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 56).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(330));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 29, 17, 55, 00).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(240));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 29, 17, 44, 00).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(250));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 29, 17, 35, 00).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(260));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 29, 17, 30, 00).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(270));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 29, 14, 40, 00).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(280));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 29, 14, 00, 00).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(290));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 29, 13, 04, 22).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(340));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 29, 11, 20, 00).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(310));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 29, 11, 00, 00).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(320));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 30, 00, 00, 00).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(330));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

    }

    public static void inserisciDatiTest7() throws TorqueException, IOException, DAOException {
        Torque.init("torque-runtime.properties");

        DAOServizioTorque daoServizio = new DAOServizioTorque();
        Service servizio = new Service();

        servizio.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        servizio.setInf2IdInitiator(FRUITORE_UNIBAS);
        servizio.setInf2IdResponder(EROGATORE_UNIBAS);
        servizio.setInf2IdAgreement("provaUNIBAS");
        servizio.setInf2IdState("Ready_Idle");
        StringBuffer out = caricaFile("/dati/WSAG_TEST_7.xml");
        servizio.setInf2SlaObject(out.toString());
        servizio.setInf2Active(new Long(1));
        servizio.setInf2CountPendingRequest(new Long(0));
        daoServizio.doInsert(servizio);


        DAOSlaObjectTraceTorque daoSla = new DAOSlaObjectTraceTorque();
        SlaObjectTrace sla = new SlaObjectTrace();

        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 12, 01, 44, 22).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(290));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 14, 01, 44, 25).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(290));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 18, 01, 44, 30).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(960));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 18, 01, 44, 35).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(290));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 20, 01, 55, 38).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(289));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 20, 01, 55, 42).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(299));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 22, 01, 55, 46).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(390));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 22, 01, 33, 50).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(390));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 22, 01, 33, 53).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(390));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 22, 01, 33, 56).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(390));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 8, 23, 23, 22).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(249));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 18, 23, 32, 35).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(259));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 18, 21, 00, 54).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(269));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 18, 12, 00, 54).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(279));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 23, 23, 00, 12).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(289));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 23, 23, 00, 54).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(299));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 23, 12, 00, 34).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(349));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 25, 22, 00, 44).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(319));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 25, 22, 00, 55).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(329));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 25, 22, 00, 22).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(339));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 1, 14, 00, 22).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(740));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 1, 22, 00, 25).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(750));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 01, 23, 12, 30).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(660));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 3, 12, 32, 35).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(170));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 5, 20, 12, 38).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(180));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 13, 20, 21, 42).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(390));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 13, 19, 18, 46).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(240));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 13, 18, 44, 50).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(910));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 13, 18, 33, 53).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(220));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 13, 22, 22, 56).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(130));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 25, 12, 02, 22).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(565));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 25, 12, 20, 25).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(210));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 26, 21, 12, 30).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(250));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 26, 21, 40, 35).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(290));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 26, 23, 20, 38).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(180));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 26, 12, 5, 42).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(490));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 27, 23, 3, 46).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(390));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 27, 12, 7, 50).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(380));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 27, 5, 1, 53).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(330));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 27, 3, 6, 56).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(230));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 22).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(240));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 25).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(250));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 30).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(260));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 35).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(270));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 38).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(280));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 42).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(290));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 46).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(340));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 50).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(310));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 53).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(320));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 56).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(330));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 29, 17, 55, 00).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(240));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 29, 17, 44, 00).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(250));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 29, 17, 35, 00).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(260));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 29, 17, 30, 00).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(270));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 29, 14, 40, 00).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(280));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 29, 14, 00, 00).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(290));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 29, 13, 04, 22).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(340));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 29, 11, 20, 00).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(310));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 29, 11, 00, 00).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(320));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 30, 00, 00, 00).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(330));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

    }

    public static void inserisciDatiTest8() throws TorqueException, IOException, DAOException {
        Torque.init("torque-runtime.properties");

        DAOServizioTorque daoServizio = new DAOServizioTorque();
        Service servizio = new Service();
        servizio.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        servizio.setInf2IdInitiator(FRUITORE_UNIBAS);
        servizio.setInf2IdResponder(EROGATORE_UNIBAS);
        servizio.setInf2IdAgreement("provaUNIBAS");
        servizio.setInf2IdState(ServiceTermStateType.READY_IDLE.value());
        StringBuffer out = caricaFile("/dati/WSAG_TEST_8.xml");
        servizio.setInf2SlaObject(out.toString());
        servizio.setInf2Active(new Long(1));
        servizio.setInf2CountPendingRequest(new Long(0));
        daoServizio.doInsert(servizio);


        DAOSlaObjectTraceTorque daoSla = new DAOSlaObjectTraceTorque();
        SlaObjectTrace sla = new SlaObjectTrace();

        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 12, 01, 44, 22).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(290));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 14, 01, 44, 25).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(290));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 18, 01, 44, 30).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(960));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 18, 01, 44, 35).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(290));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);

        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 20, 01, 55, 38).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(289));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 20, 01, 55, 42).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(299));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 22, 01, 55, 46).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(390));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 22, 01, 33, 50).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(390));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 22, 01, 33, 53).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(390));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 1 - 1, 22, 01, 33, 56).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(390));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 8, 23, 23, 22).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(249));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 18, 23, 32, 35).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(259));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 18, 21, 00, 54).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(269));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 18, 12, 00, 54).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(279));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 23, 23, 00, 12).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(289));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 23, 23, 00, 54).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(299));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 23, 12, 00, 34).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(349));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 25, 22, 00, 44).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(319));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 25, 22, 00, 55).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(329));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 2 - 1, 25, 22, 00, 22).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(339));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 1, 14, 00, 22).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(740));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 1, 22, 00, 25).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(750));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 01, 23, 12, 30).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(660));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 3, 12, 32, 35).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(170));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 5, 20, 12, 38).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(180));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 13, 20, 21, 42).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(390));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 13, 19, 18, 46).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(240));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 13, 18, 44, 50).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(910));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 13, 18, 33, 53).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(220));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 13, 22, 22, 56).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(130));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 25, 12, 02, 22).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(565));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 25, 12, 20, 25).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(210));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 26, 21, 12, 30).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(250));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 26, 21, 40, 35).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(290));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 26, 23, 20, 38).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(180));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 26, 12, 5, 42).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(490));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 27, 23, 3, 46).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(390));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 27, 12, 7, 50).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(380));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 27, 5, 1, 53).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(330));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 27, 3, 6, 56).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(230));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 22).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(240));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 25).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(250));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 30).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(260));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 35).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(270));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 38).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(280));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 42).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(290));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 46).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(340));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 50).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(310));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 53).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(320));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 28, 1, 00, 56).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(2577));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 29, 17, 55, 00).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(240));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 29, 17, 44, 00).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(250));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 29, 17, 35, 00).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(260));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 29, 17, 30, 00).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(270));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 29, 14, 40, 00).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(280));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 29, 14, 00, 00).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(290));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 29, 13, 04, 22).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(340));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 29, 11, 20, 00).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(310));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 29, 11, 00, 00).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(320));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);

        sla = new SlaObjectTrace();
        sla.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        sla.setInf2IdInitiator(FRUITORE_UNIBAS);
        sla.setInf2IdResponder(EROGATORE_UNIBAS);
        sla.setInf2SlaBasicMetric("tempoRisposta");
        sla.setInf2SlaBasicMetricDataInsert(new GregorianCalendar(2008, 3 - 1, 30, 00, 00, 00).getTime());
        sla.setInf2SlaBasicMetricValue(new Double(330));
        sla.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(999));
        daoSla.doInsert(sla);


    }

    public static void inserisciDatiTestTracciatua() throws TorqueException, IOException, DAOException {
        Torque.init("torque-runtime.properties");

        DAOServizioTorque daoServizio = new DAOServizioTorque();
        Service servizio = new Service();
        servizio.setInf2IdService(ICAR_SINCRONO_LOOPBACK_UNIBAS);
        servizio.setInf2IdInitiator(FRUITORE_UNIBAS);
        servizio.setInf2IdResponder(EROGATORE_UNIBAS);
        servizio.setInf2IdAgreement("provaUNIBAS");
        servizio.setInf2IdState(ServiceTermStateType.READY_IDLE.value());
        StringBuffer out = caricaFile("/dati/WSAG_TRACCIATURA.xml");
        servizio.setInf2SlaObject(out.toString());
        servizio.setInf2Active(new Long(1));
        servizio.setInf2CountPendingRequest(new Long(0));
        daoServizio.doInsert(servizio);

        servizio = new Service();
        servizio.setInf2IdService("IcarSincronoLoopback");
        servizio.setInf2IdInitiator("FruitoreUNICAR");
        servizio.setInf2IdResponder("ErogatoreUNICAR");
        servizio.setInf2IdAgreement("agreementId");
        servizio.setInf2IdState(ServiceTermStateType.READY_IDLE.value());
        out = caricaFile("/dati/WSAG_TRACCIATURA.xml");
        servizio.setInf2SlaObject(out.toString());
        servizio.setInf2Active(new Long(1));
        servizio.setInf2CountPendingRequest(new Long(0));
        daoServizio.doInsert(servizio);

    }

    public static StringBuffer caricaFile(String nome) throws IOException {
        InputStream is = DAOTest.class.getResourceAsStream(nome);
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[1000];
        for (int n; (n = is.read(b)) != -1;) {
            out.append(new String(b, 0, n));
        }
        return out;
    }
}
