package it.unibas.freesbee.ge.modello;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.apache.cxf.helpers.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

@Entity
public class FiltroData implements Serializable {

    private int id;
    private Date dataInizio;
    private Date dataFine;

    public FiltroData() {
    }

    public FiltroData(Date dataInizio) {
        this.dataInizio = dataInizio;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(nullable = false)
    public Date getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(Date dataInizio) {
        this.dataInizio = dataInizio;
    }

    public Date getDataFine() {
        return dataFine;
    }

    public void setDataFine(Date dataFine) {
        this.dataFine = dataFine;
    }

    public static FiltroData binding(String bodyMessaggio) throws Exception {
        FiltroData filtroData = new FiltroData();
        Document doc = XMLUtils.parse(bodyMessaggio);
        Node nodo = doc.getDocumentElement().getElementsByTagName("dataInizio").item(0);

        if (nodo == null || nodo.getTextContent().equals("")) {
            throw new Exception("Bisogna specificare la data inizio");
        }

        filtroData.setDataInizio(convertiTimestampToXMLDate(nodo.getTextContent()));

        Date dataAttuale = new GregorianCalendar().getTime();
        if (dataAttuale.after(filtroData.getDataInizio())) {
            throw new Exception("La data di inizio sottoscrizione deve essere posteriore a quella attuale");
        }

        nodo = doc.getDocumentElement().getElementsByTagName("dataFine").item(0);
        if (nodo != null && !nodo.getTextContent().equals("")) {
            filtroData.setDataFine(convertiTimestampToXMLDate(nodo.getTextContent()));
            if (filtroData.getDataFine().before(filtroData.getDataInizio())) {
                throw new Exception("La data di fine sottoscrizione deve essere posteriore a quella di inizio sottoscrizione");
            }

        }
        return filtroData;
    }

    public static Date convertiTimestampToXMLDate(String dataDaFormattare) throws Exception {
        //nel messaggio soap si mette 2009-3-1 15:20:3
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar g = new GregorianCalendar();
        g.setLenient(false);
        formatter.setCalendar(g);
        Date date = null;
        try {
            date = (Date) formatter.parse(dataDaFormattare);
        } catch (ParseException p) {
            throw new Exception("La data " + dataDaFormattare + " e' scorretta");
        }

        return date;
    }

    public static String convertiDateToXmlDate(Date data) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(data);
    }
}
