package it.unibas.icar.freesbee.persistenza.soap;

import it.unibas.icar.freesbee.modello.Utente;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOAutenticazione;
import it.unibas.icar.freesbee.ws.client.autenticazione.cxf.IWSAutenticazione;
import it.unibas.icar.freesbee.ws.client.autenticazione.cxf.WSAutenticazioneImplService;
import it.unibas.icar.freesbee.ws.security.SecurityUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DAOAutenticazioneSOAP implements IDAOAutenticazione {

    private Log logger = LogFactory.getLog(this.getClass());

    public boolean autentica(Utente utente) throws DAOException {
        try {
            logger.info("Richiedo a: " + utente.getServer() + DAOCostanti.URL_WSDL_AUTENTICAZIONE);
            logger.info("Username: " + utente.getNomeUtente());
            logger.info("Password: " + utente.getPassword());
            WSAutenticazioneImplService ss = new WSAutenticazioneImplService(new URL(utente.getServer() + DAOCostanti.URL_WSDL_AUTENTICAZIONE), DAOCostanti.SERVICE_NAME_AUTENTICAZIONE);
            IWSAutenticazione port = ss.getWSAutenticazioneImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);
           
            port.autentica(utente.getNomeUtente());

            return true;
        } catch (Exception ex) {
            if (logger.isDebugEnabled()) {
                ex.printStackTrace();
            }
            logger.error("Impossibile autenticare l'utente " + utente.getNomeUtente() + " - " + ex);
            throw new DAOException(ex);
        }
    }
    
    public String getRuolo(Utente utente) throws DAOException {
         try {
            logger.info("Richiedo a: " + utente.getServer() + DAOCostanti.URL_WSDL_AUTENTICAZIONE);
            logger.info("Username: " + utente.getNomeUtente());
            WSAutenticazioneImplService ss = new WSAutenticazioneImplService(new URL(utente.getServer() + DAOCostanti.URL_WSDL_AUTENTICAZIONE), DAOCostanti.SERVICE_NAME_AUTENTICAZIONE);
            IWSAutenticazione port = ss.getWSAutenticazioneImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);
            
            return port.getRuolo(utente.getNomeUtente());
        } catch (Exception ex) {
            if (logger.isDebugEnabled()) {
                ex.printStackTrace();
            }
            logger.error("Impossibile autenticare l'utente " + utente.getNomeUtente() + " - " + ex);
            throw new DAOException(ex);
        }
    }

  

    public boolean cambiaPassword(String nuovaPassword, Utente utente) throws DAOException {
        try {
            WSAutenticazioneImplService ss = new WSAutenticazioneImplService(new URL(utente.getServer() + DAOCostanti.URL_WSDL_AUTENTICAZIONE), DAOCostanti.SERVICE_NAME_AUTENTICAZIONE);
            IWSAutenticazione port = ss.getWSAutenticazioneImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);

            port.cambiaPassword(nuovaPassword, utente.getNomeUtente());
            return true;
        } catch (Exception ex) {
            if (logger.isDebugEnabled()) {
                ex.printStackTrace();
            }
            logger.error("Impossibile cambiare la password per l'utente " + utente.getNomeUtente() + " - " + ex);
            throw new DAOException(ex);
        }

    }

    public Utente findById(Utente utente, Long id, boolean lock) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Utente> findAll(Utente utente) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Utente> findAll(Utente utente, int offset, int limite) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Utente makePersistent(Utente utente, Utente entity) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void makeTransient(Utente utente, Utente entity) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void lock(Utente utente, Utente entity) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
}
