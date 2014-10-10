package it.unibas.silvio.web.vista.pm;

import it.unibas.silvio.modello.IstanzaAccordoDiCollaborazione;
import it.unibas.silvio.modello.IstanzaPortType;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.richfaces.model.TreeNode;

public class IstanzaAccordoDiCollaborazionePM implements TreeNode{
    
    private Log logger = LogFactory.getLog(this.getClass());
    private IstanzaAccordoDiCollaborazione istanzaAS;
    private Map<Object, TreeNode> portTypes = new HashMap<Object, TreeNode>();

    public IstanzaAccordoDiCollaborazionePM(IstanzaAccordoDiCollaborazione istanzaAS,String porta) {
        this.istanzaAS = istanzaAS;
        if(istanzaAS == null)
            return;
        List<IstanzaPortType> listaIstanze = istanzaAS.getListaIstanzePortType();
        for (IstanzaPortType istanzaPortType : listaIstanze) {
            IstanzaPortTypePM istanzaPM = new IstanzaPortTypePM(istanzaPortType,porta);
            this.addChild(istanzaPortType.getNome() + "_" + istanzaPortType.getRuolo(), istanzaPM);
        }
    }

    public Object getData() {
        return this;
    }

    public void setData(Object arg0) {       
    }

    public boolean isLeaf() {
        return portTypes.isEmpty();
    }

    public Iterator getChildren() {
        return getPortTypes().entrySet().iterator();
    }

    public TreeNode getChild(Object id) {
        return (TreeNode) getPortTypes().get(id);
    }

    public void addChild(Object identifier, TreeNode child) {
        getPortTypes().put(identifier, child);
        child.setParent(this);
    }

    public void removeChild(Object id) {
       getPortTypes().remove(id);
    }

    public TreeNode getParent() {
        return null;
    }

    public void setParent(TreeNode arg0) {
        
    }
    
    public String getType(){
        return "istanzaAccordoDiCollaborazione";
    }

    public IstanzaAccordoDiCollaborazione getIstanzaAS() {
        return istanzaAS;
    }

    public void setIstanzaAS(IstanzaAccordoDiCollaborazione istanzaAS) {
        this.istanzaAS = istanzaAS;
    }

    public Map<Object, TreeNode> getPortTypes() {
        return portTypes;
    }

    public void setPortTypes(Map<Object, TreeNode> portTypes) {
        this.portTypes = portTypes;
    }
    
    public String getNome(){
        return istanzaAS.getNome();
    }
    
    public String getRuolo(){
        return istanzaAS.getRuolo();
    }
    
    public String getAnteprimaNome(){
        return istanzaAS.getAnteprimaNome();
    }
}
