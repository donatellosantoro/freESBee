package it.unibas.freesbeesla.test.wsag;

import it.unibas.freesbee.client.cxf.sistemamonitoraggio.ServiceGuaranteeTermStateClient;
import it.unibas.freesbeesla.ConfigurazioneStatico;
import it.unibas.freesbeesla.ws.web.persistenza.soap.Ripulitore;
import java.io.IOException;
import java.io.InputStream;
import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TestRipulitore extends TestCase {

    protected Log logger = LogFactory.getLog(this.getClass());

    @Override
    protected void setUp() throws Exception {
    }

    public void test() throws IOException {

        InputStream is = this.getClass().getResourceAsStream("/dati/WSAG_SPORCO.xml");
        Ripulitore rip = new Ripulitore();
        rip.ripulisci(is);


    }
}