package it.unibas.freesbeesla.interoperabilita;

import it.unibas.freesbee.client.cxf.sistemamonitoraggio.ResponseServiceGuaranteeTermStateType;
import it.unibas.freesbeesla.utilita.Utilita;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.GregorianCalendar;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.stream.XMLStreamException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.binding.soap.interceptor.SoapPreProtocolOutInterceptor;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

public class MediatorOutInterceptorServiceGuaranteeTermState extends AbstractPhaseInterceptor<Message> {

    private Log logger = LogFactory.getLog(this.getClass());

    public MediatorOutInterceptorServiceGuaranteeTermState() {
        super(Phase.PRE_STREAM);
        addBefore(SoapPreProtocolOutInterceptor.class.getName());
    }

    public void handleMessage(Message message) {

        boolean isMessageOUT = false;
        isMessageOUT = message == message.getExchange().getOutMessage() || message == message.getExchange().getOutFaultMessage();

        //se è un messaggio di uscito o un soapfoult
        if (isMessageOUT) {
            //Copio los originale
            OutputStream os = message.getContent(OutputStream.class);
            //creo un os fittizio e lo sotituisco
            CachedStream cs = new CachedStream();
            message.setContent(OutputStream.class, cs);

            //intercetto nuovamnte il messaggio
            message.getInterceptorChain().doIntercept(message);

            try {
                cs.flush();
                //Prendo il so fittizio
                CachedOutputStream csnew = (CachedOutputStream) message.getContent(OutputStream.class);

                creaMessaggioRPC(csnew, trasformaMessaggio(csnew));

                CachedOutputStream.copyStream(csnew.getInputStream(), os, 1024);

                cs.close();

                os.flush();

                message.setContent(OutputStream.class, os);
            } catch (Exception e) {
                logger.error("Si è verificato un errore nel proxy dei messaggi in uscita. ");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void handleFault(Message message) {
    }

    private class CachedStream extends CachedOutputStream {

        public CachedStream() {
            super();
        }

        @Override
        protected void doFlush() throws IOException {
            currentStream.flush();
        }

        @Override
        protected void doClose() throws IOException {
        }

        @Override
        protected void onWrite() throws IOException {
        }
    }

    public ResponseServiceGuaranteeTermStateType trasformaMessaggio(CachedOutputStream cos) throws Exception {
        OutputStream os = cos.getOut();
        if (os instanceof ByteArrayOutputStream) {
            String messaggio = os.toString();
            logger.info("Messaggio ricevuto Interceptor OUT: " + messaggio);
            return AdapterFreesbeeToNica.trasformaMessaggioRispostaServiceGuaranteeTermState(messaggio);
        }
        throw new Exception("Impossibile leggere il flusso di ingresso.");
    }

    private void creaMessaggioRPC(CachedOutputStream os, ResponseServiceGuaranteeTermStateType risposta) throws XMLStreamException, Exception {

        int i = 1;

        MessageFactory messageFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
        SOAPMessage message = messageFactory.createMessage();

        SOAPPart soapPart = message.getSOAPPart();
        SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
        soapEnvelope.addNamespaceDeclaration("xsd", "http://www.w3.org/2001/XMLSchema");
        soapEnvelope.addNamespaceDeclaration("xsi", "http://www.w3.org/2001/XMLSchema-instance");
        String prefix = soapEnvelope.getPrefix();
        SOAPBody soapBody = soapEnvelope.getBody();

        SOAPElement el = soapBody.addChildElement("getServiceGuaranteeTermStateResponse");
        el.addNamespaceDeclaration("ns1", "http://icar.inf2.sistemamonitoraggio/");

        SOAPElement el1 = el.addChildElement("risposta");
        Name name = SOAPFactory.newInstance().createName("href");
        el1.addAttribute(name, "#id0");

        el = soapBody.addChildElement("multiRef");
        name = SOAPFactory.newInstance().createName("id");
        el.addAttribute(name, "id0");
        name = SOAPFactory.newInstance().createName("soapenc:root");
        el.addAttribute(name, "0");
        name = SOAPFactory.newInstance().createName(prefix + ":encodingStyle");
        el.addAttribute(name, "http://schemas.xmlsoap.org/soap/encoding/");
        name = SOAPFactory.newInstance().createName("xsi:type");
        el.addAttribute(name, "ns2:ResponseServiceGuaranteeTermStateType");
        el.addNamespaceDeclaration("soapenc", "http://schemas.xmlsoap.org/soap/encoding/");
        el.addNamespaceDeclaration("ns2", "http://sistemamonitoraggio.inf2.icar/");

        el1 = el.addChildElement("IdService");
        name = SOAPFactory.newInstance().createName("xsi:type");
        el1.addAttribute(name, "xsd:string");
        el1.addTextNode(risposta.getIdService());

        el1 = el.addChildElement("IdInitiator");
        name = SOAPFactory.newInstance().createName("xsi:type");
        el1.addAttribute(name, "xsd:string");
        el1.addTextNode(risposta.getIdInitiator());

        el1 = el.addChildElement("IdResponder");
        name = SOAPFactory.newInstance().createName("xsi:type");
        el1.addAttribute(name, "xsd:string");
        el1.addTextNode(risposta.getIdResponder());

        for (int j = 0; j < risposta.getResultGaranteeTermObjState().size(); j++) {
            el1 = el.addChildElement("ResultGaranteeTermObjState");
            name = SOAPFactory.newInstance().createName("href");
            el1.addAttribute(name, "#id" + i++);
        }

        int num = i;
        for (int j=1; j<num; j++) {
            el = soapBody.addChildElement("multiRef");
            name = SOAPFactory.newInstance().createName("id");
            el.addAttribute(name, "id" + j);
            name = SOAPFactory.newInstance().createName("soapenc:root");
            el.addAttribute(name, "0");
            name = SOAPFactory.newInstance().createName(prefix + ":encodingStyle");
            el.addAttribute(name, "http://schemas.xmlsoap.org/soap/encoding/");
            el.addNamespaceDeclaration("soapenc", "http://schemas.xmlsoap.org/soap/encoding/");


            el1 = el.addChildElement("GuaranteeTermName");
            name = SOAPFactory.newInstance().createName("xsi:type");
            el1.addAttribute(name, "xsd:string");
            el1.addTextNode(risposta.getResultGaranteeTermObjState().get(j-1).getGuaranteeTermName());

            el1 = el.addChildElement("GuaranteeState");
            name = SOAPFactory.newInstance().createName("href");
            el1.addAttribute(name, "#id" + i++);

            el1 = el.addChildElement("ObserveInterval");
            name = SOAPFactory.newInstance().createName("href");
            el1.addAttribute(name, "#id" + i++);


            el = soapBody.addChildElement("multiRef");
            name = SOAPFactory.newInstance().createName("id");
            el.addAttribute(name, "id"+(i-2));
            name = SOAPFactory.newInstance().createName("soapenc:root");
            el.addAttribute(name, "0");
            name = SOAPFactory.newInstance().createName(prefix + ":encodingStyle");
            el.addAttribute(name, "http://schemas.xmlsoap.org/soap/encoding/");
            name = SOAPFactory.newInstance().createName("xsi:type");
            el.addAttribute(name, "ns3:GuaranteeStateType");
            el.addNamespaceDeclaration("soapenc", "http://schemas.xmlsoap.org/soap/encoding/");
            el.addNamespaceDeclaration("ns3", "http://sistemamonitoraggio.inf2.icar/");
            el.addTextNode(risposta.getResultGaranteeTermObjState().get(j-1).getGuaranteeState().value());

            el = soapBody.addChildElement("multiRef");
            name = SOAPFactory.newInstance().createName("id");
            el.addAttribute(name, "id"+(i-1));
            name = SOAPFactory.newInstance().createName("soapenc:root");
            el.addAttribute(name, "0");
            name = SOAPFactory.newInstance().createName(prefix + ":encodingStyle");
            el.addAttribute(name, "http://schemas.xmlsoap.org/soap/encoding/");
            el.addNamespaceDeclaration("soapenc", "http://schemas.xmlsoap.org/soap/encoding/");


            el1 = el.addChildElement("DateIn");
            name = SOAPFactory.newInstance().createName("xsi:type");
            el1.addAttribute(name, "xsd:dateTime");
            el1.addTextNode(Utilita.convertiDateToXMLGregorianCalendar(new GregorianCalendar().getTime()).toXMLFormat());


            el1 = el.addChildElement("DateFn");
            name = SOAPFactory.newInstance().createName("xsi:type");
            el1.addAttribute(name, "xsd:dateTime");
            el1.addTextNode(risposta.getResultGaranteeTermObjState().get(j-1).getObserveInterval().getDateFn().toXMLFormat());
        }
        
        
        message.saveChanges();
        ByteArrayOutputStream streamSOAP = new ByteArrayOutputStream();
        message.writeTo(streamSOAP);

        os.resetOut(streamSOAP, false);

    }
}