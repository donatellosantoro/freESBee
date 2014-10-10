package it.unibas.silvio.modello;

import java.util.Date;
import javax.persistence.Transient;

public class Messaggio {

    public static final String INVIATO = "INVIATO";
    public static final String RICEVUTO = "RICEVUTO";
    public static final String ACK = "ACK";

    private String idMessaggio;
    private String canalePolling;
    private String tipo;

    public String getCanalePolling() {
        return canalePolling;
    }

    public void setCanalePolling(String canalePolling) {
        this.canalePolling = canalePolling;
    }

    public String getIdMessaggio() {
        return idMessaggio;
    }

    public void setIdMessaggio(String idMessaggio) {
        this.idMessaggio = idMessaggio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    @Transient
    public void creaIdentificatore(){
        this.setIdMessaggio("SILVIO2-" + new Date().getTime());
    }

}
