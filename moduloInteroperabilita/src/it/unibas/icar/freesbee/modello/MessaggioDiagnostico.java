package it.unibas.icar.freesbee.modello;

import java.util.Date;

public class MessaggioDiagnostico {
    private long id;
    private Date gdo;
    private String identificativoPorta;
    private String identificativoFunzione;
    private int severita;
    private String testoDiagnostico;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public Date getGdo() {
        return gdo;
    }

    public void setGdo(Date gdo) {
        this.gdo = gdo;
    }

    public String getIdentificativoFunzione() {
        return identificativoFunzione;
    }

    public void setIdentificativoFunzione(String identificativoFunzione) {
        this.identificativoFunzione = identificativoFunzione;
    }

    public String getIdentificativoPorta() {
        return identificativoPorta;
    }

    public void setIdentificativoPorta(String identificativoPorta) {
        this.identificativoPorta = identificativoPorta;
    }

    public int getSeverita() {
        return severita;
    }

    public void setSeverita(int severita) {
        this.severita = severita;
    }

    public String getTestoDiagnostico() {
        return testoDiagnostico;
    }

    public void setTestoDiagnostico(String testoDiagnostico) {
        this.testoDiagnostico = testoDiagnostico;
    }
    
    
}
