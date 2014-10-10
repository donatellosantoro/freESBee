package it.unibas.icar.freesbee.controllo;

import it.unibas.icar.freesbee.modello.Azione;
import it.unibas.icar.freesbee.modello.PortaApplicativa;
import it.unibas.icar.freesbee.modello.Servizio;
import it.unibas.icar.freesbee.modello.ServizioApplicativo;
import it.unibas.icar.freesbee.modello.Utente;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOAzione;
import it.unibas.icar.freesbee.persistenza.IDAOConfigurazione;
import it.unibas.icar.freesbee.persistenza.IDAOPortaApplicativa;
import it.unibas.icar.freesbee.persistenza.IDAOServizio;
import it.unibas.icar.freesbee.persistenza.IDAOServizioApplicativo;
import it.unibas.icar.freesbee.vista.VistaGestionePorteApplicative;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.faces.event.ActionEvent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ControlloGestionePorteApplicative {

    private Log logger = LogFactory.getLog(this.getClass());
    private VistaGestionePorteApplicative vista;
    private IDAOPortaApplicativa daoPortaApplicativa;
    private IDAOServizio daoServizio;
    private IDAOServizioApplicativo daoServizioApplicativo;
    private IDAOConfigurazione daoConfigurazione;
    private IDAOAzione daoAzione;
    private String messaggio;
    private String errore;
    private String conferma;
    private Utente utente;

    public String gestionePorteApplicative() {
        this.gestioneInserisciPortaApplicativa(new PortaApplicativa());
        this.caricaTuttePorteApplicative();
        this.vista.getNuovoPortaApplicativa().setServizio(new Servizio());
        this.vista.getNuovoPortaApplicativa().setAzione(new Azione());
        this.vista.getNuovoPortaApplicativa().setServizioApplicativo(new ServizioApplicativo());
        try {
            this.vista.setNicaPresente(daoConfigurazione.find(utente).isInviaANICA());
        } catch (DAOException ex) {
            logger.error("Impossibile leggere la configurazione " + ex);
        }
        return "gestionePorteApplicative";
    }

    public String gestioneInserisciPortaApplicativa(PortaApplicativa nuovaPortaApplicativa) {
        this.vista.setInserisci(true);
        this.vista.setModifica(false);
        this.vista.setElimina(false);
        this.vista.setNuovoPortaApplicativa(nuovaPortaApplicativa);
        return null;
    }

    public String gestioneModificaPortaApplicativa(PortaApplicativa portaApplicativaDaModificare) {
        this.vista.setInserisci(false);
        this.vista.setModifica(true);
        this.vista.setElimina(false);
        this.vista.setNuovoPortaApplicativa(portaApplicativaDaModificare);
        return null;
    }

    public String gestioneEliminaPortaApplicativa(PortaApplicativa portaApplicativaDaEliminare) {
        this.vista.setInserisci(false);
        this.vista.setModifica(false);
        this.vista.setElimina(true);
        this.vista.setNuovoPortaApplicativa(portaApplicativaDaEliminare);
        return null;
    }

    public void caricaTuttePorteApplicative() {
        try {
            List<PortaApplicativa> listaPorteApplicative = daoPortaApplicativa.findAll(this.utente);
            List<Servizio> listaServizi = daoServizio.findAll(this.utente);
            List<ServizioApplicativo> listaServiziApplicativi = getDaoServizioApplicativo().findAll(this.utente);
//            List<Azione> listaAzioni = daoAzione.findAll(this.utente);
            List<Azione> listaAzioni = new ArrayList<Azione>();
            Collections.sort(listaPorteApplicative);
            Collections.sort(listaServizi);
            Collections.sort(listaServiziApplicativi);
            Collections.sort(listaAzioni);
            if (listaPorteApplicative.size() == 0) {
                this.setMessaggio("Non ho trovato alcuna porta applicativa.");
            }
            this.vista.setListaPorteApplicative(listaPorteApplicative);
            this.vista.setListaServizi(listaServizi);
            this.vista.setListaServiziApplicativi(listaServiziApplicativi);
            this.vista.setListaAzioni(listaAzioni);
        } catch (DAOException ex) {
            logger.error("Impossibile leggere l'elenco delle porte applicative dalla base di dati. " + ex);
            this.setErrore("Impossibile leggere l'elenco delle porte applicative dalla base di dati. Controllare che freESBee sia avviato");
        }
    }

    public String inserisci() {
        try {
            PortaApplicativa nuovaPortaApplicativa = this.vista.getNuovoPortaApplicativa();
            PortaApplicativa portaApplicativaEsistente = daoPortaApplicativa.findByNome(this.utente, nuovaPortaApplicativa.getNome());
            if (portaApplicativaEsistente != null) {
                this.setErrore("Impossibile aggiungere la porta applicativa. Esiste gia' una porta applicativa con il nome specificato");
                return null;
            }

            long idAzione = 0;
            if (nuovaPortaApplicativa.getAzione() != null) {
                idAzione = nuovaPortaApplicativa.getAzione().getId();
            }
            if (idAzione <= 0) {
                nuovaPortaApplicativa.setAzione(null);
            } else {
                Azione azione = daoAzione.findById(this.utente, idAzione, false);
                if (azione == null) {
                    this.setErrore("Impossibile aggiungere la porta applicativa. Non esiste l'azione specificata");
                    return null;
                }
                nuovaPortaApplicativa.setAzione(azione);
            }

            long idServizio = nuovaPortaApplicativa.getServizio().getId();
            if (idServizio <= 0) {
                nuovaPortaApplicativa.setServizio(null);
                PortaApplicativa portaApplicativaConServizio = daoPortaApplicativa.findByServizio(this.utente,
                        nuovaPortaApplicativa.getStringaTipoServizio(),
                        nuovaPortaApplicativa.getStringaServizio(),
                        nuovaPortaApplicativa.getStringaTipoErogatore(),
                        nuovaPortaApplicativa.getStringaErogatore(),
                        nuovaPortaApplicativa.getStringaAzione());
                if (portaApplicativaConServizio != null) {
                    this.setErrore("Impossibile aggiungere la porta applicativa. Il servizio specificato e' gia' associato ad un'altra porta applicativa");
                    return null;
                }
            } else {
                Servizio servizio = daoServizio.findById(this.utente, idServizio, false);
                if (servizio == null) {
                    this.setErrore("Impossibile aggiungere la porta applicativa. Non esiste il servizio specificato");
                    return null;
                }
                PortaApplicativa portaApplicativaConServizio = daoPortaApplicativa.findByServizio(this.utente, servizio, nuovaPortaApplicativa.getAzione());
                if (portaApplicativaConServizio != null) {
                    this.setErrore("Impossibile aggiungere la porta applicativa. Il servizio specificato e' gia' associato ad un'altra porta applicativa");
                    return null;
                }
                nuovaPortaApplicativa.setServizio(servizio);
            }

            long idServizioApplicativo = nuovaPortaApplicativa.getServizioApplicativo().getId();
            if (idServizioApplicativo <= 0) {
                nuovaPortaApplicativa.setServizioApplicativo(null);
            } else {
                ServizioApplicativo servizioApplicativo = daoServizioApplicativo.findById(this.utente, idServizioApplicativo, false);
                if (servizioApplicativo == null) {
                    this.setErrore("Impossibile aggiungere la porta applicativa. Non esiste il servizio applicativo specificato");
                    return null;
                }
                nuovaPortaApplicativa.setServizioApplicativo(servizioApplicativo);
            }

            daoPortaApplicativa.makePersistent(this.utente, nuovaPortaApplicativa);

            this.setMessaggio("Porta Applicativa inserita correttamente.");
            this.gestionePorteApplicative();
        } catch (DAOException ex) {
            logger.error("Impossibile inserire la porta applicativa. " + ex);
            String erroreFormattato = ex.getMessage().substring(ex.getMessage().indexOf(":") + 1);
            this.setErrore(erroreFormattato);
        }
        return null;
    }

    public String modifica() {
        try {
            PortaApplicativa portaApplicativaDaModificare = this.vista.getNuovoPortaApplicativa();

            long idServizio = 0;
            if (portaApplicativaDaModificare.getServizio() != null) {
                idServizio = portaApplicativaDaModificare.getServizio().getId();
            }
            if (idServizio <= 0) {
                portaApplicativaDaModificare.setServizio(null);
                PortaApplicativa portaApplicativaConServizio = daoPortaApplicativa.findByServizio(this.utente,
                        portaApplicativaDaModificare.getStringaTipoServizio(),
                        portaApplicativaDaModificare.getStringaServizio(),
                        portaApplicativaDaModificare.getStringaTipoErogatore(),
                        portaApplicativaDaModificare.getStringaErogatore(),
                        portaApplicativaDaModificare.getStringaAzione());
                if (portaApplicativaConServizio != null &&
                        (portaApplicativaConServizio.getId().compareTo(portaApplicativaDaModificare.getId())) != 0) {
                    this.setErrore("Impossibile aggiungere la porta applicativa. Il servizio specificato e' gia' associato ad un'altra porta applicativa");
                    return null;
                }
            } else {
                Servizio servizio = daoServizio.findById(this.utente, idServizio, false);
                if (servizio == null) {
                    this.setErrore("Impossibile aggiungere la porta applicativa. Non esiste il servizio specificato");
                    return null;
                }
                long idAzione = portaApplicativaDaModificare.getAzione().getId();
                Azione azione;
                if (idAzione <= 0) {
                    azione = null;
                } else {
                    azione = daoAzione.findById(this.utente, idAzione, false);
                    if (azione == null) {
                        this.setErrore("Impossibile aggiungere la porta applicativa. Non esiste l'azione specificata");
                        return null;
                    }
                }
                    portaApplicativaDaModificare.setAzione(azione);
                PortaApplicativa portaApplicativaConServizio = daoPortaApplicativa.findByServizio(this.utente, servizio, azione);
                if (portaApplicativaConServizio != null &&
                        (portaApplicativaConServizio.getId().compareTo(portaApplicativaDaModificare.getId())) != 0) {
                    this.setErrore("Impossibile aggiungere la porta applicativa. Il servizio specificato e' gia' associato ad un'altra porta applicativa");
                    return null;
                }
                portaApplicativaDaModificare.setServizio(servizio);
            }

            daoPortaApplicativa.makePersistent(this.utente, portaApplicativaDaModificare);
            this.setMessaggio("Porta Applicativa modificata correttamente");
            gestionePorteApplicative();
        } catch (DAOException ex) {
            logger.error("Impossibile modificare la Porta Applicativa. " + ex);
            String erroreFormattato = ex.getMessage().substring(ex.getMessage().indexOf(":") + 1);
            this.setErrore(erroreFormattato);
        }
        return null;
    }

    public String elimina() {
        try {
            PortaApplicativa portaApplicativaDaEliminare = this.vista.getNuovoPortaApplicativa();
            daoPortaApplicativa.makeTransient(this.utente, portaApplicativaDaEliminare);
            this.setMessaggio("Porta Applicativa eliminata correttamente.");
            gestionePorteApplicative();
        } catch (DAOException ex) {
            logger.error("Impossibile eliminare la Porta Applicativa. " + ex);
            String erroreFormattato = ex.getMessage().substring(ex.getMessage().indexOf(":") + 1);
            this.setErrore(erroreFormattato);
        }
        return null;
    }

    public String schermoModificaDaTabella() {
        PortaApplicativa portaApplicativaDaTabella = (PortaApplicativa) this.getVista().getTabellaPorteApplicative().getRowData();
        Azione azionePortaApplicativa = portaApplicativaDaTabella.getAzione();
        if (azionePortaApplicativa == null) {
            portaApplicativaDaTabella.setAzione(new Azione());
        }
        if (portaApplicativaDaTabella.getServizio() != null) {
            try {
                List<Azione> listaAzioni = daoAzione.findByAccordo(this.utente, portaApplicativaDaTabella.getServizio().getAccordoServizio());
                this.vista.setListaAzioni(listaAzioni);
            } catch (DAOException ex) {
                logger.error("Impossibile leggere il nuovo servizio " + ex);
            }
        }
        this.vista.setNuovoPortaApplicativa(portaApplicativaDaTabella);
        gestioneModificaPortaApplicativa(portaApplicativaDaTabella);

        return null;
    }

    public String schermoEliminaDaTabella() {
        PortaApplicativa portaApplicativaDaEliminare = (PortaApplicativa) this.vista.getTabellaPorteApplicative().getRowData();
        this.vista.setNuovoPortaApplicativa(portaApplicativaDaEliminare);
        this.setConferma("Sei sicuro di voler eliminare la Porta Applicativa?");
        gestioneEliminaPortaApplicativa(portaApplicativaDaEliminare);
        return null;
    }

    public void servizioCambiato(ActionEvent e) {
        try {
            long idNuovoServizio = this.getVista().getNuovoPortaApplicativa().getServizio().getId();
            Servizio nuovoServizio = daoServizio.findById(this.utente, idNuovoServizio, false);
            if (nuovoServizio == null) {
                return;
            }
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

    public VistaGestionePorteApplicative getVista() {
        return vista;
    }

    public void setVista(VistaGestionePorteApplicative vista) {
        this.vista = vista;
    }

    public IDAOPortaApplicativa getDaoPortaApplicativa() {
        return daoPortaApplicativa;
    }

    public void setDaoPortaApplicativa(IDAOPortaApplicativa daoPortaApplicativa) {
        this.daoPortaApplicativa = daoPortaApplicativa;
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

    public IDAOServizioApplicativo getDaoServizioApplicativo() {
        return daoServizioApplicativo;
    }

    public void setDaoServizioApplicativo(IDAOServizioApplicativo daoServizioApplicativo) {
        this.daoServizioApplicativo = daoServizioApplicativo;
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
