package it.unibas.icar.freesbee.ws.registroservizi;

import com.google.inject.Singleton;
import it.unibas.icar.freesbee.modello.ConfigurazioneStatico;
import it.unibas.icar.freesbee.utilita.FreesbeeUtil;
import it.unibas.icar.freesbee.utilita.MessageUtil;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.LoggerFactory;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

@Singleton
public class WSRegistroServiziProxy extends RouteBuilder {

//    private static Log logger = LogFactory.getLog(WSRegistroServiziProxy.class);
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(WSRegistroServiziProxy.class.getName());

    @Override
    public void configure() throws Exception {
        ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();
        String port = conf.getWebservicesPort();
        String indirizzo = conf.getWebservicesIndirizzo();
        String interoperabilitaIndirizzoNica = conf.getInteroperabilitaRegistroNica();

        //TODO: Disabilitato per evitare di configurare varie porte
        
//        this.from("jetty:http://localhost:8194/ws/registroServizi")
//                .process(new ProcessorLog(this.getClass()))
//                .process(new ProcessorIdentificaRegistro())
//                .choice()
//                    .when(header("TIPO_REGISTRO").isEqualTo("WSDL"))
//                        .to("http://" + indirizzo + ":" + port + "/ws/registroServizi?wsdl")
//                    .when(header("TIPO_REGISTRO").isEqualTo("FREESBEE"))
//                        .to("http://" + indirizzo + ":" + port + "/ws/registroServizi")
//                    .when(header("NICA_GETFRUITORI").isEqualTo(true))
//                        .process(new NotSupportedProcessor())
//                    .otherwise()
//                        .to(interoperabilitaIndirizzoNica)
//                        .process(new PostProcessor());
    }

    private class ProcessorIdentificaRegistro implements Processor {

        public void process(Exchange exchange) throws Exception {
            //ContextStartup.aggiungiThread(this.getClass().getName());
            Exchange copyExchange = exchange.copy();
            String messaggio = MessageUtil.getString(copyExchange.getIn());
            messaggio = messaggio.replace("xmlns=\"http://ws.registry.openspcoop.org\"", "");
            messaggio = messaggio.replace("xmlns=\"http://registry.dao.openspcoop.org\"", "");
            messaggio = messaggio.replace("xmlns=\"http://stub.sincronizzatore.registro.icar.regione.toscana.it\"", "");
//            messaggio = messaggio.replace("<properties>", "<properties xsi:type=\"ns1:Map\" xmlns:ns1=\"http://xml.apache.org/xml-soap\">");
            MessageUtil.setString(exchange.getOut(), messaggio);
            if (messaggio.isEmpty()) {
                if(logger.isDebugEnabled()) logger.debug("Il messaggio ricevuto dal registro dei servizi e' vuoto. Probabilmente e' stato richiesto il wsdl.");
                exchange.getIn().getHeaders().put("TIPO_REGISTRO", "WSDL");
                return;
            }

            if (messaggio.contains("xmlns:fre=\"http://icar.unibas.it/freesbee/\"")) {
                exchange.getIn().getHeaders().put("TIPO_REGISTRO", "FREESBEE");
                if(logger.isDebugEnabled()) logger.debug("Il messaggio contiene intestazioni freesbee");
            } else {
                exchange.getIn().getHeaders().put("TIPO_REGISTRO", "NOFREESBEE");
                if(logger.isDebugEnabled()) logger.debug("Il messaggio non contiene intestazioni freesbee");
                FreesbeeUtil.aggiungiInstestazioniHttp(exchange.getOut(), "SOAPAction", "");
                if (messaggio.contains("getFruitoriServizioSpcoop")) {
                    exchange.getIn().getHeaders().put("NICA_GETFRUITORI", true);
                }
            }
        }
    }

    private class PostProcessor implements Processor {

        public void process(Exchange exchange) throws Exception {
            Exchange copyExchange = exchange.copy();
            String messaggio = copyExchange.getOut().getBody(String.class);
            messaggio = messaggio.replace("<properties>", "<properties xsi:type=\"ns1:Map\" xmlns:ns1=\"http://xml.apache.org/xml-soap\"  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">");            
            MessageUtil.setString(exchange.getOut(), messaggio);
        }
    }

    private class NotSupportedProcessor implements Processor {

        public void process(Exchange exchange) throws Exception {
            logger.error("E' stata richiesta un'operazione non supportata");
            MessageUtil.setString(exchange.getOut(), "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"> <soapenv:Body> <soapenv:Fault> <faultcode>soapenv:Server.userException</faultcode> <faultstring>it.toscana.regione.icar.registro.sincronizzatore.ws.SincronizzatoreNotFoundException: Servizio [SPC/UNICAR_SPC/MonitoraggioSLA] non e' stato importato in precedenza</faultstring> <detail> <it.toscana.regione.icar.registro.sincronizzatore.ws.SincronizzatoreNotFoundException/> <ns1:hostname xmlns:ns1=\"http://xml.apache.org/axis/\">icars1</ns1:hostname> </detail> </soapenv:Fault> </soapenv:Body></soapenv:Envelope>");
        }
    }
}
