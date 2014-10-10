package it.unibas.freesbeesla.ws.web.persistenza.torque;

import it.unibas.freesbeesla.tracciatura.modello.SoggettoSLA;
import it.unibas.freesbeesla.tracciatura.modello.dao.SoggettoSLAPeer;
import it.unibas.freesbeesla.ws.web.persistenza.soap.DAOException;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.torque.TorqueException;
import org.apache.torque.util.Criteria;

public class DAOSoggettoSLATorque {

    private static Log logger = LogFactory.getLog(DAOSoggettoSLATorque.class);

    public SoggettoSLA selectByNomeSoggetto(String nome) throws DAOException {
        try {
            Criteria criterio = new Criteria();
            criterio.add(SoggettoSLAPeer.NOME_SOGGETTO, nome);
            List listaSoggetti = SoggettoSLAPeer.doSelect(criterio);

            if ((listaSoggetti == null)) {
                logger.error("Impossibile accedere alla tabella dei soggetti nel DB");
                throw new DAOException("Impossibile accedere alla tabella dei soggetti nel DB");
            }

            if ((listaSoggetti.size() == 0)) {
                logger.debug("Soggetto non presente nel DB");
                return null;
            }

            SoggettoSLA soggettoTrovato = (SoggettoSLA) listaSoggetti.get(0);
            return soggettoTrovato;
        } catch (TorqueException ex) {
            logger.error("Si e' verificato un errore durante l'accesso al DB " + ex);
            throw new DAOException("Si e' verificato un errore durante l'accesso al DB");
        }
    }

    public List<SoggettoSLA> selectAll() throws DAOException {
        try {
            List listaSoggetti = SoggettoSLAPeer.doSelect(new Criteria());

            if ((listaSoggetti == null)) {
                logger.error("Impossibile accedere alla tabella dei soggetti nel DB");
                throw new DAOException("Impossibile accedere alla tabella dei soggetti nel DB");
            }

            return listaSoggetti;

        } catch (TorqueException ex) {
            logger.error("Si e' verificato un errore durante l'accesso al DB " + ex);
            throw new DAOException("Si e' verificato un errore durante l'accesso al DB");
        }
    }

    public void doInsert(SoggettoSLA soggetto) throws DAOException {
        try {
            SoggettoSLAPeer.doInsert(soggetto);
        } catch (TorqueException ex) {
            logger.error("Si e' verificato un errore durante l'accesso al DB " + ex);
            throw new DAOException("Si e' verificato un errore durante l'accesso al DB");
        }
    }

    public void doDelete(SoggettoSLA soggetto) throws DAOException {
        try {
            SoggettoSLAPeer.doDelete(soggetto);
        } catch (TorqueException ex) {
            logger.error("Si e' verificato un errore durante l'accesso al DB " + ex);
            throw new DAOException("Si e' verificato un errore durante l'accesso al DB");
        }

    }
    

    public void deleteAll() throws DAOException {
        try {
            SoggettoSLAPeer.executeStatement("delete from " +SoggettoSLAPeer.TABLE_NAME);
        } catch (TorqueException ex) {
            logger.error("Si e' verificato un errore durante l'accesso al DB " + ex);
            throw new DAOException("Si e' verificato un errore durante l'accesso al DB");
        }

    }    
}
