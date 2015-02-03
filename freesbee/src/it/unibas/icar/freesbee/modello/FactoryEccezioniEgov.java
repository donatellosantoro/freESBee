package it.unibas.icar.freesbee.modello;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.helpers.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class FactoryEccezioniEgov {

    private static Log logger = LogFactory.getLog(FactoryEccezioniEgov.class);
    private static FactoryEccezioniEgov singleton = new FactoryEccezioniEgov();
    private Map<String, Eccezione> mappaEccezioni = new HashMap<String, Eccezione>();

    public static FactoryEccezioniEgov getInstance() {
        return singleton;
    }

    private FactoryEccezioniEgov() {
        try {
            InputStream is = ConfigurazioneStatico.class.getResourceAsStream("/eccezioni/eccezioni.xml");
            Document docEccezioni = XMLUtils.parse(is);
            Element elementEccezioni = docEccezioni.getDocumentElement();
            NodeList listaEccezioni = elementEccezioni.getElementsByTagName("eccezione");
            for (int i = 0; i < listaEccezioni.getLength(); i++) {
                Eccezione eccezione = new Eccezione();
                Element nodeEccezione = (Element) listaEccezioni.item(i);
                String codice = getTestoFiglio("codiceEccezione", nodeEccezione);
                eccezione.setCodiceEccezione(codice);
                eccezione.setContestoCodifica(getTestoFiglio("contestoCodifica", nodeEccezione));
                eccezione.setPosizione(getTestoFiglio("posizione", nodeEccezione));
                eccezione.setRilevanza(getTestoFiglio("rilevanza", nodeEccezione));
                mappaEccezioni.put(codice, eccezione);
            }
            if (logger.isDebugEnabled()) logger.debug("Lista delle eccezioni caricata correttamente");
        } catch (Exception ex) {
            logger.error("Impossibile leggere la lista delle eccezioni.");
            if (logger.isDebugEnabled()) logger.error(ex);
        }
    }

    public Eccezione getEccezione(int cod) {
        return getEccezione(cod + "");
    }

    public Eccezione getEccezione(String codice) {
        Eccezione e = mappaEccezioni.get(codice);
        if (e == null) {
            logger.error("Il codice d'errore " + codice + " non e' definito nel file eccezioni.xml");
            e = new Eccezione();
        }
        Eccezione eccezioneResult = (Eccezione) e.clone();
        eccezioneResult.setCodiceEccezione(eccezioneResult.convertiCodiceEccezione());
        return eccezioneResult;
    }

    private String getTestoFiglio(String figlio, Element el) {
        NodeList nl = el.getElementsByTagName(figlio);
        if (nl.getLength() < 1) {
            return "";
        }
        return nl.item(0).getTextContent();
    }
}
