package it.unibas.freesbee.websla.persistenza.soap;

import it.unibas.freesbee.client.cxf.sistemamonitoraggio.GuaranteeTermObj;
import it.unibas.freesbee.client.cxf.sistemamonitoraggio.RequestServiceGuaranteeTermStateType;
import it.unibas.freesbee.client.cxf.sistemamonitoraggio.RequestServiceTermStateType;
import it.unibas.freesbee.client.cxf.sistemamonitoraggio.ResponseServiceGuaranteeTermStateTypeSuper;
import it.unibas.freesbee.client.cxf.sistemamonitoraggio.ResponseServiceTermStateTypeSuper;
import it.unibas.freesbee.client.cxf.sistemamonitoraggio.ResultGaranteeTermObjStateSuper;
import it.unibas.freesbee.utilita.ClientPD;
import it.unibas.freesbee.websla.controllo.ControlloMonitoraggioSLAServiziFruiti;
import it.unibas.freesbee.websla.controllo.ControlloMonitoraggioStatoServiziFruiti;
import it.unibas.freesbee.websla.modello.WebServizio;
import it.unibas.freesbee.websla.modello.ConfigurazioneStatico;
import it.unibas.freesbee.websla.modello.WebServizioFruito;
import it.unibas.freesbee.websla.utilita.MessaggiMonitoraggio;
import it.unibas.freesbee.websla.vista.VistaMonitoraggioSLAServiziFruiti;
import it.unibas.freesbee.websla.vista.VistaMonitoraggioStatoServiziFruiti;
import it.unibas.freesbee.websla.ws.web.stub.DatiConfigurazione;
import it.unibas.icar.freesbee.modello.Soggetto;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import javax.xml.bind.*;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.helpers.XMLUtils;
import org.xml.sax.SAXException;

public class DAOMonitoraggioSOAP {

    private Log logger = LogFactory.getLog(this.getClass());
    private ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();
    private ClientPD clientPD = new ClientPD();

    public List<ResultGaranteeTermObjStateSuper> monitoraggioSLAErogati(String url, WebServizio webServizio, List<GuaranteeTermObj> listaSLA) throws DAOException {
        List<ResultGaranteeTermObjStateSuper> listaRisposta = new ArrayList<ResultGaranteeTermObjStateSuper>();

        try {
            //Creazione della richiesta da inviare alla PD 
            RequestServiceGuaranteeTermStateType richiesta = new RequestServiceGuaranteeTermStateType();
            richiesta.setIdService(webServizio.getNome());
            richiesta.setIdInitiator(webServizio.getFruitore());
            richiesta.setIdResponder(webServizio.getErogatore());

            richiesta.getGuaranteeTermObj().addAll(listaSLA);

            String bodyRichiesta = this.marsahaller(richiesta);

            //Preleva l'indirizzo della PD dalla configurazione
            logger.info("\n\nURL: " + url + "\n\n");

            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            DatiConfigurazione dati = (DatiConfigurazione) session.getAttribute("datiConfigurazione");

            clientPD.setErogatore(dati.getNomeSLA());
            clientPD.setTipo_erogatore(dati.getTipoSLA());
            clientPD.setFruitore(dati.getNomeSLA());
            clientPD.setTipo_fruitore(dati.getTipoSLA());
            clientPD.setServizio(dati.getNomeServizioMonitoraggioSLA());
            clientPD.setTipo_servizio(dati.getTipoServizioMonitoraggioSLA());
            String bodyRisposta = clientPD.invocaPortaDelegata(url, bodyRichiesta);
            clientPD.ripulisci();

            //String bodyRisposta = UtilitaMessaggi.inviaMessaggioXmlToURL(url, bodyRichiesta);
            //String bodyRisposta = clientPD.invocaPortaDelegata(conf.getPortaDelegataServizioMonitoraggioSLA(), bodyRichiesta);

            ResponseServiceGuaranteeTermStateTypeSuper risposta = (ResponseServiceGuaranteeTermStateTypeSuper) this.unmarsahaller(bodyRisposta, "responseServiceGuaranteeTermStateTypeSuper");

            listaRisposta = risposta.getResultGaranteeTermObjStateSuper();



        } catch (MalformedURLException ex) {
            logger.error("Impossibile impostare la comunicazione con il modulo di monitoraggio freESBeSLA. " + ex);
            throw new DAOException(ex);
        } catch (IOException ioex) {
            logger.error("Impossibile inviare la richiesta alla porta delegata. " + ioex);
            throw new DAOException(ioex);
        } catch (Exception ex) {
            logger.error("Impossibile aggiungere i parametri SLA. " + ex);
            throw new DAOException(ex);
        }
        return listaRisposta;
    }

    public ResponseServiceTermStateTypeSuper monitoraggioStatoErogati(String url, WebServizio webServizio) throws DAOException {
        ResponseServiceTermStateTypeSuper risposta = null;
        try {
            //Creazione della richiesta da inviare alla PD 
            RequestServiceTermStateType richiesta = new RequestServiceTermStateType();
            richiesta.setIdService(webServizio.getNome());
            richiesta.setIdInitiator(webServizio.getFruitore());
            richiesta.setIdResponder(webServizio.getErogatore());

            String bodyRichiesta = this.marsahaller(richiesta);

            //Preleva l'indirizzo della PD dalla configurazione
            logger.info("\n\nURL: " + url + "\n\n");
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            DatiConfigurazione dati = (DatiConfigurazione) session.getAttribute("datiConfigurazione");

            clientPD.setErogatore(dati.getNomeSLA());
            clientPD.setTipo_erogatore(dati.getTipoSLA());
            clientPD.setFruitore(dati.getNomeSLA());
            clientPD.setTipo_fruitore(dati.getTipoSLA());
            clientPD.setServizio(dati.getNomeServizioMonitoraggioStato());
            clientPD.setTipo_servizio(dati.getTipoServizioMonitoraggioStato());
            String bodyRisposta = clientPD.invocaPortaDelegata(url, bodyRichiesta);
            clientPD.ripulisci();

            //String bodyRisposta = clientPD.invocaPortaDelegata(conf.getPortaDelegataServizioMonitoraggioStato(), bodyRichiesta);
            //String bodyRisposta = UtilitaMessaggi.inviaMessaggioXmlToURL(url, bodyRichiesta);

         risposta = (ResponseServiceTermStateTypeSuper) this.unmarsahaller(bodyRisposta, "responseServiceTermStateTypeSuper");


        } catch (IOException ioex) {
            logger.error("Impossibile inviare la richiesta alla porta delegata. " + ioex);
            throw new DAOException(ioex);
        } catch (Exception ex) {
            logger.error("Errore durante la verifica dello stato del servizio erogato.  " + ex);
            throw new DAOException(ex);
        }
        return risposta;
    }

    public ResponseServiceTermStateTypeSuper monitoraggioStatoFruiti(String url, WebServizioFruito webServizio) throws DAOException {
      ResponseServiceTermStateTypeSuper risposta = null;
        try {
            if (webServizio.isTipo()) {
                //Creazione della richiesta da inviare alla PD 
                RequestServiceTermStateType richiesta = new RequestServiceTermStateType();
                richiesta.setIdService(webServizio.getNome());
                richiesta.setIdInitiator(webServizio.getFruitore());
                richiesta.setIdResponder(webServizio.getErogatore());

                String bodyRichiesta = this.marsahaller(richiesta);

                //Preleva l'indirizzo della PD 

                logger.info("\n\nURL: " + url + "\n\n");
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                DatiConfigurazione dati = (DatiConfigurazione) session.getAttribute("datiConfigurazione");
                VistaMonitoraggioStatoServiziFruiti vista = (VistaMonitoraggioStatoServiziFruiti) session.getAttribute("vistaMonitoraggioStatoServiziFruiti");
                ControlloMonitoraggioStatoServiziFruiti controllo = (ControlloMonitoraggioStatoServiziFruiti) session.getAttribute("controlloMonitoraggioStatoServiziFruiti");
                Soggetto erogatore = controllo.getMappaSoggettiSLA().get(vista.getSoggettoSlaSelezionato());

                clientPD.setErogatore(erogatore.getNome());
                clientPD.setTipo_erogatore(erogatore.getTipo());
                clientPD.setFruitore(dati.getNomeSLA());
                clientPD.setTipo_fruitore(dati.getTipoSLA());
                clientPD.setServizio(dati.getNomeServizioMonitoraggioStato());
                clientPD.setTipo_servizio(dati.getTipoServizioMonitoraggioStato());
                String bodyRisposta = clientPD.invocaPortaDelegata(url, bodyRichiesta);
                clientPD.ripulisci();

                //String pd = webServizio.getPortaDelegataMonitoraggioStato();
                //String bodyRisposta = UtilitaMessaggi.inviaMessaggioXmlToPD(pd, bodyRichiesta);
                // String bodyRisposta = clientPD.invocaPortaDelegata(pd, bodyRichiesta);

               risposta = (ResponseServiceTermStateTypeSuper) this.unmarsahaller(bodyRisposta, "responseServiceTermStateTypeSuper");

            } else {
                //TODO: per la compatibilita spcoop
                //Creazione della richiesta da inviare alla PD 
                String bodyRichiesta = MessaggiMonitoraggio.requestServiceTermStateType(webServizio.getNome(), webServizio.getFruitore(), webServizio.getErogatore());
                logger.info("Messaggio inviato alla porta delegata: " + bodyRichiesta);

                //Preleva l'indirizzo della PD 
                String pd = webServizio.getPortaDelegataMonitoraggioStato();
                logger.info("PD: " + pd);

//                String bodyRisposta = UtilitaMessaggi.inviaMessaggioXmlToPD(pd, bodyRichiesta);
                String bodyRisposta = clientPD.invocaPortaDelegata(pd, bodyRichiesta);

//                response = MessaggiMonitoraggio.responseServiceTermStateType(bodyRisposta);

            }

        } catch (IOException ioex) {
            logger.error("Impossibile inviare la richiesta alla porta delegata. " + ioex);
            throw new DAOException(ioex);
        } catch (Exception ex) {
            logger.error("Errore durante la verifica dello stato del servizio fruito. " + ex);
            throw new DAOException(ex);
        }
        return risposta;
    }

    public List<ResultGaranteeTermObjStateSuper> monitoraggioSLAFruiti(String url, WebServizioFruito webServizio, List<GuaranteeTermObj> listaSLA) throws DAOException {
        List<ResultGaranteeTermObjStateSuper> listaRisposta = new ArrayList<ResultGaranteeTermObjStateSuper>();

        try {
            if (webServizio.isTipo()) {
                //Creazione della richiesta da inviare alla PD 
                RequestServiceGuaranteeTermStateType richiesta = new RequestServiceGuaranteeTermStateType();
                richiesta.setIdService(webServizio.getNome());
                richiesta.setIdInitiator(webServizio.getFruitore());
                richiesta.setIdResponder(webServizio.getErogatore());

                richiesta.getGuaranteeTermObj().addAll(listaSLA);

                String bodyRichiesta = this.marsahaller(richiesta);
                logger.info("\n\nURL: " + url + "\n\n");
                //Preleva l'indirizzo della PD 
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                DatiConfigurazione dati = (DatiConfigurazione) session.getAttribute("datiConfigurazione");
                ControlloMonitoraggioSLAServiziFruiti controllo = (ControlloMonitoraggioSLAServiziFruiti) session.getAttribute("controlloMonitoraggioSLAServiziFruiti");
                VistaMonitoraggioSLAServiziFruiti vista = (VistaMonitoraggioSLAServiziFruiti) session.getAttribute("vistaMonitoraggioSLAServiziFruiti");
                Soggetto erogatore = controllo.getMappaSoggettiSLA().get(vista.getSoggettoSlaSelezionato());

                clientPD.setErogatore(erogatore.getNome());
                clientPD.setTipo_erogatore(erogatore.getTipo());
                clientPD.setFruitore(dati.getNomeSLA());
                clientPD.setTipo_fruitore(dati.getTipoSLA());
                clientPD.setServizio(dati.getNomeServizioMonitoraggioSLA());
                clientPD.setTipo_servizio(dati.getTipoServizioMonitoraggioSLA());
                String bodyRisposta = clientPD.invocaPortaDelegata(url, bodyRichiesta);
                clientPD.ripulisci();


                //String pd = webServizio.getPortaDelegataMonitoraggioSLA();
                //String bodyRisposta = UtilitaMessaggi.inviaMessaggioXmlToPD(pd, bodyRichiesta);
                //String bodyRisposta = clientPD.invocaPortaDelegata(pd, bodyRichiesta);

                ResponseServiceGuaranteeTermStateTypeSuper risposta = (ResponseServiceGuaranteeTermStateTypeSuper) this.unmarsahaller(bodyRisposta, "responseServiceGuaranteeTermStateTypeSuper");

                listaRisposta = risposta.getResultGaranteeTermObjStateSuper();


            } else {
//TODO: compatibilita spccoop
                //Creazione della richiesta da inviare alla PD 
                GuaranteeTermObj sla = listaSLA.get(0);
                String bodyRichiesta = MessaggiMonitoraggio.requestServiceGuaranteeTermStateType(webServizio.getNome(), webServizio.getFruitore(), webServizio.getErogatore(), sla.getGuaranteeTermName(), sla.getDateFn().toXMLFormat());
                logger.info("Messaggio inviato alla porta delegata: " + bodyRichiesta);

                //Preleva l'indirizzo della PD 
                String pd = webServizio.getPortaDelegataMonitoraggioSLA();
                logger.info("PD: " + pd);

//                String bodyRisposta = UtilitaMessaggi.inviaMessaggioXmlToPD(pd, bodyRichiesta);
                String bodyRisposta = clientPD.invocaPortaDelegata(pd, bodyRichiesta);


//                listaResponse.add(MessaggiMonitoraggio.responseServiceGuaranteeTermStateType(bodyRisposta));
                return null;
            }

        } catch (MalformedURLException ex) {
            logger.error("Impossibile impostare la comunicazione con il modulo di monitoraggio freESBeSLA. " + ex);
            throw new DAOException(ex);
        } catch (IOException ioex) {
            logger.error("Impossibile inviare la richiesta alla porta delegata. " + ioex);
            throw new DAOException(ioex);
        } catch (Exception e) {
            logger.error("Impossibile aggiungere i parametri SLA. " + e);
            throw new DAOException(e);
        }
        return listaRisposta;
    }

    public String marsahaller(Object o) throws JAXBException, FileNotFoundException, SOAPException, ParserConfigurationException, SAXException, IOException {
        logger.info("Effettuo il marshaller dell'oggetto richiesta");
        JAXBContext jc = JAXBContext.newInstance("it.unibas.freesbee.client.cxf.sistemamonitoraggio");
        Marshaller m = jc.createMarshaller();
        OutputStream os = new ByteArrayOutputStream();
        m.marshal(o, os);

        MessageFactory messageFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
        SOAPMessage message = messageFactory.createMessage();
        Document bodyDocument = XMLUtils.parse(os.toString());
        message.getSOAPBody().addDocument(bodyDocument);
        message.saveChanges();
        ByteArrayOutputStream streamSOAP = new ByteArrayOutputStream();
        message.writeTo(streamSOAP);
        logger.debug("Messaggio inviato alla porta delegata: " + streamSOAP.toString());
        return streamSOAP.toString();
    }

    public Object unmarsahaller(String messaggioSOAP, String nomeClasse) throws JAXBException, FileNotFoundException, SOAPException, SOAPException {
        logger.info("Effettuo l'unmarshaller del messaggio risposta");
        logger.info("@@@@@@@@@@@ Messaggio : " + messaggioSOAP);
        MessageFactory messageFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
        SOAPMessage message = messageFactory.createMessage();
        SOAPPart soapPart = message.getSOAPPart();
        Reader soapStringReader = new StringReader(messaggioSOAP);
        Source soapSource = new StreamSource(soapStringReader);
        soapPart.setContent(soapSource);
        message.saveChanges();
        String streamSoap = "";

        if (message.getSOAPBody() != null && message.getSOAPBody().hasChildNodes()) {
            Document document = message.getSOAPBody().extractContentAsDocument();
            streamSoap = XMLUtils.toString(document);

            streamSoap = streamSoap.replace("risposta", nomeClasse);
            int f = streamSoap.indexOf('>');
            streamSoap = (String) streamSoap.subSequence(f + 1, streamSoap.length());
            logger.debug("Messaggio ricevuto  " + streamSoap);
        }
        JAXBContext jc = JAXBContext.newInstance("it.unibas.freesbee.client.cxf.sistemamonitoraggio");
        Unmarshaller u = jc.createUnmarshaller();

        return u.unmarshal(new ByteArrayInputStream(streamSoap.getBytes()));
    }
}
