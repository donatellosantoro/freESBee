package it.unibas.freesbee.monitoraggio.dbwsa;

import it.unibas.freesbee.monitoraggio.calcolo.core.StatoServizio;
import it.unibas.freesbee.monitoraggio.dbwsa.dbutilita.DBUtilita;
import it.unibas.freesbee.monitoraggio.exception.daoexception.DAOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DAOVerificaStatoServizio {

    private DAOVerificaStatoServizio() {
    }
    private static DBUtilita db = DBUtilita.getInstance();
    public static Log logger = LogFactory.getLog(DAOVerificaStatoServizio.class);

    public static String verificaStatoServizio(String idService, String idInitiator, String idResponder) throws DAOException {
        String statoServizio = "";
        statoServizio = verificaStatoServizioSuper(idService, idInitiator, idResponder).getStato();
        return statoServizio;

    }

    public static StatoServizio verificaStatoServizioSuper(String idService, String idInitiator, String idResponder) throws DAOException {
        String query = "SELECT inf2_id_state, inf2_count_pending_request ";
        query += "FROM icar_inf2_service i ";
        query += "WHERE i.inf2_id_service='" + idService + "' ";
        query += "AND  i.inf2_id_initiator='" + idInitiator + "' ";
        query += "AND  i.inf2_id_responder='" + idResponder + "';";
        StatoServizio statoServizio = null;
        try {
            db.connetti();
            statoServizio = db.verificaStato(query);

            return statoServizio;
        } catch (DAOException daoe) {
            throw daoe;
        } finally {
            db.disconnetti();
        }

    }
}
