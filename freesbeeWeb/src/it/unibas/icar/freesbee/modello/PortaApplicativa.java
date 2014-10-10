package it.unibas.icar.freesbee.modello;

import java.io.Serializable;

public class PortaApplicativa implements Serializable,Comparable {

    private Long id;
    private String nome;
    private String descrizione;
    private Long idServizio;
    private Servizio servizio;
    private Long idAzione;
    private Azione azione;
    private Long idServizioApplicativo;
    private ServizioApplicativo servizioApplicativo;
    private String stringaTipoServizio;
    private String stringaServizio;
    private String stringaTipoErogatore;
    private String stringaErogatore;
    private String stringaAzione;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Servizio getServizio() {
        return servizio;
    }

    public void setServizio(Servizio servizio) {
        this.servizio = servizio;
    }

    public Azione getAzione() {
        return azione;
    }

    public void setAzione(Azione azione) {
        this.azione = azione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public ServizioApplicativo getServizioApplicativo() {
        return servizioApplicativo;
    }

    public void setServizioApplicativo(ServizioApplicativo servizioApplicativo) {
        this.servizioApplicativo = servizioApplicativo;
    }
    
    public int compareTo(Object o) {
        return this.getNome().compareToIgnoreCase(((PortaApplicativa)o).getNome());
    }
    
    public Long getIdServizio() {
        return idServizio;
    }

    public void setIdServizio(Long idServizio) {
        this.idServizio = idServizio;
    }  
    
    public Long getIdAzione() {
        return idAzione;
    }

    public void setIdAzione(Long idAzione) {
        this.idAzione = idAzione;
    }
    
    public Long getIdServizioApplicativo() {
        return idServizioApplicativo;
    }

    public void setIdServizioApplicativo(Long idServizioApplicativo) {
        this.idServizioApplicativo = idServizioApplicativo;
    }

    public String getStringaAzione() {
        return stringaAzione;
    }

    public void setStringaAzione(String stringaAzione) {
        this.stringaAzione = stringaAzione;
    }

    public String getStringaServizio() {
        return stringaServizio;
    }

    public void setStringaServizio(String stringaServizio) {
        this.stringaServizio = stringaServizio;
    }

    public String getStringaTipoServizio() {
        return stringaTipoServizio;
    }

    public void setStringaTipoServizio(String stringaTipoServizio) {
        this.stringaTipoServizio = stringaTipoServizio;
    }

    public String getStringaErogatore() {
        return stringaErogatore;
    }

    public void setStringaErogatore(String stringaErogatore) {
        this.stringaErogatore = stringaErogatore;
    }

    public String getStringaTipoErogatore() {
        return stringaTipoErogatore;
    }

    public void setStringaTipoErogatore(String stringaTipoErogatore) {
        this.stringaTipoErogatore = stringaTipoErogatore;
    }
    
}
