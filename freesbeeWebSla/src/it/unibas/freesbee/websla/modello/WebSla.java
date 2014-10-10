
package it.unibas.freesbee.websla.modello;


import it.unibas.freesbee.websla.utilita.Utilita;
import java.util.Date;
import javax.xml.datatype.XMLGregorianCalendar;


public class WebSla {

    private String guaranteeTermName;
    private XMLGregorianCalendar dateFn;
    private String esito = "";
    private double risultato = 0.0;
    private String risultatoAtteso = "";

    

    public String getGuaranteeTermName() {
        return guaranteeTermName;
    }

   
    public void setGuaranteeTermName(String value) {
        this.guaranteeTermName = value;
    }

   
    public XMLGregorianCalendar getDateFn() {
        return dateFn;
    }

    
    public void setDateFn(XMLGregorianCalendar value) {
        this.dateFn = value;
    }
    

    public String getDataFormattata() {
        Date data = Utilita.convertiXMLGregorianCalendarToDate(dateFn);
        return Utilita.formattaDataEOra(data);
    }

    public String getEsito() {
        return esito;
    }

    public void setEsito(String esito) {
        this.esito = esito;
    }

    public double getRisultato() {
        return risultato;
    }

    public void setRisultato(double risultato) {
        this.risultato = risultato;
    }


    public String getRisultatoAtteso() {
        return risultatoAtteso;
    }


    public void setRisultatoAtteso(String risultatoAtteso) {
        this.risultatoAtteso = risultatoAtteso;
    }


}
