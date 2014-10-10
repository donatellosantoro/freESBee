package it.unibas.icar.interfreesbee.freesbee.registroservizi;

import com.google.inject.ImplementedBy;
import it.unibas.icar.interfreesbee.freesbee.registroservizi.adapter.FreesbeeToNica;
import it.unibas.icar.interfreesbee.freesbee.registroservizi.adapter.NicaToFreesbee;
import it.unibas.icar.freesbee.utilita.CostantiSOAP;
import it.unibas.icar.freesbee.persistenza.SOAPFault;
import it.unibas.icar.freesbee.ws.registroservizi.AccordoServizioRS;
import it.unibas.icar.freesbee.ws.registroservizi.AccordoServizioRSRisposta;
import it.unibas.icar.freesbee.ws.registroservizi.ServizioRS;
import it.unibas.icar.freesbee.ws.registroservizi.ServizioRSRisposta;
import it.unibas.icar.freesbee.ws.registroservizi.SoggettoRS;
import it.unibas.icar.freesbee.ws.registroservizi.SoggettoRSRisposta;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import javax.xml.rpc.ServiceException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openspcoop.dao.registry.SoggettoSpcoop;
import org.openspcoop.dao.registry.ServizioSpcoop;
import org.openspcoop.dao.registry.driver.FiltroSPCoop;
import org.openspcoop.dao.registry.driver.FiltroServiziSPCoop;
import org.openspcoop.dao.registry.driver.IDServizio;
import org.openspcoop.dao.registry.driver.IDAccordo;
import org.openspcoop.dao.commons.IDSoggetto;
import org.openspcoop.registry.ws.WSRegistryNotFound;
import org.openspcoop.registry.ws.WSRegistrySearch;
import org.openspcoop.registry.ws.WSRegistrySearchException;
import org.openspcoop.registry.ws.WSRegistrySearchServiceLocator;

@WebService(targetNamespace = "http://registroservizi.ws.freesbee.icar.unibas.it/")
public class WSRegistroServiziImpl implements IWSRegistroServizi {

    private static Log logger = LogFactory.getLog(WSRegistroServiziImpl.class);

    public boolean existsAccordoServizio(String indirizzo, AccordoServizioRS accordo) throws SOAPFault {
        throw new UnsupportedOperationException("Not supported yet.");
        /*try {
        logger.info("Richiesta la presenza dell'accordo di servizio al seguente indirizzo: " + indirizzo);
        WSRegistrySearch registroWS = creaRegistrySearch(indirizzo);
        return registroWS.existsAccordoServizio(accordo.getNome());
        } catch (WSRegistryNotFound e) {
        throw new SOAPFault("Accordo di Servizio non trovato: " + e.getMessage());
        } catch (Exception ex) {
        logger.error("Impossibile contattare il registro dei servizi " + indirizzo + ". " + ex);
        throw new SOAPFault("Impossibile contattare il registro dei servizi " + indirizzo + ". " + ex);
        }*/
    }

    public boolean existsFruitoreServizioSpcoop(String indirizzo, ServizioRS servizio, SoggettoRS soggettoFruitore) throws SOAPFault {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean existsFruitoreServizioSpcoopCorrelato(String indirizzo, ServizioRS servizio, SoggettoRS soggettoFruitore) throws SOAPFault {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean existsServizioSpcoop(String indirizzo, ServizioRS servizio) throws SOAPFault {
        try {
            logger.info("Richiesta la presenza del servizio Spcoop al seguente indirizzo: " + indirizzo);
            WSRegistrySearch registroWS = creaRegistrySearch(indirizzo);
            IDServizio servizioRicerca = FreesbeeToNica.adapterServizio(servizio);
            return registroWS.existsServizioSpcoop(servizioRicerca);
        } catch (WSRegistryNotFound e) {
            throw new SOAPFault("ServizioSpcoop non trovato: " + e.getMessage());
        } catch (Exception ex) {
            logger.error("Impossibile contattare il registro dei servizi " + indirizzo + ". " + ex);
            throw new SOAPFault("Impossibile contattare il registro dei servizi " + indirizzo + ". " + ex);
        }
    }

    public boolean existsServizioSpcoopCorrelato(String indirizzo, ServizioRS servizio) throws SOAPFault {
        try {
            logger.info("Richiesta la presenza del servizio Spcoop correlato al seguente indirizzo: " + indirizzo);
            WSRegistrySearch registroWS = creaRegistrySearch(indirizzo);
            IDServizio servizioRicerca = FreesbeeToNica.adapterServizio(servizio);
            return registroWS.existsServizioSpcoopCorrelato(servizioRicerca);
        } catch (WSRegistryNotFound e) {
            throw new SOAPFault("ServizioSpcoop correlato non trovato: " + e.getMessage());
        } catch (Exception ex) {
            logger.error("Impossibile contattare il registro dei servizi " + indirizzo + ". " + ex);
            throw new SOAPFault("Impossibile contattare il registro dei servizi " + indirizzo + ". " + ex);
        }
    }

    public boolean existsSoggettoSPCoop(String indirizzo, SoggettoRS soggetto) throws SOAPFault {
        try {
            logger.info("Richiesto la presenza del soggetto Spcoop " + soggetto.getTipo() + " - " + soggetto.getNome() + " al seguente indirizzo: " + indirizzo);
            WSRegistrySearch registroWS = creaRegistrySearch(indirizzo);
            IDSoggetto soggettoRicerca = FreesbeeToNica.adapterSoggetto(soggetto);
            return registroWS.existsSoggettoSPCoop(soggettoRicerca);
        } catch (WSRegistryNotFound e) {
            throw new SOAPFault("SoggettoSpcoop non trovato: " + e.getMessage());
        } catch (Exception ex) {
            logger.error("Impossibile contattare il registro dei servizi " + indirizzo + ". " + ex);
            throw new SOAPFault("Impossibile contattare il registro dei servizi " + indirizzo + ". " + ex);
        }
    }

    public List<AccordoServizioRS> getAllIdAccordiServizio(String indirizzo, Date maxDate, Date minDate, AccordoServizioRS accordo) throws SOAPFault {
        throw new UnsupportedOperationException("Not supported yet.");
        /*try {
        logger.info("Richiesto l'accordo di servizio al seguente indirizzo: " + indirizzo);
        WSRegistrySearch registroWS = creaRegistrySearch(indirizzo);
        FiltroSPCoop filtro = new FiltroSPCoop();
        filtro.setMaxDate(maxDate);
        filtro.setMinDate(minDate);
        if (accordo != null) {
        filtro.setNome(accordo.getNome());
        }
        List<AccordoServizioRS> listaAccordiServizi = new ArrayList<AccordoServizioRS>();
        listaAccordiServizi = NicaToFreesbee.transformStringToAccordi(registroWS.getAllIdAccordiServizio(filtro));
        return listaAccordiServizi;
        } catch (WSRegistryNotFound e) {
        throw new SOAPFault("Accordo di Servizio non trovato: " + e.getMessage());
        } catch (Exception ex) {
        logger.error("Impossibile contattare il registro dei servizi " + indirizzo + ". " + ex);
        throw new SOAPFault("Impossibile contattare il registro dei servizi " + indirizzo + ". " + ex);
        }*/
    }

    public List<ServizioRS> getAllIdServiziSPCoop(String indirizzo, Date maxDate, Date minDate, ServizioRS servizio) throws SOAPFault {
        throw new UnsupportedOperationException("Not supported yet.");
        /*try {
        logger.info("Richiesti tutti i servizi al seguente indirizzo: " + indirizzo);
        WSRegistrySearch registroWS = creaRegistrySearch(indirizzo);
        FiltroServiziSPCoop filtro = new FiltroServiziSPCoop();
        filtro.setMaxDate(maxDate);
        filtro.setMinDate(minDate);
        if (servizio != null) {
        filtro.setNomeAccordo(servizio.getNome());
        filtro.setNomeSoggettoErogatore(servizio.getSoggettoErogatore().getNome());
        filtro.setTipoSoggettoErogatore(servizio.getSoggettoErogatore().getTipo());
        }
        List<ServizioRS> listaServizi = new ArrayList<ServizioRS>();
        listaServizi = NicaToFreesbee.transformIDServizioToServizi(registroWS.getAllIdServiziSPCoop(filtro));
        return listaServizi;
        } catch (WSRegistryNotFound e) {
        throw new SOAPFault("Accordo di Servizio non trovato: " + e.getMessage());
        } catch (Exception ex) {
        logger.error("Impossibile contattare il registro dei servizi " + indirizzo + ". " + ex);
        throw new SOAPFault("Impossibile contattare il registro dei servizi " + indirizzo + ". " + ex);
        }*/
    }

    public List<ServizioRS> getAllIdServiziSPCoopCorrelati(String indirizzo, Date maxDate, Date minDate, ServizioRS servizio) throws SOAPFault {
        try {
            logger.info("Richiesto tutti i servizi SPCoop correlati al seguente indirizzo: " + indirizzo);
            WSRegistrySearch registroWS = creaRegistrySearch(indirizzo);
            FiltroServiziSPCoop filtro = new FiltroServiziSPCoop();
//            filtro.setMaxDate(maxDate);
//            filtro.setMinDate(minDate);
//            if (servizio != null) {
//                filtro.setNomeAccordo(servizio.getNome());
//                filtro.setNomeSoggettoErogatore(servizio.getSoggettoErogatore().getNome());
//                filtro.setTipoSoggettoErogatore(servizio.getSoggettoErogatore().getTipo());
//            }
            List<ServizioRS> listaServizi = new ArrayList<ServizioRS>();
            listaServizi = NicaToFreesbee.transformIDServizioToServizi(registroWS.getAllIdServiziSPCoopCorrelati(null));
//            listaServizi = NicaToFreesbee.transformIDServizioToServizi(registroWS.getAllIdServiziSPCoopCorrelati(filtro));
            return listaServizi;
        } catch (WSRegistryNotFound e) {
            throw new SOAPFault("Accordo di Servizio non trovato: " + e.getMessage());
        } catch (Exception ex) {
            logger.info("Impossibile contattare il registro dei servizi " + indirizzo + ". " + ex);
            throw new SOAPFault("Impossibile contattare il registro dei servizi " + indirizzo + ". " + ex);
        }
    }

    public List<SoggettoRSRisposta> getAllIdSoggettiSPCoop(String indirizzo, Date maxDate, Date minDate, SoggettoRS soggetto) throws SOAPFault {
        try {
            logger.info("Richiesti tutti i soggetti SPCoop al seguente indirizzo: " + indirizzo);
            WSRegistrySearch registroWS = creaRegistrySearch(indirizzo);
            FiltroSPCoop filtro = new FiltroSPCoop();
            filtro.setMaxDate(maxDate);
            filtro.setMinDate(minDate);
            if (soggetto != null) {
                filtro.setNome(soggetto.getNome());
                filtro.setTipo(soggetto.getTipo());
            }
            List<SoggettoRSRisposta> listaSoggettoRisposta = new ArrayList<SoggettoRSRisposta>();
            listaSoggettoRisposta = NicaToFreesbee.tranformIDSoggettoToSoggettoRS(registroWS.getAllIdSoggettiSPCoop(filtro));
            return listaSoggettoRisposta;
        } catch (WSRegistryNotFound e) {
            throw new SOAPFault("Accordo di Servizio non trovato: " + e.getMessage());
        } catch (Exception ex) {
            if (logger.isDebugEnabled()) ex.printStackTrace();
            logger.error("Impossibile contattare il registro dei servizi " + indirizzo + ". " + ex);
            throw new SOAPFault("Impossibile contattare il registro dei servizi " + indirizzo + ". " + ex);
        }
    }

    public AccordoServizioRSRisposta getAccordoServizio(String indirizzo, AccordoServizioRS accordo) throws SOAPFault {
        if (accordo == null) {
            throw new IllegalArgumentException("E' stato richiesto un accordo di servizio nullo");
        }
        try {
            logger.info("Richiesto l'accordo di servizio " + accordo.getNome() + " al seguente indirizzo: " + indirizzo);
            WSRegistrySearch registroWS = creaRegistrySearch(indirizzo);
            IDAccordo idAccordo = IDAccordo.getIDAccordoFromUri(accordo.getNome());
            org.openspcoop.dao.registry.AccordoServizio accordoNicaRisposta = registroWS.getAccordoServizio(idAccordo);
            AccordoServizioRSRisposta accordoRisposta = NicaToFreesbee.adapterAccordoServizio(accordoNicaRisposta);
            return accordoRisposta;
        } catch (WSRegistryNotFound e) {
            throw new SOAPFault("Accordo di Servizio non trovato: " + e.getMessage());
        } catch (WSRegistrySearchException e) {
            throw new SOAPFault("Accordo di Servizio non trovato: " + e.getMessage());
        } catch (Exception ex) {
            if (logger.isDebugEnabled()) ex.printStackTrace();
            logger.error("Impossibile contattare il registro dei servizi " + indirizzo + ". " + ex);
            throw new SOAPFault("Impossibile contattare il registro dei servizi " + indirizzo + ". " + ex);
        }
    }

    public SoggettoRSRisposta getFruitoreServizioSpcoop(String indirizzo, ServizioRS servizio, SoggettoRS soggetto) throws SOAPFault {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public SoggettoRSRisposta getFruitoreServizioSpcoopCorrelato(String indirizzo, ServizioRS servizio, SoggettoRS soggetto) throws SOAPFault {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<SoggettoRSRisposta> getFruitoriServizioSpcoop(String indirizzo, Date maxDate, Date minDate, ServizioRS servizio) throws SOAPFault {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<SoggettoRSRisposta> getFruitoriServizioSpcoopCorrelato(String indirizzo, Date maxDate, Date minDate, ServizioRS servizio) throws SOAPFault {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ServizioRSRisposta getServizioSPCoop(String indirizzo, ServizioRS servizio) throws SOAPFault {
        try {
            if (servizio == null) {
                throw new SOAPFault("Servizio nullo");
            }
            logger.info("Richiesto il servizio " + servizio.getTipo() + " - " + servizio.getNome() + " al seguente indirizzo: " + indirizzo);
            WSRegistrySearch registroWS = creaRegistrySearch(indirizzo);
            IDServizio servizioNICA = FreesbeeToNica.adapterServizio(servizio);
            servizioNICA.setCorrelato(false);
            ServizioSpcoop servizioNicaRisposta = registroWS.getServizioSPCoop(servizioNICA);
            ServizioRSRisposta servizioRisposta = NicaToFreesbee.adapterServizio(servizioNicaRisposta);
            analizzaAccordoServizio(servizioRisposta, indirizzo);
            return servizioRisposta;
        } catch (WSRegistryNotFound e) {
            e.printStackTrace();
            throw new SOAPFault(CostantiSOAP.SERVIZIO_NON_TROVATO + ": " + e.getMessage());
        } catch (Exception ex) {
            if (logger.isDebugEnabled()) ex.printStackTrace();
            logger.error("Impossibile contattare il registro dei servizi " + indirizzo + ". " + ex);
            throw new SOAPFault("Impossibile contattare il registro dei servizi " + indirizzo + ". " + ex);
        }
    }

    public ServizioRSRisposta getServizioSPCoopCorrelato(String indirizzo, ServizioRS servizio) throws SOAPFault {
        try {
            logger.info("Richiesto il servizio correlato al seguente indirizzo: " + indirizzo);
            WSRegistrySearch registroWS = creaRegistrySearch(indirizzo);
            IDServizio servizioNICA = FreesbeeToNica.adapterServizio(servizio);
            servizioNICA.setCorrelato(true);
            ServizioSpcoop servizioNicaRisposta = registroWS.getServizioSPCoopCorrelato(servizioNICA);
            ServizioRSRisposta servizioRisposta = NicaToFreesbee.adapterServizio(servizioNicaRisposta);

            AccordoServizioRS accordoRichiesta = new AccordoServizioRS();
            accordoRichiesta.setNome(servizioRisposta.getAccordoServizio().getNome());
            AccordoServizioRSRisposta accordoServizio = getAccordoServizio(indirizzo, accordoRichiesta);
            accordoServizio.setNome(servizioRisposta.getAccordoServizio().getNome());
            servizioRisposta.setAccordoServizio(accordoServizio);
//            servizioRisposta.getAccordoServizio().setProfiloCollaborazione(accordoServizio.getProfiloCollaborazione());
            return servizioRisposta;
        } catch (WSRegistryNotFound e) {
            throw new SOAPFault("Servizio non trovato: " + e.getMessage());
        } catch (Exception ex) {
            if (logger.isDebugEnabled()) ex.printStackTrace();
            logger.error("Impossibile contattare il registro dei servizi " + indirizzo + ". " + ex);
            throw new SOAPFault("Impossibile contattare il registro dei servizi " + indirizzo + ". " + ex);
        }
    }

    public ServizioRSRisposta getServizioSPCoopCorrelatoByAccordo(String indirizzo, SoggettoRS soggetto, AccordoServizioRS accordo) throws SOAPFault {
        try {
            logger.info("Richiesto il servizio correlato con l'accordo di servizio al seguente indirizzo: " + indirizzo);
            WSRegistrySearch registroWS = creaRegistrySearch(indirizzo);
            IDSoggetto soggettoNICA = FreesbeeToNica.adapterSoggetto(soggetto);
            IDAccordo idAccordo = IDAccordo.getIDAccordoFromUri(accordo.getNome());
            ServizioSpcoop servizio = registroWS.getServizioSPCoopCorrelatoByAccordo(soggettoNICA, idAccordo);
            ServizioRSRisposta servizioRS = NicaToFreesbee.adapterServizio(servizio);
            return servizioRS;
        } catch (WSRegistryNotFound e) {
            throw new SOAPFault("Servizio non trovato: " + e.getMessage());
        } catch (Exception ex) {
            if (logger.isDebugEnabled()) ex.printStackTrace();
            logger.error("Impossibile contattare il registro dei servizi " + indirizzo + ". " + ex);
            throw new SOAPFault("Impossibile contattare il registro dei servizi " + indirizzo + ". " + ex);
        }
    }

    public SoggettoRSRisposta getSoggettoSPCoop(String indirizzo, SoggettoRS soggetto) throws SOAPFault {
        try {
            logger.info("Richiesto il soggetto al seguente indirizzo: " + indirizzo);
            WSRegistrySearch registroWS = creaRegistrySearch(indirizzo);
            IDSoggetto soggettoNICA = FreesbeeToNica.adapterSoggetto(soggetto);
            SoggettoSpcoop soggettoSp = registroWS.getSoggettoSPCoop(soggettoNICA);
            SoggettoRSRisposta soggettoRS = NicaToFreesbee.adapterSoggettoRS(soggettoSp);
            return soggettoRS;
        } catch (WSRegistryNotFound e) {
            throw new SOAPFault("Servizio non trovato: " + e.getMessage());
        } catch (Exception ex) {
            if (logger.isDebugEnabled()) ex.printStackTrace();
            logger.error("Impossibile contattare il registro dei servizi " + indirizzo + ". " + ex);
            throw new SOAPFault("Impossibile contattare il registro dei servizi " + indirizzo + ". " + ex);
        }
    }

    private void analizzaAccordoServizio(ServizioRSRisposta servizioRisposta, String indirizzo) {
        AccordoServizioRS accordoRichiesta = new AccordoServizioRS();
        accordoRichiesta.setNome(servizioRisposta.getAccordoServizio().getNome());
        if (accordoRichiesta.getNome() != null && !accordoRichiesta.getNome().isEmpty()) {
            try {
                AccordoServizioRSRisposta accordoServizio = getAccordoServizio(indirizzo, accordoRichiesta);
                accordoServizio.setNome(servizioRisposta.getAccordoServizio().getNome());
                servizioRisposta.setAccordoServizio(accordoServizio);
//            servizioRisposta.getAccordoServizio().setProfiloCollaborazione(accordoServizio.getProfiloCollaborazione());
            } catch (SOAPFault ex) {
                logger.warn("Impossibile aggiungere le informazioni dell'accordo di servizio " + accordoRichiesta.getNome());
            }
        }
    }

    private WSRegistrySearch creaRegistrySearch(String indirizzo) throws ServiceException {
        WSRegistrySearchServiceLocator locator = new WSRegistrySearchServiceLocator();
        locator.setSearchEndpointAddress(indirizzo);
        WSRegistrySearch registroWS = locator.getSearch();
        return registroWS;
    }
}
