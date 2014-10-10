package it.unibas.icar.interfreesbee.freesbee.registroservizi;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import it.unibas.guicefreesbee.ContextStartup;
import it.unibas.icar.freesbee.modello.ConfigurazioneStatico;
import java.io.IOException;
import java.net.ServerSocket;
import javax.xml.ws.Endpoint;
import org.apache.camel.builder.RouteBuilder;
import org.apache.cxf.jaxws.EndpointImpl;

@Singleton
public class WSRegistroServizi extends RouteBuilder {

    @Inject
    private IWSRegistroServizi wsRegistro;

    @Override
    public void configure() throws Exception {
        ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();
        String port = conf.getWebservicesInteroperabilitaPort();
        if (port != null && !port.equals("") && !port.equals("0")) {
            String indirizzo = conf.getWebservicesIndirizzo();
            if (portaAvviata(port)) {
                return;
            }
            EndpointImpl endpointPubblico = (EndpointImpl) Endpoint.create(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING, wsRegistro);
            endpointPubblico.publish("http://" + indirizzo + ":" + port + "/ws/registroServizi");
            ContextStartup.getEndpointAvviati().add(endpointPubblico);
        }
    }

    public IWSRegistroServizi getWsRegistro() {
        return wsRegistro;
    }

    public void setWsRegistro(IWSRegistroServizi wsRegistro) {
        this.wsRegistro = wsRegistro;
    }

    private boolean portaAvviata(String port) {
        ServerSocket socket = null;
        try {
            socket = new ServerSocket(Integer.parseInt(port));
            return false;
        } catch (IOException e) {
            return true;
        } finally {
            if (socket != null)
                try {
                    socket.close();
                } catch (IOException e) {
                }
        }
    }
}
