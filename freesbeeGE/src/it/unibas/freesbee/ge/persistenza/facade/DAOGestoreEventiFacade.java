package it.unibas.freesbee.ge.persistenza.facade;

import it.unibas.freesbee.ge.modello.Configurazione;
import it.unibas.freesbee.ge.modello.ConfigurazioniFactory;
import it.unibas.freesbee.ge.modello.GestoreEventi;
import it.unibas.freesbee.ge.persistenza.DAOException;
import it.unibas.freesbee.ge.persistenza.IDAOGestoreEventi;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOGestoreEventiHibernate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DAOGestoreEventiFacade {

    private DAOGestoreEventiFacade() {
    }
    private static Log logger = LogFactory.getLog(DAOGestoreEventiFacade.class);
    private static IDAOGestoreEventi daoGestoreEventi = new DAOGestoreEventiHibernate();

    public static void verificaEsistenzaGestoreEventi(String tipo, String nome) throws DAOException {
        if (daoGestoreEventi.findByTipoNome(tipo, nome) == null) {
            logger.error("Il soggetto " + tipo + " " + nome + " non e' un gesotore evetni");
            throw new DAOException("Il soggetto " + tipo + " " + nome + " non e' un gesotore evetni");
        }
    }

    public static void verificaNonEsistenzaGestoreEventi(String tipo, String nome) throws DAOException {
        if (daoGestoreEventi.findByTipoNome(tipo, nome) != null) {
            logger.error("Il soggetto " + tipo + " " + nome + " e' gia' un gesotore evetni");
            throw new DAOException("Il soggetto " + tipo + " " + nome + " e' gia' un gesotore evetni");
        }
    }

    public static boolean isEsistenzaGestoreEventi(String tipo, String nome) throws DAOException {
        if (daoGestoreEventi.findByTipoNome(tipo, nome) != null) {
            return true;
        }
        return false;
    }

    public static GestoreEventi getGE() throws DAOException {
        //Ritorna il ge medesimo
        Configurazione configurazione = ConfigurazioniFactory.getConfigurazioneIstance();
        verificaEsistenzaGestoreEventi(configurazione.getTipoGestoreEventi(), configurazione.getNomeGestoreEventi());
        GestoreEventi ge = daoGestoreEventi.findByTipoNome(configurazione.getTipoGestoreEventi(), configurazione.getNomeGestoreEventi());
        return ge;
    }
}
