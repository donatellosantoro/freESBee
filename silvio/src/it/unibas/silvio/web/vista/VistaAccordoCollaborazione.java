package it.unibas.silvio.web.vista;

import it.unibas.silvio.modello.AccordoDiCollaborazione;
import it.unibas.silvio.web.vista.pm.AccordoDiCollaborazionePM;
import java.io.Serializable;
import java.util.List;
import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.richfaces.component.*;

public class VistaAccordoCollaborazione implements Serializable { 
    
    public static final String oneWSDL = "oneWSDL";
    public static final String twoWSDL = "twoWSDL";
    public static final String accordoServizio = "accordoServizio";
    private int step = 1;    
    private boolean secondoPasso;
    private boolean terzoPasso;    
    private String sceltaInput;
    private String profilo;
    private String cartellaTmp;
    private UploadedFile fileWsdlErogatore;
    private UploadedFile fileWsdlFruitore;
    private UploadedFile fileAS;
    private UploadedFile fileTypes;
    private List<AccordoDiCollaborazione> listaAccordiCollaborazione;    
    private UIDataGrid listaAccordiCollaborazioneGrid;
    private AccordoDiCollaborazione accordoDiCollaborazione;
    private AccordoDiCollaborazione accordoDiCollaborazioneEliminare;
    private List<AccordoDiCollaborazionePM> listaAccordiDiCollaborazionePM;
    private boolean elimina;
    
    
    public Boolean nodoAperto(UITree nodo){
        String tipo = nodo.getNodeFace();        
        return tipo.equals("portType");
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getSceltaInput() {
        return sceltaInput;
    }

    public void setSceltaInput(String sceltaInput) {
        this.sceltaInput = sceltaInput;
    }

    public UploadedFile getFileAS() {
        return fileAS;
    }

    public void setFileAS(UploadedFile fileAS) {
        this.fileAS = fileAS;
    }

    public UploadedFile getFileWsdlErogatore() {
        return fileWsdlErogatore;
    }

    public void setFileWsdlErogatore(UploadedFile fileWsdlErogatore) {
        this.fileWsdlErogatore = fileWsdlErogatore;
    }

    public UploadedFile getFileWsdlFruitore() {
        return fileWsdlFruitore;
    }

    public void setFileWsdlFruitore(UploadedFile fileWsdlFruitore) {
        this.fileWsdlFruitore = fileWsdlFruitore;
    }

    public UploadedFile getFileTypes() {
        return fileTypes;
    }

    public void setFileTypes(UploadedFile fileTypes) {
        this.fileTypes = fileTypes;
    }

    public List<AccordoDiCollaborazione> getListaAccordiCollaborazione() {
        return listaAccordiCollaborazione;
    }

    public void setListaAccordiCollaborazione(List<AccordoDiCollaborazione> listaAccordiCollaborazione) {
        this.listaAccordiCollaborazione = listaAccordiCollaborazione;
    }

    public UIDataGrid getListaAccordiCollaborazioneGrid() {
        return listaAccordiCollaborazioneGrid;
    }

    public void setListaAccordiCollaborazioneGrid(UIDataGrid listaAccordiCollaborazioneGrid) {
        this.listaAccordiCollaborazioneGrid = listaAccordiCollaborazioneGrid;
    }
    

    public AccordoDiCollaborazione getAccordoDiCollaborazione() {
        return accordoDiCollaborazione;
    }

    public void setAccordoDiCollaborazione(AccordoDiCollaborazione accordoDiCollaborazione) {
        this.accordoDiCollaborazione = accordoDiCollaborazione;
    }

    public AccordoDiCollaborazionePM getAccordoDiCollaborazionePM() {
        return new AccordoDiCollaborazionePM(accordoDiCollaborazione);
    }

    public void setAccordoDiCollaborazionePM(AccordoDiCollaborazionePM accordoDiCollaborazione) {
    }

    public List<AccordoDiCollaborazionePM> getListaAccordiDiCollaborazionePM() {
        return listaAccordiDiCollaborazionePM;
    }

    public void setListaAccordiDiCollaborazionePM(List<AccordoDiCollaborazionePM> listaAccordiDiCollaborazionePM) {
        this.listaAccordiDiCollaborazionePM = listaAccordiDiCollaborazionePM;
    }

    public String getCartellaTmp() {
        return cartellaTmp;
    }

    public void setCartellaTmp(String cartellaTmp) {
        this.cartellaTmp = cartellaTmp;
    }

    public boolean isElimina() {
        return elimina;
    }

    public void setElimina(boolean elimina) {
        this.elimina = elimina;
    }

    public AccordoDiCollaborazione getAccordoDiCollaborazioneEliminare() {
        return accordoDiCollaborazioneEliminare;
    }

    public void setAccordoDiCollaborazioneEliminare(AccordoDiCollaborazione accordoDiCollaborazioneEliminare) {
        this.accordoDiCollaborazioneEliminare = accordoDiCollaborazioneEliminare;
    }

    public String getProfilo() {
        if(sceltaInput.equals(oneWSDL)){
            return "Sincrono";
        }
        if(sceltaInput.equals(twoWSDL)){
            return "Asincrono";
        }
        if(sceltaInput.equals(accordoServizio)){
            return "AccordoDiServizio";
        }
        return null;
    }

    public void setProfilo(String profilo) {
        this.profilo = profilo;
    }
    
    public boolean isSecondoPasso() {
        return secondoPasso;
    }

    public void setSecondoPasso(boolean secondoPasso) {
        this.secondoPasso = secondoPasso;
    }

    public boolean isTerzoPasso() {
        return terzoPasso;
    }

    public void setTerzoPasso(boolean terzoPasso) {
        this.terzoPasso = terzoPasso;
    }
    
    
    

}
