package it.unibas.icar.freesbee.ws.web;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import it.unibas.icar.freesbee.modello.ConfigurazioneStatico;
import it.unibas.icar.freesbee.persistenza.IDAOAccordoServizio;
import it.unibas.icar.freesbee.persistenza.IDAOPortaApplicativa;
import it.unibas.icar.freesbee.persistenza.IDAOPortaDelegata;
import it.unibas.icar.freesbee.persistenza.IDAOServizio;
import it.unibas.icar.freesbee.persistenza.IDAOSoggetto;
import it.unibas.guicefreesbee.ContextStartup;
import javax.xml.ws.Endpoint;
import org.apache.camel.builder.RouteBuilder;
import org.apache.cxf.jaxws.EndpointImpl;

@Singleton
public class WSServizio extends RouteBuilder {

    @Inject
    private IDAOServizio daoServizio;
    @Inject
    private IDAOPortaApplicativa daoPortaApplicativa;
    @Inject
    private IDAOPortaDelegata daoPortaDelegata;
    @Inject
    private IDAOAccordoServizio daoAccordoServizio;
    @Inject
    private IDAOSoggetto daoSoggetto;

    @Override
    public void configure() throws Exception {
        ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();
        String port = conf.getWebservicesPort();
        String indirizzo = conf.getWebservicesIndirizzo();
        WSServizioImpl wsServizio = new WSServizioImpl(daoServizio, daoPortaApplicativa, daoPortaDelegata, daoAccordoServizio, daoSoggetto);
        EndpointImpl endpoint = (EndpointImpl) Endpoint.create(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING, wsServizio);
        endpoint.publish("http://" + indirizzo + ":" + port + "/ws/servizio");
        ContextStartup.getEndpointAvviati().add(endpoint);
    }

    public IDAOAccordoServizio getDaoAccordoServizio() {
        return daoAccordoServizio;
    }

    public void setDaoAccordoServizio(IDAOAccordoServizio daoAccordoServizio) {
        this.daoAccordoServizio = daoAccordoServizio;
    }

    public IDAOSoggetto getDaoSoggetto() {
        return daoSoggetto;
    }

    public void setDaoSoggetto(IDAOSoggetto daoSoggetto) {
        this.daoSoggetto = daoSoggetto;
    }

    public IDAOPortaApplicativa getDaoPortaApplicativa() {
        return daoPortaApplicativa;
    }

    public void setDaoPortaApplicativa(IDAOPortaApplicativa daoPortaApplicativa) {
        this.daoPortaApplicativa = daoPortaApplicativa;
    }

    public IDAOPortaDelegata getDaoPortaDelegata() {
        return daoPortaDelegata;
    }

    public void setDaoPortaDelegata(IDAOPortaDelegata daoPortaDelegata) {
        this.daoPortaDelegata = daoPortaDelegata;
    }

    public IDAOServizio getDaoServizio() {
        return daoServizio;
    }

    public void setDaoServizio(IDAOServizio daoServizio) {
        this.daoServizio = daoServizio;
    }
}
