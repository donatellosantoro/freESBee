package it.unibas.freesbee.monitoraggio.calcolo.functions.aggregate;

import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.BasicMetric;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.FunctionType;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Max;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Mean;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Min;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Sum;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Window;
import it.unibas.freesbee.monitoraggio.dbwsa.DAOAggregateFunction;
import it.unibas.freesbee.monitoraggio.exception.daoexception.DAOException;
import it.unibas.freesbee.monitoraggio.exception.inf2exception.INF2Exception;
import it.unibas.freesbee.monitoraggio.exception.inf2exception.INF2SystemException;
import java.util.List;
import org.joda.time.DateTime;

public abstract class AggregateFunction {

    private String sqlFunction;
    protected BasicMetric basicMetric;
    protected Window window;
    protected FunctionType functionType;

    protected AggregateFunction(String sqlFunction) {
        this.sqlFunction = sqlFunction;
    }
    
    private AggregateFunction() {}
    
    public static AggregateFunction createAggregateFunction(FunctionType functionRoot) throws INF2Exception {
        if(functionRoot instanceof Sum) {
            Sum fun = (Sum)functionRoot;
            return new SumFunction(fun.getFunction(), fun.getWindow(), fun.getBasicMetric());
        }else if(functionRoot instanceof Mean) {
            Mean fun = (Mean)functionRoot;
            return new MeanFunction(fun.getFunction(), fun.getWindow(), fun.getBasicMetric());
        }else if(functionRoot instanceof Max) {
            Max fun = (Max)functionRoot;
            return new MaxFunction(fun.getFunction(), fun.getWindow(), fun.getBasicMetric());
        }else if(functionRoot instanceof Min) {
            Min fun = (Min)functionRoot;
            return new MinFunction(fun.getFunction(), fun.getWindow(), fun.getBasicMetric());
        }
        throw new INF2Exception(INF2SystemException.MSG_ERROR_001);
    }    
        
    public double getResultOperation(String basicMetricName, Window window, DateTime dataFine) throws DAOException, INF2Exception {
        List<Double> listaRisultato = DAOAggregateFunction.caricaRisultato(this, basicMetricName, window, dataFine);
        if (window.getInterval() != null) {
            return this.getResultOperationComplex(listaRisultato);
        }
        return listaRisultato.get(0);
    }

    public abstract double getResultOperationComplex(List<Double> listaRisultato) throws INF2Exception;

    public String getSqlFunction() {
        return sqlFunction;
    }
    
    public BasicMetric getBasicMetric() {
        return basicMetric;
    }

    public void setBasicMetric(BasicMetric basicMetric) {
        this.basicMetric = basicMetric;
    }

    public Window getWindow() {
        return window;
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    public FunctionType getFunction() {
        return functionType;
    }    
        
}
