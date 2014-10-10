package it.unibas.freesbee.ge.web.persistenza.soap;

import it.unibas.freesbee.ge.web.modello.ConfigurazioneStatico;
import it.unibas.freesbee.ge.web.modello.Utente;
import it.unibas.freesbee.ge.web.utilita.Utilita;
import it.unibas.freesbee.ge.web.ws.security.SecurityUtil;
import it.unibas.freesbee.ge.web.ws.stub.GestoreEventi;
import it.unibas.freesbee.ge.web.ws.stub.IWSGestioneGestoreEventi;
import it.unibas.freesbee.ge.web.ws.stub.WSGestioneGestoreEventiImplService;
import java.net.URL;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DAOGestoreEventiSOAP {

    private ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();
    private Log logger = LogFactory.getLog(this.getClass());

    public void addGestoreEventi(GestoreEventi gestoreEventi, Utente utente) throws DAOException {
        try {
            String url = Utilita.costruisciURL(conf.getIndirizzoGE(), DAOCostanti.URL_WSDL_WEB_GESTORE_EVENTI);
            logger.info("URL di Categiria Evento " + url);
            WSGestioneGestoreEventiImplService ss = new WSGestioneGestoreEventiImplService(new URL(url), DAOCostanti.SERVICE_NAME_GESTORE_EVENTI);
            IWSGestioneGestoreEventi port = ss.getWSGestioneGestoreEventiImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);
            port.addGestoreEventi(gestoreEventi);

        } catch (Exception e) {
            logger.error("Impossibile salvare il gestore eventi " + e.getMessage());
            throw new DAOException(e);
        }
    }

    public void updateGestoreEventi(GestoreEventi gestoreEventi, Utente utente) throws DAOException {
        try {
            String url = Utilita.costruisciURL(conf.getIndirizzoGE(), DAOCostanti.URL_WSDL_WEB_GESTORE_EVENTI);
            logger.info("URL di Categiria Evento " + url);
            WSGestioneGestoreEventiImplService ss = new WSGestioneGestoreEventiImplService(new URL(url), DAOCostanti.SERVICE_NAME_GESTORE_EVENTI);
            IWSGestioneGestoreEventi port = ss.getWSGestioneGestoreEventiImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);
            port.updateGestoriEventi(gestoreEventi);
        } catch (Exception e) {
            logger.error("Impossibile aggiornare il gestore eventi " + e.getMessage());
            throw new DAOException(e);
        }
    }

    public List<GestoreEventi> getListaGestoreEventi(Utente utente) throws DAOException {
        try {
            String url = Utilita.costruisciURL(conf.getIndirizzoGE(), DAOCostanti.URL_WSDL_WEB_GESTORE_EVENTI);
            logger.info("URL di Categiria Evento " + url);
            WSGestioneGestoreEventiImplService ss = new WSGestioneGestoreEventiImplService(new URL(url), DAOCostanti.SERVICE_NAME_GESTORE_EVENTI);
            IWSGestioneGestoreEventi port = ss.getWSGestioneGestoreEventiImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);
            List<GestoreEventi> lista = port.getListaGestoriEventi();
            return lista;
        } catch (Exception e) {
            logger.error("Impossibile caricare la lista dei gestori eventi " + e.getMessage());
            throw new DAOException(e);
        }
    }
}
