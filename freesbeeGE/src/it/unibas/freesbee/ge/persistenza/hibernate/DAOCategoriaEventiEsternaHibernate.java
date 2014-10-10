package it.unibas.freesbee.ge.persistenza.hibernate;

import it.unibas.freesbee.ge.modello.CategoriaEventiEsterna;
import it.unibas.freesbee.ge.modello.FiltroPubblicatoreEsterno;
import it.unibas.freesbee.ge.modello.PubblicatoreEsterno;
import it.unibas.freesbee.ge.modello.Sottoscrittore;
import it.unibas.freesbee.ge.modello.SottoscrizioneEsterna;
import it.unibas.freesbee.ge.persistenza.DAOException;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiEsterna;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;

public class DAOCategoriaEventiEsternaHibernate extends DAOGenericoHibernate<CategoriaEventiEsterna> implements IDAOCategoriaEventiEsterna {

    private Log logger = LogFactory.getLog(this.getClass());

    public DAOCategoriaEventiEsternaHibernate() {
        super(CategoriaEventiEsterna.class);
    }

    public CategoriaEventiEsterna findByNome(String nome) throws DAOException {
        logger.debug("Richiesta categoria eventi " + nome);
        List<CategoriaEventiEsterna> lista = super.findByCriteria(Restrictions.eq("nome", nome));
        if (lista.size() != 0) {
            Hibernate.initialize(lista.get(0));
            return lista.get(0);
        }
        return null;
    }

    public boolean existsPubblicatoreEsterno(CategoriaEventiEsterna categoriaEventiEsterna, PubblicatoreEsterno pubblicatoreEsterno) {
        //CONFERMATO
        logger.debug("Verifica della esistenza del pubblicatore " + pubblicatoreEsterno + " per la categoria eventi " + categoriaEventiEsterna);
        Hibernate.initialize(categoriaEventiEsterna);
        for (PubblicatoreEsterno pubblicatore : categoriaEventiEsterna.getListaPubblicatori()) {
            if (pubblicatoreEsterno.compareTo(pubblicatore) == 0) {
                logger.debug(true);
                return true;
            }
        }
        logger.debug(false);
        return false;
    }

    public boolean existsSottoscrittore(CategoriaEventiEsterna categoriaEventiEsterna, Sottoscrittore sottoscrittore) throws DAOException {
        logger.debug("Verifica della esistenza del sottoscrittore " + sottoscrittore + " per la categoria eventi " + categoriaEventiEsterna);
        for (SottoscrizioneEsterna sottoscrizione : categoriaEventiEsterna.getListaSottoscrizioni()) {
            if (sottoscrizione.getSottoscrittore().compareTo(sottoscrittore) == 0) {
                logger.debug(true);
                return true;
            }
        }
        logger.debug(false);
        return false;
    }

    public SottoscrizioneEsterna findByCategoriaEventiSottoscrittore(CategoriaEventiEsterna categoriaEventiEsterna, Sottoscrittore sottoscrittore) throws DAOException {
        logger.debug("Richiesta sottoscrizione del sottoscrittore " + sottoscrittore + " per la categoria eventi " + categoriaEventiEsterna);
        List<SottoscrizioneEsterna> lista = categoriaEventiEsterna.getListaSottoscrizioni();
        for (SottoscrizioneEsterna sottoscrizione : lista) {
            Hibernate.initialize(sottoscrizione);
            if (sottoscrizione.getSottoscrittore().compareTo(sottoscrittore) == 0) {
                return sottoscrizione;
            }
        }
        return null;
    }

    public void removePubblicatoreEsternoPerCategoriaEventi(CategoriaEventiEsterna categoriaEventiEsterna, PubblicatoreEsterno pubblicatoreEsterno) throws DAOException {
        logger.debug("Verifica se il soggetto non e' più " + pubblicatoreEsterno + " per la categoria eventi " + categoriaEventiEsterna);
        int count = 0;
        for (SottoscrizioneEsterna sottoscrizioneEsterna : categoriaEventiEsterna.getListaSottoscrizioni()) {
            List<FiltroPubblicatoreEsterno> listaFiltroPubblicatore = sottoscrizioneEsterna.getListaFiltroPublicatore();
            for (FiltroPubblicatoreEsterno filtroPubblicatore : listaFiltroPubblicatore) {
                if (filtroPubblicatore.getPubblicatore().compareTo(pubblicatoreEsterno) == 0) {
                    count++;
                }
            }
        }
        if (count <= 1) {
            categoriaEventiEsterna.getListaPubblicatori().remove(pubblicatoreEsterno);
            pubblicatoreEsterno.getListaCatgoriaEventi().remove(categoriaEventiEsterna);
        }
    }
}
