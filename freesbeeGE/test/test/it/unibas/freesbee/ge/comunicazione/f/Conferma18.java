package test.it.unibas.freesbee.ge.comunicazione.f;

import it.unibas.freesbee.ge.modello.CategoriaEventiEsterna;
import it.unibas.freesbee.ge.modello.FiltroData;
import it.unibas.freesbee.ge.modello.ISottoscrizione;
import it.unibas.freesbee.ge.modello.Sottoscrittore;
import it.unibas.freesbee.ge.modello.SottoscrizioneEsterna;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiEsterna;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrittore;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrizioneEsterna;
import it.unibas.freesbee.ge.persistenza.facade.DAOSottoscrizioneEsternaFacade;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCategoriaEventiEsternaHibernate;
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

public class Conferma18 extends ContextTestSupport {

    protected Log logger = LogFactory.getLog(this.getClass());

    public void testConferma18Prima() throws Exception {
        logger.info("TEST - 18");
        logger.info("Prima");

        DAOUtilHibernate.beginTransaction();
        IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna = new DAOCategoriaEventiEsternaHibernate();
        CategoriaEventiEsterna categoriaEventi = daoCategoriaEventiEsterna.findByNome(DAOMock.CATEGORIA_EVENTI_ESTERNA_NON_CONFERMATA_ATTIVA_31);

        Sottoscrittore sottoscrittore = new Sottoscrittore();
        sottoscrittore.setTipo(DAOMock.TIPO_SOGGETTO_SPC);
        sottoscrittore.setNome(DAOMock.NOME_SOGGETTO_I);

        Calendar date = new GregorianCalendar();
        date.add(Calendar.MINUTE, 2);
        FiltroData filtroData = new FiltroData(date.getTime());

        SottoscrizioneEsterna sottoscrizioneEsterna = new SottoscrizioneEsterna(sottoscrittore, categoriaEventi, ISottoscrizione.TIPO_SOTTOSCRIZIONE_CONSEGNA, filtroData);

        DAOSottoscrizioneEsternaFacade.aggiungiSottoscrizioneEsterna(categoriaEventi, sottoscrittore, sottoscrizioneEsterna);

        assertTrue(categoriaEventi.isAttiva());
        assertTrue(categoriaEventi.isInAttesa());

        date.add(Calendar.MINUTE, -2);
        sottoscrizioneEsterna.setScadenzaAttesa(date.getTime());
        IDAOSottoscrizioneEsterna daoSottoscrizioneEsterna = new DAOSottoscrizioneEsternaHibernate();
        daoSottoscrizioneEsterna.makePersistent(sottoscrizioneEsterna);

        DAOSottoscrizioneEsternaFacade.verificaEsistenzaSottoscrittoreSottoscrizioneEsterna(categoriaEventi, sottoscrittore);
        assertTrue(DAOSottoscrizioneEsternaFacade.isInAttesaConfermaCategoriaEventi(sottoscrizioneEsterna));
        assertFalse(DAOSottoscrizioneEsternaFacade.isInAttesaConfermaPubblicatori(sottoscrizioneEsterna));
        sottoscrizioneEsterna = daoCategoriaEventiEsterna.findByCategoriaEventiSottoscrittore(categoriaEventi, sottoscrittore);


        sottoscrittore = new Sottoscrittore();
        sottoscrittore.setTipo(DAOMock.TIPO_SOGGETTO_SPC);
        sottoscrittore.setNome(DAOMock.NOME_SOGGETTO_L);

        date = new GregorianCalendar();
        date.add(Calendar.MINUTE, 1);
        filtroData = new FiltroData(date.getTime());

        sottoscrizioneEsterna = new SottoscrizioneEsterna(sottoscrittore, categoriaEventi, ISottoscrizione.TIPO_SOTTOSCRIZIONE_CONSEGNA, filtroData);

        DAOSottoscrizioneEsternaFacade.aggiungiSottoscrizioneEsterna(categoriaEventi, sottoscrittore, sottoscrizioneEsterna);

        date.add(Calendar.MINUTE, 2);
        sottoscrizioneEsterna.setScadenzaAttesa(date.getTime());

        daoSottoscrizioneEsterna.makePersistent(sottoscrizioneEsterna);

        assertTrue(categoriaEventi.isAttiva());
        assertTrue(categoriaEventi.isInAttesa());

        DAOSottoscrizioneEsternaFacade.verificaEsistenzaSottoscrittoreSottoscrizioneEsterna(categoriaEventi, sottoscrittore);
        assertTrue(DAOSottoscrizioneEsternaFacade.isInAttesaConfermaCategoriaEventi(sottoscrizioneEsterna));
        assertFalse(DAOSottoscrizioneEsternaFacade.isInAttesaConfermaPubblicatori(sottoscrizioneEsterna));
        sottoscrizioneEsterna = daoCategoriaEventiEsterna.findByCategoriaEventiSottoscrittore(categoriaEventi, sottoscrittore);

        DAOUtilHibernate.commit();
    }

    public void testConferma18() throws Exception {
        logger.info("TEST - 18");
        logger.info("Non si è Ricevuta nessuna Confemra per la categoria di eventi e si eliminao le sottoscrizioni in attesa che sono scadute");
        Thread.currentThread().sleep(5000);
        DemoneRipulituraInAttesa demone = new DemoneRipulituraInAttesa(new DAOSottoscrizioneEsternaHibernate());
        Thread.currentThread().sleep(10000);
    }

    public void testConferma18Dopo() {
        try {
            logger.info("TEST - 18");
            logger.info("Dopo");

            DAOUtilHibernate.beginTransaction();
            IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna = new DAOCategoriaEventiEsternaHibernate();
            CategoriaEventiEsterna categoriaEventi = daoCategoriaEventiEsterna.findByNome(DAOMock.CATEGORIA_EVENTI_ESTERNA_NON_CONFERMATA_ATTIVA_31);
            assertTrue(categoriaEventi.isInAttesa());
            assertTrue(categoriaEventi.isAttiva());

            IDAOSottoscrittore daoSottoscrittore = new DAOSottoscrittoreHibernate();
            Sottoscrittore sottoscrittore = daoSottoscrittore.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_I);
            assertNull(sottoscrittore);

            sottoscrittore = daoSottoscrittore.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_L);
            assertNotNull(sottoscrittore);

            SottoscrizioneEsterna sottoscrizione = daoCategoriaEventiEsterna.findByCategoriaEventiSottoscrittore(categoriaEventi, sottoscrittore);
            assertNotNull(sottoscrizione);
            assertTrue(DAOSottoscrizioneEsternaFacade.isInAttesaConfermaCategoriaEventi(sottoscrizione));

            DAOUtilHibernate.commit();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            Assert.fail(ex.getMessage());
        }
    }
}

