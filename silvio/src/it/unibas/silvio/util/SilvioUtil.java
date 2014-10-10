package it.unibas.silvio.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.w3c.dom.DOMException;

@SuppressWarnings("unchecked")
public class SilvioUtil {

    private static org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog(SilvioUtil.class);

    public static String controllaConnettore(String connettore) {
        if (connettore.endsWith("/")) {
            return connettore;
        } else {
            return connettore + "/";
        }
    }

    public static void copiaIntestazioniHTTP(Message in, Message out) {
        Map<String, Object> intestazioni = in.getHeaders();
        Set<String> setChiavi = intestazioni.keySet();
        for (String chiave : setChiavi) {
            Object value = intestazioni.get(chiave);
            out.setHeader(chiave, value);
        }
    }

    public static void copiaQueryStringToHttp(String indirizzoErogatore, Message target) {
        try {
            String queryString = indirizzoErogatore.substring(indirizzoErogatore.indexOf("?") + 1);
            logger.debug("QueryString: " + queryString);
            String[] queries = queryString.split("&");
            for (String q : queries) {
                logger.debug("\tQuery: " + q);
                if (q != null && !q.trim().isEmpty() && q.contains("=")) {
                    q = q.trim();
                    String chiave = q.substring(0, q.indexOf("="));
                    String valore = q.substring(q.indexOf("=") + 1);
                    target.setHeader(chiave, valore);
                    logger.debug("\t\tChiave: " + chiave + " - " + valore);
                }
            }
        } catch (Exception e) {
            if (logger.isInfoEnabled()) {
                e.printStackTrace();
                logger.info("Errore nella copia della query string nelle intestazioni http " + e);
            }
        }
    }

    public static String stampaIntestazioni(Message message) {
        if (message == null) {
            return "Messaggio nullo";
        }
        Map<String, Object> intestazioni = message.getHeaders();
        Set<String> setChiavi = intestazioni.keySet();
        String s = setChiavi.size() + " intestazioni\n";
        for (String chiave : setChiavi) {
            Object value = intestazioni.get(chiave);
            s += "L'intestazione " + chiave + " vale " + value + "\n";
        }
        return s;
    }

    public static List<String> getAttachmentList(Exchange exchange) {
        List<String> lista = (List<String>) exchange.getProperty(CostantiSOAP.SOAP_ATTACHMENT_LIST);
        if (lista == null) {
            lista = new ArrayList<String>();
            exchange.setProperty(CostantiSOAP.SOAP_ATTACHMENT_LIST, lista);
        }
        return lista;
    }

    public static List<SOAPElement> getSOAPHeaderList(Exchange exchange) {
        List<SOAPElement> lista = (List<SOAPElement>) exchange.getProperty(CostantiSOAP.SOAP_HEADER_LIST);
        if (lista == null) {
            lista = new ArrayList<SOAPElement>();
            exchange.setProperty(CostantiSOAP.SOAP_HEADER_LIST, lista);
        }
        return lista;
    }

    public static SOAPElement aggiungiIntestazioneWSAAction(String nomeOperation) throws SOAPException, DOMException {

        SOAPElement elementAction = SOAPFactory.newInstance().createElement(CostantiSOAP.WSA_ACTION);
        elementAction.setTextContent(nomeOperation);
        elementAction.setPrefix(CostantiSOAP.PREFIX_WSA);
        return elementAction;
    }

    public static SOAPElement aggiungiIntestazioneWSAMessageId(String idMessaggio) throws SOAPException, DOMException {
        SOAPElement elementMessageID = SOAPFactory.newInstance().createElement(CostantiSOAP.WSA_MESSAGEID);
        elementMessageID.setTextContent(idMessaggio);
        elementMessageID.setPrefix(CostantiSOAP.PREFIX_WSA);
        return elementMessageID;
    }

    public static SOAPElement aggiungiIntestazioneWSARelatesTo(String idMessaggio) throws SOAPException, DOMException {
        SOAPElement elementMessageID = SOAPFactory.newInstance().createElement(CostantiSOAP.WSA_MESSAGERELATESTOID);
        elementMessageID.setTextContent(idMessaggio);
        elementMessageID.setPrefix(CostantiSOAP.PREFIX_WSA);
        return elementMessageID;
    }

    public static SOAPElement aggiungiIntestazioneWSATo() throws DOMException, SOAPException {

        SOAPElement elementTo = SOAPFactory.newInstance().createElement(CostantiSOAP.WSA_TO);
        elementTo.setTextContent("http://www.w3.org/2005/08/addressing/anonymous");
        elementTo.setPrefix(CostantiSOAP.PREFIX_WSA);
        return elementTo;
    }
}
