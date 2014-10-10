package it.unibas.freesbee.ge.web.ws.stub;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {

    private final static QName _CambiaPassword_QNAME = new QName("http://ge.freesbee.unibas.it/", "cambiaPassword");
    private final static QName _Autentica_QNAME = new QName("http://ge.freesbee.unibas.it/", "autentica");
    private final static QName _AutenticaResponse_QNAME = new QName("http://ge.freesbee.unibas.it/", "autenticaResponse");
    private final static QName _SOAPFault_QNAME = new QName("http://ge.freesbee.unibas.it/", "SOAPFault");
    private final static QName _CambiaPasswordResponse_QNAME = new QName("http://ge.freesbee.unibas.it/", "cambiaPasswordResponse");

    public ObjectFactory() {
    }

    public CambiaPasswordResponse createCambiaPasswordResponse() {
        return new CambiaPasswordResponse();
    }

    public SOAPFault createSOAPFault() {
        return new SOAPFault();
    }

    public CambiaPassword createCambiaPassword() {
        return new CambiaPassword();
    }

    public AutenticaResponse createAutenticaResponse() {
        return new AutenticaResponse();
    }

    public Autentica createAutentica() {
        return new Autentica();
    }

    @XmlElementDecl(namespace = "http://ge.freesbee.unibas.it/", name = "cambiaPassword")
    public JAXBElement<CambiaPassword> createCambiaPassword(CambiaPassword value) {
        return new JAXBElement<CambiaPassword>(_CambiaPassword_QNAME, CambiaPassword.class, null, value);
    }

    @XmlElementDecl(namespace = "http://ge.freesbee.unibas.it/", name = "autentica")
    public JAXBElement<Autentica> createAutentica(Autentica value) {
        return new JAXBElement<Autentica>(_Autentica_QNAME, Autentica.class, null, value);
    }

    @XmlElementDecl(namespace = "http://ge.freesbee.unibas.it/", name = "autenticaResponse")
    public JAXBElement<AutenticaResponse> createAutenticaResponse(AutenticaResponse value) {
        return new JAXBElement<AutenticaResponse>(_AutenticaResponse_QNAME, AutenticaResponse.class, null, value);
    }

    @XmlElementDecl(namespace = "http://ge.freesbee.unibas.it/", name = "SOAPFault")
    public JAXBElement<SOAPFault> createSOAPFault(SOAPFault value) {
        return new JAXBElement<SOAPFault>(_SOAPFault_QNAME, SOAPFault.class, null, value);
    }

    @XmlElementDecl(namespace = "http://ge.freesbee.unibas.it/", name = "cambiaPasswordResponse")
    public JAXBElement<CambiaPasswordResponse> createCambiaPasswordResponse(CambiaPasswordResponse value) {
        return new JAXBElement<CambiaPasswordResponse>(_CambiaPasswordResponse_QNAME, CambiaPasswordResponse.class, null, value);
    }
    private final static QName _ModificaConfigurazioneSP_QNAME = new QName("http://ge.freesbee.unibas.it/", "modificaConfigurazioneSP");
    private final static QName _CaricaConfigurazione_QNAME = new QName("http://ge.freesbee.unibas.it/", "caricaConfigurazione");
    private final static QName _ModificaConfigurazioneGEResponse_QNAME = new QName("http://ge.freesbee.unibas.it/", "modificaConfigurazioneGEResponse");
    private final static QName _ModificaConfigurazioneSPResponse_QNAME = new QName("http://ge.freesbee.unibas.it/", "modificaConfigurazioneSPResponse");
    private final static QName _ModificaConfigurazioneGE_QNAME = new QName("http://ge.freesbee.unibas.it/", "modificaConfigurazioneGE");
    private final static QName _CaricaConfigurazioneResponse_QNAME = new QName("http://ge.freesbee.unibas.it/", "caricaConfigurazioneResponse");

    public ConfigurazioneSP createConfigurazioneSP() {
        return new ConfigurazioneSP();
    }

    public ModificaConfigurazioneGEResponse createModificaConfigurazioneGEResponse() {
        return new ModificaConfigurazioneGEResponse();
    }

    public ModificaConfigurazioneSP createModificaConfigurazioneSP() {
        return new ModificaConfigurazioneSP();
    }

    public ModificaConfigurazioneSPResponse createModificaConfigurazioneSPResponse() {
        return new ModificaConfigurazioneSPResponse();
    }

    public ModificaConfigurazioneGE createModificaConfigurazioneGE() {
        return new ModificaConfigurazioneGE();
    }

    public DatiConfigurazione createDatiConfigurazione() {
        return new DatiConfigurazione();
    }

    public CaricaConfigurazione createCaricaConfigurazione() {
        return new CaricaConfigurazione();
    }

    public Configurazione createConfigurazione() {
        return new Configurazione();
    }

    public CaricaConfigurazioneResponse createCaricaConfigurazioneResponse() {
        return new CaricaConfigurazioneResponse();
    }

    @XmlElementDecl(namespace = "http://ge.freesbee.unibas.it/", name = "modificaConfigurazioneSP")
    public JAXBElement<ModificaConfigurazioneSP> createModificaConfigurazioneSP(ModificaConfigurazioneSP value) {
        return new JAXBElement<ModificaConfigurazioneSP>(_ModificaConfigurazioneSP_QNAME, ModificaConfigurazioneSP.class, null, value);
    }

    @XmlElementDecl(namespace = "http://ge.freesbee.unibas.it/", name = "caricaConfigurazione")
    public JAXBElement<CaricaConfigurazione> createCaricaConfigurazione(CaricaConfigurazione value) {
        return new JAXBElement<CaricaConfigurazione>(_CaricaConfigurazione_QNAME, CaricaConfigurazione.class, null, value);
    }

    @XmlElementDecl(namespace = "http://ge.freesbee.unibas.it/", name = "modificaConfigurazioneGEResponse")
    public JAXBElement<ModificaConfigurazioneGEResponse> createModificaConfigurazioneGEResponse(ModificaConfigurazioneGEResponse value) {
        return new JAXBElement<ModificaConfigurazioneGEResponse>(_ModificaConfigurazioneGEResponse_QNAME, ModificaConfigurazioneGEResponse.class, null, value);
    }

    @XmlElementDecl(namespace = "http://ge.freesbee.unibas.it/", name = "modificaConfigurazioneSPResponse")
    public JAXBElement<ModificaConfigurazioneSPResponse> createModificaConfigurazioneSPResponse(ModificaConfigurazioneSPResponse value) {
        return new JAXBElement<ModificaConfigurazioneSPResponse>(_ModificaConfigurazioneSPResponse_QNAME, ModificaConfigurazioneSPResponse.class, null, value);
    }

    @XmlElementDecl(namespace = "http://ge.freesbee.unibas.it/", name = "modificaConfigurazioneGE")
    public JAXBElement<ModificaConfigurazioneGE> createModificaConfigurazioneGE(ModificaConfigurazioneGE value) {
        return new JAXBElement<ModificaConfigurazioneGE>(_ModificaConfigurazioneGE_QNAME, ModificaConfigurazioneGE.class, null, value);
    }

    @XmlElementDecl(namespace = "http://ge.freesbee.unibas.it/", name = "caricaConfigurazioneResponse")
    public JAXBElement<CaricaConfigurazioneResponse> createCaricaConfigurazioneResponse(CaricaConfigurazioneResponse value) {
        return new JAXBElement<CaricaConfigurazioneResponse>(_CaricaConfigurazioneResponse_QNAME, CaricaConfigurazioneResponse.class, null, value);
    }
    private final static QName _UpdateCategoriaEventiInternaResponse_QNAME = new QName("http://ge.freesbee.unibas.it/", "updateCategoriaEventiInternaResponse");
    private final static QName _UpdateCategoriaEventiInterna_QNAME = new QName("http://ge.freesbee.unibas.it/", "updateCategoriaEventiInterna");
    private final static QName _AddCategoriaEventiInterna_QNAME = new QName("http://ge.freesbee.unibas.it/", "addCategoriaEventiInterna");
    private final static QName _GetListaCategoriaEventiInterneResponse_QNAME = new QName("http://ge.freesbee.unibas.it/", "getListaCategoriaEventiInterneResponse");
    private final static QName _GetListaCategoriaEventiInterne_QNAME = new QName("http://ge.freesbee.unibas.it/", "getListaCategoriaEventiInterne");
    private final static QName _AddCategoriaEventiInternaResponse_QNAME = new QName("http://ge.freesbee.unibas.it/", "addCategoriaEventiInternaResponse");

    public CategoriaEventiInterna createCategoriaEventiInterna() {
        return new CategoriaEventiInterna();
    }

    public FiltroPubblicatoreInterno createFiltroPubblicatoreInterno() {
        return new FiltroPubblicatoreInterno();
    }

    public UpdateCategoriaEventiInterna createUpdateCategoriaEventiInterna() {
        return new UpdateCategoriaEventiInterna();
    }

    public AddCategoriaEventiInterna createAddCategoriaEventiInterna() {
        return new AddCategoriaEventiInterna();
    }

    public FiltroData createFiltroData() {
        return new FiltroData();
    }

    public AddCategoriaEventiInternaResponse createAddCategoriaEventiInternaResponse() {
        return new AddCategoriaEventiInternaResponse();
    }

    public Sottoscrittore createSottoscrittore() {
        return new Sottoscrittore();
    }

    public SottoscrizioneInterna createSottoscrizioneInterna() {
        return new SottoscrizioneInterna();
    }

    public PubblicatoreInterno createPubblicatoreInterno() {
        return new PubblicatoreInterno();
    }

    public GetListaCategoriaEventiInterne createGetListaCategoriaEventiInterne() {
        return new GetListaCategoriaEventiInterne();
    }

    public GetListaCategoriaEventiInterneResponse createGetListaCategoriaEventiInterneResponse() {
        return new GetListaCategoriaEventiInterneResponse();
    }

    public FiltroContenuto createFiltroContenuto() {
        return new FiltroContenuto();
    }

    public UpdateCategoriaEventiInternaResponse createUpdateCategoriaEventiInternaResponse() {
        return new UpdateCategoriaEventiInternaResponse();
    }

    @XmlElementDecl(namespace = "http://ge.freesbee.unibas.it/", name = "updateCategoriaEventiInternaResponse")
    public JAXBElement<UpdateCategoriaEventiInternaResponse> createUpdateCategoriaEventiInternaResponse(UpdateCategoriaEventiInternaResponse value) {
        return new JAXBElement<UpdateCategoriaEventiInternaResponse>(_UpdateCategoriaEventiInternaResponse_QNAME, UpdateCategoriaEventiInternaResponse.class, null, value);
    }

    @XmlElementDecl(namespace = "http://ge.freesbee.unibas.it/", name = "updateCategoriaEventiInterna")
    public JAXBElement<UpdateCategoriaEventiInterna> createUpdateCategoriaEventiInterna(UpdateCategoriaEventiInterna value) {
        return new JAXBElement<UpdateCategoriaEventiInterna>(_UpdateCategoriaEventiInterna_QNAME, UpdateCategoriaEventiInterna.class, null, value);
    }

    @XmlElementDecl(namespace = "http://ge.freesbee.unibas.it/", name = "addCategoriaEventiInterna")
    public JAXBElement<AddCategoriaEventiInterna> createAddCategoriaEventiInterna(AddCategoriaEventiInterna value) {
        return new JAXBElement<AddCategoriaEventiInterna>(_AddCategoriaEventiInterna_QNAME, AddCategoriaEventiInterna.class, null, value);
    }

    @XmlElementDecl(namespace = "http://ge.freesbee.unibas.it/", name = "getListaCategoriaEventiInterneResponse")
    public JAXBElement<GetListaCategoriaEventiInterneResponse> createGetListaCategoriaEventiInterneResponse(GetListaCategoriaEventiInterneResponse value) {
        return new JAXBElement<GetListaCategoriaEventiInterneResponse>(_GetListaCategoriaEventiInterneResponse_QNAME, GetListaCategoriaEventiInterneResponse.class, null, value);
    }

    @XmlElementDecl(namespace = "http://ge.freesbee.unibas.it/", name = "getListaCategoriaEventiInterne")
    public JAXBElement<GetListaCategoriaEventiInterne> createGetListaCategoriaEventiInterne(GetListaCategoriaEventiInterne value) {
        return new JAXBElement<GetListaCategoriaEventiInterne>(_GetListaCategoriaEventiInterne_QNAME, GetListaCategoriaEventiInterne.class, null, value);
    }

    @XmlElementDecl(namespace = "http://ge.freesbee.unibas.it/", name = "addCategoriaEventiInternaResponse")
    public JAXBElement<AddCategoriaEventiInternaResponse> createAddCategoriaEventiInternaResponse(AddCategoriaEventiInternaResponse value) {
        return new JAXBElement<AddCategoriaEventiInternaResponse>(_AddCategoriaEventiInternaResponse_QNAME, AddCategoriaEventiInternaResponse.class, null, value);
    }
    private final static QName _GetListaCategoriaEventiEsterneResponse_QNAME = new QName("http://ge.freesbee.unibas.it/", "getListaCategoriaEventiEsterneResponse");
    private final static QName _UpdateCategoriaEventiEsterna_QNAME = new QName("http://ge.freesbee.unibas.it/", "updateCategoriaEventiEsterna");
    private final static QName _GetListaCategoriaEventiEsterne_QNAME = new QName("http://ge.freesbee.unibas.it/", "getListaCategoriaEventiEsterne");
    private final static QName _AddCategoriaEventiEsternaResponse_QNAME = new QName("http://ge.freesbee.unibas.it/", "addCategoriaEventiEsternaResponse");
    private final static QName _AddCategoriaEventiEsterna_QNAME = new QName("http://ge.freesbee.unibas.it/", "addCategoriaEventiEsterna");
    private final static QName _UpdateCategoriaEventiEsternaResponse_QNAME = new QName("http://ge.freesbee.unibas.it/", "updateCategoriaEventiEsternaResponse");

    public SottoscrizioneEsterna createSottoscrizioneEsterna() {
        return new SottoscrizioneEsterna();
    }

    public AddCategoriaEventiEsterna createAddCategoriaEventiEsterna() {
        return new AddCategoriaEventiEsterna();
    }

    public PubblicatoreEsterno createPubblicatoreEsterno() {
        return new PubblicatoreEsterno();
    }

    public AddCategoriaEventiEsternaResponse createAddCategoriaEventiEsternaResponse() {
        return new AddCategoriaEventiEsternaResponse();
    }

    public UpdateCategoriaEventiEsterna createUpdateCategoriaEventiEsterna() {
        return new UpdateCategoriaEventiEsterna();
    }

    public CategoriaEventiEsterna createCategoriaEventiEsterna() {
        return new CategoriaEventiEsterna();
    }

    public GetListaCategoriaEventiEsterne createGetListaCategoriaEventiEsterne() {
        return new GetListaCategoriaEventiEsterne();
    }

    public GetListaCategoriaEventiEsterneResponse createGetListaCategoriaEventiEsterneResponse() {
        return new GetListaCategoriaEventiEsterneResponse();
    }

    public FiltroPubblicatoreEsterno createFiltroPubblicatoreEsterno() {
        return new FiltroPubblicatoreEsterno();
    }

    public UpdateCategoriaEventiEsternaResponse createUpdateCategoriaEventiEsternaResponse() {
        return new UpdateCategoriaEventiEsternaResponse();
    }

    @XmlElementDecl(namespace = "http://ge.freesbee.unibas.it/", name = "getListaCategoriaEventiEsterneResponse")
    public JAXBElement<GetListaCategoriaEventiEsterneResponse> createGetListaCategoriaEventiEsterneResponse(GetListaCategoriaEventiEsterneResponse value) {
        return new JAXBElement<GetListaCategoriaEventiEsterneResponse>(_GetListaCategoriaEventiEsterneResponse_QNAME, GetListaCategoriaEventiEsterneResponse.class, null, value);
    }

    @XmlElementDecl(namespace = "http://ge.freesbee.unibas.it/", name = "updateCategoriaEventiEsterna")
    public JAXBElement<UpdateCategoriaEventiEsterna> createUpdateCategoriaEventiEsterna(UpdateCategoriaEventiEsterna value) {
        return new JAXBElement<UpdateCategoriaEventiEsterna>(_UpdateCategoriaEventiEsterna_QNAME, UpdateCategoriaEventiEsterna.class, null, value);
    }

    @XmlElementDecl(namespace = "http://ge.freesbee.unibas.it/", name = "getListaCategoriaEventiEsterne")
    public JAXBElement<GetListaCategoriaEventiEsterne> createGetListaCategoriaEventiEsterne(GetListaCategoriaEventiEsterne value) {
        return new JAXBElement<GetListaCategoriaEventiEsterne>(_GetListaCategoriaEventiEsterne_QNAME, GetListaCategoriaEventiEsterne.class, null, value);
    }

    @XmlElementDecl(namespace = "http://ge.freesbee.unibas.it/", name = "addCategoriaEventiEsternaResponse")
    public JAXBElement<AddCategoriaEventiEsternaResponse> createAddCategoriaEventiEsternaResponse(AddCategoriaEventiEsternaResponse value) {
        return new JAXBElement<AddCategoriaEventiEsternaResponse>(_AddCategoriaEventiEsternaResponse_QNAME, AddCategoriaEventiEsternaResponse.class, null, value);
    }

    @XmlElementDecl(namespace = "http://ge.freesbee.unibas.it/", name = "addCategoriaEventiEsterna")
    public JAXBElement<AddCategoriaEventiEsterna> createAddCategoriaEventiEsterna(AddCategoriaEventiEsterna value) {
        return new JAXBElement<AddCategoriaEventiEsterna>(_AddCategoriaEventiEsterna_QNAME, AddCategoriaEventiEsterna.class, null, value);
    }

    @XmlElementDecl(namespace = "http://ge.freesbee.unibas.it/", name = "updateCategoriaEventiEsternaResponse")
    public JAXBElement<UpdateCategoriaEventiEsternaResponse> createUpdateCategoriaEventiEsternaResponse(UpdateCategoriaEventiEsternaResponse value) {
        return new JAXBElement<UpdateCategoriaEventiEsternaResponse>(_UpdateCategoriaEventiEsternaResponse_QNAME, UpdateCategoriaEventiEsternaResponse.class, null, value);
    }
    private final static QName _UpdateGestoriEventi_QNAME = new QName("http://ge.freesbee.unibas.it/", "updateGestoriEventi");
    private final static QName _AddGestoreEventiResponse_QNAME = new QName("http://ge.freesbee.unibas.it/", "addGestoreEventiResponse");
    private final static QName _UpdateGestoriEventiResponse_QNAME = new QName("http://ge.freesbee.unibas.it/", "updateGestoriEventiResponse");
    private final static QName _GetListaGestoriEventiResponse_QNAME = new QName("http://ge.freesbee.unibas.it/", "getListaGestoriEventiResponse");
    private final static QName _AddGestoreEventi_QNAME = new QName("http://ge.freesbee.unibas.it/", "addGestoreEventi");
    private final static QName _GetListaGestoriEventi_QNAME = new QName("http://ge.freesbee.unibas.it/", "getListaGestoriEventi");

    public AddGestoreEventiResponse createAddGestoreEventiResponse() {
        return new AddGestoreEventiResponse();
    }

    public GetListaGestoriEventiResponse createGetListaGestoriEventiResponse() {
        return new GetListaGestoriEventiResponse();
    }

    public GestoreEventi createGestoreEventi() {
        return new GestoreEventi();
    }

    public UpdateGestoriEventi createUpdateGestoriEventi() {
        return new UpdateGestoriEventi();
    }

    public AddGestoreEventi createAddGestoreEventi() {
        return new AddGestoreEventi();
    }

    public GetListaGestoriEventi createGetListaGestoriEventi() {
        return new GetListaGestoriEventi();
    }

    public UpdateGestoriEventiResponse createUpdateGestoriEventiResponse() {
        return new UpdateGestoriEventiResponse();
    }

    @XmlElementDecl(namespace = "http://ge.freesbee.unibas.it/", name = "updateGestoriEventi")
    public JAXBElement<UpdateGestoriEventi> createUpdateGestoriEventi(UpdateGestoriEventi value) {
        return new JAXBElement<UpdateGestoriEventi>(_UpdateGestoriEventi_QNAME, UpdateGestoriEventi.class, null, value);
    }

    @XmlElementDecl(namespace = "http://ge.freesbee.unibas.it/", name = "addGestoreEventiResponse")
    public JAXBElement<AddGestoreEventiResponse> createAddGestoreEventiResponse(AddGestoreEventiResponse value) {
        return new JAXBElement<AddGestoreEventiResponse>(_AddGestoreEventiResponse_QNAME, AddGestoreEventiResponse.class, null, value);
    }

    @XmlElementDecl(namespace = "http://ge.freesbee.unibas.it/", name = "updateGestoriEventiResponse")
    public JAXBElement<UpdateGestoriEventiResponse> createUpdateGestoriEventiResponse(UpdateGestoriEventiResponse value) {
        return new JAXBElement<UpdateGestoriEventiResponse>(_UpdateGestoriEventiResponse_QNAME, UpdateGestoriEventiResponse.class, null, value);
    }

    @XmlElementDecl(namespace = "http://ge.freesbee.unibas.it/", name = "getListaGestoriEventiResponse")
    public JAXBElement<GetListaGestoriEventiResponse> createGetListaGestoriEventiResponse(GetListaGestoriEventiResponse value) {
        return new JAXBElement<GetListaGestoriEventiResponse>(_GetListaGestoriEventiResponse_QNAME, GetListaGestoriEventiResponse.class, null, value);
    }

    @XmlElementDecl(namespace = "http://ge.freesbee.unibas.it/", name = "addGestoreEventi")
    public JAXBElement<AddGestoreEventi> createAddGestoreEventi(AddGestoreEventi value) {
        return new JAXBElement<AddGestoreEventi>(_AddGestoreEventi_QNAME, AddGestoreEventi.class, null, value);
    }

    @XmlElementDecl(namespace = "http://ge.freesbee.unibas.it/", name = "getListaGestoriEventi")
    public JAXBElement<GetListaGestoriEventi> createGetListaGestoriEventi(GetListaGestoriEventi value) {
        return new JAXBElement<GetListaGestoriEventi>(_GetListaGestoriEventi_QNAME, GetListaGestoriEventi.class, null, value);
    }
}
