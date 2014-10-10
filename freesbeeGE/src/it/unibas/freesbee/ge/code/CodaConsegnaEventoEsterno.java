package it.unibas.freesbee.ge.code;

import it.unibas.freesbee.ge.messaggi.Messaggi;
import it.unibas.freesbee.ge.modello.Configurazione;
import it.unibas.freesbee.ge.modello.ConfigurazioniFactory;
import it.unibas.freesbee.ge.modello.ConfigurazioneSP;
import it.unibas.freesbee.ge.modello.StatoMessaggioEsterno;
import it.unibas.freesbee.ge.persistenza.DAOException;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOUtilHibernate;
import it.unibas.freesbee.utilita.ClientPD;
import it.unibas.springfreesbee.ge.ConfigurazioneStatico;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CodaConsegnaEventoEsterno implements MessageListener {

    private Log logger = LogFactory.getLog(this.getClass());
    private ConfigurazioneSP configurazioneSP;
    private Configurazione configurazione;
    private static ClientPD clientPD = new ClientPD();

    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage) message;
        StatoMessaggioEsterno statoMessaggio = null;
        String messaggioDaConsegnare = "";
        String url = "";

        try {
            DAOUtilHibernate.beginTransaction();
            configurazione = ConfigurazioniFactory.getConfigurazioneIstance();
            configurazioneSP = ConfigurazioniFactory.getConfigurazioneSPIstance();
            DAOUtilHibernate.commit();

            statoMessaggio = (StatoMessaggioEsterno) objectMessage.getObject();
            logger.debug("Coda Consegna nel Dominio (Esterna) -- Messaggio con ID: " + statoMessaggio.getEventoPubblicato().getId());
            logger.debug("Da consegnare a: " + statoMessaggio.getSottoscrittore());

            if (ConfigurazioneStatico.getInstance().isProtezioneSP()) {
                url = configurazioneSP.getUrlFreesbeeSP() + "?" + "autenticazione=" + configurazioneSP.getAutenticazione() + "&" + "risorsa=" + configurazioneSP.getRisorsa() + configurazioneSP.getRisorsaPdConsegna() + "/" + "&" + "username=" + configurazioneSP.getNomeUtenteSP() + "&" + "password=" + configurazioneSP.getPasswordSP();
            } else {
                url = configurazione.getPdConsegna();
            }

            logger.debug("url: " + url);

            messaggioDaConsegnare = Messaggi.creaMessaggioConsegnaEsterno(statoMessaggio);
            clientPD.ripulisci();
            clientPD.setErogatore(statoMessaggio.getSottoscrittore().getNome());
            clientPD.setTipo_erogatore(statoMessaggio.getSottoscrittore().getTipo());
            clientPD.setFruitore(configurazione.getNomeGestoreEventi());
            clientPD.setTipo_fruitore(configurazione.getTipoGestoreEventi());
            clientPD.setServizio(configurazione.getNomeServizioConsegna() + "_" + statoMessaggio.getEventoPubblicato().getCategoriaEventi().getNome());
            clientPD.setTipo_servizio(configurazione.getTipoServizioConsegna());

            clientPD.invocaPortaDelegata(url, messaggioDaConsegnare);

            logger.info("Consegna effettuata correttamente");

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
}
