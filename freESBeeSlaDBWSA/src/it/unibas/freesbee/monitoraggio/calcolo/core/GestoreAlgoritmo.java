package it.unibas.freesbee.monitoraggio.calcolo.core;

import it.unibas.freesbee.monitoraggio.calcolo.functions.aggregate.AggregateFunction;
import it.unibas.freesbee.monitoraggio.calcolo.functions.binary.BinaryFunction;
import it.unibas.freesbee.monitoraggio.calcolo.functions.hits.HitsFunction;
import it.unibas.freesbee.monitoraggio.calcolo.functions.round.RoundFunction;
import it.unibas.freesbee.monitoraggio.calcolo.windowutils.*;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.FunctionType;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Hits;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Max;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Mean;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Min;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.OperandType;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Round;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Sum;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Divide;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Plus;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Times;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Minus;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Window;
import it.unibas.freesbee.monitoraggio.exception.daoexception.DAOException;
import it.unibas.freesbee.monitoraggio.exception.inf2exception.INF2Exception;
import it.unibas.freesbee.monitoraggio.exception.inf2exception.INF2InvalidAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;

public class GestoreAlgoritmo {

    private DateTime dataFine;
    private Log logger = LogFactory.getLog(this.getClass());

    public GestoreAlgoritmo(DateTime dataFine) {
        this.dataFine = dataFine;
    }

    public List<Double> calcolaAlgoritmo(FunctionType funRoot, Window winRoot) throws DAOException, INF2Exception {
        logger.debug("Function di tipo : " + funRoot.toString());
        if (isAggregateFunction(funRoot)) {
            return this.algoritmoAggregateFunction(funRoot, winRoot);
        } else if (funRoot instanceof Hits) { 
            return algoritmoHitsFunction(funRoot, winRoot);
        } else if (funRoot instanceof Round) { 
            return algoritmoRoundFunction(funRoot, winRoot);
        } else if (isBinaryFunction(funRoot)) {
            return algoritmoBinaryFunction(funRoot, winRoot);
        }
        throw new INF2Exception(INF2InvalidAlgorithmException.MSG_ERROR_003);
    }

    public List<Double> algoritmoAggregateFunction(FunctionType funRoot, Window winRoot) throws INF2Exception, DAOException {
        AggregateFunction function = AggregateFunction.createAggregateFunction(funRoot);
        List<Double> list = new ArrayList<Double>();
        if (function.getBasicMetric() != null) {
            if (winRoot == null) {
                list.add(function.getResultOperation(function.getBasicMetric().getValue(), function.getWindow(), this.dataFine));
            } else if (winRoot.equals(function.getWindow())) {
                list.add(function.getResultOperation(function.getBasicMetric().getValue(), winRoot, this.dataFine));
            } else {
                List<DateTime> numiteration = getNumIteration(winRoot, function.getWindow(), function.getBasicMetric().getValue());
                for (int i = 0; i < numiteration.size(); i++) {
                    list.add(function.getResultOperation(function.getBasicMetric().getValue(), function.getWindow(), numiteration.get(i)));
                }
            }
            return list;
        } else { //function.getBasicMetric == NULL
            if (winRoot == null) {
                List<Double> list1 = calcolaAlgoritmo(function.getFunction(), function.getWindow()); // Unico caso in cui nella chiamata ricorsiva successiva il valore di winRoot sarà diverso da null
                list.add(function.getResultOperationComplex(list1));
                return list;
            } else { // winRoot diverso da NULL
                List<Double> list1 = calcolaAlgoritmo(function.getFunction(), winRoot);
                list.add(function.getResultOperationComplex(list1));
                return list;
            }
        }
    }

    public List<Double> algoritmoHitsFunction(FunctionType funRoot, Window winRoot) throws DAOException {
        Hits functionType = (Hits) funRoot;
        List<Double> list = new ArrayList<Double>();
        HitsFunction hits = new HitsFunction(functionType);
        if (winRoot == null) {
            list.add(hits.getResultOperation(hits.getBasicMetric().getValue(), hits.getThreshold(), hits.getWin(), this.dataFine));
        } else if (winRoot.equals(hits.getWin())) {
            list.add(hits.getResultOperation(hits.getBasicMetric().getValue(), hits.getThreshold(), winRoot, this.dataFine));
        } else {
            List<DateTime> numiteration = getNumIteration(winRoot, hits.getWin(), hits.getBasicMetric().getValue());
            for (int i = 0; i < numiteration.size(); i++) {
                list.add(hits.getResultOperation(hits.getBasicMetric().getValue(), hits.getThreshold(), hits.getWin(), numiteration.get(i)));
            }
        }
        return list;
    }

    public List<Double> algoritmoRoundFunction(FunctionType funRoot, Window winRoot) throws INF2Exception, DAOException {
        Round functionType = (Round)funRoot;
        RoundFunction round = new RoundFunction(functionType);
        if (round.getBasicMetric() != null) {
            return round.getResultSingleOperation(round.getBasicMetric().getValue(), round.getDigits(), winRoot, this.dataFine);
        } else {
            List<Double> list = calcolaAlgoritmo(round.getFunction(), winRoot);
            return round.getResultOperationComplex(list, round.getDigits());
        }
    }

    public List<Double> algoritmoBinaryFunction(FunctionType funRoot, Window winRoot) throws INF2Exception, DAOException {
        BinaryFunction binaryFunction = BinaryFunction.createBinaryFunction(funRoot);
        List<OperandType> listaOperandi = binaryFunction.getListaOperandi();
        OperandType op1 = listaOperandi.get(0);
        OperandType op2 = listaOperandi.get(1);
        if ((op1.getBasicMetric() != null) && op2.getBasicMetric() != null) {
            return binaryFunction.getResultOperation(op1.getBasicMetric().getValue(), op2.getBasicMetric().getValue(), winRoot, this.dataFine);
        } else {
            if (op1.getFunction() != null) {
                if (op2.getBasicMetric() != null) {
                    List<Double> lstop1 = calcolaAlgoritmo(op1.getFunction(), winRoot);
                    List<Double> lstop2 = binaryFunction.getResultSingleOperation(op2.getBasicMetric().getValue(), winRoot, this.dataFine);
                    return binaryFunction.getResultComplexOperation(lstop1, lstop2);
                } else if (op2.getLongScalar() != null) {
                    List<Double> lstop1 = calcolaAlgoritmo(op1.getFunction(), winRoot);
                    List<Double> listaStep5 = binaryFunction.getResultOperationComplexLongScalar(lstop1, op2.getLongScalar());
                    return listaStep5;
                } else if (op2.getFloatScalar() != null) {
                    List<Double> lstop1 = calcolaAlgoritmo(op1.getFunction(), winRoot);
                    return binaryFunction.getResultOperationComplexFloatScalar(lstop1, op2.getFloatScalar());
                } else {
                    List<Double> lstop1 = calcolaAlgoritmo(op1.getFunction(), winRoot);
                    List<Double> lstop2 = calcolaAlgoritmo(op2.getFunction(), winRoot);
                    List<Double> listaRisultato = binaryFunction.getResultComplexOperation(lstop1, lstop2);
                    return listaRisultato;
                }
            } else if (op1.getLongScalar() != null) {
                if (op2.getBasicMetric() != null) {
                    return binaryFunction.getResultOperationLongScalar(op1.getLongScalar(), op2.getBasicMetric().getValue(), winRoot, this.dataFine);
                } else if (op2.getLongScalar() != null) {
                    return binaryFunction.getResultOperation(op1.getLongScalar().doubleValue(), op2.getLongScalar().doubleValue());
                } else if (op2.getFloatScalar() != null) {
                    return binaryFunction.getResultOperation(op1.getLongScalar().doubleValue(), op2.getFloatScalar().doubleValue());
                } else {
                    List<Double> lstop2 = calcolaAlgoritmo(op2.getFunction(), winRoot);
                    return binaryFunction.getResultOperationComplexLongScalar(op1.getLongScalar(), lstop2);
                }
            } else if (op1.getFloatScalar() != null) {
                if (op2.getBasicMetric() != null) {
                    return binaryFunction.getResultOperationFloatScalar(op1.getFloatScalar(), op2.getBasicMetric().getValue(), winRoot, this.dataFine);
                } else if (op2.getLongScalar() != null) {
                    return binaryFunction.getResultOperation(op1.getFloatScalar().doubleValue(), op2.getLongScalar().doubleValue());
                } else if (op2.getFloatScalar() != null) {
                    return binaryFunction.getResultOperation(op1.getFloatScalar().doubleValue(), op2.getFloatScalar().doubleValue());
                } else {
                    List<Double> lstop2 = calcolaAlgoritmo(op2.getFunction(), winRoot);
                    return binaryFunction.getResultOperationComplexFloatScalar(op1.getFloatScalar(), lstop2);
                }
            } else {
                List<Double> lstop1 = binaryFunction.getResultSingleOperation(op1.getBasicMetric().getValue(), winRoot, this.dataFine);
                if (op2.getLongScalar() != null) {
                    return binaryFunction.getResultOperationComplexLongScalar(op2.getLongScalar(), lstop1);
                } else if (op2.getFloatScalar() != null) {
                    return binaryFunction.getResultOperationComplexFloatScalar(op2.getFloatScalar(), lstop1);
                } else {
                    List<Double> lstop2 = calcolaAlgoritmo(op2.getFunction(), winRoot);
                    return binaryFunction.getResultComplexOperation(lstop1, lstop2);
                }
            }
        }
    }

    public List<DateTime> getNumIteration(Window winRoot, Window win/*function.getWin()*/, String basicMetricName) throws DAOException {
        List<DateTime> arrDate = new ArrayList<DateTime>();
        if (winRoot.getInterval() != null && win.getInterval() != null) { // entrambe le finestre di tipo temporale..

            if (winRoot.getInterval().value().equalsIgnoreCase("YEAR")) {
                WindowUtils utils = new WindowIntervalYear(win, this.dataFine);
                return utils.getArrayDate();
            }
            if (winRoot.getInterval().value().equalsIgnoreCase("SIXMONTHS")) {
                WindowUtils utils = new WindowIntervalSixMonths(win, this.dataFine);
                return utils.getArrayDate();
            }
            if (winRoot.getInterval().value().equalsIgnoreCase("THREEMONTHS")) {
                WindowUtils utils = new WindowIntervalThreeMonths(win, this.dataFine);
                return utils.getArrayDate();
            }
            if (winRoot.getInterval().value().equalsIgnoreCase("MONTH")) {
                WindowUtils utils = new WindowIntervalMonth(win, this.dataFine);
                return utils.getArrayDate();
            }
            if (winRoot.getInterval().value().equalsIgnoreCase("TWOWEEKS")) {
                WindowUtils utils = new WindowIntervalTwoWeeks(win, this.dataFine);
                return utils.getArrayDate();
            }
            if (winRoot.getInterval().value().equalsIgnoreCase("WEEK")) {
                WindowUtils utils = new WindowIntervalWeek(win, this.dataFine);
                return utils.getArrayDate();
            }
            if (winRoot.getInterval().value().equalsIgnoreCase("DAY")) {
                WindowUtils utils = new WindowIntervalDay(win, this.dataFine);
                return utils.getArrayDate();
            }
            if (winRoot.getInterval().value().equalsIgnoreCase("SIXHOURS")) {
                WindowUtils utils = new WindowIntervalSixHours(win, this.dataFine);
                return utils.getArrayDate();
            }
            if (winRoot.getInterval().value().equalsIgnoreCase("HOUR")) {
                WindowUtils utils = new WindowIntervalHour(win, this.dataFine);
                return utils.getArrayDate();
            }
        }
        if (winRoot.getTimes() != null && win.getTimes() != null) { // root e function.getWindow() quantitativa
            return WindowUtils.getWindowTimes(winRoot.getTimes(), win.getTimes(), dataFine, basicMetricName);
        }
        if (winRoot.getInterval() != null && win.getTimes() != null) { // root temporale e function.getWindow() quantitativa
            return WindowUtils.getWindowInterTimes(winRoot.getInterval(), win.getTimes(), dataFine, basicMetricName);
        }
        if (winRoot.getTimes() != null && win.getInterval() != null) { // root quantitativa e function.getWindow() temporale
            return WindowUtils.getWindowTimesInter(winRoot.getTimes(), win.getInterval(), dataFine, basicMetricName);
        }
        return arrDate;
    }

    private boolean isAggregateFunction(FunctionType fun) {
        if (fun instanceof Sum || fun instanceof Max || fun instanceof Min || fun instanceof Mean) {
            return true;
        }
        return false;
    }

    private boolean isBinaryFunction(FunctionType fun) {
        if (fun instanceof Divide || fun instanceof Plus || fun instanceof Times || fun instanceof Minus) {
            return true;
        }
        return false;
    }
}