package it.unibas.freesbee.monitoraggio.calcolo.windowutils;

import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Window;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;

public class WindowIntervalWeek extends WindowUtils {
    
    public WindowIntervalWeek(Window window, DateTime dataFinale) {
        super(window, dataFinale);
        this.mdt = dataFinale.toMutableDateTime();
    }
    
    private static int numIter = 0;
    private static final int DAY_IN_WEEK = 7;
    private static final int SIXHOURS_IN_WEEK = 28;
    private static final int HOURS_IN_WEEK = (24*7);
    private MutableDateTime mdt;
    
    @Override
    public List<DateTime> getIntervalDay() {
        numIter = DAY_IN_WEEK;
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
        numIter = SIXHOURS_IN_WEEK; 
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
        numIter = HOURS_IN_WEEK;
	for (int i=1 ;i<numIter; i++){
            mdt.addHours(-1);
	    //calDataFine.add(Calendar.HOUR,-1);
	    //dataIniziale = calDataFine.getTime(); 
	    arrayDate.add(mdt.toDateTime());
	}
        return arrayDate;
    }    

}
