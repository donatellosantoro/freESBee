package it.unibas.freesbee.ge.ws.sil;

import it.unibas.freesbee.ge.modello.CategoriaEventiEsterna;
import it.unibas.freesbee.ge.modello.CategoriaEventiInterna;
import it.unibas.freesbee.ge.modello.FiltroData;
import it.unibas.freesbee.ge.modello.FiltroPubblicatoreInterno;
import it.unibas.freesbee.ge.modello.GestoreEventi;
import it.unibas.freesbee.ge.modello.ICategoriaEventi;
import it.unibas.freesbee.ge.modello.ISottoscrizione;
import it.unibas.freesbee.ge.modello.PubblicatoreEsterno;
import it.unibas.freesbee.ge.modello.PubblicatoreInterno;
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
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrizioneEsterna;
import it.unibas.freesbee.ge.persistenza.facade.DAOCategoriaEventiEsternaFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOGestoreEventiFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOPubblicatoreEsternoFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOPubblicatoreInternoFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOSottoscrizioneEsternaFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOSottoscrizioneInternaFacade;
import it.unibas.freesbee.ge.ws.gestoreeventi.ClientGEControlProtocol;
import it.unibas.freesbee.ge.ws.gestoreeventi.SOAPFault;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class OperationRicercaCategoria {

    private static Log logger = LogFactory.getLog(WSConsegnaMessaggioImpl.class);
    private IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna;
    private IDAOCategoriaEventiInterna daoCategoriaEventiInterna;
    private IDAOGestoreEventi daoGestoreEventi;
    private IDAOSottoscrittore daoSottoscrittore;
    private IDAOSottoscrizioneEsterna daoSottoscrizioneEsterna;
    private IDAOPubblicatoreInterno daoPubblicatoreInterno;
    private IDAOPubblicatoreEsterno daoPubblicatoreEsterno;

    public OperationRicercaCategoria(IDAOCategoriaEventiInterna daoCategoriaEventi, IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna, IDAOGestoreEventi daoGestoreEventi, IDAOSottoscrittore daoSottoscrittore, IDAOPubblicatoreInterno daoPubblicatoreInterno, IDAOPubblicatoreEsterno daoPubblicatoreEsterno, IDAOSottoscrizioneEsterna daoSottoscrizioneEsterna) {
        this.daoCategoriaEventiInterna = daoCategoriaEventi;
        this.daoGestoreEventi = daoGestoreEventi;
        this.daoSottoscrittore = daoSottoscrittore;
        this.daoSottoscrizioneEsterna = daoSottoscrizioneEsterna;
        this.daoCategoriaEventiEsterna = daoCategoriaEventiEsterna;
        this.daoPubblicatoreInterno = daoPubblicatoreInterno;
        this.daoPubblicatoreEsterno = daoPubblicatoreEsterno;
    }

    void gestisciRichiesta(Node elOperation, String tipoPubblicatore, String nomePubblicatore) throws SOAPFault {
        String categoriaEventiDaConfermare = leggiCategoriaEventiDaConfermare(elOperation);
        logger.debug("La categoria di eventi da confermare e' " + categoriaEventiDaConfermare);
        logger.debug("La richiesta di conferma della categoria di eventi e' stata pubblicata dal gestore eventi " + tipoPubblicatore + "  " + nomePubblicatore);

        try {

            GestoreEventi ge = DAOGestoreEventiFacade.getGE();

            GestoreEventi gestoreEventi = daoGestoreEventi.findByTipoNome(tipoPubblicatore, nomePubblicatore);

            if (gestoreEventi == null) {
                logger.error("Il gestore eventi " + tipoPubblicatore + " " + nomePubblicatore + " non e' conosciuto");
                throw new SOAPFault("Il gestore eventi " + tipoPubblicatore + " " + nomePubblicatore + " non e' conosciuto");
            }

            CategoriaEventiInterna categoriaEventi = daoCategoriaEventiInterna.findByNome(categoriaEventiDaConfermare);

            if (categoriaEventi != null) {

                if (categoriaEventi.getNome().equals(ICategoriaEventi.GE_CONTROL_PROTOCOL)) {
                    logger.error("La categoria di eventi " + ICategoriaEventi.GE_CONTROL_PROTOCOL + " non puo' essere confermata");
                    throw new SOAPFault("La categoria di eventi " + ICategoriaEventi.GE_CONTROL_PROTOCOL + " non puo'essere confermata");
                }

                if (!categoriaEventi.isAttiva()) {
                    logger.error("La categoria di eventi " + categoriaEventi + " non e' attiva");
                    throw new SOAPFault("La categoria di eventi " + categoriaEventi + " non e' attiva");
                }

                PubblicatoreInterno pubblicatoreInterno = daoPubblicatoreInterno.findByTipoNome(ge.getTipo(), ge.getNome());

                if (pubblicatoreInterno == null || !DAOPubblicatoreInternoFacade.isPubblicatoreInternoPerCategoriaEventiInterna(categoriaEventi, pubblicatoreInterno)) {
                    logger.error("Il gestore eventi " + ge + " non e' pubblicatore per la categoria di eventi " + categoriaEventi);
                    throw new SOAPFault("Il gestore eventi " + ge + " non e' pubblicatore per la categoria di eventi " + categoriaEventi);
                }

                Sottoscrittore sottoscrittore = daoSottoscrittore.findByTipoNome(tipoPubblicatore, nomePubblicatore);

                if (sottoscrittore == null) {
                    sottoscrittore = new Sottoscrittore(tipoPubblicatore, nomePubblicatore);
                }

                SottoscrizioneInterna sottoscrizione = daoCategoriaEventiInterna.findByCategoriaEventiSottoscrittore(categoriaEventi, sottoscrittore);

                if (sottoscrizione != null) {
                    logger.info("Il gestore eventi " + sottoscrittore + " e' gia' sottoscrittore per la categoria " + categoriaEventi);
                    ClientGEControlProtocol.pubblicaRispostaRicercaCategoriaEventi(categoriaEventi, gestoreEventi);
                    return;
                }

                logger.debug("Aggiungo il gestore eventi " + tipoPubblicatore + " " + nomePubblicatore + " come sottoscrittore per la categoria " + categoriaEventiDaConfermare);
                Calendar date = new GregorianCalendar();
                date.add(Calendar.MINUTE, 2);
                FiltroData filtroData = new FiltroData(date.getTime());

                sottoscrizione = new SottoscrizioneInterna(sottoscrittore, categoriaEventi, ISottoscrizione.TIPO_SOTTOSCRIZIONE_CONSEGNA, filtroData);
                FiltroPubblicatoreInterno filtroPubblicatore = new FiltroPubblicatoreInterno(pubblicatoreInterno, sottoscrizione);
                sottoscrizione.getListaFiltroPublicatore().add(filtroPubblicatore);
                DAOSottoscrizioneInternaFacade.aggiungiSottoscrizioneInterna(categoriaEventi, sottoscrittore, sottoscrizione);

                ClientGEControlProtocol.pubblicaRispostaRicercaCategoriaEventi(categoriaEventi, gestoreEventi);

            } else {
                logger.info("La categoria richiesta non e' nel mio dominio.");
            }

        } catch (DAOException ex) {
            ex.printStackTrace();
            logger.error("Impossibile cercare la categoria " + categoriaEventiDaConfermare + ". " + ex);
        }

    }

    public void gestisciRisposta(Node elOperation, String tipoPubblicatore, String nomePubblicatore) throws SOAPFault {
        String categoriaEventiConfermata = leggiCategoriaEventiDaConfermare(elOperation);
        logger.debug("La categoria di eventi confermata e' " + categoriaEventiConfermata);
        logger.debug("La conferma della categoria di eventi e' stata pubblicata dal gestore eventi " + tipoPubblicatore + "  " + nomePubblicatore);

        try {

            GestoreEventi gestoreEventi = daoGestoreEventi.findByTipoNome(tipoPubblicatore, nomePubblicatore);

            PubblicatoreEsterno pubblicatoreConferma = daoPubblicatoreEsterno.findByTipoNome(tipoPubblicatore, nomePubblicatore);

            if (gestoreEventi == null) {
                logger.error("Il gestore eventi " + tipoPubblicatore + " " + nomePubblicatore + " non e' conosciuto");
                throw new SOAPFault("Il gestore eventi " + tipoPubblicatore + " " + nomePubblicatore + " non e' conosciuto");
            }

            CategoriaEventiEsterna categoriaEventiEsterna = daoCategoriaEventiEsterna.findByNome(categoriaEventiConfermata);

            if (categoriaEventiEsterna == null) {
                logger.error("La categoria di eventi " + categoriaEventiConfermata + " non esiste");
                throw new SOAPFault("La categoria di eventi " + categoriaEventiConfermata + " non esiste");
            }

            if (categoriaEventiEsterna.getNome().equals(ICategoriaEventi.GE_CONTROL_PROTOCOL)) {
                logger.error("La categoria di eventi " + ICategoriaEventi.GE_CONTROL_PROTOCOL + " non puo' essere confermata");
                throw new SOAPFault("La categoria di eventi " + ICategoriaEventi.GE_CONTROL_PROTOCOL + " non puo'essere confermata");
            }

            if (!categoriaEventiEsterna.isAttiva()) {
                logger.error("La categoria di eventi " + categoriaEventiConfermata + " non e' attiva");
                throw new SOAPFault("La categoria di eventi " + categoriaEventiConfermata + " non e' attiva");
            }

            if (!categoriaEventiEsterna.isInAttesa()) {
                logger.info("La categoria di eventi " + categoriaEventiConfermata + " e' stata gia' confermata");

                PubblicatoreEsterno pubblicatoreGE = DAOCategoriaEventiEsternaFacade.getGestoreEvetniPubblicatorePerCategoriaEventi(categoriaEventiEsterna);

                if (pubblicatoreGE == null) {
                    logger.error("La categoria di eventi " + categoriaEventiConfermata + " risulta confermata ma non esiste un gestore eventi pubblicatore per questa categoria");
                    throw new SOAPFault("La categoria di eventi " + categoriaEventiConfermata + " risulta confermata ma non esiste un gestore eventi pubblicatore per questa categoria");
                }

                if (pubblicatoreGE != null) {
                    logger.debug("Il gestore eventi " + pubblicatoreGE + " e' il pubblicatore per la categoria di eventi " + categoriaEventiConfermata);

                    if (pubblicatoreConferma == null || pubblicatoreGE.compareTo(pubblicatoreConferma) != 0) {
                        logger.error("Il gestore eventi " + tipoPubblicatore + " " + nomePubblicatore + " che ha confermato la categoria e' differente dall'attuale pubblicatore " + pubblicatoreGE + " della categoria ");
                        throw new SOAPFault("Il gestore eventi " + tipoPubblicatore + " " + nomePubblicatore + " che ha confermato la categoria e' differente dall'attuale pubblicatore " + pubblicatoreGE + " della categoria ");
                    }

                }

                return;
            }

            if (pubblicatoreConferma == null) {
                pubblicatoreConferma = new PubblicatoreEsterno(gestoreEventi.getTipo(), gestoreEventi.getNome());
            }


            List<SottoscrizioneEsterna> listaSottoscrizioni = categoriaEventiEsterna.getListaSottoscrizioni();
            for (SottoscrizioneEsterna sot : listaSottoscrizioni) {
                if (DAOSottoscrizioneEsternaFacade.isInAttesaConfermaCategoriaEventi(sot) && !DAOSottoscrizioneEsternaFacade.isInAttesaConfermaPubblicatori(sot)) {
                    logger.debug("Conferma sottoscrizione del sottoscrittore " + sot.getSottoscrittore() + " per la categoria di eventi " + sot.getCategoriaEventi().getNome());
                    sot.setScadenzaAttesa(null);
                    daoSottoscrizioneEsterna.makePersistent(sot);
                }
            }
            logger.debug("Confermate le sottoscrizioni della categoria di eventi " + categoriaEventiEsterna + " in attesa di conferma solo della categoria");

            DAOPubblicatoreEsternoFacade.aggiungiPubblicatoreEsterno(categoriaEventiEsterna, pubblicatoreConferma);
            categoriaEventiEsterna.setInAttesa(false);
            daoCategoriaEventiEsterna.makePersistent(categoriaEventiEsterna);

            logger.debug("Confermata la categoria di eventi " + categoriaEventiEsterna);
            logger.debug("Aggiunto il gestore eventi " + gestoreEventi + " come pubblicatore della categoria di eventi " + categoriaEventiEsterna);

        } catch (DAOException ex) {
            logger.error("Errore nella lettura dal db. " + ex);
            throw new SOAPFault("Errore nella lettura dal db. " + ex);
        }
    }

    private String leggiCategoriaEventiDaConfermare(Node elOperation) throws SOAPFault {
        return leggiTestoNodo(elOperation, "categoriaEventiConferma", "la categoria eventi da confermare");
    }

    private Node cercaNodo(Node nodoPadre, String string) {
        NodeList nodeList = nodoPadre.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node n = nodeList.item(i);
            if (n.getNodeName().equalsIgnoreCase(string)) {
                return n;
            }
        }
        return null;
    }

    private String leggiTestoNodo(Node nodoPadre, String nomeNodo, String errore) throws SOAPFault {
        Node n = cercaNodo(nodoPadre, nomeNodo);
        if (n == null) {
            throw new SOAPFault("La richiesta di informazioni sulla categoria di eventi deve contenere " + errore);
        }
        return n.getTextContent();
    }
}
