package it.unibas.freesbee.monitoraggio.calcolo.functions.hits;

import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.BasicMetric;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Hits;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Threshold;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Window;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.utils.ThresholdUtils;
import it.unibas.freesbee.monitoraggio.dbwsa.DAOHitsFunction;
import it.unibas.freesbee.monitoraggio.exception.daoexception.DAOException;
import it.unibas.freesbee.monitoraggio.exception.inf2exception.INF2Exception;
import it.unibas.freesbee.monitoraggio.exception.inf2exception.INF2NotDeterminatedAlgorithmException;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;

public class HitsFunction {

    private static Log logger = LogFactory.getLog(HitsFunction.class);
    private BasicMetric basicMetric;
    private Window window;
    private Threshold threshold;
    
    public HitsFunction() {
        
    }
    
    public HitsFunction(Hits hitsFunction) {
        this.basicMetric = hitsFunction.getBasicMetric();
        this.window = hitsFunction.getWindow();
        this.threshold = hitsFunction.getThreshold();
    }
    

    public Double getResultOperation(String bmName, Threshold th, Window win, DateTime dtFinale) throws DAOException {
        return DAOHitsFunction.caricaRisultato(bmName, th, win, dtFinale);
    }

    public Double getResultOperationComplex(List<Double> list, Threshold th) throws INF2Exception {
        if (list.size() != 0 && list != null) {
            double risultato = 0.0D;
            for (double valore : list) {
                if (ThresholdUtils.isRespected(th, valore)) {
                    risultato = risultato + 1.0D;
                }
            }
            return risultato;
        } else {
            throw new INF2Exception(INF2NotDeterminatedAlgorithmException.MSG_ERROR_001);
        }
    }

    public BasicMetric getBasicMetric() {
        return basicMetric;
    }

    public void setBasicMetric(BasicMetric basicMetric) {
        this.basicMetric = basicMetric;
    }

    public Window getWin() {
        return window;
    }

    public void setWin(Window win) {
        this.window = win;
    }

    public Threshold getThreshold() {
        return threshold;
    }

    public void setThreshold(Threshold threshold) {
        this.threshold = threshold;
    }
}
