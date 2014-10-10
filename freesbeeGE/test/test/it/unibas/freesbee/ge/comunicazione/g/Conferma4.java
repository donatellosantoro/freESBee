package test.it.unibas.freesbee.ge.comunicazione.g;

import it.unibas.freesbee.ge.messaggi.XmlJDomUtil;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCostanti;
import it.unibas.freesbee.utilita.ClientPD;
import it.unibas.springfreesbee.ge.ConfigurazioneStatico;
import java.net.URL;
import junit.framework.Assert;
import org.apache.camel.ContextTestSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;

public class Conferma4 extends ContextTestSupport {

    protected Log logger = LogFactory.getLog(this.getClass());

    public void testConferma4() throws Exception {
        logger.info("TEST - 4");
        logger.info("Ricezione Richiesta Conferma Pubblicatori per Categoria Eventi interna non attiva");
        try {
            //Carico il messaggio che verebbe generato dal GE
            URL url = this.getClass().getResource("/dati/g/test4.xml");
            Document doc = XmlJDomUtil.caricaXML(url.getFile(), false);
            String m = XmlJDomUtil.convertiDocumentToString(doc);

            ClientPD clientPD = new ClientPD();

            String indirizzo = "http://" + ConfigurazioneStatico.getInstance().getWebservicesIndirizzo() + ":" + ConfigurazioneStatico.getInstance().getWebservicesPort() + DAOCostanti.URL_WSDL_CONSEGNA_MESSAGGI;
            logger.info("url: " + indirizzo);
            logger.debug(m);

            clientPD.invocaPortaDelegata(indirizzo, m);

            Assert.fail("La conferma dei pubblicatori non ha lanciato eccezione");

        } catch (Exception ex) {
        }
    }

}

