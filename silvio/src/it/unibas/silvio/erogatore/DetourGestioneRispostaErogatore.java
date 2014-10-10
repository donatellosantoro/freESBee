package it.unibas.silvio.erogatore;

import it.unibas.silvio.processor.ProcessorLog;
import it.unibas.silvio.util.CostantiCamel;
import it.unibas.silvio.modello.*;
import it.unibas.silvio.util.CostantiSilvio;
import it.unibas.silvio.util.SilvioUtil;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DetourGestioneRispostaErogatore extends RouteBuilder{
    
    private Log logger = LogFactory.getLog(this.getClass());
    public static final String TIPO_RISPOSTA = "TIPO_RISPOSTA";
    public static final String RISPOSTA_ACK = "RISPOSTA_ACK";
    public static final String CREA_RISPOSTA = "CREA_RISPOSTA";

    @Override
    public void configure() throws Exception {
        this.from(CostantiCamel.DETOUR_GESTIONE_RISPOSTA_EROGATORE)     
            .thread(ConfigurazioneStatico.getInstance().getThreadPool())
            .process(new ProcessorLog(this.getClass()))
            .process(new ProcessorDetourRisposta())            
            .choice().when(header(RISPOSTA_ACK).isEqualTo(true))
                .to(CostantiCamel.RISPOSTA_ACK_EROGATORE)
            .end()            
            .choice().when(header(CREA_RISPOSTA).isEqualTo(true))    
                .process(new ProcessorCreaMessaggio())
                .to(CostantiCamel.ENRICHER_DATI_PARZIALI_COMPLETI_EROGATORE)
            .end();
    }
    
    private class ProcessorCreaMessaggio implements Processor{

        public void process(Exchange exchange) throws Exception {
            Messaggio messaggioRichiesta = (Messaggio) exchange.getProperty(CostantiSilvio.MESSAGGIO_RICHIESTA);
            Messaggio messaggioRisposta = new Messaggio();
            messaggioRisposta.setTipo(Messaggio.INVIATO);
            messaggioRisposta.setIstanzaOperation(messaggioRichiesta.getIstanzaOperation());
            messaggioRisposta.setIstanzaPortType(messaggioRichiesta.getIstanzaPortType());
            messaggioRisposta.setIdRelatesTo(messaggioRichiesta.getIdMessaggio());
            messaggioRisposta.setChannelErogatore(messaggioRichiesta.getChannelErogatore());            
            messaggioRisposta.setIndirizzo(messaggioRichiesta.getIndirizzo());
            messaggioRisposta.setParametriMessaggioRicevuto(messaggioRichiesta.getParametriMessaggioRicevuto());
            exchange.setProperty(CostantiSilvio.MESSAGGIO_RISPOSTA, messaggioRisposta);
            SilvioUtil.getSOAPHeaderList(exchange).clear();
        }
        
    }
    
    private class ProcessorDetourRisposta implements Processor{

        public void process(Exchange exchange) throws Exception {            
            Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiSilvio.MESSAGGIO_RICHIESTA);
            IstanzaOperation istanzaOperation = messaggio.getIstanzaOperation();
            IstanzaPortType istanzaPortType = istanzaOperation.getIstanzaPortType();
            if(istanzaOperation.isOneWay()){
                exchange.getIn().setHeader(RISPOSTA_ACK, true);
                logger.info("L'operation è one way. Ho salvato la richiesta e quindi posso sbloccare il fuitore");     
                return;
            }
            if(istanzaPortType.isErogazioneRisposta()){
                exchange.getIn().setHeader(RISPOSTA_ACK, true);
                logger.info("Ho ricevuto una risposta asincrona, l'ho salvate e quindi posso sbloccare l'erogatore");  
                return;
            }
            if(istanzaOperation.isAsincrono()){
                logger.info("L'operation è ascinrona. Ho salvato la richiesta e quindi posso sbloccare il fuitore. Inoltre devo creare la risposta.");                
                exchange.getIn().setHeader(RISPOSTA_ACK, true);
            }
            exchange.getIn().setHeader(CREA_RISPOSTA, true);
        }
        
    }
        
}
