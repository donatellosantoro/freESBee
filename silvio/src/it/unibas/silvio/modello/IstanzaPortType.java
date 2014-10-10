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
import javax.persistence.Transient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Entity
public class IstanzaPortType implements Serializable{
    public static final String EROGAZIONE = "EROGAZIONE";
    public static final String FRUIZIONE = "FRUIZIONE";
    
    private long id;
    private String URLInvio;    
    private IstanzaAccordoDiCollaborazione istanzaAccordo;
    private PortType portType;
    private List<IstanzaOperation> listaIstanzaOperation = new ArrayList<IstanzaOperation>();
    private String tipo;
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    @Transient
    public String getURLAscolto() {
        return "/" + istanzaAccordo.getRuolo() + "/" + this.getNome() + "_" + this.getId() + "/";
    }


    public String getURLInvio() {
        return URLInvio;
    }

    public void setURLInvio(String URLInvio) {
        this.URLInvio = URLInvio;
    }
           
    @ManyToOne(optional=false)
    public IstanzaAccordoDiCollaborazione getIstanzaAccordo() {
        return istanzaAccordo;
    }

    public void setIstanzaAccordo(IstanzaAccordoDiCollaborazione istanzaAccordo) {
        this.istanzaAccordo = istanzaAccordo;
    }
    
    @ManyToOne(optional=false)
    public PortType getPortType() {
        return portType;
    }

    public void setPortType(PortType portType) {
        this.portType = portType;
    }
    
    @OneToMany(mappedBy = "istanzaPortType", cascade = CascadeType.ALL, targetEntity = IstanzaOperation.class)
    public List<IstanzaOperation> getListaIstanzaOperation() {
        return listaIstanzaOperation;
    }

    public void setListaIstanzaOperation(List<IstanzaOperation> listaIstanzaOperation) {
        this.listaIstanzaOperation = listaIstanzaOperation;
    }
    
    @Column(nullable=false)
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    @Transient
    public String getNome() {
        if(this.getPortType()==null){
            return "";
        }
        return this.getPortType().getNome();
    }
    
    @Transient
    public String getRuolo() {
        if(this.getPortType()==null){
            return "";
        }
        return this.getPortType().getRuolo();
    }
    
    @Transient
    public boolean isEquals(String nome,String tipo){
        return this.getNome().equalsIgnoreCase(nome) && this.tipo.equals(tipo);
    }

    @Override
    public String toString() {
        String s = "\tIstanza PortType: " + this.getNome() + " Tipo: " + this.tipo + "dell'pt dell'" + this.getRuolo() + "\n";
        for (IstanzaOperation operation : listaIstanzaOperation) {
            s += operation.toString() + "\n";
        }
        return s;
    }
    
    @Transient
    public boolean isErogazione(){
        return this.getTipo().equals(EROGAZIONE);
    }
    
    @Transient
    public boolean isFruizione(){
        return this.getTipo().equals(FRUIZIONE);
    }   
    
    @Transient
    public boolean isErogazioneRisposta(){
        return this.getTipo().equals(EROGAZIONE) && this.getRuolo().equals(PortType.FRUITORE);
    }
    
    @Transient
    public IstanzaOperation getOperation(String nome){
        for (IstanzaOperation istanzaOperation : listaIstanzaOperation) {
            if(istanzaOperation.getOperation().getNome().equalsIgnoreCase(nome)){
                return istanzaOperation;
            }
        }
        return null;
    }
    
    @Transient
    public IstanzaOperation cercaOperationDaMessage(String nome){
        nome = pulisciNS(nome);
        IstanzaOperation istanza = getOperation(nome);
        if(istanza!=null){
            return istanza;
        }
        for (IstanzaOperation istanzaOperation : listaIstanzaOperation) {
            if(istanzaOperation.esisteMessage(nome)){
                return istanzaOperation;
            }
        }
        return null;
    }

    private String pulisciNS(String message) {
        int duePunti = message.indexOf(":");
        if(duePunti==-1){
            return message;
        }
        return message.substring(duePunti+1);
    }
    
}
