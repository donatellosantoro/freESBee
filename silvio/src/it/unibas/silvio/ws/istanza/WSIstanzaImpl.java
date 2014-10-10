package it.unibas.silvio.ws.istanza;

import it.unibas.silvio.erogatorehttp.HttpErogatoreIngressoDati;
import it.unibas.silvio.fruitorehttp.HttpIngressoDatiFruitore;
import it.unibas.silvio.modello.Configurazione;
import it.unibas.silvio.modello.IstanzaAccordoDiCollaborazione;
import it.unibas.silvio.modello.IstanzaPortType;
import it.unibas.silvio.persistenza.*;
import it.unibas.silvio.persistenza.hibernate.DAOUtilHibernate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;

public class WSIstanzaImpl implements IWSIstanza {

    private CamelContext context;
    private Log logger = LogFactory.getLog(this.getClass());
    private IDAOConfigurazione daoConfigurazione;
    private IDAOIstanzaAccordoDiCollaborazione daoIstanzaAccordo;
    private IDAOIstanzaPortType daoIstanzaPortType;
    private Map<String, RouteBuilder> istanzePTFruizione = new HashMap<String, RouteBuilder>();
    private Map<String, RouteBuilder> istanzePTErogazione = new HashMap<String, RouteBuilder>();

    public WSIstanzaImpl(CamelContext context, IDAOConfigurazione daoConfigurazione,
                         IDAOIstanzaPortType daoIstanzaPortType,IDAOIstanzaAccordoDiCollaborazione daoIstanzaAccordo) {
        this.context = context;
        this.daoConfigurazione = daoConfigurazione;
        this.daoIstanzaPortType = daoIstanzaPortType;
        this.daoIstanzaAccordo = daoIstanzaAccordo;
    }

    public void avviaIstanza(long id) throws Exception {
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            Configurazione conf = daoConfigurazione.caricaConfigurazione();
            String portaHttp = conf.getPorta();
            IstanzaAccordoDiCollaborazione istanzaAccordo = daoIstanzaAccordo.findById(id, false);
            List<IstanzaPortType> istanzePT = istanzaAccordo.getListaIstanzePortType();
            for (IstanzaPortType istanzaPortType : istanzePT) {
                avviaIstanzaPortType(istanzaPortType, portaHttp);
            }
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (DAOException ex) {
            logger.error("Errore nella lettura dal db " + ex);
            try {
                if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
                    sessionFactory.getCurrentSession().getTransaction().rollback();
                }
            } catch (Throwable rbEx) {
            }
            throw new Exception("Errore nella lettura dal db " + ex);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void avviaIstanzaPortType(IstanzaPortType istanza, String portaHttp) throws Exception {
        String nomeIstanza = istanza.getNome();
        if (istanza.isFruizione()) {
            logger.info("Trovata una nuova istanza pt di fruizione: " + nomeIstanza);
            RouteBuilder istanzaPTFruitore = new HttpIngressoDatiFruitore(istanza, portaHttp);
            context.addRoutes(istanzaPTFruitore);
            istanzePTFruizione.put(nomeIstanza, istanzaPTFruitore);
        } else {
            logger.info("Trovata una nuova istanza pt di erogazione: " + nomeIstanza);
            RouteBuilder istanzaPTErogatore = new HttpErogatoreIngressoDati(istanza, portaHttp);
            context.addRoutes(istanzaPTErogatore);
            istanzePTErogazione.put(nomeIstanza, istanzaPTErogatore);
        }
    }

    public void avviaIstanze() {
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            Configurazione conf = daoConfigurazione.caricaConfigurazione();
            String portaHttp = conf.getPorta();
            List<IstanzaPortType> listaIstanze = daoIstanzaPortType.findAll();

            for (IstanzaPortType istanza : listaIstanze) {
                avviaIstanzaPortType(istanza, portaHttp);
            }
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (Exception ex) {
            if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().rollback();
            }
            logger.error("Impossibile avviare l'istanza " + ex.getMessage());
        }
    }
}
