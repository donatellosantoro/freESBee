package it.unibas.freesbeesla.ws.web.persistenza.soap;

import it.unibas.freesbee.monitoraggio.calcolo.functions.aggregate.AggregateFunction;
import it.unibas.freesbee.monitoraggio.calcolo.functions.binary.BinaryFunction;
import it.unibas.freesbee.monitoraggio.calcolo.functions.hits.HitsFunction;
import it.unibas.freesbee.monitoraggio.calcolo.functions.round.RoundFunction;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.FunctionType;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.GuaranteeTermType;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.GuaranteeTerms;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Hits;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.IntervalTimeType;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Max;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Mean;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Min;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.OperandType;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Round;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Sum;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Window;
import it.unibas.freesbee.monitoraggio.exception.inf2exception.INF2Exception;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ValidatoreWsag {

    private static Log logger = LogFactory.getLog(ValidatoreWsag.class);

    public static String getWSAG(InputStream inputStream) throws DAOException, IOException {
        logger.info("Verifica del Wsag");
        
        String sla = null;
        logger.debug("Ripulitura wsag");
        InputStream isRipulito = Ripulitore.ripulisci(inputStream); //Chiamata al ripulitore
        Document document = creaDocument(isRipulito);
        Source dom = new DOMSource(document);

        if (verifica(document)) {
            try {
       
                Transformer transformer = null;
                StringWriter nuovoSla = new StringWriter();
                Result result = new StreamResult(nuovoSla);
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty("encoding", "UTF-8");
                transformer.transform(dom, result);
                sla = nuovoSla.toString();
                return sla;
            } catch (Exception ex) {
                logger.error("Errore nella validazione del wsag. " + ex);
                throw new DAOException(ex);
            }
        }
        return null;
    }

    private static Document creaDocument(InputStream inputStream) throws DAOException {
        Document document = null;
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setIgnoringComments(true);
            logger.info("**Il parser ignora i commenti : " + documentBuilderFactory.isIgnoringComments());
            documentBuilderFactory.setNamespaceAware(true);
            documentBuilderFactory.setValidating(true);
            documentBuilderFactory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
            documentBuilderFactory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaSource", ValidatoreWsag.class.getResourceAsStream("/ws-agreementICAR.xsd"));
            DocumentBuilder docBuilder = documentBuilderFactory.newDocumentBuilder();
            docBuilder.setErrorHandler(new ErrorHandlerWSAG());

            Document documentSorgente = docBuilder.parse(inputStream);

            documentSorgente.getDocumentElement().normalize();
            NodeList elementoGuaranteeTerm = documentSorgente.getElementsByTagName("wsag:GuaranteeTerm");
            document = docBuilder.newDocument();
            Element elementoRadice = document.createElementNS("http://schemas.ggf.org/graap/2007/03/ws-agreement", "wsag:GuaranteeTerms");
            document.appendChild(elementoRadice);
            elementoRadice.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            elementoRadice.setAttributeNS("http://www.w3.org/2001/XMLSchema-instance", "xsi:schemaLocation", "http://schemas.ggf.org/graap/2007/03/ws-agreement GuaranteeTerms.xsd");
            int size = elementoGuaranteeTerm.getLength();
            for (int i = 0; i < size; i++) {
                Element elementoIesimo = (Element) elementoGuaranteeTerm.item(i);
                Node nodo = document.importNode(elementoIesimo, true);
                document.getDocumentElement().appendChild(nodo);
            }
        } catch (ParserConfigurationException e) {
            logger.error(e.getMessage());
            throw new DAOException("Errore durante il parsing del file. " + e.getMessage());
        } catch (SAXException e) {
            logger.error(e.getMessage());
            throw new DAOException("Errore durante il parsing del file. " + e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new DAOException("Errore durante la lettura del file. " + e.getMessage());
        }
        document.normalizeDocument();
        return document;
    }

    private static boolean verifica(Document document) throws DAOException {
        JAXBContext context;
        try {

            context = JAXBContext.newInstance("it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type");
            Unmarshaller unmarshaller = context.createUnmarshaller();

            GuaranteeTerms guaranteeTerms = (GuaranteeTerms) unmarshaller.unmarshal(document.getDocumentElement());

            int guaranteeTermSize = guaranteeTerms.getGuaranteeTerm().size();
            boolean verificato = true;
           
            int i = 0;
            while ((i < guaranteeTermSize) && (verificato)) {
                GuaranteeTermType guaranteeTermType = (GuaranteeTermType) guaranteeTerms.getGuaranteeTerm().get(i);
                FunctionType functionType = guaranteeTermType.getServiceLevelObjective().getCustomServiceLevel().getFunction();
                verificato = verificaGTs(functionType, null, 0);
                i++;
            }

            return verificato;
        } catch (JAXBException e) {
            logger.error("Errore nella crezione del JAXBContext " + e.getMessage());
            return false;
        }
    }

    private static boolean verificaGTs(FunctionType functionType, Window finestra, int numeroFinestre) throws DAOException {
        Window nuovaFinestra;

        try {

            if (isAggregateFunction(functionType)) {
                AggregateFunction genericAggregateFunction = AggregateFunction.createAggregateFunction(functionType);
                //GenericAggregateFunction genericAggregateFunction = new GenericAggregateFunction(functionType);
                nuovaFinestra = genericAggregateFunction.getWindow();
                verificaFinestra(nuovaFinestra);
                if (numeroFinestre >= 2) {
                    throw new DAOException(DAOException.ERRORE_FINESTRE_OSSERVAZIONE_ANNIDATE);
                }
                if (genericAggregateFunction.getBasicMetric() != null) {
                    if (finestra != null) {
                        return controllaOrdineFinestre(finestra, nuovaFinestra);
                    }
                    return true;
                }
                return verificaGTs(genericAggregateFunction.getFunction(), nuovaFinestra, numeroFinestre + 1);
            }

            if (functionType instanceof Hits) {
                HitsFunction genericHitsFunction = new HitsFunction((Hits)functionType);
                nuovaFinestra = genericHitsFunction.getWin();
                verificaFinestra(nuovaFinestra);
                if (numeroFinestre >= 2) {
                    throw new DAOException(DAOException.ERRORE_FINESTRE_OSSERVAZIONE_ANNIDATE);
                }
                
                if (finestra != null) {
                    return controllaOrdineFinestre(finestra, nuovaFinestra);
                }
                return true;
            }

            if (functionType instanceof Round) {
                RoundFunction genericRoundFunction = new RoundFunction((Round)functionType);
                if (genericRoundFunction.getBasicMetric() != null) {
                    if (finestra != null) {
                        return true;
                    }
                    throw new DAOException(DAOException.ERRORE_METRICA_NON_DEFINITA);
                }
                return verificaGTs(genericRoundFunction.getFunction(), finestra, numeroFinestre);
            }
            BinaryFunction genericBinaryFunction = BinaryFunction.createBinaryFunction(functionType);
            List listaOperandi = genericBinaryFunction.getListaOperandi();
            OperandType operando1 = (OperandType) listaOperandi.get(0);
            OperandType operando2 = (OperandType) listaOperandi.get(1);
            if ((finestra == null) && (((operando1.getBasicMetric() != null) || (operando2.getBasicMetric() != null)))) {
                throw new DAOException(DAOException.ERRORE_METRICA_NON_DEFINITA);
            }
            if ((operando1.getFunction() != null) && (operando2.getFunction() != null)) {
                return ((verificaGTs(operando1.getFunction(), finestra, numeroFinestre)) && (verificaGTs(operando2.getFunction(), finestra, numeroFinestre)));
            }
            if (operando1.getFunction() != null) {
                return verificaGTs(operando1.getFunction(), finestra, numeroFinestre);
            }
            if (operando2.getFunction() != null) {
                return verificaGTs(operando2.getFunction(), finestra, numeroFinestre);
            }
        } catch (INF2Exception ex) {
            throw new DAOException(ex);
        }
        return true;
    }

    private static boolean controllaOrdineFinestre(Window finestraPrincipale, Window finestra) throws DAOException {
        if ((finestraPrincipale.getInterval() instanceof IntervalTimeType) && (finestra.getInterval() instanceof IntervalTimeType)) {
            return (intervalloNumericoPeriodi(finestraPrincipale.getInterval()) >= intervalloNumericoPeriodi(finestra.getInterval()));
        }
        if ((finestraPrincipale.getTimes() != null) && (finestra.getTimes() != null)) {
            return (!(finestraPrincipale.getTimes().longValue() > finestra.getTimes().longValue()));
        }
        return true;
    }

    private static int intervalloNumericoPeriodi(IntervalTimeType inter) {
        if (inter.value().equalsIgnoreCase("YEAR")) {
            return 9;
        }
        if (inter.value().equalsIgnoreCase("SIXMONTHS")) {
            return 8;
        }
        if (inter.value().equalsIgnoreCase("THREEMONTHS")) {
            return 7;
        }
        if (inter.value().equalsIgnoreCase("MONTH")) {
            return 6;
        }
        if (inter.value().equalsIgnoreCase("TWOWEEKS")) {
            return 5;
        }
        if (inter.value().equalsIgnoreCase("WEEK")) {
            return 4;
        }
        if (inter.value().equalsIgnoreCase("DAY")) {
            return 3;
        }
        if (inter.value().equalsIgnoreCase("SIXHOURS")) {
            return 2;
        }
        if (inter.value().equalsIgnoreCase("HOUR")) {
            return 1;
        }
        return 0;
    }

    private static boolean isAggregateFunction(FunctionType function) {
        return ((function instanceof Sum) || (function instanceof Max) || (function instanceof Min) || (function instanceof Mean));
    }

    private static void verificaFinestra(Window finestra) throws DAOException {
        if ((finestra.getInterval() == null) && (finestra.getTimes() == null)) {
            throw new DAOException(DAOException.ERRORE_VALORI_NON_VALIDI_FINESTRA_OSSERVAZIONE);
        }
    }

    private static class ErrorHandlerWSAG implements ErrorHandler {

        private Log logger = LogFactory.getLog(ErrorHandlerWSAG.class);

        public void error(SAXParseException e) {
            logger.error("Errore nella validazione del WSAG. " + e);
        }

        public void fatalError(SAXParseException e) {
            logger.error("Errore nella validazione del WSAG. " + e);
        }

        public void warning(SAXParseException e) {
            logger.error("Errore nella validazione del WSAG. " + e);
        }
    }    
    
}

