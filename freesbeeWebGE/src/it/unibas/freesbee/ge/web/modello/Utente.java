package it.unibas.freesbee.ge.web.modello;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Utente {
    
    private Log logger = LogFactory.getLog(this.getClass());
    
    private String nomeUtente = "admin";
    private String password = "admin";
    private String ruolo;
    private boolean autenticato;
    
    public static final String AMMINISTRATORE = "amministratore";
    public static final String UTENTE_GENERICO = "utente_generico";
    
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

    public boolean isAutenticato() {
        return autenticato;
    }

    public void setAutenticato(boolean autenticato) {
        this.autenticato = autenticato;
    }
    
    public void verifica(String password){
        String hashPasswordFornita = md5hash(password);
        if (this.getPassword().equals(hashPasswordFornita)) {
            this.setAutenticato(true);
        } else {
            this.setAutenticato(false);
        }
    }

    public String md5hash(String password) {
        String hashString = null;
        try {
            java.security.MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            byte[] hash = digest.digest(password.getBytes());
            hashString = "";
            for (int i = 0; i < hash.length; i++) {
                hashString += Integer.toHexString(
                                  (hash[i] & 0xFF) | 0x100
                              ).toLowerCase().substring(1,3);
            }
        } catch (java.security.NoSuchAlgorithmException e) {
            logger.error(e);
        }
        return hashString;
    }

  
}
