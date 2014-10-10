package it.unibas.freesbee.ge.persistenza.hibernate;

import it.unibas.freesbee.ge.modello.Configurazione;
import it.unibas.freesbee.ge.persistenza.DAOException;
import it.unibas.freesbee.ge.persistenza.IDAOConfigurazione;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DAOConfigurazioneHibernate extends DAOGenericoHibernate<Configurazione> implements IDAOConfigurazione {

    private Log logger = LogFactory.getLog(this.getClass());

    public DAOConfigurazioneHibernate() {
        super(Configurazione.class);
    }

    public Configurazione caricaConfigurazione() throws DAOException {
        Configurazione configurazione = null;
        List<Configurazione> listaConfigurazioni = this.findAll();
        if (listaConfigurazioni.size() != 0) {
            configurazione = (Configurazione) this.findAll().get(0);

        }else{
            configurazione = new Configurazione();
            makePersistent(configurazione);
        }
        return configurazione;
    }

    public void salvaConfigurazione(Configurazione configurazione) throws DAOException {
        try {
            super.makePersistent(configurazione);
        } catch (DAOException ex) {
            logger.error("Errore.non e' possibile salvare la configurazione : " +ex);
        }
    }


}
