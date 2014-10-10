package it.unibas.freesbeesla.monitoraggio.serviceguaranteetermstate.ws.server;

import it.unibas.freesbee.monitoraggio.calcolo.core.GuaranteeTermsResult;
import it.unibas.freesbeesla.monitoraggio.stub.SOAPFault;
import it.unibas.freesbee.monitoraggio.dbwsa.dbutilita.DataSource;
import it.unibas.freesbee.monitoraggio.exception.daoexception.DAOException;
import it.unibas.freesbee.monitoraggio.exception.inf2exception.INF2Exception;
import it.unibas.freesbee.monitoraggio.libreria.Monitoraggio;
import it.unibas.freesbeesla.ConfigurazioneDataSource;
import it.unibas.freesbeesla.monitoraggio.stub.GuaranteeStateType;
import it.unibas.freesbeesla.monitoraggio.stub.ObserveInterval;
import it.unibas.freesbeesla.monitoraggio.stub.GuaranteeTermObj;
import it.unibas.freesbeesla.monitoraggio.stub.RequestServiceGuaranteeTermStateType;
import it.unibas.freesbeesla.monitoraggio.stub.ResponseServiceGuaranteeTermStateTypeSuper;
import it.unibas.freesbeesla.monitoraggio.stub.ResultGaranteeTermObjStateSuper;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@javax.jws.WebService(serviceName = "ServiceGuaranteeTermState", portName = "ServiceGuaranteeTermState", targetNamespace = "http://sistemamonitoraggio.freesbee.unibas.it/", endpointInterface = "it.unibas.freesbeesla.monitoraggio.serviceguaranteetermstate.ws.server.ServiceGuaranteeTermState")
public class ServiceGuaranteeTermStateImpl implements ServiceGuaranteeTermState {

    private Log logger = LogFactory.getLog(this.getClass());

    public ResponseServiceGuaranteeTermStateTypeSuper getServiceGuaranteeTermState(RequestServiceGuaranteeTermStateType richiesta) throws SOAPFault {
        logger.info("Richiesta operazione: getServiceGuaranteeTermState");
        String idService = richiesta.getIdService();
        String idInitiator = richiesta.getIdInitiator();
        String idResponder = richiesta.getIdResponder();

        //Risposta
        ResponseServiceGuaranteeTermStateTypeSuper risposta = new ResponseServiceGuaranteeTermStateTypeSuper();
        risposta.setIdService(idService);
        risposta.setIdInitiator(idInitiator);
        risposta.setIdResponder(idResponder);

        //Dati risposta
        ResultGaranteeTermObjStateSuper resultGaranteeTermObjStateSuper = null;
        GuaranteeTermsResult result = null;

        //Libreria
        ConfigurazioneDataSource conf = ConfigurazioneDataSource.getInstance();
        DataSource.configure(conf.getDriver(), conf.getUrl(), conf.getUsername(), conf.getPassword());
        Monitoraggio monitoraggio = Monitoraggio.getInstance();


        try {
            logger.debug("Convalida wsag per il servizio " + idService + " " + idInitiator + " " + idResponder);
            monitoraggio.convalidaSintassiWSAG(idService, idInitiator, idResponder);

        } catch (Exception e) {
            logger.error("Si è verificato un errore nella generazione del validatore degli SLA.: " + e);
            e.printStackTrace();
            throw new SOAPFault("Si è verificato un errore nella generazione del validatore degli SLA.");
        }

        //Lista sla da verificare
        List<GuaranteeTermObj> listaSLA = richiesta.getGuaranteeTermObj();

        //Lista risultato verifica sla
        List<ResultGaranteeTermObjStateSuper> listaResultGaranteeTermObjStateSuper = new ArrayList();

        for (GuaranteeTermObj guaranteeTermObj : listaSLA) {
            try {
                //Inizializzati in caso di errore non devono essere null
                resultGaranteeTermObjStateSuper = new ResultGaranteeTermObjStateSuper();
                result = new GuaranteeTermsResult();

                if (guaranteeTermObj.getDateFn() == null || guaranteeTermObj.getDateFn().toString().equalsIgnoreCase("")) {
                    logger.debug("Richiesta senza  data");
                    result = monitoraggio.effettuaMonitoraggio(idService, idInitiator, idResponder, guaranteeTermObj.getGuaranteeTermName(), new GregorianCalendar().getTime());
                } else {
                    logger.debug("Richiesta con data");
                    result = monitoraggio.effettuaMonitoraggio(idService, idInitiator, idResponder, guaranteeTermObj.getGuaranteeTermName(), guaranteeTermObj.getDateFn().toGregorianCalendar().getTime());
                }

                if (result.isSoddisfatto()) {
                    resultGaranteeTermObjStateSuper.setGuaranteeState(GuaranteeStateType.FUL_FILLED);
                    resultGaranteeTermObjStateSuper.setGuaranteeTermsResult(result);
                } else {
                    resultGaranteeTermObjStateSuper.setGuaranteeState(GuaranteeStateType.VIOLATED);
                    resultGaranteeTermObjStateSuper.setGuaranteeTermsResult(result);
                }

            } catch (INF2Exception ex) {
                logger.error("Errore nella determinazione dello SLA: " + ex);
//                ex.printStackTrace();
                resultGaranteeTermObjStateSuper.setGuaranteeState(GuaranteeStateType.NOT_DETERMINED);

            } catch (DAOException ex) {
                logger.error("Errore nella determinazione dello SLA: " + ex);
//                ex.printStackTrace();
                resultGaranteeTermObjStateSuper.setGuaranteeState(GuaranteeStateType.NOT_DETERMINED);

            } finally {
                //In ogni caso aggiungi il risultato dello sla alla lista
                resultGaranteeTermObjStateSuper.setGuaranteeTermName(guaranteeTermObj.getGuaranteeTermName());
                ObserveInterval observe = new ObserveInterval();
                observe.setDateFn(guaranteeTermObj.getDateFn());
                resultGaranteeTermObjStateSuper.setObserveInterval(observe);
                resultGaranteeTermObjStateSuper.setGuaranteeTermsResult(result);

                listaResultGaranteeTermObjStateSuper.add(resultGaranteeTermObjStateSuper);
            }
        }

        if (richiesta.getGuaranteeTermObj().size() != listaResultGaranteeTermObjStateSuper.size()) {
            logger.error("Si è veirifcato un errore durante la verifica degli sla. Non tutti gli sla sono stati verificati");
            throw new SOAPFault("Si è veirifcato un errore durante la verifica degli sla. Non tutti gli sla sono stati verificati");
        }
        risposta.setResultGaranteeTermObjStateSuper(listaResultGaranteeTermObjStateSuper);

        return risposta;
    }
}
