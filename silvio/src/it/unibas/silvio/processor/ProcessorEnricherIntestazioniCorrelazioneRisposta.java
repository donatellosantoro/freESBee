package it.unibas.silvio.processor;

import it.unibas.silvio.modello.Messaggio;
import it.unibas.silvio.modello.ParametriMessaggioRicevuto;
import it.unibas.silvio.persistenza.SilvioException;
import it.unibas.silvio.util.CostantiSOAP;
import it.unibas.silvio.util.CostantiSilvio;
import it.unibas.silvio.util.SilvioUtil;
import java.util.List;
import javax.xml.soap.SOAPElement;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProcessorEnricherIntestazioniCorrelazioneRisposta implements Processor {

    private Log logger = LogFactory.getLog(this.getClass());

    public void process(Exchange exchange) throws Exception {
        Messaggio messaggioRichiesta = (Messaggio) exchange.getProperty(CostantiSilvio.MESSAGGIO_RICHIESTA);
        Messaggio messaggioRisposta = (Messaggio) exchange.getProperty(CostantiSilvio.MESSAGGIO_RISPOSTA);
        ParametriMessaggioRicevuto parametri = messaggioRisposta.getParametriMessaggioRicevuto();
        
        logger.info("Parametri" + parametri);
        String tipoCorrelazione = parametri.getTipoCorrelazione();
        if (tipoCorrelazione == null) {
            tipoCorrelazione = CostantiSilvio.CORRELAZIONE_WSA;
        }

        if (tipoCorrelazione.equals(CostantiSilvio.CORRELAZIONE_WSA)) {
            //DOBBIAMO CREARE UN NUOVO ID PER LA RISPOSTA ED AGGIUNGERLO TRA LE INTESTAZIONI
            messaggioRisposta.creaIdentificatore();
            List<SOAPElement> lista = SilvioUtil.getSOAPHeaderList(exchange);
            lista.add(SilvioUtil.aggiungiIntestazioneWSAMessageId(messaggioRisposta.getIdMessaggio()));
            lista.add(SilvioUtil.aggiungiIntestazioneWSATo());
            lista.add(SilvioUtil.aggiungiIntestazioneWSAAction(parametri.getNomeOperazione()));
            //INOLTRE DOBBIAMO AGGIUNGERE L'INTESTAZIONE RELATESTO
            lista.add(SilvioUtil.aggiungiIntestazioneWSARelatesTo(messaggioRichiesta.getIdMessaggio()));
        } else if (tipoCorrelazione.equals(CostantiSilvio.CORRELAZIONE_SPCOOP)) {
            //NON DOBBIAMO GENERARE L'ID PER LA RISPOSTA. LA GENERERA' LA PDD
            //DOBBIAMO AGGIUNGERE L'INTESTAZIONE HTTP DI CORRELAZIONE
            exchange.getIn().getHeaders().put(CostantiSOAP.INTEGRAZIONE_RIFERIMENTO_MESSAGGIO,messaggioRichiesta.getIdMessaggio());
        } else {
            throw new SilvioException("Tipo di correlazione sconosciuto: " + tipoCorrelazione);
        }
    }
}
