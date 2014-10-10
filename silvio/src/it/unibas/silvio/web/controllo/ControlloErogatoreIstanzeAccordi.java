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

public class ControlloErogatoreIstanzeAccordi {

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
        this.vista.setStepErogatore(1);
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

    private String estraiXSDTarget() throws DAOException, XmlException, XmlException {
        AccordoDiCollaborazione accordoDiCollaborazione = this.vista.getAccordoDiCollaborazione();
        IstanzaOperation istanzaOP = this.vista.getIstanzaOperation();
        Operation currentOp = istanzaOP.getOperation();
        Message message;
        Operation opFruitore;
        if (currentOp.isSincrono()) {
            message = currentOp.getMessageOut();
        } else {
            PortType fruitore = accordoDiCollaborazione.getPortType(currentOp.getPortType().getNome(), PortType.FRUITORE);
            logger.info("Trovato il portType del fruitore" + fruitore);
            if (fruitore == null) {
                fruitore = istanzaOP.getOperation().getPortType();
                opFruitore = fruitore.getOperation(currentOp.getNome());
                message = opFruitore.getMessageOut();
            } else {
                opFruitore = fruitore.getOperation(currentOp.getNome());
                message = opFruitore.getMessageIn();
            }
        }
        if (message == null) {
            String err = "Impossibile generare lo schema target. Message type sconosciuto";
            logger.error(err);
            this.setErrore(err);
            return null;
        }
        Configurazione config = daoConfigurazione.caricaConfigurazione();
        String typesOP = message.getTypes();
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

    public String annulla() {
        return "home";
    }

    public String configuraDatiErogatore() {
        this.getVista().setVisualizzaXSD(false);
        DatiPM datiPM = this.vista.getDatiPM();
        Dati dati = this.vista.getDatiPM().getDati();
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
            if (this.vista.getIstanzaOperation().getXSLTSoapToSQLErogatore() == null) {
                this.setErrore("Attenzione! Non è stato caricato il file delle trasformazioni");
                return null;
            }
        }
        if (this.vista.getOperationConfigurare().isAsincrono()) {
            String rispostaAutomatica = this.vista.getTipoRisposta();
            if (rispostaAutomatica.equals("AUTOMATICA")) {
                this.vista.getIstanzaOperation().setRispostaAutomatica(true);
            } else {
                this.vista.getIstanzaOperation().setRispostaAutomatica(false);
            }
            String indirizzoAscolto = this.vista.getIndirizzoAscoltoErogatoreAsincrono();
            if (indirizzoAscolto == null) {
                this.setErrore("Attenzione! Non e' stato inserito l'indirizzo per la risposta dell'erogatore");
                return null;
            }
            this.vista.getIstanzaOperation().setIndirizzoRispostaAsincronoErogatore(indirizzoAscolto);
        }
        this.vista.getIstanzaOperation().setDatiRisposta(dati);
        this.vista.setQuartoPasso(true);
        this.vista.setQuintoPasso(false);
        this.vista.setStepErogatore(3);
        return null;
    }

    public String uploadFile() {
        try {
            UploadedFile fileWSDLErogatoreRichiesta = this.getVista().getFileXSLTErogatoreRichiesta();
            if (fileWSDLErogatoreRichiesta == null) {
                this.setErrore("Specificare il file delle trasformazioni");
                return null;
            }
            IstanzaAccordoDiCollaborazione istanzaAccordo = this.vista.getIstanzaAccordo();
            String cartellaTmp = istanzaAccordo.getCartellaFiles();
            String stringaFileRisposta = SilvioWebUtil.uploadFile(fileWSDLErogatoreRichiesta, cartellaTmp);
            this.vista.getIstanzaOperation().setXSLTSoapToSQLErogatore(stringaFileRisposta);
            this.setMessaggio("Upload del file eseguito correttamente");
        } catch (IOException ex) {
            logger.error("Errore nella lettura dell'elenco degli accordi di collaborazione " + ex);
            this.errore = "Errore nella lettura dal db";
        }
        return null;
    }

    public String visualizzaXSDSource() {
        try {
            Dati dati = this.vista.getIstanzaOperation().getDatiRisposta();
            String xsdRichiesta = leggiXSDRichiesta();
            Document documentXSD;
            if (xsdRichiesta == null || xsdRichiesta.isEmpty()) {
                documentXSD = XSDUtil.datiToXSD(dati);
            } else {
                logger.info("XSD\n " + xsdRichiesta);
                Document docXSDRichiesta = XmlJDomUtil.caricaXML(xsdRichiesta);
                String typesOP = leggiNomeElementRichiesta();
                logger.info("Devo caricare lo schema per il tipo " + typesOP);
                documentXSD = XSDUtil.datiAndRichiestaToXSD(dati, docXSDRichiesta, typesOP, "schemaRichiesta.xsd");
            }
            String stringaXSD = XmlJDomUtil.stampaXML(documentXSD);
            this.vista.setNomeRoot("datiCompleti");
            this.vista.setVisualizzaXSD(true);
            this.vista.setVisualizzaSorgenteRichiesta(true);
            this.utente.setFileXSD(stringaXSD);
            this.utente.setNomeFileXSD("sourceRispostaErogatore.xsd");
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Impossibile generare l'xsd sorgente. " + ex);
            this.setErrore("Impossibile generare l'xsd sorgente. " + ex);
        }
        return null;
    }

    private String leggiXSDRichiesta() throws DAOException, XmlException {
        AccordoDiCollaborazione accordoDiCollaborazione = this.vista.getAccordoDiCollaborazione();
//        String typesOP = leggiNomeElementRichiesta();
//        logger.info("Devo caricare lo schema per il tipo " + typesOP);
        Configurazione config = daoConfigurazione.caricaConfigurazione();
        logger.info("Cartella conf: " + config.getDirConfig());
        logger.info("Cartella accordo: " + accordoDiCollaborazione.getCartellaFiles());
        logger.info("WSDL Erogatore: " + accordoDiCollaborazione.getWSDLErogatore());
        String fileWSDL = config.getDirConfig() + accordoDiCollaborazione.getCartellaFiles() + File.separator +
                AccordoDiCollaborazione.CARTELLA_FILE + File.separator + File.separator + accordoDiCollaborazione.getWSDLErogatore();
        logger.info("Percorso file wsdl: " + fileWSDL);

        this.vista.setNomeRoot("ZZZ");
        org.w3c.dom.Element docSchema = WSDLUtil.estraiDocSchema(fileWSDL, null);
//        org.w3c.dom.Element docSchema = WSDLUtil.estraiDocSchema(fileWSDL, typesOP);
        return XmlUtil.stampaDocument(docSchema);
    }

    private String leggiNomeElementRichiesta() {
        IstanzaOperation istanzaOP = this.vista.getIstanzaOperation();
        Operation currentOp = istanzaOP.getOperation();
        Message messageIn = currentOp.getMessageIn();
        if (messageIn == null) {
            String err = "Impossibile generare lo schema target. Message type sconosciuto";
            logger.error(err);
            this.setErrore(err);
            return null;
        }
        return messageIn.getTypes();
    }

    public String visualizzaXSDTarget() {
        try {
            String stringaXSD = leggiXSDRichiesta();
            this.vista.setVisualizzaXSD(true);
            this.utente.setFileXSD(stringaXSD);
            this.utente.setNomeFileXSD("sourceRichiestaErogatore.xsd");
        } catch (DAOException ex) {
            logger.error("Impossibile caricare la configurazione. " + ex);
            this.setErrore("Impossibile caricare la configurazione. " + ex);
        } catch (XmlException ex) {
            logger.error("Impossibile generare l'xsd target. " + ex);
            this.setErrore("Impossibile generare l'xsd target. " + ex);
        }
        return null;
    }

    public String visualizzaXSDTargetRispostaErogatore() {
        try {
            String stringaXSD = estraiXSDTarget();
            this.vista.setVisualizzaXSD(true);
            this.vista.setVisualizzaSorgenteRichiesta(false);
            this.utente.setFileXSD(stringaXSD);
            this.utente.setNomeFileXSD("targetRispostaErogatore.xsd");
        } catch (DAOException ex) {
            logger.error("Impossibile caricare la configurazione. " + ex);
            this.setErrore("Impossibile caricare la configurazione. " + ex);
        } catch (XmlException ex) {
            logger.error("Impossibile generare l'xsd target. " + ex);
            this.setErrore("Impossibile generare l'xsd target. " + ex);
        }
        return null;
    }

    public String visualizzaXSDRichiestaErogatore() {
        try {
            String stringaXSD = estraiXSDTarget();
            this.vista.setVisualizzaXSD(true);
            this.vista.setVisualizzaSorgenteRichiesta(false);
            this.utente.setFileXSD(stringaXSD);
            this.utente.setNomeFileXSD("schemaRichiesta.xsd");
        } catch (DAOException ex) {
            logger.error("Impossibile caricare la configurazione. " + ex);
            this.setErrore("Impossibile caricare la configurazione. " + ex);
        } catch (XmlException ex) {
            logger.error("Impossibile generare l'xsd target. " + ex);
            this.setErrore("Impossibile generare l'xsd target. " + ex);
        }
        return null;
    }

    public String caricaTrasformazioneErogatore() {
        if (this.getVista().getDatiPM().isDatiPayloadSelezionati()) {
            this.vista.setQuintoPasso(true);
            return prossimaOperation();
        }
        UploadedFile fileTrasformazioneErogatore = this.vista.getFileXSLTErogatoreRisposta();
        if (fileTrasformazioneErogatore == null) {
            this.setErrore("Specificare il file delle trasformazioni");
            return null;
        }
        this.vista.setQuintoPasso(true);
        return caricaTrasformazioneRichiesta(fileTrasformazioneErogatore);
    }

    public String caricaTrasformazioneRichiesta(UploadedFile fileTrasformazioneErogatore) {
        try {
            this.getVista().setVisualizzaXSD(false);
            IstanzaAccordoDiCollaborazione istanzaAccordo = this.vista.getIstanzaAccordo();
            IstanzaOperation istanzaOperation = this.vista.getIstanzaOperation();
            String cartellaTmp = istanzaAccordo.getCartellaFiles();
            String stringaFileRichiesta = SilvioWebUtil.uploadFile(fileTrasformazioneErogatore, cartellaTmp);
            istanzaOperation.setXSLTDatiToSoapErogatore(stringaFileRichiesta);
        } catch (IOException ex) {
            logger.error("Impossibile caricare il file delle trasformazioni. " + ex);
            this.setErrore("Impossibile caricare il file delle trasformazioni. " + ex);
        }
        return prossimaOperation();
    }

    public String configuraRichiestaErogatore() {
        this.getVista().setVisualizzaXSD(false);
        if (this.getVista().isElaboraRichiesta()) {
            UploadedFile fileWSDLErogatoreInsert = this.vista.getFileXSLTErogatoreRichiestaInsert();
            if (fileWSDLErogatoreInsert == null) {
                this.setErrore("Specificare il file per effettuare la insert sul DB");
                return null;
            }
            DatiSQL datiSQL = this.vista.getDatiPM().getDatiDBErogatoreRisposta();
            if (datiSQL.getSelect().getNomeUtente().isEmpty() || datiSQL.getSelect().getPassword().isEmpty() ||
                    datiSQL.getSelect().getNomeDB().isEmpty() || datiSQL.getSelect().getTipoDB().isEmpty() ||
                    datiSQL.getSelect().getIndirizzoDB().isEmpty()) {
                this.setErrore("E' indispensabile fornire tutte le informazioni sul DB");
                return null;
            }
            try {
                IstanzaAccordoDiCollaborazione istanzaAccordo = this.vista.getIstanzaAccordo();
                IstanzaOperation istanzaOperation = this.vista.getIstanzaOperation();
                String cartellaTmp = istanzaAccordo.getCartellaFiles();
                Dati datiRichiesta = new Dati();
                istanzaOperation.setElaboraRichiesta(true);
                istanzaOperation.setDatiRichiesta(datiRichiesta);
                istanzaOperation.getDatiRichiesta().setDatiSQL(datiSQL);
                String stringaFileInsert = SilvioWebUtil.uploadFile(fileWSDLErogatoreInsert, cartellaTmp);
                istanzaOperation.setXSLTSoapToSQLInsertErogatore(stringaFileInsert);
            } catch (IOException ex) {
                logger.error("Impossibile caricare il file delle trasformazioni. " + ex);
                this.setErrore("Impossibile caricare il file delle trasformazioni. " + ex);
            }
        }
        if (this.getVista().isElaboraRichiestaMail()) {
            UploadedFile fileXSLTErogatoreMailRichiesta = this.vista.getFileXSLTErogatoreMailRichiesta();
            if (fileXSLTErogatoreMailRichiesta == null) {
                this.setErrore("Specificare il file per effettuare la insert sul DB");
                return null;
            }
            String indirizzoMail = this.vista.getIndirizzoMail();
            String oggettoMail = this.vista.getOggettoMail();
            if (indirizzoMail.equals("") || oggettoMail.equals("")) {
                this.setErrore("E' indispensabile fornire tutte le informazioni sul indirizzo e-mail");
                return null;
            }
            try {
                IstanzaAccordoDiCollaborazione istanzaAccordo = this.vista.getIstanzaAccordo();
                IstanzaOperation istanzaOperation = this.vista.getIstanzaOperation();
                String cartellaTmp = istanzaAccordo.getCartellaFiles();
                istanzaOperation.setInviaMail(true);
                istanzaOperation.setIndirizzoMail(indirizzoMail);
                istanzaOperation.setOggettoMail(oggettoMail);
                String stringaFileInsert = SilvioWebUtil.uploadFile(fileXSLTErogatoreMailRichiesta, cartellaTmp);
                istanzaOperation.setXSLTSoapToMail(stringaFileInsert);
            } catch (IOException ex) {
                logger.error("Impossibile caricare il file delle trasformazioni. " + ex);
                this.setErrore("Impossibile caricare il file delle trasformazioni. " + ex);
            }
        }
        this.vista.setQuartoPasso(false);
        this.vista.setTerzoPasso(true);
        this.vista.setStepErogatore(2);
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
