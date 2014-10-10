package it.unibas.freesbee.ge.ws.web;

import it.unibas.freesbee.ge.modello.CategoriaEventiInterna;
import it.unibas.freesbee.ge.modello.GestoreEventi;
import it.unibas.freesbee.ge.modello.PubblicatoreInterno;
import it.unibas.freesbee.ge.persistenza.DAOException;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiEsterna;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiInterna;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreInterno;
import it.unibas.freesbee.ge.persistenza.facade.DAOGestoreEventiFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOPubblicatoreInternoFacade;
import it.unibas.freesbee.ge.ws.gestoreeventi.SOAPFault;
import java.util.List;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.interceptor.InInterceptors;
import org.apache.cxf.interceptor.OutFaultInterceptors;

@WebService(targetNamespace = "http://ge.freesbee.unibas.it/")
@InInterceptors(interceptors = {"it.unibas.freesbee.ge.persistenza.hibernate.HibernateInterceptor", "it.unibas.freesbee.ge.ws.web.WSSecurityInterceptor"})
@OutFaultInterceptors(interceptors = {"it.unibas.freesbee.ge.persistenza.hibernate.HibernateInterceptorFault"})
public class WSCategoriaEventiInternaImpl implements IWSCategoriaEventiInterna {

    private static Log logger = LogFactory.getLog(WSCategoriaEventiInternaImpl.class);
    private IDAOCategoriaEventiInterna daoCategoriaEventiInterna;
    private IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna;
    private IDAOPubblicatoreInterno daoPubblicatoreInterno;

    public WSCategoriaEventiInternaImpl(IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna, IDAOCategoriaEventiInterna daoCategoriaEventiInterna, IDAOPubblicatoreInterno daoPubblicatoreInterno) {
        this.daoCategoriaEventiInterna = daoCategoriaEventiInterna;
        this.daoPubblicatoreInterno = daoPubblicatoreInterno;
        this.daoCategoriaEventiEsterna = daoCategoriaEventiEsterna;
    }

    public void addCategoriaEventiInterna(@WebParam(name = "categoriaEventi") CategoriaEventiInterna categoriaEventi) throws SOAPFault {
        logger.debug("Richiesta l'operazione: addCategoriaEventiInterna");

        try {
            GestoreEventi ge = DAOGestoreEventiFacade.getGE();

            if ((daoCategoriaEventiInterna.findByNome(categoriaEventi.getNome())) != null) {
                logger.error("La categoria di eventi interna " + categoriaEventi.getNome() + " gia' esiste.");
                throw new SOAPFault("La categoria di eventi interna " + categoriaEventi.getNome() + " gia' esiste.");

            }
            if ((daoCategoriaEventiEsterna.findByNome(categoriaEventi.getNome())) != null) {
                logger.error("La categoria di eventi " + categoriaEventi.getNome() + " e' esterna.");
                throw new SOAPFault("La categoria di eventi " + categoriaEventi.getNome() + " e' esterna.");

            }
            daoCategoriaEventiInterna.makePersistent(categoriaEventi);
            PubblicatoreInterno pubblicatoreInterno = daoPubblicatoreInterno.findByTipoNome(ge.getTipo(), ge.getNome());
            if (pubblicatoreInterno == null) {
                logger.error("Il gestore eventi " + ge + "non risulta pubblicatore");
                throw new SOAPFault("Il gestore eventi " + ge + "non risulta pubblicatore");
            }
            DAOPubblicatoreInternoFacade.aggiungiPubblicatoreInterno(categoriaEventi, pubblicatoreInterno);


        } catch (DAOException ex) {
            logger.error("Errore durante l'accesso al database " + ex.getMessage());
            throw new SOAPFault("Errore durante l'accesso al database");
        }
    }

    public List<CategoriaEventiInterna> getListaCategoriaEventiInterne() throws SOAPFault {
        logger.debug("Richiesta l'operazione: getListaCategoriaEventiInterne");
        try {
            List<CategoriaEventiInterna> lista = daoCategoriaEventiInterna.findAll();
            return lista;
        } catch (DAOException ex) {
            logger.error("Errore durante l'accesso al database " + ex.getMessage());
            throw new SOAPFault("Errore durante l'accesso al database");
        }
    }

    public void updateCategoriaEventiInterna(@WebParam(name = "categoriaEventi") CategoriaEventiInterna categoriaEventi) throws SOAPFault {
        logger.debug("Richiesta l'operazione: updateCategoriaEventiInterna");
        try {
            CategoriaEventiInterna categoriaEventiInternaDB = daoCategoriaEventiInterna.findByNome(categoriaEventi.getNome());
            if (categoriaEventiInternaDB == null) {
                logger.error("La categoria di eventi interna " + categoriaEventi.getNome() + " non esiste.");
                throw new SOAPFault("La categoria di eventi interna " + categoriaEventi.getNome() + " non esiste.");
            }
            categoriaEventiInternaDB.setAttiva(categoriaEventi.isAttiva());
            daoCategoriaEventiInterna.makePersistent(categoriaEventiInternaDB);
        } catch (DAOException ex) {
            logger.error("Errore durante l'accesso al database " + ex.getMessage());
            throw new SOAPFault("Errore durante l'accesso al database");
        }
    }

    public CategoriaEventiInterna getCategoriaEventiInterna(String categoriaEventi) throws SOAPFault {
        logger.debug("Richiesta l'operazione: getCategoriaEventiInterna");
        try {
            CategoriaEventiInterna categoria = daoCategoriaEventiInterna.findByNome(categoriaEventi);
            return categoria;
        } catch (DAOException ex) {
            logger.error("Errore durante l'accesso al database " + ex.getMessage());
            throw new SOAPFault("Errore durante l'accesso al database");
        }
    }
}
