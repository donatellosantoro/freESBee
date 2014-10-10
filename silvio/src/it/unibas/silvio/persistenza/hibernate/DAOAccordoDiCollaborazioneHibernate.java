package it.unibas.silvio.persistenza.hibernate;

import it.unibas.silvio.modello.AccordoDiCollaborazione;
import it.unibas.silvio.persistenza.DAOException;
import it.unibas.silvio.persistenza.IDAOAccordoDiCollaborazione;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;

public class DAOAccordoDiCollaborazioneHibernate extends DAOGenericoHibernate<AccordoDiCollaborazione> implements IDAOAccordoDiCollaborazione{
    
    private Log logger = LogFactory.getLog(this.getClass());
    
    public DAOAccordoDiCollaborazioneHibernate(){
        super(AccordoDiCollaborazione.class);
    }
    
    public AccordoDiCollaborazione findByNome(String nome) throws DAOException {
        List<AccordoDiCollaborazione> lista = super.findByCriteria(Restrictions.eq("nome", nome));
        if (lista.size() != 0) {
            Hibernate.initialize(lista.get(0));
            return lista.get(0);
        }
        return null;
    }
    
    @Override
    public List<AccordoDiCollaborazione> findAll() throws DAOException{
        List<AccordoDiCollaborazione> listaAccordi = super.findAll();
        for(AccordoDiCollaborazione accordo : listaAccordi){
            Hibernate.initialize(accordo);
            Hibernate.initialize(accordo.getListaPortType());
        }
        return listaAccordi;
    }

}
