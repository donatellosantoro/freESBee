
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package it.unibas.icar.freesbee.ws.registroservizi.client.stub;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.7.7
 * 2013-12-13T16:50:24.928+01:00
 * Generated source version: 2.7.7
 * 
 */

@javax.jws.WebService(
                      serviceName = "WSRegistroServiziImplService",
                      portName = "WSRegistroServiziImplPort",
                      targetNamespace = "http://registroservizi.ws.freesbee.icar.unibas.it/",
                      wsdlLocation = "/Users/michele/Desktop/gestioneGareServerICARLAB/registroServizi_3.wsdl",
                      endpointInterface = "it.unibas.icar.freesbee.ws.registroservizi.client.stub.IWSRegistroServizi")
                      
public class IWSRegistroServiziImpl implements IWSRegistroServizi {

    private static final Logger LOG = Logger.getLogger(IWSRegistroServiziImpl.class.getName());

    /* (non-Javadoc)
     * @see it.unibas.icar.freesbee.ws.registroservizi.client.stub.IWSRegistroServizi#getAllIdServiziSPCoopCorrelati(it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAllIdServiziSPCoopCorrelati  parameters ,)java.lang.String  indirizzoRegistro )*
     */
    public it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAllIdServiziSPCoopCorrelatiResponse getAllIdServiziSPCoopCorrelati(GetAllIdServiziSPCoopCorrelati parameters,java.lang.String indirizzoRegistro) throws SOAPFault_Exception , DAOException_Exception    { 
        LOG.info("Executing operation getAllIdServiziSPCoopCorrelati");
        System.out.println(parameters);
        System.out.println(indirizzoRegistro);
        try {
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAllIdServiziSPCoopCorrelatiResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new SOAPFault_Exception("SOAPFault...");
        //throw new DAOException_Exception("DAOException...");
    }

    /* (non-Javadoc)
     * @see it.unibas.icar.freesbee.ws.registroservizi.client.stub.IWSRegistroServizi#existsServizioSpcoop(it.unibas.icar.freesbee.ws.registroservizi.client.stub.ExistsServizioSpcoop  parameters ,)java.lang.String  indirizzoRegistro )*
     */
    public it.unibas.icar.freesbee.ws.registroservizi.client.stub.ExistsServizioSpcoopResponse existsServizioSpcoop(ExistsServizioSpcoop parameters,java.lang.String indirizzoRegistro) throws SOAPFault_Exception , DAOException_Exception    { 
        LOG.info("Executing operation existsServizioSpcoop");
        System.out.println(parameters);
        System.out.println(indirizzoRegistro);
        try {
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.ExistsServizioSpcoopResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new SOAPFault_Exception("SOAPFault...");
        //throw new DAOException_Exception("DAOException...");
    }

    /* (non-Javadoc)
     * @see it.unibas.icar.freesbee.ws.registroservizi.client.stub.IWSRegistroServizi#existsFruitoreServizioSpcoopCorrelato(it.unibas.icar.freesbee.ws.registroservizi.client.stub.ExistsFruitoreServizioSpcoopCorrelato  parameters ,)java.lang.String  indirizzoRegistro )*
     */
    public it.unibas.icar.freesbee.ws.registroservizi.client.stub.ExistsFruitoreServizioSpcoopCorrelatoResponse existsFruitoreServizioSpcoopCorrelato(ExistsFruitoreServizioSpcoopCorrelato parameters,java.lang.String indirizzoRegistro) throws SOAPFault_Exception , DAOException_Exception    { 
        LOG.info("Executing operation existsFruitoreServizioSpcoopCorrelato");
        System.out.println(parameters);
        System.out.println(indirizzoRegistro);
        try {
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.ExistsFruitoreServizioSpcoopCorrelatoResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new SOAPFault_Exception("SOAPFault...");
        //throw new DAOException_Exception("DAOException...");
    }

    /* (non-Javadoc)
     * @see it.unibas.icar.freesbee.ws.registroservizi.client.stub.IWSRegistroServizi#getFruitoriServizioSpcoopCorrelato(it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetFruitoriServizioSpcoopCorrelato  parameters ,)java.lang.String  indirizzoRegistro )*
     */
    public it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetFruitoriServizioSpcoopCorrelatoResponse getFruitoriServizioSpcoopCorrelato(GetFruitoriServizioSpcoopCorrelato parameters,java.lang.String indirizzoRegistro) throws SOAPFault_Exception , DAOException_Exception    { 
        LOG.info("Executing operation getFruitoriServizioSpcoopCorrelato");
        System.out.println(parameters);
        System.out.println(indirizzoRegistro);
        try {
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetFruitoriServizioSpcoopCorrelatoResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new SOAPFault_Exception("SOAPFault...");
        //throw new DAOException_Exception("DAOException...");
    }

    /* (non-Javadoc)
     * @see it.unibas.icar.freesbee.ws.registroservizi.client.stub.IWSRegistroServizi#getAccordoServizio(it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAccordoServizio  parameters ,)java.lang.String  indirizzoRegistro )*
     */
    public it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAccordoServizioResponse getAccordoServizio(GetAccordoServizio parameters,java.lang.String indirizzoRegistro) throws SOAPFault_Exception , DAOException_Exception    { 
        LOG.info("Executing operation getAccordoServizio");
        System.out.println(parameters);
        System.out.println(indirizzoRegistro);
        try {
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAccordoServizioResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new SOAPFault_Exception("SOAPFault...");
        //throw new DAOException_Exception("DAOException...");
    }

    /* (non-Javadoc)
     * @see it.unibas.icar.freesbee.ws.registroservizi.client.stub.IWSRegistroServizi#existsServizioSpcoopCorrelato(it.unibas.icar.freesbee.ws.registroservizi.client.stub.ExistsServizioSpcoopCorrelato  parameters ,)java.lang.String  indirizzoRegistro )*
     */
    public it.unibas.icar.freesbee.ws.registroservizi.client.stub.ExistsServizioSpcoopCorrelatoResponse existsServizioSpcoopCorrelato(ExistsServizioSpcoopCorrelato parameters,java.lang.String indirizzoRegistro) throws SOAPFault_Exception , DAOException_Exception    { 
        LOG.info("Executing operation existsServizioSpcoopCorrelato");
        System.out.println(parameters);
        System.out.println(indirizzoRegistro);
        try {
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.ExistsServizioSpcoopCorrelatoResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new SOAPFault_Exception("SOAPFault...");
        //throw new DAOException_Exception("DAOException...");
    }

    /* (non-Javadoc)
     * @see it.unibas.icar.freesbee.ws.registroservizi.client.stub.IWSRegistroServizi#getAllIdSoggettiSPCoop(it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAllIdSoggettiSPCoop  parameters ,)java.lang.String  indirizzoRegistro )*
     */
    public it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAllIdSoggettiSPCoopResponse getAllIdSoggettiSPCoop(GetAllIdSoggettiSPCoop parameters,java.lang.String indirizzoRegistro) throws SOAPFault_Exception , DAOException_Exception    { 
        LOG.info("Executing operation getAllIdSoggettiSPCoop");
        System.out.println(parameters);
        System.out.println(indirizzoRegistro);
        try {
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAllIdSoggettiSPCoopResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new SOAPFault_Exception("SOAPFault...");
        //throw new DAOException_Exception("DAOException...");
    }

    /* (non-Javadoc)
     * @see it.unibas.icar.freesbee.ws.registroservizi.client.stub.IWSRegistroServizi#getAllIdAccordiServizio(it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAllIdAccordiServizio  parameters ,)java.lang.String  indirizzoRegistro )*
     */
    public it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAllIdAccordiServizioResponse getAllIdAccordiServizio(GetAllIdAccordiServizio parameters,java.lang.String indirizzoRegistro) throws SOAPFault_Exception , DAOException_Exception    { 
        LOG.info("Executing operation getAllIdAccordiServizio");
        System.out.println(parameters);
        System.out.println(indirizzoRegistro);
        try {
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAllIdAccordiServizioResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new SOAPFault_Exception("SOAPFault...");
        //throw new DAOException_Exception("DAOException...");
    }

    /* (non-Javadoc)
     * @see it.unibas.icar.freesbee.ws.registroservizi.client.stub.IWSRegistroServizi#getFruitoreServizioSpcoop(it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetFruitoreServizioSpcoop  parameters ,)java.lang.String  indirizzoRegistro )*
     */
    public it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetFruitoreServizioSpcoopResponse getFruitoreServizioSpcoop(GetFruitoreServizioSpcoop parameters,java.lang.String indirizzoRegistro) throws SOAPFault_Exception , DAOException_Exception    { 
        LOG.info("Executing operation getFruitoreServizioSpcoop");
        System.out.println(parameters);
        System.out.println(indirizzoRegistro);
        try {
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetFruitoreServizioSpcoopResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new SOAPFault_Exception("SOAPFault...");
        //throw new DAOException_Exception("DAOException...");
    }

    /* (non-Javadoc)
     * @see it.unibas.icar.freesbee.ws.registroservizi.client.stub.IWSRegistroServizi#getAllIdServiziSPCoop(it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAllIdServiziSPCoop  parameters ,)java.lang.String  indirizzoRegistro )*
     */
    public it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAllIdServiziSPCoopResponse getAllIdServiziSPCoop(GetAllIdServiziSPCoop parameters,java.lang.String indirizzoRegistro) throws SOAPFault_Exception , DAOException_Exception    { 
        LOG.info("Executing operation getAllIdServiziSPCoop");
        System.out.println(parameters);
        System.out.println(indirizzoRegistro);
        try {
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAllIdServiziSPCoopResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new SOAPFault_Exception("SOAPFault...");
        //throw new DAOException_Exception("DAOException...");
    }

    /* (non-Javadoc)
     * @see it.unibas.icar.freesbee.ws.registroservizi.client.stub.IWSRegistroServizi#getServizioSPCoopCorrelato(it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetServizioSPCoopCorrelato  parameters ,)java.lang.String  indirizzoRegistro )*
     */
    public it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetServizioSPCoopCorrelatoResponse getServizioSPCoopCorrelato(GetServizioSPCoopCorrelato parameters,java.lang.String indirizzoRegistro) throws SOAPFault_Exception , DAOException_Exception    { 
        LOG.info("Executing operation getServizioSPCoopCorrelato");
        System.out.println(parameters);
        System.out.println(indirizzoRegistro);
        try {
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetServizioSPCoopCorrelatoResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new SOAPFault_Exception("SOAPFault...");
        //throw new DAOException_Exception("DAOException...");
    }

    /* (non-Javadoc)
     * @see it.unibas.icar.freesbee.ws.registroservizi.client.stub.IWSRegistroServizi#existsAccordoServizio(it.unibas.icar.freesbee.ws.registroservizi.client.stub.ExistsAccordoServizio  parameters ,)java.lang.String  indirizzoRegistro )*
     */
    public it.unibas.icar.freesbee.ws.registroservizi.client.stub.ExistsAccordoServizioResponse existsAccordoServizio(ExistsAccordoServizio parameters,java.lang.String indirizzoRegistro) throws SOAPFault_Exception , DAOException_Exception    { 
        LOG.info("Executing operation existsAccordoServizio");
        System.out.println(parameters);
        System.out.println(indirizzoRegistro);
        try {
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.ExistsAccordoServizioResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new SOAPFault_Exception("SOAPFault...");
        //throw new DAOException_Exception("DAOException...");
    }

    /* (non-Javadoc)
     * @see it.unibas.icar.freesbee.ws.registroservizi.client.stub.IWSRegistroServizi#getSoggettoSPCoop(it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetSoggettoSPCoop  parameters ,)java.lang.String  indirizzoRegistro )*
     */
    public it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetSoggettoSPCoopResponse getSoggettoSPCoop(GetSoggettoSPCoop parameters,java.lang.String indirizzoRegistro) throws SOAPFault_Exception , DAOException_Exception    { 
        LOG.info("Executing operation getSoggettoSPCoop");
        System.out.println(parameters);
        System.out.println(indirizzoRegistro);
        try {
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetSoggettoSPCoopResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new SOAPFault_Exception("SOAPFault...");
        //throw new DAOException_Exception("DAOException...");
    }

    /* (non-Javadoc)
     * @see it.unibas.icar.freesbee.ws.registroservizi.client.stub.IWSRegistroServizi#getServizioSPCoopCorrelatoByAccordo(it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetServizioSPCoopCorrelatoByAccordo  parameters ,)java.lang.String  indirizzoRegistro )*
     */
    public it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetServizioSPCoopCorrelatoByAccordoResponse getServizioSPCoopCorrelatoByAccordo(GetServizioSPCoopCorrelatoByAccordo parameters,java.lang.String indirizzoRegistro) throws SOAPFault_Exception , DAOException_Exception    { 
        LOG.info("Executing operation getServizioSPCoopCorrelatoByAccordo");
        System.out.println(parameters);
        System.out.println(indirizzoRegistro);
        try {
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetServizioSPCoopCorrelatoByAccordoResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new SOAPFault_Exception("SOAPFault...");
        //throw new DAOException_Exception("DAOException...");
    }

    /* (non-Javadoc)
     * @see it.unibas.icar.freesbee.ws.registroservizi.client.stub.IWSRegistroServizi#getServizioSPCoop(it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetServizioSPCoop  parameters ,)java.lang.String  indirizzoRegistro )*
     */
    public it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetServizioSPCoopResponse getServizioSPCoop(GetServizioSPCoop parameters,java.lang.String indirizzoRegistro) throws SOAPFault_Exception , DAOException_Exception    { 
        LOG.info("Executing operation getServizioSPCoop");
        System.out.println(parameters);
        System.out.println(indirizzoRegistro);
        try {
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetServizioSPCoopResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new SOAPFault_Exception("SOAPFault...");
        //throw new DAOException_Exception("DAOException...");
    }

    /* (non-Javadoc)
     * @see it.unibas.icar.freesbee.ws.registroservizi.client.stub.IWSRegistroServizi#getFruitoreServizioSpcoopCorrelato(it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetFruitoreServizioSpcoopCorrelato  parameters ,)java.lang.String  indirizzoRegistro )*
     */
    public it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetFruitoreServizioSpcoopCorrelatoResponse getFruitoreServizioSpcoopCorrelato(GetFruitoreServizioSpcoopCorrelato parameters,java.lang.String indirizzoRegistro) throws SOAPFault_Exception , DAOException_Exception    { 
        LOG.info("Executing operation getFruitoreServizioSpcoopCorrelato");
        System.out.println(parameters);
        System.out.println(indirizzoRegistro);
        try {
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetFruitoreServizioSpcoopCorrelatoResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new SOAPFault_Exception("SOAPFault...");
        //throw new DAOException_Exception("DAOException...");
    }

    /* (non-Javadoc)
     * @see it.unibas.icar.freesbee.ws.registroservizi.client.stub.IWSRegistroServizi#existsFruitoreServizioSpcoop(it.unibas.icar.freesbee.ws.registroservizi.client.stub.ExistsFruitoreServizioSpcoop  parameters ,)java.lang.String  indirizzoRegistro )*
     */
    public it.unibas.icar.freesbee.ws.registroservizi.client.stub.ExistsFruitoreServizioSpcoopResponse existsFruitoreServizioSpcoop(ExistsFruitoreServizioSpcoop parameters,java.lang.String indirizzoRegistro) throws SOAPFault_Exception , DAOException_Exception    { 
        LOG.info("Executing operation existsFruitoreServizioSpcoop");
        System.out.println(parameters);
        System.out.println(indirizzoRegistro);
        try {
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.ExistsFruitoreServizioSpcoopResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new SOAPFault_Exception("SOAPFault...");
        //throw new DAOException_Exception("DAOException...");
    }

    /* (non-Javadoc)
     * @see it.unibas.icar.freesbee.ws.registroservizi.client.stub.IWSRegistroServizi#existsSoggettoSPCoop(it.unibas.icar.freesbee.ws.registroservizi.client.stub.ExistsSoggettoSPCoop  parameters ,)java.lang.String  indirizzoRegistro )*
     */
    public it.unibas.icar.freesbee.ws.registroservizi.client.stub.ExistsSoggettoSPCoopResponse existsSoggettoSPCoop(ExistsSoggettoSPCoop parameters,java.lang.String indirizzoRegistro) throws SOAPFault_Exception , DAOException_Exception    { 
        LOG.info("Executing operation existsSoggettoSPCoop");
        System.out.println(parameters);
        System.out.println(indirizzoRegistro);
        try {
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.ExistsSoggettoSPCoopResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new SOAPFault_Exception("SOAPFault...");
        //throw new DAOException_Exception("DAOException...");
    }

    /* (non-Javadoc)
     * @see it.unibas.icar.freesbee.ws.registroservizi.client.stub.IWSRegistroServizi#getFruitoriServizioSpcoop(it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetFruitoriServizioSpcoop  parameters ,)java.lang.String  indirizzoRegistro )*
     */
    public it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetFruitoriServizioSpcoopResponse getFruitoriServizioSpcoop(GetFruitoriServizioSpcoop parameters,java.lang.String indirizzoRegistro) throws SOAPFault_Exception , DAOException_Exception    { 
        LOG.info("Executing operation getFruitoriServizioSpcoop");
        System.out.println(parameters);
        System.out.println(indirizzoRegistro);
        try {
            it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetFruitoriServizioSpcoopResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new SOAPFault_Exception("SOAPFault...");
        //throw new DAOException_Exception("DAOException...");
    }

}