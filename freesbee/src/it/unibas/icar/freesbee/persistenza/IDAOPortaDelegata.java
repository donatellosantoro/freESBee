package it.unibas.icar.freesbee.persistenza;

import com.google.inject.ImplementedBy;
import it.unibas.icar.freesbee.modello.PortaDelegata;
import it.unibas.icar.freesbee.modello.Soggetto;
import it.unibas.icar.freesbee.persistenza.hibernate.DAOPortaDelegataHibernate;
import java.util.List;

@ImplementedBy(DAOPortaDelegataHibernate.class)
public interface IDAOPortaDelegata  extends IDAOGenerico<PortaDelegata> {
    
    public PortaDelegata findByNome(String nome) throws DAOException; 
    
    public List<PortaDelegata> findBySoggetto(Soggetto soggetto) throws DAOException;
            
}



