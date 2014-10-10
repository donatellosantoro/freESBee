package it.unibas.freesbeesla.ws.web.persistenza.soap;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class Ripulitore {

    
    protected static Log logger = LogFactory.getLog(Ripulitore.class);

    public static InputStream ripulisci(InputStream isIn) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(isIn));
        String line = null;
        OutputStream os = new ByteArrayOutputStream();
        while ((line = br.readLine()) != null) {
            if ((line.indexOf("<!--") != -1) || (line.indexOf("-->") != -1) || (line.equals(""))) {
            } else {
                os.write(line.getBytes());
            }
        }
        os.flush();

        logger.debug("");
        logger.debug("");
        logger.debug("*********************************************************************");
        logger.debug( XmlJDomUtil.formattaXML(os.toString()));
        logger.debug("");
        logger.debug("");
        logger.debug("*********************************************************************");
        String sms = XmlJDomUtil.formattaXML(os.toString());
        byte[] bytes = sms.getBytes("UTF-8");
        return new ByteArrayInputStream(bytes);
    }

    
}
