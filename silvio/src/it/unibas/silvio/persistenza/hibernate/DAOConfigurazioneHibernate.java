package it.unibas.silvio.persistenza.hibernate;

import it.unibas.silvio.modello.Configurazione;
import it.unibas.silvio.persistenza.DAOException;
import it.unibas.silvio.persistenza.IDAOConfigurazione;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class DAOConfigurazioneHibernate extends DAOGenericoHibernate<Configurazione> implements IDAOConfigurazione {

    private Log logger = LogFactory.getLog(this.getClass());

    public DAOConfigurazioneHibernate() {
        super(Configurazione.class);
    }
    

    public Configurazione caricaConfigurazione() {
        Configurazione configurazione = null;
        try {
            List<Configurazione> lista = super.findAll();
            if (lista.size() == 0) {
                configurazione = new Configurazione();
                configurazione.setDirConfig("C:\\config\\silvio");
                configurazione.setPorta("9192");
                super.makePersistent(configurazione);
            } else if(lista.size() == 1){
                configurazione = lista.get(0);
            }else if(lista.size() > 1){
                throw new DAOException("ERRORE! Sono state trovate più configurazioni. Impossibile stabilire qual è quella corretta");
                
            }
        } catch (DAOException e) {
            logger.error("ERRORE " + e);
        }
        return configurazione;
    }
    public void salvaConfigurazione(Configurazione configurazione){
        try {
            super.makePersistent(configurazione);
        } catch (DAOException ex) {
            logger.error("ERRORE! " +ex);
        }
    }
}
