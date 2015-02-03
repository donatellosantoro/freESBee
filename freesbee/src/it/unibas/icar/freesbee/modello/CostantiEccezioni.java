package it.unibas.icar.freesbee.modello;

public class CostantiEccezioni {
    //

    public static final String INFO = "INFO";
    public static final String GRAVE = "GRAVE";
    //
    //Intestazione
    public static String[] ACTOR_NON_VALIDO = new String[]{"002", GRAVE, "Intestazione/actor"};
    public static String[] MUST_UNDERSTAND_NON_VALIDO = new String[]{"002", GRAVE, "Intestazione/mustUnderstand"};
    //Mittente
    public static String[] MITTENTE_PIU_VOLTE = new String[]{"101", GRAVE, "Mittente/IdentificativoParte"};
    public static String[] MITTENTE_SCONOSCIUTO = new String[]{"101", GRAVE, "Mittente/IdentificativoParte"};
    public static String[] MITTENTE_TIPO_NON_VALIDO = new String[]{"101", GRAVE, "Mittente/IdentificativoParte/tipo"};
    public static String[] MITTENTE_INDIRIZZO_TELEMATICO_PRESENTE = new String[]{"101", INFO, "Mittente/IdentificativoParte/indirizzoTelematico"};
    //Destinatario
    public static String[] DESTINATARIO_SCONOSCIUTO = new String[]{"102", GRAVE, "Destinatario/IdentificativoParte"};
    public static String[] DESTINATARIO_TIPO_NON_VALIDO = new String[]{"102", GRAVE, "Destinatario/IdentificativoParte/tipo"};
    public static String[] DESTINATARIO_INDIRIZZO_TELEMATICO_PRESENTE = new String[]{"102", INFO, "Destinatario/IdentificativoParte/indirizzoTelematico"};
    //ProfiloCollaborazione
    public static String[] PROFILO_COLLABORAZIONE_NON_VALIDO = new String[]{"103", GRAVE, "ProfiloCollaborazione"};
    public static String[] PROFILO_COLLABORAZIONE_NON_PRESENTE = new String[]{"103", GRAVE, "ProfiloCollaborazione"};
    public static String[] SERVIZIO_CORRELATO_PRESENTE = new String[]{"103", INFO, "ProfiloCollaborazione/servizioCorrelato"};
    public static String[] PROFILO_COLLABORAZIONE_TIPO_PRESENTE = new String[]{"103", INFO, "ProfiloCollaborazione/tipo"};
    public static String[] PROFILO_COLLABORAZIONE_TIPO_NON_VALIDO = new String[]{"103", GRAVE, "ProfiloCollaborazione/tipo"};
    //Collaborazione
    public static String[] COLLABORAZIONE_NON_PREVISTO = new String[]{"104", INFO, "Collaborazione"};
    public static String[] COLLABORAZIONE_NON_VALIDO = new String[]{"104", GRAVE, "Collaborazione"};
    public static String[] COLLABORAZIONE_NON_PRESENTE = new String[]{"104", GRAVE, "Collaborazione"};
    //Servizio
    public static String[] SERVIZIO_NON_PRESENTE = new String[]{"105", GRAVE, "Servizio"};
    public static String[] SERVIZIO_NON_RICONOSCIUTO = new String[]{"105", GRAVE, "Servizio"};
    public static String[] SERVIZIO_TIPO_NON_VALIDO = new String[]{"105", GRAVE, "Servizio/tipo"};
    //Azione
    public static String[] AZIONE_NON_PRESENTE = new String[]{"106", GRAVE, "Azione"};
    public static String[] AZIONE_NON_RICONOSCIUTA = new String[]{"106", GRAVE, "Azione"};
    //Scadenza
    public static String[] SCADENZA_NON_VALIDA = new String[]{"112", GRAVE, "Messaggio/Scadenza"};
    public static String[] MESSAGGIO_SCADUTO = new String[]{"301", GRAVE, "Messaggio/Scadenza"};
    //Identificatore
    public static String[] IDENTIFICATORE_NON_VALIDO = new String[]{"110", GRAVE, "Messaggio/Identificatore"};
    public static String[] IDENTIFICATORE_NON_VALORIZZATO = new String[]{"107", GRAVE, "Messaggio/Identificatore"};
    public static String[] IDENTIFICATORE_DUPLICATO = new String[]{"110", GRAVE, "Messaggio/Identificatore"};
    //OraRegistrazione
    public static String[] ORA_REGISTRAZIONE_NON_VALIDO = new String[]{"108", GRAVE, "Messaggio/OraRegistrazione"};
    public static String[] ORA_REGISTRAZIONE_TEMPO_NON_VALIDO = new String[]{"108", GRAVE, "Messaggio/OraRegistrazione/tempo"};
    //RiferimentoMessaggio
    public static String[] RIFERIMENTO_MESSAGGIO_NON_VALIDO = new String[]{"111", GRAVE, "Messaggio/RiferimentoMessaggio"};
    public static String[] RIFERIMENTO_MESSAGGIO_PRESENTE = new String[]{"111", INFO, "Messaggio/RiferimentoMessaggio"};
    //ListaTrasmissioni
    public static String[] TRASMISSIONE_ORIGINE_IDENTIFICATIVO_NON_AVVALORATO = new String[]{"116", GRAVE, "ListaTrasmissioni/Trasmissione/Origine/IdentificativoParte"};
    public static String[] TRASMISSIONE_ORIGINE_TIPO_NON_VALIDO = new String[]{"116", GRAVE, "ListaTrasmissioni/Trasmissione/Origine/IdentificativoParte/tipo"};
    public static String[] TRASMISSIONE_ORIGINE_INDIRIZZO_TELEMATICO_PRESENTE = new String[]{"116", INFO, "ListaTrasmissioni/Trasmissione/Origine/IdentificativoParte/indirizzoTelematico"};
    public static String[] TRASMISSIONE_DESTINAZIONE_IDENTIFICATIVO_NON_AVVALORATO = new String[]{"116", GRAVE, "ListaTrasmissioni/Trasmissione/Destinazione/IdentificativoParte"};
    public static String[] TRASMISSIONE_DESTINAZIONE_TIPO_NON_VALIDO = new String[]{"116", GRAVE, "ListaTrasmissioni/Trasmissione/Destinazione/IdentificativoParte/tipo"};
    public static String[] TRASMISSIONE_DESTINAZIONE_INDIRIZZO_TELEMATICO_PRESENTE = new String[]{"116", INFO, "ListaTrasmissioni/Trasmissione/Destinazione/IdentificativoParte/indirizzoTelematico"};
    public static String[] TRASMISSIONE_ORA_REGISTRAZIONE_NON_VALIDO = new String[]{"116", GRAVE, "ListaTrasmissioni/Trasmissione/OraRegistrazione"};
    public static String[] TRASMISSIONE_TEMPO_NON_VALIDO = new String[]{"116", GRAVE, "ListaTrasmissioni/Trasmissione/OraRegistrazione/tempo"};
    //ProfiloTrasmissione
    public static String[] PROFILO_TRASMISSIONE_INOLTRO_NON_VALIDO = new String[]{"113", GRAVE, "ProfiloTrasmissione/inoltro"};
    public static String[] PROFILO_TRASMISSIONE_CONFERMA_RICEZIONE_NON_VALIDO = new String[]{"113", GRAVE, "ProfiloTrasmissione/confermaRicezione"};
    //Sequenza
    public static String[] SEQUENZA_PRESENTE = new String[]{"401", INFO, "Sequenza"};
    public static String[] SEQUENZA_NUMERO_PROGRESSIVO_NON_VALIDO = new String[]{"114", GRAVE, "Sequenza/numeroProgressivo"};
    //ListaRiscontri
    public static String[] LISTA_RISCONTRI_PRESENTE = new String[]{"115", INFO, "ListaRiscontri"};
    public static String[] RISCONTRO_IDENTIFICATORE_NON_VALIDO = new String[]{"115", GRAVE, "ListaRiscontri/Riscontro/Identificatore"};
    public static String[] RISCONTRO_ORA_REGISTRAZIONE_NON_VALIDO = new String[]{"115", GRAVE, "ListaRiscontri/Riscontro/OraRegistrazione"};
    public static String[] RISCONTRO_TEMPO_NON_VALIDO = new String[]{"115", GRAVE, "ListaRiscontri/Riscontro/OraRegistrazione/tempo"};

}
