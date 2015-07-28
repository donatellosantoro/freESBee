package it.unibas.icar.updatefreesbee.persistenza;

import it.unibas.icar.updatefreesbee.Costanti;
import it.unibas.icar.updatefreesbee.modello.Updater;
import java.io.*;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UpdateWar {

    private static Log logger = LogFactory.getLog(UpdateWar.class);

    public void update(String webappDir, String tempDir, Updater updater) throws PersistenceException {
        File tempFolder = new File(tempDir);
        File[] listFiles = tempFolder.listFiles();
        String templateFolder = "varie" + File.separator
                + "risorse" + File.separator
                + "templateFolder" + File.separator;
        File fileTemplateFolder = new File(templateFolder);
        File tempFolderFreesbee = new File(webappDir + File.separator + "tempFolderFreesbee");
        try {
            FileUtils.copyDirectory(fileTemplateFolder, tempFolderFreesbee);
        } catch (IOException ex) {
            logger.error("Non riesco a copiare la cartella: " + fileTemplateFolder.getAbsolutePath() + " in " + tempFolderFreesbee.getAbsolutePath());
            throw new PersistenceException("Non riesco a copiare la cartella: " + fileTemplateFolder.getAbsolutePath() + " Eccezione: " + ex);
        }
        File webInfClasses = new File(tempFolderFreesbee, "WEB-INF" + File.separator + "classes");
        for (File file : listFiles) {
            int indice = file.getPath().lastIndexOf(File.separator);
            String warName = file.getPath().substring(indice + 1, file.getPath().length()).trim();
            if (updater != null) {
                updater.setStato(Costanti.STATO_AGGIORNAMENTO_WAR + warName);
            }
            //logger.info("War Name: " + warName);
            // TODO copiare i file di properties nella template
            if (file.isDirectory()) {
                for (File properties : file.listFiles()) {
                    if (properties.isFile()) {
                        try {
                            FileUtils.copyFileToDirectory(properties, webInfClasses);
                        } catch (IOException ex) {
                            logger.error("Non riesco a copiare un file di properties: " + ex);
                            throw new PersistenceException("Non riesco a copiare il file di properties nella cartella temporanea: " + properties.getAbsolutePath() + " Ex:" + ex);
                        }
                    }
                }
            }
            BufferedOutputStream outputStream = null;
            try {
                outputStream = new BufferedOutputStream(new FileOutputStream(webappDir + File.separator + warName + ".war"));
                JarUtils.jar(outputStream, tempFolderFreesbee.listFiles());

            } catch (FileNotFoundException fnfe) {
                logger.error("File Not Found: " + fnfe);
                throw new PersistenceException("File non trovato: " + fnfe);
            } catch (IOException ioe) {
                logger.error("Errore con l'IO: " + ioe);
                throw new PersistenceException("Eccezione: " + ioe);
            } finally {
                try {
                    if (outputStream != null) {
                        outputStream.close();
                    }
                } catch (IOException ioe) {
                    logger.error("Eccezione nella chiusura del file: " + ioe);
                    throw new PersistenceException("Eccezione: " + ioe);
                }
            }
            if (updater != null) {
                //updater.incrementaPercentuale();
                updater.setStato(Costanti.STATO_AGGIORNAMENTO_WAR_COMPLETATO + warName);
            }
        }
        try {
            FileUtils.deleteDirectory(tempFolderFreesbee);
        } catch (IOException ex) {
            logger.error("Unable to delete: " + tempFolderFreesbee + " Ex: " + ex);
            throw new PersistenceException("Eccezione nel cancellare la cartella temporanea: " + ex);
        }

    }
}