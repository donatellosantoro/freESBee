package test.it.unibas.freesbee.ge.comunicazione.g;

import it.unibas.freesbee.ge.messaggi.XmlJDomUtil;
import it.unibas.freesbee.ge.modello.CategoriaEventiEsterna;
import it.unibas.freesbee.ge.modello.FiltroData;
import it.unibas.freesbee.ge.modello.FiltroPubblicatoreEsterno;
import it.unibas.freesbee.ge.modello.GestoreEventi;
import it.unibas.freesbee.ge.modello.ISottoscrizione;
import it.unibas.freesbee.ge.modello.PubblicatoreEsterno;
import it.unibas.freesbee.ge.modello.Sottoscrittore;
import it.unibas.freesbee.ge.modello.SottoscrizioneEsterna;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiEsterna;
import it.unibas.freesbee.ge.persistenza.IDAOGestoreEventi;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreEsterno;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrittore;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrizioneEsterna;
import it.unibas.freesbee.ge.persistenza.facade.DAOCategoriaEventiEsternaFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOPubblicatoreEsternoFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOSottoscrizioneEsternaFacade;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCategoriaEventiEsternaHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCategoriaEventiInternaHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCostanti;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOGestoreEventiHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOPubblicatoreEsternoHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOPubblicatoreInternoHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOSottoscrittoreHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOSottoscrizioneEsternaHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOUtilHibernate;
import it.unibas.freesbee.ge.processor.ProcessorGestioneSottoscrizioni;
import it.unibas.freesbee.ge.processor.ProcessorHibernateBegin;
import it.unibas.freesbee.ge.processor.ProcessorHibernateCommit;
import it.unibas.freesbee.ge.processor.ProcessorSOAPReader;
import it.unibas.freesbee.ge.ws.gestoreeventi.ProcessorInviaRichiestaInformazioniPubblicatori;
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

public class Conferma26 extends ContextTestSupport {

    protected Log logger = LogFactory.getLog(this.getClass());
    protected MockEndpoint resultEndpoint;

    public void testConferma26Init() throws Exception {
        logger.info("TEST - 26");
        logger.info("Init");

        DAOUtilHibernate.beginTransaction();

        IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna = new DAOCategoriaEventiEsternaHibernate();

        CategoriaEventiEsterna categoriaEventi = DAOCategoriaEventiEsternaFacade.verificaEsistenzaCategoriaEventiEsternaAttavia(DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_9);

        Sottoscrittore sottoscrittore = new Sottoscrittore();
        sottoscrittore.setTipo(DAOMock.TIPO_SOGGETTO_SPC);
        sottoscrittore.setNome(DAOMock.NOME_SOGGETTO_A);

        PubblicatoreEsterno pubblicatore = new PubblicatoreEsterno();
        pubblicatore.setTipo(DAOMock.TIPO_SOGGETTO_SPC);
        pubblicatore.setNome(DAOMock.NOME_SOGGETTO_B);

        IDAOPubblicatoreEsterno daoPubblicatoreEsterno = new DAOPubblicatoreEsternoHibernate();
        daoPubblicatoreEsterno.makePersistent(pubblicatore);

        Calendar date = new GregorianCalendar();
        date.add(Calendar.MINUTE, 2);
        FiltroData filtroData = new FiltroData(date.getTime());

        SottoscrizioneEsterna sottoscrizioneEsterna = new SottoscrizioneEsterna(sottoscrittore, categoriaEventi, ISottoscrizione.TIPO_SOTTOSCRIZIONE_CONSEGNA, filtroData);
        FiltroPubblicatoreEsterno filtroPubblicatore = new FiltroPubblicatoreEsterno(pubblicatore, sottoscrizioneEsterna);
        sottoscrizioneEsterna.getListaFiltroPublicatore().add(filtroPubblicatore);

        DAOSottoscrizioneEsternaFacade.aggiungiSottoscrizioneEsterna(categoriaEventi, sottoscrittore, sottoscrizioneEsterna);

        assertTrue(categoriaEventi.isAttiva());
        assertFalse(categoriaEventi.isInAttesa());

        sottoscrizioneEsterna = daoCategoriaEventiEsterna.findByCategoriaEventiSottoscrittore(categoriaEventi, sottoscrittore);

        date.add(Calendar.MINUTE, -2);
        sottoscrizioneEsterna.setScadenzaAttesa(date.getTime());
        IDAOSottoscrizioneEsterna daoSottoscrizioneEsterna = new DAOSottoscrizioneEsternaHibernate();
        daoSottoscrizioneEsterna.makePersistent(sottoscrizioneEsterna);

        DAOSottoscrizioneEsternaFacade.verificaEsistenzaSottoscrittoreSottoscrizioneEsterna(categoriaEventi, sottoscrittore);
        assertFalse(DAOSottoscrizioneEsternaFacade.isInAttesaConfermaCategoriaEventi(sottoscrizioneEsterna));
        assertTrue(DAOSottoscrizioneEsternaFacade.isInAttesaConfermaPubblicatori(sottoscrizioneEsterna));

        DAOUtilHibernate.commit();
    }

    public void testConferma26Prima() throws Exception {
        try {
            logger.info("TEST - 26");
            logger.info("Prima");

            Calendar date = new GregorianCalendar();
            date.add(Calendar.MINUTE, 2);

            String sms = "<?xml version='1.0' encoding='utf-8'?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ge=\"http://ge.freesbee.unibas.it/\"><soapenv:Header/><soapenv:Body><ge:aggiungiSottoscrizione><sottoscrizione>";
            sms += "<filtroData><dataFine/><dataInizio>" + FiltroData.convertiDateToXmlDate(date.getTime()) + "</dataInizio></filtroData>";
            sms += "<listaFiltroPublicatore><pubblicatore><descrizione/><nome>" + DAOMock.NOME_SOGGETTO_C + "</nome><tipo>" + DAOMock.TIPO_SOGGETTO_SPC + "</tipo></pubblicatore></listaFiltroPublicatore>";
            sms += "<sottoscrittore><descrizione/><nome>" + DAOMock.NOME_SOGGETTO_D + "</nome><tipo>" + DAOMock.TIPO_SOGGETTO_SPC + "</tipo></sottoscrittore>";
            sms += "<tipoSottoscrizione>" + ISottoscrizione.TIPO_SOTTOSCRIZIONE_NOTIFICA + "</tipoSottoscrizione>";
            sms += "</sottoscrizione></ge:aggiungiSottoscrizione></soapenv:Body></soapenv:Envelope>";

            sendBody("direct:start", sms);

            Exchange exchange = resultEndpoint.getReceivedExchanges().get(0);
            Message messaggio = (Message) exchange.getIn();

            resultEndpoint.assertIsSatisfied();


        } catch (Exception ex) {
            logger.error(ex.getMessage());
            DAOUtilHibernate.rollback();
            Assert.fail(ex.getMessage());

        }
    }

    public void testConferma26VerificaPrima() throws Exception {
        try {
            logger.info("TEST - 26");
            logger.info("Verifica inserimento sottoscrizioni da confermare");
            Thread.currentThread().sleep(5000);

            DAOUtilHibernate.beginTransaction();

            IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna = new DAOCategoriaEventiEsternaHibernate();
            IDAOPubblicatoreEsterno daoPubblicatoreEsterno = new DAOPubblicatoreEsternoHibernate();
            IDAOSottoscrittore daoSottoscrittore = new DAOSottoscrittoreHibernate();

            CategoriaEventiEsterna catgoriaEventiEsterna9 = DAOCategoriaEventiEsternaFacade.verificaEsistenzaCategoriaEventiEsternaAttavia(DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_9);
            assertFalse(catgoriaEventiEsterna9.isInAttesa());
            assertTrue(catgoriaEventiEsterna9.isAttiva());

            Sottoscrittore sottoscrittoreA = daoSottoscrittore.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_A);

            DAOSottoscrizioneEsternaFacade.verificaEsistenzaSottoscrittoreSottoscrizioneEsterna(catgoriaEventiEsterna9, sottoscrittoreA);

            SottoscrizioneEsterna sottoscrizioneA = daoCategoriaEventiEsterna.findByCategoriaEventiSottoscrittore(catgoriaEventiEsterna9, sottoscrittoreA);
            assertEquals(ISottoscrizione.TIPO_SOTTOSCRIZIONE_CONSEGNA, sottoscrizioneA.getTipoSottoscrizione());
            assertFalse(DAOSottoscrizioneEsternaFacade.isInAttesaConfermaCategoriaEventi(sottoscrizioneA));
            assertTrue(DAOSottoscrizioneEsternaFacade.isInAttesaConfermaPubblicatori(sottoscrizioneA));
            assertEquals(1, sottoscrizioneA.getListaFiltroPublicatore().size());
            assertNotNull(sottoscrizioneA.getScadenzaAttesa());


            PubblicatoreEsterno pubblicatoreB = daoPubblicatoreEsterno.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_B);
            assertNotNull(pubblicatoreB);
            assertTrue(sottoscrizioneA.getListaFiltroPublicatore().get(0).getPubblicatore().compareTo(pubblicatoreB) == 0);


            IDAOGestoreEventi daoGestoreEventi = new DAOGestoreEventiHibernate();
            GestoreEventi gestoreEventi = daoGestoreEventi.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_GE);
            assertNotNull(gestoreEventi);

            PubblicatoreEsterno soggettoPubblicatore = daoPubblicatoreEsterno.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_GE);
            assertNotNull(soggettoPubblicatore);

            CategoriaEventiEsterna catgoriaEventiEsterna10 = DAOCategoriaEventiEsternaFacade.verificaEsistenzaCategoriaEventiEsternaAttavia(DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_10);
            assertFalse(catgoriaEventiEsterna10.isInAttesa());
            assertTrue(catgoriaEventiEsterna10.isAttiva());

            Sottoscrittore sottoscrittoreD = daoSottoscrittore.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_D);

            DAOSottoscrizioneEsternaFacade.verificaEsistenzaSottoscrittoreSottoscrizioneEsterna(catgoriaEventiEsterna10, sottoscrittoreD);

            SottoscrizioneEsterna sottoscrizioneD = daoCategoriaEventiEsterna.findByCategoriaEventiSottoscrittore(catgoriaEventiEsterna10, sottoscrittoreD);
            assertEquals(ISottoscrizione.TIPO_SOTTOSCRIZIONE_NOTIFICA, sottoscrizioneD.getTipoSottoscrizione());
            assertFalse(DAOSottoscrizioneEsternaFacade.isInAttesaConfermaCategoriaEventi(sottoscrizioneD));
            assertTrue(DAOSottoscrizioneEsternaFacade.isInAttesaConfermaPubblicatori(sottoscrizioneD));
            assertEquals(1, sottoscrizioneD.getListaFiltroPublicatore().size());
            assertNotNull(sottoscrizioneD.getScadenzaAttesa());

            PubblicatoreEsterno pubblicatoreC = daoPubblicatoreEsterno.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_C);
            assertNotNull(pubblicatoreC);
            assertTrue(sottoscrizioneD.getListaFiltroPublicatore().get(0).getPubblicatore().compareTo(pubblicatoreC) == 0);

            assertTrue(DAOPubblicatoreEsternoFacade.isPubblicatoreEsternoDaConfermare(catgoriaEventiEsterna9, pubblicatoreB));

            assertFalse(DAOPubblicatoreEsternoFacade.isPubblicatoreEsternoDaConfermare(catgoriaEventiEsterna10, pubblicatoreB));

            DAOUtilHibernate.commit();

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            DAOUtilHibernate.rollback();
            Assert.fail(ex.getMessage());
        }
    }

    public void testConferma26() throws Exception {
        logger.info("TEST - 26");
        logger.info("Si è Ricevuta Confemra Pubblicatori per la cateogria di eventi non confermata con un pubblicatore non è pubblicatore esterno da confermare per la categoria");
        try {
            //Carico il messaggio che verebbe generato dal GE
            URL url = this.getClass().getResource("/dati/g/test26.xml");
            Document doc = XmlJDomUtil.caricaXML(url.getFile(), false);
            String m = XmlJDomUtil.convertiDocumentToString(doc);

            ClientPD clientPD = new ClientPD();

            String indirizzo = "http://" + ConfigurazioneStatico.getInstance().getWebservicesIndirizzo() + ":" + ConfigurazioneStatico.getInstance().getWebservicesPort() + DAOCostanti.URL_WSDL_CONSEGNA_MESSAGGI;
            logger.info("url: " + indirizzo);
            logger.debug(m);

            clientPD.invocaPortaDelegata(indirizzo, m);


        } catch (Exception ex) {
            logger.error(ex.getMessage());
            Assert.fail("La conferma dei pubblicatori ha lanciato eccezione");
        }
    }

    public void testConferma26VerificaDopo() throws Exception {
        try {
            logger.info("TEST - 26");
            logger.info("Verifica Conferma Sottoscrizioni");
            Thread.currentThread().sleep(10000);

             DAOUtilHibernate.beginTransaction();

            IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna = new DAOCategoriaEventiEsternaHibernate();
            IDAOPubblicatoreEsterno daoPubblicatoreEsterno = new DAOPubblicatoreEsternoHibernate();
            IDAOSottoscrittore daoSottoscrittore = new DAOSottoscrittoreHibernate();

            CategoriaEventiEsterna catgoriaEventiEsterna9 = DAOCategoriaEventiEsternaFacade.verificaEsistenzaCategoriaEventiEsternaAttavia(DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_9);
            assertFalse(catgoriaEventiEsterna9.isInAttesa());
            assertTrue(catgoriaEventiEsterna9.isAttiva());

            Sottoscrittore sottoscrittoreA = daoSottoscrittore.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_A);

            DAOSottoscrizioneEsternaFacade.verificaEsistenzaSottoscrittoreSottoscrizioneEsterna(catgoriaEventiEsterna9, sottoscrittoreA);

            SottoscrizioneEsterna sottoscrizioneA = daoCategoriaEventiEsterna.findByCategoriaEventiSottoscrittore(catgoriaEventiEsterna9, sottoscrittoreA);
            assertEquals(ISottoscrizione.TIPO_SOTTOSCRIZIONE_CONSEGNA, sottoscrizioneA.getTipoSottoscrizione());
            assertFalse(DAOSottoscrizioneEsternaFacade.isInAttesaConfermaCategoriaEventi(sottoscrizioneA));
            assertTrue(DAOSottoscrizioneEsternaFacade.isInAttesaConfermaPubblicatori(sottoscrizioneA));
            assertEquals(1, sottoscrizioneA.getListaFiltroPublicatore().size());
            assertNotNull(sottoscrizioneA.getScadenzaAttesa());


            PubblicatoreEsterno pubblicatoreB = daoPubblicatoreEsterno.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_B);
            assertNotNull(pubblicatoreB);
            assertTrue(sottoscrizioneA.getListaFiltroPublicatore().get(0).getPubblicatore().compareTo(pubblicatoreB) == 0);


            IDAOGestoreEventi daoGestoreEventi = new DAOGestoreEventiHibernate();
            GestoreEventi gestoreEventi = daoGestoreEventi.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_GE);
            assertNotNull(gestoreEventi);

            PubblicatoreEsterno soggettoPubblicatore = daoPubblicatoreEsterno.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_GE);
            assertNotNull(soggettoPubblicatore);

            CategoriaEventiEsterna catgoriaEventiEsterna10 = DAOCategoriaEventiEsternaFacade.verificaEsistenzaCategoriaEventiEsternaAttavia(DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_10);
            assertFalse(catgoriaEventiEsterna10.isInAttesa());
            assertTrue(catgoriaEventiEsterna10.isAttiva());

            Sottoscrittore sottoscrittoreD = daoSottoscrittore.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_D);

            DAOSottoscrizioneEsternaFacade.verificaEsistenzaSottoscrittoreSottoscrizioneEsterna(catgoriaEventiEsterna10, sottoscrittoreD);

            SottoscrizioneEsterna sottoscrizioneD = daoCategoriaEventiEsterna.findByCategoriaEventiSottoscrittore(catgoriaEventiEsterna10, sottoscrittoreD);
            assertEquals(ISottoscrizione.TIPO_SOTTOSCRIZIONE_NOTIFICA, sottoscrizioneD.getTipoSottoscrizione());
            assertFalse(DAOSottoscrizioneEsternaFacade.isInAttesaConfermaCategoriaEventi(sottoscrizioneD));
            assertFalse(DAOSottoscrizioneEsternaFacade.isInAttesaConfermaPubblicatori(sottoscrizioneD));
            assertEquals(1, sottoscrizioneD.getListaFiltroPublicatore().size());
            assertNull(sottoscrizioneD.getScadenzaAttesa());

            PubblicatoreEsterno pubblicatoreC = daoPubblicatoreEsterno.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_C);
            assertNotNull(pubblicatoreC);
            assertTrue(sottoscrizioneD.getListaFiltroPublicatore().get(0).getPubblicatore().compareTo(pubblicatoreC) == 0);

            assertTrue(DAOPubblicatoreEsternoFacade.isPubblicatoreEsternoDaConfermare(catgoriaEventiEsterna9, pubblicatoreB));

            assertFalse(DAOPubblicatoreEsternoFacade.isPubblicatoreEsternoDaConfermare(catgoriaEventiEsterna10, pubblicatoreB));

            DAOUtilHibernate.commit();


        } catch (Exception ex) {
            logger.error(ex.getMessage());
            DAOUtilHibernate.rollback();
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
                from("direct:start").process(new ProcessorHibernateBegin()).process(new ProcessorSOAPReader()).process(new ProcessorGestioneSottoscrizioni(DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_10, new DAOCategoriaEventiInternaHibernate(), new DAOCategoriaEventiEsternaHibernate(), new DAOSottoscrittoreHibernate(), new DAOPubblicatoreInternoHibernate(), new DAOPubblicatoreEsternoHibernate(), new DAOGestoreEventiHibernate())).process(new ProcessorHibernateCommit()).process(new ProcessorHibernateBegin()).process(new ProcessorInviaRichiestaInformazioniPubblicatori()).process(new ProcessorHibernateCommit()).to("mock:result");
            }
        };

    }
}

