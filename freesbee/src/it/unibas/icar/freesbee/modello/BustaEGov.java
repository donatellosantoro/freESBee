package it.unibas.icar.freesbee.modello;

import it.unibas.icar.freesbee.inoltrobustaegov.HttpInoltroBustaEGov;
import it.unibas.icar.freesbee.utilita.UtilitaDate;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

public class BustaEGov {

//    private static Log logger = LogFactory.getLog(BustaEGov.class);
    private static final Logger logger = LoggerFactory.getLogger(BustaEGov.class.getName());
    private static BustaEGov singleton = new BustaEGov();
    private static int idProgressivo = 0;
    private String stringaUltimoRilascio = "";

    private BustaEGov() {
    }

    public static synchronized BustaEGov getInstance() {
        return singleton;
    }

    public synchronized String getNuovoIdentificatore(String soggetto) {
        Date oraCorrente = new Date();
        String stringaCorrente = UtilitaDate.formattaData(oraCorrente) + "_" + UtilitaDate.formattaOra(oraCorrente);
        if (!stringaCorrente.equals(stringaUltimoRilascio)) {
            setIdProgressivo(0);
            setStringaUltimoRilascio(stringaCorrente);
        }
        DecimalFormat df = new DecimalFormat("0000000");
        setIdProgressivo(getIdProgressivo() + 1);
        String stringaProgressivo = df.format(getIdProgressivo());
        String identificatore = soggetto + "_" + soggetto + "SPCoopIT_" + stringaProgressivo + "_" + stringaCorrente;
        return identificatore;
    }

    public synchronized int getIdProgressivo() {
        return idProgressivo;
    }

    public synchronized void setIdProgressivo(int id) {
        idProgressivo = id;
    }

    public synchronized String getStringaUltimoRilascio() {
        return stringaUltimoRilascio;
    }

    public synchronized void setStringaUltimoRilascio(String stringaUltimoRilascio) {
        this.stringaUltimoRilascio = stringaUltimoRilascio;
    }
    
}
