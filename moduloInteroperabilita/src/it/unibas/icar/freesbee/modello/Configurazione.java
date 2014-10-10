package it.unibas.icar.freesbee.modello;

public class Configurazione{
    public static String TEMPO_LOCALE = "EGOV_IT_Locale";
    public static String TEMPO_SINCRONIZZATO = "EGOV_IT_SPC";
    
    private long id;
    private String indirizzoPortaDelegata = "http://localhost:8192/PD/";
    private String indirizzoPortaApplicativa = "http://localhost:8192/PA/";
    private String tempo = TEMPO_LOCALE;
    private boolean inviaANICA;
    private String connettoreRegistroServizi;
    private long idSoggettoErogatoreRegistroServizi;
    private Soggetto soggettoErogatoreRegistroServizi;
    private boolean NICA;
    private long idSoggettoErogatoreNICA;
    private Soggetto soggettoErogatoreNICA;
    

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
    
    public long getIdSoggettoErogatoreRegistroServizi() {
        return idSoggettoErogatoreRegistroServizi;
    }

    public void setIdSoggettoErogatoreRegistroServizi(long idSoggettoErogatoreRegistroServizi) {
        this.idSoggettoErogatoreRegistroServizi = idSoggettoErogatoreRegistroServizi;
    }
    
    public long getIdSoggettoErogatoreNICA() {
        return idSoggettoErogatoreNICA;
    }

    public void setIdSoggettoErogatoreNICA(long idSoggettoErogatoreNICA) {
        this.idSoggettoErogatoreNICA = idSoggettoErogatoreNICA;
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
}
