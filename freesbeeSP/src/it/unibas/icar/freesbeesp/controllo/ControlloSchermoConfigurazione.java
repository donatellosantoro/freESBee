package it.unibas.icar.freesbeesp.controllo;

import it.unibas.icar.freesbeesp.endpoint.XacmlHttpsEndpoint;
import it.unibas.icar.freesbeesp.modello.ConfigurazioneStatico;
import it.unibas.icar.freesbeesp.modello.Istanza;
import it.unibas.icar.freesbeesp.modello.XMLConfigurazione;
import it.unibas.icar.freesbeesp.util.XmlJDomUtil;
import it.unibas.icar.freesbeesp.vista.VistaSchermoConfigurazione;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.cxf.helpers.XMLUtils;
import org.jdom.Attribute;
import org.jdom.Namespace;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ControlloSchermoConfigurazione {

    private Log logger = LogFactory.getLog(this.getClass());
    private XMLConfigurazione configurazione = XMLConfigurazione.getInstance();
    private VistaSchermoConfigurazione vistaSchermoConfigurazione;
    private CamelContext context;
    private String errore;
    private String successo;
    private String messaggio;

    public ControlloSchermoConfigurazione() {
        XMLConfigurazione xmlConfigurazione = XMLConfigurazione.getInstance();
        context = xmlConfigurazione.getCamelContext();
    }

    public String caricaConfigurazione() {
        try {
            XMLConfigurazione xmlConfigurazione = XMLConfigurazione.getInstance();
            List<Istanza> listaIstanze = this.vistaSchermoConfigurazione.getListaIstanza();
            caricaFileConfigurazione(listaIstanze);
            if (listaIstanze.size() == 0) {
                this.successo = "Nessuna istanza configurata";
            } else {
                xmlConfigurazione.setListaIstanze(listaIstanze);
                this.successo = "Lista delle istanze caricate";
            }
            this.vistaSchermoConfigurazione.setVisualizzaIstanze(true);
        } catch (Exception ex) {
            logger.error(ex);
            this.errore = "Impossibile caricare la lista delle instanze";
        }
        return "configurazione";
    }

    public String aggiungi() {
        this.vistaSchermoConfigurazione.setVisualizzaPannelloAggiungi(true);
        return null;
    }

    public String registro() {
        try {
            List<Istanza> listaIstanze = this.vistaSchermoConfigurazione.getListaIstanza();
            caricaFileConfigurazione(listaIstanze);
            this.vistaSchermoConfigurazione.setVisualizzaRegistroServizi(true);
            return null;
        } catch (Exception ex) {
            logger.error(ex);
            this.errore = "Impossibile leggere il file di configurazione";
        }
        return null;
    }

    public String chiudiRegistro() {
        this.vistaSchermoConfigurazione.setVisualizzaRegistroServizi(false);
        return null;
    }

    public String salvaRegistro() {
        this.vistaSchermoConfigurazione.setVisualizzaRegistroServizi(false);
        List<Istanza> listaIstanze = this.vistaSchermoConfigurazione.getListaIstanza();
        String indirizzoRegistroServizi = this.vistaSchermoConfigurazione.getIndirizzoRegistroServizi();
        try {
            salvaListaIstanze(listaIstanze, indirizzoRegistroServizi);
            this.successo = "Indirizzo modificato correttamente";
        } catch (Exception ex) {
            ex.printStackTrace();
            this.errore = "Impossibile salvare il file";
        }
        return null;
    }

    public String aggiungiIstanza() {
        caricaConfigurazione();
        List<Istanza> listaIstanze = this.vistaSchermoConfigurazione.getListaIstanza();
        String indirizzoRegistroServizi = this.vistaSchermoConfigurazione.getIndirizzoRegistroServizi();
        logger.info("AGGIUNGI ISTANZA - ATTUALE DIMENSIONE LISTA: " + listaIstanze.size());
        Istanza istanza = new Istanza();
        istanza.setAccordoServizio(this.vistaSchermoConfigurazione.getAccordoServizio());
        istanza.setRisorsa(this.vistaSchermoConfigurazione.getRisorsa());
        istanza.setUriAscolto(this.vistaSchermoConfigurazione.getUriAscolto());
        listaIstanze.add(istanza);

//        XMLConfigurazione xmlConfigurazione = XMLConfigurazione.getInstance();
//        xmlConfigurazione.getListaIstanze().add(istanza);
        logger.info("AGGIUNGI ISTANZA - NUOVA DIMENSIONE LISTA: " + listaIstanze.size());
        try {
            salvaListaIstanze(listaIstanze, indirizzoRegistroServizi);
            this.successo = "File salvato correttamente";
            this.vistaSchermoConfigurazione.setVisualizzaPannelloAggiungi(false);
            caricaConfigurazione();

            RouteBuilder newXacmlEndpoint = new XacmlHttpsEndpoint();
            context.addRoutes(newXacmlEndpoint);
        } catch (Exception ex) {
            ex.printStackTrace();
            this.errore = "Impossibile salvare il file";
        }
        return null;
    }

    private InputStream caricaFileConfigurazione() throws Exception {
        String confDir = ConfigurazioneStatico.getInstance().getConfigDir();
        String confFile = confDir + "configurazione.xml";
        logger.info("Carico il file " + confFile);
        File fileConf = new File(confFile);
        if (!fileConf.exists()) {
            fileConf.getParentFile().mkdirs();
            salvaListaIstanze(new ArrayList<Istanza>(), "http://localhost:8191/ws/registroServizi");
        }
        InputStream is = new FileInputStream(fileConf);
        return is;
    }

    private void caricaFileConfigurazione(List<Istanza> listaIstanze) throws Exception {
        listaIstanze.clear();
        InputStream isConfFile = caricaFileConfigurazione();
        Document docEccezioni = XMLUtils.parse(isConfFile);
        Element elementConfigurazione = docEccezioni.getDocumentElement();
        String indirizzoRegistroServizi = elementConfigurazione.getAttribute("indirizzoRegistroServizi");
        this.vistaSchermoConfigurazione.setIndirizzoRegistroServizi(indirizzoRegistroServizi);
        NodeList nodiIstanze = elementConfigurazione.getElementsByTagName("istanza");
        for (int i = 0; i < nodiIstanze.getLength(); i++) {
            Istanza istanza = new Istanza();
            Element nodeIstanza = (Element) nodiIstanze.item(i);
            istanza.setUriAscolto(getTestoFiglio("uriAscolto", nodeIstanza));
            istanza.setRisorsa(getTestoFiglio("risorsa", nodeIstanza));
            istanza.setAccordoServizio(getTestoFiglio("accordoServizio", nodeIstanza));
            listaIstanze.add(istanza);
        }
        logger.info("Dimensione lista: " + listaIstanze.size());
    }

    private void salvaListaIstanze(List<Istanza> listaIstanze, String indirizzoRegistroServizi) throws Exception {
        org.jdom.Document document = null;
        org.jdom.Element rootElement = new org.jdom.Element("configurazione");
        org.jdom.Element elementoUtente = new org.jdom.Element("utente");
        org.jdom.Element elementoNomeUtente = new org.jdom.Element("nomeUtente");
        org.jdom.Element elementoPassword = new org.jdom.Element("password");
        elementoNomeUtente.addContent(configurazione.getUtente().getNomeUtente());
        elementoPassword.addContent(configurazione.getUtente().getPassword());
        elementoUtente.addContent(elementoNomeUtente);
        elementoUtente.addContent(elementoPassword);
        rootElement.addNamespaceDeclaration(Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance"));
        rootElement.setAttribute(new Attribute("indirizzoRegistroServizi", indirizzoRegistroServizi));
        rootElement.addContent(elementoUtente);
        for (Istanza nomeIstanze : listaIstanze) {
            org.jdom.Element elementoIstanza = new org.jdom.Element("istanza");
            org.jdom.Element elementoRisorsa = new org.jdom.Element("risorsa");
            org.jdom.Element elementoUriAscolto = new org.jdom.Element("uriAscolto");
            org.jdom.Element elementoAccordoServizio = new org.jdom.Element("accordoServizio");
            elementoRisorsa.addContent(nomeIstanze.getRisorsa());
            elementoUriAscolto.addContent(nomeIstanze.getUriAscolto());
            elementoAccordoServizio.addContent(nomeIstanze.getAccordoServizio());
            elementoIstanza.addContent(elementoRisorsa);
            elementoIstanza.addContent(elementoUriAscolto);
            elementoIstanza.addContent(elementoAccordoServizio);
            rootElement.addContent(elementoIstanza);
        }
        document = new org.jdom.Document(rootElement);
        String confDir = ConfigurazioneStatico.getInstance().getConfigDir();
        String confFile = confDir + "configurazione.xml";
        XmlJDomUtil.salvaXML(confFile, document);
    }

    public String annullaIstanza() {
        this.vistaSchermoConfigurazione.setVisualizzaPannelloAggiungi(false);
        return null;
    }

    public String chiudiEliminaIstanza() {
        this.vistaSchermoConfigurazione.setElimina(false);
        return null;
    }

    public String eliminaIstanza() {
        Istanza istanzaEliminare = this.vistaSchermoConfigurazione.getIstanzaEliminare();
        List<Istanza> listaIstanze = this.vistaSchermoConfigurazione.getListaIstanza();
        String indirizzoRegistroServizi = this.vistaSchermoConfigurazione.getIndirizzoRegistroServizi();
        logger.info("Istanza da eliminare:" + istanzaEliminare.getAccordoServizio());
        try {
            caricaFileConfigurazione(listaIstanze);
            for (Iterator<Istanza> it = listaIstanze.iterator(); it.hasNext();) {
                Istanza istanza = it.next();
                logger.info("Istanza: " + istanza.getAccordoServizio());
                if (istanza.getAccordoServizio().equalsIgnoreCase(istanzaEliminare.getAccordoServizio()) &&
                        istanza.getRisorsa().equalsIgnoreCase(istanzaEliminare.getRisorsa()) &&
                        istanza.getUriAscolto().equalsIgnoreCase(istanzaEliminare.getUriAscolto())) {
                    logger.info("Elimino l'istanza: " + istanzaEliminare.getAccordoServizio());
                    it.remove();
                }
            }
            salvaListaIstanze(listaIstanze, indirizzoRegistroServizi);
            this.successo = "Istanza eliminata correttamente";
            this.vistaSchermoConfigurazione.setElimina(false);
            return caricaConfigurazione();
        } catch (Exception ex) {
            ex.printStackTrace();
            this.errore = "Impossibile eliminare l'istanza selezionata";
        }
        return null;
    }

    public String confermaEliminazione() {
        this.vistaSchermoConfigurazione.setElimina(true);
        Istanza istanzaEliminare = (Istanza) this.vistaSchermoConfigurazione.getListaIstanzaGrid().getRowData();
        this.vistaSchermoConfigurazione.setIstanzaEliminare(istanzaEliminare);
        return null;
    }

    private String getTestoFiglio(String figlio, Element el) {
        NodeList nl = el.getElementsByTagName(figlio);
        if (nl.getLength() < 1) {
            return "";
        }
        return nl.item(0).getTextContent();
    }

    public VistaSchermoConfigurazione getVistaSchermoConfigurazione() {
        return vistaSchermoConfigurazione;
    }

    public void setVistaSchermoConfigurazione(VistaSchermoConfigurazione vistaSchermoConfigurazione) {
        this.vistaSchermoConfigurazione = vistaSchermoConfigurazione;
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
}
