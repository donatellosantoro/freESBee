package it.unibas.freesbee.ge.web.persistenza.soap;

import javax.xml.namespace.QName;

public class DAOCostanti {

    public static final String GE_CONTROL_PROTOCOL = "GEControlProtocol";

    public static final String URL_WSDL_WEB_AUTENTICAZIONE = "freesbeege/ws/web/autenticazione?wsdl";
    public static final QName SERVICE_NAME_AUTENTICAZIONE = new QName("http://ge.freesbee.unibas.it/", "WSAutenticazioneImplService");

    public static final String URL_WSDL_WEB_CONFIGURAZIONE = "freesbeege/ws/web/configurazione?wsdl";
    public static final QName SERVICE_NAME_CONFIGURAZIONE = new QName("http://ge.freesbee.unibas.it/", "WSConfiguraImplService");

    public static final String URL_WSDL_WEB_CATEGORIA_EVENTI_ESTERNA = "freesbeege/ws/web/categoriaEventiEsterna?wsdl";
    public static final QName SERVICE_NAME_CATEGORIA_EVENTI_ESTERNA = new QName("http://ge.freesbee.unibas.it/", "WSCategoriaEventiEsternaImplService");

    public static final String URL_WSDL_WEB_CATEGORIA_EVENTI_INTERNA = "freesbeege/ws/web/categoriaEventiInterna?wsdl";
    public static final QName SERVICE_NAME_CATEGORIA_EVENTI_INTERNA = new QName("http://ge.freesbee.unibas.it/", "WSCategoriaEventiInternaImplService");

    public static final String URL_WSDL_WEB_GESTORE_EVENTI = "freesbeege/ws/web/gestoreEventi?wsdl";
    public static final QName SERVICE_NAME_GESTORE_EVENTI = new QName("http://ge.freesbee.unibas.it/", "WSGestioneGestoreEventiImplService");


}