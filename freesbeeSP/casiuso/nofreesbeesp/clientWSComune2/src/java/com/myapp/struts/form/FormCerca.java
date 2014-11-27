package com.myapp.struts.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class FormCerca extends ActionForm {

    private String targa;

    public String freesbeeSPSessionId;

    public String getFreesbeeSPSessionId() {
        return freesbeeSPSessionId;
    }

    public void setFreesbeeSPSessionId(String freesbeeSPSessionId) {
        this.freesbeeSPSessionId = freesbeeSPSessionId;
    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public ActionErrors validate(ActionMapping mapping,
            HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (targa == null || targa.equals("")) {
            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("Il codice fiscale non puo essere vuoto", false));
        }
        return errors;
    }
}
