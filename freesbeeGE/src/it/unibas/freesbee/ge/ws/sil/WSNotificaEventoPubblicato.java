package it.unibas.freesbee.ge.ws.sil;

import it.unibas.freesbee.ge.persistenza.hibernate.DAOCostanti;
import it.unibas.springfreesbee.ge.ConfigurazioneStatico;
import it.unibas.springfreesbee.ge.ContextStartup;
import org.apache.camel.builder.RouteBuilder;
import org.apache.cxf.jaxws.EndpointImpl;
import javax.xml.ws.Endpoint;

public class WSNotificaEventoPubblicato extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();
        String port = conf.getWebservicesPort();
        String indirizzo = conf.getWebservicesIndirizzo();
        EndpointImpl endpoint = (EndpointImpl) Endpoint.create(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING, new WSNotificaEventoPubblicatoImpl());
        endpoint.publish("http://" + indirizzo + ":" + port + DAOCostanti.URL_WSDL_NOTIFICA_MESSAGGI);
        ContextStartup.getEndpointAvviati().add(endpoint);

    }
}
