package it.unibas.silvio.modello;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Configurazione implements Serializable{
    private long id;
    private String dirConfig;
    private String porta;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    
    public String getDirConfig() {
        return dirConfig;
    }

    public void setDirConfig(String dirConfig) {
        if(!dirConfig.endsWith(java.io.File.separator)){
            dirConfig = dirConfig + java.io.File.separator;
        }
        this.dirConfig = dirConfig;
    }

    public String getPorta() {
        return porta;
    }

    public void setPorta(String porta) {
        this.porta = porta;
    }

}
