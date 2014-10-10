package it.unibas.freesbee.ge.ws.gestoreeventi;

import it.unibas.freesbee.ge.code.GestoreCode;
import it.unibas.freesbee.ge.messaggi.XmlJDomUtil;
import it.unibas.freesbee.ge.modello.CategoriaEventiEsterna;
import it.unibas.freesbee.ge.modello.CategoriaEventiInterna;
import it.unibas.freesbee.ge.modello.EventoPubblicatoEsterno;
import it.unibas.freesbee.ge.modello.EventoPubblicatoInterno;
import it.unibas.freesbee.ge.modello.FiltroContenuto;
import it.unibas.freesbee.ge.modello.FiltroData;
import it.unibas.freesbee.ge.modello.GestoreEventi;
import it.unibas.freesbee.ge.modello.ICategoriaEventi;
import it.unibas.freesbee.ge.modello.IEventoPubblicato;
import it.unibas.freesbee.ge.modello.IFiltroPubblicatore;
import it.unibas.freesbee.ge.modello.IPubblicatore;
import it.unibas.freesbee.ge.modello.ISottoscrizione;
import it.unibas.freesbee.ge.modello.IStatoMessaggio;
import it.unibas.freesbee.ge.modello.PubblicatoreEsterno;
import it.unibas.freesbee.ge.modello.PubblicatoreInterno;
import it.unibas.freesbee.ge.modello.Sottoscrittore;
import it.unibas.freesbee.ge.modello.SottoscrizioneEsterna;
import it.unibas.freesbee.ge.modello.SottoscrizioneInterna;
import it.unibas.freesbee.ge.modello.StatoMessaggioEsterno;
import it.unibas.freesbee.ge.modello.StatoMessaggioInterno;
import it.unibas.freesbee.ge.persistenza.DAOException;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiEsterna;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiInterna;
import it.unibas.freesbee.ge.persistenza.IDAOEventoPubblicatoEsterno;
import it.unibas.freesbee.ge.persistenza.IDAOEventoPubblicatoInterno;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreEsterno;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreInterno;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrittore;
import it.unibas.freesbee.ge.persistenza.IDAOStatoMessaggioEsterno;
import it.unibas.freesbee.ge.persistenza.IDAOStatoMessaggioInterno;
import it.unibas.freesbee.ge.persistenza.facade.DAOCategoriaEventiEsternaFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOCategoriaEventiInternaFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOGestoreEventiFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOPubblicatoreEsternoFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOPubblicatoreInternoFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOSottoscrizioneEsternaFacade;
import it.unibas.freesbee.ge.modello.ConsegnaEventoPubblicato;
import java.util.GregorianCalendar;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.helpers.XMLUtils;
import org.apache.cxf.interceptor.InInterceptors;
import org.apache.cxf.interceptor.OutFaultInterceptors;
import org.w3c.dom.Element;
import javax.xml.transform.Source;
import org.w3c.dom.Document;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.jws.WebResult;
import javax.xml.transform.dom.DOMSource;

@WebService(targetNamespace = "http://ge.freesbee.unibas.it/")
@InInterceptors(interceptors = {"it.unibas.freesbee.ge.persistenza.hibernate.HibernateInterceptor"/*, "it.unibas.freesbee.ge.ws.gestoreeventi.InterceptorWsdl"*/})
@OutFaultInterceptors(interceptors = {"it.unibas.freesbee.ge.persistenza.hibernate.HibernateInterceptorFault"})
public class WSGestoreEventiImpl implements IWSGestoreEventi {

    private static Log logger = LogFactory.getLog(WSGestoreEventiImpl.class);
    private IDAOEventoPubblicatoInterno daoEventoPubblicatoInterno;
    private IDAOEventoPubblicatoEsterno daoEventoPubblicatoEsterno;
    private IDAOPubblicatoreInterno daoPubblicatoreInterno;
    private IDAOPubblicatoreEsterno daoPubblicatoreEsterno;
    private IDAOCategoriaEventiInterna daoCategoriaEventiInterna;
    private IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna;
    private IDAOSottoscrittore daoSottoscrittore;
    private IDAOStatoMessaggioInterno daoStatoMessaggioInterno;
    private IDAOStatoMessaggioEsterno daoStatoMessaggioEsterno;
    private GestoreEventi ge = null;

    public WSGestoreEventiImpl(IDAOEventoPubblicatoInterno daoEventoPubblicatoInterno, IDAOEventoPubblicatoEsterno daoEventoPubblicatoEsterno, IDAOPubblicatoreInterno daoPubblicatoreInterno, IDAOPubblicatoreEsterno daoPubblicatoreEsterno, IDAOStatoMessaggioInterno daoStatoMessaggioInterno, IDAOStatoMessaggioEsterno daoStatoMessaggioEsterno, IDAOSottoscrittore daoSottoscrittore, IDAOCategoriaEventiInterna daoCategoriaEventiInterna, IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna) {
        this.daoEventoPubblicatoInterno = daoEventoPubblicatoInterno;
        this.daoEventoPubblicatoEsterno = daoEventoPubblicatoEsterno;
        this.daoPubblicatoreInterno = daoPubblicatoreInterno;
        this.daoPubblicatoreEsterno = daoPubblicatoreEsterno;
        this.daoStatoMessaggioInterno = daoStatoMessaggioInterno;
        this.daoStatoMessaggioEsterno = daoStatoMessaggioEsterno;
        this.daoSottoscrittore = daoSottoscrittore;
        this.daoCategoriaEventiInterna = daoCategoriaEventiInterna;
        this.daoCategoriaEventiEsterna = daoCategoriaEventiEsterna;
    }

    @WebMethod(operationName = "pubblicaMessaggio")
    public String pubblicaMessaggio(@WebParam(name = "messaggioSoap") Object messaggioSoap, @WebParam(name = "nomePubblicatore") String nomePubblicatore, @WebParam(name = "tipoPubblicatore") String tipoPubblicatore, @WebParam(name = "categoriaEventi") String categoriaEventi, @WebParam(name = "nomePubblicatoreEsterno") String nomePubblicatoreEsterno, @WebParam(name = "tipoPubblicatoreEsterno") String tipoPubblicatoreEsterno) throws SOAPFault {
        logger.debug("Operazione richiesta: pubblicaMessaggio");

        logger.debug("-------I dati dell'evento sono : ------");
        logger.debug("nomePubblicatore: - " + nomePubblicatore);
        logger.debug("tipoPubblicatore - " + tipoPubblicatore);
        logger.debug("categoria eventi - " + categoriaEventi);
        logger.debug("nomePubblicatoreEsterno - " + nomePubblicatoreEsterno);
        logger.debug("tipoPubblicatoreEsterno - " + tipoPubblicatoreEsterno);

        try {
            ge = DAOGestoreEventiFacade.getGE();

            IPubblicatore pubblicatore = daoPubblicatoreInterno.findByTipoNome(tipoPubblicatore, nomePubblicatore);

            if (pubblicatore != null) {
                //Se il pubblicatore e' il GE e la categoria e' GEControlProtocol eseguo normalente la consegna o la notifica
                //Perche' si tratta di una comunicazione che il GE vuole mandare ai suoi sottoscrittori del dominio
                //Solo i SIL  del dominio possono sottoscriversi alla categoria GEControlProtocol (interna)
                //Verifico prima di andare oltre se esiste e se e' attiva
                CategoriaEventiInterna categoriaEventiInterna = DAOCategoriaEventiInternaFacade.verificaEsistenzaCategoriaEventiInternaAttavia(categoriaEventi);

                PubblicatoreInterno pubblicatoreInterno = daoPubblicatoreInterno.findByTipoNome(ge.getTipo(), ge.getNome());

                if (pubblicatoreInterno == null || !DAOPubblicatoreInternoFacade.isPubblicatoreInternoPerCategoriaEventiInterna(categoriaEventiInterna, pubblicatoreInterno)) {
                    logger.error("Il gestore eventi " + ge + " non e' pubblicatore per la categoria di eventi " + categoriaEventi);
                    throw new SOAPFault("Il gestore eventi " + ge + " non e' pubblicatore per la categoria di eventi " + categoriaEventi);
                }

                //Pubblicatori interni ed il GE stesso
                return pubblicaEventoInterno(categoriaEventi, tipoPubblicatore, nomePubblicatore, messaggioSoap);
            }

            pubblicatore = daoPubblicatoreEsterno.findByTipoNome(tipoPubblicatore, nomePubblicatore);

            if (pubblicatore != null) {
                //Verifico prima di andare oltre se esiste e se e' attiva
                CategoriaEventiEsterna categoriaEventiEsterna = DAOCategoriaEventiEsternaFacade.verificaEsistenzaCategoriaEventiEsternaAttavia(categoriaEventi);

                if (categoriaEventiEsterna.isInAttesa()) {
                    logger.error("La categoria di eventi " + categoriaEventi + " non e' confermata");
                    throw new SOAPFault("La categoria di eventi " + categoriaEventi + " non e' confermata");
                }

                PubblicatoreEsterno pubblicatoreConferma = daoPubblicatoreEsterno.findByTipoNome(tipoPubblicatore, nomePubblicatore);

                PubblicatoreEsterno pubblicatoreGE = DAOCategoriaEventiEsternaFacade.getGestoreEvetniPubblicatorePerCategoriaEventi(categoriaEventiEsterna);

                if (pubblicatoreGE == null) {
                    logger.error("La categoria di eventi " + categoriaEventi + " risulta confermata ma non esiste un gestore eventi pubblicatore per questa categoria");
                    throw new SOAPFault("La categoria di eventi " + categoriaEventi + " risulta confermata ma non esiste un gestore eventi pubblicatore per questa categoria");
                }

                if (pubblicatoreGE != null) {
                    logger.debug("Il gestore eventi " + pubblicatoreGE + " e' il pubblicatore per la categoria di eventi " + categoriaEventi);

                    if (pubblicatoreConferma == null || pubblicatoreGE.compareTo(pubblicatoreConferma) != 0) {
                        logger.error("Il gestore eventi " + tipoPubblicatore + " " + nomePubblicatore + " che ha pubblicato il messagio e' differente dall'attuale pubblicatore " + pubblicatoreGE + " della categoria ");
                        throw new SOAPFault("Il gestore eventi " + tipoPubblicatore + " " + nomePubblicatore + " che ha pubblicato il messagio e' differente dall'attuale pubblicatore " + pubblicatoreGE + " della categoria ");
                    }

                }

                if (categoriaEventi.equals(CategoriaEventiInterna.GE_CONTROL_PROTOCOL)) {
                    //Verifico prima di andare oltre se esiste e se e' attiva
                    DAOCategoriaEventiEsternaFacade.verificaEsistenzaCategoriaEventiEsternaAttavia(categoriaEventi);
                    return pubblicaEventoInternoGE(categoriaEventi, tipoPubblicatore, nomePubblicatore, tipoPubblicatoreEsterno, nomePubblicatoreEsterno, messaggioSoap);
                }

                return pubblicaEventoEsterno(categoriaEventi, tipoPubblicatore, nomePubblicatore, tipoPubblicatoreEsterno, nomePubblicatoreEsterno, messaggioSoap);

            } else {
                logger.error("Il pubblicatore " + tipoPubblicatore + " " + nomePubblicatore + " non e' conosciuto");
                throw new SOAPFault("Il pubblicatore " + tipoPubblicatore + " " + nomePubblicatore + " non e' conosciuto");

            }

        } catch (Exception ex) {
            logger.error("Si e' verificato un errore durante l'inserimento nella coda del messaggio ");
            ex.printStackTrace();
            throw new SOAPFault(ex.getMessage());
        }
    }

    private String pubblicaEventoInternoGE(String categoriaEventi, String tipoPubblicatore, String nomePubblicatore, String tipoPubblicatoreEsterno, String nomePubblicatoreEsterno, Object messaggioSoap) throws DAOException, Exception {
        //Verifico che chi pubblica e' un gestore eventi
        DAOGestoreEventiFacade.verificaEsistenzaGestoreEventi(tipoPubblicatore, nomePubblicatore);
        DAOPubblicatoreEsternoFacade.verificaEsistenzaPubblicatoreEsterno(tipoPubblicatore, nomePubblicatore, categoriaEventi);

        if ((tipoPubblicatoreEsterno != null && nomePubblicatoreEsterno != null) && (!tipoPubblicatoreEsterno.equals("") && !nomePubblicatoreEsterno.equals(""))) {
            logger.error("Non si deve specificare il pubblicatore esterno per la catgoria di eventi " + ICategoriaEventi.GE_CONTROL_PROTOCOL);
            throw new Exception("Non si deve specificare il pubblicatore esterno per la catgoria di eventi " + ICategoriaEventi.GE_CONTROL_PROTOCOL);
        }

        EventoPubblicatoEsterno eventoPubblicato = (EventoPubblicatoEsterno) this.riempiRiferimentiEventoPubblicato(tipoPubblicatore, nomePubblicatore, categoriaEventi, messaggioSoap, true, tipoPubblicatoreEsterno, nomePubblicatoreEsterno);
        logger.debug("Pubblico un evento esterno con messaggio " + XmlJDomUtil.formattaXML(eventoPubblicato.getMessaggio()));

        eventoPubblicato.setDataPubblicazione(new GregorianCalendar().getTime());

        eventoPubblicato.setTipoPubblicatoreEsterno(tipoPubblicatore);
        eventoPubblicato.setNomePubblicatoreEsterno(nomePubblicatore);

        daoEventoPubblicatoEsterno.makePersistent(eventoPubblicato);


        Sottoscrittore sottoscrittoreGE = daoSottoscrittore.findByTipoNome(ge.getTipo(), ge.getNome());

        SottoscrizioneEsterna sottoscrizione = daoCategoriaEventiEsterna.findByCategoriaEventiSottoscrittore(daoCategoriaEventiEsterna.findByNome(categoriaEventi), sottoscrittoreGE);

        if (sottoscrizione.getListaFiltroPublicatore().size() > 0) {
            logger.error("La sottoscrizione del gestore eventi per la categoria eventi " + ICategoriaEventi.GE_CONTROL_PROTOCOL + " non deve specificare filtri pubblicatori");
            throw new Exception("La sottoscrizione del gestore eventi per la categoria eventi " + ICategoriaEventi.GE_CONTROL_PROTOCOL + " non deve specificare filtri pubblicatori");
        }

        if (sottoscrizione.getFiltroContenuto() != null && !sottoscrizione.getFiltroContenuto().getRegularExpression().equals("")) {
            logger.error("La sottoscrizione del gestore eventi per la categoria eventi " + ICategoriaEventi.GE_CONTROL_PROTOCOL + " non deve specificare filtro contenturo");
            throw new Exception("La sottoscrizione del gestore eventi per la categoria eventi " + ICategoriaEventi.GE_CONTROL_PROTOCOL + " non deve specificare filtro contenuto");
        }

        if (sottoscrizione.getTipoSottoscrizione().equals(ISottoscrizione.TIPO_SOTTOSCRIZIONE_NOTIFICA)) {
            logger.error("La sottoscrizione del gestore eventi per la categoria eventi " + ICategoriaEventi.GE_CONTROL_PROTOCOL + " deve essere di tipo " + ISottoscrizione.TIPO_SOTTOSCRIZIONE_CONSEGNA);
            throw new Exception("La sottoscrizione del gestore eventi per la categoria eventi " + ICategoriaEventi.GE_CONTROL_PROTOCOL + " deve essere di tipo " + ISottoscrizione.TIPO_SOTTOSCRIZIONE_CONSEGNA);
        }

        if (verificaFiltroData(sottoscrizione.getFiltroData(), eventoPubblicato.getDataPubblicazione())) {

            StatoMessaggioEsterno statoMessaggio = new StatoMessaggioEsterno();
            statoMessaggio.setEventoPubblicato(eventoPubblicato);
            statoMessaggio.setDataAggiornamento(new GregorianCalendar().getTime());
            statoMessaggio.setSottoscrittore(sottoscrizione.getSottoscrittore());


            daoStatoMessaggioEsterno.makePersistent(statoMessaggio);



            if (sottoscrizione.getTipoSottoscrizione().equals(ISottoscrizione.TIPO_SOTTOSCRIZIONE_CONSEGNA)) {
                // nella coda consegna vengono inseriti i messaggi da consegnare ai
                // sottoscrittori interni al dominio e i messaggi per i GE esterni.
                statoMessaggio.setStato(IStatoMessaggio.IN_CODA_CONSEGNA);
                daoStatoMessaggioEsterno.makePersistent(statoMessaggio);
                logger.info("CONSEGNA AL GE");
                if (GestoreCode.addCodaConsegnaEventoEsterno(statoMessaggio)) {
                    statoMessaggio.setStato(IStatoMessaggio.CONSEGNATO);
                } else {
                    statoMessaggio.setStato(IStatoMessaggio.ERRORE_CONSEGNA);
                }
                GestoreCode.aggiornaStatoMessaggioStatoEsterno(statoMessaggio);

            }

        }

        return "Operazione effettuata correttamnte";
    }

    private String pubblicaEventoInterno(String categoriaEventi, String tipoPubblicatore, String nomePubblicatore, Object messaggioSoap) throws DAOException, Exception {
        //Verifiche
        DAOPubblicatoreInternoFacade.verificaEsistenzaPubblicatoreInterno(tipoPubblicatore, nomePubblicatore, categoriaEventi);

        EventoPubblicatoInterno eventoPubblicato = (EventoPubblicatoInterno) this.riempiRiferimentiEventoPubblicato(tipoPubblicatore, nomePubblicatore, categoriaEventi, messaggioSoap, false, "", "");
        logger.debug("Pubblico un evento interno con messaggio " + XmlJDomUtil.formattaXML(eventoPubblicato.getMessaggio()));

        eventoPubblicato.setDataPubblicazione(new GregorianCalendar().getTime());

        daoEventoPubblicatoInterno.makePersistent(eventoPubblicato);

        //Se la categoria e' GEControlProtocol il pubblicatore e' il GE e consegno normalmente
        //Creazione dell'elenco dei sottoscritori a cui consegnare il messaggio o la notifica (con filtri)
        List<SottoscrizioneInterna> listaSottoscrizioni = eventoPubblicato.getCategoriaEventi().getListaSottoscrizioni();

        logger.debug("Sono presenti " + listaSottoscrizioni.size() + " sottoscrizioni");


        PubblicatoreInterno pubbGE = daoPubblicatoreInterno.findByTipoNome(ge.getTipo(), ge.getNome());

        for (SottoscrizioneInterna sottoscrizione : listaSottoscrizioni) {

            boolean verifica = false;

            if (DAOGestoreEventiFacade.isEsistenzaGestoreEventi(sottoscrizione.getSottoscrittore().getTipo(), sottoscrizione.getSottoscrittore().getNome())) {
                logger.debug("Il sottoscrittore " + sottoscrizione.getSottoscrittore() + " e' un gestore eventi");
                verifica = verificaSottoscrizione(sottoscrizione, eventoPubblicato, pubbGE);

            } else {
                logger.debug("Il sottoscrittore " + sottoscrizione.getSottoscrittore() + " e' un sottoscrittore interno");
                verifica = verificaSottoscrizione(sottoscrizione, eventoPubblicato, null);
            }

            if (verifica) {

                StatoMessaggioInterno statoMessaggio = new StatoMessaggioInterno();
                statoMessaggio.setEventoPubblicato(eventoPubblicato);
                statoMessaggio.setDataAggiornamento(new GregorianCalendar().getTime());
                statoMessaggio.setSottoscrittore(sottoscrizione.getSottoscrittore());

                daoStatoMessaggioInterno.makePersistent(statoMessaggio);

                Sottoscrittore sottoscrittore = sottoscrizione.getSottoscrittore();

                if (DAOGestoreEventiFacade.isEsistenzaGestoreEventi(sottoscrittore.getTipo(), sottoscrittore.getNome())) {
                    if (sottoscrizione.getTipoSottoscrizione().equals(ISottoscrizione.TIPO_SOTTOSCRIZIONE_CONSEGNA)) {
                        //Se il sottoscrittore e' un gestore eventi
                        statoMessaggio.setStato(IStatoMessaggio.IN_CODA_PUBBLICA);
                        daoStatoMessaggioInterno.makePersistent(statoMessaggio);
                        logger.info("PUBBLICA SU GE");
                        if (GestoreCode.addCodaPubblica(statoMessaggio)) {
                            statoMessaggio.setStato(IStatoMessaggio.PUBBLICATO);
                        } else {
                            statoMessaggio.setStato(IStatoMessaggio.ERRORE_PUBBLICA);
                        }
                        GestoreCode.aggiornaStatoMessaggioStatoInterno(statoMessaggio);

                    } else {
                        //Non si dovrebbe mai verificare
                        logger.error("Il tipo di sottoscrizione per i gestori eventi deve essere " + ISottoscrizione.TIPO_SOTTOSCRIZIONE_CONSEGNA);
                        throw new Exception("Il tipo di sottoscrizione per i gestori eventi deve essere " + ISottoscrizione.TIPO_SOTTOSCRIZIONE_CONSEGNA);
                    }
                } else {

                    if (sottoscrizione.getTipoSottoscrizione().equals(ISottoscrizione.TIPO_SOTTOSCRIZIONE_NOTIFICA)) {
                        statoMessaggio.setStato(IStatoMessaggio.IN_CODA_NOTIFICA);
                        daoStatoMessaggioInterno.makePersistent(statoMessaggio);
                        logger.info("NOTIFICA NEL DOMINIO (INTERNO)");
                        if (GestoreCode.addCodaNotificaEventoInterno(statoMessaggio)) {
                            statoMessaggio.setStato(IStatoMessaggio.NOTIFICATO);
                        } else {
                            statoMessaggio.setStato(IStatoMessaggio.ERRORE_NOTIFICA);
                        }
                        GestoreCode.aggiornaStatoMessaggioStatoInterno(statoMessaggio);

                    } else if (sottoscrizione.getTipoSottoscrizione().equals(ISottoscrizione.TIPO_SOTTOSCRIZIONE_CONSEGNA)) {
                        // nella coda consegna vengono inseriti i messaggi da consegnare ai
                        // sottoscrittori interni al dominio
                        statoMessaggio.setStato(IStatoMessaggio.IN_CODA_CONSEGNA);
                        daoStatoMessaggioInterno.makePersistent(statoMessaggio);
                        logger.info("CONSEGNA NEL DOMINIO (INTERNO)");
                        if (GestoreCode.addCodaConsegnaEventoInterno(statoMessaggio)) {
                            statoMessaggio.setStato(IStatoMessaggio.CONSEGNATO);
                        } else {
                            statoMessaggio.setStato(IStatoMessaggio.ERRORE_CONSEGNA);
                        }
                        GestoreCode.aggiornaStatoMessaggioStatoInterno(statoMessaggio);
                    }

                }
            }
        }

        return "Operazione effettuata correttamente";
    }

    private String pubblicaEventoEsterno(String categoriaEventi, String tipoPubblicatore, String nomePubblicatore, String tipoPubblicatoreEsterno, String nomePubblicatoreEsterno, Object messaggioSoap) throws DAOException, Exception {

        //Verifico che chi pubblica e' un gestore eventi
        DAOGestoreEventiFacade.verificaEsistenzaGestoreEventi(tipoPubblicatore, nomePubblicatore);
        DAOPubblicatoreEsternoFacade.verificaEsistenzaPubblicatoreEsterno(tipoPubblicatore, nomePubblicatore, categoriaEventi);

        EventoPubblicatoEsterno eventoPubblicato = (EventoPubblicatoEsterno) this.riempiRiferimentiEventoPubblicato(tipoPubblicatore, nomePubblicatore, categoriaEventi, messaggioSoap, true, tipoPubblicatoreEsterno, nomePubblicatoreEsterno);
        logger.debug("Pubblico un evento esterno con messaggio " + XmlJDomUtil.formattaXML(eventoPubblicato.getMessaggio()));

        //Non lo verifico perche' il pubblicatore esterno potrebbe anche non essere registrato se non e' inserito in alcun filtro
        IPubblicatore pubblicatore = new PubblicatoreEsterno(tipoPubblicatoreEsterno, nomePubblicatoreEsterno);

        eventoPubblicato.setDataPubblicazione(new GregorianCalendar().getTime());

        daoEventoPubblicatoEsterno.makePersistent(eventoPubblicato);

        //Se la categoria e' GEControlProtocol il pubblicatore e' il GE e consegno normalmente
        //Creazione dell'elenco dei sottoscritori a cui consegnare il messaggio o la notifica (con filtri)
        List<SottoscrizioneEsterna> listaSottoscrizioni = eventoPubblicato.getCategoriaEventi().getListaSottoscrizioni();

        logger.debug("Sono presenti " + listaSottoscrizioni.size() + " sottoscrizioni");

        for (SottoscrizioneEsterna sottoscrizione : listaSottoscrizioni) {

            boolean confermata = (!DAOSottoscrizioneEsternaFacade.isInAttesaConfermaCategoriaEventi(sottoscrizione)) && (!DAOSottoscrizioneEsternaFacade.isInAttesaConfermaPubblicatori(sottoscrizione));

            Sottoscrittore sottoscrittoreGE = daoSottoscrittore.findByTipoNome(ge.getTipo(), ge.getNome());
            if (sottoscrizione.getSottoscrittore().compareTo(sottoscrittoreGE) == 0) {
                logger.error("Il gestore eventi " + ge + " non puo' essere sottoscrittore per le categoria di eventi generiche");
                confermata = false;
            }

            logger.debug("Il sottoscrittore " + sottoscrizione.getSottoscrittore() + " e' un sottoscrittore interno");

            if (confermata && verificaSottoscrizione(sottoscrizione, eventoPubblicato, pubblicatore)) {

                StatoMessaggioEsterno statoMessaggio = new StatoMessaggioEsterno();
                statoMessaggio.setEventoPubblicato(eventoPubblicato);
                statoMessaggio.setDataAggiornamento(new GregorianCalendar().getTime());
                statoMessaggio.setSottoscrittore(sottoscrizione.getSottoscrittore());

                daoStatoMessaggioEsterno.makePersistent(statoMessaggio);

                if (sottoscrizione.getTipoSottoscrizione().equals(ISottoscrizione.TIPO_SOTTOSCRIZIONE_NOTIFICA)) {
                    statoMessaggio.setStato(IStatoMessaggio.IN_CODA_NOTIFICA);
                    daoStatoMessaggioEsterno.makePersistent(statoMessaggio);
                    logger.info("NOTIFICA NEL DOMINIO (ESTERNO)");
                    if (GestoreCode.addCodaNotificaEventoEsterno(statoMessaggio)) {
                        statoMessaggio.setStato(IStatoMessaggio.NOTIFICATO);
                    } else {
                        statoMessaggio.setStato(IStatoMessaggio.ERRORE_NOTIFICA);
                    }
                    GestoreCode.aggiornaStatoMessaggioStatoEsterno(statoMessaggio);

                } else if (sottoscrizione.getTipoSottoscrizione().equals(ISottoscrizione.TIPO_SOTTOSCRIZIONE_CONSEGNA)) {
                    // nella coda consegna vengono inseriti i messaggi da consegnare ai
                    // sottoscrittori interni al dominio e i messaggi per i GE esterni.
                    statoMessaggio.setStato(IStatoMessaggio.IN_CODA_CONSEGNA);
                    daoStatoMessaggioEsterno.makePersistent(statoMessaggio);
                    logger.info("CONSEGNA NEL DOMINIO (ESTERNO)");
                    if (GestoreCode.addCodaConsegnaEventoEsterno(statoMessaggio)) {
                        statoMessaggio.setStato(IStatoMessaggio.CONSEGNATO);
                    } else {
                        statoMessaggio.setStato(IStatoMessaggio.ERRORE_CONSEGNA);
                    }
                    GestoreCode.aggiornaStatoMessaggioStatoEsterno(statoMessaggio);

                }
            }

            logger.debug("La categoria eventi e': " +confermata);
        }

        return "Operazione effettuata correttamnte";
    }

    private IEventoPubblicato riempiRiferimentiEventoPubblicato(String tipoPubblicatore, String nomePubblicatore, String nomeCategoriaEventi, Object messaggioSoap, boolean esterno, String tipoPubblicatoreEsterno, String nomePubblicatoreEsterno) throws DAOException {
        logger.debug("Riempimento riferimenti Evento Pubblicato");

        IEventoPubblicato eventoPubblicato = null;
        ICategoriaEventi categoriaEventi = null;

        if (!esterno) {
            eventoPubblicato = new EventoPubblicatoInterno();

            categoriaEventi = daoCategoriaEventiInterna.findByNome(nomeCategoriaEventi);

            eventoPubblicato.setPubblicatore(daoPubblicatoreInterno.findByTipoNome(tipoPubblicatore, nomePubblicatore));

        } else {

            eventoPubblicato = new EventoPubblicatoEsterno();

            categoriaEventi = daoCategoriaEventiEsterna.findByNome(nomeCategoriaEventi);

            eventoPubblicato.setPubblicatore(daoPubblicatoreEsterno.findByTipoNome(tipoPubblicatore, nomePubblicatore));

            ((EventoPubblicatoEsterno) eventoPubblicato).setTipoPubblicatoreEsterno(tipoPubblicatoreEsterno);
            ((EventoPubblicatoEsterno) eventoPubblicato).setNomePubblicatoreEsterno(nomePubblicatoreEsterno);
        }

        Element elemento = (Element) messaggioSoap;
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Source src = new DOMSource(elemento.getFirstChild());
        String enc = null;
        if (src instanceof DOMSource && ((DOMSource) src).getNode() instanceof Document) {
            enc = ((Document) ((DOMSource) src).getNode()).getXmlEncoding();
        }
        XMLUtils.writeTo(src, os, -1, enc, "yes");
        String stringaMessaggio = os.toString();

        eventoPubblicato.setMessaggio(stringaMessaggio);

        eventoPubblicato.setCategoriaEventi(categoriaEventi);


        return eventoPubblicato;
    }

    private boolean verificaSottoscrizione(ISottoscrizione sottoscrizione, IEventoPubblicato eventoPubblicato, IPubblicatore pubblicatore) throws DAOException {
        if (!verificaFiltroData(sottoscrizione.getFiltroData(), eventoPubblicato.getDataPubblicazione())) {
            logger.debug("Il messaggio non rientra nella finestra temporale accettabile");
            return false;
        }


        if (pubblicatore == null) {

            if (sottoscrizione.getNomeCategoriaEventi().equals(ICategoriaEventi.GE_CONTROL_PROTOCOL)) {
                if (!DAOGestoreEventiFacade.isEsistenzaGestoreEventi(eventoPubblicato.getPubblicatore().getTipo(), eventoPubblicato.getPubblicatore().getNome())) {
                    //Non puo essere perche' le comunicazioni ai gestori eventi le fa il ClientGEControlProtocol
                    logger.error("Il pubblicatore " + eventoPubblicato.getPubblicatore() + " non e' un gestore eventi");
                    throw new DAOException("Il pubblicatore " + eventoPubblicato.getPubblicatore() + " non e' un gestore eventi");
                }
                PubblicatoreInterno pubblicatoreGEControlProtocol = daoPubblicatoreInterno.findByTipoNome(eventoPubblicato.getPubblicatore().getTipo(), eventoPubblicato.getPubblicatore().getNome());
                PubblicatoreInterno pubblicatoreGE = daoPubblicatoreInterno.findByTipoNome(ge.getTipo(), ge.getNome());
                if (pubblicatoreGEControlProtocol == null || pubblicatoreGEControlProtocol.compareTo(pubblicatoreGE) != 0) {
                }
            }

            if (!verificaFiltroPubblicatore(sottoscrizione, eventoPubblicato.getPubblicatore())) {
                logger.debug("Il pubblicatore interno del messaggio non e' fra quelli accettati dal sottoscrittore");
                return false;
            }

        } else {

            if (sottoscrizione.getNomeCategoriaEventi().equals(ICategoriaEventi.GE_CONTROL_PROTOCOL)) {
                if (!DAOGestoreEventiFacade.isEsistenzaGestoreEventi(pubblicatore.getTipo(), pubblicatore.getNome())) {
                    //Non puo essere perche' le comunicazioni ai gestori eventi le fa il ClientGEControlProtocol
                    logger.error("Il pubblicatore " + pubblicatore + " non e' un gestore eventi");
                    throw new DAOException("Il pubblicatore " + pubblicatore + " non e' un gestore eventi");
                }
            }

            if (!verificaFiltroPubblicatore(sottoscrizione, pubblicatore)) {
                logger.debug("Il pubblicatore esterno del messaggio non e' fra quelli accettati dal sottoscrittore");
                return false;
            }
        }

        if (!verificaFiltroContenuto(sottoscrizione.getFiltroContenuto(), eventoPubblicato)) {
            logger.debug("Filtro contenuto non soddisfatto");
            return false;
        }

        return true;

    }

    private boolean verificaFiltroData(FiltroData filtroData, Date dataPubblicazione) {
        logger.debug("Verifica FiltroData: ");
        logger.debug("DataInizio: " + filtroData.getDataInizio());
        logger.debug("DataFine: " + filtroData.getDataFine());
        logger.debug("DataPubblicazione: " + FiltroData.convertiDateToXmlDate(dataPubblicazione));

        if (filtroData.getDataFine() != null) {
            if (dataPubblicazione.after(filtroData.getDataInizio()) && dataPubblicazione.before(filtroData.getDataFine())) {
                logger.debug("FiltroData -> Passato");
                return true;
            }
        } else {
            logger.debug("DataFine non specificata");
            if (dataPubblicazione.after(filtroData.getDataInizio())) {
                logger.debug("FiltroData -> Passato");
                return true;
            }
        }
        logger.debug("FiltroData -> Non Passato");
        return false;
    }

    private boolean verificaFiltroContenuto(FiltroContenuto filtroContenuto, IEventoPubblicato eventoPubblicato) {
        logger.debug("Verifica FiltroContenuto: ");
        if (filtroContenuto == null) {
            // Se non e' stato specificato nessun filtro sul contenuto allora si accettano tutti i messaggi
            logger.debug("Non si e' specificato nessun filtro sul contenuto");
            logger.debug("FiltroContenuto -> Passato");
            return true;
        } else {
            logger.info("Regular Expression: " + filtroContenuto.getRegularExpression());
            Pattern expression = Pattern.compile(filtroContenuto.getRegularExpression());
            Matcher matcher = expression.matcher(eventoPubblicato.getMessaggio());
            if (matcher.find()) {
                logger.debug("FiltroContenuto -> Passato");
                return true;
            }

        }
        logger.debug("FiltroContenuto -> Non Passato");
        return false;
    }

    private boolean verificaFiltroPubblicatore(ISottoscrizione sottoscrizione, IPubblicatore pubblicatore) throws DAOException {
        logger.info("Verifica FiltroPubblicatore: " + pubblicatore);

        List listaFiltroPubblicatore = sottoscrizione.getListaIFiltroPublicatore();

        if (listaFiltroPubblicatore.size() == 0) {
            // Se non e' stato specificato nessun filtro pubblicatore allora si accettano tutti i messaggi
            logger.debug("Non si sono specificati filtri sul pubblicatore");
            logger.debug("FiltroPubblicatore -> Passato");
            return true;
        }
        Iterator it = listaFiltroPubblicatore.iterator();
        while (it.hasNext()) {
            IFiltroPubblicatore filtro = (IFiltroPubblicatore) it.next();

            if (filtro.getPubblicatore().compareTo(pubblicatore) == 0) {
                logger.debug("FiltroPubblicatore -> Passato");
                return true;
            }


        }
        logger.debug("FiltroPubblicatore -> Non Passato");
        return false;
    }

    @WebResult(name = "consegnaMessaggioNotificato")
    @WebMethod(operationName = "prelevaMessaggio")
    public ConsegnaEventoPubblicato prelevaMessaggio(@WebParam(name = "idMessaggio") String idMessaggio, @WebParam(name = "nomeSottoscrittore") String nomeSottoscrittore, @WebParam(name = "tipoSottoscrittore") String tipoSottoscrittore, @WebParam(name = "categoriaEventi") String categoriaEventi) throws SOAPFault {
        logger.debug("Operazione richiesta: prelevaMessaggio");

        logger.debug("-------I dati dell'evento sono : ------");
        logger.debug("nomeSottoscrittore: - " + nomeSottoscrittore);
        logger.debug("tipoSottoscrittore - " + tipoSottoscrittore);
        logger.debug("categoria eventi - " + categoriaEventi);
        logger.debug("idMessaggio - " + idMessaggio);

        try {
            ge = DAOGestoreEventiFacade.getGE();

            //Controllo prima l'interna se si tratta di GEControlProtocol e' sempre interna
            ICategoriaEventi categoriaEventiMessaggio = daoCategoriaEventiInterna.findByNome(categoriaEventi);

            if (categoriaEventiMessaggio != null) {

                CategoriaEventiInterna categoriaEventiInterna = DAOCategoriaEventiInternaFacade.verificaEsistenzaCategoriaEventiInternaAttavia(categoriaEventi);

                PubblicatoreInterno pubblicatoreInterno = daoPubblicatoreInterno.findByTipoNome(ge.getTipo(), ge.getNome());

                if (pubblicatoreInterno == null || !DAOPubblicatoreInternoFacade.isPubblicatoreInternoPerCategoriaEventiInterna(categoriaEventiInterna, pubblicatoreInterno)) {
                    logger.error("Il gestore eventi " + ge + " non e' pubblicatore per la categoria di eventi " + categoriaEventi);
                    throw new SOAPFault("Il gestore eventi " + ge + " non e' pubblicatore per la categoria di eventi " + categoriaEventi);
                }

                //Pubblicatori interni ed il GE stesso
                return prelevaEventoInterno(categoriaEventi, idMessaggio, tipoSottoscrittore, nomeSottoscrittore);
            }

            categoriaEventiMessaggio = daoCategoriaEventiEsterna.findByNome(categoriaEventi);

            if (categoriaEventiMessaggio != null) {

                CategoriaEventiEsterna categoriaEventiEsterna = DAOCategoriaEventiEsternaFacade.verificaEsistenzaCategoriaEventiEsternaAttavia(categoriaEventi);

                PubblicatoreEsterno pubblicatoreGE = DAOCategoriaEventiEsternaFacade.getGestoreEvetniPubblicatorePerCategoriaEventi(categoriaEventiEsterna);

                if (pubblicatoreGE == null) {
                    logger.error("La categoria di eventi " + categoriaEventi + " risulta confermata ma non esiste un gestore eventi pubblicatore per questa categoria");
                    throw new SOAPFault("La categoria di eventi " + categoriaEventi + " risulta confermata ma non esiste un gestore eventi pubblicatore per questa categoria");
                }

                return prelevaEventoEsterno(categoriaEventi, idMessaggio, tipoSottoscrittore, nomeSottoscrittore);

            } else {
                logger.error("La categoria evetni " + categoriaEventi + " non esiste");
                throw new SOAPFault("La categoria evetni " + categoriaEventi + " non esiste");

            }

        } catch (Exception ex) {
            logger.error("Si e' verificato un errore durante il prelevamento del messaggio ");
            ex.printStackTrace();
            throw new SOAPFault(ex.getMessage());
        }

    }

    private ConsegnaEventoPubblicato prelevaEventoInterno(String categoriaEventi, String idMessaggio, String tipoSottoscrittore, String nomeSottoscrittore) throws Exception {
        long id = Long.parseLong(idMessaggio.trim());
        EventoPubblicatoInterno eventoPubblicato = daoEventoPubblicatoInterno.findById(id, false);

        if (eventoPubblicato == null) {
            logger.error("Non esiste un messaggio con l'id specificato" + id);
            throw new DAOException("Non esiste un messaggio con l'id specificato" + id);
        }

        CategoriaEventiInterna categoriaEvnetiInterna = DAOCategoriaEventiInternaFacade.verificaEsistenzaCategoriaEventiInternaAttavia(categoriaEventi);

        Sottoscrittore sottoscrittore = daoSottoscrittore.findByTipoNome(tipoSottoscrittore, nomeSottoscrittore);

        if (sottoscrittore == null) {
            logger.error("Il sottoscrittore " + tipoSottoscrittore + " " + nomeSottoscrittore + " non esiste");
            throw new Exception("Il sottoscrittore " + tipoSottoscrittore + " " + nomeSottoscrittore + " non esiste");
        }

        SottoscrizioneInterna sottoscrizione = daoCategoriaEventiInterna.findByCategoriaEventiSottoscrittore(categoriaEvnetiInterna, sottoscrittore);

        if (sottoscrizione == null) {
            logger.error("Non eiste una sottoscrizione del sottoscrittore " + tipoSottoscrittore + " " + nomeSottoscrittore + " per la categoria di eventi " + categoriaEvnetiInterna.getNome());
            throw new Exception("Non eiste una sottoscrizione del sottoscrittore " + tipoSottoscrittore + " " + nomeSottoscrittore + " per la categoria di eventi " + categoriaEvnetiInterna.getNome());
        }

        ConsegnaEventoPubblicato messaggioDaConsegnare = new ConsegnaEventoPubblicato();
        messaggioDaConsegnare.setMessaggioSoap(new String(eventoPubblicato.getMessaggio().getBytes(), "UTF-8"));
        messaggioDaConsegnare.setTipoPubblicatore(eventoPubblicato.getPubblicatore().getTipo());
        messaggioDaConsegnare.setNomePubblicatore(eventoPubblicato.getPubblicatore().getNome());
        messaggioDaConsegnare.setCategoriaEventi(eventoPubblicato.getCategoriaEventi().getNome());
        messaggioDaConsegnare.setIdEventoPubblicato(eventoPubblicato.getId() + "");

        logger.debug("Prelevato il messaggio " + messaggioDaConsegnare.getMessaggioSoap());

        return messaggioDaConsegnare;
    }

    private ConsegnaEventoPubblicato prelevaEventoEsterno(String categoriaEventi, String idMessaggio, String tipoSottoscrittore, String nomeSottoscrittore) throws Exception {
        long id = Long.parseLong(idMessaggio.trim());
        EventoPubblicatoEsterno eventoPubblicato = daoEventoPubblicatoEsterno.findById(id, false);

        if (eventoPubblicato == null) {
            logger.error("Non esiste un messaggio con l'id specificato" + id);
            throw new DAOException("Non esiste un messaggio con l'id specificato" + id);
        }

        CategoriaEventiEsterna categoriaEvnetiEsterna = DAOCategoriaEventiEsternaFacade.verificaEsistenzaCategoriaEventiEsternaAttavia(categoriaEventi);

        Sottoscrittore sottoscrittore = daoSottoscrittore.findByTipoNome(tipoSottoscrittore, nomeSottoscrittore);

        if (sottoscrittore == null) {
            logger.error("Il sottoscrittore " + tipoSottoscrittore + " " + nomeSottoscrittore + " non esiste");
            throw new Exception("Il sottoscrittore " + tipoSottoscrittore + " " + nomeSottoscrittore + " non esiste");
        }

        SottoscrizioneEsterna sottoscrizione = daoCategoriaEventiEsterna.findByCategoriaEventiSottoscrittore(categoriaEvnetiEsterna, sottoscrittore);

        if (sottoscrizione == null) {
            logger.error("Non eiste una sottoscrizione del sottoscrittore " + tipoSottoscrittore + " " + nomeSottoscrittore + " per la categoria di eventi " + categoriaEvnetiEsterna.getNome());
            throw new Exception("Non eiste una sottoscrizione del sottoscrittore " + tipoSottoscrittore + " " + nomeSottoscrittore + " per la categoria di eventi " + categoriaEvnetiEsterna.getNome());
        }

        ConsegnaEventoPubblicato messaggioDaConsegnare = new ConsegnaEventoPubblicato();
        messaggioDaConsegnare.setMessaggioSoap(new String(eventoPubblicato.getMessaggio().getBytes(), "UTF-8"));
        messaggioDaConsegnare.setTipoPubblicatore(eventoPubblicato.getPubblicatore().getTipo());
        messaggioDaConsegnare.setNomePubblicatore(eventoPubblicato.getPubblicatore().getNome());
        messaggioDaConsegnare.setCategoriaEventi(eventoPubblicato.getCategoriaEventi().getNome());
        messaggioDaConsegnare.setIdEventoPubblicato(eventoPubblicato.getId() + "");

        logger.debug("Prelevato il messaggio " + messaggioDaConsegnare.getMessaggioSoap());

        return messaggioDaConsegnare;

    }
}
