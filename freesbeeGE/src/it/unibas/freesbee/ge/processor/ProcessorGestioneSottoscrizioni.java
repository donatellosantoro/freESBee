package it.unibas.freesbee.ge.processor;

import it.unibas.freesbee.ge.modello.CategoriaEventiEsterna;
import it.unibas.freesbee.ge.modello.CategoriaEventiInterna;
import it.unibas.freesbee.ge.modello.Configurazione;
import it.unibas.freesbee.ge.modello.ConfigurazioniFactory;
import it.unibas.freesbee.ge.modello.FiltroPubblicatoreEsterno;
import it.unibas.freesbee.ge.modello.FiltroPubblicatoreInterno;
import it.unibas.freesbee.ge.modello.GestoreEventi;
import it.unibas.freesbee.ge.modello.ICategoriaEventi;
import it.unibas.freesbee.ge.modello.ISottoscrizione;
import it.unibas.freesbee.ge.modello.PubblicatoreEsterno;
import it.unibas.freesbee.ge.modello.Sottoscrittore;
import it.unibas.freesbee.ge.modello.SottoscrizioneEsterna;
import it.unibas.freesbee.ge.modello.SottoscrizioneInterna;
import it.unibas.freesbee.ge.persistenza.DAOException;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiEsterna;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiInterna;
import it.unibas.freesbee.ge.persistenza.IDAOGestoreEventi;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreEsterno;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreInterno;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrittore;
import it.unibas.freesbee.ge.persistenza.facade.DAOCategoriaEventiEsternaFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOCategoriaEventiInternaFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOGestoreEventiFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOPubblicatoreEsternoFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOPubblicatoreInternoFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOSottoscrizioneEsternaFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOSottoscrizioneInternaFacade;
import it.unibas.freesbee.ge.ws.gestoreeventi.ClientGEControlProtocol;
import java.util.ArrayList;
import java.util.List;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProcessorGestioneSottoscrizioni implements Processor {

    public static final String SOTTOSCRIZIONE_ATTESA_PUBBLICATORI = "SOTTOSCRIZIONE_ATTESA_PUBBLICATORI";
    public static final String SOTTOSCRIZIONE_ATTESA_CATEGORIA = "SOTTOSCRIZIONE_ATTESA_CATEGORIA";
    private Log logger = LogFactory.getLog(this.getClass());
    private IDAOSottoscrittore daoSottoscrittore;
    private IDAOCategoriaEventiInterna daoCategoriaEventiInterna;
    private IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna;
    private IDAOPubblicatoreInterno daoPubblicatoreInterno;
    private IDAOPubblicatoreEsterno daoPubblicatoreEsterno;
    private IDAOGestoreEventi daoGestoreEventi;
    private String nome;

    public ProcessorGestioneSottoscrizioni(String categoriaEventi, IDAOCategoriaEventiInterna daoCategoriaEventiInterna, IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna, IDAOSottoscrittore daoSottoscrittore, IDAOPubblicatoreInterno daoPubblicatoreInterno, IDAOPubblicatoreEsterno daoPubblicatoreEsterno, IDAOGestoreEventi daoGestoreEventi) {
        this.daoSottoscrittore = daoSottoscrittore;
        this.daoCategoriaEventiInterna = daoCategoriaEventiInterna;
        this.daoCategoriaEventiEsterna = daoCategoriaEventiEsterna;
        this.daoPubblicatoreInterno = daoPubblicatoreInterno;
        this.daoPubblicatoreEsterno = daoPubblicatoreEsterno;
        this.daoGestoreEventi = daoGestoreEventi;
        this.nome = categoriaEventi;
    }

    public void process(Exchange exchange) throws Exception {
        String operationName = exchange.getProperty("OPERATION", String.class);
        logger.debug("------------- " + operationName + " --------------------");

        if (operationName.equals("ge:aggiungiSottoscrizione")) {
            aggiungiSottoscrizione(exchange);
        } else {
            if (operationName.equals("ge:eliminaSottoscrizione")) {
                eliminaSottoscrizione(exchange);
            } else {
                logger.error("operation non definita");
                exchange.setProperty(ProcessorFault.MESSAGGIO_ERRORE, "operation non definita");
                throw new Exception("operation non definita");
            }
        }
    }

    private void aggiungiSottoscrizione(Exchange exchange) throws Exception {
        try {
            ICategoriaEventi categoriaEventi = daoCategoriaEventiInterna.findByNome(nome);
            if (categoriaEventi != null) {
                aggiungiSottoscrizioneInterna(exchange);
                return;
            }

            categoriaEventi = daoCategoriaEventiEsterna.findByNome(nome);
            if (categoriaEventi != null) {
                aggiungiSottoscrizioneEsterna(exchange);
                return;
            }

            throw new Exception("La categoria eventi " + nome + " non e' presente nel database");
        } catch (Exception e) {
            logger.error(e.getMessage());
            exchange.setProperty(ProcessorFault.MESSAGGIO_ERRORE, e.getMessage());
            throw e;
        }
    }

    private void eliminaSottoscrizione(Exchange exchange) throws Exception {
        try {
            ICategoriaEventi categoriaEventi = daoCategoriaEventiInterna.findByNome(nome);
            if (categoriaEventi != null) {
                eliminaSottoscrizioneInterna(exchange);
                return;
            }

            categoriaEventi = daoCategoriaEventiEsterna.findByNome(nome);
            if (categoriaEventi != null) {
                eliminaSottoscrizioneEsterna(exchange);
                return;
            }

            throw new Exception("La categoria eventi " + nome + " non e' presente nel database");
        } catch (Exception e) {
            logger.error(e.getMessage());
            exchange.setProperty(ProcessorFault.MESSAGGIO_ERRORE, e.getMessage());
            throw e;
        }
    }

    private void aggiungiSottoscrizioneInterna(Exchange exchange) throws Exception {
        logger.info("Richiesta operazione: aggiungiSottoscrizione - INTERNA");
        Message requestMessage = exchange.getIn();
        String bodyMessaggio = requestMessage.getBody(String.class);

        SottoscrizioneInterna sottoscrizione = null;
        Sottoscrittore sottoscrittore = null;

        try {
            //Se non esite lancia eccezione
            CategoriaEventiInterna categoriaEventiInterna = DAOCategoriaEventiInternaFacade.verificaEsistenzaCategoriaEventiInternaAttavia(nome);

            sottoscrizione = SottoscrizioneInterna.binding(bodyMessaggio, categoriaEventiInterna);

            convalidaDatiSottoscrizione(sottoscrizione);
            convalidaDatiSottoscrizioneInterna(sottoscrizione);

            sottoscrittore = sottoscrizione.getSottoscrittore();

            convalidaDatiSottoscrittore(sottoscrittore);

            //Ricerco il sottoscrittore che può essere solo interno
            Sottoscrittore sogTrovato = daoSottoscrittore.findByTipoNome(sottoscrittore.getTipo(), sottoscrittore.getNome());

            if (sogTrovato == null) {
                logger.debug("Soggetto non presente nel database");
                DAOSottoscrizioneInternaFacade.aggiungiSottoscrizioneInterna(categoriaEventiInterna, sottoscrittore, sottoscrizione);

            } else {
                logger.info("Soggetto presente nel database");
                //Se esite lancia eccezione
                DAOSottoscrizioneInternaFacade.aggiungiSottoscrizioneInterna(categoriaEventiInterna, sogTrovato, sottoscrizione);
            }


        } catch (Exception e) {
            logger.error(e.getMessage());
            exchange.setProperty(ProcessorFault.MESSAGGIO_ERRORE, e.getMessage());
            throw e;
        }

    }

    private void eliminaSottoscrizioneInterna(Exchange exchange) throws Exception {
        logger.info("Richiesta operazione: eliminaSottoscrizione - INTERNA");

        Message requestMessage = exchange.getIn();
        String bodyMessaggio = requestMessage.getBody(String.class);

        Sottoscrittore sottoscrittore = null;

        try {
            //Se non esite lancia eccezione
            CategoriaEventiInterna categoriaEventiInterna = DAOCategoriaEventiInternaFacade.verificaEsistenzaCategoriaEventiInternaAttavia(nome);

            sottoscrittore = Sottoscrittore.binding(bodyMessaggio);

            convalidaDatiSottoscrittore(sottoscrittore);

            //Ricerco il sottoscrittore che può essere solo in terno o al limite il ge
            Sottoscrittore sogTrovato = daoSottoscrittore.findByTipoNome(sottoscrittore.getTipo(), sottoscrittore.getNome());

            if (sogTrovato != null) {
                logger.debug("Soggetto presente nel database");
                //Se non esite il sottoscrittore lancia eccezione
                DAOSottoscrizioneInternaFacade.eliminaSottoscrizioneInterna(categoriaEventiInterna, sogTrovato);

            } else {
                logger.error("Soggetto " + sottoscrittore + " non e' un sottoscrittore");
                throw new Exception("Soggetto " + sottoscrittore + " non e' un sottoscrittore");
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            exchange.setProperty(ProcessorFault.MESSAGGIO_ERRORE, e.getMessage());
            throw e;
        }

    }

    private void aggiungiSottoscrizioneEsterna(Exchange exchange) throws Exception {
        logger.info("Richiesta operazione: aggiungiSottoscrizione - ESTERNA");
        Message requestMessage = exchange.getIn();
        String bodyMessaggio = requestMessage.getBody(String.class);

        SottoscrizioneEsterna sottoscrizione = null;
        Sottoscrittore sottoscrittore = null;
        try {
            //Se non esite o non è attiva lancia eccezione
            CategoriaEventiEsterna categoriaEventiEsterna = DAOCategoriaEventiEsternaFacade.verificaEsistenzaCategoriaEventiEsternaAttavia(nome);

            sottoscrizione = SottoscrizioneEsterna.binding(bodyMessaggio, categoriaEventiEsterna);

            convalidaDatiSottoscrizione(sottoscrizione);

            sottoscrittore = sottoscrizione.getSottoscrittore();

            convalidaDatiSottoscrittore(sottoscrittore);

            //Ricerco il sottoscrittore che può essere solo in terno o al limite il ge
            Sottoscrittore sogTrovato = daoSottoscrittore.findByTipoNome(sottoscrittore.getTipo(), sottoscrittore.getNome());


            if (sogTrovato == null) {
                logger.debug("Soggetto non presente nel database");
                DAOSottoscrizioneEsternaFacade.aggiungiSottoscrizioneEsterna(categoriaEventiEsterna, sottoscrittore, sottoscrizione);

            } else {
                logger.info("Soggetto presente nel database");
                //Se esite lancia eccezione
                DAOSottoscrizioneEsternaFacade.aggiungiSottoscrizioneEsterna(categoriaEventiEsterna, sogTrovato, sottoscrizione);
            }


            if (DAOSottoscrizioneEsternaFacade.isInAttesaConfermaCategoriaEventi(sottoscrizione) && !DAOSottoscrizioneEsternaFacade.isInAttesaConfermaPubblicatori(sottoscrizione)) {
                logger.info(SOTTOSCRIZIONE_ATTESA_CATEGORIA);
                exchange.setProperty(SOTTOSCRIZIONE_ATTESA_CATEGORIA, sottoscrizione);
            }

            //Se devo confermare anche la categoria la confermo direttamento quando mi risponde per i pubblicatori
            if (DAOSottoscrizioneEsternaFacade.isInAttesaConfermaPubblicatori(sottoscrizione)) {
                logger.info(SOTTOSCRIZIONE_ATTESA_PUBBLICATORI);
                exchange.setProperty(SOTTOSCRIZIONE_ATTESA_PUBBLICATORI, sottoscrizione);
            }


        } catch (Exception e) {
            logger.error(e.getMessage());
            exchange.setProperty(ProcessorFault.MESSAGGIO_ERRORE, e.getMessage());
            throw e;
        }

    }

    private void eliminaSottoscrizioneEsterna(Exchange exchange) throws Exception {
        logger.info("Richiesta operazione: eliminaSottoscrizione - ESTERNA");

        Message requestMessage = exchange.getIn();
        String bodyMessaggio = requestMessage.getBody(String.class);

        Sottoscrittore sottoscrittore = null;

        try {
            //Se non esite lancia eccezione
            CategoriaEventiEsterna categoriaEventiEsterna = DAOCategoriaEventiEsternaFacade.verificaEsistenzaCategoriaEventiEsternaAttavia(nome);

            sottoscrittore = Sottoscrittore.binding(bodyMessaggio);

            convalidaDatiSottoscrittore(sottoscrittore);

            //Ricerco il sottoscrittore che può essere solo in terno o al limite il ge
            Sottoscrittore sogTrovato = daoSottoscrittore.findByTipoNome(sottoscrittore.getTipo(), sottoscrittore.getNome());

            if (sogTrovato != null) {
                logger.debug("Soggetto presente nel database");
                //Se non esite il sottoscrittore lancia eccezione
                DAOSottoscrizioneEsternaFacade.eliminaSottoscrizioneEsterna(categoriaEventiEsterna, sogTrovato);

                if (!categoriaEventiEsterna.isInAttesa()) {

                    PubblicatoreEsterno pubblicatoreGE = DAOCategoriaEventiEsternaFacade.getGestoreEvetniPubblicatorePerCategoriaEventi(categoriaEventiEsterna);

                    if (pubblicatoreGE == null) {
                        logger.error("La categoria di eventi " + categoriaEventiEsterna + " risulta confermata ma non esiste un gestore eventi pubblicatore per questa categoria");
                        throw new Exception("La categoria di eventi " + categoriaEventiEsterna + " risulta confermata ma non esiste un gestore eventi pubblicatore per questa categoria");
                    }

                    logger.debug("Verifico l'esistenza di sottoscrizioni confermate per la categoria di eventi " + categoriaEventiEsterna);
                    verificaEsistenszaSottoscrizioni(categoriaEventiEsterna.getNome());

                    logger.debug("La categoria eventi " + nome + " appartiene al gestore eventi " + pubblicatoreGE);
                }   
                    logger.debug("Verifico l'esistenza di sottoscrizioni non confermate per la categoria di eventi " + categoriaEventiEsterna);
                    verificaEsistenszaSottoscrizioniNonConfermate(categoriaEventiEsterna.getNome());

                

            } else {
                logger.error("Soggetto " + sottoscrittore + " non e' un sottoscrittore");
                throw new Exception("Soggetto " + sottoscrittore + " non e' un sottoscrittore");
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            exchange.setProperty(ProcessorFault.MESSAGGIO_ERRORE, e.getMessage());
            throw e;
        }

    }

    private void convalidaDatiSottoscrizione(ISottoscrizione sottoscrizione) throws Exception {
        //I sottoscrittori sono per forza SIL del dominio che hanno stipulato
        //un AS con il gesotre eventi
        if (sottoscrizione.getTipoSottoscrizione().equals("")) {
            logger.error("Bisogna specificare un tipo di sottoscrizione: CONSEGNA o NOTIFICA");
            throw new Exception("Bisogna specificare un tipo di sottoscrizione: CONSEGNA o NOTIFICA");
        }

        if (!sottoscrizione.getTipoSottoscrizione().equals(ISottoscrizione.TIPO_SOTTOSCRIZIONE_NOTIFICA) && !sottoscrizione.getTipoSottoscrizione().equals(ISottoscrizione.TIPO_SOTTOSCRIZIONE_CONSEGNA)) {
            logger.error("Il tipo di sottoscrizione specificata e' diversa da: CONSEGNA e NOTIFICA");
            throw new Exception("Il tipo di sottoscrizione specificata e' diversa da: CONSEGNA e NOTIFICA");
        }
    }

    private void convalidaDatiSottoscrizioneInterna(SottoscrizioneInterna sottoscrizione) throws Exception {
        if (nome.equalsIgnoreCase(CategoriaEventiInterna.GE_CONTROL_PROTOCOL)) {
//            if (!sottoscrizione.getTipoSottoscrizione().equals(Sottoscrizione.TIPO_SOTTOSCRIZIONE_CONSEGNA)) {
//                logger.error("La sottoscrizione per gli eventi della categoria " + CategoriaEvento.GE_CONTROL_PROTOCOL + " dev'essere di tipo " + Sottoscrizione.TIPO_SOTTOSCRIZIONE_CONSEGNA);
//                throw new Exception("La sottoscrizione per gli eventi della categoria " + CategoriaEvento.GE_CONTROL_PROTOCOL + " dev'essere di tipo " + Sottoscrizione.TIPO_SOTTOSCRIZIONE_CONSEGNA);
//            }
//           if (sottoscrizione.getFiltroContenuto() != null ) {
//                logger.error("La sottoscrizione per gli eventi della categoria " + CategoriaEvento.GE_CONTROL_PROTOCOL + " non deve avere filtri");
//                throw new Exception("La sottoscrizione per gli eventi della categoria " + CategoriaEvento.GE_CONTROL_PROTOCOL + " non deve avere filtri");
//            }

            //Non bisogna specificare filtri pubblicatori
            //Alla sottoscrizione viene aggiunto in automatico unfiltro che riguarda il GE stesso
            //perchè i SIL devono ricevere comunizazioni solo dal proprio GE
            if (sottoscrizione.getListaFiltroPublicatore() != null && sottoscrizione.getListaFiltroPublicatore().size() > 0) {
//                logger.error("La sottoscrizione per gli eventi della categoria " + CategoriaEventiInterna.GE_CONTROL_PROTOCOL + " non deve avere filtri pubblicatori");
//                throw new Exception("La sottoscrizione per gli eventi della categoria " + CategoriaEventiInterna.GE_CONTROL_PROTOCOL + " non deve avere filtri pubblicatori");
            }
            sottoscrizione.getListaFiltroPublicatore().add(aggiungiFiltroPubblicatore(sottoscrizione));
        }
    }

    private void convalidaDatiSottoscrittore(Sottoscrittore sottoscrittore) throws Exception {
        if (sottoscrittore.getNome().equals("")) {
            logger.error("Il nome del sottoscrittore non e' stato specificato");
            throw new Exception("Il nome del sottoscrittore non e' stato specificato");
        }

        if (sottoscrittore.getTipo().equals("")) {
            logger.error("Il tipo del sottoscrittore non e' stato specificato");
            throw new Exception("Il tipo del sottoscrittore non e' stato specificato");
        }
    }

    private FiltroPubblicatoreInterno aggiungiFiltroPubblicatore(SottoscrizioneInterna sottoscrizione) throws DAOException {
        Configurazione configurazione = ConfigurazioniFactory.getConfigurazioneIstance();
        //se è un GE
        DAOGestoreEventiFacade.verificaEsistenzaGestoreEventi(configurazione.getTipoGestoreEventi(), configurazione.getNomeGestoreEventi());
        //ed un pubblicatore interno per una categoria attiva
        DAOPubblicatoreInternoFacade.verificaEsistenzaPubblicatoreInterno(configurazione.getTipoGestoreEventi(), configurazione.getNomeGestoreEventi(), CategoriaEventiInterna.GE_CONTROL_PROTOCOL);
        FiltroPubblicatoreInterno filtroPubblicatore = new FiltroPubblicatoreInterno();
        filtroPubblicatore.setPubblicatore(daoPubblicatoreInterno.findByTipoNome(configurazione.getTipoGestoreEventi(), configurazione.getNomeGestoreEventi()));
        filtroPubblicatore.setSottoscrizione(sottoscrizione);
        return filtroPubblicatore;
    }

//    private GestoreEventi cercaGestoreEventiPubblicatore(CategoriaEventiEsterna categoriaEventi) throws DAOException {
//        for (PubblicatoreEsterno s : categoriaEventi.getListaPubblicatori()) {
//            GestoreEventi ge = daoGestoreEventi.findByTipoNome(s.getTipo(), s.getNome());
//            if (ge != null) {
//                return ge;
//            }
//        }
//        return null;
//    }
    private void verificaEsistenszaSottoscrizioni(String nomeCategoria) {
        try {
            CategoriaEventiEsterna categoriaEventi = daoCategoriaEventiEsterna.findByNome(nomeCategoria);
            if (categoriaEventi.getListaSottoscrizioni().size() <= 0) {
                logger.debug("Non ci sono piu' sottoscrizioni per la categoria di eventi " + nomeCategoria);

                PubblicatoreEsterno pubblicatore = DAOCategoriaEventiEsternaFacade.getGestoreEvetniPubblicatorePerCategoriaEventi(categoriaEventi);
                GestoreEventi gestoreEventi = daoGestoreEventi.findByTipoNome(pubblicatore.getTipo(), pubblicatore.getNome());

                logger.debug("Ci sono " + categoriaEventi.getListaPubblicatori().size() + " pubblicatori");

                List<PubblicatoreEsterno> lista = new ArrayList<PubblicatoreEsterno>(categoriaEventi.getListaPubblicatori());

                for (PubblicatoreEsterno pubb : lista) {
                    logger.debug("Elimino " + pubb);
                    DAOPubblicatoreEsternoFacade.eliminaPubblicatoreEsterno(categoriaEventi, pubb);
                }

                categoriaEventi.setInAttesa(true);
                daoCategoriaEventiEsterna.makePersistent(categoriaEventi);
                ClientGEControlProtocol.pubblicaRimuoviSottoscrizione(categoriaEventi, gestoreEventi);
            }
        } catch (DAOException d) {
            logger.error("Errore nell'accesso al database " + d);
            logger.error("Non e' stato possibile inviare la richiesta di eliminazione della sottoscrizione del gestore eventi per la categoria di eventi " + nomeCategoria);

        }
    }

    private void verificaEsistenszaSottoscrizioniNonConfermate(String nomeCategoria) {
        try {
            CategoriaEventiEsterna categoriaEventi = daoCategoriaEventiEsterna.findByNome(nomeCategoria);
            if (categoriaEventi.getListaSottoscrizioni().size() <= 0) {
                logger.debug("Non ci sono piu' sottoscrizioni per la categoria di eventi " + nomeCategoria);
                List<SottoscrizioneEsterna> lista = new ArrayList<SottoscrizioneEsterna>(categoriaEventi.getListaSottoscrizioni());

                for (SottoscrizioneEsterna sott : lista) {
                    List<FiltroPubblicatoreEsterno> listaP = new ArrayList<FiltroPubblicatoreEsterno>(sott.getListaIFiltroPublicatore());

                    logger.debug("Ci sono " + listaP.size() + " pubblicatori");

                    for (FiltroPubblicatoreEsterno fi : listaP) {
                    logger.debug("Elimino " + fi.getPubblicatore());
                        DAOPubblicatoreEsternoFacade.eliminaPubblicatoreEsterno(categoriaEventi, fi.getPubblicatore());
                    }
                }

                categoriaEventi.setInAttesa(true);
                daoCategoriaEventiEsterna.makePersistent(categoriaEventi);
            }
        } catch (DAOException d) {
            logger.error("Errore nell'accesso al database " + d);
            logger.error("Non e' stato possibile inviare la richiesta di eliminazione della sottoscrizione del gestore eventi per la categoria di eventi " + nomeCategoria);

        }
    }
}
