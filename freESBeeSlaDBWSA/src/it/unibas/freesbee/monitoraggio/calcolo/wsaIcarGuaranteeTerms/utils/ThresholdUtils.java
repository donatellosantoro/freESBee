package it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.utils;

import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Threshold;

public class ThresholdUtils {

    public static String getSQLOperator(Threshold th) {
        String op = th.getOperator().value();
        if (op.equals("Greater")) {
            return ">";
        } else if (op.equals("GreaterEqual")) {
            return ">=";
        } else if (op.equals("Less")) {
            return "<";
        } else if (op.equals("LessEqual")) {
            return "<=";
        } else if (op.equals("Equal")) {
            return "=";
        } else if (op.equals("NotEqual")) {
            return "!=";
        }
        return null;
    }

    public static boolean isRespected(Threshold th, Double val1) {
        String op = th.getOperator().value();
        Double val2 = Double.valueOf(th.getValue());
        if (op.equals("Greater")) {
            if (val1 > val2) {
                return true;
            }
        } else if (op.equals("GreaterEqual")) {
            if (val1 >= val2) {
                return true;
            }
        } else if (op.equals("Less")) {
            if (val1 < val2) {
                return true;
            }
        } else if (op.equals("LessEqual")) {
            if (val1 <= val2) {
                return true;
            }
        } else if (op.equals("Equal")) {
            if (val1 == val2) {
                return true;
            }
        } else if (op.equals("NotEqual")) {
            if (val1 != val2) {
                return true;
            }
        }
        return false;
    }
}
