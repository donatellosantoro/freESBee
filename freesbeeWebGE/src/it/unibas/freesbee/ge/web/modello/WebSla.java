
package it.unibas.freesbee.ge.web.modello;


import it.unibas.freesbee.ge.web.utilita.Utilita;
import java.util.Date;
import javax.xml.datatype.XMLGregorianCalendar;


public class WebSla {

    private String guaranteeTermName;
    private XMLGregorianCalendar dateFn;
    private String esito;
    private String risultato;
    

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

    public String getRisultato() {
        return risultato;
    }

    public void setRisultato(String risultato) {
        this.risultato = risultato;
    }

}
