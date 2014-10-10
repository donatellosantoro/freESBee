package it.unibas.icar.freesbee.ws.verificainstallazione;

import com.google.inject.Singleton;
import it.unibas.icar.freesbee.modello.ConfigurazioneStatico;
import it.unibas.guicefreesbee.ContextStartup;
import it.unibas.icar.freesbee.ws.tracciamento.WSMessaggioImpl;
import org.apache.camel.builder.RouteBuilder;
import org.apache.cxf.jaxws.EndpointImpl;
import javax.xml.ws.Endpoint;

@Singleton
public class WSVerificaInstallazione extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();
        String port = conf.getWebservicesPort();
        String indirizzo = conf.getWebservicesIndirizzo();
//        EndpointImpl endpoint = (EndpointImpl) Endpoint.create(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING, new WSVerificaInstallazioneImpl());
        EndpointImpl endpoint = (EndpointImpl) Endpoint.create(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_MTOM_BINDING, new WSVerificaInstallazioneImpl());
        endpoint.getProperties().put("mtom-enabled", "true");

        endpoint.publish("http://" + indirizzo + ":" + port + "/ws/verificaInstallazione");

        ContextStartup.getEndpointAvviati().add(endpoint);

    }
}
