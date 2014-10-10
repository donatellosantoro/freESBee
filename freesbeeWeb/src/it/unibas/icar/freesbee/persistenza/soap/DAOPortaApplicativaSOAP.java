package it.unibas.icar.freesbee.persistenza.soap;

import it.unibas.icar.freesbee.modello.Azione;
import it.unibas.icar.freesbee.modello.PortaApplicativa;
import it.unibas.icar.freesbee.modello.Servizio;
import it.unibas.icar.freesbee.modello.Utente;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOAzione;
import it.unibas.icar.freesbee.persistenza.IDAOPortaApplicativa;
import it.unibas.icar.freesbee.persistenza.IDAOServizio;
import it.unibas.icar.freesbee.persistenza.IDAOServizioApplicativo;
import it.unibas.icar.freesbee.ws.client.portaapplicativa.cxf.IWSPortaApplicativa;
import it.unibas.icar.freesbee.ws.client.portaapplicativa.cxf.WSPortaApplicativaImplService;
import it.unibas.icar.freesbee.ws.security.SecurityUtil;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DAOPortaApplicativaSOAP implements IDAOPortaApplicativa {

    private Log logger = LogFactory.getLog(this.getClass());
    private IDAOServizio daoServizio;
    private IDAOServizioApplicativo daoServizioApplicativo;
    private IDAOAzione daoAzione;

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

    public IDAOServizioApplicativo getDaoServizioApplicativo() {
        return daoServizioApplicativo;
    }

    public void setDaoServizioApplicativo(IDAOServizioApplicativo daoServizioApplicativo) {
        this.daoServizioApplicativo = daoServizioApplicativo;
    }

    public PortaApplicativa findByNome(Utente utente, String nome) throws DAOException {
        List<PortaApplicativa> listaPorte = findAll(utente);
        for (PortaApplicativa portaApplicativa : listaPorte) {
            if (portaApplicativa.getNome().equalsIgnoreCase(nome)) {
                riempiRiferimenti(utente, portaApplicativa);
                return portaApplicativa;
            }
        }
        return null;
    }

    public PortaApplicativa findByServizio(Utente utente, Servizio servizio, Azione azione) throws DAOException {
        if (azione == null) {
            return findByServizio(utente, servizio.getTipo(), servizio.getNome(),servizio.getErogatore().getTipo(),servizio.getErogatore().getNome(), null);
        } else {
            return findByServizio(utente, servizio.getTipo(), servizio.getNome(),servizio.getErogatore().getTipo(),servizio.getErogatore().getNome(), azione.getNome());
        }
    }

    public PortaApplicativa findByServizio(Utente utente,
                                        String stringaTipoServizio, String stringaServizio,
                                        String stringaTipoErogatore, String stringaErogatore,
                                        String stringaAzione) throws DAOException {
        List<PortaApplicativa> listaPorte = findAll(utente);
        for (PortaApplicativa portaApplicativa : listaPorte) {
            String stringaTipoServizioComparare = "";
            String stringaServizioComparare = "";
            String stringaTipoErogatoreComparare = "";
            String stringaErogatoreComparare = "";
            String stringaAzioneComparare = "";
            if (portaApplicativa.getServizio() != null) {
                stringaTipoServizioComparare = portaApplicativa.getServizio().getTipo();
                stringaServizioComparare = portaApplicativa.getServizio().getNome();
            } else {
                stringaTipoServizioComparare = portaApplicativa.getStringaTipoServizio();
                stringaServizioComparare = portaApplicativa.getStringaServizio();
            }

            if (portaApplicativa.getServizio() != null && portaApplicativa.getServizio().getErogatore() != null) {
                stringaTipoErogatoreComparare = portaApplicativa.getServizio().getErogatore().getTipo();
                stringaErogatoreComparare = portaApplicativa.getServizio().getErogatore().getNome();
            } else {
                stringaTipoErogatoreComparare = portaApplicativa.getStringaTipoErogatore();
                stringaErogatoreComparare = portaApplicativa.getStringaErogatore();
            }

            if (portaApplicativa.getAzione() != null) {
                stringaAzioneComparare = portaApplicativa.getAzione().getNome();
            } else {
                stringaAzioneComparare = portaApplicativa.getStringaAzione();
            }
            if (stringaTipoServizioComparare.equals(stringaTipoServizio) 
             && stringaServizioComparare.equals(stringaServizio) 
             && stringaTipoErogatoreComparare.equals(stringaTipoErogatore) 
             && stringaErogatoreComparare.equals(stringaErogatore)
             && comparaStringhe(stringaAzioneComparare, stringaAzione)) {
                riempiRiferimenti(utente, portaApplicativa);
                return portaApplicativa;
            }
        }
        return null;
    }

    public PortaApplicativa findById(Utente utente, Long id, boolean lock) throws DAOException {
        PortaApplicativa pa = new PortaApplicativa();
        try {
            WSPortaApplicativaImplService ss = new WSPortaApplicativaImplService(new URL(utente.getServer() + DAOCostanti.URL_WSDL_PORTA_APPLICATIVA), DAOCostanti.SERVICE_NAME_PORTA_APPLICATIVA);
            IWSPortaApplicativa port = ss.getWSPortaApplicativaImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);

            it.unibas.icar.freesbee.ws.client.portaapplicativa.cxf.PortaApplicativa portaApplicativa = port.getPortaApplicativa(id);
            copiaProprietaStubToModello(pa, portaApplicativa);
            riempiRiferimenti(utente, pa);
        } catch (Exception ex) {
            logger.error("Impossibile leggere la porta applicativa " + ex);
            throw new DAOException(ex);
        }
        return pa;
    }

    public List<PortaApplicativa> findAll(Utente utente) throws DAOException {
        try {
            List<PortaApplicativa> lista = new ArrayList();
            WSPortaApplicativaImplService ss = new WSPortaApplicativaImplService(new URL(utente.getServer() + DAOCostanti.URL_WSDL_PORTA_APPLICATIVA), DAOCostanti.SERVICE_NAME_PORTA_APPLICATIVA);
            IWSPortaApplicativa port = ss.getWSPortaApplicativaImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);

            List<it.unibas.icar.freesbee.ws.client.portaapplicativa.cxf.PortaApplicativa> listaPorteApplicative = port.getListaPorteApplicative();
            if (listaPorteApplicative == null) {
                return lista;
            }
            for (it.unibas.icar.freesbee.ws.client.portaapplicativa.cxf.PortaApplicativa portaApplicativa : listaPorteApplicative) {
                PortaApplicativa pa = new PortaApplicativa();
                copiaProprietaStubToModello(pa, portaApplicativa);
                riempiRiferimenti(utente, pa);
                lista.add(pa);
            }
            return lista;
        } catch (Exception ex) {
            logger.error("Impossibile leggere le porte applicative " + ex);
            if (logger.isDebugEnabled()) {
                ex.printStackTrace();
            }
            throw new DAOException(ex);
        }
    }

    public List<PortaApplicativa> findAll(Utente utente, int offset, int limite) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public PortaApplicativa makePersistent(Utente utente, PortaApplicativa portaApplicativa) throws DAOException {
        try {
            WSPortaApplicativaImplService ss = new WSPortaApplicativaImplService(new URL(utente.getServer() + DAOCostanti.URL_WSDL_PORTA_APPLICATIVA), DAOCostanti.SERVICE_NAME_PORTA_APPLICATIVA);
            IWSPortaApplicativa port = ss.getWSPortaApplicativaImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);

            it.unibas.icar.freesbee.ws.client.portaapplicativa.cxf.PortaApplicativa pa = new it.unibas.icar.freesbee.ws.client.portaapplicativa.cxf.PortaApplicativa();
            settaRiferimenti(portaApplicativa);
            copiaProprietaModelloToStub(pa, portaApplicativa);
            port.addPortaApplicativa(pa);
            return portaApplicativa;
        } catch (Exception ex) {
            if (logger.isDebugEnabled()) {
                ex.printStackTrace();
            }
            logger.error("Impossibile aggiungere la porta applicativa " + ex);
            throw new DAOException(ex);
        }
    }

    public void makeTransient(Utente utente, PortaApplicativa portaApplicativa) throws DAOException {
        try {
            WSPortaApplicativaImplService ss = new WSPortaApplicativaImplService(new URL(utente.getServer() + DAOCostanti.URL_WSDL_PORTA_APPLICATIVA), DAOCostanti.SERVICE_NAME_PORTA_APPLICATIVA);
            IWSPortaApplicativa port = ss.getWSPortaApplicativaImplPort();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);

            it.unibas.icar.freesbee.ws.client.portaapplicativa.cxf.PortaApplicativa portaApplicativaStub = new it.unibas.icar.freesbee.ws.client.portaapplicativa.cxf.PortaApplicativa();
            copiaProprietaModelloToStub(portaApplicativaStub, portaApplicativa);
            port.removePortaApplicativa(portaApplicativaStub);
        } catch (Exception ex) {
            logger.error("Impossibile eliminare la porta applicativa " + ex);
            throw new DAOException(ex);
        }
    }

    public void lock(Utente utente, PortaApplicativa entity) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private boolean comparaStringhe(String stringaAzioneComparare, String stringaAzione) {
        if (stringaAzioneComparare == null && stringaAzione == null) {
            return true;
        }
        if (stringaAzioneComparare == null) {
            return false;
        }
        if (stringaAzione == null) {
            return false;
        }
        return stringaAzioneComparare.equalsIgnoreCase(stringaAzione);
    }

    private void copiaProprietaStubToModello(PortaApplicativa pa, it.unibas.icar.freesbee.ws.client.portaapplicativa.cxf.PortaApplicativa portaApplicativa) {
        pa.setIdAzione(portaApplicativa.getIdAzione());
        pa.setIdServizio(portaApplicativa.getIdServizio());
        pa.setIdServizioApplicativo(portaApplicativa.getIdServizioApplicativo());
        pa.setNome(portaApplicativa.getNome());
        pa.setDescrizione(portaApplicativa.getDescrizione());
        pa.setId(portaApplicativa.getId());
        pa.setStringaAzione(portaApplicativa.getStringaAzione());
        pa.setStringaTipoServizio(portaApplicativa.getStringaTipoServizio());
        pa.setStringaServizio(portaApplicativa.getStringaServizio());
        pa.setStringaTipoErogatore(portaApplicativa.getStringaTipoErogatore());
        pa.setStringaErogatore(portaApplicativa.getStringaErogatore());
    }

    private void copiaProprietaModelloToStub(it.unibas.icar.freesbee.ws.client.portaapplicativa.cxf.PortaApplicativa pa, PortaApplicativa portaApplicativa) {
        pa.setIdAzione(portaApplicativa.getIdAzione());
        pa.setIdServizio(portaApplicativa.getIdServizio());
        pa.setIdServizioApplicativo(portaApplicativa.getIdServizioApplicativo());
        pa.setNome(portaApplicativa.getNome());
        pa.setDescrizione(portaApplicativa.getDescrizione());
        pa.setId(portaApplicativa.getId());
        pa.setStringaAzione(portaApplicativa.getStringaAzione());
        pa.setStringaTipoServizio(portaApplicativa.getStringaTipoServizio());
        pa.setStringaServizio(portaApplicativa.getStringaServizio());
        pa.setStringaTipoErogatore(portaApplicativa.getStringaTipoErogatore());
        pa.setStringaErogatore(portaApplicativa.getStringaErogatore());
    }

    private void riempiRiferimenti(Utente utente, PortaApplicativa pa) throws DAOException {
        if (pa.getIdAzione() != null && pa.getIdAzione() > 0) {
            pa.setAzione(daoAzione.findById(utente, pa.getIdAzione(), false));
        }
        if (pa.getIdServizio() != null && pa.getIdServizio() > 0) {
            pa.setServizio(daoServizio.findById(utente, pa.getIdServizio(), false));
        }
        if (pa.getIdServizioApplicativo() != null && pa.getIdServizioApplicativo() > 0) {
            pa.setServizioApplicativo(daoServizioApplicativo.findById(utente, pa.getIdServizioApplicativo(), false));
        }
    }

    private void settaRiferimenti(PortaApplicativa pa) {
        if (pa.getAzione() != null) {
            pa.setIdAzione(pa.getAzione().getId());
        }
        if (pa.getServizio() != null) {
            pa.setIdServizio(pa.getServizio().getId());
        }
        if (pa.getServizioApplicativo() != null) {
            pa.setIdServizioApplicativo(pa.getServizioApplicativo().getId());
        }
    }
}
