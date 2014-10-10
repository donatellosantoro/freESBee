package it.unibas.freesbee.ge.ws.sil;

import it.unibas.freesbee.ge.modello.CategoriaEventiInterna;
import it.unibas.freesbee.ge.modello.GestoreEventi;
import it.unibas.freesbee.ge.modello.ICategoriaEventi;
import it.unibas.freesbee.ge.modello.PubblicatoreInterno;
import it.unibas.freesbee.ge.modello.Sottoscrittore;
import it.unibas.freesbee.ge.modello.SottoscrizioneInterna;
import it.unibas.freesbee.ge.persistenza.DAOException;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiInterna;
import it.unibas.freesbee.ge.persistenza.IDAOGestoreEventi;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreInterno;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrittore;
import it.unibas.freesbee.ge.persistenza.facade.DAOGestoreEventiFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOPubblicatoreInternoFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOSottoscrizioneInternaFacade;
import it.unibas.freesbee.ge.ws.gestoreeventi.SOAPFault;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class OperationRimuoviGESottoscrittore {

    private static org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog(WSConsegnaMessaggioImpl.class);
    private IDAOGestoreEventi daoGestoreEventi;
    private IDAOCategoriaEventiInterna daoCategoriaEventiInterna;
    private IDAOPubblicatoreInterno daoPubblicatoreInterno;
    private IDAOSottoscrittore daoSottoscrittore;

    public OperationRimuoviGESottoscrittore(IDAOGestoreEventi daoGestoreEventi, IDAOCategoriaEventiInterna daoCategoriaEventiInterna, IDAOPubblicatoreInterno daoPubblicatoreInterno, IDAOSottoscrittore daoSottoscrittore) {
        this.daoGestoreEventi = daoGestoreEventi;
        this.daoCategoriaEventiInterna = daoCategoriaEventiInterna;
        this.daoPubblicatoreInterno = daoPubblicatoreInterno;
        this.daoSottoscrittore = daoSottoscrittore;

    }

    void gestisciRichiesta(Node elOperation, String tipoPubblicatore, String nomePubblicatore) throws SOAPFault {

        String nomeSottoscrittore = leggiNomeSottoscrittore(elOperation);
        String tipoSottoscrittore = leggiTipoSottoscrittore(elOperation);
        String categoriaSottoscrizione = leggiCategoriaSottoscrizione(elOperation);

        logger.debug("La richiesta di eliminazione della propria sottscrizione per la categoria di eventi " + categoriaSottoscrizione + " e' stata pubblicata dal gestore eventi " + tipoPubblicatore + "  " + nomePubblicatore);

        try {

            GestoreEventi ge = DAOGestoreEventiFacade.getGE();

            GestoreEventi gestoreEventi = daoGestoreEventi.findByTipoNome(tipoPubblicatore, nomePubblicatore);

            if (gestoreEventi == null) {
                logger.error("Il gestore eventi " + tipoPubblicatore + " " + nomePubblicatore + " non e' conosciuto");
                throw new SOAPFault("Il gestore eventi " + tipoPubblicatore + " " + nomePubblicatore + " non e' conosciuto");
            }

            GestoreEventi gestoreEventiSottoscrittore = daoGestoreEventi.findByTipoNome(tipoSottoscrittore, nomeSottoscrittore);

            if (gestoreEventiSottoscrittore == null || gestoreEventi.compareTo(gestoreEventiSottoscrittore) != 0) {
                logger.error("Il gestore eventi pubblicatore " + gestoreEventi + " e' differente da quello sottoscrittore " + tipoSottoscrittore +" " +nomeSottoscrittore);
                throw new SOAPFault("Il gestore eventi pubblicatore " + gestoreEventi + " e' differente da quello sottoscrittore " + tipoSottoscrittore +" " +nomeSottoscrittore);
            }

            CategoriaEventiInterna categoriaEventi = daoCategoriaEventiInterna.findByNome(categoriaSottoscrizione);


            if (categoriaEventi == null) {
                logger.error("La categoria di eventi " + categoriaSottoscrizione + " non e' del dominio del gestore eventi");
                throw new SOAPFault("La categoria di eventi " + categoriaSottoscrizione + " non e' del dominio del gestore eventi");
            }

            if (categoriaEventi.getNome().equals(ICategoriaEventi.GE_CONTROL_PROTOCOL)) {
                logger.error("Non si possono eliminare le sottoscrizioni per la categoria di eventi " + ICategoriaEventi.GE_CONTROL_PROTOCOL);
                throw new SOAPFault("Non si possono eliminare le sottoscrizioni per la categoria di eventi " + ICategoriaEventi.GE_CONTROL_PROTOCOL);
            }

            PubblicatoreInterno pubblicatoreInterno = daoPubblicatoreInterno.findByTipoNome(ge.getTipo(), ge.getNome());

            if (pubblicatoreInterno == null || !DAOPubblicatoreInternoFacade.isPubblicatoreInternoPerCategoriaEventiInterna(categoriaEventi, pubblicatoreInterno)) {
                logger.error("Il gestore eventi " + ge + " non e' pubblicatore per la categoria di eventi " + categoriaEventi);
                throw new SOAPFault("Il gestore eventi " + ge + " non e' pubblicatore per la categoria di eventi " + categoriaEventi);
            }


            Sottoscrittore sottoscrittore = daoSottoscrittore.findByTipoNome(tipoSottoscrittore, nomeSottoscrittore);
            if (sottoscrittore == null) {
                logger.info("Il gestore eventi " + gestoreEventi + " non e' un sottoscrittore");
                return;
            }

            SottoscrizioneInterna sottoscrizione = daoCategoriaEventiInterna.findByCategoriaEventiSottoscrittore(categoriaEventi, sottoscrittore);

            if (sottoscrizione == null) {
                logger.info("Il gestore eventi " + sottoscrittore + " non e' sottoscrittore per la categoria " + categoriaEventi);
                return;
            }


            logger.debug("Elimino il gestore eventi " + sottoscrittore + " come sottoscrittore per la categoria " + categoriaEventi);

            DAOSottoscrizioneInternaFacade.eliminaSottoscrizioneInterna(categoriaEventi, sottoscrittore);






        } catch (DAOException ex) {
            logger.warn("Impossibile cercare la categoria " + categoriaSottoscrizione + ". " + ex);
        }

    }

    private String leggiNomeSottoscrittore(Node elOperation) throws SOAPFault {
        return leggiTestoNodo(elOperation, "nomeSottoscrittore", "il nome del sottoscrittore");
    }

    private String leggiTipoSottoscrittore(Node elOperation) throws SOAPFault {
        return leggiTestoNodo(elOperation, "tipoSottoscrittore", "il tipo del sottoscrittore");
    }

    private String leggiCategoriaSottoscrizione(Node elOperation) throws SOAPFault {
        return leggiTestoNodo(elOperation, "categoriaSottoscrizione", "la categoria di sottoscrizione");
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
