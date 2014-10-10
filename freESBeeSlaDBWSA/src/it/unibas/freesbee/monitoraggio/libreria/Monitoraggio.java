package it.unibas.freesbee.monitoraggio.libreria;

import it.unibas.freesbee.monitoraggio.calcolo.core.GuaranteeTermsResult;
import it.unibas.freesbee.monitoraggio.calcolo.core.GuaranteeTermsValidator;
import it.unibas.freesbee.monitoraggio.dbwsa.dbutilita.DataSource;
import it.unibas.freesbee.monitoraggio.exception.daoexception.DAOException;
import it.unibas.freesbee.monitoraggio.exception.inf2exception.INF2Exception;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;

public class Monitoraggio {
    
    /* Questa classe contiene i metodi per :
       - Verificare la correttezza sintattica del file WSAG
       - Eseguire il monitoraggio e ritornare un esito relativo al risultato ottenuto
    */
    
    private static Monitoraggio singleton = new Monitoraggio();
    private String idService;
    private String idInitiator;
    private String idResponder;
    
    private Log logger = LogFactory.getLog(this.getClass());
    
    private Monitoraggio() {
    }
    
    public static Monitoraggio getInstance() {
        return singleton;
    }
    
    public String getIdService() {
        return idService;
    }

    public String getIdInitiator() {
        return idInitiator;
    }

    public String getIdResponder() {
        return idResponder;
    }
    
    public void setIdService(String idService) {
        this.idService = idService;
    }

    public void setIdInitiator(String idInitiator) {
        this.idInitiator = idInitiator;
    }

    public void setIdResponder(String idResponder) {
        this.idResponder = idResponder;
    }
    
    
    public boolean configuraDataBase(String aDriver, String adatabaseURI, String adbUserName, String adbPassword) {
        return DataSource.configure(aDriver, adatabaseURI, adbUserName, adbPassword);
    }    
        
    public GuaranteeTermsValidator convalidaSintassiWSAG(String idService, String idInitiator, String idResponder) throws INF2Exception, DAOException {
        GuaranteeTermsValidator gtValidator = null;
        try {
            this.setIdService(idService);
            this.setIdInitiator(idInitiator);
            this.setIdResponder(idResponder);
            gtValidator = new GuaranteeTermsValidator();
            gtValidator.fileWSAGValidator();
        }catch(INF2Exception inf2ex) {
            throw inf2ex;
        }catch(DAOException daoe) {
            throw daoe;
        }finally {
            return gtValidator;
        }
    }
    
    public GuaranteeTermsResult effettuaMonitoraggio(String idService, String idInitiator, String idResponder, String nomeGuaranteeTerm, Date dataFine) throws INF2Exception, DAOException {
        logger.info("\n************************************ Monitoraggio - effettuaMonitoraggio() ************");
        DateTime dataFineJoda = new DateTime(dataFine);
        logger.info("*** Data Fine : " + dataFineJoda.toString("yyyy-MM-dd HH:mm:ss"));
        try {
            this.setIdService(idService);
            this.setIdInitiator(idInitiator);
            this.setIdResponder(idResponder);
            GuaranteeTermsValidator termValidator = new GuaranteeTermsValidator();
            termValidator.fileWSAGValidator();
            termValidator.setDataFine(dataFineJoda);
            return termValidator.checkResult(nomeGuaranteeTerm);
        }catch(INF2Exception inf2ex) {
            throw inf2ex;
        }catch(DAOException daoe) {
            throw daoe;
        }
    }
   
//    public String verificaServizio(String idService, String idInitiator, String idResponder) throws DAOException, INF2Exception {
//        String stato = new ServiceVerifier().verificaStato(idService, idInitiator, idResponder);
//        return stato;
//    }
    
}
