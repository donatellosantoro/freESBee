package it.unibas.freesbeesla.ws.web;

import it.unibas.freesbeesla.DatiConfigurazione;
import it.unibas.freesbeesla.tracciatura.persistenza.DAOException;
import it.unibas.freesbeesla.tracciatura.ws.server.SOAPFault;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@javax.jws.WebService(serviceName = "ServiceConfigura", portName = "ServiceConfigura", targetNamespace = "http://web.ws.freesbeesla.unibas.it/", endpointInterface = "it.unibas.freesbeesla.ws.web.IWSConfigura")
public class WSConfiguraImpl implements IWSConfigura {

    private Log logger = LogFactory.getLog(this.getClass());
    private it.unibas.freesbeesla.ws.web.persistenza.soap.DAOConfigurazione daoConfigurazioneProperties = new it.unibas.freesbeesla.ws.web.persistenza.soap.DAOConfigurazione();

    public String verificaNica() throws SOAPFault {
        try {
            logger.info("Operazione richiesta: verificaNica");
            String result = null;
            result = daoConfigurazioneProperties.verificaNICA();
            return result;
        } catch (DAOException ex) {
            SOAPFault sf = new SOAPFault();
            sf.setDescription(ex.getMessage());
            throw sf;
        }
    }

    public DatiConfigurazione caricaConfigurazione() throws SOAPFault {
        try {
            logger.info("Operazione richiesta: caricaConfigurazione");
            return daoConfigurazioneProperties.getDatiConfigurazione();
        } catch (DAOException ex) {
            logger.error(ex);
            throw new SOAPFault(ex.getMessage());
        }
    }

    public void modificaConfigurazioneSLA(DatiConfigurazione datiConfigurazione) throws SOAPFault {
        try {
            logger.info("Operazione richiesta: modificaConfigurazioneSLA");
            daoConfigurazioneProperties.modificaConfigurazioneSLA(datiConfigurazione);
        } catch (DAOException ex) {
            logger.error(ex);
            throw new SOAPFault(ex.getMessage());
        }
    }

    public void modificaConfigurazioneSP(DatiConfigurazione datiConfigurazione) throws SOAPFault {
        try {
            logger.info("Operazione richiesta: modificaConfigurazioneSP");
            daoConfigurazioneProperties.modificaConfigurazioneSP(datiConfigurazione);
        } catch (DAOException ex) {
            logger.error(ex);
            throw new SOAPFault(ex.getMessage());
        }
    }
}
