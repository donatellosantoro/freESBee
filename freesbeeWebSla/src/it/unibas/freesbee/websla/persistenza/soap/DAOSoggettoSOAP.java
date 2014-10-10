package it.unibas.freesbee.websla.persistenza.soap;

import it.unibas.freesbee.websla.modello.ConfigurazioneStatico;
import it.unibas.icar.freesbee.modello.Soggetto;
import it.unibas.freesbee.websla.modello.Utente;
import it.unibas.freesbee.websla.ws.security.SecurityUtil;
import it.unibas.freesbee.websla.ws.web.stub.ServiceSoggetto;
import it.unibas.freesbee.websla.ws.web.stub.ServiceSoggetto_Service;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.SoggettoRS;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DAOSoggettoSOAP {

    private Log logger = LogFactory.getLog(this.getClass());
    private ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();

    public void aggiungiSoggetto(Utente utente, Soggetto soggetto) throws DAOException {
        try {
            String url = UtilitaFile.costruisciURL(conf.getIndirizzoModuloSLA(), DAOCostanti.URL_WSDL_SOGGETTO);
            ServiceSoggetto_Service ss = new ServiceSoggetto_Service(new URL(url), DAOCostanti.SERVICE_NAME_SOGGETTO);
            ServiceSoggetto port = ss.getServiceSoggetto();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);

            it.unibas.freesbee.websla.ws.web.stub.Soggetto soggettoStub = new it.unibas.freesbee.websla.ws.web.stub.Soggetto();

            copiaProprietaSoggettoModelloToStubSLA(soggettoStub, soggetto);
            port.addSoggettoInf2(soggettoStub);
        } catch (MalformedURLException ex) {
            logger.error("Impossibile impostare la comunicazione con il modulo di tracciatura " + ex);
            throw new DAOException(ex);
        } catch (Exception ex) {
            logger.error("Impossibile aggiungere il soggetto " + ex);
            throw new DAOException(ex);
        }
    }

    public void eliminaSoggetto(Utente utente, Soggetto soggetto) throws DAOException {
        try {
            String url = UtilitaFile.costruisciURL(conf.getIndirizzoModuloSLA(), DAOCostanti.URL_WSDL_SOGGETTO);
            ServiceSoggetto_Service ss = new ServiceSoggetto_Service(new URL(url), DAOCostanti.SERVICE_NAME_SOGGETTO);
            ServiceSoggetto port = ss.getServiceSoggetto();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);

            it.unibas.freesbee.websla.ws.web.stub.Soggetto soggettoStub = new it.unibas.freesbee.websla.ws.web.stub.Soggetto();

            copiaProprietaSoggettoModelloToStubSLA(soggettoStub, soggetto);
            port.removeSoggettoInf2(soggettoStub);
        } catch (MalformedURLException ex) {
            logger.error("Impossibile impostare la comunicazione con il modulo di tracciatura " + ex);
            throw new DAOException(ex);
        } catch (Exception ex) {
            logger.error("Impossibile eliminare il soggetto " + ex);
            throw new DAOException(ex);
        }
    }

    public List<Soggetto> getSoggettiNICA(Utente utente) throws DAOException {
        try {
            String url = UtilitaFile.costruisciURL(conf.getIndirizzoModuloSLA(), DAOCostanti.URL_WSDL_SOGGETTO);
            ServiceSoggetto_Service ss = new ServiceSoggetto_Service(new URL(url), DAOCostanti.SERVICE_NAME_SOGGETTO);
            ServiceSoggetto port = ss.getServiceSoggetto();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);

            List<it.unibas.freesbee.websla.ws.web.stub.Soggetto> listaSoggetti = port.getSoggettiNica();

            List<Soggetto> lista = new ArrayList<Soggetto>();
            if (listaSoggetti == null) {

                return lista;
            }
            for (it.unibas.freesbee.websla.ws.web.stub.Soggetto soggetto : listaSoggetti) {
                Soggetto s = new Soggetto();
                copiaProprietaSoggettoStubSLAToModello(s, soggetto);
                lista.add(s);
            }
            logger.info("Dimensione listaSoggettiNica " + listaSoggetti.size());
            return lista;
        } catch (MalformedURLException ex) {
            logger.error("Impossibile impostare la comunicazione con il modulo di tracciatura " + ex);
            throw new DAOException(ex);
        } catch (Exception ex) {
            logger.error("Impossibile ottenere la lista dei soggetti presenti nel NICA " + ex);
            throw new DAOException(ex);
        }
    }

    public List<Soggetto> getSoggettiInf2(Utente utente) throws DAOException {
        try {

            String url = UtilitaFile.costruisciURL(conf.getIndirizzoModuloSLA(), DAOCostanti.URL_WSDL_SOGGETTO);
            ServiceSoggetto_Service ss = new ServiceSoggetto_Service(new URL(url), DAOCostanti.SERVICE_NAME_SOGGETTO);
            ServiceSoggetto port = ss.getServiceSoggetto();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);

            List<it.unibas.freesbee.websla.ws.web.stub.Soggetto> listaSoggetti = port.getSoggettiInf2();


            List<Soggetto> lista = new ArrayList<Soggetto>();
            if (listaSoggetti == null) {

                return lista;
            }
            for (it.unibas.freesbee.websla.ws.web.stub.Soggetto soggetto : listaSoggetti) {
                Soggetto s = new Soggetto();
                copiaProprietaSoggettoStubSLAToModello(s, soggetto);
                lista.add(s);
            }

            logger.info("Dimensione listaSoggettiinf2 " + listaSoggetti.size());
            return lista;
        } catch (MalformedURLException ex) {
            logger.error("Impossibile impostare la comunicazione con il modulo di tracciatura " + ex);
            throw new DAOException(ex);
        } catch (Exception ex) {
            logger.error("Impossibile ottenere la lista dei soggetti presenti nel NICA " + ex);
            throw new DAOException(ex);
        }
    }


      public List<Soggetto> getSoggettiSLA(Utente utente, String nomeAccordo, String servizio, String tipo) throws DAOException {
        try {

            String url = UtilitaFile.costruisciURL(conf.getIndirizzoModuloSLA(), DAOCostanti.URL_WSDL_SOGGETTO);
            ServiceSoggetto_Service ss = new ServiceSoggetto_Service(new URL(url), DAOCostanti.SERVICE_NAME_SOGGETTO);
            ServiceSoggetto port = ss.getServiceSoggetto();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);

            List<it.unibas.freesbee.websla.ws.web.stub.Soggetto> listaSoggetti = port.getSoggettiSLA(nomeAccordo,servizio,tipo);


            List<Soggetto> lista = new ArrayList<Soggetto>();
            if (listaSoggetti == null) {

                return lista;
            }
            for (it.unibas.freesbee.websla.ws.web.stub.Soggetto soggetto : listaSoggetti) {
                Soggetto s = new Soggetto();
                copiaProprietaSoggettoStubSLAToModello(s, soggetto);
                lista.add(s);
            }

            logger.info("Dimensione listaSoggettiSLA " + listaSoggetti.size());
            return lista;
        } catch (MalformedURLException ex) {
            logger.error("Impossibile impostare la comunicazione con il modulo di tracciatura " + ex);
            throw new DAOException(ex);
        } catch (Exception ex) {
            logger.error("Impossibile ottenere la lista dei soggetti presenti nel NICA " + ex);
            throw new DAOException(ex);
        }
    }



    public static void copiaProprietaSoggettoStubSLAToModello(Soggetto soggetto, it.unibas.freesbee.websla.ws.web.stub.Soggetto soggettoStub) {
        soggetto.setId(soggettoStub.getId());
        soggetto.setNome(soggettoStub.getNome());
        soggetto.setTipo(soggettoStub.getTipo());
    }

    public static void copiaProprietaSoggettoModelloToStubSLA(it.unibas.freesbee.websla.ws.web.stub.Soggetto soggettoStub, Soggetto soggetto) {
        soggettoStub.setId(soggetto.getId());
        soggettoStub.setNome(soggetto.getNome());
        soggettoStub.setTipo(soggetto.getTipo());
    }

    public static void copiaProprietaSoggettoStubRegistroServiziToModello(Soggetto soggetto, SoggettoRS soggettoStub) {
//        soggetto.setId(soggettoStub.getId());
        soggetto.setNome(soggettoStub.getNome());
        soggetto.setTipo(soggettoStub.getTipo());
    }

    public static void copiaProprietaSoggettoModelloToStubRegistroServizi(SoggettoRS soggettoStub, Soggetto soggetto) {
//        soggettoStub.setId(soggetto.getId());
        soggettoStub.setNome(soggetto.getNome());
        soggettoStub.setTipo(soggetto.getTipo());
    }
}
