package it.unibas.icar.freesbee.persistenza.soap;

import com.sun.org.apache.commons.logging.Log;
import com.sun.org.apache.commons.logging.LogFactory;
import it.unibas.icar.freesbee.modello.AccordoServizio;
import it.unibas.icar.freesbee.modello.Azione;
import it.unibas.icar.freesbee.modello.Servizio;
import it.unibas.icar.freesbee.modello.Utente;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOAccordoServizio;
import it.unibas.icar.freesbee.persistenza.IDAOAzione;
import it.unibas.icar.freesbee.persistenza.IDAOProfiloEGov;
import it.unibas.icar.freesbee.persistenza.IDAOServizio;
import it.unibas.icar.freesbee.ws.client.accordoservizio.cxf.IWSAccordoServizio;
import it.unibas.icar.freesbee.ws.client.accordoservizio.cxf.WSAccordoServizioImplService;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import it.unibas.icar.freesbee.ws.client.accordoservizio.cxf.WSAccordoServizioImplService;
import it.unibas.icar.freesbee.ws.security.SecurityUtil;
import java.net.URL;

public class DAOAccordoServizioSOAP implements IDAOAccordoServizio {

    private Log logger = LogFactory.getLog(this.getClass());
    private IDAOProfiloEGov daoProfiloEGov;
    private IDAOServizio daoServizio;
    private IDAOAzione daoAzione;

    public AccordoServizio findByNome(Utente utente, String nome) throws DAOException {
        List<AccordoServizio> listaAccordi = findAll(utente);
        for (AccordoServizio accordo : listaAccordi) {
            if (accordo.getNome().equalsIgnoreCase(nome)) {
                riempiRiferimenti(utente, accordo);
                return accordo;
            }
        }
        return null;
    }

    public List<AccordoServizio> findAllNoLazy(Utente utente) throws DAOException {
        return findAll(utente);
    }

    public AccordoServizio findById(Utente utente, Long id, boolean lock) throws DAOException {
        AccordoServizio as = new AccordoServizio();
        try {
            WSAccordoServizioImplService ss = new WSAccordoServizioImplService(new URL(utente.getServer() + DAOCostanti.URL_WSDL_ACCORDO_SERVIZIO), DAOCostanti.SERVICE_NAME_ACCORDO_SERVIZIO);
            IWSAccordoServizio port = ss.getWSAccordoServizioImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);

            it.unibas.icar.freesbee.ws.client.accordoservizio.cxf.AccordoServizio accordoServizio = port.getAccordoServizio(id);
            copiaProprietaStubToModello(as, accordoServizio);
            riempiRiferimenti(utente, as);
        } catch (Exception ex) {
            if(logger.isDebugEnabled()) ex.printStackTrace();
            logger.error("Impossibile leggere l'accordo di servizio " + ex);
            throw new DAOException(ex);
        }
        return as;
    }

    public List<AccordoServizio> findAll(Utente utente) throws DAOException {
        try {
            WSAccordoServizioImplService ss = new WSAccordoServizioImplService(new URL(utente.getServer() + DAOCostanti.URL_WSDL_ACCORDO_SERVIZIO), DAOCostanti.SERVICE_NAME_ACCORDO_SERVIZIO);
            IWSAccordoServizio port = ss.getWSAccordoServizioImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);

            java.util.List<it.unibas.icar.freesbee.ws.client.accordoservizio.cxf.AccordoServizio> listaAccordiServizio = port.getListaAccordiServizio();
            List<AccordoServizio> lista = new ArrayList<AccordoServizio>();
            if (listaAccordiServizio == null) {
                return lista;
            }
            for (it.unibas.icar.freesbee.ws.client.accordoservizio.cxf.AccordoServizio accordoServizio : listaAccordiServizio) {
                AccordoServizio as = new AccordoServizio();
                copiaProprietaStubToModello(as, accordoServizio);
                riempiRiferimenti(utente, as);
                lista.add(as);
            }
            return lista;
        } catch (Exception ex) {
            if(logger.isDebugEnabled()) ex.printStackTrace();
            logger.error("Impossibile leggere l'accordo di servizio " + ex);
            throw new DAOException(ex);
        }
    }

    public List<AccordoServizio> findAll(Utente utente, int offset, int limite) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public AccordoServizio makePersistent(Utente utente, AccordoServizio accordoServizio) throws DAOException {
        try {
            WSAccordoServizioImplService ss = new WSAccordoServizioImplService(new URL(utente.getServer() + DAOCostanti.URL_WSDL_ACCORDO_SERVIZIO), DAOCostanti.SERVICE_NAME_ACCORDO_SERVIZIO);
            IWSAccordoServizio port = ss.getWSAccordoServizioImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);

            it.unibas.icar.freesbee.ws.client.accordoservizio.cxf.AccordoServizio accordoServizioStub = new it.unibas.icar.freesbee.ws.client.accordoservizio.cxf.AccordoServizio();
            copiaProprietaModelloToStub(accordoServizioStub, accordoServizio);
            port.addAccordoServizio(accordoServizioStub);
            return accordoServizio;
        } catch (Exception ex) {
            if(logger.isDebugEnabled()) ex.printStackTrace();
            logger.error("Impossibile aggiungere l'accordo di servizio " + ex);
            throw new DAOException(ex);
        }
    }

    public void makeTransient(Utente utente, AccordoServizio accordoServizio) throws DAOException {
        try {
            WSAccordoServizioImplService ss = new WSAccordoServizioImplService(new URL(utente.getServer() + DAOCostanti.URL_WSDL_ACCORDO_SERVIZIO), DAOCostanti.SERVICE_NAME_ACCORDO_SERVIZIO);
            IWSAccordoServizio port = ss.getWSAccordoServizioImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);

            it.unibas.icar.freesbee.ws.client.accordoservizio.cxf.AccordoServizio accordoServizioStub = new it.unibas.icar.freesbee.ws.client.accordoservizio.cxf.AccordoServizio();
            copiaProprietaModelloToStub(accordoServizioStub, accordoServizio);
            port.removeAccordoServizio(accordoServizioStub.getId());
        } catch (Exception ex) {
            if(logger.isDebugEnabled()) ex.printStackTrace();
            logger.error("Impossibile eliminare l'accordo di servizio " + ex);
            throw new DAOException(ex);
        }
    }

    public void lock(Utente utente, AccordoServizio entity) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void copiaProprietaStubToModello(AccordoServizio as, it.unibas.icar.freesbee.ws.client.accordoservizio.cxf.AccordoServizio accordoServizio) {
        as.setId(accordoServizio.getId());
        as.setNome(accordoServizio.getNome());
        as.setDescrizione(accordoServizio.getDescrizione());
        as.setIdProfiloEGov(accordoServizio.getIdProfiloEGov());
        as.setPrivato(accordoServizio.isPrivato());
        as.setProfiloCollaborazione(accordoServizio.getProfiloCollaborazione());
        as.setPolicyXACML(accordoServizio.getPolicyXACML());
    }

    private void copiaProprietaModelloToStub(it.unibas.icar.freesbee.ws.client.accordoservizio.cxf.AccordoServizio accordoServizio, AccordoServizio as) {
        accordoServizio.setId(as.getId());
        accordoServizio.setNome(as.getNome());
        accordoServizio.setDescrizione(as.getDescrizione());
        accordoServizio.setIdProfiloEGov(as.getIdProfiloEGov());
        accordoServizio.setPrivato(as.isPrivato());
        accordoServizio.setProfiloCollaborazione(as.getProfiloCollaborazione());
        accordoServizio.setPolicyXACML(as.getPolicyXACML());
    }

    private void riempiRiferimenti(Utente utente, AccordoServizio accordo) throws DAOException {
        accordo.setProfiloEGov(daoProfiloEGov.findById(utente, accordo.getIdProfiloEGov(), false));
        List<Servizio> listaServizi = daoServizio.findByAccordo(utente, accordo);
        accordo.setServizi(listaServizi);
        for (Servizio servizio : listaServizi) {
            servizio.setAccordoServizio(accordo);
        }
        List<Azione> listaAzioni = daoAzione.findByAccordo(utente, accordo);
        accordo.setAzioni(listaAzioni);
        for (Azione azione : listaAzioni) {
            azione.setAccordoServizio(accordo);
        }

    }

    public IDAOProfiloEGov getDaoProfiloEGov() {
        return daoProfiloEGov;
    }

    public void setDaoProfiloEGov(IDAOProfiloEGov daoProfiloEGov) {
        this.daoProfiloEGov = daoProfiloEGov;
    }

    public IDAOServizio getDaoServizio() {
        return daoServizio;
    }

    public void setDaoServizio(IDAOServizio daoServizio) {
        this.daoServizio = daoServizio;
    }

    public IDAOAzione getDaoAzione() {
        return daoAzione;
    }

    public void setDaoAzione(IDAOAzione daoAzione) {
        this.daoAzione = daoAzione;
    }
}
