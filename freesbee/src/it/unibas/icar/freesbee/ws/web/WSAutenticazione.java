package it.unibas.icar.freesbee.ws.web;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import it.unibas.icar.freesbee.modello.ConfigurazioneStatico;
import it.unibas.icar.freesbee.persistenza.IDAOUtente;
import it.unibas.guicefreesbee.ContextStartup;
import javax.xml.ws.Endpoint;
import org.apache.camel.builder.RouteBuilder;
import org.apache.cxf.jaxws.EndpointImpl;

@Singleton
public class WSAutenticazione extends RouteBuilder {

    @Inject
    private IDAOUtente daoUtente;
    
    @Override
    public void configure() throws Exception {
        ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();
        String port = conf.getWebservicesPort();
        String indirizzo = conf.getWebservicesIndirizzo();
        WSAutenticazioneImpl wsAutenticazione = new WSAutenticazioneImpl(daoUtente);
        EndpointImpl endpoint = (EndpointImpl) Endpoint.create(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING, wsAutenticazione);
        endpoint.publish("http://" + indirizzo + ":" + port + "/ws/autenticazione");
        ContextStartup.getEndpointAvviati().add(endpoint);
    }

    public IDAOUtente getDaoUtente() {
        return daoUtente;
    }

    public void setDaoUtente(IDAOUtente daoUtente) {
        this.daoUtente = daoUtente;
    }
}
