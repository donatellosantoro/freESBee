package it.unibas.silvio.web.vista.pm;

import it.unibas.silvio.modello.Message;
import it.unibas.silvio.modello.Operation;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.richfaces.model.TreeNode;

public class OperationPM implements TreeNode {

    private Log logger = LogFactory.getLog(this.getClass());
    private Map<Object, TreeNode> messages = new HashMap<Object, TreeNode>();
    private Operation operation;
    private PortTypePM portTypePM;

    public OperationPM(Operation operation) {
        this.operation = operation;
        if (operation == null) {
            return;
        }
        Message messageIn = operation.getMessageIn();
        Message messageOut = operation.getMessageOut();
        Message messageFault = operation.getMessageFault();
        if (messageIn != null) {
            MessagePM messagePM = new MessagePM(messageIn, "IN");
            this.addChild(messageIn.getNome(), messagePM);
            logger.debug("Aggiungo un figlio all'operation: " + messageIn.getNome());
        }
        if (messageOut != null) {
            MessagePM messagePM = new MessagePM(messageOut, "OUT");
            this.addChild(messageOut.getNome(), messagePM);
            logger.debug("Aggiungo un figlio all'operation: " + messageOut.getNome());
        }
        if (messageFault != null) {
            MessagePM messagePM = new MessagePM(messageFault, "FAULT");
            this.addChild(messageFault.getNome(), messagePM);
            logger.debug("Aggiungo un figlio all'operation: " + messageFault.getNome());
        }

    }

    public Object getData() {        
        return this;
    }

    public void setData(Object data) {
    }

    public boolean isLeaf() {
        return getMessages().isEmpty();
    }

    public Iterator getChildren() {
        return getMessages().entrySet().iterator();
    }

    public TreeNode getChild(Object id) {
        return (TreeNode) getMessages().get(id);
    }

    public void addChild(Object identifier, TreeNode child) {
        getMessages().put(identifier, child);
        child.setParent(this);
    }

    public void removeChild(Object id) {
        getMessages().remove(id);
    }

    public TreeNode getParent() {
        return portTypePM;
    }

    public void setParent(TreeNode parent) {
        portTypePM = (PortTypePM) parent;
    }

    public Map<Object, TreeNode> getMessages() {
        return messages;
    }

    public void setMessages(Map<Object, TreeNode> messages) {
        this.messages = messages;
    }

    public String getNome() {
        return operation.getNome();
    }

    public void setNome(String nome) {
    }

    public String getProfiloDiCollaborazione() {
        return operation.getProfiloDiCollaborazione();
    }

    public void setProfiloDiCollaborazione(String profilo) {
        this.operation.setProfiloDiCollaborazione(profilo);
    }

    public String getType() {
        return "operation";
    }
}
