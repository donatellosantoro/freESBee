package it.unibas.icar.freesbee.ws.web;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import it.unibas.icar.freesbee.modello.ConfigurazioneStatico;
import it.unibas.icar.freesbee.persistenza.IDAOPortaApplicativa;
import it.unibas.icar.freesbee.persistenza.IDAOServizioApplicativo;
import it.unibas.guicefreesbee.ContextStartup;
import javax.xml.ws.Endpoint;
import org.apache.camel.builder.RouteBuilder;
import org.apache.cxf.jaxws.EndpointImpl;

@Singleton
public class WSServizioApplicativo extends RouteBuilder {
    
    @Inject
    private IDAOServizioApplicativo daoServizioApplicativo;
    @Inject
    private IDAOPortaApplicativa daoPortaApplicativa;

    @Override
    public void configure() throws Exception {
        ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();
        String port = conf.getWebservicesPort();
        String indirizzo = conf.getWebservicesIndirizzo();
        WSServizioApplicativoImpl wsServizio = new WSServizioApplicativoImpl(getContext(),daoServizioApplicativo,daoPortaApplicativa);
        EndpointImpl endpoint = (EndpointImpl) Endpoint.create(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING, wsServizio);
        endpoint.publish("http://" + indirizzo + ":" + port + "/ws/applicativo");
        ContextStartup.getEndpointAvviati().add(endpoint);
}

    public IDAOServizioApplicativo getDaoServizioApplicativo() {
        return daoServizioApplicativo;
    }

    public void setDaoServizioApplicativo(IDAOServizioApplicativo daoServizioApplicativo) {
        this.daoServizioApplicativo = daoServizioApplicativo;
    }

    public IDAOPortaApplicativa getDaoPortaApplicativa() {
        return daoPortaApplicativa;
    }

    public void setDaoPortaApplicativa(IDAOPortaApplicativa daoPortaApplicativa) {
        this.daoPortaApplicativa = daoPortaApplicativa;
    }
}
