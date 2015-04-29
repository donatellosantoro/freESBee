package it.unibas.icar.freesbee.ws.echoservice;

import com.google.inject.Singleton;
import it.unibas.icar.freesbee.modello.ConfigurazioneStatico;
import it.unibas.guicefreesbee.ContextStartup;
import it.unibas.icar.freesbee.utilita.XMLJDomUtil;
import org.apache.camel.builder.RouteBuilder;
import org.apache.cxf.jaxws.EndpointImpl;
import javax.xml.ws.Endpoint;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.apache.cxf.binding.BindingFactoryManager;
import org.apache.cxf.jaxrs.JAXRSBindingFactory;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.slf4j.LoggerFactory;

@Singleton
public class WSEchoService extends RouteBuilder {

//    private static Log logger = LogFactory.getLog(WSEchoService.class);
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(WSEchoService.class.getName());

    @Override
    public void configure() throws Exception {
        ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();
        String port = conf.getWebservicesPort();
        String indirizzo = conf.getWebservicesIndirizzo();
        EndpointImpl endpoint = (EndpointImpl) Endpoint.create(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING, new WSEchoServiceImpl());
        endpoint.publish("http://" + indirizzo + ":" + port + "/ws/echoService");
        
        ContextStartup.getEndpointAvviati().add(endpoint);
//        JAXRSServerFactoryBean factoryBean = new JAXRSServerFactoryBean();
//        factoryBean.setResourceClasses(IWSEchoService.class);
//        factoryBean.setResourceProvider(IWSEchoService.class,new SingletonResourceProvider(new WSEchoServiceImpl()));
//        factoryBean.setAddress("http://" + indirizzo + ":" + port + "/ws/echoService");
//        BindingFactoryManager factoryManager = factoryBean.getBus().getExtension(BindingFactoryManager.class);
//        JAXRSBindingFactory factory = new JAXRSBindingFactory(factoryBean.getBus());
//        factoryManager.registerBindingFactory(JAXRSBindingFactory.JAXRS_BINDING_ID, factory);
//        factoryBean.create();
        

    }
}
