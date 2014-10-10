package it.unibas.silvio.modello;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import org.hibernate.annotations.Type;

@Entity
public class Messaggio {

    public static final String INVIATO = "INVIATO";
    public static final String RICEVUTO = "RICEVUTO";
    public static final String ACK = "ACK";
    
    private long id;
    private String idMessaggio;
    private String idRelatesTo;
    private Messaggio messaggioRisposta;
    private Messaggio messaggioRichiesta;
    private String indirizzo;
    private String tipo;
    private String messaggio;
    private String istanzaPortType;
    private Date data = new Date();
    private IstanzaOperation istanzaOperation;

    private String slaMittente;
    private String slaDestinatario;
    private String slaServizio;
    private long slaInizio;

    private String channelFruitore;
    private String channelErogatore;
    private ParametriEseguiIstanza parametriEseguiIstanza;
    private ParametriMessaggioRicevuto parametriMessaggioRicevuto;
    private Dati datiCompletiRichiesta;
    private Dati datiCompletiRisposta;
    private String anteprimaMessaggio;
    private org.w3c.dom.Document bodyMessaggio;
    private boolean fault;

    private List<String> selectAttachment = new ArrayList<String>();
    private Query queryAttachment = new Query();
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long getId() {
        return this.id;
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

    public String getIdRelatesTo() {
        return idRelatesTo;
    }

    public void setIdRelatesTo(String idRelatesTo) {
        this.idRelatesTo = idRelatesTo;
    }

    @OneToOne(optional=true,cascade=CascadeType.PERSIST)
    public Messaggio getMessaggioRisposta() {
        return messaggioRisposta;
    }

    public void setMessaggioRisposta(Messaggio messaggioRisposta) {
        this.messaggioRisposta = messaggioRisposta;
    }
    
    @OneToOne(optional=true,cascade=CascadeType.PERSIST,mappedBy="messaggioRisposta")
    public Messaggio getMessaggioRichiesta() {
        return messaggioRichiesta;
    }

    public void setMessaggioRichiesta(Messaggio messaggioRichiesta) {
        this.messaggioRichiesta = messaggioRichiesta;
    }

    @Type(type="text")
    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }
    
    @Type(type="text")
    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @ManyToOne(optional = false,cascade=CascadeType.REFRESH)
    public IstanzaOperation getIstanzaOperation() {
        return istanzaOperation;
    }

    public void setIstanzaOperation(IstanzaOperation istanzaOperation) {
        this.istanzaOperation = istanzaOperation;
    }

    public String getIstanzaPortType() {
        return istanzaPortType;
    }

    public void setIstanzaPortType(String istanzaPortType) {
        this.istanzaPortType = istanzaPortType;
    }

    @Transient
    public String getChannelErogatore() {
        return channelErogatore;
    }

    public void setChannelErogatore(String channelErogatore) {
        this.channelErogatore = channelErogatore;
    }
    
    @Transient
    public String getChannelFruitore() {
        return channelFruitore;
    }

    public void setChannelFruitore(String channelFruitore) {
        this.channelFruitore = channelFruitore;
    }

    @Transient
    public ParametriEseguiIstanza getParametriEseguiIstanza() {
        return parametriEseguiIstanza;
    }

    public void setParametriEseguiIstanza(ParametriEseguiIstanza parametriEseguiIstanza) {
        this.parametriEseguiIstanza = parametriEseguiIstanza;
    }

    @Transient
    public Dati getDatiCompletiRichiesta() {
        return datiCompletiRichiesta;
    }

    public void setDatiCompletiRichiesta(Dati datiCompletiRichiesta) {
        this.datiCompletiRichiesta = datiCompletiRichiesta;
    }

    @Transient
    public Dati getDatiCompletiRisposta() {
        return datiCompletiRisposta;
    }

    public void setDatiCompletiRisposta(Dati datiCompletiRisposta) {
        this.datiCompletiRisposta = datiCompletiRisposta;
    }

    @Transient
    public ParametriMessaggioRicevuto getParametriMessaggioRicevuto() {
        return parametriMessaggioRicevuto;
    }

    public void setParametriMessaggioRicevuto(ParametriMessaggioRicevuto parametriMessaggioRicevuto) {
        this.parametriMessaggioRicevuto = parametriMessaggioRicevuto;
    }
    
    
    @Transient
    public void creaIdentificatore(){
        this.setIdMessaggio("SILVIO-" + new Date().getTime());
    }

    @Transient
    public String getAnteprimaMessaggio() {
        return this.messaggio.substring(0, 49)+"...";
    }

    public void setAnteprimaMessaggio(String anteprimaMessaggio) {
        this.anteprimaMessaggio = anteprimaMessaggio;
    }

    @Transient
    public org.w3c.dom.Document getBodyMessaggio() {
        return bodyMessaggio;
    }

    public void setBodyMessaggio(org.w3c.dom.Document bodyMessaggio) {
        this.bodyMessaggio = bodyMessaggio;
    }
    
    @Transient
    public boolean isFault() {
        return fault;
    }

    public void setFault(boolean fault) {
        this.fault = fault;
    }

    @Transient
    public String getSlaDestinatario() {
        return slaDestinatario;
    }

    public void setSlaDestinatario(String slaDestinatario) {
        this.slaDestinatario = slaDestinatario;
    }

    @Transient
    public String getSlaMittente() {
        return slaMittente;
    }

    public void setSlaMittente(String slaMittente) {
        this.slaMittente = slaMittente;
    }

    @Transient
    public String getSlaServizio() {
        return slaServizio;
    }

    public void setSlaServizio(String slaServizio) {
        this.slaServizio = slaServizio;
    }
    
    @Transient
    public boolean hasSLAInfo(){
        return slaDestinatario != null && slaMittente != null && slaServizio != null;
    }

    @Transient
    public long getSlaInizio() {
        return slaInizio;
    }

    public void setSlaInizio(long slaInizio) {
        this.slaInizio = slaInizio;
    }

    @Transient
    public List<String> getSelectAttachment() {
        return selectAttachment;
    }

    public void setSelectAttachment(List<String> selectAttachment) {
        this.selectAttachment = selectAttachment;
    }

    @Transient
    public Query getQueryAttachment() {
        return queryAttachment;
    }

    public void setQueryAttachment(Query queryAttachment) {
        this.queryAttachment = queryAttachment;
    }



    
}
