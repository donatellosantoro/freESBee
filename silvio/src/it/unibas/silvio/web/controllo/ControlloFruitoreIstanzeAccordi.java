package it.unibas.silvio.web.controllo;

import it.unibas.silvio.modello.AccordoDiCollaborazione;
import it.unibas.silvio.modello.Configurazione;
import it.unibas.silvio.modello.Dati;
import it.unibas.silvio.modello.DatiSQL;
import it.unibas.silvio.modello.IstanzaAccordoDiCollaborazione;
import it.unibas.silvio.modello.IstanzaOperation;
import it.unibas.silvio.modello.IstanzaPortType;
import it.unibas.silvio.modello.Message;
import it.unibas.silvio.modello.Operation;
import it.unibas.silvio.modello.PortType;
import it.unibas.silvio.modello.Utente;
import it.unibas.silvio.persistenza.DAOException;
import it.unibas.silvio.persistenza.IDAOAccordoDiCollaborazione;
import it.unibas.silvio.persistenza.IDAOConfigurazione;
import it.unibas.silvio.persistenza.IDAOIstanzaAccordoDiCollaborazione;
import it.unibas.silvio.web.util.SilvioWebUtil;
import it.unibas.silvio.web.vista.VistaIstanzeAccordi;
import it.unibas.silvio.web.vista.pm.DatiPM;
import it.unibas.silvio.xml.WSDLUtil;
import it.unibas.silvio.xml.XSDUtil;
import it.unibas.silvio.xml.XmlException;
import it.unibas.silvio.xml.XmlJDomUtil;
import it.unibas.silvio.xml.XmlUtil;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.jdom.Document;
import org.xml.sax.SAXParseException;

public class ControlloFruitoreIstanzeAccordi {

    private Log logger = LogFactory.getLog(this.getClass());
    private VistaIstanzeAccordi vista;
    private String messaggio;
    private String errore;
    private String successo;
    private IDAOAccordoDiCollaborazione daoAccordoDiCollaborazione;
    private IDAOIstanzaAccordoDiCollaborazione daoIstanzaAccordoDiCollaborazione;
    private IDAOConfigurazione daoConfigurazione;
    private Utente utente;
    private ControlloIstanzeAccordi controllo;

    public String prossimaOperation() {
        List<Operation> listaOperation = this.vista.getOperationSelezionate();
        int intOperation = this.vista.getOperationConfigurareInt();
        if (intOperation == listaOperation.size()) {
            return "confermaConfigurazioneIstanza";
        }
        this.getVista().setOperationConfigurare(listaOperation.get(intOperation));
        this.vista.setOperationConfigurareInt(intOperation + 1);
        this.vista.setDatiPM(new DatiPM());
        this.vista.setStep(1);
        logger.info("Configuriamo la prossima operation " + this.vista.getOperationConfigurareInt());

        String ruolo = this.vista.getSceltaRuolo();
        IstanzaAccordoDiCollaborazione accordo = this.vista.getIstanzaAccordo();
        IstanzaOperation istanzaOperation = new IstanzaOperation();
        this.vista.setIstanzaOperation(istanzaOperation);
        IstanzaPortType istanzaPT = null;
        Operation operation = this.vista.getOperationConfigurare();
        if (ruolo.equals("FRUITORE")) {            
            istanzaPT = accordo.getOrCreaIstanzaPortType(operation.getPortType().getNome(), PortType.EROGATORE, IstanzaPortType.FRUIZIONE);
        } else {            
            istanzaPT = accordo.getOrCreaIstanzaPortType(operation.getPortType().getNome(), PortType.EROGATORE, IstanzaPortType.EROGAZIONE);
        }
        Operation op = istanzaPT.getPortType().getOperation(operation.getNome());
        istanzaOperation.setOperation(op);
        istanzaOperation.setIstanzaPortType(istanzaPT);
        istanzaPT.getListaIstanzaOperation().add(istanzaOperation);
        return "nuovaIstanzaOperation";
    }

    public String valida() {
        this.vista.setVisualizzaXSD(false);
        try {
            String payload = this.vista.getDatiPM().getPayloadCostante();
            if (payload == null || payload.isEmpty()) {
                return null;
            }
            ByteArrayInputStream isMessaggio = new ByteArrayInputStream(payload.getBytes());
            String stringXSD = estraiXSDTarget();
            ByteArrayInputStream isXSD = new ByteArrayInputStream(stringXSD.getBytes());
            XmlJDomUtil.caricaXML(isMessaggio, true, isXSD);
            this.successo = "Messaggio valido";
        } catch (XmlException ex) {
            logger.error("Messaggio non valido: " + ex);
            this.setErrore("Messaggio non valido: " + ex);
        } catch (Exception ex) {
            logger.error("Impossibile generare l'xsd del messaggio. " + ex);
            this.setErrore("Impossibile generare l'xsd del messaggio. " + ex);
        }
        return null;
    }

    public String configuraRichiesta() {
        this.getVista().setVisualizzaXSD(false);
        DatiPM datiPM = this.vista.getDatiPM();
        Dati dati = this.vista.getDatiPM().getDati();
        if (datiPM.isDatiDBSelezionati() && !datiPM.isDatiPayloadSelezionati()) {
            List<String> listaSelect = datiPM.getListaSelect();
            if (listaSelect.size() == 0) {
                this.setErrore("Non è stata specificata alcuna select per l'estrazione dei dati dal DB");
                return null;
            }
            DatiSQL datiSQL = datiPM.getDatiDB();
            if (datiSQL.getSelect().getNomeUtente().isEmpty() || datiSQL.getSelect().getPassword().isEmpty() ||
                    datiSQL.getSelect().getNomeDB().isEmpty() || datiSQL.getSelect().getTipoDB().isEmpty() ||
                    datiSQL.getSelect().getIndirizzoDB().isEmpty()) {
                this.setErrore("E' indispensabile fornire tutte le informazioni sul DB");
                return null;
            }
            String query = listaSelect.get(0);
            for (int i = 1; i < listaSelect.size(); i++) {
                query += " UNION " + listaSelect.get(i);
            }
            datiSQL.getSelect().setQuery(query);
            dati.setDatiSQL(datiSQL);
        }
        if (datiPM.isDatiInterattiviSelezionati() && !datiPM.isDatiPayloadSelezionati()) {
            dati.setDatiInterattivi(datiPM.getDatiInterattivi());
        }
        if (datiPM.isDatiCostantiSelezionati() && !datiPM.isDatiPayloadSelezionati()) {
            dati.setDatiCostanti(datiPM.getDatiCostanti());
        }
        if (datiPM.isDatiPayloadSelezionati()) {
            String payload = this.vista.getDatiPM().getPayloadCostante();
            if (payload == null || payload.isEmpty()) {
                this.setErrore("E' indispensabile specificare il payload");
                return null;
            }
            dati.setPayloadCostante(datiPM.getPayloadCostante());
            logger.info("Setto il payload costante con " + datiPM.getPayloadCostante());
        }
        String ruolo = this.vista.getSceltaRuolo();
        this.vista.getIstanzaOperation().setDatiRichiesta(dati);
        this.vista.setStep(2);
        this.vista.setTerzoPasso(true);
        this.vista.setQuartoPasso(false);
        return null;
    }

    public String visualizzaXSDSource() {
        try {
            Dati dati = this.vista.getIstanzaOperation().getDatiRichiesta();
            Document documentXSD = XSDUtil.datiToXSD(dati);
            String stringaXSD = XmlJDomUtil.stampaXML(documentXSD);
            this.vista.setVisualizzaXSD(true);
            this.vista.setNomeRoot("datiCompleti");
            this.utente.setFileXSD(stringaXSD);
            this.utente.setNomeFileXSD("sourceRichiestaFruitore.xsd");
        } catch (XmlException ex) {
            logger.error("Impossibile generare l'xsd sorgente. " + ex);
            this.setErrore("Impossibile generare l'xsd sorgente. " + ex);
        }
        return null;
    }

    public String visualizzaXSDTarget() {
        try {
            String stringaXSD = estraiXSDTarget();
            this.vista.setVisualizzaXSD(true);
            this.utente.setFileXSD(stringaXSD);
            this.utente.setNomeFileXSD("targetRichiestaFruitore.xsd");
        } catch (DAOException ex) {
            logger.error("Impossibile caricare la configurazione. " + ex);
            this.setErrore("Impossibile caricare la configurazione. " + ex);
        } catch (XmlException ex) {
            logger.error("Impossibile generare l'xsd target. " + ex);
            this.setErrore("Impossibile generare l'xsd target. " + ex);
        }
        return null;
    }

    private String estraiXSDTarget() throws DAOException, XmlException, XmlException {
        AccordoDiCollaborazione accordoDiCollaborazione = this.vista.getAccordoDiCollaborazione();
        IstanzaOperation istanzaOP = this.vista.getIstanzaOperation();
        Operation currentOp = istanzaOP.getOperation();
        Message messageIn = currentOp.getMessageIn();
        if (messageIn == null) {
            String err = "Impossibile generare lo schema target. Message type sconosciuto";
            logger.error(err);
            this.setErrore(err);
            return null;
        }
        Configurazione config = daoConfigurazione.caricaConfigurazione();
        String typesOP = messageIn.getTypes();     
        this.vista.setNomeRoot(typesOP);
        logger.info("Devo caricare lo schema per il tipo " + typesOP);
        logger.info("Cartella conf: " + config.getDirConfig());
        logger.info("Cartella accordo: " + accordoDiCollaborazione.getCartellaFiles());
        logger.info("WSDL Erogatore: " + accordoDiCollaborazione.getWSDLErogatore());
        String fileWSDL = config.getDirConfig() + accordoDiCollaborazione.getCartellaFiles() + File.separator +
                AccordoDiCollaborazione.CARTELLA_FILE + File.separator + File.separator + accordoDiCollaborazione.getWSDLErogatore();
        logger.info("Percorso file wsdl: " + fileWSDL);
//        org.w3c.dom.Element docSchema = WSDLUtil.estraiDocSchema(fileWSDL, typesOP);
        org.w3c.dom.Element docSchema = WSDLUtil.estraiDocSchema(fileWSDL, null);
        return XmlUtil.stampaDocument(docSchema);
    }

    public String visualizzaXSDSourceRispostaFruitore() {
        try {
            AccordoDiCollaborazione accordoDiCollaborazione = this.vista.getAccordoDiCollaborazione();
            IstanzaOperation istanzaOP = this.vista.getIstanzaOperation();
            Operation currentOp = istanzaOP.getOperation();
            Message message;
            if (currentOp.isSincrono()) {
                message = currentOp.getMessageOut();
            } else {
                message = currentOp.getMessageIn();
            }
            if (message == null) {
                String err = "Impossibile generare lo schema target. Message type sconosciuto";
                logger.error(err);
                this.setErrore(err);
                return null;
            }
            Configurazione config = daoConfigurazione.caricaConfigurazione();
            String typesOP = message.getTypes();
            logger.info("Devo caricare lo schema per il tipo " + typesOP);
            logger.info("Cartella conf: " + config.getDirConfig());
            logger.info("Cartella accordo: " + accordoDiCollaborazione.getCartellaFiles());
            logger.info("WSDL Erogatore: " + accordoDiCollaborazione.getWSDLErogatore());
            String fileWSDL = config.getDirConfig() + accordoDiCollaborazione.getCartellaFiles() + File.separator +
                    AccordoDiCollaborazione.CARTELLA_FILE + File.separator + File.separator + accordoDiCollaborazione.getWSDLErogatore();
            logger.info("Percorso file wsdl: " + fileWSDL);
//            org.w3c.dom.Element docSchema = WSDLUtil.estraiDocSchema(fileWSDL, typesOP);
            org.w3c.dom.Element docSchema = WSDLUtil.estraiDocSchema(fileWSDL, null);
            String stringaXSD = XmlUtil.stampaDocument(docSchema);
            this.vista.setVisualizzaXSD(true);            
            this.vista.setNomeRoot(typesOP);
            this.utente.setFileXSD(stringaXSD);
            this.utente.setNomeFileXSD("sourceRispostaFruitore.xsd");
        } catch (DAOException ex) {
            logger.error("Impossibile caricare la configurazione. " + ex);
            this.setErrore("Impossibile caricare la configurazione. " + ex);
        } catch (XmlException ex) {
            logger.error("Impossibile generare l'xsd target. " + ex);
            this.setErrore("Impossibile generare l'xsd target. " + ex);
        }
        return null;
    }

    public String caricaTrasformazioneRichiesta() {
        IstanzaOperation istanzaOperation = this.vista.getIstanzaOperation();
        IstanzaAccordoDiCollaborazione istanzaAccordo = this.vista.getIstanzaAccordo();
        Operation operation = istanzaOperation.getOperation();
        try {
            if (!this.getVista().getDatiPM().isDatiPayloadSelezionati()) {
                this.getVista().setVisualizzaXSD(false);
                UploadedFile fileWSDLFruitoreRichiesta = this.vista.getFileXSLTFruitoreRichiesta();
                if (fileWSDLFruitoreRichiesta == null) {
                    this.setErrore("Specificare il file delle trasformazioni");
                    return null;
                }
                this.getVista().setVisualizzaXSD(false);
                String cartellaTmp = istanzaAccordo.getCartellaFiles();
                String stringaFileRichiesta = SilvioWebUtil.uploadFile(fileWSDLFruitoreRichiesta, cartellaTmp);
                istanzaOperation.setXSLTCompletiToSoapFruitore(stringaFileRichiesta);
            }
            if (istanzaOperation.isOneWay()) {
                return prossimaOperation();
            }
            if (istanzaOperation.isAsincrono()) {
                IstanzaPortType istanzaPT = istanzaAccordo.getOrCreaIstanzaPortType(operation.getPortType().getNome(), PortType.FRUITORE, IstanzaPortType.EROGAZIONE);                
                Operation op = istanzaPT.getPortType().getOperation(operation.getNome());
                logger.info("operation: " + operation);
                logger.info("Stiamo creando l'operation " + op + " da settare al portType");
                IstanzaOperation nuovaIstanzaOperation = new IstanzaOperation();
                this.vista.setIstanzaOperation(nuovaIstanzaOperation);
                nuovaIstanzaOperation.setOperation(op);
                nuovaIstanzaOperation.setIstanzaPortType(istanzaPT);
                istanzaPT.getListaIstanzaOperation().add(nuovaIstanzaOperation);
                this.vista.setIstanzaOperation(nuovaIstanzaOperation);
            }
            this.getVista().setStep(3);
            this.vista.setQuartoPasso(true);
        } catch (IOException ex) {
            logger.error("Impossibile caricare il file delle trasformazioni. " + ex);
            this.setErrore("Impossibile caricare il file delle trasformazioni. " + ex);
        } catch (IllegalStateException iex){
            logger.error("Impossibile creare l'istanza. " + iex);
            this.setErrore("Impossibile creare l'istanza " + iex);
        }
        return "successo";
    }

    public String configuraRispostaFruitore() {
        this.vista.setQuintoPasso(true);
        if (!this.vista.getIstanzaOperation().isElaboraRisposta()) {
            return prossimaOperation();
        }
        return configuraRichiestaRisposta();
    }

    private String configuraRichiestaRisposta() {
        UploadedFile fileWSDLFruitoreRisposta = this.vista.getFileXSLTFruitoreRisposta();
        DatiSQL datiSQL = this.vista.getDatiPM().getDatiDBFruitoreRisposta();
        if (fileWSDLFruitoreRisposta == null) {
            this.setErrore("Specificare il file delle trasformazioni");
            return null;
        }
        if (datiSQL.getSelect().getNomeUtente().isEmpty() || datiSQL.getSelect().getPassword().isEmpty() ||
                datiSQL.getSelect().getNomeDB().isEmpty() || datiSQL.getSelect().getTipoDB().isEmpty() ||
                datiSQL.getSelect().getIndirizzoDB().isEmpty()) {
            logger.info("sono nell'if del db");
            this.setErrore("E' indispensabile fornire tutte le informazioni sul DB");
            return null;
        }
        try {
            IstanzaAccordoDiCollaborazione istanzaAccordo = this.vista.getIstanzaAccordo();
            IstanzaOperation istanzaOperation = this.vista.getIstanzaOperation();
            Dati datiRisposta = new Dati();
            istanzaOperation.setDatiRisposta(datiRisposta);
            istanzaOperation.getDatiRisposta().setDatiSQL(datiSQL);
            String cartellaTmp = istanzaAccordo.getCartellaFiles();
            String stringaFileRisposta;
            if (fileWSDLFruitoreRisposta != null) {
                stringaFileRisposta = SilvioWebUtil.uploadFile(fileWSDLFruitoreRisposta, cartellaTmp);
                istanzaOperation.setXSLTDatiToSQLFruitore(stringaFileRisposta);
            }
            return prossimaOperation();
        } catch (IOException ex) {
            logger.error("Impossibile caricare il file delle trasformazioni. " + ex);
            this.setErrore("Impossibile caricare il file delle trasformazioni. " + ex);
        }
        return null;

    }

    public String salvaXSD() {
        return null;
    }

    public String getErrore() {
        return errore;
    }

    public void setErrore(String errore) {
        this.errore = errore;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    public String getSuccesso() {
        return successo;
    }

    public void setSuccesso(String successo) {
        this.successo = successo;
    }

    public VistaIstanzeAccordi getVista() {
        return vista;
    }

    public void setVista(VistaIstanzeAccordi vista) {
        this.vista = vista;
    }

    public IDAOAccordoDiCollaborazione getDaoAccordoDiCollaborazione() {
        return daoAccordoDiCollaborazione;
    }

    public void setDaoAccordoDiCollaborazione(IDAOAccordoDiCollaborazione daoAccordoDiCollaborazione) {
        this.daoAccordoDiCollaborazione = daoAccordoDiCollaborazione;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public IDAOConfigurazione getDaoConfigurazione() {
        return daoConfigurazione;
    }

    public void setDaoConfigurazione(IDAOConfigurazione daoConfigurazione) {
        this.daoConfigurazione = daoConfigurazione;
    }

    public IDAOIstanzaAccordoDiCollaborazione getDaoIstanzaAccordoDiCollaborazione() {
        return daoIstanzaAccordoDiCollaborazione;
    }

    public void setDaoIstanzaAccordoDiCollaborazione(IDAOIstanzaAccordoDiCollaborazione daoIstanzaAccordoDiCollaborazione) {
        this.daoIstanzaAccordoDiCollaborazione = daoIstanzaAccordoDiCollaborazione;
    }

    public ControlloIstanzeAccordi getControllo() {
        return controllo;
    }

    public void setControllo(ControlloIstanzeAccordi controllo) {
        this.controllo = controllo;
    }
}
