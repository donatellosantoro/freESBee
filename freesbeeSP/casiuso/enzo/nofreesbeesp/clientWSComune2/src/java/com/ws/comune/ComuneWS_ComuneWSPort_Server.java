
package com.ws.comune;

import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 2.7.4
 * 2013-06-15T17:27:07.873+02:00
 * Generated source version: 2.7.4
 * 
 */
 
public class ComuneWS_ComuneWSPort_Server{

    protected ComuneWS_ComuneWSPort_Server() throws java.lang.Exception {
        System.out.println("Starting Server");
        Object implementor = new ComuneWSImpl();
//        String address = "http://localhost:8080/comuneWSApplication/ComuneWS";
        String address = "http://sp.example.unibas:8080/comuneWSApplication/ComuneWS";
        Endpoint.publish(address, implementor);
    }
    
    public static void main(String args[]) throws java.lang.Exception { 
        new ComuneWS_ComuneWSPort_Server();
        System.out.println("Server ready..."); 
        
        Thread.sleep(5 * 60 * 1000); 
        System.out.println("Server exiting");
        System.exit(0);
    }
}
