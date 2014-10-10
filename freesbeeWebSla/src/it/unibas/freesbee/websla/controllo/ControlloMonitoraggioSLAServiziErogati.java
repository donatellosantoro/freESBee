package it.unibas.freesbee.websla.controllo;

import it.unibas.freesbee.websla.utilita.Utilita;
import it.unibas.freesbee.client.cxf.sistemamonitoraggio.GuaranteeTermObj;
import it.unibas.freesbee.client.cxf.sistemamonitoraggio.ResultGaranteeTermObjStateSuper;
import it.unibas.freesbee.websla.modello.Utente;
import it.unibas.icar.freesbee.modello.Servizio;
import it.unibas.freesbee.websla.modello.WebSla;
import it.unibas.freesbee.websla.modello.WebServizio;
import it.unibas.freesbee.websla.persistenza.soap.DAOException;
import it.unibas.freesbee.websla.persistenza.soap.DAOServizioSOAP;
import it.unibas.freesbee.websla.persistenza.soap.DAOMonitoraggioSOAP;
import it.unibas.freesbee.websla.vista.VistaMonitoraggioSLAServiziErogati;
import it.unibas.freesbee.websla.ws.web.stub.DatiConfigurazione;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ControlloMonitoraggioSLAServiziErogati {

    private Log logger = LogFactory.getLog(this.getClass());
    private VistaMonitoraggioSLAServiziErogati vista;
    private String messaggio;
    private String errore;
    private DAOServizioSOAP daoServizio;
    private DAOMonitoraggioSOAP daoMonitoraggio;
    private Utente utente;

    public String richiediAcquisizionePortafoglio() {
        this.vista.ripulisci();
        this.setErrore("");
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        DatiConfigurazione dati = (DatiConfigurazione) session.getAttribute("datiConfigurazione");
        if (dati.isProtezioneSP()) {
            if (session != null) {
                session.setAttribute("schermoRitorno", "schermoMonitoraggioSLAServiziErogati");
            }
            return "schermoAcquisizionePortafoglio";
        } else {
            caricaGestioneServizi();
            return "schermoMonitoraggioSLAServiziErogati";
        }
    }

    public void caricaTuttiServizi() {
        try {
            List<Servizio> listaServizi = daoServizio.getServiziInf2Erogati(utente);
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
        return "schermoMonitoraggioSLAServiziErogati";
    }

    public String aggiungi() {
        try {
            String nomeParametroSLA = this.vista.getNomeParametroSLA();
            Date dataFineOsservazione = this.vista.getDataFineOsservazione();
            WebSla parametroSLA = new WebSla();
            parametroSLA.setGuaranteeTermName(nomeParametroSLA);
            XMLGregorianCalendar xmlGregorianCalendar = Utilita.convertiDateToXMLGregorianCalendar(dataFineOsservazione);
            parametroSLA.setDateFn(xmlGregorianCalendar);
            this.vista.getListaSlaAggiunti().add(parametroSLA);
            this.vista.setDataFineOsservazione(null);
            this.vista.setTabellaSLAAggiuntiVisibile(true);
            this.vista.setVerificato(false);
            this.vista.setNomeParametroSLA("");
        } catch (DatatypeConfigurationException ex) {
            logger.error("E' stato riscontrato un errore nella data. " + ex);
            this.setErrore("E' stato riscontrato un errore nella data");
        }
        return null;
    }

    public String verifica() {
        this.vista.setVerificato(true);
        this.vista.setAggiungi(true);
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            DatiConfigurazione dati = (DatiConfigurazione) session.getAttribute("datiConfigurazione");


            WebServizio webServizioSelezionato = this.vista.getWebServizio();
            List<GuaranteeTermObj> listaSLA = new ArrayList<GuaranteeTermObj>();

            for (int i = 0; i < this.vista.getListaSlaAggiunti().size(); i++) {
                listaSLA.add(Utilita.trasformaWebSlaInGuaranteeTermObj((WebSla) this.vista.getListaSlaAggiunti().get(i)));
            }
            List<ResultGaranteeTermObjStateSuper> listaRisposta = null;

            if (dati.isProtezioneSP()) {
                listaRisposta = daoMonitoraggio.monitoraggioSLAErogati(vista.getUrlInvio(), webServizioSelezionato, listaSLA);
            } else {
                listaRisposta = daoMonitoraggio.monitoraggioSLAErogati(dati.getPdMonitoraggioSLA(), webServizioSelezionato, listaSLA);
            }
            for (int i = 0; i < this.vista.getListaSlaAggiunti().size(); i++) {
                WebSla websla = this.vista.getListaSlaAggiunti().get(i);
                ResultGaranteeTermObjStateSuper result = listaRisposta.get(i);
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
            }

        } catch (DAOException ex) {
            logger.error("Impossibile verificare i parametri SLA per il servizio selezionato. " + ex);
            this.setErrore("Impossibile verificare i parametri SLA per il servizio selezionato");
        }
        return null;
    }

    public String aggiungiDaTabella() {
        try {
            WebServizio webServizioSelezionato = (WebServizio) this.vista.getTabellaServizi().getRowData();
            Servizio servizio = Utilita.trasformaWebServizioInServizio(webServizioSelezionato);
            List<String> parametriSLA = daoServizio.getParametriSla(utente, servizio);
            this.vista.setWebServizio(webServizioSelezionato);
            this.vista.setListaParametriSLA(parametriSLA);
            this.vista.setPannelloVisibile(true);
            this.vista.setListaSlaAggiunti(new ArrayList<WebSla>());
            this.vista.setTabellaSLAAggiuntiVisibile(false);
            this.vista.setAggiungi(false);
            this.vista.setNomeParametroSLA(null);
            this.vista.setDataFineOsservazione(null);
        } catch (DAOException ex) {
            logger.error("Impossibile leggere l'elenco dei parametri SLA dal modulo  freESBeeSLA. " + ex);
            this.setErrore("Impossibile leggere l'elenco dei parametri SLA dal modulo  freESBeeSLA");
        }
        return null;
    }

    public String eliminaDaTabella() {
        WebSla elementoSelezionato = (WebSla) this.vista.getTabellaSLA().getRowData();
        String nomeParametroSelezionato = elementoSelezionato.getGuaranteeTermName();
        WebSla elementoTrovato = null;
        for (WebSla e : this.vista.getListaSlaAggiunti()) {
            if (elementoSelezionato == e) {
                elementoTrovato = e;
            }
        }
        this.vista.getListaSlaAggiunti().remove(elementoTrovato);
        if (this.vista.getListaSlaAggiunti().size() == 0) {
            this.vista.setTabellaSLAAggiuntiVisibile(false);
        }

        this.vista.setNomeParametroSLA(null);
        this.vista.getListaParametriSLA().add(new SelectItem(nomeParametroSelezionato));
        return null;
    }

    public VistaMonitoraggioSLAServiziErogati getVista() {
        return vista;
    }

    public void setVista(VistaMonitoraggioSLAServiziErogati vista) {
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
}
