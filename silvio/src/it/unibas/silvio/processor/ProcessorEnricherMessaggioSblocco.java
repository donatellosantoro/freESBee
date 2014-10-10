package it.unibas.silvio.processor;

import it.unibas.silvio.modello.Messaggio;
import it.unibas.silvio.modello.MessaggioSbloccoManuale;
import it.unibas.silvio.modello.ParametriMessaggioRicevuto;
import it.unibas.silvio.persistenza.DAOException;
import it.unibas.silvio.persistenza.IDAOMessaggioSbloccoManuale;
import it.unibas.silvio.persistenza.SilvioException;
import it.unibas.silvio.util.CostantiSilvio;
import java.util.Date;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProcessorEnricherMessaggioSblocco implements Processor{
    
    private Log logger = LogFactory.getLog(this.getClass());
    private IDAOMessaggioSbloccoManuale daoMessaggioSblocco;

    public ProcessorEnricherMessaggioSblocco(IDAOMessaggioSbloccoManuale daoMessaggioSblocco) {
        this.daoMessaggioSblocco = daoMessaggioSblocco;
    }
        
    public void process(Exchange exchange) throws Exception {
        long id = (Long)exchange.getProperty(CostantiSilvio.ID_MESSAGGIO_SBLOCCO);
        try {            
            MessaggioSbloccoManuale messaggioSblocco = daoMessaggioSblocco.findById(id);
            
            Messaggio messaggioRichiesta = messaggioSblocco.getMessaggioRichiesta();
            
            ParametriMessaggioRicevuto parametri = new ParametriMessaggioRicevuto();
            
            Messaggio messaggioRisposta = new Messaggio();
            messaggioRisposta.setIdMessaggio(messaggioSblocco.getIdMessaggio());
            messaggioRisposta.setIdRelatesTo(messaggioSblocco.getIdMessaggioRelatedTo());
            messaggioRisposta.setIndirizzo(messaggioSblocco.getIndirizzo());
            messaggioRisposta.setIstanzaPortType(messaggioSblocco.getIstanzaPortType());
            messaggioRisposta.setData(new Date());
            messaggioRisposta.setIstanzaOperation(messaggioSblocco.getIstanzaOperation());
            messaggioRisposta.setMessaggio(messaggioSblocco.getMessaggio());
            parametri.setTest(messaggioSblocco.isTest());
            parametri.setIndirizzoRisposta(messaggioSblocco.getIndirizzoRisposta());
            parametri.setTipoCorrelazione(messaggioSblocco.getTipoCorrelazione());
            messaggioRisposta.setParametriMessaggioRicevuto(parametri);
            messaggioRichiesta.setParametriMessaggioRicevuto(parametri);
            
            exchange.setProperty(CostantiSilvio.MESSAGGIO_RISPOSTA, messaggioRisposta);
            exchange.setProperty(CostantiSilvio.MESSAGGIO_RICHIESTA, messaggioRichiesta);
            
            daoMessaggioSblocco.makeTransient(messaggioSblocco);
        } catch (DAOException ex) {
            logger.error("Impossibile caricare il messaggio per lo sblocco " + ex);
            throw new SilvioException("Impossibile caricare il messaggio per lo sblocco " + ex);
        }
    }

}
