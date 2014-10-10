package it.unibas.freesbee.ge.persistenza.facade;

import it.unibas.freesbee.ge.modello.CategoriaEventiInterna;
import it.unibas.freesbee.ge.persistenza.DAOException;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiInterna;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCategoriaEventiInternaHibernate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DAOCategoriaEventiInternaFacade {

    private DAOCategoriaEventiInternaFacade() {
    }
    private static Log logger = LogFactory.getLog(DAOCategoriaEventiInternaFacade.class);
    private static IDAOCategoriaEventiInterna daoCategoriaEventiInterna = new DAOCategoriaEventiInternaHibernate();

    public static CategoriaEventiInterna verificaEsistenzaCategoriaEventiInternaAttavia(String nome) throws DAOException {
        CategoriaEventiInterna categoriaEventiInterna = daoCategoriaEventiInterna.findByNome(nome);
        if (categoriaEventiInterna == null) {
            logger.error("La categoria eventi interna " + nome + " non e' presente nel database");
            throw new DAOException("La categoria eventi interna " + nome + " non e' presente nel database");
        }

        if (!categoriaEventiInterna.isAttiva()) {
            logger.error("La categoria eventi interna " + nome + " non e' attiva");
            throw new DAOException("La categoria eventi interna " + nome + " non e' attiva");
        }

        return categoriaEventiInterna;
    }
}
