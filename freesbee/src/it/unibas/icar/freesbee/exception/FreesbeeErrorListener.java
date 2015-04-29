package it.unibas.icar.freesbee.exception;

import it.unibas.icar.freesbee.contrib.XMLStreamHelperFix;
import javax.xml.transform.ErrorListener;
import javax.xml.transform.TransformerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

public class FreesbeeErrorListener implements ErrorListener {

//    private static Log logger = LogFactory.getLog(FreesbeeErrorListener.class.getName());
    private static final Logger logger = LoggerFactory.getLogger(FreesbeeErrorListener.class.getName());
    
    public void warning(TransformerException exception) throws TransformerException {
        logger.warn("Attenzione, e' stata rilevata un'anomalia nel sistema.");
        if (logger.isDebugEnabled()) exception.printStackTrace();
    }

    public void error(TransformerException exception) throws TransformerException {
        logger.error("Si e' verificato un errore nel sistema.");
        if (logger.isDebugEnabled()) exception.printStackTrace();
    }

    public void fatalError(TransformerException exception) throws TransformerException {
        logger.error("Si e' verificato un errore grave nel sistema.");
        if (logger.isDebugEnabled()) exception.printStackTrace();
    }
    
}
