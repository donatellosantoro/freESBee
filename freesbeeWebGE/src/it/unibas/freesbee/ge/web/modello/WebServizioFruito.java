package it.unibas.freesbee.ge.web.modello;

public class WebServizioFruito {

    private WebServizio webServizio = new WebServizio();
    private String portaDelegataMonitoraggioStato;
    private String portaDelegataMonitoraggioSLA;

    private boolean tipo = false;
  
    public WebServizio getWebServizio() {
        return webServizio;
    }

    public void setWebServizio(WebServizio webServizio) {
        this.webServizio = webServizio;
    }

    public String getErogatore() {
        return webServizio.getErogatore();
    }

    public String getFruitore() {
        return webServizio.getFruitore();
    }

    public String getNome() {
        return webServizio.getNome();
    }

    public Long getContatoreRichieste() {
        return webServizio.getContatoreRichieste();
    }

    public String getStato() {
        return webServizio.getStato();
    }

    public void setStato(String stato) {
        webServizio.setStato(stato);
    }

    public boolean isAttiva() {
        return webServizio.isAttiva();
    }

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public String getPortaDelegataMonitoraggioStato() {
        return portaDelegataMonitoraggioStato;
    }

    public void setPortaDelegataMonitoraggioStato(String portaDelegataMonitoraggioStato) {
        this.portaDelegataMonitoraggioStato = portaDelegataMonitoraggioStato;
    }

    public String getPortaDelegataMonitoraggioSLA() {
        return portaDelegataMonitoraggioSLA;
    }

    public void setPortaDelegataMonitoraggioSLA(String portaDelegataMonitoraggioSLA) {
        this.portaDelegataMonitoraggioSLA = portaDelegataMonitoraggioSLA;
    }
}
