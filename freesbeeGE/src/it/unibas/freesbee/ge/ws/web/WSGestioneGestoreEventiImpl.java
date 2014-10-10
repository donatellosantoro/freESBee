package it.unibas.freesbee.ge.ws.web;

import it.unibas.freesbee.ge.modello.CategoriaEventiEsterna;
import it.unibas.freesbee.ge.modello.GestoreEventi;
import it.unibas.freesbee.ge.modello.ICategoriaEventi;
import it.unibas.freesbee.ge.modello.PubblicatoreEsterno;
import it.unibas.freesbee.ge.modello.Sottoscrittore;
import it.unibas.freesbee.ge.persistenza.DAOException;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiEsterna;
import it.unibas.freesbee.ge.persistenza.IDAOGestoreEventi;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreEsterno;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrittore;
import it.unibas.freesbee.ge.persistenza.facade.DAOGestoreEventiFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOPubblicatoreEsternoFacade;
import it.unibas.freesbee.ge.ws.gestoreeventi.SOAPFault;
import java.util.List;
import javax.jws.WebService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.interceptor.InInterceptors;
import org.apache.cxf.interceptor.OutFaultInterceptors;

@WebService(targetNamespace = "http://ge.freesbee.unibas.it/")
@InInterceptors(interceptors = {"it.unibas.freesbee.ge.persistenza.hibernate.HibernateInterceptor", "it.unibas.freesbee.ge.ws.web.WSSecurityInterceptor"})
@OutFaultInterceptors(interceptors = {"it.unibas.freesbee.ge.persistenza.hibernate.HibernateInterceptorFault"})
public class WSGestioneGestoreEventiImpl implements IWSGestioneGestoreEventi {

    private static Log logger = LogFactory.getLog(WSGestioneGestoreEventiImpl.class);
    private IDAOGestoreEventi daoGestoreEventi;
    private IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna;
    private IDAOPubblicatoreEsterno daoPubblicatoreEsterno;
    private IDAOSottoscrittore daoSottoscrittore;

    public WSGestioneGestoreEventiImpl(IDAOGestoreEventi daoGestoreEventi, IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna, IDAOPubblicatoreEsterno daoPubblicatoreEsterno, IDAOSottoscrittore daoSottoscrittore) {
        this.daoGestoreEventi = daoGestoreEventi;
        this.daoCategoriaEventiEsterna = daoCategoriaEventiEsterna;
        this.daoPubblicatoreEsterno = daoPubblicatoreEsterno;
        this.daoSottoscrittore = daoSottoscrittore;
    }

    public void addGestoreEventi(GestoreEventi gestoreEventi) throws SOAPFault {
        logger.debug("Richiesta l'operazione: addGestoreEventi");

        try {
            DAOGestoreEventiFacade.verificaNonEsistenzaGestoreEventi(gestoreEventi.getTipo(), gestoreEventi.getNome());

            CategoriaEventiEsterna categoriaEventi = daoCategoriaEventiEsterna.findByNome(ICategoriaEventi.GE_CONTROL_PROTOCOL);
            if (categoriaEventi == null) {
                logger.error("La categoria di eventi " + categoriaEventi.getNome() + " non esiste.");
                throw new SOAPFault("La categoria di eventi " + categoriaEventi.getNome() + " non esiste.");
            }
            daoGestoreEventi.makePersistent(gestoreEventi);
            PubblicatoreEsterno pubblicatore = new PubblicatoreEsterno(gestoreEventi.getTipo(), gestoreEventi.getNome());
            pubblicatore.setDescrizione(gestoreEventi.getDescrizione());
            DAOPubblicatoreEsternoFacade.aggiungiPubblicatoreEsterno(categoriaEventi, pubblicatore);

        } catch (DAOException ex) {
            logger.error("Errore durante l'accesso al database " + ex.getMessage());
            throw new SOAPFault("Errore durante l'accesso al database");
        }
    }

    public List<GestoreEventi> getListaGestoriEventi() throws SOAPFault {
        logger.debug("Richiesta l'operazione: getListaGestoriEventi");
        try {
            List<GestoreEventi> lista = daoGestoreEventi.findAll();
            return lista;
        } catch (DAOException ex) {
            logger.error("Errore durante l'accesso al database " + ex.getMessage());
            throw new SOAPFault("Errore durante l'accesso al database");
        }
    }

    public void updateGestoriEventi(GestoreEventi gestoreEventi) throws SOAPFault {
        logger.debug("Richiesta l'operazione: updateGestoriEventi");
        try {

            GestoreEventi ge = DAOGestoreEventiFacade.getGE();

            if (ge.compareTo(gestoreEventi) == 0) {
                logger.error("Il gesotre eventi " + gestoreEventi + " deve essere modificato dalla sezione configurazione.");
                throw new SOAPFault("Il gesotre eventi " + gestoreEventi + " deve essere modificato dalla sezione configurazione.");
            }

            DAOGestoreEventiFacade.verificaEsistenzaGestoreEventi(gestoreEventi.getTipo(), gestoreEventi.getNome());

            CategoriaEventiEsterna categoriaEventi = daoCategoriaEventiEsterna.findByNome(ICategoriaEventi.GE_CONTROL_PROTOCOL);
            if (categoriaEventi == null) {
                logger.error("La categoria di eventi " + categoriaEventi.getNome() + " non esiste.");
                throw new SOAPFault("La categoria di eventi " + categoriaEventi.getNome() + " non esiste.");
            }

            GestoreEventi gestoreEventiDB = daoGestoreEventi.findByTipoNome(gestoreEventi.getTipo(), gestoreEventi.getNome());

            PubblicatoreEsterno pubblicatore = daoPubblicatoreEsterno.findByTipoNome(gestoreEventi.getTipo(), gestoreEventi.getNome());

            Sottoscrittore sottoscrittore = daoSottoscrittore.findByTipoNome(gestoreEventi.getTipo(), gestoreEventi.getNome());

            gestoreEventiDB.setTipo(gestoreEventi.getTipo());
            gestoreEventiDB.setNome(gestoreEventi.getNome());
            gestoreEventiDB.setDescrizione(gestoreEventi.getDescrizione());
            daoGestoreEventi.makePersistent(gestoreEventi);

            pubblicatore.setTipo(gestoreEventi.getTipo());
            pubblicatore.setNome(gestoreEventi.getNome());
            pubblicatore.setDescrizione(gestoreEventi.getDescrizione());
            daoPubblicatoreEsterno.makePersistent(pubblicatore);

            if (sottoscrittore != null) {
                sottoscrittore.setTipo(gestoreEventi.getTipo());
                sottoscrittore.setNome(gestoreEventi.getNome());
                sottoscrittore.setDescrizione(gestoreEventi.getDescrizione());
                daoSottoscrittore.makePersistent(sottoscrittore);
            }

        } catch (DAOException ex) {
            logger.error("Errore durante l'accesso al database " + ex.getMessage());
            throw new SOAPFault("Errore durante l'accesso al database");
        }
    }
}
