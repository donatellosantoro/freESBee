package it.unibas.freesbee.ge.ws.web;

import it.unibas.freesbee.ge.modello.CategoriaEventiEsterna;
import it.unibas.freesbee.ge.persistenza.DAOException;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiEsterna;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiInterna;
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
public class WSCategoriaEventiEsternaImpl implements IWSCategoriaEventiEsterna {

    private static Log logger = LogFactory.getLog(WSCategoriaEventiEsternaImpl.class);
    private IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna;
    private IDAOCategoriaEventiInterna daoCategoriaEventiInterna;

    public WSCategoriaEventiEsternaImpl(IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna, IDAOCategoriaEventiInterna daoCategoriaEventiInterna) {
        this.daoCategoriaEventiEsterna = daoCategoriaEventiEsterna;
        this.daoCategoriaEventiInterna = daoCategoriaEventiInterna;
    }

    public void addCategoriaEventiEsterna(@WebParam(name = "categoriaEventi") CategoriaEventiEsterna categoriaEventi) throws SOAPFault {
        logger.debug("Richiesta l'operazione: addCategoriaEventiEsterna");
        try {
      
            if ((daoCategoriaEventiEsterna.findByNome(categoriaEventi.getNome())) != null) {
                logger.error("La categoria di eventi esterna " + categoriaEventi.getNome() + " gia' esiste.");
                throw new SOAPFault("La categoria di eventi esterna " + categoriaEventi.getNome() + " gia' esiste.");
            }
            if ((daoCategoriaEventiInterna.findByNome(categoriaEventi.getNome())) != null) {
                logger.error("La categoria di eventi " + categoriaEventi.getNome() + " e' interna.");
                throw new SOAPFault("La categoria di eventi " + categoriaEventi.getNome() + " e' interna");
            }

            categoriaEventi.setAttiva(false);
            categoriaEventi.setInAttesa(true);
            daoCategoriaEventiEsterna.makePersistent(categoriaEventi);


        } catch (DAOException ex) {
            logger.error("Errore durante l'accesso al database " + ex.getMessage());
            throw new SOAPFault("Errore durante l'accesso al database");
        }
    }

    public List<CategoriaEventiEsterna> getListaCategoriaEventiEsterne() throws SOAPFault {
        logger.debug("Richiesta l'operazione: getListaCategoriaEventiEsterne");
        try {
            List<CategoriaEventiEsterna> lista = daoCategoriaEventiEsterna.findAll();
            return lista;
        } catch (DAOException ex) {
            logger.error("Errore durante l'accesso al database " + ex.getMessage());
            throw new SOAPFault("Errore durante l'accesso al database");
        }
    }

    public void updateCategoriaEventiEsterna(@WebParam(name = "categoriaEventi") CategoriaEventiEsterna categoriaEventi) throws SOAPFault {
        logger.debug("Richiesta l'operazione: updateCategoriaEventiEsterna");
        try {
            CategoriaEventiEsterna categoriaEventiInternaDB = daoCategoriaEventiEsterna.findByNome(categoriaEventi.getNome());
            if (categoriaEventiInternaDB == null) {
                logger.error("La categoria di eventi esterna " + categoriaEventi.getNome() + " non esiste.");
                throw new SOAPFault("La categoria di eventi esterna " + categoriaEventi.getNome() + " non esiste.");
            }
            categoriaEventiInternaDB.setAttiva(categoriaEventi.isAttiva());
            daoCategoriaEventiEsterna.makePersistent(categoriaEventiInternaDB);
        } catch (DAOException ex) {
            logger.error("Errore durante l'accesso al database " + ex.getMessage());
            throw new SOAPFault("Errore durante l'accesso al database");
        }
    }

    public CategoriaEventiEsterna getCategoriaEventiEsterna(String categoriaEventi) throws SOAPFault {
        logger.debug("Richiesta l'operazione: getCategoriaEventiEsterna");
        try {
            CategoriaEventiEsterna categoria = daoCategoriaEventiEsterna.findByNome(categoriaEventi);
            return categoria;
        } catch (DAOException ex) {
            logger.error("Errore durante l'accesso al database " + ex.getMessage());
            throw new SOAPFault("Errore durante l'accesso al database");
        }
    }
}
