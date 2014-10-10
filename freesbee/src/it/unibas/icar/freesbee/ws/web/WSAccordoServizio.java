package it.unibas.icar.freesbee.ws.web;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import it.unibas.icar.freesbee.modello.ConfigurazioneStatico;
import it.unibas.icar.freesbee.persistenza.IDAOAccordoServizio;
import it.unibas.icar.freesbee.persistenza.IDAOProfiloEGov;
import it.unibas.guicefreesbee.ContextStartup;
import javax.xml.ws.Endpoint;
import org.apache.camel.builder.RouteBuilder;
import org.apache.cxf.jaxws.EndpointImpl;

@Singleton
public class WSAccordoServizio extends RouteBuilder {

    @Inject
    private IDAOAccordoServizio daoAccordoServizio;
    @Inject
    private IDAOProfiloEGov daoProfiloEGov;

    @Override
    public void configure() throws Exception {
        ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();
        String port = conf.getWebservicesPort();
        String indirizzo = conf.getWebservicesIndirizzo();
        WSAccordoServizioImpl wsPD = new WSAccordoServizioImpl(daoAccordoServizio, daoProfiloEGov);
        EndpointImpl endpoint = (EndpointImpl) Endpoint.create(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING, wsPD);
        endpoint.publish("http://" + indirizzo + ":" + port + "/ws/accordoServizio");
        ContextStartup.getEndpointAvviati().add(endpoint);
    }

    public IDAOProfiloEGov getDaoProfiloEGov() {
        return daoProfiloEGov;
    }

    public void setDaoProfiloEGov(IDAOProfiloEGov daoProfiloEGov) {
        this.daoProfiloEGov = daoProfiloEGov;
    }

    public IDAOAccordoServizio getDaoAccordoServizio() {
        return daoAccordoServizio;
    }

    public void setDaoAccordoServizio(IDAOAccordoServizio daoAccordoServizio) {
        this.daoAccordoServizio = daoAccordoServizio;
    }
}
