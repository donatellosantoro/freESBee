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

public class Eliminazione2 extends ContextTestSupport {

    protected Log logger = LogFactory.getLog(this.getClass());

    public void testEliminazione2Init() throws Exception {
        logger.info("TEST - 2");
        logger.info("Init");

        DAOUtilHibernate.beginTransaction();
        IDAOPubblicatoreEsterno daoPubblicatoreEsterno = new DAOPubblicatoreEsternoHibernate();

        PubblicatoreEsterno pubblicatore = daoPubblicatoreEsterno.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_B);
        assertNull(pubblicatore);

        DAOMock.inserisciPubblicatoreEsterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_B, DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_7);
        DAOMock.inserisciPubblicatoreEsterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_B, DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_9);

        pubblicatore = daoPubblicatoreEsterno.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_B);

        CategoriaEventiEsterna categoriaEventi7 = DAOCategoriaEventiEsternaFacade.verificaEsistenzaCategoriaEventiEsternaAttavia(DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_7);
        DAOPubblicatoreEsternoFacade.verificaEsistenzaPubblicatoreEsterno(categoriaEventi7, pubblicatore);

        CategoriaEventiEsterna categoriaEventi9 = DAOCategoriaEventiEsternaFacade.verificaEsistenzaCategoriaEventiEsternaAttavia(DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_9);
        DAOPubblicatoreEsternoFacade.verificaEsistenzaPubblicatoreEsterno(categoriaEventi9, pubblicatore);

        IDAOSottoscrittore daoSottoscrittore = new DAOSottoscrittoreHibernate();
        Sottoscrittore sottoscrittore = daoSottoscrittore.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_E);
        assertNull(sottoscrittore);

        sottoscrittore = new Sottoscrittore(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_E);

        Calendar dataInizio = new GregorianCalendar();
        dataInizio.add(Calendar.MINUTE, 2);

        FiltroData filtroData7 = new FiltroData(dataInizio.getTime());
        FiltroData filtroData9 = new FiltroData(dataInizio.getTime());

        SottoscrizioneEsterna sottoscrizione7 = new SottoscrizioneEsterna(sottoscrittore, categoriaEventi7, ISottoscrizione.TIPO_SOTTOSCRIZIONE_CONSEGNA, filtroData7);
        FiltroPubblicatoreEsterno filtroPubblicatore7 = new FiltroPubblicatoreEsterno(pubblicatore, sottoscrizione7);
        sottoscrizione7.getListaFiltroPublicatore().add(filtroPubblicatore7);

        DAOSottoscrizioneEsternaFacade.aggiungiSottoscrizioneEsterna(categoriaEventi7, sottoscrittore, sottoscrizione7);

        SottoscrizioneEsterna sottoscrizione9 = new SottoscrizioneEsterna(sottoscrittore, categoriaEventi9, ISottoscrizione.TIPO_SOTTOSCRIZIONE_CONSEGNA, filtroData9);
        FiltroPubblicatoreEsterno filtroPubblicatore9 = new FiltroPubblicatoreEsterno(pubblicatore, sottoscrizione9);
        sottoscrizione9.getListaFiltroPublicatore().add(filtroPubblicatore9);

        DAOSottoscrizioneEsternaFacade.aggiungiSottoscrizioneEsterna(categoriaEventi9, sottoscrittore, sottoscrizione9);

        DAOUtilHibernate.commit();

    }

    public void testEliminazione2() throws Exception {
        logger.info("TEST - 2");
        logger.info("Pubblicatore esistene pubblicatore confermato per la categoria - Categoria eventi esterna confermata attiva");

        try {
            //Carico il messaggio che verebbe consegnato al GE
            URL url = this.getClass().getResource("/dati/n/test2.xml");
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

    public void testEliminazione2Dopo() throws Exception {
        logger.info("TEST - 2");
        logger.info("Dopo");

        DAOUtilHibernate.beginTransaction();
        IDAOPubblicatoreEsterno daoPubblicatoreEsterno = new DAOPubblicatoreEsternoHibernate();
        PubblicatoreEsterno pubblicatore = daoPubblicatoreEsterno.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_B);
        assertNotNull(pubblicatore);

        CategoriaEventiEsterna categoriaEventi7 = DAOCategoriaEventiEsternaFacade.verificaEsistenzaCategoriaEventiEsternaAttavia(DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_7);
        DAOPubblicatoreEsternoFacade.verificaNonEsistenzaPubblicatoreEsterno(categoriaEventi7, pubblicatore);

        CategoriaEventiEsterna categoriaEventi9 = DAOCategoriaEventiEsternaFacade.verificaEsistenzaCategoriaEventiEsternaAttavia(DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_9);
        DAOPubblicatoreEsternoFacade.verificaEsistenzaPubblicatoreEsterno(categoriaEventi9, pubblicatore);

        IDAOSottoscrittore daoSottoscrittore = new DAOSottoscrittoreHibernate();
        Sottoscrittore sottoscrittore = daoSottoscrittore.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_E);
        assertNotNull(sottoscrittore);

        IDAOCategoriaEventiEsterna daoCategoriaEventi = new DAOCategoriaEventiEsternaHibernate();

        SottoscrizioneEsterna sottoscrizione7 = daoCategoriaEventi.findByCategoriaEventiSottoscrittore(categoriaEventi7, sottoscrittore);
        assertNull(sottoscrizione7);

        SottoscrizioneEsterna sottoscrizione9 = daoCategoriaEventi.findByCategoriaEventiSottoscrittore(categoriaEventi9, sottoscrittore);
        assertNotNull(sottoscrizione9);
        assertEquals(1, sottoscrizione9.getListaFiltroPublicatore().size());
        assertTrue(pubblicatore.compareTo(sottoscrizione9.getListaFiltroPublicatore().get(0).getPubblicatore()) == 0);
        DAOUtilHibernate.commit();

    }
}

