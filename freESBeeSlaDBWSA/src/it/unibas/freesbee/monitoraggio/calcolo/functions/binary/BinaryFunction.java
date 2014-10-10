package it.unibas.freesbee.monitoraggio.calcolo.functions.binary;

import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Divide;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.FunctionType;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Minus;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.OperandType;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Plus;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Times;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Window;
import it.unibas.freesbee.monitoraggio.dbwsa.DAOBinaryFunction;
import it.unibas.freesbee.monitoraggio.exception.daoexception.DAOException;
import it.unibas.freesbee.monitoraggio.exception.inf2exception.INF2Exception;
import it.unibas.freesbee.monitoraggio.exception.inf2exception.INF2SystemException;
import java.util.List;
import org.joda.time.DateTime;

public abstract class BinaryFunction {

    private String sqlFunction;
    protected List<OperandType> listaOperandi;

    protected BinaryFunction(String sqlFunction) {
        this.sqlFunction = sqlFunction;
    }
    
    private BinaryFunction() {}
    
    public static BinaryFunction createBinaryFunction(FunctionType functionRoot) throws INF2Exception {
        if(functionRoot instanceof Divide) {
            Divide divideFunction = (Divide)functionRoot;
            return new DivideBinaryFunction(divideFunction.getOperand());
        }else if(functionRoot instanceof Times) {
            Times timesFunction = (Times)functionRoot;
            return new TimesBinaryFunction(timesFunction.getOperand());
        }else if(functionRoot instanceof Plus) {
            Plus plusFunction = (Plus)functionRoot;
            return new PlusBinaryFunction(plusFunction.getOperand());
        }else if(functionRoot instanceof Minus) {
            Minus minusFunction = (Minus)functionRoot;
            return new MinusBinaryFunction(minusFunction.getOperand());
        }
        throw new INF2Exception(INF2SystemException.MSG_ERROR_001);
    }    

    public List<Double> getResultOperation(String basicMetric1, String basicMetric2, Window window, DateTime dataFine) throws DAOException {
        return DAOBinaryFunction.caricaRisultato(this, window, basicMetric1, basicMetric2, dataFine);
    }

    public List<Double> getResultSingleOperation(String bmName, Window window, DateTime dataFine) throws DAOException {
        return DAOBinaryFunction.caricaRisultato(bmName, window, dataFine);
    }

    public List<Double> getResultOperationLongScalar(Long longScalar, String bmName2, Window window, DateTime dataFine) throws DAOException {
        boolean valoreAlDenominatore = false;
        return DAOBinaryFunction.caricaRisultato(this, longScalar, bmName2, window, valoreAlDenominatore, dataFine);
    }

    public List<Double> getResultOperationLongScalar(String bmName1, Long longScalar, Window window, DateTime dataFine) throws DAOException {
        boolean valoreAlDenominatore = true;
        return DAOBinaryFunction.caricaRisultato(this, longScalar, bmName1, window, valoreAlDenominatore, dataFine);
    }

    public List<Double> getResultOperationFloatScalar(Float floatScalar, String bmName2, Window window, DateTime dataFine) throws DAOException {
        boolean valoreAlDenominatore = false;
        return DAOBinaryFunction.caricaRisultato(this, floatScalar, bmName2, window, valoreAlDenominatore, dataFine);
    }

    public List<Double> getResultOperationFloatScalar(String bmName1, Float floatScalar, Window window, DateTime dataFine) throws DAOException {
        boolean valoreAlDenominatore = true;
        return DAOBinaryFunction.caricaRisultato(this, floatScalar, bmName1, window, valoreAlDenominatore, dataFine);
    }

    public abstract List<Double> getResultComplexOperation(List<Double> arraylist, List<Double> arraylist1) throws INF2Exception;

    public abstract List<Double> getResultOperationComplexLongScalar(Long long1, List<Double> arraylist) throws INF2Exception;

    public abstract List<Double> getResultOperationComplexLongScalar(List<Double> arraylist, Long long1) throws INF2Exception;

    public abstract List<Double> getResultOperationComplexFloatScalar(Float float1, List<Double> arraylist) throws INF2Exception;

    public abstract List<Double> getResultOperationComplexFloatScalar(List<Double> arraylist, Float float1) throws INF2Exception;

    public abstract List<Double> getResultOperation(double d, double d1) throws INF2Exception;
    

    public String getSqlFunction() {
        return sqlFunction;
    }

    public List<OperandType> getListaOperandi() {
        return listaOperandi;
    }

    public void setListaOperandi(List<OperandType> listaOperandi) {
        this.listaOperandi = listaOperandi;
    }
}
