package it.unibas.freesbee.ge.web.persistenza.soap;

import it.unibas.freesbee.ge.web.modello.ConfigurazioneStatico;
import it.unibas.freesbee.ge.web.modello.Utente;
import it.unibas.freesbee.ge.web.utilita.Utilita;
import it.unibas.freesbee.ge.web.ws.security.SecurityUtil;
import it.unibas.freesbee.ge.web.ws.stub.CategoriaEventiEsterna;
import it.unibas.freesbee.ge.web.ws.stub.IWSCategoriaEventiEsterna;
import it.unibas.freesbee.ge.web.ws.stub.SOAPFaultException;
import it.unibas.freesbee.ge.web.ws.stub.WSCategoriaEventiEsternaImplService;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DAOCategoriaEventiEsternaSOAP {

    private ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();
    private Log logger = LogFactory.getLog(this.getClass());

    public void addCategoriaEventi(CategoriaEventiEsterna categoriaEventi, Utente utente) throws DAOException {
        try {
            String url = Utilita.costruisciURL(conf.getIndirizzoGE(), DAOCostanti.URL_WSDL_WEB_CATEGORIA_EVENTI_ESTERNA);
            logger.info("URL di Categiria Evento " + url);
            WSCategoriaEventiEsternaImplService ss = new WSCategoriaEventiEsternaImplService(new URL(url), DAOCostanti.SERVICE_NAME_CATEGORIA_EVENTI_ESTERNA);
            IWSCategoriaEventiEsterna port = ss.getWSCategoriaEventiEsternaImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);
            port.addCategoriaEventiEsterna(categoriaEventi);

        } catch (SOAPFaultException e) {
            logger.error("Impossibile salvare la categoria di eventi " + e.getFaultInfo().getDescription());
            throw new DAOException(e.getFaultInfo().getDescription());
        } catch (MalformedURLException t) {
            logger.error("Impossibile salvare la categoria di eventi ");
            throw new DAOException(t);
        }
    }

    public void updateCategoriaEventi(CategoriaEventiEsterna categoriaEventi, Utente utente) throws DAOException {
        try {
            String url = Utilita.costruisciURL(conf.getIndirizzoGE(), DAOCostanti.URL_WSDL_WEB_CATEGORIA_EVENTI_ESTERNA);
            logger.info("URL di Categiria Evento " + url);
            WSCategoriaEventiEsternaImplService ss = new WSCategoriaEventiEsternaImplService(new URL(url), DAOCostanti.SERVICE_NAME_CATEGORIA_EVENTI_ESTERNA);
            IWSCategoriaEventiEsterna port = ss.getWSCategoriaEventiEsternaImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);
            port.updateCategoriaEventiEsterna(categoriaEventi);

        } catch (SOAPFaultException e) {
            logger.error("Impossibile aggiornare la categoria di eventi " + e.getFaultInfo().getDescription());
            throw new DAOException(e.getFaultInfo().getDescription());
        } catch (MalformedURLException t) {
            logger.error("Impossibile aggiornare la categoria di eventi ");
            throw new DAOException(t);
        }
    }

    public List<CategoriaEventiEsterna> getListaCategoriaEventi(Utente utente) throws DAOException {
        try {
            String url = Utilita.costruisciURL(conf.getIndirizzoGE(), DAOCostanti.URL_WSDL_WEB_CATEGORIA_EVENTI_ESTERNA);
            logger.info("URL di Categiria Evento " + url);
            WSCategoriaEventiEsternaImplService ss = new WSCategoriaEventiEsternaImplService(new URL(url), DAOCostanti.SERVICE_NAME_CATEGORIA_EVENTI_ESTERNA);
            IWSCategoriaEventiEsterna port = ss.getWSCategoriaEventiEsternaImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);
            List<CategoriaEventiEsterna> lista = port.getListaCategoriaEventiEsterne();
            return lista;
        } catch (SOAPFaultException e) {
            logger.error("Impossibile caricare la lista delle categorie di eventi " + e.getFaultInfo().getDescription());
            throw new DAOException(e.getFaultInfo().getDescription());
        } catch (MalformedURLException t) {
            logger.error("Impossibile caricare la lista delle categorie di eventi ");
            throw new DAOException(t);
        }
    }

    public CategoriaEventiEsterna getCategoriaEventi(Utente utente, String categoriaEventi) throws DAOException {
        try {
            String url = Utilita.costruisciURL(conf.getIndirizzoGE(), DAOCostanti.URL_WSDL_WEB_CATEGORIA_EVENTI_ESTERNA);
            logger.info("URL di Categiria Evento " + url);
            WSCategoriaEventiEsternaImplService ss = new WSCategoriaEventiEsternaImplService(new URL(url), DAOCostanti.SERVICE_NAME_CATEGORIA_EVENTI_ESTERNA);
            IWSCategoriaEventiEsterna port = ss.getWSCategoriaEventiEsternaImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);
            CategoriaEventiEsterna lista = port.getCategoriaEventiEsterna(categoriaEventi);
            return lista;

        } catch (SOAPFaultException e) {
            logger.error("Impossibile caricare la categoria di eventi " + e.getFaultInfo().getDescription());
            throw new DAOException(e.getFaultInfo().getDescription());
        } catch (MalformedURLException t) {
            logger.error("Impossibile caricare la categoria di eventi ");
            throw new DAOException(t);
        }
    }
}
