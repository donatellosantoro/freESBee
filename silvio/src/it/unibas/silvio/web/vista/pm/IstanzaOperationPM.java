package it.unibas.silvio.web.vista.pm;

import it.unibas.silvio.modello.IstanzaOperation;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.richfaces.model.TreeNode;

public class IstanzaOperationPM implements TreeNode{
    
    private IstanzaOperation istanzaOperation;
    private Log logger = LogFactory.getLog(this.getClass());
    private IstanzaPortTypePM istanzaPortType;

    public IstanzaOperationPM(IstanzaOperation istanzaOperation) {
        this.istanzaOperation = istanzaOperation;
        if(istanzaOperation == null)
            return;        
    }     

    public Object getData() {
        return this;
    }

    public void setData(Object arg0) {
        
    }

    public boolean isLeaf() {
        return true;
    }

    public Iterator getChildren() {
        return new ArrayList().iterator();
    }

    public TreeNode getChild(Object arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void addChild(Object arg0, TreeNode arg1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void removeChild(Object arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public TreeNode getParent() {
        return istanzaPortType;
    }

    public void setParent(TreeNode parent) {
        istanzaPortType = (IstanzaPortTypePM)parent;
    }

    public IstanzaPortTypePM getIstanzaPortType() {
        return istanzaPortType;
    }

    public void setIstanzaPortType(IstanzaPortTypePM istanzaPortType) {
        this.istanzaPortType = istanzaPortType;
    }
    
    public String getType() {
        return "istanzaOperation";
    }
    
    public String getNome(){
        logger.info("ISTANZA OPERATION NOME: " + istanzaOperation.getOperation().getNome());
        return istanzaOperation.getOperation().getNome();
    }
    
    public void setNome(){}
    
    public String getProfilo(){
        logger.info("ISTANZA OPERATION RPOFILO: " + istanzaOperation.getOperation().getProfiloDiCollaborazione());
        return istanzaOperation.getOperation().getProfiloDiCollaborazione();
    }
    
    public void setProfilo(){}
}
