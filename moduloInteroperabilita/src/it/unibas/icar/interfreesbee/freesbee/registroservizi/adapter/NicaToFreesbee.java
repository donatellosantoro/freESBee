package it.unibas.icar.interfreesbee.freesbee.registroservizi.adapter;

import it.unibas.icar.freesbee.modello.ProfiloEGov;
import it.unibas.icar.freesbee.utilita.UtilitaDate;
import it.unibas.icar.freesbee.ws.registroservizi.AccordoServizioRS;
import it.unibas.icar.freesbee.ws.registroservizi.AccordoServizioRSRisposta;
import it.unibas.icar.freesbee.ws.registroservizi.AzioneRSRisposta;
import it.unibas.icar.freesbee.ws.registroservizi.ServizioRS;
import it.unibas.icar.freesbee.ws.registroservizi.ServizioRSRisposta;
import it.unibas.icar.freesbee.ws.registroservizi.SoggettoRS;
import it.unibas.icar.freesbee.ws.registroservizi.SoggettoRSRisposta;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openspcoop.dao.registry.AccordoServizio;
import org.openspcoop.dao.registry.Connettore;
import org.openspcoop.dao.registry.ConnettoreProperty;
import org.openspcoop.dao.registry.PortType;
import org.openspcoop.dao.registry.ServizioSpcoop;
import org.openspcoop.dao.registry.ServizioSpcoopAzione;
import org.openspcoop.dao.registry.SoggettoSpcoop;
import org.openspcoop.dao.registry.driver.FiltroServiziSPCoop;
import org.openspcoop.dao.registry.driver.IDServizio;
import org.openspcoop.dao.commons.IDSoggetto;
import org.openspcoop.dao.registry.Operation;

public class NicaToFreesbee {

    private static Log logger = LogFactory.getLog(NicaToFreesbee.class);
    private static String PROFILO_ONEWAY = "EGOV_IT_MessaggioSingoloOneWay";
    private static String PROFILO_SINCRONO = "EGOV_IT_ServizioSincrono";
    private static String PROFILO_ASINCRONO_SIMMETRICO = "EGOV_IT_ServizioAsincronoSimmetrico";
    private static String PROFILO_ASINCRONO_ASIMMETRICO = "EGOV_IT_ServizioAsincronoAsimmetrico";

    public static ServizioRSRisposta adapterServizio(ServizioSpcoop servizioNica) {
        ServizioRSRisposta servizio = new ServizioRSRisposta();
        AccordoServizioRSRisposta accordo = new AccordoServizioRSRisposta();
        accordo.setProfiloEGov(new ProfiloEGov());
        servizio.setId(servizioNica.getId());
        servizio.setAccordoServizio(accordo);
        servizio.setCorrelato(servizioNica.getServizioCorrelato());
        accordo.setNome(servizioNica.getAccordoServizio());
        accordo.getProfiloEGov().setConfermaRicezione(trasformaBooleani(servizioNica.getConfermaRicezione()));
        accordo.getProfiloEGov().setConsegnaInOrdine(trasformaBooleani(servizioNica.getConsegnaInOrdine()));
        accordo.getProfiloEGov().setGestioneDuplicati(trasformaBooleani(servizioNica.getFiltroDuplicati()));
        accordo.getProfiloEGov().setIdCollaborazione(trasformaBooleani(servizioNica.getIdCollaborazione()));
        ServizioSpcoopAzione[] azioniNica = servizioNica.getAzioneList();
        List<AzioneRSRisposta> listaRisposta = adapterAzioniServiziToRisposta(azioniNica);
        accordo.setAzioniRSRisposta(listaRisposta);
        servizio.setFruitori(adapterFruitoriList(servizioNica.getFruitoreList()));
        SoggettoRS soggettoErogatore = new SoggettoRS();
        soggettoErogatore.setNome(servizioNica.getNomeSoggettoErogatore());
        soggettoErogatore.setTipo(servizioNica.getTipoSoggettoErogatore());
        servizio.setErogatore(soggettoErogatore);
        servizio.setNome(servizioNica.getNome());
        servizio.setTipo(servizioNica.getTipo());
        servizio.setOraRegistrazione(servizioNica.getOraRegistrazione());
        accordo.getProfiloEGov().setScadenza(UtilitaDate.formattaData(servizioNica.getScadenza()));
        return servizio;
    }

    public static AccordoServizioRSRisposta adapterAccordoServizio(AccordoServizio accordoNica) {
        AccordoServizioRSRisposta accordo = new AccordoServizioRSRisposta();
        accordo.setProfiloEGov(new ProfiloEGov());
        accordo.setNome(accordoNica.getNome());
        accordo.setDescrizione(accordoNica.getDescrizione());
        accordo.setPrivato(accordoNica.getPrivato());
        accordo.setProfiloCollaborazione(trasformaProfiloCollaborazione(accordoNica.getProfiloCollaborazione()));
        accordo.getProfiloEGov().setConfermaRicezione(trasformaBooleani(accordoNica.getConfermaRicezione()));
        accordo.getProfiloEGov().setConsegnaInOrdine(trasformaBooleani(accordoNica.getConsegnaInOrdine()));
        accordo.getProfiloEGov().setGestioneDuplicati(trasformaBooleani(accordoNica.getFiltroDuplicati()));
        accordo.getProfiloEGov().setIdCollaborazione(trasformaBooleani(accordoNica.getIdCollaborazione()));
        accordo.setOraRegistrazione(accordoNica.getOraRegistrazione());
//        accordo.setAzioniRSRisposta(adapterAzioneList(accordoNica.getAzioneList()));
        accordo.setAzioniRSRisposta(adapterAzioneList(accordoNica.getPortTypeList()));
        accordo.getProfiloEGov().setScadenza(UtilitaDate.formattaData(accordoNica.getScadenza()));
        return accordo;
    }

    public static SoggettoRSRisposta adapterSoggettoRS(SoggettoSpcoop soggettoSp) {
        SoggettoRSRisposta soggettoRS = new SoggettoRSRisposta();
        soggettoRS.setNome(soggettoSp.getNome());
        soggettoRS.setTipo(soggettoSp.getTipo());
        soggettoRS.setId(soggettoSp.getId());
        soggettoRS.setOraRegistrazione(soggettoSp.getOraRegistrazione());

        Connettore connettore = soggettoSp.getConnettore();
        if (connettore != null) {
            ConnettoreProperty[] listaConnettori = connettore.getPropertyList();
            String indirizzo = null;
            if (listaConnettori != null) {
                for (ConnettoreProperty connettoreProperty : listaConnettori) {
                    indirizzo = connettoreProperty.getValore();
                }
            }
            soggettoRS.setPortaDominio(indirizzo);
        }
        return soggettoRS;
    }

    public static List<SoggettoRSRisposta> tranformIDSoggettoToSoggettoRS(IDSoggetto[] allIdSoggettiSPCoop) {
        List<SoggettoRSRisposta> listaSoggettiRS = new ArrayList<SoggettoRSRisposta>();
        if (allIdSoggettiSPCoop == null) {
            return listaSoggettiRS;
        }
        for (IDSoggetto idSoggetto : allIdSoggettiSPCoop) {
            SoggettoRSRisposta soggettoRS = new SoggettoRSRisposta();
            soggettoRS.setNome(idSoggetto.getNome());
            soggettoRS.setTipo(idSoggetto.getTipo());
            listaSoggettiRS.add(soggettoRS);
        }
        return listaSoggettiRS;
    }

    public static ServizioRS transformFiltroServizio(FiltroServiziSPCoop filtroRicerca) {
        ServizioRS servizioRS = new ServizioRS();
        servizioRS.setNome(filtroRicerca.getNome());
        servizioRS.setTipo(filtroRicerca.getTipo());
        SoggettoRS soggettoRS = new SoggettoRS();
        soggettoRS.setNome(filtroRicerca.getNomeSoggettoErogatore());
        soggettoRS.setTipo(filtroRicerca.getTipoSoggettoErogatore());
        servizioRS.setSoggettoErogatore(soggettoRS);
        return servizioRS;
    }

    public static List<ServizioRS> transformIDServizioToServizi(IDServizio[] allIdServiziSPCoop) {
        List<ServizioRS> listaServizi = new ArrayList<ServizioRS>();
        if (allIdServiziSPCoop == null) {
            return listaServizi;
        }
        for (IDServizio idServizio : allIdServiziSPCoop) {
            ServizioRS servizio = new ServizioRS();
            servizio.setNome(idServizio.getServizio());
            servizio.setTipo(idServizio.getTipoServizio());
            SoggettoRS soggetto = new SoggettoRS();
            soggetto.setNome(idServizio.getSoggettoErogatore().getNome());
            soggetto.setTipo(idServizio.getSoggettoErogatore().getTipo());
            servizio.setSoggettoErogatore(soggetto);
            listaServizi.add(servizio);
        }
        return listaServizi;
    }

    public static ServizioRS transformServizio(IDServizio idServizio) {
        ServizioRS servizioRS = new ServizioRS();
        servizioRS.setNome(idServizio.getServizio());
        servizioRS.setTipo(idServizio.getTipoServizio());
        SoggettoRS soggettoRS = new SoggettoRS();
        soggettoRS.setNome(idServizio.getSoggettoErogatore().getNome());
        soggettoRS.setTipo(idServizio.getSoggettoErogatore().getTipo());
        servizioRS.setSoggettoErogatore(soggettoRS);
        return servizioRS;
    }

    public static SoggettoRS transformSoggetto(IDSoggetto idSoggetto) {
        SoggettoRS soggettoRS = new SoggettoRS();
        soggettoRS.setNome(idSoggetto.getNome());
        soggettoRS.setTipo(idSoggetto.getTipo());
        return soggettoRS;
    }

    public static SoggettoRS transformSoggetto(it.unibas.icar.interfreesbee.nica.registroservizi.stub.IDSoggetto idSoggetto) {
        SoggettoRS soggettoRS = new SoggettoRS();
        soggettoRS.setNome(idSoggetto.getNome());
        soggettoRS.setTipo(idSoggetto.getTipo());
        return soggettoRS;
    }

    public static List<AccordoServizioRS> transformStringToAccordi(String[] allIdAccordiServizio) {
        List<AccordoServizioRS> listaAccordiServizi = new ArrayList<AccordoServizioRS>();
        if (allIdAccordiServizio == null) {
            return listaAccordiServizi;
        }
        for (String stringaNome : allIdAccordiServizio) {
            AccordoServizioRS accordo = new AccordoServizioRS();
            accordo.setNome(stringaNome);
            listaAccordiServizi.add(accordo);
        }
        return listaAccordiServizi;
    }

    private static List<SoggettoRSRisposta> adapterFruitoriList(org.openspcoop.dao.registry.Fruitore[] listaFruitori) {
        List<SoggettoRSRisposta> listaSoggettoRSRisposta = new ArrayList<SoggettoRSRisposta>();
        if (listaFruitori == null) {
            return listaSoggettoRSRisposta;
        }
        for (org.openspcoop.dao.registry.Fruitore fruitore : listaFruitori) {
            SoggettoRSRisposta soggetto = new SoggettoRSRisposta();
            soggetto.setId(fruitore.getId());
            soggetto.setNome(fruitore.getNome());
            soggetto.setTipo(fruitore.getTipo());
            soggetto.setOraRegistrazione(fruitore.getOraRegistrazione());
            listaSoggettoRSRisposta.add(soggetto);
        }
        return listaSoggettoRSRisposta;
    }

    private static List<AzioneRSRisposta> adapterAzioneList(PortType[] portTypeList) {
        List<AzioneRSRisposta> listaAzioniRSRisposta = new ArrayList<AzioneRSRisposta>();
        if (portTypeList == null) {
            return listaAzioniRSRisposta;
        }
        for (PortType portType : portTypeList) {
            org.openspcoop.dao.registry.Operation[] azioni = portType.getAzioneList();
            if (azioni != null) {
                for (Operation operation : azioni) {
                    AzioneRSRisposta azioneRisposta = new AzioneRSRisposta();
                    azioneRisposta.setNome(portType.getNome() + ":" + operation.getNome());
                    azioneRisposta.setProfiloCollaborazione(trasformaProfiloCollaborazione(operation.getProfiloCollaborazione()));
                    ProfiloEGov profilo = new ProfiloEGov();
                    profilo.setConfermaRicezione(trasformaBooleani(operation.getConfermaRicezione()));
                    profilo.setConsegnaInOrdine(trasformaBooleani(operation.getConsegnaInOrdine()));
                    profilo.setIdCollaborazione(trasformaBooleani(operation.getIdCollaborazione()));
                    profilo.setGestioneDuplicati(trasformaBooleani(operation.getFiltroDuplicati()));
                    profilo.setScadenza(UtilitaDate.formattaData(operation.getScadenza()));
                    azioneRisposta.setProfiloEGov(profilo);
                    listaAzioniRSRisposta.add(azioneRisposta);
                }
            }
        }
        return listaAzioniRSRisposta;
    }

    private static List<AzioneRSRisposta> adapterAzioneList(org.openspcoop.dao.registry.Azione[] azioniNica) {
        List<AzioneRSRisposta> listaAzioniRSRisposta = new ArrayList<AzioneRSRisposta>();
        if (azioniNica == null) {
            return listaAzioniRSRisposta;
        }
        for (org.openspcoop.dao.registry.Azione azioneNicaRisposta : azioniNica) {
            AzioneRSRisposta azioneRisposta = new AzioneRSRisposta();
            azioneRisposta.setNome(azioneNicaRisposta.getNome());
            azioneRisposta.setProfiloCollaborazione(trasformaProfiloCollaborazione(azioneNicaRisposta.getProfiloCollaborazione()));
            ProfiloEGov profilo = new ProfiloEGov();
            profilo.setConfermaRicezione(trasformaBooleani(azioneNicaRisposta.getConfermaRicezione()));
            profilo.setConsegnaInOrdine(trasformaBooleani(azioneNicaRisposta.getConsegnaInOrdine()));
            profilo.setIdCollaborazione(trasformaBooleani(azioneNicaRisposta.getIdCollaborazione()));
            profilo.setGestioneDuplicati(trasformaBooleani(azioneNicaRisposta.getFiltroDuplicati()));
            profilo.setScadenza(UtilitaDate.formattaData(azioneNicaRisposta.getScadenza()));
            azioneRisposta.setProfiloEGov(profilo);
            listaAzioniRSRisposta.add(azioneRisposta);
        }
        return listaAzioniRSRisposta;
    }

    private static List<AzioneRSRisposta> adapterAzioniServiziToRisposta(ServizioSpcoopAzione[] azioniNica) {
        List<AzioneRSRisposta> listaRisposta = new ArrayList<AzioneRSRisposta>();
        if (azioniNica == null) {
            return listaRisposta;
        }
        for (int i = 0; i < azioniNica.length; i++) {
            ServizioSpcoopAzione servizioSpcoopAzione = azioniNica[i];
            AzioneRSRisposta azione = new AzioneRSRisposta();
            azione.setNome(servizioSpcoopAzione.getNome());
            listaRisposta.add(azione);
        }
        return listaRisposta;
    }

    private static String trasformaProfiloCollaborazione(String profiloNica) {
        if (profiloNica.equalsIgnoreCase("oneway")) {
            return PROFILO_ONEWAY;
        }
        if (profiloNica.equalsIgnoreCase("sincrono")) {
            return PROFILO_SINCRONO;
        }
        if (profiloNica.equalsIgnoreCase("asincronoSimmetrico")) {
            return PROFILO_ASINCRONO_SIMMETRICO;
        }
        if (profiloNica.equalsIgnoreCase("asincronoAsimmetrico")) {
            return PROFILO_ASINCRONO_ASIMMETRICO;
        }

        return "";
    }

    private static boolean trasformaBooleani(String stringaBool) {
        if (stringaBool == null) {
            return false;
        }
        return (stringaBool.equalsIgnoreCase("abilitato") || stringaBool.equalsIgnoreCase("true"));
    }
}
