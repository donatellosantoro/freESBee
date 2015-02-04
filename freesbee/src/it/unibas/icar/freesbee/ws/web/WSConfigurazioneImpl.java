package it.unibas.icar.freesbee.ws.web;

import it.unibas.icar.freesbee.modello.Configurazione;
import it.unibas.icar.freesbee.modello.ConfigurazioneStatico;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOConfigurazione;
import it.unibas.icar.freesbee.persistenza.IDAOSoggetto;
import it.unibas.icar.freesbee.persistenza.SOAPFault;
import it.unibas.icar.freesbee.persistenza.hibernate.DAOUtilHibernate;
import java.util.List;
import javax.jws.WebService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;

@WebService(endpointInterface = "it.unibas.icar.freesbee.ws.web.IWSConfigurazione",serviceName = "Configurazione")
public class WSConfigurazioneImpl implements IWSConfigurazione {

    private static Log logger = LogFactory.getLog(WSConfigurazioneImpl.class);
    private IDAOConfigurazione daoConfigurazione;
    private IDAOSoggetto daoSoggetto;

    public WSConfigurazioneImpl(IDAOConfigurazione daoConfigurazione, IDAOSoggetto daoSoggetto) {
        this.daoConfigurazione = daoConfigurazione;
        this.daoSoggetto = daoSoggetto;
    }

    public void addConfigurazione(Configurazione freesbeeConfig) throws SOAPFault {
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            if (freesbeeConfig.getId() == 0) {
                //E' UNA CONFIGURAZIONE DA AGGIUNGERE
                if (logger.isDebugEnabled()) logger.debug("Richiesta l'aggiunta della configurazione " + freesbeeConfig);
                settaRiferimenti(freesbeeConfig);
                daoConfigurazione.makePersistent(freesbeeConfig);
            } else {
                //E' UNA CONFIGURAZIONE DA MODIFICARE
                if (logger.isDebugEnabled()) logger.debug("Richiesta la modifica della configurazione.");

                List<Configurazione> listaConfigurazioni = daoConfigurazione.findAll();
                Configurazione configurazioneModificare = null;
                if (listaConfigurazioni.size() != 0) {
                    configurazioneModificare = (Configurazione) daoConfigurazione.findAll().get(0);
                    copiaProprieta(freesbeeConfig, configurazioneModificare);
                    settaRiferimenti(configurazioneModificare);
                    daoConfigurazione.makePersistent(configurazioneModificare);
                }
            }
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error("Si e' verificato un errore durante la modifica della configurazione.");
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new SOAPFault("Si e' verificato un errore durante la modifica della configurazione.");
        }
    }

    public Configurazione getConfigurazione() throws SOAPFault {
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        Configurazione configurazione = null;
        try {
            if (logger.isDebugEnabled()) logger.debug("Richiesta la configurazione ");
            sessionFactory.getCurrentSession().beginTransaction();
            configurazione = (Configurazione) daoConfigurazione.getConfigurazione();
            sessionFactory.getCurrentSession().getTransaction().commit();

            if (configurazione.getSoggettoErogatoreRegistroServizi() != null) {
                configurazione.setIdSoggettoErogatoreRegistroServizi(configurazione.getSoggettoErogatoreRegistroServizi().getId());
            }

            if (configurazione.getSoggettoErogatoreNICA() != null) {
                configurazione.setIdSoggettoErogatoreNICA(configurazione.getSoggettoErogatoreNICA().getId());
            }

            return configurazione;
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error("Si e' verificato un errore durante la lettura dell'azione.");
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new SOAPFault("Si e' verificato un errore durante la lettura dell'azione.");
        }
    }

    private void copiaProprieta(Configurazione freesbeeConfig, Configurazione configurazioneModificare) {
        configurazioneModificare.setConnettoreRegistroServizi(freesbeeConfig.getConnettoreRegistroServizi());
        configurazioneModificare.setIdSoggettoErogatoreRegistroServizi(freesbeeConfig.getIdSoggettoErogatoreRegistroServizi());
        configurazioneModificare.setIdSoggettoErogatoreNICA(freesbeeConfig.getIdSoggettoErogatoreNICA());
        configurazioneModificare.setIndirizzoPortaApplicativa(freesbeeConfig.getIndirizzoPortaApplicativa());
        configurazioneModificare.setIndirizzoPortaDelegata(freesbeeConfig.getIndirizzoPortaDelegata());
        configurazioneModificare.setInviaANICA(freesbeeConfig.isInviaANICA());
        configurazioneModificare.setNICA(freesbeeConfig.isNICA());
        configurazioneModificare.setTempo(freesbeeConfig.getTempo());
        configurazioneModificare.setRegistroFreesbee(freesbeeConfig.isRegistroFreesbee());
        configurazioneModificare.setMutuaAutenticazionePortaApplicativa(freesbeeConfig.isMutuaAutenticazionePortaApplicativa());
        configurazioneModificare.setMutuaAutenticazionePortaDelegata(freesbeeConfig.isMutuaAutenticazionePortaDelegata());
    }

    private void settaRiferimenti(Configurazione freesbeeConfig) throws DAOException {
        if (freesbeeConfig.getIdSoggettoErogatoreRegistroServizi() != 0) {
            freesbeeConfig.setSoggettoErogatoreRegistroServizi(daoSoggetto.findById(freesbeeConfig.getIdSoggettoErogatoreRegistroServizi(), false));
        } else {
            freesbeeConfig.setSoggettoErogatoreRegistroServizi(null);
        }

        if (freesbeeConfig.getIdSoggettoErogatoreNICA() != 0) {
            freesbeeConfig.setSoggettoErogatoreNICA(daoSoggetto.findById(freesbeeConfig.getIdSoggettoErogatoreNICA(), false));
        } else {
            freesbeeConfig.setSoggettoErogatoreNICA(null);
        }
    }

    public String getFreesbeeVersion() throws SOAPFault {
        ConfigurazioneStatico configurazioneStatico = ConfigurazioneStatico.getInstance();
        return configurazioneStatico.getFreesbeeVersion();
    }
}
