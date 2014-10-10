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

public class Eliminazione3 extends ContextTestSupport {

    protected Log logger = LogFactory.getLog(this.getClass());

    public void testEliminazione3Init() throws Exception {
        logger.info("TEST - 3");
        logger.info("Init");

        FileWriter f = new FileWriter(DAOMock.COMUNICAZIONE_ESTERNA_1);
        String s = " ";
        f.append(s.subSequence(0, s.length()));
        f.flush();


        DAOUtilHibernate.beginTransaction();
        PubblicatoreEsterno pubblicatoreQ = null;
        PubblicatoreEsterno pubblicatoreT = null;

        IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna = new DAOCategoriaEventiEsternaHibernate();
        CategoriaEventiEsterna categoriaEventi = daoCategoriaEventiEsterna.findByNome(DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_NON_ATTIVA_5);
        assertNotNull(categoriaEventi);
        assertFalse(categoriaEventi.isAttiva());
        assertFalse(categoriaEventi.isInAttesa());

        pubblicatoreQ = new PubblicatoreEsterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_Q);
        pubblicatoreT = new PubblicatoreEsterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_T);

        categoriaEventi.getListaPubblicatori().add(pubblicatoreQ);
        categoriaEventi.getListaPubblicatori().add(pubblicatoreT);
        pubblicatoreQ.getListaCatgoriaEventi().add(categoriaEventi);
        pubblicatoreT.getListaCatgoriaEventi().add(categoriaEventi);
        daoCategoriaEventiEsterna.makePersistent(categoriaEventi);
        IDAOPubblicatoreEsterno daoPubblicatoreEsterno = new DAOPubblicatoreEsternoHibernate();
        daoPubblicatoreEsterno.makePersistent(pubblicatoreQ);
        daoPubblicatoreEsterno.makePersistent(pubblicatoreT);



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
        sottoscrizioneM.getListaFiltroPublicatore().add(new FiltroPubblicatoreEsterno(pubblicatoreQ, sottoscrizioneM));
        DAOSottoscrizioneEsternaFacade.aggiungiSottoscrizioneEsterna(categoriaEventi, sottoscrittoreM, sottoscrizioneM);
        sottoscrizioneN = new SottoscrizioneEsterna(sottoscrittoreN, categoriaEventi, ISottoscrizione.TIPO_SOTTOSCRIZIONE_NOTIFICA, filtroDataN);
        sottoscrizioneN.getListaFiltroPublicatore().add(new FiltroPubblicatoreEsterno(pubblicatoreQ, sottoscrizioneN));
        DAOSottoscrizioneEsternaFacade.aggiungiSottoscrizioneEsterna(categoriaEventi, sottoscrittoreN, sottoscrizioneN);


        DAOPubblicatoreEsternoFacade.verificaEsistenzaPubblicatoreEsterno(categoriaEventi, pubblicatoreQ);
        DAOPubblicatoreEsternoFacade.verificaEsistenzaPubblicatoreEsterno(categoriaEventi, pubblicatoreT);
        DAOSottoscrizioneEsternaFacade.verificaEsistenzaSottoscrittoreSottoscrizioneEsterna(categoriaEventi, sottoscrittoreM);
        DAOSottoscrizioneEsternaFacade.verificaEsistenzaSottoscrittoreSottoscrizioneEsterna(categoriaEventi, sottoscrittoreN);

        DAOUtilHibernate.commit();



    }

    public void testEliminazione3Pubblicazione() throws Exception {
        logger.info("TEST - 3");
        logger.info("Pubblicatore esistene pubblicatore per la categoria - Categoria eventi esterna confermata non attiva");

        try {
            //Carico il messaggio che verebbe consegnato al GE
            URL url = this.getClass().getResource("/dati/d/test19.xml");
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

    public void testEliminazione3Dopo() throws Exception {
        try {
            logger.info("TEST - 3");
            logger.info("Verifica situazione pubblicatore e sottoscrittori");
            Thread.currentThread().sleep(5000);
            DAOUtilHibernate.beginTransaction();

            IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna = new DAOCategoriaEventiEsternaHibernate();
            IDAOPubblicatoreEsterno daoPubblicatoreEsterno = new DAOPubblicatoreEsternoHibernate();
            IDAOSottoscrittore daoSottoscrittore = new DAOSottoscrittoreHibernate();

            PubblicatoreEsterno pubblicatore = daoPubblicatoreEsterno.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_Q);
            assertNull(pubblicatore);

            CategoriaEventiEsterna categoria = daoCategoriaEventiEsterna.findByNome(DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_NON_ATTIVA_5);
            assertNotNull(categoria);
            assertTrue(categoria.isInAttesa());
            assertFalse(categoria.isAttiva());


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

    

    public void testEliminazione3VerificaComunicazioneEsterna1() throws Exception {
        try {
            logger.info("TEST - 3");
            logger.info("Verifica Comunicazione Esterna richiesta eliminizazione sottoscrizione");
            Thread.currentThread().sleep(5000);

            //Comunicazione inviata
            org.jdom.Document doc = XmlJDomUtil.caricaXML(DAOMock.COMUNICAZIONE_ESTERNA_1, false);
            String s = XmlJDomUtil.convertiDocumentToString(doc);

            //Comunicazione di test
            URL url = this.getClass().getResource("/dati/h/test3.xml");
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

