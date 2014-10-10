package it.unibas.icar.freesbee.controllo;

import it.unibas.icar.freesbee.modello.Configurazione;
import it.unibas.icar.freesbee.modello.Soggetto;
import it.unibas.icar.freesbee.modello.Utente;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOConfigurazione;
import it.unibas.icar.freesbee.persistenza.IDAOSoggetto;
import it.unibas.icar.freesbee.vista.VistaGestioneConfigurazione;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ControlloGestioneConfigurazione {

    private Log logger = LogFactory.getLog(this.getClass());
    private VistaGestioneConfigurazione vista;
    private String messaggioTest;
    private String errore;
    private String conferma;
    private IDAOConfigurazione daoConfigurazione;
    private IDAOSoggetto daoSoggetto;
    private String gestioneStatoConnettoreNICA;
    private Utente utente;

    public String caricaConfigurazione() {
        try {
            Configurazione configurazione = daoConfigurazione.find(this.utente);
            List<Soggetto> listaSoggetti = daoSoggetto.findAll(this.utente);
            gestioneModificaConfigurazione(configurazione);
            this.vista.setinviaANICAChecked(configurazione.isInviaANICA());
            this.vista.setNICAChecked(configurazione.isNICA());
            this.vista.setListaSoggettiErogatoreRegistroServizi(trasformaListaSoggetti(listaSoggetti));
            this.vista.setListaSoggettiErogatoreNICA(trasformaListaSoggetti(listaSoggetti));
        } catch (DAOException daoe) {
            logger.error("Impossibile leggere la configurazione dalla base di dati. " + daoe);
            this.setErrore("Impossibile leggere la configurazione dalla base di dati. Controllare che freESBee sia avviato");
        }
        return "gestioneConfigurazione";
    }

    public void gestioneInserisciConfigurazione(Configurazione nuovaConfigurazione) {
        this.vista.setInserisci(true);
        this.vista.setModifica(false);
        this.vista.setConfigurazione(nuovaConfigurazione);
    }

    public void gestioneModificaConfigurazione(Configurazione modificaConfigurazione) {
        this.vista.setInserisci(false);
        this.vista.setModifica(true);
        this.vista.setConfigurazione(modificaConfigurazione);
    }

    public void gestioneInviaANICA(ActionEvent e) throws DAOException {
        if (this.vista.isinviaANICAChecked()) {
            this.vista.setinviaANICAChecked(false);

//            List<Soggetto> listaSoggetti = daoSoggetto.findAll();
//            long idSoggetto = this.vista.getConfigurazione().getIdSoggettoErogatoreRegistroServizi();
//            Soggetto soggettoEliminare = daoSoggetto.findById(idSoggetto, false);
//            this.vista.setListaSoggettiErogatoreNICA(trasformaListaSoggetti(soggettoEliminare, listaSoggetti));

            Configurazione configurazioneModificare = this.vista.getConfigurazione();
            configurazioneModificare.setConnettoreRegistroServizi("");
            configurazioneModificare.setIdSoggettoErogatoreRegistroServizi(0);
            configurazioneModificare.setSoggettoErogatoreRegistroServizi(null);
        } else {
            this.vista.setinviaANICAChecked(true);
        }
    }

    public void gestioneStatoNICA(ActionEvent e) throws DAOException {
        if (this.vista.isNICAChecked()) {
            this.vista.setNICAChecked(false);

//            List<Soggetto> listaSoggetti = daoSoggetto.findAll();
//            long idSoggetto = this.vista.getConfigurazione().getIdSoggettoErogatoreNICA();
//            Soggetto soggettoEliminare = daoSoggetto.findById(idSoggetto, false);
//            this.vista.setListaSoggettiErogatoreRegistroServizi(trasformaListaSoggetti(soggettoEliminare, listaSoggetti));

            Configurazione configurazioneModificare = this.vista.getConfigurazione();
            configurazioneModificare.setIdSoggettoErogatoreNICA(0);
            configurazioneModificare.setSoggettoErogatoreNICA(null);
        } else {
            this.vista.setNICAChecked(true);
        }
    }

    public void gestioneSelezioneSoggetto(ActionEvent e) throws DAOException {
        List<Soggetto> listaSoggetti = daoSoggetto.findAll(this.utente);
        if (this.vista.isNICAChecked()) {
            long idSoggetto = this.vista.getConfigurazione().getIdSoggettoErogatoreNICA();
            Soggetto soggettoEliminare = daoSoggetto.findById(this.utente, idSoggetto, false);
            this.vista.setListaSoggettiErogatoreRegistroServizi(trasformaListaSoggetti(soggettoEliminare, listaSoggetti));
        } else {
            this.vista.setListaSoggettiErogatoreRegistroServizi(trasformaListaSoggetti(listaSoggetti));
        }

        if (this.vista.isinviaANICAChecked()) {
            long idSoggetto = this.vista.getConfigurazione().getIdSoggettoErogatoreRegistroServizi();
            Soggetto soggettoEliminare = daoSoggetto.findById(this.utente, idSoggetto, false);
            this.vista.setListaSoggettiErogatoreNICA(trasformaListaSoggetti(soggettoEliminare, listaSoggetti));
        } else {
            this.vista.setListaSoggettiErogatoreNICA(trasformaListaSoggetti(listaSoggetti));
        }
    }

    public String inserisci() {
        try {
            Configurazione configurazioneAggiungere = this.vista.getConfigurazione();
            if ((this.vista.isinviaANICAChecked()) &&
                    (this.vista.isNICAChecked()) &&
                    (this.vista.getConfigurazione().getIdSoggettoErogatoreNICA() == this.vista.getConfigurazione().getIdSoggettoErogatoreRegistroServizi())) {
                this.setErrore("Impossibile selezionare lo stesso soggetto sia per il Registro dei Servizi che per il NICA.");
                return "gestioneConfigurazione";
            }

            daoConfigurazione.makePersistent(this.utente, configurazioneAggiungere);
            Configurazione configurazioneAggiunta = daoConfigurazione.find(this.utente);
            this.setMessaggio("Configurazione inserita correttamente.");
            gestioneModificaConfigurazione(configurazioneAggiunta);
        } catch (DAOException ex) {
            logger.error("Impossibile inserire la configurazione. " + ex);
            String erroreFormattato = ex.getMessage().substring(ex.getMessage().indexOf(":") + 1);
            this.setErrore(erroreFormattato);
        }
        return "gestioneConfigurazione";
    }

    public String modifica() {
        try {
            Configurazione configurazione = this.vista.getConfigurazione();
            if ((this.vista.isinviaANICAChecked()) &&
                    (this.vista.isNICAChecked()) &&
                    (this.vista.getConfigurazione().getIdSoggettoErogatoreNICA() == this.vista.getConfigurazione().getIdSoggettoErogatoreRegistroServizi())) {
                this.setErrore("Impossibile selezionare lo stesso soggetto sia per il Registro dei Servizi che per il NICA.");
                return "gestioneConfigurazione";
            }

            if (!ProtocolloAmmesso(configurazione.getIndirizzoPortaDelegata())) {
                return "gestioneConfigurazione";
            }

            if (!ProtocolloAmmesso(configurazione.getIndirizzoPortaApplicativa())) {
                return "gestioneConfigurazione";
            }

            String sottostringaIndirizzoPD = configurazione.getIndirizzoPortaDelegata().substring(configurazione.getIndirizzoPortaDelegata().indexOf(":") + 3);
            String protocolloPortaDelegata = EstraiProtocollo(configurazione.getIndirizzoPortaDelegata());
            int numeroPortaDelegata = RestituisciNumeroPorta(sottostringaIndirizzoPD);

            String sottostringaIndirizzoPA = configurazione.getIndirizzoPortaApplicativa().substring(configurazione.getIndirizzoPortaApplicativa().indexOf(":") + 3);
            String protocolloPortaApplicativa = EstraiProtocollo(configurazione.getIndirizzoPortaApplicativa());
            int numeroPortaApplicativa = RestituisciNumeroPorta(sottostringaIndirizzoPA);

            if ((numeroPortaDelegata == numeroPortaApplicativa) && (!protocolloPortaDelegata.equals(protocolloPortaApplicativa))){
                this.setErrore("Errore: e' stato specificato nell'URI di entrambe le porte lo stesso numero di porta, ma protocolli diversi");
                return "gestioneConfigurazione";
            }
            daoConfigurazione.makePersistent(this.utente, configurazione);
            this.setMessaggio("Configurazione modificata correttamente.");
        } catch (DAOException ex) {
            logger.error("Impossibile modificare la configurazione. " + ex);
            this.setErrore(ex.getMessage());
        } catch (NumberFormatException nfe) {
            int posizioneIniziale = nfe.getMessage().length() - 5;
            int posizioneFinale = nfe.getMessage().length() - 1;
            String messaggioFormattato = nfe.getMessage().substring(posizioneIniziale, posizioneFinale);
            logger.error("Errore: la porta " + messaggioFormattato + " specificata non e' corretta. " + nfe.getMessage());
            this.setErrore("Errore: la porta " + messaggioFormattato + " specificata non e' corretta.");
        }
        return "gestioneConfigurazione";
    }

    private boolean ProtocolloAmmesso(String indirizzoPorta) {
        if ((indirizzoPorta.indexOf("http") == -1) && (indirizzoPorta.indexOf("https") == -1)) {
            this.setErrore("Errore: il protocollo specificato per l'URI " + indirizzoPorta + " non e' http o https");
            return false;
        }
        return true;
    }

    private String EstraiProtocollo(String indirizzoPorta) {
        if (indirizzoPorta.indexOf("https") != -1) {
            return indirizzoPorta.substring(0, 4);
        } else {
            return indirizzoPorta.substring(0, 3);
        }
    }

    private int RestituisciNumeroPorta(String sottostringaIndirizzoPorta) {
        int inizioIndicazionePorta = sottostringaIndirizzoPorta.indexOf(":") + 1;
        int fineIndicazionePorta = sottostringaIndirizzoPorta.indexOf("/");
        int numeroPorta = Integer.parseInt(sottostringaIndirizzoPorta.substring(inizioIndicazionePorta, fineIndicazionePorta));
        return numeroPorta;
    }

    private List<SelectItem> trasformaListaSoggetti(List<Soggetto> listaSoggetti) {
        List<SelectItem> lista = new ArrayList<SelectItem>();
        lista.add(new SelectItem(""));
        Collections.sort(listaSoggetti);
        for (Soggetto soggetto : listaSoggetti) {
            lista.add(new SelectItem(soggetto.getId(), soggetto.getTipo() + "\\" + soggetto.getNome()));
        }
        return lista;
    }

    private List<SelectItem> trasformaListaSoggetti(Soggetto soggettoEscludere, List<Soggetto> listaSoggetti) {
        List<SelectItem> lista = new ArrayList<SelectItem>();
        lista.add(new SelectItem(""));
        Collections.sort(listaSoggetti);
        for (Soggetto soggetto : listaSoggetti) {
            if (soggetto.getId() != soggettoEscludere.getId()) {
                lista.add(new SelectItem(soggetto.getId(), soggetto.getTipo() + "\\" + soggetto.getNome()));
            }
        }
        return lista;
    }

    public VistaGestioneConfigurazione getVista() {
        return vista;
    }

    public void setVista(VistaGestioneConfigurazione vista) {
        this.vista = vista;
    }

    public IDAOConfigurazione getDaoConfigurazione() {
        return daoConfigurazione;
    }

    public void setDaoConfigurazione(IDAOConfigurazione daoConfigurazione) {
        this.daoConfigurazione = daoConfigurazione;
    }

    public IDAOSoggetto getDaoSoggetto() {
        return daoSoggetto;
    }

    public void setDaoSoggetto(IDAOSoggetto daoSoggetto) {
        this.daoSoggetto = daoSoggetto;
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
}
