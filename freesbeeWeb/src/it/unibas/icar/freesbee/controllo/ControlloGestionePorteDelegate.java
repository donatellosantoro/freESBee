package it.unibas.icar.freesbee.controllo;

import it.unibas.icar.freesbee.modello.Azione;
import it.unibas.icar.freesbee.modello.PortaDelegata;
import it.unibas.icar.freesbee.modello.Servizio;
import it.unibas.icar.freesbee.modello.Soggetto;
import it.unibas.icar.freesbee.modello.Utente;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOAzione;
import it.unibas.icar.freesbee.persistenza.IDAOConfigurazione;
import it.unibas.icar.freesbee.persistenza.IDAOPortaDelegata;
import it.unibas.icar.freesbee.persistenza.IDAOServizio;
import it.unibas.icar.freesbee.persistenza.IDAOSoggetto;
import it.unibas.icar.freesbee.vista.VistaGestionePorteDelegate;
import java.util.Collections;
import java.util.List;
import javax.faces.event.ActionEvent;
import org.ajax4jsf.component.html.HtmlAjaxSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ControlloGestionePorteDelegate {

    private Log logger = LogFactory.getLog(this.getClass());
    private VistaGestionePorteDelegate vista;
    private IDAOPortaDelegata daoPortaDelegata;
    private IDAOConfigurazione daoConfigurazione;
    private IDAOSoggetto daoSoggetto;
    private IDAOServizio daoServizio;
    private IDAOAzione daoAzione;
    private String messaggio;
    private String errore;
    private String conferma;
    private Utente utente;

    public String gestionePorteDelegate() {

        this.gestioneInserisciPortaDelegata(new PortaDelegata());
        this.caricaTuttePorteDelegate();
//        this.vista.getNuovoPortaDelegata().setSoggettoErogatore(new Soggetto());
        this.vista.getNuovoPortaDelegata().setSoggettoFruitore(new Soggetto());
        this.vista.getNuovoPortaDelegata().setAzione(new Azione());
        this.vista.getNuovoPortaDelegata().setServizio(new Servizio());
        try {
            this.vista.setNicaPresente(daoConfigurazione.find(utente).isInviaANICA());
        } catch (DAOException ex) {
            logger.error("Impossibile leggere la configurazione " + ex);
        }
        return "gestionePorteDelegate";
    }

    public String gestioneInserisciPortaDelegata(PortaDelegata nuovaPortaDelegata) {
        this.vista.setInserisci(true);
        this.vista.setModifica(false);
        this.vista.setElimina(false);
        this.vista.setNuovoPortaDelegata(nuovaPortaDelegata);
        return null;
    }

    public String gestioneModificaPortaDelegata(PortaDelegata portaDelegataDaModificare) {
        this.vista.setInserisci(false);
        this.vista.setModifica(true);
        this.vista.setElimina(false);
        this.vista.setNuovoPortaDelegata(portaDelegataDaModificare);
        return null;
    }

    public String gestioneEliminaPortaDelegata(PortaDelegata portaDelegataDaEliminare) {
        this.vista.setInserisci(false);
        this.vista.setModifica(false);
        this.vista.setElimina(true);
        this.vista.setNuovoPortaDelegata(portaDelegataDaEliminare);
        return null;
    }

    public void caricaTuttePorteDelegate() {
        try {
            List<PortaDelegata> lista = daoPortaDelegata.findAll(this.utente);
            List<Servizio> listaServizi = daoServizio.findAll(this.utente);
//            List<Azione> listaAzioni = daoAzione.findAll(this.utente);
            Collections.sort(lista);
            Collections.sort(listaServizi);
//            Collections.sort(listaAzioni);
            if (lista.size() == 0) {
                this.setMessaggio("Non ho trovato alcuna porta delegata.");
            }
            this.vista.setListaPorteDelegate(lista);
            this.vista.setListaServizi(listaServizi);
//            this.vista.setListaAzioni(listaAzioni);
        } catch (DAOException ex) {
            logger.error("Impossibile leggere l'elenco delle porte delegate dalla base di dati. " + ex);
            this.setErrore("Impossibile leggere l'elenco delle porte delegate dalla base di dati. Controllare che freESBee sia avviato");
        }
    }

    public String inserisci() {
        try {
            PortaDelegata nuovaPortaDelegata = this.vista.getNuovoPortaDelegata();
            if (nuovaPortaDelegata.getNome().indexOf(" ") != -1) {
                this.setErrore("Impossibile aggiungere la porta delegata. Eliminare gli spazi nel nome");
                return null;
            }

            long idFruitore = nuovaPortaDelegata.getSoggettoFruitore().getId();
            if (idFruitore <= 0) {
                nuovaPortaDelegata.setSoggettoFruitore(null);
            } else {
                logger.info("Riempio i riferimenti dell'soggetto fruitore con id " + idFruitore);
                Soggetto fruitore = daoSoggetto.findById(this.utente, idFruitore, false);
                if (fruitore == null) {
                    this.setErrore("Impossibile aggiungere la porta delegata. Non esiste il fruitore specificato");
                    return null;
                }
                nuovaPortaDelegata.setSoggettoFruitore(fruitore);
            }

            long idServizio = nuovaPortaDelegata.getServizio().getId();
            if (idServizio <= 0) {
                nuovaPortaDelegata.setServizio(null);
            } else {
                Servizio servizio = daoServizio.findById(this.utente, idServizio, false);
                if (servizio == null) {
                    this.setErrore("Impossibile aggiungere la porta delegata. Non esiste il servizio specificato");
                    return null;
                }
                nuovaPortaDelegata.setServizio(servizio);
            }

            long idAzione = nuovaPortaDelegata.getAzione().getId();
            if (idAzione <= 0) {
                nuovaPortaDelegata.setAzione(null);
            } else {
                Azione azione = daoAzione.findById(this.utente, idAzione, false);
                if (azione == null) {
                    this.setErrore("Impossibile aggiungere la porta delegata. Non esiste l'azione specificata");
                    return null;
                }
                nuovaPortaDelegata.setAzione(azione);
            }

            daoPortaDelegata.makePersistent(this.utente, nuovaPortaDelegata);

            this.setMessaggio("Porta Delegata inserita correttamente.");
            this.gestionePorteDelegate();
        } catch (DAOException ex) {
            logger.error("Impossibile inserire la porta delegata. " + ex);
            String erroreFormattato = ex.getMessage().substring(ex.getMessage().indexOf(":") + 1);
            this.setErrore(erroreFormattato);
        }
        return null;
    }

    public String modifica() {
        try {
            PortaDelegata portaDelegataDaModificare = this.vista.getNuovoPortaDelegata();
            if (portaDelegataDaModificare.getSoggettoFruitore() != null) {
                long idFruitore = portaDelegataDaModificare.getSoggettoFruitore().getId();
                if (idFruitore <= 0) {
                    portaDelegataDaModificare.setSoggettoFruitore(null);
                } else {
                    logger.info("Riempio i riferimenti dell'soggetto fruitore con id " + idFruitore);
                    Soggetto fruitore = daoSoggetto.findById(this.utente, idFruitore, false);
                    if (fruitore == null) {
                        this.setErrore("Impossibile aggiungere la porta delegata. Non esiste il fruitore specificato");
                        return null;
                    }
                    portaDelegataDaModificare.setSoggettoFruitore(fruitore);
                }
            }
//            if(portaDelegataDaModificare.isFruitoreQueryString()){
//                portaDelegataDaModificare.setIdSoggettoFruitore(0);
//                portaDelegataDaModificare.setSoggettoFruitore(null);
//                portaDelegataDaModificare.setStringaFruitore(null);
//                portaDelegataDaModificare.setStringaTipoFruitore(null);
//                portaDelegataDaModificare.setStringaErogatore(null);
//                portaDelegataDaModificare.setStringaTipoErogatore(null);
//                portaDelegataDaModificare.setStringaTipoServizio(null);
//                portaDelegataDaModificare.setStringaServizio(null);
//                portaDelegataDaModificare.setIdServizio(0);
//                portaDelegataDaModificare.setServizio(null);
//            }
            if (portaDelegataDaModificare.getServizio() != null) {
                long idServizio = portaDelegataDaModificare.getServizio().getId();
                if (idServizio <= 0) {
                    portaDelegataDaModificare.setServizio(null);
                } else {
                    Servizio servizio = daoServizio.findById(this.utente, idServizio, false);
                    if (servizio == null) {
                        this.setErrore("Impossibile aggiungere la porta delegata. Non esiste il servizio specificato");
                        return null;
                    }
                    portaDelegataDaModificare.setServizio(servizio);
                }
            }


            long idAzione = portaDelegataDaModificare.getAzione().getId();
            if (idAzione <= 0) {
                portaDelegataDaModificare.setAzione(null);
            } else {
                Azione azione = daoAzione.findById(this.utente, idAzione, false);
                if (azione == null) {
                    this.setErrore("Impossibile aggiungere la porta delegata. Non esiste l'azione specificata");
                    return null;
                }

                portaDelegataDaModificare.setAzione(azione);
            }


            daoPortaDelegata.makePersistent(this.utente, portaDelegataDaModificare);
//            gestionePorteDelegate();
            vista.setNuovoPortaDelegata(new PortaDelegata());
//            this.vista.getNuovoPortaDelegata().setSoggettoErogatore(new Soggetto());
            this.vista.getNuovoPortaDelegata().setSoggettoFruitore(new Soggetto());
            this.setMessaggio("Porta Delegata modificata correttamente");
            this.gestioneInserisciPortaDelegata(new PortaDelegata());
        } catch (DAOException ex) {
            logger.error("Impossibile modificare la Porta Delegata. " + ex);
            String erroreFormattato = ex.getMessage().substring(ex.getMessage().indexOf(":") + 1);
            this.setErrore(erroreFormattato);
        }
        return null;
    }

    public String elimina() {
        try {
            PortaDelegata portaDelegataDaEliminare = this.vista.getNuovoPortaDelegata();
            daoPortaDelegata.makeTransient(this.utente, portaDelegataDaEliminare);
            this.setMessaggio("Porta Delegata eliminata correttamente.");
            this.caricaTuttePorteDelegate();
            gestioneInserisciPortaDelegata(new PortaDelegata());
            vista.setNuovoPortaDelegata(new PortaDelegata());
//            this.vista.getNuovoPortaDelegata().setSoggettoErogatore(new Soggetto());
            this.vista.getNuovoPortaDelegata().setSoggettoFruitore(new Soggetto());
            this.vista.getNuovoPortaDelegata().setServizio(new Servizio());
            this.vista.getNuovoPortaDelegata().setAzione(new Azione());
        } catch (DAOException ex) {
            logger.error("Impossibile eliminare la Porta Delegata. " + ex);
            String erroreFormattato = ex.getMessage().substring(ex.getMessage().indexOf(":") + 1);
            this.setErrore(erroreFormattato);
        }

        return null;
    }

    public String schermoModificaDaTabella() {
        PortaDelegata portaDelegataDaTabella = (PortaDelegata) this.getVista().getTabellaPorteDelegate().getRowData();
        Azione azionePortaApplicativa = portaDelegataDaTabella.getAzione();
        if (azionePortaApplicativa == null) {
            portaDelegataDaTabella.setAzione(new Azione());
        }
        if (portaDelegataDaTabella.getSoggettoFruitore() == null) {
            portaDelegataDaTabella.setSoggettoFruitore(new Soggetto());
        }
        this.vista.setNuovoPortaDelegata(portaDelegataDaTabella);
        gestioneModificaPortaDelegata(portaDelegataDaTabella);
        
        Servizio servizio = portaDelegataDaTabella.getServizio();
        if(servizio==null){
            portaDelegataDaTabella.setServizio(new Servizio());
        }else{
            this.vista.setListaSoggetti(servizio.getFruitori());
        }
        
//        try {
//            Servizio servizio = daoServizio.findById(this.utente, portaDelegataDaTabella.getIdServizio(), false);
//            this.vista.setListaSoggetti(servizio.getFruitori());
//        } catch (DAOException ex) {}
        return null;
    }

    public String schermoEliminaDaTabella() {
        PortaDelegata portaDelegataDaEliminare = (PortaDelegata) this.vista.getTabellaPorteDelegate().getRowData();
        this.vista.setNuovoPortaDelegata(portaDelegataDaEliminare);
        this.setConferma("Sei sicuro di voler eliminare la Porta Delegata ?");
        gestioneEliminaPortaDelegata(portaDelegataDaEliminare);
        return null;
    }

    public void servizioCambiato(ActionEvent e) {
        try {
            long idNuovoServizio = this.getVista().getNuovoPortaDelegata().getServizio().getId();
            Servizio nuovoServizio = daoServizio.findById(this.utente, idNuovoServizio, false);
            if (nuovoServizio == null) {
                return;
            }

            this.vista.setListaSoggetti(nuovoServizio.getFruitori());
            List<Azione> listaAzioni = daoAzione.findByAccordo(this.utente, nuovoServizio.getAccordoServizio());
            this.vista.setListaAzioni(listaAzioni);
        } catch (DAOException ex) {
            logger.error("Impossibile leggere il nuovo servizio " + ex);
        }

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

    public VistaGestionePorteDelegate getVista() {
        return vista;
    }

    public void setVista(VistaGestionePorteDelegate vista) {
        this.vista = vista;
    }

    public IDAOPortaDelegata getDaoPortaDelegata() {
        return daoPortaDelegata;
    }

    public void setDaoPortaDelegata(IDAOPortaDelegata daoPortaDelegata) {
        this.daoPortaDelegata = daoPortaDelegata;
    }

    public IDAOSoggetto getDaoSoggetto() {
        return daoSoggetto;
    }

    public void setDaoSoggetto(IDAOSoggetto daoSoggetto) {
        this.daoSoggetto = daoSoggetto;
    }

    public String getConferma() {
        return conferma;
    }

    public void setConferma(String conferma) {
        this.conferma = conferma;
    }

    public boolean isVisualizzaMessaggio() {
        return (this.messaggio != null && !this.messaggio.equalsIgnoreCase(""));
    }

    public boolean isVisualizzaErrore() {
        return (this.errore != null && !this.errore.equalsIgnoreCase(""));
    }

    public boolean isVisualizzaConferma() {
        return (this.conferma != null && !this.conferma.equalsIgnoreCase(""));
    }

    public IDAOServizio getDaoServizio() {
        return daoServizio;
    }

    public void setDaoServizio(IDAOServizio daoServizio) {
        this.daoServizio = daoServizio;
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
