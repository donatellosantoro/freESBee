package it.unibas.icar.freesbee.persistenza;

import com.google.inject.ImplementedBy;
import it.unibas.icar.freesbee.modello.Azione;
import it.unibas.icar.freesbee.modello.PortaApplicativa;
import it.unibas.icar.freesbee.modello.Servizio;
import it.unibas.icar.freesbee.modello.ServizioApplicativo;
import it.unibas.icar.freesbee.persistenza.hibernate.DAOPortaApplicativaHibernate;
import java.util.List;

@ImplementedBy(DAOPortaApplicativaHibernate.class)
public interface IDAOPortaApplicativa  extends IDAOGenerico<PortaApplicativa> {
    
    public PortaApplicativa findByNome(String nome) throws DAOException;

    public PortaApplicativa findByNomeServizio(String nomeServizio, String tipoServizio, String nomeErogatore, String tipoErogatore, String azione) throws DAOException;
    
    public PortaApplicativa findByServizio(Servizio servizio,Azione azione) throws DAOException;
    
    public List<PortaApplicativa> findByServizioApplicativo(ServizioApplicativo servizioApplicativo) throws DAOException;
}



