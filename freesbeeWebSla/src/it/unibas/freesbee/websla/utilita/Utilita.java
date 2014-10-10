package it.unibas.freesbee.websla.utilita;

import it.unibas.freesbee.client.cxf.sistemamonitoraggio.GuaranteeTermObj;
import it.unibas.freesbee.websla.modello.WebServizio;
import it.unibas.freesbee.websla.modello.WebSla;
import it.unibas.icar.freesbee.modello.Servizio;
import it.unibas.icar.freesbee.modello.Soggetto;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Utilita {

    private static Log logger = LogFactory.getLog(Utilita.class);
    private static String patternDataEOra = "yyyy-MM-dd HH:mm";
    private static String patternDataEOraMinutiSecondiMillisecondi = "yyyy-MM-dd HH:mm:ss";

    public static List trasformaServizioInWebServizio(List<Servizio> listaServizi) {
        List<WebServizio> listaWebServizi = new ArrayList<WebServizio>();

        for (Servizio servizio : listaServizi) {
            for (Soggetto soggetto : servizio.getFruitori()) {
                WebServizio webServizio = new WebServizio();

                webServizio.setNome(servizio.getNome());
                webServizio.setErogatore(servizio.getErogatore().getNome());
//            webServizio.setContatoreRichieste(servizio.getContatoreRichieste());
//            webServizio.setAttiva(trasformaLongInBoolean(servizio.getActive()));
                webServizio.setFruitore(soggetto.getNome());
                listaWebServizi.add(webServizio);
            }

        }

        return listaWebServizi;
    }

    public static Servizio trasformaWebServizioInServizio(WebServizio webServizio) {
        Servizio servizio = new Servizio();
        servizio.setNome(webServizio.getNome());

        Soggetto soggettoErogatore = new Soggetto();
        soggettoErogatore.setNome(webServizio.getErogatore());
        servizio.setErogatore(soggettoErogatore);

        Soggetto soggettoFruitore = new Soggetto();
        soggettoFruitore.setNome(webServizio.getFruitore());
        servizio.getFruitori().add(soggettoFruitore);

//        servizio.setActive(trasformaBooleanInLong(webServizio.isAttiva()));
        return servizio;
    }

    public static boolean trasformaLongInBoolean(Long valore) {
        if (valore == 0) {
            return false;
        } else {
            return true;
        }
    }

    public static Long trasformaBooleanInLong(boolean valore) {
        if (valore) {
            return new Long(1);
        } else {
            return new Long(0);
        }
    }

    public static XMLGregorianCalendar convertiDateToXMLGregorianCalendar(Date data) throws DatatypeConfigurationException {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(data);
        XMLGregorianCalendar xmlGregorianCalendar = (DatatypeFactory.newInstance()).newXMLGregorianCalendar(gregorianCalendar);
        return xmlGregorianCalendar.normalize();
    }

    public static Date convertiXMLGregorianCalendarToDate(XMLGregorianCalendar xmlGregorianCalendar) {
        long dataInMillisecondi = xmlGregorianCalendar.toGregorianCalendar().getTimeInMillis();
        Date data = new Date(dataInMillisecondi);
        return data;
    }

    public static String formattaDataEOra(Date data) {
        if (data == null) {
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patternDataEOra);
        return simpleDateFormat.format(data);
    }

    public static String formattaDataEOraMinutiSecondiMillisecondi(Date data) {
        if (data == null) {
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patternDataEOraMinutiSecondiMillisecondi);
        return simpleDateFormat.format(data);
    }

    public static GuaranteeTermObj trasformaWebSlaInGuaranteeTermObj(WebSla webSla) {
        GuaranteeTermObj gto = new GuaranteeTermObj();
        gto.setGuaranteeTermName(webSla.getGuaranteeTermName());
        gto.setDateFn(webSla.getDateFn());
        return gto;
    }

    public static String convertiOperatore(String operazione) {
        String operatore = "";
        if (operazione.equalsIgnoreCase("Greater")) {
            operatore = " > ";
        } else if (operazione.equalsIgnoreCase("GreaterEqual")) {
            operatore = " >= ";
        } else if (operazione.equalsIgnoreCase("Less")) {
            operatore = " < ";
        } else if (operazione.equalsIgnoreCase("LessEqual")) {
            operatore = " <= ";
        } else if (operazione.equalsIgnoreCase("Equal")) {
            operatore = " = ";
        } else if (operazione.equalsIgnoreCase("NotEqual")) {
            operatore = " != ";
        }

        return operatore;
    }
}
