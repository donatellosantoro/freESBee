package it.unibas.silvio.modello;

import it.unibas.silvio.xml.XmlJDomUtil;
import java.io.Serializable;

public class RispostaEseguiIstanza implements Serializable{

    private String idMessaggio;
    private String idMessaggioRisposta;
    private String messaggio;
    private String messaggioErrore;
    private String tipoMessaggio;
    private boolean errore;

    public boolean isErrore() {
        return errore;
    }

    public void setErrore(boolean errore) {
        this.errore = errore;
    }

    public String getIdMessaggio() {
        return idMessaggio;
    }

    public void setIdMessaggio(String idMessaggio) {
        this.idMessaggio = idMessaggio;
    }

    public String getIdMessaggioRisposta() {
        return idMessaggioRisposta;
    }

    public void setIdMessaggioRisposta(String idMessaggioRisposta) {
        this.idMessaggioRisposta = idMessaggioRisposta;
    }

    public String getMessaggio() {
        return XmlJDomUtil.formattaXML(messaggio);
    }

    public void setMessaggio(String messaggio) {

        this.messaggio = messaggio;
    }

    public String getMessaggioErrore() {
        return messaggioErrore;
    }

    public void setMessaggioErrore(String messaggioErrore) {
        this.messaggioErrore = messaggioErrore;
    }

    public String getTipoMessaggio() {
        return tipoMessaggio;
    }

    public void setTipoMessaggio(String tipoMessaggio) {
        this.tipoMessaggio = tipoMessaggio;
    }
}
