package test.it.unibas.freesbee.ge.comunicazione.d;

import it.unibas.freesbee.ge.messaggi.XmlJDomUtil;
import it.unibas.freesbee.ge.modello.CategoriaEventiInterna;
import it.unibas.freesbee.ge.modello.FiltroData;
import it.unibas.freesbee.ge.modello.ISottoscrizione;
import it.unibas.freesbee.ge.modello.PubblicatoreInterno;
import it.unibas.freesbee.ge.modello.Sottoscrittore;
import it.unibas.freesbee.ge.modello.SottoscrizioneInterna;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiInterna;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreInterno;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrittore;
import it.unibas.freesbee.ge.persistenza.facade.DAOCategoriaEventiInternaFacade;
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

public class Eliminazione2 extends ContextTestSupport {

    protected Log logger = LogFactory.getLog(this.getClass());
    protected MockEndpoint resultEndpoint;

    public void testEliminazione2Init() throws Exception {
        logger.info("TEST - 2");
        logger.info("Init");

        FileWriter f = new FileWriter(DAOMock.COMUNICAZIONE_ESTERNA);
        String s = " ";
        f.append(s.subSequence(0, s.length()));
        f.flush();

        f = new FileWriter(DAOMock.CONSEGNA);
        s = " ";
        f.append(s.subSequence(0, s.length()));
        f.flush();

    }

    public void testEliminazione2() throws Exception {
        try {
            DAOUtilHibernate.beginTransaction();
            logger.info("TEST - 2");
            logger.info("Eliminazione di un pubblicatore con rispettivi filtri ed eliminazione di sottoscrizioni");

            DAOUtilHibernate.beginTransaction();
            IDAOCategoriaEventiInterna daoCategoriaEventiInterna = new DAOCategoriaEventiInternaHibernate();
            IDAOSottoscrittore daoSottoscrittore = new DAOSottoscrittoreHibernate();

            Sottoscrittore sottoscrittoreH = null;
            SottoscrizioneInterna sottoscrizioneH = null;
            FiltroData filtroDataH = null;

            Calendar date = new GregorianCalendar();
            date.add(Calendar.MINUTE, 3);

            CategoriaEventiInterna categoiraEventi = categoiraEventi = daoCategoriaEventiInterna.findByNome(DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_3);

            sottoscrittoreH = daoSottoscrittore.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_H);
            filtroDataH = new FiltroData(date.getTime());
            sottoscrizioneH = new SottoscrizioneInterna(sottoscrittoreH, categoiraEventi, ISottoscrizione.TIPO_SOTTOSCRIZIONE_CONSEGNA, filtroDataH);
            DAOSottoscrizioneInternaFacade.aggiungiSottoscrizioneInterna(categoiraEventi, sottoscrittoreH, sottoscrizioneH);
            DAOUtilHibernate.commit();

            String sms = "<?xml version='1.0' encoding='utf-8'?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ge=\"http://ge.freesbee.unibas.it/\"><soapenv:Header/><soapenv:Body><ge:eliminaPubblicatore>";
            sms += "<pubblicatore><descrizione/><nome>" + DAOMock.NOME_SOGGETTO_B + "</nome><tipo>" + DAOMock.TIPO_SOGGETTO_SPC + "</tipo></pubblicatore>";
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

    public void testEliminazione2Verifica() throws Exception {
        try {
            logger.info("TEST - 2");
            logger.info("Verifica eliminazione pubblicatore");

            DAOUtilHibernate.beginTransaction();
            CategoriaEventiInterna categoriaEventiInterna = DAOCategoriaEventiInternaFacade.verificaEsistenzaCategoriaEventiInternaAttavia(DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_2);

            IDAOPubblicatoreInterno daoPubblicatoreInterno = new DAOPubblicatoreInternoHibernate();
            PubblicatoreInterno pubblicatoreInterno = daoPubblicatoreInterno.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_B);
            assertNull(pubblicatoreInterno);

            IDAOSottoscrittore daoSottoscrittore = new DAOSottoscrittoreHibernate();
            Sottoscrittore sottoscrittore = daoSottoscrittore.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_H);
            assertNotNull(sottoscrittore);

            DAOSottoscrizioneInternaFacade.verificaNonEsistenzaSottoscrittoreSottoscrizioneInterna(categoriaEventiInterna, sottoscrittore);

            IDAOCategoriaEventiInterna daoCategoriaEventiInterna = new DAOCategoriaEventiInternaHibernate();
            DAOSottoscrizioneInternaFacade.eliminaSottoscrizioneInterna(daoCategoriaEventiInterna.findByNome(DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_3), sottoscrittore);


            DAOUtilHibernate.commit();


        } catch (Exception ex) {
            logger.error(ex.getMessage());
            DAOUtilHibernate.rollback();
            Assert.fail(ex.getMessage());
        }
    }

    public void testEliminazione2VerificaComunicazioneEsterna() throws Exception {
        try {
            logger.info("TEST - 2");
            logger.info("Verifica Comunicazione Esterna eliminazione pubblicatore");

            //Comunicazione inviata
            org.jdom.Document doc = XmlJDomUtil.caricaXML(DAOMock.COMUNICAZIONE_ESTERNA, false);
            String s = XmlJDomUtil.convertiDocumentToString(doc);

            //Comunicazione di test
            URL url = this.getClass().getResource("/dati/d/test2Esterno.xml");
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

    public void testEliminazione2VerificaComunicazioneInterna() throws Exception {
        try {
            logger.info("TEST - 2");
            logger.info("Verifica Comunicazione Interna eliminazione pubblicatore");

            //Comunicazione inviata
            org.jdom.Document doc = XmlJDomUtil.caricaXML(DAOMock.CONSEGNA, false);
            String s = XmlJDomUtil.convertiDocumentToString(doc);

            //Comunicazione di test
            URL url = this.getClass().getResource("/dati/d/test2Interno.xml");
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

