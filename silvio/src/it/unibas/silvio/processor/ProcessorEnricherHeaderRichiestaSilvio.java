package it.unibas.silvio.processor;

import it.unibas.silvio.modello.IstanzaOperation;
import it.unibas.silvio.modello.Messaggio;
import it.unibas.silvio.modello.ParametriEseguiIstanza;
import it.unibas.silvio.util.CostantiSOAP;
import it.unibas.silvio.util.CostantiSilvio;
import it.unibas.silvio.util.SilvioUtil;
import java.util.List;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPFactory;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProcessorEnricherHeaderRichiestaSilvio implements Processor {

    private Log logger = LogFactory.getLog(this.getClass());

    public void process(Exchange exchange) throws Exception {
        Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiSilvio.MESSAGGIO_RICHIESTA);
        ParametriEseguiIstanza parametriIstanza = messaggio.getParametriEseguiIstanza();
        IstanzaOperation istanzaOperation = messaggio.getIstanzaOperation();
        boolean istanzaTest = parametriIstanza.isTest();
        
        List<SOAPElement> lista = SilvioUtil.getSOAPHeaderList(exchange);
        if (istanzaOperation.isAsincrono()) {
            logger.info("Operazione asincrona. Devo caricare l'indirizzo per la risposta.");
            String indirizzoAscolto = creaIndirizzoRisposta(istanzaOperation);
            if (indirizzoAscolto != null) {
                logger.info("Indirizzo di ascolto: " + indirizzoAscolto);
                SOAPElement indirizzoAscoltoRisposta = SOAPFactory.newInstance().createElement(CostantiSOAP.INDIRIZZO_ASCOLTO_RISPOSTA);
                indirizzoAscoltoRisposta.setTextContent(indirizzoAscolto);
                lista.add(indirizzoAscoltoRisposta);
            }
        }
        //CREO L'ELEMENTO PER L'ISTANZA DI TEST E PER IL NOME DELL'OPERATION
        SOAPElement elementIstanzaTest = SOAPFactory.newInstance().createElement(CostantiSOAP.ISTANZA_TEST);
        elementIstanzaTest.setTextContent("" + istanzaTest);
        SOAPElement elementNomeOperation = SOAPFactory.newInstance().createElement(CostantiSOAP.NOME_OPERATION);
        elementNomeOperation.setTextContent(istanzaOperation.getNome());
        lista.add(elementIstanzaTest);
        lista.add(elementNomeOperation);
    }
    
    private String creaIndirizzoRisposta(IstanzaOperation istanzaOperation) {
//        IstanzaPortType myIstanzaPortType = istanzaOperation.getIstanzaPortType();
//        IstanzaAccordoDiCollaborazione istanzaAccordo = myIstanzaPortType.getIstanzaAccordo();
//        IstanzaAccordoDiCollaborazione istanzaAccordoCaricata = daoIstanzaAccordo.findByNome(istanzaAccordo.getNome());
//        List<IstanzaPortType> listaIstanzePortType = istanzaAccordoCaricata.getListaIstanzePortType();
//        for (IstanzaPortType istanzaPortType : listaIstanzePortType) {
//            if (myIstanzaPortType.getNome().equals(istanzaPortType.getNome()) && istanzaPortType.isErogazioneRisposta()) {
//                String indirizzo = "localhost:9192/" + istanzaPortType.getURLAscolto();            
//                return indirizzo;
//            }
//        }
        return null;
    }
}
