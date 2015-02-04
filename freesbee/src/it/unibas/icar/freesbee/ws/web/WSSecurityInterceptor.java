package it.unibas.icar.freesbee.ws.web;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import it.unibas.icar.freesbee.persistenza.IDAOUtente;
import it.unibas.icar.freesbee.persistenza.SOAPFault;
import it.unibas.icar.freesbee.persistenza.hibernate.DAOUtenteHibernate;
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

@Singleton
public class WSSecurityInterceptor extends AbstractPhaseInterceptor {

    private static Log logger = LogFactory.getLog(WSSecurityInterceptor.class);
    @Inject
    private IDAOUtente daoUtente;

    public WSSecurityInterceptor() {
        super(Phase.PRE_PROTOCOL);
        this.daoUtente = new DAOUtenteHibernate();
    }

    public WSSecurityInterceptor(String s) {
        super(Phase.PRE_PROTOCOL);
        this.daoUtente = new DAOUtenteHibernate();
    }

    @SuppressWarnings("unchecked")
    public void handleMessage(Message message) throws Fault {
        try {
            Map inProps = new HashMap();
            inProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
            inProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_DIGEST);
            inProps.put(WSHandlerConstants.PW_CALLBACK_REF, new ServerPasswordCallback(daoUtente));
            WSS4JInInterceptor wss4jInHandler = new WSS4JInInterceptor(inProps);
//            ValidateUserTokenInterceptor userTokenInterceptor = new ValidateUserTokenInterceptor(Phase.POST_PROTOCOL);
            message.getInterceptorChain().add(wss4jInHandler);
            message.getInterceptorChain().add(new SAAJInInterceptor());
//            message.getInterceptorChain().add(userTokenInterceptor);
        } catch (Exception e) {
            logger.error("Si e' verificato un errore di sicurezza nel sistema.");
            if (logger.isDebugEnabled()) e.printStackTrace();
        }
    }

    public IDAOUtente getDaoUtente() {
        return daoUtente;
    }

    public void setDaoUtente(IDAOUtente daoUtente) {
        this.daoUtente = daoUtente;
    }
}