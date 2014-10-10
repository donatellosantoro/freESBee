package it.unibas.silvio.web.vista.pm;

import it.unibas.silvio.modello.Message;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.richfaces.model.TreeNode;

public class MessagePM implements TreeNode {

    private Log logger = LogFactory.getLog(this.getClass());
    private Message message;
    private String tipo;
    private OperationPM operationPM;

    public MessagePM(Message message,String tipo) {
        this.message = message;
        this.tipo = tipo;
    }

    public Object getData() {
        return this;
    }

    public void setData(Object data) {
    }

    public boolean isLeaf() {
        return true;
    }

    public Iterator getChildren() {
        return new ArrayList().iterator();
    }

    public TreeNode getChild(Object id) {
        throw new UnsupportedOperationException("Message do not have children");
    }

    public void addChild(Object identifier, TreeNode child) {
        throw new UnsupportedOperationException("Message do not have children");
    }

    public void removeChild(Object id) {
        throw new UnsupportedOperationException("Message do not have children");
    }

    public TreeNode getParent() {
        return operationPM;
    }

    public void setParent(TreeNode parent) {
        operationPM = (OperationPM) parent;
    }

    public String getNome() {
        return message.getNome();
    }

    public void setNome(String nome) {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
    }

    public String getType() {
        return "message";
    }
}
