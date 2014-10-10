package it.unibas.icar.freesbee.test.core.moduloControllo.sbustamento;

import it.unibas.icar.freesbee.modello.AccordoServizio;
import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.modello.ProfiloEGov;
import it.unibas.icar.freesbee.processors.ProcessorUnWrapper;
import it.unibas.icar.freesbee.processors.SOAPProcessorReader;
import it.unibas.icar.freesbee.utilita.CostantiBusta;
import junit.framework.Assert;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.CamelTestSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TestSbustamento extends CamelTestSupport {

    protected Log logger = LogFactory.getLog(this.getClass());
    protected MockEndpoint resultEndpoint;

    public void testBustaUno() throws Exception {
        resultEndpoint.expectedBodiesReceived("<agv:prenota xmlns:agv=\"http://agenziaViaggi.unibas.it/\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><agv:andata><agv:partenza>new york</agv:partenza><agv:arrivo>roma</agv:arrivo></agv:andata><agv:ritorno><agv:partenza>roma</agv:partenza><agv:arrivo>new york</agv:arrivo></agv:ritorno></agv:prenota>");
        sendBody("direct:start", "<env:Envelope xmlns:env=\"http://schemas.xmlsoap.org/soap/envelope/\"><env:Header><agv:prova xmlns:agv=\"http://agenziaViaggi.unibas.it/\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">test</agv:prova><eGov_IT:Intestazione SOAP_ENV:actor=\"http://www.cnipa.it/eGov_it/portadominio\" SOAP_ENV:mustUnderstand=\"1\" xmlns:SOAP_ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:eGov_IT=\"http://www.cnipa.it/schemas/2003/eGovIT/Busta1_0/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><eGov_IT:IntestazioneMessaggio><eGov_IT:Mittente><eGov_IT:IdentificativoParte tipo=\"SPC\">IcarlabSoggettoFruitore</eGov_IT:IdentificativoParte></eGov_IT:Mittente><eGov_IT:Destinatario><eGov_IT:IdentificativoParte tipo=\"SPC\">IcarlabSoggettoErogatore</eGov_IT:IdentificativoParte></eGov_IT:Destinatario><eGov_IT:ProfiloCollaborazione>EGOV_IT_ServizioSincrono</eGov_IT:ProfiloCollaborazione><eGov_IT:Servizio tipo=\"SPC\">AgenziaViaggiService</eGov_IT:Servizio><eGov_IT:Messaggio><eGov_IT:Identificatore>IcarlabSoggettoFruitore_IcarlabSoggettoFruitoreSPCoopIT_0000001_2007-10-30_19:30</eGov_IT:Identificatore><eGov_IT:OraRegistrazione tempo=\"EGOV_IT_Locale\">2008-01-14T16:04:21.000</eGov_IT:OraRegistrazione></eGov_IT:Messaggio><eGov_IT:ProfiloTrasmissione confermaRicezione=\"false\" inoltro=\"EGOV_IT_PIUDIUNAVOLTA\"/></eGov_IT:IntestazioneMessaggio><eGov_IT:ListaTrasmissioni><eGov_IT:Trasmissione><eGov_IT:Origine><eGov_IT:IdentificativoParte tipo=\"SPC\">Icarlab.unibas.it</eGov_IT:IdentificativoParte></eGov_IT:Origine><eGov_IT:Destinazione><eGov_IT:IdentificativoParte tipo=\"SPC\">IcarlabSoggettoErogatore</eGov_IT:IdentificativoParte></eGov_IT:Destinazione><eGov_IT:OraRegistrazione tempo=\"EGOV_IT_Locale\">2008-01-14T16:04:21.000</eGov_IT:OraRegistrazione></eGov_IT:Trasmissione></eGov_IT:ListaTrasmissioni></eGov_IT:Intestazione></env:Header><env:Body><agv:prenota xmlns:agv=\"http://agenziaViaggi.unibas.it/\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><agv:andata><agv:partenza>new york</agv:partenza><agv:arrivo>roma</agv:arrivo></agv:andata><agv:ritorno><agv:partenza>roma</agv:partenza><agv:arrivo>new york</agv:arrivo></agv:ritorno></agv:prenota></env:Body></env:Envelope>");
        Assert.assertTrue(resultEndpoint.getReceivedExchanges().size() == 1);
        Exchange exchange = resultEndpoint.getReceivedExchanges().get(0);

        Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiBusta.MESSAGGIO);
        String nomePortaDelegata = messaggio.getPortaDelegata();
        Assert.assertEquals("PortaDelegataProva", nomePortaDelegata);

        Assert.assertEquals("IcarlabSoggettoFruitore", messaggio.getFruitore());
        Assert.assertEquals("SPC", messaggio.getTipoFruitore());
        Assert.assertEquals("IcarlabSoggettoErogatore", messaggio.getErogatore());
        Assert.assertEquals("SPC", messaggio.getTipoErogatore());
        Assert.assertEquals("AgenziaViaggiService", messaggio.getServizio());
        Assert.assertEquals("SPC", messaggio.getTipoServizio());
        Assert.assertEquals(null, messaggio.getAzione());
        Assert.assertEquals(AccordoServizio.PROFILO_SINCRONO, messaggio.getProfiloCollaborazione());
        Assert.assertEquals("false", messaggio.getConfermaRicezione());
        Assert.assertEquals(ProfiloEGov.ACCETTA_DUPLICATI, messaggio.getInoltro());
        Assert.assertEquals("IcarlabSoggettoFruitore_IcarlabSoggettoFruitoreSPCoopIT_0000001_2007-10-30_19:30", messaggio.getIdMessaggio());
        Assert.assertEquals("2008-01-14T16:04:21.000", messaggio.getOraRegistrazione());
        Assert.assertEquals("EGOV_IT_Locale", messaggio.getTempo());

        resultEndpoint.assertIsSatisfied();
    }

    public void testBustaDue() throws Exception {
        sendBody("direct:start", "<?xml version=\"1.0\" encoding=\"utf-8\"?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Header><eGov_IT:Intestazione SOAP_ENV:actor=\"http://www.cnipa.it/eGov_it/portadominio\" SOAP_ENV:mustUnderstand=\"1\" xmlns:SOAP_ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:eGov_IT=\"http://www.cnipa.it/schemas/2003/eGovIT/Busta1_0/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><eGov_IT:IntestazioneMessaggio><eGov_IT:Mittente><eGov_IT:IdentificativoParte tipo=\"SPC\">IcarlabSoggettoErogatore</eGov_IT:IdentificativoParte></eGov_IT:Mittente><eGov_IT:Destinatario><eGov_IT:IdentificativoParte tipo=\"SPC\">IcarlabSoggettoFruitore</eGov_IT:IdentificativoParte></eGov_IT:Destinatario><eGov_IT:ProfiloCollaborazione>EGOV_IT_ServizioSincrono</eGov_IT:ProfiloCollaborazione><eGov_IT:Servizio tipo=\"SPC\">AgenziaViaggiService</eGov_IT:Servizio><eGov_IT:Messaggio><eGov_IT:Identificatore>IcarlabSoggettoErogatore_IcarlabSoggettoErogatoreSPCoopIT_0000006_2008-01-14_13:58</eGov_IT:Identificatore><eGov_IT:OraRegistrazione tempo=\"EGOV_IT_Locale\">2008-01-14T13:58:02.000</eGov_IT:OraRegistrazione><eGov_IT:RiferimentoMessaggio>IcarlabSoggettoFruitore_IcarlabSoggettoFruitoreSPCoopIT_0000001_2007-10-30_19:30</eGov_IT:RiferimentoMessaggio><eGov_IT:Scadenza>2008-01-19T13:58:00.000</eGov_IT:Scadenza></eGov_IT:Messaggio><eGov_IT:ProfiloTrasmissione confermaRicezione=\"false\" inoltro=\"EGOV_IT_PIUDIUNAVOLTA\"/></eGov_IT:IntestazioneMessaggio><eGov_IT:ListaTrasmissioni><eGov_IT:Trasmissione><eGov_IT:Origine><eGov_IT:IdentificativoParte tipo=\"SPC\">IcarlabSoggettoErogatore</eGov_IT:IdentificativoParte></eGov_IT:Origine><eGov_IT:Destinazione><eGov_IT:IdentificativoParte tipo=\"SPC\">Icarlab.unibas.it</eGov_IT:IdentificativoParte></eGov_IT:Destinazione><eGov_IT:OraRegistrazione tempo=\"EGOV_IT_Locale\">2008-01-14T13:58:01.000</eGov_IT:OraRegistrazione></eGov_IT:Trasmissione><eGov_IT:Trasmissione><eGov_IT:Origine><eGov_IT:IdentificativoParte tipo=\"SPC\">Icarlab.unibas.it</eGov_IT:IdentificativoParte></eGov_IT:Origine><eGov_IT:Destinazione><eGov_IT:IdentificativoParte tipo=\"SPC\">IcarlabSoggettoFruitore</eGov_IT:IdentificativoParte></eGov_IT:Destinazione><eGov_IT:OraRegistrazione tempo=\"EGOV_IT_Locale\">2008-01-14T13:58:02.000</eGov_IT:OraRegistrazione></eGov_IT:Trasmissione></eGov_IT:ListaTrasmissioni></eGov_IT:Intestazione></soapenv:Header><soapenv:Body><agv:prenotaResponse xmlns:agv=\"http://unibas.it/agenzia\"><agv:riepilogo>-------------------------Agenzia Viaggi 8-)S.O. Windows Vista-------------------------Itinerario numero: 955* Andata	Partenza: ny - New York	Arrivo: rm - Roma* Ritorno	Partenza: rm - Roma	Arrivo: ny - New York-------------------------Costo totale: 342-------------------------</agv:riepilogo></agv:prenotaResponse></soapenv:Body></soapenv:Envelope>");
        Assert.assertTrue(resultEndpoint.getReceivedExchanges().size() == 1);
        Exchange exchange = resultEndpoint.getReceivedExchanges().get(0);
        Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiBusta.MESSAGGIO);

        Assert.assertEquals("IcarlabSoggettoErogatore", messaggio.getFruitore());
        Assert.assertEquals("SPC", messaggio.getTipoFruitore());
        Assert.assertEquals("IcarlabSoggettoFruitore", messaggio.getErogatore());
        Assert.assertEquals("SPC", messaggio.getTipoErogatore());
        Assert.assertEquals("AgenziaViaggiService", messaggio.getServizio());
        Assert.assertEquals("SPC", messaggio.getTipoServizio());
        Assert.assertEquals(null, messaggio.getAzione());
        Assert.assertEquals(AccordoServizio.PROFILO_SINCRONO, messaggio.getProfiloCollaborazione());
        Assert.assertEquals("false", messaggio.getConfermaRicezione());
        Assert.assertEquals(ProfiloEGov.ACCETTA_DUPLICATI, messaggio.getInoltro());
        Assert.assertEquals("IcarlabSoggettoErogatore_IcarlabSoggettoErogatoreSPCoopIT_0000006_2008-01-14_13:58", messaggio.getIdMessaggio());
        Assert.assertEquals("2008-01-14T13:58:02.000", messaggio.getOraRegistrazione());
        Assert.assertEquals("EGOV_IT_Locale", messaggio.getTempo());
        Assert.assertEquals("2008-01-19T13:58:00.000", messaggio.getScadenza());
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
                    //.process(new SOAPProcessorReader())
                    .process(SOAPProcessorReader.getInstance())
                    .process(new ProcessInitialize())
                    .process(new ProcessorUnWrapper())
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
