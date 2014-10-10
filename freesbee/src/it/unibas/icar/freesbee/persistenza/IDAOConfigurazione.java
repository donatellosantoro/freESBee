package it.unibas.icar.freesbee.persistenza;

import com.google.inject.ImplementedBy;
import it.unibas.icar.freesbee.modello.Configurazione;
import it.unibas.icar.freesbee.persistenza.hibernate.DAOConfigurazioneHibernate;

@ImplementedBy(DAOConfigurazioneHibernate.class)
public interface IDAOConfigurazione  extends IDAOGenerico<Configurazione> {
    
    public Configurazione getConfigurazione() throws DAOException;
    
}