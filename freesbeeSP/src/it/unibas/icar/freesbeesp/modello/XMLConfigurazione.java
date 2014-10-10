package it.unibas.icar.freesbeesp.modello;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.camel.CamelContext;
import org.apache.cxf.helpers.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XMLConfigurazione {

    private static org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog(XMLConfigurazione.class);
    private static XMLConfigurazione singleton = new XMLConfigurazione();
    private Utente utente = new Utente();
    private List<Istanza> listaIstanze = new ArrayList<Istanza>();
    private String indirizzoRegistroServizi;
    private CamelContext camelContext;

    public static XMLConfigurazione getInstance() {
        return singleton;
    }

    private XMLConfigurazione() {
        try {
            InputStream is = caricaFileConfigurazione();
            if (is == null) {
                logger.warn("freesbeeSP non e' configurato per proteggere le istanze lato erogatore. ");
                return;
            }
            Document docEccezioni = XMLUtils.parse(is);
            Element elementConfigurazione = docEccezioni.getDocumentElement();
            indirizzoRegistroServizi = elementConfigurazione.getAttribute("indirizzoRegistroServizi");
            NodeList nodiUtente = elementConfigurazione.getElementsByTagName("utente");
            for (int i = 0; i < nodiUtente.getLength(); i++) {
                Element nodeUtente = (Element) nodiUtente.item(i);
                this.utente.setNomeUtente(getTestoFiglio("nomeUtente", nodeUtente));
                this.utente.setPassword(getTestoFiglio("password", nodeUtente));
            }
            NodeList nodiIstanze = elementConfigurazione.getElementsByTagName("istanza");
            for (int i = 0; i < nodiIstanze.getLength(); i++) {
                Istanza istanza = new Istanza();
                Element nodeIstanza = (Element) nodiIstanze.item(i);
                istanza.setUriAscolto(getTestoFiglio("uriAscolto", nodeIstanza));
                istanza.setRisorsa(getTestoFiglio("risorsa", nodeIstanza));
                istanza.setAccordoServizio(getTestoFiglio("accordoServizio", nodeIstanza));
                listaIstanze.add(istanza);
            }
            logger.info("Lista delle istanze caricata correttamente");
        } catch (Exception ex) {
            logger.error("Impossibile leggere la lista delle istanze " + ex);
        }
    }

    private InputStream caricaFileConfigurazione() throws Exception {
        String confDir = ConfigurazioneStatico.getInstance().getConfigDir();
        if (!confDir.endsWith(File.separator)) {
            confDir += File.separator;
        }
        String confFile = confDir + "configurazione.xml";
        logger.info("Carico il file " + confFile);
        File fileConf = new File(confFile);
        if (!fileConf.exists()) {
            return null;
        }
        InputStream is = new FileInputStream(fileConf);
        return is;
    }

    private String getTestoFiglio(String figlio, Element el) {
        NodeList nl = el.getElementsByTagName(figlio);
        if (nl.getLength() < 1) {
            return "";
        }
        return nl.item(0).getTextContent();
    }

    public String getIndirizzoRegistroServizi() {
        return indirizzoRegistroServizi;
    }

    public void setIndirizzoRegistroServizi(String indirizzoRegistroServizi) {
        this.indirizzoRegistroServizi = indirizzoRegistroServizi;
    }

    public List<Istanza> getListaIstanze() {
        return listaIstanze;
    }

    public void setListaIstanze(List<Istanza> listaIstanze) {
        this.listaIstanze = listaIstanze;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public CamelContext getCamelContext() {
        return camelContext;
    }

    public void setCamelContext(CamelContext camelContext) {
        this.camelContext = camelContext;
    }
}
