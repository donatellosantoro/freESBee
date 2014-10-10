package it.unibas.icar.freesbee.persistenza.soap;

import it.unibas.icar.freesbee.modello.Configurazione;
import it.unibas.icar.freesbee.modello.Utente;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOConfigurazione;
import it.unibas.icar.freesbee.persistenza.IDAOSoggetto;
import it.unibas.icar.freesbee.ws.client.configurazione.cxf.Configurazione_Service;
import it.unibas.icar.freesbee.ws.client.configurazione.cxf.IWSConfigurazione;
import it.unibas.icar.freesbee.ws.security.SecurityUtil;
import java.net.URL;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DAOConfigurazioneSOAP implements IDAOConfigurazione {

    private Log logger = LogFactory.getLog(this.getClass());
    private IDAOSoggetto daoSoggetto;

    public IDAOSoggetto getDaoSoggetto() {
        return daoSoggetto;
    }

    public void setDaoSoggetto(IDAOSoggetto daoSoggetto) {
        this.daoSoggetto = daoSoggetto;
    }

    public Configurazione find(Utente utente) throws DAOException {
        Configurazione c = new Configurazione();
        try {
            Configurazione_Service ss = new Configurazione_Service(new URL(utente.getServer() + DAOCostanti.URL_WSDL_CONFIGURAZIONE), DAOCostanti.SERVICE_NAME_CONFIGURAZIONE);
            IWSConfigurazione port = ss.getWSConfigurazioneImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);

            it.unibas.icar.freesbee.ws.client.configurazione.cxf.Configurazione configurazione = port.getConfigurazione();
            copiaProprietaStubToModello(c, configurazione);
            copiaVersioneFreesbee(port, c);
        } catch (Exception ex) {
            if (logger.isDebugEnabled()) {
                ex.printStackTrace();
            }
            logger.error("Impossibile leggere la configurazione " + ex);
            throw new DAOException(ex);
        }
        return c;
    }

    public Configurazione findById(Utente utente, Long id, boolean lock) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Configurazione> findAll(Utente utente) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Configurazione> findAll(Utente utente, int offset, int limite) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void makeTransient(Utente utente, Configurazione entity) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Configurazione makePersistent(Utente utente, Configurazione configurazione) throws DAOException {
        try {
            Configurazione_Service ss = new Configurazione_Service(new URL(utente.getServer() + DAOCostanti.URL_WSDL_CONFIGURAZIONE), DAOCostanti.SERVICE_NAME_CONFIGURAZIONE);
            IWSConfigurazione port = ss.getWSConfigurazioneImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);

            it.unibas.icar.freesbee.ws.client.configurazione.cxf.Configurazione configurazioneStub = new it.unibas.icar.freesbee.ws.client.configurazione.cxf.Configurazione();
            copiaProprietaModelloToStub(configurazioneStub, configurazione);
            port.addConfigurazione(configurazioneStub);
            return configurazione;
        } catch (Exception ex) {
            if (logger.isDebugEnabled()) {
                ex.printStackTrace();
            }
            logger.error("Impossibile aggiungere la configurazione " + ex);
            throw new DAOException(ex);
        }
    }

    public void lock(Utente utente, Configurazione entity) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void copiaProprietaStubToModello(Configurazione c, it.unibas.icar.freesbee.ws.client.configurazione.cxf.Configurazione configurazione) {
        c.setId(configurazione.getId());
        c.setConnettoreRegistroServizi(configurazione.getConnettoreRegistroServizi());
        c.setIdSoggettoErogatoreRegistroServizi(configurazione.getIdSoggettoErogatoreRegistroServizi());
        c.setIdSoggettoErogatoreNICA(configurazione.getIdSoggettoErogatoreNICA());
        c.setIndirizzoPortaApplicativa(configurazione.getIndirizzoPortaApplicativa());
        c.setIndirizzoPortaDelegata(configurazione.getIndirizzoPortaDelegata());
        c.setInviaANICA(configurazione.isInviaANICA());
        c.setNICA(configurazione.isNICA());
        c.setTempo(configurazione.getTempo());
        c.setRegistroFreesbee(configurazione.isRegistroFreesbee());
    }

    private void copiaProprietaModelloToStub(it.unibas.icar.freesbee.ws.client.configurazione.cxf.Configurazione c, Configurazione configurazione) {
        c.setId(configurazione.getId());
        c.setConnettoreRegistroServizi(configurazione.getConnettoreRegistroServizi());
        c.setIdSoggettoErogatoreRegistroServizi(configurazione.getIdSoggettoErogatoreRegistroServizi());
        c.setIdSoggettoErogatoreNICA(configurazione.getIdSoggettoErogatoreNICA());
        c.setIndirizzoPortaApplicativa(configurazione.getIndirizzoPortaApplicativa());
        c.setIndirizzoPortaDelegata(configurazione.getIndirizzoPortaDelegata());
        c.setInviaANICA(configurazione.isInviaANICA());
        c.setNICA(configurazione.isNICA());
        c.setTempo(configurazione.getTempo());
        c.setRegistroFreesbee(configurazione.isRegistroFreesbee());
    }

    private void settaRiferimenti(Utente utente, Configurazione c) throws DAOException {
        if (c.getIdSoggettoErogatoreRegistroServizi() != 0) {
            c.setSoggettoErogatoreRegistroServizi(daoSoggetto.findById(utente, c.getIdSoggettoErogatoreRegistroServizi(), false));
        }

        if (c.getIdSoggettoErogatoreNICA() != 0) {
            c.setSoggettoErogatoreNICA(daoSoggetto.findById(utente, c.getIdSoggettoErogatoreNICA(), false));
        }
    }
    
    private void copiaVersioneFreesbee(IWSConfigurazione port, Configurazione configurazione) throws DAOException{
        try {
            configurazione.setFreesbeeVersion(port.getFreesbeeVersion());
        } catch (Exception ex) {
            if (logger.isDebugEnabled()) {
                ex.printStackTrace();
            }
            logger.error("Impossibile aggiungere la versione " + ex);
            throw new DAOException(ex);
        }
    }
}
