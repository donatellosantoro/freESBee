package com.myapp.struts.controllo;

import com.myapp.struts.form.FormCerca;
import com.ws.aci.AciWS;
import com.ws.aci.AciWS_Service;
import com.ws.aci.Immatricolazione;
import com.ws.comune.Cittadino;
import com.ws.comune.ComuneWS;
import java.util.GregorianCalendar;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.*;
import com.ws.comune.*;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utility.SSLUtilities;

public class AzioneCerca extends Action {

    private Log logger = LogFactory.getLog(this.getClass());

    public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        FormCerca formCerca = (FormCerca) form;
        String targa = formCerca.getTarga();
        String freesbeespSession = formCerca.getFreesbeeSPSessionId();
        session.setAttribute("freesbeeSP", freesbeespSession);
        logger.error("FREESBEE_SESSION_ID" + freesbeespSession);
//        Cittadino cittadino = cercaCittadino(codiceFiscale, freesbeespSession);
         Immatricolazione immatricolazione=cercaImmatricolazione(targa,freesbeespSession);

        if (immatricolazione == null) {
            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("Targa  inserita inesistente", false));
            saveErrors(request, errors);
            return mapping.findForward("ricercaFallita");
        } else {

      
            GregorianCalendar dataNascita = immatricolazione.getDataNascita().toGregorianCalendar();
            GregorianCalendar dataImmatricolazione=immatricolazione.getDataImmatricolazione().toGregorianCalendar();
            session.setAttribute("immatricolazione",immatricolazione);
            session.setAttribute("dataNascita", dataNascita);
            session.setAttribute("dataImmatricolazione",dataImmatricolazione);
            return mapping.findForward("successo");

        }



    }

   
    
      private Immatricolazione cercaImmatricolazione(String targa,String spSession){
          com.ws.aci.AciWS_Service service = null;
        try {
            service= new AciWS_Service();
        } catch (MalformedURLException ex) {
            logger.error(ex);
        }
          AciWS port=service.getAciWSPort();
          setEndpointAddress(port, "https://sp.comune.pz:8443/freesbeesp/forwardToPD.post;jsessionid=" + spSession);
          return port.cercaImmatricolazione(targa);
      }
    
    
        private void setEndpointAddress(AciWS port, String nuovo_indirizzo) {
        assert port instanceof BindingProvider;
        BindingProvider bp = (BindingProvider) port;
        Map<String, Object> context = bp.getRequestContext();
        Object oldAddress = context.get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY);
        context.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, nuovo_indirizzo);
        SSLUtilities.trustAllHostnames();
        SSLUtilities.trustAllHttpsCertificates();


    }
}
