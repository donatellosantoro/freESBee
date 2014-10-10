package it.unibas.icar.interfreesbee.freesbee.registroservizi.adapter;

import it.unibas.icar.freesbee.ws.registroservizi.AccordoServizioRSRisposta;
import it.unibas.icar.freesbee.ws.registroservizi.AzioneRSRisposta;
import it.unibas.icar.freesbee.ws.registroservizi.ServizioRS;
import it.unibas.icar.freesbee.ws.registroservizi.SoggettoRS;
import java.util.ArrayList;
import java.util.List;
import org.openspcoop.dao.registry.AccordoServizio;
import org.openspcoop.dao.registry.Azione;
import org.openspcoop.dao.registry.driver.IDServizio;
import org.openspcoop.dao.commons.IDSoggetto;

public class FreesbeeToNica {

    private static org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog(FreesbeeToNica.class);

    public static IDServizio adapterServizio(ServizioRS servizio) {
        IDServizio servizioNica = new IDServizio();
        servizioNica.setServizio(servizio.getNome());
        servizioNica.setTipoServizio(servizio.getTipo());       
        IDSoggetto erogatoreNica = new IDSoggetto();
        erogatoreNica.setTipo(servizio.getSoggettoErogatore().getTipo());
        erogatoreNica.setNome(servizio.getSoggettoErogatore().getNome());
        servizioNica.setSoggettoErogatore(erogatoreNica);
        return servizioNica;
    }

    public static IDSoggetto adapterSoggetto(SoggettoRS soggetto) {
        IDSoggetto soggettoNICA = new IDSoggetto();
        soggettoNICA.setNome(soggetto.getNome());
        soggettoNICA.setTipo(soggetto.getTipo());
        return soggettoNICA;
    }
    
    public static List<IDServizio> adapterListaServizi(List<ServizioRS> listaServizi){
        List<IDServizio> listaIDServizi = new ArrayList<IDServizio>();
        for (ServizioRS servizioRS : listaServizi) {
            IDServizio idServizio = new IDServizio();
            idServizio.setServizio(servizioRS.getNome());
            idServizio.setTipoServizio(servizioRS.getTipo());
            IDSoggetto idSoggetto = FreesbeeToNica.adapterSoggetto(servizioRS.getSoggettoErogatore());
            idServizio.setSoggettoErogatore(idSoggetto);
            listaIDServizi.add(idServizio);
        }
        return listaIDServizi;
    }
    
    public static AccordoServizio adapterAccordo(AccordoServizioRSRisposta accordoRS){
        AccordoServizio accordo = new AccordoServizio();
        accordo.setNome(accordoRS.getNome());
        accordo.setProfiloCollaborazione(accordoRS.getProfiloCollaborazione());
        accordo.setDescrizione(accordoRS.getDescrizione());
        accordo.setOraRegistrazione(accordo.getOraRegistrazione());
        accordo.setConfermaRicezione("" + accordoRS.getProfiloEGov().isConfermaRicezione());
        accordo.setConsegnaInOrdine("" + accordoRS.getProfiloEGov().isConsegnaInOrdine());
        accordo.setDescrizione(accordoRS.getDescrizione());
        accordo.setFiltroDuplicati("" + accordoRS.getProfiloEGov().isGestioneDuplicati());
        Azione[] listaAzioni = adapterAzioni(accordoRS.getAzioniRSRisposta());
        accordo.setAzioneList(listaAzioni);        
        return accordo;
    }

    private static Azione[] adapterAzioni(List<AzioneRSRisposta> azioniRSRisposta) {
        Azione[] listaAzione = new Azione[azioniRSRisposta.size()];
        for (int i = 0; i < azioniRSRisposta.size(); i++) {
            AzioneRSRisposta azioneRS = azioniRSRisposta.get(i);
            Azione azione = new Azione();
            azione.setNome(azioneRS.getNome());
            azione.setConfermaRicezione("" + azioneRS.getProfiloEGov().isConfermaRicezione());
            azione.setConsegnaInOrdine("" + azioneRS.getProfiloEGov().isConsegnaInOrdine());
            azione.setFiltroDuplicati("" + azioneRS.getProfiloEGov().isGestioneDuplicati());
            listaAzione[i] = azione;
        }        
        return listaAzione;
    }
}
