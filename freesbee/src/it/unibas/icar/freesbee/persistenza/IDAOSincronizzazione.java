package it.unibas.icar.freesbee.persistenza;

import com.google.inject.ImplementedBy;
import it.unibas.icar.freesbee.modello.Sincronizzazione;
import it.unibas.icar.freesbee.persistenza.hibernate.DAOSincronizzazioneHibernate;

@ImplementedBy(DAOSincronizzazioneHibernate.class)
public interface IDAOSincronizzazione  extends IDAOGenerico<Sincronizzazione> {
    
}