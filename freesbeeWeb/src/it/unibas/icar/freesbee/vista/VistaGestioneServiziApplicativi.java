package it.unibas.icar.freesbee.vista;

import it.unibas.icar.freesbee.modello.ServizioApplicativo;
import it.unibas.icar.freesbee.modello.ServizioApplicativo;
import it.unibas.icar.freesbee.persistenza.IDAOServizioApplicativo;
import java.io.Serializable;
import java.util.List;
import javax.faces.component.UIData;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.richfaces.component.UITabPanel;

public class VistaGestioneServiziApplicativi implements Serializable{

    private Log logger = LogFactory.getLog(this.getClass());
    private ServizioApplicativo nuovoServizioApplicativo = new ServizioApplicativo();
    private List<ServizioApplicativo> listaServiziApplicativi;
    private transient UIData tabellaServiziApplicativi;
    private boolean inserisci;
    private boolean modifica;
    private boolean elimina;
    private boolean pannelloVisibile = true;
    private IDAOServizioApplicativo daoServizioApplicativo;
    private UITabPanel tabPanel;
    private String[] source = new String[]{};
    private String[] target = new String[]{};

    public VistaGestioneServiziApplicativi() {
        this.inserisci = true;
        this.modifica = false;
        this.elimina = false;
    }

    public UIData getTabellaServiziApplicativi() {
        return tabellaServiziApplicativi;
    }

    public void setTabellaServiziApplicativi(UIData tabellaServiziApplicativi) {
        this.tabellaServiziApplicativi = tabellaServiziApplicativi;
    }

    public boolean isInserisci() {
        return inserisci;
    }

    public void setInserisci(boolean inserisci) {
        if (inserisci) {
            setPannelloVisibile(true);
        }
        this.inserisci = inserisci;
    }

    public boolean isModifica() {
        return modifica;
    }

    public void setModifica(boolean modifica) {
        if (modifica) {
        }
        this.modifica = modifica;
    }

    public boolean isElimina() {
        return elimina;
    }

    public void setElimina(boolean elimina) {
        if (elimina) {
            setPannelloVisibile(true);
        }
        this.elimina = elimina;
    }

//    public List<SelectItem> getListaServiziApplicativi() {
//        List<SelectItem> nuovaListaServiziApplicativi = new ArrayList<SelectItem>();
//        try {
//            List<ServizioApplicativo> lista = getDaoServizioApplicativo().findAll();
//            nuovaListaServiziApplicativi.add(new SelectItem(""));
//            Collections.sort(lista);
//            for (ServizioApplicativo servizioApplicativo : lista) {
//                nuovaListaServiziApplicativi.add(new SelectItem(servizioApplicativo.getId(), servizioApplicativo.getNome() + "\\" + servizioApplicativo.getConnettore()));
//            }
//        } catch (DAOException ex) {
//            logger.error("Impossibile leggere l'elenco dei servizi applicativi " + ex);
//        }
//        return nuovaListaServiziApplicativi;
//    }

    public boolean isPannelloVisibile() {
        return pannelloVisibile;
    }

    public void setPannelloVisibile(boolean pannelloVisibile) {
        this.pannelloVisibile = pannelloVisibile;
    }

    public ServizioApplicativo getNuovoServizioApplicativo() {
        return nuovoServizioApplicativo;
    }

    public void setNuovoServizioApplicativo(ServizioApplicativo nuovoServizioApplicativo) {
        this.nuovoServizioApplicativo = nuovoServizioApplicativo;
    }
    
    public String[] getSource() {
        return source;
    }

    public void setSource(String[] source) {
        this.source = source;
    }

    public String[] getTarget() {
        return target;
    }

    public void setTarget(String[] target) {
        this.target = target;
    }

    public UITabPanel getTabPanel() {
        return tabPanel;
    }

    public void setTabPanel(UITabPanel tabPanel) {
        this.tabPanel = tabPanel;
    }

    public List<ServizioApplicativo> getListaServiziApplicativi() {
        return listaServiziApplicativi;
    }

    public void setListaServiziApplicativi(List<ServizioApplicativo> listaServiziApplicativi) {
        this.listaServiziApplicativi = listaServiziApplicativi;
    }

    public IDAOServizioApplicativo getDaoServizioApplicativo() {
        return daoServizioApplicativo;
    }

    public void setDaoServizioApplicativo(IDAOServizioApplicativo daoServizioApplicativo) {
        this.daoServizioApplicativo = daoServizioApplicativo;
    }
}
