package it.unibas.icar.freesbee.modulocontrollo.sbustamento;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.processors.ProcessorErroreSbustamento;
import it.unibas.icar.freesbee.processors.ProcessorLogFactory;
import it.unibas.icar.freesbee.utilita.CostantiBusta;
import it.unibas.icar.freesbee.utilita.FreesbeeCamel;
import it.unibas.icar.freesbee.utilita.FreesbeeUtil;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Singleton
public class WireTapCollaborazioneSbustamento extends RouteBuilder {

    private static Log logger = LogFactory.getLog(WireTapCollaborazioneSbustamento.class);
    @Inject
    private ProcessorErroreSbustamento processorErroreSbustamento;

    public void configure() throws Exception {
        this.from(FreesbeeCamel.SEDA_WIRETAP_COLLABORAZIONE_SBUSTAMENTO)
//	    	.threads(ConfigurazioneStatico.getInstance().getCamelThreadPool(),
//	  		         ConfigurazioneStatico.getInstance().getCamelThreadPoolMax())
                //.process(new ProcessorLog(this.getClass()))
            .process(ProcessorLogFactory.getInstance().getProcessorLog(this.getClass()))
            .doTry()
                .process(new ProcessorCorrelazioneMessaggi())
                .to(FreesbeeCamel.SEDA_WIRETAP_TRACCIAMENTO_SBUSTAMENTO)
            .doCatch(Exception.class)
                .process(processorErroreSbustamento);
    }

    private class ProcessorCorrelazioneMessaggi implements Processor {

        @SuppressWarnings("unchecked")
        public void process(Exchange exchange) throws Exception {
            //ContextStartup.aggiungiThread(this.getClass().getName());
            Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiBusta.MESSAGGIO);

            if (messaggio.isNica()) {
                //SONO UN NICA. NON DEVO PREOCCUPARMI DELLA CORRELAZIONE DEI MESSAGGI
                return;
            }
            cercaIntestazioniCorrelazione(exchange, messaggio);
        }

        private void cercaIntestazioniCorrelazione(Exchange exchange, Messaggio messaggio) {
            String idSil = FreesbeeUtil.cercaIntestazioniIDWsa(exchange);
            if (idSil == null) {
                idSil = FreesbeeUtil.cercaIntestazioniIDIntegrationManager(exchange);
            }
            if (idSil == null) {
                idSil = FreesbeeUtil.cercaIntestazioniIDHttpHeader(exchange.getIn());
            }
            if(idSil == null){
                idSil = messaggio.getIdMessaggio();
            }
            messaggio.setIdSil(idSil);

            String relatesSil = FreesbeeUtil.cercaIntestazioniIDRelatesToWsa(exchange);
            if (relatesSil == null) {
                relatesSil = FreesbeeUtil.cercaIntestazioniIDRelatesToIntegrationManager(exchange);
            }
            if (relatesSil == null) {
                relatesSil = FreesbeeUtil.cercaIntestazioniIDRelatesToHttpHeader(exchange.getIn());
            }
            if (relatesSil != null) {
                messaggio.setSilRelatesTo(relatesSil);
            }
        }
    }

    public ProcessorErroreSbustamento getProcessorErroreSbustamento() {
        return processorErroreSbustamento;
    }

    public void setProcessorErroreSbustamento(ProcessorErroreSbustamento processorErroreSbustamento) {
        this.processorErroreSbustamento = processorErroreSbustamento;
    }
}
