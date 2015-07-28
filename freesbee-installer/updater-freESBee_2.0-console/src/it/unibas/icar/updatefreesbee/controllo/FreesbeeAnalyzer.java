package it.unibas.icar.updatefreesbee.controllo;

import it.unibas.icar.updatefreesbee.Costanti;
import it.unibas.icar.updatefreesbee.persistenza.PersistenceException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FreesbeeAnalyzer {

    private static Log logger = LogFactory.getLog(FreesbeeAnalyzer.class.getName());

    public static boolean isFreesbee(File file) {
        if (file.isDirectory()) {
            return isFreesbeeFolder(file);
        } else if (file.getName().toLowerCase().endsWith(Costanti.WAR)) {
            return isFreesbeeWar(file);
        }
        return false;
    }

    public static String getFreesbeeVersion(File file) {
        if (!isFreesbee(file)) {
            throw new IllegalArgumentException("Il percorso " + file + " non e' una versione di freESBee valida");
        }
        if (file.isDirectory()) {
            return getFreesbeeVersionFolder(file);
        } else if (file.getName().toLowerCase().endsWith(Costanti.WAR)) {
            return getFreesbeeVersionWar(file);
        }
        return Costanti.UNVERSIONED;
    }

    private static boolean isFreesbeeFolder(File file) {
        String fileMetaInf = file.toString() + File.separator + Costanti.FREESBEE_META_INF;
        File fileToCheck = new File(fileMetaInf);
        return fileToCheck.exists();
    }

    private static boolean isFreesbeeWar(File file) {
        JarFile war;
        try {
            war = new JarFile(file);
            JarEntry freesbeeXml = war.getJarEntry(Costanti.FREESBEE_META_INF);
            return (freesbeeXml != null);
        } catch (IOException ex) {
            logger.error("Eccezione nel caricamento del file war: " + ex);
            throw new PersistenceException("Eccezione nel caricamento del file war: " + ex);
        }
    }

    private static String getFreesbeeVersionFolder(File file) {
        try {
            String fileBuildPath = file.toString() + File.separator + Costanti.FREESBEE_BUILD_PROP;
            File fileBuild = new File(fileBuildPath);
            if (!fileBuild.exists()) {
                return Costanti.UNVERSIONED;
            }
            return loadBuildProperties(new FileInputStream(fileBuild));
        } catch (FileNotFoundException ex) {
            throw new PersistenceException("Eccezione nel caricamento del file di properties: " + ex);
        }
    }

    private static String getFreesbeeVersionWar(File file) {
        JarFile war;
        try {
            war = new JarFile(file);
            JarEntry fileBuild = war.getJarEntry(Costanti.FREESBEE_BUILD_PROP);
            if (fileBuild == null) {
                return Costanti.UNVERSIONED;
            }
            return loadBuildProperties(war.getInputStream(fileBuild));
        } catch (IOException ex) {
            logger.error("Eccezione nel caricamento del file war: " + ex);
            throw new PersistenceException("Eccezione nel caricamento del file war: " + ex);
        }
    }

    public static String loadBuildProperties(File file) {
        try {
            return loadBuildProperties(new FileInputStream(file));
        } catch (FileNotFoundException ex) {
            throw new PersistenceException("Impossibile leggere il file " + file);
        }
    }
    public static String loadBuildProperties(InputStream is) {
        if(is==null){
            throw new IllegalArgumentException("Impossibile leggere il numero di versione da uno stream nullo");
        }
        Properties properties = new Properties();
        try {
            properties.load(is);
            String majorVersion = properties.getProperty("major.version");
            String minorVersion = properties.getProperty("minor.version");
            String buildNumber = properties.getProperty("build.number");
            String freesbeeVersion = majorVersion + "." + minorVersion + "." + buildNumber;
            return freesbeeVersion;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new PersistenceException("Impossibile leggere il numero di versione");
        }
    }
}
