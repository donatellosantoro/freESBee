package it.unibas.freesbee.ge.processor;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.servicemix.soap.marshalers.SoapMarshaler;
import org.apache.servicemix.soap.marshalers.SoapMessage;
import org.apache.servicemix.soap.marshalers.SoapWriter;

public class ProcessorAck implements Processor {

    public void process(Exchange exchange) throws Exception {
        SoapMarshaler soapMarshaler = new SoapMarshaler(true);
        soapMarshaler.setSoapUri(SoapMarshaler.SOAP_11_URI);
        soapMarshaler.setPrefix("SOAP_ENV");
        SoapMessage soapMessage = new SoapMessage();

        Reader bodyStringReader = new StringReader("<esito>Operazione effettuata correttamente</esito>");

        if (exchange.getProperty(ProcessorGestioneSottoscrizioni.SOTTOSCRIZIONE_ATTESA_CATEGORIA) != null || exchange.getProperty(ProcessorGestioneSottoscrizioni.SOTTOSCRIZIONE_ATTESA_PUBBLICATORI) != null) {
            bodyStringReader = new StringReader("<esito>Sottoscrizione in attesa di conferma</esito>");
        }
        Source bodySource = new StreamSource(bodyStringReader);
        soapMessage.setSource(bodySource);

        SoapWriter soapWriter = new SoapWriter(soapMarshaler, soapMessage);
        OutputStream outputStream = new ByteArrayOutputStream();
        soapWriter.write(outputStream);
        outputStream.close();
        String stringaBody = outputStream.toString();

        exchange.getOut().setBody(stringaBody);
        exchange.getOut().getHeaders().clear();

    }
}