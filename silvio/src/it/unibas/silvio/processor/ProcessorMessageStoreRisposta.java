package it.unibas.silvio.processor;

import it.unibas.silvio.modello.Messaggio;
import it.unibas.silvio.persistenza.DAOException;
import it.unibas.silvio.persistenza.IDAOMessaggio;
import it.unibas.silvio.persistenza.SilvioException;
import it.unibas.silvio.util.CostantiSilvio;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProcessorMessageStoreRisposta implements Processor {

    private Log logger = LogFactory.getLog(this.getClass());
    private IDAOMessaggio daoMessaggio;

    public ProcessorMessageStoreRisposta(IDAOMessaggio daoMessaggio) {
        this.daoMessaggio = daoMessaggio;
    }

    public void process(Exchange exchange) throws Exception {
        Messaggio messaggioRichiesta = (Messaggio) exchange.getProperty(CostantiSilvio.MESSAGGIO_RICHIESTA);
        Messaggio messaggioRisposta = (Messaggio) exchange.getProperty(CostantiSilvio.MESSAGGIO_RISPOSTA);
        try {
            messaggioRichiesta.setMessaggioRisposta(messaggioRisposta);
            daoMessaggio.makePersistent(messaggioRichiesta);
            daoMessaggio.makePersistent(messaggioRisposta);
        } catch (DAOException ex) {
            logger.error("Impossibile salvare il messaggio inviato " + ex);
            throw new SilvioException("Impossibile salvare il messaggio inviato " + ex);
        }
    }
}
