package it.unibas.icar.freesbee.portaapplicativa;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import it.unibas.icar.freesbee.processors.ProcessorControllaEccezioniEGov;
import it.unibas.icar.freesbee.processors.ProcessorIdentificazioneErogatore;
import it.unibas.icar.freesbee.processors.ProcessorLogFactory;
import it.unibas.icar.freesbee.processors.ProcessorSbloccaPollingConsumerPortaApplicativa;
import it.unibas.icar.freesbee.utilita.FreesbeeCamel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Singleton
public class EnricherIdentificatoreErogatore extends RouteBuilder {

    private static Log logger = LogFactory.getLog(EnricherIdentificatoreErogatore.class);
    @Inject
    private ProcessorIdentificazioneErogatore processorIdentificazioneErogatore;
    @Inject
    private ProcessorControllaEccezioniEGov processorControllaEccezioniEGov;
    @Inject
    private ProcessorSbloccaPollingConsumerPortaApplicativa processorSbloccaPollingConsumerPortaApplicativa;

    public void configure() throws Exception {
        getProcessorSbloccaPollingConsumerPortaApplicativa().setEccezione(true);
        this.from(FreesbeeCamel.SEDA_ENRICHER_IDENTIFICATORE_EROGATORE)
//	    	.threads(ConfigurazioneStatico.getInstance().getCamelThreadPool(),
//	  		         ConfigurazioneStatico.getInstance().getCamelThreadPoolMax())
            //.process(new ProcessorLog(this.getClass()))
            .process(ProcessorLogFactory.getInstance().getProcessorLog(this.getClass()))
            .doTry()
                .process(processorIdentificazioneErogatore)
                .process(processorControllaEccezioniEGov) //Solo qui possiamo dire di aver verificato tutte le possibili eccezioni e inviarle
                .to(FreesbeeCamel.SEDA_HTTP_CONSEGNA_BUSTE_SOAP)
            .doCatch(Exception.class)
                .process(getProcessorSbloccaPollingConsumerPortaApplicativa())
            .end();
        
    }

    public ProcessorIdentificazioneErogatore getProcessorIdentificazioneErogatore() {
        return processorIdentificazioneErogatore;
    }

    public void setProcessorIdentificazioneErogatore(ProcessorIdentificazioneErogatore processorIdentificazioneErogatore) {
        this.processorIdentificazioneErogatore = processorIdentificazioneErogatore;
    }
    
    public ProcessorControllaEccezioniEGov getProcessControllaEccezioniEGov() {
        return processorControllaEccezioniEGov;
    }

    public void setProcessControllaEccezioniEGov(ProcessorControllaEccezioniEGov processControllaEccezioniEGov) {
        this.processorControllaEccezioniEGov = processControllaEccezioniEGov;
    }

    public ProcessorSbloccaPollingConsumerPortaApplicativa getProcessorSbloccaPollingConsumerPortaApplicativa() {
        return processorSbloccaPollingConsumerPortaApplicativa;
    }

    public void setProcessorSbloccaPollingConsumerPortaApplicativa(ProcessorSbloccaPollingConsumerPortaApplicativa processorSbloccaPollingConsumerPortaApplicativa) {
        this.processorSbloccaPollingConsumerPortaApplicativa = processorSbloccaPollingConsumerPortaApplicativa;
    }
}
