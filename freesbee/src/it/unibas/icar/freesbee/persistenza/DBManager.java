package it.unibas.icar.freesbee.persistenza;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import it.unibas.icar.freesbee.exception.FreesbeeException;
import it.unibas.icar.freesbee.modello.AccordoServizio;
import it.unibas.icar.freesbee.modello.Azione;
import it.unibas.icar.freesbee.modello.Configurazione;
import it.unibas.icar.freesbee.modello.ConfigurazioneStatico;
import it.unibas.icar.freesbee.modello.PortaApplicativa;
import it.unibas.icar.freesbee.modello.PortaDelegata;
import it.unibas.icar.freesbee.modello.Servizio;
import it.unibas.icar.freesbee.modello.Soggetto;
import it.unibas.icar.freesbee.modulocontrollo.sbustamento.WireTapTracciamentoSbustamento;
import it.unibas.icar.freesbee.persistenza.hibernate.DAOUtilHibernate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;

@Singleton
public class DBManager {

//    private static Log logger = LogFactory.getLog(DBManager.class.getName());
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(DBManager.class.getName());
    @Inject
    private IDAOPortaDelegata daoPortaDelegata;
    @Inject
    private IDAOPortaApplicativa daoPortaApplicativa;
    @Inject
    private IDAOServizio daoServizio;
    @Inject
    private IDAOConfigurazione daoConfigurazione;
    @Inject
    private IDAOSoggetto daoSoggetto;
    //
    private Map<String, PortaDelegata> cachePortaDelegata = new HashMap<String, PortaDelegata>();
    private Map<String, PortaApplicativa> cachePortaApplicativa = new HashMap<String, PortaApplicativa>();
    private Map<String, Soggetto> cacheSoggetto = new HashMap<String, Soggetto>();
    private Map<String, Servizio> cacheServizioCorrelato = new HashMap<String, Servizio>();
    private Map<String, List<Servizio>> cacheServizi = new HashMap<String, List<Servizio>>();
    private Map<String, Servizio> cacheServizio = new HashMap<String, Servizio>();
    private Configurazione cacheConfigurazione = null;

    public PortaDelegata getPortaDelegata(String nomePortaDelegata) throws FreesbeeException {
        if (ConfigurazioneStatico.getInstance().isCacheDB()) {
            PortaDelegata cache = cachePortaDelegata.get(nomePortaDelegata);
            if (cache != null) return cache;
        }
        SessionFactory sessionFactory = null;
        PortaDelegata portaDelegata = null;
        try {
            if (logger.isDebugEnabled()) logger.debug("Si sta accedendo al DB per ottenere informazioni circa la porta delegata.");
            sessionFactory = DAOUtilHibernate.getSessionFactory();
            sessionFactory.getCurrentSession().beginTransaction();
            portaDelegata = daoPortaDelegata.findByNome(nomePortaDelegata);
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (DAOException ex) {
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new FreesbeeException("Errore durante l'accesso al DB per ottenere informazioni circa la porta delegata.");
        } finally {
            rollbackTransaction(sessionFactory);
        }
        
        if (portaDelegata == null) {
            throw new FreesbeeException("Impossibile trovare la porta delegata " + nomePortaDelegata + " nel DB.");
        }
        if (ConfigurazioneStatico.getInstance().isCacheDB()) {
            cachePortaDelegata.put(nomePortaDelegata, portaDelegata);
        }
        return portaDelegata;
    }

    public Soggetto getSoggetto(String nomeSoggetto, String tipoSoggetto) throws FreesbeeException {
        String chiave = nomeSoggetto + "#" + tipoSoggetto;
        if (ConfigurazioneStatico.getInstance().isCacheDB()) {
            if (cacheSoggetto.containsKey(chiave)) {
                return cacheSoggetto.get(chiave);
            }
        }
        SessionFactory sessionFactory = null;
        Soggetto soggetto = null;
        try {
            if (logger.isDebugEnabled()) logger.debug("Si sta accedendo al DB per ottenere informazioni sul soggetto.");
            sessionFactory = DAOUtilHibernate.getSessionFactory();
            sessionFactory.getCurrentSession().beginTransaction();
            soggetto = daoSoggetto.findByNome(nomeSoggetto, tipoSoggetto);
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (DAOException ex) {
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new FreesbeeException("Errore durante l'accesso al DB per ottenere informazioni sul soggetto.");
        } finally {
            rollbackTransaction(sessionFactory);
        }
        
        if (ConfigurazioneStatico.getInstance().isCacheDB()) {
            cacheSoggetto.put(chiave, soggetto);
        }
        return soggetto;
    }

    public Servizio findCorrelato(AccordoServizio accordo, Soggetto fruitore) throws FreesbeeException {
        String chiave = accordo.getNome() + "#" + fruitore.getNomeBreve();
        if (ConfigurazioneStatico.getInstance().isCacheDB()) {
            if (cacheServizioCorrelato.containsKey(chiave)) {
                return cacheServizioCorrelato.get(chiave);
            }
        }
        SessionFactory sessionFactory = null;
        Servizio servizioCorrelato = null;
        try {
            if (logger.isDebugEnabled()) logger.debug("Si sta accedendo al DB per ottenere informazioni sul servizio correlato.");
            sessionFactory = DAOUtilHibernate.getSessionFactory();
            sessionFactory.getCurrentSession().beginTransaction();
            servizioCorrelato = daoServizio.findCorrelato(accordo, fruitore);
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (DAOException ex) {
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new FreesbeeException("Errore durante l'accesso al DB per ottenere informazioni sul servizio correlato.");
        } finally {
            rollbackTransaction(sessionFactory);
        }
        
        if (ConfigurazioneStatico.getInstance().isCacheDB()) {
            cacheServizioCorrelato.put(chiave, servizioCorrelato);
        }
        return servizioCorrelato;
    }

    public Configurazione getConfigurazione() throws FreesbeeException {
        if (ConfigurazioneStatico.getInstance().isCacheDB() && cacheConfigurazione != null) {
            return cacheConfigurazione;
        }
        SessionFactory sessionFactory = null;
        Configurazione configurazione = null;
        try {
            if (logger.isDebugEnabled()) logger.debug("Si sta accedendo al DB per caricare la configurazione.");
            sessionFactory = DAOUtilHibernate.getSessionFactory();
            sessionFactory.getCurrentSession().beginTransaction();
            configurazione = daoConfigurazione.getConfigurazione();
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (Exception ex) {
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new FreesbeeException("Errore durante il caricamento della configurazione dal DB.");
        } finally {
            rollbackTransaction(sessionFactory);
        }
        
        if (configurazione == null) {
            throw new FreesbeeException("Impossibile caricare la configurazione dal DB.");
        }
        if (ConfigurazioneStatico.getInstance().isCacheDB()) {
            cacheConfigurazione = configurazione;
        }
        return configurazione;
    }

    public PortaApplicativa getPortaApplicativa(String nomeServizio, String tipoServizio, String nomeErogatore, String tipoErogatore, String azione) throws FreesbeeException {
        String chiave = nomeServizio + "#" + tipoServizio + "#" + nomeErogatore + "#" + tipoErogatore + "#" + azione;
        if (ConfigurazioneStatico.getInstance().isCacheDB()) {
            PortaApplicativa cache = cachePortaApplicativa.get(chiave);
            if (cache != null) return cache;
        }
        SessionFactory sessionFactory = null;
        PortaApplicativa portaApplicativa = null;
        try {
            if (logger.isDebugEnabled()) logger.debug("Si sta accedendo al DB per ottenere informazioni circa la porta applicativa.");
            sessionFactory = DAOUtilHibernate.getSessionFactory();
            sessionFactory.getCurrentSession().beginTransaction();
            portaApplicativa = daoPortaApplicativa.findByNomeServizio(nomeServizio, tipoServizio, nomeErogatore, tipoErogatore, azione);
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (Exception ex) {
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new FreesbeeException("Errore durante l'accesso al DB per ottenere informazioni circa la porta applicativa.");
        } finally {
            rollbackTransaction(sessionFactory);
        }
//        if (portaApplicativa == null) {
//            throw new FreesbeeException("Non esiste alcuna porta applicativa per il servizio " + tipoServizio + "-" + nomeServizio + " azione " + azione);
//        }
        if (ConfigurazioneStatico.getInstance().isCacheDB()) {
            cachePortaApplicativa.put(chiave, portaApplicativa);
        }
        return portaApplicativa;
    }

    public PortaApplicativa getPortaApplicativaByServizio(Servizio servizio, Azione azione) throws FreesbeeException {
        String chiave = servizio.toString() + "#" + azione;
        if (ConfigurazioneStatico.getInstance().isCacheDB()) {
            PortaApplicativa cache = cachePortaApplicativa.get(chiave);
            if (cache != null) return cache;
        }
        SessionFactory sessionFactory = null;
        PortaApplicativa portaApplicativa = null;
        try {
            if (logger.isDebugEnabled()) logger.debug("Si sta accedendo al DB per ottenere informazioni circa la porta applicativa.");
            sessionFactory = DAOUtilHibernate.getSessionFactory();
            sessionFactory.getCurrentSession().beginTransaction();
            portaApplicativa = daoPortaApplicativa.findByServizio(servizio, azione);
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (Exception ex) {
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new FreesbeeException("Errore durante l'accesso al DB per ottenere informazioni circa la porta applicativa.");
        } finally {
            rollbackTransaction(sessionFactory);
        }
//        if (portaApplicativa == null) {
//            throw new FreesbeeException("Non esiste alcuna porta applicativa per il servizio " + tipoServizio + "-" + nomeServizio + " azione " + azione);
//        }
        if (ConfigurazioneStatico.getInstance().isCacheDB()) {
            cachePortaApplicativa.put(chiave, portaApplicativa);
        }
        return portaApplicativa;
    }

    public List<Servizio> getServizi(String nomeServizio, String tipoServizio) throws FreesbeeException {
        String chiave = nomeServizio + "#" + tipoServizio;
        if (ConfigurazioneStatico.getInstance().isCacheDB()) {
            List<Servizio> cache = cacheServizi.get(chiave);
            if (cache != null) return cache;
        }
        SessionFactory sessionFactory = null;
        List<Servizio> servizi = null;
        try {
            if (logger.isDebugEnabled()) logger.debug("Si sta accedendo al DB per ottenere informazioni sui servizi.");
            sessionFactory = DAOUtilHibernate.getSessionFactory();
            sessionFactory.getCurrentSession().beginTransaction();
            servizi = daoServizio.findByNome(nomeServizio, tipoServizio);
            for (Servizio servizio : servizi) {
//                servizio.getFruitori().size(); //Forziamo l'inizializzazione dei fruitori
                Hibernate.initialize(servizio.getFruitori());
            }
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (Exception ex) {
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new FreesbeeException("Errore durante l'accesso al DB per ottenere informazioni sui servizi.");
        } finally {
            rollbackTransaction(sessionFactory);
        }
//        if (portaApplicativa == null) {
//            throw new FreesbeeException("Non esiste alcuna porta applicativa per il servizio " + tipoServizio + "-" + nomeServizio + " azione " + azione);
//        }
        if (ConfigurazioneStatico.getInstance().isCacheDB()) {
            cacheServizi.put(chiave, servizi);
        }
        return servizi;
    }

    public Servizio getServizio(String nomeServizio, String tipoServizio, Soggetto soggettoErogatore) throws FreesbeeException {
        String chiave = nomeServizio + "#" + tipoServizio + "#" + soggettoErogatore.getNomeBreve();
        if (ConfigurazioneStatico.getInstance().isCacheDB()) {
            Servizio cache = cacheServizio.get(chiave);
            if (cache != null) return cache;
        }
        SessionFactory sessionFactory = null;
        Servizio servizio = null;
        try {
            if (logger.isDebugEnabled()) logger.debug("Si sta accedendo al DB per ottenere informazioni sui servizi.");
            sessionFactory = DAOUtilHibernate.getSessionFactory();
            sessionFactory.getCurrentSession().beginTransaction();
            servizio = daoServizio.findByNome(nomeServizio, tipoServizio, soggettoErogatore);
            if (servizio != null) {
                Hibernate.initialize(servizio.getAccordoServizio().getAzioni());
            }
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (Exception ex) {
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new FreesbeeException("Errore durante l'accesso al DB per ottenere informazioni sui servizi.");
        } finally {
            rollbackTransaction(sessionFactory);
        }
//        if (portaApplicativa == null) {
//            throw new FreesbeeException("Non esiste alcuna porta applicativa per il servizio " + tipoServizio + "-" + nomeServizio + " azione " + azione);
//        }
        if (ConfigurazioneStatico.getInstance().isCacheDB()) {
            cacheServizio.put(chiave, servizio);
        }
        return servizio;
    }

    ////////////////
    private void rollbackTransaction(SessionFactory sessionFactory) {
        try {
            if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().rollback();
            }
        } catch (Throwable rbEx) {
        }
    }

    ///////// GET and SET
    public IDAOPortaDelegata getDaoPortaDelegata() {
        return daoPortaDelegata;
    }

    public void setDaoPortaDelegata(IDAOPortaDelegata daoPortaDelegata) {
        this.daoPortaDelegata = daoPortaDelegata;
    }

    public IDAOConfigurazione getDaoConfigurazione() {
        return daoConfigurazione;
    }

    public void setDaoConfigurazione(IDAOConfigurazione daoConfigurazione) {
        this.daoConfigurazione = daoConfigurazione;
    }

    public IDAOServizio getDaoServizio() {
        return daoServizio;
    }

    public void setDaoServizio(IDAOServizio daoServizio) {
        this.daoServizio = daoServizio;
    }

    public IDAOPortaApplicativa getDaoPortaApplicativa() {
        return daoPortaApplicativa;
    }

    public void setDaoPortaApplicativa(IDAOPortaApplicativa daoPortaApplicativa) {
        this.daoPortaApplicativa = daoPortaApplicativa;
    }
}