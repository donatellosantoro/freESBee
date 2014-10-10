package it.unibas.freesbeesla.ws.web.persistenza.soap;

import it.unibas.freesbeesla.ConfigurazioneStatico;
import it.unibas.freesbeesla.DatiConfigurazione;
import it.unibas.freesbeesla.tracciatura.persistenza.DAOException;
import it.unibas.freesbeesla.utilita.UtilitaMessaggi;
import it.unibas.freesbeesla.ws.web.persistenza.torque.DAOConfigurazioneTorque;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DAOConfigurazione {

    private static Log logger = LogFactory.getLog(DAOConfigurazione.class);

    public String verificaNICA() throws DAOException {
        String resultType = ConfigurazioneStatico.NON_VERIFICATO;

        try {
            DAOConfigurazione daoConfigurazione = new DAOConfigurazione();
            DatiConfigurazione datiConfigurazione = daoConfigurazione.getDatiConfigurazione();
            logger.debug("Configurazione - verifica della disponibilità del  Registro Servizi del NICA: " + datiConfigurazione.getIndirizzoRegistroServizi());
            URL urlWsdl = new URL(datiConfigurazione.getIndirizzoRegistroServizi() + "?wsdl");

            logger.info("Indirizzo NICA: " + urlWsdl);
            String message = UtilitaMessaggi.invocaWSDL(urlWsdl);

            logger.info("Namespace cercato: " + ConfigurazioneStatico.getInstance().getNamespaceFreesbe());
            if (message.contains(ConfigurazioneStatico.getInstance().getNamespaceFreesbe())) {
                logger.debug("TIPO: " + ConfigurazioneStatico.FREESBEE);
                resultType = ConfigurazioneStatico.FREESBEE;
                ConfigurazioneStatico.getInstance().setTipoNica(ConfigurazioneStatico.FREESBEE);
            } else {
                logger.debug("TIPO: " + ConfigurazioneStatico.NICA);
                resultType = ConfigurazioneStatico.NICA;
                ConfigurazioneStatico.getInstance().setTipoNica(ConfigurazioneStatico.NICA);
            }

        } catch (java.net.ConnectException ex) {
            ConfigurazioneStatico.getInstance().setTipoNica(ConfigurazioneStatico.NON_VERIFICATO);
            logger.error("Non è possibile verificare il Registro Servizi del NICA. Connesione rifiutata." + ex);
            throw new DAOException("Non è possibile verificare il Registro Servizi del NICA. Connesione rifiutata.");
        } catch (Exception ex) {
            ConfigurazioneStatico.getInstance().setTipoNica(ConfigurazioneStatico.NON_VERIFICATO);
            logger.error("La verifica non è andata a buon fine." + ex);
            throw new DAOException("La verifica non è andata a buon fine.");
        }
        return resultType;
    }

    public DatiConfigurazione getDatiConfigurazione() throws DAOException {
        try {
            DAOConfigurazioneTorque daoConfigurazione = new DAOConfigurazioneTorque();
            DatiConfigurazione datiConfigurazione = daoConfigurazione.doSelectConfigurazione();
            datiConfigurazione.setProtezioneSP(ConfigurazioneStatico.getInstance().isProtezioneSP());
            return datiConfigurazione;
        } catch (Exception ex) {
            ConfigurazioneStatico.getInstance().setTipoNica(ConfigurazioneStatico.NON_VERIFICATO);
            logger.error("La verifica non è andata a buon fine." + ex);
            throw new DAOException("La verifica non è andata a buon fine.");
        }
    }

    public void modificaConfigurazioneSLA(DatiConfigurazione datiConfigurazione) throws DAOException {
        try {
            DAOConfigurazioneTorque daoConfigurazione = new DAOConfigurazioneTorque();
            daoConfigurazione.modificaConfigurazioneSLA(datiConfigurazione);
        } catch (Exception ex) {
            logger.error("Non è possibile aggiornare la configurazione del modulo SLA." + ex);
            throw new DAOException("Non è possibile aggiornare la configurazione del modulo SLA.");
        }
    }

    public void modificaConfigurazioneSP(DatiConfigurazione datiConfigurazione) throws DAOException {
        try {
            DAOConfigurazioneTorque daoConfigurazione = new DAOConfigurazioneTorque();
            daoConfigurazione.modificaConfigurazioneSP(datiConfigurazione);
        } catch (Exception ex) {
            logger.error("Non è possibile aggiornare la configurazione SP del modulo SLA." + ex);
            throw new DAOException("Non è possibile aggiornare la configurazione SP del modulo SLA.");
        }


    }
}
