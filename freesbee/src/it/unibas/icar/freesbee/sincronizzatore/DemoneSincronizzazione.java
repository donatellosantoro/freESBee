package it.unibas.icar.freesbee.sincronizzatore;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import it.unibas.icar.freesbee.modello.AccordoServizio;
import it.unibas.icar.freesbee.modello.ConfigurazioneStatico;
import it.unibas.icar.freesbee.modello.Servizio;
import it.unibas.icar.freesbee.modello.Sincronizzazione;
import it.unibas.icar.freesbee.modello.Soggetto;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOAccordoServizio;
import it.unibas.icar.freesbee.persistenza.IDAOAzione;
import it.unibas.icar.freesbee.persistenza.IDAOProfiloEGov;
import it.unibas.icar.freesbee.persistenza.IDAOServizio;
import it.unibas.icar.freesbee.persistenza.IDAOSincronizzazione;
import it.unibas.icar.freesbee.persistenza.IDAOSoggetto;
import it.unibas.icar.freesbee.persistenza.hibernate.DAOUtilHibernate;
import it.unibas.icar.freesbee.ws.registroservizi.AccordoServizioRSRisposta;
import it.unibas.icar.freesbee.ws.registroservizi.ServizioRS;
import it.unibas.icar.freesbee.ws.registroservizi.ServizioRSRisposta;
import it.unibas.icar.freesbee.ws.registroservizi.SoggettoRS;
import it.unibas.icar.freesbee.ws.registroservizi.SoggettoRSRisposta;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAccordoServizio;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAllIdAccordiServizio;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAllIdServiziSPCoop;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAllIdServiziSPCoopCorrelati;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAllIdSoggettiSPCoop;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetServizioSPCoop;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetServizioSPCoopCorrelato;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetSoggettoSPCoop;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.IWSRegistroServizi;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.WSRegistroServiziImplService;
import java.net.URL;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;

//@Singleton
public class DemoneSincronizzazione extends Thread {

    private static Log logger = LogFactory.getLog(DemoneSincronizzazione.class);
    private IDAOSincronizzazione daoSincronizzazione;
    private IDAOAzione daoAzione;
    private IDAOServizio daoServizio;
    private IDAOSoggetto daoSoggetto;
    private IDAOAccordoServizio daoAccordoServizio;
    private IDAOProfiloEGov daoProfiloEgov;
    private SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();

    //@Inject()
    public DemoneSincronizzazione(IDAOSincronizzazione daoSincronizzazione, IDAOAzione daoAzione, IDAOServizio daoServizio, IDAOSoggetto daoSoggetto, IDAOAccordoServizio daoAccordoServizio, IDAOProfiloEGov daoProfiloEgov) {
        this.daoSincronizzazione = daoSincronizzazione;
        this.daoAzione = daoAzione;
        this.daoServizio = daoServizio;
        this.daoSoggetto = daoSoggetto;
        this.daoAccordoServizio = daoAccordoServizio;
        this.daoProfiloEgov = daoProfiloEgov;
        this.start();
    }

    @Override
    public void run() {
        if (logger.isInfoEnabled()) logger.info("Avviata la sincronizzazione.");
        ConfigurazioneStatico config = ConfigurazioneStatico.getInstance();
        int contatore = 0;
        while (config.isAttivo()) {
            try {
                sessionFactory.getCurrentSession().beginTransaction();
                List<Sincronizzazione> listaSincronizzazioni = daoSincronizzazione.findAll();
                sessionFactory.getCurrentSession().getTransaction().commit();
                for (Sincronizzazione sincronizzazione : listaSincronizzazioni) {
                    this.sincronizza(sincronizzazione, contatore == 0);
                }
                contatore++;
                contatore = (contatore % config.getIntervalloSincronizzazioneTotale());
                if (logger.isDebugEnabled()) logger.debug("Mi metto in pausa per " + (config.getIntervalloSincronizzazione() / 1000) + " secondi");
                try {
                    sleep(config.getIntervalloSincronizzazione());
                    sleep(config.getIntervalloSincronizzazione());
                } catch (InterruptedException ine) {
                }
            } catch (DAOException ex) {
                logger.error("Impossibile sincronizzarsi. " + ex);
                break;
            } finally {
                try {
                    if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
                        sessionFactory.getCurrentSession().getTransaction().rollback();
                    }
                } catch (Throwable rbEx) {
                }
            }
        }
        logger.info("Sincronizzazione fermata");
    }

    private void sincronizza(Sincronizzazione sincronizzazione, boolean completa) {
        try {
            if (logger.isDebugEnabled()) logger.debug("Devo sincronizzarmi con " + sincronizzazione.getNome() + "@" + sincronizzazione.getIndirizzo());
            if (logger.isDebugEnabled()) logger.debug("Sincronizzazione con un freesbee? " + sincronizzazione.isFreesbee() + " - Completa?" + completa);
            sessionFactory.getCurrentSession().beginTransaction();
            String indirizzoWSDL = sincronizzazione.getIndirizzo();
            String indirizzoRichiesta = null;
            if (!sincronizzazione.isFreesbee()) {
                ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();
                indirizzoWSDL = conf.getInteroperabilitaRegistroFreesbee(); //E GLI DEVO INVIARE L'INDIRIZZO DEL REGISTRO
                indirizzoRichiesta = sincronizzazione.getIndirizzo(); //E GLI DEVO INVIARE L'INDIRIZZO DEL REGISTRO
            } else {
                if (!(indirizzoWSDL.toLowerCase()).endsWith("?wsdl")) {
                    indirizzoWSDL = indirizzoWSDL + "?wsdl";
                }
            }
            WSRegistroServiziImplService service = new WSRegistroServiziImplService(new URL(indirizzoWSDL));
            IWSRegistroServizi port = service.getWSRegistroServiziImplPort();
            this.sincronizzaSoggetti(port, indirizzoRichiesta);
            this.sincronizzaAccordi(port, indirizzoRichiesta);
            this.sincronizzaServizi(port, indirizzoRichiesta);
            this.sincronizzaServiziCorrelati(port, indirizzoRichiesta);
            sessionFactory.getCurrentSession().getTransaction().commit();
            if (logger.isDebugEnabled()) logger.debug("Sincronizzazione con " + sincronizzazione.getNome() + " completata!");
        } catch (Exception ex) {
            if (logger.isDebugEnabled()) ex.printStackTrace();
            logger.error("Impossibile sincronizzarmi con " + sincronizzazione.getNome() + "@" + sincronizzazione.getIndirizzo() + ". " + ex);
            if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
                if (logger.isDebugEnabled()) logger.debug("C'è una transazione di hibernate attiva e faccio il rollback");
                sessionFactory.getCurrentSession().getTransaction().rollback();
            }
        }

    }

    private void sincronizzaServizi(IWSRegistroServizi port, String indirizzoRichiesta) throws Exception {
        GetAllIdServiziSPCoop richiesta = new GetAllIdServiziSPCoop();
        List<ServizioRS> listaServizi = port.getAllIdServiziSPCoop(richiesta, indirizzoRichiesta).getReturn();
        if (logger.isDebugEnabled()) logger.debug("Trovati " + listaServizi.size() + " servizi.");
        for (ServizioRS servizioRS : listaServizi) {
            if (logger.isDebugEnabled()) logger.debug("Richiedo i dettagli del servizio " + servizioRS.getTipo() + " - " + servizioRS.getNome());
            Soggetto soggettoErogatore = daoSoggetto.findByNome(servizioRS.getSoggettoErogatore().getNome(), servizioRS.getSoggettoErogatore().getTipo());
            if (soggettoErogatore == null) {
                throw new IllegalStateException("Impossibile sincronizzare il servizio. Il soggetto erogatore non è presente nel registro");
            }
            Servizio servizioEsistente = daoServizio.findByNome(servizioRS.getNome(), servizioRS.getTipo(), soggettoErogatore);
            GetServizioSPCoop richiestaServizio = new GetServizioSPCoop();
            richiestaServizio.setServizio(servizioRS);
            if (logger.isDebugEnabled()) logger.debug("Richiedo il servizio.getNome " + servizioRS.getNome());
            if (logger.isDebugEnabled()) logger.debug("Richiedo il servizio.getTipo " + servizioRS.getTipo());
            ServizioRSRisposta nuovoServizio = port.getServizioSPCoop(richiestaServizio, indirizzoRichiesta).getReturn();
            if (servizioEsistente == null) {
                aggiungiServizio(soggettoErogatore, nuovoServizio, servizioRS);
            } else {
                if (nuovoServizio.getOraRegistrazione() == null || servizioEsistente.getOraRegistrazione() == null || (nuovoServizio.getOraRegistrazione().getTime() > servizioEsistente.getOraRegistrazione().getTime())) {
                    if (logger.isDebugEnabled()) logger.debug("Il servizio " + servizioEsistente.getTipo() + " - " + servizioEsistente.getNome() + " è presente nel registro, ma dobbiamo aggiornarlo");
                    this.aggiornaServizio(servizioEsistente, nuovoServizio.trasformaServizio());
                    daoServizio.makePersistent(servizioEsistente);
                    if (logger.isDebugEnabled()) logger.debug("Il servizio " + servizioEsistente.getTipo() + " - " + servizioEsistente.getNome() + " aggiornato correttamente");
                } else {
                    if (logger.isDebugEnabled()) logger.debug("Il servizio " + servizioEsistente.getTipo() + " - " + servizioEsistente.getNome() + " è presente nel registro");
                }
            }
        }
    }

    private void sincronizzaServiziCorrelati(IWSRegistroServizi port, String indirizzoRichiesta) throws Exception {
        GetAllIdServiziSPCoopCorrelati richiesta = new GetAllIdServiziSPCoopCorrelati();
        List<ServizioRS> listaServizi = null;
        try {
            listaServizi = port.getAllIdServiziSPCoopCorrelati(richiesta, indirizzoRichiesta).getReturn();
        } catch (Exception e) {
            String eccezione = e.getMessage();
            if (eccezione.contains("Servizi non trovati")) {
                return;
            }
            throw e;
        }
        if (logger.isDebugEnabled()) logger.debug("Trovati " + listaServizi.size() + " servizi.");
        for (ServizioRS servizioRS : listaServizi) {
            if (logger.isDebugEnabled()) logger.debug("Richiedo i dettagli del servizio correlato " + servizioRS.getTipo() + " - " + servizioRS.getNome());
            Soggetto soggettoErogatore = daoSoggetto.findByNome(servizioRS.getSoggettoErogatore().getNome(), servizioRS.getSoggettoErogatore().getTipo());
            if (soggettoErogatore == null) {
                throw new IllegalStateException("Impossibile sincronizzare il servizio correlato. Il soggetto erogatore non è presente nel registro");
            }
            Servizio servizioEsistente = daoServizio.findByNome(servizioRS.getNome(), servizioRS.getTipo(), soggettoErogatore);
            GetServizioSPCoopCorrelato richiestaServizio = new GetServizioSPCoopCorrelato();
            richiestaServizio.setServizio(servizioRS);
            ServizioRSRisposta nuovoServizio = port.getServizioSPCoopCorrelato(richiestaServizio, indirizzoRichiesta).getReturn();
            if (servizioEsistente == null) {
                aggiungiServizioCorrelato(soggettoErogatore, servizioRS, nuovoServizio);
            } else {
                if (nuovoServizio.getOraRegistrazione() == null || servizioEsistente.getOraRegistrazione() == null || (nuovoServizio.getOraRegistrazione().getTime() > servizioEsistente.getOraRegistrazione().getTime())) {
                    if (logger.isDebugEnabled()) logger.debug("Il servizio correlato " + servizioEsistente.getTipo() + " - " + servizioEsistente.getNome() + " è presente nel registro, ma dobbiamo aggiornarlo");
                    this.aggiornaServizio(servizioEsistente, nuovoServizio.trasformaServizio());
                    daoServizio.makePersistent(servizioEsistente);
                    if (logger.isDebugEnabled()) logger.debug("Il servizio correlato " + servizioEsistente.getTipo() + " - " + servizioEsistente.getNome() + " aggiornato correttamente");
                } else {
                    if (logger.isDebugEnabled()) logger.debug("Il servizio correlato " + servizioEsistente.getTipo() + " - " + servizioEsistente.getNome() + " è presente nel registro");
                }
            }
        }
    }

    private void sincronizzaSoggetti(IWSRegistroServizi port, String indirizzoRichiesta) throws Exception {
        GetAllIdSoggettiSPCoop richiesta = new GetAllIdSoggettiSPCoop();
        List<SoggettoRSRisposta> listaSoggetti = port.getAllIdSoggettiSPCoop(richiesta, indirizzoRichiesta).getReturn();
        if (logger.isDebugEnabled()) logger.debug("Trovati " + listaSoggetti.size() + " soggetti.");
        for (SoggettoRSRisposta soggetto : listaSoggetti) {
            if (logger.isDebugEnabled()) logger.debug("Cerco se esiste il soggetto " + soggetto.getTipo() + " - " + soggetto.getNome());
            Soggetto soggettoEsistente = daoSoggetto.findByNome(soggetto.getNome(), soggetto.getTipo());
            GetSoggettoSPCoop richiestaSoggetto = new GetSoggettoSPCoop();
            richiestaSoggetto.setSoggetto(new SoggettoRS(soggetto.getTipo(), soggetto.getNome()));
            SoggettoRSRisposta nuovoSoggetto = port.getSoggettoSPCoop(richiestaSoggetto, indirizzoRichiesta).getReturn();
            if (soggettoEsistente == null) {
                if (logger.isDebugEnabled()) logger.debug("Il soggetto " + soggetto.getTipo() + " - " + soggetto.getNome() + " non è presente nel registro e dobbiamo aggiugerlo");
                Soggetto soggettoSalvare = nuovoSoggetto.trasformaSoggetto();
                soggettoSalvare.setId(0);
                if (logger.isInfoEnabled()) logger.info("Aggiungo il soggetto " + soggettoSalvare.getNome());
                soggettoSalvare.setDescrizione(soggettoSalvare.getDescrizione() + " [sync]");
                daoSoggetto.makePersistent(soggettoSalvare);
                if (logger.isDebugEnabled()) logger.debug("Il soggettogetNome " + soggettoSalvare.getNome() + " salvato correttamente");
            } else {
                if (nuovoSoggetto.getOraRegistrazione() == null || soggettoEsistente.getOraRegistrazione() == null || (nuovoSoggetto.getOraRegistrazione().getTime() > soggettoEsistente.getOraRegistrazione().getTime())) {
                    if (logger.isDebugEnabled()) logger.debug("Il soggetto " + soggettoEsistente.getTipo() + " - " + soggettoEsistente.getNome() + " è presente nel registro, ma dobbiamo aggiornarlo");
                    this.aggiornaSoggetto(soggettoEsistente, nuovoSoggetto.trasformaSoggetto());
                    daoSoggetto.makePersistent(soggettoEsistente);
                    if (logger.isDebugEnabled()) logger.debug("Il soggetto " + soggettoEsistente.getTipo() + " - " + soggettoEsistente.getNome() + " aggiornato correttamente");
                } else {
                    if (logger.isDebugEnabled()) logger.debug("Il soggetto " + soggettoEsistente.getTipo() + " - " + soggettoEsistente.getNome() + " è presente nel registro");
                }
            }
        }
    }

    private void sincronizzaAccordi(IWSRegistroServizi port, String indirizzoRichiesta) throws Exception {
        GetAllIdAccordiServizio richiesta = new GetAllIdAccordiServizio();
        List<AccordoServizio> listaAccordi = port.getAllIdAccordiServizio(richiesta, indirizzoRichiesta).getReturn();
        if (logger.isDebugEnabled()) logger.debug("Trovati " + listaAccordi.size() + " accordi.");
        for (AccordoServizio accordoServizio : listaAccordi) {
            AccordoServizio accordoEsistente = daoAccordoServizio.findByNome(accordoServizio.getNome());
            GetAccordoServizio richiestaAS = new GetAccordoServizio();
            richiestaAS.setAccordoServizio(accordoServizio);
            AccordoServizioRSRisposta nuovoAccordo = port.getAccordoServizio(richiestaAS, indirizzoRichiesta).getReturn();

            if (logger.isDebugEnabled()) logger.debug("L'accordo di servizio " + accordoServizio.getNome() + " " + accordoServizio.isPrivato());


            if (accordoEsistente == null) {
                if (logger.isDebugEnabled()) logger.debug("L'accordo di servizio " + accordoServizio.getNome() + " non è presente nel registro e dobbiamo aggiugerlo");
                AccordoServizio accordoSalvare = nuovoAccordo.trasformaAccordoServizio();
                if (logger.isInfoEnabled()) logger.info("Aggiungo l'accordo " + accordoSalvare.getNome());
                daoProfiloEgov.makePersistent(accordoSalvare.getProfiloEGov());
                daoAccordoServizio.makePersistent(accordoSalvare);
                if (logger.isDebugEnabled()) logger.debug("Accordo " + accordoSalvare.getNome() + " salvato correttamente");
            } else {
                if (!nuovoAccordo.isPrivato() && (nuovoAccordo.getOraRegistrazione() == null || accordoEsistente.getOraRegistrazione() == null || (nuovoAccordo.getOraRegistrazione().after(accordoEsistente.getOraRegistrazione())))) {
                    if (logger.isDebugEnabled()) logger.debug("L'accordo di servizio " + accordoServizio.getNome() + " è presente nel registro, ma dobbiamo aggiornarlo");
                    this.aggiornaAccordoServizio(accordoEsistente, nuovoAccordo.trasformaAccordoServizio());
                    daoAccordoServizio.makePersistent(accordoEsistente);
                    if (logger.isDebugEnabled()) logger.debug("Accordo " + accordoServizio.getNome() + " aggiornato correttamente");

                } else {
                    if (logger.isDebugEnabled()) logger.debug("L'accordo di servizio " + accordoServizio.getNome() + " è presente nel registro");
                }
            }
        }
    }

    private void aggiungiServizio(Soggetto soggettoErogatore, ServizioRSRisposta nuovoServizio, ServizioRS servizioRS) throws IllegalStateException, DAOException {
        if (logger.isDebugEnabled()) logger.debug("Il servizio " + servizioRS.getTipo() + " - " + servizioRS.getNome() + " non è presente nel registro e dobbiamo aggiugerlo");
        if (nuovoServizio.getAccordoServizio().getNome() == null || nuovoServizio.getAccordoServizio().getNome().isEmpty()) {
            logger.warn("Il servizio " + servizioRS.getTipo() + " - " + servizioRS.getNome() + " non e' associato ad alcun accordo di servizio, quindi lo escludo dalla sincronizzazione");
        } else {
            AccordoServizio accordo = daoAccordoServizio.findByNome(nuovoServizio.getAccordoServizio().getNome());
            if (accordo == null) {
                logger.warn("Impossibile sincronizzare il servizio. L'accordo a cui fa riferimento non è presente nel registro. Probabilmente il servizio e' pubblico mentre l'accordo e' privato");
            } else {
                Servizio servizioSalvare = nuovoServizio.trasformaServizio();
                servizioSalvare.setAccordoServizio(accordo);
                servizioSalvare.setErogatore(soggettoErogatore);
                servizioSalvare.setId(0);
                List<SoggettoRSRisposta> fruitoriRS = nuovoServizio.getFruitori();
                for (SoggettoRSRisposta soggettoRSRisposta : fruitoriRS) {
                    Soggetto soggettoFruitore = daoSoggetto.findByNome(soggettoRSRisposta.getNome(), soggettoRSRisposta.getTipo());
                    if (soggettoFruitore == null) {
                        throw new IllegalStateException("Impossibile sincronizzare il servizio. Il soggetto fruitore non è presente nel registro");
                    }
                    servizioSalvare.getFruitori().add(soggettoFruitore);
                }
                if (logger.isInfoEnabled()) logger.info("Aggiungo il servizio " + servizioSalvare.getNome());
                daoServizio.makePersistent(servizioSalvare);
                if (logger.isDebugEnabled()) logger.debug("Il servizio " + servizioSalvare.getNome() + " e' stato salvato correttamente");
            }
        }
    }

    private void aggiungiServizioCorrelato(Soggetto soggettoErogatore, ServizioRS servizioRS, ServizioRSRisposta nuovoServizio) throws DAOException, IllegalStateException {
        if (logger.isDebugEnabled()) logger.debug("Il servizio " + servizioRS.getTipo() + " - " + servizioRS.getNome() + " non è presente nel registro e dobbiamo aggiugerlo");
        AccordoServizio accordo = daoAccordoServizio.findByNome(nuovoServizio.getAccordoServizio().getNome());
        if (accordo == null) {
            throw new IllegalStateException("Impossibile sincronizzare il servizio correlato. L'accordo a cui fa riferimento non è presente nel registro");
        }
        Servizio servizioSalvare = nuovoServizio.trasformaServizio();
        servizioSalvare.setAccordoServizio(accordo);
        servizioSalvare.setErogatore(soggettoErogatore);
        servizioSalvare.setId(0);
        List<SoggettoRSRisposta> fruitoriRS = nuovoServizio.getFruitori();
        for (SoggettoRSRisposta soggettoRSRisposta : fruitoriRS) {
            Soggetto soggettoFruitore = daoSoggetto.findByNome(soggettoRSRisposta.getNome(), soggettoRSRisposta.getTipo());
            if (soggettoFruitore == null) {
                throw new IllegalStateException("Impossibile sincronizzare il servizio correlato. Il soggetto fruitore non è presente nel registro");
            }
            servizioSalvare.getFruitori().add(soggettoFruitore);
        }
        if (logger.isInfoEnabled()) logger.info("Aggiungo il servizio correlato " + servizioSalvare.getNome());
        daoServizio.makePersistent(servizioSalvare);
        if (logger.isDebugEnabled()) logger.debug("Il servizio correlato " + servizioSalvare.getNome() + " e' stato salvato correttamente");
    }

    public void aggiornaSoggetto(Soggetto vecchio, Soggetto nuovo) {
        vecchio.setNome(nuovo.getNome());
        vecchio.setTipo(nuovo.getTipo());
        vecchio.setDescrizione(nuovo.getDescrizione());
        vecchio.setPortaDominio(nuovo.getPortaDominio());
        vecchio.setOraRegistrazione(nuovo.getOraRegistrazione());
    }

    private void aggiornaAccordoServizio(AccordoServizio vecchio, AccordoServizio nuovo) {
        vecchio.setDescrizione(nuovo.getDescrizione());
        vecchio.setProfiloCollaborazione(nuovo.getProfiloCollaborazione());
        vecchio.setPrivato(nuovo.isPrivato());
        vecchio.setOraRegistrazione(nuovo.getOraRegistrazione());

        vecchio.getProfiloEGov().setConfermaRicezione(nuovo.getProfiloEGov().isConfermaRicezione());
        vecchio.getProfiloEGov().setConsegnaInOrdine(nuovo.getProfiloEGov().isConsegnaInOrdine());
        vecchio.getProfiloEGov().setGestioneDuplicati(nuovo.getProfiloEGov().isGestioneDuplicati());
        vecchio.getProfiloEGov().setIdCollaborazione(nuovo.getProfiloEGov().isIdCollaborazione());

    }

    private void aggiornaServizio(Servizio vecchio, Servizio nuovo) {
        vecchio.setNome(nuovo.getNome());
        vecchio.setTipo(nuovo.getTipo());
        vecchio.setCorrelato(nuovo.isCorrelato());
        vecchio.setOraRegistrazione(nuovo.getOraRegistrazione());
        List<Soggetto> fruitori = nuovo.getFruitori();
        if (fruitori != null) {
            for (Soggetto soggetto : fruitori) {
                if (!esisteSoggetto(vecchio.getFruitori(), soggetto)) {
                    vecchio.getFruitori().add(soggetto);
                }
            }
        }
    }

    private boolean esisteSoggetto(List<Soggetto> fruitori, Soggetto fruitore) {
        for (Soggetto soggetto : fruitori) {
            if (soggetto.equals(fruitore)) {
                return true;
            }
        }
        return false;
    }
}
