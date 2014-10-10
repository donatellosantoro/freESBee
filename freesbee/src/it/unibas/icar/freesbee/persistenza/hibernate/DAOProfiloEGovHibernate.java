package it.unibas.icar.freesbee.persistenza.hibernate;

import com.google.inject.Singleton;
import it.unibas.icar.freesbee.modello.ProfiloEGov;
import it.unibas.icar.freesbee.persistenza.IDAOProfiloEGov;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Singleton
public class DAOProfiloEGovHibernate extends DAOGenericoHibernate<ProfiloEGov> implements IDAOProfiloEGov {
    
    private static Log logger = LogFactory.getLog(DAOProfiloEGovHibernate.class);
    
    public DAOProfiloEGovHibernate() {
        super(ProfiloEGov.class);
    }
}
