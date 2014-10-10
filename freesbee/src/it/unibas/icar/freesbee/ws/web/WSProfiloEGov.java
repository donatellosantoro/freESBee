package it.unibas.icar.freesbee.ws.web;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import it.unibas.icar.freesbee.modello.ConfigurazioneStatico;
import it.unibas.icar.freesbee.persistenza.IDAOProfiloEGov;
import it.unibas.guicefreesbee.ContextStartup;
import javax.xml.ws.Endpoint;
import org.apache.camel.builder.RouteBuilder;
import org.apache.cxf.jaxws.EndpointImpl;

@Singleton
public class WSProfiloEGov extends RouteBuilder {

    @Inject
    private IDAOProfiloEGov daoProfiloEgov;

    @Override
    public void configure() throws Exception {
        ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();
        String port = conf.getWebservicesPort();
        String indirizzo = conf.getWebservicesIndirizzo();
        WSProfiloEGovImpl wsServizio = new WSProfiloEGovImpl(daoProfiloEgov);
        EndpointImpl endpoint = (EndpointImpl) Endpoint.create(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING, wsServizio);
        endpoint.publish("http://" + indirizzo + ":" + port + "/ws/profiloEGov");
        ContextStartup.getEndpointAvviati().add(endpoint);
    }

    public IDAOProfiloEGov getDaoProfiloEgov() {
        return daoProfiloEgov;
    }

    public void setDaoProfiloEgov(IDAOProfiloEGov daoProfiloEgov) {
        this.daoProfiloEgov = daoProfiloEgov;
    }
}
