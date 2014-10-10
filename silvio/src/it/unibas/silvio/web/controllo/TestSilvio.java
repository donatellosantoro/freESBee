package it.unibas.silvio.web.controllo;

import it.unibas.silvio.modello.IstanzaOperation;
import it.unibas.silvio.modello.Messaggio;
import it.unibas.silvio.modello.RispostaEseguiIstanza;
import it.unibas.silvio.persistenza.DAOException;
import it.unibas.silvio.persistenza.IDAOMessaggio;
import it.unibas.silvio.persistenza.hibernate.DAOMessaggioHibernate;
import it.unibas.silvio.persistenza.hibernate.DAOUtilHibernate;
import it.unibas.silvio.xml.XmlException;
import it.unibas.silvio.xml.XmlJDomUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.xml.sax.SAXException;

public class TestSilvio extends TestCase {

    private String bodyMessage;
    private String stringUrl;
    private IstanzaOperation istanzaOp;
    private int secondi;
    private Log logger = LogFactory.getLog(this.getClass());
    private IDAOMessaggio daoMessaggio = new DAOMessaggioHibernate();

    public TestSilvio(String arg0, String bodyMessage, String stringUrl,IstanzaOperation istanzaOp,int secondi) {
        super(arg0);
        this.bodyMessage = bodyMessage;
        this.stringUrl = stringUrl;
        this.istanzaOp = istanzaOp;
        this.secondi = secondi;
    }

    public void testSilvio() {
        try {            
            logger.info("Invio del messaggio in corso all'url: " + stringUrl);
            URL url = new URL(stringUrl);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", bodyMessage.getBytes().length + "");
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(bodyMessage);
            wr.flush();

            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            String stringaRisposta = "";
            while ((line = rd.readLine()) != null) {
                stringaRisposta += line;
            }
            wr.close();
            rd.close();
            String idMessaggioInviato = registraRisposta(stringaRisposta);
            if(istanzaOp.isAsincrono()){
                assertNotNull("L'id del messaggio inviato è null", idMessaggioInviato);
                logger.info("Ci mettiamo in attesa di " + this.secondi + " secondi e poi controlliamo se c'è una risposta per il messaggio " + idMessaggioInviato);
                Thread.sleep(secondi * 1000);
                Messaggio risposta = cercaRisposta(idMessaggioInviato);
                assertNotNull("Risposta al messaggio " + idMessaggioInviato + " non ricevuta", risposta);
            }
            
        } catch (Exception ex) {
            logger.info("Errore nel test sincrono");
        }
    }

    private Messaggio cercaRisposta(String idMessaggioInviato){
        SessionFactory sessionFactory = null;
        try {
            sessionFactory = DAOUtilHibernate.getSessionFactory();
            sessionFactory.getCurrentSession().beginTransaction();
            Messaggio risposta = daoMessaggio.findByIDRelatesTo(idMessaggioInviato, Messaggio.RICEVUTO);
            sessionFactory.getCurrentSession().getTransaction().commit();
            return risposta;
        } catch (DAOException ex) {
            logger.error("Errore nella lettura delle porte delegate " + ex);
            try {
                if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
                    sessionFactory.getCurrentSession().getTransaction().rollback();
                }
            } catch (Throwable rbEx) {
                logger.error("Could not rollback transaction after exception!", rbEx);
            }
            Assert.fail("Impossibile leggere nel db dei messaggi " + ex);
        }
        return null;
    }

    private String registraRisposta(String stringaRisposta) throws XmlException, IOException, ParserConfigurationException, ParserConfigurationException, SAXException, SAXException, TransformerConfigurationException, TransformerException {
        logger.info("Ricezione del messaggio in corso ");

        RispostaEseguiIstanza rispostaEseguiIstanza = new RispostaEseguiIstanza();
        Document document = XmlJDomUtil.caricaXML(stringaRisposta);
        Element rispostaIstanzaElement = document.getRootElement();
        Element successoElemet = rispostaIstanzaElement.getChild("successo");
        if (successoElemet != null) {

            rispostaEseguiIstanza.setErrore(false);
            Attribute tipoAttribute = successoElemet.getAttribute("tipo");
            rispostaEseguiIstanza.setTipoMessaggio(tipoAttribute.getValue());

            Element element = successoElemet.getChild("idMessaggioRichiesta");
            rispostaEseguiIstanza.setIdMessaggio(element.getText());

            element = successoElemet.getChild("messaggio");
            if (element != null) {
                rispostaEseguiIstanza.setMessaggio(element.getText());
            }
            element = successoElemet.getChild("idMessaggioRisposta");
            rispostaEseguiIstanza.setIdMessaggioRisposta("-----------------------------");

            if (element != null) {
                rispostaEseguiIstanza.setIdMessaggioRisposta(element.getText());
            }
            return rispostaEseguiIstanza.getIdMessaggio();
        } else {
            Element erroreElemet = rispostaIstanzaElement.getChild("errore");
            rispostaEseguiIstanza.setErrore(true);
            rispostaEseguiIstanza.setMessaggioErrore(erroreElemet.getChild("messaggioErrore").getText());
            Assert.fail(rispostaEseguiIstanza.getMessaggioErrore());
            return null;
        }

    }
}
