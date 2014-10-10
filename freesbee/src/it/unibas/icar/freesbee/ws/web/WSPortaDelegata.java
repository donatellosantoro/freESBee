package it.unibas.icar.freesbee.ws.web;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import it.unibas.icar.freesbee.modello.ConfigurazioneStatico;
import it.unibas.icar.freesbee.persistenza.IDAOAzione;
import it.unibas.icar.freesbee.persistenza.IDAOConfigurazione;
import it.unibas.icar.freesbee.persistenza.IDAOPortaDelegata;
import it.unibas.icar.freesbee.persistenza.IDAOServizio;
import it.unibas.icar.freesbee.persistenza.IDAOSoggetto;
import it.unibas.guicefreesbee.ContextStartup;
import it.unibas.icar.freesbee.persistenza.DBManager;
import javax.xml.ws.Endpoint;
import org.apache.camel.builder.RouteBuilder;
import org.apache.cxf.jaxws.EndpointImpl;

@Singleton
public class WSPortaDelegata extends RouteBuilder {

    @Inject
    private IDAOPortaDelegata daoPortaDelegata;
    @Inject
    private DBManager dbManager;
    @Inject
    private IDAOServizio daoServizio;
    @Inject
    private IDAOSoggetto daoSoggetto;
    @Inject
    private IDAOAzione daoAzione;

    @Override
    public void configure() throws Exception {
        ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();
        String port = conf.getWebservicesPort();
        String indirizzo = conf.getWebservicesIndirizzo();
        WSPortaDelegataImpl wsPD = new WSPortaDelegataImpl(getContext(), daoPortaDelegata, daoServizio, daoSoggetto, daoAzione, dbManager);
        EndpointImpl endpoint = (EndpointImpl) Endpoint.create(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING, wsPD);
        endpoint.publish("http://" + indirizzo + ":" + port + "/ws/portaDelegata");
        ContextStartup.getEndpointAvviati().add(endpoint);
        wsPD.avviaPorte();
    }

    public IDAOAzione getDaoAzione() {
        return daoAzione;
    }

    public void setDaoAzione(IDAOAzione daoAzione) {
        this.daoAzione = daoAzione;
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

    public IDAOSoggetto getDaoSoggetto() {
        return daoSoggetto;
    }

    public void setDaoSoggetto(IDAOSoggetto daoSoggetto) {
        this.daoSoggetto = daoSoggetto;
    }

    public DBManager getDbManager() {
        return dbManager;
    }

    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }
}
