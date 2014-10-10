package it.unibas.icar.freesbee.utilita;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilitaDate {

    private static String patternDataEOra = "yyyy-MM-dd'T'HH:mm:ss";
    private static String patternData = "yyyy-MM-dd";
    private static String patternOra = "HH:mm";

    public static String formattaDataEOra(Date data) {
        if (data == null) {
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patternDataEOra);
        return simpleDateFormat.format(data);
    }

    public static String formattaData(Date data) {
        if (data == null) {
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patternData);
        return simpleDateFormat.format(data);
    }

    public static String formattaOra(Date data) {
        if (data == null) {
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patternOra);
        return simpleDateFormat.format(data);
    }

    public static Date parseData(String stringa) throws ParseException {
        if (stringa == null || stringa.isEmpty()) {
            return null;
        }
        Date data = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patternData);
        simpleDateFormat.setLenient(false);
        data = simpleDateFormat.parse(stringa);
        return data;
    }

    public static Date parseDataEOra(String stringa) throws ParseException {
        if (stringa == null || stringa.isEmpty()) {
            return null;
        }
        Date data = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patternDataEOra);
        simpleDateFormat.setLenient(false);
        data = simpleDateFormat.parse(stringa);
        return data;
    }

    public static Date parseOra(String stringa) throws ParseException {
        if (stringa == null || stringa.isEmpty()) {
            return null;
        }
        Date data = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patternOra);
        simpleDateFormat.setLenient(false);
        data = simpleDateFormat.parse(stringa);
        return data;
    }

    public static Date formattaData(String stringa){
        try {
            if (stringa == null || stringa.isEmpty()) {
                return null;
            }
            Date data = null;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patternData);
            data = simpleDateFormat.parse(stringa);
            return data;
        } catch (ParseException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
