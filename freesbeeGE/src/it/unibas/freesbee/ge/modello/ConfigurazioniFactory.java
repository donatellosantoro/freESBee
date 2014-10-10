package it.unibas.freesbee.ge.modello;

import it.unibas.freesbee.ge.persistenza.DAOException;
import it.unibas.freesbee.ge.persistenza.IDAOConfigurazione;
import it.unibas.freesbee.ge.persistenza.IDAOConfigurazioneSP;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOConfigurazioneHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOConfigurazioneSPHibernate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ConfigurazioniFactory {

    private static Configurazione singleton;
    private static ConfigurazioneSP singletonSP;
    private static Log logger = LogFactory.getLog(ConfigurazioniFactory.class);
    private static IDAOConfigurazione daoConfigurazione = new DAOConfigurazioneHibernate();
    private static IDAOConfigurazioneSP daoConfigurazioneSP = new DAOConfigurazioneSPHibernate();
    

    static {
        try {
            singleton = daoConfigurazione.caricaConfigurazione();

            singletonSP = daoConfigurazioneSP.caricaConfigurazioneSP();

            logger.info("Caricate le configurazioni");
        } catch (DAOException ex) {
            logger.error("Eccezione nel caricamento delle configurazioni: " + ex.getMessage());
        //        throw new DAOException("Eccezione nel caricamento delle configurazioni");
        }

    }

    public static Configurazione getConfigurazioneIstance() throws DAOException {
        if (singleton == null) {
            singleton = daoConfigurazione.caricaConfigurazione();
        }
        return singleton;
    }

    public static ConfigurazioneSP getConfigurazioneSPIstance() throws DAOException {
        if (singletonSP == null) {
            singletonSP = daoConfigurazioneSP.caricaConfigurazioneSP();
        }
        return singletonSP;
    }
}
