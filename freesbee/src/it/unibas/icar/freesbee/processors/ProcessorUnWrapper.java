package it.unibas.icar.freesbee.processors;

import it.unibas.icar.freesbee.exception.FreesbeeException;
import it.unibas.icar.freesbee.modello.AccordoServizio;
import it.unibas.icar.freesbee.modello.CostantiEccezioni;
import it.unibas.icar.freesbee.modello.Eccezione;
import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.modello.Riscontro;
import it.unibas.icar.freesbee.modello.Trasmissione;
import it.unibas.icar.freesbee.utilita.CostantiBusta;
import it.unibas.icar.freesbee.utilita.CostantiSOAP;
import it.unibas.icar.freesbee.utilita.BustaUtil;
import java.util.List;
import java.util.Map;
import javax.xml.namespace.QName;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Node;

public class ProcessorUnWrapper implements Processor {

    private static Log logger = LogFactory.getLog(ProcessorUnWrapper.class);

    public ProcessorUnWrapper() {
    }

    @SuppressWarnings("unchecked")
    public void process(Exchange exchange) throws Exception {
        //ContextStartup.aggiungiThread(this.getClass().getName());
        //new ProcessorLog(this.getClass()).process(exchange);
        ProcessorLogFactory.getInstance().getProcessorLog(this.getClass()).process(exchange);
        Map<QName, DocumentFragment> mappaHeaders = (Map<QName, DocumentFragment>) exchange.getProperty(CostantiSOAP.SOAP_HEADERS);

        if (logger.isInfoEnabled()) logger.info("Si sta trasformando il messaggio EGOV in messaggio SOAP.");
        Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiBusta.MESSAGGIO);

        if (mappaHeaders == null) {
            logger.error("Non sono state trovate intestazioni nel messaggio. Probabilmente il messaggio ricevuto non e' una busta EGOV.");
            throw new FreesbeeException("Non sono state trovate intestazioni nel messaggio. Probabilmente il messaggio ricevuto non e' una busta EGOV.");
        }
        
        if (logger.isDebugEnabled()) logger.debug("Le intestazioni sono: " + mappaHeaders);
        
        DocumentFragment df = mappaHeaders.get(new QName(CostantiSOAP.NAMESPACE_EGOV, "Intestazione"));
        if (df == null) {
            logger.error("Non sono state trovate intestazioni EGOV. Probabilmente il messaggio ricevuto non e' una busta EGOV.");
            throw new FreesbeeException("Non sono state trovate intestazioni nel messaggio. Probabilmente il messaggio ricevuto non e' una busta EGOV.");
        }
        try {
            mappaHeaders.remove(new QName(CostantiSOAP.NAMESPACE_EGOV, "Intestazione"));
            Node nodoIntestazione = BustaUtil.cercaNodo(df.getChildNodes(), "Intestazione");
            String nodoActor = BustaUtil.getAttributo(nodoIntestazione, CostantiSOAP.NAMESPACE_SOAP_ENVELOPE, CostantiSOAP.PREFIX_ACTOR_EGOV);
            if (nodoActor != null) {
                messaggio.setActor(nodoActor);
            }
            String nodoMustUnderstand = BustaUtil.getAttributo(nodoIntestazione, CostantiSOAP.NAMESPACE_SOAP_ENVELOPE, CostantiSOAP.PREFIX_MUSTUNDERSTAND);
            if (nodoMustUnderstand != null) {
                messaggio.setMustUnderstand(nodoMustUnderstand);
            }

            /* ------------------------------------------------ INIZIO INTESTAZIONE MESSAGGIO ------------------------------------------------ */

            Node nodoIntestazioneMessaggio = BustaUtil.cercaNodo(nodoIntestazione.getChildNodes(), "IntestazioneMessaggio");
            Node nodoMittente = BustaUtil.cercaNodo(nodoIntestazioneMessaggio.getChildNodes(), CostantiBusta.MITTENTE);
            //Verifichiamo che non ci siano più nodi mittente
            List<Node> cercaListaNodo = BustaUtil.cercaListaNodo(nodoMittente.getChildNodes(), CostantiBusta.IDENTIFICATIVOPARTE);
            if (cercaListaNodo.size() > 1) {
                messaggio.setMittentiMultipli(true);
            }
            //
            Node nodoIdentificativoParteMittente = BustaUtil.cercaNodo(nodoMittente.getChildNodes(), CostantiBusta.IDENTIFICATIVOPARTE);
            String valoreIdentificativoParteMittente = nodoIdentificativoParteMittente.getTextContent();
            String attributoTipoIdentificativoParteMittente = BustaUtil.getAttributo(nodoIdentificativoParteMittente, CostantiBusta.TIPO);
            String attributoIndirizzoTelematicoIdentificativoParteMittente = BustaUtil.getAttributo(nodoIdentificativoParteMittente, CostantiBusta.INDIRIZZOTELEMATICO);
            if (attributoIndirizzoTelematicoIdentificativoParteMittente != null) {
                messaggio.setIndirizzoTelematicoFruitore(attributoIndirizzoTelematicoIdentificativoParteMittente);
            }
            messaggio.setFruitore(valoreIdentificativoParteMittente);
            messaggio.setTipoFruitore(attributoTipoIdentificativoParteMittente);

            Node nodoDestinatario = BustaUtil.cercaNodo(nodoIntestazioneMessaggio.getChildNodes(), CostantiBusta.DESTINATARIO);
            Node nodoIdentificativoParteDestinatario = BustaUtil.cercaNodo(nodoDestinatario.getChildNodes(), CostantiBusta.IDENTIFICATIVOPARTE);
            String valoreIdentificativoParteDestinatario = nodoIdentificativoParteDestinatario.getTextContent();
            String attributoTipoIdentificativoParteDestinatario = BustaUtil.getAttributo(nodoIdentificativoParteDestinatario, CostantiBusta.TIPO);
            String attributoIndirizzoTelematicoIdentificativoParteDestinatario = BustaUtil.getAttributo(nodoIdentificativoParteDestinatario, CostantiBusta.INDIRIZZOTELEMATICO);
            if (attributoIndirizzoTelematicoIdentificativoParteDestinatario != null) {
                messaggio.setIndirizzoTelematicoErogatore(attributoIndirizzoTelematicoIdentificativoParteDestinatario);
            }
            messaggio.setErogatore(valoreIdentificativoParteDestinatario);
            messaggio.setTipoErogatore(attributoTipoIdentificativoParteDestinatario);

            Node nodoServizio = BustaUtil.cercaNodo(nodoIntestazioneMessaggio.getChildNodes(), CostantiBusta.SERVIZIO);
            if (nodoServizio != null) {
                String valoreServizio = nodoServizio.getTextContent();
                String attributoTipoServizio = BustaUtil.getAttributo(nodoServizio, CostantiBusta.TIPO);
                messaggio.setServizio(valoreServizio);
                messaggio.setTipoServizio(attributoTipoServizio);
            }

            Node nodoAzione = BustaUtil.cercaNodo(nodoIntestazioneMessaggio.getChildNodes(), CostantiBusta.AZIONE);
            if (nodoAzione != null) {
                String valoreAzione = nodoAzione.getTextContent();
                messaggio.setAzione(valoreAzione);
            }

            Node nodoMessaggio = BustaUtil.cercaNodo(nodoIntestazioneMessaggio.getChildNodes(), CostantiBusta.ELEMENTOMESSAGGIO);
            Node nodoIdentificatoreMessaggio = BustaUtil.cercaNodo(nodoMessaggio.getChildNodes(), CostantiBusta.IDENTIFICATORE);
            String valoreIdentificatoreMessaggio = nodoIdentificatoreMessaggio.getTextContent();
            Node nodoOraRegistrazioneMessaggio = BustaUtil.cercaNodo(nodoMessaggio.getChildNodes(), CostantiBusta.ORAREGISTRAZIONE);
            String valoreOraRegistrazioneMessaggio = nodoOraRegistrazioneMessaggio.getTextContent();
            String attributoTempoOraRegistrazioneMessaggio = BustaUtil.getAttributo(nodoOraRegistrazioneMessaggio, CostantiBusta.TEMPO);
            Node nodoRiferimentoMessaggio = BustaUtil.cercaNodo(nodoMessaggio.getChildNodes(), CostantiBusta.RIFERIMENTOMESSAGGIO);
            if (nodoRiferimentoMessaggio != null) {
                String valoreRiferimentoMessaggio = nodoRiferimentoMessaggio.getTextContent();
                messaggio.setRiferimentoMessaggio(valoreRiferimentoMessaggio);
            }

            Node nodoScadenza = BustaUtil.cercaNodo(nodoMessaggio.getChildNodes(), CostantiBusta.SCADENZA);
            if (nodoScadenza != null) {
                String valoreScadenza = nodoScadenza.getTextContent();
                messaggio.setScadenza(valoreScadenza);
            }
            messaggio.setIdMessaggio(valoreIdentificatoreMessaggio);
            messaggio.setOraRegistrazione(valoreOraRegistrazioneMessaggio);
            messaggio.setTempo(attributoTempoOraRegistrazioneMessaggio);

            Node nodoProfiloCollaborazione = BustaUtil.cercaNodo(nodoIntestazioneMessaggio.getChildNodes(), CostantiBusta.PROFILOCOLLABORAZIONE);
            String valoreProfiloCollaborazione = nodoProfiloCollaborazione.getTextContent();
            String attributoServizioCorrelatoProfiloCollaborazione = BustaUtil.getAttributo(nodoProfiloCollaborazione, CostantiBusta.SERVIZIOCORRELATO);
            if (attributoServizioCorrelatoProfiloCollaborazione != null) {
                messaggio.setServizioCorrelato(attributoServizioCorrelatoProfiloCollaborazione);
            }
            String attributoTipoProfiloCollaborazione = BustaUtil.getAttributo(nodoProfiloCollaborazione, CostantiBusta.TIPO);
            if (attributoTipoProfiloCollaborazione != null) {
                messaggio.setTipoProfiloCollaborazione(attributoTipoProfiloCollaborazione);
            }
            if (valoreProfiloCollaborazione == null || valoreProfiloCollaborazione.isEmpty()) {
                valoreProfiloCollaborazione = AccordoServizio.PROFILO_SINCRONO;
            }
            messaggio.setProfiloCollaborazione(valoreProfiloCollaborazione);
            exchange.getIn().setHeader(CostantiBusta.VALOREPROFILOCOLLABORAZIONE, valoreProfiloCollaborazione);
            exchange.setProperty(CostantiBusta.VALOREPROFILOCOLLABORAZIONE, valoreProfiloCollaborazione);

            Node nodoCollaborazione = BustaUtil.cercaNodo(nodoIntestazioneMessaggio.getChildNodes(), CostantiBusta.COLLABORAZIONE);
            if (nodoCollaborazione != null) {
                String valoreCollaborazione = nodoCollaborazione.getTextContent();
                messaggio.setCollaborazione(valoreCollaborazione);
            }

            Node nodoProfiloTrasmissione = BustaUtil.cercaNodo(nodoIntestazioneMessaggio.getChildNodes(), CostantiBusta.PROFILOTRASMISSIONE);
            String attributoInoltroProfiloTrasmissione = BustaUtil.getAttributo(nodoProfiloTrasmissione, CostantiBusta.INOLTRO);
            String attributoConfermaRicezioneProfiloTrasmissione = BustaUtil.getAttributo(nodoProfiloTrasmissione, CostantiBusta.CONFERMARICEZIONE);
            messaggio.setInoltro(attributoInoltroProfiloTrasmissione);
            messaggio.setConfermaRicezione(attributoConfermaRicezioneProfiloTrasmissione);

            Node nodoSequenza = BustaUtil.cercaNodo(nodoIntestazioneMessaggio.getChildNodes(), CostantiBusta.SEQUENZA);
            if (nodoSequenza != null) {
                messaggio.aggiungiEccezione(CostantiEccezioni.SEQUENZA_PRESENTE);
                String attributoNumeroProgressivoSequenza = BustaUtil.getAttributo(nodoSequenza, CostantiBusta.NUMEROPROGRESSIVO);
                messaggio.setNumeroProgressivo(attributoNumeroProgressivoSequenza);
            }

            Node nodoListaEccezioni = BustaUtil.cercaNodo(nodoIntestazione.getChildNodes(), CostantiBusta.LISTAECCEZIONI);
            if (nodoListaEccezioni != null) {
                List<Node> listaEccezioni = BustaUtil.cercaListaNodo(nodoListaEccezioni.getChildNodes(), CostantiBusta.ECCEZIONE);
                for (Node nodoEccezione : listaEccezioni) {
                    String attributoCodiceEccezione = BustaUtil.getAttributo(nodoEccezione, CostantiBusta.CODICEECCEZIONE);
                    String attributoContestoCodifica = BustaUtil.getAttributo(nodoEccezione, CostantiBusta.CONTESTOCODIFICA);
                    String attributoPosizione = BustaUtil.getAttributo(nodoEccezione, CostantiBusta.POSIZIONE);
                    String attributoRilevanza = BustaUtil.getAttributo(nodoEccezione, CostantiBusta.RILEVANZA);
                    Eccezione ecc = new Eccezione(attributoContestoCodifica, attributoCodiceEccezione, attributoRilevanza, attributoPosizione);
                    ecc.setMessaggio(messaggio);
                    messaggio.getListaEccezioni().add(ecc);
                }
            }


            Node nodoListaRiscontri = BustaUtil.cercaNodo(nodoIntestazione.getChildNodes(), CostantiBusta.LISTARISCONTRI);
            if (nodoListaRiscontri != null) {
                messaggio.aggiungiEccezione(CostantiEccezioni.LISTA_RISCONTRI_PRESENTE);
                List<Node> listaRiscontri = BustaUtil.cercaListaNodo(nodoListaRiscontri.getChildNodes(), CostantiBusta.RISCONTRO);
                for (Node nodoRiscontro : listaRiscontri) {
                    Node nodoIdentificatoreRiscontro = BustaUtil.cercaNodo(nodoRiscontro.getChildNodes(), CostantiBusta.IDENTIFICATORE);
                    String valoreIdentificatoreRiscontro = nodoIdentificatoreRiscontro.getTextContent();
                    Node nodoOraRegistrazioneRiscontro = BustaUtil.cercaNodo(nodoRiscontro.getChildNodes(), CostantiBusta.ORAREGISTRAZIONE);
                    String valoreOraRegistrazioneRiscontro = nodoOraRegistrazioneRiscontro.getTextContent();
                    String attributoTempoOraRegistrazioneRiscontro = BustaUtil.getAttributo(nodoOraRegistrazioneRiscontro, CostantiBusta.TEMPO);
                    Riscontro r = new Riscontro();
                    r.setIdentificatore(valoreIdentificatoreRiscontro);
                    r.setOraRegistrazione(valoreOraRegistrazioneRiscontro);
                    r.setTempo(attributoTempoOraRegistrazioneRiscontro);
                    messaggio.getListaRiscontri().add(r);
                }
            }

            Node nodoListaTrasmissioni = BustaUtil.cercaNodo(nodoIntestazione.getChildNodes(), CostantiBusta.LISTATRASMISSIONI);
            if (nodoListaTrasmissioni != null) {
                List<Node> listaTrasmissioni = BustaUtil.cercaListaNodo(nodoListaTrasmissioni.getChildNodes(), CostantiBusta.TRASMISSIONE);
                for (Node nodoTrasmissione : listaTrasmissioni) {
                    Node nodoOrigineTrasmissione = BustaUtil.cercaNodo(nodoTrasmissione.getChildNodes(), CostantiBusta.ORIGINE);
                    Node nodoIdentificativoParteOrigineTrasmissione = BustaUtil.cercaNodo(nodoOrigineTrasmissione.getChildNodes(), CostantiBusta.IDENTIFICATIVOPARTE);
                    String valoreIdentificativoParteOrigineTrasmissione = nodoIdentificativoParteOrigineTrasmissione.getTextContent();
                    String attributoTipoIdentificativoParteOrigineTrasmissione = BustaUtil.getAttributo(nodoIdentificativoParteOrigineTrasmissione, CostantiBusta.TIPO);
                    String attributoIndirizzoTelematicoIdentificativoParteOrigineTrasmissione = BustaUtil.getAttributo(nodoIdentificativoParteOrigineTrasmissione, CostantiBusta.INDIRIZZOTELEMATICO);

                    Node nodoDestinazioneTrasmissione = BustaUtil.cercaNodo(nodoTrasmissione.getChildNodes(), CostantiBusta.DESTINAZIONE);
                    Node nodoIdentificativoParteDestinazioneTrasmissione = BustaUtil.cercaNodo(nodoDestinazioneTrasmissione.getChildNodes(), CostantiBusta.IDENTIFICATIVOPARTE);
                    String valoreIdentificativoParteDestinazioneTrasmissione = nodoIdentificativoParteDestinazioneTrasmissione.getTextContent();
                    String attributoTipoIdentificativoParteDestinazioneTrasmissione = BustaUtil.getAttributo(nodoIdentificativoParteDestinazioneTrasmissione, CostantiBusta.TIPO);
                    String attributoIndirizzoTelematicoIdentificativoParteDestinazioneTrasmissione = BustaUtil.getAttributo(nodoIdentificativoParteDestinazioneTrasmissione, CostantiBusta.INDIRIZZOTELEMATICO);

                    Node nodoOraRegistrazioneTrasmissione = BustaUtil.cercaNodo(nodoTrasmissione.getChildNodes(), CostantiBusta.ORAREGISTRAZIONE);
                    String valoreOraRegistrazioneTrasmissione = nodoOraRegistrazioneTrasmissione.getTextContent();
                    String attributoTempoOraRegistrazioneTrasmissione = BustaUtil.getAttributo(nodoOraRegistrazioneTrasmissione, CostantiBusta.TEMPO);

                    Trasmissione t = new Trasmissione();
                    t.setOrigineNome(valoreIdentificativoParteOrigineTrasmissione);
                    t.setOrigineTipo(attributoTipoIdentificativoParteOrigineTrasmissione);
                    t.setOrigineIndirizzoTelematico(attributoIndirizzoTelematicoIdentificativoParteOrigineTrasmissione);
                    t.setDestinazioneNome(valoreIdentificativoParteDestinazioneTrasmissione);
                    t.setDestinazioneTipo(attributoTipoIdentificativoParteDestinazioneTrasmissione);
                    t.setDestinazioneIndirizzoTelematico(attributoIndirizzoTelematicoIdentificativoParteDestinazioneTrasmissione);
                    t.setOraRegistrazione(valoreOraRegistrazioneTrasmissione);
                    t.setTempo(attributoTempoOraRegistrazioneTrasmissione);
                    messaggio.getListaTrasmissioni().add(t);
                }
            }

        } catch (Exception ex) {
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new FreesbeeException("Errore nel processamento delle intestazioni EGOV.");
        }
    }
}
