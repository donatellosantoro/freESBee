package it.unibas.icar.freesbee.modello;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;


@Table(uniqueConstraints = {@UniqueConstraint(columnNames={"idMessaggio", "tipo"})})
//                            @UniqueConstraint(columnNames={"idSil", "tipo"}),
//                            @UniqueConstraint(columnNames={"silRelatesTo", "tipo"})})
@Entity
public class Messaggio {

    public static String TIPO_INVIATO = "INVIATO";
    public static String TIPO_RICEVUTO = "RICEVUTO";
    public static String TIPO_MESSAGGIO_RICHIESTA = "Richiesta";
    public static String TIPO_MESSAGGIO_RISPOSTA = "Risposta";
    public static String TIPO_ACK_RICHIESTA = "ACK_RICHIESTA";
    public static String TIPO_ACK_RISPOSTA = "ACK_RISPOSTA";

    private long id;
    private String idMessaggio;
//    private String idMessaggioRichiesta;
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
    private String tipoProfiloCollaborazione;
    private String profiloCollaborazione;      
    private String confermaRicezione;
    private String inoltro;
    private String indirizzoTelematico;
    private String indirizzoTelematicoErogatore;
    private String indirizzoTelematicoFruitore;
    private String tipoServizioCorrelato;
    private String servizioCorrelato;
    private boolean correlato;
    private String collaborazione;
    private String numeroProgressivo;
    private String scadenza;
    private String tempo;
    private String tipo;
    private String tipoMessaggio;
    private String actor;
    private String mustUnderstand;
    private boolean nica = false;
    private Soggetto nomeNica;
    private boolean mittentiMultipli = false;
    private boolean mutuaAutenticazione;
    private List<Eccezione> listaEccezioni = new ArrayList<Eccezione>();
    private List<Trasmissione> listaTrasmissioni = new ArrayList<Trasmissione>();
    private List<Riscontro> listaRiscontri = new ArrayList<Riscontro>();
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
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

//    @Transient
//    public String getIdMessaggioRichiesta() {
//        return idMessaggioRichiesta;
//    }
//
//    public void setIdMessaggioRichiesta(String idMessaggioRichiesta) {
//        this.idMessaggioRichiesta = idMessaggioRichiesta;
//    }
    
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

    @Transient
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


    @Transient
    public String getPortaDelegata() {
        return portaDelegata;
    }

    public void setPortaDelegata(String portaDelegata) {
        this.portaDelegata = portaDelegata;
    }

    @Transient
    public String getPortaDelegataChannel() {
        return portaDelegataChannel;
    }

    public void setPortaDelegataChannel(String portaDelegataChannel) {
        this.portaDelegataChannel = portaDelegataChannel;
    }

    @Transient
    public String getConfermaRicezione() {
        return confermaRicezione;
    }

    public void setConfermaRicezione(String confermaRicezione) {
        this.confermaRicezione = confermaRicezione;
    }

    @Transient
    public String getInoltro() {
        return inoltro;
    }

    public void setInoltro(String inoltro) {
        this.inoltro = inoltro;
    }

    public String getTipoProfiloCollaborazione() {
        return tipoProfiloCollaborazione;
    }

    public void setTipoProfiloCollaborazione(String tipoProfiloCollaborazione) {
        this.tipoProfiloCollaborazione = tipoProfiloCollaborazione;
    }

    public String getProfiloCollaborazione() {
        return profiloCollaborazione;
    }

    public void setProfiloCollaborazione(String profiloCollaborazione) {
        this.profiloCollaborazione = profiloCollaborazione;
    }

    @Transient
    public String getIndirizzoTelematico() {
        return indirizzoTelematico;
    }

    public void setIndirizzoTelematico(String indirizzoTelematico) {
        this.indirizzoTelematico = indirizzoTelematico;
    }
    
    @Transient
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

    @Transient
    public String getIndirizzoTelematicoFruitore() {
        return indirizzoTelematicoFruitore;
    }

    public void setIndirizzoTelematicoFruitore(String indirizzoTelematicoFruitore) {
        this.indirizzoTelematicoFruitore = indirizzoTelematicoFruitore;
    }

    @Transient
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

    public String getTipoServizioCorrelato() {
        return tipoServizioCorrelato;
    }

    public void setTipoServizioCorrelato(String tipoServizioCorrelato) {
        this.tipoServizioCorrelato = tipoServizioCorrelato;
    }
    
    
    
    @Transient
    public boolean isCorrelato() {
        return correlato;
    }

    public void setCorrelato(boolean correlato) {
        this.correlato = correlato;
    }
    

    @Transient
    public String getPortaApplicativa() {
        return portaApplicativa;
    }

    public void setPortaApplicativa(String portaApplicativa) {
        this.portaApplicativa = portaApplicativa;
    }

    @Transient
    public String getPortaApplicativaChannel() {
        return portaApplicativaChannel;
    }

    public void setPortaApplicativaChannel(String portaApplicativaChannel) {
        this.portaApplicativaChannel = portaApplicativaChannel;
    }

    @Transient
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

    @Transient
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

    public String getTipoMessaggio() {
        return tipoMessaggio;
    }

    public void setTipoMessaggio(String tipoMessaggio) {
        this.tipoMessaggio = tipoMessaggio;
    }

    @OneToMany(mappedBy="messaggio", cascade=CascadeType.ALL)
    public List<Eccezione> getListaEccezioni() {
        return listaEccezioni;
    }

    public void setListaEccezioni(List<Eccezione> listaEccezioni) {
        this.listaEccezioni = listaEccezioni;
    }

    @Transient
    public boolean isNica() {
        return nica;
    }

    public void setNica(boolean nica) {
        this.nica = nica;
    }

    @Transient
    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    @Transient
    public String getMustUnderstand() {
        return mustUnderstand;
    }

    public void setMustUnderstand(String mustUnderstand) {
        this.mustUnderstand = mustUnderstand;
    }

    @Transient
    public List<Trasmissione> getListaTrasmissioni() {
        return listaTrasmissioni;
    }

    public void setListaTrasmissioni(List<Trasmissione> listaTrasmissioni) {
        this.listaTrasmissioni = listaTrasmissioni;
    }

    @Transient
    public List<Riscontro> getListaRiscontri() {
        return listaRiscontri;
    }

    public void setListaRiscontri(List<Riscontro> listaRiscontri) {
        this.listaRiscontri = listaRiscontri;
    }

    @Transient
    public Soggetto getNomeNica() {
        return nomeNica;
    }

    public void setNomeNica(Soggetto nomeNica) {
        this.nomeNica = nomeNica;
    }

    @Transient
    public boolean isMittentiMultipli() {
        return mittentiMultipli;
    }

    public void setMittentiMultipli(boolean mittentiMultipli) {
        this.mittentiMultipli = mittentiMultipli;
    }

    @Transient
    public boolean isMutuaAutenticazione() {
        return mutuaAutenticazione;
    }

    public void setMutuaAutenticazione(boolean mutuaAutenticazione) {
        this.mutuaAutenticazione = mutuaAutenticazione;
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

    @Transient
    public void aggiungiEccezione(String[] descrizione){
        Eccezione e = new Eccezione(descrizione);
        this.getListaEccezioni().add(e);
        e.setMessaggio(this);
    }

    @Transient
    public List<Eccezione> getEccezioniGravi(){
        List<Eccezione> erroriGravi = new ArrayList<Eccezione>();
        for (Eccezione eccezione : listaEccezioni) {
            if(eccezione.getRilevanza().equals(CostantiEccezioni.GRAVE)){
                erroriGravi.add(eccezione);
            }
        }
        return erroriGravi;
    }
    
}
