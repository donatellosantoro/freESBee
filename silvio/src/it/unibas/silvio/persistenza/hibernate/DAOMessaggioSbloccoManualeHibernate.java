package it.unibas.silvio.persistenza.hibernate;

import it.unibas.silvio.modello.MessaggioSbloccoManuale;
import it.unibas.silvio.persistenza.DAOException;
import it.unibas.silvio.persistenza.IDAOMessaggioSbloccoManuale;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;

public class DAOMessaggioSbloccoManualeHibernate extends DAOGenericoHibernate<MessaggioSbloccoManuale> implements IDAOMessaggioSbloccoManuale{
    
    private Log logger = LogFactory.getLog(this.getClass());

    public DAOMessaggioSbloccoManualeHibernate() {
        super(MessaggioSbloccoManuale.class);
    }
        
    public MessaggioSbloccoManuale findById(long id) throws DAOException {
        List<MessaggioSbloccoManuale> listaMessaggi = null;
        try {
            listaMessaggi = super.findByCriteria(Restrictions.eq("id", id));            
            if (listaMessaggi.size() != 0) {
                Hibernate.initialize(listaMessaggi.get(0));
                return listaMessaggi.get(0);
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return null;
    }

}
