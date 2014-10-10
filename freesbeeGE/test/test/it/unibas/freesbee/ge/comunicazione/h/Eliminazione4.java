package test.it.unibas.freesbee.ge.comunicazione.h;

import it.unibas.freesbee.ge.messaggi.XmlJDomUtil;
import it.unibas.freesbee.ge.modello.CategoriaEventiEsterna;
import it.unibas.freesbee.ge.modello.FiltroData;
import it.unibas.freesbee.ge.modello.FiltroPubblicatoreEsterno;
import it.unibas.freesbee.ge.modello.ISottoscrizione;
import it.unibas.freesbee.ge.modello.PubblicatoreEsterno;
import it.unibas.freesbee.ge.modello.Sottoscrittore;
import it.unibas.freesbee.ge.modello.SottoscrizioneEsterna;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreEsterno;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrittore;
import it.unibas.freesbee.ge.persistenza.facade.DAOCategoriaEventiEsternaFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOPubblicatoreEsternoFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOSottoscrizioneEsternaFacade;
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

public class Eliminazione4 extends ContextTestSupport {

    protected Log logger = LogFactory.getLog(this.getClass());

    public void testEliminazione4Init() throws Exception {
        logger.info("TEST - 4");
        logger.info("Init");

        FileWriter f = new FileWriter(DAOMock.COMUNICAZIONE_ESTERNA_1);
        String s = " ";
        f.append(s.subSequence(0, s.length()));
        f.flush();

    
        DAOUtilHibernate.beginTransaction();
        PubblicatoreEsterno pubblicatoreQ = null;
    
        CategoriaEventiEsterna categoriaEventi = DAOCategoriaEventiEsternaFacade.verificaEsistenzaCategoriaEventiEsternaAttavia(DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_9);

        pubblicatoreQ = new PubblicatoreEsterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_Q);
        
        DAOPubblicatoreEsternoFacade.aggiungiPubblicatoreEsterno(categoriaEventi, pubblicatoreQ);
        
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
        DAOSottoscrizioneEsternaFacade.verificaEsistenzaSottoscrittoreSottoscrizioneEsterna(categoriaEventi, sottoscrittoreM);
        DAOSottoscrizioneEsternaFacade.verificaEsistenzaSottoscrittoreSottoscrizioneEsterna(categoriaEventi, sottoscrittoreN);
        assertFalse(categoriaEventi.isInAttesa());
        DAOUtilHibernate.commit();



    }

    public void testEliminazione4Pubblicazione() throws Exception {
        logger.info("TEST - 4");
        logger.info("Pubblicatore esistene pubblicatore per la categoria - Categoria eventi esterna confermata attiva");

        try {
            //Carico il messaggio che verebbe consegnato al GE
            URL url = this.getClass().getResource("/dati/d/test26.xml");
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

    public void testEliminazione4Dopo() throws Exception {
        try {
            logger.info("TEST - 4");
            logger.info("Verifica situazione pubblicatore e sottoscrittori");
            Thread.currentThread().sleep(5000);
            DAOUtilHibernate.beginTransaction();

            IDAOPubblicatoreEsterno daoPubblicatoreEsterno = new DAOPubblicatoreEsternoHibernate();
            IDAOSottoscrittore daoSottoscrittore = new DAOSottoscrittoreHibernate();

            PubblicatoreEsterno pubblicatore = daoPubblicatoreEsterno.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_Q);
            assertNull(pubblicatore);

            CategoriaEventiEsterna categoria = DAOCategoriaEventiEsternaFacade.verificaEsistenzaCategoriaEventiEsternaAttavia(DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_9);
            
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

        public void testEliminazione4VerificaComunicazioneEsterna1() throws Exception {
        try {
            logger.info("TEST - 4");
            logger.info("Verifica Comunicazione Esterna richiesta eliminizazione sottoscrizione");
            Thread.currentThread().sleep(5000);

            //Comunicazione inviata
            org.jdom.Document doc = XmlJDomUtil.caricaXML(DAOMock.COMUNICAZIONE_ESTERNA_1, false);
            String s = XmlJDomUtil.convertiDocumentToString(doc);

            //Comunicazione di test
            URL url = this.getClass().getResource("/dati/h/test4.xml");
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

