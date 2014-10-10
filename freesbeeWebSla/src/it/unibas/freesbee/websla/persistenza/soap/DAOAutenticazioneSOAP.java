package it.unibas.freesbee.websla.persistenza.soap;

import it.unibas.freesbee.websla.modello.ConfigurazioneStatico;
import it.unibas.freesbee.websla.modello.Utente;
import it.unibas.freesbee.websla.ws.security.SecurityUtil;
import it.unibas.freesbee.websla.ws.web.stub.IWSAutenticazione;
import it.unibas.freesbee.websla.ws.web.stub.WSAutenticazioneImplService;
import java.net.URL;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DAOAutenticazioneSOAP {

    private Log logger = LogFactory.getLog(this.getClass());
    private ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();

    public boolean autentica(Utente utente) throws DAOException {
        try {
           
            String url = UtilitaFile.costruisciURL(conf.getIndirizzoModuloSLA(), DAOCostanti.URL_WSDL_AUTENTICAZIONE);
            logger.info("URL di autenticazione "+ url);
            WSAutenticazioneImplService ss = new WSAutenticazioneImplService(new URL(url), DAOCostanti.SERVICE_NAME_AUTENTICAZIONE);
            IWSAutenticazione port = ss.getWSAutenticazioneImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);

            port.autentica(utente.getNomeUtente());
            return true;
        } catch (Exception e) {
            logger.error("Errore nella autenticazione nome utente o password errato. " + e);
            throw new DAOException("Errore nella autenticazione nome utente o password errato.");
        }
    }

    public boolean cambiaPassword(String nuovaPassword, Utente utente) throws DAOException {
        try {
            
            String url = UtilitaFile.costruisciURL(conf.getIndirizzoModuloSLA(), DAOCostanti.URL_WSDL_AUTENTICAZIONE);
            WSAutenticazioneImplService ss = new WSAutenticazioneImplService(new URL(url), DAOCostanti.SERVICE_NAME_AUTENTICAZIONE);
            IWSAutenticazione port = ss.getWSAutenticazioneImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);

            port.cambiaPassword(nuovaPassword, utente.getNomeUtente());
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Impossibile cambiare la password per l'utente " + utente.getNomeUtente() + " - " + ex);
            throw new DAOException("Impossibile cambiare la password per l'utente " + utente.getNomeUtente());
        }

    }
}
