package it.unibas.icar.freesbeesp.vista;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.model.SelectItem;

public class VistaSchermoLogin implements Serializable {

    private String nomeUtente;
    private String password;
    private String idpSelezionato;
    private List<SelectItem> listaIdP = new ArrayList<SelectItem>();

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNomeUtente() {
        return nomeUtente;
    }

    public void setNomeUtente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
    }

    public String getIdpSelezionato() {
        return idpSelezionato;
    }

    public void setIdpSelezionato(String idpSelezionato) {
        this.idpSelezionato = idpSelezionato;
    }

    

    public void riempiSelectItem(Map<String, String> mappaIdP) {
        listaIdP.clear();
        if (mappaIdP != null) {
            for (String idpAddress : mappaIdP.keySet()) {
                String idpName = mappaIdP.get(idpAddress);
                listaIdP.add(new SelectItem(idpAddress, idpName));
            }
        }
    }

    public List<SelectItem> getListaIdP() {
        return listaIdP;
    }

    public void setListaIdP(List<SelectItem> listaIdP) {
        this.listaIdP = listaIdP;
    }
}
