package it.unibas.icar.interfreesbee.nica.registroservizi.adapter;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import it.unibas.icar.freesbee.ws.registroservizi.AccordoServizioRSRisposta;
import it.unibas.icar.freesbee.ws.registroservizi.AzioneRSRisposta;
import it.unibas.icar.freesbee.ws.registroservizi.ServizioRS;
import it.unibas.icar.freesbee.ws.registroservizi.ServizioRSRisposta;
import it.unibas.icar.freesbee.ws.registroservizi.SoggettoRS;
import it.unibas.icar.freesbee.ws.registroservizi.SoggettoRSRisposta;
import it.unibas.icar.interfreesbee.nica.registroservizi.stub.AccordoServizio;
import it.unibas.icar.interfreesbee.nica.registroservizi.stub.ArrayOfTns2Azione;
import it.unibas.icar.interfreesbee.nica.registroservizi.stub.ArrayOfTns2ConnettoreProperty;
import it.unibas.icar.interfreesbee.nica.registroservizi.stub.ArrayOfTns2ServizioSpcoopAzione;
import it.unibas.icar.interfreesbee.nica.registroservizi.stub.Azione;
import it.unibas.icar.interfreesbee.nica.registroservizi.stub.Connettore;
import it.unibas.icar.interfreesbee.nica.registroservizi.stub.ConnettoreProperty;
import it.unibas.icar.interfreesbee.nica.registroservizi.stub.FiltroSPCoop;
import it.unibas.icar.interfreesbee.nica.registroservizi.stub.IDServizio;
import it.unibas.icar.interfreesbee.nica.registroservizi.stub.IDSoggetto;
import it.unibas.icar.interfreesbee.nica.registroservizi.stub.ServizioSpcoop;
import it.unibas.icar.interfreesbee.nica.registroservizi.stub.ServizioSpcoopAzione;
import it.unibas.icar.interfreesbee.nica.registroservizi.stub.SoggettoSpcoop;

import it.unibas.icar.freesbee.utilita.UtilitaDate;
import it.unibas.icar.interfreesbee.freesbee.registroservizi.adapter.NicaToFreesbee;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

public class FreesbeeToNica {

    private static String PROFILO_ONEWAY = "EGOV_IT_MessaggioSingoloOneWay";
    private static String PROFILO_SINCRONO = "EGOV_IT_ServizioSincrono";
    private static String PROFILO_ASINCRONO_SIMMETRICO = "EGOV_IT_ServizioAsincronoSimmetrico";
    private static String PROFILO_ASINCRONO_ASIMMETRICO = "EGOV_IT_ServizioAsincronoAsimmetrico";

    public static SoggettoRS adapterFiltroToSoggetto(FiltroSPCoop filtroRicerca) {
        SoggettoRS soggetto = new SoggettoRS();
        if (filtroRicerca!=null && filtroRicerca.getNome() != null && filtroRicerca.getTipo() != null) {
            soggetto.setNome(filtroRicerca.getNome());
            soggetto.setTipo(filtroRicerca.getTipo());
        }
        return soggetto;
    }

    public static ServizioRS adapterIDServiceToServizio(IDServizio idService) {
        ServizioRS servizio = new ServizioRS();
        servizio.setNome(idService.getServizio());
        servizio.setTipo(idService.getTipoServizio());
        IDSoggetto soggetto = idService.getSoggettoErogatore();
        SoggettoRS soggettoRS = NicaToFreesbee.transformSoggetto(soggetto);
        servizio.setSoggettoErogatore(soggettoRS);
        return servizio;
    }

    public static List<IDSoggetto> adapterListaSoggettiToID(List<SoggettoRSRisposta> soggettoRSRisposta) {
        List<IDSoggetto> listaSoggettoID = new ArrayList<IDSoggetto>();
        for (SoggettoRSRisposta soggettoRS : soggettoRSRisposta) {
            IDSoggetto soggettoID = new IDSoggetto();
            soggettoID.setNome(soggettoRS.getNome());
            soggettoID.setTipo(soggettoRS.getTipo());
            listaSoggettoID.add(soggettoID);
        }
        return listaSoggettoID;
    }

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

    public static SoggettoSpcoop adapterSoggetto(SoggettoRSRisposta soggetto) {
        SoggettoSpcoop soggettoNICA = new SoggettoSpcoop();
        soggettoNICA.setId(soggetto.getId());
        soggettoNICA.setNome(soggetto.getNome());
        soggettoNICA.setTipo(soggetto.getTipo());
        soggettoNICA.setDescrizione(soggetto.getDescrizione());
        Connettore conn = new Connettore();
        conn.setNome("CNT_" + soggettoNICA.getTipo() + "_" + soggettoNICA.getNome());
        conn.setTipo("http");

        ArrayOfTns2ConnettoreProperty arrayConnettori = new ArrayOfTns2ConnettoreProperty();
        ConnettoreProperty conProp = new ConnettoreProperty();
        conProp.setId(new Long(-1));
        conProp.setNome("location");
        conProp.setValore(soggetto.getPortaDominio());
        arrayConnettori.getItem().add(conProp);
        conn.setPropertyList(arrayConnettori);

        it.unibas.icar.interfreesbee.nica.registroservizi.stub.Map mappaConnettore = new it.unibas.icar.interfreesbee.nica.registroservizi.stub.Map();
        it.unibas.icar.interfreesbee.nica.registroservizi.stub.MapItem mItem = new it.unibas.icar.interfreesbee.nica.registroservizi.stub.MapItem();
        mItem.setKey("location");
        mItem.setValue(soggetto.getPortaDominio());
        mappaConnettore.getItem().add(mItem);
        conn.setProperties(mappaConnettore);

        soggettoNICA.setConnettore(conn);
        soggetto.getPortaDominio();

        return soggettoNICA;
    }

    public static List<IDServizio> adapterListaServizi(List<ServizioRS> listaServizi) {
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

    public static AccordoServizio adapterAccordo(AccordoServizioRSRisposta accordoRS) {
        AccordoServizio accordo = new AccordoServizio();
        accordo.setNome(accordoRS.getNome());
        accordo.setProfiloCollaborazione(trasformaProfiloCollaborazione(accordoRS.getProfiloCollaborazione()));
        accordo.setDescrizione(accordoRS.getDescrizione());
        XMLGregorianCalendar calendar = transformDateToXmlCalendar(accordoRS.getOraRegistrazione());
        accordo.setOraRegistrazione(calendar);
        accordo.setConfermaRicezione(trasformaBooleani(accordoRS.getProfiloEGov().isConfermaRicezione()));
        accordo.setConsegnaInOrdine(trasformaBooleani(accordoRS.getProfiloEGov().isConsegnaInOrdine()));
        accordo.setFiltroDuplicati(trasformaBooleani(accordoRS.getProfiloEGov().isGestioneDuplicati()));
        accordo.setIdCollaborazione(trasformaBooleani(accordoRS.getProfiloEGov().isIdCollaborazione()));
        accordo.setDescrizione(accordoRS.getDescrizione());
        ArrayOfTns2Azione array = adapterAzioni(accordoRS.getAzioniRSRisposta());
//        ArrayOfTns2Azione array = new ArrayOfTns2Azione();        
        accordo.setAzioneList(array);
        accordo.setUtilizzoSenzaAzione(true);
        return accordo;
    }

    public static List<String> transformAccordiToString(List<it.unibas.icar.freesbee.modello.AccordoServizio> listaAccordi) {
        List<String> listaStringhe = new ArrayList<String>();
        for (it.unibas.icar.freesbee.modello.AccordoServizio accordo : listaAccordi) {
            String stringa = accordo.getNome();
            listaStringhe.add(stringa);
        }
        return listaStringhe;
    }

    public static List<IDServizio> transformServiziToID(List<ServizioRS> listaServizi) {
        List<IDServizio> listaIDServizi = new ArrayList<IDServizio>();
        for (ServizioRS servizio : listaServizi) {
            IDServizio serv = new IDServizio();
            serv.setServizio(servizio.getNome());
            serv.setTipoServizio(servizio.getTipo());
//            serv.setAccordo(??);
//            serv.setAzione(??);
//            serv.setCorrelato(??);
            IDSoggetto soggettoErogatore = new IDSoggetto();
            soggettoErogatore.setNome(servizio.getSoggettoErogatore().getNome());
            soggettoErogatore.setTipo(servizio.getSoggettoErogatore().getTipo());
            serv.setSoggettoErogatore(soggettoErogatore);
            listaIDServizi.add(serv);
        }
        return listaIDServizi;
    }

    private static ArrayOfTns2Azione adapterAzioni(List<AzioneRSRisposta> azioniRSRisposta) {
        ArrayOfTns2Azione listaTns = new ArrayOfTns2Azione();
        List<Azione> listaAzioni = listaTns.getItem();
        for (AzioneRSRisposta azioneRSRisposta : azioniRSRisposta) {
            Azione azione = new Azione();
            if (azioneRSRisposta.getProfiloEGov() != null) {
                azione.setConfermaRicezione(trasformaBooleani(azioneRSRisposta.getProfiloEGov().isConfermaRicezione()));
                azione.setConsegnaInOrdine(trasformaBooleani(azioneRSRisposta.getProfiloEGov().isConsegnaInOrdine()));
                azione.setFiltroDuplicati(trasformaBooleani(azioneRSRisposta.getProfiloEGov().isGestioneDuplicati()));
                azione.setIdCollaborazione(trasformaBooleani(azioneRSRisposta.getProfiloEGov().isIdCollaborazione()));
            }
            azione.setNome(azioneRSRisposta.getNome());
            listaAzioni.add(azione);
        }
        return listaTns;
    }

    public static ServizioSpcoop adapterServizio(ServizioRSRisposta servizioRSRisposta) {
        ServizioSpcoop servizioSP = new ServizioSpcoop();
        if (servizioRSRisposta == null) {
            return servizioSP;
        }
        servizioSP.setId(servizioRSRisposta.getId());
        servizioSP.setAccordoServizio(servizioRSRisposta.getAccordoServizio().getNome());
        servizioSP.setConfermaRicezione(trasformaBooleani(servizioRSRisposta.getAccordoServizio().getProfiloEGov().isConfermaRicezione()));
        servizioSP.setConsegnaInOrdine(trasformaBooleani(servizioRSRisposta.getAccordoServizio().getProfiloEGov().isConsegnaInOrdine()));
        servizioSP.setFiltroDuplicati(trasformaBooleani(servizioRSRisposta.getAccordoServizio().getProfiloEGov().isGestioneDuplicati()));
        servizioSP.setIdCollaborazione(trasformaBooleani(servizioRSRisposta.getAccordoServizio().getProfiloEGov().isIdCollaborazione()));
        servizioSP.setNome(servizioRSRisposta.getNome());
        servizioSP.setTipo(servizioRSRisposta.getTipo());
        servizioSP.setOraRegistrazione(transformDateToXmlCalendar(servizioRSRisposta.getOraRegistrazione()));
        servizioSP.setNomeSoggettoErogatore(servizioRSRisposta.getErogatore().getNome());
        servizioSP.setTipoSoggettoErogatore(servizioRSRisposta.getErogatore().getTipo());
        servizioSP.setServizioCorrelato(servizioRSRisposta.isCorrelato());
        servizioSP.setAzioneList(adapterAzioniServizioSp(servizioRSRisposta.getAccordoServizio().getAzioniRSRisposta()));
        servizioSP.setScadenza(UtilitaDate.formattaData(servizioRSRisposta.getAccordoServizio().getProfiloEGov().getScadenza()));
        return servizioSP;
    }

    private static ArrayOfTns2ServizioSpcoopAzione adapterAzioniServizioSp(List<AzioneRSRisposta> azioniRSRisposta) {
        ArrayOfTns2ServizioSpcoopAzione array = new ArrayOfTns2ServizioSpcoopAzione();
        List<ServizioSpcoopAzione> listaAzioni = array.getItem();
        for (AzioneRSRisposta azioneRSRisposta : azioniRSRisposta) {
            ServizioSpcoopAzione servzioAzione = new ServizioSpcoopAzione();
            servzioAzione.setNome(azioneRSRisposta.getNome());
            listaAzioni.add(servzioAzione);
        }
        return array;
    }

    public static XMLGregorianCalendar transformDateToXmlCalendar(Date date) {
        GregorianCalendar gregorian = new GregorianCalendar();
        gregorian.setTime(date);
        XMLGregorianCalendar calendar = new XMLGregorianCalendarImpl(gregorian);
        return calendar;
    }

    private static String trasformaProfiloCollaborazione(String profiloFreesbee) {
        if (profiloFreesbee.equalsIgnoreCase(PROFILO_ONEWAY)) {
            return "oneway";
        }
        if (profiloFreesbee.equalsIgnoreCase(PROFILO_SINCRONO)) {
            return "sincrono";
        }
        if (profiloFreesbee.equalsIgnoreCase(PROFILO_ASINCRONO_SIMMETRICO)) {
            return "asincronoSimmetrico";
        }
        if (profiloFreesbee.equalsIgnoreCase(PROFILO_ASINCRONO_ASIMMETRICO)) {
            return "asincronoAsimmetrico";
        }

        return "";
    }

    private static String trasformaBooleani(boolean stringaBool) {
        if (stringaBool) {
            return "abilitato";
        }
        return "disabilitato";
    }
}
