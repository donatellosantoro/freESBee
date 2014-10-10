package test.it.unibas.freesbee.ge.comunicazione.h;

import it.unibas.freesbee.ge.messaggi.XmlJDomUtil;
import it.unibas.freesbee.ge.modello.CategoriaEventiInterna;
import it.unibas.freesbee.ge.modello.FiltroData;
import it.unibas.freesbee.ge.modello.FiltroPubblicatoreInterno;
import it.unibas.freesbee.ge.modello.GestoreEventi;
import it.unibas.freesbee.ge.modello.ISottoscrizione;
import it.unibas.freesbee.ge.modello.PubblicatoreInterno;
import it.unibas.freesbee.ge.modello.Sottoscrittore;
import it.unibas.freesbee.ge.modello.SottoscrizioneInterna;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiInterna;
import it.unibas.freesbee.ge.persistenza.IDAOGestoreEventi;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreInterno;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrittore;
import it.unibas.freesbee.ge.persistenza.facade.DAOGestoreEventiFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOSottoscrizioneInternaFacade;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCategoriaEventiInternaHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCostanti;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOGestoreEventiHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOPubblicatoreInternoHibernate;
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

public class Richiesta14 extends ContextTestSupport {

    protected Log logger = LogFactory.getLog(this.getClass());

    public void testRichiesta14Prima() throws Exception {
        try {
            logger.info("TEST - 14");
            logger.info("Verifica situazione sottoscrittore");

            DAOUtilHibernate.beginTransaction();

            IDAOGestoreEventi daoGestoreEventi = new DAOGestoreEventiHibernate();
            GestoreEventi gestoreEventi = daoGestoreEventi.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_GE);
            assertNotNull(gestoreEventi);

            IDAOSottoscrittore daoSottoscrittore = new DAOSottoscrittoreHibernate();
            Sottoscrittore sottoscrittore = daoSottoscrittore.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_GE);
            assertNull(sottoscrittore);

            IDAOCategoriaEventiInterna daoCategoriaEventiInterna = new DAOCategoriaEventiInternaHibernate();
            CategoriaEventiInterna categoriaEventi = daoCategoriaEventiInterna.findByNome(DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_2);
            assertNotNull(categoriaEventi);

            sottoscrittore = new Sottoscrittore(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_GE);

            IDAOPubblicatoreInterno daoPubblicatoreInterno = new DAOPubblicatoreInternoHibernate();
            PubblicatoreInterno pubblicatoreInterno = daoPubblicatoreInterno.findByTipoNome(DAOGestoreEventiFacade.getGE().getTipo(), DAOGestoreEventiFacade.getGE().getNome());

            Calendar date = new GregorianCalendar();
            date.add(Calendar.MINUTE, 2);
            FiltroData filtroData = new FiltroData(date.getTime());

            SottoscrizioneInterna sottoscrizione = new SottoscrizioneInterna(sottoscrittore, categoriaEventi, ISottoscrizione.TIPO_SOTTOSCRIZIONE_CONSEGNA, filtroData);
            FiltroPubblicatoreInterno filtroPubblicatore = new FiltroPubblicatoreInterno(pubblicatoreInterno, sottoscrizione);
            sottoscrizione.getListaFiltroPublicatore().add(filtroPubblicatore);

            DAOSottoscrizioneInternaFacade.aggiungiSottoscrizioneInterna(categoriaEventi, sottoscrittore, sottoscrizione);


            DAOUtilHibernate.commit();


        } catch (Exception ex) {
            logger.error(ex.getMessage());
            DAOUtilHibernate.rollback();
            Assert.fail(ex.getMessage());
        }
    }

    public void testRichiesta14() throws Exception {
        logger.info("TEST - 14");
        logger.info("Categoria eventi esistente - Gestore Eventi pubblicatore sottoscrittore per la catgoria attiva");

        try {
            //Carico il messaggio che verebbe consegnato al GE
            URL url = this.getClass().getResource("/dati/h/test14.xml");
            Document doc = XmlJDomUtil.caricaXML(url.getFile(), false);
            String m = XmlJDomUtil.convertiDocumentToString(doc);

            ClientPD clientPD = new ClientPD();

            String indirizzo = "http://" + ConfigurazioneStatico.getInstance().getWebservicesIndirizzo() + ":" + ConfigurazioneStatico.getInstance().getWebservicesPort() + DAOCostanti.URL_WSDL_CONSEGNA_MESSAGGI;
            logger.info("url: " + indirizzo);
            logger.debug(m);

            clientPD.invocaPortaDelegata(indirizzo, m);



        } catch (Exception ex) {
            logger.error(ex.getMessage());
            Assert.fail("L'eliminazione della sottoscrizione non ha lanciato eccezione");
        }
    }

    public void testRichiesta14Dopo() throws Exception {
        try {
            logger.info("TEST - 14");
            logger.info("Dopo");
            Thread.currentThread().sleep(5000);

            DAOUtilHibernate.beginTransaction();

            IDAOGestoreEventi daoGestoreEventi = new DAOGestoreEventiHibernate();
            GestoreEventi gestoreEventi = daoGestoreEventi.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_GE);
            assertNotNull(gestoreEventi);

            IDAOSottoscrittore daoSottoscrittore = new DAOSottoscrittoreHibernate();
            Sottoscrittore sottoscrittore = daoSottoscrittore.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_GE);
            assertNull(sottoscrittore);

            IDAOCategoriaEventiInterna daoCategoriaEventiInterna = new DAOCategoriaEventiInternaHibernate();
            CategoriaEventiInterna categoriaEventi = daoCategoriaEventiInterna.findByNome(DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_2);
            assertNotNull(categoriaEventi);

            DAOUtilHibernate.commit();


        } catch (Exception ex) {
            logger.error(ex.getMessage());
            DAOUtilHibernate.rollback();
            Assert.fail(ex.getMessage());
        }
    }
}

