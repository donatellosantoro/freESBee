package it.unibas.icar.freesbee.test.core.portaDelegata;

import it.unibas.icar.freesbee.processors.SOAPProcessorReader;
import it.unibas.icar.freesbee.test.qualificazione.UtilTest;
import it.unibas.icar.freesbee.utilita.CostantiBusta;
import junit.framework.Assert;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.CamelTestSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

public class TestSoapProcessorReader extends CamelTestSupport {

//    protected Log logger = LogFactory.getLog(this.getClass());
    private static final Logger logger = LoggerFactory.getLogger(TestSoapProcessorReader.class.getName());
    protected MockEndpoint resultEndpoint;

    public void test() throws Exception {
        resultEndpoint.expectedBodiesReceived("<agv:prenota xmlns:agv=\"http://agenziaViaggi.unibas.it/\"><agv:andata><agv:partenza>new york</agv:partenza><agv:arrivo>roma</agv:arrivo></agv:andata></agv:prenota>");
        sendBody("direct:start", "<?xml version='1.0' encoding='utf-8'?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Header>    <agv:prova xmlns:agv=\"http://agenziaViaggi.unibas.it/\">test</agv:prova></soapenv:Header><soapenv:Body><agv:prenota xmlns:agv=\"http://agenziaViaggi.unibas.it/\"><agv:andata><agv:partenza>new york</agv:partenza><agv:arrivo>roma</agv:arrivo></agv:andata></agv:prenota></soapenv:Body></soapenv:Envelope>");
        Assert.assertTrue(resultEndpoint.getReceivedExchanges().size() == 1);
        Exchange exchange = resultEndpoint.getReceivedExchanges().get(0);
        String nomePortaDelegata = (String) exchange.getProperty(CostantiBusta.PORTA_DELEGATA);
        Assert.assertEquals("PortaDelegataProva", nomePortaDelegata);
        resultEndpoint.assertIsSatisfied();
    }


    public void testDue() throws Exception {
//        resultEndpoint.expectedBodiesReceived("<ns1:getQuote soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:ns1=\"urn:xmethods-delayed-quotes\" xmlns:se=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/1999/XMLSchema-instance\">	<symbol xsi:type=\"xsd:string\" xmlns=\"\">IBM</symbol></ns1:getQuote>");
//        sendBody("direct:start", "<env:Envelope xmlns:env=\"http://schemas.xmlsoap.org/soap/envelope/\"><env:Header><ciao soapenv:actor=\"http://www.prova.it\" soapenv:mustUnderstand=\"1\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"\">prova</ciao><ciao2 soapenv:actor=\"http://www.prova.it\" soapenv:mustUnderstand=\"1\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"\">prova2</ciao2><eGov_IT:Intestazione SOAP_ENV:actor=\"http://www.cnipa.it/eGov_it/portadominio\" SOAP_ENV:mustUnderstand=\"1\" xmlns:SOAP_ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:eGov_IT=\"http://www.cnipa.it/schemas/2003/eGovIT/Busta1_0/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><eGov_IT:IntestazioneMessaggio><eGov_IT:Mittente><eGov_IT:IdentificativoParte tipo=\"SPC\">IcarlabSoggettoFruitore</eGov_IT:IdentificativoParte></eGov_IT:Mittente><eGov_IT:Destinatario><eGov_IT:IdentificativoParte tipo=\"SPC\">IcarlabSoggettoErogatore</eGov_IT:IdentificativoParte></eGov_IT:Destinatario><eGov_IT:ProfiloCollaborazione>EGOV_IT_ServizioSincrono</eGov_IT:ProfiloCollaborazione><eGov_IT:Servizio tipo=\"SPC\">AgenziaViaggiService</eGov_IT:Servizio><eGov_IT:Messaggio><eGov_IT:Identificatore>IcarlabSoggettoFruitore_IcarlabSoggettoFruitoreSPCoopIT_0000001_2008-01-17_16:37</eGov_IT:Identificatore><eGov_IT:OraRegistrazione tempo=\"EGOV_IT_Locale\">2008-01-17T16:37:47.000</eGov_IT:OraRegistrazione></eGov_IT:Messaggio><eGov_IT:ProfiloTrasmissione confermaRicezione=\"false\" inoltro=\"EGOV_IT_PIUDIUNAVOLTA\"/></eGov_IT:IntestazioneMessaggio><eGov_IT:ListaTrasmissioni><eGov_IT:Trasmissione><eGov_IT:Origine><eGov_IT:IdentificativoParte tipo=\"SPC\">Icarlab.unibas.it</eGov_IT:IdentificativoParte></eGov_IT:Origine><eGov_IT:Destinazione><eGov_IT:IdentificativoParte tipo=\"SPC\">IcarlabSoggettoErogatore</eGov_IT:IdentificativoParte></eGov_IT:Destinazione><eGov_IT:OraRegistrazione tempo=\"EGOV_IT_Locale\">2008-01-17T16:37:47.000</eGov_IT:OraRegistrazione></eGov_IT:Trasmissione></eGov_IT:ListaTrasmissioni></eGov_IT:Intestazione></env:Header><env:Body><ns1:getQuote soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:ns1=\"urn:xmethods-delayed-quotes\" xmlns:se=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/1999/XMLSchema-instance\">	<symbol xsi:type=\"xsd:string\" xmlns=\"\">IBM</symbol></ns1:getQuote></env:Body></env:Envelope>");
        sendBody("direct:start", "<env:Envelope xmlns:env=\"http://schemas.xmlsoap.org/soap/envelope/\"><env:Header><ciao soapenv:actor=\"http://www.prova.it\" soapenv:mustUnderstand=\"1\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">prova</ciao><ciao2 soapenv:actor=\"http://www.prova.it\" soapenv:mustUnderstand=\"1\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" >prova2</ciao2><eGov_IT:Intestazione SOAP_ENV:actor=\"http://www.cnipa.it/eGov_it/portadominio\" SOAP_ENV:mustUnderstand=\"1\" xmlns:SOAP_ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:eGov_IT=\"http://www.cnipa.it/schemas/2003/eGovIT/Busta1_0/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><eGov_IT:IntestazioneMessaggio><eGov_IT:Mittente><eGov_IT:IdentificativoParte tipo=\"SPC\">IcarlabSoggettoFruitore</eGov_IT:IdentificativoParte></eGov_IT:Mittente><eGov_IT:Destinatario><eGov_IT:IdentificativoParte tipo=\"SPC\">IcarlabSoggettoErogatore</eGov_IT:IdentificativoParte></eGov_IT:Destinatario><eGov_IT:ProfiloCollaborazione>EGOV_IT_ServizioSincrono</eGov_IT:ProfiloCollaborazione><eGov_IT:Servizio tipo=\"SPC\">AgenziaViaggiService</eGov_IT:Servizio><eGov_IT:Messaggio><eGov_IT:Identificatore>IcarlabSoggettoFruitore_IcarlabSoggettoFruitoreSPCoopIT_0000001_2008-01-17_16:37</eGov_IT:Identificatore><eGov_IT:OraRegistrazione tempo=\"EGOV_IT_Locale\">2008-01-17T16:37:47.000</eGov_IT:OraRegistrazione></eGov_IT:Messaggio><eGov_IT:ProfiloTrasmissione confermaRicezione=\"false\" inoltro=\"EGOV_IT_PIUDIUNAVOLTA\"/></eGov_IT:IntestazioneMessaggio><eGov_IT:ListaTrasmissioni><eGov_IT:Trasmissione><eGov_IT:Origine><eGov_IT:IdentificativoParte tipo=\"SPC\">Icarlab.unibas.it</eGov_IT:IdentificativoParte></eGov_IT:Origine><eGov_IT:Destinazione><eGov_IT:IdentificativoParte tipo=\"SPC\">IcarlabSoggettoErogatore</eGov_IT:IdentificativoParte></eGov_IT:Destinazione><eGov_IT:OraRegistrazione tempo=\"EGOV_IT_Locale\">2008-01-17T16:37:47.000</eGov_IT:OraRegistrazione></eGov_IT:Trasmissione></eGov_IT:ListaTrasmissioni></eGov_IT:Intestazione></env:Header><env:Body><ns1:getQuote soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:ns1=\"urn:xmethods-delayed-quotes\" xmlns:se=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/1999/XMLSchema-instance\">	<symbol xsi:type=\"xsd:string\" xmlns=\"\">IBM</symbol></ns1:getQuote></env:Body></env:Envelope>");
        Assert.assertTrue(resultEndpoint.getReceivedExchanges().size() == 1);
        Exchange exchange = resultEndpoint.getReceivedExchanges().get(0);
        String nomePortaDelegata = (String) exchange.getProperty(CostantiBusta.PORTA_DELEGATA);
        Assert.assertEquals("PortaDelegataProva", nomePortaDelegata);
        resultEndpoint.assertIsSatisfied();
    }


    public void testTre() throws Exception {
        String richiesta = UtilTest.leggiFile("bodyRisp.xml");
        // TODO verificare questo tet
//        resultEndpoint.expectedBodiesReceived(""); 
//        sendBody("direct:start", richiesta);
//        Assert.assertTrue(resultEndpoint.getReceivedExchanges().size() == 1);
//        Exchange exchange = resultEndpoint.getReceivedExchanges().get(0);
//        logger.info("Body: \n" + exchange.getIn().getBody(String.class));
        resultEndpoint.assertIsSatisfied();
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        resultEndpoint = (MockEndpoint) resolveMandatoryEndpoint("mock:result");
    }

    @Override
    protected RouteBuilder createRouteBuilder() {
        return new RouteBuilder() {

            public void configure() {
                from("direct:start")
                    .process(new ProcessInitialize())
                    //.process(new SOAPProcessorReader())
                    .process(SOAPProcessorReader.getInstance())
                    .to("mock:result");
            }
        };
    }
    
    private class ProcessInitialize implements Processor {
        public void process(Exchange exchange) throws Exception {
            exchange.setProperty(CostantiBusta.PORTA_DELEGATA, "PortaDelegataProva");
            exchange.setProperty(CostantiBusta.PORTA_DELEGATA_CHANNEL_ID, exchange.getExchangeId());
        }        
    }
}
