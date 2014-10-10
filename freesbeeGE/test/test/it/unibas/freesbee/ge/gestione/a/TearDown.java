package test.it.unibas.freesbee.ge.gestione.a;

import it.unibas.freesbee.ge.modello.Configurazione;
import it.unibas.freesbee.ge.modello.ConfigurazioniFactory;
import test.it.unibas.freesbee.ge.dao.*;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOUtilHibernate;
import org.apache.camel.ContextTestSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TearDown extends ContextTestSupport {

    protected Log logger = LogFactory.getLog(this.getClass());

    public void testTearDown() throws Exception {
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

        DAOUtilHibernate.beginTransaction();

        logger.info("Ripulitura Pubblicatori Gestore Eventi");
        Configurazione conf = ConfigurazioniFactory.getConfigurazioneIstance();
        DAOMock.ripulisciPubblicatoreInterno(conf.getTipoGestoreEventi(), conf.getNomeGestoreEventi(), DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_2);
        DAOMock.ripulisciPubblicatoreInterno(conf.getTipoGestoreEventi(), conf.getNomeGestoreEventi(), DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_3);

        logger.info("Ripulitura Catgorie Eventi Interne");
        DAOMock.ripulisciCategoriaEventiInterna(DAOMock.CATEGORIA_EVENTI_INTERNA_NON_ATTIVA_1);
        DAOMock.ripulisciCategoriaEventiInterna(DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_2);
        DAOMock.ripulisciCategoriaEventiInterna(DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_3);


        DAOUtilHibernate.commit();

    }
}

