package test.it.unibas.freesbee.ge.funzionali.i;

import it.unibas.freesbee.ge.messaggi.XmlJDomUtil;
import it.unibas.freesbee.ge.modello.ConsegnaEventoPubblicato;
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
import it.unibas.freesbee.ge.stub.NotificaEventoPubblicato;
import it.unibas.freesbee.ge.stub.WSGestoreEventiImplService;
import it.unibas.freesbee.ge.ws.gestoreeventi.IWSGestoreEventi;
import it.unibas.freesbee.utilita.ClientPD;
import it.unibas.springfreesbee.ge.ConfigurazioneStatico;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import junit.framework.Assert;
import org.apache.camel.ContextTestSupport;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.helpers.XMLUtils;
import org.jdom.Document;
import org.xml.sax.SAXException;
import test.it.unibas.freesbee.ge.dao.DAOMock;

public class Prelievo29 extends ContextTestSupport {

    protected Log logger = LogFactory.getLog(this.getClass());
    protected MockEndpoint resultEndpoint;
    protected static String id;

    public void testPrelievo29Init() throws Exception {
        logger.info("TEST - 29");
        logger.info("Init");
        FileWriter f = new FileWriter(DAOMock.NOTIFICA);
        String s = "";
        f.append(s.subSequence(0, s.length()));
        f.flush();

    }

    public void testPrelievo29Prima() throws Exception {
        try {
            logger.info("TEST - 29");
            logger.info("Data di inizio anteriore a quella di pubblicazione");

            Calendar date = new GregorianCalendar();
            date.add(Calendar.SECOND, 10);

            String sms = "<?xml version='1.0' encoding='utf-8'?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ge=\"http://ge.freesbee.unibas.it/\"><soapenv:Header/><soapenv:Body><ge:aggiungiSottoscrizione><sottoscrizione>";
            sms += "<filtroData><dataFine/><dataInizio>" + FiltroData.convertiDateToXmlDate(date.getTime()) + "</dataInizio></filtroData>";
            sms += "<sottoscrittore><descrizione/><nome>" + DAOMock.NOME_SOGGETTO_D + "</nome><tipo>" + DAOMock.TIPO_SOGGETTO_SPC + "</tipo></sottoscrittore>";
            sms += "<tipoSottoscrizione>" + ISottoscrizione.TIPO_SOTTOSCRIZIONE_NOTIFICA + "</tipoSottoscrizione>";
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

    public void testPrelievo29() throws Exception {
        logger.info("TEST - 29");
        logger.info("Pubblicazione di A per la categoria Test2");
        try {
            Thread.currentThread().sleep(15000);
            //Carico il messaggio che verebbe generato dal GE
            URL url = this.getClass().getResource("/dati/i/test1.xml");
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

    public void testPrelievo29VerificaNotificaComunicazione() throws Exception {
        try {
            logger.info("TEST - 29");
            logger.info("Verifica Notifica Comunicazione");
            Thread.currentThread().sleep(5000);
            //Comunicazione inviata
            org.jdom.Document doc = XmlJDomUtil.caricaXML(DAOMock.NOTIFICA, false);
            String s = XmlJDomUtil.convertiDocumentToString(doc);

            logger.debug("Comunicazione inviata: ");
            logger.debug(XmlJDomUtil.formattaXML(s));

            NotificaEventoPubblicato n = (NotificaEventoPubblicato) unmarsahaller(s);

            assertNotNull(n.getIdEventoPubblicato());
            assertFalse(n.getIdEventoPubblicato().equals(""));

            id = n.getIdEventoPubblicato();

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            Assert.fail(ex.getMessage());
        }
    }

    private Object unmarsahaller(String s) throws JAXBException, FileNotFoundException, SOAPException, ParserConfigurationException, SAXException, IOException {
        logger.debug("Effettuo l'unmarshaller dell'oggetto richiesta");
//        s = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"><SOAP-ENV:Header/><SOAP-ENV:Body><ns2:notificaEventoPubblicato xmlns:ns2=\"http://ge.freesbee.unibas.it/\"><idEventoPubblicato>32771</idEventoPubblicato></ns2:notificaEventoPubblicato></SOAP-ENV:Body></SOAP-ENV:Envelope>";

        MessageFactory messageFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
        SOAPMessage message = messageFactory.createMessage();
        SOAPPart soapPart = message.getSOAPPart();
        Reader soapStringReader = new StringReader(s);
        Source soapSource = new StreamSource(soapStringReader);
        soapPart.setContent(soapSource);
        message.saveChanges();
        String streamSoap = "";

        org.w3c.dom.Document document = message.getSOAPBody().extractContentAsDocument();
        streamSoap = XMLUtils.toString(document);


        JAXBContext jc = JAXBContext.newInstance("it.unibas.freesbee.ge.stub");
        javax.xml.bind.Unmarshaller u = jc.createUnmarshaller();
        InputStream is = new ByteArrayInputStream(streamSoap.getBytes());
        logger.debug(streamSoap);
        it.unibas.freesbee.ge.stub.NotificaEventoPubblicato notifica = (NotificaEventoPubblicato) u.unmarshal(is);

        return notifica;
    }

    public void testPrelievo29PrelevaMessaggio() throws Exception {
        logger.info("TEST - 29");
        logger.info("Preleva Messaggio");
        try {
            Thread.currentThread().sleep(15000);
            WSGestoreEventiImplService ss = new WSGestoreEventiImplService(new URL("http://" + ConfigurazioneStatico.getInstance().getWebservicesIndirizzo() + ":" + ConfigurazioneStatico.getInstance().getWebservicesPort() + DAOCostanti.URL_WSDL_GESTORE_EVENTI + "?wsdl"));
            IWSGestoreEventi port = ss.getWSGestoreEventiImplPort();

            ConsegnaEventoPubblicato risposta = port.prelevaMessaggio(id, DAOMock.NOME_SOGGETTO_C, DAOMock.TIPO_SOGGETTO_SPC, DAOMock.CATEGORIA_EVENTI_INTERNA_NON_ATTIVA_1);

            Assert.fail("Il prelievo del messaggio non ha lanciato eccezione");

        } catch (Exception ex) {
        }
    }

    public void testPrelievo29Clean() throws Exception {
        try {
            logger.info("TEST - 29");
            logger.info("Clean");
            Thread.currentThread().sleep(15000);
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

