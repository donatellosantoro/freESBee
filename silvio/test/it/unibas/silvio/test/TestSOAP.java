package it.unibas.silvio.test;

import it.unibas.silvio.util.SOAPUtil;
import junit.framework.Assert;
import junit.framework.TestCase;

public class TestSOAP extends TestCase {

    public void testSoapFault() {
        String soapFault = "<?xml version='1.0' encoding='UTF-8'?><SOAP_ENV:Envelope xmlns:SOAP_ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"><SOAP_ENV:Body><SOAP_ENV:Fault><faultcode>SOAP_ENV:Client</faultcode><faultstring>";
        soapFault += "Test Fault";
        soapFault += "</faultstring></SOAP_ENV:Fault></SOAP_ENV:Body></SOAP_ENV:Envelope>";
        
        Assert.assertEquals("Test Fault", SOAPUtil.controllaFault(soapFault));


        String soapSuccess = "<?xml version='1.0' encoding='UTF-8'?><SOAP_ENV:Envelope xmlns:SOAP_ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"><SOAP_ENV:Body>";
        soapSuccess += "Success";
        soapSuccess += "</SOAP_ENV:Body></SOAP_ENV:Envelope>";

        Assert.assertNull(SOAPUtil.controllaFault(soapSuccess));
    }
}
