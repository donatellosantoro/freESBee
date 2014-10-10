package it.unibas.freesbeesla.ws.web;

import it.unibas.freesbeesla.tracciatura.modello.Utente;
import it.unibas.freesbeesla.tracciatura.ws.server.SOAPFault;
import it.unibas.freesbeesla.ws.web.persistenza.soap.DAOException;
import it.unibas.freesbeesla.ws.web.persistenza.torque.DAOUtenteTorque;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.torque.Torque;

@javax.jws.WebService(endpointInterface = "it.unibas.freesbeesla.ws.web.IWSAutenticazione")
public class WSAutenticazioneImpl implements IWSAutenticazione {

    private static Log logger = LogFactory.getLog(WSAutenticazioneImpl.class);
    private DAOUtenteTorque daoUtente = new DAOUtenteTorque();

    static {
        try {
            Torque.init("torque-runtime.properties");
        } catch (Exception e) {
            logger.error("Errore nella inizializzazione di torque " + e);
        }
    }

    public WSAutenticazioneImpl() {
    }

    public void autentica(String nomeUtente) throws SOAPFault {
       
        if (logger.isInfoEnabled()) {
            logger.info("Richiesta l'autenticazione di un utente");
        }
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("Richiedo al dao " + daoUtente + " l'utente " + nomeUtente);
            }
            Utente utente = daoUtente.selectByNomeUtente(nomeUtente);
            if (utente == null) {
                throw new SOAPFault("Si e' verificato un errore durante l'autenticazione dell'utente. Utente inesistente: " + nomeUtente);
            }
        } catch (DAOException ex) {
            logger.error("Si e' verificato un errore durante l'autenticazione dell'utente" + ex);
            throw new SOAPFault("Si e' verificato un errore durante l'autenticazione dell'utente" + ex);
        }
    }

    public void cambiaPassword(String nuovaPassword, String nomeUtente) throws SOAPFault {
        if (logger.isInfoEnabled()) {
            logger.info("Richiesta la modifica della password di un utente");
        }
        try {
            Utente utente = daoUtente.selectByNomeUtente(nomeUtente);
            if (utente == null) {
                throw new SOAPFault("Si e' verificato un errore durante l'autenticazione dell'utente. Utente inesistente");
            }
            utente.setPassword(nuovaPassword);
            daoUtente.updatePassword(utente);
        } catch (DAOException ex) {
            logger.error("Si e' verificato un errore durante la modifica della password dell'utente" + ex);
            throw new SOAPFault("Si e' verificato un errore durante la modifica della password dell'utente" + ex);
        }
    }
}
