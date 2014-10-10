package it.unibas.silvio.ws.istanza;

import it.unibas.silvio.modello.ConfigurazioneStatico;
import it.unibas.silvio.persistenza.*;
import it.unibas.silvio.spring.ContextStartup;
import javax.xml.ws.Endpoint;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.jaxws.EndpointImpl;

public class WSIstanza extends RouteBuilder{
    
    private IDAOConfigurazione daoConfigurazione;
    private IDAOIstanzaPortType daoIstanzaPortType;
    private IDAOIstanzaAccordoDiCollaborazione daoIstanzaAccordo;
    private Log logger = LogFactory.getLog(this.getClass());

    @Override
    public void configure() throws Exception {
        ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();
        String port = conf.getWebservicesPort();
        String indirizzo = conf.getWebservicesIndirizzo();
        WSIstanzaImpl wsIstanza = new WSIstanzaImpl(getContext(), daoConfigurazione, daoIstanzaPortType,daoIstanzaAccordo);        
        EndpointImpl endpoint = (EndpointImpl) Endpoint.create(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING, wsIstanza);
        endpoint.publish("http://" + indirizzo + ":" + port + "/ws/istanza");
        logger.info("Servizio pubblicato all'indirizzo:" + "http://" + indirizzo + ":" + port + "/ws/istanza");
        ContextStartup.getEndpointAvviati().add(endpoint);
        wsIstanza.avviaIstanze();
    }

    public IDAOConfigurazione getDaoConfigurazione() {
        return daoConfigurazione;
    }

    public void setDaoConfigurazione(IDAOConfigurazione daoConfigurazione) {
        this.daoConfigurazione = daoConfigurazione;
    }

    public IDAOIstanzaPortType getDaoIstanzaPortType() {
        return daoIstanzaPortType;
    }

    public void setDaoIstanzaPortType(IDAOIstanzaPortType daoIstanzaPortType) {
        this.daoIstanzaPortType = daoIstanzaPortType;
    }

    public IDAOIstanzaAccordoDiCollaborazione getDaoIstanzaAccordo() {
        return daoIstanzaAccordo;
    }

    public void setDaoIstanzaAccordo(IDAOIstanzaAccordoDiCollaborazione daoIstanzaAccordo) {
        this.daoIstanzaAccordo = daoIstanzaAccordo;
    }
    
    

}
