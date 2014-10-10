package it.unibas.freesbeesla.ws.web;

import it.unibas.freesbeesla.ws.web.stub.ServerPasswordCallback;
import it.unibas.freesbeesla.ws.web.persistenza.torque.DAOUtenteTorque;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.binding.soap.saaj.SAAJInInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.handler.WSHandlerConstants;

public class WSSecurityInterceptor extends AbstractPhaseInterceptor {

    private Log logger = LogFactory.getLog(this.getClass());
    private DAOUtenteTorque daoUtente;

    public WSSecurityInterceptor() {
        super(Phase.PRE_PROTOCOL);
        this.daoUtente = new DAOUtenteTorque();
    }

    public WSSecurityInterceptor(String s) {
        super(Phase.PRE_PROTOCOL);
        this.daoUtente = new DAOUtenteTorque();
    }

    @SuppressWarnings("unchecked")
    public void handleMessage(Message message) throws Fault {
        try {
            Map inProps = new HashMap();
            inProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
            inProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_DIGEST);
            inProps.put(WSHandlerConstants.PW_CALLBACK_REF, new ServerPasswordCallback(daoUtente));
            WSS4JInInterceptor wss4jInHandler = new WSS4JInInterceptor(inProps);
            message.getInterceptorChain().add(wss4jInHandler);
            message.getInterceptorChain().add(new SAAJInInterceptor());
        } catch (Exception e) {
            logger.error("Si e' verificato un errore di sicurezza nel sistema " + e.getMessage());
        }
    }

    public DAOUtenteTorque getDaoUtente() {
        return daoUtente;
    }

    public void setDaoUtente(DAOUtenteTorque daoUtente) {
        this.daoUtente = daoUtente;
    }
}
