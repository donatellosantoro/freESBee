package it.unibas.silvio.web.controllo;

import com.clarkware.junitperf.ConstantTimer;
import com.clarkware.junitperf.LoadTest;
import com.clarkware.junitperf.Timer;
import it.unibas.silvio.modello.IstanzaOperation;
import junit.extensions.RepeatedTest;
import junit.framework.Test;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TestSilvioPerfSincrono {

    private static Log logger = LogFactory.getLog(TestSilvioPerfSincrono.class);

    public static Test suite(String bodyMessage,IstanzaOperation istanzaOp, String stringUrl, int numeroUtenti, int numeroRichieste,int secondi) {

        Test testCase = new TestSilvio("testSilvio", bodyMessage, stringUrl,istanzaOp,secondi);
        Timer timer = new ConstantTimer(100);

        Test repeatTest = new RepeatedTest(testCase, numeroRichieste);
        Test loadTest = new LoadTest(repeatTest, numeroUtenti/**,timer**/);

        return loadTest;
    }
}
