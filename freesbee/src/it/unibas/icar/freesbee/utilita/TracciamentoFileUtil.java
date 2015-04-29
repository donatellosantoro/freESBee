package it.unibas.icar.freesbee.utilita;

import it.unibas.icar.freesbee.exception.XmlException;
import it.unibas.icar.freesbee.modello.ConfigurazioneStatico;
import it.unibas.icar.freesbee.modello.Eccezione;
import it.unibas.icar.freesbee.modello.Messaggio;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.slf4j.LoggerFactory;

public class TracciamentoFileUtil {

    public static final String XML_SCHEMA = "http://www.w3.org/2001/XMLSchema-instance";
    public static final String SCHEMA_LOCATION = "http://www.cnipa.it/schemas/2003/eGovIT/Tracciamento1_0/";
    public static final String SOAP_ENV = "http://schemas.xmlsoap.org/soap/envelope/";
    public static final String EGOV_IT = "http://www.cnipa.it/schemas/2003/eGovIT/Busta1_0/";
    public static final String XSI = "http://www.w3.org/2001/XMLSchema-instance";
    public static final String NS_EGOV = "http://www.cnipa.it/eGov_it/portadominio";
//    private static Log logger = LogFactory.getLog("TracciaFileUtil");
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(TracciamentoFileUtil.class.getName());

    public static void creaFile(Messaggio messaggioDaTracciare, String path) throws IOException {
        String nomeFile = "";
        try {
            Document documento = new Document(creaStrutturaXML(messaggioDaTracciare));
            if (messaggioDaTracciare.getIdMessaggio() != null) {
                String nome = messaggioDaTracciare.getIdMessaggio().replace(":", "_") + "_" + messaggioDaTracciare.getTipo();
                nomeFile = File.separator + "Tracciamento-" + nome + ".xml";
            } else {
                nomeFile = File.separator + "Tracciamento.xml";
            }
//            File file = new File(path + nomeFile);
            XMLJDomUtil.salvaXML(path + nomeFile, documento);
        } catch (XmlException ex) {
            logger.error("Si e' verificato un errore durante la creazione del file di tracciamento " + nomeFile);
            if (logger.isDebugEnabled()) ex.printStackTrace();
        }

    }

    private static Element creaStrutturaXML(Messaggio messaggioDaTracciare) {
        ConfigurazioneStatico configurazioneStatico = ConfigurazioneStatico.getInstance();
        String pathTracciamentoXsd = configurazioneStatico.getPercorsoFile() + "/tracciamento.xsd";
        Element traccia = new Element("traccia", "tra", SCHEMA_LOCATION);
        List<Attribute> attributes = new ArrayList<Attribute>();
        Attribute attributoSchemaLocation = new Attribute("schemaLocation", SCHEMA_LOCATION + " " + pathTracciamentoXsd, Namespace.getNamespace("xsi", XSI));
        attributes.add(attributoSchemaLocation);
//        Attribute attributoSoapEnv = new Attribute("SOAP_ENV", SOAP_ENV, Namespace.getNamespace("SOAP_ENV", SOAP_ENV));
//        attributes.add(attributoSoapEnv);
//        Attribute attributoEgovIT = new Attribute("eGov_IT", EGOV_IT, Namespace.getNamespace("eGov_IT", EGOV_IT));
//
//        attributes.add(attributoEgovIT);
        traccia.setAttributes(attributes);




        Element gdo = new Element("GDO", "tra", SCHEMA_LOCATION);
        gdo.addContent(messaggioDaTracciare.getOraRegistrazione());

        Element identificativoPorta = new Element("IdentificativoPorta", "tra", SCHEMA_LOCATION);
        identificativoPorta.addContent("http://freesbee.unibas.it");

        Element tipoMessaggio = new Element("TipoMessaggio", "tra", SCHEMA_LOCATION);
        tipoMessaggio.addContent(messaggioDaTracciare.getTipoMessaggio());

        //Element intestazione = new Element("Intestazione", "eGov_IT", NS_EGOV);
        Element intestazione = new Element("Intestazione", "eGov_IT", NS_EGOV);
        Attribute attributoIntestazioneActor = new Attribute("actor", NS_EGOV, Namespace.getNamespace("SOAP_ENV", SOAP_ENV));
        Attribute attributoIntestazioneMustUnderstand = new Attribute("mustUnderstand", "1", Namespace.getNamespace("SOAP_ENV", SOAP_ENV));
        List<Attribute> attributiIntestazione = new ArrayList<Attribute>();
        attributiIntestazione.add(attributoIntestazioneActor);
        attributiIntestazione.add(attributoIntestazioneMustUnderstand);
        intestazione.setAttributes(attributiIntestazione);


        Element intestazioneMessaggio = new Element("IntestazioneMessaggio", "eGov_IT", NS_EGOV);



        Element mittente = new Element("Mittente", "eGov_IT", NS_EGOV);

        Element identificativoParteMittente = new Element("IdentificativoParte", "eGov_IT", NS_EGOV);
        if (messaggioDaTracciare.getTipoFruitore() != null) {
            identificativoParteMittente.setAttribute("tipo", messaggioDaTracciare.getTipoFruitore());
        }
        if (messaggioDaTracciare.getFruitore() != null) {
            identificativoParteMittente.setText(messaggioDaTracciare.getFruitore());
        }

        Element destinatario = new Element("Destinatario", "eGov_IT", NS_EGOV);

        Element identificativoParteDestinatario = new Element("IdentificativoParte", "eGov_IT", NS_EGOV);

        if (messaggioDaTracciare.getTipoErogatore() != null) {
            identificativoParteDestinatario.setAttribute("tipo", messaggioDaTracciare.getTipoErogatore());
        }
        if (messaggioDaTracciare.getFruitore() != null) {
            identificativoParteDestinatario.setText(messaggioDaTracciare.getErogatore());
        }

        Element profiloCollaborazione = new Element("ProfiloCollaborazione", "eGov_IT", NS_EGOV);
        profiloCollaborazione.setText(messaggioDaTracciare.getProfiloCollaborazione());

        Element collaborazione = new Element("Collaborazione", "eGov_IT", NS_EGOV);
        if (messaggioDaTracciare.getCollaborazione() != null) {
            collaborazione.setText(messaggioDaTracciare.getCollaborazione());
        }

        Element servizio = new Element("Servizio", "eGov_IT", NS_EGOV);
        servizio.setAttribute("tipo", messaggioDaTracciare.getTipoServizio());
        servizio.setText(messaggioDaTracciare.getServizio());

        Element azione = new Element("Azione", "eGov_IT", NS_EGOV);
        if (messaggioDaTracciare.getAzione() != null) {
            azione.setText(messaggioDaTracciare.getAzione());
        }

        Element messaggio = new Element("Messaggio", "eGov_IT", NS_EGOV);

        Element identificatore = new Element("Identificatore", "eGov_IT", NS_EGOV);
        identificatore.setText(messaggioDaTracciare.getIdMessaggio());

        Element oraRegistrazione = new Element("OraRegistrazione", "eGov_IT", NS_EGOV);
        if (messaggioDaTracciare.getOraRegistrazione() != null) {
            oraRegistrazione.setAttribute("tempo", messaggioDaTracciare.getTempo());
            oraRegistrazione.setText(messaggioDaTracciare.getOraRegistrazione());
        }

        Element riferimentoMessaggio = new Element("RiferimentoMessaggio", "eGov_IT", NS_EGOV);
        if (messaggioDaTracciare.getRiferimentoMessaggio() != null) {
            riferimentoMessaggio.setText(messaggioDaTracciare.getRiferimentoMessaggio());
        }


        mittente.addContent(identificativoParteMittente);
        destinatario.addContent(identificativoParteDestinatario);
        messaggio.addContent(identificatore);
        messaggio.addContent(oraRegistrazione);
        messaggio.addContent(riferimentoMessaggio);
        intestazioneMessaggio.addContent(mittente);
        intestazioneMessaggio.addContent(destinatario);
        intestazioneMessaggio.addContent(profiloCollaborazione);
        intestazioneMessaggio.addContent(collaborazione);
        intestazioneMessaggio.addContent(servizio);
        intestazioneMessaggio.addContent(azione);
        intestazioneMessaggio.addContent(messaggio);
        intestazione.addContent(intestazioneMessaggio);
        if (messaggioDaTracciare.getListaEccezioni().size() != 0) {
            Element listaEccezioni = creaListaEccezioni(messaggioDaTracciare.getListaEccezioni());
            intestazione.addContent(listaEccezioni);
        }
        traccia.addContent(gdo);
        traccia.addContent(identificativoPorta);
        traccia.addContent(tipoMessaggio);
        traccia.addContent(intestazione);

        return traccia;
    }

    private static Element creaListaEccezioni(List<Eccezione> listaEccezioni) {
        Element eccezioni = new Element("ListaEccezioni");
        for (Eccezione e : listaEccezioni) {
            Element eccezione = new Element("Eccezione");
            Attribute contestoCodifica = new Attribute("contestoCodifica", e.getContestoCodifica());
            Attribute codiceEccezione = new Attribute("codiceEccezione", e.getCodiceEccezione());
            Attribute rilevanza = new Attribute("rilevanza", e.getRilevanza());
            Attribute posizione = new Attribute("posizione", e.getPosizione());
            List<Attribute> listaAttributi = new ArrayList<Attribute>();
            listaAttributi.add(contestoCodifica);
            listaAttributi.add(codiceEccezione);
            listaAttributi.add(rilevanza);
            listaAttributi.add(posizione);
            eccezione.setAttributes(listaAttributi);
            eccezioni.addContent(eccezione);
        }
        return eccezioni;

    }
}
