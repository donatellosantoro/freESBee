package it.unibas.freesbee.ge.code;

import it.unibas.springfreesbee.ge.ConfigurazioneStatico;
import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StarterCodaConsegnaEventoEsterno extends RouteBuilder {

    private Log logger = LogFactory.getLog(this.getClass());

    public void configure() throws Exception {
        logger.info("Avvio della coda consegna esterna");
ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://" + ConfigurazioneStatico.getInstance().getWebservicesIndirizzo()+"?broker.useShutdownHook=false");
        Connection connection = connectionFactory.createConnection();
        connection.start();

        // Creazione della sessione
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // Creazione della coda
        Queue destination = session.createQueue("consegnaesterna");
        // Creazione del producer
        MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        // Creazione del consumer
        MessageConsumer consumer = session.createConsumer(destination);
        consumer.setMessageListener(new CodaConsegnaEventoEsterno());
    }
}

