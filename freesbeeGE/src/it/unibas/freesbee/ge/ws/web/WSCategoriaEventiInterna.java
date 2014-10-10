package it.unibas.freesbee.ge.ws.web;

import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiEsterna;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiInterna;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreInterno;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCostanti;
import it.unibas.springfreesbee.ge.ConfigurazioneStatico;
import it.unibas.springfreesbee.ge.ContextStartup;
import org.apache.camel.builder.RouteBuilder;
import org.apache.cxf.jaxws.EndpointImpl;
import javax.xml.ws.Endpoint;

public class WSCategoriaEventiInterna extends RouteBuilder {

    private IDAOCategoriaEventiInterna daoCategoriaEventiInterna;
    private IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna;
    private IDAOPubblicatoreInterno daoPubblicatoreInterno;

    @Override
    public void configure() throws Exception {
        ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();
        String port = conf.getWebservicesPort();
        String indirizzo = conf.getWebservicesIndirizzo();
        EndpointImpl endpoint = (EndpointImpl) Endpoint.create(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING, new WSCategoriaEventiInternaImpl(daoCategoriaEventiEsterna,daoCategoriaEventiInterna, daoPubblicatoreInterno));
        endpoint.publish("http://" + indirizzo + ":" + port + DAOCostanti.URL_WSDL_WEB_CATEGORIA_EVENTI_INTERNA);

        ContextStartup.getEndpointAvviati().add(endpoint);

    }

    public IDAOCategoriaEventiInterna getDaoCategoriaEventiInterna() {
        return daoCategoriaEventiInterna;
    }

    public void setDaoCategoriaEventiInterna(IDAOCategoriaEventiInterna daoCategoriaEventiInterna) {
        this.daoCategoriaEventiInterna = daoCategoriaEventiInterna;
    }

    public IDAOPubblicatoreInterno getDaoPubblicatoreInterno() {
        return daoPubblicatoreInterno;
    }

    public void setDaoPubblicatoreInterno(IDAOPubblicatoreInterno daoPubblicatoreInterno) {
        this.daoPubblicatoreInterno = daoPubblicatoreInterno;
    }

    public IDAOCategoriaEventiEsterna getDaoCategoriaEventiEsterna() {
        return daoCategoriaEventiEsterna;
    }

    public void setDaoCategoriaEventiEsterna(IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna) {
        this.daoCategoriaEventiEsterna = daoCategoriaEventiEsterna;
    }
}
