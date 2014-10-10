package it.unibas.freesbee.ge.code;

import it.unibas.freesbee.ge.modello.Configurazione;
import it.unibas.freesbee.ge.modello.ConfigurazioniFactory;
import it.unibas.freesbee.ge.modello.ConfigurazioneSP;
import it.unibas.freesbee.ge.modello.StatoMessaggioEsterno;
import it.unibas.freesbee.ge.persistenza.DAOException;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOUtilHibernate;
import it.unibas.freesbee.ge.stub.NotificaEventoPubblicato;
import it.unibas.freesbee.utilita.ClientPD;
import it.unibas.springfreesbee.ge.ConfigurazioneStatico;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import javax.xml.bind.*;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.helpers.XMLUtils;
import java.io.IOException;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import org.xml.sax.SAXException;

public class CodaNotificaEventoEsterno implements MessageListener {

    private Log logger = LogFactory.getLog(this.getClass());
    private static ClientPD clientPD = new ClientPD();

    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage) message;
        StatoMessaggioEsterno statoMessaggio = null;
        NotificaEventoPubblicato eventoPubblicatoNotificato = new NotificaEventoPubblicato();
        String url = "";

        try {
            DAOUtilHibernate.beginTransaction();
            Configurazione configurazione = ConfigurazioniFactory.getConfigurazioneIstance();
            ConfigurazioneSP configurazioneSP = ConfigurazioniFactory.getConfigurazioneSPIstance();
            DAOUtilHibernate.commit();

            statoMessaggio = (StatoMessaggioEsterno) objectMessage.getObject();

            logger.debug("Coda Notifica Esterna -- Messaggio con ID: " + statoMessaggio.getEventoPubblicato().getId());
            logger.debug("Da notificare a: " + statoMessaggio.getSottoscrittore());

            eventoPubblicatoNotificato.setIdEventoPubblicato(statoMessaggio.getEventoPubblicato().getId() + "");
            eventoPubblicatoNotificato.setCategoriaEventi(statoMessaggio.getEventoPubblicato().getCategoriaEventi().getNome());

            if (ConfigurazioneStatico.getInstance().isProtezioneSP()) {
                url = configurazioneSP.getUrlFreesbeeSP() + "?" + "autenticazione=" + configurazioneSP.getAutenticazione() + "&" + "risorsa=" + configurazioneSP.getRisorsa() + configurazioneSP.getRisorsaPdNotifica() + "/" + "&" + "username=" + configurazioneSP.getNomeUtenteSP() + "&" + "password=" + configurazioneSP.getPasswordSP();
            } else {
                url = configurazione.getPdNotifica();
            }

            logger.debug("url: " + url);

            clientPD.ripulisci();
            clientPD.setErogatore(statoMessaggio.getSottoscrittore().getNome());
            clientPD.setTipo_erogatore(statoMessaggio.getSottoscrittore().getTipo());
            clientPD.setFruitore(configurazione.getNomeGestoreEventi());
            clientPD.setTipo_fruitore(configurazione.getTipoGestoreEventi());
            clientPD.setServizio(configurazione.getNomeServizioNotifica() + "_" + statoMessaggio.getEventoPubblicato().getCategoriaEventi().getNome());
            clientPD.setTipo_servizio(configurazione.getTipoServizioNotifica());
            clientPD.setAzione(configurazione.getAzioneServizioNotifica());
            clientPD.invocaPortaDelegata(url, marsahaller(eventoPubblicatoNotificato));


            logger.debug("Notifica effettuata correttamente");

        } catch (DAOException ex) {
            DAOUtilHibernate.rollback();
            logger.error("Errore nell'accesso al database");
        } catch (JMSException ex) {
            logger.error("E' stato inserito nella coda un messaggio non valido");
//            ex.printStackTrace();
        } catch (Exception ex) {
            logger.error("Errore nel marshaller");
//            ex.printStackTrace();
        }
    }

    public String marsahaller(Object o) throws JAXBException, FileNotFoundException, SOAPException, ParserConfigurationException, SAXException, IOException {
        logger.debug("Effettuo il marshaller dell'oggetto richiesta");
        JAXBContext jc = JAXBContext.newInstance("it.unibas.freesbee.ge.stub");
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

        return streamSOAP.toString();
    }
}
