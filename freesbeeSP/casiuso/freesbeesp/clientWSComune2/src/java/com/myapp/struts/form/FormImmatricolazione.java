package com.myapp.struts.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import utility.Utilita;

public class FormImmatricolazione extends ActionForm {

    private String codiceFiscale;
    private String targa;
    private String telaio;
    private String modello;
    private String cavalli;
    private String kw;
    private String classe;
    private String alimentazione;
    private String posti;
    private String uso;
    public String freesbeeSPSessionId;

    
    public String[] getClassi(){
    return Utilita.CLASSI;
    }
    
     public String[] getAlimentazioni(){
    return Utilita.ALIMENTAZIONI;
    }
     
      public String[] getPostiDisponibili(){
    return Utilita.POSTI;
    }
    
    public String[] getUsi(){
    return Utilita.USI;
    }
      
    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }


    public void setCodiceFiscale(String codicefiscale) {
        this.codiceFiscale = codicefiscale;
    }

    public String getFreesbeeSPSessionId() {
        return freesbeeSPSessionId;
    }

    public void setFreesbeeSPSessionId(String freesbeeSPSessionId) {
        this.freesbeeSPSessionId = freesbeeSPSessionId;
    }

    public String getAlimentazione() {
        return alimentazione;
    }

    public void setAlimentazione(String alimentazione) {
        this.alimentazione = alimentazione;
    }

    public String getCavalli() {
        return cavalli;
    }

    public void setCavalli(String cavalli) {
        this.cavalli = cavalli;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getKw() {
        return kw;
    }

    public void setKw(String kw) {
        this.kw = kw;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public String getPosti() {
        return posti;
    }

    public void setPosti(String posti) {
        this.posti = posti;
    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public String getTelaio() {
        return telaio;
    }

    public void setTelaio(String telaio) {
        this.telaio = telaio;
    }

    public ActionErrors validate(ActionMapping mapping,
            HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (codiceFiscale == null || codiceFiscale.equals("")) {
            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("Il campo codice fiscale non puo essere vuoto", false));
        }
        if (targa == null || targa.equals("")) {
            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("Il campo targa non puo essere vuoto", false));
        }
        if (telaio == null || telaio.equals("")) {
            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("Il campo telaio non puo essere vuoto", false));
        }
        if (modello == null || modello.equals("")) {
            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("Il campo modello non puo essere vuoto", false));
        }
        try {
            Integer.parseInt(cavalli);
        } catch (NumberFormatException nfe) {
            errors.add(errors.GLOBAL_MESSAGE, new ActionMessage("Il  campo cavalli deve essere un numero ", false));
        }
        try {
            Double.parseDouble(kw);
        } catch (NumberFormatException nfe) {
            errors.add(errors.GLOBAL_MESSAGE, new ActionMessage("Il  campo Kw deve essere un numero", false));
        }
        return errors;
    }
}
