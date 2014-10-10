package it.unibas.icar.freesbee.test.qualificazione.fruitore;

import it.unibas.icar.freesbee.test.qualificazione.UtilTest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import junit.framework.AssertionFailedError;
import org.apache.servicemix.util.FileUtil;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.bio.SocketConnector;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.w3c.dom.Document;

public abstract class AbstractPATest extends HttpServlet {

    private org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog(this.getClass());
//    private int port = 28196; //Utile per mettere TCPMon sulla 18196
    private int port = 18196;
    private Server server = new Server();
    private String fileMessaggioRisposta;

    public AbstractPATest(String fileMessaggioRisposta) {
        this.fileMessaggioRisposta = fileMessaggioRisposta;
    }

    public abstract void verificaRichiesta(String stringaRichiesta) throws AssertionFailedError;

    @Override
    protected void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws ServletException, IOException {
        InputStream isRichiesta = httpRequest.getInputStream();
        ByteArrayOutputStream streamRichiesta = new ByteArrayOutputStream();
        FileUtil.copyInputStream(isRichiesta, streamRichiesta);
        String stringaRichiesta = streamRichiesta.toString();
        logger.info("Richiesta ricevuta: \n" + stringaRichiesta);

        boolean errore = false;
        try {
            verificaRichiesta(stringaRichiesta);
        } catch (AssertionFailedError ex) {
            ex.printStackTrace();
            errore = true;
        }

        httpResponse.setContentType("text/html");
        httpResponse.setStatus(HttpServletResponse.SC_OK);

        String htmlResponse;
        if (errore) {
            htmlResponse = "<fail/>";
            logger.info("Failed");
        } else {
            if (fileMessaggioRisposta.isEmpty()) {
                htmlResponse = "";
            } else {
                htmlResponse = UtilTest.leggiMessaggio(fileMessaggioRisposta);
                Document soapRisp = UtilTest.leggiSOAP(htmlResponse);
                String idMessaggioRisposta = UtilTest.trovaNodoString("/soapenv:Envelope/soapenv:Header/eGov_IT:Intestazione/eGov_IT:IntestazioneMessaggio/eGov_IT:Messaggio/eGov_IT:Identificatore", soapRisp);
                if (idMessaggioRisposta != null) {
                    logger.info("Dobbiamo cancellare il messaggio " + idMessaggioRisposta + " prima di eseguire il test");
                    UtilTest.cancellaMessaggio(idMessaggioRisposta);
                }
            }

            logger.info("Success");
        }
        httpResponse.getWriter().println(htmlResponse);
    }

    public void startServlet() {
        Connector connector = new SocketConnector();
        connector.setPort(port);
        server.setConnectors(new Connector[]{connector});

        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);

        handler.addServletWithMapping(new ServletHolder(this), "/PA/");

        new Thread() {

            @Override
            public void run() {
                try {
                    server.start();
                    server.join();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    logger.error("Impossibile avviare il server sulla porta " + port);
                }
            }
        }.start();

        logger.info("PATest avviata all'indirizzo http://localhost:" + port + "/PA/");
    }

    public void stop() {
        try {
            server.stop();
            logger.info("Server stoppato");
        } catch (Exception ex) {
            logger.warn("Impossibile fermare il server " + ex);
        }
    }
}
