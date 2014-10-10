package it.unibas.freesbeesla.interoperabilita;

import it.unibas.freesbee.client.cxf.sistemamonitoraggio.GuaranteeTermObj;
import it.unibas.freesbee.client.cxf.sistemamonitoraggio.RequestServiceGuaranteeTermStateType;
import java.text.ParseException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import org.w3c.dom.Document;
import javax.xml.bind.*;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPMessage;
import org.apache.cxf.helpers.XMLUtils;
import org.xml.sax.SAXException;
import it.unibas.freesbee.client.cxf.sistemamonitoraggio.RequestServiceTermStateType;
import it.unibas.freesbeesla.utilita.Utilita;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class AdapterNicaToFreesbee {

    public static String trasformaMessaggioServiceTermState(String messaggio) throws ParserConfigurationException, SAXException, IOException, JAXBException, SOAPException {
        String nomeServizio = getNomeServizio(messaggio);
        String nomeFruitore = getNomeFruitore(messaggio);
        String nomeErogatore = getNomeErogatore(messaggio);

        RequestServiceTermStateType richiesta = new RequestServiceTermStateType();
        richiesta.setIdService(nomeServizio);
        richiesta.setIdInitiator(nomeFruitore);
        richiesta.setIdResponder(nomeErogatore);

        JAXBContext jc = JAXBContext.newInstance("it.unibas.freesbee.client.cxf.sistemamonitoraggio");
        Marshaller m = jc.createMarshaller();
        OutputStream os = new ByteArrayOutputStream();
        m.marshal(richiesta, os);

        MessageFactory messageFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
        SOAPMessage message = messageFactory.createMessage();
        Document bodyDocument = XMLUtils.parse(os.toString());
        message.getSOAPBody().addDocument(bodyDocument);
        message.saveChanges();
        ByteArrayOutputStream streamSOAP = new ByteArrayOutputStream();
        message.writeTo(streamSOAP);

        return streamSOAP.toString();
    }

    public static String trasformaMessaggioServiceGuaranteeTermState(String messaggio) throws ParserConfigurationException, SAXException, IOException, JAXBException, SOAPException, ParseException, DatatypeConfigurationException {
        String nomeServizio = getNomeServizio(messaggio);
        String nomeFruitore = getNomeFruitore(messaggio);
        String nomeErogatore = getNomeErogatore(messaggio);

        RequestServiceGuaranteeTermStateType richiesta = new RequestServiceGuaranteeTermStateType();
        richiesta.setIdService(nomeServizio);
        richiesta.setIdInitiator(nomeFruitore);
        richiesta.setIdResponder(nomeErogatore);

        ArrayList<String> listaIDSla = new ArrayList<String>();

        String messaggioSLA = messaggio;
        while (messaggioSLA != null) {
            int inizio = messaggioSLA.indexOf("<GuaranteeTermObj href=");
            if (inizio != -1) {
                String pezzo = messaggioSLA.substring(inizio);
                getIdsSLA(pezzo, listaIDSla);
                int fine = pezzo.indexOf(">");
                messaggioSLA = pezzo.substring(fine + 1);
            } else {
                messaggioSLA = null;
            }
        }

        messaggioSLA = messaggio;

        for (String id : listaIDSla) {
            GuaranteeTermObj gto = getSLA(messaggioSLA, id);
            richiesta.getGuaranteeTermObj().add(gto);
        }


        JAXBContext jc = JAXBContext.newInstance("it.unibas.freesbee.client.cxf.sistemamonitoraggio");
        Marshaller m = jc.createMarshaller();
        OutputStream os = new ByteArrayOutputStream();
        m.marshal(richiesta, os);

        MessageFactory messageFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
        SOAPMessage message = messageFactory.createMessage();
        Document bodyDocument = XMLUtils.parse(os.toString());
        message.getSOAPBody().addDocument(bodyDocument);
        message.saveChanges();
        ByteArrayOutputStream streamSOAP = new ByteArrayOutputStream();
        message.writeTo(streamSOAP);

        return streamSOAP.toString();
    }

    private static String getNomeServizio(String messaggio) {
        int inizio = messaggio.indexOf("<IdService xsi:type=\"xsd:string\">");
        String primoPezzo = messaggio.substring(inizio);
        int fine = primoPezzo.indexOf(">") + 1;
        String secondoPezzo = primoPezzo.substring(fine);
        int termine = secondoPezzo.indexOf("<");
        return secondoPezzo.substring(0, termine);
    }

    private static String getNomeFruitore(String messaggio) {
        int inizio = messaggio.indexOf("<IdInitiator xsi:type=\"xsd:string\">");
        String primoPezzo = messaggio.substring(inizio);
        int fine = primoPezzo.indexOf(">") + 1;
        String secondoPezzo = primoPezzo.substring(fine);
        int termine = secondoPezzo.indexOf("<");
        return secondoPezzo.substring(0, termine);
    }

    private static String getNomeErogatore(String messaggio) {
        int inizio = messaggio.indexOf("<IdResponder xsi:type=\"xsd:string\">");
        String primoPezzo = messaggio.substring(inizio);
        int fine = primoPezzo.indexOf(">") + 1;
        String secondoPezzo = primoPezzo.substring(fine);
        int termine = secondoPezzo.indexOf("<");
        return secondoPezzo.substring(0, termine);
    }

    //Prelevo la lista degli id degli SLA
    private static void getIdsSLA(String messaggio, ArrayList<String> listaIDSla) {
        int inizio = messaggio.indexOf("#") + 1;
        String primoPezzo = messaggio.substring(inizio);
        int termine = primoPezzo.indexOf("\"");
        listaIDSla.add(primoPezzo.substring(0, termine));
    }

    private static GuaranteeTermObj getSLA(String messaggio, String id) throws ParseException, DatatypeConfigurationException {
        GuaranteeTermObj gto = new GuaranteeTermObj();
        int inizio = messaggio.indexOf("id=\"" + id + "\"");
        String primoPezzo = messaggio.substring(inizio);

        int inizioName = primoPezzo.indexOf("<GuaranteeTermName xsi:type=\"xsd:string\">");
        String pezzoNome = primoPezzo.substring(inizioName);
        int fineNome = pezzoNome.indexOf(">") + 1;
        pezzoNome = pezzoNome.substring(fineNome);
        int termine = pezzoNome.indexOf("<");
        gto.setGuaranteeTermName(pezzoNome.substring(0, termine));

        int inizioData = primoPezzo.indexOf("<DateFn xsi:type=\"xsd:dateTime\">");
        String pezzoData = primoPezzo.substring(inizioData);
        int fineData = pezzoData.indexOf(">") + 1;
        pezzoData = pezzoData.substring(fineData);
        termine = pezzoData.indexOf("<");

        String dataDaFormattare = pezzoData.substring(0, termine);

        gto.setDateFn(Utilita.convertiDateToXMLGregorianCalendar(Utilita.convertiTimestampToXMLDate(dataDaFormattare)));

        return gto;

    }
}
