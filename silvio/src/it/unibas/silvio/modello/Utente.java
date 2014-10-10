package it.unibas.silvio.modello;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Entity
public class Utente implements Serializable {
    public static final String AMMINISTRATORE = "amministratore";
    public static final String UTENTE_GENERICO = "utente_generico";

    private Log logger = LogFactory.getLog(this.getClass());
    private long id;
    private String nome;
    private String password;
    private String ruolo;
    private String fileXSD;
    private String nomeFileXSD;
    private boolean autenticato;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(nullable = false)
    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public String md5hash(String password) {
        String hashString = null;
        try {
            java.security.MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            byte[] hash = digest.digest(password.getBytes());
            hashString = "";
            for (int i = 0; i < hash.length; i++) {
                hashString += Integer.toHexString(
                        (hash[i] & 0xFF) | 0x100).toLowerCase().substring(1, 3);
            }
        } catch (java.security.NoSuchAlgorithmException e) {
            logger.error(e);
        }
        return hashString;
    }

    @Override
    public String toString() {
        return this.id + "\t" + this.nome + "\t" + this.password + "\t" + this.ruolo;
    }

    @Transient
    public String getFileXSD() {
        return fileXSD;
    }

    public void setFileXSD(String fileXSD) {
        this.fileXSD = fileXSD;
    }

    @Transient
    public String getNomeFileXSD() {
        return nomeFileXSD;
    }

    public void setNomeFileXSD(String nomeFileXSD) {
        this.nomeFileXSD = nomeFileXSD;
    }

    @Transient
    public boolean isAutenticato() {
        return autenticato;
    }

    public void setAutenticato(boolean autenticato) {
        this.autenticato = autenticato;
    }

}
