package it.unibas.freesbee.websla.controllo;

import it.unibas.freesbee.client.cxf.sistemamonitoraggio.ResponseServiceTermStateTypeSuper;
import it.unibas.freesbee.websla.utilita.Utilita;
import it.unibas.freesbee.websla.modello.Utente;
import it.unibas.freesbee.websla.persistenza.soap.DAOException;
import it.unibas.icar.freesbee.modello.Servizio;
import it.unibas.freesbee.websla.modello.WebServizio;
import it.unibas.freesbee.websla.modello.WebServizioFruito;
import it.unibas.freesbee.websla.persistenza.soap.DAOMonitoraggioSOAP;
import it.unibas.freesbee.websla.persistenza.soap.DAOServizioSOAP;
import it.unibas.freesbee.websla.persistenza.soap.DAOSoggettoSOAP;
import it.unibas.freesbee.websla.vista.VistaMonitoraggioStatoServiziFruiti;
import it.unibas.freesbee.websla.ws.web.stub.DatiConfigurazione;
import it.unibas.icar.freesbee.modello.Soggetto;
import java.util.ArrayList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

public class ControlloMonitoraggioStatoServiziFruiti {

    private Log logger = LogFactory.getLog(this.getClass());
    private VistaMonitoraggioStatoServiziFruiti vista;
    private DAOServizioSOAP daoServizio;
    private DAOSoggettoSOAP daoSoggetto;
    private DAOMonitoraggioSOAP daoMonitoraggio;
    private String errore;
    private String messaggio;
    private Utente utente;
    private Map<String, Soggetto> mappaSoggettiSLA = new HashMap<String, Soggetto>();

    public String richiediAcquisizionePortafoglio() {
        this.vista.ripulisci();
        this.setErrore("");
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        DatiConfigurazione dati = (DatiConfigurazione) session.getAttribute("datiConfigurazione");
        if (dati.isProtezioneSP()) {
            if (session != null) {
                session.setAttribute("schermoRitorno", "schermoMonitoraggioStatoServiziFruiti");
            }
            return "schermoAcquisizionePortafoglio";
        } else {
            caricaGestioneServizi();
            caricaLsitaModuliSLA(dati.getAccordoServizioStato(), dati.getNomeServizioMonitoraggioStato(), dati.getTipoServizioMonitoraggioStato());
            return "schermoMonitoraggioStatoServiziFruiti";
        }
    }

    public void caricaLsitaModuliSLA(String accordo, String servizio, String tipo) {
        try {

            setMappaSoggettiSLA(new HashMap<String, Soggetto>());
            List<Soggetto> listaSoggettiSLA = daoSoggetto.getSoggettiSLA(utente, accordo, servizio, tipo);
            List<SelectItem> listaItem = new ArrayList<SelectItem>();
            listaItem.add(new SelectItem(""));
            for (Soggetto soggetto : listaSoggettiSLA) {
                listaItem.add(new SelectItem(soggetto.getTipo() + " - " + soggetto.getNome()));
                getMappaSoggettiSLA().put(soggetto.getTipo() + " - " + soggetto.getNome(), soggetto);
            }
            this.vista.setListaItem(listaItem);

        } catch (it.unibas.freesbee.websla.persistenza.soap.DAOException ex) {
            ex.printStackTrace();
            logger.error("Impossibile leggere l'elenco dei servizi monitorati dal modulo  freESBeeSLA. " + ex);
            this.setErrore("Impossibile leggere l'elenco dei servizi monitorati dal modulo  freESBeeSLA");
        }
    }

    public void caricaTuttiServizi() {
        try {
            List<Servizio> listaServiziINF2 = daoServizio.getServiziInf2Fruiti(utente);

            List<WebServizio> listaWebServiziINF2 = Utilita.trasformaServizioInWebServizio(listaServiziINF2);
            logger.debug("Dimensione lista INF2 " + listaServiziINF2.size() + "      " + listaWebServiziINF2.size());

            Collections.sort(listaServiziINF2);

            this.vista.setListaServizi(listaWebServiziINF2);


        } catch (it.unibas.freesbee.websla.persistenza.soap.DAOException ex) {
            logger.error("Impossibile leggere l'elenco dei servizi monitorati dal modulo  freESBeeSLA. " + ex);
            this.setErrore("Impossibile leggere l'elenco dei servizi monitorati dal modulo  freESBeeSLA");
        }
    }

    public String caricaGestioneServizi() {
        logger.debug("Caricamento di tutti i servizi monitorati in corso.");
        this.vista.ripulisci();
        this.setErrore("");
        caricaTuttiServizi();
        return "schermoMonitoraggioStatoServiziFruiti";
    }

    public String aggiungi() {
        WebServizioFruito servizioAggiunto = this.vista.getWebServizioSelezionato();
        servizioAggiunto.setPortaDelegataMonitoraggioStato(this.vista.getPortaDelegataMonitoraggioStato());
        servizioAggiunto.setTipo(this.vista.isTipo());

        this.vista.getListaServiziAggiunti().add(servizioAggiunto);
        this.vista.setAggiungi(false);
        this.vista.setTabellaServizioVisibile(true);
        this.vista.setTabellaServiziAggiuntiVisibile(true);

        return null;
    }

    public String deselezionaServizio() {
        WebServizioFruito servizioEliminato = this.vista.getWebServizioSelezionato();
        this.vista.getListaServiziAggiunti().remove(servizioEliminato);
        this.vista.setWebServizioSelezionato(null);
        this.vista.ripulisci();
        this.setErrore("");
        return null;
    }

    public String selezionaServizio() {
        this.vista.ripulisci();
        this.setErrore("");
        WebServizioFruito servizioSelezionato = new WebServizioFruito();
        servizioSelezionato.setWebServizio((WebServizio) this.vista.getTabellaServizi().getRowData());
        this.vista.setWebServizioSelezionato(servizioSelezionato);
        this.vista.setTabellaServizioVisibile(true);
        this.vista.setAggiungi(true);
        return null;
    }

    public void verifica() {
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            DatiConfigurazione dati = (DatiConfigurazione) session.getAttribute("datiConfigurazione");

            WebServizioFruito servizioFerificato = (WebServizioFruito) this.vista.getWebServizioSelezionato();
            WebServizio servizio = servizioFerificato.getWebServizio();

            ResponseServiceTermStateTypeSuper response;

            if (dati.isProtezioneSP()) {
                response = daoMonitoraggio.monitoraggioStatoFruiti(vista.getUrlInvio(), servizioFerificato);
            } else {
                response = daoMonitoraggio.monitoraggioStatoFruiti(dati.getPdMonitoraggioStato(), servizioFerificato);
            }

            servizio.setStato(response.getServiceTermState().value());
            servizio.setContatoreRichieste(new Long(response.getCount()));

            this.vista.setVerificato(true);

        } catch (DAOException ex) {
            ex.printStackTrace();
            logger.error("Impossibile verificare lo stato dei servizi fruiti. " + ex);
            this.setErrore("Impossibile verificare lo stato dei servizi fruiti. Verificare la porta delegata inserita.");
        }

    }

    public void annullaVerifica() {
        WebServizioFruito servizioFerificato = (WebServizioFruito) this.vista.getWebServizioSelezionato();
        this.vista.getListaServiziAggiunti().remove(servizioFerificato);
        this.vista.setTabellaServiziAggiuntiVisibile(false);
        this.vista.setVerificato(false);
        this.vista.setAggiungi(true);
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

    public VistaMonitoraggioStatoServiziFruiti getVista() {
        return vista;
    }

    public void setVista(VistaMonitoraggioStatoServiziFruiti vista) {

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

    public DAOSoggettoSOAP getDaoSoggetto() {
        return daoSoggetto;
    }

    public void setDaoSoggetto(DAOSoggettoSOAP daoSoggetto) {
        this.daoSoggetto = daoSoggetto;
    }

    /**
     * @return the mappaSoggettiSLA
     */
    public Map<String, Soggetto> getMappaSoggettiSLA() {
        return mappaSoggettiSLA;
    }

    /**
     * @param mappaSoggettiSLA the mappaSoggettiSLA to set
     */
    public void setMappaSoggettiSLA(Map<String, Soggetto> mappaSoggettiSLA) {
        this.mappaSoggettiSLA = mappaSoggettiSLA;
    }
}
