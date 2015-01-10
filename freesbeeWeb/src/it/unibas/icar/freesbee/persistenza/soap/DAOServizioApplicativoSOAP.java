package it.unibas.icar.freesbee.persistenza.soap;

import it.unibas.icar.freesbee.modello.ServizioApplicativo;
import it.unibas.icar.freesbee.modello.Utente;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOServizioApplicativo;
import it.unibas.icar.freesbee.ws.client.servizioapplicativo.cxf.IWSServizioApplicativo;
import it.unibas.icar.freesbee.ws.client.servizioapplicativo.cxf.WSServizioApplicativoImplService;
import it.unibas.icar.freesbee.ws.security.SecurityUtil;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DAOServizioApplicativoSOAP implements IDAOServizioApplicativo {

    private Log logger = LogFactory.getLog(this.getClass());

    public ServizioApplicativo findByNome(Utente utente, String nome) throws DAOException {
        List<ServizioApplicativo> listaServizi = findAll(utente);
        for (ServizioApplicativo servizioApplicativo : listaServizi) {
            if (servizioApplicativo.getNome().equalsIgnoreCase(nome)) {
                return servizioApplicativo;
            }
        }
        return null;
    }

    public List<ServizioApplicativo> findAllNoLazy(Utente utente) throws DAOException {
        return findAll(utente);
    }

    public ServizioApplicativo findById(Utente utente, Long id, boolean lock) throws DAOException {
        ServizioApplicativo servizioApplicativo = new ServizioApplicativo();
        try {
            WSServizioApplicativoImplService ss = new WSServizioApplicativoImplService(new URL(utente.getServer() + DAOCostanti.URL_WSDL_SERVIZIO_APPLICATIVO), DAOCostanti.SERVICE_NAME_SERVIZIO_APPLICATIVO);
            IWSServizioApplicativo port = ss.getWSServizioApplicativoImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente,port);

            it.unibas.icar.freesbee.ws.client.servizioapplicativo.cxf.ServizioApplicativo servStub = port.getServizioApplicativo(id);
            copiaProprietaStubToModello(servizioApplicativo, servStub);
        } catch (Exception ex) {
            logger.error("Impossibile leggere il servizio " + ex);
            throw new DAOException(ex);
        }
        return servizioApplicativo;
    }

    public List<ServizioApplicativo> findAll(Utente utente) throws DAOException {
        try {
            WSServizioApplicativoImplService ss = new WSServizioApplicativoImplService(new URL(utente.getServer() + DAOCostanti.URL_WSDL_SERVIZIO_APPLICATIVO), DAOCostanti.SERVICE_NAME_SERVIZIO_APPLICATIVO);
            IWSServizioApplicativo port = ss.getWSServizioApplicativoImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente,port);

            List<it.unibas.icar.freesbee.ws.client.servizioapplicativo.cxf.ServizioApplicativo> listaServ = port.getListaServiziApplicativi();
            List<ServizioApplicativo> lista = new ArrayList<ServizioApplicativo>();
            if (listaServ == null) {
                return lista;
            }
            for (it.unibas.icar.freesbee.ws.client.servizioapplicativo.cxf.ServizioApplicativo servizioStub : listaServ) {
                ServizioApplicativo servizioApplicativo = new ServizioApplicativo();
                copiaProprietaStubToModello(servizioApplicativo, servizioStub);
                lista.add(servizioApplicativo);
            }
            return lista;
        } catch (Exception ex) {
            if(logger.isDebugEnabled()) ex.printStackTrace();
            logger.error("Impossibile leggere il servizio applicativo " + ex);
            throw new DAOException(ex);
        }
    }

    public List<ServizioApplicativo> findAll(Utente utente, int offset, int limite) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ServizioApplicativo makePersistent(Utente utente, ServizioApplicativo servizioApplicativo) throws DAOException {
        try {
            WSServizioApplicativoImplService ss = new WSServizioApplicativoImplService(new URL(utente.getServer() + DAOCostanti.URL_WSDL_SERVIZIO_APPLICATIVO), DAOCostanti.SERVICE_NAME_SERVIZIO_APPLICATIVO);
            IWSServizioApplicativo port = ss.getWSServizioApplicativoImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente,port);

            it.unibas.icar.freesbee.ws.client.servizioapplicativo.cxf.ServizioApplicativo servizioApplicativoStub = new it.unibas.icar.freesbee.ws.client.servizioapplicativo.cxf.ServizioApplicativo();
            copiaProprietaModelloToStub(servizioApplicativoStub, servizioApplicativo);
            port.addServizioApplicativo(servizioApplicativoStub);
            return servizioApplicativo;
        } catch (Exception ex) {
            if(logger.isDebugEnabled()) ex.printStackTrace();
            logger.error("Impossibile aggiungere il servizio " + ex);
            throw new DAOException(ex);
        }
    }

    public void makeTransient(Utente utente, ServizioApplicativo servizioApplicativo) throws DAOException {
        try {
            WSServizioApplicativoImplService ss = new WSServizioApplicativoImplService(new URL(utente.getServer() + DAOCostanti.URL_WSDL_SERVIZIO_APPLICATIVO), DAOCostanti.SERVICE_NAME_SERVIZIO_APPLICATIVO);
            IWSServizioApplicativo port = ss.getWSServizioApplicativoImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente,port);

            it.unibas.icar.freesbee.ws.client.servizioapplicativo.cxf.ServizioApplicativo servizioApplicativoStub = new it.unibas.icar.freesbee.ws.client.servizioapplicativo.cxf.ServizioApplicativo();
            copiaProprietaModelloToStub(servizioApplicativoStub, servizioApplicativo);
            port.removeServizioApplicativo(servizioApplicativoStub.getId());
        } catch (Exception ex) {
            if(logger.isDebugEnabled()) ex.printStackTrace();
            logger.error("Impossibile eliminare il servizio " + ex);
            throw new DAOException(ex);
        }
    }

    public void lock(Utente utente, ServizioApplicativo entity) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void copiaProprietaStubToModello(ServizioApplicativo servizioApplicativo, it.unibas.icar.freesbee.ws.client.servizioapplicativo.cxf.ServizioApplicativo servizioStub) {
        servizioApplicativo.setId(servizioStub.getId());
        servizioApplicativo.setNome(servizioStub.getNome());
        servizioApplicativo.setConnettore(servizioStub.getConnettore());
        servizioApplicativo.setDescrizione(servizioStub.getDescrizione());
        servizioApplicativo.setMutuaAutenticazione(servizioStub.isMutuaAutenticazione());
    }

    private void copiaProprietaModelloToStub(it.unibas.icar.freesbee.ws.client.servizioapplicativo.cxf.ServizioApplicativo servizioStub, ServizioApplicativo servizioApplicativo) {
        servizioStub.setId(servizioApplicativo.getId());
        servizioStub.setNome(servizioApplicativo.getNome());
        servizioStub.setConnettore(servizioApplicativo.getConnettore());
        servizioStub.setDescrizione(servizioApplicativo.getDescrizione());
        servizioStub.setMutuaAutenticazione(servizioApplicativo.isMutuaAutenticazione());
    }
}
