package it.unibas.silvio.web.vista.pm;

import it.unibas.silvio.modello.IstanzaOperation;
import it.unibas.silvio.modello.IstanzaPortType;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.richfaces.model.TreeNode;

public class IstanzaPortTypePM implements TreeNode {

    private IstanzaPortType istanzaPortType;
    private Map<Object, TreeNode> operations = new HashMap<Object, TreeNode>();
    private Log logger = LogFactory.getLog(this.getClass());
    private IstanzaAccordoDiCollaborazionePM istanzaAccordoPM;
    private String porta;

    public IstanzaPortTypePM(IstanzaPortType istanzaPortType,String porta) {
        this.istanzaPortType = istanzaPortType;
        this.porta = porta;
        if (istanzaPortType == null) {
            return;
        }
        List<IstanzaOperation> listaIstanza = istanzaPortType.getListaIstanzaOperation();
        for (IstanzaOperation istanzaOperation : listaIstanza) {
            IstanzaOperationPM istanzaOperationPM = new IstanzaOperationPM(istanzaOperation);
            logger.info("Istanza operation: " + istanzaOperation);
            logger.info("Istanza operation getNome: " + istanzaOperation.getNome());
            this.addChild(istanzaOperation.getOperation().getNome(), istanzaOperationPM);
            logger.info("aggiungo l'istanza dell'operation: " + istanzaOperation.getOperation().getNome());
        }        
    }

    public Object getData() {
        return this;
    }

    public void setData(Object arg0) {
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
        return istanzaAccordoPM;
    }

    public void setParent(TreeNode parent) {
        istanzaAccordoPM = (IstanzaAccordoDiCollaborazionePM)parent;
    }

    public IstanzaPortType getIstanzaPortType() {
        return istanzaPortType;
    }

    public void setIstanzaPortType(IstanzaPortType istanzaPortType) {
        this.istanzaPortType = istanzaPortType;
    }

    public Map<Object, TreeNode> getOperations() {
        return operations;
    }

    public void setOperations(Map<Object, TreeNode> operations) {
        this.operations = operations;
    }

    public IstanzaAccordoDiCollaborazionePM getIstanzaAccordoPM() {
        return istanzaAccordoPM;
    }

    public void setIstanzaAccordoPM(IstanzaAccordoDiCollaborazionePM istanzaAccordoPM) {
        this.istanzaAccordoPM = istanzaAccordoPM;
    }
    
    public String getType() {
        return "istanzaPortType";
    }
    
    public String getNome(){
        return istanzaPortType.getNome();
    }
    
    public String getRuolo(){
        return istanzaPortType.getTipo();
    }
    
    public String getIndirizzo(){
        return "http://localhost:" + porta + istanzaPortType.getURLAscolto();
    }
    
    public String getIndirizzoSblocco(){
        return istanzaPortType.getURLInvio();
    }
    
    public void setNome(){}
    
    public void setRuolo(){}
    
    public void setIndirizzoSblocco(String indirizzo){
        this.istanzaPortType.setURLInvio(indirizzo);
    }
}
