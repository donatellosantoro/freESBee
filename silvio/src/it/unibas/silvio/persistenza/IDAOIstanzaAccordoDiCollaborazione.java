package it.unibas.silvio.persistenza;

import it.unibas.silvio.modello.IstanzaAccordoDiCollaborazione;
import java.util.List;

public interface IDAOIstanzaAccordoDiCollaborazione extends IDAOGenerico<IstanzaAccordoDiCollaborazione>{
    
    public IstanzaAccordoDiCollaborazione findByNome(String nome) throws DAOException;
    
    public List<IstanzaAccordoDiCollaborazione> findAll() throws DAOException;
    
    public List<IstanzaAccordoDiCollaborazione> findAllFruitori() throws DAOException;

}
