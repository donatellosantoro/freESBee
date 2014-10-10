package it.unibas.silvio.web.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.faces.context.FacesContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.custom.fileupload.UploadedFile;

public class SilvioWebUtil {

    private static Log logger = LogFactory.getLog(SilvioWebUtil.class);

    protected static ClassLoader getCurrentClassLoader(Object defaultObject) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        if (loader == null) {
            loader = defaultObject.getClass().getClassLoader();
        }
        return loader;
    }
    

    public static String caricaMessaggio(String chiave) {
        FacesContext context = FacesContext.getCurrentInstance();
        String text = SilvioWebUtil.getMessageResourceString(context.getApplication().getMessageBundle(), chiave, null, context.getViewRoot().getLocale());
        if(text==null){
            logger.warn("Non esiste la stringa per il messaggio " + chiave);
            return chiave;
        }
        return text;
    }

    public static String uploadFile(UploadedFile file, String dir) throws IOException {
        if (file == null) {
            logger.info("Nessun file specificato");
            return null;
        }
        new File(dir).mkdirs();
        String nomeFile = file.getName();
        nomeFile = estraiNomeFile(nomeFile);
        logger.info("Nome File: " + nomeFile);
        String pathFile = dir + nomeFile;
        File temp = new File(pathFile);
        int contatore = 1;
        while (temp.exists()) {
            nomeFile = contatore + "_" + estraiNomeFile(nomeFile);
            pathFile = dir + nomeFile;
            temp = new File(pathFile);
            contatore++;
        }
        temp.deleteOnExit();
        new File(dir).deleteOnExit();

        InputStream stream = file.getInputStream();
        byte[] line = new byte[1024];
        int bytes = 0;
        FileOutputStream fileOutS = new FileOutputStream(pathFile);

        while (0 < (bytes = stream.read(line))) {
            fileOutS.write(line, 0, bytes);
        }

        fileOutS.close();
        stream.close();

        logger.info("File caricato in " + pathFile);
        return nomeFile;
    }

    public static String getMessageResourceString(String bundleName, String key,Object params[], Locale locale) {
        String text = null;
        ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale,
                getCurrentClassLoader(params));
        try {
            text = bundle.getString(key);
        } catch (MissingResourceException e) {
            text = "?? key " + key + " not found ??";
        }

        if (params != null) {
            MessageFormat mf = new MessageFormat(text, locale);
            text = mf.format(params, new StringBuffer(), null).toString();
        }

        return text;
    }

    public static List<String> estraiZip(String dir, String fileDaEstrarre) throws IOException {
        List<String> fileEstratti = new ArrayList<String>();
        String cartellaZip = fileDaEstrarre.substring(0, fileDaEstrarre.length() - 4);
        boolean success = new File(dir + File.separator + cartellaZip).mkdir();
        if (success) {
            logger.info("Cartella " + cartellaZip + " Creata");
        } else {
            logger.info("Impossibile creare la cartella " + cartellaZip);
        }
        ZipFile zipFile = new ZipFile(dir + fileDaEstrarre);
        Enumeration en = zipFile.entries();
        while (en.hasMoreElements()) {
            ZipEntry entry = (ZipEntry) en.nextElement();

            if (!entry.isDirectory()) {
                File parent = new File(entry.getName()).getParentFile();
                if (parent != null && !parent.equals("")) {
                    new File(dir + cartellaZip + File.separator + parent).mkdirs();
                    fileEstratti.add(cartellaZip + File.separator + entry.getName());
                }
                BufferedInputStream inputStream = new BufferedInputStream(zipFile.getInputStream(entry));
                File outFile = new File(dir + cartellaZip + File.separator + entry.getName());
                FileOutputStream outputStream = new FileOutputStream(outFile);
                byte[] buffer = new byte[8192];
                int read;
                while ((read = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, read);
                }
                if (entry.getName().contains(".zip")) {
                    List<String> lista = estraiZip(dir + cartellaZip + File.separator, entry.getName());
                    for (String s : lista) {
                        fileEstratti.add(s);
                    }
                }
                outputStream.close();
                inputStream.close();
                fileEstratti.add(cartellaZip + File.separator + entry.getName());
            } else {
                logger.info("Sto elaborando una cartella: " + entry.getName());
                new File(entry.getName()).mkdirs();
                fileEstratti.add(cartellaZip + File.separator + entry.getName());
            }
        }
        logger.info("ESTRAZIONE DI " + fileDaEstrarre + " TERMINATA");
        return fileEstratti;
    }

    private static String estraiNomeFile(String nomeFile) {        
        logger.debug("Nomefile: " + nomeFile);
        int x = nomeFile.lastIndexOf(File.separator);
        if(x==-1){
            return nomeFile;
        }
        return nomeFile.substring(x + 1);
    }
}
