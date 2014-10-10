package it.unibas.silvio.processor;

import it.unibas.silvio.modello.Configurazione;
import it.unibas.silvio.modello.Dati;
import it.unibas.silvio.modello.DatiCostanti;
import it.unibas.silvio.modello.DatiDB;
import it.unibas.silvio.modello.DatiSQL;
import it.unibas.silvio.modello.DatoPrimitivo;
import it.unibas.silvio.modello.IstanzaOperation;
import it.unibas.silvio.modello.Messaggio;
import it.unibas.silvio.persistenza.IDAOConfigurazione;
import it.unibas.silvio.persistenza.SilvioException;
import it.unibas.silvio.util.CostantiSilvio;
import it.unibas.silvio.xml.XmlJDomUtil;
import java.io.File;
import java.util.List;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;

public class ProcessorTransformerCompletiSoapErogatore implements Processor {

    private Log logger = LogFactory.getLog(this.getClass());
    private IDAOConfigurazione daoConfigurazione;

    public ProcessorTransformerCompletiSoapErogatore(IDAOConfigurazione daoConfigurazione) {
        this.daoConfigurazione = daoConfigurazione;
    }

    public void process(Exchange exchange) throws Exception {
        Messaggio messaggioRichiesta = (Messaggio) exchange.getProperty(CostantiSilvio.MESSAGGIO_RICHIESTA);
        Messaggio messaggioRisposta = (Messaggio) exchange.getProperty(CostantiSilvio.MESSAGGIO_RISPOSTA);
        IstanzaOperation istanzaOperation = messaggioRisposta.getIstanzaOperation();
        Dati datiCompleti = messaggioRisposta.getDatiCompletiRisposta();

        if(datiCompleti.isPayloadCostante()){
            messaggioRisposta.setMessaggio(datiCompleti.getPayloadCostante());
            return;
        }

        boolean istanzaTest = messaggioRichiesta.getParametriMessaggioRicevuto().isTest();
        if (istanzaTest) {
            logger.info("Sto eseguendo un'istanza di test quindi non effettuo alcuna trasformazione");
            return;
        }
        try {
            Configurazione configurazione = daoConfigurazione.caricaConfigurazione();
            Document documentDatiCompleti = creaDocumentDatiRisposta(datiCompleti, messaggioRichiesta);
            if (logger.isDebugEnabled()) {
                logger.debug("DATI COMPLETI\n" + XmlJDomUtil.stampaXML(documentDatiCompleti));
//                XmlJDomUtil.salvaXML("c:\\tmp\\datiCompletiErogatore.xml", documentDatiCompleti);
            }
            String fileXSLT = configurazione.getDirConfig() + istanzaOperation.getIstanzaPortType().getIstanzaAccordo().getCartellaFiles() +
                    istanzaOperation.getXSLTDatiToSoapErogatore();
            logger.info("File di trasformazione " + fileXSLT);
            File file = new File(fileXSLT);
            if (!file.canRead()) {
                throw new SilvioException("Impossibile leggere il file di trasformazione " + fileXSLT);
            }
            Document rispostaSOAP = XmlJDomUtil.transformToDocument(fileXSLT, documentDatiCompleti);
            if (logger.isDebugEnabled()) {
                logger.debug("RISPOSTA SOAP\n" + XmlJDomUtil.stampaXML(rispostaSOAP));
//                XmlJDomUtil.salvaXML("c:\\tmp\\rispostaSOAP.xml", rispostaSOAP);
            }
            if (rispostaSOAP != null && rispostaSOAP.getRootElement() != null && rispostaSOAP.getRootElement().getName().equals(CostantiSilvio.ERRORE_SILVIO)) {
                throw new SilvioException(rispostaSOAP.getRootElement().getText());
            }
            messaggioRisposta.setMessaggio(XmlJDomUtil.stampaXML(rispostaSOAP));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Impossibile eseguire la trasformazione " + e);
            throw new SilvioException("Impossibile eseguire la trasformazione " + e);
        }
    }

    private Document creaDocumentDatiRisposta(Dati datiCompleti, Messaggio messaggioRichiesta) {
        Element rootElement = new Element("datiRisposta");
        Document datiCompletiDocument = new Document(rootElement);
        rootElement.addContent(creaDocumentDatiCompleti(datiCompleti));
        rootElement.addContent(creaDocumentDatiRichiesta(messaggioRichiesta));
        return datiCompletiDocument;
    }

    private Element creaDocumentDatiRichiesta(Messaggio messaggioRichiesta) {
        Element rootElement = new Element("datiRichiesta");
        try {
            org.w3c.dom.Document w3cBodyDocument = messaggioRichiesta.getBodyMessaggio();
            if (w3cBodyDocument != null) {
                Document bodyDocument = XmlJDomUtil.convert(w3cBodyDocument);
                if (bodyDocument != null && bodyDocument.getRootElement() != null) {
                    rootElement.addContent((Element)bodyDocument.getRootElement().clone());
                }
            }
        } catch (Exception ex) {
            logger.warn("Impossibile includere nei dati completi il messaggio della richiesta " + ex);
        }
        return rootElement;
    }

    private Element creaDocumentDatiCompleti(Dati datiCompleti) {
        Element rootElement = new Element("datiCompleti");
        DatiSQL datiSQL = datiCompleti.getDatiSQL();
        DatiCostanti datiCostanti = datiCompleti.getDatiCostanti();
        if (datiSQL != null) {
            List<DatiDB> listaDB = datiSQL.getListaDatiDB();
            for (DatiDB datiDB : listaDB) {
                rootElement.addContent(aggiungiDati(datiDB.getDatiDB(), "datiDB"));
            }
        }else{
            logger.info("DATI DB NULL");
        }
        if (datiCostanti != null) {
            rootElement.addContent(aggiungiDati(datiCostanti.getDatiCostanti(), "datiCostanti"));
        }else{
            logger.info("DATI COSTANTI NULL");
        }
        return rootElement;
    }

    private Element aggiungiDati(List<DatoPrimitivo> datiCostanti, String string) {
        Element elementDati = new Element(string);
        for (DatoPrimitivo datoPrimitivo : datiCostanti) {
            Element dato = new Element(datoPrimitivo.getNome());
            dato.setText(datoPrimitivo.getValore());
            elementDati.addContent(dato);
        }
        return elementDati;
    }
}
