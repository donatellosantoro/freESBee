package it.unibas.silvio.processor;

import it.unibas.silvio.modello.Messaggio;
import it.unibas.silvio.modello.MessaggioSbloccoManuale;
import it.unibas.silvio.modello.ParametriMessaggioRicevuto;
import it.unibas.silvio.persistenza.DAOException;
import it.unibas.silvio.persistenza.IDAOMessaggioSbloccoManuale;
import it.unibas.silvio.persistenza.SilvioException;
import it.unibas.silvio.util.CostantiSilvio;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProcessorEventDrivenConsumerSbloccoRisposta implements Processor{
    
    private Log logger = LogFactory.getLog(this.getClass());
    private IDAOMessaggioSbloccoManuale daoMessaggioSblocco;

    public ProcessorEventDrivenConsumerSbloccoRisposta(IDAOMessaggioSbloccoManuale daoMessaggioSblocco) {
        this.daoMessaggioSblocco = daoMessaggioSblocco;
    }
        
    public void process(Exchange exchange) throws Exception {
        Messaggio messaggioRichiesta = (Messaggio) exchange.getProperty(CostantiSilvio.MESSAGGIO_RICHIESTA);
        Messaggio messaggioRisposta = (Messaggio) exchange.getProperty(CostantiSilvio.MESSAGGIO_RISPOSTA);
        ParametriMessaggioRicevuto parametri = messaggioRisposta.getParametriMessaggioRicevuto();        
        
        MessaggioSbloccoManuale messaggioSblocco = new MessaggioSbloccoManuale();
        messaggioSblocco.setMessaggioRichiesta(messaggioRichiesta);
        messaggioSblocco.setIndirizzoRisposta(parametri.getIndirizzoRisposta());
        messaggioSblocco.setTest(parametri.isTest());
        messaggioSblocco.setIdMessaggio(messaggioRisposta.getIdMessaggio());
        messaggioSblocco.setIdMessaggioRelatedTo(messaggioRisposta.getIdRelatesTo());
        messaggioSblocco.setIstanzaPortType(messaggioRisposta.getIstanzaPortType());
        messaggioSblocco.setMessaggio(messaggioRisposta.getMessaggio());
        messaggioSblocco.setIndirizzo(messaggioRisposta.getIndirizzo());
        messaggioSblocco.setTipoCorrelazione(parametri.getTipoCorrelazione());
        messaggioSblocco.setIstanzaOperation(messaggioRisposta.getIstanzaOperation());
        
        try {            
            daoMessaggioSblocco.makePersistent(messaggioSblocco);
            logger.info("Salvato il messaggio per lo sblocco");
            logger.info("Mi metto in attesa dello sblocco");
        } catch (DAOException ex) {
            logger.error("Impossibile salvare il messaggio relativo allo sblocco automatico " + ex);
            throw new SilvioException("Impossibile salvare il messaggio relativo allo sblocco automatico " + ex);
        }
        
    }

}
