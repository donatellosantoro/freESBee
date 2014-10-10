package it.unibas.freesbeesla.ws.web;

import it.unibas.freesbee.monitoraggio.dbwsa.dbutilita.DataSource;
import it.unibas.freesbeesla.ConfigurazioneDataSource;
import it.unibas.freesbeesla.tracciatura.ws.server.SOAPFault;
import it.unibas.freesbeesla.ws.web.grafico.GeneraGrafico;
import it.unibas.freesbeesla.ws.web.grafico.UtilitaGrafico;
import it.unibas.freesbeesla.ws.web.stub.StatisticheSLAErogatore;
import it.unibas.freesbeesla.ws.web.persistenza.soap.DAOException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@javax.jws.WebService(serviceName = "ServiceMonitoraggioStatistiche", portName = "ServiceMonitoraggioStatistiche", targetNamespace = "http://web.ws.freesbeesla.unibas.it/", endpointInterface = "it.unibas.freesbeesla.ws.web.IWSMonitoraggioStatistiche")
public class WSMonitoraggioStatisticheImpl implements IWSMonitoraggioStatistiche {

    private Log logger = LogFactory.getLog(this.getClass());

    public DataHandler statisticheErogatore(StatisticheSLAErogatore richiesta) throws SOAPFault {
        try {
            ConfigurazioneDataSource conf = ConfigurazioneDataSource.getInstance();
            DataSource.configure(conf.getDriver(), conf.getUrl(), conf.getUsername(), conf.getPassword());

            BufferedImage image = null;
            DataHandler attach = null;
            logger.info("Operazione richiesta: statisticheErogatore");
            UtilitaGrafico calcola = new UtilitaGrafico(richiesta);
            calcola.calcolaStatistiche();
            GeneraGrafico chart = new GeneraGrafico(richiesta);
            image = chart.getChartViewer();
            File f = File.createTempFile("grafico", ".png");
            ImageIO.write(image, "png", f);
            FileDataSource fds = new FileDataSource(f.getAbsolutePath());
            attach = new DataHandler(fds);
            f.deleteOnExit();
            return attach;
        } catch (IOException ex) {
            logger.error("Errore nella realizzazione del grafico delle statistiche. " + ex.getMessage());
            throw new SOAPFault("Si e' verificato un errore nella realizzazione del grafico delle statistiche");
        } catch (IllegalArgumentException ex) {
            logger.error("Errore nella realizzazione del grafico delle statistiche. " + ex.getMessage());
            throw new SOAPFault(ex.getMessage());
        } catch (DAOException ex) {
            logger.error("Errore nella realizzazione del grafico delle statistiche. " + ex.getMessage());
            throw new SOAPFault("Si e' verificato un errore nella realizzazione del grafico delle statistiche");
        }
    }
}
