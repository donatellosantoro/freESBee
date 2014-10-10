package it.unibas.silvio.fruitore;

import it.unibas.silvio.modello.*;
import it.unibas.silvio.persistenza.*;
import it.unibas.silvio.processor.ProcessorHibernateBegin;
import it.unibas.silvio.processor.ProcessorHibernateCommit;
import it.unibas.silvio.processor.ProcessorHibernateRollback;
import it.unibas.silvio.processor.ProcessorLog;
import it.unibas.silvio.processor.ProcessorRispostaFruitore;
import it.unibas.silvio.processor.ProcessorSOAPReader;
import it.unibas.silvio.processor.ProcessorSbloccaPollingConsumerFruitore;
import it.unibas.silvio.util.CostantiCamel;
import it.unibas.silvio.util.CostantiSilvio;
import it.unibas.silvio.util.SilvioUtil;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MessageStoreDBFruitore extends RouteBuilder {

    private Log logger = LogFactory.getLog(this.getClass());
    private IDAOMessaggio daoMessaggio;
    private IDAOConfigurazione daoConfigurazione;

    @Override
    public void configure() throws Exception {
        this.from(CostantiCamel.MESSAGE_STORE_DB_FRUITORE)        
            .thread(ConfigurazioneStatico.getInstance().getThreadPool())
            .tryBlock()        
                .process(new ProcessorLog(this.getClass()))
                .process(new ProcessorHibernateBegin())
                .process(new ProcessorEstraiBustaSoap())
                .process(new ProcessorMessageStoreRisposta())
                .process(new ProcessorRispostaFruitore(daoConfigurazione))
                .process(new ProcessorHibernateCommit())                
                .process(new ProcessorSbloccaPollingConsumerFruitore(false))
            .handle(Exception.class)
                .process(new ProcessorSbloccaPollingConsumerFruitore(true))
                .process(new ProcessorHibernateRollback())   
            .end();
    }

    private class ProcessorEstraiBustaSoap implements Processor {

        public void process(Exchange exchange) throws Exception {
            Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiSilvio.MESSAGGIO_RISPOSTA);
            Exchange exchangeInviare = exchange.copy();
            exchangeInviare.setProperty(CostantiSilvio.MESSAGGIO_RICHIESTA,messaggio);
            SilvioUtil.copiaIntestazioniHTTP(exchange.getIn(), exchangeInviare.getIn());
            new ProcessorSOAPReader().process(exchangeInviare);
        }

    }
    
    private class ProcessorMessageStoreRisposta implements Processor {

        public void process(Exchange exchange) throws Exception {
            Messaggio messaggioRichiesta = (Messaggio) exchange.getProperty(CostantiSilvio.MESSAGGIO_RICHIESTA);
            Messaggio messaggioRisposta = (Messaggio) exchange.getProperty(CostantiSilvio.MESSAGGIO_RISPOSTA);
            try {
                messaggioRichiesta.setMessaggioRisposta(messaggioRisposta);
                daoMessaggio.makePersistent(messaggioRichiesta);
                daoMessaggio.makePersistent(messaggioRisposta);
            } catch (Exception ex) {
                logger.error("Impossibile salvare il messaggio inviato " + ex);
                throw new SilvioException("Impossibile salvare il messaggio inviato " + ex);
            }
        }
    }

    public IDAOMessaggio getDaoMessaggio() {
        return daoMessaggio;
    }

    public void setDaoMessaggio(IDAOMessaggio daoMessaggio) {
        this.daoMessaggio = daoMessaggio;
    }

    public IDAOConfigurazione getDaoConfigurazione() {
        return daoConfigurazione;
    }

    public void setDaoConfigurazione(IDAOConfigurazione daoConfigurazione) {
        this.daoConfigurazione = daoConfigurazione;
    }   
}
