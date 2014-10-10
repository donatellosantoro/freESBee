package test.it.unibas.freesbee.ge.funzionali.i;

import it.unibas.freesbee.ge.messaggi.XmlJDomUtil;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCostanti;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOUtilHibernate;
import it.unibas.freesbee.utilita.ClientPD;
import it.unibas.springfreesbee.ge.ConfigurazioneStatico;
import java.net.URL;
import junit.framework.Assert;
import org.apache.camel.ContextTestSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import test.it.unibas.freesbee.ge.dao.DAOMock;

public class Pubblicazione15 extends ContextTestSupport {

    protected Log logger = LogFactory.getLog(this.getClass());

    public void testPubblicazione15Prima() throws Exception {
        logger.info("TEST - 15");
        logger.info("Prima");
        DAOUtilHibernate.beginTransaction();
        DAOMock.inserisciPubblicatoreInterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_C, DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_3);
        DAOUtilHibernate.commit();
    }

    public void testPubblicazione15() throws Exception {
        logger.info("TEST - 15");
        logger.info("Pubblicatore Inesistente");
        try {
            //Carico il messaggio che verebbe generato dal GE
            URL url = this.getClass().getResource("/dati/i/test3.xml");
            Document doc = XmlJDomUtil.caricaXML(url.getFile(), false);
            String m = XmlJDomUtil.convertiDocumentToString(doc);

            ClientPD clientPD = new ClientPD();

            String indirizzo = "http://" + ConfigurazioneStatico.getInstance().getWebservicesIndirizzo() + ":" + ConfigurazioneStatico.getInstance().getWebservicesPort() + DAOCostanti.URL_WSDL_GESTORE_EVENTI;
            logger.info("url: " + indirizzo);
            logger.debug(m);

            clientPD.invocaPortaDelegata(indirizzo, m);

            Assert.fail("La pubblicazione non ha lanciato eccezione");

        } catch (Exception ex) {
        }
    }

    public void testPubblicazione15Clean() throws Exception {
        logger.info("TEST - 15");
        logger.info("Clean");
        DAOUtilHibernate.beginTransaction();
        DAOMock.ripulisciPubblicatoreInterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_C, DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_3);
        DAOUtilHibernate.commit();
    }
}

