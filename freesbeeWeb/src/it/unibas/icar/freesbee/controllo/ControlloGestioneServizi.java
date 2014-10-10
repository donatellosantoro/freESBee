package it.unibas.icar.freesbee.controllo;

import it.unibas.icar.freesbee.modello.AccordoServizio;
import it.unibas.icar.freesbee.modello.Azione;
import it.unibas.icar.freesbee.modello.Servizio;
import it.unibas.icar.freesbee.modello.Soggetto;
import it.unibas.icar.freesbee.modello.Utente;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOAccordoServizio;
import it.unibas.icar.freesbee.persistenza.IDAOConfigurazione;
import it.unibas.icar.freesbee.persistenza.IDAOServizio;
import it.unibas.icar.freesbee.persistenza.IDAOSoggetto;
import it.unibas.icar.freesbee.vista.VistaGestioneServizi;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.faces.event.ActionEvent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.richfaces.component.UIListShuttle;

public class ControlloGestioneServizi {

    private Log logger = LogFactory.getLog(this.getClass());
    private VistaGestioneServizi vista;
    private String messaggioTest;
    private String errore;
    private String conferma;
    private IDAOSoggetto daoSoggetto;
    private IDAOAccordoServizio daoAccordoServizio;
    private IDAOConfigurazione daoConfigurazione;
    private IDAOServizio daoServizio;
    private Utente utente;

    public void caricaTuttiServizi() {
        if (this.vista.isNicaPresente()) {
            return;
        }
        try {
            List<Servizio> lista = daoServizio.findAllNoLazy(this.utente);
            List<Soggetto> listaSoggetti = daoSoggetto.findAll(this.utente);
            Collections.sort(lista);
            Collections.sort(listaSoggetti);
            if (lista.size() == 0) {
                this.setMessaggio("Non ho trovato alcun servizio.");
            }
            this.getVista().setListaServizi(lista);
            this.getVista().setTuttiSoggetti(listaSoggetti);
            this.getVista().setSource(this.converti(listaSoggetti));
            this.getVista().setTarget(new String[0]);
            this.getVista().setAzioniDisponibili(new String[0]);
            this.getVista().setAzioniSelezionate(new String[0]);
        } catch (DAOException ex) {
            logger.error("Impossibile leggere l'elenco dei servizi dalla base di dati. " + ex);
            this.setErrore("Impossibile leggere l'elenco dei servizi dalla base di dati. Controllare che freESBee sia avviato");
        }
    }

    private List caricaSoggettiDisponibili(List<Soggetto> listaTuttiSoggetti, List<Soggetto> listaSoggettiFruitori) {
        List<Soggetto> listaSoggettiDisponibili = new ArrayList();
        if (listaTuttiSoggetti.size() == listaSoggettiFruitori.size()) {
            return listaSoggettiDisponibili;
        } else if (listaSoggettiFruitori.size() == 0) {
            return listaTuttiSoggetti;
        } else {
            for (Iterator<Soggetto> iteratorTuttiSoggetti = listaTuttiSoggetti.iterator(); iteratorTuttiSoggetti.hasNext();) {
                Soggetto soggetto = (Soggetto) iteratorTuttiSoggetti.next();

                boolean flag = false;

                for (Iterator<Soggetto> it = listaSoggettiFruitori.iterator(); it.hasNext();) {
                    Soggetto soggettoFruitore = (Soggetto) it.next();

                    if ((soggettoFruitore.getId() == soggetto.getId())) {
                        flag = true;
                    }
                }
                if (!flag) {
                    listaSoggettiDisponibili.add(soggetto);
                }
            }
        }
        return listaSoggettiDisponibili;
    }

    private String[] converti(List<Soggetto> listaSoggetti) {
        String[] source = new String[listaSoggetti.size()];
        for (int i = 0; i < listaSoggetti.size(); i++) {
            source[i] = listaSoggetti.get(i).getNomeLista();
        }
        return source;
    }

    private List<Soggetto> convertiTarget(String[] target) throws DAOException {
        List<Soggetto> tuttiSoggetti = daoSoggetto.findAll(this.utente);
        List<Soggetto> soggettiTarget = new ArrayList<Soggetto>();
        for (String soggettoString : target) {
            soggettiTarget.add(findSoggetto(tuttiSoggetti, getSoggettoId(soggettoString)));
        }
        return soggettiTarget;
    }

    private Soggetto findSoggetto(List<Soggetto> listaSoggetti, long idTarget) {
        for (Soggetto soggetto : listaSoggetti) {
            if (soggetto.getId() == idTarget) {
                return soggetto;
            }
        }
        return null;
    }

    private long getSoggettoId(String s) {
        int index = s.lastIndexOf("[");
        String stringLong = s.substring(index + 1, s.length() - 1);
        return Long.parseLong(stringLong);
    }

    public String caricaGestioneServizi() {
        Servizio nuovoServizio = new Servizio();
        nuovoServizio.setErogatore(new Soggetto());
        nuovoServizio.setAccordoServizio(new AccordoServizio());
        nuovoServizio.setAllAzioni(true);
        nuovoServizio.setAzioni(null);
        this.vista.setNuovoServizio(nuovoServizio);
        gestioneInserisciServizi(nuovoServizio);
        try {
            this.vista.setNicaPresente(daoConfigurazione.find(utente).isInviaANICA());
        } catch (DAOException ex) {
            logger.error("Impossibile leggere la configurazione " + ex);
        }
        caricaTuttiServizi();
        return "gestioneServizi";
    }

    public String gestioneInserisciServizi(Servizio nuovoServizio) {
        this.vista.setInserisci(true);
        this.vista.setModifica(false);
        this.vista.setElimina(false);
        this.vista.setNuovoServizio(nuovoServizio);
        return null;
    }

    public String gestioneModificaServizi(Servizio servizioModificare) {
        this.vista.setInserisci(false);
        this.vista.setModifica(true);
        this.vista.setElimina(false);
        this.vista.setNuovoServizio(servizioModificare);
        return null;
    }

    public String gestioneEliminaServizi(Servizio servizioEliminare) {
        this.vista.setInserisci(false);
        this.vista.setModifica(false);
        this.vista.setElimina(true);
        this.vista.setNuovoServizio(servizioEliminare);
        return null;
    }

    public String inserisci() {
        try {
            Servizio servizioAggiungere = this.vista.getNuovoServizio();

            List<Soggetto> soggettiFruitori = convertiTarget(this.vista.getTarget());
            servizioAggiungere.setFruitori(soggettiFruitori);
            if (!servizioAggiungere.isAllAzioni()) {
                servizioAggiungere.setAzioniFromArray(this.vista.getAzioniSelezionate());
            }
            daoServizio.makePersistent(this.utente, servizioAggiungere);
            this.setMessaggio("Servizio inserito correttamente.");
            Servizio servizio = new Servizio();
            servizio.setErogatore(new Soggetto());
            servizio.setAccordoServizio(new AccordoServizio());

            gestioneInserisciServizi(servizio);
            caricaTuttiServizi();
        } catch (DAOException ex) {
            logger.error("Impossibile inserire il nuovo servizio. " + ex);
            String erroreFormattato = ex.getMessage().substring(ex.getMessage().indexOf(":") + 1);
            this.setErrore(erroreFormattato);
        }

        return "gestioneServizi";
    }

    public String modifica() {
        try {
            Servizio servizioModificare = this.vista.getNuovoServizio();

            List<Soggetto> soggettiFruitori = convertiTarget(this.vista.getTarget());
            servizioModificare.setFruitori(soggettiFruitori);
            if (!servizioModificare.isAllAzioni()) {
                servizioModificare.setAzioniFromArray(this.vista.getAzioniSelezionate());
                servizioModificare.setAllAzioni(false);
            }
            daoServizio.makePersistent(this.utente, servizioModificare);
            this.setMessaggio("Servizio modificato correttamente.");
            Servizio servizio = new Servizio();
            servizio.setErogatore(new Soggetto());
            servizio.setAccordoServizio(new AccordoServizio());
            this.vista.setNuovoServizio(servizio);
            gestioneInserisciServizi(servizio);
            caricaTuttiServizi();
        } catch (DAOException ex) {
            logger.error("Impossibile modificare il servizio. " + ex);
            String erroreFormattato = ex.getMessage().substring(ex.getMessage().indexOf(":") + 1);
            this.setErrore(erroreFormattato);
        }
        return "gestioneServizi";
    }

    public String elimina() {
        try {
            Servizio servizioEliminare = this.vista.getNuovoServizio();
            daoServizio.makeTransient(this.utente, servizioEliminare);
            this.setMessaggio("Servizio eliminato correttamente.");
            Servizio servizio = new Servizio();
            servizio.setErogatore(new Soggetto());
            servizio.setAccordoServizio(new AccordoServizio());
            this.vista.setNuovoServizio(servizio);
            gestioneInserisciServizi(servizio);
            caricaTuttiServizi();
        } catch (DAOException ex) {
            logger.error("Impossibile eliminare il servizio. " + ex);
            String erroreFormattato = ex.getMessage().substring(ex.getMessage().indexOf(":") + 1);
            this.setErrore(erroreFormattato);
        }
        return null;
    }

    public String schermoModificaDaDettagli() {
        return "gestioneAccordiServizi";
    }

    public String schermoModificaDaTabella() {
        try {
            Servizio servizioSelezionato = (Servizio) this.vista.getTabellaServizi().getRowData();
            Servizio servizio = daoServizio.findById(this.utente, servizioSelezionato.getId(), true);
            List<Soggetto> listaSoggettiFruitori = servizio.getFruitori();
            this.vista.setTarget(converti(listaSoggettiFruitori));
            if (!servizio.isAllAzioni()) {
                this.vista.setAzioniSelezionate(servizio.getListaAzioni().toArray(new String[0]));
                long idAccordoServizio = servizio.getIdAccordoServizio();
                AccordoServizio accordoServizio = daoAccordoServizio.findById(utente, idAccordoServizio, false);
                caricaAzioniDisponibili(accordoServizio);
            }
            List<Soggetto> listaTuttiSoggetti = daoSoggetto.findAll(this.utente);
            List<Soggetto> listaSoggettiDisponibili = caricaSoggettiDisponibili(listaTuttiSoggetti, listaSoggettiFruitori);
//            this.vista.setSource(new String[0]);
            this.vista.setSource(converti(listaSoggettiDisponibili));
            gestioneModificaServizi(servizioSelezionato);
            this.vista.setPannelloVisibile(true);
        } catch (DAOException ex) {
            logger.error("Impossibile modificare il servizio. " + ex);
            String erroreFormattato = ex.getMessage().substring(ex.getMessage().indexOf(":") + 1);
            this.setErrore(erroreFormattato);
        }
        return "gestioneServizi";
    }

    public String schermoEliminaDaDettagli() {
        return null;
    }

    public String schermoEliminaDaTabella() {
        Servizio servizioEliminare = (Servizio) this.vista.getTabellaServizi().getRowData();
        gestioneEliminaServizi(servizioEliminare);
        this.setConferma("Sei sicuro di voler eliminare il servizio ?");
        return null;
    }

    private Servizio getServizioSelezionato() {
        Servizio servizioSelezionato = null;
        try {
            long idServizio = ((Long) this.vista.getServizioSelezionato().getValue()).longValue();
            servizioSelezionato = daoServizio.findById(this.utente, idServizio, false);
        } catch (Exception ex) {
            logger.error("Impossibile leggere il servizio selezionato. " + ex);
            return null;
        }
        return servizioSelezionato;
    }

    public void aggiornaAzioniDisponibili(ActionEvent e) {
        try {
            boolean isSelected = this.vista.getNuovoServizio().isAllAzioni();
            if (isSelected) return;
            AccordoServizio accordoServizio = null;
            long idAccordo = this.vista.getNuovoServizio().getAccordoServizio().getId();
            if (idAccordo <= 0) return;
            accordoServizio = this.daoAccordoServizio.findById(utente, idAccordo, false);
            if (accordoServizio != null) {
                List<Azione> azioni = accordoServizio.getAzioni();
                List<String> azioniDisponibili = new ArrayList<String>();
                for (Azione azione : azioni) {
                    azioniDisponibili.add(azione.getNome());
                }
                this.vista.setAzioniDisponibili(azioniDisponibili.toArray(new String[0]));
            }
        } catch (Exception ex) {
            logger.error("Impossibile leggere l'accordo di servizio selezionato. " + ex);
        }
    }

    public void caricaAzioniDisponibili(AccordoServizio accordoServizio) {
        String[] azioniSelezionate = this.vista.getAzioniSelezionate();
        if (accordoServizio != null) {
            List<Azione> azioni = accordoServizio.getAzioni();
            List<String> azioniDisponibili = new ArrayList<String>();
            for (Azione azione : azioni) {
                if (!azionePresente(azioniSelezionate, azione)) azioniDisponibili.add(azione.getNome());
            }
            this.vista.setAzioniDisponibili(azioniDisponibili.toArray(new String[0]));

        }
    }

    private boolean azionePresente(String[] azioniSelezionate, Azione azione) {
        for (String action : azioniSelezionate) {
            if (action.equals(azione.getNome())) {
                return true;
            }
        }
        return false;
    }

    public void gestisciAzioni(ActionEvent e) {
        boolean isSelected = this.vista.getNuovoServizio().isAllAzioni();
        if (isSelected) {
            this.getVista().setAzioniDisponibili(new String[0]);
            this.getVista().setAzioniSelezionate(new String[0]);
        } else {
            aggiornaAzioniDisponibili(e);
        }
    }

    public VistaGestioneServizi getVista() {
        return vista;
    }

    public void setVista(VistaGestioneServizi vista) {
        this.vista = vista;
    }

    public IDAOServizio getDaoServizio() {
        return daoServizio;
    }

    public void setDaoServizio(IDAOServizio daoServizio) {
        this.daoServizio = daoServizio;
    }

    public String getMessaggio() {
        return messaggioTest;
    }

    public void setMessaggio(String messaggio) {
        this.messaggioTest = messaggio;
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

    public IDAOSoggetto getDaoSoggetto() {
        return daoSoggetto;
    }

    public void setDaoSoggetto(IDAOSoggetto daoSoggetto) {
        this.daoSoggetto = daoSoggetto;
    }

    public IDAOAccordoServizio getDaoAccordoServizio() {
        return daoAccordoServizio;
    }

    public void setDaoAccordoServizio(IDAOAccordoServizio daoAccordoServizio) {
        this.daoAccordoServizio = daoAccordoServizio;
    }

    public boolean isVisualizzaMessaggio() {
        return (this.messaggioTest != null && !this.messaggioTest.equalsIgnoreCase(""));
    }

    public boolean isVisualizzaErrore() {
        return (this.errore != null && !this.errore.equalsIgnoreCase(""));
    }

    public boolean isVisualizzaConferma() {
        return (this.getConferma() != null && !this.conferma.equalsIgnoreCase(""));
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
