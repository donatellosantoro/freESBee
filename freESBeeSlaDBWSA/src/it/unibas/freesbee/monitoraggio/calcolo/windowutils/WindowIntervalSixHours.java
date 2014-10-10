package it.unibas.freesbee.monitoraggio.calcolo.windowutils;

import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Window;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;

public class WindowIntervalSixHours extends WindowUtils {
    
    public WindowIntervalSixHours(Window window, DateTime dataFinale) {
        super(window, dataFinale);
        this.mdt = dataFinale.toMutableDateTime();
    }
    
    private int numIter = 0;
    private static final int HOURS_IN_SIXHOURS = 6;
    private MutableDateTime mdt;



    @Override
    public List<DateTime> getIntervalHour() {
    	numIter = HOURS_IN_SIXHOURS;
  	for (int i=1 ;i<numIter; i++){
            mdt.addHours(-1);
	    arrayDate.add(mdt.toDateTime());
	}
        return arrayDate;
    }

}
