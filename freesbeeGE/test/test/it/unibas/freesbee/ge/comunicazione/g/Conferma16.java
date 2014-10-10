package test.it.unibas.freesbee.ge.comunicazione.g;

import it.unibas.freesbee.ge.messaggi.XmlJDomUtil;
import it.unibas.freesbee.ge.modello.CategoriaEventiEsterna;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiEsterna;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCategoriaEventiEsternaHibernate;
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

public class Conferma16 extends ContextTestSupport {

    protected Log logger = LogFactory.getLog(this.getClass());

    public void test16Prima() throws Exception {
        logger.info("TEST - 16");
        logger.info("Prima");

        DAOUtilHibernate.beginTransaction();
        IDAOCategoriaEventiEsterna daoCategoriaEventi = new DAOCategoriaEventiEsternaHibernate();
        CategoriaEventiEsterna categoriaEventi = daoCategoriaEventi.findByNome(DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_32);

        assertNotNull(categoriaEventi);
        assertFalse(categoriaEventi.isInAttesa());
        assertTrue(categoriaEventi.isAttiva());

        assertEquals(0,categoriaEventi.getListaPubblicatori().size());
        DAOUtilHibernate.commit();
    }

    public void testConferma16() throws Exception {
        logger.info("TEST - 16");
        logger.info("Ricezione Conferma Pubblicatori per Categoria Eventi gia' confermata ma senza un pobblicatore gestore eventi");
        try {
            //Carico il messaggio che verebbe generato dal GE
            URL url = this.getClass().getResource("/dati/g/test16.xml");
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

