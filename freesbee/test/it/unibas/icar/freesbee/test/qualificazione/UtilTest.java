package it.unibas.icar.freesbee.test.qualificazione;

import it.unibas.icar.freesbee.test.qualificazione.erogatore.*;
import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.hibernate.DAOMessaggioHibernate;
import it.unibas.icar.freesbee.persistenza.hibernate.DAOUtilHibernate;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.xpath.XPathExpressionException;
import junit.framework.Assert;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import javax.activation.DataHandler;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.servicemix.util.jaf.ByteArrayDataSource;
import org.hibernate.SessionFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class UtilTest {

    public static final String INDIRIZZO_PA = "http://localhost:8196/PA/";
//    public static final String INDIRIZZO_PA = "http://localhost:18196/PA/";
    private static Log logger = LogFactory.getLog(UtilTest.class);

    public static String trovaNodoString(String exp, Document soapRisposta) {
        try {
            org.w3c.dom.Node node = (org.w3c.dom.Node) UtilTest.getXPath().evaluate(exp, soapRisposta, XPathConstants.NODE);
            if (node == null) {
                return null;
            }
            return node.getTextContent();
        } catch (XPathExpressionException ex) {
            logger.warn("Impossibile valutare l'espressine xpath " + exp + ". " + ex);
            ex.printStackTrace();
        }
        return null;
    }

    public static org.w3c.dom.Node trovaNodo(String exp, Document soapRisposta) {
        try {
            org.w3c.dom.Node node = (org.w3c.dom.Node) UtilTest.getXPath().evaluate(exp, soapRisposta, XPathConstants.NODE);
            if (node == null) {
                return null;
            }
            return node;
        } catch (XPathExpressionException ex) {
            logger.warn("Impossibile valutare l'espressine xpath " + exp + ". " + ex);
            ex.printStackTrace();
        }
        return null;
    }

    private static XPath getXPath() {
        XPath xpath = XPathFactory.newInstance().newXPath();
        xpath.setNamespaceContext(new QualificazioneNamespaceContext());
        return xpath;
    }

    public static String leggiMessaggio(String nomeFile) {
        return leggiMessaggio(nomeFile, true);
    }

    public static String leggiMessaggio(String nomeFile, boolean lanciaEccezione) {
        try {
            InputStream in = UtilTest.class.getResourceAsStream("/dati" + nomeFile);
            if (in == null && !lanciaEccezione) {
                return null;
            }
            Assert.assertNotNull("Impossibile trovare il file " + nomeFile, in);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            copyStream(in, out);
            return out.toString();
        } catch (IOException ex) {
            Assert.fail("Impossibile caricare il file " + nomeFile + ". " + ex);
        }
        return null;
    }

    public static String leggiFile(String nomeFile) {
        try {
            InputStream in = UtilTest.class.getResourceAsStream("/dati/" + nomeFile);
            Assert.assertNotNull("Impossibile trovare il file " + nomeFile, in);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            copyStream(in, out);
            return out.toString();
        } catch (IOException ex) {
            Assert.fail("Impossibile caricare il file " + nomeFile + ". " + ex);
        }
        return null;
    }

    public static final void copyStream(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int len;

        while ((len = in.read(buffer)) >= 0) {
            out.write(buffer, 0, len);
        }
    }

    public static final String inviaPA(String messaggio) {
        return invia(INDIRIZZO_PA, messaggio, new HashMap<String, String>());
    }

    public static final String invia(String indirizzo, String messaggio) {
        return invia(indirizzo, messaggio, new HashMap<String, String>());
    }

    public static final String invia(String indirizzo, String messaggio, Map<String, String> mappaIntestazioni) {
        try {
            URL url = new URL(indirizzo);
            logger.debug("URL: " + url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setDefaultUseCaches(false);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", messaggio.getBytes().length + "");
            for (String chiave : mappaIntestazioni.keySet()) {
                String valore = mappaIntestazioni.get(chiave);
                conn.setRequestProperty(chiave, valore);
            }
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(messaggio);
            wr.flush();
            wr.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            String stringaRisposta = "";
            while ((line = in.readLine()) != null) {
                stringaRisposta += line;
            }
            in.close();
            wr.close();
            conn.disconnect();
            return stringaRisposta;
        } catch (Exception ex) {
            Assert.fail("Impossibile inviare il messaggio. " + ex);
            return null;
        }
    }

    public static final SOAPMessage inviaAttachment(String indirizzo, String messaggio) {
        return inviaAttachment(indirizzo, messaggio, new HashMap<String, String>());
    }

    public static final SOAPMessage inviaAttachment(String indirizzo, String messaggio, Map<String, String> mappaIntestazioni) {
        SOAPMessage result = null;
        try {
            URL url = new URL(indirizzo);
            logger.debug("URL: " + url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setDefaultUseCaches(false);
            conn.setRequestProperty("Content-Type", "application/soap+xml;charset=UTF-8");
            conn.setRequestProperty("Content-Length", messaggio.getBytes().length + "");
            conn.setRequestProperty("Accept-Encoding", "gzip,deflate");
            for (String chiave : mappaIntestazioni.keySet()) {
                String valore = mappaIntestazioni.get(chiave);
                conn.setRequestProperty(chiave, valore);
            }
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(messaggio);
            wr.flush();
            wr.close();

            InputStream inputStream = conn.getInputStream();
            String toString = IOUtils.toString(inputStream);
            System.out.println(toString);
            result = MessageFactory.newInstance().createMessage(null, inputStream);

//            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            String line;
//            String stringaRisposta = "";
//            while ((line = in.readLine()) != null) {
//                stringaRisposta += line;
//            }
//            in.close();
            wr.close();
            conn.disconnect();
        } catch (Exception ex) {
            ex.printStackTrace();
            Assert.fail("Impossibile inviare il messaggio. " + ex);
            return null;
        }
        return result;
    }

    public static Document leggiSOAP(String messaggioSOAP) {
        try {
            MessageFactory messageFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
            SOAPMessage message = messageFactory.createMessage();
            SOAPPart soapPart = message.getSOAPPart();
            Reader soapStringReader = new StringReader(messaggioSOAP);
            Source soapSource = new StreamSource(soapStringReader);
            soapPart.setContent(soapSource);
            message.saveChanges();
            SOAPEnvelope envelope = soapPart.getEnvelope();
            return envelope.getOwnerDocument();
        } catch (Exception ex) {
            Assert.fail("Impossibile elaborare il messaggio soap. " + ex);
            return null;
        }
    }

    public static Element getFiglio(Element padre, String ns, String nome) {
        NodeList list = padre.getElementsByTagNameNS(ns, nome);
        Assert.assertEquals(1, list.getLength());
        return (Element) list.item(0);
    }

    public static void cancellaMessaggio(String idMessaggioRichiesta) {
        SessionFactory sessionFactory = null;
        DAOMessaggioHibernate daoMessaggio = new DAOMessaggioHibernate();

        try {
            sessionFactory = DAOUtilHibernate.getSessionFactory();
            sessionFactory.getCurrentSession().beginTransaction();
            Messaggio messaggioCancellare = daoMessaggio.findByIdMessaggio(idMessaggioRichiesta, null);
            if (messaggioCancellare != null) {
                daoMessaggio.makeTransient(messaggioCancellare);
            }
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (DAOException ex) {
            logger.error("Errore nella lettura dal database " + ex);
            try {
                if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
                    sessionFactory.getCurrentSession().getTransaction().rollback();
                }
            } catch (Throwable rbEx) {
            }
        }
    }

    public static void salvaMessaggio(Messaggio messaggio) {
        SessionFactory sessionFactory = null;
        DAOMessaggioHibernate daoMessaggio = new DAOMessaggioHibernate();

        try {
            sessionFactory = DAOUtilHibernate.getSessionFactory();
            sessionFactory.getCurrentSession().beginTransaction();
            daoMessaggio.makePersistent(messaggio);
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (DAOException ex) {
            logger.error("Errore nella lettura dal database " + ex);
            try {
                if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
                    sessionFactory.getCurrentSession().getTransaction().rollback();
                }
            } catch (Throwable rbEx) {
            }
        }
    }
}
