package test.it.unibas.freesbee.ge.funzionali.i;

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
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreInterno;
import it.unibas.freesbee.ge.persistenza.facade.DAOCategoriaEventiInternaFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOGestoreEventiFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOSottoscrizioneInternaFacade;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCategoriaEventiInternaHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCostanti;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOPubblicatoreInternoHibernate;
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

public class Pubblicazione24 extends ContextTestSupport {

    protected Log logger = LogFactory.getLog(this.getClass());

    public void testPubblicazione24Prima() throws Exception {
        try {
            logger.info("TEST - 24");
            logger.info("Prima");
            DAOUtilHibernate.beginTransaction();

            IDAOPubblicatoreInterno daoPubblicatoreInterno = new DAOPubblicatoreInternoHibernate();
            IDAOCategoriaEventiInterna daoCategoriaEventiInterna = new DAOCategoriaEventiInternaHibernate();

            GestoreEventi ge = DAOGestoreEventiFacade.getGE();

            PubblicatoreInterno pubblicatoreInterno = daoPubblicatoreInterno.findByTipoNome(ge.getTipo(), ge.getNome());

            Sottoscrittore sottoscrittore = new Sottoscrittore(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_GE);

            CategoriaEventiInterna categoriaEventi = DAOCategoriaEventiInternaFacade.verificaEsistenzaCategoriaEventiInternaAttavia(DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_2);

            SottoscrizioneInterna sottoscrizione = daoCategoriaEventiInterna.findByCategoriaEventiSottoscrittore(categoriaEventi, sottoscrittore);

            Calendar date = new GregorianCalendar();
            date.add(Calendar.SECOND, 5);
            FiltroData filtroData = new FiltroData(date.getTime());

            sottoscrizione = new SottoscrizioneInterna(sottoscrittore, categoriaEventi, ISottoscrizione.TIPO_SOTTOSCRIZIONE_NOTIFICA, filtroData);
            FiltroPubblicatoreInterno filtroPubblicatore = new FiltroPubblicatoreInterno(pubblicatoreInterno, sottoscrizione);
            sottoscrizione.getListaFiltroPublicatore().add(filtroPubblicatore);
            DAOSottoscrizioneInternaFacade.aggiungiSottoscrizioneInterna(categoriaEventi, sottoscrittore, sottoscrizione);

            DAOUtilHibernate.commit();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            Assert.fail("L'inserimento della sottoscrizione ha lanciato eccezione");
        }
    }

    public void testPubblicazione24() throws Exception {
        logger.info("TEST - 24");
        logger.info("Cateogria di Eventi GEControlProtocol");
        try {
            Thread.currentThread().sleep(15000);
            //Carico il messaggio che verebbe generato dal GE
            URL url = this.getClass().getResource("/dati/i/test1.xml");
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

    public void testPubblicazione24Clean() throws Exception {
        try {
            logger.info("TEST - 24");
            logger.info("Clean");

            DAOUtilHibernate.beginTransaction();
            DAOMock.ripulisciSottoscrittoreSottoscrizioneIntera(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_GE, DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_2);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            Assert.fail("L'eliminazione della sottoscrizione ha lanciato eccezione");
        }
    }
}

