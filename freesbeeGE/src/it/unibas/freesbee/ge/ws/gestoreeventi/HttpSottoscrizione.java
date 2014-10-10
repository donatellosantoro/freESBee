package it.unibas.freesbee.ge.ws.gestoreeventi;

import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiEsterna;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiInterna;
import it.unibas.freesbee.ge.persistenza.IDAOGestoreEventi;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreEsterno;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreInterno;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrittore;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCostanti;
import it.unibas.freesbee.ge.processor.ProcessorAck;
import it.unibas.freesbee.ge.processor.ProcessorFault;
import it.unibas.freesbee.ge.processor.ProcessorGestioneSottoscrizioni;
import it.unibas.freesbee.ge.processor.ProcessorHibernateBegin;
import it.unibas.freesbee.ge.processor.ProcessorHibernateCommit;
import it.unibas.freesbee.ge.processor.ProcessorHibernateRollback;
import it.unibas.freesbee.ge.processor.ProcessorLog;
import it.unibas.freesbee.ge.processor.ProcessorSOAPReader;
import it.unibas.springfreesbee.ge.ConfigurazioneStatico;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpSottoscrizione extends RouteBuilder {

    private Log logger = LogFactory.getLog(this.getClass());
    private String categoriaEventi;
    private IDAOSottoscrittore daoSottoscrittore;
    private IDAOCategoriaEventiInterna daoCategoriaEventiInterna;
    private IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna;
    private IDAOPubblicatoreInterno daoPubblicatoreInterno;
    private IDAOPubblicatoreEsterno daoPubblicatoreEsterno;
    private IDAOGestoreEventi daoGestoreEventi;
    public static final String MESSAGGIO_ERRORE = "ERRORE";

    public HttpSottoscrizione(String categoriaEventi, IDAOCategoriaEventiInterna daoCategoriaEventiInterna, IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna, IDAOSottoscrittore daoSottoscrittore, IDAOPubblicatoreInterno daoPubblicatoreInterno, IDAOPubblicatoreEsterno daoPubblicatoreEsterno, IDAOGestoreEventi daoGestoreEventi) {
        this.categoriaEventi = categoriaEventi;
        this.daoSottoscrittore = daoSottoscrittore;
        this.daoCategoriaEventiInterna = daoCategoriaEventiInterna;
        this.daoCategoriaEventiEsterna = daoCategoriaEventiEsterna;
        this.daoPubblicatoreInterno = daoPubblicatoreInterno;
        this.daoPubblicatoreEsterno = daoPubblicatoreEsterno;
        this.daoGestoreEventi = daoGestoreEventi;

    }

    @Override
    public void configure() throws Exception {
        ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();
        //String indirizzo = "cxf://http://" + conf.getWebservicesIndirizzo() + ":" + conf.getWebservicesPort() + DAOCostanti.URL_WSDL_GESTIONE_GESTIONE_SOTTOSCRIZIONI + "/" + categoriaEventi; // + "?serviceClass=it.unibas.freesbee.ge.ws.gestoreeventi.IWSGestioneSottoscrizioni";
        String indirizzo = "jetty://http://" + "0.0.0.0" + ":" + conf.getWebservicesPortGestori() + DAOCostanti.URL_WSDL_GESTIONE_GESTIONE_SOTTOSCRIZIONI + "/" + categoriaEventi + "/";


        if (logger.isInfoEnabled()) {
            logger.info("Avvio il ws all'indirizzo " + indirizzo);
        }

        this.from(indirizzo)
                .process(new ProcessorLog(HttpSottoscrizione.class))
                .tryBlock().process(new ProcessorHibernateBegin())
                .process(new ProcessorSOAPReader())
                .process(new ProcessorGestioneSottoscrizioni(categoriaEventi, daoCategoriaEventiInterna, daoCategoriaEventiEsterna, daoSottoscrittore, daoPubblicatoreInterno, daoPubblicatoreEsterno, daoGestoreEventi))
                .process(new ProcessorHibernateCommit()).process(new ProcessorHibernateBegin())
                .process(new ProcessorInviaRichiestaInformazioniPubblicatori())
                .process(new ProcessorHibernateCommit()).process(new ProcessorAck()).handle(Exception.class).process(new ProcessorHibernateRollback()).process(new ProcessorFault()).end();
    }



  
}
