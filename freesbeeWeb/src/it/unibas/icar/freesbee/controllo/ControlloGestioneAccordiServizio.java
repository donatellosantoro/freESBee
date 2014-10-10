package it.unibas.icar.freesbee.controllo;

import it.unibas.icar.freesbee.modello.AccordoServizio;
import it.unibas.icar.freesbee.modello.Azione;
import it.unibas.icar.freesbee.modello.ProfiloEGov;
import it.unibas.icar.freesbee.modello.Utente;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOAccordoServizio;
import it.unibas.icar.freesbee.persistenza.IDAOAzione;
import it.unibas.icar.freesbee.persistenza.IDAOConfigurazione;
import it.unibas.icar.freesbee.persistenza.IDAOProfiloEGov;
import it.unibas.icar.freesbee.vista.VistaGestioneAccordiServizio;
import java.util.Collections;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ControlloGestioneAccordiServizio {

    private Log logger = LogFactory.getLog(this.getClass());
    private VistaGestioneAccordiServizio vista;
    private IDAOProfiloEGov daoProfiloEGov;
    private IDAOAzione daoAzione;
    private IDAOConfigurazione daoConfigurazione;
    private IDAOAccordoServizio daoAccordoServizio;
    private String messaggio;
    private String errore;
    private String conferma;
    private Utente utente;

    public Log getLogger() {
        return logger;
    }

    public String confermaPolicy(){
        this.vista.setVisualizzaPolicy(false);
        try {
            AccordoServizio accordoModificare = this.vista.getPolicyAccordoServizio();
            daoAccordoServizio.makePersistent(this.utente, accordoModificare);
            this.setMessaggio("Policy modificata correttamente.");
            AccordoServizio nuovoAccordo = new AccordoServizio();
            nuovoAccordo.setProfiloEGov(new ProfiloEGov());
            gestioneInserisciAccordiServizi(nuovoAccordo);
            caricaTuttiAccordi();
        } catch (DAOException ex) {
            logger.error("Impossibile modificare l'accordo di servizio. " + ex);
            String erroreFormattato = ex.getMessage().substring(ex.getMessage().indexOf(":") + 1);
            this.setErrore(erroreFormattato);
        }
        return null;
    }

    public String apriModalPanelPolicy(){
        AccordoServizio accordoSelezionato = getAccordoSelezionato();
        if (accordoSelezionato == null) {
            this.setErrore("Impossibile leggere l'accordo selezionato. Aggiornare la pagina e riprovare");
            return null;
        }
        //this.vista.setNuovoAccordoServizio(accordoSelezionato);
        this.vista.setPolicyAccordoServizio(accordoSelezionato);
        this.vista.setVisualizzaPolicy(true);
        return null;
    }

    public String caricaGestioneAccordiServizi() {
//        this.getVista().setSoloGestione(false);
        AccordoServizio nuovoAccordo = new AccordoServizio();
        nuovoAccordo.setProfiloEGov(new ProfiloEGov());
        gestioneInserisciAccordiServizi(nuovoAccordo);
        this.vista.setAzioneAggiungere(new Azione());
        this.vista.setInserisciAzione(false);
        try {
            this.vista.setNicaPresente(daoConfigurazione.find(utente).isInviaANICA());
        } catch (DAOException ex) {
            logger.error("Impossibile leggere la configurazione " + ex);
        }
        caricaTuttiAccordi();
        return "gestioneAccordiServizi";
    }

    public String gestioneInserisciAccordiServizi(AccordoServizio accordo) {
        this.vista.setInserisci(true);
        this.vista.setModifica(false);
        this.vista.setElimina(false);
        this.vista.setVisualizzaPolicy(false);
        this.vista.setNuovoAccordoServizio(accordo);
        return null;
    }

    public String gestioneModificaAccordiServizi(AccordoServizio accordo) {
        this.vista.setInserisci(false);
        this.vista.setModifica(true);
        this.vista.setElimina(false);
        this.vista.setNuovoAccordoServizio(accordo);
        return null;
    }

    public String gestioneEliminaAccordiServizi(AccordoServizio accordo) {
        this.vista.setInserisci(false);
        this.vista.setModifica(false);
        this.vista.setElimina(true);
        return null;
    }

    public void caricaTuttiAccordi() {
        if(this.vista.isNicaPresente()){
            return;
        }
        try {
            List<AccordoServizio> lista = daoAccordoServizio.findAllNoLazy(this.utente);
            Collections.sort(lista);
            if (lista.size() == 0) {
                this.setMessaggio("Non ho trovato alcun accordo di servizio.");
                this.vista.setPannelloVisibile(true);
            } else {
                this.vista.setPannelloVisibile(false);
            }
            this.vista.setListaAccordi(lista);
        } catch (DAOException ex) {
            logger.error("Impossibile leggere l'elenco degli accordi di servizio dalla base di dati. " + ex);
            this.setErrore("Impossibile leggere l'elenco degli accordi di servizio dalla base di dati. Controllare che freESBee sia avviato");
        }
    }

    public String inserisci() {
        try {
            ProfiloEGov profiloEGov = daoProfiloEGov.makePersistent(this.utente, this.vista.getNuovoAccordoServizio().getProfiloEGov());
            if (logger.isInfoEnabled()) {
                logger.info("Profilo egov aggiunto correttamente " + this.vista.getNuovoAccordoServizio().getProfiloEGov().getId());
            }
            this.vista.getNuovoAccordoServizio().setIdProfiloEGov(profiloEGov.getId());
            daoAccordoServizio.makePersistent(this.utente, this.vista.getNuovoAccordoServizio());
            this.setMessaggio("Accordo inserito correttamente.");
            AccordoServizio nuovoAccordo = new AccordoServizio();
            nuovoAccordo.setProfiloEGov(new ProfiloEGov());
            gestioneInserisciAccordiServizi(nuovoAccordo);
            caricaTuttiAccordi();
        } catch (DAOException ex) {
            logger.error("Impossibile inserire il nuovo accordo di servizio. " + ex);
            String erroreFormattato = ex.getMessage().substring(ex.getMessage().indexOf(":") + 1);
            this.setErrore(erroreFormattato);
        }

        return "gestioneAccordiServizi";
    }

    public String elimina() {
        try {
            AccordoServizio accordoEliminare = this.vista.getAccordoServizioEliminare();
            daoAccordoServizio.makeTransient(this.utente, accordoEliminare);
            this.setMessaggio("Accordo di servizio eliminato correttamente.");

            AccordoServizio nuovoAccordo = new AccordoServizio();
            nuovoAccordo.setProfiloEGov(new ProfiloEGov());
            this.vista.setAccordoServizioEliminare(nuovoAccordo);
            gestioneInserisciAccordiServizi(nuovoAccordo);
            caricaTuttiAccordi();
        } catch (DAOException ex) {
            logger.error("Impossibile eliminare l'accordo di servizio. " + ex);
            String erroreFormattato = ex.getMessage().substring(ex.getMessage().indexOf(":") + 1);
            this.setErrore(erroreFormattato);
        }
        return null;
    }

    public String modifica() {
        try {
            ProfiloEGov profiloEGov = daoProfiloEGov.makePersistent(this.utente, this.vista.getNuovoAccordoServizio().getProfiloEGov());
            if (logger.isInfoEnabled()) {
                logger.info("Profilo egov modificato correttamente " + this.vista.getNuovoAccordoServizio().getProfiloEGov().getId());
            }
            this.vista.getNuovoAccordoServizio().setIdProfiloEGov(profiloEGov.getId());
            AccordoServizio accordoModificare = this.vista.getNuovoAccordoServizio();
            daoAccordoServizio.makePersistent(this.utente, accordoModificare);
            this.setMessaggio("Accordo di servizio modificato correttamente.");
            AccordoServizio nuovoAccordo = new AccordoServizio();
            nuovoAccordo.setProfiloEGov(new ProfiloEGov());
            gestioneInserisciAccordiServizi(nuovoAccordo);
            caricaTuttiAccordi();
        } catch (DAOException ex) {
            logger.error("Impossibile modificare l'accordo di servizio. " + ex);
            String erroreFormattato = ex.getMessage().substring(ex.getMessage().indexOf(":") + 1);
            this.setErrore(erroreFormattato);
        }
        return "gestioneAccordiServizi";
    }

    public String schermoModificaDaDettagli() {
        AccordoServizio accordoSelezionato = getAccordoSelezionato();
        if (accordoSelezionato == null) {
            this.setErrore("Impossibile leggere l'accordo selezionato. Aggiornare la pagina e riprovare");
            return null;
        }

        this.vista.setNuovoAccordoServizio(accordoSelezionato);
        gestioneModificaAccordiServizi(accordoSelezionato);

        this.vista.setPannelloVisibile(true);
        return "gestioneAccordiServizi";
    }

    public String schermoModificaDaTabella() {
        AccordoServizio accordoSelezionato = (AccordoServizio) this.vista.getTabellaAccordi().getRowData();
        this.vista.setNuovoAccordoServizio(accordoSelezionato);
        gestioneModificaAccordiServizi(accordoSelezionato);
        this.vista.setPannelloVisibile(true);
        return "gestioneAccordiServizi";
    }

    public String schermoEliminaDaDettagli() {
        AccordoServizio accordoSelezionato = getAccordoSelezionato();
        if (accordoSelezionato == null) {
            this.setErrore("Impossibile leggere l'accordo selezionato. Aggiornare la pagina e riprovare");
            return null;
        }
        this.vista.setAccordoServizioEliminare(accordoSelezionato);
        gestioneEliminaAccordiServizi(accordoSelezionato);
        this.setConferma("Sei sicuro di voler eliminare l'accordo di servizio e i servizi e le azioni ad esso collegati?");
        return null;
    }

    public String schermoEliminaDaTabella() {
        AccordoServizio accordoSelezionato = (AccordoServizio) this.vista.getTabellaAccordi().getRowData();
        this.vista.setAccordoServizioEliminare(accordoSelezionato);
        gestioneEliminaAccordiServizi(accordoSelezionato);
        this.setConferma("Sei sicuro di voler eliminare l'accordo di servizio e i servizi e le azioni ad esso collegati?");
        return null;
    }

    public String schermoAggiungiServizioDaDettagli() {
        AccordoServizio accordoSelezionato = getAccordoSelezionato();
        if (accordoSelezionato == null) {
            this.setErrore("Impossibile leggere l'accordo selezionato. Aggiornare la pagina e riprovare");
            return null;
        }
        this.vista.getAzioneAggiungere().setProfiloCollaborazione(accordoSelezionato.getProfiloCollaborazione());
        this.vista.setAccordoServizioEliminare(accordoSelezionato);
        this.vista.setInserisciAzione(true);
        return null;
    }

    public String schermoEliminaAzioneDaTabella() {
        try {
            Azione azioneSelezionata = (Azione) this.vista.getTabellaAzioni().getRowData();
            azioneSelezionata.getAccordoServizio().getAzioni().remove(azioneSelezionata);
            daoAzione.makeTransient(this.utente, azioneSelezionata);
            daoAccordoServizio.makePersistent(this.utente, azioneSelezionata.getAccordoServizio());
            this.setMessaggio("Azione eliminata correttamente.");
            caricaTuttiAccordi();
        } catch (DAOException ex) {
            logger.error("Impossibile eliminare l'azione. " + ex);
            this.setErrore("Impossibile eliminare l'azione.");
        }
        return "gestioneAccordiServizi";
    }

    public String inserisciAzione() {
        try {
            AccordoServizio accordoModificare = this.vista.getAccordoServizioEliminare();
            Azione azioneAggiungere = this.vista.getAzioneAggiungere();
            ProfiloEGov profiloAggiungere = this.vista.getProfiloAggiungere();

            accordoModificare.getAzioni().add(azioneAggiungere);
            daoAccordoServizio.makePersistent(this.utente, accordoModificare);
            azioneAggiungere.setAccordoServizio(accordoModificare);
            azioneAggiungere.setIdAccordoServizio(accordoModificare.getId());
            if (this.vista.isProfiloEgovSpecifico()) {
                azioneAggiungere.setProfiloEGov(profiloAggiungere);
                daoProfiloEGov.makePersistent(this.utente, profiloAggiungere);
                azioneAggiungere.setIdProfiloEGov(profiloAggiungere.getId());
            }
            daoAzione.makePersistent(this.utente, azioneAggiungere);

            this.setMessaggio("Azione inserita correttamente.");
            AccordoServizio nuovoAccordo = new AccordoServizio();
            nuovoAccordo.setProfiloEGov(new ProfiloEGov());
            this.vista.setAzioneAggiungere(new Azione());
            this.vista.setProfiloAggiungere(new ProfiloEGov());
            this.vista.setInserisciAzione(false);
            gestioneInserisciAccordiServizi(nuovoAccordo);
            caricaTuttiAccordi();
        } catch (DAOException ex) {
            logger.error("Impossibile aggiungere l'azione all'accordo specificato. " + ex);
            this.setErrore("Impossibile aggiungere l'azione all'accordo specificato. ");
        }
        return "gestioneAccordiServizi";
    }

    private AccordoServizio getAccordoSelezionato() {
        AccordoServizio accordoSelezionato = null;
        try {
            long idAccordoSelezionato = ((Long) this.vista.getAccordoSelezionato().getValue()).longValue();
            accordoSelezionato = daoAccordoServizio.findById(this.utente, idAccordoSelezionato, false);
        } catch (Exception ex) {
            logger.error("Impossibile leggere l'accordo selezionato. " + ex);
            return null;
        }
        return accordoSelezionato;
    }

    public void setLogger(Log logger) {
        this.logger = logger;
    }

    public VistaGestioneAccordiServizio getVista() {
        return vista;
    }

    public void setVista(VistaGestioneAccordiServizio vista) {
        this.vista = vista;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    public String getErrore() {
        return errore;
    }

    public void setErrore(String errore) {
        this.errore = errore;
    }

    public String getConferma() {
        return conferma;
    }

    public void setConferma(String conferma) {
        this.conferma = conferma;
    }

    public IDAOProfiloEGov getDaoProfiloEGov() {
        return daoProfiloEGov;
    }

    public void setDaoProfiloEGov(IDAOProfiloEGov daoProfiloEGov) {
        this.daoProfiloEGov = daoProfiloEGov;
    }

    public IDAOAccordoServizio getDaoAccordoServizio() {
        return daoAccordoServizio;
    }

    public void setDaoAccordoServizio(IDAOAccordoServizio daoAccordoServizio) {
        this.daoAccordoServizio = daoAccordoServizio;
    }

    public boolean isVisualizzaMessaggio() {
        return (this.messaggio != null && !this.messaggio.equalsIgnoreCase(""));
    }

    public boolean isVisualizzaErrore() {
        return (this.errore != null && !this.errore.equalsIgnoreCase(""));
    }

    public boolean isVisualizzaConferma() {
        return (this.getConferma() != null && !this.conferma.equalsIgnoreCase(""));
    }

    public IDAOAzione getDaoAzione() {
        return daoAzione;
    }

    public void setDaoAzione(IDAOAzione daoAzione) {
        this.daoAzione = daoAzione;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public IDAOConfigurazione getDaoConfigurazione() {
        return daoConfigurazione;
    }

    public void setDaoConfigurazione(IDAOConfigurazione daoConfigurazione) {
        this.daoConfigurazione = daoConfigurazione;
    }
    
    
}
