package it.unibas.icar.freesbee.ws.web;

import it.unibas.icar.freesbee.modello.ConfigurazioneStatico;
import it.unibas.icar.freesbee.modello.PortaDelegata;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.DBManager;
import it.unibas.icar.freesbee.persistenza.IDAOAzione;
import it.unibas.icar.freesbee.persistenza.IDAOPortaDelegata;
import it.unibas.icar.freesbee.persistenza.IDAOServizio;
import it.unibas.icar.freesbee.persistenza.IDAOSoggetto;
import it.unibas.icar.freesbee.persistenza.SOAPFault;
import it.unibas.icar.freesbee.persistenza.hibernate.DAOUtilHibernate;
import it.unibas.icar.freesbee.portadelegatahttp.HttpPortaDelegata;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jetty.JettyHttpComponent;
import org.apache.camel.util.jsse.KeyManagersParameters;
import org.apache.camel.util.jsse.KeyStoreParameters;
import org.apache.camel.util.jsse.SSLContextParameters;
import org.apache.commons.httpclient.contrib.ssl.EasySSLProtocolSocketFactory;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;

public class WSPortaDelegataImpl implements IWSPortaDelegata {

    private static Log logger = LogFactory.getLog(WSPortaApplicativaImpl.class);
    private CamelContext context;
    private IDAOPortaDelegata daoPortaDelegata;
    private IDAOServizio daoServizio;
    private IDAOSoggetto daoSoggetto;
    private IDAOAzione daoAzione;
    private DBManager dbManager;
    private Map<String, RouteBuilder> porteDelegateAvviate = new HashMap<String, RouteBuilder>();

    public WSPortaDelegataImpl(CamelContext context, IDAOPortaDelegata daoPortaDelegata, IDAOServizio daoServizio,
            IDAOSoggetto daoSoggetto, IDAOAzione daoAzione, DBManager dbManager) {
        this.context = context;
        this.daoPortaDelegata = daoPortaDelegata;
        this.daoServizio = daoServizio;
        this.daoSoggetto = daoSoggetto;
        this.daoAzione = daoAzione;
        this.dbManager = dbManager;
    }

    public void addPortaDelegata(PortaDelegata pd) throws SOAPFault {
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            if (pd.getId() == 0) {
                //E' UNA PORTA DELEGATA DA AGGIUNGERE
                if (logger.isInfoEnabled()) logger.info("Richiesta l'aggiunta della porta delegata " + pd);
                String nome = pd.getNome();
                PortaDelegata nuovaPortaDelegata = daoPortaDelegata.findByNome(nome);
                if (nuovaPortaDelegata != null) {
                    throw new SOAPFault("Esiste già una porta delegata con il nome specificato");
                }
                riempiRiferimenti(pd);
                daoPortaDelegata.makePersistent(pd);
                Hibernate.initialize(pd);
                if (pd.getServizio() != null) {
                    Hibernate.initialize(pd.getServizio().getAccordoServizio());
                }
                sessionFactory.getCurrentSession().getTransaction().commit();
                avviaIstanzaPortaDelegata(pd);
                if (logger.isInfoEnabled()) logger.info("Porta delegata avviata!");
            } else {
                //E' UNA PORTA DELEGATA DA MODIFICARE
                if (logger.isInfoEnabled()) logger.info("Richiesta la modifica della porta delegata");
                PortaDelegata pdModificare = daoPortaDelegata.findById(pd.getId(), true);
                if (pdModificare == null) {
                    throw new SOAPFault("Non esiste alcuna porta delegata con l'id specificato");
                }
                riempiRiferimenti(pd);
                chiudiIstanzaPortaDelegata(pdModificare);
                copiaProprieta(pd, pdModificare);
                daoPortaDelegata.makePersistent(pdModificare);
                Hibernate.initialize(pdModificare);
                if (pdModificare.getServizio() != null) {
                    Hibernate.initialize(pdModificare.getServizio().getAccordoServizio());
                }
                sessionFactory.getCurrentSession().getTransaction().commit();
                avviaIstanzaPortaDelegata(pdModificare);
            }
        } catch (Exception ex) {
            if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().rollback();
            }
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new SOAPFault("Impossibile aggiungere la nuova porta delegata " + ex.getMessage());
        }
    }

    public void removePortaDelegata(long id) throws SOAPFault {
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        try {
            if (logger.isInfoEnabled()) logger.info("Richiesta la rimozione della porta delegata con id " + id);
            sessionFactory.getCurrentSession().beginTransaction();
            PortaDelegata portaDelegataRimuovere = daoPortaDelegata.findById(id, false);
            chiudiIstanzaPortaDelegata(portaDelegataRimuovere);
            daoPortaDelegata.makeTransient(portaDelegataRimuovere);
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (Exception ex) {
            if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().rollback();
            }
            logger.error("Impossibile rimuovere la porta delegata " + ex);
            throw new SOAPFault("Impossibile rimuovere la porta delegata " + ex.getMessage());
        }
    }

    public void avviaPorte() {
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        try {
            configuraJetty();
            sessionFactory.getCurrentSession().beginTransaction();
            List<PortaDelegata> listaPorteDelegate = daoPortaDelegata.findAll();
            sessionFactory.getCurrentSession().getTransaction().commit();
            for (PortaDelegata portaDelegata : listaPorteDelegate) {
                avviaIstanzaPortaDelegata(portaDelegata);
            }
        } catch (Exception ex) {
            if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().rollback();
            }
            logger.error("Impossibile aggiungere la porta delegata " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public List<PortaDelegata> getListaPorteDelegate() throws SOAPFault {
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        try {
            if (logger.isInfoEnabled()) logger.info("Richiesta la lista delle porte delegate");
            sessionFactory.getCurrentSession().beginTransaction();
            List<PortaDelegata> listaPorteDelegate = daoPortaDelegata.findAll();
            for (PortaDelegata portaDelegata : listaPorteDelegate) {
                settaRiferimenti(portaDelegata);
            }
            sessionFactory.getCurrentSession().getTransaction().commit();
            return listaPorteDelegate;
        } catch (Exception ex) {
            if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().rollback();
            }
            logger.error("Impossibile leggere la lista delle porte delegate. " + ex);
            throw new SOAPFault("Impossibile leggere la lista delle porte delegate. " + ex.getMessage());
        }
    }

    public PortaDelegata getPortaDelegata(long id) throws SOAPFault {
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            PortaDelegata portaDelegata = daoPortaDelegata.findById(id, false);
            settaRiferimenti(portaDelegata);
            sessionFactory.getCurrentSession().getTransaction().commit();
            return portaDelegata;
        } catch (Exception ex) {
            if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().rollback();
            }
            logger.error("Impossibile leggere la porta delegata. " + ex);
            throw new SOAPFault("Impossibile leggere la porta delegata. " + ex.getMessage());
        }
    }

    private void avviaIstanzaPortaDelegata(PortaDelegata portaDelegata) throws Exception {
        String nome = portaDelegata.getNome();
//        String profiloCollaborazione = portaDelegata.getServizio().getAccordoServizio().getProfiloCollaborazione();
        String tipo;
        if (portaDelegata.getServizio() != null) {
            tipo = portaDelegata.getServizio().getTipo();
        } else {
            tipo = portaDelegata.getStringaTipoServizio();
        }
        if (logger.isInfoEnabled()) logger.info("E' stata aggiunta una nuova porta delegata: " + nome);
        RouteBuilder portaDelegataAggiunta = new HttpPortaDelegata(nome, tipo, dbManager);
        context.addRoutes(portaDelegataAggiunta);
        porteDelegateAvviate.put(portaDelegata.getNome(), portaDelegataAggiunta);
    }

    private void chiudiIstanzaPortaDelegata(PortaDelegata portaDelegata) throws Exception {
        String nomePortaDelegata = portaDelegata.getNome();
        if (logger.isInfoEnabled()) logger.info("E' stata eliminata una porta delegata: " + nomePortaDelegata);
        RouteBuilder portaDelegataEliminata = porteDelegateAvviate.get(nomePortaDelegata);
//        portaDelegataEliminata.doStop();
        if (logger.isInfoEnabled()) logger.info("Porta arrestata correttamente");
//        String indirizzo = "jetty:http://0.0.0.0:8192/" + nomePortaDelegata + "/";//                    
//        JettyHttpEndpoint endpointEliminare = (JettyHttpEndpoint) context.getEndpoint(indirizzo);
//        if (logger.isInfoEnabled()) logger.info("Endpoint eliminare: " + endpointEliminare);
    }

    private void copiaProprieta(PortaDelegata pd, PortaDelegata pdModificare) {
        pdModificare.setAzione(pd.getAzione());
        pdModificare.setDescrizione(pd.getDescrizione());
        pdModificare.setNome(pd.getNome());
        pdModificare.setServizio(pd.getServizio());
        pdModificare.setFruitoreQueryString(pd.isFruitoreQueryString());
        pdModificare.setStringaAzione(pd.getStringaAzione());
        pdModificare.setStringaErogatore(pd.getStringaErogatore());
        pdModificare.setStringaFruitore(pd.getStringaFruitore());
        pdModificare.setStringaServizio(pd.getStringaServizio());
        pdModificare.setStringaTipoErogatore(pd.getStringaTipoErogatore());
        pdModificare.setStringaTipoFruitore(pd.getStringaTipoFruitore());
        pdModificare.setStringaTipoServizio(pd.getStringaTipoServizio());
        pdModificare.setSoggettoFruitore(pd.getSoggettoFruitore());
    }

    private void riempiRiferimenti(PortaDelegata pd) throws DAOException {
        if (pd.getIdAzione() > 0) {
            logger.info("Riempio i riferimenti dell'azione con id " + pd.getIdAzione());
            pd.setAzione(daoAzione.findById(pd.getIdAzione(), false));
        }
        if (pd.getIdServizio() > 0) {
            logger.info("Riempio i riferimenti dell'servizio con id " + pd.getIdServizio());
            pd.setServizio(daoServizio.findById(pd.getIdServizio(), false));
        }
        if (pd.getIdSoggettoFruitore() > 0) {
            logger.info("Riempio i riferimenti dell'soggetto fruitore con id " + pd.getIdSoggettoFruitore());
            pd.setSoggettoFruitore(daoSoggetto.findById(pd.getIdSoggettoFruitore(), false));
        }
    }

    private void settaRiferimenti(PortaDelegata pd) {
        if (pd.getAzione() != null) {
            pd.setIdAzione(pd.getAzione().getId());
        }
        if (pd.getServizio() != null) {
            pd.setIdServizio(pd.getServizio().getId());
        }
        if (pd.getSoggettoFruitore() != null) {
            pd.setIdSoggettoFruitore(pd.getSoggettoFruitore().getId());
        }
    }

    private void configuraJetty() throws IOException {
        impostaParametriPrestazioni();
        impostaParametriSicurezza();
    }

    private void impostaParametriPrestazioni() {
        JettyHttpComponent componentJetty = (JettyHttpComponent) context.getComponent("jetty");
        int jettyThreadQueueMinSize = ConfigurazioneStatico.getInstance().getThreadJettyPoolMin();
        int jettyThreadQueueMaxSize = ConfigurazioneStatico.getInstance().getThreadJettyPoolMax();
        QueuedThreadPool threadPool = new QueuedThreadPool();
        threadPool.setMinThreads(jettyThreadQueueMinSize);
        threadPool.setMaxThreads(jettyThreadQueueMaxSize);
        componentJetty.setThreadPool(threadPool);
        logger.info("Imposto il thread pool di Jetty con una QueueSize di min: " + jettyThreadQueueMinSize + ", max: " + jettyThreadQueueMaxSize);
    }
    
    private void impostaParametriSicurezza() throws IOException {
        //Configurazione sui client: accettare anche connessioni non sicure (con certificati autofirmati)
        ProtocolSocketFactory easy = new EasySSLProtocolSocketFactory();
        Protocol protocol = new Protocol("https", easy, 8443); //TODO [Michele]: verificare come impostare questa porta
        Protocol.registerProtocol("https", protocol);

        //Configurazione sul server: parametri per l'acquisizione dei dati del certificato
        
        String fileKeyStore = ConfigurazioneStatico.getInstance().getFileKeyStore();
        String password = ConfigurazioneStatico.getInstance().getPasswordKeyStore();

        File sorgente = new File(fileKeyStore);

        KeyStoreParameters keyStoreParameters = new KeyStoreParameters();
        keyStoreParameters.setResource(sorgente.getPath());
        keyStoreParameters.setPassword(password);

        KeyManagersParameters keyManagersParameters = new KeyManagersParameters();
        keyManagersParameters.setKeyStore(keyStoreParameters);
        keyManagersParameters.setKeyPassword(password);

        SSLContextParameters sslContextParameters = new SSLContextParameters();
        sslContextParameters.setKeyManagers(keyManagersParameters);

        JettyHttpComponent componentJetty = (JettyHttpComponent) context.getComponent("jetty", JettyHttpComponent.class);
        componentJetty.setSslContextParameters(sslContextParameters);
    }
}
