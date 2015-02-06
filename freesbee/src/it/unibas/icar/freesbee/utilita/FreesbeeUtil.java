package it.unibas.icar.freesbee.utilita;

import it.unibas.icar.freesbee.exception.FreesbeeException;
import it.unibas.icar.freesbee.modello.Messaggio;
import java.util.Map;
import java.util.Set;
import javax.xml.namespace.QName;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.NodeList;

@SuppressWarnings("unchecked")
public class FreesbeeUtil {

    private static Log logger = LogFactory.getLog(FreesbeeUtil.class);
    public static final String PATTERN_INONLY = "http://www.w3.org/2004/08/wsdl/in-only";
    public static final String PATTERN_INOUT = "http://www.w3.org/2004/08/wsdl/in-out";
    public static final QName defaultQName = new QName("http://icar.unibas.it/FreESBee", "frsb");
    public static final String FREESBEE_DEFAULT_NAMESPACE = "http://icar.unibas.it/FreESBee";
    public static final String FREESBEE_DEFAULT_NAMESPACE_PREF = "frsb";
    public static final String HTTP_REQUEST_HEADER = "org.apache.camel.component.http.header";

    public static String leggiDettagliPDDinamica(Message message, String query, String key) {
        String value = leggiIntestazioneHttp(message, key);
        if (value == null) {
            value = leggiQueryString(query, key);
        }
        return value;
    }

    public static String leggiQueryString(String query, String key) {
        if (query == null || key == null) {
            return null;
        }
        String[] token = query.split("&");
        for (int i = 0; i < token.length; i++) {
            String chiave_valore = token[i];
            int posEqual = chiave_valore.indexOf("=");
            if (posEqual != -1) {
                String chiave = chiave_valore.substring(0, posEqual);
                String valore = chiave_valore.substring(posEqual + 1);
                if (chiave.equalsIgnoreCase(key)) {
                    return valore;
                }
            }
        }
        return null;
    }

    public static String leggiIntestazioneHttp(Message message, String key) {
        String header = null;
        Map<String, Object> intestazioni = message.getHeaders();
        Set<String> setChiavi = intestazioni.keySet();
        for (String chiave : setChiavi) {
            if (chiave.equalsIgnoreCase(key)) {
                header = (String) intestazioni.get(chiave);
            }
        }
        return header;
    }

    public static void aggiungiInstestazioniHttp(Message messaggio, String chiave, String valore) {
        if (messaggio == null || chiave == null) {
            logger.error("Si sta cercando di aggiungere delle instestazioni HTTP ad un messaggio nullo o con una chiave nulla.");
            return;
        }
        //AGGIUNGO LE INTESTAZIONI DIRETTAMENTE AL MESSAGGIO        
        if (logger.isDebugEnabled()) logger.debug("Si aggiungono le intestazioni HTTP " + chiave + ": " + valore);
        messaggio.setHeader(chiave, valore);
    }

    public static String stampaIntestazioni(Exchange exchange) {
        if (exchange == null) {
            return "Exchange nullo";
        }
        Map<String, Object> intestazioni = exchange.getProperties();
        Set<String> setChiavi = intestazioni.keySet();
        String s = setChiavi.size() + " properties\n";
        for (String chiave : setChiavi) {
            Object value = intestazioni.get(chiave);
            s += "L'intestazione " + chiave + " vale " + value + "\n";
        }
        return s;
    }

    public static String controllaConnettore(String connettore) {
        if (connettore.endsWith("/")) {
            return connettore;
        } else {
            return connettore + "/";
        }
    }

    // METODI PER LA CORRELAZIONE DEI MESSAGGI
    public static String cercaIntestazioniIDWsa(Exchange exchange) {
        Map<QName, DocumentFragment> mappaHeaders = (Map<QName, DocumentFragment>) exchange.getProperty(CostantiSOAP.SOAP_HEADERS);
        if (mappaHeaders == null) {
            return null;
        }
        DocumentFragment idWSA = mappaHeaders.get(CostantiSOAP.WSA_MESSAGEID);
        if (idWSA == null) {
            return null;
        }
        return idWSA.getTextContent();
    }

    public static String cercaIntestazioniIDIntegrationManager(Exchange exchange) {
        Map<QName, DocumentFragment> mappaHeaders = (Map<QName, DocumentFragment>) exchange.getProperty(CostantiSOAP.SOAP_HEADERS);
        if (mappaHeaders == null) {
            return null;
        }
        DocumentFragment idIntegr = mappaHeaders.get(CostantiSOAP.IM_HEADER);
        if (idIntegr == null || !idIntegr.hasChildNodes()) {
            return null;
        }
        NodeList nodeList = idIntegr.getChildNodes();
        return BustaUtil.getAttributo(nodeList.item(0), CostantiSOAP.INTEGRAZIONE_ID_MESSAGGIO);
    }

    public static String cercaIntestazioniIDHttpHeader(Message message) {
        if (message == null) {
            throw new IllegalArgumentException("Il Camel Message e' null. Impossibile leggere le intestazioni.");
        }
        String idSil = (String) message.getHeader(CostantiSOAP.INTEGRAZIONE_ID_MESSAGGIO);
        return idSil;
    }

    public static String cercaIntestazioniIDRelatesToIntegrationManager(Exchange exchange) {
        Map<QName, DocumentFragment> mappaHeaders = (Map<QName, DocumentFragment>) exchange.getProperty(CostantiSOAP.SOAP_HEADERS);
        if (mappaHeaders == null) {
            return null;
        }
        DocumentFragment idIntegr = mappaHeaders.get(CostantiSOAP.IM_HEADER);
        if (idIntegr == null || !idIntegr.hasChildNodes()) {
            return null;
        }
        NodeList nodeList = idIntegr.getChildNodes();
        return BustaUtil.getAttributo(nodeList.item(0), CostantiSOAP.INTEGRAZIONE_RIFERIMENTO_MESSAGGIO);
    }

    public static String cercaIntestazioniIDRelatesToWsa(Exchange exchange) {
        Map<QName, DocumentFragment> mappaHeaders = (Map<QName, DocumentFragment>) exchange.getProperty(CostantiSOAP.SOAP_HEADERS);
        if (logger.isDebugEnabled()) logger.debug("La mappa delle instestazioni del messaggio vale: " + mappaHeaders);
        if (mappaHeaders == null) {
            return null;
        }
//        Set<QName> setQ = mappaHeaders.keySet();
//        for (QName qName : setQ) {
//            if (logger.isInfoEnabled())
//                logger.info("\t" + qName);
//        }
        if (mappaHeaders == null) {
            return null;
        }
        DocumentFragment relatesWSA = mappaHeaders.get(CostantiSOAP.WSA_MESSAGERELATESTOID);
        if (relatesWSA == null) {
            return null;
        }
        return relatesWSA.getTextContent();
    }

    public static String cercaIntestazioniIDRelatesToHttpHeader(Message message) {
        if (message == null) {
            throw new IllegalArgumentException("Il Camel Message e' null. Impossibile leggere le intestazioni.");
        }
        String idSilRelatesTo = (String) message.getHeader(CostantiSOAP.INTEGRAZIONE_RIFERIMENTO_MESSAGGIO);
        return idSilRelatesTo;
    }

    public static void aggiungiIntestazioniInteroperabilita(Message message, Messaggio messaggio) {
        if (message == null || messaggio == null) {
            logger.error("Si sta cercando di aggiungere delle instestazioni di interoperabilita' ad un messaggio nullo.");
            return;
        }
        if (messaggio.getIdSil() != null) {
            FreesbeeUtil.aggiungiInstestazioniHttp(message, CostantiSOAP.INTEGRAZIONE_ID_MESSAGGIO, messaggio.getIdSil());
        }
        if (messaggio.getSilRelatesTo() != null) {
            FreesbeeUtil.aggiungiInstestazioniHttp(message, CostantiSOAP.INTEGRAZIONE_RIFERIMENTO_MESSAGGIO, messaggio.getSilRelatesTo());
        }
    }

    public static String estraiIntestazioniHTTP(Message message) {
        String stringaEstratta = "";
        String contentType = (String) message.getHeader("Content-Type");
        String mimeVersion = "MIME-Version: " + (String) message.getHeader("MIME-Version");

        if (mimeVersion != null) {
            stringaEstratta += "MIME-Version: " + mimeVersion + "\n";
        }
        if (contentType != null) {
            stringaEstratta += "Content-Type: " + contentType + "\n";
        }

        stringaEstratta += "\n";

        return stringaEstratta;
    }

    public static String pulisciSoapAction(String soapAction) {
        if (soapAction == null || soapAction.trim().isEmpty()) {
            return "";
        }
        String s = soapAction.trim();
        if (s.startsWith("\"") && s.endsWith("\"")) {
            s = s.substring(1, s.length() - 1);
        }
        return s;
    }

    public static void copiaIntestazioniMime(Message src, Message dest) {
        String mime = (String) src.getHeader("MIME-Version");
        if (mime != null) {
            FreesbeeUtil.aggiungiInstestazioniHttp(dest, "MIME-Version", mime);
        }
        String content = (String) src.getHeader("Content-Type");
        if (content != null) {
            FreesbeeUtil.aggiungiInstestazioniHttp(dest, "Content-Type", content);
        }
    }

    public static int impostaNumeroPortaDaIndirizzo(String indirizzoCompleto) throws FreesbeeException {
        if ((!indirizzoCompleto.contains("//"))) {
            throw new FreesbeeException("L'indirizzo specificato: " + indirizzoCompleto + " non definisce quale protocollo utilizzare.");
        }
        
        String sottostringaIndirizzo = indirizzoCompleto.substring(indirizzoCompleto.indexOf("//") + 2);
        
        try {
            if (sottostringaIndirizzo.contains(":")) {
                String stringaNumeroPorta = sottostringaIndirizzo.substring(sottostringaIndirizzo.indexOf(":") + 1, sottostringaIndirizzo.indexOf("/"));
                return Integer.parseInt(stringaNumeroPorta);
            }
        
            if (indirizzoCompleto.contains("https")) {
                return Integer.parseInt("443");
            }
            
            return Integer.parseInt("80");
        } catch (NumberFormatException nfe) {
            logger.error("Il numero di porta specificato e' errato. L'indirizzo completo e': " + indirizzoCompleto);
            if (logger.isDebugEnabled()) nfe.printStackTrace();
            throw new FreesbeeException("Il numero di porta specificato e' errato. L'indirizzo completo e': " + indirizzoCompleto);
        }
    }
}
