package it.unibas.icar.freesbee.test.interoperabilita;

import it.unibas.icar.freesbee.modello.AccordoServizio;
import it.unibas.icar.freesbee.ws.registroservizi.AccordoServizioRS;
import it.unibas.icar.freesbee.ws.registroservizi.AccordoServizioRSRisposta;
import it.unibas.icar.freesbee.ws.registroservizi.AzioneRSRisposta;
import it.unibas.icar.freesbee.ws.registroservizi.ServizioRS;
import it.unibas.icar.freesbee.ws.registroservizi.ServizioRSRisposta;
import it.unibas.icar.freesbee.ws.registroservizi.SoggettoRS;
import it.unibas.icar.interfreesbee.freesbee.registroservizi.IWSRegistroServizi;
import it.unibas.icar.interfreesbee.freesbee.registroservizi.WSRegistroServiziImpl;
import java.util.List;
import junit.framework.Assert;
import junit.framework.TestCase;

public class TestRegistroServizi extends TestCase{

    private IWSRegistroServizi registro = new WSRegistroServiziImpl();
    private String indirizzo = "http://localhost:1234/nica-regpriv/Inquiry";
//    private String indirizzo = "http://nica.regione.basilicata.it/nica-regpriv/Inquiry";

    public void testGetAccordoServizio(){
        try {
            AccordoServizioRS accordo = new AccordoServizioRS();
            accordo.setNome("SPC/BasilicataErogatore:ASIcarLoopback:1");
            AccordoServizioRSRisposta risposta = registro.getAccordoServizio(indirizzo, accordo);
            List<AzioneRSRisposta> listaAzioni = risposta.getAzioniRSRisposta();
            Assert.assertEquals("ASIcarLoopback", risposta.getNome());
            Assert.assertEquals(6, listaAzioni.size());
            Assert.assertEquals("IcarAsincronoAsimmetricoLoopback:Richiesta", listaAzioni.get(0).getNome());
            Assert.assertEquals(AccordoServizio.PROFILO_ASINCRONO_ASIMMETRICO, listaAzioni.get(0).getProfiloCollaborazione());
            Assert.assertEquals("IcarAsincronoAsimmetricoLoopback:RichiestaStato", listaAzioni.get(1).getNome());
            Assert.assertEquals(AccordoServizio.PROFILO_ASINCRONO_ASIMMETRICO, listaAzioni.get(1).getProfiloCollaborazione());
            Assert.assertEquals("IcarAsincronoSimmetricoCorrelatoLoopback:Risposta", listaAzioni.get(2).getNome());
            Assert.assertEquals(AccordoServizio.PROFILO_ASINCRONO_SIMMETRICO, listaAzioni.get(2).getProfiloCollaborazione());
            Assert.assertEquals("IcarAsincronoSimmetricoLoopback:Richiesta", listaAzioni.get(3).getNome());
            Assert.assertEquals(AccordoServizio.PROFILO_ASINCRONO_SIMMETRICO, listaAzioni.get(3).getProfiloCollaborazione());
            Assert.assertEquals("IcarOneWayLoopback:Ping", listaAzioni.get(4).getNome());
            Assert.assertEquals(AccordoServizio.PROFILO_ONEWAY, listaAzioni.get(4).getProfiloCollaborazione());
            Assert.assertEquals("IcarSincronoLoopback:Echo", listaAzioni.get(5).getNome());
            Assert.assertEquals(AccordoServizio.PROFILO_SINCRONO, listaAzioni.get(5).getProfiloCollaborazione());
        } catch (Exception ex) {
            ex.printStackTrace();
            Assert.fail(ex.getLocalizedMessage());
        }
    }

    
    public void testGetServizioSPCoopCorrelatoByAccordo(){
        try {
            AccordoServizioRS accordo = new AccordoServizioRS();
            accordo.setNome("SPC/BasilicataErogatore:ASIcarLoopback:1");
            SoggettoRS soggetto = new SoggettoRS("SPC", "RegioneBasilicata");
            ServizioRSRisposta risposta = registro.getServizioSPCoopCorrelatoByAccordo(indirizzo, soggetto, accordo);
            Assert.assertEquals("IcarAsincronoSimmetricoCorrelatoLoopback", risposta.getNome());
            Assert.assertEquals("RegioneBasilicata", risposta.getErogatore().getNome());
            Assert.assertEquals(true, risposta.isCorrelato());
            Assert.assertEquals(false, risposta.getAccordoServizio().getProfiloEGov().isConfermaRicezione());
        } catch (Exception ex) {
            ex.printStackTrace();
            Assert.fail(ex.getLocalizedMessage());
        }
    }

    public void testGetServizioSPCoopCorrelato(){
        try {
            ServizioRS servizio = new ServizioRS("SPC", "IcarAsincronoSimmetricoCorrelatoLoopback");
            servizio.setSoggettoErogatore(new SoggettoRS("SPC", "RegioneBasilicata"));
            ServizioRSRisposta risposta = registro.getServizioSPCoopCorrelato(indirizzo, servizio);
            Assert.assertEquals("IcarAsincronoSimmetricoCorrelatoLoopback", risposta.getNome());
            Assert.assertEquals("RegioneBasilicata", risposta.getErogatore().getNome());
            Assert.assertEquals(true, risposta.isCorrelato());
            Assert.assertEquals(AccordoServizio.PROFILO_ONEWAY, risposta.getAccordoServizio().getProfiloCollaborazione());
            Assert.assertEquals(false, risposta.getAccordoServizio().getProfiloEGov().isConfermaRicezione());
        } catch (Exception ex) {
            ex.printStackTrace();
            Assert.fail(ex.getLocalizedMessage());
        }
    }

    public void testGetServizioSPCoop(){
        try {
            ServizioRS servizio = new ServizioRS("SPC", "IcarAsincronoSimmetricoLoopback");
            servizio.setSoggettoErogatore(new SoggettoRS("SPC", "BasilicataErogatore"));
            ServizioRSRisposta risposta = registro.getServizioSPCoop(indirizzo, servizio);
            Assert.assertEquals("IcarAsincronoSimmetricoLoopback", risposta.getNome());
            Assert.assertEquals("BasilicataErogatore", risposta.getErogatore().getNome());
            Assert.assertEquals(false, risposta.isCorrelato());
            Assert.assertEquals(AccordoServizio.PROFILO_ONEWAY, risposta.getAccordoServizio().getProfiloCollaborazione());
            Assert.assertEquals(false, risposta.getAccordoServizio().getProfiloEGov().isConfermaRicezione());
            Assert.assertEquals("SPC/BasilicataErogatore:ASIcarLoopback:1", risposta.getAccordoServizio().getNome());
            List<AzioneRSRisposta> listaAzioni = risposta.getAccordoServizio().getAzioniRSRisposta();
            Assert.assertEquals(6, listaAzioni.size());
        } catch (Exception ex) {
            ex.printStackTrace();
            Assert.fail(ex.getLocalizedMessage());
        }
    }

    public void testGetServizioSPCoop2(){
        try {
            ServizioRS servizio = new ServizioRS("SPC", "IcarSincronoLoopback");
            servizio.setSoggettoErogatore(new SoggettoRS("SPC", "BasilicataErogatore"));
            ServizioRSRisposta risposta = registro.getServizioSPCoop(indirizzo, servizio);
            Assert.assertEquals("IcarSincronoLoopback", risposta.getNome());
            Assert.assertEquals("BasilicataErogatore", risposta.getErogatore().getNome());
            Assert.assertEquals(false, risposta.isCorrelato());
            
            Assert.assertEquals(AccordoServizio.PROFILO_ONEWAY, risposta.getAccordoServizio().getProfiloCollaborazione());
//            Assert.assertEquals(AccordoServizio.PROFILO_SINCRONO, risposta.getAccordoServizio().getProfiloCollaborazione());
            Assert.assertEquals(false, risposta.getAccordoServizio().getProfiloEGov().isConfermaRicezione());
        } catch (Exception ex) {
            ex.printStackTrace();
            Assert.fail(ex.getLocalizedMessage());
        }
    }

}
