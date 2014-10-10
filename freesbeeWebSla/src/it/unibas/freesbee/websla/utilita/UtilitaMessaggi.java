package it.unibas.freesbee.websla.utilita;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.GeneralSecurityException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.contrib.ssl.EasySSLProtocolSocketFactory;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UtilitaMessaggi {

    private static Log logger = LogFactory.getLog(UtilitaMessaggi.class);

    public static String inviaMessaggioXmlToURL(String urlStringa, String body) throws IOException, GeneralSecurityException {
        URL url = new URL(urlStringa);
        Protocol protocol = new Protocol("https", new EasySSLProtocolSocketFactory(), url.getPort());
        Protocol.registerProtocol("https", protocol);
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(urlStringa);

        RequestEntity request = new StringRequestEntity(body, "application/soap+xml;charset=UTF-8;", null);
        postMethod.setRequestEntity(request);
        httpClient.executeMethod(postMethod);
        String risposta = postMethod.getResponseBodyAsString();
        if (postMethod.getStatusCode() < 200 || postMethod.getStatusCode() > 299) {
            throw new IOException("URL errato: " + urlStringa);
        }
        logger.info("\n\n\n\n***********" + risposta);
        return risposta;
    }

    public static String invocaWSDL(URL urlWsdl) throws MalformedURLException, IOException {

        URLConnection conn = urlWsdl.openConnection();

        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        String stringaRisposta = "";
        while ((line = rd.readLine()) != null) {
            stringaRisposta += line;
        }

        rd.close();

        return stringaRisposta;
    }
}
