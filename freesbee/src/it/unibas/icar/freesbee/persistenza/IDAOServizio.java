package it.unibas.icar.freesbee.persistenza;

import com.google.inject.ImplementedBy;
import it.unibas.icar.freesbee.modello.AccordoServizio;
import it.unibas.icar.freesbee.modello.Servizio;
import it.unibas.icar.freesbee.modello.Soggetto;
import it.unibas.icar.freesbee.persistenza.hibernate.DAOServizioHibernate;
import java.util.Date;
import java.util.List;

@ImplementedBy(DAOServizioHibernate.class)
public interface IDAOServizio  extends IDAOGenerico<Servizio> {

    public Servizio findByAccordoErogatore(AccordoServizio accordoServizio, Soggetto erogatore) throws DAOException;
    
    public Servizio findByNome(String nome,String tipo,long erogatoreId) throws DAOException;
    
    public Servizio findByNome(String nome,String tipo,Soggetto erogatore) throws DAOException;
    
    public Servizio findByNome(String nome, String tipo, Soggetto erogatore,boolean correlato) throws DAOException;
    
    public List<Servizio> findByNome(String nome, String tipo) throws DAOException;

    public Servizio findCorrelato(AccordoServizio accordo, Soggetto fruitore) throws DAOException;
    
    public List<Servizio> findAllCorrelati() throws DAOException;
    
    public List<Servizio> findAllServizi() throws DAOException;
    
    public List<Servizio> findAllFilter(Date maxDate, Date minDate,String nome, String tipo, Soggetto erogatore) throws DAOException;
    
    public List<Servizio> findAllCorrelatiFilter(Date maxDate, Date minDate,String nome, String tipo, Soggetto erogatore) throws DAOException;
            
}



