package it.unibas.icar.freesbee.utilita;

import it.unibas.icar.freesbee.modello.ConfigurazioneStatico;


public class FreesbeeCamel {
    
    // Endpoint HTTP
    // public static final String JETTY_HTTP_8192 = "jetty:http://0.0.0.0:8192/";
    
    // public static final String JETTY_HTTP_8192_PA = "jetty:http://0.0.0.0:8192/PA/";
    
    // alternative ai seda --> vm
    
    // Endpoint Imbustamento
    public static final String SEDA_ARGS = "?waitForTaskToComplete=Never&concurrentConsumers=" + ConfigurazioneStatico.getInstance().getConcurrentConsumer();
    
    public static final String SEDA_FILTRO_AUTENTICAZIONE = "seda:FiltroAutenticazione" + SEDA_ARGS;

    public static final String SEDA_POLLING_CONSUMER_PORTA_DELEGATA_CHANNEL = "seda:PollingConsumerPortaDelegataChannel";
    
    public static String DIRECT_POLLING_CONSUMER_PORTA_DELEGATA_CHANNEL = "direct:PollingConsumerPortaDelegataChannel";
    
    public static final String SEDA_ENRICHER_ACCORDO_SERVIZIO = "seda:EnricherAccordoServizio" + SEDA_ARGS;

    public static final String SEDA_PREIMBUSTAMENTO_RICHIESTA = "seda:PreImbustamentoRichiesta" + SEDA_ARGS;

    public static final String SEDA_ENRICHER_IDENTIFICATORE_EROGATORE = "seda:EnricherIdentificatoreErogatore" + SEDA_ARGS;

    public static final String SEDA_ENRICHER_PREIMBUSTAMENTO_RISPOSTA = "seda:EnricherPreImbustamentoRisposta" + SEDA_ARGS;
    
    public static final String SEDA_PREIMBUSTAMENTO_RISPOSTA = "seda:PreImbustamentoRisposta" + SEDA_ARGS;
    
    public static final String SEDA_CONTENT_BASED_ROUTER_IMBUSTAMENTO = "seda:ContentBasedRouterImbustamento" + SEDA_ARGS;
    
    public static final String SEDA_HTTP_INOLTRO_BUSTA_EGOV = "seda:HttpInoltroBustaEGov" + SEDA_ARGS;
    
    public static final String SEDA_DETOUR_COLLABORAZIONE_IMBUSTAMENTO = "seda:DetourCollaborazioneImbustamento" + SEDA_ARGS;
    
//    public static final String SEDA_ENRICHER_FIRMA_DIGITALE_IMBUSTAMENTO = "seda:EnricherFirmaDigitaleImbustamento" + SEDA_ARGS;
    
    public static final String SEDA_WIRETAP_COLLABORAZIONE_IMBUSTAMENTO = "seda:WireTapCollaborazioneImbustamento" + SEDA_ARGS;

//    public static final String SEDA_DETOUR_RISCONTRI_IMBUSTAMENTO = "seda:DetourRiscontriImbustamento" + SEDA_ARGS;
    
//    public static final String SEDA_DETOUR_SEQUENZA_IMBUSTAMENTO = "seda:DetourSequenzaImbustamento" + SEDA_ARGS;
    
    public static final String SEDA_WIRETAP_TRACCIAMENTO_IMBUSTAMENTO = "seda:WireTapTracciamentoImbustamento" + SEDA_ARGS;
    
    public static final String SEDA_ENVELOPE_WRAPPER_EGOV_IMBUSTAMENTO = "seda:EnvelopeWrapperEgovImbustamento" + SEDA_ARGS;

//    public static final String SEDA_FILTRO_AUTORIZZAZIONE_IMBUSTAMENTO = "seda:FiltroAutorizzazioneImbustamento" + SEDA_ARGS;
    
    public static final String SEDA_PROCESSA_RISPOSTA_PORTA_DOMINIO_EROGATORE = "seda:ProcessaRispostaPortaDominioErogatore" + SEDA_ARGS;
    
    public static final String SEDA_PROCESSA_RISPOSTA_PORTA_DOMINIO_EROGATORE_NICA = "seda:ProcessaRispostaPortaDominioErogatoreNica" + SEDA_ARGS;
    
    public static final String SEDA_PROCESSA_RISPOSTA_PORTA_DOMINIO_EROGATORE_PDD = "seda:ProcessaRispostaPortaDominioErogatorePdd" + SEDA_ARGS;
    
    public static final String SEDA_PROCESSA_RISPOSTA_SERVIZIO_APPLICATIVO = "seda:ProcessaRispostaServizioApplicativo" + SEDA_ARGS;
    
    // Endpoint Sbustamento    
    public static final String SEDA_HTTP_CONSEGNA_BUSTE_SOAP = "seda:HttpConsegnaBusteSoap" + SEDA_ARGS;
    
    public static final String SEDA_PRESBUSTAMENTO_RICHIESTA = "seda:PreSbustamentoRichiesta" + SEDA_ARGS;
    
    public static final String SEDA_POLLING_CONSUMER_PORTA_APPLICATIVA_CHANNEL = "seda:PollingConsumerPortaApplicativaChannel";
    
    public static String DIRECT_POLLING_CONSUMER_PORTA_APPLICATIVA_CHANNEL = "direct:PollingConsumerPortaApplicativaChannel";
    
    public static final String SEDA_DETOUR_COLLABORAZIONE_SBUSTAMENTO = "seda:DetourCollaborazioneSbustamento" + SEDA_ARGS;
    
    public static final String SEDA_WIRETAP_COLLABORAZIONE_SBUSTAMENTO = "seda:WireTapCollaborazioneSbustamento" + SEDA_ARGS;
    
    public static final String SEDA_WIRETAP_TRACCIAMENTO_SBUSTAMENTO = "seda:WireTapTracciamentoSbustamento" + SEDA_ARGS;
    
//    public static final String SEDA_DETOUR_RISCONTRI_SBUSTAMENTO = "seda:DetourRiscontriSbustamento" + SEDA_ARGS;
    
//    public static final String SEDA_DETOUR_SEQUENZA_SBUSTAMENTO = "seda:DetourSequenzaSbustamento" + SEDA_ARGS;
    
    public static final String SEDA_ENVELOPE_WRAPPER_EGOV_SBUSTAMENTO = "seda:EnvelopeWrapperEgovSbustamento" + SEDA_ARGS;
    
//    public static final String SEDA_FILTRO_AUTORIZZAZIONE_SBUSTAMENTO = "seda:FiltroAutorizzazioneSbustamento" + SEDA_ARGS;
    
//    public static final String SEDA_FILTRO_VALIDAZIONE_SBUSTAMENTO = "seda:FiltroValidazioneSbustamento" + SEDA_ARGS;
    
    public static final String SEDA_PRESBUSTAMENTO_RISPOSTA = "seda:PreSbustamentoRisposta" + SEDA_ARGS;
    
    
    // 
    public static final String SEDA_ENVELOPE_WRAPPER_TEST = "seda:EnvelopeWrapperTest" + SEDA_ARGS;
    
    public static final String SEDA_ENRICHER_TEST = "seda:EnricherTest" + SEDA_ARGS;
    
    // Endpoint ACK & FAULT
    public static final String SEDA_RICHIESTA_ACK = "seda:RichiestaAck" + SEDA_ARGS;

    public static final String SEDA_RISPOSTA_ACK = "seda:RispostaAck" + SEDA_ARGS;
    
    public static final String SEDA_CONTENT_BASED_ROUTER_SBUSTAMENTO = "seda:ContentBasedRouterSbustamento" + SEDA_ARGS;
    
    // Endpoint NICA
    public static final String SEDA_CONTENT_BASED_ROUTER_NICA = "seda:ContentBasedRouterNica" + SEDA_ARGS;
    public static final String SEDA_ENRICHER_NICA = "seda:EnricherNica" + SEDA_ARGS;
    
}
