package test.it.unibas.freesbee.ge.comunicazione.d;

import it.unibas.freesbee.ge.messaggi.XmlJDomUtil;
import it.unibas.freesbee.ge.modello.CategoriaEventiInterna;
import it.unibas.freesbee.ge.modello.FiltroData;
import it.unibas.freesbee.ge.modello.FiltroPubblicatoreInterno;
import it.unibas.freesbee.ge.modello.ICategoriaEventi;
import it.unibas.freesbee.ge.modello.ISottoscrizione;
import it.unibas.freesbee.ge.modello.PubblicatoreInterno;
import it.unibas.freesbee.ge.modello.Sottoscrittore;
import it.unibas.freesbee.ge.modello.SottoscrizioneInterna;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiInterna;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreInterno;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrittore;
import it.unibas.freesbee.ge.persistenza.facade.DAOCategoriaEventiInternaFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOPubblicatoreInternoFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOSottoscrizioneInternaFacade;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOUtilHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCategoriaEventiInternaHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOEventoPubblicatoInternoHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOPubblicatoreInternoHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOSottoscrittoreHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOSottoscrizioneInternaHibernate;
import it.unibas.freesbee.ge.processor.ProcessorGestionePubblicatori;
import it.unibas.freesbee.ge.processor.ProcessorHibernateBegin;
import it.unibas.freesbee.ge.processor.ProcessorHibernateCommit;
import it.unibas.freesbee.ge.processor.ProcessorSOAPReader;
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
import test.it.unibas.freesbee.ge.dao.DAOMock;

public class Eliminazione1 extends ContextTestSupport {

    protected Log logger = LogFactory.getLog(this.getClass());
    protected MockEndpoint resultEndpoint;

    public void testEliminazione1Init() throws Exception {
        logger.info("TEST - 1");
        logger.info("Init");

        Thread.currentThread().sleep(5000);

        FileWriter f = new FileWriter(DAOMock.COMUNICAZIONE_ESTERNA);
        String s = " ";
        f.append(s.subSequence(0, s.length()));
        f.flush();


        f = new FileWriter(DAOMock.CONSEGNA);
        s = " ";
        f.append(s.subSequence(0, s.length()));
        f.flush();

        f = new FileWriter(DAOMock.CONSEGNA_1);
        s = " ";
        f.append(s.subSequence(0, s.length()));
        f.flush();


        DAOUtilHibernate.beginTransaction();
        IDAOCategoriaEventiInterna daoCategoriaEventiInterna = new DAOCategoriaEventiInternaHibernate();
        PubblicatoreInterno pubblicatoreA = null;
        PubblicatoreInterno pubblicatoreB = null;
        Sottoscrittore sottoscrittoreG = null;
        Sottoscrittore sottoscrittoreH = null;
        SottoscrizioneInterna sottoscrizioneG = null;
        SottoscrizioneInterna sottoscrizioneH = null;
        FiltroData filtroDataG = null;
        FiltroData filtroDataH = null;

        Calendar date = new GregorianCalendar();
        date.add(Calendar.MINUTE, 3);

        CategoriaEventiInterna categoiraEventi = daoCategoriaEventiInterna.findByNome(DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_2);

        pubblicatoreA = new PubblicatoreInterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_A);
        pubblicatoreB = new PubblicatoreInterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_B);
        DAOPubblicatoreInternoFacade.aggiungiPubblicatoreInterno(categoiraEventi, pubblicatoreA);
        DAOPubblicatoreInternoFacade.aggiungiPubblicatoreInterno(categoiraEventi, pubblicatoreB);


        sottoscrittoreG = new Sottoscrittore(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_G);
        sottoscrittoreH = new Sottoscrittore(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_H);
        filtroDataG = new FiltroData(date.getTime());
        filtroDataH = new FiltroData(date.getTime());
        sottoscrizioneG = new SottoscrizioneInterna(sottoscrittoreG, categoiraEventi, ISottoscrizione.TIPO_SOTTOSCRIZIONE_CONSEGNA, filtroDataG);
        sottoscrizioneG.getListaFiltroPublicatore().add(new FiltroPubblicatoreInterno(pubblicatoreA, sottoscrizioneG));
        sottoscrizioneH = new SottoscrizioneInterna(sottoscrittoreH, categoiraEventi, ISottoscrizione.TIPO_SOTTOSCRIZIONE_NOTIFICA, filtroDataH);
        sottoscrizioneH.getListaFiltroPublicatore().add(new FiltroPubblicatoreInterno(pubblicatoreA, sottoscrizioneH));
        sottoscrizioneH.getListaFiltroPublicatore().add(new FiltroPubblicatoreInterno(pubblicatoreB, sottoscrizioneH));
        DAOSottoscrizioneInternaFacade.aggiungiSottoscrizioneInterna(categoiraEventi, sottoscrittoreG, sottoscrizioneG);
        DAOSottoscrizioneInternaFacade.aggiungiSottoscrizioneInterna(categoiraEventi, sottoscrittoreH, sottoscrizioneH);

        categoiraEventi = daoCategoriaEventiInterna.findByNome(ICategoriaEventi.GE_CONTROL_PROTOCOL);
        Sottoscrittore sottoscrittoreS = new Sottoscrittore(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_S);
        date = new GregorianCalendar();
        date.add(Calendar.SECOND, 5);
        FiltroData filtroDataS = new FiltroData(date.getTime());
        SottoscrizioneInterna sottoscrizioneS = new SottoscrizioneInterna(sottoscrittoreS, categoiraEventi, ISottoscrizione.TIPO_SOTTOSCRIZIONE_CONSEGNA, filtroDataS);
        DAOSottoscrizioneInternaFacade.aggiungiSottoscrizioneInterna(categoiraEventi, sottoscrittoreS, sottoscrizioneS);

        DAOUtilHibernate.commit();


    }

    public void testEliminazione1() throws Exception {
        try {

            logger.info("TEST - 1");
            logger.info("Eliminazione di un pubblicatore con rispettivi filtri ed eliminazione di sottoscrizioni");

            Thread.currentThread().sleep(15000);
            DAOUtilHibernate.beginTransaction();

            String sms = "<?xml version='1.0' encoding='utf-8'?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ge=\"http://ge.freesbee.unibas.it/\"><soapenv:Header/><soapenv:Body><ge:eliminaPubblicatore>";
            sms += "<pubblicatore><descrizione/><nome>" + DAOMock.NOME_SOGGETTO_A + "</nome><tipo>" + DAOMock.TIPO_SOGGETTO_SPC + "</tipo></pubblicatore>";
            sms += "</ge:eliminaPubblicatore></soapenv:Body></soapenv:Envelope>";

            sendBody("direct:start", sms);


            Exchange exchange = resultEndpoint.getReceivedExchanges().get(0);
            Message messaggio = (Message) exchange.getIn();

            resultEndpoint.assertIsSatisfied();


        } catch (Exception ex) {
            logger.error(ex.getMessage());
            DAOUtilHibernate.rollback();
            Assert.fail("L'eliminazione del pubblicatore ha lanciato eccezione");

        }
    }

    public void testEliminazione1Verifica() throws Exception {
        try {
            logger.info("TEST - 1");
            logger.info("Verifica eliminazione pubblicatore");

            DAOUtilHibernate.beginTransaction();
            CategoriaEventiInterna categoriaEventiInterna = DAOCategoriaEventiInternaFacade.verificaEsistenzaCategoriaEventiInternaAttavia(DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_2);

            IDAOPubblicatoreInterno daoPubblicatoreInterno = new DAOPubblicatoreInternoHibernate();
            PubblicatoreInterno pubblicatoreInterno = daoPubblicatoreInterno.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_A);
            assertNull(pubblicatoreInterno);

            IDAOSottoscrittore daoSottoscrittore = new DAOSottoscrittoreHibernate();
            Sottoscrittore sottoscrittore = daoSottoscrittore.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_G);
            assertNull(sottoscrittore);

            sottoscrittore = daoSottoscrittore.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_H);
            assertNotNull(sottoscrittore);

            IDAOCategoriaEventiInterna daoCategoriaEventiInterna = new DAOCategoriaEventiInternaHibernate();
            SottoscrizioneInterna sottoscrizione = daoCategoriaEventiInterna.findByCategoriaEventiSottoscrittore(categoriaEventiInterna, sottoscrittore);
            assertEquals(ISottoscrizione.TIPO_SOTTOSCRIZIONE_NOTIFICA, sottoscrizione.getTipoSottoscrizione());
            assertEquals(1, sottoscrizione.getListaFiltroPublicatore().size());


            PubblicatoreInterno soggettoPubblicatore = daoPubblicatoreInterno.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_B);
            DAOPubblicatoreInternoFacade.verificaEsistenzaPubblicatoreInterno(categoriaEventiInterna, soggettoPubblicatore);

            PubblicatoreInterno pubblicatore = sottoscrizione.getListaFiltroPublicatore().get(0).getPubblicatore();
            assertTrue(pubblicatore.compareTo(soggettoPubblicatore) == 0);


            DAOUtilHibernate.commit();

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            DAOUtilHibernate.rollback();
            Assert.fail(ex.getMessage());
        }
    }

    public void testEliminazione1VerificaComunicazioneEsterna() throws Exception {
        try {
            logger.info("TEST - 1");
            logger.info("Verifica Comunicazione Esterna eliminazione pubblicatore");
            Thread.currentThread().sleep(10000);
            //Comunicazione inviata
            org.jdom.Document doc = XmlJDomUtil.caricaXML(DAOMock.COMUNICAZIONE_ESTERNA, false);
            String s = XmlJDomUtil.convertiDocumentToString(doc);

            //Comunicazione di test
            URL url = this.getClass().getResource("/dati/d/test1Esterno.xml");
            doc = XmlJDomUtil.caricaXML(url.getFile(), false);
            String m = XmlJDomUtil.convertiDocumentToString(doc);


            logger.debug("Comunicazione inviata: ");
            logger.debug(XmlJDomUtil.formattaXML(s));
            logger.debug("Comunicazione di test:");
            logger.debug(XmlJDomUtil.formattaXML(m));

            assertEquals(s, m);

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            Assert.fail(ex.getMessage());
        }
    }

    public void testEliminazione1VerificaComunicazioneInterna() throws Exception {
        try {
            logger.info("TEST - 1");
            logger.info("Verifica Comunicazione Interna eliminazione pubblicatore");
            Thread.currentThread().sleep(5000);

            //Comunicazione inviata
            org.jdom.Document doc = XmlJDomUtil.caricaXML(DAOMock.CONSEGNA, false);
            String s = XmlJDomUtil.convertiDocumentToString(doc);

            //Comunicazione di test
            URL url = this.getClass().getResource("/dati/d/test1Interno.xml");
            doc = XmlJDomUtil.caricaXML(url.getFile(), false);
            String m = XmlJDomUtil.convertiDocumentToString(doc);


            logger.debug("Comunicazione inviata: ");
            logger.debug(XmlJDomUtil.formattaXML(s));
            logger.debug("Comunicazione di test:");
            logger.debug(XmlJDomUtil.formattaXML(m));


            assertEquals(s, m);

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            Assert.fail(ex.getMessage());
        }
    }

    public void testEliminazione1VerificaComunicazioneInterna1() throws Exception {
        try {
            logger.info("TEST - 1");
            logger.info("Verifica Comunicazione Interna eliminazione sottoscrizione");
            Thread.currentThread().sleep(5000);

            //Comunicazione inviata
            org.jdom.Document doc = XmlJDomUtil.caricaXML(DAOMock.CONSEGNA_1, false);
            String s = XmlJDomUtil.convertiDocumentToString(doc);

            //Comunicazione di test
            URL url = this.getClass().getResource("/dati/d/test1.xml");
            doc = XmlJDomUtil.caricaXML(url.getFile(), false);
            String m = XmlJDomUtil.convertiDocumentToString(doc);


            logger.debug("Comunicazione inviata: ");
            logger.debug(XmlJDomUtil.formattaXML(s));
            logger.debug("Comunicazione di test:");
            logger.debug(XmlJDomUtil.formattaXML(m));


            assertEquals(s, m);

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
                from("direct:start").process(new ProcessorHibernateBegin()).process(new ProcessorSOAPReader()).process(new ProcessorGestionePubblicatori(DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_2, new DAOPubblicatoreInternoHibernate(), new DAOCategoriaEventiInternaHibernate(), new DAOSottoscrizioneInternaHibernate(), new DAOEventoPubblicatoInternoHibernate())).process(new ProcessorHibernateCommit()).to("mock:result");
            }
        };

    }
}

