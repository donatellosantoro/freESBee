package test.it.unibas.freesbee.ge.comunicazione.g;

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
import it.unibas.freesbee.ge.persistenza.facade.DAOPubblicatoreEsternoFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOSottoscrizioneEsternaFacade;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCategoriaEventiEsternaHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOPubblicatoreEsternoHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOSottoscrittoreHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOSottoscrizioneEsternaHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOUtilHibernate;
import it.unibas.freesbee.ge.utilita.DemoneRipulituraInAttesa;
import java.util.Calendar;
import java.util.GregorianCalendar;
import junit.framework.Assert;
import org.apache.camel.ContextTestSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import test.it.unibas.freesbee.ge.dao.DAOMock;

public class Conferma27 extends ContextTestSupport {

    protected Log logger = LogFactory.getLog(this.getClass());

    public void testConferma27Prima() throws Exception {
        logger.info("TEST - 27");
        logger.info("Prima");

        DAOUtilHibernate.beginTransaction();
        IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna = new DAOCategoriaEventiEsternaHibernate();
        CategoriaEventiEsterna categoriaEventi = daoCategoriaEventiEsterna.findByNome(DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_10);

        Sottoscrittore sottoscrittore = new Sottoscrittore();
        sottoscrittore.setTipo(DAOMock.TIPO_SOGGETTO_SPC);
        sottoscrittore.setNome(DAOMock.NOME_SOGGETTO_I);

        PubblicatoreEsterno pubblicatore = new PubblicatoreEsterno();
        pubblicatore.setTipo(DAOMock.TIPO_SOGGETTO_SPC);
        pubblicatore.setNome(DAOMock.NOME_SOGGETTO_F);

        IDAOPubblicatoreEsterno daoPubblicatoreEsterno = new DAOPubblicatoreEsternoHibernate();
        daoPubblicatoreEsterno.makePersistent(pubblicatore);


        Calendar date = new GregorianCalendar();
        date.add(Calendar.MINUTE, 2);
        FiltroData filtroData = new FiltroData(date.getTime());

        SottoscrizioneEsterna sottoscrizioneEsterna = new SottoscrizioneEsterna(sottoscrittore, categoriaEventi, ISottoscrizione.TIPO_SOTTOSCRIZIONE_CONSEGNA, filtroData);
        FiltroPubblicatoreEsterno filtroPubblicatore = new FiltroPubblicatoreEsterno(pubblicatore, sottoscrizioneEsterna);
        sottoscrizioneEsterna.getListaFiltroPublicatore().add(filtroPubblicatore);

        DAOSottoscrizioneEsternaFacade.aggiungiSottoscrizioneEsterna(categoriaEventi, sottoscrittore, sottoscrizioneEsterna);

        assertTrue(categoriaEventi.isAttiva());
        assertFalse(categoriaEventi.isInAttesa());

        date.add(Calendar.MINUTE, -2);
        sottoscrizioneEsterna.setScadenzaAttesa(date.getTime());
        IDAOSottoscrizioneEsterna daoSottoscrizioneEsterna = new DAOSottoscrizioneEsternaHibernate();
        daoSottoscrizioneEsterna.makePersistent(sottoscrizioneEsterna);

        DAOSottoscrizioneEsternaFacade.verificaEsistenzaSottoscrittoreSottoscrizioneEsterna(categoriaEventi, sottoscrittore);
        assertFalse(DAOSottoscrizioneEsternaFacade.isInAttesaConfermaCategoriaEventi(sottoscrizioneEsterna));
        assertTrue(DAOSottoscrizioneEsternaFacade.isInAttesaConfermaPubblicatori(sottoscrizioneEsterna));
        sottoscrizioneEsterna = daoCategoriaEventiEsterna.findByCategoriaEventiSottoscrittore(categoriaEventi, sottoscrittore);

        IDAOSottoscrittore daoSottoscrittore = new DAOSottoscrittoreHibernate();
        sottoscrittore = daoSottoscrittore.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_L);

        date = new GregorianCalendar();
        date.add(Calendar.MINUTE, 1);
        filtroData = new FiltroData(date.getTime());

        pubblicatore = daoPubblicatoreEsterno.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_F);


        sottoscrizioneEsterna = new SottoscrizioneEsterna(sottoscrittore, categoriaEventi, ISottoscrizione.TIPO_SOTTOSCRIZIONE_CONSEGNA, filtroData);
        filtroPubblicatore = new FiltroPubblicatoreEsterno(pubblicatore, sottoscrizioneEsterna);
        sottoscrizioneEsterna.getListaFiltroPublicatore().add(filtroPubblicatore);

        DAOSottoscrizioneEsternaFacade.aggiungiSottoscrizioneEsterna(categoriaEventi, sottoscrittore, sottoscrizioneEsterna);

        assertTrue(categoriaEventi.isAttiva());
        assertFalse(categoriaEventi.isInAttesa());

        sottoscrizioneEsterna = daoCategoriaEventiEsterna.findByCategoriaEventiSottoscrittore(categoriaEventi, sottoscrittore);
        date.add(Calendar.MINUTE, 2);
        sottoscrizioneEsterna.setScadenzaAttesa(date.getTime());

        daoSottoscrizioneEsterna.makePersistent(sottoscrizioneEsterna);

        DAOSottoscrizioneEsternaFacade.verificaEsistenzaSottoscrittoreSottoscrizioneEsterna(categoriaEventi, sottoscrittore);
        assertFalse(DAOSottoscrizioneEsternaFacade.isInAttesaConfermaCategoriaEventi(sottoscrizioneEsterna));
        assertTrue(DAOSottoscrizioneEsternaFacade.isInAttesaConfermaPubblicatori(sottoscrizioneEsterna));



        DAOUtilHibernate.commit();
    }

    public void testConferma27() throws Exception {
        logger.info("TEST - 27");
        logger.info("Non si è Ricevuta nessuna Confemra per il pubblicatore F la categoria di eventi e si eliminao le sottoscrizioni in attesa che sono scadute");
        Thread.currentThread().sleep(5000);
        DemoneRipulituraInAttesa demone = new DemoneRipulituraInAttesa(new DAOSottoscrizioneEsternaHibernate());
        Thread.currentThread().sleep(10000);
    }

    public void testConferma27Dopo() {
        try {
            logger.info("TEST - 27");
            logger.info("Dopo");

            DAOUtilHibernate.beginTransaction();
            IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna = new DAOCategoriaEventiEsternaHibernate();
            CategoriaEventiEsterna categoriaEventi = daoCategoriaEventiEsterna.findByNome(DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_10);
            assertFalse(categoriaEventi.isInAttesa());
            assertTrue(categoriaEventi.isAttiva());

            IDAOSottoscrittore daoSottoscrittore = new DAOSottoscrittoreHibernate();
            Sottoscrittore sottoscrittore = daoSottoscrittore.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_I);
            assertNull(sottoscrittore);

            sottoscrittore = daoSottoscrittore.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_L);
            assertNotNull(sottoscrittore);
            SottoscrizioneEsterna sottoscrizione = daoCategoriaEventiEsterna.findByCategoriaEventiSottoscrittore(categoriaEventi, sottoscrittore);
            assertNotNull(sottoscrizione);
            assertFalse(DAOSottoscrizioneEsternaFacade.isInAttesaConfermaCategoriaEventi(sottoscrizione));

            IDAOPubblicatoreEsterno daoPubblicatoreEsterno = new DAOPubblicatoreEsternoHibernate();
            PubblicatoreEsterno pubblicatore = daoPubblicatoreEsterno.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_F);
            assertNotNull(pubblicatore);
            assertTrue(DAOPubblicatoreEsternoFacade.isPubblicatoreEsternoDaConfermare(categoriaEventi, pubblicatore));

            DAOUtilHibernate.commit();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            Assert.fail(ex.getMessage());
        }
    }
}

