package test.it.unibas.freesbee.ge.funzionali.i;

import it.unibas.freesbee.ge.modello.Configurazione;
import it.unibas.freesbee.ge.modello.ConfigurazioniFactory;
import test.it.unibas.freesbee.ge.dao.*;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOUtilHibernate;
import org.apache.camel.ContextTestSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SetUp extends ContextTestSupport {

    protected Log logger = LogFactory.getLog(this.getClass());

    public void testSetUp() throws Exception {
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        DAOUtilHibernate.beginTransaction();

        logger.info("Inserimento Catgorie Eventi Interne");
        DAOMock.inserisciCategoriaEventiIntenra(DAOMock.CATEGORIA_EVENTI_INTERNA_NON_ATTIVA_1, false);
        DAOMock.inserisciCategoriaEventiIntenra(DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_2, true);
        DAOMock.inserisciCategoriaEventiIntenra(DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_3, true);
        DAOMock.inserisciCategoriaEventiIntenra(DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_0, true);

        logger.info("Inserimento Pubblicatore Interno Gestore Eventi");
        Configurazione conf = ConfigurazioniFactory.getConfigurazioneIstance();
        DAOMock.inserisciPubblicatoreInterno(conf.getTipoGestoreEventi(), conf.getNomeGestoreEventi(), DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_2);
        DAOMock.inserisciPubblicatoreInterno(conf.getTipoGestoreEventi(), conf.getNomeGestoreEventi(), DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_3);
        DAOMock.inserisciPubblicatoreInterno(conf.getTipoGestoreEventi(), conf.getNomeGestoreEventi(), DAOMock.CATEGORIA_EVENTI_INTERNA_NON_ATTIVA_1);

        logger.info("Inserimento Pubblicatore Interno");
        DAOMock.inserisciPubblicatoreInterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_A, DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_2);
        DAOMock.inserisciPubblicatoreInterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_B, DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_2);


        logger.info("Inserisci Gestore Eventi");
        DAOMock.inserisciGestoreEventi(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_GE);


        DAOUtilHibernate.commit();

    }
}

