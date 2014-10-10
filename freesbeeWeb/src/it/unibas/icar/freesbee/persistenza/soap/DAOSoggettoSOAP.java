package it.unibas.icar.freesbee.persistenza.soap;

import it.unibas.icar.freesbee.modello.Soggetto;
import it.unibas.icar.freesbee.modello.Utente;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOSoggetto;
import it.unibas.icar.freesbee.ws.client.soggetto.cxf.IWSSoggetto;
import it.unibas.icar.freesbee.ws.client.soggetto.cxf.WSSoggettoImplService;
import it.unibas.icar.freesbee.ws.security.SecurityUtil;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DAOSoggettoSOAP implements IDAOSoggetto {

    private Log logger = LogFactory.getLog(this.getClass());

    public Soggetto findByNome(Utente utente, String nome, String tipo) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Soggetto findById(Utente utente, Long id, boolean lock) throws DAOException {
        Soggetto s = new Soggetto();
        try {
            WSSoggettoImplService ss = new WSSoggettoImplService(new URL(utente.getServer() + DAOCostanti.URL_WSDL_SOGGETTO), DAOCostanti.SERVICE_NAME_SOGGETTO);
            IWSSoggetto port = ss.getWSSoggettoImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente,port);
            
            it.unibas.icar.freesbee.ws.client.soggetto.cxf.Soggetto soggetto = port.getSoggetto(id);
            copiaProprietaStubToModello(s, soggetto);
        } catch (Exception ex) {
            logger.error("Impossibile leggere il soggetto " + ex);
            throw new DAOException(ex);
        }
        return s;
    }

    public List<Soggetto> findAll(Utente utente) throws DAOException {
        try {
            WSSoggettoImplService ss = new WSSoggettoImplService(new URL(utente.getServer() + DAOCostanti.URL_WSDL_SOGGETTO), DAOCostanti.SERVICE_NAME_SOGGETTO);
            IWSSoggetto port = ss.getWSSoggettoImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente,port);

            java.util.List<it.unibas.icar.freesbee.ws.client.soggetto.cxf.Soggetto> listaSoggetti = port.getListaSoggetti();
            List<Soggetto> lista = new ArrayList<Soggetto>();
            if (listaSoggetti == null) {
                return lista;
            }
            for (it.unibas.icar.freesbee.ws.client.soggetto.cxf.Soggetto soggetto : listaSoggetti) {
                Soggetto s = new Soggetto();
                copiaProprietaStubToModello(s, soggetto);
                lista.add(s);
            }
            return lista;
        } catch (Exception ex) {
            logger.error("Impossibile leggere i soggetti " + ex);
            throw new DAOException(ex);
        }

    }

    public List<Soggetto> findAll(Utente utente, int offset, int limite) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Soggetto makePersistent(Utente utente, Soggetto soggetto) throws DAOException {
        try {
            WSSoggettoImplService ss = new WSSoggettoImplService(new URL(utente.getServer() + DAOCostanti.URL_WSDL_SOGGETTO), DAOCostanti.SERVICE_NAME_SOGGETTO);
            IWSSoggetto port = ss.getWSSoggettoImplPort();
            
            SecurityUtil.settaIntestazioniSicurezza(utente,port);
            
            it.unibas.icar.freesbee.ws.client.soggetto.cxf.Soggetto soggettoStub = new it.unibas.icar.freesbee.ws.client.soggetto.cxf.Soggetto();
            copiaProprietaModelloToStub(soggettoStub, soggetto);
            port.addSoggetto(soggettoStub);
            return soggetto;
        } catch (Exception ex) {
            logger.error("Impossibile aggiungere il soggetto " + ex);
            throw new DAOException(ex);
        }
    }

    public void makeTransient(Utente utente, Soggetto soggetto) throws DAOException {
        try {
            WSSoggettoImplService ss = new WSSoggettoImplService(new URL(utente.getServer() + DAOCostanti.URL_WSDL_SOGGETTO), DAOCostanti.SERVICE_NAME_SOGGETTO);
            IWSSoggetto port = ss.getWSSoggettoImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente,port);

            it.unibas.icar.freesbee.ws.client.soggetto.cxf.Soggetto soggettoStub = new it.unibas.icar.freesbee.ws.client.soggetto.cxf.Soggetto();
            copiaProprietaModelloToStub(soggettoStub, soggetto);
            port.removeSoggetto(soggettoStub.getId());
        } catch (Exception ex) {
            logger.error("Impossibile eliminare il soggetto " + ex);
            throw new DAOException(ex);
        }
    }

    public void lock(Utente utente, Soggetto entity) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void copiaProprietaStubToModello(Soggetto soggetto, it.unibas.icar.freesbee.ws.client.soggetto.cxf.Soggetto soggettoStub) {
        soggetto.setId(soggettoStub.getId());
        soggetto.setNome(soggettoStub.getNome());
        soggetto.setTipo(soggettoStub.getTipo());
        soggetto.setDescrizione(soggettoStub.getDescrizione());
        soggetto.setPortaDominio(soggettoStub.getPortaDominio());
    }

    private void copiaProprietaModelloToStub(it.unibas.icar.freesbee.ws.client.soggetto.cxf.Soggetto soggettoStub, Soggetto soggetto) {
        soggettoStub.setId(soggetto.getId());
        soggettoStub.setNome(soggetto.getNome());
        soggettoStub.setTipo(soggetto.getTipo());
        soggettoStub.setDescrizione(soggetto.getDescrizione());
        soggettoStub.setPortaDominio(soggetto.getPortaDominio());
    }
}
