package it.unibas.freesbeesla.monitoraggio.servicetermstate.ws.server;

import it.unibas.freesbee.monitoraggio.calcolo.core.StatoServizio;
import it.unibas.freesbee.monitoraggio.dbwsa.DAOVerificaStatoServizio;
import it.unibas.freesbee.monitoraggio.dbwsa.dbutilita.DataSource;
import it.unibas.freesbeesla.ConfigurazioneDataSource;
import it.unibas.freesbeesla.monitoraggio.stub.RequestServiceTermStateType;
import it.unibas.freesbeesla.monitoraggio.stub.ResponseServiceTermStateTypeSuper;
import it.unibas.freesbeesla.monitoraggio.stub.ServiceTermStateType;
import it.unibas.freesbeesla.monitoraggio.stub.SOAPFault;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@javax.jws.WebService(serviceName = "ServiceTermState", portName = "ServiceTermState", targetNamespace = "http://sistemamonitoraggio.freesbee.unibas.it/", endpointInterface = "it.unibas.freesbeesla.monitoraggio.servicetermstate.ws.server.ServiceTermState")
public class ServiceTermStateImpl implements ServiceTermState {

    private Log logger = LogFactory.getLog(this.getClass());

    public ResponseServiceTermStateTypeSuper getServiceTermState(RequestServiceTermStateType richiesta) throws SOAPFault {
        logger.info("Richiesta operazione: getServiceTermState");
        String idService = richiesta.getIdService();
        String idInitiator = richiesta.getIdInitiator();
        String idResponder = richiesta.getIdResponder();
        ResponseServiceTermStateTypeSuper risposta = null;
        risposta = new ResponseServiceTermStateTypeSuper();
        risposta.setIdInitiator(idInitiator);
        risposta.setIdResponder(idResponder);
        risposta.setIdService(idService);
        try {
            ConfigurazioneDataSource conf = ConfigurazioneDataSource.getInstance();
            DataSource.configure(conf.getDriver(), conf.getUrl(), conf.getUsername(), conf.getPassword());

            StatoServizio statoRisultato = DAOVerificaStatoServizio.verificaStatoServizioSuper(idService, idInitiator, idResponder);

            risposta.setServiceTermState(ServiceTermStateType.fromValue(statoRisultato.getStato()));
            risposta.setCount(statoRisultato.getCount());

        } catch (Exception ex) {
            logger.error(ex);
            ex.printStackTrace();
            throw new SOAPFault("Errore durante l'accesso al DB.");
        }
        return risposta;
    }
}
