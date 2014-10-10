package it.unibas.freesbee.monitoraggio.calcolo.core;

import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.CustomServiceLevelType;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.FunctionType;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.GuaranteeTermType;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.GuaranteeTerms;
import it.unibas.freesbee.monitoraggio.dbwsa.DAOFileWSAG;
import it.unibas.freesbee.monitoraggio.exception.inf2exception.INF2ResourceNotFoundException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import it.unibas.freesbee.monitoraggio.exception.daoexception.DAOException;
import it.unibas.freesbee.monitoraggio.exception.inf2exception.INF2Exception;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;

public class GuaranteeTermsValidator {
    
    private GuaranteeTerms guaranteeTerms;
    private DateTime dataFine;
    private double risultato;
    
    private Log logger = LogFactory.getLog(this.getClass());
            
    public void fileWSAGValidator() throws INF2Exception, DAOException {
        JAXBContext jc;
        try {
            jc = JAXBContext.newInstance("it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type");
            Unmarshaller u = jc.createUnmarshaller();
            String objSLA = DAOFileWSAG.caricaFileWSAG();
            if(objSLA == null) {
                throw new INF2Exception(INF2ResourceNotFoundException.MSG_ERROR_003);
            }
            guaranteeTerms = (GuaranteeTerms)u.unmarshal(new StringReader(objSLA));
        }catch(DAOException daoe) {
            throw new DAOException(daoe);
        }catch(JAXBException jaxbex) {
            throw new INF2Exception(jaxbex);
        }
    }
    
    public GuaranteeTermsResult checkResult(String nomeGuaranteeTerm) throws DAOException, INF2Exception {
        logger.info("\n************************************ GuaranteeTermsValidator - checkResult()");
        GuaranteeTermsResult gtResult = new GuaranteeTermsResult();
        GuaranteeTermType gtObject = this.trovaGuaranteeTerm(nomeGuaranteeTerm);
        if(gtObject != null) {
            CustomServiceLevelType customSLT = gtObject.getServiceLevelObjective().getCustomServiceLevel();
            FunctionType functionType = customSLT.getFunction();
            gtResult.setThresholdValue(Double.parseDouble(customSLT.getThreshold().getValue()));
            gtResult.setThresholdOperator(customSLT.getThreshold().getOperator().value());
            logger.info("********* Data fine : " + dataFine.toString());
            GestoreAlgoritmo gestoreAlgoritmo = new GestoreAlgoritmo(this.getDataFine());
            List<Double> listaRisultato = gestoreAlgoritmo.calcolaAlgoritmo(functionType, null);
            this.risultato = listaRisultato.get(0);  
            gtResult.setRisultatoFinale(risultato);
            return gtResult;
        }else {
            throw new INF2Exception(INF2ResourceNotFoundException.MSG_ERROR_002);            
        }
    }

    private GuaranteeTermType trovaGuaranteeTerm(String nomeGuaranteeTerm) {
        for(GuaranteeTermType gtObject : guaranteeTerms.getGuaranteeTerm()) {
            if(gtObject.getName().equalsIgnoreCase(nomeGuaranteeTerm)) {
                return gtObject;
            }
        }
        return null;
    }
    
    public List<String> getListaNomiGt() {
        List<String> listaNomiGt = new ArrayList<String>();
        for(GuaranteeTermType gtt : this.guaranteeTerms.getGuaranteeTerm()) {
            listaNomiGt.add(gtt.getName());
        }
        return listaNomiGt;
    }

    public DateTime getDataFine() {
        return dataFine;
    }

    public void setDataFine(DateTime dataFine) {
        this.dataFine = dataFine;
    }

    public double getRisultato() {
        return risultato;
    }

}
