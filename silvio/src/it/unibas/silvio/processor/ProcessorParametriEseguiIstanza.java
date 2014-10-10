package it.unibas.silvio.processor;

import it.unibas.silvio.modello.Messaggio;
import it.unibas.silvio.modello.ParametriEseguiIstanza;
import it.unibas.silvio.persistenza.SilvioException;
import it.unibas.silvio.util.CostantiSilvio;
import it.unibas.silvio.xml.DAOIstanzaXml;
import it.unibas.silvio.xml.XmlException;
import it.unibas.silvio.xml.XmlJDomUtil;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;

public class ProcessorParametriEseguiIstanza implements Processor {

    private Log logger = LogFactory.getLog(this.getClass());

    public void process(Exchange exchange) throws Exception {
        try {
            Message messageIn = exchange.getIn();
            Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiSilvio.MESSAGGIO_RICHIESTA);
            String stringaMessaggio = messageIn.getBody(String.class);
            logger.debug("Ricevuta la seguente configurazione per avviare l'istanza " + messaggio.getIstanzaPortType());
            logger.debug(stringaMessaggio);
            ByteArrayInputStream messageIS = new ByteArrayInputStream(stringaMessaggio.getBytes());
            InputStream isXSD = this.getClass().getResourceAsStream("/" + CostantiSilvio.NOME_FILE_ESEGUI_ISTANZA_XSD);
            if (isXSD == null) {
                throw new SilvioException("Impossibile caricare l'xsd " + CostantiSilvio.NOME_FILE_ESEGUI_ISTANZA_XSD + " per validare il file di configurazione per l'istanza");
            }
            logger.debug("File XML Configurazione: " + stringaMessaggio);
            Document doc = XmlJDomUtil.caricaXML(messageIS, true, isXSD);            
            ParametriEseguiIstanza parametriEseguiIstanza = DAOIstanzaXml.elaboraFileConfigurazione(doc);
            messaggio.setParametriEseguiIstanza(parametriEseguiIstanza);
            exchange.setProperty(CostantiSilvio.NOME_OPERAZIONE, parametriEseguiIstanza.getNomeOperazione());
            logger.debug("File di configurazione elaborato correttamente. \n" + parametriEseguiIstanza);
        } catch (XmlException ex) {
            logger.error("Errore nella lettura dei parametri per l'esecuzione dell'istanza " + ex);
            throw new SilvioException("Errore nella lettura dei parametri per l'esecuzione dell'istanza " + ex);
        }catch (Exception ex) {
            logger.error("Errore nella lettura dei parametri per l'esecuzione dell'istanza " + ex);
            throw new SilvioException("Errore nella lettura dei parametri per l'esecuzione dell'istanza " + ex);
        }
    }
}