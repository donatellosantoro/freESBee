package com.myapp.struts.controllo;

import com.myapp.struts.form.FormImmatricolazione;
import com.myapp.struts.modello.Immatricolazione;
import com.myapp.struts.persistenza.DAOException;
import com.myapp.struts.persistenza.DAOImmatricolazione;
import com.myapp.struts.persistenza.DAOUtilHibernate;
import com.myapp.struts.persistenza.IDAOImmatricolazione;
import com.ws.comune.Cittadino;
import com.ws.comune.ComuneWS;
import java.util.GregorianCalendar;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.BindingProvider;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.*;
import com.ws.comune.*;
import java.util.List;
import utility.SSLUtilities;

public class AzioneNuovaImmatricolazione extends Action {

    private Log logger = LogFactory.getLog(this.getClass());
    private IDAOImmatricolazione daoImmatricolazione = new DAOImmatricolazione();

    public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        FormImmatricolazione formImmatricolazione = (FormImmatricolazione) form;
        String freesbeespSession = formImmatricolazione.getFreesbeeSPSessionId();
        String codiceFiscale = formImmatricolazione.getCodiceFiscale();
        String targa = formImmatricolazione.getTarga();
        String telaio = formImmatricolazione.getTelaio();
        try {
         Cittadino cittadino = cercaCittadino(codiceFiscale, freesbeespSession);
        if (cittadino == null) {
            errors.add(errors.GLOBAL_MESSAGE, new ActionMessage("Il cittadino non risulta registrato", false));
            saveErrors(request, errors);
            return mapping.findForward("errore");
        }
        try {
            DAOUtilHibernate.beginTransaction();
            List<Immatricolazione> lista = daoImmatricolazione.findAll();
            DAOUtilHibernate.commit();
            for (Immatricolazione immatricolazione : lista) {
                if (immatricolazione.getTarga().equals(targa)) {
                    errors.add(errors.GLOBAL_MESSAGE, new ActionMessage("Targa gia presente in archivio", false));
                }
                if (immatricolazione.getTelaio().equals(telaio)) {
                    errors.add(errors.GLOBAL_MESSAGE, new ActionMessage("Telaio gia presente in archivio", false));
                }
            }

        } catch (DAOException dao) {
            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("Errore accesso Dbms:" + dao, false));
            saveErrors(request, errors);
            mapping.findForward("errore");
        }

        if (errors.size() != 0) {
            saveErrors(request, errors);
            return mapping.findForward("errore");
        }
        Immatricolazione nuovaImmatricolazione = new Immatricolazione();
        nuovaImmatricolazione.setCodiceFiscale(cittadino.getCodiceFiscale());
        nuovaImmatricolazione.setTarga(formImmatricolazione.getTarga());
        nuovaImmatricolazione.setTelaio(formImmatricolazione.getTelaio());
        nuovaImmatricolazione.setDataImmatricolazione(new GregorianCalendar());
        nuovaImmatricolazione.setModello(formImmatricolazione.getModello());
        nuovaImmatricolazione.setClasse(formImmatricolazione.getClasse());
        nuovaImmatricolazione.setCavalli(Integer.parseInt(formImmatricolazione.getCavalli()));
        nuovaImmatricolazione.setKw(Double.parseDouble(formImmatricolazione.getKw()));
        nuovaImmatricolazione.setUso(formImmatricolazione.getUso());
        nuovaImmatricolazione.setPosti(Integer.parseInt(formImmatricolazione.getPosti()));
        nuovaImmatricolazione.setAlimentazione(formImmatricolazione.getAlimentazione());
        nuovaImmatricolazione.setNome(cittadino.getNome());
        nuovaImmatricolazione.setCognome(cittadino.getCognome());
        nuovaImmatricolazione.setDataNascita(cittadino.getDataNascita().toGregorianCalendar());
        nuovaImmatricolazione.setComuneNascita(cittadino.getComuneNascita());
        nuovaImmatricolazione.setComuneResidenza(cittadino.getComuneResidenza());
        nuovaImmatricolazione.setVia(cittadino.getVia());
        try {
            DAOUtilHibernate.beginTransaction();
            daoImmatricolazione.makePersistent(nuovaImmatricolazione);
            DAOUtilHibernate.commit();
        } catch (DAOException dao) {
            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("Errore accesso Dbms:" + dao, false));
            saveErrors(request, errors);
            mapping.findForward("errore");
        }
        }
        
        catch (Exception mcd) {
            return mapping.findForward("accessoNegato");
        }

//        String freesbeespSession = formCerca.getFreesbeeSPSessionId();
//        session.setAttribute("freesbeeSP", freesbeespSession);
        return mapping.findForward("successo");

    }

    private Cittadino cercaCittadino(String codiceFiscale, String spSession)throws Exception
   {
        ComuneWS_Service service = new ComuneWS_Service();
        ComuneWS port = service.getComuneWSPort();
        setEndpointAddress(port, "https://sp.comune.pz:8443/freesbeesp/forwardToPD.post;jsessionid=" + spSession);
        return port.cercaCittadino(codiceFiscale);
    
    }

    private void setEndpointAddress(ComuneWS port, String nuovo_indirizzo) {
        assert port instanceof BindingProvider;
        BindingProvider bp = (BindingProvider) port;
        Map<String, Object> context = bp.getRequestContext();
        Object oldAddress = context.get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY);
        context.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, nuovo_indirizzo);
        SSLUtilities.trustAllHostnames();
        SSLUtilities.trustAllHttpsCertificates();


    }
}
