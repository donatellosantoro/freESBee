package it.unibas.freesbeesla.ws.web.grafico;

import it.unibas.freesbee.monitoraggio.calcolo.core.GuaranteeTermsResult;
import it.unibas.freesbeesla.ws.web.stub.*;
import it.unibas.freesbee.monitoraggio.calcolo.core.GuaranteeTermsValidator;
import it.unibas.freesbee.monitoraggio.libreria.Monitoraggio;
import it.unibas.freesbeesla.ws.web.persistenza.soap.DAOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jfree.data.category.DefaultCategoryDataset;

public class UtilitaGrafico {

    private Log logger = LogFactory.getLog(this.getClass());
    private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    private StatisticheSLAErogatore statisticheSLAErogatore;
    private GuaranteeTermsValidator validator = null;
    private Monitoraggio monitoraggio = Monitoraggio.getInstance();

    public UtilitaGrafico(StatisticheSLAErogatore statistiche) {
        this.statisticheSLAErogatore = statistiche;
    }

    public void calcolaStatistiche() throws IllegalArgumentException, DAOException {
        logger.info("Inizio calcolo statistiche");
        convalida();
        logger.info("Fine convalida date");
        creaGuaranteeTermsValidator();
        logger.info("Creato il validatore");
        generazioneDataset();
        logger.info("Generato il data set");
    }

    private void generazioneDataset() throws DAOException {
        logger.info("Generazione del Dataset in corso ");
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String series = "Valore Parametro SLA";
        String nomeSla = (String) statisticheSLAErogatore.getSlaNome();
        List arrayDateOsservate = creaArrayDate();
        logger.debug("Dimensione array delle date: " + arrayDateOsservate.size());
        int sizeDateOss = arrayDateOsservate.size();



        for (int i = 0; i < sizeDateOss; i++) {
            try {
                Date dataFinale = formatter.parse((String) arrayDateOsservate.get(i));

                GuaranteeTermsResult guaranteeTermResult = monitoraggio.effettuaMonitoraggio(statisticheSLAErogatore.getNomeServizio(), statisticheSLAErogatore.getNomeFruitore(), statisticheSLAErogatore.getNomeErogatore(), nomeSla, dataFinale);

                logger.debug("Valutazione del parametro per la data finale " + dataFinale + " effettuata.");
                double val = guaranteeTermResult.getRisultatoFinale();
                dataset.addValue(((Number) (val)), ((Comparable) (series)), (Comparable) arrayDateOsservate.get(i));
                if (statisticheSLAErogatore.getSoglia() == null || statisticheSLAErogatore.getSoglia() == 0) {
                    double soglia = guaranteeTermResult.getThresholdValue();
                    statisticheSLAErogatore.setSoglia(soglia);
                    String operazione = guaranteeTermResult.getThresholdOperator();
                    statisticheSLAErogatore.setOperazione(operazione);
                }

            } catch (Exception re) {
                logger.error("Errore non grave. " + re.getMessage());
                dataset.addValue(((Number) (null)), ((Comparable) (series)), (Comparable) arrayDateOsservate.get(i));
            }
        }
        statisticheSLAErogatore.setDataSetSla(dataset);

    }

    private List creaArrayDate() {
        Date dataInizio = statisticheSLAErogatore.getDataInizioToDate();
        Date dataFine = statisticheSLAErogatore.getDataFineToDate();

        String step = (String) statisticheSLAErogatore.getStep();
        List<String> arrayDate = new ArrayList();
        List<String> newArrayDate = new ArrayList();

        Date dataTemp = dataFine;

        String dataTempStr = formatter.format(dataTemp);
        arrayDate.add(dataTempStr);

        dataTemp = subtractInterval(dataTemp, step);
        while (dataTemp.after(dataInizio)) {
            dataTempStr = formatter.format(dataTemp);
            arrayDate.add(dataTempStr);
            dataTemp = subtractInterval(dataTemp, step);
        }

        for (int i = arrayDate.size() - 1; i >= 0; i--) {
            newArrayDate.add(arrayDate.get(i));
        }

        logger.info("Creato l'array delle date");
        return newArrayDate;
    }

    private void convalida() throws IllegalArgumentException, DAOException {
        logger.info("Inizio convalida date");
        logger.info("1");
        XMLGregorianCalendar dataInizio = statisticheSLAErogatore.getDataInizio();
        logger.info("2");
        XMLGregorianCalendar dataFine = statisticheSLAErogatore.getDataFine();

        GregorianCalendar g = new GregorianCalendar();
        g.setLenient(false);
        g.set(dataInizio.getYear(), dataInizio.getMonth() - 1, dataInizio.getDay(), dataInizio.getHour(), dataInizio.getMinute(), dataInizio.getSecond());
        logger.info("1");
        g.getTime();
        g.set(dataFine.getYear(), dataFine.getMonth() - 1, dataFine.getDay(), dataFine.getHour(), dataFine.getMinute(), dataFine.getSecond());
        logger.info("2");
        g.getTime();

        logger.info("1");


//        DAOSlaObjectTraceTorque daoObjectTrace = new DAOSlaObjectTraceTorque();
        logger.info("Ricerco la data minima per: ");
        logger.info("Servizio: " + statisticheSLAErogatore.getNomeServizio());
        logger.info("Erogatore: " + statisticheSLAErogatore.getNomeErogatore());
        logger.info("Fruitore: " + statisticheSLAErogatore.getNomeFruitore());
        logger.info("SLA: " + statisticheSLAErogatore.getSlaNome());
        logger.info("Data inizio: " + dataInizio);
        logger.info("Data fine: " + dataFine);

//        Date dataMinima = daoObjectTrace.selectMinDataByServizio(this.statisticheSLAErogatore.getNomeServizio(), this.statisticheSLAErogatore.getNomeFruitore(), this.statisticheSLAErogatore.getNomeErogatore());
//
//        logger.debug("Data minima: " + dataMinima);
//
//        if (statisticheSLAErogatore.getDataFineToDate().getTime() < dataMinima.getTime()) {
//            logger.error("Errore. La data finale precede la prima data utile presente nel DB");
//            throw new IllegalArgumentException("Errore. La data finale precede la prima data utile presente nel DB");
//        }
//
//        if (statisticheSLAErogatore.getDataInizioToDate().getTime() < dataMinima.getTime()) {
//            logger.error("Errore. La data iniziale precede la prima data utile presente nel DB");
//            throw new IllegalArgumentException("Errore. La data iniziale" +
//                    " precede la prima data utile presente nel DB");
//        }

        if (dataFine.compare(dataInizio) == DatatypeConstants.LESSER) {
            logger.error("La data di inizio si trova dopo di quella di fine.");
            throw new DAOException("La data di inizio si trova dopo di quella di fine.");
        }

        logger.info("Convalida delle date effettuata");

    }

    private void creaGuaranteeTermsValidator() throws DAOException {
        try {
            validator = monitoraggio.convalidaSintassiWSAG(statisticheSLAErogatore.getNomeServizio(), statisticheSLAErogatore.getNomeFruitore(), statisticheSLAErogatore.getNomeErogatore());
            logger.info("Creazione del validatore effettuata");
        } catch (Exception ex) {
            logger.error("Errore. " + ex.getMessage());
            throw new DAOException("Errore. " + ex.getMessage());
        }
    }

    private Date subtractInterval(Date dataTemp, String interval) {
        Calendar calDatTemp = Calendar.getInstance();
        calDatTemp.setTime(dataTemp);

        if ("HOUR".equalsIgnoreCase(interval)) {
            calDatTemp.add(Calendar.HOUR, -1);
        }

        if ("SIXHOURS".equalsIgnoreCase(interval)) {
            calDatTemp.add(Calendar.HOUR, -6);
        }

        if ("DAY".equalsIgnoreCase(interval)) {
            calDatTemp.add(Calendar.DAY_OF_YEAR, -1);
        }

        if ("WEEK".equalsIgnoreCase(interval)) {
            calDatTemp.add(Calendar.WEEK_OF_YEAR, -1);
        }

        if ("TWOWEEKS".equalsIgnoreCase(interval)) {
            calDatTemp.add(Calendar.WEEK_OF_YEAR, -2);
        }

        if ("MONTH".equalsIgnoreCase(interval)) {
            calDatTemp.add(Calendar.MONTH, -1);
        }

        if ("THREEMONTHS".equalsIgnoreCase(interval)) {
            calDatTemp.add(Calendar.MONTH, -3);
        }

        if ("SIXMONTHS".equalsIgnoreCase(interval)) {
            calDatTemp.add(Calendar.MONTH, -6);
        }

        if ("YEAR".equalsIgnoreCase(interval)) {
            calDatTemp.add(Calendar.YEAR, -1);
        }

        return calDatTemp.getTime();
    }
}
