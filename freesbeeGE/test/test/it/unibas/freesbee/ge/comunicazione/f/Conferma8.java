package test.it.unibas.freesbee.ge.comunicazione.f;

import it.unibas.freesbee.ge.messaggi.XmlJDomUtil;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCostanti;
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

    public void testInserimento8Init() throws Exception {
        logger.info("TEST - 8");
        logger.info("Init");
        Thread.currentThread().sleep(5000);
        FileWriter f = new FileWriter(DAOMock.CONSEGNA);
        String s = " ";
        f.append(s.subSequence(0, s.length()));
        f.flush();
    }


    public void testConferma8() throws Exception {
        logger.info("TEST - 8");
        logger.info("Ricezione Conferma Categoria Eventi");
        try {
            //Carico il messaggio che verebbe generato dal GE
            URL url = this.getClass().getResource("/dati/f/test8.xml");
            Document doc = XmlJDomUtil.caricaXML(url.getFile(), false);
            String m = XmlJDomUtil.convertiDocumentToString(doc);

            ClientPD clientPD = new ClientPD();

            String indirizzo = "http://" + ConfigurazioneStatico.getInstance().getWebservicesIndirizzo() + ":" + ConfigurazioneStatico.getInstance().getWebservicesPort() + DAOCostanti.URL_WSDL_GESTORE_EVENTI;
            logger.info("url: " + indirizzo);
            logger.debug(m);

            clientPD.invocaPortaDelegata(indirizzo, m);

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            Assert.fail("La pubblicazione della conferma per la categoria ha lanciato eccezione");
        }
    }

    public void testConferma8VerificaConsegnaComunicazione() {
        try {
            logger.info("TEST - 8");
            logger.info("Verifica Comunicazione Interna richiesta informazioni categoria");
            Thread.currentThread().sleep(5000);
            //Comunicazione inviata
            org.jdom.Document doc = XmlJDomUtil.caricaXML(DAOMock.CONSEGNA, false);
            String s = XmlJDomUtil.convertiDocumentToString(doc);

            //Comunicazione di test
            URL url = this.getClass().getResource("/dati/f/test8Consegna.xml");
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

