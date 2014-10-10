package it.unibas.silvio.web.vista.app;

import it.unibas.silvio.modello.IstanzaOperation;
import org.apache.commons.logging.Log;

public class VistaApp {

    private org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog(this.getClass());
    private IstanzaOperation istanzaOperationSelezionata;

    public IstanzaOperation getIstanzaOperationSelezionata() {
        return istanzaOperationSelezionata;
    }

    public void setIstanzaOperationSelezionata(IstanzaOperation istanzaOperationSelezionata) {
        this.istanzaOperationSelezionata = istanzaOperationSelezionata;
    }

    public Log getLogger() {
        return logger;
    }

    public void setLogger(Log logger) {
        this.logger = logger;
    }

    
}
