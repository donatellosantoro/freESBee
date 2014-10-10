package test.it.unibas.freesbee.ge.gestione.c;

import it.unibas.freesbee.ge.modello.CategoriaEventiEsterna;
import it.unibas.freesbee.ge.modello.FiltroData;
import it.unibas.freesbee.ge.modello.ISottoscrizione;
import it.unibas.freesbee.ge.modello.PubblicatoreEsterno;
import it.unibas.freesbee.ge.modello.Sottoscrittore;
import it.unibas.freesbee.ge.modello.SottoscrizioneEsterna;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiEsterna;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreEsterno;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrittore;
import it.unibas.freesbee.ge.persistenza.facade.DAOCategoriaEventiEsternaFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOPubblicatoreEsternoFacade;
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

public class Inserimento21 extends ContextTestSupport {

    protected Log logger = LogFactory.getLog(this.getClass());
    protected MockEndpoint resultEndpoint;

    public void testInserimento21() throws Exception {
        try {
            logger.info("TEST - 21");
            logger.info("Sottoscrittore non esistente - Categoria eventi esterna esistente confermata attiva - Filtro Pubblicatore già confermato");

            DAOUtilHibernate.beginTransaction();
            IDAOPubblicatoreEsterno daoPubblicatoreEsterno = new DAOPubblicatoreEsternoHibernate();
            IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna = new DAOCategoriaEventiEsternaHibernate();

            PubblicatoreEsterno pubblicatore = new PubblicatoreEsterno();
            pubblicatore.setTipo(DAOMock.TIPO_SOGGETTO_SPC);
            pubblicatore.setNome(DAOMock.NOME_SOGGETTO_X);
            pubblicatore.setDescrizione("");

            CategoriaEventiEsterna categoiraEventiEsterna = daoCategoriaEventiEsterna.findByNome(DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_10);
            categoiraEventiEsterna.getListaPubblicatori().add(pubblicatore);

            pubblicatore.getListaCatgoriaEventi().add(categoiraEventiEsterna);
            daoCategoriaEventiEsterna.makePersistent(categoiraEventiEsterna);
            daoPubblicatoreEsterno.makePersistent(pubblicatore);

            DAOUtilHibernate.commit();


            Calendar date = new GregorianCalendar();
            date.add(Calendar.MINUTE, 2);

            String sms = "<?xml version='1.0' encoding='utf-8'?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ge=\"http://ge.freesbee.unibas.it/\"><soapenv:Header/><soapenv:Body><ge:aggiungiSottoscrizione><sottoscrizione>";
            sms += "<filtroData><dataFine/><dataInizio>" + FiltroData.convertiDateToXmlDate(date.getTime()) + "</dataInizio></filtroData>";
            sms += "<listaFiltroPublicatore><pubblicatore><descrizione/><nome>" + DAOMock.NOME_SOGGETTO_X + "</nome><tipo>" + DAOMock.TIPO_SOGGETTO_SPC + "</tipo></pubblicatore></listaFiltroPublicatore>";
            sms += "<sottoscrittore><descrizione/><nome>" + DAOMock.NOME_SOGGETTO_S + "</nome><tipo>" + DAOMock.TIPO_SOGGETTO_SPC + "</tipo></sottoscrittore>";
            sms += "<tipoSottoscrizione>" + ISottoscrizione.TIPO_SOTTOSCRIZIONE_NOTIFICA + "</tipoSottoscrizione>";
            sms += "</sottoscrizione></ge:aggiungiSottoscrizione></soapenv:Body></soapenv:Envelope>";

            sendBody("direct:start", sms);


            Exchange exchange = resultEndpoint.getReceivedExchanges().get(0);
            Message messaggio = (Message) exchange.getIn();

            resultEndpoint.assertIsSatisfied();


        } catch (Exception ex) {
            logger.error(ex.getMessage());
            DAOUtilHibernate.rollback();
            Assert.fail("L'inserimento della sottoscrizione ha lanciato eccezione");
        }
    }

    public void testInserimento21Verifica() throws Exception {
        try {
            logger.info("TEST - 21");
            logger.info("Verifica inserimento sottoscrizione");

            DAOUtilHibernate.beginTransaction();
            CategoriaEventiEsterna catgoriaEventiEsterna = DAOCategoriaEventiEsternaFacade.verificaEsistenzaCategoriaEventiEsternaAttavia(DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_10);
            IDAOSottoscrittore daoSottoscrittore = new DAOSottoscrittoreHibernate();
            Sottoscrittore sottoscrittore = daoSottoscrittore.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_S);
            DAOSottoscrizioneEsternaFacade.verificaEsistenzaSottoscrittoreSottoscrizioneEsterna(catgoriaEventiEsterna, sottoscrittore);
            IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna = new DAOCategoriaEventiEsternaHibernate();
            SottoscrizioneEsterna sottoscrizione = daoCategoriaEventiEsterna.findByCategoriaEventiSottoscrittore(catgoriaEventiEsterna, sottoscrittore);
            assertEquals(ISottoscrizione.TIPO_SOTTOSCRIZIONE_NOTIFICA, sottoscrizione.getTipoSottoscrizione());
            assertFalse(DAOSottoscrizioneEsternaFacade.isInAttesaConfermaCategoriaEventi(sottoscrizione));
            assertFalse(DAOSottoscrizioneEsternaFacade.isInAttesaConfermaPubblicatori(sottoscrizione));
            assertEquals(1, sottoscrizione.getListaFiltroPublicatore().size());

            IDAOPubblicatoreEsterno daoPubblicatoreEsterno = new DAOPubblicatoreEsternoHibernate();
            PubblicatoreEsterno soggettoPubblicatore = daoPubblicatoreEsterno.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_X);
            assertTrue(DAOPubblicatoreEsternoFacade.isPubblicatoreEsternoPerCategoriaEventiEsterna(catgoriaEventiEsterna, soggettoPubblicatore));

            PubblicatoreEsterno pubblicatore = sottoscrizione.getListaFiltroPublicatore().get(0).getPubblicatore();
            assertTrue(pubblicatore.compareTo(soggettoPubblicatore) == 0);
            assertFalse(DAOPubblicatoreEsternoFacade.isInAttesaPubblicatoreEsternoPerCategoriaEventi(catgoriaEventiEsterna, pubblicatore));
            assertFalse(DAOSottoscrizioneEsternaFacade.isInAttesaConfermaPubblicatori(sottoscrizione));

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
                from("direct:start").process(new ProcessorHibernateBegin()).process(new ProcessorSOAPReader()).process(new ProcessorGestioneSottoscrizioni(DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_10, new DAOCategoriaEventiInternaHibernate(), new DAOCategoriaEventiEsternaHibernate(), new DAOSottoscrittoreHibernate(), new DAOPubblicatoreInternoHibernate(), new DAOPubblicatoreEsternoHibernate(), new DAOGestoreEventiHibernate())).process(new ProcessorHibernateCommit()).to("mock:result");
            }
        };

    }
}

