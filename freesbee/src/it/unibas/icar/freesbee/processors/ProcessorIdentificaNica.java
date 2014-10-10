package it.unibas.icar.freesbee.processors;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import it.unibas.icar.freesbee.modello.Configurazione;
import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.modello.Soggetto;
import it.unibas.icar.freesbee.persistenza.DBManager;
import it.unibas.icar.freesbee.utilita.CostantiBusta;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Singleton
public class ProcessorIdentificaNica implements Processor {

    private static Log logger = LogFactory.getLog(ProcessorIdentificaNica.class);
    @Inject
    private DBManager dbManager;

    public ProcessorIdentificaNica() {
    }

    public void process(Exchange exchange) throws Exception {
        //ContextStartup.aggiungiThread(this.getClass().getName());
        Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiBusta.MESSAGGIO);

        Configurazione configurazione = dbManager.getConfigurazione();
        boolean isNica = configurazione.isNICA();
        Soggetto configurazioneNomeNica = configurazione.getSoggettoErogatoreNICA();

        if (isNica && configurazioneNomeNica != null) {
            String erogatore = messaggio.getErogatore();
            String tipoErogatore = messaggio.getTipoErogatore();
            String nomeNica = configurazioneNomeNica.getNome();
            String tipoNica = configurazioneNomeNica.getTipo();
            if (erogatore.equals(nomeNica) && tipoErogatore.equals(tipoNica)) {
                //SIAMO UN NICA, MA IL MESSAGGIO è DESTINATO AD UNA NOSTRA PORTA DELEGATA
                isNica = false;
            }
        }

        messaggio.setNica(isNica);
        messaggio.setNomeNica(configurazione.getSoggettoErogatoreNICA());
        if (logger.isInfoEnabled()) logger.info("Processo il messaggio come un Nica? " + isNica);
        exchange.getIn().setHeader(CostantiBusta.RUOLO_NICA, isNica); //DEV'ESSERE NELL'HEADER

    }

    public DBManager getDbManager() {
        return dbManager;
    }

    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }
}
