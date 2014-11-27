package com.myapp.struts.controllo;

import com.myapp.struts.form.FormImmatricolazione;
import com.myapp.struts.modello.Immatricolazione;
import com.myapp.struts.persistenza.DAOException;
import com.myapp.struts.persistenza.DAOImmatricolazione;
import com.myapp.struts.persistenza.DAOUtilHibernate;
import com.myapp.struts.persistenza.IDAOImmatricolazione;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import com.ws.comune.Cittadino;
import java.util.GregorianCalendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.*;
import it.unibas.icar.freesbeesp.exception.XmlException;
import it.unibas.icar.freesbeesp.modello.Utente;
import it.unibas.icar.freesbeesp.sso.SSOClient;
import it.unibas.icar.freesbeesp.util.XmlJDomUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;
import org.jdom.Document;
import org.jdom.Element;
import utility.SSLUtilities;

public class AzioneNuovaImmatricolazione extends Action {

    private Log logger = LogFactory.getLog(this.getClass());
    private String risorsa = "https://sp.comune.pz/PD_CERCA_CITTADINO";
    private IDAOImmatricolazione daoImmatricolazione = new DAOImmatricolazione();
    private boolean errorePermessi = false;

    public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        FormImmatricolazione formImmatricolazione = (FormImmatricolazione) form;
//        String freesbeespSession = formImmatricolazione.getFreesbeeSPSessionId();
        String codiceFiscale = formImmatricolazione.getCodiceFiscale();
        String targa = formImmatricolazione.getTarga();
        String telaio = formImmatricolazione.getTelaio();
        try {
            Cittadino cittadino = cercaCittadino(codiceFiscale, utente);
            if (errorePermessi) {
                errorePermessi = false;
                return mapping.findForward("accessoNegato");

            }
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
        } catch (Exception mcd) {
            return mapping.findForward("accessoNegato");
        }

//        String freesbeespSession = formCerca.getFreesbeeSPSessionId();
//        session.setAttribute("freesbeeSP", freesbeespSession);
        return mapping.findForward("successo");

    }

    private Cittadino cercaCittadino(String codiceFiscale, Utente utente) throws Exception {
        String messaggioInviare = "<?xml version='1.0' encoding='UTF-8'?><S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\"><S:Body><ns2:cercaCittadino xmlns:ns2=\"http://comune.ws.com/\"><codiceFiscale>" + codiceFiscale + "</codiceFiscale></ns2:cercaCittadino></S:Body></S:Envelope>";
        SSLUtilities.trustAllHostnames();
        SSLUtilities.trustAllHttpsCertificates();
        String risposta = new SSOClient().sendSoapSamlRequest(risorsa, utente.getPortafoglioAsserzioni(), utente.getSsoSession(), messaggioInviare);
        logger.info(risposta);
        if (!risposta.contains("<title>403 Forbidden</title>")) {
            Document xmlRisposta = null;
            try {
                xmlRisposta = XmlJDomUtil.caricaXML(risposta);
            } catch (XmlException xmlException) {
                logger.error("Eccezione nella trasformazione del messaggio");
            }
            if (xmlRisposta != null) {
                Element rootElement = xmlRisposta.getRootElement();
                List<Element> children = (List<Element>) rootElement.getChildren();
                Element body = children.get(0);
                Element cercaCittadino = (Element) body.getChildren().get(0);
                if (!cercaCittadino.getChildren().isEmpty()) {
                    Element response = (Element) cercaCittadino.getChildren().get(0);
                    String cf = response.getChildText("codiceFiscale");
                    String cognome = response.getChildText("cognome");
                    String comuneNascita = response.getChildText("comuneNascita");
                    String comuneResidenza = response.getChildText("comuneResidenza");
                    String dataNascitaString = response.getChildText("dataNascita");
                    String idString = response.getChildText("id");
                    String nome = response.getChildText("nome");
                    String via = response.getChildText("via");

                    Cittadino cittadino = new Cittadino();
                    cittadino.setCodiceFiscale(cf);
                    cittadino.setCognome(cognome);
                    cittadino.setComuneNascita(comuneNascita);
                    cittadino.setComuneResidenza(comuneResidenza);
                    cittadino.setDataNascita(getDate(dataNascitaString));
                    cittadino.setId(Long.parseLong(idString));
                    cittadino.setNome(nome);
                    cittadino.setVia(via);
                    return cittadino;
                }
            }
        } else {
            errorePermessi = true;
        }

        return null;

    }

    private XMLGregorianCalendar getDate(String stringDate) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        try {
            cal.setTime(sdf.parse(stringDate));
        } catch (ParseException parseException) {
        }
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(cal.getTime());;
        XMLGregorianCalendarImpl calendar = new XMLGregorianCalendarImpl(gc);
        return calendar;

    }

}
