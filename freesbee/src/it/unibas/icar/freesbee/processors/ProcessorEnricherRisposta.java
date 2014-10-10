package it.unibas.icar.freesbee.processors;

import it.unibas.guicefreesbee.ContextStartup;
import it.unibas.icar.freesbee.modello.BustaEGov;
import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.utilita.CostantiBusta;
import it.unibas.icar.freesbee.utilita.UtilitaDate;
import java.util.Date;
import java.util.Map;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProcessorEnricherRisposta implements Processor {

    private static Log logger = LogFactory.getLog(ProcessorEnricherRisposta.class);

    public ProcessorEnricherRisposta() {
    }

    public void process(Exchange exchange) throws Exception {
        //ContextStartup.aggiungiThread(this.getClass().getName());
        Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiBusta.MESSAGGIO);

        Messaggio messaggioRichiesta = new Messaggio();
        messaggioRichiesta.setErogatore(messaggio.getErogatore());
        messaggioRichiesta.setTipoErogatore(messaggio.getTipoErogatore());
        messaggioRichiesta.setFruitore(messaggio.getFruitore());
        messaggioRichiesta.setTipoFruitore(messaggio.getTipoFruitore());
        messaggioRichiesta.setOraRegistrazione(messaggio.getOraRegistrazione());
        messaggioRichiesta.setTempo(messaggio.getTempo());
        exchange.setProperty(CostantiBusta.MESSAGGIO_RICHIESTA, messaggioRichiesta);

        String identificatoreRichiesta = messaggio.getIdMessaggio();
        String fruitoreRisposta = messaggio.getErogatore();
        String tipoFruitoreRisposta = messaggio.getTipoErogatore();
        String erogatoreRisposta = messaggio.getFruitore();
        String tipoErogatoreRisposta = messaggio.getTipoFruitore();

        String identificatoreRisposta = BustaEGov.getInstance().getNuovoIdentificatore(fruitoreRisposta);

        messaggio.setFruitore(fruitoreRisposta);
        messaggio.setTipoFruitore(tipoFruitoreRisposta);
        messaggio.setErogatore(erogatoreRisposta);
        messaggio.setTipoErogatore(tipoErogatoreRisposta);
        messaggio.setIdMessaggio(identificatoreRisposta);
        messaggio.setRiferimentoMessaggio(identificatoreRichiesta);
        messaggio.setOraRegistrazione(UtilitaDate.formattaDataEOra(new Date()));
    }
}
