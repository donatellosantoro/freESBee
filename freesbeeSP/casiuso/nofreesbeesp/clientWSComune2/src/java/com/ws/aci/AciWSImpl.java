/**
 * Please modify this class to meet your needs This class is not complete
 */
package com.ws.aci;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



/**
 * This class was generated by Apache CXF 2.7.4 2013-06-15T00:35:58.036+02:00
 * Generated source version: 2.7.4
 *
 */
@javax.jws.WebService(
        serviceName = "AciWS",
        portName = "AciWSPort",
        targetNamespace = "http://aci.ws.com/",
        //   wsdlLocation = "http://localhost:8080/aciWebService/AciWS?wsdl",
        wsdlLocation = "http://sp.example.unibas:8080/aciWebService/AciWS?wsdl",
        endpointInterface = "com.ws.aci.AciWS")

public class AciWSImpl implements AciWS {

    private static final Log LOG = LogFactory.getLog(AciWSImpl.class);

    /* (non-Javadoc)
     * @see com.ws.aci.AciWS#cercaImmatricolazione(java.lang.String  targa )*
     */
    public com.ws.aci.Immatricolazione cercaImmatricolazione(java.lang.String targa) {
        LOG.info("Executing operation cercaImmatricolazione");
        System.out.println(targa);
        try {
            com.ws.aci.Immatricolazione _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}
