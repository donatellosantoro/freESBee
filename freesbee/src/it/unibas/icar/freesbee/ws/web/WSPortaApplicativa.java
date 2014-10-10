package it.unibas.icar.freesbee.ws.web;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import it.unibas.icar.freesbee.modello.ConfigurazioneStatico;
import it.unibas.icar.freesbee.persistenza.IDAOAzione;
import it.unibas.icar.freesbee.persistenza.IDAOPortaApplicativa;
import it.unibas.icar.freesbee.persistenza.IDAOServizio;
import it.unibas.icar.freesbee.persistenza.IDAOServizioApplicativo;
import it.unibas.guicefreesbee.ContextStartup;
import javax.xml.ws.Endpoint;
import org.apache.camel.builder.RouteBuilder;
import org.apache.cxf.jaxws.EndpointImpl;

@Singleton
public class WSPortaApplicativa extends RouteBuilder {

    @Inject
    private IDAOPortaApplicativa daoPortaApplicativa;
    @Inject
    private IDAOAzione daoAzione;
    @Inject
    private IDAOServizio daoServizio;
    @Inject
    private IDAOServizioApplicativo daoServizioApplicativo;

    @Override
    public void configure() throws Exception {
        ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();
        String port = conf.getWebservicesPort();
        String indirizzo = conf.getWebservicesIndirizzo();
        WSPortaApplicativaImpl wsPA = new WSPortaApplicativaImpl(daoPortaApplicativa, daoAzione, daoServizio, daoServizioApplicativo);
        EndpointImpl endpoint = (EndpointImpl) Endpoint.create(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING, wsPA);
        endpoint.publish("http://" + indirizzo + ":" + port + "/ws/portaApplicativa");
        ContextStartup.getEndpointAvviati().add(endpoint);
    }

    public IDAOPortaApplicativa getDaoPortaApplicativa() {
        return daoPortaApplicativa;
    }

    public void setDaoPortaApplicativa(IDAOPortaApplicativa daoPortaApplicativa) {
        this.daoPortaApplicativa = daoPortaApplicativa;
    }

    public IDAOAzione getDaoAzione() {
        return daoAzione;
    }

    public void setDaoAzione(IDAOAzione daoAzione) {
        this.daoAzione = daoAzione;
    }

    public IDAOServizio getDaoServizio() {
        return daoServizio;
    }

    public void setDaoServizio(IDAOServizio daoServizio) {
        this.daoServizio = daoServizio;
    }

    public IDAOServizioApplicativo getDaoServizioApplicativo() {
        return daoServizioApplicativo;
    }

    public void setDaoServizioApplicativo(IDAOServizioApplicativo daoServizioApplicativo) {
        this.daoServizioApplicativo = daoServizioApplicativo;
    }
}
