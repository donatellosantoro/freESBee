package it.unibas.freesbee.ge.ws.gestoreeventi;

import it.unibas.freesbee.ge.modello.CategoriaEventiEsterna;
import it.unibas.freesbee.ge.modello.ISottoscrizione;
import it.unibas.freesbee.ge.modello.SottoscrizioneEsterna;
import it.unibas.freesbee.ge.processor.ProcessorGestioneSottoscrizioni;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProcessorInviaRichiestaInformazioniPubblicatori implements Processor {

    private Log logger = LogFactory.getLog(this.getClass());

    public void process(Exchange exchange) throws Exception {
        ISottoscrizione sottoscrizione = (ISottoscrizione) exchange.getProperty(ProcessorGestioneSottoscrizioni.SOTTOSCRIZIONE_ATTESA_PUBBLICATORI);

        if (sottoscrizione instanceof SottoscrizioneEsterna) {
            if (sottoscrizione != null) {
                logger.info("C'e' una sottoscrizione in attesa. Per confermare la sottoscrizione bisogna chiedere a quale gestore eventi appartengono i pubblicatori che non conosciamo");
                ClientGEControlProtocol.pubblicaRichiesteRicercaPubblicatori((SottoscrizioneEsterna) sottoscrizione);
            }
        }

        sottoscrizione = (ISottoscrizione) exchange.getProperty(ProcessorGestioneSottoscrizioni.SOTTOSCRIZIONE_ATTESA_CATEGORIA);

        if (sottoscrizione instanceof SottoscrizioneEsterna) {
            if (sottoscrizione != null) {
                logger.info("C'e' una sottoscrizione in attesa. Per confermare la sottoscrizione bisogna chiedere a quale gestore eventi appartiene la categoria che non conosciamo");
                CategoriaEventiEsterna cat = ((SottoscrizioneEsterna) sottoscrizione).getCategoriaEventi();
                ClientGEControlProtocol.pubblicaRicercaCategoriaEventi(cat);
            }
        }
    }
}