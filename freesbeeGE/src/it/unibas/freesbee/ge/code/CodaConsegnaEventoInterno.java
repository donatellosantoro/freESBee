package it.unibas.freesbee.ge.code;

import it.unibas.freesbee.ge.messaggi.Messaggi;
import it.unibas.freesbee.ge.modello.Configurazione;
import it.unibas.freesbee.ge.modello.ConfigurazioniFactory;
import it.unibas.freesbee.ge.modello.ConfigurazioneSP;
import it.unibas.freesbee.ge.modello.StatoMessaggioInterno;
import it.unibas.freesbee.ge.persistenza.DAOException;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOUtilHibernate;
import it.unibas.freesbee.utilita.ClientPD;
import it.unibas.springfreesbee.ge.ConfigurazioneStatico;
import java.util.HashMap;
import java.util.Map;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CodaConsegnaEventoInterno implements MessageListener {

    private Log logger = LogFactory.getLog(this.getClass());
    private Configurazione configurazione;
    private ConfigurazioneSP configurazioneSP;
    private static ClientPD clientPD = new ClientPD();

    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage) message;
        StatoMessaggioInterno statoMessaggio = null;
        String messaggioDaConsegnare = "";
        String url = "";

        try {
            DAOUtilHibernate.beginTransaction();
            configurazione = ConfigurazioniFactory.getConfigurazioneIstance();
            configurazioneSP = ConfigurazioniFactory.getConfigurazioneSPIstance();
            DAOUtilHibernate.commit();

            statoMessaggio = (StatoMessaggioInterno) objectMessage.getObject();

            logger.debug("Coda Consegna nel Dominio (EventoInterno) -- Messaggio con ID: " + statoMessaggio.getEventoPubblicato().getId());
            logger.debug("Da consegnare a: " + statoMessaggio.getSottoscrittore());


            if (ConfigurazioneStatico.getInstance().isProtezioneSP()) {
                url = configurazioneSP.getUrlFreesbeeSP() + "?" + "autenticazione=" + configurazioneSP.getAutenticazione() + "&" + "risorsa=" + configurazioneSP.getRisorsa() + configurazioneSP.getRisorsaPdConsegna() + "/" + "&" + "username=" + configurazioneSP.getNomeUtenteSP() + "&" + "password=" + configurazioneSP.getPasswordSP();
            } else {
                url = configurazione.getPdConsegna();
            }

            logger.debug("url: " + url);

            messaggioDaConsegnare = Messaggi.creaMessaggioConsegnaInterno(statoMessaggio);

            //Serve per poter effettuare i test
            Map<String, String> headers = new HashMap<String, String>();
            if (messaggioDaConsegnare.contains("comunicazioneEliminazioneSottoscrizione")) {
                headers.put("TIPO_ELIMINAZIONE", "ELIMINAZIONE_SOTTOSCRIZIONE");
            }

            clientPD.ripulisci();
            clientPD.setHeaders(headers);
            clientPD.setErogatore(statoMessaggio.getSottoscrittore().getNome());
            clientPD.setTipo_erogatore(statoMessaggio.getSottoscrittore().getTipo());
            clientPD.setFruitore(configurazione.getNomeGestoreEventi());
            clientPD.setTipo_fruitore(configurazione.getTipoGestoreEventi());
            clientPD.setServizio(configurazione.getNomeServizioConsegna() + "_" + statoMessaggio.getEventoPubblicato().getCategoriaEventi().getNome());
            clientPD.setTipo_servizio(configurazione.getTipoServizioConsegna());
            clientPD.setAzione(configurazione.getAzioneServizioConsegna());
            clientPD.invocaPortaDelegata(url, messaggioDaConsegnare);

            logger.info("Consegna effettuata correttamente");

        } catch (DAOException ex) {
            DAOUtilHibernate.rollback();
            logger.error("Errore nell'accesso al database");
//            ex.printStackTrace();
        } catch (JMSException ex) {
            logger.error("E' stato inserito nella coda un messaggio non valido");
//            ex.printStackTrace();
        } catch (Exception ex) {
            logger.error("Errore nella consegna del messaggio");
//            ex.printStackTrace();
        }

    }
}
