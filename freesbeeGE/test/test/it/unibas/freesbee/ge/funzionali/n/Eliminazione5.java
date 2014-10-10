package test.it.unibas.freesbee.ge.funzionali.n;

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
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import junit.framework.Assert;
import org.apache.camel.ContextTestSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import test.it.unibas.freesbee.ge.dao.DAOMock;

public class Eliminazione5 extends ContextTestSupport {

    protected Log logger = LogFactory.getLog(this.getClass());
 
    public void testEliminazione5Init() throws Exception {
        logger.info("TEST - 5");
        logger.info("Init");

        DAOUtilHibernate.beginTransaction();
        IDAOPubblicatoreEsterno daoPubblicatoreEsterno = new DAOPubblicatoreEsternoHibernate();

        PubblicatoreEsterno pubblicatore = daoPubblicatoreEsterno.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_W);
        assertNull(pubblicatore);

        DAOMock.inserisciPubblicatoreEsterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_W, DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_9);

        pubblicatore = daoPubblicatoreEsterno.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_W);

        CategoriaEventiEsterna categoriaEventi6 = DAOCategoriaEventiEsternaFacade.verificaEsistenzaCategoriaEventiEsternaAttavia(DAOMock.CATEGORIA_EVENTI_ESTERNA_NON_CONFERMATA_ATTIVA_6);

        CategoriaEventiEsterna categoriaEventi9 = DAOCategoriaEventiEsternaFacade.verificaEsistenzaCategoriaEventiEsternaAttavia(DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_9);
        DAOPubblicatoreEsternoFacade.verificaEsistenzaPubblicatoreEsterno(categoriaEventi9, pubblicatore);

        IDAOSottoscrittore daoSottoscrittore = new DAOSottoscrittoreHibernate();
        Sottoscrittore sottoscrittore = daoSottoscrittore.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_O);
        assertNull(sottoscrittore);

        sottoscrittore = new Sottoscrittore(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_O);

        Calendar dataInizio = new GregorianCalendar();
        dataInizio.add(Calendar.MINUTE, 2);

        FiltroData filtroData6 = new FiltroData(dataInizio.getTime());
        FiltroData filtroData9 = new FiltroData(dataInizio.getTime());

        SottoscrizioneEsterna sottoscrizione6 = new SottoscrizioneEsterna(sottoscrittore, categoriaEventi6, ISottoscrizione.TIPO_SOTTOSCRIZIONE_CONSEGNA, filtroData6);
        FiltroPubblicatoreEsterno filtroPubblicatore6 = new FiltroPubblicatoreEsterno(pubblicatore, sottoscrizione6);
        sottoscrizione6.getListaFiltroPublicatore().add(filtroPubblicatore6);

        DAOSottoscrizioneEsternaFacade.aggiungiSottoscrizioneEsterna(categoriaEventi6, sottoscrittore, sottoscrizione6);

        DAOPubblicatoreEsternoFacade.verificaNonEsistenzaPubblicatoreEsterno(categoriaEventi6, pubblicatore);
        assertTrue(DAOPubblicatoreEsternoFacade.isPubblicatoreEsternoDaConfermare(categoriaEventi6, pubblicatore));

        SottoscrizioneEsterna sottoscrizione9 = new SottoscrizioneEsterna(sottoscrittore, categoriaEventi9, ISottoscrizione.TIPO_SOTTOSCRIZIONE_CONSEGNA, filtroData9);
        FiltroPubblicatoreEsterno filtroPubblicatore9 = new FiltroPubblicatoreEsterno(pubblicatore, sottoscrizione9);
        sottoscrizione9.getListaFiltroPublicatore().add(filtroPubblicatore9);

        DAOSottoscrizioneEsternaFacade.aggiungiSottoscrizioneEsterna(categoriaEventi9, sottoscrittore, sottoscrizione9);

        DAOUtilHibernate.commit();

    }

    public void testEliminazione5() throws Exception {
        logger.info("TEST - 5");
        logger.info("Pubblicatore esistene pubblicatore da confermare per la categoria - Categoria eventi esterna non confermata attiva");

        try {
            //Carico il messaggio che verebbe consegnato al GE
            URL url = this.getClass().getResource("/dati/n/test5.xml");
            Document doc = XmlJDomUtil.caricaXML(url.getFile(), false);
            String m = XmlJDomUtil.convertiDocumentToString(doc);

            ClientPD clientPD = new ClientPD();

            String indirizzo = "http://" + ConfigurazioneStatico.getInstance().getWebservicesIndirizzo() + ":" + ConfigurazioneStatico.getInstance().getWebservicesPort() + DAOCostanti.URL_WSDL_CONSEGNA_MESSAGGI;
            logger.info("url: " + indirizzo);
            logger.debug(m);

            String ris = clientPD.invocaPortaDelegata(indirizzo, m);

            logger.debug(XmlJDomUtil.formattaXML(ris));
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            Assert.fail("L'eliminazione del pubblicatore ha lanciato eccezione");

        }
    }

    public void testEliminazione5Dopo() throws Exception {
        logger.info("TEST - 5");
        logger.info("Dopo");

        DAOUtilHibernate.beginTransaction();
        IDAOPubblicatoreEsterno daoPubblicatoreEsterno = new DAOPubblicatoreEsternoHibernate();
        PubblicatoreEsterno pubblicatore = daoPubblicatoreEsterno.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_W);
assertNotNull(pubblicatore);

        CategoriaEventiEsterna categoriaEventi6 = DAOCategoriaEventiEsternaFacade.verificaEsistenzaCategoriaEventiEsternaAttavia(DAOMock.CATEGORIA_EVENTI_ESTERNA_NON_CONFERMATA_ATTIVA_6);
        DAOPubblicatoreEsternoFacade.verificaNonEsistenzaPubblicatoreEsterno(categoriaEventi6, pubblicatore);
        assertTrue(DAOPubblicatoreEsternoFacade.isPubblicatoreEsternoDaConfermare(categoriaEventi6, pubblicatore));

        CategoriaEventiEsterna categoriaEventi9 = DAOCategoriaEventiEsternaFacade.verificaEsistenzaCategoriaEventiEsternaAttavia(DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_9);
        DAOPubblicatoreEsternoFacade.verificaNonEsistenzaPubblicatoreEsterno(categoriaEventi9, pubblicatore);

    IDAOSottoscrittore daoSottoscrittore = new DAOSottoscrittoreHibernate();
        Sottoscrittore sottoscrittore = daoSottoscrittore.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_O);
      assertNotNull(sottoscrittore);

        IDAOCategoriaEventiEsterna daoCategoriaEventi = new DAOCategoriaEventiEsternaHibernate();

        SottoscrizioneEsterna sottoscrizione9 = daoCategoriaEventi.findByCategoriaEventiSottoscrittore(categoriaEventi9, sottoscrittore);
        assertNull(sottoscrizione9);

        SottoscrizioneEsterna sottoscrizione6= daoCategoriaEventi.findByCategoriaEventiSottoscrittore(categoriaEventi6, sottoscrittore);
        assertNotNull(sottoscrizione6);
        assertEquals(1, sottoscrizione6.getListaFiltroPublicatore().size());
        assertTrue(pubblicatore.compareTo(sottoscrizione6.getListaFiltroPublicatore().get(0).getPubblicatore()) == 0);
        DAOUtilHibernate.commit();

    }


}

