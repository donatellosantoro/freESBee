package it.unibas.freesbee.monitoraggio.dbwsa;

import it.unibas.freesbee.monitoraggio.calcolo.windowutils.GestoreDate;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Window;
import it.unibas.freesbee.monitoraggio.dbwsa.dbutilita.DBUtilita;
import it.unibas.freesbee.monitoraggio.exception.daoexception.DAOException;
import it.unibas.freesbee.monitoraggio.libreria.Monitoraggio;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;

public class DAORoundFunction {

    public static Log logger = LogFactory.getLog(DAORoundFunction.class);
    public static DBUtilita db = DBUtilita.getInstance();
    public static Monitoraggio m = Monitoraggio.getInstance();

    private DAORoundFunction() {
    }

    public static List<Double> caricaRisultato(String bmName, long digits, Window window, DateTime dataFinale) throws DAOException {
        String query = creaQuery(bmName, window, dataFinale);
        logger.debug(query);
        List<Double> listaRisultato = null;
        try {
            db.connetti();
            listaRisultato = db.eseguiQuery(query);
            listaRisultato = arrotondaRisultati(listaRisultato, digits);
            return listaRisultato;
        } catch (DAOException daoe) {
            throw daoe;
        } finally {
            db.disconnetti();
        }
    }

    public static List<Double> arrotondaRisultati(List<Double> listaRisultato, long digits) {
        List<Double> listaRisultatiRound = new ArrayList<Double>();
        int digitsInt = (int)digits;
        for(Double risultato : listaRisultato){
            BigDecimal big = new BigDecimal(risultato.doubleValue());
            BigDecimal bigScaled = big.setScale(digitsInt, BigDecimal.ROUND_HALF_UP);
            listaRisultatiRound.add(new Double(bigScaled.doubleValue()));
        }
        return listaRisultatiRound;
    }

    private static String creaQuery(String bmName, Window window, DateTime dataFinale) {
        String strDataFine = dataFinale.toString("yyyy-MM-dd HH:mm:ss");
        String query = "";
        if (window.getInterval() != null) {
            String strDataIniziale = GestoreDate.getDataInizio(dataFinale, window.getInterval().value()).toString("yyyy-MM-dd HH:mm:ss");
            query += "SELECT inf2_sla_basic_metric_value ";
            query += "FROM (SELECT * FROM icar_inf2_sla_object_trace i ";
            query += "WHERE i.inf2_id_service='" + m.getIdService() + "' AND  i.inf2_id_initiator='" + m.getIdInitiator() + "' AND  i.inf2_id_responder='" + m.getIdResponder() + "' ";
            query += "AND i.inf2_sla_basic_metric='" + bmName + "' ";
            query += "AND i.inf2_sla_basic_metric_data_insert <= '" + strDataFine + "' ";
            query += "AND i.inf2_sla_basic_metric_data_insert > '" + strDataIniziale + "' ";
            query += "order by inf2_sla_basic_metric_data_insert DESC,inf2_sla_basic_metric_milliseconds_insert DESC) AS view;";
        } else {
            query += "SELECT subQuery.inf2_sla_basic_metric_value ";
            query += "FROM (SELECT i.inf2_sla_basic_metric_value ";
            query += "FROM icar_inf2_sla_object_trace i ";
            query += "WHERE i.inf2_id_service='" + m.getIdService() + "' AND  i.inf2_id_initiator='" + m.getIdInitiator() + "' AND  i.inf2_id_responder='" + m.getIdResponder() + "' ";
            query += "AND i.inf2_sla_basic_metric='" + bmName + "' ";
            query += "AND i.inf2_sla_basic_metric_data_insert <= '" + strDataFine + "' ";
            query += "order by inf2_sla_basic_metric_data_insert DESC,inf2_sla_basic_metric_milliseconds_insert DESC ";
            query += "LIMIT " + window.getTimes() + ") subQuery;";
        }
        return query;
    }
}
