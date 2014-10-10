package it.unibas.icar.freesbee.persistenza.soap;

import it.unibas.icar.freesbee.modello.AccordoServizio;
import it.unibas.icar.freesbee.modello.Azione;
import it.unibas.icar.freesbee.modello.Utente;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOAccordoServizio;
import it.unibas.icar.freesbee.persistenza.IDAOAzione;
import it.unibas.icar.freesbee.persistenza.IDAOProfiloEGov;
import it.unibas.icar.freesbee.ws.client.azione.cxf.IWSAzione;
import it.unibas.icar.freesbee.ws.client.azione.cxf.WSAzioneImplService;
import it.unibas.icar.freesbee.ws.security.SecurityUtil;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DAOAzioneSOAP implements IDAOAzione {

    private Log logger = LogFactory.getLog(this.getClass());
    private IDAOAccordoServizio daoAccordoServizio;
    private IDAOProfiloEGov daoProfiloEGov;

    public IDAOProfiloEGov getDaoProfiloEGov() {
        return daoProfiloEGov;
    }

    public void setDaoProfiloEGov(IDAOProfiloEGov daoProfiloEGov) {
        this.daoProfiloEGov = daoProfiloEGov;
    }

    public IDAOAccordoServizio getDaoAccordoServizio() {
        return daoAccordoServizio;
    }

    public void setDaoAccordoServizio(IDAOAccordoServizio daoAccordoServizio) {
        this.daoAccordoServizio = daoAccordoServizio;
    }

    public Azione findById(Utente utente, Long id, boolean lock) throws DAOException {
        Azione azione = new Azione();
        try {
            WSAzioneImplService ss = new WSAzioneImplService(new URL(utente.getServer() + DAOCostanti.URL_WSDL_AZIONE), DAOCostanti.SERVICE_NAME_AZIONE);
            IWSAzione port = ss.getWSAzioneImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente,port);

            it.unibas.icar.freesbee.ws.client.azione.cxf.Azione azioneStub = port.getAzione(id);
            copiaProprietaStubToModello(azione, azioneStub);
            riempiRiferimenti(utente,azione);
        } catch (Exception ex) {
            logger.error("Impossibile leggere l'azione " + ex);
            throw new DAOException(ex);
        }
        return azione;
    }

    public List<Azione> findAll(Utente utente) throws DAOException {
        try {
            WSAzioneImplService ss = new WSAzioneImplService(new URL(utente.getServer() + DAOCostanti.URL_WSDL_AZIONE), DAOCostanti.SERVICE_NAME_AZIONE);
            IWSAzione port = ss.getWSAzioneImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente,port);

            java.util.List<it.unibas.icar.freesbee.ws.client.azione.cxf.Azione> listaAzioni = port.getListaAzioni();
            List<Azione> lista = new ArrayList<Azione>();
            if (listaAzioni == null) {
                return lista;
            }
            for (it.unibas.icar.freesbee.ws.client.azione.cxf.Azione azione : listaAzioni) {
                Azione a = new Azione();
                copiaProprietaStubToModello(a, azione);
                lista.add(a);
                riempiRiferimenti(utente,a);
            }
            return lista;
        } catch (Exception ex) {
            logger.error("Impossibile leggere le azioni " + ex);
            throw new DAOException(ex);
        }
    }

    public List<Azione> findAll(Utente utente, int offset, int limite) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Azione makePersistent(Utente utente, Azione azione) throws DAOException {
        try {
            WSAzioneImplService ss = new WSAzioneImplService(new URL(utente.getServer() + DAOCostanti.URL_WSDL_AZIONE), DAOCostanti.SERVICE_NAME_AZIONE);
            IWSAzione port = ss.getWSAzioneImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente,port);

            it.unibas.icar.freesbee.ws.client.azione.cxf.Azione azioneStub = new it.unibas.icar.freesbee.ws.client.azione.cxf.Azione();
            copiaProprietaModelloToStub(azioneStub, azione);
            port.addAzione(azioneStub);
            return azione;
        } catch (Exception ex) {
            logger.error("Impossibile aggiungere l'azione " + ex);
            throw new DAOException(ex);
        }
    }

    public void makeTransient(Utente utente, Azione azione) throws DAOException {
        try {
            WSAzioneImplService ss = new WSAzioneImplService(new URL(utente.getServer() + DAOCostanti.URL_WSDL_AZIONE), DAOCostanti.SERVICE_NAME_AZIONE);
            IWSAzione port = ss.getWSAzioneImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente,port);

            it.unibas.icar.freesbee.ws.client.azione.cxf.Azione azioneStub = new it.unibas.icar.freesbee.ws.client.azione.cxf.Azione();
            copiaProprietaModelloToStub(azioneStub, azione);
            port.removeAzione(azioneStub.getId());
        } catch (Exception ex) {
            logger.error("Impossibile eliminare l'azione " + ex);
            throw new DAOException(ex);
        }
    }

    public void lock(Utente utente, Azione azione) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private void copiaProprietaStubToModello(Azione azione, it.unibas.icar.freesbee.ws.client.azione.cxf.Azione azioneStub) {
        azione.setId(azioneStub.getId());
        azione.setNome(azioneStub.getNome());
        azione.setDescrizione(azioneStub.getDescrizione());
        azione.setIdAccordoServizio(azioneStub.getIdAccordoServizio());
        azione.setIdProfiloEGov(azioneStub.getIdProfiloEGov());
        azione.setProfiloCollaborazione(azioneStub.getProfiloCollaborazione());
    }

    private void copiaProprietaModelloToStub(it.unibas.icar.freesbee.ws.client.azione.cxf.Azione azioneStub, Azione azione) {
        azioneStub.setId(azione.getId());
        azioneStub.setNome(azione.getNome());
        azioneStub.setDescrizione(azione.getDescrizione());
        azioneStub.setIdAccordoServizio(azione.getIdAccordoServizio());
        azioneStub.setIdProfiloEGov(azione.getIdProfiloEGov());
        azioneStub.setProfiloCollaborazione(azione.getProfiloCollaborazione());
    }

    private void riempiRiferimenti(Utente utente, Azione azione) throws DAOException {
        azione.setAccordoServizio(daoAccordoServizio.findById(utente,azione.getIdAccordoServizio(), false));
        if (azione.getIdProfiloEGov() > 0) {
            azione.setProfiloEGov(daoProfiloEGov.findById(utente,azione.getIdProfiloEGov(), false));
        }
    }

    private void settaRiferimenti(Azione azione) {
        azione.setIdAccordoServizio(azione.getAccordoServizio().getId());
        if (azione.getProfiloEGov() != null) {
            azione.setIdProfiloEGov(azione.getProfiloEGov().getId());
        }
    }

    public List<Azione> findByAccordo(Utente utente, AccordoServizio accordo) throws DAOException {
        List<Azione> listaAzioni = findAll(utente);
        List<Azione> lista = new ArrayList<Azione>();
        for (Azione azione : listaAzioni) {
            if (azione.getAccordoServizio().getId() == accordo.getId()) {
                riempiRiferimenti(utente,azione);
                lista.add(azione);
            }
        }
        return lista;
    }
}
