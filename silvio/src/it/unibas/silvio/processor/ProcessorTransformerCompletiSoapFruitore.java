package it.unibas.silvio.processor;

import it.unibas.silvio.modello.Configurazione;
import it.unibas.silvio.modello.Dati;
import it.unibas.silvio.modello.DatiCostanti;
import it.unibas.silvio.modello.DatiDB;
import it.unibas.silvio.modello.DatiInterattivi;
import it.unibas.silvio.modello.DatiSQL;
import it.unibas.silvio.modello.DatoPrimitivo;
import it.unibas.silvio.modello.IstanzaOperation;
import it.unibas.silvio.modello.Messaggio;
import it.unibas.silvio.modello.ParametriEseguiIstanza;
import it.unibas.silvio.persistenza.IDAOConfigurazione;
import it.unibas.silvio.persistenza.SilvioException;
import it.unibas.silvio.util.CostantiSilvio;
import it.unibas.silvio.xml.XmlJDomUtil;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;

public class ProcessorTransformerCompletiSoapFruitore implements Processor {

    private Log logger = LogFactory.getLog(this.getClass());
    private IDAOConfigurazione daoConfigurazione;

    public ProcessorTransformerCompletiSoapFruitore(IDAOConfigurazione daoConfigurazione) {
        this.daoConfigurazione = daoConfigurazione;
    }

    public void process(Exchange exchange) throws Exception {
        Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiSilvio.MESSAGGIO_RICHIESTA);
        ParametriEseguiIstanza parametriIstanza = messaggio.getParametriEseguiIstanza();
        IstanzaOperation istanzaOperation = messaggio.getIstanzaOperation();
        boolean istanzaTest = parametriIstanza.isTest();
        Dati datiCompleti = messaggio.getDatiCompletiRichiesta();

        if (datiCompleti.isPayloadCostante()) {
            messaggio.setMessaggio(datiCompleti.getPayloadCostante());
            return;
        }

        if (istanzaTest) {
            logger.info("Sto eseguendo un'istanza di test quindi non effettuo alcuna trasformazione");
            return;
        }
        try {
            Configurazione configurazione = daoConfigurazione.caricaConfigurazione();
            Document documentDatiCompleti = creaDocumentDatiCompleti(datiCompleti);
            if (logger.isDebugEnabled()) {
                logger.debug(XmlJDomUtil.stampaXML(documentDatiCompleti));
//                XmlJDomUtil.salvaXML("c:\\tmp\\datiCompleti.xml", documentDatiCompleti);
            }
            String fileXSLT = configurazione.getDirConfig() + istanzaOperation.getIstanzaPortType().getIstanzaAccordo().getCartellaFiles() +
                    istanzaOperation.getXSLTCompletiToSoapFruitore();
            File file = new File(fileXSLT);
            if (!file.canRead()) {
                throw new SilvioException("Impossibile leggere il file di trasformazione " + fileXSLT);
            }
            Document richiestaSOAP = XmlJDomUtil.transformToDocument(fileXSLT, documentDatiCompleti);
            if (logger.isDebugEnabled()) {
                logger.debug(XmlJDomUtil.stampaXML(richiestaSOAP));
//                XmlJDomUtil.salvaXML("c:\\tmp\\richiestaSOAP.xml", richiestaSOAP);
            }
            if (richiestaSOAP != null && richiestaSOAP.getRootElement() != null && richiestaSOAP.getRootElement().getName().equals(CostantiSilvio.ERRORE_SILVIO)) {
                throw new SilvioException(richiestaSOAP.getRootElement().getText());
            }
            messaggio.setMessaggio(XmlJDomUtil.stampaXML(richiestaSOAP));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Impossibile eseguire la trasformazione " + e);
            throw new SilvioException("Impossibile eseguire la trasformazione " + e);
        }
    }

    private Document creaDocumentDatiCompleti(Dati datiCompleti) {
        Element rootElement = new Element("datiCompleti");
        Document datiCompletiDocument = new Document(rootElement);
        DatiSQL datiSQL = datiCompleti.getDatiSQL();
        DatiInterattivi datiInterattivi = datiCompleti.getDatiInterattivi();
        DatiCostanti datiCostanti = datiCompleti.getDatiCostanti();
        if (datiSQL != null) {
            List<DatiDB> listaDB = datiSQL.getListaDatiDB();
            for (DatiDB datiDB : listaDB) {
                rootElement.addContent(aggiungiDati(datiDB.getDatiDB(), "datiDB"));
            }
        }
        if (datiInterattivi != null) {
            rootElement.addContent(aggiungiDati(datiInterattivi.getDatiInterattivi(), "datiInterattivi"));
        }
        if (datiCostanti != null) {
            rootElement.addContent(aggiungiDati(datiCostanti.getDatiCostanti(), "datiCostanti"));
        }
        return datiCompletiDocument;
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
