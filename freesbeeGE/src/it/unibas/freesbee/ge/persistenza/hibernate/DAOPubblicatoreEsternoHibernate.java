package it.unibas.freesbee.ge.persistenza.hibernate;

import it.unibas.freesbee.ge.modello.FiltroPubblicatoreEsterno;
import it.unibas.freesbee.ge.modello.PubblicatoreEsterno;
import it.unibas.freesbee.ge.modello.SottoscrizioneEsterna;
import it.unibas.freesbee.ge.persistenza.DAOException;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreEsterno;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;

public class DAOPubblicatoreEsternoHibernate extends DAOGenericoHibernate<PubblicatoreEsterno> implements IDAOPubblicatoreEsterno {

    private Log logger = LogFactory.getLog(this.getClass());

    public DAOPubblicatoreEsternoHibernate() {
        super(PubblicatoreEsterno.class);
    }

    public PubblicatoreEsterno findByTipoNome(String tipo, String nome) throws DAOException {
        logger.debug("Richiesto il pubblicatore esterno " + tipo + " " + nome);
        List<PubblicatoreEsterno> lista = super.findByCriteria(Restrictions.eq("nome", nome), Restrictions.eq("tipo", tipo));
        if (lista.size() != 0) {
            Hibernate.initialize(lista.get(0));
            return lista.get(0);
        }
        return null;
    }

    public void deletePubblicatoreEsterno(PubblicatoreEsterno pubblicatoreEsterno) throws DAOException {
        logger.debug("Verifica se il pubblicatore " + pubblicatoreEsterno + " e' da elimininare");
        DAOSottoscrizioneEsternaHibernate d = new DAOSottoscrizioneEsternaHibernate();
        List<SottoscrizioneEsterna> listaSottoscrizioni = d.findAll();
        int c = 0;
        boolean flag = false;
        for (SottoscrizioneEsterna sottoscrizioneEsterna : listaSottoscrizioni) {

            List<FiltroPubblicatoreEsterno> listaFiltroPubblicatore = sottoscrizioneEsterna.getListaFiltroPublicatore();
            for (FiltroPubblicatoreEsterno filtroPubblicatore : listaFiltroPubblicatore) {
                if (filtroPubblicatore.getPubblicatore().compareTo(pubblicatoreEsterno) == 0) {
                    flag = true;
                }
            }
            if (flag) {
                c++;
                flag = false;
            }

        }

        if (c <= 1) {
            super.makeTransient(pubblicatoreEsterno);
        }
    }

   
}
