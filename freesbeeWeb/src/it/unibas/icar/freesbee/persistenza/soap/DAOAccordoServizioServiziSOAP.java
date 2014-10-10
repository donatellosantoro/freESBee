package it.unibas.icar.freesbee.persistenza.soap;

import it.unibas.icar.freesbee.modello.AccordoServizio;
import it.unibas.icar.freesbee.modello.Utente;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOAccordoServizio;
import it.unibas.icar.freesbee.persistenza.IDAOProfiloEGov;
import it.unibas.icar.freesbee.ws.client.accordoservizio.cxf.IWSAccordoServizio;
import it.unibas.icar.freesbee.ws.client.accordoservizio.cxf.WSAccordoServizioImplService;
import it.unibas.icar.freesbee.ws.security.SecurityUtil;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DAOAccordoServizioServiziSOAP implements IDAOAccordoServizio {

    private Log logger = LogFactory.getLog(this.getClass());
    private IDAOProfiloEGov daoProfiloEGov;

    public AccordoServizio findByNome(Utente utente, String nome) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<AccordoServizio> findAllNoLazy(Utente utente) throws DAOException {
        return findAll(utente);
    }

    public AccordoServizio findById(Utente utente, Long id, boolean lock) throws DAOException {
        AccordoServizio as = new AccordoServizio();
        try {
            WSAccordoServizioImplService ss = new WSAccordoServizioImplService(new URL(utente.getServer() + DAOCostanti.URL_WSDL_ACCORDO_SERVIZIO), DAOCostanti.SERVICE_NAME_ACCORDO_SERVIZIO);
            IWSAccordoServizio port = ss.getWSAccordoServizioImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente,port);

            it.unibas.icar.freesbee.ws.client.accordoservizio.cxf.AccordoServizio accordoServizio = port.getAccordoServizio(id);
            copiaProprietaStubToModello(as, accordoServizio);
            riempiRiferimenti(utente,as);
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

            SecurityUtil.settaIntestazioniSicurezza(utente,port);

            java.util.List<it.unibas.icar.freesbee.ws.client.accordoservizio.cxf.AccordoServizio> listaAccordiServizio = port.getListaAccordiServizio();
            List<AccordoServizio> lista = new ArrayList<AccordoServizio>();
            if (listaAccordiServizio == null) {
                return lista;
            }
            for (it.unibas.icar.freesbee.ws.client.accordoservizio.cxf.AccordoServizio accordoServizio : listaAccordiServizio) {
                AccordoServizio as = new AccordoServizio();
                copiaProprietaStubToModello(as, accordoServizio);
                lista.add(as);
                riempiRiferimenti(utente,as);
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void makeTransient(Utente utente, AccordoServizio accordoServizio) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
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
    }

    private void riempiRiferimenti(Utente utente, AccordoServizio accordo) throws DAOException {
        accordo.setProfiloEGov(getDaoProfiloEGov().findById(utente,accordo.getIdProfiloEGov(), false));
    }

    private void settaRiferimenti(Utente utente, AccordoServizio accordo) {
        accordo.setIdProfiloEGov(accordo.getProfiloEGov().getId());
    }

    public IDAOProfiloEGov getDaoProfiloEGov() {
        return daoProfiloEGov;
    }

    public void setDaoProfiloEGov(IDAOProfiloEGov daoProfiloEGov) {
        this.daoProfiloEGov = daoProfiloEGov;
    }
}
