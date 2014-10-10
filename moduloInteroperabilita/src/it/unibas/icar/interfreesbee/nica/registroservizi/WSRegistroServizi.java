package it.unibas.icar.interfreesbee.nica.registroservizi;

import it.unibas.guicefreesbee.ContextStartup;
import it.unibas.icar.freesbee.modello.ConfigurazioneStatico;
import javax.xml.ws.Endpoint;
import org.apache.camel.builder.RouteBuilder;
import org.apache.cxf.jaxws.EndpointImpl;


public class WSRegistroServizi extends RouteBuilder {

    private WSRegistroServiziImpl wsRegistroProva;
    
    @Override
    public void configure() throws Exception {
        ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();
        String port = conf.getWebservicesInteroperabilitaPort();
        String indirizzo = conf.getWebservicesIndirizzo();
        EndpointImpl endpointPubblico = (EndpointImpl) Endpoint.create(javax.xml.ws.soap.SOAPBinding.SOAP11HTTP_BINDING, wsRegistroProva);
        endpointPubblico.publish("http://" + indirizzo + ":" + port + "/nica/registroServizi");
        ContextStartup.getEndpointAvviati().add(endpointPubblico);
    }

    public WSRegistroServiziImpl getWsRegistroProva() {
        return wsRegistroProva;
    }

    public void setWsRegistroProva(WSRegistroServiziImpl wsRegistroProva) {
        this.wsRegistroProva = wsRegistroProva;
    }

    
}
