/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.unibas.icar.freesbeesp.sso.driver;

import it.unibas.icar.freesbeesp.sso.SSOSessionResponse;
import java.util.Map;

/**
 *
 * @author Donatello
 */
public interface ISSODriver {

    SSOSessionResponse createSession(String username, String password, String risorsa, String idpEntityID) throws Exception;

    Map<String, String> verificaDiscoveryService(String risorsa);

}
