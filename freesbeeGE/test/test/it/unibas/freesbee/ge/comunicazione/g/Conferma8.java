package test.it.unibas.freesbee.ge.comunicazione.g;

import it.unibas.freesbee.ge.messaggi.XmlJDomUtil;
import it.unibas.freesbee.ge.modello.CategoriaEventiInterna;
import it.unibas.freesbee.ge.modello.Configurazione;
import it.unibas.freesbee.ge.modello.ConfigurazioniFactory;
import it.unibas.freesbee.ge.modello.GestoreEventi;
import it.unibas.freesbee.ge.modello.PubblicatoreInterno;
import it.unibas.freesbee.ge.modello.Sottoscrittore;
import it.unibas.freesbee.ge.modello.SottoscrizioneInterna;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiInterna;
import it.unibas.freesbee.ge.persistenza.IDAOGestoreEventi;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreInterno;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrittore;
import it.unibas.freesbee.ge.persistenza.facade.DAOPubblicatoreInternoFacade;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCategoriaEventiInternaHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCostanti;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOGestoreEventiHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOPubblicatoreInternoHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOSottoscrittoreHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOUtilHibernate;
import it.unibas.freesbee.utilita.ClientPD;
import it.unibas.springfreesbee.ge.ConfigurazioneStatico;
import java.io.FileWriter;
import java.net.URL;
import junit.framework.Assert;
import org.apache.camel.ContextTestSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import test.it.unibas.freesbee.ge.dao.DAOMock;

public class Conferma8 extends ContextTestSupport {

    protected Log logger = LogFactory.getLog(this.getClass());

    public void testConferma8Init() throws Exception {
        logger.info("TEST - 8");
        logger.info("Init");
        Thread.currentThread().sleep(5000);
        FileWriter f = new FileWriter(DAOMock.COMUNICAZIONE_ESTERNA);
        String s = "";
        f.append(s.subSequence(0, s.length()));
        f.flush();

    }

    public void test8Prima() throws Exception {
        logger.info("TEST - 8");
        logger.info("Prima");

        DAOUtilHibernate.beginTransaction();
        IDAOCategoriaEventiInterna daoCategoriaEventi = new DAOCategoriaEventiInternaHibernate();
        CategoriaEventiInterna categoriaEventi = daoCategoriaEventi.findByNome(DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_2);
        assertNotNull(categoriaEventi);
        assertTrue(categoriaEventi.isAttiva());

        PubblicatoreInterno pubblicatore = new PubblicatoreInterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_P);
        DAOPubblicatoreInternoFacade.aggiungiPubblicatoreInterno(categoriaEventi, pubblicatore);
        assertTrue(DAOPubblicatoreInternoFacade.isPubblicatoreInternoPerCategoriaEventiInterna(categoriaEventi, pubblicatore));

        pubblicatore = new PubblicatoreInterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_Q);
        DAOPubblicatoreInternoFacade.aggiungiPubblicatoreInterno(categoriaEventi, pubblicatore);
        assertTrue(DAOPubblicatoreInternoFacade.isPubblicatoreInternoPerCategoriaEventiInterna(categoriaEventi, pubblicatore));

        IDAOGestoreEventi daoGestoreEventi = new DAOGestoreEventiHibernate();
        GestoreEventi gesoreEventi = daoGestoreEventi.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_GE);
        assertNotNull(gesoreEventi);

        IDAOSottoscrittore daoSottoscrittore = new DAOSottoscrittoreHibernate();
        Sottoscrittore sottoscrittoreGE = daoSottoscrittore.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_GE);
        assertNull(sottoscrittoreGE);

        assertNull(daoCategoriaEventi.findByCategoriaEventiSottoscrittore(categoriaEventi, sottoscrittoreGE));

        DAOUtilHibernate.commit();
    }

    public void testConferma8() throws Exception {
        logger.info("TEST - 8");
        logger.info("Ricezione Richeista Conferma di Pubblicatori corretta");
        try {
            //Carico il messaggio che verebbe generato dal GE
            URL url = this.getClass().getResource("/dati/g/test8.xml");
            Document doc = XmlJDomUtil.caricaXML(url.getFile(), false);
            String m = XmlJDomUtil.convertiDocumentToString(doc);

            ClientPD clientPD = new ClientPD();

            String indirizzo = "http://" + ConfigurazioneStatico.getInstance().getWebservicesIndirizzo() + ":" + ConfigurazioneStatico.getInstance().getWebservicesPort() + DAOCostanti.URL_WSDL_CONSEGNA_MESSAGGI;
            logger.info("url: " + indirizzo);
            logger.debug(m);

            clientPD.invocaPortaDelegata(indirizzo, m);


        } catch (Exception ex) {
            logger.error(ex.getMessage());
            Assert.fail("La conferma dei pubblicvatori categoria ha lanciato eccezione");
        }
    }

    public void testInserimento8VerificaComunicazioneEsterna() throws Exception {
        try {
            logger.info("TEST - 8");
            logger.info("Verifica Comunicazione Esterna Conferma pubblicatori");
            Thread.currentThread().sleep(5000);
            //Comunicazione inviata
            org.jdom.Document doc = XmlJDomUtil.caricaXML(DAOMock.COMUNICAZIONE_ESTERNA, false);
            String s = XmlJDomUtil.convertiDocumentToString(doc);

            //Comunicazione di test
            URL url = this.getClass().getResource("/dati/g/test8Esterno.xml");
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

    public void test8Dopo() throws Exception {
        logger.info("TEST - 8");
        logger.info("Dopo");
        DAOUtilHibernate.beginTransaction();

        IDAOCategoriaEventiInterna daoCategoriaEventiInterna = new DAOCategoriaEventiInternaHibernate();
        CategoriaEventiInterna categoriaEventi = daoCategoriaEventiInterna.findByNome(DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_2);
        assertNotNull(categoriaEventi);
        assertTrue(categoriaEventi.isAttiva());

        IDAOGestoreEventi daoGestoreEventi = new DAOGestoreEventiHibernate();
        GestoreEventi gesoreEventi = daoGestoreEventi.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_GE);
        assertNotNull(gesoreEventi);

        Configurazione conf = ConfigurazioniFactory.getConfigurazioneIstance();
        IDAOPubblicatoreInterno daoPubblicatoreInterno = new DAOPubblicatoreInternoHibernate();
        PubblicatoreInterno pubblicatore = daoPubblicatoreInterno.findByTipoNome(conf.getTipoGestoreEventi(), conf.getNomeGestoreEventi());

        DAOPubblicatoreInternoFacade.verificaEsistenzaPubblicatoreInterno(categoriaEventi, pubblicatore);


        IDAOSottoscrittore daoSottoscrittore = new DAOSottoscrittoreHibernate();

        Sottoscrittore sottoscrittoreGE = daoSottoscrittore.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_GE);
        assertNotNull(sottoscrittoreGE);

        SottoscrizioneInterna sottoscrizione = daoCategoriaEventiInterna.findByCategoriaEventiSottoscrittore(categoriaEventi, sottoscrittoreGE);
        assertNotNull(sottoscrizione);

        assertEquals(1, sottoscrizione.getListaFiltroPublicatore().size());

        assertTrue(sottoscrizione.getListaFiltroPublicatore().get(0).getPubblicatore().compareTo(pubblicatore) == 0);
        DAOUtilHibernate.commit();
    }
}

