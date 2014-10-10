package it.unibas.freesbee.websla.controllo;

import it.unibas.freesbee.client.cxf.sistemamonitoraggio.ResponseServiceTermStateTypeSuper;
import it.unibas.freesbee.websla.utilita.Utilita;
import it.unibas.freesbee.websla.modello.Utente;
import it.unibas.icar.freesbee.modello.Servizio;
import it.unibas.freesbee.websla.modello.WebServizio;
import it.unibas.freesbee.websla.persistenza.soap.DAOException;
import it.unibas.freesbee.websla.persistenza.soap.DAOMonitoraggioSOAP;
import it.unibas.freesbee.websla.persistenza.soap.DAOServizioSOAP;
import it.unibas.freesbee.websla.vista.VistaMonitoraggioStatoServiziErogati;
import it.unibas.freesbee.websla.ws.web.stub.DatiConfigurazione;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.Collections;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class ControlloMonitoraggioStatoServiziErogati {

    private Log logger = LogFactory.getLog(this.getClass());
    private VistaMonitoraggioStatoServiziErogati vista;
    private DAOServizioSOAP daoServizio;
    private DAOMonitoraggioSOAP daoMonitoraggio;
    private String errore;
    private String messaggio;
    private Utente utente;

    public String richiediAcquisizionePortafoglio() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        DatiConfigurazione dati = (DatiConfigurazione) session.getAttribute("datiConfigurazione");
        if (dati.isProtezioneSP()) {
            if (session != null) {
                session.setAttribute("schermoRitorno", "schermoMonitoraggioStatoServiziErogati");
            }
            return "schermoAcquisizionePortafoglio";
        } else {
            caricaGestioneServizi();
            return "schermoMonitoraggioStatoServiziErogati";
        }
    }

    public void caricaTuttiServizi() {
        try {
            this.vista.ripulisci();
            this.setErrore("");
            List<Servizio> listaServizi = daoServizio.getServiziInf2Erogati(utente);
            List<WebServizio> listaWebServiziINF2 = Utilita.trasformaServizioInWebServizio(listaServizi);

            Collections.sort(listaServizi);

            this.vista.setListaServizi(listaWebServiziINF2);
        } catch (DAOException ex) {
            logger.error("Impossibile leggere l'elenco dei servizi dal modulo freESBeeSLA. " + ex);
            this.setErrore("Impossibile leggere l'elenco dei servizi dal modulo  freESBeeSLA. Controllare che sia avviato");
        }
    }

    public void caricaTuttiServiziStato() {
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            DatiConfigurazione dati = (DatiConfigurazione) session.getAttribute("datiConfigurazione");

            List<Servizio> listaServiziINF2 = daoServizio.getServiziInf2Erogati(utente);
            List<WebServizio> listaWebServiziINF2 = Utilita.trasformaServizioInWebServizio(listaServiziINF2);
            logger.debug("Dimensione lista INF2 " + listaServiziINF2.size() + "      " + listaWebServiziINF2.size());

            for (int i = 0; i < listaWebServiziINF2.size(); i++) {
                ResponseServiceTermStateTypeSuper response;
                if (dati.isProtezioneSP()) {
                    response = daoMonitoraggio.monitoraggioStatoErogati(vista.getUrlInvio(), listaWebServiziINF2.get(i));
                } else {
                    response = daoMonitoraggio.monitoraggioStatoErogati(dati.getPdMonitoraggioStato(), listaWebServiziINF2.get(i));
                }
                listaWebServiziINF2.get(i).setStato(response.getServiceTermState().value());
                listaWebServiziINF2.get(i).setContatoreRichieste(new Long(response.getCount()));
            }

            Collections.sort(listaServiziINF2);

            this.vista.setListaServizi(listaWebServiziINF2);
            this.vista.setVerificato(true);
        } catch (it.unibas.freesbee.websla.persistenza.soap.DAOException ex) {
            logger.error("Impossibile leggere l'elenco dei servizi monitorati dal modulo  freESBeeSLA. " + ex);
            this.setErrore("Impossibile leggere l'elenco dei servizi monitorati dal modulo  freESBeeSLA");
        }
    }

    public String caricaGestioneServizi() {
        logger.debug("Caricamento di tutti i servizi monitorati in corso.");
        caricaTuttiServizi();

        return "schermoMonitoraggioStatoServiziErogati";
    }

    public DAOServizioSOAP getDaoServizio() {
        return daoServizio;
    }

    public void setDaoServizio(DAOServizioSOAP daoServizio) {
        this.daoServizio = daoServizio;
    }

    public String getErrore() {
        return errore;
    }

    public void setErrore(String errore) {
        this.errore = errore;
    }

    public VistaMonitoraggioStatoServiziErogati getVista() {
        return vista;
    }

    public void setVista(VistaMonitoraggioStatoServiziErogati vista) {

        this.vista = vista;
    }

    public boolean isVisualizzaErrore() {
        return (this.errore != null && !this.errore.equalsIgnoreCase(""));
    }

    public DAOMonitoraggioSOAP getDaoMonitoraggio() {
        return daoMonitoraggio;
    }

    public void setDaoMonitoraggio(DAOMonitoraggioSOAP daoMonitoraggio) {
        this.daoMonitoraggio = daoMonitoraggio;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    public boolean isVisualizzaMessaggio() {
        return (this.messaggio != null && !this.messaggio.equalsIgnoreCase(""));
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
}
