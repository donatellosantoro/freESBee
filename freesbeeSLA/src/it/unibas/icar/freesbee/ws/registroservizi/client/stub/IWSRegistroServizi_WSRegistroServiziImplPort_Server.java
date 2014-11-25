
package it.unibas.icar.freesbee.ws.registroservizi.client.stub;

import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 2.7.7
 * 2013-12-13T16:50:24.969+01:00
 * Generated source version: 2.7.7
 * 
 */
 
public class IWSRegistroServizi_WSRegistroServiziImplPort_Server{

    protected IWSRegistroServizi_WSRegistroServiziImplPort_Server() throws java.lang.Exception {
        System.out.println("Starting Server");
        Object implementor = new IWSRegistroServiziImpl();
        String address = "http://10.6.32.202:8191/ws/registroServizi";
        Endpoint.publish(address, implementor);
    }
    
    public static void main(String args[]) throws java.lang.Exception { 
        new IWSRegistroServizi_WSRegistroServiziImplPort_Server();
        System.out.println("Server ready..."); 
        
        Thread.sleep(5 * 60 * 1000); 
        System.out.println("Server exiting");
        System.exit(0);
    }
}