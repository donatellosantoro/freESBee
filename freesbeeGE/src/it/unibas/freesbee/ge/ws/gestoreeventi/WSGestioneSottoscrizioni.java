package it.unibas.freesbee.ge.ws.gestoreeventi;

import it.unibas.freesbee.ge.modello.CategoriaEventiEsterna;
import it.unibas.freesbee.ge.modello.CategoriaEventiInterna;
import it.unibas.freesbee.ge.modello.ICategoriaEventi;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiEsterna;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiInterna;
import it.unibas.freesbee.ge.persistenza.IDAOGestoreEventi;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreEsterno;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreInterno;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrittore;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOUtilHibernate;
import java.util.List;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;

public class WSGestioneSottoscrizioni extends RouteBuilder {

    private Log logger = LogFactory.getLog(this.getClass());
    private IDAOSottoscrittore daoSottoscrittore;
    private IDAOCategoriaEventiInterna daoCategoriaEventiInterna;
    private IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna;
    private IDAOPubblicatoreInterno daoPubblicatoreInterno;
    private IDAOPubblicatoreEsterno daoPubblicatoreEsterno;
    private IDAOGestoreEventi daoGestoreEventi;

    @Override
    public void configure() throws Exception {
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        String nome = "";
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            List<CategoriaEventiInterna> listaCategoriaEventiInterna = daoCategoriaEventiInterna.findAll();
            List<CategoriaEventiEsterna> listaCategoriaEventiEsterna = daoCategoriaEventiEsterna.findAll();
            sessionFactory.getCurrentSession().getTransaction().commit();

            for (CategoriaEventiInterna categoriaEvento : listaCategoriaEventiInterna) {
                nome = categoriaEvento.getNome();
                RouteBuilder wsAvviato = new HttpSottoscrizione(nome, daoCategoriaEventiInterna, daoCategoriaEventiEsterna, daoSottoscrittore, daoPubblicatoreInterno, getDaoPubblicatoreEsterno(), daoGestoreEventi);

                this.getContext().addRoutes(wsAvviato);
            }

            for (CategoriaEventiEsterna categoriaEvento : listaCategoriaEventiEsterna) {
                nome = categoriaEvento.getNome();
                if (!nome.equals(ICategoriaEventi.GE_CONTROL_PROTOCOL)) {
                    RouteBuilder wsAvviato = new HttpSottoscrizione(nome, daoCategoriaEventiInterna, daoCategoriaEventiEsterna, daoSottoscrittore, daoPubblicatoreInterno, getDaoPubblicatoreEsterno(), daoGestoreEventi);

                    this.getContext().addRoutes(wsAvviato);
                }
            }

        } catch (Exception ex) {
            if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().rollback();
            }
            logger.error("Impossibile avviare il web services per la categoria di evento " + nome);
            logger.error(ex.getMessage());
        }

    }

    public IDAOSottoscrittore getDaoSottoscrittore() {
        return daoSottoscrittore;
    }

    public void setDaoSottoscrittore(IDAOSottoscrittore daoSottoscrittore) {
        this.daoSottoscrittore = daoSottoscrittore;
    }

    public IDAOCategoriaEventiInterna getDaoCategoriaEventiInterna() {
        return daoCategoriaEventiInterna;
    }

    public void setDaoCategoriaEventiInterna(IDAOCategoriaEventiInterna daoCategoriaEventiInterna) {
        this.daoCategoriaEventiInterna = daoCategoriaEventiInterna;
    }

    public IDAOCategoriaEventiEsterna getDaoCategoriaEventiEsterna() {
        return daoCategoriaEventiEsterna;
    }

    public void setDaoCategoriaEventiEsterna(IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna) {
        this.daoCategoriaEventiEsterna = daoCategoriaEventiEsterna;
    }

    public IDAOPubblicatoreInterno getDaoPubblicatoreInterno() {
        return daoPubblicatoreInterno;
    }

    public void setDaoPubblicatoreInterno(IDAOPubblicatoreInterno daoPubblicatoreInterno) {
        this.daoPubblicatoreInterno = daoPubblicatoreInterno;
    }

    public IDAOGestoreEventi getDaoGestoreEventi() {
        return daoGestoreEventi;
    }

    public void setDaoGestoreEventi(IDAOGestoreEventi daoGestoreEventi) {
        this.daoGestoreEventi = daoGestoreEventi;
    }

    public IDAOPubblicatoreEsterno getDaoPubblicatoreEsterno() {
        return daoPubblicatoreEsterno;
    }

    public void setDaoPubblicatoreEsterno(IDAOPubblicatoreEsterno daoPubblicatoreEsterno) {
        this.daoPubblicatoreEsterno = daoPubblicatoreEsterno;
    }
}