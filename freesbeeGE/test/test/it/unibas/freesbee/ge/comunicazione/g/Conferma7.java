package test.it.unibas.freesbee.ge.comunicazione.g;

import it.unibas.freesbee.ge.messaggi.XmlJDomUtil;
import it.unibas.freesbee.ge.modello.CategoriaEventiInterna;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiInterna;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCategoriaEventiInternaHibernate;
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

public class Conferma7 extends ContextTestSupport {

    protected Log logger = LogFactory.getLog(this.getClass());

    public void test7Prima() throws Exception {
        logger.info("TEST - 7");
        logger.info("Prima");

        DAOUtilHibernate.beginTransaction();
        IDAOCategoriaEventiInterna daoCategoriaEventi = new DAOCategoriaEventiInternaHibernate();
        CategoriaEventiInterna categoriaEventi = daoCategoriaEventi.findByNome(DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_3);
        assertNotNull(categoriaEventi);
        assertTrue(categoriaEventi.isAttiva());
        assertEquals(0, categoriaEventi.getListaPubblicatori().size());
        DAOUtilHibernate.commit();
    }

    public void testConferma7() throws Exception {
        logger.info("TEST - 7");
        logger.info("Ricezione Richeista Conferma di Pubblicatori per una Catgoria Evneti interna ma senza pubblicatori");
        try {
            //Carico il messaggio che verebbe generato dal GE
            URL url = this.getClass().getResource("/dati/g/test7.xml");
            Document doc = XmlJDomUtil.caricaXML(url.getFile(), false);
            String m = XmlJDomUtil.convertiDocumentToString(doc);

            ClientPD clientPD = new ClientPD();

            String indirizzo = "http://" + ConfigurazioneStatico.getInstance().getWebservicesIndirizzo() + ":" + ConfigurazioneStatico.getInstance().getWebservicesPort() + DAOCostanti.URL_WSDL_CONSEGNA_MESSAGGI;
            logger.info("url: " + indirizzo);
            logger.debug(m);

            clientPD.invocaPortaDelegata(indirizzo, m);

            Assert.fail("La conferma dei pubblicatori categoria non ha lanciato eccezione");

        } catch (Exception ex) {
        }
    }
}

