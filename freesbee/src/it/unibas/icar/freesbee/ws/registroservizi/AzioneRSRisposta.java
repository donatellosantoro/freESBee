package it.unibas.icar.freesbee.ws.registroservizi;

import it.unibas.icar.freesbee.modello.Azione;
import it.unibas.icar.freesbee.modello.ProfiloEGov;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class AzioneRSRisposta{
    
    private String nome;   
    private String profiloCollaborazione;
    private ProfiloEGov profiloEGov;
    
    public AzioneRSRisposta(){
        
    }
    
    public AzioneRSRisposta(Azione azione){
        this.nome = azione.getNome();
        this.profiloEGov = azione.getProfiloEGov();
        this.profiloCollaborazione = azione.getProfiloCollaborazione();
    }
    
    public Azione trasformaAzione(){
        Azione azione = new Azione();
        azione.setNome(this.nome);
        azione.setProfiloEGov(this.profiloEGov);
        azione.setProfiloCollaborazione(this.profiloCollaborazione);
        return azione;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public ProfiloEGov getProfiloEGov() {
        return profiloEGov;
    }

    public void setProfiloEGov(ProfiloEGov profiloEGov) {
        this.profiloEGov = profiloEGov;
    }

    public String getProfiloCollaborazione() {
        return profiloCollaborazione;
    }

    public void setProfiloCollaborazione(String profiloCollaborazione) {
        this.profiloCollaborazione = profiloCollaborazione;
    }


}
