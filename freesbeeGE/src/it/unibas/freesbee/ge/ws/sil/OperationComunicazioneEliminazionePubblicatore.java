package it.unibas.freesbee.ge.ws.sil;

import it.unibas.freesbee.ge.modello.CategoriaEventiEsterna;
import it.unibas.freesbee.ge.modello.FiltroPubblicatoreEsterno;
import it.unibas.freesbee.ge.modello.GestoreEventi;
import it.unibas.freesbee.ge.modello.PubblicatoreEsterno;
import it.unibas.freesbee.ge.modello.SottoscrizioneEsterna;
import it.unibas.freesbee.ge.persistenza.DAOException;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiEsterna;
import it.unibas.freesbee.ge.persistenza.IDAOGestoreEventi;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreEsterno;
import it.unibas.freesbee.ge.persistenza.facade.DAOCategoriaEventiEsternaFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOGestoreEventiFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOPubblicatoreEsternoFacade;
import it.unibas.freesbee.ge.ws.gestoreeventi.ClientGEControlProtocol;
import it.unibas.freesbee.ge.ws.gestoreeventi.SOAPFault;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class OperationComunicazioneEliminazionePubblicatore {

    private static Log logger = LogFactory.getLog(WSConsegnaMessaggioImpl.class);
    private IDAOPubblicatoreEsterno daoPubblicatoreEsterno;
    private IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna;
    private IDAOGestoreEventi daoGestoreEventi;

    public OperationComunicazioneEliminazionePubblicatore(IDAOPubblicatoreEsterno daoPubblicatoreEsterno, IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna, IDAOGestoreEventi daoGestoreEventi) {
        this.daoPubblicatoreEsterno = daoPubblicatoreEsterno;
        this.daoCategoriaEventiEsterna = daoCategoriaEventiEsterna;
        this.daoGestoreEventi = daoGestoreEventi;

    }

    public void gestisciComunicazione(Node elOperation, String tipoPubblicatore, String nomePubblicatore) throws SOAPFault {
        PubblicatoreEsterno pubblicatoreDaEliminare = leggiPubblicatore(elOperation);
        String categoriaEventiPubblicaotreEliminato = leggiCategoriaPubblicata(elOperation);
        logger.debug("Il pubblicatore da eliminare e' " + pubblicatoreDaEliminare + " per la categoria eventi " + categoriaEventiPubblicaotreEliminato);
        logger.debug("Il messaggio di eliminazione del pubblicattore e' stato pubblicato dal gestore eventi " + tipoPubblicatore + " - " + nomePubblicatore);

        //Elimina anche i fitri pubblicatori che non sono stati ancora confermati
        try {

            GestoreEventi gestoreEventi = daoGestoreEventi.findByTipoNome(tipoPubblicatore, nomePubblicatore);

            if (gestoreEventi == null) {
                logger.error("Il gestore eventi " + tipoPubblicatore + " " + nomePubblicatore + " non e' conosciuto");
                throw new SOAPFault("Il gestore eventi " + tipoPubblicatore + " " + nomePubblicatore + " non e' conosciuto");
            }

            CategoriaEventiEsterna categoriaEventi = daoCategoriaEventiEsterna.findByNome(categoriaEventiPubblicaotreEliminato);

            //Solo se non esiste lancia eccezione
            if (categoriaEventi == null) {
                logger.error("La categoria eventi " + categoriaEventiPubblicaotreEliminato + " non esiste");
                throw new SOAPFault("La categoria eventi " + categoriaEventiPubblicaotreEliminato + " non esiste");
            }

            if (!categoriaEventi.isInAttesa()) {

                //Verifico se il gestore e venti che ha inviato la comunicazione esiste ed e' pubblicatore per la categoria eventi
                //per cui sta facendo la comunicazione
                PubblicatoreEsterno pubblicatoreEsternoGE = daoPubblicatoreEsterno.findByTipoNome(tipoPubblicatore, nomePubblicatore);

                if (pubblicatoreEsternoGE == null) {
                    logger.error("Il gestore eventi " + tipoPubblicatore + " " + nomePubblicatore + " non e' pubblicatore esterno ");
                    throw new SOAPFault("Il gestore eventi " + tipoPubblicatore + " " + nomePubblicatore + " non e' pubblicatore esterno ");
                } else {
                    if (!DAOPubblicatoreEsternoFacade.isPubblicatoreEsternoPerCategoriaEventiEsterna(categoriaEventi, pubblicatoreEsternoGE)) {
                        logger.error("Il gestore eventi " + tipoPubblicatore + " " + nomePubblicatore + " non e' pubblicatore per la categoria eventi " + categoriaEventi);
                        throw new SOAPFault("Il gestore eventi " + tipoPubblicatore + " " + nomePubblicatore + " non e' pubblicatore per la categoria eventi " + categoriaEventi);
                    }
                }

            }




            PubblicatoreEsterno sogTrovato = daoPubblicatoreEsterno.findByTipoNome(pubblicatoreDaEliminare.getTipo(), pubblicatoreDaEliminare.getNome());

            //Soggetto non esistente
            if (sogTrovato == null) {
                logger.info("Il soggetto " + pubblicatoreDaEliminare + " non esiste");
                return;
            }

            if (DAOPubblicatoreEsternoFacade.isPubblicatoreEsternoPerCategoriaEventiEsterna(categoriaEventi, sogTrovato)) {
                //Soggetto pubblicatore esterno confermato
                logger.debug("Il pubblicatore " + sogTrovato + " e' un pubblicatore confermato per la categoria " + categoriaEventi);
                DAOPubblicatoreEsternoFacade.eliminaPubblicatoreEsterno(categoriaEventi, sogTrovato);
                ClientGEControlProtocol.pubblicaInternamenteComunicazioneRimozionePubblicatore(sogTrovato, categoriaEventi);
            } else {
                //Soggetto forse pubblicatore esterno non confermato
                if (DAOPubblicatoreEsternoFacade.isPubblicatoreEsternoDaConfermare(categoriaEventi, sogTrovato)) {
                    //Pubblicatore
                    logger.debug("Il pubblicatore " + sogTrovato + " e' un pubblicatore non confermato per la categoria " + categoriaEventi);
                    DAOPubblicatoreEsternoFacade.eliminaPubblicatoreEsterno(categoriaEventi, sogTrovato);
                    ClientGEControlProtocol.pubblicaInternamenteComunicazioneRimozionePubblicatore(sogTrovato, categoriaEventi);
                } else {
                    logger.info("Il soggetto " + sogTrovato + " non e' un pubblicatore esterno per la categoria eventi specificata");
                    return;
                }

            }

            if (!categoriaEventi.isInAttesa()) {
                logger.debug("Verifico l'esistenza di sottoscrizioni confermate per la categoria di eventi " + categoriaEventiPubblicaotreEliminato);
                verificaEsistenszaSottoscrizioni(categoriaEventiPubblicaotreEliminato);
            }


            logger.debug("Verifico l'esistenza di sottoscrizioni non confermate per la categoria di eventi " + categoriaEventi);
            verificaEsistenszaSottoscrizioniNonConfermate(categoriaEventi.getNome());


        } catch (DAOException ex) {
            ex.printStackTrace();
            logger.error("Errore nell'accesso al database " + ex);
            throw new SOAPFault("Errore nell'accesso al database " + ex);
        }

        return;

    }

    private void verificaEsistenszaSottoscrizioni(String nomeCategoria) {
        try {
            CategoriaEventiEsterna categoriaEventi = daoCategoriaEventiEsterna.findByNome(nomeCategoria);
            if (categoriaEventi.getListaSottoscrizioni().size() <= 0) {
                logger.debug("Non ci sono piu' sottoscrizioni per la categoria di eventi " + nomeCategoria);

                PubblicatoreEsterno pubblicatore = DAOCategoriaEventiEsternaFacade.getGestoreEvetniPubblicatorePerCategoriaEventi(categoriaEventi);
                GestoreEventi gestoreEventi = daoGestoreEventi.findByTipoNome(pubblicatore.getTipo(), pubblicatore.getNome());

                List<PubblicatoreEsterno> lista = new ArrayList<PubblicatoreEsterno>(categoriaEventi.getListaPubblicatori());

                for (PubblicatoreEsterno pubb : lista) {
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

    private PubblicatoreEsterno leggiPubblicatore(Node elOperation) throws SOAPFault {
        Node nodePubblicatore = cercaNodo(elOperation, "pubblicatore");
        if (nodePubblicatore == null) {
            logger.error("La comunicazione di eliminazione di un pubblicatore deve contenere il pubblicatore da elimminare");
            throw new SOAPFault("La comunicazione di eliminazione di un pubblicatore deve contenere il pubblicatore da elimminare");
        }
        PubblicatoreEsterno pubblicatoreDaEliminare = new PubblicatoreEsterno();
        pubblicatoreDaEliminare.setNome(leggiTestoNodo(nodePubblicatore, "nomePubblicatore", "il nome del pubblicatore"));
        pubblicatoreDaEliminare.setTipo(leggiTestoNodo(nodePubblicatore, "tipoPubblicatore", "il tipo del pubblicatore"));

        return pubblicatoreDaEliminare;
    }

    private String leggiCategoriaPubblicata(Node elOperation) throws SOAPFault {
        return leggiTestoNodo(elOperation, "categoriaPubblicata", "la categoria pubblicata");
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
            throw new SOAPFault("La richiesta di informazioni sui pubblicatori deve contenere " + errore);
        }
        return n.getTextContent();
    }
}
