package it.unibas.freesbee.ge.ws.gestoreeventi;

import it.unibas.freesbee.ge.messaggi.Messaggi;
import it.unibas.freesbee.ge.modello.CategoriaEventiEsterna;
import it.unibas.freesbee.ge.modello.CategoriaEventiInterna;
import it.unibas.freesbee.ge.modello.Configurazione;
import it.unibas.freesbee.ge.modello.ConfigurazioneSP;
import it.unibas.freesbee.ge.modello.ConfigurazioniFactory;
import it.unibas.freesbee.ge.modello.GestoreEventi;
import it.unibas.freesbee.ge.modello.ICategoriaEventi;
import it.unibas.freesbee.ge.modello.IPubblicatore;
import it.unibas.freesbee.ge.modello.PubblicatoreInterno;
import it.unibas.freesbee.ge.modello.Sottoscrittore;
import it.unibas.freesbee.ge.modello.SottoscrizioneEsterna;
import it.unibas.freesbee.ge.persistenza.DAOException;
import it.unibas.freesbee.ge.persistenza.IDAOGestoreEventi;
import it.unibas.freesbee.ge.persistenza.facade.DAOGestoreEventiFacade;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCostanti;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOGestoreEventiHibernate;
import it.unibas.freesbee.utilita.ClientPD;
import it.unibas.springfreesbee.ge.ConfigurazioneStatico;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ClientGEControlProtocol {

    private static Log logger = LogFactory.getLog(ClientGEControlProtocol.class);

    public static void pubblicaEsternamenteComunicazioneRimozionePubblicatore(PubblicatoreInterno pubblicatore, CategoriaEventiInterna categoriaEventiInterna) {
        try {
            //Verifica l'esistena del GE stesso
            GestoreEventi ge = DAOGestoreEventiFacade.getGE();

            IDAOGestoreEventi daoGestoreEventi = new DAOGestoreEventiHibernate();

            String messaggioInviare = Messaggi.creaMessaggioComunicazioneRimozionePubblicatore(pubblicatore, categoriaEventiInterna, ge);

            for (GestoreEventi gestoreEventi : daoGestoreEventi.findAll()) {

                if (!gestoreEventi.equals(ge)) {

                    logger.debug("Pubblica  la comunicazione di rimozione del pubblicatore sul gestore eventi " + gestoreEventi);

                    pubblica(gestoreEventi, messaggioInviare, "comunicazione di rimozione del pubblicatore " + pubblicatore, null);
                }
            }

        } catch (DAOException ex) {
            logger.error("Impossibile leggere l'elenco dei gestori eventi " + ex);
        }
    }

    public static void pubblicaInternamenteComunicazioneRimozionePubblicatore(IPubblicatore pubblicatore, ICategoriaEventi categoriaEventiInterna) {
        try {
            //Verifica l'esistena del GE stesso
            GestoreEventi ge = DAOGestoreEventiFacade.getGE();

            String messaggioInviare = Messaggi.creaMessaggioComunicazioneRimozionePubblicatore(pubblicatore, categoriaEventiInterna, ge);

            logger.debug("Pubblico la comunicazione di rimozione del pubblicatore sul gestore eventi " + ge);

            String indirizzo = "http://" + ConfigurazioneStatico.getInstance().getWebservicesIndirizzo() + ":" + ConfigurazioneStatico.getInstance().getWebservicesPort() + DAOCostanti.URL_WSDL_GESTORE_EVENTI;

            ClientPD clientPD = new ClientPD();

            clientPD.invocaPortaDelegata(indirizzo, messaggioInviare);

        } catch (DAOException ex) {
            logger.error("Impossibile verificare il gestori eventi " + ex);
        } catch (Exception ex) {
            logger.error("Impossibile pubblicare su me stesso la comunicazione di rimozione del pubblicatore");
            ex.printStackTrace();
        }

    }

    public static void pubblicaInternamenteComunicazioneRimozioneSottoscrizione(String causa, Sottoscrittore sottoscrittore, ICategoriaEventi categoriaEventiInterna) {
        try {
            //Verifica l'esistena del GE stesso
            GestoreEventi ge = DAOGestoreEventiFacade.getGE();

            String messaggioInviare = Messaggi.creaMessaggioComunicazioneRimozioneSottoscrizione(causa, sottoscrittore, categoriaEventiInterna, ge);

            logger.debug("Pubblico la comunicazione di rimozione della sotoscrizione sul gestore eventi " + ge);

            String indirizzo = "http://" + ConfigurazioneStatico.getInstance().getWebservicesIndirizzo() + ":" + ConfigurazioneStatico.getInstance().getWebservicesPort() + DAOCostanti.URL_WSDL_GESTORE_EVENTI;

            ClientPD clientPD = new ClientPD();

            clientPD.invocaPortaDelegata(indirizzo, messaggioInviare);

        } catch (DAOException ex) {
            logger.error("Impossibile verificare il gestori eventi " + ex);
        } catch (Exception ex) {
            logger.error("Impossibile pubblicare su me stesso la comunicazione di rimozione della sottoscrizione");
            ex.printStackTrace();
        }

    }

    public static void pubblicaRichiesteRicercaPubblicatori(SottoscrizioneEsterna sottoscrizione) throws DAOException {
        try {
            String messaggioInviare = Messaggi.creaMessaggioRicercaPubblicatori(sottoscrizione);

            GestoreEventi ge = DAOGestoreEventiFacade.getGE();

            IDAOGestoreEventi daoGestoreEventi = new DAOGestoreEventiHibernate();

            for (GestoreEventi gestoreEventi : daoGestoreEventi.findAll()) {

                if (!gestoreEventi.equals(ge)) {

                    logger.debug("Pubblico la richiesta di informanzioni sui pubblicatori sul gestore eventi " + gestoreEventi);

                    pubblica(gestoreEventi, messaggioInviare, "richiesta conferma pubblicatori ", null);
                }

            }

        } catch (DAOException ex) {
            logger.error("Impossibile leggere l'elenco dei gestori eventi " + ex);
        }
    }

    public static void pubblicaRispostaRicercaPubblicatori(GestoreEventi gestoreEventi, String categoriaSottoscrizione, List<PubblicatoreInterno> pubblicatoriConfermati) {
        try {
            String messaggioRisposta = Messaggi.creaMessaggioRispostaRicercaPubblicatori(categoriaSottoscrizione, pubblicatoriConfermati);

            logger.debug("Pubblico la risposta di informanzioni sui pubblicatori sul gestore eventi " + gestoreEventi);

            pubblica(gestoreEventi, messaggioRisposta, "la risposta per i pubblicatori da confermare", null);

        } catch (DAOException ex) {
            logger.error("Impossibile leggere l'elenco dei gestori eventi " + ex);
        }
    }

    public static void pubblicaRicercaCategoriaEventi(CategoriaEventiEsterna categoriaEventiEsterna) {
        try {
            //Verifica l'esistena del GE stesso
            GestoreEventi ge = DAOGestoreEventiFacade.getGE();

            IDAOGestoreEventi daoGestoreEventi = new DAOGestoreEventiHibernate();

            String messaggioInviare = Messaggi.creaMessaggioRicercaCategoriaEventi(categoriaEventiEsterna);

            for (GestoreEventi gestoreEventi : daoGestoreEventi.findAll()) {

                if (!gestoreEventi.equals(ge)) {

                    logger.debug("Pubblica la richiesta di informazioni sulla categoria eventi sul gestore eventi " + gestoreEventi);

                    pubblica(gestoreEventi, messaggioInviare, "richiesta di informazioni per la categoria di eventi " + categoriaEventiEsterna.getNome(), null);
                }
            }

        } catch (DAOException ex) {
            logger.error("Impossibile leggere l'elenco dei gestori eventi " + ex);
        }
    }

    public static void pubblicaRispostaRicercaCategoriaEventi(CategoriaEventiInterna categoriaEventiInterna, GestoreEventi gestoreEventi) {
        try {

            String messaggioInviare = Messaggi.creaMessaggioRispostaRicercaCategoriaEventi(categoriaEventiInterna);

            logger.debug("Pubblica la risposta di richiesta di informazioni sulla categoria eventi sul gestore eventi " + gestoreEventi);

            pubblica(gestoreEventi, messaggioInviare, " la risposta di richiesta di informazioni per la categoria di eventi " + categoriaEventiInterna, null);


        } catch (DAOException ex) {
            logger.error("Impossibile leggere l'elenco dei gestori eventi " + ex);
        }
    }

    public static void pubblicaRimuoviSottoscrizione(CategoriaEventiEsterna categoriaEventiEsterna, GestoreEventi gestoreEventi) {
        try {

            String messaggioInviare = Messaggi.creaMessaggioRimuoviSottoscrizione(categoriaEventiEsterna);

            logger.debug("Pubblica la richiesta di rimozione di una sottoscrizione per la categoria eventi " + categoriaEventiEsterna + " sul gestore eventi " + gestoreEventi);

            //Serve per poter effettuare i test
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("TIPO_ELIMINAZIONE", "ELIMINAZIONE_SOTTOSCRIZIONE");

            pubblica(gestoreEventi, messaggioInviare, "richiesta di informazioni per la categoria di eventi " + categoriaEventiEsterna.getNome(), headers);



        } catch (DAOException ex) {
            logger.error("Impossibile leggere l'elenco dei gestori eventi " + ex);
        }
    }

    private static void pubblica(GestoreEventi gestoreEventi, String messaggioInviare, String errore, Map<String, String> headers) {
        try {
            ClientPD clientPD = new ClientPD();

            ConfigurazioneSP configurazioneSP = ConfigurazioniFactory.getConfigurazioneSPIstance();
            Configurazione configurazione = ConfigurazioniFactory.getConfigurazioneIstance();

            String url;
            clientPD.ripulisci();

            //il messaggio verrà inviato ad un altro GE
            if (ConfigurazioneStatico.getInstance().isProtezioneSP()) {
                url = configurazioneSP.getUrlFreesbeeSP() + "?" + "autenticazione=" + configurazioneSP.getAutenticazione() + "&" + "risorsa=" + configurazioneSP.getRisorsa() + configurazioneSP.getRisorsaPdPubblica() + "/" + "&" + "username=" + configurazioneSP.getNomeUtenteSP() + "&" + "password=" + configurazioneSP.getPasswordSP();
            } else {
                url = configurazione.getPdPubblica();
            }

            logger.debug("url: " + url);
            if (headers != null) {
                clientPD.setHeaders(headers);
            }
            clientPD.setErogatore(gestoreEventi.getNome());
            clientPD.setTipo_erogatore(gestoreEventi.getTipo());
            clientPD.setFruitore(configurazione.getNomeGestoreEventi());
            clientPD.setTipo_fruitore(configurazione.getTipoGestoreEventi());
//            clientPD.setServizio(configurazione.getNomeServizioPubblica() + "_" + ICategoriaEventi.GE_CONTROL_PROTOCOL);
            clientPD.setServizio(configurazione.getNomeServizioPubblica());
            clientPD.setTipo_servizio(configurazione.getTipoServizioPubblica());


            logger.debug("Messaggio Da Consegnare/Pubblicare : " + messaggioInviare);
            String ris = clientPD.invocaPortaDelegata(url, messaggioInviare);
            logger.debug("Risposta invocazione pd: " + ris);

        } catch (Exception ex) {
            logger.error("Impossibile pubblicare " + errore + " sul gestore eventi " + gestoreEventi);
            ex.printStackTrace();
        }
    }
}
