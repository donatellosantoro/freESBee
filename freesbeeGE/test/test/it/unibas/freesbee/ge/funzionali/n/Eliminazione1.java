package test.it.unibas.freesbee.ge.funzionali.n;

import it.unibas.freesbee.ge.modello.CategoriaEventiInterna;
import it.unibas.freesbee.ge.modello.FiltroData;
import it.unibas.freesbee.ge.modello.FiltroPubblicatoreInterno;
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
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCategoriaEventiInternaHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOEventoPubblicatoInternoHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOPubblicatoreInternoHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOSottoscrittoreHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOSottoscrizioneInternaHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOUtilHibernate;
import it.unibas.freesbee.ge.processor.ProcessorGestionePubblicatori;
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

public class Eliminazione1 extends ContextTestSupport {

    protected Log logger = LogFactory.getLog(this.getClass());
    protected MockEndpoint resultEndpoint;

    public void testEliminazione1Init() throws Exception {
        logger.info("TEST - 1");
        logger.info("Init");

        DAOUtilHibernate.beginTransaction();
        IDAOPubblicatoreInterno daoPubblicatoreInterno = new DAOPubblicatoreInternoHibernate();

        PubblicatoreInterno pubblicatore = daoPubblicatoreInterno.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_A);
        assertNull(pubblicatore);

        DAOMock.inserisciPubblicatoreInterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_A, DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_2);
        DAOMock.inserisciPubblicatoreInterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_A, DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_3);

        pubblicatore = daoPubblicatoreInterno.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_A);

        CategoriaEventiInterna categoriaEventi2 = DAOCategoriaEventiInternaFacade.verificaEsistenzaCategoriaEventiInternaAttavia(DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_2);
        DAOPubblicatoreInternoFacade.verificaEsistenzaPubblicatoreInterno(categoriaEventi2, pubblicatore);

        CategoriaEventiInterna categoriaEventi3 = DAOCategoriaEventiInternaFacade.verificaEsistenzaCategoriaEventiInternaAttavia(DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_3);
        DAOPubblicatoreInternoFacade.verificaEsistenzaPubblicatoreInterno(categoriaEventi3, pubblicatore);

        IDAOSottoscrittore daoSottoscrittore = new DAOSottoscrittoreHibernate();
        Sottoscrittore sottoscrittore = daoSottoscrittore.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_M);
        assertNull(sottoscrittore);

        sottoscrittore = new Sottoscrittore(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_M);

        Calendar dataInizio = new GregorianCalendar();
        dataInizio.add(Calendar.MINUTE, 2);

        FiltroData filtroData2 = new FiltroData(dataInizio.getTime());
        FiltroData filtroData3 = new FiltroData(dataInizio.getTime());

        SottoscrizioneInterna sottoscrizione2 = new SottoscrizioneInterna(sottoscrittore, categoriaEventi2, ISottoscrizione.TIPO_SOTTOSCRIZIONE_CONSEGNA, filtroData2);
        FiltroPubblicatoreInterno filtroPubblicatore2 = new FiltroPubblicatoreInterno(pubblicatore, sottoscrizione2);
        sottoscrizione2.getListaFiltroPublicatore().add(filtroPubblicatore2);

        DAOSottoscrizioneInternaFacade.aggiungiSottoscrizioneInterna(categoriaEventi2, sottoscrittore, sottoscrizione2);

        SottoscrizioneInterna sottoscrizione3 = new SottoscrizioneInterna(sottoscrittore, categoriaEventi3, ISottoscrizione.TIPO_SOTTOSCRIZIONE_CONSEGNA, filtroData3);
        FiltroPubblicatoreInterno filtroPubblicatore3 = new FiltroPubblicatoreInterno(pubblicatore, sottoscrizione3);
        sottoscrizione3.getListaFiltroPublicatore().add(filtroPubblicatore3);

        DAOSottoscrizioneInternaFacade.aggiungiSottoscrizioneInterna(categoriaEventi3, sottoscrittore, sottoscrizione3);

        DAOUtilHibernate.commit();

    }

    public void testEliminazione1() throws Exception {
        try {

            logger.info("TEST - 1");
            logger.info("Pubblicatore  esistente - Categoria eventi interna esistente nattiva");


            String sms = "<?xml version='1.0' encoding='utf-8'?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ge=\"http://ge.freesbee.unibas.it/\"><soapenv:Header/><soapenv:Body><ge:eliminaPubblicatore>";
            sms += "<pubblicatore><descrizione/><nome>" + DAOMock.NOME_SOGGETTO_A + "</nome><tipo>" + DAOMock.TIPO_SOGGETTO_SPC + "</tipo></pubblicatore>";
            sms += "</ge:eliminaPubblicatore></soapenv:Body></soapenv:Envelope>";

            sendBody("direct:start", sms);


            Exchange exchange = resultEndpoint.getReceivedExchanges().get(0);
            Message messaggio = (Message) exchange.getIn();

            resultEndpoint.assertIsSatisfied();


        } catch (Exception ex) {
            logger.error(ex.getMessage());
            Assert.fail("L'eliminazione del pubblicatore  ha lanciato eccezione");
        }
    }

    public void testEliminazione1Dopo() throws Exception {
        logger.info("TEST - 1");
        logger.info("Dopo");

        DAOUtilHibernate.beginTransaction();
        IDAOPubblicatoreInterno daoPubblicatoreInterno = new DAOPubblicatoreInternoHibernate();
        PubblicatoreInterno pubblicatore = daoPubblicatoreInterno.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_A);
        assertNotNull(pubblicatore);

        CategoriaEventiInterna categoriaEventi2 = DAOCategoriaEventiInternaFacade.verificaEsistenzaCategoriaEventiInternaAttavia(DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_2);
        DAOPubblicatoreInternoFacade.verificaNonEsistenzaPubblicatoreInterno(categoriaEventi2, pubblicatore);

        CategoriaEventiInterna categoriaEventi3 = DAOCategoriaEventiInternaFacade.verificaEsistenzaCategoriaEventiInternaAttavia(DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_3);
        DAOPubblicatoreInternoFacade.verificaEsistenzaPubblicatoreInterno(categoriaEventi3, pubblicatore);

        IDAOSottoscrittore daoSottoscrittore = new DAOSottoscrittoreHibernate();
        Sottoscrittore sottoscrittore = daoSottoscrittore.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_M);
        assertNotNull(sottoscrittore);

        IDAOCategoriaEventiInterna daoCategoriaEventi = new DAOCategoriaEventiInternaHibernate();

        SottoscrizioneInterna sottoscrizione2 = daoCategoriaEventi.findByCategoriaEventiSottoscrittore(categoriaEventi2, sottoscrittore);
        assertNull(sottoscrizione2);

        SottoscrizioneInterna sottoscrizione3 = daoCategoriaEventi.findByCategoriaEventiSottoscrittore(categoriaEventi3, sottoscrittore);
        assertNotNull(sottoscrizione3);
        assertEquals(1, sottoscrizione3.getListaFiltroPublicatore().size());
        assertTrue(pubblicatore.compareTo(sottoscrizione3.getListaFiltroPublicatore().get(0).getPubblicatore()) == 0);
        DAOUtilHibernate.commit();

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

