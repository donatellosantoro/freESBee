package it.unibas.freesbee.ge.persistenza.hibernate;

import it.unibas.freesbee.ge.modello.ConfigurazioneSP;
import it.unibas.freesbee.ge.persistenza.DAOException;
import it.unibas.freesbee.ge.persistenza.IDAOConfigurazioneSP;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DAOConfigurazioneSPHibernate extends DAOGenericoHibernate<ConfigurazioneSP> implements IDAOConfigurazioneSP {

    private Log logger = LogFactory.getLog(this.getClass());

    public DAOConfigurazioneSPHibernate() {
        super(ConfigurazioneSP.class);
    }

    public ConfigurazioneSP caricaConfigurazioneSP() throws DAOException {
        ConfigurazioneSP configurazioneSP = null;
        List<ConfigurazioneSP> listaConfigurazioniSP = this.findAll();
        if (listaConfigurazioniSP.size() != 0) {
            configurazioneSP = (ConfigurazioneSP) this.findAll().get(0);

        }else{
            configurazioneSP = new ConfigurazioneSP();
            makePersistent(configurazioneSP);
        }
        return configurazioneSP;
    }

    public void salvaConfigurazioneSP(ConfigurazioneSP configurazioneSP) throws DAOException {
        try {
            super.makePersistent(configurazioneSP);
        } catch (DAOException ex) {
            logger.error("Errore.non e' possibile salvare la configurazione : " +ex);
        }
    }


}
