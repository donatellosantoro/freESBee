package it.unibas.freesbee.monitoraggio.calcolo.functions.round;

import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.BasicMetric;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.FunctionType;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Round;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Window;
import it.unibas.freesbee.monitoraggio.dbwsa.DAORoundFunction;
import it.unibas.freesbee.monitoraggio.exception.daoexception.DAOException;
import it.unibas.freesbee.monitoraggio.exception.inf2exception.INF2Exception;
import it.unibas.freesbee.monitoraggio.exception.inf2exception.INF2InvalidAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;

public class RoundFunction {

    private static Log logger = LogFactory.getLog(RoundFunction.class);
    private BasicMetric basicMetric;
    private FunctionType function;
    private long digits;
    
    public RoundFunction(Round roundFunction) {
        this.basicMetric = roundFunction.getBasicMetric();
        this.function = roundFunction.getFunction();
        this.digits = roundFunction.getDigits();
    }

    public List getResultSingleOperation(String bmName, long digits, Window window, DateTime dataFine) throws INF2Exception, DAOException {
        if (window == null) {
            throw new INF2Exception(INF2InvalidAlgorithmException.MSG_ERROR_003);
        }
        return DAORoundFunction.caricaRisultato(bmName, digits, window, dataFine);
    }

    public List getResultOperationComplex(List<Double> list, long digits) {
        List<Double> listaRisultato = new ArrayList<Double>();
        for (double valore : list) {
            listaRisultato.add(getRoundNum(valore, digits));
        }
        return listaRisultato;
    }

    @SuppressWarnings("empty-statement")
    private Double getRoundNum(double value, long decimalPlace) {
        double power_of_ten;
        for (power_of_ten = 1.0D; decimalPlace-- > 0L; power_of_ten *= 10D);
        return new Double((double) Math.round(value * power_of_ten) / power_of_ten);
    }

    public BasicMetric getBasicMetric() {
        return basicMetric;
    }

    public FunctionType getFunction() {
        return function;
    }

    public long getDigits() {
        return digits;
    }
}
