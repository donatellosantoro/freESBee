package it.unibas.icar.freesbee.ws.verificainstallazione;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MessaggioInstallazione")
public class MessaggioInstallazione {

    @XmlElement(name = "messaggio")
    private String messaggio;
    @XmlElement(name = "handler")
    private DataHandler handler;

    public MessaggioInstallazione(String messaggio, DataHandler handler) {
        this.messaggio = messaggio;
        this.handler = handler;
    }

    public MessaggioInstallazione() {
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    public DataHandler getHandler() {
        return handler;
    }

    public void setHandler(DataHandler handler) {
        this.handler = handler;
    }

}
