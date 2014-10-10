package it.unibas.freesbee.websla.controllo.filtri;

import it.unibas.freesbee.websla.controllo.ControlloMonitoraggioSLAServiziErogati;
import it.unibas.freesbee.websla.controllo.ControlloMonitoraggioSLAServiziFruiti;
import it.unibas.freesbee.websla.controllo.ControlloMonitoraggioStatoServiziErogati;
import it.unibas.freesbee.websla.controllo.ControlloMonitoraggioStatoServiziFruiti;
import it.unibas.freesbee.websla.vista.VistaMonitoraggioSLAServiziErogati;
import it.unibas.freesbee.websla.vista.VistaMonitoraggioSLAServiziFruiti;
import it.unibas.freesbee.websla.vista.VistaMonitoraggioStatoServiziErogati;
import it.unibas.freesbee.websla.vista.VistaMonitoraggioStatoServiziFruiti;
import it.unibas.freesbee.websla.ws.web.stub.DatiConfigurazione;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FiltroSessione implements Filter {

    private Log logger = LogFactory.getLog(this.getClass());
    private FilterConfig filterConfig = null;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterCahin) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
//        logger.info(httpRequest.getServletPath());

        boolean protezioneSP = false;

        //Se si usa la protezione SP
        if (session != null) {

            DatiConfigurazione dati = (DatiConfigurazione) session.getAttribute("datiConfigurazione");
            if (dati != null) {
                protezioneSP = dati.isProtezioneSP();
            } else {
                protezioneSP = false;
            }
            if (protezioneSP) {

                String urlFreesbeeSP = dati.getUrlFreesbeeSP();
                //"http://192.168.7.6:8082/freesbeesp/";
                String risorsa = dati.getRisorsa();
                //"https://sp.example.unibas.org/";
                String risorsaMonitoraggioStato = dati.getRisorsaPdMonitoraggioStato();
                //"PD_MONITORAGGIO_STATO/";
                String risorsaMonitoraggioSla = dati.getRisorsaPdMonitoraggioSLA();
                //"PD_MONITORAGGIO_SLA/";
                String urlInvio = urlFreesbeeSP + "/forwardToPD.post";
                String tipoProtocollo = "";
                if(request.isSecure()){
                tipoProtocollo = "https://";
                }else{
                tipoProtocollo = "http://";
                }

                ControlloMonitoraggioSLAServiziErogati controlloSLAErogati = (ControlloMonitoraggioSLAServiziErogati) session.getAttribute("controlloMonitoraggioSLAServiziErogati");
                VistaMonitoraggioSLAServiziErogati vistaSLAErogati = (VistaMonitoraggioSLAServiziErogati) session.getAttribute("vistaMonitoraggioSLAServiziErogati");

                VistaMonitoraggioStatoServiziErogati vistaSTATOErogati = (VistaMonitoraggioStatoServiziErogati) session.getAttribute("vistaMonitoraggioStatoServiziErogati");
                ControlloMonitoraggioStatoServiziErogati controlloSTATOErogati = (ControlloMonitoraggioStatoServiziErogati) session.getAttribute("controlloMonitoraggioStatoServiziErogati");

                ControlloMonitoraggioSLAServiziFruiti controlloSLAFruiti = (ControlloMonitoraggioSLAServiziFruiti) session.getAttribute("controlloMonitoraggioSLAServiziFruiti");
                VistaMonitoraggioSLAServiziFruiti vistaSLAFruiti = (VistaMonitoraggioSLAServiziFruiti) session.getAttribute("vistaMonitoraggioSLAServiziFruiti");

                VistaMonitoraggioStatoServiziFruiti vistaSTATOFruiti = (VistaMonitoraggioStatoServiziFruiti) session.getAttribute("vistaMonitoraggioStatoServiziFruiti");
                ControlloMonitoraggioStatoServiziFruiti controlloSTATOFruiti = (ControlloMonitoraggioStatoServiziFruiti) session.getAttribute("controlloMonitoraggioStatoServiziFruiti");


                String freesbeeSPSessionId = httpRequest.getParameter("freesbeeSPSessionId");
                String indirizzoRedirect = "";
                String paginaRitorno = (String) session.getAttribute("schermoRitorno");

                if (freesbeeSPSessionId != null) {
                    urlInvio += ";jsessionid=" + freesbeeSPSessionId;
                    if (vistaSLAErogati != null) {
                        vistaSLAErogati.setUrlInvio(urlInvio);
                    }
                    if (vistaSTATOErogati != null) {
                        vistaSTATOErogati.setUrlInvio(urlInvio);
                    }
                    if (vistaSLAFruiti != null) {
                        vistaSLAFruiti.setUrlInvio(urlInvio);
                    }
                    if (vistaSTATOFruiti != null) {
                        vistaSTATOFruiti.setUrlInvio(urlInvio);
                    }
                }

                //schermoAcquisizionePortafoglio
                if ((httpRequest.getServletPath().equals("/schermoAcquisizionePortafoglio.faces"))) {

                    //schermoMonitoraggioSLAServiziErogati
                    if (paginaRitorno.equals("schermoMonitoraggioSLAServiziErogati")) {
                        controlloSLAErogati.caricaGestioneServizi();
                        if (freesbeeSPSessionId != null) {
                            indirizzoRedirect = urlFreesbeeSP + "/schermoLogin.faces;jsessionid=" + freesbeeSPSessionId + "?autenticazione=UTENTE&uriDiDestinazione=" + tipoProtocollo + request.getServerName() + ":" + request.getServerPort() + "/" + httpRequest.getContextPath() + "/" + paginaRitorno + ".faces&silSessionId=" + session.getId() + "&risorsa=" + risorsa + risorsaMonitoraggioSla;
                        } else {
                            indirizzoRedirect = urlFreesbeeSP + "/schermoLogin.faces?autenticazione=UTENTE&uriDiDestinazione=" + tipoProtocollo + request.getServerName() + ":" + request.getServerPort() + "/" + httpRequest.getContextPath() + "/" + paginaRitorno + ".faces&silSessionId=" + session.getId() + "&risorsa=" + risorsa + risorsaMonitoraggioSla;
                        }
                    }

                    //schermoMonitoraggioSLAServiziFruiti
                    if (paginaRitorno.equals("schermoMonitoraggioSLAServiziFruiti")) {
                        controlloSLAFruiti.caricaGestioneServizi();
                        controlloSLAFruiti.caricaLsitaModuliSLA(dati.getAccordoServizioSLA(), dati.getNomeServizioMonitoraggioSLA(), dati.getTipoServizioMonitoraggioSLA());
                        if (freesbeeSPSessionId != null) {
                            indirizzoRedirect = urlFreesbeeSP + "/schermoLogin.faces;jsessionid=" + freesbeeSPSessionId + "?autenticazione=UTENTE&uriDiDestinazione=" + tipoProtocollo + request.getServerName() + ":" + request.getServerPort() + "/" + httpRequest.getContextPath() + "/" + paginaRitorno + ".faces&silSessionId=" + session.getId() + "&risorsa=" + risorsa + risorsaMonitoraggioSla;
                        } else {
                            indirizzoRedirect = urlFreesbeeSP + "/schermoLogin.faces?autenticazione=UTENTE&uriDiDestinazione=" + tipoProtocollo + request.getServerName() + ":" + request.getServerPort() + "/" + httpRequest.getContextPath() + "/" + paginaRitorno + ".faces&silSessionId=" + session.getId() + "&risorsa=" + risorsa + risorsaMonitoraggioSla;
                        }
                    }


                    //schermoMonitoraggioStatoServiziErogati
                    if (paginaRitorno.equals("schermoMonitoraggioStatoServiziErogati")) {
                        controlloSTATOErogati.caricaGestioneServizi();
                        if (freesbeeSPSessionId != null) {
                            indirizzoRedirect = urlFreesbeeSP + "/schermoLogin.faces;jsessionid=" + freesbeeSPSessionId + "?autenticazione=UTENTE&uriDiDestinazione=" + tipoProtocollo + request.getServerName() + ":" + request.getServerPort() + "/" + httpRequest.getContextPath() + "/" + paginaRitorno + ".faces&silSessionId=" + session.getId() + "&risorsa=" + risorsa + risorsaMonitoraggioStato;
                        } else {
                            indirizzoRedirect = urlFreesbeeSP + "/schermoLogin.faces?autenticazione=UTENTE&uriDiDestinazione=" + tipoProtocollo + request.getServerName() + ":" + request.getServerPort() + "/" + httpRequest.getContextPath() + "/" + paginaRitorno + ".faces&silSessionId=" + session.getId() + "&risorsa=" + risorsa + risorsaMonitoraggioStato;
                        }
                    }

                    //schermoMonitoraggioStatoServiziFruiti
                    if (paginaRitorno.equals("schermoMonitoraggioStatoServiziFruiti")) {
                        controlloSTATOFruiti.caricaGestioneServizi();
                        controlloSTATOFruiti.caricaLsitaModuliSLA(dati.getAccordoServizioStato(), dati.getNomeServizioMonitoraggioStato(), dati.getTipoServizioMonitoraggioStato());
                        if (freesbeeSPSessionId != null) {
                            indirizzoRedirect = urlFreesbeeSP + "/schermoLogin.faces;jsessionid=" + freesbeeSPSessionId + "?autenticazione=UTENTE&uriDiDestinazione=" + tipoProtocollo + request.getServerName() + ":" + request.getServerPort() + "/" + httpRequest.getContextPath() + "/" + paginaRitorno + ".faces&silSessionId=" + session.getId() + "&risorsa=" + risorsa + risorsaMonitoraggioStato;
                        } else {
                            indirizzoRedirect = urlFreesbeeSP + "/schermoLogin.faces?autenticazione=UTENTE&uriDiDestinazione=" + tipoProtocollo + request.getServerName() + ":" + request.getServerPort() + "/" + httpRequest.getContextPath() + "/" + paginaRitorno + ".faces&silSessionId=" + session.getId() + "&risorsa=" + risorsa + risorsaMonitoraggioStato;
                        }
                    }


                    logger.info("Redirect a " + indirizzoRedirect);
                    httpResponse.sendRedirect(indirizzoRedirect);
                    return;
                }
            }
        }

        filterCahin.doFilter(request, response);

    }

    public void destroy() {
        this.filterConfig = null;
    }
}
