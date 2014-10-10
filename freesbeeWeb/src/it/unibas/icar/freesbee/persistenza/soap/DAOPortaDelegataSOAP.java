package it.unibas.icar.freesbee.persistenza.soap;

import it.unibas.icar.freesbee.modello.PortaDelegata;
import it.unibas.icar.freesbee.modello.Soggetto;
import it.unibas.icar.freesbee.modello.Utente;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOAzione;
import it.unibas.icar.freesbee.persistenza.IDAOPortaDelegata;
import it.unibas.icar.freesbee.persistenza.IDAOServizio;
import it.unibas.icar.freesbee.persistenza.IDAOSoggetto;
import it.unibas.icar.freesbee.ws.client.portadelegata.cxf.IWSPortaDelegata;
import it.unibas.icar.freesbee.ws.client.portadelegata.cxf.WSPortaDelegataImplService;
import it.unibas.icar.freesbee.ws.security.SecurityUtil;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DAOPortaDelegataSOAP implements IDAOPortaDelegata {

    private Log logger = LogFactory.getLog(this.getClass());
    private IDAOAzione daoAzione;
    private IDAOServizio daoServizio;
    private IDAOSoggetto daoSoggetto;

    public PortaDelegata findByNome(Utente utente, String nome) throws DAOException {
        List<PortaDelegata> listaPorte = findAll(utente);
        for (PortaDelegata portaDelegata : listaPorte) {
            if (portaDelegata.getNome().equalsIgnoreCase(nome)) {
                riempiRiferimenti(utente, portaDelegata);
                return portaDelegata;
            }
        }
        return null;
    }

    public List<PortaDelegata> findBySoggetto(Utente utente, Soggetto soggetto) throws DAOException {
        List<PortaDelegata> listaPorte = findAll(utente);
        List<PortaDelegata> lista = new ArrayList<PortaDelegata>();
        for (PortaDelegata portaDelegata : listaPorte) {
            if (portaDelegata.getIdSoggettoFruitore() == soggetto.getId()) {
                lista.add(portaDelegata);
                riempiRiferimenti(utente, portaDelegata);
            }
        }
        return lista;
    }

    public PortaDelegata findById(Utente utente, Long id, boolean lock) throws DAOException {
        PortaDelegata pd = new PortaDelegata();
        try {
            WSPortaDelegataImplService ss = new WSPortaDelegataImplService(new URL(utente.getServer() + DAOCostanti.URL_WSDL_PORTA_DELEGATA), DAOCostanti.SERVICE_NAME_PORTA_DELEGATA);
            IWSPortaDelegata port = ss.getWSPortaDelegataImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);

            it.unibas.icar.freesbee.ws.client.portadelegata.cxf.PortaDelegata portaDelegata = port.getPortaDelegata(id);
            copiaProprietaStubToModello(pd, portaDelegata);
            riempiRiferimenti(utente, pd);
        } catch (Exception ex) {
            logger.error("Impossibile leggere la porta applicativa " + ex);
            throw new DAOException(ex);
        }
        return pd;
    }

    public List<PortaDelegata> findAll(Utente utente) throws DAOException {
        try {
            List<PortaDelegata> lista = new ArrayList();
            WSPortaDelegataImplService ss = new WSPortaDelegataImplService(new URL(utente.getServer() + DAOCostanti.URL_WSDL_PORTA_DELEGATA), DAOCostanti.SERVICE_NAME_PORTA_DELEGATA);
            IWSPortaDelegata port = ss.getWSPortaDelegataImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);

            List<it.unibas.icar.freesbee.ws.client.portadelegata.cxf.PortaDelegata> listaPorteDelegate = port.getListaPorteDelegate();
            if (listaPorteDelegate == null) {
                return lista;
            }
            for (it.unibas.icar.freesbee.ws.client.portadelegata.cxf.PortaDelegata portaDelegata : listaPorteDelegate) {
                PortaDelegata pd = new PortaDelegata();
                copiaProprietaStubToModello(pd, portaDelegata);
                riempiRiferimenti(utente, pd);
                lista.add(pd);
            }
            return lista;
        } catch (Exception ex) {
            logger.error("Impossibile leggere le porte applicative " + ex);
            if(logger.isDebugEnabled()) ex.printStackTrace();
            throw new DAOException(ex);
        }
    }

    public List<PortaDelegata> findAll(Utente utente, int offset, int limite) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public PortaDelegata makePersistent(Utente utente, PortaDelegata portaDelegata) throws DAOException {
        try {
            WSPortaDelegataImplService ss = new WSPortaDelegataImplService(new URL(utente.getServer() + DAOCostanti.URL_WSDL_PORTA_DELEGATA), DAOCostanti.SERVICE_NAME_PORTA_DELEGATA);
            IWSPortaDelegata port = ss.getWSPortaDelegataImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);

            it.unibas.icar.freesbee.ws.client.portadelegata.cxf.PortaDelegata pd = new it.unibas.icar.freesbee.ws.client.portadelegata.cxf.PortaDelegata();
            settaRiferimenti(portaDelegata);
            copiaProprietaModelloToStub(pd, portaDelegata);
            port.addPortaDelegata(pd);
            return portaDelegata;
        } catch (Exception ex) {
//            if(logger.isDebugEnabled()) ex.printStackTrace();
            logger.error("Impossibile aggiungere la porta delegata " + ex);
            throw new DAOException(ex);
        }
    }

    public void makeTransient(Utente utente, PortaDelegata portaDelegata) throws DAOException {
        try {
            WSPortaDelegataImplService ss = new WSPortaDelegataImplService(new URL(utente.getServer() + DAOCostanti.URL_WSDL_PORTA_DELEGATA), DAOCostanti.SERVICE_NAME_PORTA_DELEGATA);
            IWSPortaDelegata port = ss.getWSPortaDelegataImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);

            it.unibas.icar.freesbee.ws.client.portadelegata.cxf.PortaDelegata portaDelegataStub = new it.unibas.icar.freesbee.ws.client.portadelegata.cxf.PortaDelegata();
            copiaProprietaModelloToStub(portaDelegataStub, portaDelegata);
            port.removePortaDelegata(portaDelegataStub.getId());
        } catch (Exception ex) {
            logger.error("Impossibile eliminare la porta delegata " + ex);
            throw new DAOException(ex);
        }
    }

    public void lock(Utente utente, PortaDelegata entity) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void copiaProprietaStubToModello(PortaDelegata pd, it.unibas.icar.freesbee.ws.client.portadelegata.cxf.PortaDelegata portaDelegata) {
        pd.setIdAzione(portaDelegata.getIdAzione());
        pd.setIdServizio(portaDelegata.getIdServizio());
        pd.setIdSoggettoFruitore(portaDelegata.getIdSoggettoFruitore());
        pd.setNome(portaDelegata.getNome());
        pd.setDescrizione(portaDelegata.getDescrizione());
        pd.setFruitoreQueryString(portaDelegata.isFruitoreQueryString());
        pd.setStringaTipoServizio(portaDelegata.getStringaTipoServizio());
        pd.setStringaServizio(portaDelegata.getStringaServizio());
        pd.setStringaErogatore(portaDelegata.getStringaErogatore());
        pd.setStringaTipoErogatore(portaDelegata.getStringaTipoErogatore());
        pd.setStringaFruitore(portaDelegata.getStringaFruitore());
        pd.setStringaTipoFruitore(portaDelegata.getStringaTipoFruitore());
        pd.setStringaAzione(portaDelegata.getStringaAzione());
        pd.setId(portaDelegata.getId());
    }

    private void copiaProprietaModelloToStub(it.unibas.icar.freesbee.ws.client.portadelegata.cxf.PortaDelegata pd, PortaDelegata portaDelegata) {
        pd.setIdAzione(portaDelegata.getIdAzione());
        pd.setIdServizio(portaDelegata.getIdServizio());
        pd.setIdSoggettoFruitore(portaDelegata.getIdSoggettoFruitore());
        pd.setNome(portaDelegata.getNome());
        pd.setDescrizione(portaDelegata.getDescrizione());
        pd.setFruitoreQueryString(portaDelegata.isFruitoreQueryString());
        pd.setStringaTipoServizio(portaDelegata.getStringaTipoServizio());
        pd.setStringaServizio(portaDelegata.getStringaServizio());
        pd.setStringaErogatore(portaDelegata.getStringaErogatore());
        pd.setStringaTipoErogatore(portaDelegata.getStringaTipoErogatore());
        pd.setStringaFruitore(portaDelegata.getStringaFruitore());
        pd.setStringaTipoFruitore(portaDelegata.getStringaTipoFruitore());
        pd.setStringaAzione(portaDelegata.getStringaAzione());
        if (portaDelegata.getId() != null) {
            pd.setId(portaDelegata.getId());
        }
    }

    private void riempiRiferimenti(Utente utente, PortaDelegata pd) throws DAOException {
        if (pd.getIdAzione() > 0) {
            logger.info("Riempio i riferimenti dell'azione con id " + pd.getIdAzione());
            pd.setAzione(daoAzione.findById(utente, pd.getIdAzione(), false));
        }
        if (pd.getIdServizio() > 0) {
            logger.info("Riempio i riferimenti dell'servizio con id " + pd.getIdServizio());
            pd.setServizio(daoServizio.findById(utente, pd.getIdServizio(), false));
        }
        if (pd.getIdSoggettoFruitore() > 0) {
            logger.info("Riempio i riferimenti dell'soggetto fruitore con id " + pd.getIdSoggettoFruitore());
            pd.setSoggettoFruitore(daoSoggetto.findById(utente, pd.getIdSoggettoFruitore(), false));
        }
    }

    private void settaRiferimenti(PortaDelegata pd) {
        if (pd.getAzione() != null) {
            pd.setIdAzione(pd.getAzione().getId());
        }
        if (pd.getServizio() != null) {
            pd.setIdServizio(pd.getServizio().getId());
        }
        if (pd.getSoggettoFruitore() != null) {
            pd.setIdSoggettoFruitore(pd.getSoggettoFruitore().getId());
        }
    }

    public IDAOAzione getDaoAzione() {
        return daoAzione;
    }

    public void setDaoAzione(IDAOAzione daoAzione) {
        this.daoAzione = daoAzione;
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
}
