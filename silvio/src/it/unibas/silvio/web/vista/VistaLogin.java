package it.unibas.silvio.web.vista;

import it.unibas.silvio.modello.IstanzaOperation;
import it.unibas.silvio.modello.Utente;
import it.unibas.silvio.persistenza.DAOException;
import it.unibas.silvio.persistenza.IDAOIstanzaOperation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class VistaLogin implements Serializable {

    private Utente utente = new Utente();
    private Log logger = LogFactory.getLog(this.getClass());
    private List<SelectItem> avvioRapido = new ArrayList<SelectItem>();
    private String avvioRapidoSelezionato;
    private transient IDAOIstanzaOperation daoIstanzaOperation;

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public List<SelectItem> getAvvioRapido() {
        if (avvioRapido.isEmpty()) {
        logger.info("Aggiorno la lista degli avvii rapidi");
            try {
                avvioRapido.add(new SelectItem(0, "- Configura SIL-VIO -"));
                List<IstanzaOperation> operations = daoIstanzaOperation.findAll();
                for (IstanzaOperation istanzaOperation : operations) {
                    if (istanzaOperation.getIstanzaPortType().isFruizione() && istanzaOperation.isAvvioRapido()) {
                        avvioRapido.add(new SelectItem(istanzaOperation.getId(), istanzaOperation.getIstanzaPortType().getIstanzaAccordo().getNome() + " - " + istanzaOperation.getIstanzaPortType().getNome()));
                    }
                }
            } catch (DAOException ex) {
                logger.warn("Impossibile leggere l'elenco delle istanze.");
            }
        }
        return avvioRapido;
    }

    public void setAvvioRapido(List<SelectItem> avvioRapido) {
        this.avvioRapido = avvioRapido;
    }

    public String getAvvioRapidoSelezionato() {
        return avvioRapidoSelezionato;
    }

    public void setAvvioRapidoSelezionato(String avvioRapidoSelezionato) {
        this.avvioRapidoSelezionato = avvioRapidoSelezionato;
    }

    public IDAOIstanzaOperation getDaoIstanzaOperation() {
        return daoIstanzaOperation;
    }

    public void setDaoIstanzaOperation(IDAOIstanzaOperation daoIstanzaOperation) {
        this.daoIstanzaOperation = daoIstanzaOperation;
    }
}
