package it.unibas.icar.freesbee.ws.web;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import it.unibas.icar.freesbee.modello.ConfigurazioneStatico;
import it.unibas.icar.freesbee.persistenza.IDAOConfigurazione;
import it.unibas.icar.freesbee.persistenza.IDAOSoggetto;
import it.unibas.guicefreesbee.ContextStartup;
import javax.xml.ws.Endpoint;
import org.apache.camel.builder.RouteBuilder;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.apache.cxf.jaxws.EndpointImpl;
import org.slf4j.LoggerFactory;

@Singleton
public class WSConfigurazione extends RouteBuilder {

//    private static Log logger = LogFactory.getLog(WSConfigurazione.class);
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(WSConfigurazione.class.getName());
    @Inject
    private IDAOConfigurazione daoConfigurazione;
    @Inject
    private IDAOSoggetto daoSoggetto;

    @Override
    public void configure() throws Exception {
        ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();
        String port = conf.getWebservicesPort();
        String indirizzo = conf.getWebservicesIndirizzo();
        WSConfigurazioneImpl wsConfigurazione = new WSConfigurazioneImpl(daoConfigurazione,daoSoggetto);
        EndpointImpl endpoint = (EndpointImpl) Endpoint.create(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING, wsConfigurazione);
        endpoint.publish("http://" + indirizzo + ":" + port + "/ws/configurazione");
        ContextStartup.getEndpointAvviati().add(endpoint);
    }

    public IDAOConfigurazione getDaoConfigurazione() {
        return daoConfigurazione;
    }

    public void setDaoConfigurazione(IDAOConfigurazione daoConfigurazione) {
        this.daoConfigurazione = daoConfigurazione;
    }

    public IDAOSoggetto getDaoSoggetto() {
        return daoSoggetto;
    }

    public void setDaoSoggetto(IDAOSoggetto daoSoggetto) {
        this.daoSoggetto = daoSoggetto;
    }
}
