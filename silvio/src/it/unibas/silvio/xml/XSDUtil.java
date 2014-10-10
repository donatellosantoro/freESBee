package it.unibas.silvio.xml;

import it.unibas.silvio.modello.Dati;
import it.unibas.silvio.modello.DatiCostanti;
import it.unibas.silvio.modello.DatiInterattivi;
import it.unibas.silvio.modello.DatiSQL;
import it.unibas.silvio.modello.DatoPrimitivo;
import it.unibas.silvio.modello.Query;
import it.unibas.silvio.persistenza.DAOException;
import it.unibas.silvio.sql.ResultSetParser;
import it.unibas.silvio.sql.ResultSetUtil;
import it.unibas.silvio.sql.SelectParser;
import java.sql.ResultSet;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;

public class XSDUtil {

    private static Log logger = LogFactory.getLog(XSDUtil.class);
    public static final Namespace XSD_NAMESPACE = Namespace.getNamespace("xs", "http://www.w3.org/2001/XMLSchema");

    public static Document datiToXSD(Dati dati) throws XmlException {
        Element rootElement = new Element("schema", XSD_NAMESPACE);
        Document xsdDocument = new Document(rootElement);
        Element elementDati = datiCompletiToXSD(dati);
        rootElement.addContent(elementDati);
        return xsdDocument;
    }

    public static Document datiAndRichiestaToXSD(Dati dati, Document xsdRichiesta, String nameElement, String nomeSchemaRichiesta) throws XmlException {
        Element rootElement = new Element("schema", XSD_NAMESPACE);
        Document xsdDocument = new Document(rootElement);

        Element elementRisposta = new Element("element", XSD_NAMESPACE);
        elementRisposta.setAttribute("name", "datiRisposta");
        Element elementComplexType = new Element("complexType", XSD_NAMESPACE);
        Element elementSequence = new Element("sequence", XSD_NAMESPACE);
        elementRisposta.addContent(elementComplexType);
        elementComplexType.addContent(elementSequence);
        rootElement.addContent(elementRisposta);

        Element elementDati = datiCompletiToXSD(dati);
        elementSequence.addContent(elementDati);
        if (xsdRichiesta != null) {
            String targetNamespace = XSDParser.getTargetNamespace(xsdRichiesta);
            logger.info("Lo schema da importare ha il target namespace: " + targetNamespace);

            if (targetNamespace != null) {
                //FACCIAMO L'IMPORT DELLO SCHEMA DELLA RICHIESTA E AGGIUNGIAMO I NS NECESSARI
                Element elementImport = new Element("import", XSD_NAMESPACE);
                elementImport.setAttribute("namespace", targetNamespace);
                elementImport.setAttribute("schemaLocation", nomeSchemaRichiesta);
                rootElement.addContent(0,elementImport);
                nameElement = "tns:" + XmlUtil.eliminaPrefissoNS(nameElement);
                rootElement.addNamespaceDeclaration(Namespace.getNamespace("tns", targetNamespace));
            } else {
                //SE NON HA IL TARGET NAMESPACE INCLUDIAMO TUTTO LO SCHEMA
//              XSDParser.eliminaNamespace(xsdRichiesta);
                Element elementRichiesta = datiRichiestaToXSD(xsdRichiesta, rootElement);
                elementSequence.addContent(elementRichiesta);
            }
            //aggiungo il riferimento all'elemento della richiesta
            Element elementRefRichiesta = new Element("element", XSD_NAMESPACE);
            elementRefRichiesta.setAttribute("ref", nameElement);
            //<xs:element ref="richiesta_RichiestaRispostaAsincrona_AggiornamentoMobilita"/>
            elementSequence.addContent(elementRefRichiesta);
        }
        return xsdDocument;
    }

    private static Element datiRichiestaToXSD(Document xsdRichiesta, Element rootXSD) {
        Element elementRichiesta = new Element("element", XSD_NAMESPACE);
        elementRichiesta.setAttribute("name", "datiRichiesta");
        Element elementComplexType = new Element("complexType", XSD_NAMESPACE);
        Element elementSequence = new Element("sequence", XSD_NAMESPACE);
        elementRichiesta.addContent(elementComplexType);
        elementComplexType.addContent(elementSequence);
        Element rootElement = xsdRichiesta.getRootElement();
        if (rootElement != null) {
            List<Element> elementiSchema = rootElement.getChildren();
            for (Element element : elementiSchema) {
                logger.info("Aggiungo l'elemento" + element);
//                if (element.getName().equalsIgnoreCase("element")) {
//                    elementSequence.addContent((Element) element.clone());
//                } else {
                rootXSD.addContent((Element) element.clone());
//                }
            }
        }
        return elementRichiesta;
    }

    private static Element datiCompletiToXSD(Dati dati) throws XmlException {
        Element elementDati = new Element("element", XSD_NAMESPACE);
        elementDati.setAttribute("name", "datiCompleti");
        Element elementComplexType = new Element("complexType", XSD_NAMESPACE);
        Element elementSequence = new Element("sequence", XSD_NAMESPACE);
        elementDati.addContent(elementComplexType);
        elementComplexType.addContent(elementSequence);
        // ANALIZZA I DATI PROVENIENTI DAL DB
        Element elementSQL = dbToXSD(dati.getDatiSQL());
        if (elementSQL != null) {
            elementSequence.addContent(elementSQL);
        }
        // ANALIZZA I DATI DA CHIEDERE ALL'UTENTE
        Element elementInterattivi = interattiviToXSD(dati.getDatiInterattivi());
        if (elementInterattivi != null) {
            elementSequence.addContent(elementInterattivi);
        }
        // ANALIZZA I DATI COSTANTI
        Element elementCostanti = costantiToXSD(dati.getDatiCostanti());
        if (elementCostanti != null) {
            elementSequence.addContent(elementCostanti);
        }
        return elementDati;
    }

    public static Element dbToXSD(DatiSQL dati) throws XmlException {
        if (dati == null || dati.getSelect() == null || dati.getSelect().getQuery() == null) {
            return null;
        }
        List<DatoPrimitivo> datiSQL = leggiDatiResultSet(dati.getSelect());
        if (datiSQL == null) {
            datiSQL = SelectParser.parse(dati.getSelect().getQuery());
        }
        if (datiSQL == null) {
            throw new XmlException("Non è stato possibile generare l'XSD a partire dal ResultSet. Controllare i dati di connessione al db");
        }
        Element elementDati = new Element("element", XSD_NAMESPACE);
        elementDati.setAttribute("name", "datiDB");
        elementDati.setAttribute("maxOccurs", "unbounded");
        Element elementComplexType = new Element("complexType", XSD_NAMESPACE);
        elementDati.addContent(elementComplexType);
        elementComplexType.addContent(datiPrimitiviToXSD(datiSQL));
        return elementDati;
    }

    private static List<DatoPrimitivo> leggiDatiResultSet(Query query) {
        ResultSetUtil rsUtil = new ResultSetUtil();
        List<DatoPrimitivo> listaDati = null;
        try {
            ResultSet rs = rsUtil.queryToRs(query, SelectParser.escludiWhere(query.getQuery()));
            listaDati = ResultSetParser.parseFirst(rs);
        } catch (DAOException ex) {
            logger.error("Impossibile eseguire la query sul db per estrarne il resultSet. " + ex);
        } finally {
            rsUtil.chiudiConnessioni();
        }
        return listaDati;
    }

    public static Element interattiviToXSD(DatiInterattivi dati) throws XmlException {
        if (dati == null || dati.getDatiInterattivi().isEmpty()) {
            return null;
        }
        Element elementDati = new Element("element", XSD_NAMESPACE);
        elementDati.setAttribute("name", "datiInterattivi");
        Element elementComplexType = new Element("complexType", XSD_NAMESPACE);
        elementDati.addContent(elementComplexType);
        elementComplexType.addContent(datiPrimitiviToXSD(dati.getDatiInterattivi()));
        return elementDati;
    }

    public static Element costantiToXSD(DatiCostanti dati) throws XmlException {
        if (dati == null || dati.getDatiCostanti().isEmpty()) {
            return null;
        }
        Element elementDati = new Element("element", XSD_NAMESPACE);
        elementDati.setAttribute("name", "datiCostanti");
        Element elementComplexType = new Element("complexType", XSD_NAMESPACE);
        elementDati.addContent(elementComplexType);
        elementComplexType.addContent(datiPrimitiviToXSD(dati.getDatiCostanti()));
        return elementDati;
    }

    private static Element datiPrimitiviToXSD(List<DatoPrimitivo> dati) {
        Element elementSequence = new Element("sequence", XSD_NAMESPACE);
        for (DatoPrimitivo dato : dati) {
            Element elementDato = new Element("element", XSD_NAMESPACE);
            elementDato.setAttribute("name", dato.getNome());
            if (dato.getTipo() == null) {
                elementDato.setAttribute("type", "xs:string");
            } else {
                elementDato.setAttribute("type", dato.getTipo());
            }
            elementSequence.addContent(elementDato);
        }
        return elementSequence;
    }
}
