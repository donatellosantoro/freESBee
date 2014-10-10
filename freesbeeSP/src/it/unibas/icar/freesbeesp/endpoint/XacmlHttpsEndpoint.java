package it.unibas.icar.freesbeesp.endpoint;

import it.unibas.icar.freesbeesp.modello.ConfigurazioneStatico;
import it.unibas.icar.freesbeesp.modello.Istanza;
import it.unibas.icar.freesbeesp.modello.XMLConfigurazione;
import java.util.List;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class XacmlHttpsEndpoint extends RouteBuilder{

    private Log logger = LogFactory.getLog(this.getClass());

    @Override
    public void configure() throws Exception {
        ConfigurazioneStatico configurazioneStatico = ConfigurazioneStatico.getInstance();
        String indirizzo = "jetty:" + configurazioneStatico.getIndirizzoErogatore();
        XMLConfigurazione configurazioneXml = XMLConfigurazione.getInstance();
        String indirizzoRegistroServizi = configurazioneXml.getIndirizzoRegistroServizi();
        List<Istanza> listaIstanze = configurazioneXml.getListaIstanze();

        for (Istanza istanza : listaIstanze) {
            String url = indirizzo + istanza.getUriAscolto();
            if (logger.isInfoEnabled()) logger.info("Avvio l'endpoint erogatore all'indirizzo " + url);
            this.from(url)
                .tryBlock()
                    .process(new XACMLPDPProcessor(istanza.getAccordoServizio(),indirizzoRegistroServizi))
                    .to(istanza.getRisorsa())
                .handle(Exception.class)
                    .process(new ProcessorEccezione())
                .end();
        }
    }
}
