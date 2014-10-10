package it.unibas.icar.freesbee.processors;

import com.google.inject.Singleton;
import it.unibas.guicefreesbee.ContextStartup;
import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.utilita.CostantiBusta;
import it.unibas.icar.freesbee.utilita.CostantiSOAP;
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
public class ProcessorEnricherTestInteroperabilita implements Processor {

    private static Log logger = LogFactory.getLog(ProcessorEnricherTestInteroperabilita.class);

    public ProcessorEnricherTestInteroperabilita() {
    }

    @SuppressWarnings("unchecked")
    public void process(Exchange exchange) throws Exception {
        //ContextStartup.aggiungiThread(this.getClass().getName());
        //new ProcessorLog(ProcessorEnricherTestInteroperabilita.class).process(exchange);
        ProcessorLogFactory.getInstance().getProcessorLog(this.getClass()).process(exchange);
        Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiBusta.MESSAGGIO);

        String fruitore = messaggio.getFruitore();
        String erogatore = messaggio.getErogatore();
        String servizio = messaggio.getServizio();
        String identificatoreMessaggio = messaggio.getIdMessaggio();
        boolean isNica = messaggio.isNica();

        Map<QName, DocumentFragment> mappaHeaders = (Map<QName, DocumentFragment>) exchange.getProperty(CostantiSOAP.SOAP_HEADERS);

        if (mappaHeaders == null) {
            mappaHeaders = new HashMap<QName, DocumentFragment>();
            exchange.setProperty(CostantiSOAP.SOAP_HEADERS, mappaHeaders);
        }

        if (servizio.startsWith(CostantiBusta.SERVIZIO_TEST)) {
            if (logger.isInfoEnabled()) logger.info("Ricevuto messaggio di test. Aggiungo le intestazioni");
            // Creazione dell'header <frsb_Test>

//            DocumentFragment dfTestRichiesta = FreesbeeUtil.createEmptyHeader(CostantiSOAP.NAMESPACE_EGOV_TEST, CostantiSOAP.PREFIX_EGOV_TEST, "TestRichiesta");

            // Creazione dell'header di test per la richiesta
            if (isNica) {
                if (logger.isInfoEnabled()) logger.info("Aggiungo intestazioni test nica");
                DocumentFragment dfTest = BustaUtil.createEmptyHeader(CostantiSOAP.NAMESPACE_EGOV_TEST, CostantiSOAP.PREFIX_EGOV_TEST, "Header_EGov_Test_Nica");
                mappaHeaders.put(new QName(CostantiSOAP.NAMESPACE_EGOV_TEST, "Header_EGov_Test_Nica"), dfTest);
                Node nodoTestRichiesta = BustaUtil.cercaNodo(dfTest.getChildNodes(), "Header_EGov_Test_Nica");
//                Element elementoTestMessaggio = FreesbeeUtil.appendHeader(nodoTest, CostantiSOAP.NAMESPACE_EGOV_TEST, CostantiSOAP.PREFIX_EGOV_TEST, "TestMessaggio");
                if (nodoTestRichiesta != null) {
                    Element elementoIDMessaggioRichiesta = BustaUtil.appendValues(nodoTestRichiesta, CostantiSOAP.NAMESPACE_EGOV_TEST, CostantiSOAP.PREFIX_EGOV_TEST, "IDMessaggio", identificatoreMessaggio);
                    Element elementoFruitoreRichiesta = BustaUtil.appendValues(nodoTestRichiesta, CostantiSOAP.NAMESPACE_EGOV_TEST, CostantiSOAP.PREFIX_EGOV_TEST, "NICA", messaggio.getNomeNica().getNome());
                }
            }
            // Creazione dell'header di test per la richiesta
            String imbustaRichiesta = (String) exchange.getIn().getHeader(CostantiBusta.IMBUSTA_RICHIESTA_RISPOSTA);
            if ((imbustaRichiesta != null) && (imbustaRichiesta.equalsIgnoreCase(CostantiBusta.VALORE_IMBUSTA_RICHIESTA))) {
                DocumentFragment dfTest = BustaUtil.createEmptyHeader(CostantiSOAP.NAMESPACE_EGOV_TEST, CostantiSOAP.PREFIX_EGOV_TEST, "Header_EGov_Test_Richiesta");
                mappaHeaders.put(new QName(CostantiSOAP.NAMESPACE_EGOV_TEST, "Header_EGov_Test"), dfTest);
                Node nodoTestRichiesta = BustaUtil.cercaNodo(dfTest.getChildNodes(), "Header_EGov_Test_Richiesta");
//                Element elementoTestMessaggio = FreesbeeUtil.appendHeader(nodoTest, CostantiSOAP.NAMESPACE_EGOV_TEST, CostantiSOAP.PREFIX_EGOV_TEST, "TestMessaggio");
                if (nodoTestRichiesta != null) {
                    Element elementoIDMessaggioRichiesta = BustaUtil.appendValues(nodoTestRichiesta, CostantiSOAP.NAMESPACE_EGOV_TEST, CostantiSOAP.PREFIX_EGOV_TEST, "IDMessaggio", identificatoreMessaggio);
                    Element elementoFruitoreRichiesta = BustaUtil.appendValues(nodoTestRichiesta, CostantiSOAP.NAMESPACE_EGOV_TEST, CostantiSOAP.PREFIX_EGOV_TEST, "Fruitore", fruitore);
                    Element elementoErogatoreRichiesta = BustaUtil.appendValues(nodoTestRichiesta, CostantiSOAP.NAMESPACE_EGOV_TEST, CostantiSOAP.PREFIX_EGOV_TEST, "Erogatore", erogatore);
                    Element elementoServizioRichiesta = BustaUtil.appendValues(nodoTestRichiesta, CostantiSOAP.NAMESPACE_EGOV_TEST, CostantiSOAP.PREFIX_EGOV_TEST, "Servizio", servizio);
                }
            }

            // Creazione dell'header di test per la risposta
            String imbustaRisposta = (String) exchange.getIn().getHeader(CostantiBusta.IMBUSTA_RICHIESTA_RISPOSTA);
            if ((imbustaRisposta != null) && (imbustaRisposta.equalsIgnoreCase(CostantiBusta.VALORE_IMBUSTA_RISPOSTA))) {

                //Controllo se ci sono le intestazioni della richiesta e le inserisco
                DocumentFragment dfTestRichiesta = (DocumentFragment) exchange.getProperty("IntestazioniTestRichiesta");
                if (dfTestRichiesta != null) {
                    if (logger.isInfoEnabled()) logger.info("Ho trovato le informazioni di test della richiesta. Le inserisco");
                    mappaHeaders.put(new QName(CostantiSOAP.NAMESPACE_EGOV_TEST, "Header_EGov_Test_Richiesta"), dfTestRichiesta);
                }

//                DocumentFragment dfTestRisposta = FreesbeeUtil.createEmptyHeader(CostantiSOAP.NAMESPACE_EGOV_TEST, CostantiSOAP.PREFIX_EGOV_TEST, "TestRisposta");
                DocumentFragment dfTestRisposta = BustaUtil.createEmptyHeader(CostantiSOAP.NAMESPACE_EGOV_TEST, CostantiSOAP.PREFIX_EGOV_TEST, "Header_EGov_Test_Risposta");
                mappaHeaders.put(new QName(CostantiSOAP.NAMESPACE_EGOV_TEST, "Header_EGov_Test"), dfTestRisposta);

//                mappaHeaders.put(new QName(CostantiSOAP.NAMESPACE_EGOV_TEST, "Header_EGov_Test_Risposta"), dfTest);

                Node nodoTestRisposta = BustaUtil.cercaNodo(dfTestRisposta.getChildNodes(), "Header_EGov_Test_Risposta");
//                Element elementoTestMessaggio = FreesbeeUtil.appendHeader(nodoTest, CostantiSOAP.NAMESPACE_EGOV_TEST, CostantiSOAP.PREFIX_EGOV_TEST, "TestMessaggio");
                if (nodoTestRisposta != null) {
                    Element elementoIDMessaggioRisposta = BustaUtil.appendValues(nodoTestRisposta, CostantiSOAP.NAMESPACE_EGOV_TEST, CostantiSOAP.PREFIX_EGOV_TEST, "IDMessaggio", identificatoreMessaggio);
                    Element elementoFruitoreRisposta = BustaUtil.appendValues(nodoTestRisposta, CostantiSOAP.NAMESPACE_EGOV_TEST, CostantiSOAP.PREFIX_EGOV_TEST, "Fruitore", fruitore);
                    Element elementoErogatoreRisposta = BustaUtil.appendValues(nodoTestRisposta, CostantiSOAP.NAMESPACE_EGOV_TEST, CostantiSOAP.PREFIX_EGOV_TEST, "Erogatore", erogatore);
                    Element elementoServizioRisposta = BustaUtil.appendValues(nodoTestRisposta, CostantiSOAP.NAMESPACE_EGOV_TEST, CostantiSOAP.PREFIX_EGOV_TEST, "Servizio", servizio);
                }
            }
        } else {
            if (logger.isInfoEnabled()) logger.info("Ricevuto messaggio non di test.");
        }
    }
}
