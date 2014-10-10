package it.unibas.freesbee.monitoraggio.calcolo.windowutils;

import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.IntervalTimeType;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Window;
import it.unibas.freesbee.monitoraggio.dbwsa.DAOWindowUtils;
import it.unibas.freesbee.monitoraggio.exception.daoexception.DAOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;

public class WindowUtils {

    protected Log logger = LogFactory.getLog(WindowUtils.class);
    protected List<DateTime> arrayDate = new ArrayList<DateTime>();
    protected DateTime dataFine = new DateTime();
    protected Window window;
    
    public WindowUtils(Window window, DateTime dataFine) {
        this.window = window;
        this.dataFine = dataFine;
        this.arrayDate.add(dataFine);
    }

    public List<DateTime> getArrayDate() {
        if (window.getInterval().value().equalsIgnoreCase("YEAR")) {
            return this.getIntervalYear();
        }
        if (window.getInterval().value().equalsIgnoreCase("SIXMONTHS")) {
            return this.getIntervalSixMonths();
        }
        if (window.getInterval().value().equalsIgnoreCase("THREEMONTHS")) {
            return this.getIntervalThreeMonths();
        }
        if (window.getInterval().value().equalsIgnoreCase("MONTH")) {
            return this.getIntervalMonth();
        }
        if (window.getInterval().value().equalsIgnoreCase("TWOWEEKS")) {
            return this.getIntervalTwoWeeks();
        }
        if (window.getInterval().value().equalsIgnoreCase("WEEK")) {
            return this.getIntervalWeek();
        }
        if (window.getInterval().value().equalsIgnoreCase("DAY")) {
            return this.getIntervalDay();
        }
        if (window.getInterval().value().equalsIgnoreCase("SIXHOURS")) {
            return this.getIntervalSixHours();
        }
        if (window.getInterval().value().equalsIgnoreCase("HOUR")) {
            return this.getIntervalHour();
        }
        return arrayDate;
    }

    public List<DateTime> getIntervalYear() {
        return arrayDate;
    }

    public List<DateTime> getIntervalSixMonths() {
        return arrayDate;
    }

    public List<DateTime> getIntervalThreeMonths() {
        return arrayDate;
    }

    public List<DateTime> getIntervalMonth() {
        return arrayDate;
    }

    public List<DateTime> getIntervalTwoWeeks() {
        return arrayDate;
    }

    public List<DateTime> getIntervalWeek() {
        return arrayDate;
    }

    public List<DateTime> getIntervalDay() {
        return arrayDate;
    }

    public List<DateTime> getIntervalSixHours() {
        return arrayDate;
    }

    public List<DateTime> getIntervalHour() {
        return arrayDate;
    }

    public static List<DateTime> getWindowTimes(long winRoot, long win, DateTime dataFinale, String basicMetricName) throws DAOException  {
        return DAOWindowUtils.getWindowTimesDAO(winRoot, win, dataFinale, basicMetricName);
    }

    public static List<DateTime> getWindowInterTimes(IntervalTimeType winRootInter, long winTimes, DateTime dataFinale, String basicMetricName) throws DAOException  {
        return DAOWindowUtils.getWindowInterTimesDAO(winRootInter, winTimes, dataFinale, basicMetricName);
    }

    public static List<DateTime> getWindowTimesInter(long winRootTimes, IntervalTimeType winInter, DateTime dataFinale, String basicMetricName) throws DAOException  {
        return DAOWindowUtils.getWindowTimesInterDAO(winRootTimes, winInter, dataFinale, basicMetricName);
    }



}
