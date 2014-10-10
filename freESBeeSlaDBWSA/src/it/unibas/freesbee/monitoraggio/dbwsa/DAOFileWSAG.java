package it.unibas.freesbee.monitoraggio.dbwsa;

import it.unibas.freesbee.monitoraggio.exception.daoexception.DAOException;
import it.unibas.freesbee.monitoraggio.dbwsa.dbutilita.DBUtilita;
import it.unibas.freesbee.monitoraggio.libreria.Monitoraggio;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DAOFileWSAG {
    
    private static Log logger = LogFactory.getLog(DAOFileWSAG.class);
    private static DBUtilita db;
    private static Monitoraggio m = Monitoraggio.getInstance();
    
    private DAOFileWSAG () {}
    
    public static String caricaFileWSAG() throws DAOException {
        String query = "SELECT inf2_sla_object FROM icar_inf2_service i ";
        query += "WHERE i.inf2_id_service='" + m.getIdService() + "' AND i.inf2_id_initiator='" + m.getIdInitiator() + "' AND  i.inf2_id_responder='" + m.getIdResponder() + "'";
        logger.debug(query);
        
        String fileWSAG = null;
        try {
            db = DBUtilita.getInstance();
            db.connetti();
            fileWSAG = db.eseguiQueryTesto(query);
            logger.debug("File WSAG : \n" + fileWSAG);
        }catch(DAOException inf2ex) {
            logger.error(inf2ex);
            throw new DAOException(inf2ex);
        }finally {
            db.disconnetti();
        }
        return fileWSAG;
    }
    
    

}
