package it.unibas.silvio.processor;

import it.unibas.silvio.modello.IstanzaPortType;
import it.unibas.silvio.modello.Messaggio;
import it.unibas.silvio.persistenza.*;
import it.unibas.silvio.util.CostantiSilvio;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProcessorCorrelaMessaggio implements Processor {

    private Log logger = LogFactory.getLog(this.getClass());
    private IDAOMessaggio daoMessaggio;

    public ProcessorCorrelaMessaggio(IDAOMessaggio daoMessaggio) {
        this.daoMessaggio = daoMessaggio;
    }

    public void process(Exchange exchange) throws Exception {
        Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiSilvio.MESSAGGIO_RICHIESTA);
        IstanzaPortType istanzaPortType = messaggio.getIstanzaOperation().getIstanzaPortType();
        if (istanzaPortType.isErogazioneRisposta()) {
            //SU QUESTO CANALE ACCETTIAMO SOLO MESSAGGI DI RISPOSTA, CHE DEVONO ESSERE CORRELATI AL MESSAGGIO DI RICHIESTA

            String riferimentoMessaggioRichiesta = messaggio.getIdRelatesTo();
            if (riferimentoMessaggioRichiesta == null || riferimentoMessaggioRichiesta.isEmpty()) {
                logger.error("Il messaggio ricevuto è una risposta e deve contenere le informazioni di correlazione al messaggio di richiesta");
                throw new SilvioException("Il messaggio ricevuto è una risposta e deve contenere le informazioni di correlazione al messaggio di richiesta");
            }

            logger.info("Il messaggio ricevuto è una risposta ad un messaggio precedentemente inviato");
            try {                
                Messaggio messaggioRichiesta = daoMessaggio.findByIDMessaggio(riferimentoMessaggioRichiesta, Messaggio.INVIATO);
                if (messaggioRichiesta == null) {
                    logger.warn("Il messaggio ricevuto è una risposta ad un messaggio che non è stato mai inviato IDRELATESTO: " + riferimentoMessaggioRichiesta);
                    throw new SilvioException("Il messaggio ricevuto è una risposta ad un messaggio che non è stato mai inviato IDRELATESTO: " + riferimentoMessaggioRichiesta);
                }
                messaggioRichiesta.setMessaggioRisposta(messaggio);
                daoMessaggio.makePersistent(messaggioRichiesta);
            } catch (DAOException ex) {
                logger.error("Impossibile cercare il messaggio di richiesta a cui è correlato " + ex);
                throw new SilvioException("Impossibile cercare il messaggio di richiesta a cui è correlato " + ex);
            }
        }
    }
}
