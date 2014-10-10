package it.unibas.icar.freesbee.persistenza.soap;

import it.unibas.icar.freesbee.modello.AccordoServizio;
import it.unibas.icar.freesbee.modello.Servizio;
import it.unibas.icar.freesbee.modello.Soggetto;
import it.unibas.icar.freesbee.modello.Utente;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOAccordoServizio;
import it.unibas.icar.freesbee.persistenza.IDAOServizio;
import it.unibas.icar.freesbee.persistenza.IDAOSoggetto;
import it.unibas.icar.freesbee.ws.client.servizio.cxf.IWSServizio;
import it.unibas.icar.freesbee.ws.client.servizio.cxf.WSServizioImplService;
import it.unibas.icar.freesbee.ws.security.SecurityUtil;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DAOServizioSOAP implements IDAOServizio {

    private Log logger = LogFactory.getLog(this.getClass());
    private IDAOAccordoServizio daoAccordoServizio;
    private IDAOSoggetto daoSoggetto;

    public IDAOSoggetto getDaoSoggetto() {
        return daoSoggetto;
    }

    public void setDaoSoggetto(IDAOSoggetto daoSoggetto) {
        this.daoSoggetto = daoSoggetto;
    }

    public IDAOAccordoServizio getDaoAccordoServizio() {
        return daoAccordoServizio;
    }

    public void setDaoAccordoServizio(IDAOAccordoServizio daoAccordoServizio) {
        this.daoAccordoServizio = daoAccordoServizio;
    }

    public Servizio findByNome(Utente utente, String nome) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Servizio findByNome(Utente utente, String nome, String tipo, Soggetto erogatore) throws DAOException {
        List<Servizio> listaServizi = findAll(utente);
        for (Servizio servizio : listaServizi) {
            if (servizio.getNome().equalsIgnoreCase(nome) && servizio.getTipo().equalsIgnoreCase(tipo) && servizio.getErogatore().getId() == erogatore.getId()) {
                riempiRiferimenti(utente,servizio);
                return servizio;
            }
        }
        return null;
    }

    public List<Servizio> findByAccordo(Utente utente, AccordoServizio accordo) throws DAOException {
        List<Servizio> listaServizi = findAll(utente);
        List<Servizio> lista = new ArrayList<Servizio>();
        for (Servizio servizio : listaServizi) {
            if (servizio.getAccordoServizio().getId() == accordo.getId()) {
                riempiRiferimenti(utente,servizio);
                lista.add(servizio);
            }
        }
        return lista;
    }

    public List<Servizio> findAllNoLazy(Utente utente) throws DAOException {
        return findAll(utente);
    }

    public Servizio findById(Utente utente, Long id, boolean lock) throws DAOException {
        Servizio servizio = new Servizio();
        try {
            WSServizioImplService ss = new WSServizioImplService(new URL(utente.getServer() + DAOCostanti.URL_WSDL_SERVIZIO), DAOCostanti.SERVICE_NAME_SERVIZIO);
            IWSServizio port = ss.getWSServizioImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente,port);

            it.unibas.icar.freesbee.ws.client.servizio.cxf.Servizio servStub = port.getServizio(id);
            copiaProprietaStubToModello(servizio, servStub);
            riempiRiferimenti(utente,servizio);
        } catch (Exception ex) {
            logger.error("Impossibile leggere il servizio " + ex);
            throw new DAOException(ex);
        }
        return servizio;
    }

    public List<Servizio> findAll(Utente utente) throws DAOException {
        try {
            WSServizioImplService ss = new WSServizioImplService(new URL(utente.getServer() + DAOCostanti.URL_WSDL_SERVIZIO), DAOCostanti.SERVICE_NAME_SERVIZIO);
            IWSServizio port = ss.getWSServizioImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente,port);

            List<Servizio> lista = new ArrayList<Servizio>();
            List<it.unibas.icar.freesbee.ws.client.servizio.cxf.Servizio> listaServ = port.getListaServizi();
            if (listaServ == null) {
                return lista;
            }
            for (it.unibas.icar.freesbee.ws.client.servizio.cxf.Servizio servizioStub : listaServ) {
                Servizio servizio = new Servizio();
                copiaProprietaStubToModello(servizio, servizioStub);
                riempiRiferimenti(utente,servizio);
                lista.add(servizio);
            }
            return lista;
        } catch (Exception ex) {
            if(logger.isDebugEnabled()) ex.printStackTrace();
            logger.error("Impossibile leggere il servizio " + ex);
            throw new DAOException(ex);
        }
    }

    public List<Servizio> findAll(Utente utente, int offset, int limite) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Servizio makePersistent(Utente utente, Servizio servizio) throws DAOException {
        try {
            WSServizioImplService ss = new WSServizioImplService(new URL(utente.getServer() + DAOCostanti.URL_WSDL_SERVIZIO), DAOCostanti.SERVICE_NAME_SERVIZIO);
            IWSServizio port = ss.getWSServizioImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente,port);

            it.unibas.icar.freesbee.ws.client.servizio.cxf.Servizio servParam = new it.unibas.icar.freesbee.ws.client.servizio.cxf.Servizio();
            copiaProprietaModelloToStub(servParam, servizio);
            settaRiferimenti(servizio, servParam);
            port.addServizio(servParam);
            return servizio;
        } catch (Exception ex) {
            if(logger.isDebugEnabled()) ex.printStackTrace();
            logger.error("Impossibile aggiungere il servizio " + ex);
            throw new DAOException(ex);
        }
    }

    public void makeTransient(Utente utente, Servizio servizio) throws DAOException {
        try {
            WSServizioImplService ss = new WSServizioImplService(new URL(utente.getServer() + DAOCostanti.URL_WSDL_SERVIZIO), DAOCostanti.SERVICE_NAME_SERVIZIO);
            IWSServizio port = ss.getWSServizioImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente,port);

            it.unibas.icar.freesbee.ws.client.servizio.cxf.Servizio removeServ = new it.unibas.icar.freesbee.ws.client.servizio.cxf.Servizio();
            copiaProprietaModelloToStub(removeServ, servizio);
            settaRiferimenti(servizio, removeServ);
            port.removeServizio(servizio.getId());
        } catch (Exception ex) {
            if(logger.isDebugEnabled()) ex.printStackTrace();
            logger.error("Impossibile eliminare il servizio " + ex);
            throw new DAOException(ex);
        }
    }

    public void lock(Utente utente, Servizio entity) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void copiaProprietaStubToModello(Servizio servizio, it.unibas.icar.freesbee.ws.client.servizio.cxf.Servizio servizioStub) {
        servizio.setId(servizioStub.getId());
        servizio.setIdAccordoServizio(servizioStub.getIdAccordoServizio());
        servizio.setIdErogatore(servizioStub.getIdErogatore());
        servizio.setIdFruitori(servizioStub.getIdFruitori());
        servizio.setNome(servizioStub.getNome());
        servizio.setPrivato(servizioStub.isPrivato());
        servizio.setTipo(servizioStub.getTipo());
        servizio.setCorrelato(servizioStub.isCorrelato());
        servizio.setUrlServizio(servizioStub.getUrlServizio());
        if (servizioStub.getAzioni() != null) {
            servizio.setAllAzioni(false);
            servizio.setAzioni(servizioStub.getAzioni());
        } else {
            servizio.setAllAzioni(true);
        }
    }

    private void copiaProprietaModelloToStub(it.unibas.icar.freesbee.ws.client.servizio.cxf.Servizio servizioStub, Servizio servizio) {
        servizioStub.setId(servizio.getId());
        servizioStub.setNome(servizio.getNome());
        servizioStub.setPrivato(servizio.isPrivato());
        servizioStub.setTipo(servizio.getTipo());
        servizioStub.setCorrelato(servizio.isCorrelato());
        servizioStub.setUrlServizio(servizio.getUrlServizio());
        if (!servizio.isAllAzioni()) servizioStub.setAzioni(servizio.getAzioni());
    }

    private void riempiRiferimenti(Utente utente, Servizio servizio) throws DAOException {
        servizio.setAccordoServizio(daoAccordoServizio.findById(utente,servizio.getIdAccordoServizio(), false));
        servizio.setErogatore(daoSoggetto.findById(utente,servizio.getIdErogatore(), false));
        servizio.setFruitori(riempiListaSoggettiFruitori(utente,servizio.getIdFruitori()));
    }

    private List<Soggetto> riempiListaSoggettiFruitori(Utente utente, List<Long> idFruitori) throws DAOException {
        List<Soggetto> listaFruitori = new ArrayList<Soggetto>();
        for (Iterator<Long> iterator = idFruitori.iterator(); iterator.hasNext();) {
            long idFruitore = (long) iterator.next();
            Soggetto soggettoFruitore = daoSoggetto.findById(utente,idFruitore, false);
            listaFruitori.add(soggettoFruitore);
        }
        return listaFruitori;
    }

    private void settaRiferimenti(Servizio servizio, it.unibas.icar.freesbee.ws.client.servizio.cxf.Servizio servizioStub) {
        servizioStub.setIdAccordoServizio(servizio.getAccordoServizio().getId());
        servizioStub.setIdErogatore(servizio.getErogatore().getId());
        if (servizio.getFruitori() != null) {
            for (Soggetto soggetto : servizio.getFruitori()) {
                servizioStub.getIdFruitori().add(soggetto.getId());
            }
        }
    }
}
