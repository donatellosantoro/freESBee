package it.unibas.icar.freesbee.modello;

import it.unibas.icar.freesbee.utilita.FreesbeeUtil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

@Entity
public class Configurazione {

//    private static Log logger = LogFactory.getLog(FreesbeeUtil.class);
    private static final Logger logger = LoggerFactory.getLogger(FreesbeeUtil.class.getName());
    public static String TEMPO_LOCALE = "EGOV_IT_Locale";
    public static String TEMPO_SINCRONIZZATO = "EGOV_IT_SPC";
    private long id;
    private String indirizzoPortaDelegata = "http://localhost:8192/PD/";
    private boolean mutuaAutenticazionePortaDelegata;
    private String indirizzoPortaApplicativa = "http://localhost:8196/PA/";
    private boolean mutuaAutenticazionePortaApplicativa;
    private String tempo = TEMPO_LOCALE;
    private boolean inviaANICA;
    private String connettoreRegistroServizi = "http://localhost:8191/ws/registroServizi";
    private long idSoggettoErogatoreRegistroServizi;
    private Soggetto soggettoErogatoreRegistroServizi;
    private boolean NICA;
    private long idSoggettoErogatoreNICA;
    private boolean registroFreesbee;
    private Soggetto soggettoErogatoreNICA;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column
    public String getConnettoreRegistroServizi() {
        return connettoreRegistroServizi;
    }

    public void setConnettoreRegistroServizi(String connettoreRegistroServizi) {
        this.connettoreRegistroServizi = connettoreRegistroServizi;
    }

    @Transient
    public long getIdSoggettoErogatoreRegistroServizi() {
        return idSoggettoErogatoreRegistroServizi;
    }

    public void setIdSoggettoErogatoreRegistroServizi(long idSoggettoErogatoreRegistroServizi) {
        this.idSoggettoErogatoreRegistroServizi = idSoggettoErogatoreRegistroServizi;
    }

    @Transient
    public long getIdSoggettoErogatoreNICA() {
        return idSoggettoErogatoreNICA;
    }

    public void setIdSoggettoErogatoreNICA(long idSoggettoErogatoreNICA) {
        this.idSoggettoErogatoreNICA = idSoggettoErogatoreNICA;
    }

    @Column(nullable = false)
    public String getIndirizzoPortaApplicativa() {
        return indirizzoPortaApplicativa;
    }

    @Transient
    public List<String> getListaIndirizziPortaApplicativa() {
        List<String> listaIndirizzi = new ArrayList<String>();
        if (indirizzoPortaApplicativa == null) {
            return listaIndirizzi;
        }
        String[] arrayIndirizzi = indirizzoPortaApplicativa.split(";");
        for (int i = 0; i < arrayIndirizzi.length; i++) {
            listaIndirizzi.add(arrayIndirizzi[i].trim());
        }
        return listaIndirizzi;
    }

    public void setIndirizzoPortaApplicativa(String indirizzoPortaApplicativa) {
        this.indirizzoPortaApplicativa = indirizzoPortaApplicativa;
    }

    @Column(nullable = false)
    public boolean isMutuaAutenticazionePortaApplicativa() {
        return mutuaAutenticazionePortaApplicativa;
    }

    public void setMutuaAutenticazionePortaApplicativa(boolean mutuaAutenticazionePortaApplicativa) {
        this.mutuaAutenticazionePortaApplicativa = mutuaAutenticazionePortaApplicativa;
    }

    @Column(nullable = false)
    public String getIndirizzoPortaDelegata() {
        return indirizzoPortaDelegata;
    }

    @Transient
    public List<String> getListaIndirizziPortaDelegata() {
        List<String> listaIndirizzi = new ArrayList<String>();
        if (indirizzoPortaDelegata == null) {
            return listaIndirizzi;
        }
        String[] arrayIndirizzi = indirizzoPortaDelegata.split(";");
        for (int i = 0; i < arrayIndirizzi.length; i++) {
            listaIndirizzi.add(arrayIndirizzi[i].trim());
        }
        return listaIndirizzi;
    }

    public void setIndirizzoPortaDelegata(String indirizzoPortaDelegata) {
        this.indirizzoPortaDelegata = indirizzoPortaDelegata;
    }

    @Column(nullable = false)
    public boolean isMutuaAutenticazionePortaDelegata() {
        return mutuaAutenticazionePortaDelegata;
    }

    public void setMutuaAutenticazionePortaDelegata(boolean mutuaAutenticazionePortaDelegata) {
        this.mutuaAutenticazionePortaDelegata = mutuaAutenticazionePortaDelegata;
    }

    @Column
    public boolean isInviaANICA() {
        return inviaANICA;
    }

    public void setInviaANICA(boolean inviaANICA) {
        this.inviaANICA = inviaANICA;
    }

    @Column
    public boolean isNICA() {
        return NICA;
    }

    public void setNICA(boolean NICA) {
        this.NICA = NICA;
    }

    @Column(nullable = false)
    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    @OneToOne(optional = true)
    @XmlTransient
    public Soggetto getSoggettoErogatoreNICA() {
        return soggettoErogatoreNICA;
    }

    public void setSoggettoErogatoreNICA(Soggetto soggettoErogatoreNICA) {
        this.soggettoErogatoreNICA = soggettoErogatoreNICA;
    }

    @OneToOne(optional = true)
    @XmlTransient
    public Soggetto getSoggettoErogatoreRegistroServizi() {
        return soggettoErogatoreRegistroServizi;
    }

    public void setSoggettoErogatoreRegistroServizi(Soggetto soggettoErogatoreRegistroServizi) {
        this.soggettoErogatoreRegistroServizi = soggettoErogatoreRegistroServizi;
    }

    public boolean isRegistroFreesbee() {
        return registroFreesbee;
    }

    public void setRegistroFreesbee(boolean registroFreesbee) {
        this.registroFreesbee = registroFreesbee;
    }
}
