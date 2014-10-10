package it.unibas.silvio.util;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import it.unibas.silvio.modello.Messaggio;
import java.net.URL;
import java.util.GregorianCalendar;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.contrib.ssl.EasySSLProtocolSocketFactory;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.protocol.Protocol;

public class SLAUtil {

    private static org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog(SLAUtil.class);
    public static final String COMPLETED = "Completed";
    public static final String NOT_READY = "Not_Ready";
    public static final String READY_IDLE = "Ready_Idle";
    public static final String READY_PROCESSING = "Ready_Processing";

    public static void updateServiceTermState_Processing(Messaggio messaggio, String indirizzoWS) {
        updateServiceTermState(messaggio, indirizzoWS, READY_PROCESSING);
    }

    public static void updateServiceTermState_Idle(Messaggio messaggio, String indirizzoWS) {
        updateServiceTermState(messaggio, indirizzoWS, READY_IDLE);
    }

    public static void updateServiceTermState(Messaggio messaggio, String indirizzoWS, String type) {
        if (StringUtil.isVuota(indirizzoWS)) {
            logger.warn("Impossibile aggiornare gli SLA. L'indirizzo del WS degli SLA non è stato impostato");
        }
        String idMittente = messaggio.getSlaMittente();
        logger.info("SLA: idMittente: " + idMittente);
        String idDestinatario = messaggio.getSlaDestinatario();
        logger.info("SLA: idDestinatario: " + idDestinatario);
        String idServizio = messaggio.getSlaServizio();
        logger.info("SLA: idServizio: " + idServizio);
        try {
            HttpClient httpClient = new HttpClient();
            URL url = new URL(indirizzoWS);
            Protocol protocol = new Protocol("https", new EasySSLProtocolSocketFactory(), url.getPort());
            Protocol.registerProtocol("https", protocol);
            logger.info("Devo contattare la pagina " + indirizzoWS);
            PostMethod method = new PostMethod(indirizzoWS);

            String messaggioSOAP = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:sis=\"http://sistematracciatura.freesbee.unibas.it/\">" +
                    "<soapenv:Header/><soapenv:Body>" +
                    "<sis:richiesta>" +
                    "<IdService>" + idServizio + "</IdService>" +
                    "<IdInitiator>" + idMittente + "</IdInitiator>" +
                    "<IdResponder>" + idDestinatario + "</IdResponder>" +
                    "<ServiceState>" + type + "</ServiceState>" +
                    "</sis:richiesta>" +
                    "</soapenv:Body>" +
                    "</soapenv:Envelope>";
            logger.info("Effettuo l'aggiornamento dello sla con il seguente messaggio:\n " + messaggioSOAP);

            RequestEntity request = new StringRequestEntity(messaggioSOAP, "application/soap+xml;charset=UTF-8;", null);
            method.setRequestEntity(request);
            httpClient.executeMethod(method);

            if (logger.isInfoEnabled()) {
                logger.info(HttpUtil.stampaMethod(method));
            }
            if (method.getStatusCode() < 200 || method.getStatusCode() > 299) {
                logger.warn("Impossibile aggiornare gli SLA. Ho ricevuto come risposta uno stato " + method.getStatusCode());
            }

        } catch (Exception ex) {
            logger.warn("Impossibile aggiornare gli SLA. " + ex);
        }
    }

    public static void insertServiceBasicMetric(Messaggio messaggio, long duration, String indirizzoWS) {
        if (StringUtil.isVuota(indirizzoWS)) {
            logger.warn("Impossibile aggiornare gli SLA. L'indirizzo del WS degli SLA non è stato impostato");
        }
        String idMittente = messaggio.getSlaMittente();
        logger.info("SLA: idMittente: " + idMittente);
        String idDestinatario = messaggio.getSlaDestinatario();
        logger.info("SLA: idDestinatario: " + idDestinatario);
        String idServizio = messaggio.getSlaServizio();
        logger.info("SLA: idServizio: " + idServizio);
        GregorianCalendar calendar = new GregorianCalendar();
        XMLGregorianCalendar data = new XMLGregorianCalendarImpl(calendar);
        try {
            HttpClient httpClient = new HttpClient();
            URL url = new URL(indirizzoWS);
            Protocol protocol = new Protocol("https", new EasySSLProtocolSocketFactory(), url.getPort());
            Protocol.registerProtocol("https", protocol);
            logger.info("Devo contattare la pagina " + indirizzoWS);
            PostMethod method = new PostMethod(indirizzoWS);

            String messaggioSOAP = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:sis=\"http://sistematracciatura.freesbee.unibas.it/\">" +
                    "<soapenv:Header/><soapenv:Body>" +
                    "<sis:richiestaInsert>" +
                    "<IdService>" + idServizio + "</IdService>" +
                    "<IdInitiator>" + idMittente + "</IdInitiator>" +
                    "<IdResponder>" + idDestinatario + "</IdResponder>" +
                    "<BasicMetric>tempoRisposta</BasicMetric>" +
                    "<BasicMetricValue>" + duration + "</BasicMetricValue>" +
                    "<Date>" + data + "</Date>" +
                    "</sis:richiestaInsert>" +
                    "</soapenv:Body>" +
                    "</soapenv:Envelope>";
            logger.info("Effettuo l'aggiornamento delle metriche dello sla con il seguente messaggio:\n " + messaggioSOAP);

            RequestEntity request = new StringRequestEntity(messaggioSOAP, "application/soap+xml;charset=UTF-8;", null);
            method.setRequestEntity(request);
            httpClient.executeMethod(method);

            if (logger.isInfoEnabled()) {
                logger.info(HttpUtil.stampaMethod(method));
            }
            if (method.getStatusCode() < 200 || method.getStatusCode() > 299) {
                logger.warn("Impossibile aggiornare gli SLA. Ho ricevuto come risposta uno stato " + method.getStatusCode());
            }
        } catch (Exception ex) {
            logger.warn("Impossibile aggiornare gli SLA. " + ex);
        }
    }
}
