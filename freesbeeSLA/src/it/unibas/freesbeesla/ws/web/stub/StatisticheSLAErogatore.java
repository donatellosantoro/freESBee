package it.unibas.freesbeesla.ws.web.stub;

import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.datatype.XMLGregorianCalendar;
import org.jfree.data.category.DefaultCategoryDataset;

@XmlRootElement(name = "StatisticheSLAErogatore")
public class StatisticheSLAErogatore {

    private String nomeServizio;
    private String nomeFruitore;
    private String nomeErogatore;
    private String slaNome;
  
    private XMLGregorianCalendar dataInizio;

    private XMLGregorianCalendar dataFine;
    private String step;
    private Collection nomiParSla;
    private Double soglia;
    private String operazione;
    private DefaultCategoryDataset dataSetSla;

    public String getNomeServizio() {
        return nomeServizio;
    }

    public void setNomeServizio(String nomeServizio) {
        this.nomeServizio = nomeServizio;
    }

    public String getNomeFruitore() {
        return nomeFruitore;
    }

    public void setNomeFruitore(String nomeFruitore) {
        this.nomeFruitore = nomeFruitore;
    }

    public String getNomeErogatore() {
        return nomeErogatore;
    }

    public void setNomeErogatore(String nomeErogatore) {
        this.nomeErogatore = nomeErogatore;
    }

    public String getSlaNome() {
        return slaNome;
    }

    public void setSlaNome(String slaNome) {
        this.slaNome = slaNome;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public Collection getNomiParSla() {
        return nomiParSla;
    }

    public void setNomiParSla(Collection nomiParSla) {
        this.nomiParSla = nomiParSla;
    }

    @XmlTransient
    public Double getSoglia() {
        return soglia;
    }

    public void setSoglia(Double soglia) {
        this.soglia = soglia;
    }

    @XmlTransient
    public String getOperazione() {
        return operazione;
    }

    public void setOperazione(String operazione) {
        this.operazione = operazione;
    }

    @XmlTransient
    public DefaultCategoryDataset getDataSetSla() {
        return dataSetSla;
    }

    public void setDataSetSla(DefaultCategoryDataset dataSetSla) {
        this.dataSetSla = dataSetSla;
    }

      @XmlSchemaType(name = "dateTime")
    public XMLGregorianCalendar getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(XMLGregorianCalendar dataInizio) {
        this.dataInizio = dataInizio;
    }

      @XmlSchemaType(name = "dateTime")
    public XMLGregorianCalendar getDataFine() {
        return dataFine;
    }

    public void setDataFine(XMLGregorianCalendar dataFine) {
        this.dataFine = dataFine;
    }

    public Date getDataFineToDate() {
        GregorianCalendar data = new GregorianCalendar(dataFine.getYear(), dataFine.getMonth()-1, dataFine.getDay(), dataFine.getHour(), dataFine.getMinute());
        return data.getTime();
    }
    
     public Date getDataInizioToDate() {
        GregorianCalendar data = new GregorianCalendar(dataInizio.getYear(), dataInizio.getMonth()-1, dataInizio.getDay(), dataInizio.getHour(), dataInizio.getMinute());
        return data.getTime();
    }
}
