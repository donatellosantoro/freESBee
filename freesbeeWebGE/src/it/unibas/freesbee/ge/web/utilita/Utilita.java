package it.unibas.freesbee.ge.web.utilita;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Utilita {

    private static Log logger = LogFactory.getLog(Utilita.class);
    private static String patternDataEOra = "yyyy-MM-dd HH:mm";
    private static String patternDataEOraMinutiSecondiMillisecondi = "yyyy-MM-dd HH:mm:ss";

     public static boolean trasformaLongInBoolean(Long valore) {
        if (valore == 0) {
            return false;
        } else {
            return true;
        }
    }

    public static Long trasformaBooleanInLong(boolean valore) {
        if (valore) {
            return new Long(1);
        } else {
            return new Long(0);
        }
    }

      public static XMLGregorianCalendar convertiDateToXMLGregorianCalendar(Date data) throws DatatypeConfigurationException {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(data);
        XMLGregorianCalendar xmlGregorianCalendar = (DatatypeFactory.newInstance()).newXMLGregorianCalendar(gregorianCalendar);
        return xmlGregorianCalendar;
    }

    public static Date convertiXMLGregorianCalendarToDate(XMLGregorianCalendar xmlGregorianCalendar) {
        long dataInMillisecondi = xmlGregorianCalendar.toGregorianCalendar().getTimeInMillis();
        Date data = new Date(dataInMillisecondi);
        return data;
    }

    public static String formattaDataEOra(Date data) {
        if (data == null) {
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patternDataEOra);
        return simpleDateFormat.format(data);
    }

    public static String formattaDataEOraMinutiSecondiMillisecondi(Date data) {
        if (data == null) {
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patternDataEOraMinutiSecondiMillisecondi);
        return simpleDateFormat.format(data);
    }

    public static String costruisciURL(String url, String stringaDaParserizzare) {
        if (url.endsWith("/")) {
            return url + stringaDaParserizzare;
        } else {
            return url + "/" + stringaDaParserizzare;
        }
    }

       public static String convertiSeconditoGiorni(int ss) {

       int giorni = ((ss/60)/60)/24;

       return giorni+"";
       }


}
