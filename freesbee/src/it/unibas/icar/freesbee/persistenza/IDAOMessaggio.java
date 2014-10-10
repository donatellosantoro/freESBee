package it.unibas.icar.freesbee.persistenza;

import com.google.inject.ImplementedBy;
import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.persistenza.hibernate.DAOMessaggioHibernate;

@ImplementedBy(DAOMessaggioHibernate.class)
public interface IDAOMessaggio extends IDAOGenerico<Messaggio> {

    public Messaggio findByIdMessaggio(String idMessaggio, String tipo) throws DAOException;
    
    public Messaggio findByIDSil(String idSil, String tipo) throws DAOException;

    public Messaggio findByIDSilRelatesTo(String idSilRelatesTo, String tipo) throws DAOException;
}



