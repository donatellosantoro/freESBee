package it.unibas.icar.updatefreesbee.persistenza;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoadConfig {

    private static Log logger = LogFactory.getLog(LoadConfig.class);
    private static LoadConfig singleton = new LoadConfig();
    private List<File> listaFreesbeeWar;

    private LoadConfig() {
    }

    public static LoadConfig getInstance() {
        return singleton;
    }

    public List<File> getListaFreesbeeWar() {
        return listaFreesbeeWar;
    }

    public void loadConfig(String webappDir, String tempDir, String cartellaFileConfig, boolean salvaConfig) throws PersistenceException {
        // cerchiamo prima i tutti i file war.
        File webappFolder = new File(webappDir);
        File[] fileList = webappFolder.listFiles();
        List<File> listaWar = new ArrayList<File>();
        listaFreesbeeWar = new ArrayList<File>();
        for (File file : fileList) {
            if (!file.isDirectory()) {
                if (file.getName().endsWith(".war") || file.getName().endsWith(".WAR")) {
                    listaWar.add(file);
                }
            }
        }
        // controlliamo se hanno in META-INF il file freesbee.xml
        for (File file : listaWar) {
            JarFile war;
            try {
                war = new JarFile(file);
                //JarEntry freesbeeXml = war.getJarEntry("META-INF" + File.separator + "freesbee.xml");
                JarEntry freesbeeXml = war.getJarEntry("META-INF/freesbee.xml");
                if (freesbeeXml != null) {
                    listaFreesbeeWar.add(file);
                }
            } catch (IOException ex) {
                logger.error("Eccezione nel caricamento del file war: " + ex);
                throw new PersistenceException("Eccezione nel caricamento del file war: " + ex);
            }
        }
        for (File file : listaFreesbeeWar) {
            String name = estraiNome(file.getAbsolutePath());
            File tempFolder = new File(tempDir + File.separator + name);
            BufferedInputStream inputStream = null;
            try {
                inputStream = new BufferedInputStream(new FileInputStream(file));
                JarUtils.unjar(inputStream, tempFolder);
                File freesbeeProperties = new File(tempFolder + File.separator + "WEB-INF"
                        + File.separator + "classes"
                        + File.separator + "freesbee.properties");
                File hibernateProperties = new File(tempFolder + File.separator + "WEB-INF"
                        + File.separator + "classes"
                        + File.separator + "hibernate.properties");
                File log4jProperties = new File(tempFolder + File.separator + "WEB-INF"
                        + File.separator + "classes"
                        + File.separator + "log4j.properties");
                FileUtils.copyFile(freesbeeProperties, new File(tempFolder, "freesbee.properties"));
                FileUtils.copyFile(hibernateProperties, new File(tempFolder, "hibernate.properties"));
                FileUtils.copyFile(log4jProperties, new File(tempFolder, "log4j.properties"));
                if (salvaConfig) {
                    File cartellaConfig = new File(cartellaFileConfig + File.separator + name);
                    FileUtils.copyFileToDirectory(freesbeeProperties, cartellaConfig);
                    FileUtils.copyFileToDirectory(hibernateProperties, cartellaConfig);
                    FileUtils.copyFileToDirectory(log4jProperties, cartellaConfig);
                }
                FileUtils.deleteDirectory(new File(tempFolder, File.separator + "WEB-INF"));
                FileUtils.deleteDirectory(new File(tempFolder, File.separator + "META-INF"));
            } catch (FileNotFoundException fnfe) {
                logger.error("File not found: " + fnfe);
                throw new PersistenceException("File non trovato: " + fnfe);
            } catch (IOException ioe) {
                logger.error("Errore con l'IO: " + ioe);
                throw new PersistenceException("File non trovato: " + ioe);
            } finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException ioe) {
                    logger.error("Impossibile chiudere il file. " + ioe);
                    throw new PersistenceException("Errore nella chiusura del file: " + ioe);
                }
            }
            modificaFreesbeeProperties(tempFolder.getAbsolutePath());
            modificaHibernateProperties(tempFolder.getAbsolutePath());
            modificaLog4jProperties(tempFolder.getAbsolutePath());
        }
    }

    private String estraiNome(String name) {
        int indice = name.lastIndexOf(File.separator);
        return name.substring(indice + 1, name.length() - 4);
    }

    private void modificaFreesbeeProperties(String tempFileFolder) throws PersistenceException {
        try {
            InputStream inputStreamOld = new FileInputStream(tempFileFolder + File.separator + "freesbee.properties");
            Properties oldProperties = new Properties();
            oldProperties.load(inputStreamOld);
            if (inputStreamOld != null) {
                inputStreamOld.close();
            }
            File fileProperties = new File("./varie/risorse/templateFolder/WEB-INF/classes/freesbee.properties");
            InputStream inputStreamNew = new FileInputStream(fileProperties);
            Properties newProperties = new Properties();
            newProperties.load(inputStreamNew);
            if (inputStreamNew != null) {
                inputStreamNew.close();
            }
            mergeProperties(oldProperties, newProperties);
            FileOutputStream fos = new FileOutputStream(tempFileFolder + File.separator + "freesbee.properties");
            newProperties.store(fos, null);
        } catch (IOException ex) {
            logger.error("Errore nel caricamento del file freesbee.properties: " + ex);
            throw new PersistenceException("Errore nel caricamento del file freesbee.properties: " + ex);
        }
        modifica(tempFileFolder + File.separator + "freesbee.properties");
    }

    private void mergeProperties(Properties oldProperties, Properties newProperties) {
        if (logger.isDebugEnabled()) {
            System.out.println("OLD PROPERTIES");
            oldProperties.list(System.out);
            System.out.println("");
            System.out.println("NEW PROPERTIES");
            newProperties.list(System.out);
            System.out.println("");
        }
        for (String name : newProperties.stringPropertyNames()) {
            String oldValue = oldProperties.getProperty(name);
            String newValue = newProperties.getProperty(name);
            if (oldValue != null && !oldValue.equals(newValue)) {
                //Forzare il cambiamento di valori errati
                if (name.equals("webservices.interoperabilita.port")
//                        || name.equals("interoperabilita.registro.freesbee")
//                        || name.equals("interoperabilita.registro.nica")
                        || name.equals("camel.thread.pool")
                        || name.equals("camel.thread.pool.max")
                        || name.equals("thread.jetty.pool.min")
                        || name.equals("thread.jetty.pool.max")) {
                    System.out.println("Annullo le modifiche sulla proprieta' " + name + ". Utilizzo " + newValue + " invece di " + oldValue);
                    continue;
                }
                if (name.equals("webservices.indirizzo") && oldValue.equals("localhost")) {
                    System.out.println("Forzo il cambiamento della proprieta' " + name + " utilizzando il valore " + newValue + " invece di " + oldValue);
                    continue;
                }
                if (logger.isDebugEnabled()) logger.debug("Per la proprieta' " + name + " utilizzo il vecchio valore " + oldValue);
                newProperties.setProperty(name, oldValue);
            } else {
                if (logger.isDebugEnabled()) logger.debug("Non esiste la proprieta' " + name + " utilizzo il nuovo valore " + newValue);
            }
        }
        if (logger.isDebugEnabled()) {
            System.out.println("MERGED PROPERTIES");
            newProperties.list(System.out);
            System.out.println("");
        }
    }

    private void modifica(String fileName) throws PersistenceException {
        // rimuovere i caratteri \\ dai percorsi
        BufferedReader flusso = null;
        StringBuilder buffer = new StringBuilder();
        try {
            FileReader fileReader = new FileReader(fileName);
            flusso = new BufferedReader(fileReader);
            String s = null;
            while ((s = flusso.readLine()) != null) {
                if (s.contains("\\:")) {
                    s = s.replace("\\:", ":");
                }
                buffer.append(s).append("\n");
            }
        } catch (FileNotFoundException fnfe) {
            logger.error("File not found: " + fnfe);
            throw new PersistenceException("File non trovato: " + fnfe);
        } catch (IOException ioe) {
            logger.error("Errore io: " + ioe);
            throw new PersistenceException("Errore: " + ioe);
        } finally {
            try {
                if (flusso != null) {
                    flusso.close();
                }
            } catch (IOException ioe) {
                logger.error("Errore io nella chiusura del flusso: " + ioe);
                throw new PersistenceException("Errore: " + ioe);
            }
        }
        PrintWriter printWriter = null;
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            printWriter = new PrintWriter(fileWriter);
            printWriter.print(buffer.toString());
        } catch (IOException ioe) {
            logger.error("Errore io: " + ioe);
            throw new PersistenceException("Errore: " + ioe);
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }

    }

    private void modificaHibernateProperties(String tempFileFolder) throws PersistenceException {
        modifica(tempFileFolder + File.separator + "hibernate.properties");
    }

    private void modificaLog4jProperties(String tempFileFolder) throws PersistenceException {
        modifica(tempFileFolder + File.separator + "log4j.properties");
    }
}
