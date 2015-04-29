package it.unibas.icar.freesbee.ws.registroservizi;

import it.unibas.icar.freesbee.modello.*;
import it.unibas.icar.freesbee.ws.echoservice.WSEchoServiceImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import org.slf4j.LoggerFactory;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class AccordoServizioRSRisposta {

//    private static Log logger = LogFactory.getLog(AccordoServizioRSRisposta.class);
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AccordoServizioRSRisposta.class.getName());
    private String nome;
    private String descrizione;
    private String profiloCollaborazione;
    private List<ServizioRSRisposta> servizi = new ArrayList<ServizioRSRisposta>();
    private ProfiloEGov profiloEGov;
    private List<AzioneRSRisposta> azioni = new ArrayList<AzioneRSRisposta>();
    private boolean privato;
    private String oldNomeForUpdate;
    private Date oraRegistrazione;
    private String policyXACML;

    public AccordoServizioRSRisposta() {
    }

    public AccordoServizioRSRisposta(AccordoServizio accordo) {

        this.nome = accordo.getNome();
        this.descrizione = accordo.getDescrizione();
        this.profiloCollaborazione = accordo.getProfiloCollaborazione();
        this.privato = accordo.isPrivato();
        this.oraRegistrazione = accordo.getOraRegistrazione();
        this.policyXACML = accordo.getPolicyXACML();
        this.profiloEGov = accordo.getProfiloEGov();

        this.oldNomeForUpdate = accordo.getOldNomeForUpdate();
        for (Azione azione : accordo.getAzioni()) {
            azioni.add(new AzioneRSRisposta(azione));
        }
        for (Servizio servizio : accordo.getServizi()) {
            servizi.add(new ServizioRSRisposta(servizio, false));
        }
    }

    public AccordoServizio trasformaAccordoServizio() {
        AccordoServizio accordoServizio = new AccordoServizio();
        accordoServizio.setNome(this.nome);
        accordoServizio.setDescrizione(this.descrizione);
        accordoServizio.setProfiloCollaborazione(this.profiloCollaborazione);
        accordoServizio.setPrivato(this.privato);
        accordoServizio.setOraRegistrazione(this.oraRegistrazione);
        accordoServizio.setPolicyXACML(this.policyXACML);
        ProfiloEGov profiloEGovNuovo = new ProfiloEGov();
        profiloEGovNuovo.setConfermaRicezione(this.profiloEGov.isConfermaRicezione());
        profiloEGovNuovo.setConsegnaInOrdine(this.profiloEGov.isConsegnaInOrdine());
        profiloEGovNuovo.setGestioneDuplicati(this.profiloEGov.isGestioneDuplicati());
        profiloEGovNuovo.setScadenza(this.profiloEGov.getScadenza());
        profiloEGovNuovo.setIdCollaborazione(this.profiloEGov.isIdCollaborazione());
        accordoServizio.setProfiloEGov(profiloEGovNuovo);

        accordoServizio.setOldNomeForUpdate(this.oldNomeForUpdate);
        if (azioni != null) {
            for (AzioneRSRisposta azioneRSRsiposta : azioni) {
                Azione azione = azioneRSRsiposta.trasformaAzione();
                accordoServizio.getAzioni().add(azione);
                azione.setAccordoServizio(accordoServizio);
            }
        }
//        for (ServizioRSRisposta servizioRSRisposta : this.servizi) {
//            Servizio servizio = servizioRSRisposta.trasformaServizio();
//            accordoServizio.getServizi().add(servizio);
//            servizio.setAccordoServizio(accordoServizio);
//        }
        return accordoServizio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getProfiloCollaborazione() {
        return profiloCollaborazione;
    }

    public void setProfiloCollaborazione(String profiloCollaborazione) {
        this.profiloCollaborazione = profiloCollaborazione;
    }

    @XmlElement(name = "servizi")
    public List<ServizioRSRisposta> getServiziRSRisposta() {
        return servizi;
    }

    public void setServiziRSRisposta(List<ServizioRSRisposta> servizi) {
        this.servizi = servizi;
    }

    public ProfiloEGov getProfiloEGov() {
        return profiloEGov;
    }

    public void setProfiloEGov(ProfiloEGov profiloEGov) {
        this.profiloEGov = profiloEGov;
    }

    @XmlElement(name = "azioni")
    public List<AzioneRSRisposta> getAzioniRSRisposta() {
        return azioni;
    }

    public void setAzioniRSRisposta(List<AzioneRSRisposta> azioni) {
        this.azioni = azioni;
    }

    public boolean isPrivato() {
        return privato;
    }

    public void setPrivato(boolean privato) {
        this.privato = privato;
    }

    public String getOldNomeForUpdate() {
        return oldNomeForUpdate;
    }

    public void setOldNomeForUpdate(String oldNomeForUpdate) {
        this.oldNomeForUpdate = oldNomeForUpdate;
    }

    public Date getOraRegistrazione() {
        return oraRegistrazione;
    }

    public void setOraRegistrazione(Date oraRegistrazione) {
        this.oraRegistrazione = oraRegistrazione;
    }

    public String getPolicyXACML() {
        return policyXACML;
    }

    public void setPolicyXACML(String policyXACML) {
        this.policyXACML = policyXACML;
    }
}
