package it.unibas.icar.freesbee.persistenza.hibernate;

import com.google.inject.Singleton;
import it.unibas.icar.freesbee.modello.Configurazione;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOConfigurazione;
import java.util.List;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.LoggerFactory;

@Singleton
public class DAOConfigurazioneHibernate extends DAOGenericoHibernate<Configurazione> implements IDAOConfigurazione {

//    private static Log logger = LogFactory.getLog(DAOConfigurazioneHibernate.class);
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(DAOConfigurazioneHibernate.class.getName());

    public DAOConfigurazioneHibernate() {
        super(Configurazione.class);
    }

    public Configurazione getConfigurazione() throws DAOException {
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
}
