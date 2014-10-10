package it.unibas.icar.interfreesbee.nica.registroservizi;

import it.unibas.icar.freesbee.ws.registroservizi.AccordoServizioRS;
import it.unibas.icar.freesbee.ws.registroservizi.AccordoServizioRSRisposta;
import it.unibas.icar.freesbee.ws.registroservizi.ServizioRS;
import it.unibas.icar.freesbee.ws.registroservizi.ServizioRSRisposta;
import it.unibas.icar.freesbee.ws.registroservizi.SoggettoRS;
import it.unibas.icar.freesbee.ws.registroservizi.SoggettoRSRisposta;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.IWSRegistroServizi;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.WSRegistroServiziImplService;
import it.unibas.icar.interfreesbee.freesbee.registroservizi.adapter.NicaToFreesbee;
import it.unibas.icar.interfreesbee.nica.registroservizi.adapter.FreesbeeToNica;
import it.unibas.icar.interfreesbee.nica.registroservizi.stub.AccordoServizio;
import it.unibas.icar.interfreesbee.nica.registroservizi.stub.FiltroSPCoop;
import it.unibas.icar.interfreesbee.nica.registroservizi.stub.FiltroServiziSPCoop;
import it.unibas.icar.interfreesbee.nica.registroservizi.stub.IDServizio;
import it.unibas.icar.interfreesbee.nica.registroservizi.stub.IDSoggetto;
import it.unibas.icar.interfreesbee.nica.registroservizi.stub.ServizioSpcoop;
import it.unibas.icar.interfreesbee.nica.registroservizi.stub.SoggettoSpcoop;
import it.unibas.icar.interfreesbee.nica.registroservizi.stub.WSRegistryNotFound_Exception;
import it.unibas.icar.interfreesbee.nica.registroservizi.stub.WSRegistrySearchException_Exception;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class WSRegistroServiziImpl implements it.unibas.icar.interfreesbee.nica.registroservizi.IWSRegistroServizi {

    private Log logger = LogFactory.getLog(this.getClass());
    private String indirizzoConnettore = "http://localhost:8191/ws/registroServizi?wsdl";
    private WSRegistroServiziImplService service = null;

    public boolean existsServizioSpcoopCorrelato(IDServizio idServizio) throws WSRegistrySearchException_Exception {
        logger.info("Richiesto il servizio spcoop correlato");
        try {
            ServizioRS servizio = FreesbeeToNica.adapterIDServiceToServizio(idServizio);
            IWSRegistroServizi registro = getConnettoreRegistro();
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.ExistsServizioSpcoopCorrelato richiesta = new it.unibas.icar.freesbee.ws.registroservizi.client.stub.ExistsServizioSpcoopCorrelato();
            richiesta.setServizio(servizio);
            boolean risposta = registro.existsServizioSpcoopCorrelato(richiesta, null).isReturn();
            logger.info("Esiste il servizio correlato " + servizio.getNome() + "? " + risposta);
            return risposta;
        } catch (Exception ex) {
            if (logger.isDebugEnabled()) {
                ex.printStackTrace();
            }
            logger.info("Impossibile verificare l'esistenza del servizio " + ex);
            throw new WSRegistrySearchException_Exception("Impossibile verificare l'esistenza del servizio " + ex);
        }
    }

    public List<IDServizio> getAllIdServiziSPCoop(FiltroServiziSPCoop filtroRicerca) throws WSRegistryNotFound_Exception, WSRegistrySearchException_Exception {
        try {
            IWSRegistroServizi registro = getConnettoreRegistro();
            ServizioRS servizio = new ServizioRS();
            SoggettoRS soggettoErogatore = new SoggettoRS();
            servizio.setSoggettoErogatore(soggettoErogatore);
            servizio.setNome(filtroRicerca.getNome());
            servizio.setTipo(filtroRicerca.getTipo());
            soggettoErogatore.setNome(filtroRicerca.getNomeSoggettoErogatore());
            soggettoErogatore.setTipo(filtroRicerca.getTipoSoggettoErogatore());
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAllIdServiziSPCoop richiesta = new it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAllIdServiziSPCoop();
            richiesta.setServizio(servizio);
            List<ServizioRS> listaServizi = registro.getAllIdServiziSPCoop(richiesta, null).getReturn();
            List<IDServizio> listaIDServizi = FreesbeeToNica.transformServiziToID(listaServizi);
            return listaIDServizi;
        } catch (Exception ex) {
            if (logger.isDebugEnabled()) {
                ex.printStackTrace();
            }
            logger.info("Impossibile restituire la lista degli accordi di servizio " + ex);
            throw new WSRegistrySearchException_Exception("Impossibile restituire la lista degli accordi di servizio " + ex);
        }
    }

    public boolean existsSoggettoSPCoop(IDSoggetto idSoggetto) throws WSRegistrySearchException_Exception {
        try {
            logger.info("Richiesto il soggetto " + idSoggetto.getTipo() + " - " + idSoggetto.getNome());
            SoggettoRS soggettoRS = NicaToFreesbee.transformSoggetto(idSoggetto);
            IWSRegistroServizi registro = getConnettoreRegistro();
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.ExistsSoggettoSPCoop richiesta = new it.unibas.icar.freesbee.ws.registroservizi.client.stub.ExistsSoggettoSPCoop();
            richiesta.setSoggetto(soggettoRS);
            boolean ritorno = registro.existsSoggettoSPCoop(richiesta, null).isReturn();
            return ritorno;
        } catch (Exception ex) {
            if (logger.isDebugEnabled()) {
                ex.printStackTrace();
            }
            logger.info("Impossibile restituire il soggetto " + ex);
            throw new WSRegistrySearchException_Exception("Impossibile restituire il soggetto " + ex);
        }
    }

    public ServizioSpcoop getServizioSPCoopCorrelato(IDServizio idService) throws WSRegistryNotFound_Exception, WSRegistrySearchException_Exception {
        try {
            ServizioRS servizio = FreesbeeToNica.adapterIDServiceToServizio(idService);
            IWSRegistroServizi registro = getConnettoreRegistro();
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetServizioSPCoopCorrelato richiesta = new it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetServizioSPCoopCorrelato();
            richiesta.setServizio(servizio);
            ServizioRSRisposta servizioRisposta = registro.getServizioSPCoopCorrelato(richiesta, null).getReturn();
            ServizioSpcoop servizioRitorno = FreesbeeToNica.adapterServizio(servizioRisposta);
            return servizioRitorno;
        } catch (Exception ex) {
            if (logger.isDebugEnabled()) {
                ex.printStackTrace();
            }
            logger.info("Impossibile restituire il servizio correlato " + ex);
            throw new WSRegistrySearchException_Exception("Impossibile restituire il servizio correlato " + ex);
        }
    }

    public List<String> getAllIdAccordiServizio(FiltroSPCoop filtroRicerca) throws WSRegistryNotFound_Exception, WSRegistrySearchException_Exception {
        try {
            it.unibas.icar.freesbee.modello.AccordoServizio accordo = new it.unibas.icar.freesbee.modello.AccordoServizio();
            if (filtroRicerca != null && filtroRicerca.getNome() != null) {
                accordo.setNome(filtroRicerca.getNome());
            }
            IWSRegistroServizi registro = getConnettoreRegistro();
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAllIdAccordiServizio richiesta = new it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAllIdAccordiServizio();
            richiesta.setAccordoServizio(accordo);
            List<it.unibas.icar.freesbee.modello.AccordoServizio> listaAccordi = registro.getAllIdAccordiServizio(richiesta, null).getReturn();
            List<String> listaStringhe = FreesbeeToNica.transformAccordiToString(listaAccordi);
            return listaStringhe;
        } catch (Exception ex) {
            if (logger.isDebugEnabled()) {
                ex.printStackTrace();
            }
            logger.info("Impossibile restituire la lista degli accordi di servizio " + ex);
            throw new WSRegistrySearchException_Exception("Impossibile restituire la lista degli accordi di servizio " + ex);
        }
    }

    public ServizioSpcoop getServizioSPCoop(IDServizio idService) throws WSRegistryNotFound_Exception, WSRegistrySearchException_Exception {
        try {
            ServizioRS servizio = FreesbeeToNica.adapterIDServiceToServizio(idService);
            IWSRegistroServizi registro = getConnettoreRegistro();
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetServizioSPCoop richiesta = new it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetServizioSPCoop();
            richiesta.setServizio(servizio);
            ServizioRSRisposta risposta = registro.getServizioSPCoop(richiesta, null).getReturn();
            ServizioSpcoop servizioRitorno = FreesbeeToNica.adapterServizio(risposta);
            return servizioRitorno;
        } catch (Exception ex) {
            if (logger.isDebugEnabled()) {
                ex.printStackTrace();
            }
            logger.info("Impossibile restituire il servizio " + ex);
            throw new WSRegistrySearchException_Exception("Impossibile restituire il servizio " + ex);
        }
    }

    public List<IDServizio> getAllIdServiziSPCoopCorrelati(FiltroServiziSPCoop filtroRicerca) throws WSRegistryNotFound_Exception, WSRegistrySearchException_Exception {
        try {
            IWSRegistroServizi registro = getConnettoreRegistro();
            ServizioRS servizio = new ServizioRS();
            SoggettoRS soggettoErogatore = new SoggettoRS();
            servizio.setSoggettoErogatore(soggettoErogatore);
            servizio.setNome(filtroRicerca.getNome());
            servizio.setTipo(filtroRicerca.getTipo());
            soggettoErogatore.setNome(filtroRicerca.getNomeSoggettoErogatore());
            soggettoErogatore.setTipo(filtroRicerca.getTipoSoggettoErogatore());
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAllIdServiziSPCoopCorrelati richiesta = new it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAllIdServiziSPCoopCorrelati();
            richiesta.setServizio(servizio);
            List<ServizioRS> listaServizi = registro.getAllIdServiziSPCoopCorrelati(richiesta, null).getReturn();
            List<IDServizio> listaIDServizi = FreesbeeToNica.transformServiziToID(listaServizi);
            return listaIDServizi;
        } catch (Exception ex) {
            if (logger.isDebugEnabled()) {
                ex.printStackTrace();
            }
            logger.info("Impossibile restituire la lista degli accordi di servizio " + ex);
            throw new WSRegistrySearchException_Exception("Impossibile restituire la lista degli accordi di servizio " + ex);
        }
    }

    public SoggettoSpcoop getSoggettoSPCoop(IDSoggetto idSoggetto) throws WSRegistryNotFound_Exception, WSRegistrySearchException_Exception {
        try {
            logger.info("Richiesto il soggetto " + idSoggetto.getTipo() + " - " + idSoggetto.getNome());
            SoggettoRS soggettoRS = NicaToFreesbee.transformSoggetto(idSoggetto);
            IWSRegistroServizi registro = getConnettoreRegistro();
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetSoggettoSPCoop richiesta = new it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetSoggettoSPCoop();
            richiesta.setSoggetto(soggettoRS);
            SoggettoRSRisposta risposta = registro.getSoggettoSPCoop(richiesta, null).getReturn();
            SoggettoSpcoop risp = FreesbeeToNica.adapterSoggetto(risposta);
            return risp;
        } catch (Exception ex) {
            if (logger.isDebugEnabled()) {
                ex.printStackTrace();
            }
            logger.info("Impossibile restituire il soggetto " + ex);
            throw new WSRegistrySearchException_Exception("Impossibile restituire il soggetto " + ex);
        }
    }

    public ServizioSpcoop getServizioSPCoopCorrelatoByAccordo(IDSoggetto idSoggetto, String nomeAccordo) throws WSRegistryNotFound_Exception, WSRegistrySearchException_Exception {
        try {
            SoggettoRS soggettoRS = NicaToFreesbee.transformSoggetto(idSoggetto);
            AccordoServizioRS accordo = new AccordoServizioRS();
            accordo.setNome(nomeAccordo);
            IWSRegistroServizi registro = getConnettoreRegistro();
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetServizioSPCoopCorrelatoByAccordo richiesta = new it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetServizioSPCoopCorrelatoByAccordo();
            richiesta.setAccordoServizio(accordo);
            richiesta.setSoggetto(soggettoRS);
            ServizioRSRisposta servizioRisposta = registro.getServizioSPCoopCorrelatoByAccordo(richiesta, null).getReturn();
            ServizioSpcoop servizioRitorno = FreesbeeToNica.adapterServizio(servizioRisposta);
            return servizioRitorno;
        } catch (Exception ex) {
            if (logger.isDebugEnabled()) {
                ex.printStackTrace();
            }
            logger.info("Impossibile restituire il servizio correlato by accordo " + ex);
            throw new WSRegistrySearchException_Exception("Impossibile restituire il servizio correlato by accordo" + ex);
        }
    }

    public boolean existsAccordoServizio(String nomeAccordo) throws WSRegistrySearchException_Exception {
        try {
            it.unibas.icar.freesbee.modello.AccordoServizio accordo = new it.unibas.icar.freesbee.modello.AccordoServizio();
            accordo.setNome(nomeAccordo);
            IWSRegistroServizi registro = getConnettoreRegistro();
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.ExistsAccordoServizio richiesta = new it.unibas.icar.freesbee.ws.registroservizi.client.stub.ExistsAccordoServizio();
            richiesta.setAccordoServizio(accordo);
            boolean risposta = registro.existsAccordoServizio(richiesta, null).isReturn();
            logger.info("Esiste l'accordo di servizio " + nomeAccordo + "? " + risposta);
            return risposta;
        } catch (Exception ex) {
            if (logger.isDebugEnabled()) {
                ex.printStackTrace();
            }
            logger.info("Impossibile verificare l'esistenza dell'accordo " + ex);
            throw new WSRegistrySearchException_Exception("Impossibile restituire l'accordo " + ex);
        }
    }

    public boolean existsServizioSpcoop(IDServizio idServizio) throws WSRegistrySearchException_Exception {
        try {
            ServizioRS servizio = FreesbeeToNica.adapterIDServiceToServizio(idServizio);
            IWSRegistroServizi registro = getConnettoreRegistro();
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.ExistsServizioSpcoop richiesta = new it.unibas.icar.freesbee.ws.registroservizi.client.stub.ExistsServizioSpcoop();
            richiesta.setServizio(servizio);
            boolean risposta = registro.existsServizioSpcoop(richiesta, null).isReturn();
            logger.info("Esiste il servizio " + servizio.getNome() + "? " + risposta);
            return risposta;
        } catch (Exception ex) {
            if (logger.isDebugEnabled()) {
                ex.printStackTrace();
            }
            logger.info("Impossibile verificare l'esistenza del servizio " + ex);
            throw new WSRegistrySearchException_Exception("Impossibile verificare l'esistenza del servizio " + ex);
        }
    }

    public List<IDSoggetto> getAllIdSoggettiSPCoop(FiltroSPCoop filtroRicerca) throws WSRegistryNotFound_Exception, WSRegistrySearchException_Exception {
        try {
            SoggettoRS soggetto = FreesbeeToNica.adapterFiltroToSoggetto(filtroRicerca);
            IWSRegistroServizi registro = getConnettoreRegistro();
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAllIdSoggettiSPCoop richiesta = new it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAllIdSoggettiSPCoop();
            richiesta.setSoggetto(soggetto);
            if (filtroRicerca !=null) {
                richiesta.setMaxDate(filtroRicerca.getMaxDate());
                richiesta.setMinDate(filtroRicerca.getMinDate());
            }
            List<SoggettoRSRisposta> listaSoggettiRS = registro.getAllIdSoggettiSPCoop(richiesta, null).getReturn();
            List<IDSoggetto> listaIDSoggetto = FreesbeeToNica.adapterListaSoggettiToID(listaSoggettiRS);
            return listaIDSoggetto;
        } catch (Exception ex) {
            if (logger.isDebugEnabled()) {
                ex.printStackTrace();
            }
            logger.info("Impossibile restituire la lista dei soggetti " + ex);
            throw new WSRegistrySearchException_Exception("Impossibile restituire la lista dei soggetti " + ex);
        }
    }

    public AccordoServizio getAccordoServizio(String nomeAccordo) throws WSRegistryNotFound_Exception, WSRegistrySearchException_Exception {
        try {
            AccordoServizioRS accordoRS = new AccordoServizioRS();
            accordoRS.setNome(nomeAccordo);
            IWSRegistroServizi registro = getConnettoreRegistro();
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAccordoServizio richiesta = new it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAccordoServizio();
            it.unibas.icar.freesbee.modello.AccordoServizio accordoModello = new it.unibas.icar.freesbee.modello.AccordoServizio();
            accordoModello.setNome(nomeAccordo);
            richiesta.setAccordoServizio(accordoModello);
            AccordoServizioRSRisposta risposta = registro.getAccordoServizio(richiesta, null).getReturn();
            AccordoServizio accordo = FreesbeeToNica.adapterAccordo(risposta);
            return accordo;
        } catch (Exception ex) {
            if (logger.isDebugEnabled()) {
                ex.printStackTrace();
            }
            logger.info("Impossibile restituire l'accordo " + ex);
            throw new WSRegistrySearchException_Exception("Impossibile restituire l'accordo " + ex);
        }
    }

    private IWSRegistroServizi getConnettoreRegistro() throws MalformedURLException {
        if (service == null) {
            service = new WSRegistroServiziImplService(new URL(indirizzoConnettore));
        }
        return service.getWSRegistroServiziImplPort();
    }
}
