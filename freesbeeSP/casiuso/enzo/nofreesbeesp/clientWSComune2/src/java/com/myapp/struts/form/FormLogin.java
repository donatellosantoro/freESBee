package com.myapp.struts.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class FormLogin extends ActionForm {

    private String nomeUtente;
    private String password;

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (nomeUtente == null || nomeUtente.equals("")) {
            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("Il nome utente non pu&ograve; essere vuoto", false));
        }
        if (password == null || password.equals("")) {
            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("Devi fornire una password", false));
        }
        return errors;
    }

    public String getNomeUtente() {
        return nomeUtente;
    }

    public void setNomeUtente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
