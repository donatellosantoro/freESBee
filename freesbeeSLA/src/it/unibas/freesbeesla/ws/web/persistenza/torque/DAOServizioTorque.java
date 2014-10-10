package it.unibas.freesbeesla.ws.web.persistenza.torque;

import it.unibas.freesbeesla.tracciatura.modello.Service;
import it.unibas.freesbeesla.tracciatura.modello.dao.ServicePeer;
import it.unibas.freesbeesla.ws.web.persistenza.soap.DAOException;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.torque.TorqueException;
import org.apache.torque.util.Criteria;

public class DAOServizioTorque {

    private static Log logger = LogFactory.getLog(DAOServizioTorque.class);

    public Service selectByNomeFruitoreErogatoreServizi(String idService, String idInitiator, String idResponder) throws DAOException {
        try {
            Criteria criterio = new Criteria();
            criterio.add(ServicePeer.INF2_ID_SERVICE, idService);
            criterio.add(ServicePeer.INF2_ID_INITIATOR, idInitiator);
            criterio.add(ServicePeer.INF2_ID_RESPONDER, idResponder);

            List listaServices = ServicePeer.doSelect(criterio);

            if ((listaServices == null)) {
                logger.error("Impossibile accedere alla tabella dei servizio nel DB");
                throw new DAOException("Impossibile accedere alla tabella dei servizio nel DB");
            }

            if ((listaServices.size() == 0)) {
                logger.info("Service non presente nel DB");
                throw new DAOException("Utente non presente nel DB");
            }

            Service servizioTrovato = (Service) listaServices.get(0);
            return servizioTrovato;
        } catch (TorqueException ex) {
            logger.error("Si e' verificato un errore durante l'accesso al DB " + ex);
            throw new DAOException("Si e' verificato un errore durante l'accesso al DB");
        }
    }

    public List<Service> selectAll() throws DAOException {
        try {
            List listaServices = ServicePeer.doSelect(new Criteria());

            if ((listaServices == null)) {
                logger.error("Impossibile accedere alla tabella dei servizi nel DB");
                throw new DAOException("Impossibile accedere alla tabella dei servizi nel DB");
            }

            return listaServices;

        } catch (TorqueException ex) {
            logger.error("Si e' verificato un errore durante l'accesso al DB " + ex);
            throw new DAOException("Si e' verificato un errore durante l'accesso al DB");
        }
    }

    public void doInsert(Service servizio) throws DAOException {
        try {
            ServicePeer.doInsert(servizio);
        } catch (TorqueException ex) {
            logger.error("Si e' verificato un errore durante l'accesso al DB " + ex);
            throw new DAOException("Si e' verificato un errore durante l'accesso al DB");
        }
    }

    public void doUpdateActive(Service servizio) throws DAOException {
        try {
            Criteria criterio = new Criteria();
            criterio.add(ServicePeer.INF2_ACTIVE, servizio.getInf2Active());
            criterio.add(ServicePeer.INF2_ID_SERVICE, servizio.getInf2IdService());
            criterio.add(ServicePeer.INF2_ID_INITIATOR, servizio.getInf2IdInitiator());
            criterio.add(ServicePeer.INF2_ID_RESPONDER, servizio.getInf2IdResponder());
            ServicePeer.doUpdate(criterio);
        } catch (TorqueException ex) {
            logger.error("Si e' verificato un errore durante l'accesso al DB " + ex);
            throw new DAOException("Si e' verificato un errore durante l'accesso al DB");
        }

    }

    public void deleteAll() throws DAOException {
        try {
            ServicePeer.executeStatement("delete from " + ServicePeer.TABLE_NAME);
        } catch (TorqueException ex) {
            logger.error("Si e' verificato un errore durante l'accesso al DB " + ex);
            throw new DAOException("Si e' verificato un errore durante l'accesso al DB");
        }
    }
}
