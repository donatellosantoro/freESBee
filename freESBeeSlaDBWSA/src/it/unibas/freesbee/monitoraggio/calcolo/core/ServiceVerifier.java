package it.unibas.freesbee.monitoraggio.calcolo.core;

import it.unibas.freesbee.monitoraggio.dbwsa.DAOVerificaStatoServizio;
import it.unibas.freesbee.monitoraggio.exception.daoexception.DAOException;
import it.unibas.freesbee.monitoraggio.exception.inf2exception.INF2Exception;
import it.unibas.freesbee.monitoraggio.exception.inf2exception.INF2ResourceNotFoundException;

public class ServiceVerifier {

    public String verificaStato(String idService, String idInitiator, String idResponder) throws DAOException, INF2Exception {
        String res = "";
        try {
            res = DAOVerificaStatoServizio.verificaStatoServizio(idService, idInitiator, idResponder);
            if ("".equals(res)) {
                throw new INF2Exception(INF2ResourceNotFoundException.MSG_ERROR_003);
            }
        } catch (DAOException e) {
            throw e;
        }
        return res;
    }
}
