package it.unibas.freesbee.websla.persistenza.soap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class UtilitaFile {
    
    public static final void copyFile(File in, File out) throws FileNotFoundException, IOException{
        if(in==null || out==null){
            return;
        }
        FileInputStream inStream = new FileInputStream(in);
        FileOutputStream outStream = new FileOutputStream(out);
        copyStream(inStream, outStream);
        inStream.close();
        outStream.close();
    }
    
    public static final void copyStreamToFile(InputStream in, File out) throws FileNotFoundException, IOException{
        FileOutputStream outTmpFile = new FileOutputStream(out);
        copyStream(in, outTmpFile);
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
    
      public static String costruisciURL(String url, String stringaDaParserizzare) {
        if (url.endsWith("/")) {
            return url + stringaDaParserizzare;
        } else {
            return url + "/" + stringaDaParserizzare;
        }
    }
}
