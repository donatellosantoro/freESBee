package test.it.unibas.freesbee.ge.comunicazione.h;

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

public class Eliminazione1 extends ContextTestSupport {

    protected Log logger = LogFactory.getLog(this.getClass());

    public void testEliminazione1Init() throws Exception {
        logger.info("TEST - 1");
        logger.info("Init");

        FileWriter f = new FileWriter(DAOMock.COMUNICAZIONE_ESTERNA_1);
        String s = "<root><ok/></root>";
        f.append(s.subSequence(0, s.length()));
        f.flush();


        DAOUtilHibernate.beginTransaction();
        IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna = new DAOCategoriaEventiEsternaHibernate();
        PubblicatoreEsterno pubblicatoreQ = null;

        Sottoscrittore sottoscrittoreM = null;
        Sottoscrittore sottoscrittoreN = null;

        SottoscrizioneEsterna sottoscrizioneM = null;
        SottoscrizioneEsterna sottoscrizioneN = null;

        FiltroData filtroDataM = null;
        FiltroData filtroDataN = null;

        Calendar date = new GregorianCalendar();
        date.add(Calendar.MINUTE, 3);

        CategoriaEventiEsterna categoriaEventi = daoCategoriaEventiEsterna.findByNome(DAOMock.CATEGORIA_EVENTI_ESTERNA_NON_CONFERMATA_NON_ATTIVA_4);
        assertNotNull(categoriaEventi);
        assertFalse(categoriaEventi.isAttiva());
        assertTrue(categoriaEventi.isInAttesa());

        pubblicatoreQ = new PubblicatoreEsterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_Q);


        IDAOPubblicatoreEsterno daoPubblicatoreEsterno = new DAOPubblicatoreEsternoHibernate();
        daoPubblicatoreEsterno.makePersistent(pubblicatoreQ);

        sottoscrittoreM = new Sottoscrittore(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_M);
        sottoscrittoreN = new Sottoscrittore(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_N);

        filtroDataM = new FiltroData(date.getTime());
        filtroDataN = new FiltroData(date.getTime());


        sottoscrizioneM = new SottoscrizioneEsterna(sottoscrittoreM, categoriaEventi, ISottoscrizione.TIPO_SOTTOSCRIZIONE_NOTIFICA, filtroDataM);
        sottoscrizioneM.getListaFiltroPublicatore().add(new FiltroPubblicatoreEsterno(pubblicatoreQ, sottoscrizioneM));
        DAOSottoscrizioneEsternaFacade.aggiungiSottoscrizioneEsterna(categoriaEventi, sottoscrittoreM, sottoscrizioneM);


        sottoscrizioneN = new SottoscrizioneEsterna(sottoscrittoreN, categoriaEventi, ISottoscrizione.TIPO_SOTTOSCRIZIONE_NOTIFICA, filtroDataN);
        sottoscrizioneN.getListaFiltroPublicatore().add(new FiltroPubblicatoreEsterno(pubblicatoreQ, sottoscrizioneN));
        DAOSottoscrizioneEsternaFacade.aggiungiSottoscrizioneEsterna(categoriaEventi, sottoscrittoreN, sottoscrizioneN);

        DAOPubblicatoreEsternoFacade.verificaNonEsistenzaPubblicatoreEsterno(categoriaEventi, pubblicatoreQ);

        DAOSottoscrizioneEsternaFacade.verificaEsistenzaSottoscrittoreSottoscrizioneEsterna(categoriaEventi, sottoscrittoreM);
        DAOSottoscrizioneEsternaFacade.verificaEsistenzaSottoscrittoreSottoscrizioneEsterna(categoriaEventi, sottoscrittoreN);
        assertTrue(categoriaEventi.isInAttesa());

        DAOUtilHibernate.commit();


    }

    public void testEliminazione1Pubblicazione() throws Exception {
        logger.info("TEST - 1");
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

    public void testEliminazione1Dopo() throws Exception {
        try {
            logger.info("TEST - 1");
            logger.info("Verifica situazione pubblicatore e sottoscrittori");
            Thread.currentThread().sleep(5000);
            DAOUtilHibernate.beginTransaction();

            IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna = new DAOCategoriaEventiEsternaHibernate();
            IDAOPubblicatoreEsterno daoPubblicatoreEsterno = new DAOPubblicatoreEsternoHibernate();
            IDAOSottoscrittore daoSottoscrittore = new DAOSottoscrittoreHibernate();

            PubblicatoreEsterno pubblicatore = daoPubblicatoreEsterno.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_Q);
            assertNull(pubblicatore);

            CategoriaEventiEsterna categoria = daoCategoriaEventiEsterna.findByNome(DAOMock.CATEGORIA_EVENTI_ESTERNA_NON_CONFERMATA_NON_ATTIVA_4);
            assertNotNull(categoria);

            Sottoscrittore sottoscrittore = daoSottoscrittore.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_M);
            assertNull(sottoscrittore);

            sottoscrittore = daoSottoscrittore.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_N);
            assertNull(sottoscrittore);

            assertEquals(0, categoria.getListaSottoscrizioni().size());

            DAOUtilHibernate.commit();


        } catch (Exception ex) {
            logger.error(ex.getMessage());
            DAOUtilHibernate.rollback();
            Assert.fail(ex.getMessage());
        }
    }

    public void testInserimento1VerificaComunicazioneEsterna1() throws Exception {
        try {
            logger.info("TEST - 1");
            logger.info("Verifica Comunicazione Esterna richiesta eliminizazione sottoscrizione");
            Thread.currentThread().sleep(5000);
            //Comunicazione inviata
            org.jdom.Document doc = XmlJDomUtil.caricaXML(DAOMock.COMUNICAZIONE_ESTERNA_1, false);
            String s = XmlJDomUtil.convertiDocumentToString(doc);

            //Comunicazione di test
            String m = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><root><ok/></root>";

            logger.info("Comunicazione inviata: ");
            logger.info(XmlJDomUtil.formattaXML(s));
            logger.info("Comunicazione di test:");
            logger.info(XmlJDomUtil.formattaXML(m));

            assertEquals(XmlJDomUtil.formattaXML(m), XmlJDomUtil.formattaXML(s));

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            Assert.fail(ex.getMessage());
        }
    }
}

