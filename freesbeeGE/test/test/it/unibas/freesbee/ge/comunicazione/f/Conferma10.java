package test.it.unibas.freesbee.ge.comunicazione.f;

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

public class Conferma10 extends ContextTestSupport {

    protected Log logger = LogFactory.getLog(this.getClass());

    public void test10Prima() throws Exception {
        logger.info("TEST - 10");
        logger.info("Prima");
        DAOUtilHibernate.beginTransaction();
        IDAOCategoriaEventiEsterna daoCategoriaEventi = new DAOCategoriaEventiEsternaHibernate();
        CategoriaEventiEsterna categoriaEventi = daoCategoriaEventi.findByNome(DAOMock.CATEGORIA_EVENTI_NON_ESISTENTE_27);
        assertNull(categoriaEventi);
        DAOUtilHibernate.commit();
    }

    public void testConferma10() throws Exception {
        logger.info("TEST - 10");
        logger.info("Ricezione Conferma Categoria Eventi non esistente");
        try {
            //Carico il messaggio che verebbe generato dal GE
            URL url = this.getClass().getResource("/dati/f/test10.xml");
            Document doc = XmlJDomUtil.caricaXML(url.getFile(), false);
            String m = XmlJDomUtil.convertiDocumentToString(doc);

            ClientPD clientPD = new ClientPD();

            String indirizzo = "http://" + ConfigurazioneStatico.getInstance().getWebservicesIndirizzo() + ":" + ConfigurazioneStatico.getInstance().getWebservicesPort() + DAOCostanti.URL_WSDL_CONSEGNA_MESSAGGI;
            logger.info("url: " + indirizzo);
            logger.debug(m);

            clientPD.invocaPortaDelegata(indirizzo, m);

            Assert.fail("La conferma della categoria non ha lanciato eccezione");

        } catch (Exception ex) {
        }
    }
}

