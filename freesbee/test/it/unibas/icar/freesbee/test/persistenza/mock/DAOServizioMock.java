package it.unibas.icar.freesbee.test.persistenza.mock;

import it.unibas.icar.freesbee.modello.AccordoServizio;
import it.unibas.icar.freesbee.modello.PortaApplicativa;
import it.unibas.icar.freesbee.modello.Servizio;
import it.unibas.icar.freesbee.modello.ServizioApplicativo;
import it.unibas.icar.freesbee.modello.Soggetto;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOServizio;
import java.util.Date;
import java.util.List;

public class DAOServizioMock implements IDAOServizio {

    public Servizio findByNome(String nome, String tipo, Soggetto erogatore) throws DAOException {
        Servizio servizio = new Servizio();
        servizio.setNome(nome);
        servizio.setTipo("TEST");

        PortaApplicativa portaApplicativa = new PortaApplicativa();
        portaApplicativa.setNome("portaApplicativa");
        portaApplicativa.setServizio(servizio);
        servizio.getPortaApplicativa().add(portaApplicativa);

        ServizioApplicativo servizioApplicativo = new ServizioApplicativo();
        servizioApplicativo.setNome("servizioApplicativo");
        servizioApplicativo.setConnettore("http://localhost:8080/axis2/services/AgenziaViaggi");

        portaApplicativa.setServizioApplicativo(servizioApplicativo);

        return servizio;
    }

    public Servizio findById(Long id, boolean lock) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Servizio> findAll() throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Servizio> findAll(int offset, int limite) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Servizio makePersistent(Servizio entity) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void makeTransient(Servizio entity) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void lock(Servizio entity) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Servizio findCorrelato(AccordoServizio accordo, Soggetto fruitore) throws DAOException {
        return null;
    }

    public Servizio findByAccordoErogatore(AccordoServizio accordoServizio, Soggetto erogatore) throws DAOException {
        return null;
    }
    
    public Servizio findByNome(String nome, String tipo, Soggetto erogatore, boolean correlato) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Servizio findByNome(String nome, String tipo, boolean correlato) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Servizio> findAllCorrelati() throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Servizio> findAllFilter(Date maxDate, Date minDate, String nome, String tipo, Soggetto erogatore) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Servizio> findAllCorrelatiFilter(Date maxDate, Date minDate, String nome, String tipo, Soggetto erogatore) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Servizio> findAllServizi() throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Servizio findByNome(String nome, String tipo, long erogatoreId) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Servizio> findByNome(String nome, String tipo) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
