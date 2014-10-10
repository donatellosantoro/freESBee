package it.unibas.icar.freesbeesp.modello;

import it.unibas.icar.freesbee.modello.AccordoServizio;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAccordoServizio;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAccordoServizioResponse;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.IWSRegistroServizi;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.WSRegistroServiziImplService;
import it.unibas.icar.freesbeesp.util.FileUtil;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class PolicyCache {

    private org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog(this.getClass());
    private Map<String, File> cache = new HashMap<String,File>();

    private static PolicyCache singleton = new PolicyCache();

    public static PolicyCache getInstance() {
        return singleton;
    }

    public File getPolicy(String nomeAccordoServizio){
        File accordoCache = cache.get(nomeAccordoServizio);
        if(accordoCache!=null){
            return accordoCache;
        }
        accordoCache = contattaRegistroServizi(nomeAccordoServizio);
        cache.put(nomeAccordoServizio, accordoCache);
        return accordoCache;
    }

    private File contattaRegistroServizi(String nomeAccordoServizio) {
        XMLConfigurazione configurazioneXml = XMLConfigurazione.getInstance();
        String indirizzoRegistroServizi = configurazioneXml.getIndirizzoRegistroServizi();
        try {
            if (!(indirizzoRegistroServizi.toLowerCase()).endsWith("?wsdl")) {
                indirizzoRegistroServizi = indirizzoRegistroServizi + "?wsdl";
            }
            AccordoServizio accordo = new AccordoServizio(nomeAccordoServizio);
            logger.info("Richiedo le informazioni al registro dei servizi " + indirizzoRegistroServizi);
            WSRegistroServiziImplService service = new WSRegistroServiziImplService(new URL(indirizzoRegistroServizi));
            IWSRegistroServizi port = service.getWSRegistroServiziImplPort();
            GetAccordoServizio richiesta = new GetAccordoServizio();
            richiesta.setAccordoServizio(accordo);
            GetAccordoServizioResponse risposta = port.getAccordoServizio(richiesta, indirizzoRegistroServizi);
            String policy = risposta.getReturn().getPolicyXACML();
            logger.info("Trovata la policy" + policy);
            return FileUtil.saveStringToTempFile(policy);
        } catch (Exception ex) {
            if (logger.isInfoEnabled()) {
                ex.printStackTrace();
            }
            logger.error("Errore mentre si richiedevano informazioni al registro dei servizi " + ex);
            return null;
        }
    }

}
