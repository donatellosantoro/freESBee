package it.unibas.freesbee.ge.ws.web;

import it.unibas.freesbee.ge.persistenza.IDAOUtente;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCostanti;
import it.unibas.springfreesbee.ge.ConfigurazioneStatico;
import it.unibas.springfreesbee.ge.ContextStartup;
import org.apache.camel.builder.RouteBuilder;
import org.apache.cxf.jaxws.EndpointImpl;
import javax.xml.ws.Endpoint;

public class WSAutenticazione extends RouteBuilder {

    private IDAOUtente daoUtente;

    @Override
    public void configure() throws Exception {
        ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();
        String port = conf.getWebservicesPort();
        String indirizzo = conf.getWebservicesIndirizzo();
        EndpointImpl endpoint = (EndpointImpl) Endpoint.create(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING, new WSAutenticazioneImpl(daoUtente));
        endpoint.publish("http://" + indirizzo + ":" + port + DAOCostanti.URL_WSDL__WEB_AUTENTICAZIONE);

        ContextStartup.getEndpointAvviati().add(endpoint);

    }

    public IDAOUtente getDaoUtente() {
        return daoUtente;
    }

    public void setDaoUtente(IDAOUtente daoUtente) {
        this.daoUtente = daoUtente;
    }
}
