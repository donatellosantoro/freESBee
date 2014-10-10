package it.unibas.icar.freesbee.ws.registroservizi;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import it.unibas.icar.freesbee.modello.ConfigurazioneStatico;
import it.unibas.guicefreesbee.ContextStartup;
import javax.xml.ws.Endpoint;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.jaxws.EndpointImpl;

@Singleton
public class WSRegistroServizi extends RouteBuilder {

    @Inject
    private IWSRegistroServizi wsRegistro;
    private static Log logger = LogFactory.getLog(WSRegistroServizi.class);
    
    @Override
    public void configure() throws Exception {
        ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();
        String port = conf.getWebservicesPort();
        String indirizzo = conf.getWebservicesIndirizzo();
        EndpointImpl endpointPubblico = (EndpointImpl) Endpoint.create(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING, wsRegistro);
        endpointPubblico.publish("http://" + indirizzo + ":" + port + "/ws/registroServizi");
        ContextStartup.getEndpointAvviati().add(endpointPubblico);
        
//        EndpointImpl endpointPrivato = (EndpointImpl) Endpoint.create(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING, wsRegistro);
//        endpointPrivato.publish("http://" + indirizzo + ":" + port + "/ws/registroServizi");
//        ContextStartup.getEndpointAvviati().add(endpointPrivato);
    }

    public IWSRegistroServizi getWsRegistro() {
        return wsRegistro;
    }

    public void setWsRegistro(IWSRegistroServizi wsRegistro) {
        this.wsRegistro = wsRegistro;
    }
    
    
}
