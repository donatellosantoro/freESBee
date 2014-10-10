package test.it.unibas.freesbee.ge.funzionali.m;

import test.it.unibas.freesbee.ge.comunicazione.g.*;
import it.unibas.freesbee.ge.modello.Configurazione;
import it.unibas.freesbee.ge.modello.ConfigurazioniFactory;
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

        logger.info("Ripulitura Pubblicatori Interni");
        DAOMock.ripulisciPubblicatoreInterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_A, DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_0);
        DAOMock.ripulisciPubblicatoreInterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_A, DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_2);

        logger.info("Ripulitura Sottoscrizioni Interne");
        DAOMock.ripulisciSottoscrittoreSottoscrizioneIntera(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_D, DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_0);
        DAOMock.ripulisciSottoscrittoreSottoscrizioneIntera(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_D, DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_2);

        logger.info("Ripulitura Pubblicatori Gestore Eventi");
        Configurazione conf = ConfigurazioniFactory.getConfigurazioneIstance();
        DAOMock.ripulisciPubblicatoreInterno(conf.getTipoGestoreEventi(), conf.getNomeGestoreEventi(), DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_0);
        DAOMock.ripulisciPubblicatoreInterno(conf.getTipoGestoreEventi(), conf.getNomeGestoreEventi(), DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_2);

        logger.info("Ripulitura Sottoscrizioni Esterne");
        DAOMock.ripulisciSottoscrittoreSottoscrizioneEsterna(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_E, DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_7);
        DAOMock.ripulisciSottoscrittoreSottoscrizioneEsterna(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_E, DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_9);


        logger.info("Ripulitura Pubblicatori Gestore Eventi");
        DAOMock.ripulisciPubblicatoreEsterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_GE, ICategoriaEventi.GE_CONTROL_PROTOCOL);
        DAOMock.ripulisciPubblicatoreEsterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_GE, DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_7);
        DAOMock.ripulisciPubblicatoreEsterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_GE, DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_9);

        logger.info("Ripulitura Pubblicatori Esterni");
        DAOMock.ripulisciPubblicatoreEsterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_B, DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_7);
        DAOMock.ripulisciPubblicatoreEsterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_B, DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_9);

        logger.info("Ripulitura Catgorie Eventi Interne");
        DAOMock.ripulisciCategoriaEventiInterna(DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_0);
        DAOMock.ripulisciCategoriaEventiInterna(DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_2);

        logger.info("Ripulitura Gestore Eventi");
        DAOMock.ripulisciGestoreEventi(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_GE);


        logger.info("Ripulitura Catgorie Eventi Esterne da Confermare");
        DAOMock.ripulisciCategoriaEventiEsterna(DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_7);
        DAOMock.ripulisciCategoriaEventiEsterna(DAOMock.CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_9);

        DAOUtilHibernate.commit();

    }
}

