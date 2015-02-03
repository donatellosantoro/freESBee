package it.unibas.icar.freesbee.processors;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import it.unibas.icar.freesbee.exception.FreesbeeException;
import it.unibas.icar.freesbee.modello.Configurazione;
import it.unibas.icar.freesbee.modello.Eccezione;
import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.persistenza.DBManager;
import it.unibas.icar.freesbee.utilita.CostantiSOAP;
import it.unibas.icar.freesbee.utilita.CostantiBusta;
import it.unibas.icar.freesbee.utilita.BustaUtil;
import java.util.HashMap;
import java.util.Map;
import javax.xml.namespace.QName;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

@Singleton
public class ProcessorWrapper implements Processor {

    private static Log logger = LogFactory.getLog(ProcessorWrapper.class);
    @Inject
    private DBManager dbManager;

    public ProcessorWrapper() {
    }

    @SuppressWarnings("unchecked")
    public void process(Exchange exchange) throws Exception {
        //ContextStartup.aggiungiThread(this.getClass().getName());
//        if (dbManager == null) {
//            dbManager = new DBManager();
//        }
        Configurazione configurazione = dbManager.getConfigurazione();
        try {
            if (logger.isInfoEnabled()) logger.info("Si sta trasformando il messaggio SOAP in messaggio EGOV.");
            Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiBusta.MESSAGGIO);
            Messaggio messaggioRichiesta = (Messaggio) exchange.getProperty(CostantiBusta.MESSAGGIO_RICHIESTA);

            messaggio.setTempo(configurazione.getTempo());
            if (logger.isDebugEnabled()) logger.debug("@ Fruitore: " + messaggio.getFruitore());

            Map<QName, DocumentFragment> mappaHeaders = (Map<QName, DocumentFragment>) exchange.getProperty(CostantiSOAP.SOAP_HEADERS);
            if (mappaHeaders == null) {
                mappaHeaders = new HashMap<QName, DocumentFragment>();
                exchange.setProperty(CostantiSOAP.SOAP_HEADERS, mappaHeaders);
                exchange.setProperty(CostantiSOAP.SOAP_HEADERS, mappaHeaders);
            }

            DocumentFragment df = BustaUtil.createEmptyHeader(CostantiSOAP.NAMESPACE_EGOV, CostantiSOAP.PREFIX_EGOV, "Intestazione");
            mappaHeaders.put(new QName(CostantiSOAP.NAMESPACE_EGOV, "Header_EGov"), df);

            Node nodoIntestazione = BustaUtil.cercaNodo(df.getChildNodes(), "Intestazione");

            /* ------------------------------------------------ INIZIO INTESTAZIONE MESSAGGIO ------------------------------------------------ */

            Element elementoIntestazioneMessaggio = BustaUtil.appendHeader(nodoIntestazione, CostantiSOAP.NAMESPACE_EGOV, CostantiSOAP.PREFIX_EGOV, "IntestazioneMessaggio");

            Element elementoMittente = BustaUtil.appendHeader(elementoIntestazioneMessaggio, CostantiSOAP.NAMESPACE_EGOV, CostantiSOAP.PREFIX_EGOV, CostantiBusta.MITTENTE);
            Element elementoIdentificativoParteMittente = BustaUtil.appendValues(elementoMittente, CostantiSOAP.NAMESPACE_EGOV, CostantiSOAP.PREFIX_EGOV, CostantiBusta.IDENTIFICATIVOPARTE, messaggio.getFruitore());
            BustaUtil.aggiungiAttributo(elementoIdentificativoParteMittente, CostantiBusta.TIPO, messaggio.getTipoFruitore());
            if (messaggio.getIndirizzoTelematico() != null) {
                BustaUtil.aggiungiAttributo(elementoIdentificativoParteMittente, CostantiBusta.INDIRIZZOTELEMATICO, messaggio.getIndirizzoTelematico());
            }

            Element elementoDestinatario = BustaUtil.appendHeader(elementoIntestazioneMessaggio, CostantiSOAP.NAMESPACE_EGOV, CostantiSOAP.PREFIX_EGOV, CostantiBusta.DESTINATARIO);
            Element elementoIdentificativoParteDestinatario = BustaUtil.appendValues(elementoDestinatario, CostantiSOAP.NAMESPACE_EGOV, CostantiSOAP.PREFIX_EGOV, CostantiBusta.IDENTIFICATIVOPARTE, messaggio.getErogatore());
            BustaUtil.aggiungiAttributo(elementoIdentificativoParteDestinatario, CostantiBusta.TIPO, messaggio.getTipoErogatore());
            if (messaggio.getIndirizzoTelematicoErogatore() != null) {
                BustaUtil.aggiungiAttributo(elementoIdentificativoParteDestinatario, CostantiBusta.INDIRIZZOTELEMATICO, messaggio.getIndirizzoTelematicoErogatore());
            }

            if (messaggio.getProfiloCollaborazione() != null && !messaggio.getProfiloCollaborazione().isEmpty()) {
                Element elementoProfiloCollaborazione = BustaUtil.appendValues(elementoIntestazioneMessaggio, CostantiSOAP.NAMESPACE_EGOV, CostantiSOAP.PREFIX_EGOV, CostantiBusta.PROFILOCOLLABORAZIONE, messaggio.getProfiloCollaborazione());
            }
//            if (messaggio.getServizioCorrelato() != null) {
//                BustaUtil.aggiungiAttributo(elementoProfiloCollaborazione, CostantiBusta.SERVIZIOCORRELATO, messaggio.getServizioCorrelato());
//                BustaUtil.aggiungiAttributo(elementoProfiloCollaborazione, CostantiBusta.TIPO, messaggio.getTipoServizioCorrelato());
//            }

            if (messaggio.getCollaborazione() != null) {
                Element elementoCollaborazione = BustaUtil.appendValues(elementoIntestazioneMessaggio, CostantiSOAP.NAMESPACE_EGOV, CostantiSOAP.PREFIX_EGOV, CostantiBusta.COLLABORAZIONE, messaggio.getCollaborazione());
            }

            if (messaggio.getServizio() != null) {
                Element elementoServizio = BustaUtil.appendValues(elementoIntestazioneMessaggio, CostantiSOAP.NAMESPACE_EGOV, CostantiSOAP.PREFIX_EGOV, CostantiBusta.SERVIZIO, messaggio.getServizio());
                BustaUtil.aggiungiAttributo(elementoServizio, CostantiBusta.TIPO, messaggio.getTipoServizio());
            }

            if (messaggio.getAzione() != null) {
                Element elementoAzione = BustaUtil.appendValues(elementoIntestazioneMessaggio, CostantiSOAP.NAMESPACE_EGOV, CostantiSOAP.PREFIX_EGOV, CostantiBusta.AZIONE, messaggio.getAzione());
            }

            Element elementoMessaggio = BustaUtil.appendHeader(elementoIntestazioneMessaggio, CostantiSOAP.NAMESPACE_EGOV, CostantiSOAP.PREFIX_EGOV, CostantiBusta.ELEMENTOMESSAGGIO);
            Element elementoIdentificatoreMessaggio = BustaUtil.appendValues(elementoMessaggio, CostantiSOAP.NAMESPACE_EGOV, CostantiSOAP.PREFIX_EGOV, CostantiBusta.IDENTIFICATORE, messaggio.getIdMessaggio());
            Element elementoOraRegistrazioneMessaggio = BustaUtil.appendValues(elementoMessaggio, CostantiSOAP.NAMESPACE_EGOV, CostantiSOAP.PREFIX_EGOV, CostantiBusta.ORAREGISTRAZIONE, messaggio.getOraRegistrazione());
            BustaUtil.aggiungiAttributo(elementoOraRegistrazioneMessaggio, CostantiBusta.TEMPO, configurazione.getTempo());
            if (messaggio.getRiferimentoMessaggio() != null && !messaggio.getRiferimentoMessaggio().isEmpty()) {
                Element elementoRiferimentoMessaggio = BustaUtil.appendValues(elementoMessaggio, CostantiSOAP.NAMESPACE_EGOV, CostantiSOAP.PREFIX_EGOV, CostantiBusta.RIFERIMENTOMESSAGGIO, messaggio.getRiferimentoMessaggio());
            }

            if (messaggio.getInoltro() != null && !messaggio.getInoltro().isEmpty()) {
                Element elementoProfiloTrasmissione = BustaUtil.appendHeader(elementoIntestazioneMessaggio, CostantiSOAP.NAMESPACE_EGOV, CostantiSOAP.PREFIX_EGOV, CostantiBusta.PROFILOTRASMISSIONE);
                BustaUtil.aggiungiAttributo(elementoProfiloTrasmissione, CostantiBusta.INOLTRO, messaggio.getInoltro());
                BustaUtil.aggiungiAttributo(elementoProfiloTrasmissione, CostantiBusta.CONFERMARICEZIONE, messaggio.getConfermaRicezione());
            }

//            if (messaggio.getNumeroProgressivo() != null) {
//                Element elementoSequenza = BustaUtil.appendHeader(elementoIntestazioneMessaggio, CostantiSOAP.NAMESPACE_EGOV, CostantiSOAP.PREFIX_EGOV, CostantiBusta.SEQUENZA);
//                BustaUtil.aggiungiAttributo(elementoSequenza, CostantiBusta.NUMEROPROGRESSIVO, messaggio.getNumeroProgressivo());
//            }
            /* ------------------------------------------------ FINE INTESTAZIONE MESSAGGIO ------------------------------------------------ */

            //Dovremmo solo aggiungere la nostra trasmissione, in realtà per la qualificazione dobbiamo *ricrearla*, in modo da poterla riempire con i campi giusti anche
            //quando loro ci mandano dati scorretti (ex. erogatore scorretto)
            Element elementoListaTrasmissioni = BustaUtil.appendHeader(nodoIntestazione, CostantiSOAP.NAMESPACE_EGOV, CostantiSOAP.PREFIX_EGOV, "ListaTrasmissioni");
            aggiungiTrasmissione(messaggioRichiesta, elementoListaTrasmissioni);
            aggiungiTrasmissione(messaggio, elementoListaTrasmissioni);


            if (messaggio.getListaEccezioni().size() > 0) {
                Element elementoListaEccezioni = BustaUtil.appendHeader(nodoIntestazione, CostantiSOAP.NAMESPACE_EGOV, CostantiSOAP.PREFIX_EGOV, CostantiBusta.LISTAECCEZIONI);
                for (Eccezione eccezione : messaggio.getListaEccezioni()) {
                    Element elementoEccezione = BustaUtil.appendHeader(elementoListaEccezioni, CostantiSOAP.NAMESPACE_EGOV, CostantiSOAP.PREFIX_EGOV, CostantiBusta.ECCEZIONE);
                    BustaUtil.aggiungiAttributo(elementoEccezione, CostantiBusta.CODICEECCEZIONE, eccezione.convertiCodiceEccezione());
                    BustaUtil.aggiungiAttributo(elementoEccezione, CostantiBusta.CONTESTOCODIFICA, eccezione.getContestoCodifica());
                    BustaUtil.aggiungiAttributo(elementoEccezione, CostantiBusta.POSIZIONE, eccezione.getPosizione());
                    BustaUtil.aggiungiAttributo(elementoEccezione, CostantiBusta.RILEVANZA, eccezione.getRilevanza());
                }
            }

//        if (VerificaEsistenzaProprieta(intestazioni, "listaRiscontri")) {
//            Element elementoListaRiscontri = BustaUtil.appendHeader(nodoIntestazione, CostantiSOAP.NAMESPACE_EGOV, CostantiSOAP.PREFIX_EGOV, CostantiBusta.LISTARISCONTRI);
//            Element elementoRiscontro = BustaUtil.appendHeader(elementoListaRiscontri, CostantiSOAP.NAMESPACE_EGOV, CostantiSOAP.PREFIX_EGOV, CostantiBusta.RISCONTRO);
//            Element elementoIdentificatoreRiscontro = BustaUtil.appendValues(elementoRiscontro, CostantiSOAP.NAMESPACE_EGOV, CostantiSOAP.PREFIX_EGOV, CostantiBusta.IDENTIFICATORE, (String) intestazioni.get(CostantiBusta.VALOREIDENTIFICATORE)); // CostantiBusta.VALOREIDENTIFICATORE = "IcarlabSoggettoFruitore_IcarlabSoggettoFruitoreSPCoopIT_0000012_" + UtilitaDate.getDataAttuale() + "_" + UtilitaDate.getOraAttuale()
//            Element elementoOraRegistrazioneRiscontro = BustaUtil.appendValues(elementoRiscontro, CostantiSOAP.NAMESPACE_EGOV, CostantiSOAP.PREFIX_EGOV, CostantiBusta.ORAREGISTRAZIONE, UtilitaDate.formattaDataEOra((Date) intestazioni.get(CostantiBusta.VALOREORAREGISTRAZIONE)));
//            BustaUtil.aggiungiAttributo(elementoOraRegistrazioneRiscontro, CostantiBusta.TEMPO, (String) intestazioni.get(CostantiBusta.VALORETEMPO));
//        }


        } catch (Exception ex) {
            throw new FreesbeeException("Errore nel processamento delle intestazioni egov " + ex);
        }
    }

    private void aggiungiTrasmissione(Messaggio messaggio, Element elementoListaTrasmissioni) {
        if (messaggio == null) {
            return;
        }
        Element elementoTrasmissione = BustaUtil.appendHeader(elementoListaTrasmissioni, CostantiSOAP.NAMESPACE_EGOV, CostantiSOAP.PREFIX_EGOV, CostantiBusta.TRASMISSIONE);

        String nomeFritore = messaggio.getFruitore();
        if (nomeFritore == null || nomeFritore.isEmpty()) {
            nomeFritore = "Sconosciuto";
        }
        String tipoFruitore = "SPC";
        Element elementoOrigineTrasmissione = BustaUtil.appendHeader(elementoTrasmissione, CostantiSOAP.NAMESPACE_EGOV, CostantiSOAP.PREFIX_EGOV, CostantiBusta.ORIGINE);
        Element elementoIdentificativoParteOrigineTrasmissione = BustaUtil.appendValues(elementoOrigineTrasmissione, CostantiSOAP.NAMESPACE_EGOV, CostantiSOAP.PREFIX_EGOV, CostantiBusta.IDENTIFICATIVOPARTE, nomeFritore);
        BustaUtil.aggiungiAttributo(elementoIdentificativoParteOrigineTrasmissione, CostantiBusta.TIPO, tipoFruitore);

        String nomeErogatore = messaggio.getErogatore();
        if (nomeErogatore == null || nomeErogatore.isEmpty()) {
            nomeErogatore = "Sconosciuto";
        }
        String tipoErogatore = "SPC";
        Element elementoDestinazioneTrasmissione = BustaUtil.appendHeader(elementoTrasmissione, CostantiSOAP.NAMESPACE_EGOV, CostantiSOAP.PREFIX_EGOV, CostantiBusta.DESTINAZIONE);
        Element elementoIdentificativoParteDestinazioneTrasmissione = BustaUtil.appendValues(elementoDestinazioneTrasmissione, CostantiSOAP.NAMESPACE_EGOV, CostantiSOAP.PREFIX_EGOV, CostantiBusta.IDENTIFICATIVOPARTE, nomeErogatore);
        BustaUtil.aggiungiAttributo(elementoIdentificativoParteDestinazioneTrasmissione, CostantiBusta.TIPO, tipoErogatore);

        Element elementoOraRegistrazioneTrasmissione = BustaUtil.appendValues(elementoTrasmissione, CostantiSOAP.NAMESPACE_EGOV, CostantiSOAP.PREFIX_EGOV, CostantiBusta.ORAREGISTRAZIONE, messaggio.getOraRegistrazione());
        BustaUtil.aggiungiAttributo(elementoOraRegistrazioneTrasmissione, CostantiBusta.TEMPO, "EGOV_IT_SPC");
    }

    public DBManager getDbManager() {
        return dbManager;
    }

    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }
}
