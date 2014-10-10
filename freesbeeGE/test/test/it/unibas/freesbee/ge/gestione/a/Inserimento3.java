package test.it.unibas.freesbee.ge.gestione.a;

import it.unibas.freesbee.ge.modello.CategoriaEventiInterna;
import it.unibas.freesbee.ge.modello.PubblicatoreInterno;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreInterno;
import it.unibas.freesbee.ge.persistenza.facade.DAOCategoriaEventiInternaFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOPubblicatoreInternoFacade;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCategoriaEventiInternaHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOEventoPubblicatoInternoHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOPubblicatoreInternoHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOSottoscrizioneInternaHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOUtilHibernate;
import it.unibas.freesbee.ge.processor.ProcessorGestionePubblicatori;
import it.unibas.freesbee.ge.processor.ProcessorHibernateBegin;
import it.unibas.freesbee.ge.processor.ProcessorHibernateCommit;
import it.unibas.freesbee.ge.processor.ProcessorSOAPReader;
import junit.framework.Assert;
import org.apache.camel.ContextTestSupport;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import test.it.unibas.freesbee.ge.dao.DAOMock;

public class Inserimento3 extends ContextTestSupport {

    protected Log logger = LogFactory.getLog(this.getClass());
    protected MockEndpoint resultEndpoint;

    public void testInserimento3() throws Exception {
        try {

            logger.info("TEST - 3");
            logger.info("Pubblicatore non esistente - Categoria eventi interna esistente attiva");

            String sms = "<?xml version='1.0' encoding='utf-8'?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ge=\"http://ge.freesbee.unibas.it/\"><soapenv:Header/><soapenv:Body><ge:aggiungiPubblicatore>";
            sms += "<pubblicatore><descrizione/><nome>" + DAOMock.NOME_SOGGETTO_A + "</nome><tipo>" + DAOMock.TIPO_SOGGETTO_SPC + "</tipo></pubblicatore>";
            sms += "</ge:aggiungiPubblicatore></soapenv:Body></soapenv:Envelope>";

            sendBody("direct:start", sms);


            Exchange exchange = resultEndpoint.getReceivedExchanges().get(0);
            Message messaggio = (Message) exchange.getIn();

            resultEndpoint.assertIsSatisfied();


        } catch (Exception ex) {
            logger.error(ex.getMessage());
            DAOUtilHibernate.rollback();
               Assert.fail("L'inserimento del pubblicatore  ha lanciato eccezione");

        }
    }

    public void testInserimento3Verifica() throws Exception {
        try {
            logger.info("TEST - 3");
            logger.info("Verifica inserimento pubblicatore");

            DAOUtilHibernate.beginTransaction();
            CategoriaEventiInterna catgoriaEventiInterna = DAOCategoriaEventiInternaFacade.verificaEsistenzaCategoriaEventiInternaAttavia(DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_2);
            IDAOPubblicatoreInterno daoPubblicatoreInterno = new DAOPubblicatoreInternoHibernate();
            PubblicatoreInterno pubblicatoreInterno = daoPubblicatoreInterno.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_A);
            DAOPubblicatoreInternoFacade.verificaEsistenzaPubblicatoreInterno(catgoriaEventiInterna, pubblicatoreInterno);
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
                from("direct:start").process(new ProcessorHibernateBegin()).process(new ProcessorSOAPReader()).process(new ProcessorGestionePubblicatori(DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_2, new DAOPubblicatoreInternoHibernate(), new DAOCategoriaEventiInternaHibernate(), new DAOSottoscrizioneInternaHibernate(), new DAOEventoPubblicatoInternoHibernate())).process(new ProcessorHibernateCommit()).to("mock:result");
            }
        };

    }
}

