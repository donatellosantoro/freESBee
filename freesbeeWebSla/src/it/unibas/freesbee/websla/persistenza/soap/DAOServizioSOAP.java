package it.unibas.freesbee.websla.persistenza.soap;

import it.unibas.freesbee.websla.modello.ConfigurazioneStatico;
import it.unibas.freesbee.websla.modello.Utente;
import it.unibas.freesbee.websla.ws.security.SecurityUtil;
import it.unibas.icar.freesbee.modello.Servizio;
import it.unibas.icar.freesbee.modello.Soggetto;
import it.unibas.freesbee.websla.ws.web.stub.ServiceServizio;

import it.unibas.freesbee.websla.ws.web.stub.ServiceServizio_Service;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.ServizioRS;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.SoggettoRS;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DAOServizioSOAP {

    private Log logger = LogFactory.getLog(this.getClass());
    private ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();

    public void aggiungiServizio(Utente utente, Servizio servizio, byte[] file) throws DAOException {
        try {
            String url = UtilitaFile.costruisciURL(conf.getIndirizzoModuloSLA(), DAOCostanti.URL_WSDL_SERVIZIO);
            ServiceServizio_Service ss = new ServiceServizio_Service(new URL(url), DAOCostanti.SERVICE_NAME_SERVIZIO);
            ServiceServizio port = ss.getServiceServizio();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);

            it.unibas.freesbee.websla.ws.web.stub.Servizio servizioStub = new it.unibas.freesbee.websla.ws.web.stub.Servizio();

            copiaProprietaServizioModelloToStub(servizioStub, servizio);
            port.addServizio(servizioStub, file);
        } catch (MalformedURLException ex) {
            logger.error("Impossibile impostare la comunicazione con il modulo di tracciatura " + ex);
            throw new DAOException(ex);
        } catch (Exception ex) {
            logger.error("Impossibile aggiungere il servizio " + ex);
            throw new DAOException(ex);
        }
    }

    public void updateServizioActive(Utente utente, Servizio servizio) throws DAOException {
        try {
            String url = UtilitaFile.costruisciURL(conf.getIndirizzoModuloSLA(), DAOCostanti.URL_WSDL_SERVIZIO);
            ServiceServizio_Service ss = new ServiceServizio_Service(new URL(url), DAOCostanti.SERVICE_NAME_SERVIZIO);
            ServiceServizio port = ss.getServiceServizio();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);

            it.unibas.freesbee.websla.ws.web.stub.Servizio servizioStub = new it.unibas.freesbee.websla.ws.web.stub.Servizio();

            copiaProprietaServizioModelloToStub(servizioStub, servizio);
            port.updateServizioActive(servizioStub);
        } catch (MalformedURLException ex) {
            logger.error("Impossibile impostare la comunicazione con il modulo di tracciatura " + ex);
            throw new DAOException(ex);
        } catch (Exception ex) {
            logger.error("Impossibile aggiungere il servizio " + ex);
            throw new DAOException(ex);
        }
    }

    public List<Servizio> getServiziNICANonMonitorati(Utente utente) throws DAOException {
        try {
            String url = UtilitaFile.costruisciURL(conf.getIndirizzoModuloSLA(), DAOCostanti.URL_WSDL_SERVIZIO);
            ServiceServizio_Service ss = new ServiceServizio_Service(new URL(url), DAOCostanti.SERVICE_NAME_SERVIZIO);
            ServiceServizio port = ss.getServiceServizio();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);

            List<it.unibas.freesbee.websla.ws.web.stub.Servizio> listaServizi = port.getServiziNicaNonMonitorati();

            List<Servizio> lista = new ArrayList<Servizio>();
            if (listaServizi == null) {
                return lista;
            }

            for (it.unibas.freesbee.websla.ws.web.stub.Servizio servizio : listaServizi) {
                Servizio s = new Servizio();
                copiaProprietaServizioStubToModello(s, servizio);
                lista.add(s);
            }
            return lista;
        } catch (MalformedURLException ex) {
            logger.error("Impossibile impostare la comunicazione con il modulo di tracciatura " + ex);
            throw new DAOException(ex);
        } catch (Exception ex) {
            logger.error("Impossibile ottenere la lista dei servizi presenti nel NICA " + ex);
            throw new DAOException(ex);
        }
    }

    public List<Servizio> getServiziInf2Erogati(Utente utente) throws DAOException {
        try {
            String url = UtilitaFile.costruisciURL(conf.getIndirizzoModuloSLA(), DAOCostanti.URL_WSDL_SERVIZIO);
            ServiceServizio_Service ss = new ServiceServizio_Service(new URL(url), DAOCostanti.SERVICE_NAME_SERVIZIO);
            ServiceServizio port = ss.getServiceServizio();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);

            List<it.unibas.freesbee.websla.ws.web.stub.Servizio> listaServizi = port.getServiziInf2Erogati();

            List<Servizio> lista = new ArrayList<Servizio>();
            if (listaServizi == null) {
                return lista;
            }
            for (it.unibas.freesbee.websla.ws.web.stub.Servizio servizio : listaServizi) {
                Servizio s = new Servizio();
                copiaProprietaServizioStubToModello(s, servizio);
                lista.add(s);
            }

            return lista;
        } catch (MalformedURLException ex) {
            logger.error("Impossibile impostare la comunicazione con il modulo di tracciatura " + ex);
            throw new DAOException(ex);
        } catch (Exception ex) {
            logger.error("Impossibile ottenere la lista dei servizi presenti nel modulo  freESBeeSLA " + ex);
            throw new DAOException(ex);
        }
    }

    public List<Servizio> getServiziInf2Fruiti(Utente utente) throws DAOException {
        try {
            String url = UtilitaFile.costruisciURL(conf.getIndirizzoModuloSLA(), DAOCostanti.URL_WSDL_SERVIZIO);
            ServiceServizio_Service ss = new ServiceServizio_Service(new URL(url), DAOCostanti.SERVICE_NAME_SERVIZIO);
            ServiceServizio port = ss.getServiceServizio();

                 SecurityUtil.settaIntestazioniSicurezza(utente, port);

            List<it.unibas.freesbee.websla.ws.web.stub.Servizio> listaServizi = port.getServiziInf2Fruiti();

            List<Servizio> lista = new ArrayList<Servizio>();
            if (listaServizi == null) {
                return lista;
            }
            for (it.unibas.freesbee.websla.ws.web.stub.Servizio servizio : listaServizi) {
                Servizio s = new Servizio();
                copiaProprietaServizioStubToModello(s, servizio);
                lista.add(s);
            }

            return lista;
        } catch (MalformedURLException ex) {
            logger.error("Impossibile impostare la comunicazione con il modulo di tracciatura " + ex);
            throw new DAOException(ex);
        } catch (Exception ex) {
            logger.error("Impossibile ottenere la lista dei servizi presenti nel modulo  freESBeeSLA " + ex);
            throw new DAOException(ex);
        }
    }

    public List<String> getParametriSla(Utente utente, Servizio servizio) throws DAOException {
        try {
            String url = UtilitaFile.costruisciURL(conf.getIndirizzoModuloSLA(), DAOCostanti.URL_WSDL_SERVIZIO);
            ServiceServizio_Service ss = new ServiceServizio_Service(new URL(url), DAOCostanti.SERVICE_NAME_SERVIZIO);
            ServiceServizio port = ss.getServiceServizio();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);

            it.unibas.freesbee.websla.ws.web.stub.Servizio servizioStub = new it.unibas.freesbee.websla.ws.web.stub.Servizio();
            copiaProprietaServizioModelloToStub(servizioStub, servizio);

            List<String> listaParametriSLA = port.getParametriSla(servizioStub);
            return listaParametriSLA;
        } catch (MalformedURLException ex) {
            logger.error("Impossibile impostare la comunicazione con il modulo di tracciatura " + ex);
            throw new DAOException(ex);
        } catch (Exception ex) {
            logger.error("Impossibile ottenere la lista dei parametri presenti nel modulo  freESBeeSLA " + ex);
            throw new DAOException(ex);
        }
    }

    private void copiaProprietaServizioStubToModello(Servizio servizio, it.unibas.freesbee.websla.ws.web.stub.Servizio servizioStub) {
        it.unibas.freesbee.websla.ws.web.stub.Soggetto soggettoErogatoreStub = servizioStub.getErogatore();
        Soggetto soggettoErogatore = new Soggetto();
        DAOSoggettoSOAP.copiaProprietaSoggettoStubSLAToModello(soggettoErogatore, soggettoErogatoreStub);

        List listaFruitori = new ArrayList();
        for (it.unibas.freesbee.websla.ws.web.stub.Soggetto soggettoFruitoreStub : servizioStub.getFruitori()) {
            Soggetto soggettoFruitore = new Soggetto();
            DAOSoggettoSOAP.copiaProprietaSoggettoStubSLAToModello(soggettoFruitore, soggettoFruitoreStub);
            listaFruitori.add(soggettoFruitore);
        }

        servizio.setNome(servizioStub.getNome());
        servizio.setTipo(servizioStub.getTipo());
        servizio.setErogatore(soggettoErogatore);
        servizio.setFruitori(listaFruitori);
//        servizio.setContatoreRichieste(servizioStub.getContatoreRichieste());
//        servizio.setActive(servizioStub.getActive()); // TODO [Michele] verificare
    }

    private void copiaProprietaServizioModelloToStub(it.unibas.freesbee.websla.ws.web.stub.Servizio servizioStub, Servizio servizio) {
        Soggetto soggettoErogatore = servizio.getErogatore();
        it.unibas.freesbee.websla.ws.web.stub.Soggetto soggettoErogatoreStub = new it.unibas.freesbee.websla.ws.web.stub.Soggetto();
        DAOSoggettoSOAP.copiaProprietaSoggettoModelloToStubSLA(soggettoErogatoreStub, soggettoErogatore);

        List listaFruitoriStub = new ArrayList();
        for (Soggetto soggettoFruitore : servizio.getFruitori()) {
            it.unibas.freesbee.websla.ws.web.stub.Soggetto soggettoFruitoreStub = new it.unibas.freesbee.websla.ws.web.stub.Soggetto();
            DAOSoggettoSOAP.copiaProprietaSoggettoModelloToStubSLA(soggettoFruitoreStub, soggettoFruitore);
            listaFruitoriStub.add(soggettoFruitoreStub);

            servizioStub.getFruitori().add(soggettoFruitoreStub);
        }

        servizioStub.setNome(servizio.getNome());
        servizioStub.setErogatore(soggettoErogatoreStub);
//        servizioStub.setActive(servizio.getActive()); // TODO [Michele] verificare
//        servizioStub.setStato(servizio.getStato()); // TODO [Michele] verificare
//        servizioStub.setContatoreRichieste(servizio.getContatoreRichieste()); // TODO [Michele] verificare

    }
}
