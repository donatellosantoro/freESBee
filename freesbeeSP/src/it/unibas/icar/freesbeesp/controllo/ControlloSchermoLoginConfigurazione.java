package it.unibas.icar.freesbeesp.controllo;

import it.unibas.icar.freesbeesp.modello.ConfigurazioneStatico;
import it.unibas.icar.freesbeesp.modello.XMLConfigurazione;
import it.unibas.icar.freesbeesp.vista.VistaSchermoLoginConfigurazione;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ControlloSchermoLoginConfigurazione {

    private Log logger = LogFactory.getLog(this.getClass());
    private VistaSchermoLoginConfigurazione vistaSchermoLoginConfigurazione;
    private String messaggio;
    private String errore;

    public String login() {
        XMLConfigurazione xmlConfigurazione = XMLConfigurazione.getInstance();
        String nomeUtente = this.vistaSchermoLoginConfigurazione.getNomeUtente();
        String password = this.vistaSchermoLoginConfigurazione.getPassword();
        if (nomeUtente.equals("")) {
            this.errore = "Il campo Nome Utente e' vuoto !";
            return null;
        } else if (!nomeUtente.equals(xmlConfigurazione.getUtente().getNomeUtente())) {
            this.errore = "Il Nome Utente e' errato !";
            return null;
        }

        if (password.equals("")) {
            this.errore = "Il campo Password e' vuoto !";
            return null;
        } else if (!xmlConfigurazione.getUtente().getPassword().equals(md5hash(password))) {
            this.errore = "La Password e' errata !";
            return null;
        }
        return "configurazione";
    }

    private String md5hash(String password) {
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

    public VistaSchermoLoginConfigurazione getVistaSchermoLoginConfigurazione() {
        return vistaSchermoLoginConfigurazione;
    }

    public void setVistaSchermoLoginConfigurazione(VistaSchermoLoginConfigurazione vistaSchermoLoginConfigurazione) {
        this.vistaSchermoLoginConfigurazione = vistaSchermoLoginConfigurazione;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    public String getErrore() {
        return errore;
    }

    public void setErrore(String errore) {
        this.errore = errore;
    }

    public boolean isVisualizzaMessaggio() {
        return (this.messaggio != null && !this.messaggio.equalsIgnoreCase(""));
    }

    public boolean isVisualizzaErrore() {
        return (this.errore != null && !this.errore.equalsIgnoreCase(""));
    }
}
