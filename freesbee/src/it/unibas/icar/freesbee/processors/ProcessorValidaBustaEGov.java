package it.unibas.icar.freesbee.processors;

import it.unibas.icar.freesbee.modello.CostantiEccezioni;
import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.modello.ProfiloEGov;
import it.unibas.icar.freesbee.modello.Riscontro;
import it.unibas.icar.freesbee.modello.Trasmissione;
import it.unibas.icar.freesbee.utilita.CostantiBusta;
import it.unibas.icar.freesbee.utilita.UtilitaDate;
import java.text.ParseException;
import java.util.Date;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.LoggerFactory;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

public class ProcessorValidaBustaEGov implements Processor {

//    private static Log logger = LogFactory.getLog(ProcessorValidaBustaEGov.class);
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ProcessorValidaBustaEGov.class.getName());

    public ProcessorValidaBustaEGov() {
    }

    public void process(Exchange exchange) throws Exception {
        //ContextStartup.aggiungiThread(this.getClass().getName());
        Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiBusta.MESSAGGIO);
        
        String tipoFruitore = messaggio.getTipoFruitore();
        if (tipoFruitore == null || !tipoFruitore.equals(TIPO_VALIDO)) {
            messaggio.aggiungiEccezione(CostantiEccezioni.MITTENTE_TIPO_NON_VALIDO);
            logger.error("Attributo tipo del fruitore diverso da " + TIPO_VALIDO);
//            throw new FreesbeeException("Attributo tipo dell'fruitore diverso da " + TIPO_VALIDO);
            messaggio.setTipoFruitore(TIPO_VALIDO); //Così possiamo procedere con gli altri controlli
        }
        String indirizzoTelematicoFruitore = messaggio.getIndirizzoTelematicoFruitore();
        if ((indirizzoTelematicoFruitore != null) && (!indirizzoTelematicoFruitore.trim().isEmpty())) {
            messaggio.aggiungiEccezione(CostantiEccezioni.MITTENTE_INDIRIZZO_TELEMATICO_PRESENTE);
            logger.error("Elemento Mittente/IdentificativoParte/indirizzoTelematico presente, non dovrebbe esserci o se presente dovrebbe essere vuoto.");
        }

        String tipoErogatore = messaggio.getTipoErogatore();
        if (tipoErogatore == null || !tipoErogatore.equals(TIPO_VALIDO)) {
            messaggio.aggiungiEccezione(CostantiEccezioni.DESTINATARIO_TIPO_NON_VALIDO);
            logger.error("Attributo tipo dell'erogatore diverso da " + TIPO_VALIDO);
//            throw new FreesbeeException("Attributo tipo dell'erogatore diverso da " + TIPO_VALIDO);
            messaggio.setTipoErogatore(TIPO_VALIDO); //Così possiamo procedere con gli altri controlli
        }
        String indirizzoTelematicoErogatore = messaggio.getIndirizzoTelematicoErogatore();
        if ((indirizzoTelematicoErogatore != null) && (!indirizzoTelematicoErogatore.trim().isEmpty())) {
            messaggio.aggiungiEccezione(CostantiEccezioni.DESTINATARIO_INDIRIZZO_TELEMATICO_PRESENTE);
            logger.error("Elemento Destinatario/IdentificativoParte/indirizzoTelematico presente, non dovrebbe esserci o se presente dovrebbe essere vuoto.");
        }

        if (messaggio.isMittentiMultipli()) {
            messaggio.aggiungiEccezione(CostantiEccezioni.MITTENTE_PIU_VOLTE);
            logger.error("Elemento Mittente/IdentificativoParte inserito piu' volte.");
//            throw new FreesbeeException("Elemento Mittente/IdentificativoParte inserito più volte");
        }

        String oraRegistrazione = messaggio.getOraRegistrazione();
        try {
            UtilitaDate.parseDataEOra(oraRegistrazione);
        } catch (Exception e) {
            messaggio.aggiungiEccezione(CostantiEccezioni.ORA_REGISTRAZIONE_NON_VALIDO);
            logger.error("Attributo OraRegistrazione non valido.");
            messaggio.setOraRegistrazione("1970-01-01T00:00:00");
//            throw new FreesbeeException("Attributo OraRegistrazione non valido");
        }

        String tempo = messaggio.getTempo();
        if (tempo != null && !tempo.equals(TEMPO_VALIDO)) {
            messaggio.aggiungiEccezione(CostantiEccezioni.ORA_REGISTRAZIONE_TEMPO_NON_VALIDO);
            logger.error("Attributo tempo presente ma diverso da " + TEMPO_VALIDO + ". Il valore attuale e' " + tempo);
            messaggio.setOraRegistrazione("1970-01-01T00:00:00");
//            throw new FreesbeeException("Attributo tempo presente ma diverso da " + TEMPO_VALIDO);
        }

        String inoltro = messaggio.getInoltro();
        if (inoltro != null && !inoltro.equals(INOLTRO_VALIDO)) {
            messaggio.aggiungiEccezione(CostantiEccezioni.PROFILO_TRASMISSIONE_INOLTRO_NON_VALIDO);
            logger.error("Attributo inoltro presente ma diverso da " + INOLTRO_VALIDO);
//            throw new FreesbeeException("Attributo inoltro presente ma diverso da " + INOLTRO_VALIDO);
        }

        String confermaRicezione = messaggio.getConfermaRicezione();
        if (confermaRicezione != null && !confermaRicezione.equals(CONFERMA_RICEZIONE_VALIDO)) {
            messaggio.aggiungiEccezione(CostantiEccezioni.PROFILO_TRASMISSIONE_CONFERMA_RICEZIONE_NON_VALIDO);
            logger.error("Attributo conferma ricezione presente ma diverso da " + CONFERMA_RICEZIONE_VALIDO);
//            throw new FreesbeeException("Attributo conferma ricezione presente ma diverso da " + CONFERMA_RICEZIONE_VALIDO);
        }


        String nomeServizio = messaggio.getServizio();
        String tipoServizio = messaggio.getTipoServizio();
        if (nomeServizio == null || nomeServizio.isEmpty() || tipoServizio == null || tipoServizio.isEmpty()) {
            messaggio.aggiungiEccezione(CostantiEccezioni.SERVIZIO_NON_PRESENTE);
            logger.error("Elemento Servizio non presente.");
//            throw new FreesbeeException("Elemento Servizio non presente");
        }

        if (tipoServizio != null && !tipoServizio.equals(TIPO_VALIDO)) {
            messaggio.aggiungiEccezione(CostantiEccezioni.SERVIZIO_TIPO_NON_VALIDO);
            logger.error("Attributo tipo del servizio diverso da " + TIPO_VALIDO);
//            throw new FreesbeeException("Attributo tipo del servizio diverso da " + TIPO_VALIDO);
            messaggio.setTipoServizio(TIPO_VALIDO); //Così possiamo procedere con gli altri controlli
        }


        String stringaAzione = messaggio.getAzione();
        if (stringaAzione == null || stringaAzione.isEmpty()) {
            messaggio.aggiungiEccezione(CostantiEccezioni.AZIONE_NON_PRESENTE);
            logger.error("Elemento Azione non presente.");
//            throw new FreesbeeException("Elemento Azione non presente");
        }

        String profiloCollaborazione = messaggio.getProfiloCollaborazione();
        if (profiloCollaborazione == null || profiloCollaborazione.isEmpty()) {
            messaggio.aggiungiEccezione(CostantiEccezioni.PROFILO_COLLABORAZIONE_NON_PRESENTE);
            logger.error("Elemento ProfiloCollaborazione non presente.");
//            throw new FreesbeeException("Elemento ProfiloCollaborazione non presente");
        }

        String tipoProfiloCollaborazione = messaggio.getTipoProfiloCollaborazione();
        if (tipoProfiloCollaborazione != null && !profiloCollaborazione.equals(TIPO_VALIDO)) {
            messaggio.aggiungiEccezione(CostantiEccezioni.PROFILO_COLLABORAZIONE_TIPO_NON_VALIDO);
            logger.error("Elemento Tipo ProfiloCollaborazione presente ma non valido.");
//            throw new FreesbeeException("Elemento Tipo ProfiloCollaborazione presente ma non valido");
        } else if ((tipoProfiloCollaborazione != null) && (!tipoProfiloCollaborazione.trim().isEmpty())) {
            messaggio.aggiungiEccezione(CostantiEccezioni.PROFILO_COLLABORAZIONE_TIPO_PRESENTE);
            logger.error("Elemento ProfiloCollaborazione/Tipo presente, non dovrebbe esserci o se presente dovrebbe essere vuoto.");
        }

        String actor = messaggio.getActor();
        if (actor != null && !actor.equals(ACTOR_VALIDO)) {
            messaggio.aggiungiEccezione(CostantiEccezioni.ACTOR_NON_VALIDO);
            logger.error("Elemento Actor presente ma non valido.");
//            throw new FreesbeeException("Elemento Actor presente ma non valido");
        }

        String mustUnderstand = messaggio.getMustUnderstand();
        if (mustUnderstand != null && !mustUnderstand.equals(MUST_UNDERSTAND_VALIDO)) {
            messaggio.aggiungiEccezione(CostantiEccezioni.MUST_UNDERSTAND_NON_VALIDO);
            logger.error("Elemento MustUnderstand presente ma non valido.");
//            throw new FreesbeeException("Elemento MustUnderstand presente ma non valido");
        }

        for (Trasmissione trasmissione : messaggio.getListaTrasmissioni()) {
            if (logger.isDebugEnabled()) logger.debug(trasmissione.toString());
            if (trasmissione.getOrigineNome() == null || trasmissione.getOrigineNome().isEmpty()) {
                messaggio.aggiungiEccezione(CostantiEccezioni.TRASMISSIONE_ORIGINE_IDENTIFICATIVO_NON_AVVALORATO);
                logger.error("Elemento Origine/IdentificativoParte non presente.");
//                throw new FreesbeeException("Elemento Origine/IdentificativoParte non presente");
            }
            if (trasmissione.getOrigineTipo() != null && !trasmissione.getOrigineTipo().equals(TIPO_VALIDO)) {
                messaggio.aggiungiEccezione(CostantiEccezioni.TRASMISSIONE_ORIGINE_TIPO_NON_VALIDO);
                logger.error("Elemento Origine/Tipo diverso da " + TIPO_VALIDO);
//                throw new FreesbeeException("Elemento Origine/Tipo diverso da " + TIPO_VALIDO);
            }
            if ((trasmissione.getOrigineIndirizzoTelematico() != null) && (!trasmissione.getOrigineIndirizzoTelematico().trim().isEmpty())) {
                messaggio.aggiungiEccezione(CostantiEccezioni.TRASMISSIONE_ORIGINE_INDIRIZZO_TELEMATICO_PRESENTE);
                logger.error("Elemento ListaTrasmissioni/Trasmissione/Origine/IdentificativoParte/indirizzoTelematico presente, non dovrebbe esserci o se presente dovrebbe essere vuoto.");
            }
            if (trasmissione.getDestinazioneNome() == null || trasmissione.getDestinazioneNome().isEmpty()) {
                messaggio.aggiungiEccezione(CostantiEccezioni.TRASMISSIONE_DESTINAZIONE_IDENTIFICATIVO_NON_AVVALORATO);
                logger.error("Elemento Destinazione/IdentificativoParte non presente.");
//                throw new FreesbeeException("Elemento Destinazione/IdentificativoParte non presente");
            }
            if (trasmissione.getDestinazioneTipo() != null && !trasmissione.getDestinazioneTipo().equals(TIPO_VALIDO)) {
                messaggio.aggiungiEccezione(CostantiEccezioni.TRASMISSIONE_DESTINAZIONE_TIPO_NON_VALIDO);
                logger.error("Elemento Destinazione/Tipo diverso da " + TIPO_VALIDO);
//                throw new FreesbeeException("Elemento Destinazione/Tipo diverso da " + TIPO_VALIDO);
            }
            if ((trasmissione.getDestinazioneIndirizzoTelematico() != null) && (!trasmissione.getDestinazioneIndirizzoTelematico().trim().isEmpty())) {
                messaggio.aggiungiEccezione(CostantiEccezioni.TRASMISSIONE_DESTINAZIONE_INDIRIZZO_TELEMATICO_PRESENTE);
                logger.error("Elemento ListaTrasmissioni/Trasmissione/Destinazione/IdentificativoParte/indirizzoTelematico presente, non dovrebbe esserci o se presente dovrebbe essere vuoto.");
            }
            if (trasmissione.getTempo() != null && !trasmissione.getTempo().equals(TEMPO_VALIDO)) {
                messaggio.aggiungiEccezione(CostantiEccezioni.TRASMISSIONE_TEMPO_NON_VALIDO);
                logger.error("Attributo Trasmissione/tempo presente ma diverso da " + TEMPO_VALIDO);
//                throw new FreesbeeException("Attributo Trasmissione/tempo presente ma diverso da " + TEMPO_VALIDO);
            }
            if (trasmissione.getOraRegistrazione() != null) {
                try {
                    UtilitaDate.parseDataEOra(trasmissione.getOraRegistrazione());
                } catch (Exception e) {
                    messaggio.aggiungiEccezione(CostantiEccezioni.TRASMISSIONE_ORA_REGISTRAZIONE_NON_VALIDO);
                    logger.error("Attributo Riscontro/OraRegistrazione presente ma non valido formalmente.");
//                    throw new FreesbeeException("Attributo Riscontro/OraRegistrazione presente ma non valido formalmente");
                }
            }
        }

        for (Riscontro riscontro : messaggio.getListaRiscontri()) {
            if (logger.isDebugEnabled()) logger.debug(riscontro.toString());
            if (!validaIdentificatore(riscontro.getIdentificatore())) {
                messaggio.aggiungiEccezione(CostantiEccezioni.RISCONTRO_IDENTIFICATORE_NON_VALIDO);
                logger.error("Elemento Riscontro/Identificatore non valido.");
//                throw new FreesbeeException("Elemento Riscontro/Identificatore non valido");
            }
            if (riscontro.getOraRegistrazione() != null) {
                try {
                    UtilitaDate.parseDataEOra(riscontro.getOraRegistrazione());
                } catch (Exception e) {
                    messaggio.aggiungiEccezione(CostantiEccezioni.RISCONTRO_ORA_REGISTRAZIONE_NON_VALIDO);
                    logger.error("Attributo Riscontro/OraRegistrazione presente ma non valido formalmente.");
//                    throw new FreesbeeException("Attributo Riscontro/OraRegistrazione presente ma non valido formalmente");
                }
            }
            if (riscontro.getTempo() != null && !riscontro.getTempo().equals(TEMPO_VALIDO)) {
                messaggio.aggiungiEccezione(CostantiEccezioni.RISCONTRO_TEMPO_NON_VALIDO);
                logger.error("Attributo OraRegistrazione/tempo presente ma diverso da " + TEMPO_VALIDO);
//                throw new FreesbeeException("Attributo OraRegistrazione/tempo presente ma diverso da " + TEMPO_VALIDO);
            }
        }

        String scadenza = messaggio.getScadenza();
        if (scadenza != null) {
            try {
                Date dataScadenza = UtilitaDate.parseDataEOra(scadenza);
                if (dataScadenza.before(new Date())) {
                    messaggio.aggiungiEccezione(CostantiEccezioni.MESSAGGIO_SCADUTO);
                    logger.error("Messaggio scaduto.");
//                    throw new FreesbeeException("Messaggio scaduto");
                }
            } catch (ParseException pe) {
                messaggio.aggiungiEccezione(CostantiEccezioni.SCADENZA_NON_VALIDA);
                logger.error("Scadenza non valida.");
//                throw new FreesbeeException("Scadenza non valida");
            }
        }


        String identificatore = messaggio.getIdMessaggio();
        if (identificatore == null || identificatore.isEmpty()) {
            messaggio.aggiungiEccezione(CostantiEccezioni.IDENTIFICATORE_NON_VALORIZZATO);
            logger.error("Identificatore non valorizzato.");
//            throw new FreesbeeException("Identificatore non valorizzato");
        }
        if (identificatore != null && !validaIdentificatore(identificatore)) {
            messaggio.aggiungiEccezione(CostantiEccezioni.IDENTIFICATORE_NON_VALIDO);
            logger.error("Identificatore non valido.");
            messaggio.setIdMessaggio("");
            messaggio.setRiferimentoMessaggio(null);
//            throw new FreesbeeException("Identificatore non valido");
        }

        String numeroProgressivo = messaggio.getNumeroProgressivo();
        if (numeroProgressivo != null) {
            if (numeroProgressivo.length() != 7) {
                messaggio.aggiungiEccezione(CostantiEccezioni.SEQUENZA_NUMERO_PROGRESSIVO_NON_VALIDO);
                logger.error("Numero progressivo non valido.");
//                throw new FreesbeeException("Numero progressivo non valido");
            }
            try {
                Integer.parseInt(numeroProgressivo);
            } catch (Exception e) {
                messaggio.aggiungiEccezione(CostantiEccezioni.SEQUENZA_NUMERO_PROGRESSIVO_NON_VALIDO);
                logger.error("Numero progressivo non valido.");
//                throw new FreesbeeException("Numero progressivo non valido");
            }
        }

        String riferimentoMessaggio = messaggio.getRiferimentoMessaggio();
        if (riferimentoMessaggio != null && !validaIdentificatore(riferimentoMessaggio)) {
            messaggio.aggiungiEccezione(CostantiEccezioni.RIFERIMENTO_MESSAGGIO_NON_VALIDO);
            logger.error("Elemento Riferimento messaggio non valido.");
//            throw new FreesbeeException("Elemento Riferimento messaggio non valido");
        }

        String collaborazione = messaggio.getCollaborazione();
        if (collaborazione != null && !validaIdentificatore(collaborazione)) {
            messaggio.aggiungiEccezione(CostantiEccezioni.COLLABORAZIONE_NON_VALIDO);
            logger.error("Elemento Collaborazione messaggio non valido.");
//            throw new FreesbeeException("Elemento Collaborazione messaggio non valido");
        }

        String servizioCorrelato = messaggio.getServizioCorrelato();
        if ((servizioCorrelato != null) && (!servizioCorrelato.trim().isEmpty())) {
            messaggio.aggiungiEccezione(CostantiEccezioni.SERVIZIO_CORRELATO_PRESENTE);
            logger.error("Elemento ProfiloCollaborazione/ServizioCorrelato presente, non dovrebbe esserci o se presente dovrebbe essere vuoto.");
        }
    }

    public boolean validaIdentificatore(String identificatore) {
        String[] parti = identificatore.split("_");
        if (parti.length != 5) {
            return false;
        }
        for (int i = 0; i < parti.length; i++) {
            if (parti[i] == null || parti[i].isEmpty()) {
                return false;
            }
        }
        if (parti[2].length() != 7) {
            return false;
        }
        try {
            Integer.parseInt(parti[2]);
            Date d = UtilitaDate.parseData(parti[3]);
            Date t = UtilitaDate.parseOra(parti[4]);
        } catch (Exception e) {
            return false;
        }


        return true;
    }
    //Valori validi
    private final String TIPO_VALIDO = "SPC";
    private final String TEMPO_VALIDO = "EGOV_IT_SPC";
    private final String INOLTRO_VALIDO = ProfiloEGov.EVITA_DUPLICATI;
    private final String CONFERMA_RICEZIONE_VALIDO = "false";
    private final String ACTOR_VALIDO = "http://www.cnipa.it/eGov_it/portadominio";
    private final String MUST_UNDERSTAND_VALIDO = "1";
}
