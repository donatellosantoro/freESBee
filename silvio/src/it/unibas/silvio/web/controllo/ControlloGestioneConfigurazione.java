package it.unibas.silvio.web.controllo;

import it.unibas.silvio.modello.Configurazione;
import it.unibas.silvio.persistenza.DAOException;
import it.unibas.silvio.persistenza.IDAOConfigurazione;
import it.unibas.silvio.web.util.SilvioWebUtil;
import it.unibas.silvio.web.vista.VistaGestioneConfigurazione;
import javax.faces.context.FacesContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ControlloGestioneConfigurazione {

    private VistaGestioneConfigurazione vista;
    private IDAOConfigurazione daoConfigurazione;
    private String messaggio;
    private String info;
    private String errore;
    private Log logger = LogFactory.getLog(this.getClass());

    public String caricaConfigurazione() {
        try {
            Configurazione configurazione = this.daoConfigurazione.caricaConfigurazione();
            this.vista.setDirConfig(configurazione.getDirConfig());
            this.vista.setPorta(configurazione.getPorta());
            return "configurazioneCaricata";
        } catch (DAOException ex) {
            logger.error("Impossibile caricare la configurazione. " + ex);
            this.setErrore("Impossibile caricare la configurazione. " + ex);
            return null;
        }
    }

    public String salvaConfigurazione() {
        try {
            Configurazione configurazione = this.daoConfigurazione.caricaConfigurazione();
            configurazione.setDirConfig(this.vista.getDirConfig());
            configurazione.setPorta(this.vista.getPorta());
            this.daoConfigurazione.salvaConfigurazione(configurazione);
            this.setMessaggio(SilvioWebUtil.caricaMessaggio("configurazione_salvata"));
            this.setInfo(SilvioWebUtil.caricaMessaggio("configurazione_salvata_info"));
            return "configurazioneSalvata";
        } catch (DAOException ex) {
            logger.error("Impossibile caricare la configurazione. " + ex);
            this.setErrore("Impossibile caricare la configurazione. " + ex);
            return null;
        }
    }

    public IDAOConfigurazione getDaoConfigurazione() {
        return daoConfigurazione;
    }

    public void setDaoConfigurazione(IDAOConfigurazione daoConfigurazione) {
        this.daoConfigurazione = daoConfigurazione;
    }

    public VistaGestioneConfigurazione getVista() {
        return vista;
    }

    public void setVista(VistaGestioneConfigurazione vista) {
        this.vista = vista;
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

    public boolean isVisualizzaMessaggio() {
        return (this.messaggio != null && !this.messaggio.equalsIgnoreCase(""));
    }

    public boolean isVisualizzaErrore() {
        return (this.errore != null && !this.errore.equalsIgnoreCase(""));
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    
    
}
