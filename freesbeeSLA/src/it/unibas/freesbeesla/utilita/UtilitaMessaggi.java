package it.unibas.freesbeesla.utilita;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class UtilitaMessaggi {

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
