package it.unibas.icar.freesbee.ws.registroservizi;

import it.unibas.icar.freesbee.modello.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class AccordoServizioRSRisposta {

    private String nome;
    private String descrizione;
    private String profiloCollaborazione;
    private List<ServizioRSRisposta> servizi = new ArrayList<ServizioRSRisposta>();
    private ProfiloEGov profiloEGov;
    private List<AzioneRSRisposta> azioni = new ArrayList<AzioneRSRisposta>();
    private boolean privato;
    private String oldNomeForUpdate;
    private Date oraRegistrazione;

    public AccordoServizioRSRisposta() {

    }

    public AccordoServizioRSRisposta(AccordoServizio accordo) {
        this.nome = accordo.getNome();
        this.descrizione = accordo.getDescrizione();
        this.profiloCollaborazione = accordo.getProfiloCollaborazione();
        this.privato = accordo.isPrivato();
        this.oraRegistrazione = accordo.getOraRegistrazione();
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
        accordoServizio.setProfiloEGov(this.profiloEGov);
        accordoServizio.setOldNomeForUpdate(this.oldNomeForUpdate);
        if (azioni != null) {
            for (AzioneRSRisposta azioneRSRsiposta : azioni) {
                Azione azione = azioneRSRsiposta.trasformaAzione();
                accordoServizio.getAzioni().add(azione);
                azione.setAccordoServizio(accordoServizio);
            }
        }
        for (ServizioRSRisposta servizioRSRisposta : this.servizi) {
            Servizio servizio = servizioRSRisposta.trasformaServizio();
            accordoServizio.getServizi().add(servizio);
        }
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
}
