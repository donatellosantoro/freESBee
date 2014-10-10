package test.it.unibas.freesbee.ge.gestione.b;

import it.unibas.freesbee.ge.modello.ICategoriaEventi;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiInterna;
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

        logger.info("Ripulitura Pubblicatore Interno");
        DAOMock.ripulisciPubblicatoreInterno(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_A, DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_3);

        logger.info("Ripulitura Sottoscrizione Interna");
        DAOMock.ripulisciSottoscrittoreSottoscrizioneIntera(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_G, DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_2);
        DAOMock.ripulisciSottoscrittoreSottoscrizioneIntera(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_H, DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_2);
        DAOMock.ripulisciSottoscrittoreSottoscrizioneIntera(DAOMock.TIPO_SOGGETTO_SPC, DAOMock.NOME_SOGGETTO_G, ICategoriaEventi.GE_CONTROL_PROTOCOL);

        logger.info("Ripulitura Catgorie Eventi Interne");
        DAOMock.ripulisciCategoriaEventiInterna(DAOMock.CATEGORIA_EVENTI_INTERNA_NON_ATTIVA_1);
        DAOMock.ripulisciCategoriaEventiInterna(DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_2);
        DAOMock.ripulisciCategoriaEventiInterna(DAOMock.CATEGORIA_EVENTI_INTERNA_ATTIVA_3);



        DAOUtilHibernate.commit();

    }
}

