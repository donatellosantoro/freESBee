package it.unibas.freesbee.ge.web.persistenza.soap;

import it.unibas.freesbee.ge.web.modello.ConfigurazioneStatico;
import it.unibas.freesbee.ge.web.modello.Utente;
import it.unibas.freesbee.ge.web.utilita.Utilita;
import it.unibas.freesbee.ge.web.ws.security.SecurityUtil;
import it.unibas.freesbee.ge.web.ws.stub.CategoriaEventiInterna;
import it.unibas.freesbee.ge.web.ws.stub.IWSCategoriaEventiInterna;
import it.unibas.freesbee.ge.web.ws.stub.SOAPFaultException;
import it.unibas.freesbee.ge.web.ws.stub.WSCategoriaEventiInternaImplService;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DAOCategoriaEventiInternaSOAP {

    private ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();
    private Log logger = LogFactory.getLog(this.getClass());

    public void addCategoriaEventi(CategoriaEventiInterna categoriaEventi, Utente utente) throws DAOException {
        try {
            String url = Utilita.costruisciURL(conf.getIndirizzoGE(), DAOCostanti.URL_WSDL_WEB_CATEGORIA_EVENTI_INTERNA);
            logger.info("URL di Categiria Evento " + url);
            WSCategoriaEventiInternaImplService ss = new WSCategoriaEventiInternaImplService(new URL(url), DAOCostanti.SERVICE_NAME_CATEGORIA_EVENTI_INTERNA);
            IWSCategoriaEventiInterna port = ss.getWSCategoriaEventiInternaImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);
            port.addCategoriaEventiInterna(categoriaEventi);

        } catch (SOAPFaultException e) {
            logger.error("Impossibile salvare la categoria di eventi " + e.getFaultInfo().getDescription());
            throw new DAOException(e.getFaultInfo().getDescription());
        } catch (MalformedURLException t) {
            logger.error("Impossibile salvare la categoria di eventi ");
            throw new DAOException(t);
        }
    }

    public void updateCategoriaEventi(CategoriaEventiInterna categoriaEventi, Utente utente) throws DAOException {
        try {
            String url = Utilita.costruisciURL(conf.getIndirizzoGE(), DAOCostanti.URL_WSDL_WEB_CATEGORIA_EVENTI_INTERNA);
            logger.info("URL di Categiria Evento " + url);
            WSCategoriaEventiInternaImplService ss = new WSCategoriaEventiInternaImplService(new URL(url), DAOCostanti.SERVICE_NAME_CATEGORIA_EVENTI_INTERNA);
            IWSCategoriaEventiInterna port = ss.getWSCategoriaEventiInternaImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);
            port.updateCategoriaEventiInterna(categoriaEventi);
        } catch (SOAPFaultException e) {
            logger.error("Impossibile aggiornare la categoria di eventi " + e.getFaultInfo().getDescription());
            throw new DAOException(e.getFaultInfo().getDescription());
        } catch (MalformedURLException t) {
            logger.error("Impossibile aggiornare la categoria di eventi ");
            throw new DAOException(t);
        }
    }

    public List<CategoriaEventiInterna> getListaCategoriaEventi(Utente utente) throws DAOException {
        try {
            String url = Utilita.costruisciURL(conf.getIndirizzoGE(), DAOCostanti.URL_WSDL_WEB_CATEGORIA_EVENTI_INTERNA);
            logger.info("URL di Categiria Evento " + url);
            WSCategoriaEventiInternaImplService ss = new WSCategoriaEventiInternaImplService(new URL(url), DAOCostanti.SERVICE_NAME_CATEGORIA_EVENTI_INTERNA);
            IWSCategoriaEventiInterna port = ss.getWSCategoriaEventiInternaImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);
            List<CategoriaEventiInterna> lista = port.getListaCategoriaEventiInterne();
            return lista;

        } catch (SOAPFaultException e) {
            logger.error("Impossibile caricare la lista delle categorie di eventi " + e.getFaultInfo().getDescription());
            throw new DAOException(e.getFaultInfo().getDescription());
        } catch (MalformedURLException t) {
            logger.error("Impossibile caricare la lista delle categorie di eventi ");
            throw new DAOException(t);
        }
    }

    public CategoriaEventiInterna getCategoriaEventi(Utente utente, String categoriaEventi) throws DAOException {
        try {
            String url = Utilita.costruisciURL(conf.getIndirizzoGE(), DAOCostanti.URL_WSDL_WEB_CATEGORIA_EVENTI_INTERNA);
            logger.info("URL di Categiria Evento " + url);
            WSCategoriaEventiInternaImplService ss = new WSCategoriaEventiInternaImplService(new URL(url), DAOCostanti.SERVICE_NAME_CATEGORIA_EVENTI_INTERNA);
            IWSCategoriaEventiInterna port = ss.getWSCategoriaEventiInternaImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);
            CategoriaEventiInterna lista = port.getCategoriaEventiInterna(categoriaEventi);
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
