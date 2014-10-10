package it.unibas.silvio.persistenza;

import javax.xml.ws.WebFault;


@WebFault(name="SOAPFault", faultBean="it.unibas.icar.freesbee.ws.servizioapplicativo.SOAPFault")
public class SOAPFault extends Exception{
    
    public SOAPFault(String s) {
        super(s);
    }
    
}
