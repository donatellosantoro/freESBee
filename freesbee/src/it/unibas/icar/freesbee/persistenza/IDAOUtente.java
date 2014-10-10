package it.unibas.icar.freesbee.persistenza;

import com.google.inject.ImplementedBy;
import it.unibas.icar.freesbee.modello.Utente;
import it.unibas.icar.freesbee.persistenza.hibernate.DAOUtenteHibernate;

@ImplementedBy(DAOUtenteHibernate.class)
public interface IDAOUtente  extends IDAOGenerico<Utente> {
    
    public Utente findByNomeUtente(String nomeUtente) throws DAOException; 
            
}



