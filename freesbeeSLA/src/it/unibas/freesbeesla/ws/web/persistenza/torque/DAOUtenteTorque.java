package it.unibas.freesbeesla.ws.web.persistenza.torque;

import it.unibas.freesbeesla.tracciatura.modello.Utente;
import it.unibas.freesbeesla.tracciatura.modello.dao.UtentePeer;
import it.unibas.freesbeesla.ws.web.persistenza.soap.DAOException;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.torque.TorqueException;
import org.apache.torque.util.Criteria;

public class DAOUtenteTorque  {

    private static Log logger = LogFactory.getLog(DAOUtenteTorque.class);

    public Utente selectByNomeUtente(String nomeUtente) throws DAOException {
        try {
            Criteria criterio = new Criteria();
            criterio.add(UtentePeer.USERNAME, nomeUtente);
            List listaUtenti = UtentePeer.doSelect(criterio);

            if ((listaUtenti == null)) {
                logger.error("Impossibile accedere alla tabella degli utenti nel DB");
                throw new DAOException("Impossibile accedere alla tabella degli utenti nel DB");
            }

            if ((listaUtenti.size() == 0)) {
                logger.info("Utente non presente nel DB");
                throw new DAOException("Utente non presente nel DB");
            }

            Utente utenteTrovato = (Utente) listaUtenti.get(0);
            return utenteTrovato;
        } catch (TorqueException ex) {
            logger.error("Si e' verificato un errore durante l'accesso al DB " + ex);
            throw new DAOException("Si e' verificato un errore durante l'accesso al DB");
        }
    }

      public void updatePassword(Utente utente) throws DAOException {
      
      try {
           UtentePeer.doUpdate(utente);
        } catch (TorqueException ex) {
            logger.error("Si e' verificato un errore durante l'accesso al DB " + ex);
            throw new DAOException("Si e' verificato un errore durante l'accesso al DB");
        }
      }
 
   
}
