package it.unibas.silvio.fruitore;

import it.unibas.silvio.modello.ConfigurazioneStatico;
import it.unibas.silvio.persistenza.IDAODati;
import it.unibas.silvio.persistenza.IDAOIstanzaAccordoDiCollaborazione;
import it.unibas.silvio.processor.ProcessorEnricherAttachment;
import it.unibas.silvio.processor.ProcessorEnricherDatiParzialiCompletiFruitore;
import it.unibas.silvio.processor.ProcessorEnricherHeaderRichiestaSilvio;
import it.unibas.silvio.processor.ProcessorHibernateBegin;
import it.unibas.silvio.processor.ProcessorHibernateCommit;
import it.unibas.silvio.processor.ProcessorHibernateRollback;
import it.unibas.silvio.processor.ProcessorLog;
import it.unibas.silvio.processor.ProcessorSbloccaPollingConsumerFruitore;
import it.unibas.silvio.util.CostantiCamel;
import it.unibas.silvio.util.CostantiSilvio;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EnricherDatiParzialiCompletiFruitore extends RouteBuilder{
    
    private Log logger = LogFactory.getLog(this.getClass());
    private IDAOIstanzaAccordoDiCollaborazione daoIstanzaAccordo;
    private IDAODati daoDati;

    @Override
    public void configure() throws Exception {
        this.from(CostantiCamel.ENRICHER_DATI_PARZIALI_COMPLETI_FRUITORE)
                .thread(ConfigurazioneStatico.getInstance().getThreadPool())
                .tryBlock()
                    .process(new ProcessorLog(this.getClass()))
                    .process(new ProcessorHibernateBegin())
                    .process(new ProcessorEnricherDatiParzialiCompletiFruitore(daoIstanzaAccordo,daoDati))
                    .process(new ProcessorEnricherHeaderRichiestaSilvio())
                    .process(new ProcessorEnricherAttachment(CostantiSilvio.MESSAGGIO_RICHIESTA))
                    .process(new ProcessorHibernateCommit())
                    .to(CostantiCamel.PRODUCER_COMPLETI_SOAP_FRUITORE)
                .handle(Exception.class)      
                    .process(new ProcessorSbloccaPollingConsumerFruitore(true))
                    .process(new ProcessorHibernateRollback())                      
                .end();
    }

    public IDAOIstanzaAccordoDiCollaborazione getDaoIstanzaAccordo() {
        return daoIstanzaAccordo;
    }

    public void setDaoIstanzaAccordo(IDAOIstanzaAccordoDiCollaborazione daoIstanzaAccordo) {
        this.daoIstanzaAccordo = daoIstanzaAccordo;
    }

    public IDAODati getDaoDati() {
        return daoDati;
    }

    public void setDaoDati(IDAODati daoDati) {
        this.daoDati = daoDati;
    }
    
    

}
