package it.unibas.freesbee.ge.web.ws.security;

import it.unibas.freesbee.ge.web.modello.Utente;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.binding.soap.saaj.SAAJOutInterceptor;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.handler.WSHandlerConstants;

public class SecurityUtil {

    private static Log logger = LogFactory.getLog(SecurityUtil.class);
    
    public static void settaIntestazioniSicurezza(Utente utente,Object port) {
        Client client = ClientProxy.getClient(port);
        org.apache.cxf.endpoint.Endpoint cxfEndpoint = client.getEndpoint();
        Map outProps = new HashMap();
        outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
        outProps.put(WSHandlerConstants.USER, utente.getNomeUtente());
        outProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_DIGEST);
        outProps.put(WSHandlerConstants.PW_CALLBACK_REF, new ClientPasswordCallback(utente.getPassword()));
        WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps);
        cxfEndpoint.getOutInterceptors().add(wssOut);
        cxfEndpoint.getOutInterceptors().add(new SAAJOutInterceptor());
    }
    
}
