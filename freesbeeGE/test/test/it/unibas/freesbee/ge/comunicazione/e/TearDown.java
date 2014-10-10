package test.it.unibas.freesbee.ge.comunicazione.e;

import it.unibas.freesbee.ge.modello.ICategoriaEventi;
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

        logger.info("Ripulitura Pubblicatori Esterni");
        DAOMock.ripulisciPubblicatoreEsterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_T, DAOMock.CATEGORIA_EVENTI_ESTERNA_NON_CONFERMATA_ATTIVA_6);
        DAOMock.ripulisciPubblicatoreEsterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_T, DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_7);
        DAOMock.ripulisciPubblicatoreEsterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_T, DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_9);
        DAOMock.ripulisciPubblicatoreEsterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_Q, DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_9);
        DAOMock.ripulisciPubblicatoreEsterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_O, DAOMock.CATEGORIA_EVENTI_ESTERNA_NON_CONFERMATA_ATTIVA_6);
        DAOMock.ripulisciPubblicatoreEsterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_Q, DAOMock.CATEGORIA_EVENTI_ESTERNA_NON_CONFERMATA_ATTIVA_8);

        logger.info("Ripulitura Sottoscrizioni Esterne");
        DAOMock.ripulisciSottoscrittoreSottoscrizioneEsterna(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_G, DAOMock.CATEGORIA_EVENTI_ESTERNA_NON_CONFERMATA_ATTIVA_6);

        logger.info("Ripulitura Pubblicatori Gestore Eventi");
        DAOMock.ripulisciPubblicatoreEsterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_GE, ICategoriaEventi.GE_CONTROL_PROTOCOL);
        DAOMock.ripulisciPubblicatoreEsterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_GE, DAOMock.CATEGORIA_EVENTI_ESTERNA_NON_CONFERMATA_ATTIVA_6);
        DAOMock.ripulisciPubblicatoreEsterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_GE, DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_7);
        DAOMock.ripulisciPubblicatoreEsterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_GE, DAOMock.CATEGORIA_EVENTI_ESTERNA_NON_CONFERMATA_ATTIVA_8);
        DAOMock.ripulisciPubblicatoreEsterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_GE, DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_9);
        DAOMock.ripulisciPubblicatoreEsterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_GE, DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_10);
        DAOMock.ripulisciPubblicatoreEsterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_NON_GE, DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_7);

        logger.info("Ripulitura Gestore Eventi");
        DAOMock.ripulisciGestoreEventi(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_GE);
        DAOMock.ripulisciGestoreEventi(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_NON_GE);

        logger.info("Ripulitura Catgorie Eventi Interne");
        DAOMock.ripulisciCategoriaEventiInterna(DAOMock.CATEGORIA_EVENTI_INTERNA_NON_ATTIVA_1);
        DAOMock.ripulisciCategoriaEventiInterna(DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_2);
        DAOMock.ripulisciCategoriaEventiInterna(DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_3);

        logger.info("Ripulitura Catgorie Eventi Esterne");
        DAOMock.ripulisciCategoriaEventiEsterna(DAOMock.CATEGORIA_EVENTI_ESTERNA_NON_CONFERMATA_NON_ATTIVA_4);
        DAOMock.ripulisciCategoriaEventiEsterna(DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_NON_ATTIVA_5);
        DAOMock.ripulisciCategoriaEventiEsterna(DAOMock.CATEGORIA_EVENTI_ESTERNA_NON_CONFERMATA_ATTIVA_6);
        DAOMock.ripulisciCategoriaEventiEsterna(DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_7);
        DAOMock.ripulisciCategoriaEventiEsterna(DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_9);
        DAOMock.ripulisciCategoriaEventiEsterna(DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_10);
        DAOMock.ripulisciCategoriaEventiEsterna(DAOMock.CATEGORIA_EVENTI_ESTERNA_NON_CONFERMATA_ATTIVA_8);

        DAOUtilHibernate.commit();

    }
}

