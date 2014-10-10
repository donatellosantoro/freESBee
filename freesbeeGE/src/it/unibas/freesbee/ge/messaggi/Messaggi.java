package it.unibas.freesbee.ge.messaggi;

import it.unibas.freesbee.ge.modello.CategoriaEventiEsterna;
import it.unibas.freesbee.ge.modello.CategoriaEventiInterna;
import it.unibas.freesbee.ge.modello.FiltroPubblicatoreEsterno;
import it.unibas.freesbee.ge.modello.GestoreEventi;
import it.unibas.freesbee.ge.modello.ICategoriaEventi;
import it.unibas.freesbee.ge.modello.IPubblicatore;
import it.unibas.freesbee.ge.modello.IStatoMessaggio;
import it.unibas.freesbee.ge.modello.PubblicatoreInterno;
import it.unibas.freesbee.ge.modello.Sottoscrittore;
import it.unibas.freesbee.ge.modello.SottoscrizioneEsterna;
import it.unibas.freesbee.ge.modello.StatoMessaggioEsterno;
import it.unibas.freesbee.ge.modello.StatoMessaggioInterno;
import it.unibas.freesbee.ge.persistenza.DAOException;
import it.unibas.freesbee.ge.persistenza.facade.DAOGestoreEventiFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOPubblicatoreEsternoFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOSottoscrizioneEsternaFacade;
import java.util.List;

public class Messaggi {

    private Messaggi() {
    }

    public static String creaMessaggioComunicazioneRimozionePubblicatore(IPubblicatore pubblicatore, ICategoriaEventi categoriaEvneti, GestoreEventi ge) {
        String messaggio =
                "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"  xmlns:ge=\"http://ge.freesbee.unibas.it/\">" +
                "<SOAP-ENV:Body>" +
                "<ge:pubblicaMessaggio>" +
                "<messaggioSoap>" +
                "<comunicazioneEliminazionePubblicatore><pubblicatore>" +
                "<nomePubblicatore>" + pubblicatore.getNome() + "</nomePubblicatore>" +
                "<tipoPubblicatore>" + pubblicatore.getTipo() + "</tipoPubblicatore></pubblicatore>" +
                "<categoriaPubblicata>" + categoriaEvneti.getNome() + "</categoriaPubblicata>" +
                "</comunicazioneEliminazionePubblicatore>" +
                "</messaggioSoap>" +
                "<nomePubblicatore>" + ge.getNome() + "</nomePubblicatore>" +
                "<tipoPubblicatore>" + ge.getTipo() + "</tipoPubblicatore>" +
                "<categoriaEventi>GEControlProtocol</categoriaEventi>" +
                "</ge:pubblicaMessaggio>" +
                "</SOAP-ENV:Body>" +
                "</SOAP-ENV:Envelope>";

        return messaggio;
    }

    public static String creaMessaggioConsegnaInterno(StatoMessaggioInterno statoMessaggio) {
        String messaggioConsegna = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:ge=\"http://ge.freesbee.unibas.it/\"><soap:Header/><soap:Body>";
        messaggioConsegna += "<ge:consegnaMessaggio><messaggioSoap>" + statoMessaggio.getIEventoPubblicato().getMessaggio() + "</messaggioSoap>";
        messaggioConsegna += "<nomePubblicatore>" + statoMessaggio.getIEventoPubblicato().getPubblicatore().getNome() + "</nomePubblicatore>";
        messaggioConsegna += "<tipoPubblicatore>" + statoMessaggio.getIEventoPubblicato().getPubblicatore().getTipo() + "</tipoPubblicatore>";
        messaggioConsegna += "<categoriaEventi>" + statoMessaggio.getIEventoPubblicato().getCategoriaEventi().getNome() + "</categoriaEventi>";
        messaggioConsegna += "</ge:consegnaMessaggio></soap:Body></soap:Envelope>";
        return messaggioConsegna.trim();
    }

    public static String creaMessaggioConsegnaEsterno(StatoMessaggioEsterno statoMessaggio) {
        String messaggioConsegna = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:ge=\"http://ge.freesbee.unibas.it/\"><soap:Header/><soap:Body>";
        messaggioConsegna += "<ge:consegnaMessaggio><messaggioSoap>" + statoMessaggio.getIEventoPubblicato().getMessaggio() + "</messaggioSoap>";
        messaggioConsegna += "<nomePubblicatore>" + statoMessaggio.getEventoPubblicato().getNomePubblicatoreEsterno() + "</nomePubblicatore>";
        messaggioConsegna += "<tipoPubblicatore>" + statoMessaggio.getEventoPubblicato().getTipoPubblicatoreEsterno() + "</tipoPubblicatore>";
        messaggioConsegna += "<categoriaEventi>" + statoMessaggio.getIEventoPubblicato().getCategoriaEventi().getNome() + "</categoriaEventi>";
        messaggioConsegna += "</ge:consegnaMessaggio></soap:Body></soap:Envelope>";
        return messaggioConsegna.trim();
    }

    //Ripublicazione su altro getore evneti
    public static String creaMessaggioPubblica(IStatoMessaggio statoMessaggio) throws DAOException {
        GestoreEventi ge = DAOGestoreEventiFacade.getGE();
        String messaggioPubblica = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:ge=\"http://ge.freesbee.unibas.it/\"><soap:Header/><soap:Body>";
        messaggioPubblica += "<ge:pubblicaMessaggio><messaggioSoap>" + statoMessaggio.getIEventoPubblicato().getMessaggio() + "</messaggioSoap>";
        messaggioPubblica += "<nomePubblicatore>" + ge.getNome() + "</nomePubblicatore>";
        messaggioPubblica += "<tipoPubblicatore>" + ge.getTipo() + "</tipoPubblicatore>";
        messaggioPubblica += "<categoriaEventi>" + statoMessaggio.getIEventoPubblicato().getCategoriaEventi().getNome() + "</categoriaEventi>";
        messaggioPubblica += "<nomePubblicatoreEsterno>" + statoMessaggio.getIEventoPubblicato().getPubblicatore().getNome() + "</nomePubblicatoreEsterno>";
        messaggioPubblica += "<tipoPubblicatoreEsterno>" + statoMessaggio.getIEventoPubblicato().getPubblicatore().getTipo() + "</tipoPubblicatoreEsterno>";
        messaggioPubblica += "</ge:pubblicaMessaggio> </soap:Body></soap:Envelope>";
        return messaggioPubblica.trim();
    }

    public static String creaMessaggioRicercaPubblicatori(SottoscrizioneEsterna sottoscrizione) throws DAOException {
        if (!DAOSottoscrizioneEsternaFacade.isInAttesaConfermaPubblicatori(sottoscrizione)) {
            throw new IllegalArgumentException("E' stata richiesta la ricerca dei pubblicatori per una sottoscrizione che non e' in attesa.");
        }

        GestoreEventi ge = DAOGestoreEventiFacade.getGE();

        String pubblicatori = "";
        for (FiltroPubblicatoreEsterno filtroPubblicatore : sottoscrizione.getListaFiltroPublicatore()) {
            if (DAOPubblicatoreEsternoFacade.isInAttesaPubblicatoreEsternoPerCategoriaEventi(sottoscrizione.getCategoriaEventi(), filtroPubblicatore.getPubblicatore())) {
                pubblicatori += "<pubblicatore>" +
                        "<nomePubblicatore>" + filtroPubblicatore.getPubblicatore().getNome() + "</nomePubblicatore>" +
                        "<tipoPubblicatore>" + filtroPubblicatore.getPubblicatore().getTipo() + "</tipoPubblicatore>" +
                        "</pubblicatore> ";
            }
        }

        String messaggio =
                "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"  xmlns:ge=\"http://ge.freesbee.unibas.it/\">" +
                "<SOAP-ENV:Body>" +
                "<ge:pubblicaMessaggio>" +
                "<messaggioSoap>" +
                "<ricercaPubblicatori>" + pubblicatori +
                "<categoriaSottoscrizione>" + sottoscrizione.getCategoriaEventi().getNome() + "</categoriaSottoscrizione>" + "</ricercaPubblicatori>" +
                "</messaggioSoap>" +
                "<nomePubblicatore>" + ge.getNome() + "</nomePubblicatore>" +
                "<tipoPubblicatore>" + ge.getTipo() + "</tipoPubblicatore>" +
                "<categoriaEventi>GEControlProtocol</categoriaEventi>" +
                "</ge:pubblicaMessaggio>" +
                "</SOAP-ENV:Body>" +
                "</SOAP-ENV:Envelope>";

        return messaggio;
    }

    public static String creaMessaggioRispostaRicercaPubblicatori(String categoriaSottoscrizione, List<PubblicatoreInterno> pubblicatoriConfermati) throws DAOException {
        GestoreEventi ge = DAOGestoreEventiFacade.getGE();

        String pubblicatori = "";
        for (PubblicatoreInterno pubblicatore : pubblicatoriConfermati) {
            pubblicatori += "<pubblicatore>" +
                    "<nomePubblicatore>" + pubblicatore.getNome() + "</nomePubblicatore>" +
                    "<tipoPubblicatore>" + pubblicatore.getTipo() + "</tipoPubblicatore>" +
                    "</pubblicatore> ";
        }



        String messaggio =
                "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"  xmlns:ge=\"http://ge.freesbee.unibas.it/\">" +
                "<SOAP-ENV:Body>" +
                "<ge:pubblicaMessaggio>" +
                "<messaggioSoap>" +
                "<confermaPubblicatori>" + pubblicatori +
                "<categoriaSottoscrizione>" + categoriaSottoscrizione + "</categoriaSottoscrizione>" +
                "</confermaPubblicatori>" +
                "</messaggioSoap>" +
                "<nomePubblicatore>" + ge.getNome() + "</nomePubblicatore>" +
                "<tipoPubblicatore>" + ge.getTipo() + "</tipoPubblicatore>" +
                "<categoriaEventi>GEControlProtocol</categoriaEventi>" +
                "</ge:pubblicaMessaggio>" +
                "</SOAP-ENV:Body>" +
                "</SOAP-ENV:Envelope>";
        return messaggio;
    }

    public static String creaMessaggioRicercaCategoriaEventi(CategoriaEventiEsterna categoriaEventiEsterna) throws DAOException {
        GestoreEventi ge = DAOGestoreEventiFacade.getGE();

        String messaggio =
                "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"  xmlns:ge=\"http://ge.freesbee.unibas.it/\">" +
                "<SOAP-ENV:Body>" +
                "<ge:pubblicaMessaggio>" +
                "<messaggioSoap>" +
                "<ricercaCategoria>" +
                "<categoriaEventiConferma>" + categoriaEventiEsterna.getNome() + "</categoriaEventiConferma>" +
                "</ricercaCategoria>" +
                "</messaggioSoap>" +
                "<nomePubblicatore>" + ge.getNome() + "</nomePubblicatore>" +
                "<tipoPubblicatore>" + ge.getTipo() + "</tipoPubblicatore>" +
                "<categoriaEventi>GEControlProtocol</categoriaEventi>" +
                "</ge:pubblicaMessaggio>" +
                "</SOAP-ENV:Body>" +
                "</SOAP-ENV:Envelope>";

        return messaggio;
    }

    public static String creaMessaggioRispostaRicercaCategoriaEventi(CategoriaEventiInterna categoriaEventiInterna) throws DAOException {
        GestoreEventi ge = DAOGestoreEventiFacade.getGE();

        String messaggio =
                "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"  xmlns:ge=\"http://ge.freesbee.unibas.it/\">" +
                "<SOAP-ENV:Body>" +
                "<ge:pubblicaMessaggio>" +
                "<messaggioSoap>" +
                "<confermaCategoria>" +
                "<categoriaEventiConferma>" + categoriaEventiInterna.getNome() + "</categoriaEventiConferma>" +
                "</confermaCategoria>" +
                "</messaggioSoap>" +
                "<nomePubblicatore>" + ge.getNome() + "</nomePubblicatore>" +
                "<tipoPubblicatore>" + ge.getTipo() + "</tipoPubblicatore>" +
                "<categoriaEventi>GEControlProtocol</categoriaEventi>" +
                "</ge:pubblicaMessaggio>" +
                "</SOAP-ENV:Body>" +
                "</SOAP-ENV:Envelope>";
        return messaggio;
    }

    public static String creaMessaggioComunicazioneRimozioneSottoscrizione(String causa, Sottoscrittore sottoscrittore, ICategoriaEventi categoriaEvneti, GestoreEventi ge) {
        String messaggio =
                "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"  xmlns:ge=\"http://ge.freesbee.unibas.it/\">" +
                "<SOAP-ENV:Body>" +
                "<ge:pubblicaMessaggio>" +
                "<messaggioSoap>" +
                "<comunicazioneEliminazioneSottoscrizione><sottoscrittore>" +
                "<nomeSottoscrittore>" + sottoscrittore.getNome() + "</nomeSottoscrittore>" +
                "<tipoSottoscrittore>" + sottoscrittore.getTipo() + "</tipoSottoscrittore></sottoscrittore>" +
                "<categoriaPubblicata>" + categoriaEvneti.getNome() + "</categoriaPubblicata>" +
                "<causa>" + causa + "</causa>" +
                "</comunicazioneEliminazioneSottoscrizione>" +
                "</messaggioSoap>" +
                "<nomePubblicatore>" + ge.getNome() + "</nomePubblicatore>" +
                "<tipoPubblicatore>" + ge.getTipo() + "</tipoPubblicatore>" +
                "<categoriaEventi>GEControlProtocol</categoriaEventi>" +
                "</ge:pubblicaMessaggio>" +
                "</SOAP-ENV:Body>" +
                "</SOAP-ENV:Envelope>";
        return messaggio;
    }

    public static String creaMessaggioPrelevaMessaggio(String id, String tipoSottoscrittore, String nomeSottoscrittore, String categoriaEventi) {
        String messaggio =
                "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:ge=\"http://ge.freesbee.unibas.it/\">" +
                "<soap:Header/><soap:Body><ge:prelevaMessaggio>" +
                "<idMessaggio>" + id + "</idMessaggio>" +
                "<nomeSottoscrittore>" + nomeSottoscrittore + "</nomeSottoscrittore>" +
                "<tipoSottoscrittore>" + tipoSottoscrittore + "</tipoSottoscrittore>" +
                "<categoriaEventi>" + categoriaEventi + "</categoriaEventi>" +
                "</ge:prelevaMessaggio></soap:Body></soap:Envelope>";

        return messaggio;

    }

    public static String creaMessaggioRimuoviSottoscrizione(CategoriaEventiEsterna categoriaEventi) throws DAOException {
        GestoreEventi ge = DAOGestoreEventiFacade.getGE();

        String messaggio =
                "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"  xmlns:ge=\"http://ge.freesbee.unibas.it/\">" +
                "<SOAP-ENV:Body>" +
                "<ge:pubblicaMessaggio>" +
                "<messaggioSoap>" +
                "<rimuoviGESottoscrittore>" +
                "<nomeSottoscrittore>" + ge.getNome() + "</nomeSottoscrittore>" +
                "<tipoSottoscrittore>" + ge.getTipo() + "</tipoSottoscrittore>" +
                "<categoriaSottoscrizione>" + categoriaEventi.getNome() + "</categoriaSottoscrizione>" +
                "</rimuoviGESottoscrittore>" +
                "</messaggioSoap>" +
                "<nomePubblicatore>" + ge.getNome() + "</nomePubblicatore>" +
                "<tipoPubblicatore>" + ge.getTipo() + "</tipoPubblicatore>" +
                "<categoriaEventi>GEControlProtocol</categoriaEventi>" +
                "</ge:pubblicaMessaggio>" +
                "</SOAP-ENV:Body>" +
                "</SOAP-ENV:Envelope>";

        return messaggio;
    }
}
