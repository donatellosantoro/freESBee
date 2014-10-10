package it.unibas.freesbee.ge.persistenza.hibernate;

import it.unibas.freesbee.ge.modello.CategoriaEventiInterna;
import it.unibas.freesbee.ge.modello.PubblicatoreInterno;
import it.unibas.freesbee.ge.modello.Sottoscrittore;
import it.unibas.freesbee.ge.modello.SottoscrizioneInterna;
import it.unibas.freesbee.ge.persistenza.DAOException;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiInterna;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;

public class DAOCategoriaEventiInternaHibernate extends DAOGenericoHibernate<CategoriaEventiInterna> implements IDAOCategoriaEventiInterna {

    private Log logger = LogFactory.getLog(this.getClass());

    public DAOCategoriaEventiInternaHibernate() {
        super(CategoriaEventiInterna.class);
    }

    public CategoriaEventiInterna findByNome(String nome) throws DAOException {
        logger.debug("Richiesta categoria eventi " + nome);
        List<CategoriaEventiInterna> lista = super.findByCriteria(Restrictions.eq("nome", nome));
        if (lista.size() != 0) {
            Hibernate.initialize(lista.get(0));
            return lista.get(0);
        }
        return null;
    }

    public boolean existsPubblicatoreInterno(CategoriaEventiInterna categoriaEventiInterna, PubblicatoreInterno pubblicatoreInterno) {
        logger.debug("Verifica della esistenza del pubblicatore " + pubblicatoreInterno + " per la categoria eventi " + categoriaEventiInterna);
        for (PubblicatoreInterno pubblicatore : categoriaEventiInterna.getListaPubblicatori()) {
            if (pubblicatoreInterno.compareTo(pubblicatore) == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean existsSottoscrittore(CategoriaEventiInterna categoriaEventiInterna, Sottoscrittore sottoscrittore) {
        logger.debug("Verifica della esistenza del sottoscrittore " + sottoscrittore + " per la categoria eventi " + categoriaEventiInterna);
        for (SottoscrizioneInterna sottoscrizione : categoriaEventiInterna.getListaSottoscrizioni()) {
            Hibernate.initialize(sottoscrizione);
            if (sottoscrizione.getSottoscrittore().compareTo(sottoscrittore) == 0) {
                logger.debug(true);
                return true;
            }
        }
        return false;
    }

    public SottoscrizioneInterna findByCategoriaEventiSottoscrittore(CategoriaEventiInterna categoriaEventiInterna, Sottoscrittore sottoscrittore) throws DAOException {
        logger.debug("Richiesta sottoscrizione del sottoscrittore " + sottoscrittore + " per la categoria eventi " + categoriaEventiInterna);
        List<SottoscrizioneInterna> lista = categoriaEventiInterna.getListaSottoscrizioni();
        for (SottoscrizioneInterna sottoscrizione : lista) {
            Hibernate.initialize(sottoscrizione);
            if (sottoscrizione.getSottoscrittore().compareTo(sottoscrittore) == 0) {
                return sottoscrizione;
            }
        }
        return null;
    }
}
