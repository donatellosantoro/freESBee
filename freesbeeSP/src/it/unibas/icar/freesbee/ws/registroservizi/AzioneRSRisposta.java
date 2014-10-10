package it.unibas.icar.freesbee.ws.registroservizi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class AzioneRSRisposta{
    
    private String nome;   
    private String profiloCollaborazione;
    
    public AzioneRSRisposta(){
        
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProfiloCollaborazione() {
        return profiloCollaborazione;
    }

    public void setProfiloCollaborazione(String profiloCollaborazione) {
        this.profiloCollaborazione = profiloCollaborazione;
    }


}
