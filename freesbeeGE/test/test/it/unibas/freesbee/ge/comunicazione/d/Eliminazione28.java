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
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrizioneEsterna;
import it.unibas.freesbee.ge.persistenza.facade.DAOCategoriaEventiEsternaFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOPubblicatoreEsternoFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOSottoscrizioneEsternaFacade;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCategoriaEventiEsternaHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCostanti;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOPubblicatoreEsternoHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOSottoscrittoreHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOSottoscrizioneEsternaHibernate;
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

public class Eliminazione28 extends ContextTestSupport {

    protected Log logger = LogFactory.getLog(this.getClass());

    public void testEliminazione28Init() throws Exception {
        logger.info("TEST - 28");
        logger.info("Init");

        FileWriter f = new FileWriter(DAOMock.CONSEGNA);
        String s = " ";
        f.append(s.subSequence(0, s.length()));
        f.flush();

        DAOUtilHibernate.beginTransaction();
        PubblicatoreEsterno pubblicatoreQ = null;
        PubblicatoreEsterno pubblicatoreR = null;
        PubblicatoreEsterno pubblicatoreP = null; //Gia nella base di dati

        CategoriaEventiEsterna categoriaEventi = DAOCategoriaEventiEsternaFacade.verificaEsistenzaCategoriaEventiEsternaAttavia(DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_9);

        pubblicatoreQ = new PubblicatoreEsterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_Q);
        pubblicatoreR = new PubblicatoreEsterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_R);

        IDAOPubblicatoreEsterno daoPubblicatoreEsterno = new DAOPubblicatoreEsternoHibernate();
        daoPubblicatoreEsterno.makePersistent(pubblicatoreQ);
        pubblicatoreP = daoPubblicatoreEsterno.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_P);

        DAOPubblicatoreEsternoFacade.aggiungiPubblicatoreEsterno(categoriaEventi, pubblicatoreR);

        Sottoscrittore sottoscrittoreM = null;
        SottoscrizioneEsterna sottoscrizioneM = null;
        FiltroData filtroDataM = null;

        Sottoscrittore sottoscrittoreN = null;
        SottoscrizioneEsterna sottoscrizioneN = null;
        FiltroData filtroDataN = null;

        Calendar date = new GregorianCalendar();
        date.add(Calendar.MINUTE, 3);


        sottoscrittoreM = new Sottoscrittore(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_M);
        sottoscrittoreN = new Sottoscrittore(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_N);
        filtroDataM = new FiltroData(date.getTime());
        filtroDataN = new FiltroData(date.getTime());

        sottoscrizioneM = new SottoscrizioneEsterna(sottoscrittoreM, categoriaEventi, ISottoscrizione.TIPO_SOTTOSCRIZIONE_NOTIFICA, filtroDataM);
        sottoscrizioneM.getListaFiltroPublicatore().add(new FiltroPubblicatoreEsterno(pubblicatoreP, sottoscrizioneM));
        sottoscrizioneM.getListaFiltroPublicatore().add(new FiltroPubblicatoreEsterno(pubblicatoreQ, sottoscrizioneM));
        DAOSottoscrizioneEsternaFacade.aggiungiSottoscrizioneEsterna(categoriaEventi, sottoscrittoreM, sottoscrizioneM);

        IDAOSottoscrizioneEsterna daoSottoscrizioneEsterna = new DAOSottoscrizioneEsternaHibernate();
        date.add(Calendar.HOUR, 6);
        sottoscrizioneM.setScadenzaAttesa(date.getTime());
        daoSottoscrizioneEsterna.makePersistent(sottoscrizioneM);

        sottoscrizioneN = new SottoscrizioneEsterna(sottoscrittoreN, categoriaEventi, ISottoscrizione.TIPO_SOTTOSCRIZIONE_NOTIFICA, filtroDataN);
        sottoscrizioneN.getListaFiltroPublicatore().add(new FiltroPubblicatoreEsterno(pubblicatoreR, sottoscrizioneN));
        sottoscrizioneN.getListaFiltroPublicatore().add(new FiltroPubblicatoreEsterno(pubblicatoreQ, sottoscrizioneN));
        DAOSottoscrizioneEsternaFacade.aggiungiSottoscrizioneEsterna(categoriaEventi, sottoscrittoreN, sottoscrizioneN);

        date.add(Calendar.HOUR, 6);
        sottoscrizioneN.setScadenzaAttesa(date.getTime());
        daoSottoscrizioneEsterna.makePersistent(sottoscrizioneN);


        DAOPubblicatoreEsternoFacade.verificaEsistenzaPubblicatoreEsterno(categoriaEventi, pubblicatoreP);

        assertTrue(DAOPubblicatoreEsternoFacade.isPubblicatoreEsternoDaConfermare(categoriaEventi, pubblicatoreQ));

        DAOPubblicatoreEsternoFacade.verificaEsistenzaPubblicatoreEsterno(categoriaEventi, pubblicatoreR);

        DAOSottoscrizioneEsternaFacade.verificaEsistenzaSottoscrittoreSottoscrizioneEsterna(categoriaEventi, sottoscrittoreM);
        DAOSottoscrizioneEsternaFacade.verificaEsistenzaSottoscrittoreSottoscrizioneEsterna(categoriaEventi, sottoscrittoreN);
        assertFalse(categoriaEventi.isInAttesa());

        assertFalse(DAOSottoscrizioneEsternaFacade.isInAttesaConfermaCategoriaEventi(sottoscrizioneN));
        assertFalse(DAOSottoscrizioneEsternaFacade.isInAttesaConfermaCategoriaEventi(sottoscrizioneM));

        assertTrue(DAOSottoscrizioneEsternaFacade.isInAttesaConfermaPubblicatori(sottoscrizioneM));
        assertTrue(DAOSottoscrizioneEsternaFacade.isInAttesaConfermaPubblicatori(sottoscrizioneN));

        DAOUtilHibernate.commit();



    }

    public void testEliminazione28() throws Exception {
        logger.info("TEST - 28");
        logger.info("Pubblicatore esistene pubblicatore per la categoria - Categoria eventi esterna confermata attiva");

        try {
            //Carico il messaggio che verebbe consegnato al GE
            URL url = this.getClass().getResource("/dati/d/test28.xml");
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

    public void testEliminazione28Dopo() throws Exception {
        try {
            logger.info("TEST - 28");
            logger.info("Verifica situazione pubblicatore e sottoscrittori");
            Thread.currentThread().sleep(5000);
            DAOUtilHibernate.beginTransaction();

            IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna = new DAOCategoriaEventiEsternaHibernate();
            IDAOPubblicatoreEsterno daoPubblicatoreEsterno = new DAOPubblicatoreEsternoHibernate();
            IDAOSottoscrittore daoSottoscrittore = new DAOSottoscrittoreHibernate();

            CategoriaEventiEsterna categoria = DAOCategoriaEventiEsternaFacade.verificaEsistenzaCategoriaEventiEsternaAttavia(DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_9);

            PubblicatoreEsterno pubblicatore = daoPubblicatoreEsterno.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_P);
            assertNull(pubblicatore);

            PubblicatoreEsterno pubblicatoreQ = daoPubblicatoreEsterno.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_Q);
            assertNotNull(pubblicatoreQ);
            assertTrue(DAOPubblicatoreEsternoFacade.isPubblicatoreEsternoDaConfermare(categoria, pubblicatoreQ));

            PubblicatoreEsterno pubblicatoreR = daoPubblicatoreEsterno.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_R);
            assertNotNull(pubblicatoreR);
            DAOPubblicatoreEsternoFacade.verificaEsistenzaPubblicatoreEsterno(categoria, pubblicatoreR);


            Sottoscrittore sottoscrittoreM = daoSottoscrittore.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_M);
            assertNotNull(sottoscrittoreM);
            DAOSottoscrizioneEsternaFacade.verificaEsistenzaSottoscrittoreSottoscrizioneEsterna(categoria, sottoscrittoreM);
            SottoscrizioneEsterna sottoscrizioneM = daoCategoriaEventiEsterna.findByCategoriaEventiSottoscrittore(categoria, sottoscrittoreM);
            assertFalse(DAOSottoscrizioneEsternaFacade.isInAttesaConfermaCategoriaEventi(sottoscrizioneM));
            assertTrue(DAOSottoscrizioneEsternaFacade.isInAttesaConfermaPubblicatori(sottoscrizioneM));
            assertEquals(1, sottoscrizioneM.getListaFiltroPublicatore().size());
            assertTrue(sottoscrizioneM.getListaFiltroPublicatore().get(0).getPubblicatore().compareTo(pubblicatoreQ) == 0);

            Sottoscrittore sottoscrittoreN = daoSottoscrittore.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_N);
            assertNotNull(sottoscrittoreN);
            DAOSottoscrizioneEsternaFacade.verificaEsistenzaSottoscrittoreSottoscrizioneEsterna(categoria, sottoscrittoreN);
            SottoscrizioneEsterna sottoscrizioneN = daoCategoriaEventiEsterna.findByCategoriaEventiSottoscrittore(categoria, sottoscrittoreN);
            assertNotNull(sottoscrizioneN);
            assertEquals(2, sottoscrizioneN.getListaFiltroPublicatore().size());
            assertFalse(DAOSottoscrizioneEsternaFacade.isInAttesaConfermaCategoriaEventi(sottoscrizioneN));
            assertTrue(DAOSottoscrizioneEsternaFacade.isInAttesaConfermaPubblicatori(sottoscrizioneN));

            DAOUtilHibernate.commit();


        } catch (Exception ex) {
            logger.error(ex.getMessage());
            DAOUtilHibernate.rollback();
            Assert.fail(ex.getMessage());
        }
    }

    public void testEliminazione28VerificaComunicazioneInterna() throws Exception {
        try {
            logger.info("TEST - 28");
            logger.info("Verifica Comunicazione Interna eliminazione pubblicatore");
            Thread.currentThread().sleep(5000);
            //Comunicazione inviata
            org.jdom.Document doc = XmlJDomUtil.caricaXML(DAOMock.CONSEGNA, false);
            String s = XmlJDomUtil.convertiDocumentToString(doc);

            //Comunicazione di test
            URL url = this.getClass().getResource("/dati/d/test28Interno.xml");
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

