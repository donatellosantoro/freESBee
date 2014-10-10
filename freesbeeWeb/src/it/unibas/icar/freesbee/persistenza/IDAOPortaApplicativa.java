package it.unibas.icar.freesbee.persistenza;

import it.unibas.icar.freesbee.modello.Azione;
import it.unibas.icar.freesbee.modello.PortaApplicativa;
import it.unibas.icar.freesbee.modello.Servizio;
import it.unibas.icar.freesbee.modello.Utente;

public interface IDAOPortaApplicativa  extends IDAOGenerico<PortaApplicativa> {
    
    public PortaApplicativa findByNome(Utente utente, String nome) throws DAOException;
    
    public PortaApplicativa findByServizio(Utente utente, Servizio servizio, Azione azione) throws DAOException;

    public PortaApplicativa findByServizio(Utente utente,
                                        String stringaTipoServizio, String stringaServizio,
                                        String stringaTipoErogatore, String stringaErogatore,
                                        String stringaAzione) throws DAOException;
            
}



