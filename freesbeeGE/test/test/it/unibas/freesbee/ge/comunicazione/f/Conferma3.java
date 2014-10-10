package test.it.unibas.freesbee.ge.comunicazione.f;

import it.unibas.freesbee.ge.messaggi.XmlJDomUtil;
import it.unibas.freesbee.ge.modello.CategoriaEventiInterna;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiInterna;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCategoriaEventiInternaHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCostanti;
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

public class Conferma3 extends ContextTestSupport {

    protected Log logger = LogFactory.getLog(this.getClass());

    public void testInserimento3Init() throws Exception {
        logger.info("TEST - 3");
        logger.info("Init");
        Thread.currentThread().sleep(5000);
        FileWriter f = new FileWriter(DAOMock.COMUNICAZIONE_ESTERNA);
        String s = "<root><ok/></root>";
        f.append(s.subSequence(0, s.length()));
        f.flush();

    }

    public void testConferma3Prima() throws Exception {
        logger.info("TEST - 3");
        logger.info("Prima");

        DAOUtilHibernate.beginTransaction();
        IDAOCategoriaEventiInterna daoCategoriaEventiInterna = new DAOCategoriaEventiInternaHibernate();
        CategoriaEventiInterna categoriaEventi = daoCategoriaEventiInterna.findByNome(DAOMock.CATEGORIA_EVENTI_NON_ESISTENTE_27);
        assertNull(categoriaEventi);
        DAOUtilHibernate.commit();
    }

    public void testConferma3() throws Exception {
        logger.info("TEST - 3");
        logger.info("Ricezione Richiesta Conferma Categoria Eventi non esistente");
        try {
            //Carico il messaggio che verebbe generato dal GE
            URL url = this.getClass().getResource("/dati/f/test3.xml");
            Document doc = XmlJDomUtil.caricaXML(url.getFile(), false);
            String m = XmlJDomUtil.convertiDocumentToString(doc);

            ClientPD clientPD = new ClientPD();

            String indirizzo = "http://" + ConfigurazioneStatico.getInstance().getWebservicesIndirizzo() + ":" + ConfigurazioneStatico.getInstance().getWebservicesPort() + DAOCostanti.URL_WSDL_CONSEGNA_MESSAGGI;
            logger.info("url: " + indirizzo);
            logger.debug(m);

            clientPD.invocaPortaDelegata(indirizzo, m);

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            Assert.fail("La conferma della categoria ha lanciato eccezione");
        }
    }

    public void testInserimento3VerificaComunicazioneEsterna() throws Exception {
        try {
            logger.info("TEST - 3");
            logger.info("Verifica Comunicazione Esterna richiesta informazioni categoria eventi");
            Thread.currentThread().sleep(5000);
            //Comunicazione inviata
            org.jdom.Document doc = XmlJDomUtil.caricaXML(DAOMock.COMUNICAZIONE_ESTERNA, false);
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

