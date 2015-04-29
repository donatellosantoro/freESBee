package it.unibas.icar.freesbee.persistenza.hibernate;

import it.unibas.icar.freesbee.modello.MessaggioDiagnostico;
import it.unibas.icar.freesbee.persistenza.IDAOMessaggioDiagnostico;
import org.slf4j.LoggerFactory;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

public class DAOMessaggioDiagnosticoHibernate extends DAOGenericoHibernate<MessaggioDiagnostico> implements IDAOMessaggioDiagnostico {

//    private static Log logger = LogFactory.getLog(DAOMessaggioDiagnosticoHibernate.class);
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(DAOMessaggioDiagnosticoHibernate.class.getName());

    public DAOMessaggioDiagnosticoHibernate() {
        super(MessaggioDiagnostico.class);
    }
}
