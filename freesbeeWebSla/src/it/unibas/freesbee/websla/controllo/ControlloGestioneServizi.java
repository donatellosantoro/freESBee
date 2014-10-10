package it.unibas.freesbee.websla.controllo;

import it.unibas.freesbee.websla.utilita.Utilita;
import it.unibas.freesbee.websla.modello.Utente;
import it.unibas.icar.freesbee.modello.Servizio;
import it.unibas.icar.freesbee.modello.Soggetto;
import it.unibas.freesbee.websla.modello.WebServizio;
import it.unibas.freesbee.websla.persistenza.soap.DAOException;
import it.unibas.freesbee.websla.persistenza.soap.DAOServizioSOAP;
import it.unibas.freesbee.websla.vista.VistaGestioneServizi;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.faces.event.ActionEvent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.custom.fileupload.UploadedFile;

public class ControlloGestioneServizi {

    private Log logger = LogFactory.getLog(this.getClass());
    private VistaGestioneServizi vista;
    private String messaggio;
    private String errore;
    private DAOServizioSOAP daoServizio;
    private List listaFileCaricati = new ArrayList();
    private Utente utente;

    public void caricaTuttiServizi() {

        try {
            List<Servizio> listaServiziNICA = daoServizio.getServiziNICANonMonitorati(utente);
            List<WebServizio> listaWebServiziNICA = Utilita.trasformaServizioInWebServizio(listaServiziNICA);
            Collections.sort(listaServiziNICA);
            this.vista.setListaServiziNICA(listaWebServiziNICA);
        } catch (DAOException ex) {
            logger.error("Impossibile leggere l'elenco dei servizi non monitorati dal modulo  freESBeeSLA. " + ex);
            this.setErrore("Impossibile leggere l'elenco dei servizi non monitorati dal modulo  freESBeeSLA. Controllare che sia avviato.");
        }

        try {
            List<Servizio> listaServiziINF2Erogati = daoServizio.getServiziInf2Erogati(utente); 
            List<WebServizio> listaWebServiziINF2 = Utilita.trasformaServizioInWebServizio(listaServiziINF2Erogati);
            List<Servizio> listaServiziINF2Fruiti = daoServizio.getServiziInf2Fruiti(utente);
            listaWebServiziINF2.addAll(Utilita.trasformaServizioInWebServizio(listaServiziINF2Fruiti));
            this.vista.setListaServiziINF2(listaWebServiziINF2);
        } catch (DAOException ex) {
            logger.error("Impossibile leggere l'elenco dei servizi monitorati dal modulo  freESBeeSLA. " + ex);
            this.setErrore("Impossibile leggere l'elenco dei servizi monitorati dal modulo  freESBeeSLA. Controllare che sia avviato.");
        }
    }

    public String caricaGestioneServizi() {
        logger.debug("Caricamento di tutti i servizi in corso.");
        caricaTuttiServizi();
        return "schermoGestioneServizi";
    }

    public void aggiornaServizio(ActionEvent e) throws DAOException {
        try {
            WebServizio webServizioSelezionato = (WebServizio) this.vista.getTabellaServiziINF2().getRowData();
            Servizio servizioAggiornare = Utilita.trasformaWebServizioInServizio(webServizioSelezionato);
            daoServizio.updateServizioActive(utente,servizioAggiornare);
        } catch (DAOException ex) {
            logger.error("Impossibile aggiornare il servizio. " + ex);
            this.setErrore("Impossibile aggiornare il servizio.");
        }
    }

    public String caricaFileConfigurazioneWSAG() {
        try {
            WebServizio webServizioSelezionato = (WebServizio) this.vista.getTabellaServiziNICA().getRowData();
            Servizio servizio = new Servizio();
            servizio.setNome(webServizioSelezionato.getNome());
            Soggetto erogatore = new Soggetto();
            erogatore.setNome(webServizioSelezionato.getErogatore());
            Soggetto fruitore = new Soggetto();
            fruitore.setNome(webServizioSelezionato.getFruitore());
            servizio.setErogatore(erogatore);
            servizio.getFruitori().add(fruitore);

            UploadedFile fileWSAG = this.vista.getFileWSAG();        
            if (fileWSAG == null) {
                this.setErrore("Impossibile monitorare il servizio, non e' stato specificato alcun file wsag.");
                return null;
            }

            daoServizio.aggiungiServizio(utente, servizio, fileWSAG.getBytes());
            this.setMessaggio("Il servizio e' stato aggiunto alla lista dei servizi monitorati");
        } catch (IOException ex) {
            logger.error("Impossibile monitorare il servizio, errore nel caricamento del file. " + ex);
            this.setErrore("Impossibile monitorare il servizio, errore nel caricamento del file.");
        } catch (DAOException ex) {
            logger.error("Impossibile monitorare il servizio, errore nel caricamento del file. " + ex);
            this.setErrore("Impossibile monitorare il servizio, errore nel caricamento del file.");
        }
        return caricaGestioneServizi();
    }

    public VistaGestioneServizi getVista() {
        return vista;
    }

    public void setVista(VistaGestioneServizi vista) {
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

    public List getListaFileCaricati() {
        return listaFileCaricati;
    }

    public void setListaFileCaricati(List listaFileCaricati) {
        this.listaFileCaricati = listaFileCaricati;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
}
