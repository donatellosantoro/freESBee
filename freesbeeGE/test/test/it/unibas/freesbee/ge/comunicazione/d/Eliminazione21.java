package test.it.unibas.freesbee.ge.comunicazione.d;

import it.unibas.freesbee.ge.messaggi.XmlJDomUtil;
import it.unibas.freesbee.ge.modello.CategoriaEventiEsterna;
import it.unibas.freesbee.ge.modello.PubblicatoreEsterno;
import it.unibas.freesbee.ge.modello.Sottoscrittore;
import it.unibas.freesbee.ge.modello.SottoscrizioneEsterna;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiEsterna;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreEsterno;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrittore;
import it.unibas.freesbee.ge.persistenza.facade.DAOPubblicatoreEsternoFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOSottoscrizioneEsternaFacade;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCategoriaEventiEsternaHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCostanti;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOPubblicatoreEsternoHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOSottoscrittoreHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOUtilHibernate;
import it.unibas.freesbee.utilita.ClientPD;
import it.unibas.springfreesbee.ge.ConfigurazioneStatico;
import java.io.FileWriter;
import java.net.URL;
import junit.framework.Assert;
import org.apache.camel.ContextTestSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import test.it.unibas.freesbee.ge.dao.DAOMock;

public class Eliminazione21 extends ContextTestSupport {

    protected Log logger = LogFactory.getLog(this.getClass());

    public void testEliminazione21Init() throws Exception {
        logger.info("TEST - 21");
        logger.info("Init");

        FileWriter f = new FileWriter(DAOMock.CONSEGNA);
        String s = " ";
        f.append(s.subSequence(0, s.length()));
        f.flush();

        DAOUtilHibernate.beginTransaction();
        PubblicatoreEsterno pubblicatoreT = null;

        IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna = new DAOCategoriaEventiEsternaHibernate();
        CategoriaEventiEsterna categoriaEventi = daoCategoriaEventiEsterna.findByNome(DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_NON_ATTIVA_5);
        assertNotNull(categoriaEventi);
        assertFalse(categoriaEventi.isAttiva());
        assertFalse(categoriaEventi.isInAttesa());

        IDAOPubblicatoreEsterno daoPubblicatoreEsterno = new DAOPubblicatoreEsternoHibernate();
        pubblicatoreT = daoPubblicatoreEsterno.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_T);
        assertNotNull(pubblicatoreT);

        DAOPubblicatoreEsternoFacade.verificaEsistenzaPubblicatoreEsterno(categoriaEventi, pubblicatoreT);

        Sottoscrittore sottoscrittoreL = null;
        SottoscrizioneEsterna sottoscrizioneL = null;


        IDAOSottoscrittore daoSottoscrittore = new DAOSottoscrittoreHibernate();
        sottoscrittoreL = daoSottoscrittore.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_L);
        assertNotNull(sottoscrittoreL);

        sottoscrizioneL = daoCategoriaEventiEsterna.findByCategoriaEventiSottoscrittore(categoriaEventi, sottoscrittoreL);
        assertNotNull(sottoscrizioneL);

        DAOSottoscrizioneEsternaFacade.verificaEsistenzaSottoscrittoreSottoscrizioneEsterna(categoriaEventi, sottoscrittoreL);

        DAOUtilHibernate.commit();



    }

    public void testEliminazione21() throws Exception {
        logger.info("TEST - 21");
        logger.info("Pubblicatore esistene pubblicatore per la categoria - Categoria eventi esterna confermata non attiva");

        try {
            //Carico il messaggio che verebbe consegnato al GE
            URL url = this.getClass().getResource("/dati/d/test21.xml");
            Document doc = XmlJDomUtil.caricaXML(url.getFile(), false);
            String m = XmlJDomUtil.convertiDocumentToString(doc);

            ClientPD clientPD = new ClientPD();

            String indirizzo = "http://" + ConfigurazioneStatico.getInstance().getWebservicesIndirizzo() + ":" + ConfigurazioneStatico.getInstance().getWebservicesPort() + DAOCostanti.URL_WSDL_CONSEGNA_MESSAGGI;
            logger.info("url: " + indirizzo);

            clientPD.invocaPortaDelegata(indirizzo, m);


        } catch (Exception ex) {
            logger.error(ex.getMessage());
            Assert.fail("L'eliminazione del pubblicatore ha lanciato eccezione");


        }
    }

    public void testEliminazione21Dopo() throws Exception {
        try {
            logger.info("TEST - 21");
            logger.info("Verifica situazione pubblicatore e sottoscrittori");
            Thread.currentThread().sleep(5000);
            DAOUtilHibernate.beginTransaction();

            IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna = new DAOCategoriaEventiEsternaHibernate();
            IDAOPubblicatoreEsterno daoPubblicatoreEsterno = new DAOPubblicatoreEsternoHibernate();
            IDAOSottoscrittore daoSottoscrittore = new DAOSottoscrittoreHibernate();

            PubblicatoreEsterno pubblicatore = daoPubblicatoreEsterno.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_T);
            assertNull(pubblicatore);

            CategoriaEventiEsterna categoria = daoCategoriaEventiEsterna.findByNome(DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_NON_ATTIVA_5);
            assertNotNull(categoria);
            assertFalse(categoria.isInAttesa());
            assertFalse(categoria.isAttiva());


            Sottoscrittore sottoscrittore = daoSottoscrittore.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_L);
            assertNotNull(sottoscrittore);

            SottoscrizioneEsterna sottoscrizione = daoCategoriaEventiEsterna.findByCategoriaEventiSottoscrittore(categoria, sottoscrittore);
            assertNotNull(sottoscrizione);

            DAOSottoscrizioneEsternaFacade.verificaEsistenzaSottoscrittoreSottoscrizioneEsterna(categoria, sottoscrittore);



            DAOUtilHibernate.commit();


        } catch (Exception ex) {
            logger.error(ex.getMessage());
            DAOUtilHibernate.rollback();
            Assert.fail(ex.getMessage());
        }
    }

    public void testEliminazione21VerificaComunicazioneInterna() throws Exception {
        try {
            logger.info("TEST - 21");
            logger.info("Verifica Comunicazione Interna eliminazione pubblicatore");
            Thread.currentThread().sleep(5000);
            //Comunicazione inviata
            org.jdom.Document doc = XmlJDomUtil.caricaXML(DAOMock.CONSEGNA, false);
            String s = XmlJDomUtil.convertiDocumentToString(doc);

            //Comunicazione di test
            URL url = this.getClass().getResource("/dati/d/test21Interno.xml");
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

    public void testEliminazione21Clean() throws Exception {
        try {
            logger.info("TEST - 21");
            logger.info("Verifica situazione pubblicatore e sottoscrittori");
            Thread.currentThread().sleep(5000);

            DAOUtilHibernate.beginTransaction();

            IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna = new DAOCategoriaEventiEsternaHibernate();
            IDAOSottoscrittore daoSottoscrittore = new DAOSottoscrittoreHibernate();

            CategoriaEventiEsterna categoria = daoCategoriaEventiEsterna.findByNome(DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_NON_ATTIVA_5);

            Sottoscrittore sottoscrittore = daoSottoscrittore.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_L);
            assertNotNull(sottoscrittore);

            SottoscrizioneEsterna sottoscrizione = daoCategoriaEventiEsterna.findByCategoriaEventiSottoscrittore(categoria, sottoscrittore);
            assertNotNull(sottoscrizione);

            DAOSottoscrizioneEsternaFacade.eliminaSottoscrizioneEsterna(categoria, sottoscrittore);
            DAOUtilHibernate.commit();


        } catch (Exception ex) {
            logger.error(ex.getMessage());
            DAOUtilHibernate.rollback();
            Assert.fail(ex.getMessage());
        }
    }
}

