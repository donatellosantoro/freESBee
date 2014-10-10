package it.unibas.icar.freesbee.modello;

public class Utente {

    private long id;
    private String nomeUtente;
    private String password;
    private String ruolo;
    
    public static final String AMMINISTRATORE = "amministratore";
    public static final String UTENTE_GENERICO = "utente_generico";

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public String getNomeUtente() {
        return nomeUtente;
    }

    public void setNomeUtente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }    
}
