package it.unibas.freesbee.ge.ws.web;

import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiEsterna;
import it.unibas.freesbee.ge.persistenza.IDAOGestoreEventi;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreEsterno;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrittore;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCostanti;
import it.unibas.springfreesbee.ge.ConfigurazioneStatico;
import it.unibas.springfreesbee.ge.ContextStartup;
import org.apache.camel.builder.RouteBuilder;
import org.apache.cxf.jaxws.EndpointImpl;
import javax.xml.ws.Endpoint;

public class WSGestioneGestoreEventi extends RouteBuilder {

    private IDAOPubblicatoreEsterno daoPubblicatoreEsterno;
    private IDAOSottoscrittore daoSottoscrittore;
    private IDAOGestoreEventi daoGestoreEventi;
    private IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna;

    @Override
    public void configure() throws Exception {
        ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();
        String port = conf.getWebservicesPort();
        String indirizzo = conf.getWebservicesIndirizzo();
        EndpointImpl endpoint = (EndpointImpl) Endpoint.create(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING, new WSGestioneGestoreEventiImpl(getDaoGestoreEventi(), getDaoCategoriaEventiEsterna(), getDaoPubblicatoreEsterno(), getDaoSottoscrittore()));
        endpoint.publish("http://" + indirizzo + ":" + port + DAOCostanti.URL_WSDL_WEB_GESTORE_EVENTI);

        ContextStartup.getEndpointAvviati().add(endpoint);

    }

    public IDAOPubblicatoreEsterno getDaoPubblicatoreEsterno() {
        return daoPubblicatoreEsterno;
    }

    public void setDaoPubblicatoreEsterno(IDAOPubblicatoreEsterno daoPubblicatoreEsterno) {
        this.daoPubblicatoreEsterno = daoPubblicatoreEsterno;
    }

    public IDAOSottoscrittore getDaoSottoscrittore() {
        return daoSottoscrittore;
    }

    public void setDaoSottoscrittore(IDAOSottoscrittore daoSottoscrittore) {
        this.daoSottoscrittore = daoSottoscrittore;
    }

    public IDAOGestoreEventi getDaoGestoreEventi() {
        return daoGestoreEventi;
    }

    public void setDaoGestoreEventi(IDAOGestoreEventi daoGestoreEventi) {
        this.daoGestoreEventi = daoGestoreEventi;
    }

    public IDAOCategoriaEventiEsterna getDaoCategoriaEventiEsterna() {
        return daoCategoriaEventiEsterna;
    }

    public void setDaoCategoriaEventiEsterna(IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna) {
        this.daoCategoriaEventiEsterna = daoCategoriaEventiEsterna;
    }
}
