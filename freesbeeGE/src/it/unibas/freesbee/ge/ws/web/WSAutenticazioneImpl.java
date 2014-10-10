package it.unibas.freesbee.ge.ws.web;

import it.unibas.freesbee.ge.ws.gestoreeventi.SOAPFault;
import it.unibas.freesbee.ge.modello.Utente;
import it.unibas.freesbee.ge.persistenza.DAOException;
import it.unibas.freesbee.ge.persistenza.IDAOUtente;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOUtenteHibernate;
import javax.jws.WebMethod;
import javax.jws.WebService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.interceptor.InInterceptors;
import org.apache.cxf.interceptor.OutFaultInterceptors;

@WebService(targetNamespace = "http://ge.freesbee.unibas.it/")
@InInterceptors(interceptors = {"it.unibas.freesbee.ge.persistenza.hibernate.HibernateInterceptor", "it.unibas.freesbee.ge.ws.web.WSSecurityInterceptor"})
@OutFaultInterceptors(interceptors = {"it.unibas.freesbee.ge.persistenza.hibernate.HibernateInterceptorFault"})
public class WSAutenticazioneImpl implements IWSAutenticazione{

    private static Log logger = LogFactory.getLog(WSAutenticazioneImpl.class);
    private IDAOUtente daoUtente;

    public WSAutenticazioneImpl() {
        daoUtente = new DAOUtenteHibernate();
    }

    public WSAutenticazioneImpl(IDAOUtente daoUtente) {
        this.daoUtente = daoUtente;
    }

    @WebMethod(operationName = "autentica")
    public void autentica(String nomeUtente) throws SOAPFault {
        if (logger.isInfoEnabled()) {
            logger.info("Richiesta l'autenticazione di un utente");
        }
        try {
            if (logger.isDebugEnabled()) {

                logger.info("Richiedo al dao " + daoUtente + " l'utente " + nomeUtente);
            }
            Utente utente = daoUtente.findByNomeUtente(nomeUtente);
            if (utente == null) {
                throw new SOAPFault("Si e' verificato un errore durante l'autenticazione dell'utente. Utente inesistente: " + nomeUtente);
            }
        } catch (DAOException ex) {
            logger.error("Si e' verificato un errore durante l'autenticazione dell'utente" + ex);
            throw new SOAPFault("Si e' verificato un errore durante l'autenticazione dell'utente" + ex);
        }
    }

    @WebMethod(operationName = "cambiaPassword")
    public void cambiaPassword(String nuovaPassword, String nomeUtente) throws SOAPFault {
        if (logger.isInfoEnabled()) {
            logger.info("Richiesta la modifica della password di un utente");
        }
        try {
            Utente utente = daoUtente.findByNomeUtente(nomeUtente);
            if (utente == null) {
                throw new SOAPFault("Si e' verificato un errore durante l'autenticazione dell'utente. Utente inesistente");
            }
            utente.setPassword(nuovaPassword);
            daoUtente.makePersistent(utente);
        } catch (DAOException ex) {
            logger.error("Si e' verificato un errore durante la modifica della password dell'utente" + ex);
            throw new SOAPFault("Si e' verificato un errore durante la modifica della password dell'utente" + ex);
        }
    }
}
