package it.unibas.silvio.persistenza;

import it.unibas.silvio.modello.AccordoDiCollaborazione;
import java.util.List;

public interface IDAOAccordoDiCollaborazione extends IDAOGenerico<AccordoDiCollaborazione>{
    
    public AccordoDiCollaborazione findByNome(String nome) throws DAOException;
    
    public List<AccordoDiCollaborazione> findAll() throws DAOException;

}
