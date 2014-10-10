package it.unibas.icar.freesbee.modello;

import java.util.ArrayList;
import java.util.List;

public class Messaggio {
    
    public static String TIPO_INVIATO = "INVIATO";
    public static String TIPO_RICEVUTO = "RICEVUTO";    

    private long id;
    private String idMessaggio;
    private String riferimentoMessaggio;
    private String oraRegistrazione;
    private String idSil;
    private String silRelatesTo;
    private String fruitore;
    private String tipoFruitore;
    private String erogatore;
    private String tipoErogatore;
    private String servizio;
    private String tipoServizio;
    private String connettoreErogatore;
    private String connettoreServizioApplicativo;
    private String azione;
    private String portaDelegata;
    private String portaDelegataChannel;   
    private String portaApplicativa;
    private String portaApplicativaChannel;        
    private String profiloCollaborazione;      
    private String tipoProfiloCollaborazione;
    private String confermaRicezione;
    private String inoltro;
    private String indirizzoTelematico;
    private String indirizzoTelematicoErogatore;
    private String indirizzoTelematicoFruitore;
    private String servizioCorrelato;
    private boolean correlato;
    private String collaborazione;
    private String numeroProgressivo;
    private String scadenza;
    private String tempo;
    private String tipo;
    private boolean nica = false;
    private Soggetto nomeNica;
    private List<Eccezione> listaEccezioni = new ArrayList<Eccezione>();
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    
    public String getIdMessaggio() {
        return idMessaggio;
    }

    public void setIdMessaggio(String idMessaggio) {
        this.idMessaggio = idMessaggio;
    }

    public String getRiferimentoMessaggio() {
        return riferimentoMessaggio;
    }

    public void setRiferimentoMessaggio(String riferimentoMessaggio) {
        this.riferimentoMessaggio = riferimentoMessaggio;
    }

    public String getOraRegistrazione() {
        return oraRegistrazione;
    }

    public void setOraRegistrazione(String oraRegistrazione) {
        this.oraRegistrazione = oraRegistrazione;
    }
    
    public String getIdSil() {
        return idSil;
    }

    public void setIdSil(String idSil) {
        this.idSil = idSil;
    }

    
    public String getSilRelatesTo() {
        return silRelatesTo;
    }

    public void setSilRelatesTo(String silRelatesTo) {
        this.silRelatesTo = silRelatesTo;
    }

    public String getAzione() {
        return azione;
    }

    public void setAzione(String azione) {
        this.azione = azione;
    }

    public String getConnettoreErogatore() {
        return connettoreErogatore;
    }

    public void setConnettoreErogatore(String connettoreErogatore) {
        this.connettoreErogatore = connettoreErogatore;
    }

    public String getErogatore() {
        return erogatore;
    }

    public void setErogatore(String erogatore) {
        this.erogatore = erogatore;
    }

    public String getFruitore() {
        return fruitore;
    }

    public void setFruitore(String fruitore) {
        this.fruitore = fruitore;
    }

    public String getServizio() {
        return servizio;
    }

    public void setServizio(String servizio) {
        this.servizio = servizio;
    }

    public String getTipoErogatore() {
        return tipoErogatore;
    }

    public void setTipoErogatore(String tipoErogatore) {
        this.tipoErogatore = tipoErogatore;
    }

    public String getTipoFruitore() {
        return tipoFruitore;
    }

    public void setTipoFruitore(String tipoFruitore) {
        this.tipoFruitore = tipoFruitore;
    }

    public String getTipoServizio() {
        return tipoServizio;
    }

    public void setTipoServizio(String tipoServizio) {
        this.tipoServizio = tipoServizio;
    }

    public String getPortaDelegata() {
        return portaDelegata;
    }

    public void setPortaDelegata(String portaDelegata) {
        this.portaDelegata = portaDelegata;
    }

    public String getPortaDelegataChannel() {
        return portaDelegataChannel;
    }

    public void setPortaDelegataChannel(String portaDelegataChannel) {
        this.portaDelegataChannel = portaDelegataChannel;
    }

    public String getConfermaRicezione() {
        return confermaRicezione;
    }

    public void setConfermaRicezione(String confermaRicezione) {
        this.confermaRicezione = confermaRicezione;
    }

    public String getInoltro() {
        return inoltro;
    }

    public void setInoltro(String inoltro) {
        this.inoltro = inoltro;
    }

    public String getProfiloCollaborazione() {
        return profiloCollaborazione;
    }

    public void setProfiloCollaborazione(String profiloCollaborazione) {
        this.profiloCollaborazione = profiloCollaborazione;
    }

    public String getIndirizzoTelematico() {
        return indirizzoTelematico;
    }

    public void setIndirizzoTelematico(String indirizzoTelematico) {
        this.indirizzoTelematico = indirizzoTelematico;
    }
    
    public String getIndirizzoTelematicoErogatore() {
        return indirizzoTelematicoErogatore;
    }

    public void setIndirizzoTelematicoErogatore(String indirizzoTelematicoErogatore) {
        this.indirizzoTelematicoErogatore = indirizzoTelematicoErogatore;
    }

    public String getCollaborazione() {
        return collaborazione;
    }

    public void setCollaborazione(String collaborazione) {
        this.collaborazione = collaborazione;
    }

    public String getIndirizzoTelematicoFruitore() {
        return indirizzoTelematicoFruitore;
    }

    public void setIndirizzoTelematicoFruitore(String indirizzoTelematicoFruitore) {
        this.indirizzoTelematicoFruitore = indirizzoTelematicoFruitore;
    }

    public String getNumeroProgressivo() {
        return numeroProgressivo;
    }

    public void setNumeroProgressivo(String numeroProgressivo) {
        this.numeroProgressivo = numeroProgressivo;
    }

    public String getServizioCorrelato() {
        return servizioCorrelato;
    }

    public void setServizioCorrelato(String servizioCorrelato) {
        this.servizioCorrelato = servizioCorrelato;
    }    
    
    public boolean isCorrelato() {
        return correlato;
    }

    public void setCorrelato(boolean correlato) {
        this.correlato = correlato;
    }
    
    public String getPortaApplicativa() {
        return portaApplicativa;
    }

    public void setPortaApplicativa(String portaApplicativa) {
        this.portaApplicativa = portaApplicativa;
    }

    public String getPortaApplicativaChannel() {
        return portaApplicativaChannel;
    }

    public void setPortaApplicativaChannel(String portaApplicativaChannel) {
        this.portaApplicativaChannel = portaApplicativaChannel;
    }

    public String getConnettoreServizioApplicativo() {
        return connettoreServizioApplicativo;
    }

    public void setConnettoreServizioApplicativo(String connettoreServizioApplicativo) {
        this.connettoreServizioApplicativo = connettoreServizioApplicativo;
    }

    public String getScadenza() {
        return scadenza;
    }

    public void setScadenza(String scadenza) {
        this.scadenza = scadenza;
    }
    
    public String getTipoProfiloCollaborazione() {
        return tipoProfiloCollaborazione;
    }

    public void setTipoProfiloCollaborazione(String tipoProfiloCollaborazione) {
        this.tipoProfiloCollaborazione = tipoProfiloCollaborazione;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public List<Eccezione> getListaEccezioni() {
        return listaEccezioni;
    }

    public void setListaEccezioni(List<Eccezione> listaEccezioni) {
        this.listaEccezioni = listaEccezioni;
    }

    public boolean isNica() {
        return nica;
    }

    public void setNica(boolean nica) {
        this.nica = nica;
    }
    
    public Soggetto getNomeNica() {
        return nomeNica;
    }

    public void setNomeNica(Soggetto nomeNica) {
        this.nomeNica = nomeNica;
    }

    @Override
    public String toString() {
        String s = "[" + id + "] IDEGOV: " + this.getIdMessaggio();
        if(this.getRiferimentoMessaggio()!=null && !this.getRiferimentoMessaggio().equals("")){
            s += " Correlato: " + this.getRiferimentoMessaggio();
        }
        s += "\n\tServizio: " + this.getServizio() + " - " + this.getProfiloCollaborazione();        
        if(this.getIdSil()!=null && !this.getIdSil().equals("")){
            s += " \n\tID SIL: " + this.getIdSil();
        }
        if(this.getSilRelatesTo()!=null && !this.getSilRelatesTo().equals("")){
            s += " Correlato: " + this.getSilRelatesTo();
        }
        return s;
    }
    
}
