package it.unibas.freesbee.monitoraggio.calcolo.windowutils;

import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Window;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;

public class WindowIntervalTwoWeeks extends WindowUtils {
    
    public WindowIntervalTwoWeeks(Window window, DateTime dataFinale){
        super(window, dataFinale);
        this.mdt = dataFinale.toMutableDateTime();
    }
    
    private int numIter = 0;
    private static final int WEEKS_IN_TWOWEEKS = 2;
    private static final int DAYS_IN_TWOWEEKS = 14;
    private static final int SIXHOURS_IN_TWOWEEKS = 56;
    private static final int HOURS_IN_TWOWEEKS = (24*14);
    private MutableDateTime mdt;


    @Override
    public List<DateTime> getIntervalWeek() {
        numIter = WEEKS_IN_TWOWEEKS;
	for (int i=1 ;i<numIter; i++){
            mdt.addWeeks(-1);
	    //calDataFine.add(Calendar.WEEK_OF_YEAR,-1);
	    //dataIniziale = calDataFine.getTime(); 
	    arrayDate.add(mdt.toDateTime());
	}
        return arrayDate;
    }

    @Override
    public List<DateTime> getIntervalDay() {
        numIter = DAYS_IN_TWOWEEKS;
	for (int i=1 ;i<numIter; i++){
	    mdt.addDays(-1);
            //calDataFine.add(Calendar.DAY_OF_YEAR,-1);
	    //dataIniziale = calDataFine.getTime(); 
	    arrayDate.add(mdt.toDateTime());
	}
        return arrayDate;
    }

    @Override
    public List<DateTime> getIntervalSixHours() {
        numIter = SIXHOURS_IN_TWOWEEKS;
	for (int i=1 ;i<numIter; i++){
	    mdt.addHours(-6);
            //calDataFine.add(Calendar.HOUR,-6);
	    //dataIniziale = calDataFine.getTime(); 
	    arrayDate.add(mdt.toDateTime());
	}
        return arrayDate;
    }

    @Override
    public List<DateTime> getIntervalHour() {
        numIter = HOURS_IN_TWOWEEKS;
	for (int i=1 ;i<numIter; i++){
	    mdt.addHours(-1);
            //calDataFine.add(Calendar.HOUR,-1);
	    //dataIniziale = calDataFine.getTime(); 
	    arrayDate.add(mdt.toDateTime());
	}
        return arrayDate;
    }

}
