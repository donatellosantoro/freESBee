package it.unibas.freesbeesla.utilita;

import java.text.ParseException;
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

    private static String trasformaStato(Long stato) {
        if (stato == 0) {
            return "NOT_READY";
        }
        if (stato == 1) {
            return "READY_IDLE";
        }
        if (stato == 2) {
            return "READY_PROCESSING";
        }
        if (stato == 3) {
            return "COMPLETED";
        }
        return "";
    }

    public static Date convertiTimestampToXMLDate(String dataDaFormattare) throws ParseException {
        String data = dataDaFormattare.substring(0, dataDaFormattare.indexOf("T"));
        data = data + " " + dataDaFormattare.substring(dataDaFormattare.indexOf("T") + 1);
        //Attenzione verificare se bisogna mettere HH
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
        Date date = (Date) formatter.parse(data);
        return date;
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
}
