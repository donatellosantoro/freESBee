package it.unibas.icar.freesbee.persistenza;

import com.google.inject.ImplementedBy;
import it.unibas.icar.freesbee.modello.Azione;
import it.unibas.icar.freesbee.persistenza.hibernate.DAOAzioneHibernate;

@ImplementedBy(DAOAzioneHibernate.class)
public interface IDAOAzione extends IDAOGenerico<Azione> {
            
    public Azione findByNome(String nome, long accordoServizioId) throws DAOException;
    
}



