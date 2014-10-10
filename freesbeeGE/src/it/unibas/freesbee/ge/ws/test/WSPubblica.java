package it.unibas.freesbee.ge.ws.test;

import it.unibas.freesbee.ge.persistenza.hibernate.DAOUtilHibernate;
import java.io.FileWriter;
import java.util.Map;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;

public class WSPubblica extends RouteBuilder {

    private Log logger = LogFactory.getLog(this.getClass());

    @Override
    public void configure() throws Exception {
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        String nome = "";
        try {
            sessionFactory.getCurrentSession().beginTransaction();

            sessionFactory.getCurrentSession().getTransaction().commit();


            RouteBuilder wsAvviato = new HttpPubblica();

            this.getContext().addRoutes(wsAvviato);


        } catch (Exception ex) {
            if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().rollback();
            }
            logger.error("Impossibile avviare il web services per la categoria di evento " + nome);
            logger.error(ex.getMessage());
        }

    }

    class HttpPubblica extends RouteBuilder {

        private Log logger = LogFactory.getLog(this.getClass());

        private HttpPubblica() {
        }

        @Override
        public void configure() throws Exception {
            String indirizzo = "jetty://http://localhost:8111/PD/PD_PUBBLICA/";
//            String indirizzo = ConfigurazioniFactory.getConfigurazioneIstance().getPdPubblica();
            if (logger.isInfoEnabled()) {
                logger.info("Avvio il ws di test all'indirizzo " + indirizzo);
            }

            this.from(indirizzo).process(new ProcessorTest(HttpPubblica.class));
        }
    }

    class ProcessorTest implements Processor {

        private Log logger = LogFactory.getLog(this.getClass());
        private Class classe;

        private ProcessorTest() {
            this.classe = this.classe;
        }

        private ProcessorTest(Class classe) {
            this.classe = classe;
        }

        public void process(Exchange exchange) throws Exception {
            logger.info(classe.getSimpleName() + ": processo la richiesta");
            //logger.info("La mappa delle proprieta' vale: " + exchange.getIn().getHeaders());
            Map headers = exchange.getIn().getHeaders();
            if (headers.get("TIPO_ELIMINAZIONE") == null) {
                logger.info("Scrivo in " + "c:\\pubblicaEsternamente.xml");
                FileWriter f = new FileWriter("c:\\pubblicaEsternamente.xml");
                String s = exchange.getIn().getBody(String.class);
                f.append(s.subSequence(0, s.length()));
                f.flush();
            } else {
                logger.info("Scrivo in " + "c:\\pubblicaEsternamente1.xml");
                FileWriter f = new FileWriter("c:\\pubblicaEsternamente1.xml");
                String s = exchange.getIn().getBody(String.class);
                f.append(s.subSequence(0, s.length()));
                f.flush();

            }
        }
    }
}