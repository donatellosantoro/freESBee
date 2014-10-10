package it.unibas.freesbee.ge.ws.web;

import it.unibas.freesbee.ge.modello.Configurazione;
import it.unibas.freesbee.ge.modello.ConfigurazioneSP;
import it.unibas.freesbee.ge.modello.ConfigurazioniFactory;
import it.unibas.freesbee.ge.modello.DatiConfigurazione;
import it.unibas.freesbee.ge.modello.GestoreEventi;
import it.unibas.freesbee.ge.modello.PubblicatoreInterno;
import it.unibas.freesbee.ge.modello.Sottoscrittore;
import it.unibas.freesbee.ge.persistenza.DAOException;
import it.unibas.freesbee.ge.persistenza.IDAOConfigurazione;
import it.unibas.freesbee.ge.persistenza.IDAOConfigurazioneSP;
import it.unibas.freesbee.ge.persistenza.IDAOGestoreEventi;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreInterno;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrittore;
import it.unibas.freesbee.ge.ws.gestoreeventi.SOAPFault;
import it.unibas.springfreesbee.ge.ConfigurazioneStatico;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.interceptor.InInterceptors;
import org.apache.cxf.interceptor.OutFaultInterceptors;

@WebService(targetNamespace = "http://ge.freesbee.unibas.it/")
@InInterceptors(interceptors = {"it.unibas.freesbee.ge.persistenza.hibernate.HibernateInterceptor", "it.unibas.freesbee.ge.ws.web.WSSecurityInterceptor"})
@OutFaultInterceptors(interceptors = {"it.unibas.freesbee.ge.persistenza.hibernate.HibernateInterceptorFault"})
public class WSConfiguraImpl implements IWSConfigura {

    private static Log logger = LogFactory.getLog(WSConfiguraImpl.class);
    private IDAOConfigurazione daoConfigurazione;
    private IDAOConfigurazioneSP daoConfigurazioneSP;
    private IDAOPubblicatoreInterno daoPubblicatoreInterno;
    private IDAOSottoscrittore daoSottoscrittore;
    private IDAOGestoreEventi daoGestoreEventi;

    public WSConfiguraImpl(IDAOConfigurazione daoConfigurazione, IDAOConfigurazioneSP daoConfigurazioneSP, IDAOPubblicatoreInterno daoPubblicatoreInterno, IDAOSottoscrittore daoSottoscrittore, IDAOGestoreEventi daoGestoreEventi) {
        this.daoConfigurazione = daoConfigurazione;
        this.daoConfigurazioneSP = daoConfigurazioneSP;
        this.daoPubblicatoreInterno = daoPubblicatoreInterno;
        this.daoGestoreEventi = daoGestoreEventi;
        this.daoSottoscrittore = daoSottoscrittore;

    }

    public void modificaConfigurazioneGE(@WebParam(name = "datiConfigurazione", targetNamespace = "") DatiConfigurazione datiConfigurazione) throws SOAPFault {
        logger.debug("Richiesta l'operazione: modificaConfigurazioneGE");
        try {

            Configurazione configurazione = ConfigurazioniFactory.getConfigurazioneIstance();
            Configurazione configurazioneModificata = datiConfigurazione.getConfigurazione();

            if (!configurazione.getNomeGestoreEventi().equals(configurazioneModificata.getNomeGestoreEventi()) || configurazione.getTipoGestoreEventi().equals(configurazioneModificata.getTipoGestoreEventi())) {

                GestoreEventi gestoreEventi = daoGestoreEventi.findByTipoNome(configurazione.getTipoGestoreEventi(), configurazione.getNomeGestoreEventi());
                if (gestoreEventi == null) {
                    logger.error("Il soggetto " + configurazione.getTipoGestoreEventi() + " " + configurazione.getNomeGestoreEventi() + "non risulta gestore eventi");
                    throw new SOAPFault("Il soggetto " + configurazione.getTipoGestoreEventi() + " " + configurazione.getNomeGestoreEventi() + "non risulta gestore eventi");
                }

                gestoreEventi.setTipo(configurazioneModificata.getTipoGestoreEventi());
                gestoreEventi.setNome(configurazioneModificata.getNomeGestoreEventi());

                daoGestoreEventi.makePersistent(gestoreEventi);

                PubblicatoreInterno pubblicatore = daoPubblicatoreInterno.findByTipoNome(configurazione.getTipoGestoreEventi(), configurazione.getNomeGestoreEventi());
                if (pubblicatore == null) {
                    logger.error("Il gestore eventi " + configurazione.getTipoGestoreEventi() + " " + configurazione.getNomeGestoreEventi() + " non risulta pubblicatore");
                    throw new SOAPFault("Il gestore eventi " + configurazione.getTipoGestoreEventi() + " " + configurazione.getNomeGestoreEventi() + " non risulta pubblicatore");
                }

                pubblicatore.setTipo(configurazioneModificata.getTipoGestoreEventi());
                pubblicatore.setNome(configurazioneModificata.getNomeGestoreEventi());

                daoPubblicatoreInterno.makePersistent(pubblicatore);

                Sottoscrittore sottoscrittore = daoSottoscrittore.findByTipoNome(configurazione.getTipoGestoreEventi(), configurazione.getNomeGestoreEventi());
                if (sottoscrittore == null) {

                    logger.error("Il gestore eventi " + configurazione.getTipoGestoreEventi() + " " + configurazione.getNomeGestoreEventi() + " non risulta sottoscrittore");
                    throw new SOAPFault("Il gestore eventi " + configurazione.getTipoGestoreEventi() + " " + configurazione.getNomeGestoreEventi() + " non risulta sottoscrittore");
                }

                sottoscrittore.setTipo(configurazioneModificata.getTipoGestoreEventi());
                sottoscrittore.setNome(configurazioneModificata.getNomeGestoreEventi());

                daoSottoscrittore.makePersistent(sottoscrittore);
            }


            configurazione.setNomeGestoreEventi(configurazioneModificata.getNomeGestoreEventi());
            configurazione.setTipoGestoreEventi(configurazioneModificata.getTipoGestoreEventi());

            configurazione.setNomeServizioConsegna(configurazioneModificata.getNomeServizioConsegna());
            configurazione.setNomeServizioNotifica(configurazioneModificata.getNomeServizioNotifica());
            configurazione.setNomeServizioPubblica(configurazioneModificata.getNomeServizioPubblica());
            configurazione.setTipoServizioPubblica(configurazioneModificata.getTipoServizioPubblica());
            configurazione.setTipoServizioNotifica(configurazioneModificata.getTipoServizioNotifica());
            configurazione.setTipoServizioConsegna(configurazioneModificata.getTipoServizioConsegna());
            configurazione.setPdConsegna(configurazioneModificata.getPdConsegna());
            configurazione.setPdPubblica(configurazioneModificata.getPdPubblica());
            configurazione.setPdNotifica(configurazioneModificata.getPdNotifica());
            configurazione.setScadenzaMessaggi(configurazioneModificata.getScadenzaMessaggi());
            configurazione.setAzioneServizioConsegna(configurazioneModificata.getAzioneServizioConsegna());
            configurazione.setAzioneServizioNotifica(configurazioneModificata.getAzioneServizioNotifica());
            daoConfigurazione.makePersistent(configurazione);

        } catch (DAOException ex) {
            logger.error("Errore durante l'accesso al database " + ex.getMessage());
            throw new SOAPFault("Errore durante l'accesso al database");
        }
    }

    public void modificaConfigurazioneSP(@WebParam(name = "datiConfigurazione", targetNamespace = "") DatiConfigurazione datiConfigurazione) throws SOAPFault {
        logger.debug("Richiesta l'operazione: modificaConfigurazioneSP");

        try {
            ConfigurazioneSP configurazioneSP = ConfigurazioniFactory.getConfigurazioneSPIstance();
            ConfigurazioneSP configurazioneSPModificata = datiConfigurazione.getConfigurazioneSP();
            configurazioneSP.setAutenticazione(configurazioneSPModificata.getAutenticazione());
            configurazioneSP.setNomeUtenteSP(configurazioneSPModificata.getNomeUtenteSP());
            configurazioneSP.setPasswordSP(configurazioneSPModificata.getPasswordSP());
            configurazioneSP.setRisorsa(configurazioneSPModificata.getRisorsa());
            configurazioneSP.setRisorsaPdConsegna(configurazioneSPModificata.getRisorsaPdConsegna());
            configurazioneSP.setRisorsaPdNotifica(configurazioneSPModificata.getRisorsaPdNotifica());
            configurazioneSP.setRisorsaPdPubblica(configurazioneSPModificata.getRisorsaPdPubblica());
            configurazioneSP.setUrlFreesbeeSP(configurazioneSPModificata.getUrlFreesbeeSP());
            daoConfigurazioneSP.makePersistent(configurazioneSP);
        } catch (DAOException ex) {
            logger.error("Errore durante l'accesso al database " + ex.getMessage());
            throw new SOAPFault("Errore durante l'accesso al database");
        }
    }

    public DatiConfigurazione caricaConfigurazione() throws SOAPFault {
        logger.debug("Richiesta l'operazione: caricaConfigurazione");

        DatiConfigurazione dati = new DatiConfigurazione();
        try {
            Configurazione configurazione = ConfigurazioniFactory.getConfigurazioneIstance();
            ConfigurazioneSP configurazioneSP = ConfigurazioniFactory.getConfigurazioneSPIstance();
            dati.setConfigurazione(configurazione);
            dati.setConfigurazioneSP(configurazioneSP);
            dati.setProtezioneSP(ConfigurazioneStatico.getInstance().isProtezioneSP());

        } catch (DAOException daoE) {
            logger.error("Errore durante l'accesso al database " + daoE.getMessage());
            throw new SOAPFault("Errore durante l'accesso al database");
        }
        return dati;
    }
}
