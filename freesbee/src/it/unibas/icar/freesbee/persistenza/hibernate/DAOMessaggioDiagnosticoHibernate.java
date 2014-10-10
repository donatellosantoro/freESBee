package it.unibas.icar.freesbee.persistenza.hibernate;

import it.unibas.icar.freesbee.modello.MessaggioDiagnostico;
import it.unibas.icar.freesbee.persistenza.IDAOMessaggioDiagnostico;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DAOMessaggioDiagnosticoHibernate extends DAOGenericoHibernate<MessaggioDiagnostico> implements IDAOMessaggioDiagnostico {

    private static Log logger = LogFactory.getLog(DAOMessaggioDiagnosticoHibernate.class);

    public DAOMessaggioDiagnosticoHibernate() {
        super(MessaggioDiagnostico.class);
    }
}
