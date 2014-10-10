package it.unibas.freesbee.monitoraggio.calcolo.windowutils;

import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Window;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;

public class WindowIntervalDay extends WindowUtils {
    
    public WindowIntervalDay(Window window, DateTime dataFinale) {
        super(window, dataFinale);
        this.mtd = dataFinale.toMutableDateTime();
    }

    private static int numIter = 0;
    private static final int SIXHOURS_IN_DAY = 4;
    private static final int HOURS_IN_DAY = 24;
    private MutableDateTime mtd;

    @Override
    public List<DateTime> getIntervalSixHours() {
        numIter = SIXHOURS_IN_DAY;
	for (int i=1 ;i<numIter; i++){
            mtd.addHours(-6);
	    arrayDate.add(mtd.toDateTime());
	}
        return arrayDate;
    }

    @Override
    public List<DateTime> getIntervalHour() {
	numIter = HOURS_IN_DAY;
	for (int i=1 ;i<numIter; i++){
            mtd.addHours(-1);
            arrayDate.add(mtd.toDateTime());
	}
        return arrayDate;
    }
    

}
