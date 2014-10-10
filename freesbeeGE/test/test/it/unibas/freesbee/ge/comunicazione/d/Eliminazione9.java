package test.it.unibas.freesbee.ge.comunicazione.d;

import it.unibas.freesbee.ge.messaggi.XmlJDomUtil;
import it.unibas.freesbee.ge.modello.CategoriaEventiEsterna;
import it.unibas.freesbee.ge.modello.FiltroData;
import it.unibas.freesbee.ge.modello.FiltroPubblicatoreEsterno;
import it.unibas.freesbee.ge.modello.ISottoscrizione;
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
import java.util.Calendar;
import java.util.GregorianCalendar;
import junit.framework.Assert;
import org.apache.camel.ContextTestSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import test.it.unibas.freesbee.ge.dao.DAOMock;

public class Eliminazione9 extends ContextTestSupport {

    protected Log logger = LogFactory.getLog(this.getClass());

    public void testEliminazione9Init() throws Exception {
        logger.info("TEST - 9");
        logger.info("Init");

        FileWriter f = new FileWriter(DAOMock.CONSEGNA);
        String s = " ";
        f.append(s.subSequence(0, s.length()));
        f.flush();

        f = new FileWriter(DAOMock.CONSEGNA_1);
        s = " ";
        f.append(s.subSequence(0, s.length()));
        f.flush();

        DAOUtilHibernate.beginTransaction();
        IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna = new DAOCategoriaEventiEsternaHibernate();
        PubblicatoreEsterno pubblicatoreQ = null;
        PubblicatoreEsterno pubblicatoreR = null;

        Sottoscrittore sottoscrittoreL = null;
        Sottoscrittore sottoscrittoreM = null;
        Sottoscrittore sottoscrittoreN = null;
        SottoscrizioneEsterna sottoscrizioneL = null;
        SottoscrizioneEsterna sottoscrizioneM = null;
        SottoscrizioneEsterna sottoscrizioneN = null;
        FiltroData filtroDataL = null;
        FiltroData filtroDataM = null;
        FiltroData filtroDataN = null;

        Calendar date = new GregorianCalendar();
        date.add(Calendar.MINUTE, 3);

        CategoriaEventiEsterna categoriaEventi = daoCategoriaEventiEsterna.findByNome(DAOMock.CATEGORIA_EVENTI_ESTERNA_NON_CONFERMATA_NON_ATTIVA_4);
        assertNotNull(categoriaEventi);
        assertFalse(categoriaEventi.isAttiva());
        assertTrue(categoriaEventi.isInAttesa());

        pubblicatoreQ = new PubblicatoreEsterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_Q);
        pubblicatoreR = new PubblicatoreEsterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_R);

        IDAOPubblicatoreEsterno daoPubblicatoreEsterno = new DAOPubblicatoreEsternoHibernate();
        daoPubblicatoreEsterno.makePersistent(pubblicatoreQ);
        daoPubblicatoreEsterno.makePersistent(pubblicatoreR);

        sottoscrittoreL = new Sottoscrittore(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_L);
        sottoscrittoreM = new Sottoscrittore(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_M);
        sottoscrittoreN = new Sottoscrittore(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_N);
        filtroDataL = new FiltroData(date.getTime());
        filtroDataM = new FiltroData(date.getTime());
        filtroDataN = new FiltroData(date.getTime());

        sottoscrizioneL = new SottoscrizioneEsterna(sottoscrittoreL, categoriaEventi, ISottoscrizione.TIPO_SOTTOSCRIZIONE_CONSEGNA, filtroDataL);
        DAOSottoscrizioneEsternaFacade.aggiungiSottoscrizioneEsterna(categoriaEventi, sottoscrittoreL, sottoscrizioneL);

        sottoscrizioneM = new SottoscrizioneEsterna(sottoscrittoreM, categoriaEventi, ISottoscrizione.TIPO_SOTTOSCRIZIONE_NOTIFICA, filtroDataM);
        sottoscrizioneM.getListaFiltroPublicatore().add(new FiltroPubblicatoreEsterno(pubblicatoreQ, sottoscrizioneM));
        DAOSottoscrizioneEsternaFacade.aggiungiSottoscrizioneEsterna(categoriaEventi, sottoscrittoreM, sottoscrizioneM);


        sottoscrizioneN = new SottoscrizioneEsterna(sottoscrittoreN, categoriaEventi, ISottoscrizione.TIPO_SOTTOSCRIZIONE_NOTIFICA, filtroDataN);
        sottoscrizioneN.getListaFiltroPublicatore().add(new FiltroPubblicatoreEsterno(pubblicatoreQ, sottoscrizioneN));
        sottoscrizioneN.getListaFiltroPublicatore().add(new FiltroPubblicatoreEsterno(pubblicatoreR, sottoscrizioneN));
        DAOSottoscrizioneEsternaFacade.aggiungiSottoscrizioneEsterna(categoriaEventi, sottoscrittoreN, sottoscrizioneN);

        DAOPubblicatoreEsternoFacade.verificaNonEsistenzaPubblicatoreEsterno(categoriaEventi, pubblicatoreR);
        DAOPubblicatoreEsternoFacade.verificaNonEsistenzaPubblicatoreEsterno(categoriaEventi, pubblicatoreQ);
        DAOSottoscrizioneEsternaFacade.verificaEsistenzaSottoscrittoreSottoscrizioneEsterna(categoriaEventi, sottoscrittoreL);
        DAOSottoscrizioneEsternaFacade.verificaEsistenzaSottoscrittoreSottoscrizioneEsterna(categoriaEventi, sottoscrittoreM);
        DAOSottoscrizioneEsternaFacade.verificaEsistenzaSottoscrittoreSottoscrizioneEsterna(categoriaEventi, sottoscrittoreN);
        assertTrue(categoriaEventi.isInAttesa());

        DAOUtilHibernate.commit();


    }

    public void testEliminazione9() throws Exception {
        logger.info("TEST - 9");
        logger.info("Pubblicatore esistene pubblicatore da confermare per la categoria - Categoria eventi esterna non confermata non attiva");

        try {
            //Carico il messaggio che verebbe consegnato al GE
            URL url = this.getClass().getResource("/dati/d/test9.xml");
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

    public void testEliminazione9Dopo() throws Exception {
        try {
            logger.info("TEST - 9");
            logger.info("Verifica situazione pubblicatore e sottoscrittori");
            Thread.currentThread().sleep(5000);
            DAOUtilHibernate.beginTransaction();

            IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna = new DAOCategoriaEventiEsternaHibernate();
            IDAOPubblicatoreEsterno daoPubblicatoreEsterno = new DAOPubblicatoreEsternoHibernate();
            IDAOSottoscrittore daoSottoscrittore = new DAOSottoscrittoreHibernate();

            PubblicatoreEsterno pubblicatore = daoPubblicatoreEsterno.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_Q);
            assertNull(pubblicatore);

            pubblicatore = daoPubblicatoreEsterno.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_R);
            assertNotNull(pubblicatore);

            CategoriaEventiEsterna categoria = daoCategoriaEventiEsterna.findByNome(DAOMock.CATEGORIA_EVENTI_ESTERNA_NON_CONFERMATA_NON_ATTIVA_4);
            assertNotNull(categoria);

            Sottoscrittore sottoscrittore = daoSottoscrittore.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_L);
            assertNotNull(sottoscrittore);
            SottoscrizioneEsterna sottoscrizione = daoCategoriaEventiEsterna.findByCategoriaEventiSottoscrittore(categoria, sottoscrittore);
            assertNotNull(sottoscrizione);

            sottoscrittore = daoSottoscrittore.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_M);
            assertNull(sottoscrittore);

            sottoscrittore = daoSottoscrittore.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_N);
            assertNotNull(sottoscrittore);
            sottoscrizione = daoCategoriaEventiEsterna.findByCategoriaEventiSottoscrittore(categoria, sottoscrittore);
            assertNotNull(sottoscrizione);
            assertEquals(1, sottoscrizione.getListaFiltroPublicatore().size());

            PubblicatoreEsterno pubb = sottoscrizione.getListaFiltroPublicatore().get(0).getPubblicatore();
            assertTrue(pubblicatore.compareTo(pubb) == 0);
            assertTrue(categoria.isInAttesa());

            DAOUtilHibernate.commit();


        } catch (Exception ex) {
            logger.error(ex.getMessage());
            DAOUtilHibernate.rollback();
            Assert.fail(ex.getMessage());
        }
    }

    public void testEliminazione9VerificaComunicazioneInterna() throws Exception {
        try {
            logger.info("TEST - 9");
            logger.info("Verifica Comunicazione Interna eliminazione pubblicatore");
            Thread.currentThread().sleep(5000);
            //Comunicazione inviata
            org.jdom.Document doc = XmlJDomUtil.caricaXML(DAOMock.CONSEGNA, false);
            String s = XmlJDomUtil.convertiDocumentToString(doc);

            //Comunicazione di test
            URL url = this.getClass().getResource("/dati/d/test9Interno.xml");
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

    public void testEliminazione9VerificaComunicazioneInterna1() throws Exception {
        try {
            logger.info("TEST - 9");
            logger.info("Verifica Comunicazione Interna eliminazione sottoscrizione");
            Thread.currentThread().sleep(5000);

            //Comunicazione inviata
            org.jdom.Document doc = XmlJDomUtil.caricaXML(DAOMock.CONSEGNA_1, false);
            String s = XmlJDomUtil.convertiDocumentToString(doc);

            //Comunicazione di test
            URL url = this.getClass().getResource("/dati/d/test9Interno1.xml");
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
}

