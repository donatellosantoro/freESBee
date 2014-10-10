package it.unibas.freesbee.monitoraggio.calcolo.windowutils;

import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Window;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;

public class WindowIntervalYear extends WindowUtils {

    private static int numIter = 0;
    private MutableDateTime mdt;

    public WindowIntervalYear(Window window, DateTime dataFinale) {
        super(window, dataFinale);
        this.mdt = dataFinale.toMutableDateTime();
    }

    @Override
    public List<DateTime> getIntervalSixMonths() {
        numIter = GestoreDate.SIXMONTHS_IN_YEAR;
        for (int i = 1; i < numIter; i++) {
            mdt.addMonths(-6);
            arrayDate.add(mdt.toDateTime());
        }
        return arrayDate;
    }

    @Override
    public List<DateTime> getIntervalThreeMonths() {
        numIter = GestoreDate.THREEMONTHS_IN_YEAR;
        for (int i = 1; i < numIter; i++) {
            mdt.addMonths(-3);
            arrayDate.add(mdt.toDateTime());
        }
        return arrayDate;
    }

    @Override
    public List<DateTime> getIntervalMonth() {
        numIter = GestoreDate.MONTHS_IN_YEAR;
        for (int i = 1; i < numIter; i++) {
            mdt.addMonths(-1);
            arrayDate.add(mdt.toDateTime());
        }
        return arrayDate;
    }

    @Override
    public List<DateTime> getIntervalTwoWeeks() {
        numIter = GestoreDate.getTwoWeeksInYear(dataFine.getYear());
        for (int i = 1; i < numIter; i++) {
            mdt.addWeeks(-2);
            arrayDate.add(mdt.toDateTime());
        }
        return arrayDate;
    }

    @Override
    public List<DateTime> getIntervalWeek() {
        numIter = GestoreDate.getWeeksInYear(dataFine.getYear());
        for (int i = 1; i < numIter; i++) {
            mdt.addWeeks(-1);
            arrayDate.add(mdt.toDateTime());
        }
        return arrayDate;
    }

    @Override
    public List<DateTime> getIntervalDay() {
        numIter = GestoreDate.daysToYear(dataFine.getYear());
        for (int i = 1; i < numIter; i++) {
            mdt.addDays(-1);
            arrayDate.add(mdt.toDateTime());
        }
        return arrayDate;
    }

    @Override
    public List<DateTime> getIntervalSixHours() {
        numIter = GestoreDate.getSixHoursInYear(dataFine.getYear());
        for (int i = 1; i < numIter; i++) {
            mdt.addHours(-6);
            arrayDate.add(mdt.toDateTime());
        }
        return arrayDate;
    }

    @Override
    public List<DateTime> getIntervalHour() {
        numIter = GestoreDate.getHoursInYear(dataFine.getYear());
        for (int i = 1; i < numIter; i++) {
            mdt.addHours(-1);
            arrayDate.add(mdt.toDateTime());
        }
        return arrayDate;
    }
}
