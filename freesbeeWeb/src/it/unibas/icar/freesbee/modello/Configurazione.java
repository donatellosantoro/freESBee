package it.unibas.icar.freesbee.modello;

import java.io.Serializable;
import java.util.Date;

public class Configurazione implements Serializable{
    
    private int idProgressivo = 1;
    private Date oraUltimoRilascio = new Date();
    
    private long id;
    private String indirizzoPortaDelegata;
    private String indirizzoPortaApplicativa;
    private String tempo;
    private boolean inviaANICA;
    private String connettoreRegistroServizi;
    private long idSoggettoErogatoreRegistroServizi;
    private Soggetto soggettoErogatoreRegistroServizi;
    private boolean NICA;
    private long idSoggettoErogatoreNICA;
    private Soggetto soggettoErogatoreNICA;
    private boolean registroFreesbee;
    private String freesbeeVersion;

    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getConnettoreRegistroServizi() {
        return connettoreRegistroServizi;
    }

    public void setConnettoreRegistroServizi(String connettoreRegistroServizi) {
        this.connettoreRegistroServizi = connettoreRegistroServizi;
    }
    
    public String getIndirizzoPortaApplicativa() {
        return indirizzoPortaApplicativa;
    }

    public void setIndirizzoPortaApplicativa(String indirizzoPortaApplicativa) {
        this.indirizzoPortaApplicativa = indirizzoPortaApplicativa;
    }
    
    public String getIndirizzoPortaDelegata() {
        return indirizzoPortaDelegata;
    }

    public void setIndirizzoPortaDelegata(String indirizzoPortaDelegata) {
        this.indirizzoPortaDelegata = indirizzoPortaDelegata;
    }
    
    public boolean isInviaANICA() {
        return inviaANICA;
    }

    public void setInviaANICA(boolean inviaANICA) {
        this.inviaANICA = inviaANICA;
    }
            
    public boolean isNICA() {
        return NICA;
    }

    public void setNICA(boolean NICA) {
        this.NICA = NICA;
    }
    
    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public Soggetto getSoggettoErogatoreNICA() {
        return soggettoErogatoreNICA;
    }

    public void setSoggettoErogatoreNICA(Soggetto soggettoErogatoreNICA) {
        this.soggettoErogatoreNICA = soggettoErogatoreNICA;
    }

    public Soggetto getSoggettoErogatoreRegistroServizi() {
        return soggettoErogatoreRegistroServizi;
    }

    public void setSoggettoErogatoreRegistroServizi(Soggetto soggettoErogatoreRegistroServizi) {
        this.soggettoErogatoreRegistroServizi = soggettoErogatoreRegistroServizi;
    }

    public long getIdSoggettoErogatoreNICA() {
        return idSoggettoErogatoreNICA;
    }

    public void setIdSoggettoErogatoreNICA(long idSoggettoErogatoreNICA) {
        this.idSoggettoErogatoreNICA = idSoggettoErogatoreNICA;
    }

    public long getIdSoggettoErogatoreRegistroServizi() {
        return idSoggettoErogatoreRegistroServizi;
    }

    public void setIdSoggettoErogatoreRegistroServizi(long idSoggettoErogatoreRegistroServizi) {
        this.idSoggettoErogatoreRegistroServizi = idSoggettoErogatoreRegistroServizi;
    }

    public boolean isRegistroFreesbee() {
        return registroFreesbee;
    }

    public void setRegistroFreesbee(boolean registroFreesbee) {
        this.registroFreesbee = registroFreesbee;
    }

    public String getFreesbeeVersion() {
        return freesbeeVersion;
    }

    public void setFreesbeeVersion(String freesbeeVersion) {
        this.freesbeeVersion = freesbeeVersion;
    }
    
}
