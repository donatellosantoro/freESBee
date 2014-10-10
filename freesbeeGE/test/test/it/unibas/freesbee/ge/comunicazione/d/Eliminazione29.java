package test.it.unibas.freesbee.ge.comunicazione.d;

import it.unibas.freesbee.ge.messaggi.XmlJDomUtil;
import it.unibas.freesbee.ge.modello.CategoriaEventiEsterna;
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

public class Eliminazione29 extends ContextTestSupport {

    protected Log logger = LogFactory.getLog(this.getClass());

    public void testEliminazione29Init() throws Exception {
        logger.info("TEST - 29");
        logger.info("Init");

        FileWriter f = new FileWriter(DAOMock.CONSEGNA);
        String s = " ";
        f.append(s.subSequence(0, s.length()));
        f.flush();

    }

    public void testEliminazione29() throws Exception {
        logger.info("TEST - 29");
        logger.info("Pubblicatore esistene pubblicatore da confermare per la categoria - Categoria eventi esterna confermata attiva");

        try {
            //Carico il messaggio che verebbe consegnato al GE
            URL url = this.getClass().getResource("/dati/d/test29.xml");
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

    public void testEliminazione29Dopo() throws Exception {
        try {
            logger.info("TEST - 29");
            logger.info("Verifica situazione pubblicatore e sottoscrittori");
            Thread.currentThread().sleep(5000);
            DAOUtilHibernate.beginTransaction();

            IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna = new DAOCategoriaEventiEsternaHibernate();
            IDAOPubblicatoreEsterno daoPubblicatoreEsterno = new DAOPubblicatoreEsternoHibernate();
            IDAOSottoscrittore daoSottoscrittore = new DAOSottoscrittoreHibernate();

            CategoriaEventiEsterna categoria = DAOCategoriaEventiEsternaFacade.verificaEsistenzaCategoriaEventiEsternaAttavia(DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_9);

            PubblicatoreEsterno pubblicatoreQ = daoPubblicatoreEsterno.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_Q);
            assertNull(pubblicatoreQ);

            PubblicatoreEsterno pubblicatoreR = daoPubblicatoreEsterno.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_R);
            assertNotNull(pubblicatoreR);
            DAOPubblicatoreEsternoFacade.verificaEsistenzaPubblicatoreEsterno(categoria, pubblicatoreR);


            Sottoscrittore sottoscrittoreM = daoSottoscrittore.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_M);
            assertNull(sottoscrittoreM);

            Sottoscrittore sottoscrittoreN = daoSottoscrittore.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_N);
            assertNotNull(sottoscrittoreN);
            DAOSottoscrizioneEsternaFacade.verificaEsistenzaSottoscrittoreSottoscrizioneEsterna(categoria, sottoscrittoreN);
            SottoscrizioneEsterna sottoscrizioneN = daoCategoriaEventiEsterna.findByCategoriaEventiSottoscrittore(categoria, sottoscrittoreN);
            assertNotNull(sottoscrizioneN);
            assertEquals(1, sottoscrizioneN.getListaFiltroPublicatore().size());
            assertFalse(DAOSottoscrizioneEsternaFacade.isInAttesaConfermaCategoriaEventi(sottoscrizioneN));
            assertFalse(DAOSottoscrizioneEsternaFacade.isInAttesaConfermaPubblicatori(sottoscrizioneN));
            assertTrue(sottoscrizioneN.getListaFiltroPublicatore().get(0).getPubblicatore().compareTo(pubblicatoreR) == 0);

            DAOUtilHibernate.commit();


        } catch (Exception ex) {
            logger.error(ex.getMessage());
            DAOUtilHibernate.rollback();
            Assert.fail(ex.getMessage());
        }
    }

    public void testEliminazione29VerificaComunicazioneInterna() throws Exception {
        try {
            logger.info("TEST - 29");
            logger.info("Verifica Comunicazione Interna eliminazione pubblicatore");
            Thread.currentThread().sleep(5000);
            //Comunicazione inviata
            org.jdom.Document doc = XmlJDomUtil.caricaXML(DAOMock.CONSEGNA, false);
            String s = XmlJDomUtil.convertiDocumentToString(doc);

            //Comunicazione di test
            URL url = this.getClass().getResource("/dati/d/test29Interno.xml");
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

    public void testEliminazione29Clean() throws Exception {
        try {
            logger.info("TEST - 29");
            logger.info("Clean");


            DAOUtilHibernate.beginTransaction();
            IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna = new DAOCategoriaEventiEsternaHibernate();
            IDAOSottoscrittore daoSottoscrittore = new DAOSottoscrittoreHibernate();
            Sottoscrittore sottoscrittoreN = daoSottoscrittore.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_N);
            DAOSottoscrizioneEsternaFacade.eliminaSottoscrizioneEsterna(daoCategoriaEventiEsterna.findByNome(DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_9), sottoscrittoreN);

            DAOUtilHibernate.commit();

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            DAOUtilHibernate.rollback();
            Assert.fail(ex.getMessage());
        }
    }
}

