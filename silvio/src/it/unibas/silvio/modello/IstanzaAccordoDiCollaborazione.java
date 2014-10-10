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
public class IstanzaAccordoDiCollaborazione implements Serializable {
    public static final String CARTELLA_FILE = "ISTANZE";
    private Log logger = LogFactory.getLog(this.getClass());
    
    private long id;
    private String nome;
    private String ruolo;
    private String anteprimaNome;
    private List<IstanzaPortType> listaIstanzePortType = new ArrayList<IstanzaPortType>();
    private AccordoDiCollaborazione accordo = new AccordoDiCollaborazione();
    private String cartellaFiles;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(nullable = false, unique = true)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    @OneToMany(mappedBy = "istanzaAccordo", cascade = CascadeType.ALL, targetEntity = IstanzaPortType.class)
    public List<IstanzaPortType> getListaIstanzePortType() {
        return listaIstanzePortType;
    }

    public void setListaIstanzePortType(List<IstanzaPortType> listaIstanzePortType) {
        this.listaIstanzePortType = listaIstanzePortType;
    }

    @ManyToOne(optional = false)
    public AccordoDiCollaborazione getAccordo() {
        return accordo;
    }

    public void setAccordo(AccordoDiCollaborazione accordo) {
        this.accordo = accordo;
    }

    public String getCartellaFiles() {
        return cartellaFiles;
    }

    public void setCartellaFiles(String cartellaFiles) {
        this.cartellaFiles = cartellaFiles;
    }

    @Transient
    public IstanzaPortType getOrCreaIstanzaPortType(String nome, String ruolo, String tipo) {
        for (IstanzaPortType istanzaPortType : listaIstanzePortType) {
            if (istanzaPortType.isEquals(nome, tipo)) {
                return istanzaPortType;
            }
        }
        IstanzaPortType istanzaPT = new IstanzaPortType();
        PortType pt = this.accordo.getPortType(nome, ruolo);
        if (pt == null) {
            throw new IllegalStateException("Impossibile creare l'istanza. Non esiste nessun port type " + nome + " del " + ruolo);
        }
        istanzaPT.setPortType(pt);
        istanzaPT.setIstanzaAccordo(this);
        istanzaPT.setTipo(tipo);
        this.getListaIstanzePortType().add(istanzaPT);
        logger.info("Creata un'istanza port type di " + istanzaPT.getTipo() + " per il porttype " + istanzaPT.getNome() + " del " + istanzaPT.getRuolo());
        return istanzaPT;
    }
    
    @Transient
    public String getAnteprimaNome() {
        if(this.nome.length() > 35){
            return this.nome.substring(0, 35) +"...";
        }
        return this.nome;
    }

    public void setAnteprimaNome(String anteprimaNome) {
        this.anteprimaNome = anteprimaNome;
    }

    @Override
    public String toString() {
        String s = "\nIstanza dell'accordo: " + this.accordo.getNome() + "\n";
        for (IstanzaPortType portType : listaIstanzePortType) {
            s += portType.toString() + "\n";
        }
        return s;
    }
}
