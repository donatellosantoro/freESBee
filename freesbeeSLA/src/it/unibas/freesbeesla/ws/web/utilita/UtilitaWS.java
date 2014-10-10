package it.unibas.freesbeesla.ws.web.utilita;

import it.unibas.icar.freesbee.modello.Servizio;
import it.unibas.icar.freesbee.modello.Soggetto;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.ServizioRS;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.SoggettoRS;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.SoggettoRSRisposta;

public class UtilitaWS {
    
    public static ServizioRS trasformaServizio(Servizio servizio){
        ServizioRS servizioRS = new ServizioRS();
        servizioRS.setNome(servizio.getNome());
        servizioRS.setTipo(servizio.getTipo());
        servizioRS.setSoggettoErogatore(trasformaSoggetto(servizio.getErogatore()));
        for (Soggetto soggettoFruitore : servizio.getFruitori()) {
            SoggettoRS soggettoRSFruitore = trasformaSoggetto(soggettoFruitore);
            servizioRS.getSoggettiFruitori().add(soggettoRSFruitore);
        }
        
        return servizioRS;
    }
    
    public static Servizio trasformaServizioRS(ServizioRS servizioRS){
        Servizio servizio = new Servizio();
        servizio.setNome(servizioRS.getNome());
        servizio.setTipo(servizioRS.getTipo());
        servizio.setErogatore(trasformaSoggettoRS(servizioRS.getSoggettoErogatore()));
        for (SoggettoRS soggettoRSFruitore : servizioRS.getSoggettiFruitori()) {
            Soggetto soggettoFruitore = trasformaSoggettoRS(soggettoRSFruitore);
            servizio.getFruitori().add(soggettoFruitore);
        }
        
        return servizio;
    }
    
    public static SoggettoRS trasformaSoggetto(Soggetto soggetto){
        SoggettoRS nuovoSoggettoRS = new SoggettoRS();
        nuovoSoggettoRS.setTipo(soggetto.getTipo());
        nuovoSoggettoRS.setNome(soggetto.getNome());
        return nuovoSoggettoRS;
    }
    
    public static Soggetto trasformaSoggettoRS(SoggettoRS soggetto){
        Soggetto nuovoSoggetto = new Soggetto();
        nuovoSoggetto.setTipo(soggetto.getTipo());
        nuovoSoggetto.setNome(soggetto.getNome());
        return nuovoSoggetto;
    }
    
    public static Soggetto trasformaSoggettoRSRisposta(SoggettoRSRisposta soggetto){
        Soggetto nuovoSoggetto = new Soggetto(); 
        nuovoSoggetto.setId(soggetto.getId());
        nuovoSoggetto.setTipo(soggetto.getTipo());
        nuovoSoggetto.setNome(soggetto.getNome());
        nuovoSoggetto.setDescrizione(soggetto.getDescrizione());
        nuovoSoggetto.setPortaDominio(soggetto.getPortaDominio());
        nuovoSoggetto.setOraRegistrazione(soggetto.getOraRegistrazione().toGregorianCalendar().getGregorianChange());
        nuovoSoggetto.setOldNomeForUpdate(soggetto.getOldNomeForUpdate());
        nuovoSoggetto.setOldTipoForUpdate(soggetto.getOldTipoForUpdate());
        return nuovoSoggetto;
    }
    
}
