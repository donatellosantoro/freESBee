package it.unibas.silvio.web.controllo;

import it.unibas.silvio.modello.Messaggio;
import it.unibas.silvio.persistenza.DAOException;
import it.unibas.silvio.persistenza.IDAOMessaggio;
import it.unibas.silvio.web.vista.VistaVisualizzaMessaggi;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.model.SelectItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ControlloVisualizzaMessaggi {

    private Log logger = LogFactory.getLog(this.getClass());
    private VistaVisualizzaMessaggi vista;
    private String messaggio;
    private String errore;
    private String successo;
    private IDAOMessaggio daoMessaggio;

    public String caricaMessaggi() {
        try {
            logger.info("Carico i messaggi dal DB");
            List<Messaggio> listaMessaggio = daoMessaggio.findAll();
            if (listaMessaggio.size() == 0) {
                this.successo = "Non sono presenti messaggi nel db";
                return "messaggiCaricati";
            }
            this.vista.setListaMessaggi(listaMessaggio);
            List<SelectItem> listaItemIstanza = this.vista.getListaIstanza();
            Map<Object, String> mappaMessaggi = new HashMap<Object, String>();
            listaItemIstanza.add(new SelectItem("TUTTE"));
            for (Messaggio messaggio : listaMessaggio) {
                if (!mappaMessaggi.containsValue(messaggio.getIstanzaPortType())) {
                    logger.info("Non e' presente questo valore: " + messaggio.getIstanzaPortType());
                    mappaMessaggi.put(messaggio.getId(), messaggio.getIstanzaPortType());
                    listaItemIstanza.add(new SelectItem(messaggio.getIstanzaPortType()));
                }
            }
            this.vista.setListaIstanza(listaItemIstanza);
        } catch (DAOException ex) {
            logger.error("ERRORE! " + ex);
        }
        return "messaggiCaricati";
    }

    public String filtraMessaggi() {
        String idMessaggio = this.vista.getIdMessaggio();
        String idMessaggioRelates = this.vista.getIdMessaggioReleted();
        String tipoMessaggio = this.vista.getTipoMessaggio();
        String tipoIstanza = this.vista.getTipoIstanza();
        List<Messaggio> listaMessaggi;
        if (!idMessaggio.equals("") && !idMessaggioRelates.equals("")) {
            this.setErrore("Attenzione! La ricerca può avvenire o per idMessaggio o per idMessaggioRelatesTO");
        }
        try {
            if (!idMessaggio.equals("") || !idMessaggioRelates.equals("")) {
                if (!idMessaggio.equals("")) {
                    logger.info("Effettuo la ricerca per idMessaggio");
                    listaMessaggi = daoMessaggio.findByIDMessaggio(idMessaggio);
                    this.vista.setListaMessaggi(listaMessaggi);
                } else {
                    logger.info("Effettuo la ricerca per idMessaggioRelatesTO");
                    listaMessaggi = daoMessaggio.findByIDRelatesTo(idMessaggioRelates);
                    this.vista.setListaMessaggi(listaMessaggi);
                }
            }
            if (tipoMessaggio.equals("TUTTI") && tipoIstanza.equals("TUTTE")) {
                logger.info("Cerco tutti i messaggi presenti nel DB");
                listaMessaggi = daoMessaggio.findAll();
                this.vista.setListaMessaggi(listaMessaggi);
            }
            if (tipoMessaggio.equals("TUTTI") && !tipoIstanza.equals("TUTTE")) {
                logger.info("Cerco tutti i tipi di messaggi dell'istanza " + tipoIstanza);
                listaMessaggi = daoMessaggio.findByIstanzaPortType(tipoIstanza);
                this.vista.setListaMessaggi(listaMessaggi);
            }
            if (!tipoMessaggio.equals("TUTTI") && tipoIstanza.equals("TUTTE")) {
                logger.info("Cerco i messaggi " + tipoMessaggio + " di tutte le istanze");
                listaMessaggi = daoMessaggio.findByTipo(tipoMessaggio);
                this.vista.setListaMessaggi(listaMessaggi);
            }
            if (!tipoMessaggio.equals("TUTTI") && !tipoIstanza.equals("TUTTE")) {
                logger.info("Cerco i messaggi " + tipoMessaggio + " dell'istanza " + tipoIstanza);
                listaMessaggi = daoMessaggio.findByTipoIstanza(tipoMessaggio, tipoIstanza);
                this.vista.setListaMessaggi(listaMessaggi);
            }
            if (this.vista.getListaMessaggi() == null) {
                this.successo = "Caricati 0 messaggi";
            } else {
                this.successo = "Caricati " + this.vista.getListaMessaggi().size() + " messaggi";
            }
        } catch (DAOException daoe) {
            logger.error("ERRORE! IMPOSSIBILE ESEGUIRE LA RICERCA " + daoe);
            this.errore = "Errore. Impossibile eseguire la ricerca";
        }
        this.vista.setRicercaFiltro(true);
        return null;
    }

    public String confermaEliminazione() {
        Messaggio messaggioDaEliminare = (Messaggio) this.vista.getTabellaMessaggi().getRowData();
        this.vista.setMessaggio(messaggioDaEliminare);
        logger.info("Messaggio da eliminare " + messaggioDaEliminare.getId());
        this.vista.setElimina(true);
        logger.info("Stato conferma eliminazione " + this.vista.isElimina());
        return null;
    }

    public String svuotaMessaggi() {
        try {
            List<Messaggio> listaMessaggio = daoMessaggio.findAll();
            if (listaMessaggio.size() == 0) {
                this.errore = "Attenzione! Non sono presenti messaggi nel DB";
                return null;
            }
            for (Messaggio mess : listaMessaggio) {
                eliminaMessaggio(mess);
            }
            return this.filtraMessaggi();
        } catch (DAOException ex) {
            logger.error("ERRORE! IMPOSSIBILE ELIMINARE IL MESSAGGIO " + ex);
            this.errore = "Errore. Impossibile eliminare il messaggio";
        }
        return null;
    }

    public void eliminaMessaggio() {
        Messaggio messaggioDaEliminare = (Messaggio) this.vista.getMessaggio();
        try {
            logger.info("Sto eliminando " + messaggioDaEliminare.getId());
            eliminaMessaggio(messaggioDaEliminare);
            this.filtraMessaggi();
            this.chiudiModalPanelElimina();
            this.messaggio = "Messaggio eliminato con successo.";
        } catch (DAOException ex) {
            logger.error("ERRORE! IMPOSSIBILE ELIMINARE IL MESSAGGIO " + ex);
            this.errore = "Errore. Impossibile eliminare il messaggio";
        }
    }

    public String chiudiModalPanelElimina() {
        this.vista.setElimina(false);
        return null;
    }

    public String visualizzaDettaglioMessaggio() {
        Messaggio messaggioDaVisualizzare = (Messaggio) this.vista.getTabellaMessaggi().getRowData();
        logger.info("Messaggio da visualizzare: " + messaggioDaVisualizzare.getId());
        this.vista.setMessaggio(messaggioDaVisualizzare);
        this.vista.setVisualizzaMessaggio(true);
        return null;
    }

    public String chiudiModalPanelVisualizzaMessaggio() {
        this.vista.setVisualizzaMessaggio(false);
        return null;
    }

    public IDAOMessaggio getDaoMessaggio() {
        return daoMessaggio;
    }

    public void setDaoMessaggio(IDAOMessaggio daoMessaggio) {
        this.daoMessaggio = daoMessaggio;
    }

    public String getErrore() {
        return errore;
    }

    public void setErrore(String errore) {
        this.errore = errore;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    public String getSuccesso() {
        return successo;
    }

    public void setSuccesso(String successo) {
        this.successo = successo;
    }

    public VistaVisualizzaMessaggi getVista() {
        return vista;
    }

    public void setVista(VistaVisualizzaMessaggi vista) {
        this.vista = vista;
    }

    public boolean isVisualizzaMessaggio() {
        return (this.messaggio != null && !this.messaggio.equalsIgnoreCase(""));
    }

    public boolean isVisualizzaErrore() {
        return (this.errore != null && !this.errore.equalsIgnoreCase(""));
    }

    private void eliminaMessaggio(Messaggio messaggioDaEliminare) throws DAOException {
        Messaggio richiesta = messaggioDaEliminare.getMessaggioRichiesta();
        if (richiesta != null) {
            richiesta.setMessaggioRisposta(null);
            this.daoMessaggio.makePersistent(richiesta);
            messaggioDaEliminare.setMessaggioRichiesta(null);
        }
        Messaggio risposta = messaggioDaEliminare.getMessaggioRisposta();
        if (risposta != null) {
            risposta.setMessaggioRichiesta(null);
            this.daoMessaggio.makePersistent(risposta);
            messaggioDaEliminare.setMessaggioRisposta(null);
        }
        this.daoMessaggio.makeTransient(messaggioDaEliminare);
    }
}
