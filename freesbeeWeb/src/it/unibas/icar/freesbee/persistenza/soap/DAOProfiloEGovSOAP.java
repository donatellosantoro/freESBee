package it.unibas.icar.freesbee.persistenza.soap;

import it.unibas.icar.freesbee.modello.ProfiloEGov;
import it.unibas.icar.freesbee.modello.Utente;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOProfiloEGov;
import it.unibas.icar.freesbee.ws.client.profiloegov.cxf.IWSProfiloEGov;
import it.unibas.icar.freesbee.ws.client.profiloegov.cxf.WSProfiloEGovImplService;
import it.unibas.icar.freesbee.ws.security.SecurityUtil;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DAOProfiloEGovSOAP implements IDAOProfiloEGov {

    private Log logger = LogFactory.getLog(this.getClass());

    public ProfiloEGov findById(Utente utente, Long id, boolean lock) throws DAOException {
        ProfiloEGov profiloEGov = new ProfiloEGov();
        try {
            WSProfiloEGovImplService ss = new WSProfiloEGovImplService(new URL(utente.getServer() + DAOCostanti.URL_WSDL_PROFILO_EGOV), DAOCostanti.SERVICE_NAME_PROFILO_EGOV);
            IWSProfiloEGov port = ss.getWSProfiloEGovImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente,port);

            it.unibas.icar.freesbee.ws.client.profiloegov.cxf.ProfiloEGov profiloEGovStub = port.getProfiloEGov(id);
            copiaProprietaStubToModello(profiloEGov, profiloEGovStub);
        } catch (Exception ex) {
            logger.error("Impossibile leggere il profilo E-Gov " + ex);
            throw new DAOException(ex);
        }
        return profiloEGov;
    }

    public List<ProfiloEGov> findAll(Utente utente) throws DAOException {
        try {
            WSProfiloEGovImplService ss = new WSProfiloEGovImplService(new URL(utente.getServer() + DAOCostanti.URL_WSDL_PROFILO_EGOV), DAOCostanti.SERVICE_NAME_PROFILO_EGOV);
            IWSProfiloEGov port = ss.getWSProfiloEGovImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente,port);

            List<ProfiloEGov> lista = new ArrayList();
            List<it.unibas.icar.freesbee.ws.client.profiloegov.cxf.ProfiloEGov> listaProfili = port.getListaProfiliEGov();
            if (listaProfili == null) {
                return lista;
            }
            for (it.unibas.icar.freesbee.ws.client.profiloegov.cxf.ProfiloEGov profiloEGov : listaProfili) {
                ProfiloEGov p = new ProfiloEGov();
                copiaProprietaStubToModello(p, profiloEGov);
                lista.add(p);
            }
            return lista;
        } catch (Exception ex) {
            if(logger.isDebugEnabled()) ex.printStackTrace();
            logger.error("Impossibile leggere i profili E-Gov " + ex);
            throw new DAOException(ex);
        }
    }

    public List<ProfiloEGov> findAll(Utente utente, int offset, int limite) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ProfiloEGov makePersistent(Utente utente, ProfiloEGov profiloEGov) throws DAOException {
        try {
            WSProfiloEGovImplService ss = new WSProfiloEGovImplService(new URL(utente.getServer() + DAOCostanti.URL_WSDL_PROFILO_EGOV), DAOCostanti.SERVICE_NAME_PROFILO_EGOV);
            IWSProfiloEGov port = ss.getWSProfiloEGovImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente,port);

            it.unibas.icar.freesbee.ws.client.profiloegov.cxf.ProfiloEGov profilo = new it.unibas.icar.freesbee.ws.client.profiloegov.cxf.ProfiloEGov();
            copiaProprietaModelloToStub(profilo, profiloEGov);
            profiloEGov.setId(port.addProfiloEGov(profilo));
            return profiloEGov;
        } catch (Exception ex) {
            if(logger.isDebugEnabled()) ex.printStackTrace();
            logger.error("Impossibile aggiungere il profilo EGov " + ex);
            throw new DAOException(ex);
        }
    }

    public void makeTransient(Utente utente, ProfiloEGov profiloEGov) throws DAOException {
        try {
            WSProfiloEGovImplService ss = new WSProfiloEGovImplService(new URL(utente.getServer() + DAOCostanti.URL_WSDL_PROFILO_EGOV), DAOCostanti.SERVICE_NAME_PROFILO_EGOV);
            IWSProfiloEGov port = ss.getWSProfiloEGovImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente,port);

            it.unibas.icar.freesbee.ws.client.profiloegov.cxf.ProfiloEGov profilo = new it.unibas.icar.freesbee.ws.client.profiloegov.cxf.ProfiloEGov();
            copiaProprietaModelloToStub(profilo, profiloEGov);
            port.removeProfiloEGov(profilo.getId());
        } catch (Exception ex) {
            logger.error("Impossibile eliminare il profilo EGov " + ex);
            throw new DAOException(ex);
        }
    }

    public void lock(Utente utente, ProfiloEGov entity) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void copiaProprietaStubToModello(ProfiloEGov profiloEGov, it.unibas.icar.freesbee.ws.client.profiloegov.cxf.ProfiloEGov profiloEGovStub) {
        profiloEGov.setId(profiloEGovStub.getId());
        profiloEGov.setConfermaRicezione(profiloEGovStub.isConfermaRicezione());
        profiloEGov.setConsegnaInOrdine(profiloEGovStub.isConsegnaInOrdine());
        profiloEGov.setGestioneDuplicati(profiloEGovStub.isGestioneDuplicati());
        profiloEGov.setIdCollaborazione(profiloEGovStub.isIdCollaborazione());
        if (profiloEGovStub.getScadenza() != null) {
            long date = (long) profiloEGovStub.getScadenza().getMillisecond();
            profiloEGov.setScadenza(new java.util.Date(date));
        }
    }

    private void copiaProprietaModelloToStub(it.unibas.icar.freesbee.ws.client.profiloegov.cxf.ProfiloEGov profiloEGovStub, ProfiloEGov profiloEGov) {
        profiloEGovStub.setId(profiloEGov.getId());
        profiloEGovStub.setConfermaRicezione(profiloEGov.isConfermaRicezione());
        profiloEGovStub.setConsegnaInOrdine(profiloEGov.isConsegnaInOrdine());
        profiloEGovStub.setGestioneDuplicati(profiloEGov.isGestioneDuplicati());
        profiloEGovStub.setIdCollaborazione(profiloEGov.isIdCollaborazione());
    }
}
