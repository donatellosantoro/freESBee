package it.unibas.icar.freesbee.test.persistenza.mock;

import it.unibas.icar.freesbee.modello.AccordoServizio;
import it.unibas.icar.freesbee.modello.PortaDelegata;
import it.unibas.icar.freesbee.modello.ProfiloEGov;
import it.unibas.icar.freesbee.modello.Servizio;
import it.unibas.icar.freesbee.modello.Soggetto;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOPortaDelegata;
import java.util.ArrayList;
import java.util.List;

public class DAOPortaDelegataMockTest implements IDAOPortaDelegata {

    public PortaDelegata findByNome(String nome) throws DAOException {
        PortaDelegata portaDelegata = new PortaDelegata();
        portaDelegata.setNome(nome);
        portaDelegata.setAzione(null);
        portaDelegata.setDescrizione("");

        Soggetto soggettoFruitore = new Soggetto();
        soggettoFruitore.setTipo("TEST");
        soggettoFruitore.setNome("TEST_FRUITORE");
        
        Soggetto soggettoErogatore = new Soggetto();
        soggettoErogatore.setTipo("TEST");
        soggettoErogatore.setNome("TEST_EROGATORE");
        
        portaDelegata.setSoggettoFruitore(soggettoFruitore);
        
        Servizio servizio = new Servizio();
        servizio.setTipo("TEST");
        servizio.setNome("TEST_SERVIZIO");
       
        List<Soggetto> listaFruitori = new ArrayList<Soggetto>();
        listaFruitori.add(soggettoFruitore);
        
        servizio.setFruitori(listaFruitori);
        servizio.setErogatore(soggettoErogatore);
        
        AccordoServizio accordoServizio = new AccordoServizio();
        accordoServizio.setProfiloCollaborazione(AccordoServizio.PROFILO_SINCRONO);
        accordoServizio.setProfiloEGov(new ProfiloEGov());
        servizio.setAccordoServizio(accordoServizio);
        
        portaDelegata.setServizio(servizio);

        return portaDelegata;
    }

    public List<PortaDelegata> findBySoggetto(Soggetto soggetto) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public PortaDelegata findById(Long id, boolean lock) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<PortaDelegata> findAll() throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<PortaDelegata> findAll(int offset, int limite) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public PortaDelegata makePersistent(PortaDelegata entity) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void makeTransient(PortaDelegata entity) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void lock(PortaDelegata entity) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void removeByServizio(Servizio servizio) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
