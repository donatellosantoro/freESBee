package it.unibas.icar.freesbee.persistenza;

import com.google.inject.ImplementedBy;
import it.unibas.icar.freesbee.modello.ServizioApplicativo;
import it.unibas.icar.freesbee.persistenza.hibernate.DAOServizioApplicativoHibernate;
import java.util.List;

@ImplementedBy(DAOServizioApplicativoHibernate.class)
public interface IDAOServizioApplicativo extends IDAOGenerico<ServizioApplicativo> {

    public ServizioApplicativo findByNome(String nome) throws DAOException;

    public ServizioApplicativo findByNome(String nome, String connettore) throws DAOException;
    
    public ServizioApplicativo findByNome(String nome, String connettore, String descrizione) throws DAOException;

    public List<ServizioApplicativo> findAllNoLazy() throws DAOException;

    }



