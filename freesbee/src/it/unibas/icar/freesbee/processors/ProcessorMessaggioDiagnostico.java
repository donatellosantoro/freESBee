package it.unibas.icar.freesbee.processors;

import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.modello.MessaggioDiagnostico;
import it.unibas.icar.freesbee.persistenza.IDAOMessaggioDiagnostico;
import it.unibas.icar.freesbee.persistenza.hibernate.DAOMessaggioDiagnosticoHibernate;
import it.unibas.icar.freesbee.persistenza.hibernate.DAOUtilHibernate;
import it.unibas.icar.freesbee.utilita.CostantiBusta;
import java.util.Date;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;

public class ProcessorMessaggioDiagnostico implements Processor {

//    private static Log logger = LogFactory.getLog(ProcessorMessaggioDiagnostico.class);
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ProcessorMessaggioDiagnostico.class.getName());
    private String identificativoPorta;
    private String identificativoFunzione;
    private int severita;
    private String testoDiagnostico;

    public ProcessorMessaggioDiagnostico() {
    }

    public ProcessorMessaggioDiagnostico(String identificativoPorta, String identificativoFunzione, int severita, String testoDiagnostico) {
        this.identificativoPorta = identificativoPorta;
        this.identificativoFunzione = identificativoFunzione;
        this.severita = severita;
        this.testoDiagnostico = testoDiagnostico;
    }

    public void process(Exchange exchange) throws Exception {
        //ContextStartup.aggiungiThread(this.getClass().getName());
        Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiBusta.MESSAGGIO);
        MessaggioDiagnostico messDiagn = new MessaggioDiagnostico();
        IDAOMessaggioDiagnostico daoMessaggioDiagnostico = new DAOMessaggioDiagnosticoHibernate();
        messDiagn.setGdo(new Date());
        messDiagn.setIdentificativoFunzione(identificativoFunzione);
        messDiagn.setIdentificativoPorta(identificativoPorta);
        messDiagn.setSeverita(severita);
        messDiagn.setTestoDiagnostico(testoDiagnostico);


        SessionFactory sessionFactory = null;
        try {
            sessionFactory = DAOUtilHibernate.getSessionFactory();
            sessionFactory.getCurrentSession().beginTransaction();
            daoMessaggioDiagnostico.makePersistent(messDiagn);
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (Exception ex) {
            try {
                if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
                    sessionFactory.getCurrentSession().getTransaction().rollback();
                }
            } catch (Throwable rbEx) {
            }
        }

    }
}
