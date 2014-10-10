package it.unibas.freesbee.ge.processor;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import javax.xml.namespace.QName;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.servicemix.soap.SoapFault;
import org.apache.servicemix.soap.marshalers.SoapMarshaler;
import org.apache.servicemix.soap.marshalers.SoapMessage;
import org.apache.servicemix.soap.marshalers.SoapWriter;

public class ProcessorFault implements Processor {

    public static final String MESSAGGIO_ERRORE = "ERRORE";
    private static Log logger = LogFactory.getLog(ProcessorFault.class);

    public void process(Exchange exchange) throws Exception {
        String errore = exchange.getProperty(MESSAGGIO_ERRORE, String.class);

        SoapMarshaler soapMarshaler = new SoapMarshaler(true);
        soapMarshaler.setSoapUri(SoapMarshaler.SOAP_11_URI);
        soapMarshaler.setPrefix("SOAP_ENV");
        SoapMessage soapMessage = new SoapMessage();

        SoapFault soapFault = new SoapFault(QName.valueOf("SOAP_ENV:Client"), errore);

        soapMessage.setFault(soapFault);

        SoapWriter soapWriter = new SoapWriter(soapMarshaler, soapMessage);
        OutputStream outputStream = new ByteArrayOutputStream();
        soapWriter.write(outputStream);
        outputStream.close();
        String stringaBody = outputStream.toString();

        exchange.getOut().setBody(stringaBody);
        exchange.getOut().getHeaders().clear();

    }
}