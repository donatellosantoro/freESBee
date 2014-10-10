package it.unibas.icar.freesbee.test.core.moduloControllo.imbustamento;

import it.unibas.icar.freesbee.modello.Configurazione;
import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.persistenza.DBManager;
import it.unibas.icar.freesbee.persistenza.hibernate.DAOConfigurazioneHibernate;
import it.unibas.icar.freesbee.test.persistenza.mock.DAOConfigurazioneMock;
import it.unibas.icar.freesbee.test.persistenza.mock.DAOPortaDelegataMock;
import it.unibas.icar.freesbee.test.persistenza.mock.DAOServizioMock;
import it.unibas.icar.freesbee.processors.ProcessorEnricher;
import it.unibas.icar.freesbee.processors.ProcessorEnricherTestInteroperabilita;
import it.unibas.icar.freesbee.processors.ProcessorWrapper;
import it.unibas.icar.freesbee.processors.SOAPProcessorReader;
import it.unibas.icar.freesbee.processors.strategy.EnricherPortaDelegataFactory;
import it.unibas.icar.freesbee.processors.strategy.EnricherPortaDelegataSemplice;
import it.unibas.icar.freesbee.test.persistenza.mock.DAOAccordoServizioMock;
import it.unibas.icar.freesbee.test.persistenza.mock.DAOPortaDelegataMockTest;
import it.unibas.icar.freesbee.test.persistenza.mock.DAOSoggettoMock;
import it.unibas.icar.freesbee.utilita.CostantiBusta;
import it.unibas.icar.freesbee.utilita.CostantiSOAP;
import it.unibas.icar.freesbee.utilita.BustaUtil;
import java.util.Map;
import javax.xml.namespace.QName;
import junit.framework.Assert;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.CamelTestSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.servicemix.common.JbiConstants;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Node;

public class TestImbustamento extends CamelTestSupport {

    protected Log logger = LogFactory.getLog(this.getClass());
    protected MockEndpoint resultEndpoint;

    public void test() throws Exception {
        resultEndpoint.expectedBodiesReceived("<?xml version=\"1.0\" encoding=\"UTF-8\"?><agv:prenota xmlns:agv=\"http://agenziaViaggi.unibas.it/\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><agv:andata><agv:partenza>new york</agv:partenza><agv:arrivo>roma</agv:arrivo></agv:andata></agv:prenota>");
        sendBody("direct:start", "<?xml version='1.0' encoding='utf-8'?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Header>    <agv:prova xmlns:agv=\"http://agenziaViaggi.unibas.it/\">test</agv:prova></soapenv:Header><soapenv:Body><agv:prenota xmlns:agv=\"http://agenziaViaggi.unibas.it/\"><agv:andata><agv:partenza>new york</agv:partenza><agv:arrivo>roma</agv:arrivo></agv:andata></agv:prenota></soapenv:Body></soapenv:Envelope>");
        Assert.assertTrue(resultEndpoint.getReceivedExchanges().size() == 1);
        Exchange exchange = resultEndpoint.getReceivedExchanges().get(0);
        
        Map<QName, DocumentFragment> mappaHeaders = (Map<QName, DocumentFragment>) exchange.getProperty(JbiConstants.SOAP_HEADERS);

        DocumentFragment df = mappaHeaders.get(new QName(CostantiSOAP.NAMESPACE_EGOV, "Header_EGov"));        
        Assert.assertNotNull(df);
        
        Node nodoIntestazione = BustaUtil.cercaNodo(df.getChildNodes(), "Intestazione"); 
        Assert.assertNotNull(nodoIntestazione);
        Node nodoIntestazioneMessaggio = BustaUtil.cercaNodo(nodoIntestazione.getChildNodes(), "IntestazioneMessaggio"); 
        Assert.assertNotNull(nodoIntestazioneMessaggio);
        
        Node nodoMittente = BustaUtil.cercaNodo(nodoIntestazioneMessaggio.getChildNodes(), CostantiBusta.MITTENTE); 
        Assert.assertNotNull(nodoMittente);
        
        Node identificativoParteMittente = BustaUtil.cercaNodo(nodoMittente.getChildNodes(), CostantiBusta.IDENTIFICATIVOPARTE);        
        Assert.assertNotNull(identificativoParteMittente);
        String tipoMittente = BustaUtil.getAttributo(identificativoParteMittente, CostantiBusta.TIPO);
        String valoreMittente = identificativoParteMittente.getTextContent();
        System.out.println("Tipo mittente: " + tipoMittente);
        System.out.println("Valore mittente: " + valoreMittente);
        Assert.assertEquals("SPC",tipoMittente);
        Assert.assertEquals("FRUITORE", valoreMittente);
        
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
                ProcessorEnricher processorEnricher = new ProcessorEnricher();
                DBManager dbManager = new DBManager();
                dbManager.setDaoPortaDelegata(new DAOPortaDelegataMock());
                processorEnricher.setDbManager(dbManager);
                dbManager.setDaoConfigurazione(new DAOConfigurazioneMock(new Configurazione()));
                EnricherPortaDelegataFactory factory = new EnricherPortaDelegataFactory();
                EnricherPortaDelegataSemplice enricher = new EnricherPortaDelegataSemplice();
                factory.setEnricherPortaDelegataSemplice(enricher);
                enricher.setDaoAccordo(new DAOAccordoServizioMock());
                enricher.setDaoServizio(new DAOServizioMock());
                enricher.setDaoSoggetto(new DAOSoggettoMock());
                enricher.setDbManager(dbManager);
                processorEnricher.setEnricherFactory(factory);
                ProcessorWrapper processorWrapper = new ProcessorWrapper();
                processorWrapper.setDbManager(dbManager);
                from("direct:start")
                        .process(new ProcessInitialize())
                        //.process(new SOAPProcessorReader())
                        .process(SOAPProcessorReader.getInstance())
                        .process(processorEnricher)
                        .process(processorWrapper)
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
