package it.unibas.freesbee.monitoraggio.calcolo.functions.binary;

import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.OperandType;
import it.unibas.freesbee.monitoraggio.exception.inf2exception.INF2Exception;
import java.util.ArrayList;
import java.util.List;

public class PlusBinaryFunction extends BinaryFunction {

    protected PlusBinaryFunction(List<OperandType> listaOperandi) {
        super("+");
        this.listaOperandi = listaOperandi;
    }

    @Override
    public List<Double> getResultComplexOperation(List<Double> arraylist1, List<Double> arraylist2) throws INF2Exception {
        List<Double> listaRisultato = new ArrayList<Double>();
        int size = arraylist1.size();
        if (arraylist1.size() != arraylist2.size()) {
            size = arraylist1.size() <= arraylist2.size() ? size : arraylist2.size();
        }
        for (int i = 0; i < size; i++) {
            listaRisultato.add(arraylist1.get(i) + arraylist2.get(i));
        }

        return listaRisultato;
    }

    @Override
    public List<Double> getResultOperationComplexLongScalar(Long longScalar, List<Double> arraylist) throws INF2Exception {
        List<Double> listaRisultato = new ArrayList<Double>();
        for (double valore : arraylist) {
            listaRisultato.add(longScalar.longValue() + valore);
        }
        return listaRisultato;
    }

    @Override
    public List<Double> getResultOperationComplexLongScalar(List<Double> arraylist, Long longScalar) throws INF2Exception {
        return this.getResultOperationComplexLongScalar(longScalar, arraylist);
    }

    @Override
    public List<Double> getResultOperationComplexFloatScalar(Float floatScalar, List<Double> arraylist) throws INF2Exception {
        List<Double> listaRisultato = new ArrayList<Double>();
        for (double valore : arraylist) {
            listaRisultato.add(floatScalar.floatValue() + valore);
        }
        return listaRisultato;
    }

    @Override
    public List<Double> getResultOperationComplexFloatScalar(List<Double> arraylist, Float float1) throws INF2Exception {
        return this.getResultOperationComplexFloatScalar(float1, arraylist);
    }

    @Override
    public List<Double> getResultOperation(double op1, double op2) throws INF2Exception {
        List<Double> listaRisultato = new ArrayList<Double>();
        listaRisultato.add(op1 + op2);
        return listaRisultato;
    }
}
