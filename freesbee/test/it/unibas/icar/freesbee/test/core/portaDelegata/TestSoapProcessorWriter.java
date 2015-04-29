package it.unibas.icar.freesbee.test.core.portaDelegata;

import it.unibas.icar.freesbee.processors.SOAPProcessorReader;
import it.unibas.icar.freesbee.processors.SOAPProcessorWriterFactory;
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

public class TestSoapProcessorWriter extends CamelTestSupport {

//    protected Log logger = LogFactory.getLog(this.getClass());
    private static final Logger logger = LoggerFactory.getLogger(TestSoapProcessorWriter.class.getName());
    protected MockEndpoint resultEndpoint;

    public void test() throws Exception {
        resultEndpoint.expectedBodiesReceived("<?xml version='1.0' encoding='UTF-8'?><SOAP_ENV:Envelope xmlns:SOAP_ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"><SOAP_ENV:Header><agv:prova xmlns:agv=\"http://agenziaViaggi.unibas.it/\">test</agv:prova></SOAP_ENV:Header><SOAP_ENV:Body><agv:prenota xmlns:agv=\"http://agenziaViaggi.unibas.it/\"><agv:andata><agv:partenza>new york</agv:partenza><agv:arrivo>roma</agv:arrivo></agv:andata></agv:prenota></SOAP_ENV:Body></SOAP_ENV:Envelope>");
        sendBody("direct:start", "<?xml version='1.0' encoding='utf-8'?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Header><agv:prova xmlns:agv=\"http://agenziaViaggi.unibas.it/\">test</agv:prova></soapenv:Header><soapenv:Body><agv:prenota xmlns:agv=\"http://agenziaViaggi.unibas.it/\"><agv:andata><agv:partenza>new york</agv:partenza><agv:arrivo>roma</agv:arrivo></agv:andata></agv:prenota></soapenv:Body></soapenv:Envelope>");
        Assert.assertTrue(resultEndpoint.getReceivedExchanges().size() == 1);
        Exchange exchange = resultEndpoint.getReceivedExchanges().get(0);
        resultEndpoint.assertIsSatisfied();
    }

    public void testDue() throws Exception {
//        resultEndpoint.expectedBodiesReceived("<?xml version=\"1.0\" ?><env:Envelope xmlns:env=\"http://schemas.xmlsoap.org/soap/envelope/\"><env:Header><agv:prova xmlns:agv=\"http://agenziaViaggi.unibas.it/\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">test</agv:prova></env:Header><env:Body><agv:prenota xmlns:agv=\"http://agenziaViaggi.unibas.it/\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><agv:andata><agv:partenza>new york</agv:partenza><agv:arrivo>roma</agv:arrivo></agv:andata></agv:prenota></env:Body></env:Envelope>");
        sendBody("direct:start", "<env:Envelope xmlns:env=\"http://schemas.xmlsoap.org/soap/envelope/\"><env:Body><ns1:getQuote soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:ns1=\"http://agenziaViaggi.unibas.it\" xmlns:se=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/1999/XMLSchema-instance\"></ns1:getQuote></env:Body></env:Envelope>");
        Assert.assertTrue(resultEndpoint.getReceivedExchanges().size() == 1);
        Exchange exchange = resultEndpoint.getReceivedExchanges().get(0);
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
                    //.process(new SOAPProcessorWriterFactory())
                    .process(SOAPProcessorWriterFactory.getInstance().getProcessorWriter())
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
