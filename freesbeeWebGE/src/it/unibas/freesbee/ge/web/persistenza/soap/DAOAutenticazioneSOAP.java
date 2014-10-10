package it.unibas.freesbee.ge.web.persistenza.soap;

import it.unibas.freesbee.ge.web.modello.ConfigurazioneStatico;
import it.unibas.freesbee.ge.web.modello.Utente;
import it.unibas.freesbee.ge.web.persistenza.soap.DAOException;
import it.unibas.freesbee.ge.web.utilita.Utilita;
import it.unibas.freesbee.ge.web.ws.stub.WSAutenticazioneImplService;
import it.unibas.freesbee.ge.web.ws.security.SecurityUtil;
import it.unibas.freesbee.ge.web.ws.stub.IWSAutenticazione;
import java.net.URL;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DAOAutenticazioneSOAP {

    private Log logger = LogFactory.getLog(this.getClass());
    private ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();

    public boolean autentica(Utente utente) throws DAOException {
        try {
            String url = Utilita.costruisciURL(conf.getIndirizzoGE(), DAOCostanti.URL_WSDL_WEB_AUTENTICAZIONE);
            logger.info("URL di autenticazione " + url);
            WSAutenticazioneImplService ss = new WSAutenticazioneImplService(new URL(url), DAOCostanti.SERVICE_NAME_AUTENTICAZIONE);
            IWSAutenticazione port = ss.getWSAutenticazioneImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);

            port.autentica(utente.getNomeUtente());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Impossibile impostare la comunicazione con il Gestore Eventi. " + e);
            throw new DAOException("Impossibile impostare la comunicazione con il Gestore Eventi");
        }

    }

    public boolean cambiaPassword(String nuovaPassword, Utente utente) throws DAOException {
        try {

            String url = Utilita.costruisciURL(conf.getIndirizzoGE(), DAOCostanti.URL_WSDL_WEB_AUTENTICAZIONE);
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
