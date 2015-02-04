package it.unibas.icar.freesbee.processors;

import it.unibas.icar.freesbee.contrib.SoapWriterFix;
import it.unibas.icar.freesbee.exception.FreesbeeException;
import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.utilita.CostantiBusta;
import it.unibas.icar.freesbee.utilita.CostantiSOAP;
import it.unibas.icar.freesbee.utilita.FreesbeeUtil;
import it.unibas.icar.freesbee.utilita.MessageUtil;
import it.unibas.icar.freesbee.xml.XmlUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.helpers.XMLUtils;
import org.apache.servicemix.soap.SoapFault;
import org.apache.servicemix.soap.marshalers.SoapMarshaler;
import org.apache.servicemix.soap.marshalers.SoapMessage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SOAPProcessorWriterFactory {

    private static Log logger = LogFactory.getLog(SOAPProcessorWriterFactory.class);
    private static SOAPProcessorWriterFactory singleton = new SOAPProcessorWriterFactory();
    private Map<String, SOAPProcessorWriter> cache = new HashMap<String, SOAPProcessorWriter>();

    public static SOAPProcessorWriterFactory getInstance() {
        return singleton;
    }

    private SOAPProcessorWriterFactory() {
    }

    public Processor getProcessorWriter(String codiceErrore, String tipoErrore) {
        SOAPProcessorWriter processor = this.cache.get(codiceErrore + "-" + tipoErrore);
        if (processor == null) {
            processor = new SOAPProcessorWriter(codiceErrore, tipoErrore);
            this.cache.put(codiceErrore + "-" + tipoErrore, processor);
        }
        return processor;
    }

    public Processor getProcessorWriter() {
        SOAPProcessorWriter processor = this.cache.get("-");
        if (processor == null) {
            processor = new SOAPProcessorWriter();
            this.cache.put("-", processor);
        }
        return processor;
    }

    private class SOAPProcessorWriter implements Processor {

        private String codiceErrore = "001";
        private String tipoErrore = "SOAP_ENV:Client";

        public SOAPProcessorWriter() {
        }

        public SOAPProcessorWriter(String codiceErrore, String tipoErrore) {
            this.codiceErrore = codiceErrore;
            this.tipoErrore = tipoErrore;
        }

        public void process(Exchange exchange) throws Exception {
            //ContextStartup.aggiungiThread(this.getClass().getName());
            ProcessorLogFactory.getInstance().getProcessorLog(this.getClass()).process(exchange);
            SoapMarshaler soapMarshaler = new SoapMarshaler(true);
            soapMarshaler.getInputFactory().setProperty("javax.xml.stream.isCoalescing", true);  // legge il contenuto del tag come stringa
            soapMarshaler.getInputFactory().setProperty("javax.xml.stream.isReplacingEntityReferences", false);  // non dovrebbe cambiare le entita'
            soapMarshaler.setSoapUri(CostantiSOAP.SOAP_VERSION);
            soapMarshaler.setPrefix("SOAP_ENV");

            String eccezioneRestituire = (String) exchange.getProperty(CostantiBusta.ECCEZIONE_DA_RESTITUIRE);
            SoapFault faultApplicativo = (SoapFault) exchange.getProperty(CostantiSOAP.SOAP_FAULT);
            SoapMessage soapMessage = new SoapMessage();

//        Exception e = (Exception) exchange.getIn().getHeader("caught.exception");
            Exception e = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
            if (e != null) {
//        if (exchange.isFailed()) {
                //E' UN MESSAGGIO DI ERRORE. GENERIAMO UN SOAP FAULT
                Throwable eccezione = e;
                if (logger.isInfoEnabled()) logger.info("E' stato ricevuto un messaggio di errore con la seguente eccezione " + eccezione);
                String stringaEccezione = eccezione.toString();
                if (eccezione instanceof FreesbeeException) {
                    stringaEccezione = "EGOV_IT_" + codiceErrore + " - Formato Busta non corretto";
//                stringaEccezione = "\n<![CDATA[ EGOV_IT_" + codiceErrore + " - Formato Busta non corretto]]>\n";
                }
                Map mappaHeaderSoapFault = (Map) exchange.getProperty(CostantiSOAP.SOAP_HEADERS + ".fault");
                SoapFault soapFault = new SoapFault(QName.valueOf(tipoErrore), stringaEccezione);

                soapMessage.setFault(soapFault);
                if (logger.isDebugEnabled()) logger.debug("MappaHeaderSoapFault " + mappaHeaderSoapFault);
                soapMessage.setHeaders(mappaHeaderSoapFault);
            } else if (eccezioneRestituire != null) {
                //LA PORTA DI DOMINIO HA RICEVUTO UNA RISPOSTA DI FAULT CHE DOBBIAMO INOLTRARE AL RICHIEDENTE
                Map mappaHeaderSoap = (Map) exchange.getProperty(CostantiSOAP.SOAP_HEADERS);
                if (logger.isInfoEnabled()) logger.info("Si sta inoltrando l'eccezione " + eccezioneRestituire);
                SoapFault soapFault = new SoapFault(QName.valueOf("env:Server"), eccezioneRestituire);

                soapMessage.setFault(soapFault);
                soapMessage.setHeaders(mappaHeaderSoap);
            } else if (faultApplicativo != null) {
                //IL SERVIZIO APPLICATIVO MI HA RISPOSTO CON UN FAULT CHE DOBBIAMO INOLTRARE AL RICHIEDENTE
                Map mappaHeaderSoap = (Map) exchange.getProperty(CostantiSOAP.SOAP_HEADERS);
                if (logger.isInfoEnabled()) logger.info("Si sta inoltrando l'eccezione " + faultApplicativo.getReason());

                soapMessage.setFault(faultApplicativo);
                soapMessage.setHeaders(mappaHeaderSoap);
            } else {
                Map mappaHeaderSoap = (Map) exchange.getProperty(CostantiSOAP.SOAP_HEADERS);
                Map mappaAttachment = (Map) exchange.getProperty(CostantiSOAP.SOAP_ATTACHMENT);
                if (mappaAttachment != null) {
                    //DEVO AGGIUNGERE L'ATTACHMENT
                    soapMessage.setAttachments(mappaAttachment);
                    if (logger.isDebugEnabled()) logger.debug("Mappa degli attachment " + mappaAttachment);
                    exchange.removeProperty(CostantiSOAP.SOAP_ATTACHMENT);
                }
                if (logger.isDebugEnabled()) logger.debug("Si sta scrivendo il messaggio con il seguente body\n" + MessageUtil.getString(exchange.getIn()));
                if (MessageUtil.isEmpty(exchange.getIn())) {
                    if (logger.isInfoEnabled()) logger.info("E' stato ricevuto un messaggio vuoto.");
                    return;
                }
                if (exchange.getProperty(CostantiBusta.FIGLI_MULTIPLI) != null && exchange.getProperty(CostantiBusta.FIGLI_MULTIPLI).equals("true")) {
                    if (logger.isDebugEnabled()) logger.debug("Il messaggio da scrivere ha più figli nel body");
                } else {
                    soapMessage.setSource(MessageUtil.getSource(exchange.getIn()));
                }
                soapMessage.setHeaders(mappaHeaderSoap);
            }
            
          SoapWriterFix soapWriter = new SoapWriterFix(soapMarshaler, soapMessage);
//            SoapWriter soapWriter = new SoapWriter(soapMarshaler, soapMessage);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            soapWriter.write(outputStream);
            ByteArrayInputStream streamBody = new ByteArrayInputStream(outputStream.toByteArray());
            IOUtils.closeQuietly(outputStream);
            if (exchange.getProperty(CostantiBusta.FIGLI_MULTIPLI) != null && exchange.getProperty(CostantiBusta.FIGLI_MULTIPLI).equals("true")
                    && !MessageUtil.isEmpty(exchange.getIn())) {
                String bodyMessaggio = MessageUtil.getString(exchange.getIn());
                String stringaBody = IOUtils.toString(streamBody);
                streamBody.reset();
                stringaBody = scriviBodyConPiuFigli(stringaBody, bodyMessaggio);
                MessageUtil.setString(exchange.getIn(), stringaBody);
            }

            Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiBusta.MESSAGGIO);
            Message messageIn = exchange.getIn();
            String soapAction = (String) messageIn.getHeader("SOAPAction");
            Object nica = messageIn.getHeader(CostantiBusta.RUOLO_NICA);
            Object profiloColl = messageIn.getHeader(CostantiBusta.VALOREPROFILOCOLLABORAZIONE);
//            MessageUtil.setString(exchange.getIn(),stringaBody);
            MessageUtil.setStream(exchange.getIn(), streamBody);
            exchange.getIn().getHeaders().clear();
            FreesbeeUtil.aggiungiInstestazioniHttp(messageIn, "SOAPAction", soapAction);
            exchange.setProperty(CostantiBusta.MESSAGGIO, messaggio);
            exchange.setProperty(CostantiBusta.RUOLO_NICA, nica);
            exchange.setProperty(CostantiBusta.VALOREPROFILOCOLLABORAZIONE, profiloColl);

            if (soapMessage.hasAttachments()) {
                FreesbeeUtil.aggiungiInstestazioniHttp(messageIn, "Content-Type", soapWriter.getContentType());
            } else {
                FreesbeeUtil.aggiungiInstestazioniHttp(messageIn, "Content-Type", "text/xml;");
            }
            
            if (logger.isDebugEnabled()) logger.debug("La creazione del messaggio SOAP e' stata completata con successo. Il messaggio generato e': " + MessageUtil.getString(exchange.getIn()));
        }

        private String scriviBodyConPiuFigli(String stringaBody, String bodyMessaggio) {
            if (logger.isDebugEnabled()) logger.debug("Si sta aggiungendo a \n" + stringaBody + "\n il seguente body: \n" + bodyMessaggio);
            try {
                Document docMessaggio = XMLUtils.parse(stringaBody);
                bodyMessaggio = "<body>" + bodyMessaggio + "</body>";
//            Document docBody = XMLUtils.parse(bodyMessaggio);
//            Node nodeBody = docBody.getDocumentElement();            

                Reader bodyStringReader = new StringReader(bodyMessaggio);
                Source bodySource = new StreamSource(bodyStringReader);
                Node nodeBody = XMLUtils.fromSource(bodySource).getFirstChild();

                Element envElem = docMessaggio.getDocumentElement();
                if (envElem != null && envElem.getLocalName().compareToIgnoreCase("envelope") == 0) {
                    NodeList envChild = envElem.getChildNodes();
                    Node elementBody = null;
                    for (int i = 0; i < envChild.getLength(); i++) {
                        if (envChild.item(i).getLocalName().equalsIgnoreCase("body")) {
                            elementBody = envChild.item(i);
                        }
                    }
                    if (elementBody != null) {
                        NodeList elementiAggiungere = nodeBody.getChildNodes();
                        for (int i = 0; i < elementiAggiungere.getLength(); i++) {
                            Node nodoAggiungere = elementiAggiungere.item(i).cloneNode(true);
                            if (logger.isDebugEnabled()) logger.debug("Si sta aggiungendo l'elemento " + nodoAggiungere);
                            elementBody.appendChild(docMessaggio.importNode(nodoAggiungere, true));
                        }
                    }
                }
                return XmlUtil.stampaDocument(docMessaggio, true);
            } catch (Exception e) {
                logger.error("Impossibile aggiungere il body con più figli.");
                if (logger.isDebugEnabled()) e.printStackTrace();
            }
            return stringaBody;
        }
    }
}
