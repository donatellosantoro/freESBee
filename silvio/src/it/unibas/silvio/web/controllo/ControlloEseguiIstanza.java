package it.unibas.silvio.web.controllo;

import it.unibas.silvio.modello.ConfigurazioneStatico;
import it.unibas.silvio.modello.Dati;
import it.unibas.silvio.modello.DatiInterattivi;
import it.unibas.silvio.modello.DatiSQL;
import it.unibas.silvio.modello.DatoPrimitivo;
import it.unibas.silvio.modello.IstanzaAccordoDiCollaborazione;
import it.unibas.silvio.modello.IstanzaOperation;
import it.unibas.silvio.modello.IstanzaPortType;
import it.unibas.silvio.modello.Messaggio;
import it.unibas.silvio.modello.Query;
import it.unibas.silvio.modello.RispostaEseguiIstanza;
import it.unibas.silvio.modello.Utente;
import it.unibas.silvio.persistenza.DAOException;
import it.unibas.silvio.persistenza.IDAOConfigurazione;
import it.unibas.silvio.persistenza.IDAOIstanzaAccordoDiCollaborazione;
import it.unibas.silvio.persistenza.IDAOIstanzaOperation;
import it.unibas.silvio.persistenza.IDAOMessaggio;
import it.unibas.silvio.sql.SelectParser;
import it.unibas.silvio.web.util.SilvioWebUtil;
import it.unibas.silvio.web.vista.VistaEseguiIstanza;
import it.unibas.silvio.xml.XmlException;
import it.unibas.silvio.xml.XmlJDomUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.xml.sax.SAXException;

public class ControlloEseguiIstanza {

    private Log logger = LogFactory.getLog(this.getClass());
    private VistaEseguiIstanza vista;
    private String messaggio;
    private String errore;
    private String erroreIndirizzo;
    private IDAOIstanzaAccordoDiCollaborazione daoIstanzaAccordoDiCollaborazione;
    private IDAOIstanzaOperation daoIstanzaOperation;
    private IDAOConfigurazione daoConfigurazione;
    private IDAOMessaggio daoMessaggio;
    private Utente utente;

    private void pulisciVista() {
        this.vista.setIstanzaSelezionata("");
        this.vista.setOperationSelezionata("");
        this.vista.setUrlAscolto("");
        this.vista.setUrlInvio("");
        this.vista.setVisualizzaRispostaSincrona(false);
        this.vista.setVisualizzaPannelloRispostaSincrona(false);
        this.vista.setSecondoPasso(false);
        this.vista.setTerzoPasso(false);
        this.vista.setVisualizzaDatiDB(false);
        this.vista.setVisualizzaDatiInterattivi(false);
        this.vista.setAutenticazioneFederata(false);
        this.vista.setConferma(true);
        this.vista.setInvia(false);

        this.vista.setCorrelazione("");
        this.vista.setIstanzaAccordoSelezionata(new IstanzaAccordoDiCollaborazione());
        this.vista.setIstanzaOperationSelezionata(new IstanzaOperation());
        this.vista.setListaItem(new ArrayList<SelectItem>());
        this.vista.setListaItemOperation(new ArrayList<SelectItem>());
        this.vista.setListaParametriDB(new ArrayList<DatoPrimitivo>());
        this.vista.setListaParametriInterattivi(new ArrayList<DatoPrimitivo>());
        this.vista.setMappaIdentificatore(new HashMap<String, IstanzaOperation>());
        this.vista.setRispostaEseguiIstanza(null);
        this.vista.setTabPanelParametriDB(null);
        this.vista.setTabPanelParametriInterattivi(null);
        this.vista.setTabellaParametriDB(null);
        this.vista.setTabellaParametriInterattivi(null);
        this.vista.setVisualizzaVerificaRispostaAincrona(false);
        this.vista.setVisualizzaPulsanteLogin(false);
        this.vista.setVisualizzaModalPanelURLInvio(false);
        this.vista.setIndirizzoInvio(null);
        this.vista.setNuovaRisorsa(null);
    }

    public String cambiaApp() {
        try {
            long idIstanzaOperation = ((Long) this.vista.getIstanzaAppSelezionata().getValue()).longValue();
            return avviaApp(idIstanzaOperation);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Impossibile leggere l'istanza port type selezionata. " + ex);
            return null;
        }
    }

    public String avviaApp(long idIstanza) {
        pulisciVista();
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        try {
            IstanzaOperation operation = daoIstanzaOperation.findById(new Long(idIstanza), false);
            this.vista.setIstanzaOperationSelezionata(operation);
            this.vista.setUrlInvio(operation.getIstanzaPortType().getURLInvio());
            this.vista.setUrlAscolto(operation.getIstanzaPortType().getURLAscolto());

            Dati dati = operation.getDatiRichiesta();
            DatiSQL datiSQL = dati.getDatiSQL();
            DatiInterattivi datiInterattivi = dati.getDatiInterattivi();
            if (datiSQL != null) {
                vista.setVisualizzaDatiDB(true);
                Query query = datiSQL.getSelect();
                String stringQuery = query.getQuery();
                List<DatoPrimitivo> listaDatiprimitivi = SelectParser.estraiParametri(stringQuery);
                vista.setListaParametriDB(listaDatiprimitivi);
                logger.info("Configurazione dati provenienti dal db");
            }

            if (datiInterattivi != null) {
                vista.setVisualizzaDatiInterattivi(true);
                List<DatoPrimitivo> listaDatiprimitivi = datiInterattivi.getDatiInterattivi();
                vista.setListaParametriInterattivi(listaDatiprimitivi);
                logger.info("Configurazione dati interattivi ");
            }

            List<IstanzaOperation> elencoIstanze = new ArrayList<IstanzaOperation>();
            List<IstanzaOperation> operations = daoIstanzaOperation.findAll();
            for (IstanzaOperation istanzaOperation : operations) {
                if (istanzaOperation.getIstanzaPortType().isFruizione() && istanzaOperation.isAvvioRapido()) {
                    elencoIstanze.add(istanzaOperation);
                }
            }
            this.vista.setElencoIstanze(elencoIstanze);

            String skinDir = operation.getSkinDir();
            if (skinDir == null) {
                skinDir = session.getServletContext().getRealPath("/app/");
            }
            File skinFolder = new File(skinDir);
            if (!skinFolder.exists() || !skinFolder.isDirectory()) {
                skinDir = session.getServletContext().getRealPath("/app/");
            }

            String appPath = "file:///" + session.getServletContext().getRealPath("/app/");
            session.setAttribute("appPath", appPath);
            session.setAttribute("skin", session.getServletContext().getContextPath() + "/skin");
            session.setAttribute("skinDir", "file:///" + skinDir);

            logger.info("Avvio l'operazione: " + operation.getId());
            logger.info("\tappPath: " + appPath);
            logger.info("\tskinDir: " + skinDir);

            Hibernate.initialize(this.vista.getListaParametriInterattivi());
            if (operation.isAutenticazioneFederata()) {
                String indirizzoSP = ConfigurazioneStatico.getInstance().getFreesbeeSPURL();
                this.vista.setUrlInvio(indirizzoSP + "/forwardToPD.post");
                this.vista.setIndirizzoInvio(operation.getIstanzaPortType().getURLInvio());
                return "acquisisciPortafoglioApp";
            } else {
                return "app";
            }
        } catch (DAOException ex) {
            caricaErrore("errore_generico_db");
            logger.error("Problemi durante il refresh dell'istanza operazione selezionata" + ex);
        }
        return null;
    }

    public void operationSelezionataCambiata() {
        IstanzaOperation operation = this.vista.getMappaIdentificatore().get(this.vista.getOperationSelezionata());
        this.vista.setIndirizzoInvio(operation.getIstanzaPortType().getURLInvio());
//        return null;
    }

    public String modificaIndirizzoInvio() {
        this.vista.setVisualizzaModalPanelURLInvio(true);
        return null;
    }

    public String salvaModalPanelURLInvio() {
        this.vista.setVisualizzaModalPanelURLInvio(false);
        return null;
    }

    public String chiudiModalPanelURLInvio() {
        this.vista.setVisualizzaModalPanelURLInvio(false);
        return null;
    }

    public String login() {
        this.vista.setVisualizzaPulsanteLogin(false);
        this.vista.setVisualizzaRispostaSincrona(false);
        return "acquisisciPortafoglioInvalida";
    }

    public String loginApp() {
        this.vista.setVisualizzaPulsanteLogin(false);
        this.vista.setVisualizzaRispostaSincrona(false);
        return "acquisisciPortafoglioInvalidaApp";
    }

    public String annulla() {
        pulisciVista();
        return "home";
    }

    public String confermaDati() {
        this.vista.setInvia(true);
        this.vista.setConferma(false);
        this.vista.setTerzoPasso(true);
        return null;
    }

    public String eseguiIstanza() {
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
        return "eseguiIstanza";
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

    public String inserisciParametri() {
        if (this.vista.isAutenticazioneFederata() && (this.vista.getIndirizzoInvio() == null || this.vista.getIndirizzoInvio().trim().isEmpty())) {
            this.erroreIndirizzo = "Non e' stato selezionato l'indirizzo di invio. Utilizzare il pulsante 'URL Invio' per inserire l'indirizzo";
            return null;
        }

        ConfigurazioneStatico configurazioneStatico = ConfigurazioneStatico.getInstance();
        try {
            IstanzaOperation operation = this.vista.getMappaIdentificatore().get(this.vista.getOperationSelezionata());

            getDaoIstanzaOperation().makePersistent(operation);
            this.vista.setIstanzaOperationSelezionata(operation);

            if (this.vista.isAutenticazioneFederata()) {
                String indirizzoSP = ConfigurazioneStatico.getInstance().getFreesbeeSPURL();
                this.vista.setUrlInvio(indirizzoSP + "/forwardToPD.post");
            } else {
                this.vista.setUrlInvio(operation.getIstanzaPortType().getURLInvio());
            }

            this.vista.setUrlAscolto(operation.getIstanzaPortType().getURLAscolto());
            Dati dati = operation.getDatiRichiesta();
            DatiSQL datiSQL = dati.getDatiSQL();
            DatiInterattivi datiInterattivi = dati.getDatiInterattivi();
            if (datiSQL != null) {
                vista.setVisualizzaDatiDB(true);
                Query query = datiSQL.getSelect();
                String stringQuery = query.getQuery();
                List<DatoPrimitivo> listaDatiprimitivi = SelectParser.estraiParametri(stringQuery);
                vista.setListaParametriDB(listaDatiprimitivi);
                logger.info("Configurazione dati provenienti dal db");
            }

            if (datiInterattivi != null) {
                vista.setVisualizzaDatiInterattivi(true);
                List<DatoPrimitivo> listaDatiprimitivi = datiInterattivi.getDatiInterattivi();
                vista.setListaParametriInterattivi(listaDatiprimitivi);
                logger.info("Configurazione dati interattivi ");
            }

        } catch (DAOException ex) {
            caricaErrore("errore_generico_db");
            logger.error("Problemi durante il refresh dell'istanza operazione selezionata" + ex);
        }
        this.vista.setSecondoPasso(true);
        logger.info("operation selezionata " + vista.getOperationSelezionata());

        Hibernate.initialize(this.vista.getListaParametriInterattivi());

        if (this.vista.isAutenticazioneFederata()) {
            return "acquisisciPortafoglio";
        } else {
            return "eseguiIstanzaOperation";
        }
    }

    public void inviaMessaggioXML() {
        try {
            String body = creaMessaggioXML();
            String stringaUrl = "http://localhost:";
            stringaUrl += daoConfigurazione.caricaConfigurazione().getPorta();
            stringaUrl += this.vista.getUrlAscolto();
            URL url = new URL(stringaUrl);
            logger.info("Invio del messaggio in corso all'url: " + url);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", body.getBytes().length + "");
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(body);
            wr.flush();

            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            String stringaRisposta = "";
            while ((line = rd.readLine()) != null) {
                stringaRisposta += line;
            }
            wr.close();
            rd.close();
            this.registraRisposta(stringaRisposta);
        } catch (DAOException ex) {
            logger.error("Impossibile caricare la configurazione" + ex);
        } catch (IOException ex) {
            logger.error("Errore durante la conversione del messaggio da inviare in stringa " + ex);
            caricaErrore("istanza_errore_document");
        } catch (Exception e) {
            logger.error("Errore durante l'invio del messaggio o la ricezione della risposta " + e);
            caricaErrore("istanza_errore_conversazione");
        }
    }

    private String creaMessaggioXML() throws IOException {
        Document document = null;

        Element rootElement = new Element("istanzaOperation");
        rootElement.addNamespaceDeclaration(Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance"));
        rootElement.setAttribute(new Attribute("noNamespaceSchemaLocation", "eseguiIstanza.xsd", Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance")));
        rootElement.setAttribute(new Attribute("test", "false"));
        rootElement.setAttribute(new Attribute("correlazione", this.vista.getCorrelazione()));

        Element elementNomeOperazione = new Element("nomeOperazione");
        elementNomeOperazione.addContent(this.vista.getIstanzaOperationSelezionata().getNome());
        rootElement.addContent(elementNomeOperazione);

        Element elementIndirizzoRichiesta = new Element("indirizzoRichiesta");
        elementIndirizzoRichiesta.addContent(vista.getUrlInvio());
        rootElement.addContent(elementIndirizzoRichiesta);

        if (vista.getNuovaRisorsa() != null && !vista.getNuovaRisorsa().isEmpty()) {
            Element elementNuovaRisorsa = new Element("risorsa");
            elementNuovaRisorsa.addContent(vista.getNuovaRisorsa());
            rootElement.addContent(elementNuovaRisorsa);
        }

        Element elementDatiParziali = new Element("datiParziali");

        if (this.vista.getListaParametriDB().size() > 0) {
            Element elementDatiDB = new Element("datiDB");
            for (DatoPrimitivo datoPrimitivo : this.vista.getListaParametriDB()) {
                Element dato = new Element("dato");
                dato.setAttribute(new Attribute("nome", datoPrimitivo.getNome()));
                dato.addContent(datoPrimitivo.getValore());
                elementDatiDB.addContent(dato);
            }
            elementDatiParziali.addContent(elementDatiDB);
        }

        if (this.vista.getListaParametriInterattivi().size() > 0) {
            Element elementDatiInterattivi = new Element("datiInterattivi");
            for (DatoPrimitivo datoPrimitivo : this.vista.getListaParametriInterattivi()) {
                Element dato = new Element("dato");
                dato.setAttribute(new Attribute("nome", datoPrimitivo.getNome()));
                dato.addContent(datoPrimitivo.getValore());
                elementDatiInterattivi.addContent(dato);
            }
            elementDatiParziali.addContent(elementDatiInterattivi);
        }

        rootElement.addContent(elementDatiParziali);
        document = new Document(rootElement);

        String bodyMessage = XmlJDomUtil.convertiDocumentToString(document);
        return bodyMessage;
    }

    private void registraRisposta(String stringaRisposta) throws XmlException, IOException, ParserConfigurationException, ParserConfigurationException, SAXException, SAXException, TransformerConfigurationException, TransformerException {
        RispostaEseguiIstanza rispostaEseguiIstanza = new RispostaEseguiIstanza();
        Document document = XmlJDomUtil.caricaXML(stringaRisposta);
        Element rispostaIstanzaElement = document.getRootElement();
        Element successElement = rispostaIstanzaElement.getChild("successo");
        if (successElement != null) {

            rispostaEseguiIstanza.setErrore(false);
            Attribute tipoAttribute = successElement.getAttribute("tipo");
            rispostaEseguiIstanza.setTipoMessaggio(tipoAttribute.getValue());

            Element element = successElement.getChild("idMessaggioRichiesta");
            rispostaEseguiIstanza.setIdMessaggio(element.getText());


            element = successElement.getChild("messaggio");
            if (element != null) {
                rispostaEseguiIstanza.setMessaggio(element.getText());
            }
            element = successElement.getChild("idMessaggioRisposta");
            rispostaEseguiIstanza.setIdMessaggioRisposta("-----------------------------");

            if (element != null) {
                rispostaEseguiIstanza.setIdMessaggioRisposta(element.getText());
            }
            caricaMessaggio("istanza_messaggio_invio");
        } else {
            Element erroreElemet = rispostaIstanzaElement.getChild("errore");
            rispostaEseguiIstanza.setErrore(true);
            String messaggioErrore = erroreElemet.getChild("messaggioErrore").getText();
            rispostaEseguiIstanza.setMessaggioErrore(messaggioErrore);
            if (messaggioErrore.startsWith("Authorization Failed")) {
                this.vista.setVisualizzaPulsanteLogin(true);
            }
            this.errore = "Errore durante l'esecuzione dell'istanza. " + messaggioErrore;
        //caricaMessaggio("istanza_messaggio_invio_errore");
        }
        this.vista.setRispostaEseguiIstanza(rispostaEseguiIstanza);
        this.vista.setVisualizzaRispostaSincrona(this.vista.getIstanzaOperationSelezionata().isSincrono());
        this.vista.setVisualizzaVerificaRispostaAincrona(this.vista.getIstanzaOperationSelezionata().isAsincrono());

    }

    public void mostraRispostaSincrona() throws DAOException {
        this.vista.setVisualizzaPannelloRispostaSincrona(true);
    }

    public void verificaRispostaAincrona() {
        try {
            Messaggio rispostaAsincrona = daoMessaggio.findResponseAsyncByIdMessageRequest(vista.getRispostaEseguiIstanza().getIdMessaggio());
            if (rispostaAsincrona != null) {
                RispostaEseguiIstanza risposta = new RispostaEseguiIstanza();
                risposta.setErrore(false);
                risposta.setIdMessaggio(rispostaAsincrona.getIdRelatesTo());
                risposta.setIdMessaggioRisposta(rispostaAsincrona.getIdMessaggio());
                risposta.setTipoMessaggio(rispostaAsincrona.getTipo());
                risposta.setMessaggio(rispostaAsincrona.getMessaggio());
                this.vista.setRispostaEseguiIstanza(risposta);
                this.vista.setVisualizzaPannelloRispostaSincrona(true);
//                this.vista.setVisualizzaVerificaRispostaAincrona(false);
            } else {
                caricaMessaggio("istana_operation_esegui_nessuna_risposta_messaggio_asincrono");
            }
        } catch (DAOException ex) {
            caricaErrore("errore_generico_db");
        }
    }

    public String defaultUrlInvio() {
        try {
            IstanzaOperation operation = this.vista.getMappaIdentificatore().get(this.vista.getOperationSelezionata());
            getDaoIstanzaOperation().makePersistent(operation);

            if (this.vista.isAutenticazioneFederata()) {
                String indirizzoSP = ConfigurazioneStatico.getInstance().getFreesbeeSPURL();
                this.vista.setUrlInvio(indirizzoSP + "/forwardToPD.post");
            } else {
                this.vista.setUrlInvio(operation.getIstanzaPortType().getURLInvio());
            }
            logger.warn("URL INVIO: " + this.vista.getUrlInvio());

        } catch (DAOException ex) {
            caricaErrore("errore_generico_db");
            logger.error("Problemi durante il refresh dell'istanza operazione selezionata" + ex);
        }
        return null;
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

    public VistaEseguiIstanza getVista() {
        return vista;
    }

    public void setVista(VistaEseguiIstanza vista) {
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

    public String getErroreIndirizzo() {
        return erroreIndirizzo;
    }

    public void setErroreIndirizzo(String erroreIndirizzo) {
        this.erroreIndirizzo = erroreIndirizzo;
    }
}
