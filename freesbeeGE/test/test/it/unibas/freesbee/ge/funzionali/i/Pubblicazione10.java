package test.it.unibas.freesbee.ge.funzionali.i;

import it.unibas.freesbee.ge.messaggi.XmlJDomUtil;
import it.unibas.freesbee.ge.modello.FiltroData;
import it.unibas.freesbee.ge.modello.ISottoscrizione;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCategoriaEventiEsternaHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCategoriaEventiInternaHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCostanti;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOGestoreEventiHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOPubblicatoreEsternoHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOPubblicatoreInternoHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOSottoscrittoreHibernate;
import it.unibas.freesbee.ge.processor.ProcessorGestioneSottoscrizioni;
import it.unibas.freesbee.ge.processor.ProcessorHibernateBegin;
import it.unibas.freesbee.ge.processor.ProcessorHibernateCommit;
import it.unibas.freesbee.ge.processor.ProcessorSOAPReader;
import it.unibas.freesbee.utilita.ClientPD;
import it.unibas.springfreesbee.ge.ConfigurazioneStatico;
import java.io.FileWriter;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import junit.framework.Assert;
import org.apache.camel.ContextTestSupport;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import test.it.unibas.freesbee.ge.dao.DAOMock;

public class Pubblicazione10 extends ContextTestSupport {

    protected Log logger = LogFactory.getLog(this.getClass());
    protected MockEndpoint resultEndpoint;

    public void testPubblicazione10Init() throws Exception {
        logger.info("TEST - 10");
        logger.info("Init");
        FileWriter f = new FileWriter(DAOMock.CONSEGNA);
        String s = "<root><ok/></root>";
        f.append(s.subSequence(0, s.length()));
        f.flush();

    }

    public void testPubblicazione10Prima() throws Exception {
        try {
            logger.info("TEST - 10");
            logger.info("Filtro Pubblicatore non soddisfatto");

            Calendar dateI = new GregorianCalendar();
            dateI.add(Calendar.SECOND, 10);

            Calendar dateF = new GregorianCalendar();
            dateF.add(Calendar.DAY_OF_MONTH, 1);

            String sms = "<?xml version='1.0' encoding='utf-8'?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ge=\"http://ge.freesbee.unibas.it/\"><soapenv:Header/><soapenv:Body><ge:aggiungiSottoscrizione><sottoscrizione>";
            sms += "<filtroData><dataInizio>" + FiltroData.convertiDateToXmlDate(dateI.getTime()) + "</dataInizio><dataFine>" + FiltroData.convertiDateToXmlDate(dateF.getTime()) + "</dataFine></filtroData>";
            sms += "<listaFiltroPublicatore><pubblicatore><descrizione/><nome>" + DAOMock.NOME_SOGGETTO_A + "</nome><tipo>" + DAOMock.TIPO_SOGGETTO_SPC + "</tipo></pubblicatore></listaFiltroPublicatore>";
            sms += "<sottoscrittore><descrizione/><nome>" + DAOMock.NOME_SOGGETTO_D + "</nome><tipo>" + DAOMock.TIPO_SOGGETTO_SPC + "</tipo></sottoscrittore>";
            sms += "<tipoSottoscrizione>" + ISottoscrizione.TIPO_SOTTOSCRIZIONE_CONSEGNA + "</tipoSottoscrizione>";
            sms += "</sottoscrizione></ge:aggiungiSottoscrizione></soapenv:Body></soapenv:Envelope>";

            sendBody("direct:start", sms);


            Exchange exchange = resultEndpoint.getReceivedExchanges().get(0);
            Message messaggio = (Message) exchange.getIn();

            resultEndpoint.assertIsSatisfied();


        } catch (Exception ex) {
            logger.error(ex.getMessage());
            Assert.fail("L'inserimento della sottoscrizione ha lanciato eccezione");
        }
    }

    public void testPubblicazione10() throws Exception {
        logger.info("TEST - 10");
        logger.info("Pubblicazione di B per la categoria Test2");
        try {
            Thread.currentThread().sleep(15000);
            //Carico il messaggio che verebbe generato dal GE
            URL url = this.getClass().getResource("/dati/i/test2.xml");
            Document doc = XmlJDomUtil.caricaXML(url.getFile(), false);
            String m = XmlJDomUtil.convertiDocumentToString(doc);

            ClientPD clientPD = new ClientPD();

            String indirizzo = "http://" + ConfigurazioneStatico.getInstance().getWebservicesIndirizzo() + ":" + ConfigurazioneStatico.getInstance().getWebservicesPort() + DAOCostanti.URL_WSDL_GESTORE_EVENTI;
            logger.info("url: " + indirizzo);
            logger.debug(m);

            clientPD.invocaPortaDelegata(indirizzo, m);


        } catch (Exception ex) {
            logger.error(ex.getMessage());
            Assert.fail("La pubblicazione ha lanciato eccezione");
        }
    }

    public void testPubblicazione10VerificaConsegnaComunicazione() {
        try {
            logger.info("TEST - 10");
            logger.info("Verifica Consegna");
            Thread.currentThread().sleep(5000);
            //Comunicazione inviata
            org.jdom.Document doc = XmlJDomUtil.caricaXML(DAOMock.CONSEGNA, false);
            String s = XmlJDomUtil.convertiDocumentToString(doc);

            //Comunicazione di test
            String m = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><root><ok/></root>";

            logger.info("Comunicazione inviata: ");
            logger.info(XmlJDomUtil.formattaXML(s));
            logger.info("Comunicazione di test:");
            logger.info(XmlJDomUtil.formattaXML(m));

            assertEquals(XmlJDomUtil.formattaXML(m), XmlJDomUtil.formattaXML(s));

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            Assert.fail(ex.getMessage());
        }
    }

    public void testPubblicazione10Clean() throws Exception {
        try {
            logger.info("TEST - 10");
            logger.info("Clean");

            String sms = "<?xml version='1.0' encoding='utf-8'?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ge=\"http://ge.freesbee.unibas.it/\"><soapenv:Header/><soapenv:Body><ge:eliminaSottoscrizione>";
            sms += "<sottoscrittore><descrizione/><nome>" + DAOMock.NOME_SOGGETTO_D + "</nome><tipo>" + DAOMock.TIPO_SOGGETTO_SPC + "</tipo></sottoscrittore>";
            sms += "</ge:eliminaSottoscrizione></soapenv:Body></soapenv:Envelope>";

            sendBody("direct:start", sms);

            Exchange exchange = resultEndpoint.getReceivedExchanges().get(0);
            Message messaggio = (Message) exchange.getIn();

            resultEndpoint.assertIsSatisfied();


        } catch (Exception ex) {
            logger.error(ex.getMessage());
            Assert.fail("L'eliminazione della sottoscrizione ha lanciato eccezione");
        }
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
                from("direct:start").process(new ProcessorHibernateBegin()).process(new ProcessorSOAPReader()).process(new ProcessorGestioneSottoscrizioni(DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_2, new DAOCategoriaEventiInternaHibernate(), new DAOCategoriaEventiEsternaHibernate(), new DAOSottoscrittoreHibernate(), new DAOPubblicatoreInternoHibernate(), new DAOPubblicatoreEsternoHibernate(), new DAOGestoreEventiHibernate())).process(new ProcessorHibernateCommit()).to("mock:result");
            }
        };

    }
}

