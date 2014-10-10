package it.unibas.freesbee.websla.persistenza.soap;

import it.unibas.freesbee.websla.modello.ConfigurazioneStatico;
import it.unibas.freesbee.websla.modello.Utente;
import it.unibas.freesbee.websla.ws.security.SecurityUtil;
import it.unibas.freesbee.websla.ws.web.stub.ServiceMonitoraggioStatistiche;
import it.unibas.freesbee.websla.ws.web.stub.ServiceMonitoraggioStatistiche_Service;
import it.unibas.freesbee.websla.ws.web.stub.StatisticheSLAErogatore;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DAOStatisticheSOAP {

    private Log logger = LogFactory.getLog(this.getClass());
    private ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();

    public File getStatisticheErogatore(Utente utente, String path, StatisticheSLAErogatore statistiche, long tempoInMillisecondi) throws DAOException {
        try {
            String url = UtilitaFile.costruisciURL(conf.getIndirizzoModuloSLA(), DAOCostanti.URL_WSDL_MONITORAGGIO_STATISTICHE);
            ServiceMonitoraggioStatistiche_Service ss = new ServiceMonitoraggioStatistiche_Service(new URL(url), DAOCostanti.SERVICE_NAME_MONITORAGGIO_STATISTICHE);
            ServiceMonitoraggioStatistiche port = ss.getServiceMonitoraggioStatistiche();

            SecurityUtil.settaIntestazioniSicurezza(utente, port);

            it.unibas.freesbee.websla.ws.web.stub.StatisticheErogatore richiesta = new it.unibas.freesbee.websla.ws.web.stub.StatisticheErogatore();

            richiesta.setStatisticheSLAErogatore(statistiche);

            it.unibas.freesbee.websla.ws.web.stub.StatisticheErogatoreResponse risposta = port.statisticheErogatore(richiesta);

            ByteArrayInputStream inputStream = new ByteArrayInputStream(risposta.getReturn());

            File file = new File(path + "/statistiche" + tempoInMillisecondi + ".png");

            UtilitaFile.copyStreamToFile(inputStream, file);

            return file;
        } catch (IOException ioex) {
            logger.error("Errore nella lettura del grafdico delle statistiche. " + ioex);
            throw new DAOException(ioex);
        } catch (Exception ex) {
            logger.error("Errore nella lettura del grafdico delle statistiche. " + ex);
            throw new DAOException(ex);
        }
    }

    public void removeStatisticheErogatore(String path, long tempoInMillisecondi) {
        File file = new File(path + "/statistiche" + tempoInMillisecondi + ".png");
        file.delete();
    }
}
