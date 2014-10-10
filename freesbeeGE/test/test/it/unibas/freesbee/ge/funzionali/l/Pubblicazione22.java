package test.it.unibas.freesbee.ge.funzionali.l;

import it.unibas.freesbee.ge.messaggi.XmlJDomUtil;
import it.unibas.freesbee.ge.modello.FiltroData;
import it.unibas.freesbee.ge.modello.ICategoriaEventi;
import it.unibas.freesbee.ge.modello.ISottoscrizione;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCategoriaEventiEsternaHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCategoriaEventiInternaHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCostanti;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOGestoreEventiHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOPubblicatoreEsternoHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOPubblicatoreInternoHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOSottoscrittoreHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOUtilHibernate;
import it.unibas.freesbee.ge.processor.ProcessorGestioneSottoscrizioni;
import it.unibas.freesbee.ge.processor.ProcessorHibernateBegin;
import it.unibas.freesbee.ge.processor.ProcessorHibernateCommit;
import it.unibas.freesbee.ge.processor.ProcessorSOAPReader;
import it.unibas.freesbee.utilita.ClientPD;
import it.unibas.springfreesbee.ge.ConfigurazioneStatico;
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

public class Pubblicazione22 extends ContextTestSupport {

    protected Log logger = LogFactory.getLog(this.getClass());
    protected MockEndpoint resultEndpoint;

    public void testPubblicazione22Init() throws Exception {
        logger.info("TEST - 22");
        logger.info("Init");
        DAOUtilHibernate.beginTransaction();
        DAOMock.inserisciPubblicatoreInterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_A, ICategoriaEventi.GE_CONTROL_PROTOCOL);
        DAOUtilHibernate.commit();
    }

    public void testPubblicazione22Prima() throws Exception {
        try {
            logger.info("TEST - 22");
            logger.info("Filtro Pubblicatore corretto");

            Calendar dateI = new GregorianCalendar();
            dateI.add(Calendar.SECOND, 10);

            Calendar dateF = new GregorianCalendar();
            dateF.add(Calendar.DAY_OF_MONTH, 1);

            String sms = "<?xml version='1.0' encoding='utf-8'?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ge=\"http://ge.freesbee.unibas.it/\"><soapenv:Header/><soapenv:Body><ge:aggiungiSottoscrizione><sottoscrizione>";
            sms += "<filtroData><dataInizio>" + FiltroData.convertiDateToXmlDate(dateI.getTime()) + "</dataInizio><dataFine>" + FiltroData.convertiDateToXmlDate(dateF.getTime()) + "</dataFine></filtroData>";
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

    public void testPubblicazione22() throws Exception {
        logger.info("TEST - 22");
        logger.info("Cateogria di Eventi GEControlProtocol");
        try {
            Thread.currentThread().sleep(15000);
            //Carico il messaggio che verebbe generato dal GE
            URL url = this.getClass().getResource("/dati/l/test12.xml");
            Document doc = XmlJDomUtil.caricaXML(url.getFile(), false);
            String m = XmlJDomUtil.convertiDocumentToString(doc);

            ClientPD clientPD = new ClientPD();

            String indirizzo = "http://" + ConfigurazioneStatico.getInstance().getWebservicesIndirizzo() + ":" + ConfigurazioneStatico.getInstance().getWebservicesPort() + DAOCostanti.URL_WSDL_GESTORE_EVENTI;
            logger.info("url: " + indirizzo);
            logger.debug(m);

            clientPD.invocaPortaDelegata(indirizzo, m);

            Assert.fail("La pubblicazione non ha lanciato eccezione");

        } catch (Exception ex) {
        }
    }

    public void testPubblicazione22Clean() throws Exception {
        logger.info("TEST - 22");
        logger.info("Clean");
        DAOUtilHibernate.beginTransaction();
        DAOMock.ripulisciPubblicatoreInterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_A, ICategoriaEventi.GE_CONTROL_PROTOCOL);
        DAOUtilHibernate.commit();
    }

    public void testPubblicazione22CleanDopo() throws Exception {
        try {
            logger.info("TEST - 22");
            logger.info("CleanDopo");

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
                from("direct:start").process(new ProcessorHibernateBegin()).process(new ProcessorSOAPReader()).process(new ProcessorGestioneSottoscrizioni(ICategoriaEventi.GE_CONTROL_PROTOCOL, new DAOCategoriaEventiInternaHibernate(), new DAOCategoriaEventiEsternaHibernate(), new DAOSottoscrittoreHibernate(), new DAOPubblicatoreInternoHibernate(), new DAOPubblicatoreEsternoHibernate(), new DAOGestoreEventiHibernate())).process(new ProcessorHibernateCommit()).to("mock:result");
            }
        };

    }
}

