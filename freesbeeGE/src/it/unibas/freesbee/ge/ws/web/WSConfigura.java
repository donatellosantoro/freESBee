package it.unibas.freesbee.ge.ws.web;

import it.unibas.freesbee.ge.persistenza.IDAOConfigurazione;
import it.unibas.freesbee.ge.persistenza.IDAOConfigurazioneSP;
import it.unibas.freesbee.ge.persistenza.IDAOGestoreEventi;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreInterno;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrittore;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCostanti;
import it.unibas.springfreesbee.ge.ConfigurazioneStatico;
import it.unibas.springfreesbee.ge.ContextStartup;
import org.apache.camel.builder.RouteBuilder;
import org.apache.cxf.jaxws.EndpointImpl;
import javax.xml.ws.Endpoint;

public class WSConfigura extends RouteBuilder {

    private IDAOConfigurazione daoConfigurazione;
    private IDAOConfigurazioneSP daoConfigurazioneSP;
    private IDAOPubblicatoreInterno daoPubblicatoreInterno;
    private IDAOSottoscrittore daoSottoscrittore;
    private IDAOGestoreEventi daoGestoreEventi;


    @Override
    public void configure() throws Exception {
        ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();
        String port = conf.getWebservicesPort();
        String indirizzo = conf.getWebservicesIndirizzo();
        EndpointImpl endpoint = (EndpointImpl) Endpoint.create(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING, new WSConfiguraImpl(daoConfigurazione, daoConfigurazioneSP, daoPubblicatoreInterno, daoSottoscrittore, daoGestoreEventi));
        endpoint.publish("http://" + indirizzo + ":" + port + DAOCostanti.URL_WSDL_WEB_CONFIGURAZIONE);

        ContextStartup.getEndpointAvviati().add(endpoint);

    }

    public IDAOConfigurazione getDaoConfigurazione() {
        return daoConfigurazione;
    }

    public void setDaoConfigurazione(IDAOConfigurazione daoConfigurazione) {
        this.daoConfigurazione = daoConfigurazione;
    }

    public IDAOConfigurazioneSP getDaoConfigurazioneSP() {
        return daoConfigurazioneSP;
    }

    public void setDaoConfigurazioneSP(IDAOConfigurazioneSP daoConfigurazioneSP) {
        this.daoConfigurazioneSP = daoConfigurazioneSP;
    }

    public IDAOPubblicatoreInterno getDaoPubblicatoreInterno() {
        return daoPubblicatoreInterno;
    }

    public void setDaoPubblicatoreInterno(IDAOPubblicatoreInterno daoPubblicatoreInterno) {
        this.daoPubblicatoreInterno = daoPubblicatoreInterno;
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

}
