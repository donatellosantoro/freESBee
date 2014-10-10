package it.unibas.freesbee.ge.ws.sil;

import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiEsterna;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiInterna;
import it.unibas.freesbee.ge.persistenza.IDAOGestoreEventi;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreEsterno;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreInterno;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrittore;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrizioneEsterna;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCostanti;
import it.unibas.springfreesbee.ge.ConfigurazioneStatico;
import it.unibas.springfreesbee.ge.ContextStartup;
import javax.xml.ws.Endpoint;
import org.apache.camel.builder.RouteBuilder;
import org.apache.cxf.jaxws.EndpointImpl;

public class WSConsegnaMessaggio extends RouteBuilder {

    private IDAOPubblicatoreEsterno daoPubblicatoreEsterno;
    private IDAOSottoscrittore daoSottoscrittore;
    private IDAOGestoreEventi daoGestoreEventi;
    private IDAOCategoriaEventiInterna daoCategoriaEventiInterna;
    private IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna;
    private IDAOPubblicatoreInterno daoPubblicatoreInterno;
    private IDAOSottoscrizioneEsterna daoSottoscrizioneEsterna;

    @Override
    public void configure() throws Exception {

        ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();
        String port = conf.getWebservicesPort();
        String indirizzo = conf.getWebservicesIndirizzo();
        EndpointImpl endpoint = (EndpointImpl) Endpoint.create(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING, new WSConsegnaMessaggioImpl(daoPubblicatoreEsterno, daoSottoscrittore, daoGestoreEventi, daoCategoriaEventiInterna, daoCategoriaEventiEsterna, daoPubblicatoreInterno, daoSottoscrizioneEsterna));
        endpoint.publish("http://" + indirizzo + ":" + port + DAOCostanti.URL_WSDL_CONSEGNA_MESSAGGI);

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

    public IDAOCategoriaEventiInterna getDaoCategoriaEventiInterna() {
        return daoCategoriaEventiInterna;
    }

    public void setDaoCategoriaEventiInterna(IDAOCategoriaEventiInterna daoCategoriaEventiInterna) {
        this.daoCategoriaEventiInterna = daoCategoriaEventiInterna;
    }

    public IDAOCategoriaEventiEsterna getDaoCategoriaEventiEsterna() {
        return daoCategoriaEventiEsterna;
    }

    public void setDaoCategoriaEventiEsterna(IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna) {
        this.daoCategoriaEventiEsterna = daoCategoriaEventiEsterna;
    }

    public IDAOPubblicatoreInterno getDaoPubblicatoreInterno() {
        return daoPubblicatoreInterno;
    }

    public void setDaoPubblicatoreInterno(IDAOPubblicatoreInterno daoPubblicatoreInterno) {
        this.daoPubblicatoreInterno = daoPubblicatoreInterno;
    }

    public IDAOSottoscrizioneEsterna getDaoSottoscrizioneEsterna() {
        return daoSottoscrizioneEsterna;
    }

    public void setDaoSottoscrizioneEsterna(IDAOSottoscrizioneEsterna daoSottoscrizioneEsterna) {
        this.daoSottoscrizioneEsterna = daoSottoscrizioneEsterna;
    }
}
