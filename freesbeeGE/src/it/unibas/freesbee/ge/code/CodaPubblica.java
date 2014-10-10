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
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CodaPubblica implements MessageListener {

    private Log logger = LogFactory.getLog(this.getClass());
    private ConfigurazioneSP configurazioneSP;
    private Configurazione configurazione;
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


            logger.debug("Coda Pubblica  -- Messaggio con ID: " + statoMessaggio.getEventoPubblicato().getId());
            logger.debug("Da consegnare a: " + statoMessaggio.getSottoscrittore());

            //il messaggio verrà inviato ad un altro GE
            //url = "http://192.168.7.17:8080/freesbeesp/schermoLogin.faces?autenticazione=AGENTE&risorsa=https://sp.example.unibas.org/PUBBLICA/&username=stelavoro&password=stelavoro";

            if (ConfigurazioneStatico.getInstance().isProtezioneSP()) {
                url = configurazioneSP.getUrlFreesbeeSP() + "?" + "autenticazione=" + configurazioneSP.getAutenticazione() + "&" + "risorsa=" + configurazioneSP.getRisorsa() + configurazioneSP.getRisorsaPdPubblica() + "/" + "&" + "username=" + configurazioneSP.getNomeUtenteSP() + "&" + "password=" + configurazioneSP.getPasswordSP();
            } else {
                url = configurazione.getPdPubblica();
            }

            logger.debug("url: " + url);

            DAOUtilHibernate.beginTransaction();
            messaggioDaConsegnare = Messaggi.creaMessaggioPubblica(statoMessaggio);
            DAOUtilHibernate.commit();

            clientPD.ripulisci();
            clientPD.setErogatore(statoMessaggio.getSottoscrittore().getNome());
            clientPD.setTipo_erogatore(statoMessaggio.getSottoscrittore().getTipo());
            clientPD.setFruitore(configurazione.getNomeGestoreEventi());
            clientPD.setTipo_fruitore(configurazione.getTipoGestoreEventi());
//            clientPD.setServizio(configurazione.getNomeServizioPubblica() + "_" + statoMessaggio.getEventoPubblicato().getCategoriaEventi().getNome());
            clientPD.setServizio(configurazione.getNomeServizioPubblica());
            clientPD.setTipo_servizio(configurazione.getTipoServizioPubblica());

            clientPD.invocaPortaDelegata(url, messaggioDaConsegnare);

            logger.info("Pubblicazione effettuata correttamente");

        } catch (DAOException ex) {
            DAOUtilHibernate.rollback();
            logger.error("Errore nell'accesso al database");
//            ex.printStackTrace();
        } catch (JMSException ex) {
            logger.error("E' stato inserito nella coda un messaggio non valido");
//            ex.printStackTrace();
        } catch (Exception ex) {
            logger.error("Errore nella pubblicazione del messaggio");
//            ex.printStackTrace();
        }


    }
}
