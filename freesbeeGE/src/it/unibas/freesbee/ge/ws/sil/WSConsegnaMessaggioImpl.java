package it.unibas.freesbee.ge.ws.sil;

import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiEsterna;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiInterna;
import it.unibas.freesbee.ge.persistenza.IDAOGestoreEventi;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreEsterno;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreInterno;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrittore;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrizioneEsterna;
import it.unibas.freesbee.ge.ws.gestoreeventi.SOAPFault;
import javax.jws.WebService;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

@WebService(targetNamespace = "http://ge.freesbee.unibas.it/")
public class WSConsegnaMessaggioImpl implements IWSConsegnaMessaggio {

    private static org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog(WSConsegnaMessaggioImpl.class);
    public static final String OPERAZIONE_RICHIESTA_RICERCA_PUBBLICATORI = "ricercaPubblicatori";
    public static final String OPERAZIONE_RISPOSTA_RICERCA_PUBBLICATORE = "confermaPubblicatori";
    public static final String OPERAZIONE_RICHIESTA_RICERCA_CATEGORIA = "ricercaCategoria";
    public static final String OPERAZIONE_RISPOSTA_RICERCA_CATEGORIA = "confermaCategoria";
    public static final String OPERAZIONE_RICHIESTA_RIMUOVI_GE_SOTTOSCRITTORE = "rimuoviGESottoscrittore";
    public static final String OPERAZIONE_COMUNICAZIONE_ELIMINAZIONE_PUBBLICATORE = "comunicazioneEliminazionePubblicatore";
    private IDAOPubblicatoreEsterno daoPubblicatoreEsterno;
    private IDAOPubblicatoreInterno daoPubblicatoreInterno;
    private IDAOSottoscrittore daoSottoscrittore;
    private IDAOSottoscrizioneEsterna daoSottoscrizioneEsterna;
    private IDAOGestoreEventi daoGestoreEventi;
    private IDAOCategoriaEventiInterna daoCategoriaEventiInterna;
    private IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna;

    public WSConsegnaMessaggioImpl(IDAOPubblicatoreEsterno daoPubblicatoreEsterno, IDAOSottoscrittore daoSottoscrittore, IDAOGestoreEventi daoGestoreEventi, IDAOCategoriaEventiInterna daoCategoriaEventiInterna, IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna, IDAOPubblicatoreInterno daoPubblicatoreInterno, IDAOSottoscrizioneEsterna daoSottoscrizioneEsterna) {
        this.daoPubblicatoreEsterno = daoPubblicatoreEsterno;
        this.daoSottoscrittore = daoSottoscrittore;
        this.daoGestoreEventi = daoGestoreEventi;
        this.daoCategoriaEventiInterna = daoCategoriaEventiInterna;
        this.daoCategoriaEventiEsterna = daoCategoriaEventiEsterna;
        this.daoPubblicatoreInterno = daoPubblicatoreInterno;
        this.daoSottoscrizioneEsterna = daoSottoscrizioneEsterna;

    }

    public void consegna(Object messaggioSoap, String nomePubblicatore, String tipoPubblicatore, String categoriaEventi) throws SOAPFault {
        logger.debug("Contattato il web service  consegna.");
        Element elemento = (Element) messaggioSoap;
        Node elOperation = elemento.getFirstChild();
        if (elOperation == null || elOperation.getNodeName() == null) {
            return;
        }
        String operazione = elOperation.getNodeName();
        logger.info("Operazione: " + operazione);

        if (operazione.equals(OPERAZIONE_RICHIESTA_RICERCA_PUBBLICATORI)) {
            new OperationRicercaPubblicatori(daoSottoscrittore, daoCategoriaEventiInterna, daoCategoriaEventiEsterna, daoGestoreEventi, daoPubblicatoreInterno, daoPubblicatoreEsterno, daoSottoscrizioneEsterna).gestisciRichiesta(elOperation, tipoPubblicatore, nomePubblicatore);
        }

        if (operazione.equals(OPERAZIONE_RISPOSTA_RICERCA_PUBBLICATORE)) {
            new OperationRicercaPubblicatori(daoSottoscrittore, daoCategoriaEventiInterna, daoCategoriaEventiEsterna, daoGestoreEventi, daoPubblicatoreInterno, daoPubblicatoreEsterno, daoSottoscrizioneEsterna).gestisciRisposta(elOperation, tipoPubblicatore, nomePubblicatore);
        }

        if (operazione.equals(OPERAZIONE_RICHIESTA_RICERCA_CATEGORIA)) {
            new OperationRicercaCategoria(daoCategoriaEventiInterna, daoCategoriaEventiEsterna, daoGestoreEventi, daoSottoscrittore, daoPubblicatoreInterno, daoPubblicatoreEsterno, daoSottoscrizioneEsterna).gestisciRichiesta(elOperation, tipoPubblicatore, nomePubblicatore);
        }

        if (operazione.equals(OPERAZIONE_RISPOSTA_RICERCA_CATEGORIA)) {
            new OperationRicercaCategoria(daoCategoriaEventiInterna, daoCategoriaEventiEsterna, daoGestoreEventi, daoSottoscrittore, daoPubblicatoreInterno, daoPubblicatoreEsterno, daoSottoscrizioneEsterna).gestisciRisposta(elOperation, tipoPubblicatore, nomePubblicatore);
        }

        if (operazione.equals(OPERAZIONE_RICHIESTA_RIMUOVI_GE_SOTTOSCRITTORE)) {
            new OperationRimuoviGESottoscrittore(daoGestoreEventi, daoCategoriaEventiInterna, daoPubblicatoreInterno,daoSottoscrittore).gestisciRichiesta(elOperation, tipoPubblicatore, nomePubblicatore);
        }

        if (operazione.equals(OPERAZIONE_COMUNICAZIONE_ELIMINAZIONE_PUBBLICATORE)) {

            new OperationComunicazioneEliminazionePubblicatore(daoPubblicatoreEsterno, daoCategoriaEventiEsterna, daoGestoreEventi).gestisciComunicazione(elOperation, tipoPubblicatore, nomePubblicatore);
        }
    }
}

