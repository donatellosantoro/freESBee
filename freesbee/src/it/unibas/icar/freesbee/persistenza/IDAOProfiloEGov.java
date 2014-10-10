package it.unibas.icar.freesbee.persistenza;

import com.google.inject.ImplementedBy;
import it.unibas.icar.freesbee.modello.ProfiloEGov;
import it.unibas.icar.freesbee.persistenza.hibernate.DAOProfiloEGovHibernate;

@ImplementedBy(DAOProfiloEGovHibernate.class)
public interface IDAOProfiloEGov extends IDAOGenerico<ProfiloEGov> {
            
}



