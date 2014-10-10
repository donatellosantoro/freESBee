package it.unibas.freesbee.monitoraggio.calcolo.windowutils;

import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Window;
import java.util.List;
import org.joda.time.DateTime;

public class WindowIntervalHour extends WindowUtils {
    
    public WindowIntervalHour(Window window, DateTime dataFinale) {
        super(window, dataFinale);
    }

    @Override
    public List<DateTime> getIntervalHour() {
        return arrayDate;
    }

}
