package it.unibas.silvio.web.controllo;

import it.unibas.silvio.modello.MessaggioSbloccoManuale;
import it.unibas.silvio.modello.ConfigurazioneStatico;
import it.unibas.silvio.persistenza.IDAOMessaggioSbloccoManuale;
import it.unibas.silvio.web.vista.VistaSbloccoAsincrono;
import it.unibas.silvio.xml.XmlJDomUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;

public class ControlloSbloccaRichiesteAsincrone {

    private Log logger = LogFactory.getLog(this.getClass());
    private String messaggio;
    private String errore;
    private String successo;
    private IDAOMessaggioSbloccoManuale daoMessaggioSblocco;
    private VistaSbloccoAsincrono vista;

    public String elenco() {
        List<MessaggioSbloccoManuale> listaMessaggi;
        try {
            listaMessaggi = daoMessaggioSblocco.findAll();
            if (listaMessaggi.size() == 0) {
                this.successo = "Non e' presente alcuna richiesta in sospeso.";
                return "elencoAsincroni";
            }
            this.vista.setListaMessaggi(listaMessaggi);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Errore nella lettura dell'elenco degli accordi di collaborazione " + ex);
            this.errore = "Errore nella lettura dal db";
        }
        return "elencoAsincroni";
    }

    public String sbloccaMessaggio() {
        MessaggioSbloccoManuale messaggioDaSbloccare = (MessaggioSbloccoManuale) this.vista.getTabellaMessaggi().getRowData();
        long id = messaggioDaSbloccare.getId();
        try {
            String body = creaDocumentoXML(id);
            logger.info("Body: " + body);
            ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();
            String port = conf.getAsincronoPort();
            String indirizzoHttp = port + "/asincrono/";
            String indirizzoPublic = "http://localhost:" + indirizzoHttp;
            logger.info("Indirizzo di invio: " + indirizzoPublic);

            URL url = new URL(indirizzoPublic);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setDefaultUseCaches(false);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", body.getBytes().length + "");
            logger.info("connection: " + conn.toString());
            logger.info("connection output stream: " + conn.getOutputStream());
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            logger.info("output stream writer: " + wr.toString());
            wr.write(body);
            wr.flush();
            wr.close();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            String stringaRisposta = "";
            while ((line = in.readLine()) != null) {
                stringaRisposta += line;
            }
            in.close();
            wr.close();
            conn.disconnect();
            this.messaggio = "Messaggio inviato correttamente";
            return elenco();
        } catch (IOException ioe) {
            logger.error("Errore durante la conversione del messaggio da inviare " + ioe);
            this.errore = "Errore durante la conversione del messaggio da inviare";
        }
        return null;
    }   

    public String visualizzaDettaglioMessaggio() {
        MessaggioSbloccoManuale messaggioDaVisualizzare = (MessaggioSbloccoManuale) this.vista.getTabellaMessaggi().getRowData();
        this.vista.setMessaggio(messaggioDaVisualizzare);
        logger.info("Messaggio: " + messaggioDaVisualizzare.getMessaggio());
        this.vista.setVisualizzaMessaggio(true);
        return null;
    }

    public String chiudiModalPanelVisualizzaMessaggio() {
        this.vista.setVisualizzaMessaggio(false);
        return null;
    }

    public IDAOMessaggioSbloccoManuale getDaoMessaggioSblocco() {
        return daoMessaggioSblocco;
    }

    public void setDaoMessaggioSblocco(IDAOMessaggioSbloccoManuale daoMessaggioSblocco) {
        this.daoMessaggioSblocco = daoMessaggioSblocco;
    }

    public String getErrore() {
        return errore;
    }

    public void setErrore(String errore) {
        this.errore = errore;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    public String getSuccesso() {
        return successo;
    }

    public void setSuccesso(String successo) {
        this.successo = successo;
    }

    public VistaSbloccoAsincrono getVista() {
        return vista;
    }

    public void setVista(VistaSbloccoAsincrono vista) {
        this.vista = vista;
    }

    private String creaDocumentoXML(long id) throws IOException {
        Document document = null;

        Element rootElement = new Element("istanzaOperation");
        rootElement.addNamespaceDeclaration(Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance"));

        Element elementNomeOperazione = new Element("id");
        elementNomeOperazione.addContent("" + id);
        rootElement.addContent(elementNomeOperazione);

        document = new Document(rootElement);

        String bodyMessage = XmlJDomUtil.convertiDocumentToString(document);
        return bodyMessage;
    }
}
