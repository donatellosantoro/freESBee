package test.it.unibas.freesbee.ge.comunicazione.d;

import it.unibas.freesbee.ge.messaggi.XmlJDomUtil;
import it.unibas.freesbee.ge.modello.CategoriaEventiEsterna;
import it.unibas.freesbee.ge.modello.GestoreEventi;
import it.unibas.freesbee.ge.modello.PubblicatoreEsterno;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiEsterna;
import it.unibas.freesbee.ge.persistenza.IDAOGestoreEventi;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreEsterno;
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

public class Eliminazione17 extends ContextTestSupport {

    protected Log logger = LogFactory.getLog(this.getClass());

    public void testEliminazione17Prima() throws Exception {
        try {
            logger.info("TEST - 17");
            logger.info("Verifica situazione pubblicatore");

            DAOUtilHibernate.beginTransaction();
            IDAOPubblicatoreEsterno daoPubblicatoreEsterno = new DAOPubblicatoreEsternoHibernate();
            PubblicatoreEsterno pubblicatore = daoPubblicatoreEsterno.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_T);
            assertNull(pubblicatore);

            IDAOGestoreEventi daoGestoreEventi = new DAOGestoreEventiHibernate();
            GestoreEventi gestoreEventi = daoGestoreEventi.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_GE);
            assertNotNull(gestoreEventi);
            pubblicatore = daoPubblicatoreEsterno.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_GE);
            assertNotNull(pubblicatore);
            IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna = new DAOCategoriaEventiEsternaHibernate();
            CategoriaEventiEsterna categoriaEventi = daoCategoriaEventiEsterna.findByNome(DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_NON_ATTIVA_5);
            assertNotNull(categoriaEventi);
            assertFalse(categoriaEventi.isAttiva());
            assertFalse(categoriaEventi.isInAttesa());

            categoriaEventi.getListaPubblicatori().add(pubblicatore);
            pubblicatore.getListaCatgoriaEventi().add(categoriaEventi);
            daoCategoriaEventiEsterna.makePersistent(categoriaEventi);
            daoPubblicatoreEsterno.makePersistent(pubblicatore);

            DAOUtilHibernate.commit();


        } catch (Exception ex) {
            logger.error(ex.getMessage());
            DAOUtilHibernate.rollback();
            Assert.fail(ex.getMessage());
        }
    }

    public void testEliminazione17() throws Exception {
        logger.info("TEST - 17");
        logger.info("Pubblicatore non esistene - Categoria eventi esterna  confermata non attiva ");

        try {
            //Carico il messaggio che verebbe consegnato al GE
            URL url = this.getClass().getResource("/dati/d/test17.xml");
            Document doc = XmlJDomUtil.caricaXML(url.getFile(), false);
            String m = XmlJDomUtil.convertiDocumentToString(doc);

            ClientPD clientPD = new ClientPD();

            String indirizzo = "http://" + ConfigurazioneStatico.getInstance().getWebservicesIndirizzo() + ":" + ConfigurazioneStatico.getInstance().getWebservicesPort() + DAOCostanti.URL_WSDL_CONSEGNA_MESSAGGI;
            logger.info("url: " + indirizzo);
            logger.debug(m);

            clientPD.invocaPortaDelegata(indirizzo, m);



        } catch (Exception ex) {
            logger.error(ex.getMessage());
            Assert.fail("L'eliminazione del pubblicatore ha lanciato eccezione");
        }
    }

    public void testEliminazione17Dopo() throws Exception {
        try {
            logger.info("TEST - 17");
            logger.info("Verifica situazione pubblicatore");

            DAOUtilHibernate.beginTransaction();
            IDAOPubblicatoreEsterno daoPubblicatoreEsterno = new DAOPubblicatoreEsternoHibernate();
            PubblicatoreEsterno pubblicatore = daoPubblicatoreEsterno.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_T);
            assertNull(pubblicatore);
            DAOUtilHibernate.commit();


        } catch (Exception ex) {
            logger.error(ex.getMessage());
            DAOUtilHibernate.rollback();
            Assert.fail(ex.getMessage());
        }
    }
}

