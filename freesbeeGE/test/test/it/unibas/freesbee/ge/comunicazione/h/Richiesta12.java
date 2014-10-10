package test.it.unibas.freesbee.ge.comunicazione.h;

import it.unibas.freesbee.ge.messaggi.XmlJDomUtil;
import it.unibas.freesbee.ge.modello.CategoriaEventiInterna;
import it.unibas.freesbee.ge.modello.GestoreEventi;
import it.unibas.freesbee.ge.modello.Sottoscrittore;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiInterna;
import it.unibas.freesbee.ge.persistenza.IDAOGestoreEventi;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrittore;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCategoriaEventiInternaHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCostanti;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOGestoreEventiHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOSottoscrittoreHibernate;
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

public class Richiesta12 extends ContextTestSupport {

    protected Log logger = LogFactory.getLog(this.getClass());

    public void testRichiesta12Prima() throws Exception {
        try {
            logger.info("TEST - 12");
            logger.info("Verifica situazione sottoscrittore");

            DAOUtilHibernate.beginTransaction();

            IDAOGestoreEventi daoGestoreEventi = new DAOGestoreEventiHibernate();
            GestoreEventi gestoreEventi = daoGestoreEventi.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_NON_GE);
            assertNotNull(gestoreEventi);

            IDAOSottoscrittore daoSottoscrittore = new DAOSottoscrittoreHibernate();
            Sottoscrittore sottoscrittore = daoSottoscrittore.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_NON_GE);
            assertNull(sottoscrittore);

            IDAOCategoriaEventiInterna daoCategoriaEventiInterna = new DAOCategoriaEventiInternaHibernate();
            CategoriaEventiInterna categoriaEventi = daoCategoriaEventiInterna.findByNome(DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_2);
            assertNotNull(categoriaEventi);

            DAOUtilHibernate.commit();


        } catch (Exception ex) {
            logger.error(ex.getMessage());
            DAOUtilHibernate.rollback();
            Assert.fail(ex.getMessage());
        }
    }

    public void testRichiesta12() throws Exception {
        logger.info("TEST - 12");
        logger.info("Categoria eventi esistente - Gestore Eventi pubblicatore non sottoscrittore per nessuna catgoria");

        try {
            //Carico il messaggio che verebbe consegnato al GE
            URL url = this.getClass().getResource("/dati/h/test12.xml");
            Document doc = XmlJDomUtil.caricaXML(url.getFile(), false);
            String m = XmlJDomUtil.convertiDocumentToString(doc);

            ClientPD clientPD = new ClientPD();

            String indirizzo = "http://" + ConfigurazioneStatico.getInstance().getWebservicesIndirizzo() + ":" + ConfigurazioneStatico.getInstance().getWebservicesPort() + DAOCostanti.URL_WSDL_CONSEGNA_MESSAGGI;
            logger.info("url: " + indirizzo);
            logger.debug(m);

            clientPD.invocaPortaDelegata(indirizzo, m);



        } catch (Exception ex) {
            logger.error(ex.getMessage());
            Assert.fail("L'eliminazione della sottoscrizione non ha lanciato eccezione");
        }
    }

     public void testRichiesta12Dopo() throws Exception {
        try {
            logger.info("TEST - 12");
            logger.info("Dopo");

            DAOUtilHibernate.beginTransaction();

            IDAOGestoreEventi daoGestoreEventi = new DAOGestoreEventiHibernate();
            GestoreEventi gestoreEventi = daoGestoreEventi.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_NON_GE);
            assertNotNull(gestoreEventi);

            IDAOSottoscrittore daoSottoscrittore = new DAOSottoscrittoreHibernate();
            Sottoscrittore sottoscrittore = daoSottoscrittore.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_NON_GE);
            assertNull(sottoscrittore);

            IDAOCategoriaEventiInterna daoCategoriaEventiInterna = new DAOCategoriaEventiInternaHibernate();
            CategoriaEventiInterna categoriaEventi = daoCategoriaEventiInterna.findByNome(DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_2);
            assertNotNull(categoriaEventi);

            DAOUtilHibernate.commit();


        } catch (Exception ex) {
            logger.error(ex.getMessage());
            DAOUtilHibernate.rollback();
            Assert.fail(ex.getMessage());
        }
    }
}

