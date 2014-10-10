package test.it.unibas.freesbee.ge.comunicazione.f;

import it.unibas.freesbee.ge.messaggi.XmlJDomUtil;
import it.unibas.freesbee.ge.modello.CategoriaEventiEsterna;
import it.unibas.freesbee.ge.modello.GestoreEventi;
import it.unibas.freesbee.ge.modello.PubblicatoreEsterno;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiEsterna;
import it.unibas.freesbee.ge.persistenza.IDAOGestoreEventi;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreEsterno;
import it.unibas.freesbee.ge.persistenza.facade.DAOPubblicatoreEsternoFacade;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCategoriaEventiEsternaHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCostanti;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOGestoreEventiHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOPubblicatoreEsternoHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOUtilHibernate;
import it.unibas.freesbee.utilita.ClientPD;
import it.unibas.springfreesbee.ge.ConfigurazioneStatico;
import java.net.URL;
import junit.framework.Assert;
import org.apache.camel.ContextTestSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import test.it.unibas.freesbee.ge.dao.DAOMock;

public class Conferma14 extends ContextTestSupport {

    protected Log logger = LogFactory.getLog(this.getClass());

    public void test14Prima() throws Exception {
        logger.info("TEST - 14");
        logger.info("Prima");

        DAOUtilHibernate.beginTransaction();
        IDAOCategoriaEventiEsterna daoCategoriaEventi = new DAOCategoriaEventiEsternaHibernate();
        CategoriaEventiEsterna categoriaEventi = daoCategoriaEventi.findByNome(DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_32);
        assertNotNull(categoriaEventi);
        assertFalse(categoriaEventi.isInAttesa());
        assertTrue(categoriaEventi.isAttiva());

        IDAOGestoreEventi daoGestoreEventi = new DAOGestoreEventiHibernate();
        GestoreEventi gestoreEventi = daoGestoreEventi.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_NON_GE);
        assertNull(gestoreEventi);
        
        IDAOPubblicatoreEsterno daoPubblicatoreEsterno = new DAOPubblicatoreEsternoHibernate();
        PubblicatoreEsterno pubblicatore = daoPubblicatoreEsterno.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_GE);
 
        DAOPubblicatoreEsternoFacade.isPubblicatoreEsternoDaConfermare(categoriaEventi, pubblicatore);

        DAOUtilHibernate.commit();
    }

    public void testConferma14() throws Exception {
        logger.info("TEST - 14");
        logger.info("Ricezione Conferma Categoria Eventi gia' confermata ma da altro gestore eventi");
        try {
            //Carico il messaggio che verebbe generato dal GE
            URL url = this.getClass().getResource("/dati/f/test12_13_14.xml");
            Document doc = XmlJDomUtil.caricaXML(url.getFile(), false);
            String m = XmlJDomUtil.convertiDocumentToString(doc);

            ClientPD clientPD = new ClientPD();

            String indirizzo = "http://" + ConfigurazioneStatico.getInstance().getWebservicesIndirizzo() + ":" + ConfigurazioneStatico.getInstance().getWebservicesPort() + DAOCostanti.URL_WSDL_CONSEGNA_MESSAGGI;
            logger.info("url: " + indirizzo);
            logger.debug(m);

            clientPD.invocaPortaDelegata(indirizzo, m);

            Assert.fail("La conferma della categoria non ha lanciato eccezione");

        } catch (Exception ex) {
        }
    }

    public void test14Dopo() throws Exception {
        logger.info("TEST - 14");
        logger.info("Dopo");
        DAOUtilHibernate.beginTransaction();
        IDAOCategoriaEventiEsterna daoCategoriaEventi = new DAOCategoriaEventiEsternaHibernate();
        CategoriaEventiEsterna categoriaEventi = daoCategoriaEventi.findByNome(DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_32);
        assertNotNull(categoriaEventi);
        assertFalse(categoriaEventi.isInAttesa());
        assertTrue(categoriaEventi.isAttiva());

        IDAOPubblicatoreEsterno daoPubblicatoreEsterno = new DAOPubblicatoreEsternoHibernate();
        PubblicatoreEsterno pubblicatore = daoPubblicatoreEsterno.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_GE);
        DAOPubblicatoreEsternoFacade.eliminaPubblicatoreEsterno(categoriaEventi, pubblicatore);
        DAOUtilHibernate.commit();
    }
}

