package it.unibas.icar.freesbee.modello;

import it.unibas.icar.freesbee.Costanti;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlTransient;

public class Servizio implements Serializable, Comparable {

    public static final String SEPARATORE_AZIONI = ",";
    private long id;
    private String tipo = Costanti.SPC;
    private String nome;
    private long idErogatore;
    private Soggetto erogatore;
    private List<Long> idFruitori = new ArrayList<Long>();
    private List<Soggetto> fruitori = new ArrayList<Soggetto>();
    private long idAccordoServizio;
    private AccordoServizio accordoServizio;
    private boolean correlato;
    private boolean privato;
    private String urlServizio;
    private String azioni;
    private boolean allAzioni = true;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlTransient
    public Soggetto getErogatore() {
        return erogatore;
    }

    public void setErogatore(Soggetto erogatore) {
        this.erogatore = erogatore;
    }

    @XmlTransient
    public List<Soggetto> getFruitori() {
        return fruitori;
    }

    public void setFruitori(List<Soggetto> fruitori) {
        this.fruitori = fruitori;
    }

    @XmlTransient
    public AccordoServizio getAccordoServizio() {
        return accordoServizio;
    }

    public void setAccordoServizio(AccordoServizio accordoServizio) {
        this.accordoServizio = accordoServizio;
    }

    public boolean isCorrelato() {
        return correlato;
    }

    public void setCorrelato(boolean correlato) {
        this.correlato = correlato;
    }

    public boolean isPrivato() {
        return privato;
    }

    public void setPrivato(boolean privato) {
        this.privato = privato;
    }

    public String getUrlServizio() {
        return urlServizio;
    }

    public void setUrlServizio(String urlServizio) {
        this.urlServizio = urlServizio;
    }

    public String getAzioni() {
        return azioni;
    }

    public void setAzioni(String azioni) {
        this.azioni = azioni;
    }

    @XmlTransient
    public List<String> getListaAzioni() {
        List<String> result = new ArrayList<String>();
        if (azioni != null && !azioni.trim().isEmpty()) {
            for (String azione : azioni.split(SEPARATORE_AZIONI)) {
                result.add(azione.trim());
            }
        }
        return result;
    }

    public boolean isAllAzioni() {
        return allAzioni;
    }

    public void setAllAzioni(boolean allAzioni) {
        this.allAzioni = allAzioni;
    }

    public int compareTo(Object o) {
        Servizio other = (Servizio) o;
        if (this.getTipo().equalsIgnoreCase(other.getTipo())) {
            return this.getNome().compareToIgnoreCase(other.getNome());
        } else {
            return this.getTipo().compareToIgnoreCase(other.getTipo());
        }
    }

    public String getNomeBreve() {
        if (erogatore != null) {
            return this.tipo + "\\" + this.nome + " [" + erogatore.getNomeBreve() + "]";
        }
        return this.tipo + "\\" + this.nome;
    }

    public int getNumeroFruitori() {
        if (this.getFruitori() == null) {
            return 0;
        }
        return this.getFruitori().size();
    }

    public long getIdErogatore() {
        return idErogatore;
    }

    public void setIdErogatore(long idErogatore) {
        this.idErogatore = idErogatore;
    }

    public long getIdAccordoServizio() {
        return idAccordoServizio;
    }

    public void setIdAccordoServizio(long idAccordoServizio) {
        this.idAccordoServizio = idAccordoServizio;
    }

    public List<Long> getIdFruitori() {
        return idFruitori;
    }

    public void setIdFruitori(List<Long> idFruitori) {
        this.idFruitori = idFruitori;
    }

    public long[] getArrayIdFruitori() {
        long[] array = new long[getIdFruitori().size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = getIdFruitori().get(i);
        }
        return array;
    }

    public void setAzioniFromArray(String[] azioniSelezionate) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < azioniSelezionate.length; i++) {
            if (i == (azioniSelezionate.length - 1)) {
                builder.append(azioniSelezionate[i]);
            } else {
                builder.append(azioniSelezionate[i]).append(SEPARATORE_AZIONI).append(" ");
            }
        }
        this.azioni = builder.toString();
    }
    

    @Override
    public String toString() {
        return "Servizio{" + "id=" + id + ", tipo=" + tipo + ", nome=" + nome + ", idErogatore=" + idErogatore + ", erogatore=" + erogatore + ", idFruitori=" + idFruitori + ", fruitori=" + fruitori + ", idAccordoServizio=" + idAccordoServizio + ", accordoServizio=" + accordoServizio + ", correlato=" + correlato + ", privato=" + privato + ", urlServizio=" + urlServizio + ", azioni=" + azioni + '}';
    }
}
