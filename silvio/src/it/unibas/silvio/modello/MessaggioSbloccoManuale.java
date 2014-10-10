package it.unibas.silvio.modello;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Type;

@Entity
public class MessaggioSbloccoManuale {
    
    private long id;
    private boolean test;
    private String indirizzoRisposta;
    private Messaggio messaggioRichiesta;
    private String messaggio;
    private String istanzaPortType;
    private String idMessaggio;
    private String idMessaggioRelatedTo;
    private String indirizzo;
    private String tipoCorrelazione;
    private IstanzaOperation istanzaOperation;
    
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

    public String getIdMessaggioRelatedTo() {
        return idMessaggioRelatedTo;
    }

    public void setIdMessaggioRelatedTo(String idMessaggioRelatedTo) {
        this.idMessaggioRelatedTo = idMessaggioRelatedTo;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getIndirizzoRisposta() {
        return indirizzoRisposta;
    }

    public void setIndirizzoRisposta(String indirizzoRisposta) {
        this.indirizzoRisposta = indirizzoRisposta;
    }

    public String getIstanzaPortType() {
        return istanzaPortType;
    }

    public void setIstanzaPortType(String istanzaPortType) {
        this.istanzaPortType = istanzaPortType;
    }
    
    @Type(type="text")
    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }
    
    @OneToOne(optional=true)
    public Messaggio getMessaggioRichiesta() {
        return messaggioRichiesta;
    }

    public void setMessaggioRichiesta(Messaggio messaggioRichiesta) {
        this.messaggioRichiesta = messaggioRichiesta;
    }

    public boolean isTest() {
        return test;
    }

    public void setTest(boolean test) {
        this.test = test;
    }

    public String getTipoCorrelazione() {
        return tipoCorrelazione;
    }

    public void setTipoCorrelazione(String tipoCorrelazione) {
        this.tipoCorrelazione = tipoCorrelazione;
    }
    
    @OneToOne(optional=true)
    public IstanzaOperation getIstanzaOperation() {
        return istanzaOperation;
    }

    public void setIstanzaOperation(IstanzaOperation istanzaOperation) {
        this.istanzaOperation = istanzaOperation;
    }
        
}
