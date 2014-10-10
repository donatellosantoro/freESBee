package it.unibas.freesbee.monitoraggio.calcolo.windowutils;

import org.joda.time.DateTime;
import org.joda.time.Days;

public class GestoreDate {
    
    public final static int SIXMONTHS_IN_YEAR = 2;
    public final static int THREEMONTHS_IN_YEAR = 4;
    public final static int MONTHS_IN_YEAR = 12;
    

    public static int daysToYear(int year) {
        DateTime dataInizio = new DateTime(0, 1, 1, 0, 0, 0, 0); //(int year, int month, int day, int ore, int minuti, int secondi, int millisecondi)
        DateTime dataFine = new DateTime(year - 1, 12, 31, 0, 0, 0, 0);
        return giorniTraDate(dataInizio, dataFine);
    }
    
    public static int giorniTraDate(DateTime dataInizio, DateTime dataFine) {
        Days d = Days.daysBetween(dataInizio, dataFine);
        return d.getDays();
    }
    
    public static int getHoursInYear(int year) {
        return GestoreDate.daysToYear(year) * 24;
    }

    public static int getSixHoursInYear(int year) {
        return GestoreDate.daysToYear(year) * 4;
    }

    public static int getWeeksInYear(int year) {
        return GestoreDate.daysToYear(year) / 7;
    }

    public static int getTwoWeeksInYear(int year) {
        return GestoreDate.daysToYear(year) / 14;
    }

    public static DateTime getDataInizio(DateTime dataFine, String valoreIntervallo) {
        if (valoreIntervallo.equalsIgnoreCase("HOUR")) {
            return dataFine.minusHours(1);
        }
        if (valoreIntervallo.equalsIgnoreCase("SIXHOURS")) {
            return dataFine.minusHours(6);
        }
        if (valoreIntervallo.equalsIgnoreCase("DAY")) {
            return dataFine.minusDays(1);
        }
        if (valoreIntervallo.equalsIgnoreCase("WEEK")) {
            return dataFine.minusWeeks(1);
        }
        if (valoreIntervallo.equalsIgnoreCase("TWOWEEKS")) {
            return dataFine.minusWeeks(2);
        }
        if (valoreIntervallo.equalsIgnoreCase("MONTH")) {
            return dataFine.minusMonths(1);
        }
        if (valoreIntervallo.equalsIgnoreCase("THREEMONTHS")) {
            return dataFine.minusMonths(3);
        }
        if (valoreIntervallo.equalsIgnoreCase("SIXMONTHS")) {
            return dataFine.minusMonths(6);
        }
        if (valoreIntervallo.equalsIgnoreCase("YEAR")) {
            return dataFine.minusYears(1);
        }
        return dataFine;
    }
        
}
