package it.unibas.icar.freesbee.modulocontrollo.sbustamento;

import com.google.inject.Singleton;
import it.unibas.guicefreesbee.ContextStartup;
import it.unibas.icar.freesbee.modello.ConfigurazioneStatico;
import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.processors.ProcessorLogFactory;
import it.unibas.icar.freesbee.utilita.CostantiBusta;
import it.unibas.icar.freesbee.utilita.CostantiSOAP;
import it.unibas.icar.freesbee.utilita.FreesbeeCamel;
import java.util.Map;
import javax.xml.namespace.QName;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.DocumentFragment;

@Singleton
public class EnvelopeWrapperTest extends RouteBuilder {

    private static Log logger = LogFactory.getLog(EnvelopeWrapperTest.class);

    public void configure() throws Exception {
        this.from(FreesbeeCamel.SEDA_ENVELOPE_WRAPPER_TEST)
//	    	.threads(ConfigurazioneStatico.getInstance().getCamelThreadPool(),
//	  		         ConfigurazioneStatico.getInstance().getCamelThreadPoolMax())
                //.process(new ProcessorLog(this.getClass()))
                .process(ProcessorLogFactory.getInstance().getProcessorLog(this.getClass()))
                .process(new ProcessorTest())
             .to(FreesbeeCamel.SEDA_DETOUR_COLLABORAZIONE_SBUSTAMENTO);
    }

    private class ProcessorTest implements Processor {

        @SuppressWarnings("unchecked")
        public void process(Exchange exchange) throws Exception {
            //ContextStartup.aggiungiThread(this.getClass().getName());
            try {
                Messaggio messaggio = (Messaggio)exchange.getProperty(CostantiBusta.MESSAGGIO);
                
                Map<QName, DocumentFragment> mappaHeaders = (Map<QName, DocumentFragment>) exchange.getProperty(CostantiSOAP.SOAP_HEADERS);

                String nomeServizio = messaggio.getServizio();
                String sbusta = (String) exchange.getIn().getHeader(CostantiBusta.SBUSTA_RICHIESTA_RISPOSTA);
                if (logger.isDebugEnabled()) logger.debug("Nome servizio " + nomeServizio);
                if (logger.isDebugEnabled()) logger.debug("Sbusta " + sbusta);
                if (nomeServizio.startsWith(CostantiBusta.SERVIZIO_TEST) && sbusta.equalsIgnoreCase(CostantiBusta.VALORE_SBUSTA_RICHIESTA)) {
                    if (logger.isInfoEnabled()) logger.info("Ricevuta busta di test.");
                    DocumentFragment df = mappaHeaders.get(new QName(CostantiSOAP.NAMESPACE_EGOV_TEST, "Header_EGov_Test_Richiesta"));
                    if (df != null) {
                        if (logger.isInfoEnabled()) logger.info("Ho trovato le intestazioni di test della richiesta.");
                        exchange.setProperty("IntestazioniTestRichiesta", df);
                        mappaHeaders.remove(new QName(CostantiSOAP.NAMESPACE_EGOV_TEST, "Header_EGov_Test_Richiesta"));
                    }
                }
            } catch (Exception e) {
                logger.error("Errore mentre si elaboravano le intestazioni di test.");
                if (logger.isDebugEnabled()) e.printStackTrace();
            }
        }
    }
}
