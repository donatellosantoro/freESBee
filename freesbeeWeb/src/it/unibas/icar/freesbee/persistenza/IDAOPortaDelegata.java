package it.unibas.icar.freesbee.persistenza;

import it.unibas.icar.freesbee.modello.PortaDelegata;
import it.unibas.icar.freesbee.modello.Soggetto;
import it.unibas.icar.freesbee.modello.Utente;
import java.util.List;

public interface IDAOPortaDelegata  extends IDAOGenerico<PortaDelegata> {
    
    public PortaDelegata findByNome(Utente utente, String nome) throws DAOException; 
    
    public List<PortaDelegata> findBySoggetto(Utente utente, Soggetto soggetto) throws DAOException;
            
}



