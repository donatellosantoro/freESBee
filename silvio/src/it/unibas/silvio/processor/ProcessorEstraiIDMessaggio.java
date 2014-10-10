package it.unibas.silvio.processor;

import it.unibas.silvio.util.CostantiSilvio;
import it.unibas.silvio.xml.XmlJDomUtil;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;

public class ProcessorEstraiIDMessaggio implements Processor{
    
    private Log logger = LogFactory.getLog(this.getClass());

    public void process(Exchange exchange) throws Exception {
        String messaggioIngresso = exchange.getIn().getBody(String.class);
        Document documentMessaggio = XmlJDomUtil.caricaXML(messaggioIngresso);
        
        Element root = documentMessaggio.getRootElement();
        Element elementoID = root.getChild("id");
        String stringaID = elementoID.getText();
        long id = Long.parseLong(stringaID);
        logger.info("Richiesto lo sblocco per l'id: " + id);
        
        exchange.setProperty(CostantiSilvio.ID_MESSAGGIO_SBLOCCO, id);
    }

}
