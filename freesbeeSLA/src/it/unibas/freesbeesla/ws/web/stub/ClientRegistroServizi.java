package it.unibas.freesbeesla.ws.web.stub;


import it.unibas.freesbeesla.ConfigurazioneStatico;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.WSRegistroServiziImplService;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.IWSRegistroServizi;
import java.net.URL;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ClientRegistroServizi {

    private Log logger = LogFactory.getLog(this.getClass());
    private String connettoreRegistroServizi; //Indirizzo effettivo a cui inviare la richiesta
    private WSRegistroServiziImplService service;
    private IWSRegistroServizi port;
    private String indirizzoRichiesta = null;

    public ClientRegistroServizi(String connettore) throws Exception {
        this.connettoreRegistroServizi = connettore;
        ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();
        String indirizzoWSDL = connettoreRegistroServizi;

        if (!conf.getTipoNica().equalsIgnoreCase(ConfigurazioneStatico.NON_VERIFICATO)) {

            if (conf.getTipoNica().equalsIgnoreCase(ConfigurazioneStatico.NICA)) {
                //Pubblicato in beanCXF
                indirizzoWSDL = "http://localhost:8080/freesbeesla/ws/registroServizi?wsdl"; //DEVO RICHIEDERE AL MODULO DI INTEROPERABILITA'
                logger.info("Indirizzo WSDL : "+ indirizzoWSDL);
                indirizzoRichiesta = connettoreRegistroServizi; //E GLI DEVO INVIARE L'INDIRIZZO DEL REGISTRO
                logger.info("NICA : " + indirizzoRichiesta);
            } else {
                if (!(indirizzoWSDL.toLowerCase()).endsWith("?wsdl")) {
                    indirizzoWSDL = indirizzoWSDL + "?wsdl";
                    logger.info("FREESBEE: " + indirizzoWSDL);
                }
            }

            try {
                service = new WSRegistroServiziImplService(new URL(indirizzoWSDL));
                port = service.getWSRegistroServiziImplPort();
            } catch (Exception ex) {
                ex.printStackTrace();
                logger.error("Errore mentre si richiedevano informazioni al registro dei servizi " + ex);
                throw new Exception("Errore mentre si richiedevano informazioni al registro dei servizi.");
            }

        } else {
            logger.error("Il modulo non può essere utilizzato perchè non è stato configurato.");
            throw new Exception("Il modulo non può essere utilizzato perchè non è stato configurato.");
        }
    }

    public IWSRegistroServizi getPortRegisrtoServizi() {
        return port;
    }

    public String getIndirizzoRichiesta() {
        return indirizzoRichiesta;
    }
}
