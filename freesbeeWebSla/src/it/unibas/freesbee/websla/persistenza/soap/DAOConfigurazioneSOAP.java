package it.unibas.freesbee.websla.persistenza.soap;

import it.unibas.freesbee.websla.modello.ConfigurazioneStatico;
import it.unibas.freesbee.websla.modello.Utente;
import it.unibas.freesbee.websla.ws.security.SecurityUtil;
import it.unibas.freesbee.websla.ws.web.stub.DatiConfigurazione;
import it.unibas.freesbee.websla.ws.web.stub.SOAPFault_Exception;
import it.unibas.freesbee.websla.ws.web.stub.ServiceConfigura;
import it.unibas.freesbee.websla.ws.web.stub.ServiceConfigura_Service;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DAOConfigurazioneSOAP {

    private Log logger = LogFactory.getLog(DAOConfigurazioneSOAP.class);
    private ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();

    public String verificaTipoNICA(Utente utente) throws DAOException {
        try {
            String url = UtilitaFile.costruisciURL(conf.getIndirizzoModuloSLA(), DAOCostanti.URL_WSDL_CONFIGURAZIONE);
            ServiceConfigura_Service ss = new ServiceConfigura_Service(new URL(url), DAOCostanti.SERVICE_NAME_CONFIGURAZIONE);
            ServiceConfigura port = ss.getServiceConfigura();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);

            String tipoNICA = port.verificaNica();

            return tipoNICA;
        } catch (SOAPFault_Exception ex) {
            logger.error("SOAP FAULT ERROR:Impossibile verificare la comunicazione con il NICA " + ex);
            throw new DAOException(ex);
        } catch (MalformedURLException ex) {
            logger.error("MALFORMED URL ERROR:Impossibile verificare la comunicazione con il NICA " + ex);
            throw new DAOException(ex);
        }
    }

    public DatiConfigurazione caricaDatiConfigurazione(Utente utente) throws DAOException{
      try {
            String url = UtilitaFile.costruisciURL(conf.getIndirizzoModuloSLA(), DAOCostanti.URL_WSDL_CONFIGURAZIONE);
            ServiceConfigura_Service ss = new ServiceConfigura_Service(new URL(url), DAOCostanti.SERVICE_NAME_CONFIGURAZIONE);
            ServiceConfigura port = ss.getServiceConfigura();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);

            return port.caricaConfigurazione();
        } catch (SOAPFault_Exception ex) {
            logger.error("SOAP FAULT ERROR:Impossibile caricare la configurazione del modulo degli SLA " + ex);
            throw new DAOException(ex);
        } catch (MalformedURLException ex) {
            logger.error("MALFORMED URL ERROR:Impossibile caricare la configurazione del modulo degli SLA " + ex);
            throw new DAOException(ex);
        }

    }

   public void modificaDatiConfigurazioneSLA(Utente utente, DatiConfigurazione dati) throws DAOException{
      try {
            String url = UtilitaFile.costruisciURL(conf.getIndirizzoModuloSLA(), DAOCostanti.URL_WSDL_CONFIGURAZIONE);
            ServiceConfigura_Service ss = new ServiceConfigura_Service(new URL(url), DAOCostanti.SERVICE_NAME_CONFIGURAZIONE);
            ServiceConfigura port = ss.getServiceConfigura();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);

            port.modificaConfigurazioneSLA(dati);
        } catch (SOAPFault_Exception ex) {
            logger.error("SOAP FAULT ERROR:Impossibile modificare la configurazione del modulo degli SLA " + ex);
            throw new DAOException(ex);
        } catch (MalformedURLException ex) {
            logger.error("MALFORMED URL ERROR:Impossibile modificare la configurazione del modulo degli SLA " + ex);
            throw new DAOException(ex);
        }

    }

      public void modificaDatiConfigurazioneSP(Utente utente, DatiConfigurazione dati) throws DAOException{
      try {
            String url = UtilitaFile.costruisciURL(conf.getIndirizzoModuloSLA(), DAOCostanti.URL_WSDL_CONFIGURAZIONE);
            ServiceConfigura_Service ss = new ServiceConfigura_Service(new URL(url), DAOCostanti.SERVICE_NAME_CONFIGURAZIONE);
            ServiceConfigura port = ss.getServiceConfigura();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);

            port.modificaConfigurazioneSP(dati);
        } catch (SOAPFault_Exception ex) {
            logger.error("SOAP FAULT ERROR:Impossibile modificare SP la configurazione del modulo degli SLA " + ex);
            throw new DAOException(ex);
        } catch (MalformedURLException ex) {
            logger.error("MALFORMED URL ERROR:Impossibile modificare SP la configurazione del modulo degli SLA " + ex);
            throw new DAOException(ex);
        }

    }
}
