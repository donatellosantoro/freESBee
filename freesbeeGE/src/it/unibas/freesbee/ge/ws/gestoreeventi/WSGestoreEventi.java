package it.unibas.freesbee.ge.ws.gestoreeventi;

import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiEsterna;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiInterna;
import it.unibas.freesbee.ge.persistenza.IDAOEventoPubblicatoEsterno;
import it.unibas.freesbee.ge.persistenza.IDAOEventoPubblicatoInterno;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreEsterno;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreInterno;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrittore;
import it.unibas.freesbee.ge.persistenza.IDAOStatoMessaggioEsterno;
import it.unibas.freesbee.ge.persistenza.IDAOStatoMessaggioInterno;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCostanti;
import it.unibas.springfreesbee.ge.ConfigurazioneStatico;
import it.unibas.springfreesbee.ge.ContextStartup;
import javax.xml.ws.Endpoint;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.jaxws.EndpointImpl;

public class WSGestoreEventi extends RouteBuilder {

    private static Log logger = LogFactory.getLog(WSGestoreEventi.class);
    private IDAOEventoPubblicatoInterno daoEventoPubblicatoInterno;
    private IDAOEventoPubblicatoEsterno daoEventoPubblicatoEsterno;
    private IDAOPubblicatoreInterno daoPubblicatoreInterno;
    private IDAOPubblicatoreEsterno daoPubblicatoreEsterno;
    private IDAOStatoMessaggioInterno daoStatoMessaggioInterno;
    private IDAOStatoMessaggioEsterno daoStatoMessaggioEsterno;
    private IDAOSottoscrittore daoSottoscrittore;
    private IDAOCategoriaEventiInterna daoCategoriaEventiInterna;
    private IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna;


    @Override
    public void configure() throws Exception {

        ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();
        String port = conf.getWebservicesPort();
        String indirizzo = conf.getWebservicesIndirizzo();
        EndpointImpl endpoint = (EndpointImpl) Endpoint.create(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING, new WSGestoreEventiImpl(daoEventoPubblicatoInterno, daoEventoPubblicatoEsterno, daoPubblicatoreInterno, daoPubblicatoreEsterno, daoStatoMessaggioInterno, daoStatoMessaggioEsterno, daoSottoscrittore,daoCategoriaEventiInterna, daoCategoriaEventiEsterna));
        endpoint.publish("http://" + indirizzo + ":" + port + DAOCostanti.URL_WSDL_GESTORE_EVENTI);
        logger.info("Servizio Gestore Eventi: " + "http://" + indirizzo + ":" + port + DAOCostanti.URL_WSDL_GESTORE_EVENTI);
        ContextStartup.getEndpointAvviati().add(endpoint);

    }

    public IDAOEventoPubblicatoInterno getDaoEventoPubblicatoInterno() {
        return daoEventoPubblicatoInterno;
    }

    public void setDaoEventoPubblicatoInterno(IDAOEventoPubblicatoInterno daoEventoPubblicatoInterno) {
        this.daoEventoPubblicatoInterno = daoEventoPubblicatoInterno;
    }

    public IDAOEventoPubblicatoEsterno getDaoEventoPubblicatoEsterno() {
        return daoEventoPubblicatoEsterno;
    }

    public void setDaoEventoPubblicatoEsterno(IDAOEventoPubblicatoEsterno daoEventoPubblicatoEsterno) {
        this.daoEventoPubblicatoEsterno = daoEventoPubblicatoEsterno;
    }

    public IDAOPubblicatoreInterno getDaoPubblicatoreInterno() {
        return daoPubblicatoreInterno;
    }

    public void setDaoPubblicatoreInterno(IDAOPubblicatoreInterno daoPubblicatoreInterno) {
        this.daoPubblicatoreInterno = daoPubblicatoreInterno;
    }

    public IDAOPubblicatoreEsterno getDaoPubblicatoreEsterno() {
        return daoPubblicatoreEsterno;
    }

    public void setDaoPubblicatoreEsterno(IDAOPubblicatoreEsterno daoPubblicatoreEsterno) {
        this.daoPubblicatoreEsterno = daoPubblicatoreEsterno;
    }

    public IDAOStatoMessaggioInterno getDaoStatoMessaggioInterno() {
        return daoStatoMessaggioInterno;
    }

    public void setDaoStatoMessaggioInterno(IDAOStatoMessaggioInterno daoStatoMessaggioInterno) {
        this.daoStatoMessaggioInterno = daoStatoMessaggioInterno;
    }

    public IDAOStatoMessaggioEsterno getDaoStatoMessaggioEsterno() {
        return daoStatoMessaggioEsterno;
    }

    public void setDaoStatoMessaggioEsterno(IDAOStatoMessaggioEsterno daoStatoMessaggioEsterno) {
        this.daoStatoMessaggioEsterno = daoStatoMessaggioEsterno;
    }

    public IDAOSottoscrittore getDaoSottoscrittore() {
        return daoSottoscrittore;
    }

    public void setDaoSottoscrittore(IDAOSottoscrittore daoSottoscrittore) {
        this.daoSottoscrittore = daoSottoscrittore;
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
}
