package com.myapp.struts.controllo;

import com.myapp.struts.form.FormCerca;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import com.ws.aci.AciWS;
import com.ws.aci.Immatricolazione;
import it.unibas.icar.freesbeesp.exception.FreesbeeSPException;
import it.unibas.icar.freesbeesp.exception.XmlException;
import java.util.GregorianCalendar;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.BindingProvider;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.*;
import it.unibas.icar.freesbeesp.modello.Utente;
import it.unibas.icar.freesbeesp.sso.SSOClient;
import it.unibas.icar.freesbeesp.sso.SSOSessionResponse;
import it.unibas.icar.freesbeesp.sso.driver.ISSODriver;
import it.unibas.icar.freesbeesp.sso.driver.SSODriverFactory;
import it.unibas.icar.freesbeesp.util.FileUtil;
import it.unibas.icar.freesbeesp.util.HttpUtil;
import it.unibas.icar.freesbeesp.util.XmlJDomUtil;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.jdom.Document;
import org.jdom.Element;
import utility.SSLUtilities;

public class AzioneCerca extends Action {

    private Log logger = LogFactory.getLog(this.getClass());
//    private String risorsa = "https://sp.comune.pz/PD_CERCA_IMMATRICOLAZIONE";
    private String risorsa = "";
    private String risorsaUrlLoginIntegrato = "";
    private String risorsaUrlLoginFreesbeeSP = "";
    private boolean errorePermessi = false;

    public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionErrors errors = new ActionErrors();
        String tipo = request.getParameter("tipo");
        Utente utente = null;
        String targa = null;
        HttpSession session = request.getSession();
        InputStream inStream = AzioneCerca.class.getResourceAsStream("/config.properties");
        Properties properties = new Properties();
        properties.load(inStream);
        risorsa = properties.getProperty("url.ws");
        risorsaUrlLoginIntegrato = properties.getProperty("url.login.integrato");
        risorsaUrlLoginFreesbeeSP = properties.getProperty("url.login.freesbeesp");
        if (tipo != null) {
            String autenticazione = request.getParameter("autenticazione");
            String nomeUtente = request.getParameter("username");
            String passw = request.getParameter("password");
            targa = request.getParameter("targa");
            if (autenticazione.equalsIgnoreCase("integrata")) {
                logger.info("Eseguo l'autenticazione in modalità 'INTEGRATA'");
                if (tipo.equalsIgnoreCase("agente")) {
                    try {
                        ISSODriver ssoDriver = new SSODriverFactory().getIstance();
                        SSOSessionResponse shibSession = ssoDriver.createSession(nomeUtente, passw, this.risorsaUrlLoginIntegrato, null);
                        if (shibSession != null) {
                            utente = new Utente();
                            utente.setNomeUtente(nomeUtente);
                            utente.setPortafoglioAsserzioni(shibSession.getElementAssertion());
                            utente.setSilSessionId(session.getId());
                            utente.setSsoSession(shibSession.getSession());
                        }
                    } catch (Exception exception) {
                        logger.error(exception);
                    }
                }
            } else if (autenticazione.equalsIgnoreCase("freesbeesp")) {
                logger.info("Eseguo l'autenticazione in modalità 'FREESBEESP'");
                String urlInvio = this.risorsaUrlLoginFreesbeeSP + "&username=" + nomeUtente + "&password=" + passw;
                String messaggioInviare = "<?xml version='1.0' encoding='UTF-8'?><S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\"><S:Body><ns2:cercaImmatricolazione xmlns:ns2=\"http://aci.ws.com/\"><targa>" + targa + "</targa></ns2:cercaImmatricolazione></S:Body></S:Envelope>";

                HttpClient httpClient = new HttpClient();
                PostMethod postMethod = new PostMethod(urlInvio);

                RequestEntity entity = new ByteArrayRequestEntity(messaggioInviare.getBytes());
                postMethod.setRequestEntity(entity);
                logger.info("Inoltro la richiesta all'indirizzo " + urlInvio);

                httpClient.executeMethod(postMethod);
                if (logger.isDebugEnabled()) {
                    logger.debug(HttpUtil.stampaMethod(postMethod));
                }

                InputStream responseStream = postMethod.getResponseBodyAsStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                FileUtil.copyStream(responseStream, baos);
                String stringaRisposta = baos.toString();
                logger.info("Risposta: " + stringaRisposta);

                Immatricolazione immatricolazione = parserizzaRisposta(stringaRisposta);

                GregorianCalendar dataNascita = immatricolazione.getDataNascita().toGregorianCalendar();
                GregorianCalendar dataImmatricolazione = immatricolazione.getDataImmatricolazione().toGregorianCalendar();
                session.setAttribute("immatricolazione", immatricolazione);
                session.setAttribute("dataNascita", dataNascita);
                session.setAttribute("dataImmatricolazione", dataImmatricolazione);

                return mapping.findForward("successo");
            } else if (autenticazione.equalsIgnoreCase("none")) {
                String urlInvio = risorsa;
                String messaggioInviare = "<?xml version='1.0' encoding='UTF-8'?><S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\"><S:Body><ns2:cercaImmatricolazione xmlns:ns2=\"http://aci.ws.com/\"><targa>" + targa + "</targa></ns2:cercaImmatricolazione></S:Body></S:Envelope>";

                HttpClient httpClient = new HttpClient();
                PostMethod postMethod = new PostMethod(urlInvio);

                RequestEntity entity = new ByteArrayRequestEntity(messaggioInviare.getBytes());
                postMethod.setRequestEntity(entity);
                logger.info("Inoltro la richiesta all'indirizzo " + urlInvio);

                httpClient.executeMethod(postMethod);
                if (logger.isDebugEnabled()) {
                    logger.debug(HttpUtil.stampaMethod(postMethod));
                }

                InputStream responseStream = postMethod.getResponseBodyAsStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                FileUtil.copyStream(responseStream, baos);
                String stringaRisposta = baos.toString();
                logger.info("Risposta: " + stringaRisposta);

                Immatricolazione immatricolazione = parserizzaRisposta(stringaRisposta);

                GregorianCalendar dataNascita = immatricolazione.getDataNascita().toGregorianCalendar();
                GregorianCalendar dataImmatricolazione = immatricolazione.getDataImmatricolazione().toGregorianCalendar();
                session.setAttribute("immatricolazione", immatricolazione);
                session.setAttribute("dataNascita", dataNascita);
                session.setAttribute("dataImmatricolazione", dataImmatricolazione);

                return mapping.findForward("successo");
            } else {
                throw new FreesbeeSPException("Non è stato specificato il tipo di autenticazione (integrata|freesbeesp).");
            }
        } else {
            utente = (Utente) session.getAttribute("utente");
            FormCerca formCerca = (FormCerca) form;
            targa = formCerca.getTarga();
        }
//        String freesbeespSession = formCerca.getFreesbeeSPSessionId();
//        String freesbeespSession = utente.getSsoSession();
//        session.setAttribute("freesbeeSP", freesbeespSession);
//        logger.error("FREESBEE_SESSION_ID" + freesbeespSession);
//        Cittadino cittadino = cercaCittadino(codiceFiscale, freesbeespSession);
        session.setAttribute("risorsa", this.risorsa);
        session.setAttribute("utente", utente);
        if (utente != null)
            session.setAttribute("freesbeeSP", utente.getSsoSession());
        String skipService = request.getParameter("skip");
        if (skipService != null && skipService.equalsIgnoreCase("true")) {
            System.out.println("SKIP CERCA IMMATRICOLAZIONE");
            return mapping.findForward("successo");
        }
        Immatricolazione immatricolazione = cercaImmatricolazione(targa, utente);
        if (errorePermessi) {
            errorePermessi = false;
            return mapping.findForward("accessoNegato");

        }
        if (immatricolazione == null) {
            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("Targa  inserita inesistente", false));
            saveErrors(request, errors);
            return mapping.findForward("ricercaFallita");
        } else {

            GregorianCalendar dataNascita = immatricolazione.getDataNascita().toGregorianCalendar();
            GregorianCalendar dataImmatricolazione = immatricolazione.getDataImmatricolazione().toGregorianCalendar();
            session.setAttribute("immatricolazione", immatricolazione);
            session.setAttribute("dataNascita", dataNascita);
            session.setAttribute("dataImmatricolazione", dataImmatricolazione);
            return mapping.findForward("successo");

        }

    }

//    private Immatricolazione cercaImmatricolazione(String targa, Utente utente) {
//        com.ws.aci.AciWS_Service service = null;
//        try {
//            service = new AciWS_Service();
//        } catch (MalformedURLException ex) {
//            logger.error(ex);
//        }
//        AciWS port = service.getAciWSPort();
//        String utentePortafoglio = new XMLOutputter().outputString(utente.getPortafoglioAsserzioni());
//        String utenteSSoSession = utente.getSsoSession();
//        try {
//            utentePortafoglio = URLEncoder.encode(utentePortafoglio, "UTF-8");
//            utenteSSoSession = URLEncoder.encode(utenteSSoSession, "UTF-8");
//        } catch (Exception e) {}
////        setEndpointAddress(port, "https://sp.comune.pz:8443/freesbeesp/forwardToPD.post;jsessionid=" + spSession);
//        setEndpointAddress(port, "http://localhost:8080/clientWSComune2/forwardToPD?risorsa=" + risorsa + "&portafoglio=" + utentePortafoglio+"&ssoSession="+utenteSSoSession);
//        return port.cercaImmatricolazione(targa);
//    }
    private Immatricolazione cercaImmatricolazione(String targa, Utente utente) {
        String messaggioInviare = "<?xml version='1.0' encoding='UTF-8'?><S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\"><S:Body><ns2:cercaImmatricolazione xmlns:ns2=\"http://aci.ws.com/\"><targa>" + targa + "</targa></ns2:cercaImmatricolazione></S:Body></S:Envelope>";
        SSLUtilities.trustAllHostnames();
        SSLUtilities.trustAllHttpsCertificates();
        if (utente != null && utente.getPortafoglioAsserzioni() != null && utente.getSsoSession() != null) {
            String risposta = new SSOClient().sendSoapSamlRequest(risorsa, utente.getPortafoglioAsserzioni(), utente.getSsoSession(), messaggioInviare);
            logger.info(risposta);
            if (!risposta.contains("<title>403 Forbidden</title>")) {
                Immatricolazione immatricolazione = parserizzaRisposta(risposta);
                return immatricolazione;
            } else {
                this.errorePermessi = true;
            }
        }
        return null;
    }

    private XMLGregorianCalendar getDate(String stringDate) {
        Calendar cal = Calendar.getInstance();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        try {
            cal.setTime(sdf.parse(stringDate));
        } catch (ParseException parseException) {
        }
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(cal.getTime());;
        XMLGregorianCalendarImpl calendar = new XMLGregorianCalendarImpl(gc);
        return calendar;

    }

    private Immatricolazione parserizzaRisposta(String risposta) {
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
            Element cercaImmatricolazione = (Element) body.getChildren().get(0);
            if (!cercaImmatricolazione.getChildren().isEmpty()) {
                Element response = (Element) cercaImmatricolazione.getChildren().get(0);
                String alimentazione = response.getChildText("alimentazione");
                String classe = response.getChildText("classe");
                String cavalliString = response.getChildText("cavalli");
                String cf = response.getChildText("codiceFiscale");
                String cognome = response.getChildText("cognome");
                String comuneNascita = response.getChildText("comuneNascita");
                String comuneResidenza = response.getChildText("comuneResidenza");
                String dataImmatricolazioneString = response.getChildText("dataImmatricolazione");
                String dataNascitaString = response.getChildText("dataNascita");
                String idString = response.getChildText("id");
                String kwString = response.getChildText("kw");
                String modello = response.getChildText("modello");
                String nome = response.getChildText("nome");
                String postiString = response.getChildText("posti");
                String targaRicevuta = response.getChildText("targa");
                String telaio = response.getChildText("telaio");
                String uso = response.getChildText("uso");
                String via = response.getChildText("via");

                Immatricolazione immatricolazione = new Immatricolazione();
                immatricolazione.setAlimentazione(alimentazione);
                immatricolazione.setCavalli(Integer.parseInt(cavalliString));
                immatricolazione.setClasse(classe);
                immatricolazione.setCodiceFiscale(cf);
                immatricolazione.setCognome(cognome);
                immatricolazione.setComuneNascita(comuneNascita);
                immatricolazione.setComuneResidenza(comuneResidenza);
                immatricolazione.setDataImmatricolazione(getDate(dataImmatricolazioneString));
                immatricolazione.setDataNascita(getDate(dataNascitaString));
                immatricolazione.setId(Long.parseLong(idString));
                immatricolazione.setKw(Double.parseDouble(kwString));
                immatricolazione.setModello(modello);
                immatricolazione.setNome(nome);
                immatricolazione.setPosti(Integer.parseInt(postiString));
                immatricolazione.setTarga(targaRicevuta);
                immatricolazione.setTelaio(telaio);
                immatricolazione.setUso(uso);
                immatricolazione.setVia(via);

                return immatricolazione;
            }

        }
        return null;
    }
}
