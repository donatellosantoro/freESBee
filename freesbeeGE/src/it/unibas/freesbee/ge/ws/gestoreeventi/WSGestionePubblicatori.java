package it.unibas.freesbee.ge.ws.gestoreeventi;

import it.unibas.freesbee.ge.modello.CategoriaEventiInterna;
import it.unibas.freesbee.ge.modello.ICategoriaEventi;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiInterna;
import it.unibas.freesbee.ge.persistenza.IDAOEventoPubblicatoInterno;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreInterno;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrizioneInterna;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOUtilHibernate;
import java.util.List;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;

public class WSGestionePubblicatori extends RouteBuilder {

    private Log logger = LogFactory.getLog(this.getClass());
    private IDAOPubblicatoreInterno daoPubblicatoreInterno;
    private IDAOCategoriaEventiInterna daoCategoriaEventiInterna;
    private IDAOEventoPubblicatoInterno daoEventoPubblicatoInterno;
    private IDAOSottoscrizioneInterna daoSottoscrizioneInterna;

    @Override
    public void configure() throws Exception {
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        String nome = "";
        try {
            sessionFactory.getCurrentSession().beginTransaction();

            List<CategoriaEventiInterna> listaCategoriaEvento = daoCategoriaEventiInterna.findAll();
            sessionFactory.getCurrentSession().getTransaction().commit();

            for (CategoriaEventiInterna categoriaEvento : listaCategoriaEvento) {
                nome = categoriaEvento.getNome();
                if (!nome.equals(ICategoriaEventi.GE_CONTROL_PROTOCOL)) {
                    RouteBuilder wsAvviato = new HttpPubblicatore(nome, daoPubblicatoreInterno, daoCategoriaEventiInterna, daoSottoscrizioneInterna, daoEventoPubblicatoInterno);
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

    public IDAOPubblicatoreInterno getDaoPubblicatoreInterno() {
        return daoPubblicatoreInterno;
    }

    public void setDaoPubblicatoreInterno(IDAOPubblicatoreInterno daoPubblicatoreInterno) {
        this.daoPubblicatoreInterno = daoPubblicatoreInterno;
    }

    public IDAOCategoriaEventiInterna getDaoCategoriaEventiInterna() {
        return daoCategoriaEventiInterna;
    }

    public void setDaoCategoriaEventiInterna(IDAOCategoriaEventiInterna daoCategoriaEventiInterna) {
        this.daoCategoriaEventiInterna = daoCategoriaEventiInterna;
    }

    public IDAOSottoscrizioneInterna getDaoSottoscrizioneInterna() {
        return daoSottoscrizioneInterna;
    }

    public void setDaoSottoscrizioneInterna(IDAOSottoscrizioneInterna daoSottoscrizioneInterna) {
        this.daoSottoscrizioneInterna = daoSottoscrizioneInterna;
    }

    public IDAOEventoPubblicatoInterno getDaoEventoPubblicatoInterno() {
        return daoEventoPubblicatoInterno;
    }

    public void setDaoEventoPubblicatoInterno(IDAOEventoPubblicatoInterno daoEventoPubblicatoInterno) {
        this.daoEventoPubblicatoInterno = daoEventoPubblicatoInterno;
    }
}