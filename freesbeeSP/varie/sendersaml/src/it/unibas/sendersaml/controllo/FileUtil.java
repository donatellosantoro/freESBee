package it.unibas.sendersaml.controllo;

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

    public static final File saveStringToTempFile(String content) throws FileNotFoundException, IOException {
        if (content == null) {
            throw new IllegalArgumentException("Impossibile salvare una stringa null");
        }
        File fileTarget = File.createTempFile("policy", null);
        fileTarget.deleteOnExit();
        fileTarget.getParentFile().mkdirs();
        copyStreamToFile(new ByteArrayInputStream(content.getBytes()), fileTarget);
        return fileTarget;
    }
}
