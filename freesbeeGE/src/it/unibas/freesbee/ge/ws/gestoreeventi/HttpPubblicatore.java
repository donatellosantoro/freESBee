package it.unibas.freesbee.ge.ws.gestoreeventi;

import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiInterna;
import it.unibas.freesbee.ge.persistenza.IDAOEventoPubblicatoInterno;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreInterno;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrizioneInterna;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCostanti;
import it.unibas.freesbee.ge.processor.ProcessorAck;
import it.unibas.freesbee.ge.processor.ProcessorFault;
import it.unibas.freesbee.ge.processor.ProcessorGestionePubblicatori;
import it.unibas.freesbee.ge.processor.ProcessorHibernateBegin;
import it.unibas.freesbee.ge.processor.ProcessorHibernateCommit;
import it.unibas.freesbee.ge.processor.ProcessorHibernateRollback;
import it.unibas.freesbee.ge.processor.ProcessorLog;
import it.unibas.freesbee.ge.processor.ProcessorSOAPReader;
import it.unibas.springfreesbee.ge.ConfigurazioneStatico;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpPubblicatore extends RouteBuilder {

    private Log logger = LogFactory.getLog(this.getClass());
    private String categoriaEventi;
    private IDAOPubblicatoreInterno daoPubblicatoreInterno;
    private IDAOCategoriaEventiInterna daoCategoriaEventiInterna;
    private IDAOEventoPubblicatoInterno daoEventoPubblicatoInterno;
    private IDAOSottoscrizioneInterna daoSottoscrizioneInterna;


    public HttpPubblicatore(String categoriaEventi, IDAOPubblicatoreInterno daoSoggetto, IDAOCategoriaEventiInterna daoCategoriaEvento, IDAOSottoscrizioneInterna daoSottoscrizione, IDAOEventoPubblicatoInterno daoEventoPubblicato) {
        this.categoriaEventi = categoriaEventi;
        this.daoPubblicatoreInterno = daoSoggetto;
        this.daoCategoriaEventiInterna = daoCategoriaEvento;
        this.daoEventoPubblicatoInterno = daoEventoPubblicato;
        this.daoSottoscrizioneInterna = daoSottoscrizione;
    }

    @Override
    public void configure() throws Exception {
        ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();
//        String indirizzo = "cxf://http://" + conf.getWebservicesIndirizzo() + ":" + conf.getWebservicesPort() + DAOCostanti.URL_WSDL_GESTIONE_GESTIONE_PUBBLICATORI + "/" + categoriaEventi; //+ "?serviceClass=it.unibas.freesbee.ge.ws.gestoreeventi.IWSGestionePubblicatori";
        String indirizzo = "jetty://http://" + "0.0.0.0" + ":" + conf.getWebservicesPortGestori()+ DAOCostanti.URL_WSDL_GESTIONE_GESTIONE_PUBBLICATORI + "/" + categoriaEventi + "/";


        if (logger.isInfoEnabled()) {
            logger.info("Avvio il ws all'indirizzo " + indirizzo);
        }

        this.from(indirizzo)
            .process(new ProcessorLog(HttpPubblicatore.class))
            .tryBlock()
                .process(new ProcessorHibernateBegin())
                .process(new ProcessorSOAPReader())
                .process(new ProcessorGestionePubblicatori(categoriaEventi, daoPubblicatoreInterno, daoCategoriaEventiInterna, daoSottoscrizioneInterna, daoEventoPubblicatoInterno))
                .process(new ProcessorHibernateCommit())
                .process(new ProcessorAck())
            .handle(Exception.class)
                .process(new ProcessorHibernateRollback())
                .process(new ProcessorFault())
                .end();
    }

   
}
