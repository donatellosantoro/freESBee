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
public class PortType implements Serializable{
   
    public static final String EROGATORE = "EROGATORE";
    public static final String FRUITORE = "FRUITORE";

    private Log logger = LogFactory.getLog(this.getClass());
    private long id;
    private String nome;
    private String ruolo;
    private AccordoDiCollaborazione accordoDiCollaborazione;
    private List<Operation> listaOperation = new ArrayList<Operation>();
    private List<IstanzaPortType> listaIstanzaPortType = new ArrayList<IstanzaPortType>();
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    @Column(nullable=false)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    @Column(nullable=false)
    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }        
    
    @ManyToOne(optional=false)
    public AccordoDiCollaborazione getAccordoDiCollaborazione() {
        return accordoDiCollaborazione;
    }

    public void setAccordoDiCollaborazione(AccordoDiCollaborazione accordoDiCollaborazione) {
        this.accordoDiCollaborazione = accordoDiCollaborazione;
    }
    
    @OneToMany(mappedBy = "portType", cascade = CascadeType.ALL)
    public List<Operation> getListaOperation() {
        return listaOperation;
    }

    public void setListaOperation(List<Operation> listaOperation) {
        this.listaOperation = listaOperation;
    }
    
    @OneToMany(mappedBy = "portType", cascade = CascadeType.ALL)
    public List<IstanzaPortType> getListaIstanzaPortType() {
        return listaIstanzaPortType;
    }

    public void setListaIstanzaPortType(List<IstanzaPortType> listaIstanzaPortType) {
        this.listaIstanzaPortType = listaIstanzaPortType;
    }

    @Override
    public String toString() {
        String s = "\tPortType: " + this.nome + " Ruolo: " + this.ruolo + "\n";
        for (Operation operation : listaOperation) {
            s += operation.toString() + "\n";
        }
        return s;
    }
    
    @Transient
    public boolean isEquals(String nome,String ruolo){
        return this.nome.equalsIgnoreCase(nome) && this.ruolo.equals(ruolo);
    }

    @Transient
    public boolean isLike(String nome,String ruolo){
        return this.nome.startsWith(nome) && this.ruolo.equals(ruolo);
    }


    
    @Transient
    public Operation getOperation(String nome){
        for (Operation operation : listaOperation) {
            if(operation.getNome().equalsIgnoreCase(nome) || operation.getNome().toLowerCase().endsWith(nome.toLowerCase())){
                return operation;
            }
        }
        return null;
    }
}
