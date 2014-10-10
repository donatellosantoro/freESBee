package it.unibas.silvio.web.controllo;

import it.unibas.silvio.modello.AccordoDiCollaborazione;
import it.unibas.silvio.modello.Configurazione;
import it.unibas.silvio.modello.Operation;
import it.unibas.silvio.persistenza.DAOException;
import it.unibas.silvio.persistenza.IDAOAccordoDiCollaborazione;
import it.unibas.silvio.persistenza.IDAOConfigurazione;
import it.unibas.silvio.persistenza.SilvioException;
import it.unibas.silvio.web.util.FileUtil;
import it.unibas.silvio.web.util.SilvioWebUtil;
import it.unibas.silvio.web.vista.VistaAccordoCollaborazione;
import it.unibas.silvio.web.vista.pm.AccordoDiCollaborazionePM;
import it.unibas.silvio.xml.XmlException;
import it.unibas.silvio.xml.XmlJDomUtil;
import it.unibas.silvio.xml.XmlUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.jdom.Document;
import org.jdom.Element;

public class ControlloAccordoCollaborazione {

    private Log logger = LogFactory.getLog(this.getClass());
    private VistaAccordoCollaborazione vista;
    private String messaggio;
    private String errore;
    private String successo;
    private IDAOAccordoDiCollaborazione daoAccordoDiCollaborazione;
    private IDAOConfigurazione daoConfigurazione;

    public String elenco() {
        List<AccordoDiCollaborazione> listaAccordiCollaborazione;
        try {
            listaAccordiCollaborazione = daoAccordoDiCollaborazione.findAll();
            vista.setListaAccordiCollaborazione(listaAccordiCollaborazione);
            vista.setElimina(false);
            List<AccordoDiCollaborazionePM> listaAccordiCollaborazionePM = new ArrayList<AccordoDiCollaborazionePM>();
            for (AccordoDiCollaborazione accordoDiCollaborazione : listaAccordiCollaborazione) {
                listaAccordiCollaborazionePM.add(new AccordoDiCollaborazionePM(accordoDiCollaborazione));
            }
            vista.setListaAccordiDiCollaborazionePM(listaAccordiCollaborazionePM);
            if (listaAccordiCollaborazione.size() == 0) {
                logger.info("Nessun accordo di collaborazione trovato");
                this.successo = "Nessun accordo di collaborazione trovato";
            }
            pulisciVista();
            return "elencoAccordi";
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Errore nella lettura dell'elenco degli accordi di collaborazione " + ex);
            this.errore = "Errore nella lettura dal db";
        }
        return null;
    }

    public String nuovoAccordo() {
        this.vista.setStep(1);
        this.vista.setSceltaInput(null);
        this.vista.setSecondoPasso(false);
        return "nuovoAccordo";
    }

    public String sceltaTipo() {
        this.vista.setStep(2);
        this.vista.setSecondoPasso(true);
        this.vista.setTerzoPasso(false);
        return null;
    }

    public String salva() {
        AccordoDiCollaborazione accordo = this.getVista().getAccordoDiCollaborazione();
        try {
            AccordoDiCollaborazione accordoEsistente = daoAccordoDiCollaborazione.findByNome(accordo.getNome());
            if (accordoEsistente != null) {
                this.errore = "Errore nel salvataggio dell'accordo. Esiste un accordo con lo stesso nome";
                return null;
            }
            Configurazione conf = daoConfigurazione.caricaConfigurazione();
            daoAccordoDiCollaborazione.makePersistent(accordo);
            String sourceDir = this.vista.getCartellaTmp() + accordo.getCartellaFiles();
            String nuovaDir = accordo.getId() + "-" + accordo.getNome();
            String targetDir = conf.getDirConfig() + nuovaDir + File.separator + AccordoDiCollaborazione.CARTELLA_FILE + File.separatorChar;
            accordo.setCartellaFiles(nuovaDir);
            daoAccordoDiCollaborazione.makePersistent(accordo);
            logger.info("Devo copiare il contenuto della cartella \n\t" + sourceDir + " nella cartella \n\t" + targetDir);
            File srcDir = new File(sourceDir);
            File destDir = new File(targetDir);
            FileUtil.copyDir(srcDir, destDir);
            logger.info("Salvato l'accordo di collaborazione: " + accordo.getNome());
            this.setSuccesso("Accordo di Collaborazione creato correttamente!");
            return elenco();
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Errore nel salvataggio dell'accordo di collaborazione " + ex);
            this.errore = "Errore nel salvataggio dell'accordo di collaborazione";
        }
        return null;
    }

    public String caricaFile() {
        AccordoDiCollaborazione accordo = new AccordoDiCollaborazione();
        UploadedFile fileWsdlErogatore = this.vista.getFileWsdlErogatore();
        UploadedFile fileWSDLFruitore = this.vista.getFileWsdlFruitore();
        UploadedFile fileTypes = this.vista.getFileTypes();
        UploadedFile fileAs = this.vista.getFileAS();
        String tempDir = System.getProperty("java.io.tmpdir") + File.separator;
        String cartellaFiles = "SILVIO" + File.separator + new Date().getTime() + File.separator;
        String cartellaTmp = tempDir + cartellaFiles;
        String tipoAccordo = this.vista.getSceltaInput();
        try {
            if (tipoAccordo.equals(VistaAccordoCollaborazione.oneWSDL)) {
                String stringaFileErogatore = SilvioWebUtil.uploadFile(fileWsdlErogatore, cartellaTmp);
                String stringaFileTypes = SilvioWebUtil.uploadFile(fileTypes, cartellaTmp);
                accordo.setWSDLErogatore(stringaFileErogatore);
                accordo.setFileTypes(stringaFileTypes);
            }
            if (tipoAccordo.equals(VistaAccordoCollaborazione.twoWSDL)) {
                String stringaFileErogatore = SilvioWebUtil.uploadFile(fileWsdlErogatore, cartellaTmp);
                String stringaFileFruitore = SilvioWebUtil.uploadFile(fileWSDLFruitore, cartellaTmp);
                String stringaFileTypes = SilvioWebUtil.uploadFile(fileTypes, cartellaTmp);
                accordo.setWSDLErogatore(stringaFileErogatore);
                accordo.setWSDLFruitore(stringaFileFruitore);
                accordo.setFileTypes(stringaFileTypes);
            }
            if (tipoAccordo.equals(VistaAccordoCollaborazione.accordoServizio)) {
                String stringaFileAccordoServizio = SilvioWebUtil.uploadFile(fileAs, cartellaTmp);
                List<String> fileEstratti = SilvioWebUtil.estraiZip(cartellaTmp, stringaFileAccordoServizio);
                accordo.setCartellaFiles(cartellaTmp);
                Document manifest = null;
                Document informazioniEGov = null;
                String profilo = "";
                String cartellaEstrazione = stringaFileAccordoServizio.substring(0, stringaFileAccordoServizio.length() - 4) + File.separator;
                File directory = new File(cartellaTmp + File.separator + cartellaEstrazione);
                List<String> listaPath = new ArrayList<String>();
                this.caricaPathFile(directory, cartellaTmp, listaPath);
                for (String entry : fileEstratti) {
                    if ((entry.contains("Comune") || entry.contains("comune")) && entry.contains("manifest.xml")) {
                        manifest = XmlJDomUtil.caricaXML(cartellaTmp + cartellaEstrazione + entry, false);
                        logger.info("manifest.xml TROVATO");
                    }
                    if ((entry.contains("Comune") || entry.contains("comune")) && entry.contains("informazioniEGov.xml")) {
                        informazioniEGov = XmlJDomUtil.caricaXML(cartellaTmp + cartellaEstrazione + entry, false);
                        logger.info("informazioniEGov.xml TROVATO");
                    }
                    if ((entry.contains("profil") || entry.contains("Profil")) && (entry.contains("collaborazione") || entry.contains("Collaborazione"))) {
                        logger.info("FILE profili di collaborazione trovato " + entry);
                        this.caricaProfiliDaFile(entry, accordo);
                    }
                }

                if (informazioniEGov != null) {
                    profilo = this.caricaProfilo(informazioniEGov);
                } else {
                    for (String entry : fileEstratti) {
                        if (entry.contains("informazioniEGov.xml")) {
                            informazioniEGov = XmlJDomUtil.caricaXML(cartellaTmp + entry, false);
                            logger.info("informazioniEGov.xml TROVATO");
                        }
                    }
                    if (informazioniEGov != null) {
                        profilo = this.caricaProfilo(informazioniEGov);
                    }
                }
                if (manifest != null) {
                    this.setFromManifest(manifest, listaPath, accordo, profilo);
                } else {
                    logger.info("MANIFEST NULLO");
                    this.setFromList(listaPath, accordo);
                }
            }
            WSDLProcessor.elabora(accordo, tempDir + cartellaFiles);
            accordo.setCartellaFiles(cartellaFiles);
            this.vista.setAccordoDiCollaborazione(accordo);
            this.vista.setCartellaTmp(tempDir);
        } catch (Exception ex) {
            if (logger.isInfoEnabled()) {
                ex.printStackTrace();
            }
            logger.error("Errore nel caricamento dei file wsdl. " + ex);
            this.setErrore("Errore nel caricamento dei file wsdl. " + ex);
            return null;
        }
        logger.info(accordo);
        this.vista.setStep(3);
        this.vista.setTerzoPasso(true);
        return null;
    }

    public String eliminaAccordo() {
        AccordoDiCollaborazione accordo = vista.getAccordoDiCollaborazioneEliminare();
        try {
            AccordoDiCollaborazione accordoEliminare =daoAccordoDiCollaborazione.findByNome(accordo.getNome());
            String dirconfig = daoConfigurazione.caricaConfigurazione().getDirConfig();
            if (accordoEliminare.getCartellaFiles() != null) {
                String cartellaEliminare = dirconfig + File.separator + accordoEliminare.getCartellaFiles();
                if (FileUtil.deleteFolder(cartellaEliminare)) {
                    logger.info("La cartella " + cartellaEliminare + " e' stata cancellata con successo.");
                } else {
                    logger.info("Non è stato possibile cancellare la cartella " + cartellaEliminare + ". Eliminarla manualmente.");
                }
            }
            this.daoAccordoDiCollaborazione.makeTransient(accordoEliminare);
            vista.setAccordoDiCollaborazioneEliminare(null);
            this.successo = "Accordo di collaborazione eliminato correttamente";
            vista.setElimina(false);
            return elenco();
        } catch (DAOException ex) {
            logger.error("Impossibile eliminare l'accordo di servizio. " + ex);
            String erroreFormattato = ex.getMessage().substring(ex.getMessage().indexOf(":") + 1);
            this.setErrore(erroreFormattato);
            vista.setElimina(false);
            return null;
        }
    }

    public String confermaEliminazione() {
        this.vista.setElimina(true);
        AccordoDiCollaborazionePM accordoPM = (AccordoDiCollaborazionePM) this.getVista().getListaAccordiCollaborazioneGrid().getRowData();
        AccordoDiCollaborazione accordo = accordoPM.getAccordo();
        this.vista.setAccordoDiCollaborazioneEliminare(accordo);
        return null;
    }

    private void caricaPathFile(File file, String cartellaTmp, List<String> listaPath) {
        for (File f : file.listFiles()) {
            if (f.isDirectory()) {
                caricaPathFile(new File(f.getPath()), cartellaTmp, listaPath);
            } else {
                listaPath.add(f.getPath().substring(cartellaTmp.length()));
            }
        }
    }

    public String annulla() {
        pulisciVista();
        return "home";
    }

    private void pulisciVista() {
        logger.info("Pulisci vista");
        this.vista.setSecondoPasso(false);
        this.vista.setTerzoPasso(false);
    }

    private void setFromManifest(Document manifest, List<String> listaPath, AccordoDiCollaborazione accordo, String profilo) throws SilvioException {

        try {
            Element element = manifest.getRootElement();
            logger.info("ELEMENT ROOT calcolato");
            Element specificaInterfaccia = element.getChild("specifica-interfaccia", element.getNamespace());
            logger.info("ELEMENT SPECIFICA-INTERFACCIA calcolato " + specificaInterfaccia.getName());
            String logicoFruitore = specificaInterfaccia.getAttributeValue("wsdl-logico-fruitore");
            logicoFruitore = logicoFruitore.replace("/", File.separator);
            String logicoErogatore = specificaInterfaccia.getAttributeValue("wsdl-logico-erogatore");
            logicoErogatore = logicoErogatore.replace("/", File.separator);
            for (String path : listaPath) {
                if (path.contains(logicoFruitore) && !profilo.equals("sincrono")) {
                    accordo.setWSDLFruitore(path);
                    logger.info("WSDL Logico Fruitore Settato");
                }
                if (path.contains(logicoErogatore)) {
                    accordo.setWSDLErogatore(path);
                    logger.info("WSDL Logico Erogatore Settato");
                }

            }
        } catch (Exception e) {
            logger.error("ERRORE! " + e);
            throw new SilvioException(e);
        }
    }

    private void setFromList(List<String> listaPath, AccordoDiCollaborazione accordo) throws SilvioException {
        try {
            for (String path : listaPath) {
                if (path.contains("Erogatore") && path.contains("Logico")) {
                    accordo.setWSDLErogatore(path);
                } else if (path.contains("Fruitore") && path.contains("Logico")) {
                    accordo.setWSDLFruitore(path);
                } else if (path.contains("Types") && path.contains("Comune")) {
                    accordo.setCartellaFiles(path);
                }
            }
            String wsdlErogatore = accordo.getWSDLErogatore();
        } catch (Exception e) {
            throw new SilvioException(e);
        }
    }

    private String caricaProfilo(Document informazioniEGov) throws SilvioException {
        String profilo;
        try {
            Element element = informazioniEGov.getRootElement();
            profilo = element.getAttributeValue("profilo-collaborazione");
            return profilo;
        } catch (Exception e) {
            logger.error("ERRORE! Impossibile caricare il profilo di collaborazione \n" + e);
            throw new SilvioException(e);
        }
    }

    private void caricaProfiliDaFile(String path, AccordoDiCollaborazione accordo) {
        try {
            path = accordo.getCartellaFiles() + File.separator + path.replace("/", File.separator);
            Document document = XmlJDomUtil.caricaXML(path, false);
            Element elementoRadice = document.getRootElement();
            org.jdom.Namespace ns = document.getRootElement().getNamespace();
            Element elemento = elementoRadice.getChild("operationList");
            List children = elemento.getChildren();
            for (Object obj : children) {
                Element operazione = (Element) obj;
                String nomeOperazione = XmlUtil.eliminaPrefissoNS(operazione.getAttributeValue("operazione"));
                String profilo = operazione.getAttributeValue("profiloDiCollaborazione");
                if (profilo.startsWith("Asincrono")) {
                    profilo = Operation.ASINCRONO;
                } else if (profilo.startsWith("Sincrono")) {
                    profilo = Operation.SINCRONO;
                } else if (profilo.startsWith("OneWay")) {
                    profilo = Operation.ONE_WAY;
                }
                accordo.getProfiloOperation().put(nomeOperazione.toLowerCase(), profilo);

            }
        } catch (XmlException ex) {
            logger.error("ERRORE! Impossibile caricare il file " + ex);
        }
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

    public VistaAccordoCollaborazione getVista() {
        return vista;
    }

    public void setVista(VistaAccordoCollaborazione vista) {
        this.vista = vista;
    }

    public String getSuccesso() {
        return successo;
    }

    public void setSuccesso(String successo) {
        this.successo = successo;
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
}
