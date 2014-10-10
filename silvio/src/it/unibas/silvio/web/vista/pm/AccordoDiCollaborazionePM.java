package it.unibas.silvio.web.vista.pm;

import it.unibas.silvio.modello.AccordoDiCollaborazione;
import it.unibas.silvio.modello.PortType;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.richfaces.model.TreeNode;

public class AccordoDiCollaborazionePM implements TreeNode {
    private Log logger = LogFactory.getLog(this.getClass());

    private Map<Object, TreeNode> portTypes = new HashMap<Object, TreeNode>();
    private AccordoDiCollaborazione accordo;

    public AccordoDiCollaborazionePM(AccordoDiCollaborazione accordo) {
        this.accordo = accordo;
        if(accordo==null){
            return;
        }
        List<PortType> listaPortType = accordo.getListaPortType();
        for (PortType portType : listaPortType) {
            PortTypePM ptPM = new PortTypePM(portType);
            this.addChild(portType.getNome() + "_" + portType.getRuolo(), ptPM);
        }
    }

    public Object getData() {        
        return this;
    }

    public void setData(Object data) {
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
    
    public String getNome() {
        return accordo.getNome();
    }

    public void setNome(String nome) {
    }

    public void setParent(TreeNode parent) {
    }

    public Map<Object, TreeNode> getPortTypes() {
        return portTypes;
    }

    public void setPortTypes(Map<Object, TreeNode> portTypes) {
        this.portTypes = portTypes;
    }

    public String getType() {
        return "accordoDiCollaborazione";
    }

    public AccordoDiCollaborazione getAccordo() {
        return accordo;
    }

    public void setAccordo(AccordoDiCollaborazione accordo) {
        this.accordo = accordo;
    }
    
    
}
