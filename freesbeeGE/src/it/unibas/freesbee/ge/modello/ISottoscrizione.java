package it.unibas.freesbee.ge.modello;

import java.util.List;

public interface ISottoscrizione {

    String TIPO_SOTTOSCRIZIONE_CONSEGNA = "CONSEGNA";
    String TIPO_SOTTOSCRIZIONE_NOTIFICA = "NOTIFICA";

    String getTipoSottoscrizione();

    String getNomeCategoriaEventi();

    FiltroContenuto getFiltroContenuto();

    FiltroData getFiltroData();

    List getListaIFiltroPublicatore();

    Sottoscrittore getSottoscrittore();
}
