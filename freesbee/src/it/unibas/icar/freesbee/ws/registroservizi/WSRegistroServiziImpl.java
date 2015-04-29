package it.unibas.icar.freesbee.ws.registroservizi;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import it.unibas.icar.freesbee.modello.AccordoServizio;
import it.unibas.icar.freesbee.modello.Servizio;
import it.unibas.icar.freesbee.modello.Soggetto;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOAccordoServizio;
import it.unibas.icar.freesbee.persistenza.IDAOServizio;
import it.unibas.icar.freesbee.persistenza.IDAOSoggetto;
import it.unibas.icar.freesbee.persistenza.SOAPFault;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.LoggerFactory;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

@Singleton
public class WSRegistroServiziImpl implements IWSRegistroServizi {

//    private static Log logger = LogFactory.getLog(WSRegistroServiziImpl.class);
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(WSRegistroServiziImpl.class.getName());
    @Inject
    private IDAOAccordoServizio daoAccordoServizio;
    @Inject
    private IDAOSoggetto daoSoggetto;
    @Inject
    private IDAOServizio daoServizio;

    public boolean existsAccordoServizio(String indirizzo, AccordoServizioRS accordo) throws SOAPFault, DAOException {
        AccordoServizio trovato = daoAccordoServizio.findByNome(accordo.getNome());
        return trovato != null;
    }

    public AccordoServizioRSRisposta getAccordoServizio(String indirizzo, AccordoServizioRS accordo) throws SOAPFault, DAOException {
        AccordoServizio trovato = daoAccordoServizio.findByNome(accordo.getNome());
        if (trovato == null) {
            throw new SOAPFault("Accordo di Servizio non trovato");
        }
        return new AccordoServizioRSRisposta(trovato);
    }

    public boolean existsServizioSpcoop(String indirizzo, ServizioRS servizio) throws SOAPFault, DAOException {
        Soggetto soggettoErogatore = daoSoggetto.findByNome(servizio.getSoggettoErogatore().getNome(), servizio.getSoggettoErogatore().getTipo());
        if (soggettoErogatore == null) {
            throw new SOAPFault("Soggetto erogatore non trovato");
        }
        Servizio trovato = daoServizio.findByNome(servizio.getNome(), servizio.getTipo(), soggettoErogatore);
        return trovato != null;
    }

    public boolean existsServizioSpcoopCorrelato(String indirizzo, ServizioRS servizio) throws SOAPFault, DAOException {
        Soggetto soggettoErogatore = daoSoggetto.findByNome(servizio.getSoggettoErogatore().getNome(), servizio.getSoggettoErogatore().getTipo());
        if (soggettoErogatore == null) {
            throw new SOAPFault("Soggetto erogatore non trovato");
        }
        Servizio trovato = daoServizio.findByNome(servizio.getNome(), servizio.getTipo(), soggettoErogatore, true);
        return trovato != null;
    }

    public ServizioRSRisposta getServizioSPCoop(String indirizzo, ServizioRS servizio) throws SOAPFault, DAOException {
        if (servizio == null) {
            throw new SOAPFault("Servizio nullo");
        }
        Soggetto soggettoErogatore = daoSoggetto.findByNome(servizio.getSoggettoErogatore().getNome(), servizio.getSoggettoErogatore().getTipo());
        if (soggettoErogatore == null) {
            throw new SOAPFault("Soggetto erogatore non trovato");
        }
        Servizio trovato = daoServizio.findByNome(servizio.getNome(), servizio.getTipo(), soggettoErogatore);
        if (trovato == null) {
            throw new SOAPFault("Servizio non trovato");
        }
        return new ServizioRSRisposta(trovato, true);
    }

    public ServizioRSRisposta getServizioSPCoopCorrelato(String indirizzo, ServizioRS servizio) throws SOAPFault, DAOException {
        Soggetto soggettoErogatore = daoSoggetto.findByNome(servizio.getSoggettoErogatore().getNome(), servizio.getSoggettoErogatore().getTipo());
        if (soggettoErogatore == null) {
            throw new SOAPFault("Soggetto erogatore non trovato");
        }
        Servizio trovato = daoServizio.findByNome(servizio.getNome(), servizio.getTipo(), soggettoErogatore, true);
        if (trovato == null) {
            throw new SOAPFault("Servizio non trovato");
        }
        return new ServizioRSRisposta(trovato, true);
    }

    public ServizioRSRisposta getServizioSPCoopCorrelatoByAccordo(String indirizzo, SoggettoRS soggettoRS, AccordoServizioRS accordoRS) throws SOAPFault, DAOException {
        AccordoServizio accordo = daoAccordoServizio.findByNome(accordoRS.getNome());
        Soggetto soggetto = daoSoggetto.findByNome(soggettoRS.getNome(), soggettoRS.getTipo());
        Servizio correlato = daoServizio.findCorrelato(accordo, soggetto);
        if (correlato == null) {
            return null;
        }
        return new ServizioRSRisposta(correlato, true);
    }

    public boolean existsSoggettoSPCoop(String indirizzo, SoggettoRS soggetto) throws SOAPFault, DAOException {
        Soggetto trovato = daoSoggetto.findByNome(soggetto.getNome(), soggetto.getTipo());
        return trovato != null;
    }

    public SoggettoRSRisposta getSoggettoSPCoop(String indirizzo, SoggettoRS soggetto) throws SOAPFault, DAOException {
        Soggetto trovato = daoSoggetto.findByNome(soggetto.getNome(), soggetto.getTipo());
        if (trovato == null) {
            throw new SOAPFault("Soggetto non trovato");
        }
        return new SoggettoRSRisposta(trovato);
    }

    public boolean existsFruitoreServizioSpcoop(String indirizzo, ServizioRS servizio, SoggettoRS soggettoFruitoreRS) throws SOAPFault, DAOException {
        ServizioRSRisposta trovato = this.getServizioSPCoop(null, servizio);
        for (SoggettoRSRisposta soggetto : trovato.getFruitori()) {
            if (soggetto.compareTo(soggettoFruitoreRS) == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean existsFruitoreServizioSpcoopCorrelato(String indirizzo, ServizioRS servizio, SoggettoRS soggettoFruitoreRS) throws SOAPFault, DAOException {
        ServizioRSRisposta trovato = this.getServizioSPCoopCorrelato(null, servizio);
        for (SoggettoRSRisposta soggetto : trovato.getFruitori()) {
            if (soggetto.compareTo(soggettoFruitoreRS) == 0) {
                return true;
            }
        }
        return false;
    }

    public SoggettoRSRisposta getFruitoreServizioSpcoop(String indirizzo, ServizioRS servizio, SoggettoRS soggettoFruitoreRS) throws SOAPFault, DAOException {
        Soggetto soggettoFruitore = daoSoggetto.findByNome(soggettoFruitoreRS.getNome(), soggettoFruitoreRS.getTipo());
        if (soggettoFruitore == null) {
            throw new SOAPFault("Soggetto fruitore non trovato");
        }
        ServizioRSRisposta trovato = this.getServizioSPCoop(null, servizio);
        if (trovato == null) {
            throw new SOAPFault("Servizio non trovato");
        }
        return new SoggettoRSRisposta(soggettoFruitore);
    }

    public SoggettoRSRisposta getFruitoreServizioSpcoopCorrelato(String indirizzo, ServizioRS servizio, SoggettoRS soggettoFruitoreRS) throws SOAPFault, DAOException {
        Soggetto soggettoFruitore = daoSoggetto.findByNome(soggettoFruitoreRS.getNome(), soggettoFruitoreRS.getTipo());
        if (soggettoFruitore == null) {
            throw new SOAPFault("Soggetto fruitore non trovato");
        }
        ServizioRSRisposta trovato = this.getServizioSPCoopCorrelato(null, servizio);
        if (trovato == null) {
            throw new SOAPFault("Servizio non trovato");
        }
        return new SoggettoRSRisposta(soggettoFruitore);
    }

    public List<SoggettoRSRisposta> getFruitoriServizioSpcoop(String indirizzo, Date maxDate, Date minDate, ServizioRS servizio) throws SOAPFault, DAOException {
        ServizioRSRisposta trovato = this.getServizioSPCoop(null, servizio);
        if (trovato == null) {
            throw new SOAPFault("Servizio non trovato");
        }
        List<SoggettoRSRisposta> soggettiFiltrati = new ArrayList<SoggettoRSRisposta>();
        for (SoggettoRSRisposta soggettoRS : trovato.getFruitori()) {
            if ((maxDate == null || minDate == null) || (soggettoRS.getOraRegistrazione().after(minDate) && soggettoRS.getOraRegistrazione().before(maxDate))) {
                soggettiFiltrati.add(soggettoRS);
            }
        }
        return soggettiFiltrati;
    }

    public List<SoggettoRSRisposta> getFruitoriServizioSpcoopCorrelato(String indirizzo, Date maxDate, Date minDate, ServizioRS servizio) throws SOAPFault, DAOException {
        ServizioRSRisposta trovato = this.getServizioSPCoopCorrelato(null, servizio);
        if (trovato == null) {
            throw new SOAPFault("Servizio non trovato");
        }
        List<SoggettoRSRisposta> soggettiFiltrati = new ArrayList<SoggettoRSRisposta>();
        for (SoggettoRSRisposta soggettoRS : trovato.getFruitori()) {
            if ((maxDate == null || minDate == null) || (soggettoRS.getOraRegistrazione().after(minDate) && soggettoRS.getOraRegistrazione().before(maxDate))) {
                soggettiFiltrati.add(soggettoRS);
            }
        }
        return soggettiFiltrati;

    }

    public List<AccordoServizioRS> getAllIdAccordiServizio(String indirizzo, Date maxDate, Date minDate, AccordoServizioRS accordo) throws SOAPFault, DAOException {
        List<AccordoServizio> listaAccordi;
        if (accordo == null) {
            listaAccordi = daoAccordoServizio.findAllFilter(maxDate, minDate, null);
        } else {
            listaAccordi = daoAccordoServizio.findAllFilter(maxDate, minDate, accordo.getNome());
        }
        List<AccordoServizioRS> lista = new ArrayList<AccordoServizioRS>();
        for (AccordoServizio accordoServizio : listaAccordi) {
            if (!accordoServizio.isPrivato()) {
                lista.add(new AccordoServizioRS(accordoServizio));
            }
        }
        return lista;
    }

    public List<ServizioRS> getAllIdServiziSPCoop(String indirizzo, Date maxDate, Date minDate, ServizioRS servizioRS) throws SOAPFault, DAOException {
        List<Servizio> listaServizi;
        if (servizioRS == null || servizioRS.getSoggettoErogatore() == null || servizioRS.getSoggettoErogatore().getNome() == null) {
            listaServizi = daoServizio.findAllFilter(maxDate, minDate, null, null, null);
        } else {
            if (logger.isDebugEnabled()) logger.debug("Cerco l'erogatore " + servizioRS.getSoggettoErogatore());
            Soggetto soggettoErogatore = daoSoggetto.findByNome(servizioRS.getSoggettoErogatore().getNome(), servizioRS.getSoggettoErogatore().getTipo());
            if (soggettoErogatore == null) {
                throw new SOAPFault("Soggetto erogatore non trovato");
            }
            listaServizi = daoServizio.findAllFilter(maxDate, minDate, servizioRS.getNome(), servizioRS.getTipo(), soggettoErogatore);
        }
        List<ServizioRS> lista = new ArrayList<ServizioRS>();
        for (Servizio servizio : listaServizi) {
            if (!servizio.isPrivato()) {
                lista.add(new ServizioRS(servizio));
            }
        }
        return lista;
    }

    public List<ServizioRS> getAllIdServiziSPCoopCorrelati(String indirizzo, Date maxDate, Date minDate, ServizioRS servizioRS) throws SOAPFault, DAOException {
        List<Servizio> listaServizi;
        if (servizioRS == null || servizioRS.getSoggettoErogatore() == null || servizioRS.getSoggettoErogatore().getNome() == null) {
            listaServizi = daoServizio.findAllCorrelatiFilter(maxDate, minDate, null, null, null);
        } else {
            Soggetto soggettoErogatore = daoSoggetto.findByNome(servizioRS.getSoggettoErogatore().getNome(), servizioRS.getSoggettoErogatore().getTipo());
            if (soggettoErogatore == null) {
                throw new SOAPFault("Soggetto erogatore non trovato");
            }
            listaServizi = daoServizio.findAllCorrelatiFilter(maxDate, minDate, servizioRS.getNome(), servizioRS.getTipo(), soggettoErogatore);
        }
        List<ServizioRS> lista = new ArrayList<ServizioRS>();
        for (Servizio servizio : listaServizi) {
            lista.add(new ServizioRS(servizio));
        }
        return lista;
    }

    public List<SoggettoRSRisposta> getAllIdSoggettiSPCoop(String indirizzo, Date maxDate, Date minDate, SoggettoRS soggettoRS) throws SOAPFault, DAOException {
        List<Soggetto> listaSoggetti;
        if (soggettoRS == null) {
            listaSoggetti = daoSoggetto.findAllFilter(maxDate, minDate, null, null);
        } else {
            listaSoggetti = daoSoggetto.findAllFilter(maxDate, minDate, soggettoRS.getNome(), soggettoRS.getTipo());
        }
        List<SoggettoRSRisposta> lista = new ArrayList<SoggettoRSRisposta>();
        for (Soggetto soggetto : listaSoggetti) {
            lista.add(new SoggettoRSRisposta(soggetto));
        }
        return lista;
    }

    //GET E SET
    public IDAOAccordoServizio getDaoAccordoServizio() {
        return daoAccordoServizio;
    }

    public void setDaoAccordoServizio(IDAOAccordoServizio daoAccordoServizio) {
        this.daoAccordoServizio = daoAccordoServizio;
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
