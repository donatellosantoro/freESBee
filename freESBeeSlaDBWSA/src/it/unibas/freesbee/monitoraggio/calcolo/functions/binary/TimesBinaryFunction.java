package it.unibas.freesbee.monitoraggio.calcolo.functions.binary;

import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.OperandType;
import it.unibas.freesbee.monitoraggio.exception.inf2exception.INF2Exception;
import java.util.ArrayList;
import java.util.List;

public class TimesBinaryFunction extends BinaryFunction {

    protected TimesBinaryFunction(List<OperandType> listaOperandi) {
        super("*");
        this.listaOperandi = listaOperandi;
    }

    @Override
    public List<Double> getResultComplexOperation(List<Double> list1, List<Double> list2) throws INF2Exception {
        List<Double> listaRisultato = new ArrayList<Double>();
        int size = list1.size();
        if (list1.size() != list2.size()) {
            size = list1.size() <= list2.size() ? size : list2.size();
        }
        for (int i = 0; i < size; i++) {
            listaRisultato.add(list1.get(i) * list2.get(i));
        }
        return listaRisultato;
    }

    @Override
    public List<Double> getResultOperationComplexLongScalar(Long longScalar, List<Double> list) throws INF2Exception {
        List<Double> listaRisultato = new ArrayList<Double>();
        for (double valore : list) {
            listaRisultato.add(longScalar.longValue() * valore);
        }
        return listaRisultato;
    }

    @Override
    public List<Double> getResultOperationComplexLongScalar(List<Double> arraylist, Long long1) throws INF2Exception {
        return this.getResultOperationComplexLongScalar(long1, arraylist);
    }

    @Override
    public List<Double> getResultOperationComplexFloatScalar(Float floatScalar, List<Double> list) throws INF2Exception {
        List<Double> listaRisultato = new ArrayList<Double>();
        for (double valore : list) {
            listaRisultato.add(floatScalar.floatValue() * valore);
        }
        return listaRisultato;
    }

    @Override
    public List<Double> getResultOperationComplexFloatScalar(List<Double> arraylist, Float float1) throws INF2Exception {
        return this.getResultOperationComplexFloatScalar(float1, arraylist);
    }

    @Override
    public List<Double> getResultOperation(double d1, double d2) throws INF2Exception {
        List<Double> listaRisultato = new ArrayList<Double>();
        listaRisultato.add(d1 * d2);
        return listaRisultato;
    }
}
