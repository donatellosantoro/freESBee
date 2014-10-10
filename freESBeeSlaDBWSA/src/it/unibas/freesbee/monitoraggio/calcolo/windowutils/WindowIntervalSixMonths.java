package it.unibas.freesbee.monitoraggio.calcolo.windowutils;

import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Window;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;

public class WindowIntervalSixMonths extends WindowUtils {

    public WindowIntervalSixMonths(Window window, DateTime dataFinale) {
        super(window, dataFinale);
        this.mdt = dataFinale.toMutableDateTime();
    }
    private int numIter = 0;
    private static final int MONTHS_IN_SIXMONTHS = 6;
    private static final int THREEMONTHS_IN_SIXMONTHS = 2;
    private MutableDateTime mdt;

    @Override
    public List<DateTime> getIntervalThreeMonths() {
        numIter = THREEMONTHS_IN_SIXMONTHS;
        for (int i = 1; i < numIter; i++) {
            mdt.addMonths(-3);
            arrayDate.add(mdt.toDateTime());
        }
        return arrayDate;
    }

    @Override
    public List<DateTime> getIntervalMonth() {
        numIter = MONTHS_IN_SIXMONTHS;
        for (int i = 1; i < numIter; i++) {
            mdt.addMonths(-1);
            arrayDate.add(mdt.toDateTime());
        }
        return arrayDate;
    }

    @Override
    public List<DateTime> getIntervalTwoWeeks() {
        numIter = GestoreDate.getTwoWeeksInYear(dataFine.getYear())/2;
        for (int i = 1; i < numIter; i++) {
            mdt.addWeeks(-2);
            arrayDate.add(mdt.toDateTime());
        }
        return arrayDate;
    }

    @Override
    public List<DateTime> getIntervalWeek() {
        numIter = GestoreDate.getWeeksInYear(dataFine.getYear())/2;
        for (int i = 1; i < numIter; i++) {
            mdt.addWeeks(-1);
            arrayDate.add(mdt.toDateTime());
        }
        return arrayDate;
    }

    @Override
    public List<DateTime> getIntervalDay() {
        numIter = GestoreDate.daysToYear(dataFine.getYear())/2;
        for (int i = 1; i < numIter; i++) {
            mdt.addDays(-1);
            arrayDate.add(mdt.toDateTime());
        }
        return arrayDate;
    }

    @Override
    public List<DateTime> getIntervalSixHours() {
        numIter = GestoreDate.getSixHoursInYear(dataFine.getYear()) / 2;
        for (int i = 1; i < numIter; i++) {
            mdt.addHours(-6);
            arrayDate.add(mdt.toDateTime());
        }
        return arrayDate;
    }

    @Override
    public List<DateTime> getIntervalHour() {
        numIter = GestoreDate.getHoursInYear(dataFine.getYear()) / 2;
        for (int i = 1; i < numIter; i++) {
            mdt.addHours(-1);
            arrayDate.add(mdt.toDateTime());
        }
        return arrayDate;
    }
}
