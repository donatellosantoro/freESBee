package it.unibas.icar.freesbee.processors.strategy;

import com.google.inject.Inject;
import it.unibas.icar.freesbee.exception.FreesbeeException;
import it.unibas.icar.freesbee.modello.AccordoServizio;
import it.unibas.icar.freesbee.modello.Azione;
import it.unibas.icar.freesbee.modello.BustaEGov;
import it.unibas.icar.freesbee.modello.CostantiEccezioni;
import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.modello.PortaDelegata;
import it.unibas.icar.freesbee.modello.Servizio;
import it.unibas.icar.freesbee.modello.Soggetto;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.DBManager;
import it.unibas.icar.freesbee.persistenza.IDAOAccordoServizio;
import it.unibas.icar.freesbee.persistenza.IDAOServizio;
import it.unibas.icar.freesbee.persistenza.IDAOSoggetto;
import it.unibas.icar.freesbee.persistenza.hibernate.DAOUtilHibernate;
import it.unibas.icar.freesbee.utilita.StringUtil;
import it.unibas.icar.freesbee.utilita.UtilitaDate;
import java.util.Date;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;

public class EnricherPortaDelegataSemplice implements IEnricherPortaDelegataStrategy {

//    private static Log logger = LogFactory.getLog(EnricherPortaDelegataSemplice.class);
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(EnricherPortaDelegataSemplice.class.getName());
    @Inject
    private DBManager dbManager;
    @Inject
    private IDAOServizio daoServizio;
    @Inject
    private IDAOSoggetto daoSoggetto;
    @Inject
    private IDAOAccordoServizio daoAccordo;

    public void arricchisciMessaggio(PortaDelegata portaDelegata, Messaggio messaggio) throws FreesbeeException {
        assert (portaDelegata != null) : "Impossibile arricchire le informazioni di una porta delegata nulla.";
        if (portaDelegata.getSoggettoFruitore() == null) {
            throw new FreesbeeException("Il soggetto fruitore è sconosciuto.");
        }
        if (portaDelegata.getServizio() == null) {
            String errore = "Impossibile arricchire le informazioni di una porta delegata. Servizio: "
                    + portaDelegata.getServizio() + " Soggetto Fruitore: " + portaDelegata.getSoggettoFruitore();
            logger.error(errore);
            throw new FreesbeeException(errore);
        }

        Servizio servizioCorrelato = dbManager.findCorrelato(portaDelegata.getServizio().getAccordoServizio(), portaDelegata.getSoggettoFruitore());

        // Tripla (destinatario,tipo,servizio)
        String fruitore = portaDelegata.getSoggettoFruitore().getNome();
        String tipoFruitore = portaDelegata.getSoggettoFruitore().getTipo();
        String erogatore = portaDelegata.getServizio().getErogatore().getNome();
        String tipoErogatore = portaDelegata.getServizio().getErogatore().getTipo();
        String servizio = portaDelegata.getServizio().getNome();
        String tipoServizio = portaDelegata.getServizio().getTipo();
        boolean mutuaAutenticazione;
//        String connettoreErogatore = portaDelegata.getServizio().getErogatore().getPortaDominio();
        String connettoreErogatore = "";
        if ((portaDelegata.getServizio().getUrlServizio() == null) || (portaDelegata.getServizio().getUrlServizio().equals(""))) {
            connettoreErogatore = portaDelegata.getServizio().getErogatore().getPortaDominio();
            mutuaAutenticazione = portaDelegata.getServizio().getErogatore().isMutuaAutenticazione();
        } else {
            connettoreErogatore = portaDelegata.getServizio().getUrlServizio();
            mutuaAutenticazione = portaDelegata.getServizio().isMutuaAutenticazione();
        }
        boolean correlato = portaDelegata.getServizio().isCorrelato();

        String profiloCollaborazione = portaDelegata.getServizio().getAccordoServizio().getProfiloCollaborazione();
        String azione = null;
        if (portaDelegata.getAzione() != null) {
            verificaAzione(messaggio, portaDelegata.getServizio(), portaDelegata.getAzione());
            azione = portaDelegata.getAzione().getNome();
            profiloCollaborazione = portaDelegata.getAzione().getProfiloCollaborazione();
        }

        String confermaRicezione = portaDelegata.getServizio().getAccordoServizio().getProfiloEGov().isConfermaRicezione() + "";
        String inoltro = portaDelegata.getServizio().getAccordoServizio().getProfiloEGov().getStringaInoltro();
//            Date scadenzaMessaggio = portaDelegata.getAzione().getProfiloEGov().getScadenza();
        Date oraRegistrazione = new Date();

        if (logger.isDebugEnabled()) logger.debug("fruitore: " + fruitore);
        if (logger.isDebugEnabled()) logger.debug("tipoFruitore: " + tipoFruitore);
        if (logger.isDebugEnabled()) logger.debug("erogatore: " + erogatore);
        if (logger.isDebugEnabled()) logger.debug("tipoErogatore: " + tipoErogatore);
        if (logger.isDebugEnabled()) logger.debug("servizio: " + servizio);
        if (logger.isDebugEnabled()) logger.debug("tipoServizio: " + tipoServizio);
        if (logger.isDebugEnabled()) logger.debug("azione: " + azione);
        if (logger.isDebugEnabled()) logger.debug("profiloCollaborazione: " + profiloCollaborazione);
        if (logger.isDebugEnabled()) logger.debug("inoltro: " + inoltro);
        if (logger.isDebugEnabled()) logger.debug("confermaRicezione: " + confermaRicezione);
        if (logger.isDebugEnabled()) logger.debug("connettoreErogatore: " + connettoreErogatore);
        if (logger.isDebugEnabled()) logger.debug("correlato: " + correlato);
        if (logger.isDebugEnabled()) logger.debug("mutuaAutenticazione: " + mutuaAutenticazione);
        if (logger.isDebugEnabled()) logger.debug("servizioCorrelato: " + servizioCorrelato);

        messaggio.setFruitore(fruitore);
        messaggio.setTipoFruitore(tipoFruitore);
        messaggio.setErogatore(erogatore);
        messaggio.setTipoErogatore(tipoErogatore);
        messaggio.setServizio(servizio);
        messaggio.setTipoServizio(tipoServizio);
        messaggio.setAzione(azione);
        messaggio.setProfiloCollaborazione(profiloCollaborazione);
        messaggio.setOraRegistrazione(UtilitaDate.formattaDataEOra(oraRegistrazione));
        messaggio.setIdMessaggio(BustaEGov.getInstance().getNuovoIdentificatore(fruitore));
        messaggio.setInoltro(inoltro);
        messaggio.setConfermaRicezione(confermaRicezione);
        messaggio.setConnettoreErogatore(connettoreErogatore);
        messaggio.setMutuaAutenticazione(mutuaAutenticazione);
        messaggio.setCorrelato(correlato);
        if (servizioCorrelato != null) {
            messaggio.setServizioCorrelato(servizioCorrelato.getNome());
            messaggio.setTipoServizioCorrelato(servizioCorrelato.getTipo());
        }
    }

    private void verificaAzione(Messaggio messaggio, Servizio servizio, Azione azione) throws FreesbeeException {
        if (servizio.getListaAzioni().isEmpty()) {
            return;
        }
        for (String stringaAzione : servizio.getListaAzioni()) {
            if (azione.getNome().equals(stringaAzione)) {
                return;
            }
        }
        messaggio.aggiungiEccezione(CostantiEccezioni.AZIONE_NON_RICONOSCIUTA);
        throw new FreesbeeException("Non esiste alcuna azione " + azione.getNome() + " nel servizio " + servizio.getNome());
    }

    public void arricchisciAzioneDinamica(PortaDelegata portaDelegata) throws FreesbeeException {
        assert (portaDelegata != null) : "Impossibile arricchire le informazioni di una porta delegata nulla";
        String nomeAzione = portaDelegata.getStringaAzione();

        Servizio servizio = portaDelegata.getServizio();
        AccordoServizio accordoServizio = servizio.getAccordoServizio();
        SessionFactory sessionFactory = null;
        try {
            sessionFactory = DAOUtilHibernate.getSessionFactory();
            sessionFactory.getCurrentSession().beginTransaction();
            daoAccordo.makePersistent(accordoServizio);
        } catch (DAOException ex) {
            try {
                logger.error("Si e' verificato un errore durante l'accesso al DB.");
                if (logger.isDebugEnabled()) logger.debug(ex.toString());
                
                if (sessionFactory != null && sessionFactory.getCurrentSession().getTransaction().isActive()) {
                    sessionFactory.getCurrentSession().getTransaction().rollback();
                }
            } catch (Throwable rbEx) {
                logger.error("Impossibile effettuare il rollback della transazione sul DB.");
                if (logger.isDebugEnabled()) logger.debug(rbEx.toString());
            }
            
            throw new FreesbeeException("Si e' verificato un errore durante l'accesso al DB.");
        }
        
        if (nomeAzione != null && !nomeAzione.isEmpty()) {
            Azione azione = accordoServizio.cercaAzione(nomeAzione);
            if (azione == null) {
                logger.error("L'azione definita dinamicamente (nella SOAPAction) non e' prevista nell'accordo di servizio.");
//                throw new FreesbeeException("L'azione " + nomeAzione + " non è prevista nell'accordo di servizio " + servizio.getAccordoServizio().getNome());
            } else {
                portaDelegata.setAzione(azione);
            }
        }
    }

    public void arricchisciPortaDelegataDinamica(PortaDelegata portaDelegata,
            String queryFruitore, String queryTipoFruitore,
            String queryErogatore, String queryTipoErogatore,
            String queryServizio, String queryTipoServizio, String queryAzione) throws FreesbeeException {
        assert (portaDelegata != null) : "Impossibile arricchire le informazioni di una porta delegata nulla";
        SessionFactory sessionFactory = null;
        try {
            sessionFactory = DAOUtilHibernate.getSessionFactory();
            sessionFactory.getCurrentSession().beginTransaction();

            if (portaDelegata.getSoggettoFruitore() == null) {
                if (StringUtil.isStringaVuota(queryFruitore) && StringUtil.isStringaVuota(queryTipoFruitore)) {
                    throw new FreesbeeException("La query string deve includere le informazioni del fruitore.");
                } else {
                    Soggetto fruitore = daoSoggetto.findByNome(queryFruitore, queryTipoFruitore);
                    if (fruitore == null) {
                        throw new FreesbeeException("Il soggetto fruitore " + queryTipoFruitore + "-" + queryFruitore + " non e' presente nel registro dei soggetti.");
                    }
                    portaDelegata.setSoggettoFruitore(fruitore);
                }

            }
            if (portaDelegata.getServizio() == null) {
                if (StringUtil.isStringaVuota(queryErogatore) && StringUtil.isStringaVuota(queryTipoErogatore)
                        && StringUtil.isStringaVuota(queryServizio) && StringUtil.isStringaVuota(queryTipoServizio)) {
                    throw new FreesbeeException("La query string deve includere le informazioni del servizio e del suo erogatore.");
                } else {
                    Soggetto erogatore = daoSoggetto.findByNome(queryErogatore, queryTipoErogatore);
                    if (erogatore == null) {
                        throw new FreesbeeException("Il soggetto erogatore " + queryTipoErogatore + "-" + queryErogatore + " non e' presente nel registro dei soggetti.");
                    }
                    Servizio servizio = daoServizio.findByNome(queryServizio, queryTipoServizio, erogatore);
                    if (servizio == null) {
                        throw new FreesbeeException("Il servizio " + queryTipoServizio + "-" + queryServizio + " erogato da " + queryTipoErogatore + "-" + queryErogatore + " non e' presente nel registro dei soggetti.");
                    }
                    portaDelegata.setServizio(servizio);
                }
            }

            if (portaDelegata.getAzione() == null && !StringUtil.isStringaVuota(queryAzione)) {
                Azione azione = portaDelegata.getServizio().getAccordoServizio().cercaAzione(queryAzione);
                if (azione == null) {
                    throw new FreesbeeException("L'azione " + queryAzione + " non è prevista nell'accordo di servizio " + portaDelegata.getServizio().getAccordoServizio().getNome());
                }
                portaDelegata.setAzione(azione);
            }

        } catch (DAOException ex) {
            logger.error("Si e' verificato un errore durante l'accesso al DB.");
            if (logger.isDebugEnabled()) logger.debug(ex.toString());
        } finally {
            try {
                if (sessionFactory != null && sessionFactory.getCurrentSession().getTransaction().isActive()) {
                    sessionFactory.getCurrentSession().getTransaction().rollback();
                }
            } catch (Throwable rbEx) {
                logger.error("Impossibile effettuare il rollback della transazione sul DB.");
                if (logger.isDebugEnabled()) logger.debug(rbEx.toString());
            }
        }
    }

    public DBManager getDbManager() {
        return dbManager;
    }

    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public IDAOServizio getDaoServizio() {
        return daoServizio;
    }

    public void setDaoServizio(IDAOServizio daoServizio) {
        this.daoServizio = daoServizio;
    }

    public IDAOSoggetto getDaoSoggetto() {
        return daoSoggetto;
    }

    public void setDaoSoggetto(IDAOSoggetto daoSoggetto) {
        this.daoSoggetto = daoSoggetto;
    }

    public IDAOAccordoServizio getDaoAccordo() {
        return daoAccordo;
    }

    public void setDaoAccordo(IDAOAccordoServizio daoAccordo) {
        this.daoAccordo = daoAccordo;
    }
}
