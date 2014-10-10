package it.unibas.silvio.saml;

import it.unibas.silvio.modello.Dati;
import it.unibas.silvio.modello.IstanzaOperation;
import it.unibas.silvio.modello.Messaggio;
import it.unibas.silvio.modello.ParametriEseguiIstanza;
import it.unibas.silvio.processor.ProcessorSendFruitore;
import it.unibas.silvio.util.CostantiSilvio;
import junit.framework.Assert;
import org.apache.camel.ContextTestSupport;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TestInf3 extends ContextTestSupport {

    protected Log logger = LogFactory.getLog(this.getClass());
    protected MockEndpoint resultEndpoint;
    
//    private String indirizzo = "https://idp.testshib.org/idp/profile/SAML2/Redirect/SSO?SAMLRequest=fZFPb4MwDMW%2FCsq9JEGqClFBQu2l0v6pTDvsMoVgRqSQZHHYum8%2FoKq0XXa1%0A%2Fd7zz96jHI0X9RQHe4aPCTAml9FYFGujJFOwwknUKKwcAUVUoqnv70SWMuGD%0Ai045Q5IaEULUzh6cxWmE0ED41ApOtoNLSThJjrOztnKZKckQo0dBqe58GucG%0ADrpNXXhfCnR27bUBusRk9AydDqAibZpHkpyOJXnLs13f8oIVO7Xtd4Xq%2BiwH%0A1Xa8ZyzjeT6PIU5zNkZpY0kyxvINZxvGnzkXPBPb4pUkLxBwXWYmIdV%2BwRWr%0ALvw6wP%2F88kZNqhsR%2BhQucvQG0snqVuKKtfC1zkAcNuj39FfWNdiLh9n8dHxy%0ARqvvpDbGfR0CyAjL7Wh1lfx9U%2FUD%0A&amp;RelayState=https%3A%2F%2Fsp.example.unibas.org%2Fsecure%2F";
    private String indirizzo = "https://192.168.1.17/secure/";
//    private String indirizzo = "https://www.google.com/accounts/ServiceLogin?service=mail&amp;passive=true&amp;rm=false&amp;continue=https%3A%2F%2Fmail.google.com%2Fmail%2F%3Fui%3Dhtml%26zy%3Dl&amp;bsv=1k96igf4806cy&amp;ss=1&amp;ltmpl=default&amp;ltmplcache=2";
    private String stringaMessaggio = "<test>prova SAML</test>";

    public void test() throws Exception {
        sendBody("direct:start", "");
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
                ProcessorSendFruitore processorSend = new ProcessorSendFruitore(context);
                from("direct:start").process(new ProcessInitialize()).process(processorSend).to("mock:result");
            }
        };
    }

    private class ProcessInitialize implements Processor {

        public void process(Exchange exchange) throws Exception {
            Message messageIn = exchange.getIn();
            messageIn.getHeaders().clear();
            Messaggio messaggio = new Messaggio();
            exchange.setProperty(CostantiSilvio.MESSAGGIO_RICHIESTA, messaggio);
            ParametriEseguiIstanza parametri = new ParametriEseguiIstanza();
            messaggio.setParametriEseguiIstanza(parametri);
            parametri.setIndirizzo(indirizzo);

            IstanzaOperation istanzaOperation = new IstanzaOperation();
            Dati datiIstanza = new Dati();
            istanzaOperation.setDatiRichiesta(datiIstanza);
            messaggio.setIstanzaOperation(istanzaOperation);
            messaggio.setMessaggio(stringaMessaggio);
        }
    }
}