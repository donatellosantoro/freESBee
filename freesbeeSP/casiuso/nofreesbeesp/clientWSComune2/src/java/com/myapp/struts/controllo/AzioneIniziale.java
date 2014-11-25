package com.myapp.struts.controllo;


import com.myapp.struts.form.FormLogin;
import it.unibas.icar.freesbeesp.modello.Utente;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.*;

public class AzioneIniziale extends Action {

    private Log logger = LogFactory.getLog(this.getClass());
   

    public ActionForward execute(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
      HttpSession session =request.getSession();
      String sessionString=session.getId();
      session.setAttribute("sessione",sessionString);
      return  mapping.findForward("successo");    
  
    }


}

