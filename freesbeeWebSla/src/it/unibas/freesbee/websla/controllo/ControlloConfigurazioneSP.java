package it.unibas.freesbee.websla.controllo;

import it.unibas.freesbee.websla.modello.Utente;
import it.unibas.freesbee.websla.persistenza.soap.DAOConfigurazioneSOAP;
import it.unibas.freesbee.websla.persistenza.soap.DAOException;
import it.unibas.freesbee.websla.vista.VistaConfigurazioneSP;
import it.unibas.freesbee.websla.ws.web.stub.DatiConfigurazione;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ControlloConfigurazioneSP {

    private Log logger = LogFactory.getLog(this.getClass());
    private String messaggio;
    private String errore;
    private VistaConfigurazioneSP vista;
    private Utente utente;
    private DatiConfigurazione datiConfigurazione;
    private DAOConfigurazioneSOAP daoConfigurazione;

    public String caricaConfigurazioneSP() {
        this.ripulisci();
        DatiConfigurazione datiDaModificare = new DatiConfigurazione();
        datiDaModificare.setUrlFreesbeeSP(datiConfigurazione.getUrlFreesbeeSP());
        datiDaModificare.setRisorsa(datiConfigurazione.getRisorsa());
        datiDaModificare.setRisorsaPdMonitoraggioSLA(datiConfigurazione.getRisorsaPdMonitoraggioSLA());
        datiDaModificare.setRisorsaPdMonitoraggioStato(datiConfigurazione.getRisorsaPdMonitoraggioStato());
        getVista().setDatiConfigurazione(datiDaModificare);
        return "schermoConfigurazioneSP";
    }

    public void modifica() {
        try {
            vista.getDatiConfigurazione().setProtezioneSP(datiConfigurazione.isProtezioneSP());

            getDaoConfigurazione().modificaDatiConfigurazioneSP(utente, vista.getDatiConfigurazione());
            //Altrimenti mi perdo i dati dela configurazione
            vista.getDatiConfigurazione().setIndirizzoRegistroServizi(datiConfigurazione.getIndirizzoRegistroServizi());
            vista.getDatiConfigurazione().setNomeSLA(datiConfigurazione.getNomeSLA());
            vista.getDatiConfigurazione().setTipoSLA(datiConfigurazione.getTipoSLA());
            vista.getDatiConfigurazione().setNomeServizioMonitoraggioSLA(datiConfigurazione.getNomeServizioMonitoraggioSLA());
            vista.getDatiConfigurazione().setAccordoServizioSLA(datiConfigurazione.getAccordoServizioSLA());
            vista.getDatiConfigurazione().setTipoServizioMonitoraggioSLA(datiConfigurazione.getTipoServizioMonitoraggioSLA());
            vista.getDatiConfigurazione().setPdMonitoraggioSLA(datiConfigurazione.getPdMonitoraggioSLA());
            vista.getDatiConfigurazione().setAccordoServizioStato(datiConfigurazione.getAccordoServizioStato());
            vista.getDatiConfigurazione().setNomeServizioMonitoraggioStato(datiConfigurazione.getNomeServizioMonitoraggioStato());
            vista.getDatiConfigurazione().setTipoServizioMonitoraggioStato(datiConfigurazione.getTipoServizioMonitoraggioStato());
            vista.getDatiConfigurazione().setPdMonitoraggioStato(datiConfigurazione.getPdMonitoraggioStato());

            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.setAttribute("datiConfigurazione", vista.getDatiConfigurazione());
            datiConfigurazione = vista.getDatiConfigurazione();
            caricaConfigurazioneSP();
            this.messaggio = "Configurazione SP cambiata";
        } catch (DAOException ex) {
            logger.error("Impossibile modificare la configurazione SP del modulo SLA. " + ex);
            this.setErrore("Impossibile modificare la configurazione SP del modulo SLA. ");

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

    public DatiConfigurazione getDatiConfigurazione() {
        return datiConfigurazione;
    }

    public void setDatiConfigurazione(DatiConfigurazione datiConfigurazione) {
        this.datiConfigurazione = datiConfigurazione;
    }

    public VistaConfigurazioneSP getVista() {
        return vista;
    }

    public void setVista(VistaConfigurazioneSP vista) {
        this.vista = vista;
    }

    public DAOConfigurazioneSOAP getDaoConfigurazione() {
        return daoConfigurazione;
    }

    public void setDaoConfigurazione(DAOConfigurazioneSOAP daoConfigurazione) {
        this.daoConfigurazione = daoConfigurazione;
    }
}
