package it.unibas.freesbeesla.ws.web.stub;

import it.unibas.freesbeesla.tracciatura.modello.Utente;
import it.unibas.freesbeesla.ws.web.persistenza.soap.DAOException;
import it.unibas.freesbeesla.ws.web.persistenza.torque.DAOUtenteTorque;
import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ws.security.WSPasswordCallback;

public class ServerPasswordCallback implements CallbackHandler {

    private Log logger = LogFactory.getLog(this.getClass());
    private DAOUtenteTorque daoUtente;

    public ServerPasswordCallback(DAOUtenteTorque daoUtente) {
        this.daoUtente = daoUtente;
    }

    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        WSPasswordCallback pc = null;
        try {
            for (int i = 0; i < callbacks.length; i++) {
                pc = (WSPasswordCallback) callbacks[i];
                String nomeUtente = pc.getIdentifer();

                if (logger.isDebugEnabled()) {
                    logger.debug("Richiedo al dao " + daoUtente + " l'utente " + nomeUtente);
                }
                Utente utente = daoUtente.selectByNomeUtente(nomeUtente);
                if (utente != null) {
                    if (logger.isInfoEnabled()) {
                        logger.info("E' stato trovato l'utente " + utente.getUsername() + " nel DB");
                    }
                    pc.setPassword(utente.getPassword());
                } else {
                    logger.error("Si e' verificato un errore durante l'autenticazione dell'utente. Utente inesistente: " + nomeUtente);
                    throw new UnsupportedCallbackException(pc, "Si e' verificato un errore durante l'autenticazione dell'utente. Utente inesistente: " + nomeUtente);
                }
            }
        } catch (DAOException ex) {
            logger.error("Si e' verificato un errore durante l'autenticazione dell'utente " + ex);
            throw new UnsupportedCallbackException(pc, "Si e' verificato un errore durante l'autenticazione dell'utente " + ex);
        }
    }
}
