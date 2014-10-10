package it.unibas.silvio.persistenza.hibernate;

import it.unibas.silvio.modello.IstanzaAccordoDiCollaborazione;
import it.unibas.silvio.modello.IstanzaOperation;
import it.unibas.silvio.modello.IstanzaPortType;
import it.unibas.silvio.persistenza.DAOException;
import it.unibas.silvio.persistenza.IDAOIstanzaAccordoDiCollaborazione;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;

public class DAOIstanzaAccordoDiCollaborazioneHibernate extends DAOGenericoHibernate<IstanzaAccordoDiCollaborazione> implements IDAOIstanzaAccordoDiCollaborazione {

    private Log logger = LogFactory.getLog(this.getClass());

    public DAOIstanzaAccordoDiCollaborazioneHibernate() {
        super(IstanzaAccordoDiCollaborazione.class);
    }

    public IstanzaAccordoDiCollaborazione findByNome(String nome) throws DAOException {
        List<IstanzaAccordoDiCollaborazione> lista = super.findByCriteria(Restrictions.eq("nome", nome));
        if (lista.size() != 0) {
            Hibernate.initialize(lista.get(0));
            return lista.get(0);
        }
        return null;
    }
    
    public List<IstanzaAccordoDiCollaborazione> findAllFruitori() throws DAOException{
        List<IstanzaAccordoDiCollaborazione> lista = super.findByCriteria(Restrictions.eq("ruolo", "FRUITORE"));
        if (lista.size() != 0) {
            Hibernate.initialize(lista.get(0));
        }
        return lista;
    }
}
