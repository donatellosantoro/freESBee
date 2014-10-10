package test.it.unibas.freesbee.ge.comunicazione.h;

import it.unibas.freesbee.ge.messaggi.XmlJDomUtil;
import it.unibas.freesbee.ge.modello.CategoriaEventiEsterna;
import it.unibas.freesbee.ge.modello.FiltroData;
import it.unibas.freesbee.ge.modello.ISottoscrizione;
import it.unibas.freesbee.ge.modello.Sottoscrittore;
import it.unibas.freesbee.ge.modello.SottoscrizioneEsterna;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiEsterna;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrittore;
import it.unibas.freesbee.ge.persistenza.facade.DAOCategoriaEventiEsternaFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOSottoscrizioneEsternaFacade;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCategoriaEventiEsternaHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCategoriaEventiInternaHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOGestoreEventiHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOPubblicatoreEsternoHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOPubblicatoreInternoHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOSottoscrittoreHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOUtilHibernate;
import it.unibas.freesbee.ge.processor.ProcessorGestioneSottoscrizioni;
import it.unibas.freesbee.ge.processor.ProcessorHibernateBegin;
import it.unibas.freesbee.ge.processor.ProcessorHibernateCommit;
import it.unibas.freesbee.ge.processor.ProcessorSOAPReader;
import java.io.FileWriter;
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
import test.it.unibas.freesbee.ge.dao.DAOMock;

public class Eliminazione16 extends ContextTestSupport {

    protected Log logger = LogFactory.getLog(this.getClass());
    protected MockEndpoint resultEndpoint;

    public void testInit16() throws Exception {
        logger.info("TEST - 16");
        logger.info("Init");

        FileWriter f = new FileWriter(DAOMock.COMUNICAZIONE_ESTERNA_1);
        String s = "<root><ok/></root>";
        f.append(s.subSequence(0, s.length()));
        f.flush();


        DAOUtilHibernate.beginTransaction();

        IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna = new DAOCategoriaEventiEsternaHibernate();
        CategoriaEventiEsterna categoriaEventi = daoCategoriaEventiEsterna.findByNome(DAOMock.CATEGORIA_EVENTI_ESTERNA_NON_CONFERMATA_ATTIVA_6);

        Sottoscrittore sottoscrittore = new Sottoscrittore();
        sottoscrittore.setTipo(DAOMock.TIPO_SOGGETTO_SPC);
        sottoscrittore.setNome(DAOMock.NOME_SOGGETTO_M);
        sottoscrittore.setDescrizione("");

        Calendar dataInizio = new GregorianCalendar();
        dataInizio.add(Calendar.MINUTE, 2);

        FiltroData filtroData = new FiltroData(dataInizio.getTime());
        SottoscrizioneEsterna sottoscrizione = new SottoscrizioneEsterna(sottoscrittore, categoriaEventi, ISottoscrizione.TIPO_SOTTOSCRIZIONE_CONSEGNA, filtroData);
        DAOSottoscrizioneEsternaFacade.aggiungiSottoscrizioneEsterna(categoriaEventi, sottoscrittore, sottoscrizione);

        DAOUtilHibernate.commit();

    }

    public void testEliminazione16() throws Exception {
        try {
            logger.info("TEST - 16");
            logger.info("Sottoscrittore esistente - Categoria eventi esterna esistente confermata attiva - Nessun altra sottoscrizione per la categoria");

            String sms = "<?xml version='1.0' encoding='utf-8'?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ge=\"http://ge.freesbee.unibas.it/\"><soapenv:Header/><soapenv:Body><ge:eliminaSottoscrizione>";
            sms += "<sottoscrittore><descrizione/><nome>" + DAOMock.NOME_SOGGETTO_M + "</nome><tipo>" + DAOMock.TIPO_SOGGETTO_SPC + "</tipo></sottoscrittore>";
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

    public void testEliminazione16Dopo() throws Exception {
        try {
            logger.info("TEST - 16");
            logger.info("Dopo");

            DAOUtilHibernate.beginTransaction();

            CategoriaEventiEsterna categoriaEventiEsterna = DAOCategoriaEventiEsternaFacade.verificaEsistenzaCategoriaEventiEsternaAttavia(DAOMock.CATEGORIA_EVENTI_ESTERNA_NON_CONFERMATA_ATTIVA_6);
            IDAOSottoscrittore daoSottoscrittore = new DAOSottoscrittoreHibernate();
            Sottoscrittore sottoscrittore = daoSottoscrittore.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_M);
            assertNull(sottoscrittore);
            assertEquals(0, categoriaEventiEsterna.getListaSottoscrizioni().size());

            DAOUtilHibernate.commit();



        } catch (Exception ex) {
            logger.error(ex.getMessage());
            DAOUtilHibernate.rollback();
            Assert.fail(ex.getMessage());
        }
    }

    public void testEliminazione16VerificaComunicazioneEsterna1() throws Exception {
        try {
            logger.info("TEST - 16");
            logger.info("Verifica Comunicazione Esterna richiesta eliminizazione sottoscrizione");
            Thread.currentThread().sleep(5000);
            //Comunicazione inviata
            org.jdom.Document doc = XmlJDomUtil.caricaXML(DAOMock.COMUNICAZIONE_ESTERNA_1, false);
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

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        resultEndpoint = (MockEndpoint) resolveMandatoryEndpoint("mock:result");
    }

    @Override
    protected RouteBuilder createRouteBuilder() {
        return new RouteBuilder() {

            public void configure() {
                from("direct:start").process(new ProcessorHibernateBegin()).process(new ProcessorSOAPReader()).process(new ProcessorGestioneSottoscrizioni(DAOMock.CATEGORIA_EVENTI_ESTERNA_NON_CONFERMATA_ATTIVA_6, new DAOCategoriaEventiInternaHibernate(), new DAOCategoriaEventiEsternaHibernate(), new DAOSottoscrittoreHibernate(), new DAOPubblicatoreInternoHibernate(), new DAOPubblicatoreEsternoHibernate(), new DAOGestoreEventiHibernate())).process(new ProcessorHibernateCommit()).to("mock:result");
            }
        };

    }
}

