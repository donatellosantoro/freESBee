package it.unibas.silvio.web.controllo;

import it.unibas.silvio.modello.IstanzaAccordoDiCollaborazione;
import it.unibas.silvio.modello.IstanzaOperation;
import it.unibas.silvio.modello.IstanzaPortType;
import it.unibas.silvio.modello.Utente;
import it.unibas.silvio.persistenza.DAOException;
import it.unibas.silvio.persistenza.IDAOConfigurazione;
import it.unibas.silvio.persistenza.IDAOIstanzaAccordoDiCollaborazione;
import it.unibas.silvio.persistenza.IDAOIstanzaOperation;
import it.unibas.silvio.persistenza.IDAOMessaggio;
import it.unibas.silvio.web.util.SilvioWebUtil;
import it.unibas.silvio.web.vista.VistaEseguiIstanzaTest;
import it.unibas.silvio.xml.XmlJDomUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import junit.framework.Test;
import junit.textui.ResultPrinter;
import junit.textui.TestRunner;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;

public class ControlloEseguiIstanzaTest {

    private Log logger = LogFactory.getLog(this.getClass());
    private VistaEseguiIstanzaTest vista;
    private String messaggio;
    private String errore;
    private IDAOIstanzaAccordoDiCollaborazione daoIstanzaAccordoDiCollaborazione;
    private IDAOIstanzaOperation daoIstanzaOperation;
    private IDAOConfigurazione daoConfigurazione;
    private IDAOMessaggio daoMessaggio;
    private Utente utente;

    private void pulisciVista() {
        this.vista.setIstanzaSelezionata("");
        this.vista.setOperationSelezionata("");
        this.vista.setSecondoPasso(false);
        this.vista.setTerzoPasso(false);

    }

    public String annulla() {
        pulisciVista();
        return "home";
    }

    public String confermaDati() {
        this.vista.setInvia(true);
        this.vista.setTerzoPasso(true);
        this.vista.setConferma(false);
        return null;
    }

    public String eseguiIstanzaTest() {
        List<IstanzaAccordoDiCollaborazione> listaIstanzeAccordiDiCollaborazione;
        this.pulisciVista();
        try {
            listaIstanzeAccordiDiCollaborazione = daoIstanzaAccordoDiCollaborazione.findAllFruitori();
            if (listaIstanzeAccordiDiCollaborazione.size() == 0) {
                logger.info("Nessuna istanza di accordo di collaborazione trovata");
                caricaErrore("istanza_errore_esegui");
            } else {
                List<SelectItem> listaItem = new ArrayList<SelectItem>();
                listaItem.add(new SelectItem(""));
                for (IstanzaAccordoDiCollaborazione istanzaAccordoDiCollaborazione : listaIstanzeAccordiDiCollaborazione) {
                    listaItem.add(new SelectItem(istanzaAccordoDiCollaborazione.getNome()));
                }
                this.vista.setListaItem(listaItem);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Errore nella lettura dell'elenco delle istanze degli accordi di collaborazione " + ex);
            caricaErrore("errore_generico_db");
        }
        this.vista.setSecondoPasso(false);
        return "eseguiIstanzaTest";
    }

    public String istanzaCambiata() {
        IstanzaAccordoDiCollaborazione istanza;
        try {
            String nomeIstanza = this.vista.getIstanzaSelezionata();
            istanza = daoIstanzaAccordoDiCollaborazione.findByNome(nomeIstanza);
            if (istanza == null) {
                this.vista.getListaItemOperation().clear();
                return null;
            }
            List<SelectItem> listaItemOperation = new ArrayList<SelectItem>();
            List<IstanzaPortType> listaIstanzePortType = istanza.getListaIstanzePortType();

            this.getVista().getMappaIdentificatore().clear();

            for (IstanzaPortType istanzaPortType : listaIstanzePortType) {
                if (istanzaPortType.isFruizione()) {
                    List<IstanzaOperation> listaIstanzaOperation = istanzaPortType.getListaIstanzaOperation();
                    for (IstanzaOperation istanzaOperation : listaIstanzaOperation) {
                        String stringaIdentificatore = istanzaOperation.getIstanzaPortType().getNome() + "$" + istanzaOperation.getNome();
                        this.getVista().getMappaIdentificatore().put(stringaIdentificatore, istanzaOperation);
                    }
                }
            }
            Map<String, IstanzaOperation> mappaOperation = this.vista.getMappaIdentificatore();
            Set<String> chiavi = mappaOperation.keySet();
            for (String idOperation : chiavi) {
                IstanzaOperation istanzaOperation = mappaOperation.get(idOperation);
                listaItemOperation.add(new SelectItem(idOperation, istanzaOperation.getIstanzaPortType().getNome() + " - " + istanzaOperation.getNome()));
            }
            this.vista.setListaItemOperation(listaItemOperation);
            this.vista.setIstanzaAccordoSelezionata(istanza);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Errore nella lettura dell'elenco delle istanze degli accordi di collaborazione " + ex);
            caricaErrore("errore_generico_db");
        }
        logger.info("Istanza selezionata " + vista.getIstanzaSelezionata());
        return null;
    }

    public String configura() {
        try {
            IstanzaOperation operation = this.vista.getMappaIdentificatore().get(this.vista.getOperationSelezionata());
            getDaoIstanzaOperation().makePersistent(operation);
            this.vista.setIstanzaOperationSelezionata(operation);

            this.vista.setUrlInvio(operation.getIstanzaPortType().getURLInvio());
            this.vista.setUrlAscolto(operation.getIstanzaPortType().getURLAscolto());

            logger.info("operation selezionata " + vista.getOperationSelezionata());
            this.vista.setSecondoPasso(true);
            return "eseguiIstanzaTestOperation";
        } catch (DAOException ex) {
            caricaErrore("errore_generico_db");
            logger.error("Problemi durante il refresh dell'istanza operazione selezionata" + ex);
        }
        logger.info("operation selezionata " + vista.getOperationSelezionata());
        return "eseguiIstanzaTestOperation";
    }

    public String avviaTest() {
        try {
            String stringaUrl = creaURL();
            String bodyMessage = creaMessaggioXML();
            logger.info("Body del message: " + bodyMessage);
            
            IstanzaOperation istanzaOperation = this.vista.getIstanzaOperationSelezionata();
            int nUtenti = Integer.parseInt(this.vista.getNumeroUtenti());
            int nRichieste = Integer.parseInt(this.vista.getNumeroRichieste());
            int secAttesa = Integer.parseInt(this.vista.getSecondiAttesa());

            Test test = TestSilvioPerfSincrono.suite(bodyMessage,istanzaOperation, stringaUrl,nUtenti, nRichieste, secAttesa);
            String result = runTest(test);
            logger.info("Risultato del test " + result);
            this.vista.setTestResult(result);
            this.vista.setVisualizzaModalPanel(true);
            this.utente.setFileXSD(result);
            this.utente.setNomeFileXSD("result.txt");
            return null;
        } catch (DAOException ex) {
            logger.error("Impossibile caricare la configurazione" + ex);
        } catch (IOException ex) {
            logger.error("Errore durante la conversione del messaggio da inviare in stringa " + ex);
            caricaErrore("istanza_errore_document");
        } catch (Exception e) {
            logger.error("Errore durante l'invio del messaggio o la ricezione della ripsota " + e);
            caricaErrore("istanza_errore_comunicazione");
        }
        return null;
    }
    
    public String visualizzaResult(){
        this.vista.setVisualizzaModalPanel(true);
        return null;
    }

    private String runTest(Test test) {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(result);
        TestRunner runner = new junit.textui.TestRunner();
        runner.setPrinter(new ResultPrinter(ps));
        runner.doRun(test);
        return result.toString();
    }

    private String creaURL() throws DAOException {
        String stringaUrl = "http://localhost:";
        stringaUrl += daoConfigurazione.caricaConfigurazione().getPorta();
        stringaUrl += this.vista.getUrlAscolto();
        return stringaUrl;
    }

    private String creaMessaggioXML() throws IOException, DAOException {
        Document document = null;

        Element rootElement = new Element("istanzaOperation");
        rootElement.addNamespaceDeclaration(Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance"));
        rootElement.setAttribute(new Attribute("noNamespaceSchemaLocation", "eseguiIstanza.xsd", Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance")));
        rootElement.setAttribute(new Attribute("test", "true"));
        rootElement.setAttribute(new Attribute("correlazione", this.vista.getCorrelazione()));

        Element elementNomeOperazione = new Element("nomeOperazione");
        elementNomeOperazione.addContent(this.vista.getIstanzaOperationSelezionata().getNome());
        rootElement.addContent(elementNomeOperazione);

        Element elementIndirizzoRichiesta = new Element("indirizzoRichiesta");
        elementIndirizzoRichiesta.addContent(vista.getUrlInvio());
        rootElement.addContent(elementIndirizzoRichiesta);

        Element elementDatiParziali = new Element("datiParziali");
        rootElement.addContent(elementDatiParziali);

        document = new Document(rootElement);

        String bodyMessage = XmlJDomUtil.convertiDocumentToString(document);
        return bodyMessage;
    }

    public void defaultUrlInvio() {
        try {
            IstanzaOperation operation = this.vista.getMappaIdentificatore().get(this.vista.getOperationSelezionata());
            getDaoIstanzaOperation().makePersistent(operation);
            this.vista.setUrlInvio(operation.getIstanzaPortType().getURLInvio());
        } catch (DAOException ex) {
            caricaErrore("errore_generico_db");
            logger.error("Problemi durante il refresh dell'istanza operazione selezionata" + ex);
        }
    }

    private void caricaErrore(String chiave) {
        FacesContext context = FacesContext.getCurrentInstance();
        String text = SilvioWebUtil.getMessageResourceString(context.getApplication().getMessageBundle(), chiave, null, context.getViewRoot().getLocale());
        this.setErrore(text);
    }

    private void caricaMessaggio(String chiave) {
        FacesContext context = FacesContext.getCurrentInstance();
        String text = SilvioWebUtil.getMessageResourceString(context.getApplication().getMessageBundle(), chiave, null, context.getViewRoot().getLocale());
        this.setMessaggio(text);
    }

    public VistaEseguiIstanzaTest getVista() {
        return vista;
    }

    public void setVista(VistaEseguiIstanzaTest vista) {
        this.vista = vista;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public IDAOIstanzaAccordoDiCollaborazione getDaoIstanzaAccordoDiCollaborazione() {
        return daoIstanzaAccordoDiCollaborazione;
    }

    public void setDaoIstanzaAccordoDiCollaborazione(IDAOIstanzaAccordoDiCollaborazione daoIstanzaAccordoDiCollaborazione) {
        this.daoIstanzaAccordoDiCollaborazione = daoIstanzaAccordoDiCollaborazione;
    }

    public IDAOIstanzaOperation getDaoIstanzaOperation() {
        return daoIstanzaOperation;
    }

    public void setDaoIstanzaOperation(IDAOIstanzaOperation daoIstanzaOperation) {
        this.daoIstanzaOperation = daoIstanzaOperation;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    public String getErrore() {
        return errore;
    }

    public void setErrore(String errore) {
        this.errore = errore;
    }

    public IDAOConfigurazione getDaoConfigurazione() {
        return daoConfigurazione;
    }

    public void setDaoConfigurazione(IDAOConfigurazione daoConfigurazione) {
        this.daoConfigurazione = daoConfigurazione;
    }

    public IDAOMessaggio getDaoMessaggio() {
        return daoMessaggio;
    }

    public void setDaoMessaggio(IDAOMessaggio daoMessaggio) {
        this.daoMessaggio = daoMessaggio;
    }
    
}
