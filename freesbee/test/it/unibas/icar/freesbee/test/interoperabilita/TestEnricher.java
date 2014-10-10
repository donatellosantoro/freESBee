package it.unibas.icar.freesbee.test.interoperabilita;

import it.unibas.icar.freesbee.modello.AccordoServizio;
import it.unibas.icar.freesbee.modello.Configurazione;
import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.modello.ProfiloEGov;
import it.unibas.icar.freesbee.persistenza.DBManager;
import it.unibas.icar.freesbee.test.persistenza.mock.DAOConfigurazioneMock;
import it.unibas.icar.freesbee.test.persistenza.mock.DAOPortaDelegataMockInteroperabilita;
import it.unibas.icar.freesbee.test.persistenza.mock.DAOServizioMock;
import it.unibas.icar.freesbee.processors.ProcessorEnricher;
import it.unibas.icar.freesbee.processors.SOAPProcessorReader;
import it.unibas.icar.freesbee.processors.strategy.EnricherPortaDelegataFactory;
import it.unibas.icar.freesbee.processors.strategy.EnricherPortaDelegataSemplice;
import it.unibas.icar.freesbee.test.persistenza.mock.DAOAccordoServizioMock;
import it.unibas.icar.freesbee.test.persistenza.mock.DAOSoggettoMock;
import it.unibas.icar.freesbee.utilita.CostantiBusta;
import junit.framework.Assert;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.CamelTestSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TestEnricher extends CamelTestSupport {

    protected Log logger = LogFactory.getLog(this.getClass());
    protected MockEndpoint resultEndpoint;

    public void test() throws Exception {
//        resultEndpoint.expectedBodiesReceived("<agv:prenota xmlns:agv=\"http://agenziaViaggi.unibas.it/\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><agv:andata><agv:partenza>new york</agv:partenza><agv:arrivo>roma</agv:arrivo></agv:andata></agv:prenota>");
//        sendBody("direct:start", "<?xml version='1.0' encoding='utf-8'?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Header>    <agv:prova xmlns:agv=\"http://agenziaViaggi.unibas.it/\">test</agv:prova></soapenv:Header><soapenv:Body><agv:prenota xmlns:agv=\"http://agenziaViaggi.unibas.it/\"><agv:andata><agv:partenza>new york</agv:partenza><agv:arrivo>roma</agv:arrivo></agv:andata></agv:prenota></soapenv:Body></soapenv:Envelope>");
//        Assert.assertTrue(resultEndpoint.getReceivedExchanges().size() == 1);
//        Exchange exchange = resultEndpoint.getReceivedExchanges().get(0);
//        
//        Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiBusta.MESSAGGIO);
//        String nomePortaDelegata = messaggio.getPortaDelegata();
//        Assert.assertEquals("PortaDelegataProva", nomePortaDelegata);
        
//        Assert.assertEquals("FruitoreUNICAR", messaggio.getFruitore());
//        Assert.assertEquals("SPC", messaggio.getTipoFruitore());
//        Assert.assertEquals("ErogatoreUNICAR", messaggio.getErogatore());
//        Assert.assertEquals("SPC", messaggio.getTipoErogatore());
//        Assert.assertEquals("IcarOneWayLoopback", messaggio.getServizio());
//        Assert.assertEquals("SPC", messaggio.getTipoServizio());
//        Assert.assertEquals(null, messaggio.getAzione());
//        Assert.assertEquals(AccordoServizio.PROFILO_ONEWAY, messaggio.getProfiloCollaborazione());
//        Assert.assertEquals("false",messaggio.getConfermaRicezione());
//        Assert.assertEquals(ProfiloEGov.ACCETTA_DUPLICATI,messaggio.getInoltro());
        
//        resultEndpoint.assertIsSatisfied();
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
                DBManager dbManager = new DBManager();
                dbManager.setDaoPortaDelegata(new DAOPortaDelegataMockInteroperabilita());
                dbManager.setDaoServizio(new DAOServizioMock());
                dbManager.setDaoConfigurazione(new DAOConfigurazioneMock(new Configurazione()));
                ProcessorEnricher processorEnricher = new ProcessorEnricher();
                processorEnricher.setDbManager(dbManager);
                EnricherPortaDelegataFactory factory = new EnricherPortaDelegataFactory();
                EnricherPortaDelegataSemplice enricher = new EnricherPortaDelegataSemplice();
                factory.setEnricherPortaDelegataSemplice(enricher);
                enricher.setDaoAccordo(new DAOAccordoServizioMock());
                enricher.setDaoServizio(new DAOServizioMock());
                enricher.setDaoSoggetto(new DAOSoggettoMock());
                enricher.setDaoAccordo(new DAOAccordoServizioMock());
                enricher.setDbManager(dbManager);
                processorEnricher.setEnricherFactory(factory);
                Configurazione config = new Configurazione();
                config.setInviaANICA(true);
                config.setRegistroFreesbee(false);
                config.setConnettoreRegistroServizi("http://localhost:8081/nica-regpriv/Inquiry");
                from("direct:start")
                       .process(new ProcessInitialize())
                       //.process(new SOAPProcessorReader())
                        .process(SOAPProcessorReader.getInstance())
                        .process(processorEnricher)
                        .to("mock:result");
            }
        };
    }   
    
    private class ProcessInitialize implements Processor {
        public void process(Exchange exchange) throws Exception {            
            Messaggio messaggio = new Messaggio();
            messaggio.setPortaDelegata("PortaDelegataProva");
            messaggio.setPortaDelegataChannel(exchange.getExchangeId());
            exchange.setProperty(CostantiBusta.MESSAGGIO, messaggio);
        }        
    }
}
