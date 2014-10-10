package it.unibas.freesbee.ge.modello;

import java.util.Date;

public interface IEventoPubblicato {

    IPubblicatore getPubblicatore();

    void setPubblicatore(IPubblicatore pubblicatore);

    String getMessaggio();

    void setMessaggio(String messaggio);

    Date getDataPubblicazione();

    void setDataPubblicazione(Date data);

    ICategoriaEventi getCategoriaEventi();

    void setCategoriaEventi(ICategoriaEventi categoriaEventi);
}