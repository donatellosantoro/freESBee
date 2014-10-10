package it.unibas.icar.freesbee.persistenza;

import com.google.inject.ImplementedBy;
import it.unibas.icar.freesbee.modello.AccordoServizio;
import it.unibas.icar.freesbee.persistenza.hibernate.DAOAccordoServizioHibernate;
import java.util.Date;
import java.util.List;

@ImplementedBy(DAOAccordoServizioHibernate.class)
public interface IDAOAccordoServizio  extends IDAOGenerico<AccordoServizio> {
    
    public AccordoServizio findByNome(String nome) throws DAOException; 
    
    public List<AccordoServizio> findAllFilter(Date maxDate, Date minDate,String nome) throws DAOException;
            
}



