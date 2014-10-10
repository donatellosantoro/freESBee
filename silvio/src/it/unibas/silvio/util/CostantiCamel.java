package it.unibas.silvio.util;

public class CostantiCamel {
    
    /* COSTATI CAMEL - FRUITORE */
    public static final String ENRICHER_DATI_PARZIALI_COMPLETI_FRUITORE = "seda:EnricherDatiParzialiCompletiFruitore";
    public static final String ENRICHER_OPERATION_FRUITORE = "seda:EnricherOperationFruitore";
    public static final String PRODUCER_COMPLETI_SOAP_FRUITORE = "seda:ProducerCompletiSoapFruitore";
    public static final String HTTP_USCITA_FRUITORE = "seda:HttpUscitaFruitore";
    public static final String HTTP_GESTISCI_USCITA_FRUITORE = "seda:HttpGestisciUscitaFruitore";
    public static final String TRASFORMER_SOAP_SQL_FRUITORE = "seda:TrasformerSoapSqlFruitore";
    public static final String MESSAGE_STORE_DB_FRUITORE = "seda:MessageStoreDbFruitore";
    public static final String MESSAGE_STORE_RICHIESTA_FRUITORE = "seda:MessageStoreRichiestaFruitore";
    public static final String POLLING_CHANNEL_FRUITORE = "seda:PollingChannelFruitore";
    
    /* COSTATI CAMEL - EROGATORE */
    public static final String EVENT_DRIVER_CONSUMER_SBLOCCO_RISPOSTA = "seda:EventDrivenConsumerSbloccoRisposta";
    public static final String TRASFORMER_SOAP_SQL_EROGATORE = "seda:TrasformerSoapSqlErogatore";
    public static final String ENRICHER_OPERATION_EROGATORE = "seda:EnricherOperationErogatore";
    public static final String MESSAGE_STORE_DB_EROGATORE = "seda:MessageStoreDbErogatore";
    public static final String ENRICHER_DATI_PARZIALI_COMPLETI_EROGATORE = "seda:EnricherDatiParzialiCompletiErogatore";
    public static final String PRODUCER_COMPLETI_SOAP_EROGATORE = "seda:ProducerCompletiSoapErogatore";
    public static final String HTTP_USCITA_EROGATORE = "seda:HttpUscitaErogatore";
    public static final String DETOUR_GESTIONE_RISPOSTA_EROGATORE = "seda:DetourGestioneRispostaErogatore";
    public static final String GESTIONE_RISPOSTA_SINCRONA_EROGATORE = "seda:DetourGestioneRispostaErogatoreSincrona";
    public static final String DETOUR_USCITA_RISPOSTA_EROGATORE = "seda:DetourUscitaRispostaErogatore";
    public static final String POLLING_CHANNEL_EROGATORE = "seda:PollingChannelErogatore";
    public static final String EVENT_DRIVEN_CONSUMER_SBLOCCO_RISPOSTA_MANUALE = "seda:EventDrivenConsumerSbloccoRispostaManuale";
    
    
    public static final String RISPOSTA_ACK_FRUITORE = "seda:RispostaAckFruitore";
    public static final String RISPOSTA_ACK_EROGATORE = "seda:RichiestaAckErogatore";

}
