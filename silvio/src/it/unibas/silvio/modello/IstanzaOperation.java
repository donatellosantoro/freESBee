package it.unibas.silvio.modello;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Entity
public class IstanzaOperation implements Serializable {

    private Log logger = LogFactory.getLog(this.getClass());
    private Operation operation;
    private IstanzaPortType istanzaPortType;
    private String indirizzoRispostaAsincronoErogatore;
    private long id;
    private Dati datiRisposta;
    private String XSLTSoapToSQLErogatore;
    private String XSLTSoapToSQLInsertErogatore;
    private String XSLTDatiToSoapErogatore;
    private boolean elaboraRisposta;
    private boolean elaboraRichiesta;
    private boolean rispostaAutomatica;
    private boolean avvioRapido;
    private boolean autenticazioneFederata;
    private String skinDir;
    private String nomeApp;
    private Dati datiRichiesta;
    private ParametriSLA parametriSLA;
    private String XSLTCompletiToSoapFruitore;
    private String XSLTDatiToSQLFruitore;
    private List<Messaggio> listaMessaggi = new ArrayList<Messaggio>();

    private boolean inviaMail;
    private String XSLTSoapToMail;
    private String indirizzoMail;
    private String oggettoMail;

    public String getXSLTSoapToMail() {
        return XSLTSoapToMail;
    }

    public void setXSLTSoapToMail(String XSLTSoapToMail) {
        this.XSLTSoapToMail = XSLTSoapToMail;
    }

    public String getIndirizzoMail() {
        return indirizzoMail;
    }

    public void setIndirizzoMail(String indirizzoMail) {
        this.indirizzoMail = indirizzoMail;
    }

    public String getOggettoMail() {
        return oggettoMail;
    }

    public void setOggettoMail(String oggettoMail) {
        this.oggettoMail = oggettoMail;
    }
    

    public boolean isInviaMail() {
        return inviaMail;
    }

    public void setInviaMail(boolean inviaMail) {
        this.inviaMail = inviaMail;
    }
    

    public String getXSLTCompletiToSoapFruitore() {
        return XSLTCompletiToSoapFruitore;
    }

    public void setXSLTCompletiToSoapFruitore(String XSLTCompletiToSoapFruitore) {
        this.XSLTCompletiToSoapFruitore = XSLTCompletiToSoapFruitore;
    }

    public String getXSLTDatiToSQLFruitore() {
        return XSLTDatiToSQLFruitore;
    }

    public void setXSLTDatiToSQLFruitore(String XSLTDatiToSQLFruitore) {
        this.XSLTDatiToSQLFruitore = XSLTDatiToSQLFruitore;
    }

    public String getXSLTDatiToSoapErogatore() {
        return XSLTDatiToSoapErogatore;
    }

    public void setXSLTDatiToSoapErogatore(String XSLTDatiToSoapErogatore) {
        this.XSLTDatiToSoapErogatore = XSLTDatiToSoapErogatore;
    }

    public String getXSLTSoapToSQLErogatore() {
        return XSLTSoapToSQLErogatore;
    }

    public void setXSLTSoapToSQLErogatore(String XSLTSoapToSQLErogatore) {
        this.XSLTSoapToSQLErogatore = XSLTSoapToSQLErogatore;
    }

    @OneToOne(cascade = CascadeType.ALL)
    public Dati getDatiRichiesta() {
        return datiRichiesta;
    }

    public void setDatiRichiesta(Dati datiRichiesta) {
        this.datiRichiesta = datiRichiesta;
    }

    @OneToOne(cascade = CascadeType.ALL, optional=true)
    public ParametriSLA getParametriSLA() {
        return parametriSLA;
    }

    public void setParametriSLA(ParametriSLA parametriSLA) {
        this.parametriSLA = parametriSLA;
    }

    @OneToOne(cascade = CascadeType.ALL, optional = true)
    public Dati getDatiRisposta() {
        return datiRisposta;
    }

    public void setDatiRisposta(Dati datiRisposta) {
        this.datiRisposta = datiRisposta;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    public IstanzaPortType getIstanzaPortType() {
        return istanzaPortType;
    }

    public void setIstanzaPortType(IstanzaPortType istanzaPortType) {
        this.istanzaPortType = istanzaPortType;
    }

    @ManyToOne(optional = false)
    public Operation getOperation() {
        return operation;
    }

    public String getIndirizzoRispostaAsincronoErogatore() {
        return indirizzoRispostaAsincronoErogatore;
    }

    public void setIndirizzoRispostaAsincronoErogatore(String indirizzoRispostaAsincronoErogatore) {
        this.indirizzoRispostaAsincronoErogatore = indirizzoRispostaAsincronoErogatore;
    }

    public void setOperation(Operation operation) {
        logger.info("Settiamo l'operation: " + operation);
        this.operation = operation;
    }

    public boolean isRispostaAutomatica() {
        return rispostaAutomatica;
    }

    public void setRispostaAutomatica(boolean rispostaAutomatica) {
        this.rispostaAutomatica = rispostaAutomatica;
    }

    public boolean isElaboraRisposta() {
        return elaboraRisposta;
    }

    public void setElaboraRisposta(boolean elaboraRisposta) {
        this.elaboraRisposta = elaboraRisposta;
    }

    public boolean isElaboraRichiesta() {
        return elaboraRichiesta;
    }

    public void setElaboraRichiesta(boolean elaboraRichiesta) {
        this.elaboraRichiesta = elaboraRichiesta;
    }

    @OneToMany(mappedBy = "istanzaOperation", cascade = CascadeType.REMOVE, targetEntity = Messaggio.class)
    public List<Messaggio> getListaMessaggi() {
        return listaMessaggi;
    }

    public void setListaMessaggi(List<Messaggio> listaMessaggi) {
        this.listaMessaggi = listaMessaggi;
    }

    @Transient
    public boolean isOneWay() {
        return operation.isOneWay();
    }

    @Transient
    public boolean isSincrono() {
        return operation.isSincrono();
    }

    @Transient
    public boolean isAsincrono() {
        return operation.isAsincrono();
    }

    @Override
    public String toString() {
        String s = "\t\tIstanza Operation: " + this.operation.getNome() + "\n";
        return s;
    }

    @Transient
    public void addMessaggio(Messaggio e) {
        listaMessaggi.add(e);
    }

    @Transient
    public String getNome() {
        if (this.getOperation() == null) {
            return "";
        }
        return this.getOperation().getNome();
    }

    public String getXSLTSoapToSQLInsertErogatore() {
        return XSLTSoapToSQLInsertErogatore;
    }

    public void setXSLTSoapToSQLInsertErogatore(String XSLTSoapToSQLInsertErogatore) {
        this.XSLTSoapToSQLInsertErogatore = XSLTSoapToSQLInsertErogatore;
    }

    public boolean isAvvioRapido() {
        return avvioRapido;
    }

    public void setAvvioRapido(boolean avvioRapido) {
        this.avvioRapido = avvioRapido;
    }

    public boolean isAutenticazioneFederata() {
        return autenticazioneFederata;
    }

    public void setAutenticazioneFederata(boolean autenticazioneFederata) {
        this.autenticazioneFederata = autenticazioneFederata;
    }

    public String getSkinDir() {
        return skinDir;
    }

    public void setSkinDir(String skinDir) {
        this.skinDir = skinDir;
    }

    public String getNomeApp() {
        return nomeApp;
    }

    public void setNomeApp(String nomeApp) {
        this.nomeApp = nomeApp;
    }

    @Transient
    public String getAnteprimaNome() {
        return operation.getAnteprimaNome();
    }

    @Transient
    public String getNomePortType() {
        String nomePT = this.getIstanzaPortType().getNome();
        if (nomePT.length() > 40) {
            return nomePT.substring(0, 30) + "...";
        }
        return nomePT;
    }

    @Transient
    public String getLabel(){
        if(nomeApp!=null){
            return nomeApp;
        }else {
            return getNomePortType();
        }
    }
    
    @Transient
    public boolean esisteMessage(String message) {
        logger.info("Cerco il message " + message);
        Message messIn = this.getOperation().getMessageIn();
        Message messOut = this.getOperation().getMessageOut();
        Message messFault = this.getOperation().getMessageFault();

        if ((messIn != null && messIn.getNome() != null && messIn.getNome().equalsIgnoreCase(message)) ||
                (messIn != null && messIn.getTypes() != null && messIn.getTypes().equalsIgnoreCase(message)) ||
                (messOut != null && messOut.getNome() != null && messOut.getNome().equalsIgnoreCase(message)) ||
                (messOut != null && messOut.getTypes() != null && messOut.getTypes().equalsIgnoreCase(message)) ||
                (messFault != null && messFault.getNome() != null && messFault.getNome().equalsIgnoreCase(message)) ||
                (messFault != null && messFault.getTypes() != null && messFault.getTypes().equalsIgnoreCase(message))) {
            return true;
        }
        return false;
    }
}
