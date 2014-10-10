package it.unibas.freesbee.ge.processor;

import it.unibas.freesbee.ge.modello.CategoriaEventiInterna;
import it.unibas.freesbee.ge.modello.GestoreEventi;
import it.unibas.freesbee.ge.modello.PubblicatoreInterno;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiInterna;
import it.unibas.freesbee.ge.persistenza.IDAOEventoPubblicatoInterno;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreInterno;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrizioneInterna;
import it.unibas.freesbee.ge.persistenza.facade.DAOCategoriaEventiInternaFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOGestoreEventiFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOPubblicatoreInternoFacade;
import it.unibas.freesbee.ge.ws.gestoreeventi.ClientGEControlProtocol;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProcessorGestionePubblicatori implements Processor {

    private Log logger = LogFactory.getLog(this.getClass());
    private IDAOPubblicatoreInterno daoPubblicatoreInterno;
    private IDAOCategoriaEventiInterna daoCategoriaEventiInterna;
    private IDAOEventoPubblicatoInterno daoEventoPubblicatoInterno;
    private IDAOSottoscrizioneInterna daoSottoscrizioneInterna;
    private String nome;

    public ProcessorGestionePubblicatori(String categoriaEventi, IDAOPubblicatoreInterno daoSoggetto, IDAOCategoriaEventiInterna daoCategoriaEvento, IDAOSottoscrizioneInterna daoSottoscrizione, IDAOEventoPubblicatoInterno daoEventoPubblicato) {
        this.daoPubblicatoreInterno = daoSoggetto;
        this.daoCategoriaEventiInterna = daoCategoriaEvento;
        this.daoEventoPubblicatoInterno = daoEventoPubblicato;
        this.daoSottoscrizioneInterna = daoSottoscrizione;
        this.nome = categoriaEventi;
    }

    public void process(Exchange exchange) throws Exception {

        String operationName = exchange.getProperty("OPERATION", String.class);
        logger.debug("------------- " + operationName + " --------------------");

        if (operationName.equals("ge:aggiungiPubblicatore")) {
            aggiungiPubblicatore(exchange);
        } else {
            if (operationName.equals("ge:eliminaPubblicatore")) {
                eliminaPubblicatore(exchange);
            } else {
                logger.error("operation non definita");
                exchange.setProperty(ProcessorFault.MESSAGGIO_ERRORE, "operation non definita");
                throw new Exception("operation non definita");
            }
        }
    }

    private void aggiungiPubblicatore(Exchange exchange) throws Exception {
        logger.info("Richiesta operazione: aggiungiPubblicatore");
        Message requestMessage = exchange.getIn();
        String bodyMessaggio = requestMessage.getBody(String.class);

        PubblicatoreInterno pubblicatore = null;
        try {

            //Se non esite lancia eccezione
            CategoriaEventiInterna categoriaEventi = DAOCategoriaEventiInternaFacade.verificaEsistenzaCategoriaEventiInternaAttavia(nome);

            GestoreEventi ge = DAOGestoreEventiFacade.getGE();

            PubblicatoreInterno pubblicatoreInterno = daoPubblicatoreInterno.findByTipoNome(ge.getTipo(), ge.getNome());

            if (pubblicatoreInterno == null || !DAOPubblicatoreInternoFacade.isPubblicatoreInternoPerCategoriaEventiInterna(categoriaEventi, pubblicatoreInterno)) {
                throw new Exception("Il gestore eventi " + ge + " non e' pubblicatore per la categoria di eventi " + categoriaEventi);
            }

            pubblicatore = PubblicatoreInterno.binding(bodyMessaggio);

            convalidaDati(pubblicatore, categoriaEventi);


            //Ricerco il pubblicatore solo fra quelli interni
            PubblicatoreInterno sogTrovato = daoPubblicatoreInterno.findByTipoNome(pubblicatore.getTipo(), pubblicatore.getNome());

            if (sogTrovato == null) {
                logger.info("Pubblicatore non presente nel database");
                DAOPubblicatoreInternoFacade.aggiungiPubblicatoreInterno(categoriaEventi, pubblicatore);

            } else {
                logger.info("Pubblicatore presente nel database");
                //Se esite lancia eccezione
                DAOPubblicatoreInternoFacade.aggiungiPubblicatoreInterno(categoriaEventi, sogTrovato);
            }


        } catch (Exception e) {
            logger.error(e.getMessage());
            exchange.setProperty(ProcessorFault.MESSAGGIO_ERRORE, e.getMessage());
            throw e;
        }

    }

    private void eliminaPubblicatore(Exchange exchange) throws Exception {
        logger.info("Richiesta operazione: eliminaPubblicatore");
        Message requestMessage = exchange.getIn();
        String bodyMessaggio = requestMessage.getBody(String.class);

        PubblicatoreInterno pubblicatore = null;
        try {

            //Se non esite lancia eccezione
            CategoriaEventiInterna categoriaEventi = DAOCategoriaEventiInternaFacade.verificaEsistenzaCategoriaEventiInternaAttavia(nome);

            GestoreEventi ge = DAOGestoreEventiFacade.getGE();

            PubblicatoreInterno pubblicatoreInterno = daoPubblicatoreInterno.findByTipoNome(ge.getTipo(), ge.getNome());

            if (pubblicatoreInterno == null || !DAOPubblicatoreInternoFacade.isPubblicatoreInternoPerCategoriaEventiInterna(categoriaEventi, pubblicatoreInterno)) {
                throw new Exception("Il gestore eventi " + ge + " non e' pubblicatore per la categoria di eventi " + categoriaEventi);
            }

            pubblicatore = PubblicatoreInterno.binding(bodyMessaggio);

            convalidaDati(pubblicatore, categoriaEventi);

            //Ricerco il pubblicatore solo fra quelli interni
            PubblicatoreInterno sogTrovato = daoPubblicatoreInterno.findByTipoNome(pubblicatore.getTipo(), pubblicatore.getNome());

            if (sogTrovato == null) {
                exchange.setProperty(ProcessorFault.MESSAGGIO_ERRORE, "Soggetto " + pubblicatore + " non e' un pubblicatore");
                throw new Exception("Soggetto " + pubblicatore + " non e' un pubblicatore");
            }


            //Se non esite lancia eccezione
            DAOPubblicatoreInternoFacade.eliminaPubblicatoreInterno(categoriaEventi, sogTrovato);

            ClientGEControlProtocol.pubblicaEsternamenteComunicazioneRimozionePubblicatore(sogTrovato, categoriaEventi);
            ClientGEControlProtocol.pubblicaInternamenteComunicazioneRimozionePubblicatore(sogTrovato, categoriaEventi);

        } catch (Exception e) {
            logger.error(e.getMessage());
            exchange.setProperty(ProcessorFault.MESSAGGIO_ERRORE, e.getMessage());
            throw e;
        }
    }

    private void convalidaDati(PubblicatoreInterno pubblicatore, CategoriaEventiInterna categoriaEventi) throws Exception {
        //I pubblicatori sono per forza SIL del dominio che hanno stipulato
        //un AS con il gesotre eventi
        if (categoriaEventi.getNome().equals(CategoriaEventiInterna.GE_CONTROL_PROTOCOL)) {
            logger.error("Il servizio non e' accessibile per la categoria " + CategoriaEventiInterna.GE_CONTROL_PROTOCOL);
            throw new Exception("Il servizio non e' accessibile per la categoria " + CategoriaEventiInterna.GE_CONTROL_PROTOCOL);
        }

        if (pubblicatore.getNome().equals("")) {
            logger.error("Il nome del pubblicatore non e' stato specificato");
            throw new Exception("Il nome del pubblicatore non e' stato specificato");
        }

        if (pubblicatore.getTipo().equals("")) {
            logger.error("Il tipo del pubblicatore non e' stato specificato");
            throw new Exception("Il tipo del pubblicatore non e' stato specificato");
        }

        return;
    }
}