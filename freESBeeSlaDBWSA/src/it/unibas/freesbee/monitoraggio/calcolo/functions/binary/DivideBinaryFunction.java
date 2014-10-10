package it.unibas.freesbee.monitoraggio.calcolo.functions.binary;

import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.OperandType;
import it.unibas.freesbee.monitoraggio.exception.inf2exception.INF2Exception;
import it.unibas.freesbee.monitoraggio.exception.inf2exception.INF2NotDeterminatedAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class DivideBinaryFunction extends BinaryFunction {

    protected DivideBinaryFunction(List<OperandType> listaOperandi) {
        super("/");
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
            double valore1 = arraylist1.get(i);
            double valore2 = arraylist2.get(i);
            if (valore2 != 0.0D) {
                listaRisultato.add(valore1 / valore2);
            } else {
                throw new INF2Exception(INF2NotDeterminatedAlgorithmException.MSG_ERROR_002);
            }
        }
        return listaRisultato;
    }

    @Override
    public List<Double> getResultOperationComplexLongScalar(Long longScalar, List<Double> arraylist) throws INF2Exception {
        List<Double> listaRisultato = new ArrayList<Double>();
        for (double valore : arraylist) {
            if (valore != 0.0D) {
                listaRisultato.add(longScalar.longValue() / valore);
            } else {
                throw new INF2Exception(INF2NotDeterminatedAlgorithmException.MSG_ERROR_002);
            }
        }
        return listaRisultato;
    }

    @Override
    public List<Double> getResultOperationComplexLongScalar(List<Double> arraylist, Long longScalar) throws INF2Exception {
        if (longScalar.longValue() != 0L) {
            List<Double> listaRisultato = new ArrayList<Double>();
            for (double valore : arraylist) {
                listaRisultato.add(valore / longScalar.longValue());
            }
            return listaRisultato;
        } else {
            throw new INF2Exception(INF2NotDeterminatedAlgorithmException.MSG_ERROR_002);
        }
    }

    @Override
    public List<Double> getResultOperationComplexFloatScalar(Float floatScalar, List<Double> arraylist) throws INF2Exception {
        List<Double> listaRisultato = new ArrayList<Double>();
        for (double valore : arraylist) {
            if (valore != 0.0D) {
                listaRisultato.add(floatScalar.floatValue() / valore);
            } else {
                throw new INF2Exception(INF2NotDeterminatedAlgorithmException.MSG_ERROR_002);
            }
        }
        return listaRisultato;
    }

    @Override
    public List<Double> getResultOperationComplexFloatScalar(List<Double> arraylist, Float floatScalar) throws INF2Exception {
        if (floatScalar.floatValue() != 0.0F) {
            List<Double> listaRisultato = new ArrayList<Double>();
            for (double valore : arraylist) {
                listaRisultato.add(valore / floatScalar.floatValue());
            }
            return listaRisultato;
        } else {
            throw new INF2Exception(INF2NotDeterminatedAlgorithmException.MSG_ERROR_002);
        }
    }

    @Override
    public List<Double> getResultOperation(double op1, double op2) throws INF2Exception {
        if (op2 != 0.0D) {
            List<Double> listaRisultato = new ArrayList<Double>();
            listaRisultato.add(op1 / op2);
            return listaRisultato;
        } else {
            throw new INF2Exception(INF2NotDeterminatedAlgorithmException.MSG_ERROR_002);
        }
    }
    
}
