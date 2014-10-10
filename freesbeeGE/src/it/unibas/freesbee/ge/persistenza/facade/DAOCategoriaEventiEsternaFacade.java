package it.unibas.freesbee.ge.persistenza.facade;

import it.unibas.freesbee.ge.modello.CategoriaEventiEsterna;
import it.unibas.freesbee.ge.modello.PubblicatoreEsterno;
import it.unibas.freesbee.ge.persistenza.DAOException;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiEsterna;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCategoriaEventiEsternaHibernate;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DAOCategoriaEventiEsternaFacade {

    private DAOCategoriaEventiEsternaFacade() {
    }
    private static Log logger = LogFactory.getLog(DAOCategoriaEventiEsternaFacade.class);
    private static IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna = new DAOCategoriaEventiEsternaHibernate();

    public static CategoriaEventiEsterna verificaEsistenzaCategoriaEventiEsternaAttavia(String nome) throws DAOException {
        CategoriaEventiEsterna categoriaEventiEsterna = daoCategoriaEventiEsterna.findByNome(nome);
        if (categoriaEventiEsterna == null) {
            logger.error("La categoria eventi esterna " + nome + " non e' presente nel database");
            throw new DAOException("La categoria eventi esterna " + nome + " non e' presente nel database");
        }

        if (!categoriaEventiEsterna.isAttiva()) {
            logger.error("La categoria eventi esterna " + nome + " non e' attiva");
            throw new DAOException("La categoria esterna esterna " + nome + " non e' attiva");
        }

        return categoriaEventiEsterna;
    }

    public static boolean isCategoriaEventiEsternaAttavia(String nome) throws DAOException {
        CategoriaEventiEsterna categoriaEventiEsterna = daoCategoriaEventiEsterna.findByNome(nome);
        if (categoriaEventiEsterna == null) {
            logger.error("La categoria eventi esterna " + nome + " non e' presente nel database");
            return false;
        }

        if (!categoriaEventiEsterna.isAttiva()) {
            logger.error("La categoria eventi esterna " + nome + " non e' attiva");
            return false;
        }

        return true;
    }

    public static PubblicatoreEsterno getGestoreEvetniPubblicatorePerCategoriaEventi(CategoriaEventiEsterna categoriaEventi) throws DAOException {
        List<PubblicatoreEsterno> listaPubblicatori = categoriaEventi.getListaPubblicatori();
        for (PubblicatoreEsterno pubb : listaPubblicatori) {
            if (DAOGestoreEventiFacade.isEsistenzaGestoreEventi(pubb.getTipo(), pubb.getNome())) {
                return pubb;
            }
        }
        return null;
//        throw new DAOException("Non esiste un gesotre eventi pubblicatore per la categoria di eventi " + categoriaEventi);
    }
}
