package it.unibas.silvio.route;

import it.unibas.silvio.modello.Messaggio;
import it.unibas.silvio.route.processors.ProcessorPolling;
import it.unibas.silvio.modello.StepAvvioIstanza;
import it.unibas.silvio.route.processors.ProcessorLog;
import it.unibas.silvio.util.CostantiSilvio;
import it.unibas.silvio.util.StringUtil;
import it.unibas.silvio.util.xml.XmlJDomUtil;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.jdom.Document;
import org.jdom.Element;

public class RouteAvvioIstanza extends AbstractRoute {
    private org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog(this.getClass().getName());
    private StepAvvioIstanza stepAvvioIstanza;

    public RouteAvvioIstanza(StepAvvioIstanza stepAvvioIstanza) {
        this.stepAvvioIstanza = stepAvvioIstanza;
    }

    @Override
    public void configure() throws Exception {

        String indirizzo = "jetty:" + stepAvvioIstanza.getIndirizzo();

        logger.info("Avvio un'istanza all'indirizzo " + indirizzo);

        this.from(indirizzo)
                .process(new ProcessorCreaMessaggio())
                .process(new ProcessorLog(this))
                .process(new ProcessorCreaDocument())
                .process(new ProcessorProssimoStep())
            .recipientList(header(PROSSIMO_STEP))
                .process(new ProcessorPolling(getContext()));

//        this.from(indirizzo)
//                .process(new ProcessorCreaMessaggio())
//                .process(new ProcessorLog(this))
//                .process(new ProcessorCreaDocument())
//                .process(new ProcessorProssimoStep())
//            .recipientList(constant("seda:Test1,seda:Test2").tokenize(","))
//                .process(new ProcessorPolling(getContext()));
//
//        this.from("seda:Test1")
//                .process(new ProcessorCreaMessaggio())
//                .process(new ProcessorTest(5000,"Test1"))
//            .recipientList(constant("seda:OUT"));
//
//        this.from("seda:Test2")
//                .process(new ProcessorCreaMessaggio())
//                .process(new ProcessorTest(2000,"Test2"))
//            .recipientList(constant("seda:OUT"));
//
//        this.from("seda:OUT")
//                .process(new ProcessorCreaMessaggio())
//                .process(new ProcessorTest(0,"OUT"))
//            .recipientList(constant("seda:SBLOCCA_POLLING"));
    }

//    private class ProcessorTest implements Processor{
//        private int time;
//        private String nome;
//
//        public ProcessorTest(int time, String nome) {
//            this.time = time;
//            this.nome = nome;
//        }
//
//        public void process(Exchange exchange) throws Exception {
//            logger.warn("######## INIZIO" + nome);
//            Thread.sleep(time);
//            logger.warn("########   FINE" + nome);
//        }
//
//    }

    private class ProcessorCreaDocument implements Processor{

        public void process(Exchange exchange) throws Exception {
            Element rootElement = new Element("sil-vio");
            Document doc = new Document(rootElement);
            Element dati = new Element(stepAvvioIstanza.getNome());
            rootElement.addContent(dati);

            String datiInput = exchange.getIn().getBody(String.class);
            if(StringUtil.isNonVuota(datiInput)){
                Document inputDocument = XmlJDomUtil.caricaXML(datiInput);
                dati.addContent(inputDocument.cloneContent());
            }

            exchange.setProperty(CostantiSilvio.DOCUMENT, doc);

//            String datiOutput = XmlJDomUtil.stampaXML(doc);
//            exchange.getIn().setBody(datiOutput,String.class);
        }

    }

    private class ProcessorCreaMessaggio implements Processor{

        public void process(Exchange exchange) throws Exception {
            exchange.getIn().getHeaders().clear();
            Messaggio messaggio = new Messaggio();
            messaggio.creaIdentificatore();
            messaggio.setTipo(Messaggio.INVIATO);
            messaggio.setCanalePolling(SEDA_CANALE_POLLING + exchange.getExchangeId());
            exchange.setProperty(CostantiSilvio.MESSAGGIO, messaggio);
            exchange.setProperty(CostantiSilvio.STEP, stepAvvioIstanza);
        }

    }

}
