package it.unibas.silvio.modello;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class AccordoDiCollaborazione implements Serializable {

    private org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog(this.getClass());

    public static final String CARTELLA_FILE = "ACCORDO";
    private long id;
    private String nome;
    private String WSDLErogatore;
    private String WSDLFruitore;
    private String fileTypes;
    private String cartellaFiles;
    private List<PortType> listaPortType = new ArrayList<PortType>();
    private List<IstanzaAccordoDiCollaborazione> listaIstanzeAccordi = new ArrayList<IstanzaAccordoDiCollaborazione>();
    private Map<String, String> profiloOperation = new HashMap<String, String>();

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

    public String getWSDLErogatore() {
        return WSDLErogatore;
    }

    public void setWSDLErogatore(String WSDLErogatore) {
        this.WSDLErogatore = WSDLErogatore;
    }

    public String getWSDLFruitore() {
        return WSDLFruitore;
    }

    public void setWSDLFruitore(String WSDLFruitore) {
        this.WSDLFruitore = WSDLFruitore;
    }

    public String getFileTypes() {
        return fileTypes;
    }

    public void setFileTypes(String fileTypes) {
        this.fileTypes = fileTypes;
    }

    public String getCartellaFiles() {
        return cartellaFiles;
    }

    public void setCartellaFiles(String cartellaFiles) {
        this.cartellaFiles = cartellaFiles;
    }

    @OneToMany(mappedBy = "accordoDiCollaborazione", cascade = CascadeType.ALL)
    public List<PortType> getListaPortType() {
        return listaPortType;
    }

    public void setListaPortType(List<PortType> listaPortType) {
        this.listaPortType = listaPortType;
    }

    @OneToMany(mappedBy = "accordo", cascade = CascadeType.ALL, targetEntity = IstanzaAccordoDiCollaborazione.class)
    public List<IstanzaAccordoDiCollaborazione> getListaIstanzeAccordi() {
        return listaIstanzeAccordi;
    }

    public void setListaIstanzeAccordi(List<IstanzaAccordoDiCollaborazione> listaIstanzeAccordi) {
        this.listaIstanzeAccordi = listaIstanzeAccordi;
    }

    @Transient
    public Map<String, String> getProfiloOperation() {
        return profiloOperation;
    }

    public void setProfiloOperation(Map<String, String> profiloOperation) {
        this.profiloOperation = profiloOperation;
    }

    @Override
    public String toString() {
        String s = "\nNome: " + this.nome + "\n";
        s += "WSDLErogatore: " + this.WSDLErogatore + "\n";
        s += "WSDLFruitore: " + this.WSDLFruitore + "\n";
        s += "fileTypes: " + this.fileTypes + "\n";
        for (PortType portType : listaPortType) {
            s += portType.toString() + "\n";
        }
        return s;
    }

    @Transient
    public PortType getPortType(String nome, String ruolo) {
        for (PortType portType : listaPortType) {
            if (portType.isEquals(nome, ruolo) || portType.isLike(nome, ruolo)) {
                return portType;
            }
        }
        return null;
    }
}
