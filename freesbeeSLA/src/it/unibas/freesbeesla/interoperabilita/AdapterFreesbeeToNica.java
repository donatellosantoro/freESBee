package it.unibas.freesbeesla.interoperabilita;

import it.unibas.freesbee.client.cxf.sistemamonitoraggio.ResponseServiceGuaranteeTermStateType;
import it.unibas.freesbee.client.cxf.sistemamonitoraggio.ResponseServiceTermStateType;
import java.io.ByteArrayInputStream;
import java.io.Reader;
import java.io.StringReader;
import javax.xml.soap.SOAPException;
import org.w3c.dom.Document;
import javax.xml.bind.*;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import org.apache.cxf.helpers.XMLUtils;

public class AdapterFreesbeeToNica {

    public static ResponseServiceTermStateType trasformaMessaggioRispostaServiceTermState(String messaggio) throws SOAPException, JAXBException {
        MessageFactory messageFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
        SOAPMessage message = messageFactory.createMessage();
        SOAPPart soapPart = message.getSOAPPart();
        Reader soapStringReader = new StringReader(messaggio);
        Source soapSource = new StreamSource(soapStringReader);
        soapPart.setContent(soapSource);
        message.saveChanges();
        String streamSoap = "";

        if (message.getSOAPBody() != null && message.getSOAPBody().hasChildNodes()) {
            Document document = message.getSOAPBody().extractContentAsDocument();
            streamSoap = XMLUtils.toString(document);

            streamSoap = streamSoap.replace("risposta", "ResponseServiceTermStateType");
            int f = streamSoap.indexOf('>');
            streamSoap = (String) streamSoap.subSequence(f + 1, streamSoap.length());

        }
        JAXBContext jc = JAXBContext.newInstance("it.unibas.freesbee.client.cxf.sistemamonitoraggio");
        Unmarshaller u = jc.createUnmarshaller();
        ResponseServiceTermStateType risposta = (ResponseServiceTermStateType) u.unmarshal(new ByteArrayInputStream(streamSoap.getBytes()));
        return risposta;
    }

    public static ResponseServiceGuaranteeTermStateType trasformaMessaggioRispostaServiceGuaranteeTermState(String messaggio) throws SOAPException, JAXBException {
        MessageFactory messageFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
        SOAPMessage message = messageFactory.createMessage();
        SOAPPart soapPart = message.getSOAPPart();
        Reader soapStringReader = new StringReader(messaggio);
        Source soapSource = new StreamSource(soapStringReader);
        soapPart.setContent(soapSource);
        message.saveChanges();
        String streamSoap = "";

        if (message.getSOAPBody() != null && message.getSOAPBody().hasChildNodes()) {
            Document document = message.getSOAPBody().extractContentAsDocument();
            streamSoap = XMLUtils.toString(document);

            streamSoap = streamSoap.replace("risposta", "ResponseServiceGuaranteeTermStateType");
            int f = streamSoap.indexOf('>');
            streamSoap = (String) streamSoap.subSequence(f + 1, streamSoap.length());

        }
        JAXBContext jc = JAXBContext.newInstance("it.unibas.freesbee.client.cxf.sistemamonitoraggio");
        Unmarshaller u = jc.createUnmarshaller();
        ResponseServiceGuaranteeTermStateType risposta = (ResponseServiceGuaranteeTermStateType) u.unmarshal(new ByteArrayInputStream(streamSoap.getBytes()));

        return risposta;
    }
}
