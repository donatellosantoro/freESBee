package it.unibas.icar.freesbee.processors.strategy;

import it.unibas.icar.freesbee.exception.FreesbeeException;
import it.unibas.icar.freesbee.modello.BustaEGov;
import it.unibas.icar.freesbee.modello.ConfigurazioneStatico;
import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.modello.PortaDelegata;
import it.unibas.icar.freesbee.utilita.CostantiSOAP;
import it.unibas.icar.freesbee.utilita.StringUtil;
import it.unibas.icar.freesbee.utilita.UtilitaDate;
import it.unibas.icar.freesbee.ws.registroservizi.AccordoServizioRS;
import it.unibas.icar.freesbee.ws.registroservizi.AzioneRSRisposta;
import it.unibas.icar.freesbee.ws.registroservizi.ServizioRS;
import it.unibas.icar.freesbee.ws.registroservizi.ServizioRSRisposta;
import it.unibas.icar.freesbee.ws.registroservizi.SoggettoRS;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.DAOException_Exception;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetServizioSPCoop;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetServizioSPCoopCorrelato;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetServizioSPCoopCorrelatoByAccordo;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.IWSRegistroServizi;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.SOAPFault_Exception;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.WSRegistroServiziImplService;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EnricherPortaDelegataRegistroServizi implements IEnricherPortaDelegataStrategy {

    private static Log logger = LogFactory.getLog(EnricherPortaDelegataRegistroServizi.class);
    private String connettoreRegistroServizi;
    private boolean interoperabilita;
    private boolean useCache = ConfigurazioneStatico.getInstance().isCacheWS();

    protected EnricherPortaDelegataRegistroServizi(String connettoreRegistroServizi, boolean interoperabilita) {
        this.connettoreRegistroServizi = connettoreRegistroServizi;
        this.interoperabilita = interoperabilita;
    }

    public void arricchisciMessaggio(PortaDelegata portaDelegata, Messaggio messaggio) throws FreesbeeException {
        assert (portaDelegata != null) : "Impossibile arricchire le informazioni di una porta delegata nulla";
        String fruitore = portaDelegata.getStringaFruitore();
        String tipoFruitore = portaDelegata.getStringaTipoFruitore();
        if (tipoFruitore == null || fruitore == null) {
            throw new FreesbeeException("Il soggetto fruitore è sconosciuto.");
        }
        String erogatore = portaDelegata.getStringaErogatore();
        String tipoErogatore = portaDelegata.getStringaTipoErogatore();
        String nomeServizio = portaDelegata.getStringaServizio();
        String tipoServizio = portaDelegata.getStringaTipoServizio();
        String azione = null;
        if (portaDelegata.getAzione() != null) {
            azione = portaDelegata.getAzione().getNome();
        }
        if (portaDelegata.getStringaAzione() != null) {
            azione = portaDelegata.getStringaAzione();
        }

        if (logger.isDebugEnabled()) logger.debug("fruitore: " + fruitore);
        if (logger.isDebugEnabled()) logger.debug("tipoFruitore: " + tipoFruitore);
        if (logger.isDebugEnabled()) logger.debug("erogatore: " + erogatore);
        if (logger.isDebugEnabled()) logger.debug("tipoErogatore: " + tipoErogatore);
        if (logger.isDebugEnabled()) logger.debug("servizio: " + nomeServizio);
        if (logger.isDebugEnabled()) logger.debug("tipoServizio: " + tipoServizio);
        if (logger.isDebugEnabled()) logger.debug("azione: " + azione);

        Date oraRegistrazione = new Date();


        String connettoreErogatore = ""; // CHIEDERE AL REGISTRO L'INDIRIZZO DEL NICA
        boolean correlato; // LEGGERE IL SERVIZIO DAL REGISTRO E VEDERE SE E' CORRELATO
        String profiloCollaborazione; // LEGGERE ACCORDO DI SERVIZIO
        String confermaRicezione; // LEGGERE ACCORDO DI SERVIZIO
        String inoltro; // LEGGERE ACCORDO DI SERVIZIO
//            Date scadenzaMessaggio ; // LEGGERE ACCORDO DI SERVIZIO;
        ServizioRSRisposta servizioCorrelato; //CHIEDERE AL REGISTRO SE ESISTE UN SERVIZIO CORRELATO daoServizio.findCorrelato(portaDelegata.getServizio().getAccordoServizio(), portaDelegata.getSoggettoFruitore());

        String indirizzoWSDL = connettoreRegistroServizi;
        String indirizzoRichiesta = null;
        if (interoperabilita) {
            ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();
            indirizzoWSDL = conf.getInteroperabilitaRegistroFreesbee(); //DEVO RICHIEDERE AL MODULO DI INTEROPERABILITA'
            indirizzoRichiesta = connettoreRegistroServizi; //E GLI DEVO INVIARE L'INDIRIZZO DEL REGISTRO
        } else {
            if (!(indirizzoWSDL.toLowerCase()).endsWith("?wsdl")) {
                indirizzoWSDL = indirizzoWSDL + "?wsdl";
            }
        }

        try {
            ServizioRS servizioRS = new ServizioRS(tipoServizio, nomeServizio);
            servizioRS.setSoggettoErogatore(new SoggettoRS(tipoErogatore, erogatore));
            if (logger.isInfoEnabled()) logger.info("Richiedo le informazioni al registro dei servizi " + indirizzoWSDL + " interoperabilita? " + interoperabilita);
            WSRegistroServiziImplService service = new WSRegistroServiziImplService(new URL(indirizzoWSDL));
            IWSRegistroServizi port = service.getWSRegistroServiziImplPort();
            ServizioRSRisposta servizio = cercaServizioSPCoop(port, indirizzoRichiesta, servizioRS);
            if (servizio == null) {
                servizio = cercaServizioSPCoopCorrelato(port, indirizzoRichiesta, servizioRS);
            }
            if (servizio == null) {
                throw new FreesbeeException("Impossibile arricchire le informazioni del messaggio. Servizio non trovato.");
            }
            GetServizioSPCoopCorrelatoByAccordo correlatoRichiesta = new GetServizioSPCoopCorrelatoByAccordo();
            AccordoServizioRS accordo = new AccordoServizioRS();
            accordo.setNome(servizio.getAccordoServizio().getNome());
            correlatoRichiesta.setSoggetto(new SoggettoRS(tipoFruitore, fruitore));
            correlatoRichiesta.setAccordoServizio(accordo);
            servizioCorrelato = cercaCorrelato(indirizzoRichiesta, correlatoRichiesta, port);

            correlato = servizio.isCorrelato();
            profiloCollaborazione = servizio.getAccordoServizio().getProfiloCollaborazione();
            confermaRicezione = servizio.getAccordoServizio().getProfiloEGov().isConfermaRicezione() + "";
            inoltro = servizio.getAccordoServizio().getProfiloEGov().getStringaInoltro();

            if (azione != null && !azione.isEmpty()) {
                List<AzioneRSRisposta> listaAzioni = servizio.getAccordoServizio().getAzioniRSRisposta();
                AzioneRSRisposta azioneRS = cercaAzione(listaAzioni, azione);
                if (azioneRS == null) {
                    azioneRS = cercaAzione(listaAzioni, nomeServizio + ":" + azione);
                }
                if (azioneRS == null) {
                    logger.error("Impossibile arricchire le informazioni del messaggio. Azione " + azione + " sconosciuta.");
                    throw new FreesbeeException("Impossibile arricchire le informazioni del messaggio. Azione " + azione + " sconosciuta.");
                }

                String profiloCollaborazioneAzione = azioneRS.getProfiloCollaborazione();
                if (profiloCollaborazioneAzione != null && !profiloCollaborazioneAzione.isEmpty()) {
                    profiloCollaborazione = profiloCollaborazioneAzione;
                }
            }
        } catch (Exception ex) {
            if (logger.isDebugEnabled()) ex.printStackTrace();
            logger.error("Errore mentre si richiedevano informazioni al registro dei servizi " + ex);
            throw new FreesbeeException(ex.getMessage());
        }

        if (logger.isDebugEnabled()) logger.debug("profiloCollaborazione: " + profiloCollaborazione);
        if (logger.isDebugEnabled()) logger.debug("inoltro: " + inoltro);
        if (logger.isDebugEnabled()) logger.debug("confermaRicezione: " + confermaRicezione);
        if (logger.isDebugEnabled()) logger.debug("connettoreErogatore: " + connettoreErogatore);
        if (logger.isDebugEnabled()) logger.debug("correlato: " + correlato);
        if (logger.isDebugEnabled()) logger.debug("servizioCorrelato: " + servizioCorrelato);

        messaggio.setFruitore(fruitore);
        messaggio.setTipoFruitore(tipoFruitore);
        messaggio.setErogatore(erogatore);
        messaggio.setTipoErogatore(tipoErogatore);
        messaggio.setServizio(nomeServizio);
        messaggio.setTipoServizio(tipoServizio);
        messaggio.setAzione(azione);
        messaggio.setProfiloCollaborazione(profiloCollaborazione);
        messaggio.setOraRegistrazione(UtilitaDate.formattaDataEOra(oraRegistrazione));
        messaggio.setIdMessaggio(BustaEGov.getInstance().getNuovoIdentificatore(fruitore));
        messaggio.setInoltro(inoltro);
        messaggio.setConfermaRicezione(confermaRicezione);
        messaggio.setConnettoreErogatore(connettoreErogatore);
        messaggio.setCorrelato(correlato);
        if (servizioCorrelato != null) {
            messaggio.setServizioCorrelato(servizioCorrelato.getNome());
            messaggio.setTipoServizioCorrelato(servizioCorrelato.getTipo());
        }
    }

    private AzioneRSRisposta cercaAzione(List<AzioneRSRisposta> listaAzioni, String azione) {
        for (AzioneRSRisposta azioneRSRisposta : listaAzioni) {
            if (azioneRSRisposta.getNome().equalsIgnoreCase(azione)) {
                return azioneRSRisposta;
            }
        }
        return null;
    }

    private static  Map<String, ServizioRSRisposta> cacheCorrelato = new HashMap<String, ServizioRSRisposta>();
    private ServizioRSRisposta cercaCorrelato(String indirizzoRichiesta, GetServizioSPCoopCorrelatoByAccordo correlatoRichiesta, IWSRegistroServizi port) {
        try {
            String chiave = correlatoRichiesta.getAccordoServizio().getNome() + "#" + correlatoRichiesta.getSoggetto().getTipo() + "#" + correlatoRichiesta.getSoggetto().getNome();
            if(useCache && cacheCorrelato.containsKey(chiave)){
                return cacheCorrelato.get(chiave);
            }
            ServizioRSRisposta servizioCorrelato = port.getServizioSPCoopCorrelatoByAccordo(correlatoRichiesta, indirizzoRichiesta).getReturn();
            if(useCache){
                cacheCorrelato.put(chiave, servizioCorrelato);
            }
            return servizioCorrelato;
        } catch (Exception ex) {
            if (logger.isInfoEnabled()) logger.info("Nessun servizio correlato trovato");
            return null;
        }
    }

    private static Map<String, ServizioRSRisposta> cacheServizio = new HashMap<String, ServizioRSRisposta>();
    private ServizioRSRisposta cercaServizioSPCoop(IWSRegistroServizi port, String indirizzoRichiesta, ServizioRS servizioRS) throws DAOException_Exception, SOAPFault_Exception {
        try {
            String chiave = servizioRS.getTipo() + "#" + servizioRS.getNome() + servizioRS.getSoggettoErogatore().getTipo() + "#" + servizioRS.getSoggettoErogatore().getNome();
            if(useCache && cacheServizio.containsKey(chiave)){
                return cacheServizio.get(chiave);
            }
            GetServizioSPCoop richiesta = new GetServizioSPCoop();
            richiesta.setServizio(servizioRS);
            ServizioRSRisposta servizio = port.getServizioSPCoop(richiesta, indirizzoRichiesta).getReturn();
            if(useCache){
                cacheServizio.put(chiave, servizio);
            }
            return servizio;
        } catch (SOAPFault_Exception ex) {
            String messaggioErrore = ex.getMessage();
            logger.warn("Servizio non trovato " + messaggioErrore);
            if (messaggioErrore.contains(CostantiSOAP.SERVIZIO_NON_TROVATO)) {
                return null;
            } else {
                throw ex;
            }
        }
    }

    private static Map<String, ServizioRSRisposta> cacheServizioSPCoopCorrelato = new HashMap<String, ServizioRSRisposta>();
    private ServizioRSRisposta cercaServizioSPCoopCorrelato(IWSRegistroServizi port, String indirizzoRichiesta, ServizioRS servizioRS) throws DAOException_Exception, SOAPFault_Exception {
        try {
            String chiave = servizioRS.getTipo() + "#" + servizioRS.getNome() + servizioRS.getSoggettoErogatore().getTipo() + "#" + servizioRS.getSoggettoErogatore().getNome();
            if(useCache && cacheServizioSPCoopCorrelato.containsKey(chiave)){
                return cacheServizioSPCoopCorrelato.get(chiave);
            }
            GetServizioSPCoopCorrelato richiesta = new GetServizioSPCoopCorrelato();
            richiesta.setServizio(servizioRS);
            ServizioRSRisposta servizio = port.getServizioSPCoopCorrelato(richiesta, indirizzoRichiesta).getReturn();
            if(useCache){
                cacheServizioSPCoopCorrelato.put(chiave, servizio);
            }
            return servizio;
        } catch (SOAPFault_Exception ex) {
            String messaggioErrore = ex.getMessage();
            logger.warn("Servizio non trovato " + messaggioErrore);
            if (messaggioErrore.contains(CostantiSOAP.SERVIZIO_NON_TROVATO)) {
                return null;
            } else {
                throw ex;
            }
        }
    }

    public void arricchisciAzioneDinamica(PortaDelegata portaDelegata) throws FreesbeeException {
        assert (portaDelegata != null) : "Impossibile arricchire le informazioni di una porta delegata nulla";
        //NON DEVO FARE NULLA
    }

    public void arricchisciPortaDelegataDinamica(PortaDelegata portaDelegata,
            String queryFruitore, String queryTipoFruitore,
            String queryErogatore, String queryTipoErogatore,
            String queryServizio, String queryTipoServizio, String queryAzione) throws FreesbeeException {
        assert (portaDelegata != null) : "Impossibile arricchire le informazioni di una porta delegata nulla";

        if (StringUtil.isStringaVuota(portaDelegata.getStringaFruitore()) && StringUtil.isStringaVuota(portaDelegata.getStringaTipoFruitore())) {
            if (StringUtil.isStringaVuota(queryFruitore) && StringUtil.isStringaVuota(queryTipoFruitore)) {
                throw new FreesbeeException("La query string deve includere le informazioni del fruitore.");
            } else {
                portaDelegata.setStringaTipoFruitore(queryTipoFruitore);
                portaDelegata.setStringaFruitore(queryFruitore);
            }
        }

        if (StringUtil.isStringaVuota(portaDelegata.getStringaErogatore()) && StringUtil.isStringaVuota(portaDelegata.getStringaTipoErogatore())) {
            if (StringUtil.isStringaVuota(queryErogatore) && StringUtil.isStringaVuota(queryTipoErogatore)) {
                throw new FreesbeeException("La query string deve includere le informazioni dell'erogatore.");
            } else {
                portaDelegata.setStringaTipoErogatore(queryTipoErogatore);
                portaDelegata.setStringaErogatore(queryErogatore);
            }
        }

        if (StringUtil.isStringaVuota(portaDelegata.getStringaServizio()) && StringUtil.isStringaVuota(portaDelegata.getStringaTipoServizio())) {
            if (StringUtil.isStringaVuota(queryServizio) && StringUtil.isStringaVuota(queryTipoServizio)) {
                throw new FreesbeeException("La query string deve includere le informazioni del servizio.");
            } else {
                portaDelegata.setStringaTipoServizio(queryTipoServizio);
                portaDelegata.setStringaServizio(queryServizio);
            }
        }

        if (!StringUtil.isStringaVuota(queryAzione)) {
            portaDelegata.setStringaAzione(queryAzione);
        }

    }
}
