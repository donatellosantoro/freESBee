package it.unibas.silvio.web.controllo;

import it.unibas.silvio.modello.*;
import it.unibas.silvio.persistenza.DAOException;
import it.unibas.silvio.persistenza.IDAOAccordoDiCollaborazione;
import it.unibas.silvio.persistenza.IDAOConfigurazione;
import it.unibas.silvio.persistenza.IDAOIstanzaAccordoDiCollaborazione;
import it.unibas.silvio.persistenza.IDAOIstanzaPortType;
import it.unibas.silvio.persistenza.hibernate.DAOUtilHibernate;
import it.unibas.silvio.web.util.FileUtil;
import it.unibas.silvio.web.vista.VistaIstanzeAccordi;
import it.unibas.silvio.web.vista.pm.DatiPM;
import it.unibas.silvio.web.vista.pm.IstanzaAccordoDiCollaborazionePM;
import it.unibas.silvio.ws.istanza.client.IWSIstanza;
import it.unibas.silvio.ws.istanza.client.WSIstanzaImplService;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.faces.model.SelectItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;

public class ControlloIstanzeAccordi {

    private Log logger = LogFactory.getLog(this.getClass());
    private VistaIstanzeAccordi vista;
    private String messaggio;
    private String errore;
    private String successo;
    private IDAOAccordoDiCollaborazione daoAccordoDiCollaborazione;
    private IDAOIstanzaAccordoDiCollaborazione daoIstanzaAccordoDiCollaborazione;
    private IDAOConfigurazione daoConfigurazione;
    private IDAOIstanzaPortType daoIstanzaPortType;
    private Utente utente;

    public String modificaIndirizzoInvio() {
        this.vista.setVisualizzaModalPanelURLInvio(true);
        this.vista.setIstanzaPortTypeModificare(getIstanzaPortTypeSelezionata());
        return null;
    }

    private IstanzaPortType getIstanzaPortTypeSelezionata() {
        IstanzaPortType istanzaPT = null;
        try {
            long idPortType = ((Long) this.vista.getIstanzaPortTypeSelezionato().getValue()).longValue();
            istanzaPT = daoIstanzaPortType.findById(idPortType, false);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Impossibile leggere l'istanza port type selezionata. " + ex);
            return null;
        }
        return istanzaPT;
    }

    public String salvaModalPanelURLInvio() {
        try {
            IstanzaPortType istanza = this.vista.getIstanzaPortTypeModificare();
            this.daoIstanzaPortType.makePersistent(istanza);
            this.vista.setVisualizzaModalPanelURLInvio(false);
            this.messaggio = "Indirizzo di invio modificato correttamente";
        } catch (DAOException ex) {
            logger.error("Impossibile modificare l'indirizzo di invio " + ex);
            this.errore = "Impossibile modificare l'indirizzo di invio " + ex;
        }
        return elenco();
    }

    public String chiudiModalPanelURLInvio() {
        this.vista.setVisualizzaModalPanelURLInvio(false);
        return null;
    }

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
        this.vista.setSecondoPasso(true);
        this.vista.setTerzoPasso(false);
        return "nuovaIstanzaOperation";
    }

    public String annulla() {
        pulisciVista();
        return "home";
    }

    private void pulisciVista() {
        this.vista.setStep(1);
        this.vista.setSecondoPasso(false);
        this.vista.setTerzoPasso(false);
        this.vista.setQuartoPasso(false);
        this.vista.setStepErogatore(1);
        this.vista.setSceltaRuolo("");
        this.vista.setAccordoSelezionato("");
        this.vista.setListaItemOperation(new ArrayList<SelectItem>());
        this.vista.setOperationSelezionate(new ArrayList<Operation>());
        this.vista.setOperationConfigurareInt(0);
        this.vista.setListaOperationSelezionate(new ArrayList<String>());
        this.vista.setElimina(false);
        this.vista.setVisualizzaXSD(false);
        this.vista.setIstanzaAccordo(creaNuovaIstanza());
        this.vista.setIstanzaOperation(new IstanzaOperation());
        this.vista.setIndirizzoMail("");
        this.vista.setOggettoMail("");
        this.vista.setElaboraRichiestaMail(false);
    }

    private IstanzaAccordoDiCollaborazione creaNuovaIstanza() {
        IstanzaAccordoDiCollaborazione istanzaAccordo = new IstanzaAccordoDiCollaborazione();
        String tempDir = System.getProperty("java.io.tmpdir") + File.separator;
        String cartellaFiles = "SILVIO" + File.separator + new Date().getTime() + File.separator;
        String cartellaTmp = tempDir + cartellaFiles;
        logger.info("cartellaTmp: " + cartellaTmp);
        istanzaAccordo.setCartellaFiles(cartellaTmp);
        return istanzaAccordo;
    }

    public String nuovaIstanza() {
        List<AccordoDiCollaborazione> listaAccordiCollaborazione;
        this.pulisciVista();
        try {
            listaAccordiCollaborazione = daoAccordoDiCollaborazione.findAll();
            if (listaAccordiCollaborazione.size() == 0) {
                logger.info("Nessun accordo di collaborazione trovato");
                this.errore = "Nessun accordo di collaborazione trovato. Per poter continuare è necessario caricare un accordo di collaborazione";
            } else {
                List<SelectItem> listaItem = new ArrayList<SelectItem>();
                listaItem.add(new SelectItem(""));
                for (AccordoDiCollaborazione accordoDiCollaborazione : listaAccordiCollaborazione) {
                    listaItem.add(new SelectItem(accordoDiCollaborazione.getNome()));
                }
                this.vista.setListaItem(listaItem);
            }
            this.vista.setSecondoPasso(false);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Errore nella lettura dell'elenco degli accordi di collaborazione " + ex);
            this.errore = "Errore nella lettura dal db";
        }
        return "nuovaIstanza";
    }

    public String configuraIstanza() {
        List<Operation> listaOperation = new ArrayList<Operation>();
        List<String> chiaviOperation = this.vista.getListaOperationSelezionate();
        Map<String, Operation> mappaOperation = this.vista.getMappaIdentificatore();
        for (String id : chiaviOperation) {
            Operation operation = mappaOperation.get(id);
            listaOperation.add(operation);
        }
        this.getVista().setOperationSelezionate(listaOperation);
        AccordoDiCollaborazione accordo = this.vista.getAccordoDiCollaborazione();
        this.getVista().getIstanzaAccordo().setAccordo(accordo);
        this.getVista().getIstanzaAccordo().setRuolo(this.vista.getSceltaRuolo());
        return prossimaOperation();
    }

    public String indietroConfiguraIstanza() {
        this.vista.setOperationConfigurareInt(0);
        this.vista.setVisualizzaXSD(false);
        return configuraIstanza();
    }

    public String accordoCambiato() {
        AccordoDiCollaborazione accordo;
        try {
            String nomeAccordo = this.vista.getAccordoSelezionato();
            accordo = daoAccordoDiCollaborazione.findByNome(nomeAccordo);
            if (accordo == null) {
                this.getVista().getMappaIdentificatore().clear();
                this.getVista().getListaItemOperation().clear();
                return null;
            }
            List<SelectItem> listaItemOperation = new ArrayList<SelectItem>();
            List<PortType> listaPortType = accordo.getListaPortType();
            this.getVista().getMappaIdentificatore().clear();
            for (PortType portType : listaPortType) {
                List<Operation> listaOperation = portType.getListaOperation();
                for (Operation operation : listaOperation) {
                    String stringaIdentificatore = portType.getNome() + "$" + operation.getNome();
                    this.getVista().getMappaIdentificatore().put(stringaIdentificatore, operation);
                }
            }
            Map<String, Operation> mappaOperation = this.vista.getMappaIdentificatore();
            Set<String> chiavi = mappaOperation.keySet();
            for (String idOperation : chiavi) {
                Operation operation = mappaOperation.get(idOperation);
                listaItemOperation.add(new SelectItem(idOperation, operation.getPortType().getNome() + " - " + operation.getNome()));
            }
            this.vista.setListaItemOperation(listaItemOperation);
            this.vista.setAccordoDiCollaborazione(accordo);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Errore nella lettura dell'elenco degli accordi di collaborazione " + ex);
            this.errore = "Errore nella lettura dal db";
        }
        logger.info("Accordo cambiato " + vista.getAccordoSelezionato());
        return null;
    }

    public String switchDB() {
        logger.info("Cambiato DB " + vista.getDatiPM().isDatiDBSelezionati());
        vista.getDatiPM().setDatiDBSelezionati(!vista.getDatiPM().isDatiDBSelezionati());
        return null;
    }

    public String switchInterattivi() {
        logger.info("Cambiato Interattivi " + vista.getDatiPM().isDatiInterattiviSelezionati());
        vista.getDatiPM().setDatiInterattiviSelezionati(!vista.getDatiPM().isDatiInterattiviSelezionati());
        return null;
    }

    public String switchCostanti() {
        logger.info("Cambiato Costanti " + vista.getDatiPM().isDatiCostantiSelezionati());
        vista.getDatiPM().setDatiCostantiSelezionati(!vista.getDatiPM().isDatiCostantiSelezionati());
        return null;
    }

    public String switchSLA() {
        logger.info("Cambiato SLA " + vista.isAggiornaSLA());
        vista.setAggiornaSLA(!vista.isAggiornaSLA());
        return null;
    }

    public String switchPayload() {
        logger.info("Cambiato Payload " + vista.getDatiPM().isDatiPayloadSelezionati());
        vista.getDatiPM().setDatiPayloadSelezionati(!vista.getDatiPM().isDatiPayloadSelezionati());
        if (vista.getDatiPM().isDatiPayloadSelezionati()) {
            vista.getDatiPM().setDatiCostantiSelezionati(false);
            vista.getDatiPM().setDatiInterattiviSelezionati(false);
            vista.getDatiPM().setDatiDBSelezionati(false);
        }
        return null;
    }

    public String unisciStringhe() {
        String select = this.vista.getDatiPM().getSelect();
        if (select.isEmpty()) {
            this.setErrore("Specificare la select da inserire");
            return null;
        }
        this.vista.getDatiPM().getListaSelect().add(select);
        this.vista.getDatiPM().setSelect(null);
        logger.info("Select inserita: " + select);
        return null;
    }

    public String confermaInformazioniDB() {
        DatiSQL datiSQL = this.vista.getDatiPM().getDatiDB();
        if (datiSQL.getSelect().getNomeUtente().isEmpty() || datiSQL.getSelect().getPassword().isEmpty() ||
                datiSQL.getSelect().getNomeDB().isEmpty() || datiSQL.getSelect().getTipoDB().isEmpty() ||
                datiSQL.getSelect().getIndirizzoDB().isEmpty()) {
            this.setErrore("E' indispensabile fornire tutte le informazioni sul DB");
            return null;
        }
        return null;
    }

    public String confermaPayloadCostante() {
        String payload = this.vista.getDatiPM().getPayloadCostante();
        if (payload == null || payload.isEmpty()) {
            this.setErrore("E' indispensabile specificare il payload");
        }
        return null;
    }

    public String confermaSbloccoAsincrono() {
        String indirizzo = this.vista.getIndirizzoAscoltoErogatoreAsincrono();
        if (indirizzo == null || indirizzo.isEmpty()) {
            this.setErrore("E' indispensabile specificare l'indirizzo di risposta dell'erogatore");
        }
        return null;
    }

    public String confermaSLA() {
        boolean aggiornaSLA = this.vista.isAggiornaSLA();
        String indirizzo = this.vista.getIndirizzoSLA();
        IstanzaOperation istanzaOperation = this.vista.getIstanzaOperation();
        if (aggiornaSLA && indirizzo == null || indirizzo.trim().isEmpty()) {
            this.setErrore("E' indispensabile specificare l'indirizzo del ws degli sla");
            logger.warn("E' indispensabile specificare l'indirizzo del ws degli sla");
            return null;
        }
            logger.warn("zzz");
        if (aggiornaSLA) {
            ParametriSLA parametriSLA = new ParametriSLA();
            parametriSLA.setIndirizzoWS(indirizzo);
            istanzaOperation.setParametriSLA(parametriSLA);
        } else {
            istanzaOperation.setParametriSLA(null);
        }
        return null;
    }

    public String confermaEliminazione() {
        logger.info("Entrato in conferma eliminazione");
        this.vista.setElimina(true);
        String select = (String) this.vista.getDatiPM().getListaSelectGrid().getRowData();
        logger.info("Valore della query da eliminare:" + select);
        this.vista.getDatiPM().setSelectEliminare(select);
        return null;
    }

    public String confermaEliminazioneDatoCostate() {
        this.vista.setEliminaDatoCostante(true);
        DatoPrimitivo datiPrimitivi = (DatoPrimitivo) this.vista.getDatiPM().getListaDatiPrimitiviCostantiGrid().getRowData();
        logger.info("dato costante: " + datiPrimitivi);
        this.vista.getDatiPM().setDatoCostanteEliminare(datiPrimitivi);
        return null;
    }

    public String confermaEliminazioneDatoInterattivo() {
        this.vista.setEliminaDatoInterattivo(true);
        logger.info("Lista dati primitivi grid" + this.vista.getDatiPM().getListaDatiPrimitiviGridFruitore());
        DatoPrimitivo datiPrimitivi = (DatoPrimitivo) this.vista.getDatiPM().getListaDatiPrimitiviGridFruitore().getRowData();
        this.vista.getDatiPM().setDatoInterattivoEliminare(datiPrimitivi);
        return null;
    }

    public String eliminaDatoCostante() {
        DatoPrimitivo datoEliminare = this.vista.getDatiPM().getDatoCostanteEliminare();
        this.vista.getDatiPM().getDatiCostanti().getDatiCostanti().remove(datoEliminare);
        this.successo = "Dato costante eliminato correttamente";
        this.vista.setEliminaDatoCostante(false);
        return "eliminatoCostante";
    }

    public String eliminaDatoInterattivo() {
        DatoPrimitivo datoInterattivo = this.vista.getDatiPM().getDatoInterattivoEliminare();
        this.vista.getDatiPM().getDatiInterattivi().getDatiInterattivi().remove(datoInterattivo);
        this.successo = "Dato interattivo eliminato correttamente";
        this.vista.setEliminaDatoInterattivo(false);
        return "eliminatoInterattivo";
    }

    public String eliminaSelect() {
        String select = this.vista.getDatiPM().getSelectEliminare();
        this.vista.getDatiPM().getListaSelect().remove(select);
        this.successo = "Query eliminata correttamente";
        this.vista.setElimina(false);
        return "eliminato";
    }

    public String aggiungiDatoPrimitivo() {
        DatoPrimitivo dato = this.vista.getDatiPM().getDatoPrimitivo();
        this.vista.getDatiPM().getDatiInterattivi().getDatiInterattivi().add(dato);
        dato.setDatiInterattivi(this.vista.getDatiPM().getDatiInterattivi());
        this.vista.getDatiPM().setDatoPrimitivo(new DatoPrimitivo());
        return null;
    }

    public String aggiungiDatoPrimitivoCostante() {
        DatoPrimitivo datoCostante = this.vista.getDatiPM().getDatoPrimitivoCostante();
        this.vista.getDatiPM().getDatiCostanti().getDatiCostanti().add(datoCostante);
        datoCostante.setDatiCostanti(this.vista.getDatiPM().getDatiCostanti());
        logger.info("Dato inserito nella lista" + datoCostante.getNome() + " - " + datoCostante.getValore());
        this.vista.getDatiPM().setDatoPrimitivoCostante(new DatoPrimitivo());
        return null;
    }

    public String chiudiModalPanel() {
        this.vista.setElimina(false);
        this.vista.setVisualizzaXSD(false);
        return null;
    }

    public String salva() {
        try {
            IstanzaAccordoDiCollaborazione istanzaAccordo = this.vista.getIstanzaAccordo();
            IstanzaAccordoDiCollaborazione istanzaAccordoEsistente = daoIstanzaAccordoDiCollaborazione.findByNome(istanzaAccordo.getNome());
            if (istanzaAccordoEsistente != null) {
                this.errore = "Errore nel salvataggio dell'istanza. Esiste un'istanza con lo stesso nome";
                return null;
            }
            Configurazione conf = daoConfigurazione.caricaConfigurazione();
            daoIstanzaAccordoDiCollaborazione.makePersistent(istanzaAccordo);

            String sourceDir = istanzaAccordo.getCartellaFiles();
            String cartellaAccordo = istanzaAccordo.getAccordo().getCartellaFiles();
            String nuovaDir = cartellaAccordo + File.separator + IstanzaAccordoDiCollaborazione.CARTELLA_FILE + File.separatorChar + istanzaAccordo.getId() + "-" + istanzaAccordo.getNome() + File.separator;
            String targetDir = conf.getDirConfig() + File.separator + nuovaDir;
            istanzaAccordo.setCartellaFiles(nuovaDir);

            daoIstanzaAccordoDiCollaborazione.makePersistent(istanzaAccordo);
            logger.info("Devo copiare il contenuto della cartella \n\t" + sourceDir + " nella cartella \n\t" + targetDir);
            File srcDir = new File(sourceDir);
            File destDir = new File(targetDir);
            FileUtil.copyDir(srcDir, destDir);

            SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
            sessionFactory.getCurrentSession().getTransaction().commit();
            sessionFactory.getCurrentSession().beginTransaction();

            WSIstanzaImplService service = new WSIstanzaImplService();
            logger.info("URL WSDL" + service.WSDL_LOCATION);
            IWSIstanza istanza = service.getWSIstanzaImplPort();
            logger.info("ID istanza = " + istanzaAccordo.getId());
            istanza.avviaIstanza(new Long(istanzaAccordo.getId()));

            this.setSuccesso("Istanza dell'accordo di collaborazione creato correttamente.");
        } catch (DAOException ex) {
            ex.printStackTrace();
            logger.error("Errore nel salvataggio dell'istanza dell'accordo di collaborazione " + ex);
            this.errore = "Errore nel salvataggio dell'istanza dell'accordo di collaborazione. " + ex;
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Errore nel salvataggio dell'istanza dell'accordo di collaborazione " + ex);
            this.errore = "Errore nel salvataggio dell'istanza dell'accordo di collaborazione. " + ex;
            return null;
        }
        return elenco();
    }

    public String eliminaIstanza() {
        IstanzaAccordoDiCollaborazione istanzaAccoro = this.vista.getIstanzaAccordoEliminare();
        try {
            IstanzaAccordoDiCollaborazione istanzaEliminare = daoIstanzaAccordoDiCollaborazione.findByNome(istanzaAccoro.getNome());
            String dirconfig = daoConfigurazione.caricaConfigurazione().getDirConfig();
            if (istanzaEliminare.getCartellaFiles() != null) {
                String cartellaEliminare = dirconfig + File.separator + istanzaEliminare.getCartellaFiles();
                if (FileUtil.deleteFolder(cartellaEliminare)) {
                    logger.info("La cartella " + cartellaEliminare + " e' stata cancellata con successo.");
                } else {
                    logger.info("Non è stato possibile cancellare la cartella " + cartellaEliminare + ". Eliminarla manualmente.");
                }
            }
            this.daoIstanzaAccordoDiCollaborazione.makeTransient(istanzaEliminare);
        } catch (DAOException ex) {
            logger.error("Impossibile eliminare l'istanza dell'accordo di servizio. " + ex);
            String erroreFormattato = ex.getMessage().substring(ex.getMessage().indexOf(":") + 1);
            this.setErrore(erroreFormattato);
            vista.setElimina(false);
            return null;
        }
        this.successo = "Istanza Accordo di collaborazione eliminato correttamente";
        return elenco();
    }

    public String confermaEliminazioneIstanza() {
        this.vista.setElimina(true);
        IstanzaAccordoDiCollaborazionePM istanzaPM = (IstanzaAccordoDiCollaborazionePM) this.vista.getListaIstanzeAccordiCollaborazioneGrid().getRowData();
        IstanzaAccordoDiCollaborazione istanza = istanzaPM.getIstanzaAS();
        this.vista.setIstanzaAccordoEliminare(istanza);
        return null;
    }

    public String elenco() {
        List<IstanzaAccordoDiCollaborazione> listaIstanze;
        try {
            String porta = daoConfigurazione.caricaConfigurazione().getPorta();
            listaIstanze = daoIstanzaAccordoDiCollaborazione.findAll();
            if (listaIstanze.size() == 0) {
                logger.info("Nessuna istanza trovata");
                this.successo = "Nessuna istanza trovata";
            }
            logger.info("Trovate " + listaIstanze.size() + " istanze accordo");
            vista.setListaIstanze(listaIstanze);
            vista.setElimina(false);
            List<IstanzaAccordoDiCollaborazionePM> listaIstanzaAccordiCollaborazionePM = new ArrayList<IstanzaAccordoDiCollaborazionePM>();
            for (IstanzaAccordoDiCollaborazione istanzaAccordoDiCollaborazione : listaIstanze) {
                listaIstanzaAccordiCollaborazionePM.add(new IstanzaAccordoDiCollaborazionePM(istanzaAccordoDiCollaborazione, porta));
            }
            vista.setListaIstanzePM(listaIstanzaAccordiCollaborazionePM);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Errore nella lettura dell'elenco delle istanze degli accordi di collaborazione " + ex);
            this.errore = "Errore nella lettura dal db";
        }
        logger.info("elencoIstanze");
        return "elencoIstanze";
    }

    public IDAOAccordoDiCollaborazione getDaoAccordoDiCollaborazione() {
        return daoAccordoDiCollaborazione;
    }

    public void setDaoAccordoDiCollaborazione(IDAOAccordoDiCollaborazione daoAccordoDiCollaborazione) {
        this.daoAccordoDiCollaborazione = daoAccordoDiCollaborazione;
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

    public String getErrore() {
        return errore;
    }

    public void setErrore(String errore) {
        this.errore = errore;
    }

    public Log getLogger() {
        return logger;
    }

    public void setLogger(Log logger) {
        this.logger = logger;
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

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public VistaIstanzeAccordi getVista() {
        return vista;
    }

    public void setVista(VistaIstanzeAccordi vista) {
        this.vista = vista;
    }

    public IDAOIstanzaPortType getDaoIstanzaPortType() {
        return daoIstanzaPortType;
    }

    public void setDaoIstanzaPortType(IDAOIstanzaPortType daoIstanzaPortType) {
        this.daoIstanzaPortType = daoIstanzaPortType;
    }
}
