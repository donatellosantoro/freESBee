package test.it.unibas.freesbee.ge.comunicazione.h;

import it.unibas.freesbee.ge.messaggi.XmlJDomUtil;
import it.unibas.freesbee.ge.modello.CategoriaEventiEsterna;
import it.unibas.freesbee.ge.modello.GestoreEventi;
import it.unibas.freesbee.ge.modello.ICategoriaEventi;
import it.unibas.freesbee.ge.modello.PubblicatoreEsterno;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiEsterna;
import it.unibas.freesbee.ge.persistenza.IDAOGestoreEventi;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreEsterno;
import it.unibas.freesbee.ge.persistenza.facade.DAOPubblicatoreEsternoFacade;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCategoriaEventiEsternaHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOUtilHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCostanti;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOGestoreEventiHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOPubblicatoreEsternoHibernate;
import it.unibas.freesbee.utilita.ClientPD;
import it.unibas.springfreesbee.ge.ConfigurazioneStatico;
import java.io.FileWriter;
import java.net.URL;
import junit.framework.Assert;
import org.apache.camel.ContextTestSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import test.it.unibas.freesbee.ge.dao.DAOMock;

public class Richiesta5 extends ContextTestSupport {

    protected Log logger = LogFactory.getLog(this.getClass());
    protected static GestoreEventi ge;
    protected static CategoriaEventiEsterna categoiraEventiGE;

    public void testRichiesta5Init() throws Exception {
        try {
            DAOUtilHibernate.beginTransaction();
            logger.info("TEST - 5");
            logger.info("Init");

            FileWriter f = new FileWriter(DAOMock.CONSEGNA);
            String s = " ";
            f.append(s.subSequence(0, s.length()));
            f.flush();

            DAOUtilHibernate.beginTransaction();

            IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna = new DAOCategoriaEventiEsternaHibernate();

            categoiraEventiGE = daoCategoriaEventiEsterna.findByNome(ICategoriaEventi.GE_CONTROL_PROTOCOL);
            assertNotNull(categoiraEventiGE);

            IDAOGestoreEventi daoGestoreEventi = new DAOGestoreEventiHibernate();
            ge = daoGestoreEventi.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_GE);
            assertNotNull(ge);

            IDAOPubblicatoreEsterno daoPubblicatoreEsterno = new DAOPubblicatoreEsternoHibernate();
            PubblicatoreEsterno pubblicatoreEsterno = daoPubblicatoreEsterno.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_GE);

            DAOPubblicatoreEsternoFacade.verificaEsistenzaPubblicatoreEsterno(categoiraEventiGE, pubblicatoreEsterno);


            DAOUtilHibernate.commit();

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            DAOUtilHibernate.rollback();
            Assert.fail("L'eliminazione del pubblicatore ha lanciato eccezione");

        }
    }

    public void testRichiesta5() throws Exception {
        logger.info("TEST - 5");
        logger.info("Pubblicazione della Richiesta di Rimozione della sottoscrizione di un gestore eventi");

        try {
            Thread.currentThread().sleep(10000);

            //Creo lo stesso messaggi che invia il ClientGEControlProtocol
            URL url = this.getClass().getResource("/dati/h/test5.xml");
            org.jdom.Document doc = XmlJDomUtil.caricaXML(url.getFile(), false);
            String messaggio = XmlJDomUtil.convertiDocumentToString(doc);

            ClientPD clientPD = new ClientPD();

            String indirizzo = "http://" + ConfigurazioneStatico.getInstance().getWebservicesIndirizzo() + ":" + ConfigurazioneStatico.getInstance().getWebservicesPort() + DAOCostanti.URL_WSDL_GESTORE_EVENTI;
            logger.info("url: " + indirizzo);
            logger.debug(messaggio);

            clientPD.invocaPortaDelegata(indirizzo, messaggio);

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            Assert.fail("L'eliminazione della sottoscrizione ha lanciato eccezione");


        }
    }

    public void testRichiesta5VerificaConsegnaComunicazione() throws Exception {
        try {
            logger.info("TEST - 5");
            logger.info("Verifica Consegna Comunicazione");
            Thread.currentThread().sleep(5000);
            //Comunicazione inviata
            org.jdom.Document doc = XmlJDomUtil.caricaXML(DAOMock.CONSEGNA, false);
            String s = XmlJDomUtil.convertiDocumentToString(doc);

            //Comunicazione di test
            URL url = this.getClass().getResource("/dati/h/test5Consegna.xml");
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

