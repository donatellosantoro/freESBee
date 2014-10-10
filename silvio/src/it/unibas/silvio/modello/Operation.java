package it.unibas.silvio.modello;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class Operation implements Serializable {

    public static final String SINCRONO = "SINCRONO";
    public static final String ASINCRONO = "ASINCRONO";
    public static final String ONE_WAY = "ONE_WAY";
    private long id;
    private String nome;
    private String profiloDiCollaborazione;
    private PortType portType;
    private Message messageIn;
    private Message messageOut;
    private Message messageFault;
    private String anteprimaNome;
    private List<IstanzaOperation> listaIstanzaOperation = new ArrayList<IstanzaOperation>();

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(nullable = false)
    public String getProfiloDiCollaborazione() {
        return profiloDiCollaborazione;
    }

    public void setProfiloDiCollaborazione(String profiloDiCollaborazione) {
        this.profiloDiCollaborazione = profiloDiCollaborazione;
    }

    @ManyToOne(optional = false)
    public PortType getPortType() {
        return portType;
    }

    public void setPortType(PortType portType) {
        this.portType = portType;
    }

    @OneToOne(cascade = CascadeType.ALL, optional = true)
    public Message getMessageIn() {
        return messageIn;
    }

    public void setMessageIn(Message messageIn) {
        this.messageIn = messageIn;
    }

    @OneToOne(cascade = CascadeType.ALL, optional = true)
    public Message getMessageOut() {
        return messageOut;
    }

    public void setMessageOut(Message messageOut) {
        this.messageOut = messageOut;
    }

    @OneToOne(cascade = CascadeType.ALL, optional = true)
    public Message getMessageFault() {
        return messageFault;
    }

    public void setMessageFault(Message messageFault) {
        this.messageFault = messageFault;
    }

    @OneToMany(mappedBy = "operation", cascade = CascadeType.ALL, targetEntity = IstanzaOperation.class)
    public List<IstanzaOperation> getListaIstanzaOperation() {
        return listaIstanzaOperation;
    }

    public void setListaIstanzaOperation(List<IstanzaOperation> listaIstanzaOperation) {
        this.listaIstanzaOperation = listaIstanzaOperation;
    }

    @Override
    public String toString() {
        String s = "\t\tOperation: " + this.nome + " ProfiloCollaborazione: " + this.profiloDiCollaborazione + "\n";
        s += "\t\tIn: " + getMessageIn() + "\n";
        s += "\t\tOut: " + getMessageOut() + "\n";
        s += "\t\tFault: " + getMessageFault() + "\n";
        return s;
    }

    @Transient
    public String getAnteprimaNome() {
        if (nome.length() > 40) {
            return this.nome.substring(0, 30) + "...";
        }
        return this.nome;
    }
    
    public void setAnteprimaNome(String anteprimaNome){
        this.anteprimaNome = anteprimaNome;
    }

    @Transient
    public boolean isOneWay() {
        return this.profiloDiCollaborazione.equals(ONE_WAY);
    }

    @Transient
    public boolean isSincrono() {
        return this.profiloDiCollaborazione.equals(SINCRONO);
    }

    @Transient
    public boolean isAsincrono() {
        return this.profiloDiCollaborazione.equals(ASINCRONO);
    }
}
