package it.unibas.icar.freesbee.persistenza.hibernate;

import com.google.inject.Singleton;
import it.unibas.icar.freesbee.modello.Sincronizzazione;
import it.unibas.icar.freesbee.persistenza.IDAOSincronizzazione;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Singleton
public class DAOSincronizzazioneHibernate extends DAOGenericoHibernate<Sincronizzazione> implements IDAOSincronizzazione {

    private static Log logger = LogFactory.getLog(DAOSincronizzazioneHibernate.class);

    public DAOSincronizzazioneHibernate() {
        super(Sincronizzazione.class);
    }
}
