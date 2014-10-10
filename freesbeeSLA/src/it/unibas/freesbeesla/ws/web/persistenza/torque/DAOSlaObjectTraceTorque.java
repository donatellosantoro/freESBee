package it.unibas.freesbeesla.ws.web.persistenza.torque;

import it.unibas.freesbeesla.tracciatura.modello.SlaObjectTrace;
import it.unibas.freesbeesla.tracciatura.modello.dao.SlaObjectTracePeer;
import it.unibas.freesbeesla.ws.web.persistenza.soap.DAOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.torque.TorqueException;
import org.apache.torque.util.Criteria;

public class DAOSlaObjectTraceTorque {

    private static Log logger = LogFactory.getLog(DAOSlaObjectTraceTorque.class);

    public Date selectMinDataByServizio(String nomeServizio, String nomeFruitore, String nomeErogatore) throws DAOException {
        try {
            Criteria criterio = new Criteria();
            criterio.add(SlaObjectTracePeer.INF2_ID_SERVICE, nomeServizio);
            criterio.add(SlaObjectTracePeer.INF2_ID_INITIATOR, nomeFruitore);
            criterio.add(SlaObjectTracePeer.INF2_ID_RESPONDER, nomeErogatore);
            criterio.addAscendingOrderByColumn(SlaObjectTracePeer.INF2_SLA_BASIC_METRIC_DATA_INSERT);
            List listaSlaObjectTrace = SlaObjectTracePeer.doSelect(criterio);

            SlaObjectTrace slaObjectTrace = null;
            if (listaSlaObjectTrace == null) {
                logger.error("Impossibile accedere alla tabella dei servizi nel DB");
                throw new DAOException("Impossibile accedere alla tabella dei servizi nel DB");
            }
            if (listaSlaObjectTrace.size() == 0) {
                logger.error("Impossibile determinare la data minima utile");
                throw new DAOException("Impossibile determinare la data minima utile");
            }

            slaObjectTrace = (SlaObjectTrace) listaSlaObjectTrace.get(0);
            return slaObjectTrace.getInf2SlaBasicMetricDataInsert();
        } catch (TorqueException ex) {
            logger.error("Si e' verificato un errore durante l'accesso al DB " + ex);
            throw new DAOException("Si e' verificato un errore durante l'accesso al DB");
        }
    }

    public SlaObjectTrace doSelect(String inf2IdService, String inf2IdInitiator, String inf2IdResponder, String basicMetric, Double basicMetricValue, XMLGregorianCalendar date) throws DAOException {
        try {
            SlaObjectTrace slaObjectTrace = new SlaObjectTrace();
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
            List lista = SlaObjectTracePeer.doSelect(slaObjectTrace);
            if (lista.size() != 0) {
                return (SlaObjectTrace) lista.get(0);
            }

        } catch (TorqueException ex) {
            logger.error("Errore: " + ex);
        }
        return null;
    }
    
    public void doInsert(SlaObjectTrace slaObjectTrace) throws DAOException {
        try {
            SlaObjectTracePeer.doInsert(slaObjectTrace);
        } catch (TorqueException ex) {
            logger.error("Si e' verificato un errore durante l'accesso al DB " + ex);
            throw new DAOException("Si e' verificato un errore durante l'accesso al DB");
        }
    }

      public void deleteAll() throws DAOException {
        try {
            SlaObjectTracePeer.executeStatement("delete from " +SlaObjectTracePeer.TABLE_NAME);
        } catch (TorqueException ex) {
            logger.error("Si e' verificato un errore durante l'accesso al DB " + ex);
            throw new DAOException("Si e' verificato un errore durante l'accesso al DB");
        }

    }    
}
