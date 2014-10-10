package it.unibas.freesbee.ge.web.persistenza.soap;

import it.unibas.freesbee.ge.web.modello.ConfigurazioneStatico;
import it.unibas.freesbee.ge.web.modello.Utente;
import it.unibas.freesbee.ge.web.utilita.Utilita;
import it.unibas.freesbee.ge.web.ws.security.SecurityUtil;
import it.unibas.freesbee.ge.web.ws.stub.DatiConfigurazione;
import it.unibas.freesbee.ge.web.ws.stub.IWSConfigura;
import it.unibas.freesbee.ge.web.ws.stub.SOAPFaultException;
import it.unibas.freesbee.ge.web.ws.stub.WSConfiguraImplService;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DAOConfigurazioneSOAP {

    private Log logger = LogFactory.getLog(DAOConfigurazioneSOAP.class);
    private ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();

    public DatiConfigurazione caricaDatiConfigurazione(Utente utente) throws DAOException {
        try {
            String url = Utilita.costruisciURL(conf.getIndirizzoGE(), DAOCostanti.URL_WSDL_WEB_CONFIGURAZIONE);
            WSConfiguraImplService ss = new WSConfiguraImplService(new URL(url), DAOCostanti.SERVICE_NAME_CONFIGURAZIONE);
            IWSConfigura port = ss.getWSConfiguraImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);

            return port.caricaConfigurazione();
            
        } catch (SOAPFaultException ex) {
            logger.error("SOAP FAULT ERROR:Impossibile caricare la configurazione del modulo del GE " + ex);
            throw new DAOException(ex);
        } catch (MalformedURLException ex) {
            logger.error("MALFORMED URL ERROR:Impossibile caricare la configurazione del modulo del GE " + ex);
            throw new DAOException(ex);
        }

    }

    public void modificaDatiConfigurazioneGE(Utente utente, DatiConfigurazione dati) throws DAOException {
        try {
            String url = Utilita.costruisciURL(conf.getIndirizzoGE(), DAOCostanti.URL_WSDL_WEB_CONFIGURAZIONE);
            WSConfiguraImplService ss = new WSConfiguraImplService(new URL(url), DAOCostanti.SERVICE_NAME_CONFIGURAZIONE);
            IWSConfigura port = ss.getWSConfiguraImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);

            port.modificaConfigurazioneGE(dati);
        } catch (SOAPFaultException ex) {
            logger.error("SOAP FAULT ERROR:Impossibile modificare la configurazione del modulo del GE" + ex);
            throw new DAOException(ex);
        } catch (MalformedURLException ex) {
            logger.error("MALFORMED URL ERROR:Impossibile modificare la configurazione del modulo del GE " + ex);
            throw new DAOException(ex);
        }

    }

    public void modificaDatiConfigurazioneSP(Utente utente, DatiConfigurazione dati) throws DAOException {
        try {
            String url = Utilita.costruisciURL(conf.getIndirizzoGE(), DAOCostanti.URL_WSDL_WEB_CONFIGURAZIONE);
            WSConfiguraImplService ss = new WSConfiguraImplService(new URL(url), DAOCostanti.SERVICE_NAME_CONFIGURAZIONE);
            IWSConfigura port = ss.getWSConfiguraImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);

            port.modificaConfigurazioneSP(dati);
        } catch (SOAPFaultException ex) {
            logger.error("SOAP FAULT ERROR:Impossibile modificare SP la configurazione del modulo del GE " + ex);
            throw new DAOException(ex);
        } catch (MalformedURLException ex) {
            logger.error("MALFORMED URL ERROR:Impossibile modificare SP la configurazione del modulo del GE " + ex);
            throw new DAOException(ex);
        }

    }
}
