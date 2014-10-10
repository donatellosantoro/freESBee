package test.it.unibas.freesbee.ge.comunicazione.d;

import it.unibas.freesbee.ge.messaggi.Messaggi;
import it.unibas.freesbee.ge.messaggi.XmlJDomUtil;
import it.unibas.freesbee.ge.modello.CategoriaEventiInterna;
import it.unibas.freesbee.ge.modello.Configurazione;
import it.unibas.freesbee.ge.modello.ConfigurazioniFactory;
import it.unibas.freesbee.ge.modello.FiltroData;
import it.unibas.freesbee.ge.modello.GestoreEventi;
import it.unibas.freesbee.ge.modello.ICategoriaEventi;
import it.unibas.freesbee.ge.modello.ISottoscrizione;
import it.unibas.freesbee.ge.modello.PubblicatoreInterno;
import it.unibas.freesbee.ge.modello.Sottoscrittore;
import it.unibas.freesbee.ge.modello.SottoscrizioneInterna;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiInterna;
import it.unibas.freesbee.ge.persistenza.IDAOGestoreEventi;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrittore;
import it.unibas.freesbee.ge.persistenza.facade.DAOSottoscrizioneInternaFacade;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOUtilHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCategoriaEventiInternaHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCostanti;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOGestoreEventiHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOSottoscrittoreHibernate;
import it.unibas.freesbee.ge.stub.NotificaEventoPubblicato;
import it.unibas.freesbee.utilita.ClientPD;
import it.unibas.springfreesbee.ge.ConfigurazioneStatico;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import junit.framework.Assert;
import org.apache.camel.ContextTestSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.helpers.XMLUtils;
import org.xml.sax.SAXException;
import test.it.unibas.freesbee.ge.dao.DAOMock;

public class Eliminazione3 extends ContextTestSupport {

    protected Log logger = LogFactory.getLog(this.getClass());
    protected static GestoreEventi ge;
    protected static CategoriaEventiInterna categoiraEventiGE;

    public void testEliminazione3Init() throws Exception {
        try {
            logger.info("TEST - 3");
            logger.info("Init");

            FileWriter f = new FileWriter(DAOMock.NOTIFICA);
            String s = " ";
            f.append(s.subSequence(0, s.length()));
            f.flush();

            f = new FileWriter(DAOMock.CONSEGNA);
            s = " ";
            f.append(s.subSequence(0, s.length()));
            f.flush();

            DAOUtilHibernate.beginTransaction();


            DAOUtilHibernate.beginTransaction();
            IDAOCategoriaEventiInterna daoCategoriaEventiInterna = new DAOCategoriaEventiInternaHibernate();

            Sottoscrittore sottoscrittoreH = null;
            SottoscrizioneInterna sottoscrizioneH = null;
            FiltroData filtroDataH = null;

            Sottoscrittore sottoscrittoreG = null;
            SottoscrizioneInterna sottoscrizioneG = null;
            FiltroData filtroDataG = null;

            Calendar date = new GregorianCalendar();
            date.add(Calendar.SECOND, 10);

            categoiraEventiGE = daoCategoriaEventiInterna.findByNome(ICategoriaEventi.GE_CONTROL_PROTOCOL);

            Configurazione configurazione = ConfigurazioniFactory.getConfigurazioneIstance();
            IDAOGestoreEventi daoGestoreEventi = new DAOGestoreEventiHibernate();
            ge = daoGestoreEventi.findByTipoNome(configurazione.getTipoGestoreEventi(), configurazione.getNomeGestoreEventi());

            sottoscrittoreH = new Sottoscrittore(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_H);
            sottoscrittoreG = new Sottoscrittore(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_G);

            filtroDataH = new FiltroData(date.getTime());
            sottoscrizioneH = new SottoscrizioneInterna(sottoscrittoreH, categoiraEventiGE, ISottoscrizione.TIPO_SOTTOSCRIZIONE_CONSEGNA, filtroDataH);
            DAOSottoscrizioneInternaFacade.aggiungiSottoscrizioneInterna(categoiraEventiGE, sottoscrittoreH, sottoscrizioneH);

            filtroDataG = new FiltroData(date.getTime());
            sottoscrizioneG = new SottoscrizioneInterna(sottoscrittoreH, categoiraEventiGE, ISottoscrizione.TIPO_SOTTOSCRIZIONE_NOTIFICA, filtroDataG);
            DAOSottoscrizioneInternaFacade.aggiungiSottoscrizioneInterna(categoiraEventiGE, sottoscrittoreG, sottoscrizioneG);


            DAOUtilHibernate.commit();

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            DAOUtilHibernate.rollback();
            Assert.fail("L'eliminazione del pubblicatore ha lanciato eccezione");

        }
    }

    public void testEliminazione3Pubblicazione() throws Exception {
        logger.info("TEST - 3");
        logger.info("Pubblicazione della Comunicazione della Rimozione del pubblicatore interno A");

        try {
            Thread.currentThread().sleep(10000);
            PubblicatoreInterno pubblicatore = new PubblicatoreInterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_A);
            //Creo lo stesso messaggi che invia il ClientGEControlProtocol
            String messaggio = Messaggi.creaMessaggioComunicazioneRimozionePubblicatore(pubblicatore, categoiraEventiGE, ge);

            ClientPD clientPD = new ClientPD();

            String indirizzo = "http://" + ConfigurazioneStatico.getInstance().getWebservicesIndirizzo() + ":" + ConfigurazioneStatico.getInstance().getWebservicesPort() + DAOCostanti.URL_WSDL_GESTORE_EVENTI;
            logger.info("url: " + indirizzo);
            logger.debug(messaggio);

            clientPD.invocaPortaDelegata(indirizzo, messaggio);

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            Assert.fail("L'eliminazione del pubblicatore ha lanciato eccezione");


        }
    }

    public void testEliminazione3VerificaConsegnaComunicazione() throws Exception {
        try {
            logger.info("TEST - 3");
            logger.info("Verifica Consegna Comunicazione");
            Thread.currentThread().sleep(5000);
            //Comunicazione inviata
            org.jdom.Document doc = XmlJDomUtil.caricaXML(DAOMock.CONSEGNA, false);
            String s = XmlJDomUtil.convertiDocumentToString(doc);

            //Comunicazione di test
            URL url = this.getClass().getResource("/dati/d/test3Consegna.xml");
            doc = XmlJDomUtil.caricaXML(url.getFile(), false);
            String m = XmlJDomUtil.convertiDocumentToString(doc);

            logger.debug("Comunicazione inviata: ");
            logger.debug(XmlJDomUtil.formattaXML(s));
            logger.debug("Comunicazione di test:");
            logger.debug(XmlJDomUtil.formattaXML(m));

            assertEquals(m, s);

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            Assert.fail(ex.getMessage());
        }
    }

    public void testEliminazione3VerificaNotificaComunicazione() throws Exception {
        try {
            logger.info("TEST - 3");
            logger.info("Verifica Notifica Comunicazione");
            Thread.currentThread().sleep(5000);
            //Comunicazione inviata
            org.jdom.Document doc = XmlJDomUtil.caricaXML(DAOMock.NOTIFICA, false);
            String s = XmlJDomUtil.convertiDocumentToString(doc);

            logger.debug("Comunicazione inviata: ");
            logger.debug(XmlJDomUtil.formattaXML(s));

            NotificaEventoPubblicato n = (NotificaEventoPubblicato) unmarsahaller(s);

            assertNotNull(n.getIdEventoPubblicato());
            assertFalse(n.getIdEventoPubblicato().equals(""));

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            Assert.fail(ex.getMessage());
        }
    }

    public void testEliminazione3Clean() throws Exception {
        try {
            logger.info("TEST - 3");
            logger.info("Clean");

            Thread.currentThread().sleep(5000);

            DAOUtilHibernate.beginTransaction();
            IDAOCategoriaEventiInterna daoCategoriaEventiInterna = new DAOCategoriaEventiInternaHibernate();
            IDAOSottoscrittore daoSottoscrittore = new DAOSottoscrittoreHibernate();
            Sottoscrittore sottoscrittoreH = daoSottoscrittore.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_H);
            DAOSottoscrizioneInternaFacade.eliminaSottoscrizioneInterna(daoCategoriaEventiInterna.findByNome(CategoriaEventiInterna.GE_CONTROL_PROTOCOL), sottoscrittoreH);

            Sottoscrittore sottoscrittoreG = daoSottoscrittore.findByTipoNome(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_G);
            DAOSottoscrizioneInternaFacade.eliminaSottoscrizioneInterna(daoCategoriaEventiInterna.findByNome(CategoriaEventiInterna.GE_CONTROL_PROTOCOL), sottoscrittoreG);
            DAOUtilHibernate.commit();

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            DAOUtilHibernate.rollback();
            Assert.fail(ex.getMessage());
        }
    }

    private Object unmarsahaller(String s) throws JAXBException, FileNotFoundException, SOAPException, ParserConfigurationException, SAXException, IOException {
        logger.debug("Effettuo l'unmarshaller dell'oggetto richiesta");

        MessageFactory messageFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
        SOAPMessage message = messageFactory.createMessage();
        SOAPPart soapPart = message.getSOAPPart();
        Reader soapStringReader = new StringReader(s);
        Source soapSource = new StreamSource(soapStringReader);
        soapPart.setContent(soapSource);
        message.saveChanges();
        String streamSoap = "";

        org.w3c.dom.Document document = message.getSOAPBody().extractContentAsDocument();
        streamSoap = XMLUtils.toString(document);


        JAXBContext jc = JAXBContext.newInstance("it.unibas.freesbee.ge.stub");
        javax.xml.bind.Unmarshaller u = jc.createUnmarshaller();
        InputStream is = new ByteArrayInputStream(streamSoap.getBytes());
        logger.debug(streamSoap);
        it.unibas.freesbee.ge.stub.NotificaEventoPubblicato notifica = (NotificaEventoPubblicato) u.unmarshal(is);

        return notifica;
    }
}

