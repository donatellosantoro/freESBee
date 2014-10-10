package it.unibas.freesbee.websla.controllo;

import it.unibas.freesbee.websla.modello.Utente;
import it.unibas.freesbee.websla.persistenza.soap.DAOConfigurazioneSOAP;
import it.unibas.freesbee.websla.persistenza.soap.DAOException;
import it.unibas.freesbee.websla.vista.VistaConfigurazione;
import it.unibas.freesbee.websla.ws.web.stub.DatiConfigurazione;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ControlloConfigurazione {

    private Log logger = LogFactory.getLog(this.getClass());
    private String messaggio;
    private String errore;
    private VistaConfigurazione vista;
    private Utente utente;
    private DatiConfigurazione datiConfigurazione;
    private DAOConfigurazioneSOAP daoConfigurazione;

    public String caricaConfigurazioneSLA() {
        this.ripulisci();
        DatiConfigurazione datiDaModificare = new DatiConfigurazione();
        datiDaModificare.setIndirizzoRegistroServizi(datiConfigurazione.getIndirizzoRegistroServizi());
        datiDaModificare.setNomeSLA(datiConfigurazione.getNomeSLA());
        datiDaModificare.setTipoSLA(datiConfigurazione.getTipoSLA());
        datiDaModificare.setNomeServizioMonitoraggioSLA(datiConfigurazione.getNomeServizioMonitoraggioSLA());
        datiDaModificare.setAccordoServizioSLA(datiConfigurazione.getAccordoServizioSLA());
        datiDaModificare.setTipoServizioMonitoraggioSLA(datiConfigurazione.getTipoServizioMonitoraggioSLA());
        datiDaModificare.setPdMonitoraggioSLA(datiConfigurazione.getPdMonitoraggioSLA());
        datiDaModificare.setAccordoServizioStato(datiConfigurazione.getAccordoServizioStato());
        datiDaModificare.setNomeServizioMonitoraggioStato(datiConfigurazione.getNomeServizioMonitoraggioStato());
        datiDaModificare.setTipoServizioMonitoraggioStato(datiConfigurazione.getTipoServizioMonitoraggioStato());
        datiDaModificare.setPdMonitoraggioStato(datiConfigurazione.getPdMonitoraggioStato());
        vista.setDatiConfigurazione(datiDaModificare);
        return "schermoConfigurazione";
    }

    public void modifica() {
        try {
            daoConfigurazione.modificaDatiConfigurazioneSLA(utente, vista.getDatiConfigurazione());
            //Altrimenti mi perdo i dati dela configurazione SP
            vista.getDatiConfigurazione().setUrlFreesbeeSP(datiConfigurazione.getUrlFreesbeeSP());
            vista.getDatiConfigurazione().setRisorsa(datiConfigurazione.getRisorsa());
            vista.getDatiConfigurazione().setRisorsaPdMonitoraggioSLA(datiConfigurazione.getRisorsaPdMonitoraggioSLA());
            vista.getDatiConfigurazione().setRisorsaPdMonitoraggioStato(datiConfigurazione.getRisorsaPdMonitoraggioStato());
            vista.getDatiConfigurazione().setProtezioneSP(datiConfigurazione.isProtezioneSP());

            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.setAttribute("datiConfigurazione", vista.getDatiConfigurazione());
            datiConfigurazione = vista.getDatiConfigurazione();
            caricaConfigurazioneSLA();
            this.messaggio = "Configurazione cambiata";

        } catch (DAOException ex) {
            logger.error("Impossibile modificare la configurazione del modulo SLA. " + ex);
            this.setErrore("Impossibile modificare la configurazione del modulo SLA. ");

        }
    }

    private void ripulisci() {
        this.messaggio = "";
        this.errore = "";
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
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

    public boolean isVisualizzaMessaggio() {
        return (this.messaggio != null && !this.messaggio.equalsIgnoreCase(""));
    }

    public boolean isVisualizzaErrore() {
        return (this.errore != null && !this.errore.equalsIgnoreCase(""));
    }

    public VistaConfigurazione getVista() {
        return vista;
    }

    public void setVista(VistaConfigurazione vista) {
        this.vista = vista;
    }

    public DatiConfigurazione getDatiConfigurazione() {
        return datiConfigurazione;
    }

    public void setDatiConfigurazione(DatiConfigurazione datiConfigurazione) {
        this.datiConfigurazione = datiConfigurazione;
    }

    public DAOConfigurazioneSOAP getDaoConfigurazione() {
        return daoConfigurazione;
    }

    public void setDaoConfigurazione(DAOConfigurazioneSOAP daoConfigurazione) {
        this.daoConfigurazione = daoConfigurazione;
    }
}
