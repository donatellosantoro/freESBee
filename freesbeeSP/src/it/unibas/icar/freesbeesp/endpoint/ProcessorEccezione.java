package it.unibas.icar.freesbeesp.endpoint;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProcessorEccezione implements Processor {

    private Log logger = LogFactory.getLog(this.getClass());

    public ProcessorEccezione() {
    }

    public void process(Exchange exchange) throws Exception {
        logger.info("Siamo arrivati!!!");
        Throwable eccezione = (Exception) exchange.getIn().getHeader("caught.exception");
        logger.info("Eccezione " + eccezione);

        String soapFault = "<?xml version='1.0' encoding='UTF-8'?><SOAP_ENV:Envelope xmlns:SOAP_ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"><SOAP_ENV:Body><SOAP_ENV:Fault><faultcode>SOAP_ENV:Client</faultcode><faultstring>";
        soapFault += eccezione.getMessage();
        soapFault += "</faultstring></SOAP_ENV:Fault></SOAP_ENV:Body></SOAP_ENV:Envelope>";

        logger.info("SOAP FAULT: " + soapFault);

        exchange.getOut().setBody(soapFault, String.class);
        
    }
}
