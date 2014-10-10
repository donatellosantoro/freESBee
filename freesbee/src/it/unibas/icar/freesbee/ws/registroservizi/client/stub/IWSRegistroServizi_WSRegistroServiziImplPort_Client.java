
package it.unibas.icar.freesbee.ws.registroservizi.client.stub;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.5.2
 * 2012-12-18T14:05:48.793+01:00
 * Generated source version: 2.5.2
 * 
 */
public final class IWSRegistroServizi_WSRegistroServiziImplPort_Client {

    private static final QName SERVICE_NAME = new QName("http://registroservizi.ws.freesbee.icar.unibas.it/", "WSRegistroServiziImplService");

    private IWSRegistroServizi_WSRegistroServiziImplPort_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = WSRegistroServiziImplService.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        WSRegistroServiziImplService ss = new WSRegistroServiziImplService(wsdlURL, SERVICE_NAME);
        IWSRegistroServizi port = ss.getWSRegistroServiziImplPort();  
        
        {
        System.out.println("Invoking getFruitoreServizioSpcoop...");
        it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetFruitoreServizioSpcoop _getFruitoreServizioSpcoop_parameters = null;
        java.lang.String _getFruitoreServizioSpcoop_indirizzoRegistro = "";
        try {
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetFruitoreServizioSpcoopResponse _getFruitoreServizioSpcoop__return = port.getFruitoreServizioSpcoop(_getFruitoreServizioSpcoop_parameters, _getFruitoreServizioSpcoop_indirizzoRegistro);
            System.out.println("getFruitoreServizioSpcoop.result=" + _getFruitoreServizioSpcoop__return);

        } catch (SOAPFault_Exception e) { 
            System.out.println("Expected exception: SOAPFault has occurred.");
            System.out.println(e.toString());
        } catch (DAOException_Exception e) { 
            System.out.println("Expected exception: DAOException has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking getFruitoriServizioSpcoopCorrelato...");
        it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetFruitoriServizioSpcoopCorrelato _getFruitoriServizioSpcoopCorrelato_parameters = null;
        java.lang.String _getFruitoriServizioSpcoopCorrelato_indirizzoRegistro = "";
        try {
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetFruitoriServizioSpcoopCorrelatoResponse _getFruitoriServizioSpcoopCorrelato__return = port.getFruitoriServizioSpcoopCorrelato(_getFruitoriServizioSpcoopCorrelato_parameters, _getFruitoriServizioSpcoopCorrelato_indirizzoRegistro);
            System.out.println("getFruitoriServizioSpcoopCorrelato.result=" + _getFruitoriServizioSpcoopCorrelato__return);

        } catch (SOAPFault_Exception e) { 
            System.out.println("Expected exception: SOAPFault has occurred.");
            System.out.println(e.toString());
        } catch (DAOException_Exception e) { 
            System.out.println("Expected exception: DAOException has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking getAllIdSoggettiSPCoop...");
        it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAllIdSoggettiSPCoop _getAllIdSoggettiSPCoop_parameters = null;
        java.lang.String _getAllIdSoggettiSPCoop_indirizzoRegistro = "";
        try {
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAllIdSoggettiSPCoopResponse _getAllIdSoggettiSPCoop__return = port.getAllIdSoggettiSPCoop(_getAllIdSoggettiSPCoop_parameters, _getAllIdSoggettiSPCoop_indirizzoRegistro);
            System.out.println("getAllIdSoggettiSPCoop.result=" + _getAllIdSoggettiSPCoop__return);

        } catch (SOAPFault_Exception e) { 
            System.out.println("Expected exception: SOAPFault has occurred.");
            System.out.println(e.toString());
        } catch (DAOException_Exception e) { 
            System.out.println("Expected exception: DAOException has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking getServizioSPCoopCorrelato...");
        it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetServizioSPCoopCorrelato _getServizioSPCoopCorrelato_parameters = null;
        java.lang.String _getServizioSPCoopCorrelato_indirizzoRegistro = "";
        try {
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetServizioSPCoopCorrelatoResponse _getServizioSPCoopCorrelato__return = port.getServizioSPCoopCorrelato(_getServizioSPCoopCorrelato_parameters, _getServizioSPCoopCorrelato_indirizzoRegistro);
            System.out.println("getServizioSPCoopCorrelato.result=" + _getServizioSPCoopCorrelato__return);

        } catch (SOAPFault_Exception e) { 
            System.out.println("Expected exception: SOAPFault has occurred.");
            System.out.println(e.toString());
        } catch (DAOException_Exception e) { 
            System.out.println("Expected exception: DAOException has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking existsServizioSpcoop...");
        it.unibas.icar.freesbee.ws.registroservizi.client.stub.ExistsServizioSpcoop _existsServizioSpcoop_parameters = null;
        java.lang.String _existsServizioSpcoop_indirizzoRegistro = "";
        try {
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.ExistsServizioSpcoopResponse _existsServizioSpcoop__return = port.existsServizioSpcoop(_existsServizioSpcoop_parameters, _existsServizioSpcoop_indirizzoRegistro);
            System.out.println("existsServizioSpcoop.result=" + _existsServizioSpcoop__return);

        } catch (SOAPFault_Exception e) { 
            System.out.println("Expected exception: SOAPFault has occurred.");
            System.out.println(e.toString());
        } catch (DAOException_Exception e) { 
            System.out.println("Expected exception: DAOException has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking getAllIdServiziSPCoopCorrelati...");
        it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAllIdServiziSPCoopCorrelati _getAllIdServiziSPCoopCorrelati_parameters = null;
        java.lang.String _getAllIdServiziSPCoopCorrelati_indirizzoRegistro = "";
        try {
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAllIdServiziSPCoopCorrelatiResponse _getAllIdServiziSPCoopCorrelati__return = port.getAllIdServiziSPCoopCorrelati(_getAllIdServiziSPCoopCorrelati_parameters, _getAllIdServiziSPCoopCorrelati_indirizzoRegistro);
            System.out.println("getAllIdServiziSPCoopCorrelati.result=" + _getAllIdServiziSPCoopCorrelati__return);

        } catch (SOAPFault_Exception e) { 
            System.out.println("Expected exception: SOAPFault has occurred.");
            System.out.println(e.toString());
        } catch (DAOException_Exception e) { 
            System.out.println("Expected exception: DAOException has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking existsAccordoServizio...");
        it.unibas.icar.freesbee.ws.registroservizi.client.stub.ExistsAccordoServizio _existsAccordoServizio_parameters = null;
        java.lang.String _existsAccordoServizio_indirizzoRegistro = "";
        try {
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.ExistsAccordoServizioResponse _existsAccordoServizio__return = port.existsAccordoServizio(_existsAccordoServizio_parameters, _existsAccordoServizio_indirizzoRegistro);
            System.out.println("existsAccordoServizio.result=" + _existsAccordoServizio__return);

        } catch (SOAPFault_Exception e) { 
            System.out.println("Expected exception: SOAPFault has occurred.");
            System.out.println(e.toString());
        } catch (DAOException_Exception e) { 
            System.out.println("Expected exception: DAOException has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking getAllIdServiziSPCoop...");
        it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAllIdServiziSPCoop _getAllIdServiziSPCoop_parameters = null;
        java.lang.String _getAllIdServiziSPCoop_indirizzoRegistro = "";
        try {
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAllIdServiziSPCoopResponse _getAllIdServiziSPCoop__return = port.getAllIdServiziSPCoop(_getAllIdServiziSPCoop_parameters, _getAllIdServiziSPCoop_indirizzoRegistro);
            System.out.println("getAllIdServiziSPCoop.result=" + _getAllIdServiziSPCoop__return);

        } catch (SOAPFault_Exception e) { 
            System.out.println("Expected exception: SOAPFault has occurred.");
            System.out.println(e.toString());
        } catch (DAOException_Exception e) { 
            System.out.println("Expected exception: DAOException has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking existsServizioSpcoopCorrelato...");
        it.unibas.icar.freesbee.ws.registroservizi.client.stub.ExistsServizioSpcoopCorrelato _existsServizioSpcoopCorrelato_parameters = null;
        java.lang.String _existsServizioSpcoopCorrelato_indirizzoRegistro = "";
        try {
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.ExistsServizioSpcoopCorrelatoResponse _existsServizioSpcoopCorrelato__return = port.existsServizioSpcoopCorrelato(_existsServizioSpcoopCorrelato_parameters, _existsServizioSpcoopCorrelato_indirizzoRegistro);
            System.out.println("existsServizioSpcoopCorrelato.result=" + _existsServizioSpcoopCorrelato__return);

        } catch (SOAPFault_Exception e) { 
            System.out.println("Expected exception: SOAPFault has occurred.");
            System.out.println(e.toString());
        } catch (DAOException_Exception e) { 
            System.out.println("Expected exception: DAOException has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking existsFruitoreServizioSpcoopCorrelato...");
        it.unibas.icar.freesbee.ws.registroservizi.client.stub.ExistsFruitoreServizioSpcoopCorrelato _existsFruitoreServizioSpcoopCorrelato_parameters = null;
        java.lang.String _existsFruitoreServizioSpcoopCorrelato_indirizzoRegistro = "";
        try {
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.ExistsFruitoreServizioSpcoopCorrelatoResponse _existsFruitoreServizioSpcoopCorrelato__return = port.existsFruitoreServizioSpcoopCorrelato(_existsFruitoreServizioSpcoopCorrelato_parameters, _existsFruitoreServizioSpcoopCorrelato_indirizzoRegistro);
            System.out.println("existsFruitoreServizioSpcoopCorrelato.result=" + _existsFruitoreServizioSpcoopCorrelato__return);

        } catch (SOAPFault_Exception e) { 
            System.out.println("Expected exception: SOAPFault has occurred.");
            System.out.println(e.toString());
        } catch (DAOException_Exception e) { 
            System.out.println("Expected exception: DAOException has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking getAccordoServizio...");
        it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAccordoServizio _getAccordoServizio_parameters = null;
        java.lang.String _getAccordoServizio_indirizzoRegistro = "";
        try {
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAccordoServizioResponse _getAccordoServizio__return = port.getAccordoServizio(_getAccordoServizio_parameters, _getAccordoServizio_indirizzoRegistro);
            System.out.println("getAccordoServizio.result=" + _getAccordoServizio__return);

        } catch (SOAPFault_Exception e) { 
            System.out.println("Expected exception: SOAPFault has occurred.");
            System.out.println(e.toString());
        } catch (DAOException_Exception e) { 
            System.out.println("Expected exception: DAOException has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking getAllIdAccordiServizio...");
        it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAllIdAccordiServizio _getAllIdAccordiServizio_parameters = null;
        java.lang.String _getAllIdAccordiServizio_indirizzoRegistro = "";
        try {
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAllIdAccordiServizioResponse _getAllIdAccordiServizio__return = port.getAllIdAccordiServizio(_getAllIdAccordiServizio_parameters, _getAllIdAccordiServizio_indirizzoRegistro);
            System.out.println("getAllIdAccordiServizio.result=" + _getAllIdAccordiServizio__return);

        } catch (SOAPFault_Exception e) { 
            System.out.println("Expected exception: SOAPFault has occurred.");
            System.out.println(e.toString());
        } catch (DAOException_Exception e) { 
            System.out.println("Expected exception: DAOException has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking getServizioSPCoopCorrelatoByAccordo...");
        it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetServizioSPCoopCorrelatoByAccordo _getServizioSPCoopCorrelatoByAccordo_parameters = null;
        java.lang.String _getServizioSPCoopCorrelatoByAccordo_indirizzoRegistro = "";
        try {
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetServizioSPCoopCorrelatoByAccordoResponse _getServizioSPCoopCorrelatoByAccordo__return = port.getServizioSPCoopCorrelatoByAccordo(_getServizioSPCoopCorrelatoByAccordo_parameters, _getServizioSPCoopCorrelatoByAccordo_indirizzoRegistro);
            System.out.println("getServizioSPCoopCorrelatoByAccordo.result=" + _getServizioSPCoopCorrelatoByAccordo__return);

        } catch (SOAPFault_Exception e) { 
            System.out.println("Expected exception: SOAPFault has occurred.");
            System.out.println(e.toString());
        } catch (DAOException_Exception e) { 
            System.out.println("Expected exception: DAOException has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking getFruitoriServizioSpcoop...");
        it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetFruitoriServizioSpcoop _getFruitoriServizioSpcoop_parameters = null;
        java.lang.String _getFruitoriServizioSpcoop_indirizzoRegistro = "";
        try {
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetFruitoriServizioSpcoopResponse _getFruitoriServizioSpcoop__return = port.getFruitoriServizioSpcoop(_getFruitoriServizioSpcoop_parameters, _getFruitoriServizioSpcoop_indirizzoRegistro);
            System.out.println("getFruitoriServizioSpcoop.result=" + _getFruitoriServizioSpcoop__return);

        } catch (SOAPFault_Exception e) { 
            System.out.println("Expected exception: SOAPFault has occurred.");
            System.out.println(e.toString());
        } catch (DAOException_Exception e) { 
            System.out.println("Expected exception: DAOException has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking getServizioSPCoop...");
        it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetServizioSPCoop _getServizioSPCoop_parameters = null;
        java.lang.String _getServizioSPCoop_indirizzoRegistro = "";
        try {
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetServizioSPCoopResponse _getServizioSPCoop__return = port.getServizioSPCoop(_getServizioSPCoop_parameters, _getServizioSPCoop_indirizzoRegistro);
            System.out.println("getServizioSPCoop.result=" + _getServizioSPCoop__return);

        } catch (SOAPFault_Exception e) { 
            System.out.println("Expected exception: SOAPFault has occurred.");
            System.out.println(e.toString());
        } catch (DAOException_Exception e) { 
            System.out.println("Expected exception: DAOException has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking getFruitoreServizioSpcoopCorrelato...");
        it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetFruitoreServizioSpcoopCorrelato _getFruitoreServizioSpcoopCorrelato_parameters = null;
        java.lang.String _getFruitoreServizioSpcoopCorrelato_indirizzoRegistro = "";
        try {
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetFruitoreServizioSpcoopCorrelatoResponse _getFruitoreServizioSpcoopCorrelato__return = port.getFruitoreServizioSpcoopCorrelato(_getFruitoreServizioSpcoopCorrelato_parameters, _getFruitoreServizioSpcoopCorrelato_indirizzoRegistro);
            System.out.println("getFruitoreServizioSpcoopCorrelato.result=" + _getFruitoreServizioSpcoopCorrelato__return);

        } catch (SOAPFault_Exception e) { 
            System.out.println("Expected exception: SOAPFault has occurred.");
            System.out.println(e.toString());
        } catch (DAOException_Exception e) { 
            System.out.println("Expected exception: DAOException has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking getSoggettoSPCoop...");
        it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetSoggettoSPCoop _getSoggettoSPCoop_parameters = null;
        java.lang.String _getSoggettoSPCoop_indirizzoRegistro = "";
        try {
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetSoggettoSPCoopResponse _getSoggettoSPCoop__return = port.getSoggettoSPCoop(_getSoggettoSPCoop_parameters, _getSoggettoSPCoop_indirizzoRegistro);
            System.out.println("getSoggettoSPCoop.result=" + _getSoggettoSPCoop__return);

        } catch (SOAPFault_Exception e) { 
            System.out.println("Expected exception: SOAPFault has occurred.");
            System.out.println(e.toString());
        } catch (DAOException_Exception e) { 
            System.out.println("Expected exception: DAOException has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking existsFruitoreServizioSpcoop...");
        it.unibas.icar.freesbee.ws.registroservizi.client.stub.ExistsFruitoreServizioSpcoop _existsFruitoreServizioSpcoop_parameters = null;
        java.lang.String _existsFruitoreServizioSpcoop_indirizzoRegistro = "";
        try {
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.ExistsFruitoreServizioSpcoopResponse _existsFruitoreServizioSpcoop__return = port.existsFruitoreServizioSpcoop(_existsFruitoreServizioSpcoop_parameters, _existsFruitoreServizioSpcoop_indirizzoRegistro);
            System.out.println("existsFruitoreServizioSpcoop.result=" + _existsFruitoreServizioSpcoop__return);

        } catch (SOAPFault_Exception e) { 
            System.out.println("Expected exception: SOAPFault has occurred.");
            System.out.println(e.toString());
        } catch (DAOException_Exception e) { 
            System.out.println("Expected exception: DAOException has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking existsSoggettoSPCoop...");
        it.unibas.icar.freesbee.ws.registroservizi.client.stub.ExistsSoggettoSPCoop _existsSoggettoSPCoop_parameters = null;
        java.lang.String _existsSoggettoSPCoop_indirizzoRegistro = "";
        try {
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.ExistsSoggettoSPCoopResponse _existsSoggettoSPCoop__return = port.existsSoggettoSPCoop(_existsSoggettoSPCoop_parameters, _existsSoggettoSPCoop_indirizzoRegistro);
            System.out.println("existsSoggettoSPCoop.result=" + _existsSoggettoSPCoop__return);

        } catch (SOAPFault_Exception e) { 
            System.out.println("Expected exception: SOAPFault has occurred.");
            System.out.println(e.toString());
        } catch (DAOException_Exception e) { 
            System.out.println("Expected exception: DAOException has occurred.");
            System.out.println(e.toString());
        }
            }

        System.exit(0);
    }

}
