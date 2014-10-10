package it.unibas.freesbee.ge.messaggi;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FileUtil {

    private static Log logger = LogFactory.getLog(FileUtil.class);

    public static final void copyDir(File srcDir, File targetDir) throws FileNotFoundException, IOException {
        if (srcDir == null || targetDir == null) {
            throw new IllegalArgumentException("Impossibile copiare le cartelle. Una delle cartelle e' NULL");
        }
        if (!srcDir.isDirectory()) {
//            throw new FileNotFoundException("La cartella " + srcDir + " non esiste");
            logger.info("La cartella " + srcDir + " non esiste");
            return;
        }
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }

        File files[] = srcDir.listFiles();
        for (File fileSorgente : files) {
            File fileDestinazione = new File(targetDir + File.separator + fileSorgente.getName());
            if (fileSorgente.isDirectory()) {
                copyDir(fileSorgente, fileDestinazione);
            } else {
                copyFile(fileSorgente, fileDestinazione);
            }
        }
    }

    public static final void copyFile(File in, File out) throws FileNotFoundException, IOException {
        if (in == null || out == null) {
            return;
        }
        FileInputStream inStream = new FileInputStream(in);
        FileOutputStream outStream = new FileOutputStream(out);
        copyStream(inStream, outStream);
        inStream.close();
        outStream.close();
    }

    public static final void copyStreamToFile(InputStream in, File out) throws FileNotFoundException, IOException {
        FileOutputStream outTmpFile = new FileOutputStream(out);
        FileUtil.copyStream(in, outTmpFile);
        in.close();
        outTmpFile.close();
    }

    public static final void copyStream(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int len;

        while ((len = in.read(buffer)) >= 0) {
            out.write(buffer, 0, len);
        }
    }

    public static final void saveStringToFile(String content, String file) throws FileNotFoundException, IOException {
        if (content == null) {
            throw new IllegalArgumentException("Impossibile salvare una stringa null");
        }
        File fileTarget = new File(file);
        fileTarget.getParentFile().mkdirs();
        copyStreamToFile(new ByteArrayInputStream(content.getBytes()), fileTarget);
    }

    public static final boolean deleteFolder(String stringDir) {
        logger.info("Devo eliminare la cartella " + stringDir);
        File dir = new File(stringDir);
        if (dir.exists() && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                boolean success = deleteFolder(dir + File.separator + children[i]);
                if (!success) {
                    return false;
                }
            }
        }    
        // The directory is now empty so delete it
        return dir.delete();
    }
}
