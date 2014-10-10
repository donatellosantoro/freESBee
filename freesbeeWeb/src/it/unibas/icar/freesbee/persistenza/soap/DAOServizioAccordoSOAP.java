package it.unibas.icar.freesbee.persistenza.soap;

import it.unibas.icar.freesbee.modello.AccordoServizio;
import it.unibas.icar.freesbee.modello.Servizio;
import it.unibas.icar.freesbee.modello.Soggetto;
import it.unibas.icar.freesbee.modello.Utente;
import it.unibas.icar.freesbee.persistenza.DAOException;
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

public class DAOServizioAccordoSOAP implements IDAOServizio {

    private Log logger = LogFactory.getLog(this.getClass());
    private String urlWS = "http://localhost:8191/ws/servizio";
    private IDAOSoggetto daoSoggetto;
    
    public IDAOSoggetto getDaoSoggetto() {
        return daoSoggetto;
    }

    public void setDaoSoggetto(IDAOSoggetto daoSoggetto) {
        this.daoSoggetto = daoSoggetto;
    }

    public Servizio findByNome(Utente utente, String nome) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public Servizio findByNome(Utente utente, String nome, String tipo, Soggetto erogatore) throws DAOException {        
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public List<Servizio> findByAccordo(Utente utente, AccordoServizio accordo) throws DAOException {
        List<Servizio> listaServizi = findAll(utente);
        List<Servizio> lista = new ArrayList<Servizio>();
        for (Servizio servizio : listaServizi) {
            if (servizio.getIdAccordoServizio()==accordo.getId()) {
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Servizio> findAll(Utente utente) throws DAOException {
        try {
            WSServizioImplService ss = new WSServizioImplService(new URL(utente.getServer() + DAOCostanti.URL_WSDL_SERVIZIO), DAOCostanti.SERVICE_NAME_SERVIZIO);
            IWSServizio port = ss.getWSServizioImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente,port);

            java.util.List<it.unibas.icar.freesbee.ws.client.servizio.cxf.Servizio> listaServizi = port.getListaServizi();
            List<Servizio> lista = new ArrayList<Servizio>();
            if (listaServizi == null) {
                return lista;
            }
            for (it.unibas.icar.freesbee.ws.client.servizio.cxf.Servizio servizio : listaServizi) {
                Servizio s = new Servizio();
                copiaProprietaStubToModello(s, servizio);
                riempiRiferimenti(utente,s);
                lista.add(s);
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void makeTransient(Utente utente, Servizio servizio) throws DAOException {        
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void lock(Utente utente, Servizio entity) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private void copiaProprietaStubToModello(Servizio servizio, it.unibas.icar.freesbee.ws.client.servizio.cxf.Servizio servizioStub) {
        servizio.setId(servizioStub.getId());
        servizio.setIdAccordoServizio(servizioStub.getIdAccordoServizio());
        servizio.setIdErogatore(servizioStub.getIdErogatore());
        servizio.setNome(servizioStub.getNome());
        servizio.setPrivato(servizioStub.isPrivato());
        servizio.setTipo(servizioStub.getTipo());
        servizio.setCorrelato(servizioStub.isCorrelato());

        List<Long> listaIdFruitori = TrasformaLong(servizio, servizioStub);
        servizio.setIdFruitori(listaIdFruitori);
    }

    private List<Long> TrasformaLong(Servizio servizio, it.unibas.icar.freesbee.ws.client.servizio.cxf.Servizio servizioStub) {
        List<Long> listaIdFruitori = new ArrayList<Long>();
        if(servizioStub.getIdFruitori() == null){
            return listaIdFruitori;
        }
        for (long id : servizioStub.getIdFruitori()) {
            listaIdFruitori.add(id);
        }
        return listaIdFruitori;
    }
    
    private void riempiRiferimenti(Utente utente, Servizio servizio) throws DAOException {
        servizio.setErogatore(daoSoggetto.findById(utente,servizio.getIdErogatore(), false));
//        servizio.setPortaApplicativa(daoPortaApplicativa.findById(servizio.getIdPortaApplicativa(), false));
        servizio.setFruitori(riempiListaSoggettiFruitori(utente,servizio.getIdFruitori()));
    }
    
    private List<Soggetto> riempiListaSoggettiFruitori(Utente utente, List<Long> idFruitori) throws DAOException {
        List<Soggetto> listaFruitori = new ArrayList<Soggetto>();
        for (Iterator<Long> iterator = idFruitori.iterator(); iterator.hasNext();) {
            long idFruitore = (long)iterator.next();
            Soggetto soggettoFruitore = daoSoggetto.findById(utente,idFruitore, false);
            listaFruitori.add(soggettoFruitore);
        }
        return listaFruitori;
    }    
    
    private void settaRiferimenti(Servizio servizio) {
        servizio.setIdAccordoServizio(servizio.getAccordoServizio().getId());
        servizio.setIdErogatore(servizio.getErogatore().getId());
        List<Long> listaFruitori = new ArrayList<Long>();
        List<Soggetto> fruitori = servizio.getFruitori();
        for (Soggetto fruitore : fruitori) {
            listaFruitori.add(new Long(fruitore.getId()));
        }
        servizio.setIdFruitori(listaFruitori);
    }
}
