package test.it.unibas.freesbee.ge.funzionali.l;

import it.unibas.freesbee.ge.modello.ICategoriaEventi;
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

//        logger.info("Inserimento Pubblicatore Interno Gestore Eventi");
//        Configurazione conf = ConfigurazioniFactory.getConfigurazioneIstance();
//        DAOMock.inserisciPubblicatoreInterno(conf.getTipoGestoreEventi(), conf.getNomeGestoreEventi(), DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_2);
//        DAOMock.inserisciPubblicatoreInterno(conf.getTipoGestoreEventi(), conf.getNomeGestoreEventi(), DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_3);
//        DAOMock.inserisciPubblicatoreInterno(conf.getTipoGestoreEventi(), conf.getNomeGestoreEventi(), DAOMock.CATEGORIA_EVENTI_INTERNA_NON_ATTIVA_1);
//
//
        logger.info("Inserimento Catgorie Eventi Esterne");
        DAOMock.inserisciCategoriaEventiEsterna(DAOMock.CATEGORIA_EVENTI_ESTERNA_NON_CONFERMATA_NON_ATTIVA_4, true, false);
        DAOMock.inserisciCategoriaEventiEsterna(DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_NON_ATTIVA_5, false, false);
        DAOMock.inserisciCategoriaEventiEsterna(DAOMock.CATEGORIA_EVENTI_ESTERNA_NON_CONFERMATA_ATTIVA_6, true, true);
        DAOMock.inserisciCategoriaEventiEsterna(DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_7, false, true);
        DAOMock.inserisciCategoriaEventiEsterna(DAOMock.CATEGORIA_EVENTI_ESTERNA_NON_CONFERMATA_ATTIVA_8, true, true);
        DAOMock.inserisciCategoriaEventiEsterna(DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_9, false, true);
        DAOMock.inserisciCategoriaEventiEsterna(DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_10, false, true);

        logger.info("Inserisci Gestore Eventi");
        DAOMock.inserisciGestoreEventi(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_GE);

        logger.info("Inserimento Pubblicatori Gestore Eventi");
        DAOMock.inserisciPubblicatoreEsterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_GE, ICategoriaEventi.GE_CONTROL_PROTOCOL);
        DAOMock.inserisciPubblicatoreEsterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_GE, DAOMock.CATEGORIA_EVENTI_ESTERNA_NON_CONFERMATA_NON_ATTIVA_4);
        DAOMock.inserisciPubblicatoreEsterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_GE, DAOMock.CATEGORIA_EVENTI_ESTERNA_NON_CONFERMATA_ATTIVA_6);
        DAOMock.inserisciPubblicatoreEsterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_GE, DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_7);
        DAOMock.inserisciPubblicatoreEsterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_GE, DAOMock.CATEGORIA_EVENTI_ESTERNA_NON_CONFERMATA_ATTIVA_8);
//        DAOMock.inserisciPubblicatoreEsterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_GE, DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_9);
        DAOMock.inserisciPubblicatoreEsterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_GE, DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_10);

//        logger.info("Inserimento Pubblicatori Esterni");
//        DAOMock.inserisciPubblicatoreEsterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_A, DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_7);
//        DAOMock.inserisciPubblicatoreEsterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_B, DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_7);

        DAOUtilHibernate.commit();

    }
}

