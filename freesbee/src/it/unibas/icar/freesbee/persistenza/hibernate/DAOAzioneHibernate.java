package it.unibas.icar.freesbee.persistenza.hibernate;

import com.google.inject.Singleton;
import it.unibas.icar.freesbee.modello.Azione;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOAzione;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Restrictions;

@Singleton
public class DAOAzioneHibernate extends DAOGenericoHibernate<Azione> implements IDAOAzione {
    
    private static Log logger = LogFactory.getLog(DAOAzioneHibernate.class);
    
    public DAOAzioneHibernate() {
        super(Azione.class);
    }

    public Azione findByNome(String nome, long accordoServizioId) throws DAOException {
        List<Azione> lista = super.findByCriteria(Restrictions.eq("nome", nome));
        for (Azione azione : lista) {
            if(azione.getAccordoServizio().getId()==accordoServizioId){
                return azione;
            }
        }
        return null;
    }
}
