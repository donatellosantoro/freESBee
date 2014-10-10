package it.unibas.freesbee.websla.controllo;

import it.unibas.freesbee.websla.utilita.Utilita;
import it.unibas.freesbee.websla.modello.Utente;
import it.unibas.icar.freesbee.modello.Servizio;
import it.unibas.freesbee.websla.modello.WebServizio;
import it.unibas.freesbee.websla.persistenza.soap.DAOException;
import it.unibas.freesbee.websla.persistenza.soap.DAOServizioSOAP;
import it.unibas.freesbee.websla.persistenza.soap.DAOStatisticheSOAP;
import it.unibas.freesbee.websla.vista.VistaStatisticheSLAErogati;
import it.unibas.freesbee.websla.ws.web.stub.StatisticheSLAErogatore;
import java.io.File;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ControlloStatisticheSLAErogati {

    private Log logger = LogFactory.getLog(this.getClass());
    private VistaStatisticheSLAErogati vista;
    private String messaggio;
    private String errore;
    private String conferma;
    private DAOServizioSOAP daoServizio;
    private DAOStatisticheSOAP daoStatistiche;
    private Utente utente;

    public void caricaTuttiServizi() {
        try {
            List<Servizio> listaServizi = daoServizio.getServiziInf2Erogati(utente);
            List<WebServizio> listaWebServiziINF2 = Utilita.trasformaServizioInWebServizio(listaServizi);

            Collections.sort(listaServizi);

            this.vista.setListaServizi(listaWebServiziINF2);

        } catch (DAOException ex) {
            logger.error("Impossibile leggere l'elenco dei servizi dal modulo  freESBeeSLA. " + ex);
            this.setErrore("Impossibile leggere l'elenco dei servizi dal modulo  freESBeeSLA. Controllare che sia avviato");
        }
    }

    public String caricaGestioneServizi() {
        logger.debug("Caricamento di tutti i servizi monitorati in corso.");
        this.vista.ripulisci();
        caricaTuttiServizi();
        return "schermoStatisticheSLAErogati";
    }

    public String chiudi() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        String realPath = session.getServletContext().getRealPath("");
        long tempoInMillisecondi = this.vista.getTempoInMillisecondi();
        getDaoStatistiche().removeStatisticheErogatore(realPath, tempoInMillisecondi);
        this.vista.setVisualizzaGrafico(false);
        return "statisticheSLAErogati";
    }

    public String statistiche() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);

            WebServizio webServizioSelezionato = (WebServizio) this.vista.getWebServizio();
            String nomeParametroSLA = this.vista.getNomeParametroSLA();
            String nomeStep = this.vista.getNomeStep();
            Date dataInizioOsservazione = this.vista.getDataInizioOsservazione();
            Date dataFineOsservazione = this.vista.getDataFineOsservazione();

            StatisticheSLAErogatore statistiche = new StatisticheSLAErogatore();
            statistiche.setNomeServizio(webServizioSelezionato.getNome());
            statistiche.setNomeErogatore(webServizioSelezionato.getErogatore());
            statistiche.setNomeFruitore(webServizioSelezionato.getFruitore());
            statistiche.setSlaNome(nomeParametroSLA);
            statistiche.setStep(nomeStep);
            statistiche.setDataInizio(Utilita.convertiDateToXMLGregorianCalendar(dataInizioOsservazione).normalize());
            statistiche.setDataFine(Utilita.convertiDateToXMLGregorianCalendar(dataFineOsservazione).normalize());
        
            
            String realPath = session.getServletContext().getRealPath("");
            long tempoAttualeInMillisecondi = new GregorianCalendar().getTimeInMillis();
            File immagineStatistiche = getDaoStatistiche().getStatisticheErogatore(utente, realPath, statistiche, tempoAttualeInMillisecondi);
            this.vista.setTempoInMillisecondi(tempoAttualeInMillisecondi);
            this.vista.setVisualizzaGrafico(true);
        } catch (DatatypeConfigurationException ex) {
            logger.error("E' stato riscontrato un errore nella data. " + ex);
            this.setErrore("E' stato riscontrato un errore nella data");
        } catch (DAOException ex) {
            logger.error("Impossibile ottenere il grafico dal modulo  freESBeeSLA. Controllare i parametri " + ex);
            this.setErrore("Impossibile ottenere il grafico dal modulo  freESBeeSLA. Controllare i parametri.");
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
            this.vista.setNomeParametroSLA(null);
            this.vista.setDataInizioOsservazione(null);
            this.vista.setDataFineOsservazione(null);
            this.vista.setNomeStep(null);
            this.vista.setVisualizzaGrafico(false);
            this.vista.setPannelloVisibile(true);
        } catch (DAOException ex) {
            logger.error("Impossibile leggere l'elenco dei parametri SLA dal modulo  freESBeeSLA. " + ex);
            this.setErrore("Impossibile leggere l'elenco dei parametri SLA dal modulo  freESBeeSLA. Controllare che sia avviato");
        }
        return null;
    }

    public VistaStatisticheSLAErogati getVista() {
        return vista;
    }

    public void setVista(VistaStatisticheSLAErogati vista) {
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

    public boolean isVisualizzaConferma() {
        return (this.getConferma() != null && !this.conferma.equalsIgnoreCase(""));
    }

    public String getConferma() {
        return conferma;
    }

    public void setConferma(String conferma) {
        this.conferma = conferma;
    }

    public DAOStatisticheSOAP getDaoStatistiche() {
        return daoStatistiche;
    }

    public void setDaoStatistiche(DAOStatisticheSOAP daoStatistiche) {
        this.daoStatistiche = daoStatistiche;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
}
