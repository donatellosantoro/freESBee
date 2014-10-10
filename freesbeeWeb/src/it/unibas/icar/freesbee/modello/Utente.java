package it.unibas.icar.freesbee.modello;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Utente {

    private Log logger = LogFactory.getLog(this.getClass());
    private String nomeUtente;
    private String password;
    private String ruolo;
    private String server = "http://localhost:8191/ws/";
    private String freesbeeVersion;
    private boolean autenticato;
    private boolean nica;
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

    public void verifica(String password) {
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
                        (hash[i] & 0xFF) | 0x100).toLowerCase().substring(1, 3);
            }
        } catch (java.security.NoSuchAlgorithmException e) {
            logger.error(e);
        }
        return hashString;
    }

    public String getStringaServer() {
        if (server == null || server.trim().isEmpty()) {
            return "";
        }
        String s = server.toLowerCase();
        if (s.startsWith("http://")) {
            s = s.substring("http://".length());
        }
        if (s.startsWith("https://")) {
            s = s.substring("https://".length());
        }
        if (s.contains("/")) {
            s = s.substring(0, s.indexOf("/"));
        }
        if (nica) {
            s = "Nica @ " + s;
        }else{
            s = "PdD @ " + s;
        }
        return s;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public boolean isNica() {
        return nica;
    }

    public void setNica(boolean nica) {
        this.nica = nica;
    }

    public String getFreesbeeVersion() {
        return freesbeeVersion;
    }

    public void setFreesbeeVersion(String freesbeeVersion) {
        this.freesbeeVersion = freesbeeVersion;
    }
    
}
