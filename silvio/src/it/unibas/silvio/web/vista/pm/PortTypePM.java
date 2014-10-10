package it.unibas.silvio.web.vista.pm;

import it.unibas.silvio.modello.Operation;
import it.unibas.silvio.modello.PortType;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.richfaces.model.TreeNode;

public class PortTypePM implements TreeNode {
    private Log logger = LogFactory.getLog(this.getClass());

    private Map<Object, TreeNode> operations = new HashMap<Object, TreeNode>();
    private PortType portType;
    private AccordoDiCollaborazionePM accordo;

    public PortTypePM(PortType portType) {
        this.portType = portType;
        if(portType==null){
            return;
        }
        List<Operation> listaOperation = portType.getListaOperation();
        for (Operation operation : listaOperation) {            
            OperationPM operationPM = new OperationPM(operation);
            this.addChild(operation.getNome(), operationPM);
            logger.debug("Aggiungo un figlio al port type: " + operation.getNome());
        }
    }

    public Object getData() {
        return this;
    }

    public void setData(Object data) {
    }

    public boolean isLeaf() {
        return operations.isEmpty();
    }

    public Iterator getChildren() {
        return getOperations().entrySet().iterator();
    }

    public TreeNode getChild(Object id) {
        return (TreeNode) getOperations().get(id);
    }

    public void addChild(Object identifier, TreeNode child) {
        getOperations().put(identifier, child);
        child.setParent(this);
    }

    public void removeChild(Object id) {
        getOperations().remove(id);
    }

    public TreeNode getParent() {
        return accordo;
    }

    public void setParent(TreeNode parent) {
        accordo = (AccordoDiCollaborazionePM)parent;
    }

    public Map<Object, TreeNode> getOperations() {
        return operations;
    }

    public void setOperations(Map<Object, TreeNode> operations) {
        this.operations = operations;
    }
    
    public String getNome(){
        return portType.getNome();
    }
    
    public void setNome(String nome){
    }
    
    public String getRuolo(){
        return portType.getRuolo();
    }
    
    public void setRuolo(String ruolo){
    }

    public String getType() {
        return "portType";
    }
}
