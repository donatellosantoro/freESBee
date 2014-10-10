package it.unibas.freesbeesla.tracciatura.persistenza;

import it.unibas.freesbee.client.cxf.sistematracciatura.ServiceTermStateType;
import it.unibas.freesbeesla.ConfigurazioneErrori;
import it.unibas.freesbeesla.tracciatura.modello.Service;
import it.unibas.freesbeesla.tracciatura.modello.SlaObjectTrace;
import it.unibas.freesbeesla.tracciatura.modello.dao.ServicePeer;
import it.unibas.freesbeesla.tracciatura.modello.dao.SlaObjectTracePeer;
import it.unibas.freesbeesla.tracciatura.ws.server.SOAPFault;
import it.unibas.freesbeesla.tracciatura.ws.server.stub.ResultType;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.torque.TorqueException;
import org.apache.torque.util.Criteria;

public class DAOTracciatura {

    private static Log logger = LogFactory.getLog(DAOTracciatura.class);

    public ResultType updateServiceTermState(String inf2IdService, String inf2IdInitiator, String inf2IdResponder, String inf2SlaBasicMetricValue) throws SOAPFault {
        ResultType resultType = new ResultType();
        SOAPFault soapFault = new SOAPFault();
        Service service = null;
        String value = "000";
        List lista = null;

        try {
            logger.info("Tracciatura - updateServiceTermState: " + inf2IdService + "   " + inf2SlaBasicMetricValue);
            Criteria criteriaService = new Criteria();
            criteriaService.add(ServicePeer.INF2_ID_SERVICE, inf2IdService);
            criteriaService.add(ServicePeer.INF2_ID_INITIATOR, inf2IdInitiator);
            criteriaService.add(ServicePeer.INF2_ID_RESPONDER, inf2IdResponder);
            lista = ServicePeer.doSelect(criteriaService); // SELECT sulla tabella service

            if (lista.size() != 0) {
                service = (Service) lista.get(0); // recupera il servizio selezionato

                if (inf2SlaBasicMetricValue.equalsIgnoreCase(ServiceTermStateType.READY_IDLE.value()) || inf2SlaBasicMetricValue.equalsIgnoreCase(ServiceTermStateType.READY_PROCESSING.value()) || inf2SlaBasicMetricValue.equalsIgnoreCase(ServiceTermStateType.NOT_READY.value()) || inf2SlaBasicMetricValue.equalsIgnoreCase(ServiceTermStateType.COMPLETED.value())) {
                    if (inf2SlaBasicMetricValue.equalsIgnoreCase(ServiceTermStateType.READY_PROCESSING.value()) && service.getInf2IdState().equalsIgnoreCase(ServiceTermStateType.READY_IDLE.value())) {
                        service.setInf2IdState(ServiceTermStateType.READY_PROCESSING.value());
                        service.setInf2CountPendingRequest(service.getInf2CountPendingRequest() + 1);
                        ServicePeer.doUpdate(service);
                        value = "001";
                    } else {
                        if (inf2SlaBasicMetricValue.equalsIgnoreCase(ServiceTermStateType.READY_PROCESSING.value()) && service.getInf2IdState().equalsIgnoreCase(ServiceTermStateType.READY_PROCESSING.value())) {
                            service.setInf2CountPendingRequest(service.getInf2CountPendingRequest() + 1);
                            ServicePeer.doUpdate(service);
                            value = "002";
                        } else {

                            if (inf2SlaBasicMetricValue.equalsIgnoreCase(ServiceTermStateType.READY_IDLE.value()) && service.getInf2IdState().equalsIgnoreCase(ServiceTermStateType.READY_PROCESSING.value())) {

                                if (service.getInf2CountPendingRequest() == 1) {
                                    service.setInf2IdState(ServiceTermStateType.READY_IDLE.value());
                                    service.setInf2CountPendingRequest(service.getInf2CountPendingRequest() - 1);
                                    ServicePeer.doUpdate(service);
                                    value = "004";
                                } else {
                                    service.setInf2CountPendingRequest(service.getInf2CountPendingRequest() - 1);
                                    ServicePeer.doUpdate(service);
                                    value = "003";
                                }
                            } else {
                                soapFault.setCode("101");
                                soapFault.setDescription(ConfigurazioneErrori.getInstance().trovaDescrizioneErrore("101"));
                                throw soapFault;
                            }
                        }
                    }

                } else {
                    soapFault.setCode("205");
                    soapFault.setDescription(ConfigurazioneErrori.getInstance().trovaDescrizioneErrore("205"));
                    throw soapFault;
                }

            } else {
                soapFault.setCode("204");
                soapFault.setDescription(ConfigurazioneErrori.getInstance().trovaDescrizioneErrore("205"));
                throw soapFault;
            }

        } catch (TorqueException e) {
            soapFault.setCode("200");
            soapFault.setDescription("Errore nel DB.: " + e);
            throw soapFault;
        }
        resultType.setCode(value);
        String descrizione = ConfigurazioneErrori.getInstance().trovaDescrizioneErrore(value);
        resultType.setDescription(descrizione);
        int indice = descrizione.indexOf(":");
        if (indice != -1) {
            resultType.setTypeRes(descrizione.substring(0, indice));
        } else {
            resultType.setTypeRes(descrizione);
        }

        return resultType;

    }

    public ResultType insertServiceBasicMetric(String inf2IdService, String inf2IdInitiator, String inf2IdResponder, String basicMetric, Double basicMetricValue, XMLGregorianCalendar date) throws SOAPFault {
        ResultType resultType = new ResultType();
        SOAPFault soapFault = new SOAPFault();
        SlaObjectTrace slaObjectTrace = null;
        String value = "000";
        List lista = null;

        try {
            logger.debug("Tracciatura - insertServiceBasicMetric: " + inf2IdService);

            Criteria criteriaService = new Criteria();
            criteriaService.add(ServicePeer.INF2_ID_SERVICE, inf2IdService);
            criteriaService.add(ServicePeer.INF2_ID_INITIATOR, inf2IdInitiator);
            criteriaService.add(ServicePeer.INF2_ID_RESPONDER, inf2IdResponder);
            criteriaService.add(ServicePeer.INF2_ACTIVE, 1);


            lista = ServicePeer.doSelect(criteriaService);

            if (lista.size() == 1) {
                slaObjectTrace = new SlaObjectTrace();
                slaObjectTrace.setInf2IdService(inf2IdService);
                slaObjectTrace.setInf2IdInitiator(inf2IdInitiator);
                slaObjectTrace.setInf2IdResponder(inf2IdResponder);
                slaObjectTrace.setInf2SlaBasicMetric(basicMetric);
                slaObjectTrace.setInf2SlaBasicMetricValue(basicMetricValue);
                GregorianCalendar calendar = date.toGregorianCalendar();
                if (calendar == null) {
                    calendar = new GregorianCalendar();
                }
                slaObjectTrace.setInf2SlaBasicMetricDataInsert(calendar.getTime());
                slaObjectTrace.setInf2SlaBasicMetricMillisecondsInsert(new BigDecimal(calendar.get(Calendar.MILLISECOND)));

                lista = SlaObjectTracePeer.doSelect(slaObjectTrace);
                if (lista.size() == 0) {
                    SlaObjectTracePeer.doInsert(slaObjectTrace);
                } else {
                    soapFault.setCode("201");
                    soapFault.setDescription(ConfigurazioneErrori.getInstance().trovaDescrizioneErrore("201"));
                    throw soapFault;
                }

            } else {
                if (lista.size() < 1) {
                    soapFault.setCode("203");
                    soapFault.setDescription(ConfigurazioneErrori.getInstance().trovaDescrizioneErrore("203"));
                    throw soapFault;
                } else {
                    soapFault.setCode("204");
                    soapFault.setDescription(ConfigurazioneErrori.getInstance().trovaDescrizioneErrore("204"));
                    throw soapFault;
                }
            }

        } catch (TorqueException e) {
            logger.error("Errore: " + e);
            soapFault.setCode("200");
            soapFault.setDescription("Errore nel DB.");
            throw soapFault;
        }
        resultType.setCode(value);
        String descrizione = ConfigurazioneErrori.getInstance().trovaDescrizioneErrore(value);
        resultType.setDescription(descrizione);
        int indice = descrizione.indexOf(":");
        if (indice != -1) {
            resultType.setTypeRes(descrizione.substring(0, indice));
        } else {
            resultType.setTypeRes(descrizione);
        }
        return resultType;
    }
}

