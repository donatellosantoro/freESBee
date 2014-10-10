package it.unibas.silvio.processor;

import it.unibas.silvio.modello.Configurazione;
import it.unibas.silvio.modello.ConfigurazioneStatico;
import it.unibas.silvio.modello.IstanzaOperation;
import it.unibas.silvio.modello.Messaggio;
import it.unibas.silvio.persistenza.IDAOConfigurazione;
import it.unibas.silvio.persistenza.SilvioException;
import it.unibas.silvio.util.CostantiSilvio;
import it.unibas.silvio.xml.XmlJDomUtil;
import java.io.File;
import java.util.Properties;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ProcessorInviaMailErogatore implements Processor {

    private Log logger = LogFactory.getLog(this.getClass());
    private IDAOConfigurazione daoConfigurazione;

    public ProcessorInviaMailErogatore(IDAOConfigurazione daoConfigurazione) {
        this.daoConfigurazione = daoConfigurazione;
    }

    public void process(Exchange exchange) throws Exception {
        Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiSilvio.MESSAGGIO_RICHIESTA);
        logger.info("*** MESSAGGIO: " + messaggio.getMessaggio());
        IstanzaOperation istanzaOperation = messaggio.getIstanzaOperation();
        boolean istanzaTest = messaggio.getParametriMessaggioRicevuto().isTest();

        if (istanzaTest) {
            logger.info("Ricevuto un messaggio di test. Non devo inviare la mail");
            return;
        }
        if (istanzaOperation.isInviaMail()) {
            try {
                Configurazione configurazione = daoConfigurazione.caricaConfigurazione();
                String fileXSLT = configurazione.getDirConfig() + File.separator +
                        istanzaOperation.getIstanzaPortType().getIstanzaAccordo().getCartellaFiles() +
                        istanzaOperation.getXSLTSoapToMail();
                logger.info("***PERCORSO FILE XSLT: " + fileXSLT);
                logger.info("***NOME FILE XSTL: " + istanzaOperation.getXSLTSoapToMail());
                String messaggioMail = XmlJDomUtil.transformToString(messaggio.getMessaggio(), fileXSLT);
                messaggioMail = messaggioMail.replace("|", "  ");
                messaggioMail = messaggioMail.trim();
                String indirizzo = istanzaOperation.getIndirizzoMail();
                String oggetto = istanzaOperation.getOggettoMail();
                String mittente = ConfigurazioneStatico.getInstance().getIndirizzoMailMittente();
                logger.info("**MESSAGGIO MAIL: " + messaggioMail);
                logger.info("**INDIRIZZO: " + indirizzo);
                logger.info("**OGGETTO: " + oggetto);
                this.inviaMail(indirizzo, oggetto, messaggioMail, mittente);
            } catch (MessagingException me) {
                me.printStackTrace();
                logger.error("*** Impossibile inviare l'e-mail: " + me);
                throw new SilvioException("Impossibile inviare l'e-mail: " + me);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Impossibile eseguire la trasformazione " + e);
                throw new SilvioException("Impossibile eseguire la trasformazione " + e);
            }
        }
    }

    public void inviaMail(String destinatario, String oggetto, String messaggio, String mittente) throws MessagingException {
        Properties props = new Properties();
        String indirizzoServer = ConfigurazioneStatico.getInstance().getIndirizzoServerMail();
        logger.info("*** indirizzoServerWeb: " + indirizzoServer);
        props.put("mail.smtp.host", indirizzoServer);
        // Session sessione = Session.getDefaultInstance(props);
        Session sessione = Session.getInstance(props);
        Message mail = new MimeMessage(sessione);
        InternetAddress indirizzoMittente = new InternetAddress(mittente);
        logger.info("***MITTENTE: " + mittente);
        mail.setFrom(indirizzoMittente);
        InternetAddress indirizzoDestinatario = new InternetAddress(destinatario);
        mail.setRecipient(Message.RecipientType.TO, indirizzoDestinatario);
        mail.setSubject(oggetto);
        String mailContent = ConfigurazioneStatico.getInstance().getMailcontent();
        logger.info("*** MAIL CONTENT: " + mailContent);
        mail.setContent(messaggio, mailContent);
        Transport.send(mail);

    }
}
