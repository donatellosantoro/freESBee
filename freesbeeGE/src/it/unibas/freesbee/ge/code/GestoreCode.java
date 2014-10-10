package it.unibas.freesbee.ge.code;

import it.unibas.freesbee.ge.modello.StatoMessaggioEsterno;
import it.unibas.freesbee.ge.modello.StatoMessaggioInterno;
import it.unibas.freesbee.ge.persistenza.DAOException;
import it.unibas.freesbee.ge.persistenza.IDAOStatoMessaggioEsterno;
import it.unibas.freesbee.ge.persistenza.IDAOStatoMessaggioInterno;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOStatoMessaggioEsternoHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOStatoMessaggioInternoHibernate;
import it.unibas.springfreesbee.ge.ConfigurazioneStatico;
import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GestoreCode {

    private static Log logger = LogFactory.getLog(GestoreCode.class);

    public GestoreCode() {
    }

    public static boolean addCodaNotificaEventoInterno(StatoMessaggioInterno statoMessaggio) {
        boolean esito = true;
        try {
            logger.info("Inserimento del eventoPubblicato con ID: " + statoMessaggio.getEventoPubblicato().getId() + " nella coda di Notifica Intenra");
            logger.info("StatoMessaggio ID: " + statoMessaggio.getId());
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://" + ConfigurazioneStatico.getInstance().getWebservicesIndirizzo());
            //  ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://" + "192.168.7.9");
            Connection connection = connectionFactory.createConnection();


            // Creazione della sessione
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("notificainterna");


            // Creazione del producer
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);

            // Creazione del Messaggio da notificare
            ObjectMessage message = session.createObjectMessage(statoMessaggio);

            //Invio sulla coda
            producer.send(message);
        } catch (Exception e) {
            logger.error("Errore nella notifica del messaggio " + e);
            esito = false;
        }
        return esito;
    }

    public static boolean addCodaNotificaEventoEsterno(StatoMessaggioEsterno statoMessaggio) {
        boolean esito = true;
        try {

            logger.info("Inserimento del eventoPubblicato con ID: " + statoMessaggio.getEventoPubblicato().getId() + " nella coda di Notifica Esterna");
            logger.info("StatoMessaggio ID: " + statoMessaggio.getId());
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://" + ConfigurazioneStatico.getInstance().getWebservicesIndirizzo());
            //  ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://" + "192.168.7.9");
            Connection connection = connectionFactory.createConnection();


            // Creazione della sessione
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("notificaesterna");


            // Creazione del producer
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);

            // Creazione del Messaggio da notificare
            ObjectMessage message = session.createObjectMessage(statoMessaggio);

            //Invio sulla coda
            producer.send(message);
        } catch (Exception e) {
            logger.error("Errore nella notifica del messaggio " + e);
            esito = false;
        }
        return esito;
    }

    public static boolean addCodaConsegnaEventoInterno(StatoMessaggioInterno statoMessaggio) {
        boolean esito = true;
        try {

            logger.info("Inserimento dell'eventoPubblicato con ID: " + statoMessaggio.getEventoPubblicato().getId() + " nella coda di Consegna Interna");
            logger.info("StatoMessaggio ID: " + statoMessaggio.getId());

            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://" + ConfigurazioneStatico.getInstance().getWebservicesIndirizzo());
            Connection connection = connectionFactory.createConnection();


            // Creazione della sessione
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("consegnainterna");


            // Creazione del producer
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);

            // Creazione del Messaggio da notificare
            ObjectMessage message = session.createObjectMessage(statoMessaggio);

            //Invio sulla coda
            producer.send(message);
        } catch (Exception e) {
            logger.error("Errore nella notifica del messaggio " + e);
            esito = false;
        }
        return esito;
    }

    public static boolean addCodaPubblica(StatoMessaggioInterno statoMessaggio) {
        boolean esito = true;
        try {

            logger.info("Inserimento dell'eventoPubblicato con ID: " + statoMessaggio.getEventoPubblicato().getId() + " nella coda di Ri-Pubblica");
            logger.info("StatoMessaggio ID: " + statoMessaggio.getId());

            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://" + ConfigurazioneStatico.getInstance().getWebservicesIndirizzo());
            Connection connection = connectionFactory.createConnection();


            // Creazione della sessione
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("pubblica");


            // Creazione del producer
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);

            // Creazione del Messaggio da notificare
            ObjectMessage message = session.createObjectMessage(statoMessaggio);

            //Invio sulla coda
            producer.send(message);

        } catch (Exception e) {
            logger.error("Errore nella notifica del messaggio " + e);
            esito = false;
        }
        return esito;
    }

    public static boolean addCodaConsegnaEventoEsterno(StatoMessaggioEsterno statoMessaggio) {
        boolean esito = true;
        try {

            logger.info("Inserimento dell'eventoPubblicato con ID: " + statoMessaggio.getEventoPubblicato().getId() + " nella coda di Consegna Esterna");
            logger.info("StatoMessaggio ID: " + statoMessaggio.getId());
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://" + ConfigurazioneStatico.getInstance().getWebservicesIndirizzo());
            Connection connection = connectionFactory.createConnection();

            // Creazione della sessione
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("consegnaesterna");


            // Creazione del producer
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);

            // Creazione del Messaggio da notificare
            ObjectMessage message = session.createObjectMessage(statoMessaggio);

            //Invio sulla coda
            producer.send(message);
        } catch (Exception e) {
            logger.error("Errore nella notifica del messaggio " + e);
            esito = false;
        }
        return esito;

    }

    public static void aggiornaStatoMessaggioStatoInterno(StatoMessaggioInterno messaggioStato) {
        IDAOStatoMessaggioInterno dao = new DAOStatoMessaggioInternoHibernate();
        try {
            logger.debug("Aggiorno lo stato del messaggio " + messaggioStato.getId());
            dao.makePersistent(messaggioStato);
        } catch (DAOException ex) {
            logger.error("Errore nell'aggiornamento dello stato del messaggio pubblicato" + ex);
        }

    }

    public static void aggiornaStatoMessaggioStatoEsterno(StatoMessaggioEsterno messaggioStato) {
        IDAOStatoMessaggioEsterno dao = new DAOStatoMessaggioEsternoHibernate();
        try {
            logger.debug("Aggiorno lo stato del messaggio " + messaggioStato.getId());
            dao.makePersistent(messaggioStato);
        } catch (DAOException ex) {
            logger.error("Errore nell'aggiornamento dello stato del messaggio pubblicato" + ex);
        }

    }
}





