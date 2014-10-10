package it.unibas.freesbee.monitoraggio.calcolo.core;

public class GuaranteeTermsResult {
    
    public boolean soddisfatto = false;
    private double thresholdValue;
    private String thresholdOperator="";
    private double risultatoFinale;

    public double getThresholdValue() {
        return thresholdValue;
    }

    public void setThresholdValue(double thresholdValue) {
        this.thresholdValue = thresholdValue;
    }

    public String getThresholdOperator() {
        return thresholdOperator;
    }

    public void setThresholdOperator(String thresholtOperator) {
        this.thresholdOperator = thresholtOperator;
    }
    
    public double getRisultatoFinale() {
        return risultatoFinale;
    }

    public void setRisultatoFinale(double risultatoFinale) {
        this.risultatoFinale = risultatoFinale;
    }
    
    
    public boolean isSoddisfatto() {
        soddisfatto = false;
        if (thresholdOperator.equals("Greater")) {
            soddisfatto = risultatoFinale > thresholdValue;
        } else if (thresholdOperator.equals("GreaterEqual")) {
            soddisfatto = risultatoFinale >= thresholdValue;
        } else if (thresholdOperator.equals("Less")) {
            soddisfatto = risultatoFinale < thresholdValue;
        } else if (thresholdOperator.equals("LessEqual")) {
            soddisfatto = risultatoFinale <= thresholdValue;
        } else if (thresholdOperator.equals("Equal")) {
            soddisfatto = risultatoFinale == thresholdValue;
        } else if (thresholdOperator.equals("NotEqual")) {
            soddisfatto = risultatoFinale != thresholdValue;
        }
        return soddisfatto;
    }

}
