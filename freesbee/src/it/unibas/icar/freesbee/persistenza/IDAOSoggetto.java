package it.unibas.icar.freesbee.persistenza;

import com.google.inject.ImplementedBy;
import it.unibas.icar.freesbee.modello.Soggetto;
import it.unibas.icar.freesbee.persistenza.hibernate.DAOSoggettoHibernate;
import java.util.Date;
import java.util.List;

@ImplementedBy(DAOSoggettoHibernate.class)
public interface IDAOSoggetto  extends IDAOGenerico<Soggetto> {
    
    public Soggetto findByNome(String nome, String tipo) throws DAOException; 
    
    public List<Soggetto> findAllFilter(Date maxDate, Date minDate,String nome, String tipo) throws DAOException; 
            
}



