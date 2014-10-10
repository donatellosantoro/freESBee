package it.unibas.freesbee.websla.controllo;

import it.unibas.freesbee.websla.utilita.Utilita;
import it.unibas.freesbee.client.cxf.sistemamonitoraggio.GuaranteeTermObj;
import it.unibas.freesbee.client.cxf.sistemamonitoraggio.ResultGaranteeTermObjStateSuper;
import it.unibas.freesbee.websla.modello.Utente;
import it.unibas.icar.freesbee.modello.Servizio;
import it.unibas.freesbee.websla.modello.WebSla;
import it.unibas.freesbee.websla.modello.WebServizio;
import it.unibas.freesbee.websla.modello.WebServizioFruito;
import it.unibas.freesbee.websla.persistenza.soap.DAOException;
import it.unibas.freesbee.websla.persistenza.soap.DAOServizioSOAP;
import it.unibas.freesbee.websla.persistenza.soap.DAOMonitoraggioSOAP;
import it.unibas.freesbee.websla.persistenza.soap.DAOSoggettoSOAP;
import it.unibas.freesbee.websla.vista.VistaMonitoraggioSLAServiziFruiti;
import it.unibas.freesbee.websla.ws.web.stub.DatiConfigurazione;
import it.unibas.icar.freesbee.modello.Soggetto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ControlloMonitoraggioSLAServiziFruiti {

    private Log logger = LogFactory.getLog(this.getClass());
    private VistaMonitoraggioSLAServiziFruiti vista;
    private String messaggio;
    private String errore;
    private DAOServizioSOAP daoServizio;
    private DAOSoggettoSOAP daoSoggetto;
    private DAOMonitoraggioSOAP daoMonitoraggio;
    private Utente utente;
    private Map<String, Soggetto> mappaSoggettiSLA = new HashMap<String, Soggetto>();

    public String richiediAcquisizionePortafoglio() {
        this.vista.ripulisci();
        this.setErrore("");
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        DatiConfigurazione dati = (DatiConfigurazione) session.getAttribute("datiConfigurazione");

        if (dati.isProtezioneSP()) {
            if (session != null) {
                session.setAttribute("schermoRitorno", "schermoMonitoraggioSLAServiziFruiti");
            }
            return "schermoAcquisizionePortafoglio";
        } else {
            caricaGestioneServizi();
            caricaLsitaModuliSLA(dati.getAccordoServizioSLA(), dati.getNomeServizioMonitoraggioSLA(), dati.getTipoServizioMonitoraggioSLA());
            return "schermoMonitoraggioSLAServiziFruiti";
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
            List<Servizio> listaServizi = daoServizio.getServiziInf2Fruiti(utente);
            List<WebServizio> listaWebServiziINF2 = Utilita.trasformaServizioInWebServizio(listaServizi);

            Collections.sort(listaServizi);

            this.vista.setListaServizi(listaWebServiziINF2);
        } catch (DAOException ex) {
            logger.error("Impossibile leggere l'elenco dei servizi dal modulo freESBeeSLA. " + ex);
            this.setErrore("Impossibile leggere l'elenco dei servizi dal modulo  freESBeeSLA. Controllare che sia avviato");
        }
    }

    public String caricaGestioneServizi() {
        logger.debug("Caricamento di tutti i servizi monitorati in corso.");
        this.vista.ripulisci();
        this.setErrore("");
        caricaTuttiServizi();
        return "schermoMonitoraggioSLAServiziFruiti";
    }

    public String selezionaServizio() {
        try {
            this.vista.ripulisci();
            this.setErrore("");
            WebServizioFruito servizioSelezionato = new WebServizioFruito();
            WebServizio webServizio = (WebServizio) this.vista.getTabellaServizi().getRowData();
            servizioSelezionato.setWebServizio(webServizio);

            Servizio servizio = Utilita.trasformaWebServizioInServizio(webServizio);
            List<String> parametriSLA = daoServizio.getParametriSla(utente, servizio);

            this.vista.setWebServizioSelezionato(servizioSelezionato);
            this.vista.setListaParametriSLA(parametriSLA);
            this.vista.setAggiungiServizio(true);
            this.vista.setTabellaServizioVisibile(true);

        } catch (DAOException ex) {
            logger.error("Impossibile leggere l'elenco dei parametri SLA dal modulo  freESBeeSLA. " + ex);
            this.setErrore("Impossibile leggere l'elenco dei parametri SLA dal modulo  freESBeeSLA");
        }
        return null;
    }

    public String aggiungiServizio() {
        WebServizioFruito servizioAggiunto = this.vista.getWebServizioSelezionato();
        servizioAggiunto.setPortaDelegataMonitoraggioSLA(this.vista.getPortaDelegataMonitoraggioSLA());
        servizioAggiunto.setTipo(this.vista.isTipo());
        this.vista.setTabellaSLAAggiuntiVisibile(true);
        this.vista.setAggiungiServizio(false);
        this.vista.setVerificato(false);
        this.vista.setNomeParametroSLA("");
        this.vista.setAggiungi(true);

        return null;
    }

    public String deselezionaServizio() {
        this.vista.ripulisci();
        this.vista.setWebServizioSelezionato(null);
        this.vista.setNomeParametroSLA("");
        this.setErrore("");
        return null;
    }

    public String aggiungiSLA() {
        try {
            String nomeParametroSLA = this.vista.getNomeParametroSLA();
            Date dataFineOsservazione = this.vista.getDataFineOsservazione();
            WebSla parametroSLA = new WebSla();
            parametroSLA.setGuaranteeTermName(nomeParametroSLA);
            XMLGregorianCalendar xmlGregorianCalendar = Utilita.convertiDateToXMLGregorianCalendar(dataFineOsservazione);
            parametroSLA.setDateFn(xmlGregorianCalendar);
            parametroSLA.setEsito("");
            this.vista.setWebSLASelezionato(parametroSLA);
            this.vista.getListaSlaAggiunti().add(parametroSLA);
            this.vista.setTabellaSLAAggiuntiVisibile(true);
            this.vista.setAggiungi(false);
            this.vista.setVerificato(true);
        } catch (DatatypeConfigurationException ex) {
            logger.error("E' stato riscontrato un errore nella data. " + ex);
            this.setErrore("E' stato riscontrato un errore nella data");
        }
        return null;
    }

    public String eliminaSLA() {
        WebSla elementoSelezionato = (WebSla) this.vista.getWebSLASelezionato();
        this.vista.getListaSlaAggiunti().remove(elementoSelezionato);
        this.vista.setWebSLASelezionato(null);
        this.vista.setNomeParametroSLA("");
        this.vista.setDataFineOsservazione(null);
        this.vista.setAggiungi(false);
        this.vista.setTabellaSLAAggiuntiVisibile(false);
        this.vista.setAggiungiServizio(true);
        this.vista.setVerificato(false);
        return null;
    }

    public String verifica() {
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            DatiConfigurazione dati = (DatiConfigurazione) session.getAttribute("datiConfigurazione");

            WebServizioFruito webServizioSelezionato = this.vista.getWebServizioSelezionato();
            List<GuaranteeTermObj> listaSLA = new ArrayList<GuaranteeTermObj>();

            listaSLA.add(Utilita.trasformaWebSlaInGuaranteeTermObj((WebSla) this.vista.getListaSlaAggiunti().get(0)));

            List<ResultGaranteeTermObjStateSuper> listaRisposta = null;
            logger.info("\n\n\n\n************" + dati.isProtezioneSP() + "\n\n\n\n");
            if (dati.isProtezioneSP()) {
                listaRisposta = daoMonitoraggio.monitoraggioSLAFruiti(this.vista.getUrlInvio(), webServizioSelezionato, listaSLA);
            } else {
                listaRisposta = daoMonitoraggio.monitoraggioSLAFruiti(dati.getPdMonitoraggioSLA(), webServizioSelezionato, listaSLA);
            }

            WebSla websla = this.vista.getListaSlaAggiunti().get(0);
            ResultGaranteeTermObjStateSuper result = listaRisposta.get(0);
            websla.setEsito(result.getGuaranteeState().value());
            websla.setRisultato(result.getGuaranteeTermsResult().getRisultatoFinale());
            String risAtt = "";
            String operatore = Utilita.convertiOperatore(result.getGuaranteeTermsResult().getThresholdOperator());
            if (operatore.equals("")) {
                risAtt += result.getGuaranteeTermsResult().getThresholdOperator();
            } else {
                risAtt = operatore;
            }
            websla.setRisultatoAtteso(risAtt + result.getGuaranteeTermsResult().getThresholdValue());

        } catch (DAOException ex) {
            ex.printStackTrace();
            logger.error("Impossibile verificare i parametri SLA per il servizio selezionato. " + ex);
            this.setErrore("Impossibile verificare i parametri SLA per il servizio selezionato. Verificare la porta delegata inserita.");
        }

        this.vista.setVerificato(false);
        return null;
    }

    public String annullaVerifica() {
        this.vista.getListaSlaAggiunti().remove(this.vista.getWebSLASelezionato());
        this.vista.setAggiungi(true);
        this.vista.setVerificato(false);
        return null;
    }

    public VistaMonitoraggioSLAServiziFruiti getVista() {
        return vista;
    }

    public void setVista(VistaMonitoraggioSLAServiziFruiti vista) {
        this.vista = vista;
    }

    public DAOServizioSOAP getDaoServizio() {
        return daoServizio;
    }

    public void setDaoServizio(DAOServizioSOAP daoServizio) {
        this.daoServizio = daoServizio;
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

    public DAOMonitoraggioSOAP getDaoMonitoraggio() {
        return daoMonitoraggio;
    }

    public void setDaoMonitoraggio(DAOMonitoraggioSOAP daoMonitoraggio) {
        this.daoMonitoraggio = daoMonitoraggio;
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

    public Map<String, Soggetto> getMappaSoggettiSLA() {
        return mappaSoggettiSLA;
    }

    public void setMappaSoggettiSLA(Map<String, Soggetto> mappaSoggettiSLA) {
        this.mappaSoggettiSLA = mappaSoggettiSLA;
    }
}
