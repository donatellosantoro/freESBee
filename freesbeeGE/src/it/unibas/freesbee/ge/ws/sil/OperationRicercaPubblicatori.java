package it.unibas.freesbee.ge.ws.sil;

import it.unibas.freesbee.ge.modello.CategoriaEventiEsterna;
import it.unibas.freesbee.ge.modello.CategoriaEventiInterna;
import it.unibas.freesbee.ge.modello.FiltroData;
import it.unibas.freesbee.ge.modello.FiltroPubblicatoreInterno;
import it.unibas.freesbee.ge.modello.GestoreEventi;
import it.unibas.freesbee.ge.modello.ICategoriaEventi;
import it.unibas.freesbee.ge.modello.IPubblicatore;
import it.unibas.freesbee.ge.modello.ISottoscrizione;
import it.unibas.freesbee.ge.modello.PubblicatoreEsterno;
import it.unibas.freesbee.ge.modello.PubblicatoreInterno;
import it.unibas.freesbee.ge.modello.Sottoscrittore;
import it.unibas.freesbee.ge.modello.SottoscrizioneEsterna;
import it.unibas.freesbee.ge.modello.SottoscrizioneInterna;
import it.unibas.freesbee.ge.persistenza.DAOException;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiEsterna;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiInterna;
import it.unibas.freesbee.ge.persistenza.IDAOGestoreEventi;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreEsterno;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreInterno;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrittore;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrizioneEsterna;
import it.unibas.freesbee.ge.persistenza.facade.DAOCategoriaEventiEsternaFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOGestoreEventiFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOPubblicatoreEsternoFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOPubblicatoreInternoFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOSottoscrizioneEsternaFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOSottoscrizioneInternaFacade;
import it.unibas.freesbee.ge.ws.gestoreeventi.ClientGEControlProtocol;
import it.unibas.freesbee.ge.ws.gestoreeventi.SOAPFault;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class OperationRicercaPubblicatori {

    private static org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog(WSConsegnaMessaggioImpl.class);
    private IDAOSottoscrittore daoSottoscrittore;
    private IDAOSottoscrizioneEsterna daoSottoscrizioneEsterna;
    private IDAOCategoriaEventiInterna daoCategoriaEventiInterna;
    private IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna;
    private IDAOPubblicatoreEsterno daoPubblicatoreEsterno;
    private IDAOPubblicatoreInterno daoPubblicatoreInterno;
    private IDAOGestoreEventi daoGestoreEventi;

    public OperationRicercaPubblicatori(IDAOSottoscrittore daoSottoscrittore, IDAOCategoriaEventiInterna daoCategoriaEventiInterna, IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna, IDAOGestoreEventi daoGestoreEventi, IDAOPubblicatoreInterno daoPubblicatoreInterno, IDAOPubblicatoreEsterno daoPubblicatoreEsterno, IDAOSottoscrizioneEsterna daoSottoscrizioneEsterna) {
        this.daoCategoriaEventiInterna = daoCategoriaEventiInterna;
        this.daoCategoriaEventiEsterna = daoCategoriaEventiEsterna;
        this.daoSottoscrittore = daoSottoscrittore;
        this.daoGestoreEventi = daoGestoreEventi;
        this.daoPubblicatoreInterno = daoPubblicatoreInterno;
        this.daoPubblicatoreEsterno = daoPubblicatoreEsterno;
        this.daoSottoscrizioneEsterna = daoSottoscrizioneEsterna;
    }

    public void gestisciRichiesta(Node elOperation, String tipoPubblicatore, String nomePubblicatore) throws SOAPFault {

        String categoriaSottoscrizione = leggiCategoriaSottoscrizione(elOperation);

        logger.debug("La richiesta di conferma dei pubblicatori e' stata pubblicata dal gestore eventi " + tipoPubblicatore + "  " + nomePubblicatore);
        logger.debug("La categoria di eventi dei pubblicatori da confermare e' " + categoriaSottoscrizione);

        List<PubblicatoreInterno> pubblicatoriConfermare = leggiPubblicatoriInterni(elOperation);

        if (pubblicatoriConfermare.size() == 0) {
            logger.error("La richiesta di informazioni sui pubblicatori deve contenere la lista dei pubblicatori");
            throw new SOAPFault("La richiesta di informazioni sui pubblicatori deve contenere la lista dei pubblicatori");
        }

        logger.debug("I publicatori da confermare sono " + pubblicatoriConfermare);

        try {

            GestoreEventi ge = DAOGestoreEventiFacade.getGE();

            GestoreEventi gestoreEventi = daoGestoreEventi.findByTipoNome(tipoPubblicatore, nomePubblicatore);

            if (gestoreEventi == null) {
                logger.error("Il gestore eventi " + tipoPubblicatore + " " + nomePubblicatore + " non e' conosciuto");
                throw new SOAPFault("Il gestore eventi " + tipoPubblicatore + " " + nomePubblicatore + " non e' conosciuto");
            }


            CategoriaEventiInterna categoriaEventi = daoCategoriaEventiInterna.findByNome(categoriaSottoscrizione);

            if (categoriaEventi != null) {

                if (categoriaEventi.getNome().equals(ICategoriaEventi.GE_CONTROL_PROTOCOL)) {
                    logger.error("Non si possono confermare pubblicatori per la categoria di eventi " + ICategoriaEventi.GE_CONTROL_PROTOCOL);
                    throw new SOAPFault("Non si possono confermare pubblicatori per la categoria di eventi " + ICategoriaEventi.GE_CONTROL_PROTOCOL);
                }

                if (!categoriaEventi.isAttiva()) {
                    logger.error("La categoria di eventi " + categoriaEventi + " non e' attiva");
                    throw new SOAPFault("La categoria di eventi " + categoriaEventi + " non e' attiva");
                }

                PubblicatoreInterno pubblicatoreInterno = daoPubblicatoreInterno.findByTipoNome(ge.getTipo(), ge.getNome());

                if (pubblicatoreInterno == null || !DAOPubblicatoreInternoFacade.isPubblicatoreInternoPerCategoriaEventiInterna(categoriaEventi, pubblicatoreInterno)) {
                    logger.error("Il gestore eventi " + ge + " non e' pubblicatore per la categoria di eventi " + categoriaEventi);
                    throw new SOAPFault("Il gestore eventi " + ge + " non e' pubblicatore per la categoria di eventi " + categoriaEventi);
                }


                //Verifico i pubblicatori
                for (PubblicatoreInterno soggetto : pubblicatoriConfermare) {
                    //Lancia eccezione se non si è specificato il nome o il tipo del pubblicatore
                    convalidaDati(soggetto);
                    if (!DAOPubblicatoreInternoFacade.isPubblicatoreInternoPerCategoriaEventiInterna(categoriaEventi, soggetto)) {
                        logger.info("Uno o piu' soggetti non sono pubblicatori per la categoria di eventi");
                        return;
                    }
                }

                Sottoscrittore sottoscrittore = daoSottoscrittore.findByTipoNome(tipoPubblicatore, nomePubblicatore);
                if (sottoscrittore == null) {
                    sottoscrittore = new Sottoscrittore(tipoPubblicatore, nomePubblicatore);
                }

                SottoscrizioneInterna sottoscrizione = daoCategoriaEventiInterna.findByCategoriaEventiSottoscrittore(categoriaEventi, sottoscrittore);

                if (sottoscrizione != null) {
                    logger.info("Il gestore eventi " + sottoscrittore + " e' gia' sottoscrittore per la categoria " + categoriaEventi);
                    ClientGEControlProtocol.pubblicaRispostaRicercaPubblicatori(gestoreEventi, categoriaSottoscrizione, pubblicatoriConfermare);
                    return;
                }


                logger.debug("Aggiungo il gestore eventi " + tipoPubblicatore + " " + nomePubblicatore + " come sottoscrittore per la categoria " + categoriaEventi);
                Calendar date = new GregorianCalendar();
                date.add(Calendar.MINUTE, 2);
                FiltroData filtroData = new FiltroData(date.getTime());

                sottoscrizione = new SottoscrizioneInterna(sottoscrittore, categoriaEventi, ISottoscrizione.TIPO_SOTTOSCRIZIONE_CONSEGNA, filtroData);
                FiltroPubblicatoreInterno filtroPubblicatore = new FiltroPubblicatoreInterno(pubblicatoreInterno, sottoscrizione);
                sottoscrizione.getListaFiltroPublicatore().add(filtroPubblicatore);
                DAOSottoscrizioneInternaFacade.aggiungiSottoscrizioneInterna(categoriaEventi, sottoscrittore, sottoscrizione);

                ClientGEControlProtocol.pubblicaRispostaRicercaPubblicatori(gestoreEventi, categoriaSottoscrizione, pubblicatoriConfermare);

            } else {
                logger.info("La categoria di eventi " + categoriaSottoscrizione + " non e' del dominio del gestore eventi");

            }

        } catch (DAOException e) {
            logger.error(e.getMessage());
            throw new SOAPFault(e.getMessage());
        }
    }

    public void gestisciRisposta(Node elOperation, String tipoPubblicatore, String nomePubblicatore) throws SOAPFault {
        String categoriaSottoscrizione = leggiCategoriaSottoscrizione(elOperation);

        logger.debug("La conferma dei pubblicatori e' stata pubblicata dal gestore eventi " + tipoPubblicatore + "  " + nomePubblicatore);
        logger.debug("La categoria di eventi dei pubblicatori confermati e' " + categoriaSottoscrizione);

        try {

            GestoreEventi gestoreEventi = daoGestoreEventi.findByTipoNome(tipoPubblicatore, nomePubblicatore);

            PubblicatoreEsterno pubblicatoreConferma = daoPubblicatoreEsterno.findByTipoNome(tipoPubblicatore, nomePubblicatore);


            if (gestoreEventi == null) {
                logger.error("Il gestore eventi " + tipoPubblicatore + " " + nomePubblicatore + " non e' conosciuto");
                throw new SOAPFault("Il gestore eventi " + tipoPubblicatore + " " + nomePubblicatore + " non e' conosciuto");
            }

            CategoriaEventiEsterna categoriaEventi = daoCategoriaEventiEsterna.findByNome(categoriaSottoscrizione);

            if (categoriaEventi != null) {

                if (categoriaEventi.getNome().equals(ICategoriaEventi.GE_CONTROL_PROTOCOL)) {
                    logger.error("La categoria di eventi " + ICategoriaEventi.GE_CONTROL_PROTOCOL + " non puo' essere confermata");
                    throw new SOAPFault("La categoria di eventi " + ICategoriaEventi.GE_CONTROL_PROTOCOL + " non puo'essere confermata");
                }

                if (!categoriaEventi.isAttiva()) {
                    logger.error("La categoria di eventi " + categoriaSottoscrizione + " non e' attiva");
                    throw new SOAPFault("La categoria di eventi " + categoriaSottoscrizione + " non e' attiva");
                }


                if (!categoriaEventi.isInAttesa()) {
                    logger.debug("La categoria di eventi " + categoriaSottoscrizione + " e' confermata");

                    PubblicatoreEsterno pubblicatoreGE = DAOCategoriaEventiEsternaFacade.getGestoreEvetniPubblicatorePerCategoriaEventi(categoriaEventi);

                    if (pubblicatoreGE == null) {
                        logger.error("La categoria di eventi " + categoriaSottoscrizione + " risulta confermata ma non esiste un gestore eventi pubblicatore per questa categoria");
                        throw new SOAPFault("La categoria di eventi " + categoriaSottoscrizione + " risulta confermata ma non esiste un gestore eventi pubblicatore per questa categoria");
                    }

                    if (pubblicatoreGE != null) {
                        logger.debug("Il gestore eventi " + pubblicatoreGE + " e' il pubblicatore per la categoria di eventi " + categoriaSottoscrizione);

                        if (pubblicatoreConferma == null || pubblicatoreGE.compareTo(pubblicatoreConferma) != 0) {
                            logger.error("Il gestore eventi " + tipoPubblicatore + " " + nomePubblicatore + " che ha confermato i pubblicatori e' differente dall'attuale pubblicatore " + pubblicatoreGE + " della categoria ");
                            throw new SOAPFault("Il gestore eventi " + tipoPubblicatore + " " + nomePubblicatore + " che ha confermato i pubblicatori e' differente dall'attuale pubblicatore " + pubblicatoreGE + " della categoria ");
                        }

                    }
                }

                List<PubblicatoreEsterno> pubblicatoriConfermati = leggiPubblicatorEsterni(elOperation);
                //Conferma  dei pubblicatori
                logger.debug("Si confermano i pubblicatori " + pubblicatoriConfermati);

                for (PubblicatoreEsterno pubblicatore : pubblicatoriConfermati) {
                    convalidaDati(pubblicatore);
                    PubblicatoreEsterno p = daoPubblicatoreEsterno.findByTipoNome(pubblicatore.getTipo(), pubblicatore.getNome());
                    if (p != null && DAOPubblicatoreEsternoFacade.isPubblicatoreEsternoDaConfermare(categoriaEventi, p)) {
                        if (DAOPubblicatoreEsternoFacade.isInAttesaPubblicatoreEsternoPerCategoriaEventi(categoriaEventi, pubblicatore)) {
                            logger.info("Confermato il pubblicatore " + pubblicatore + " per la categoria di eventi " + categoriaSottoscrizione);
                            DAOPubblicatoreEsternoFacade.aggiungiPubblicatoreEsterno(categoriaEventi, pubblicatore);
                        } else {
                            logger.info("Il pubblicatore " + pubblicatore + " per cui e'arrivata la conferma e' gia' stato confermato");
                        }
                    } else {
                        logger.info("Il pubblicatore " + pubblicatore + " per cui e'arrivata la conferma non e' un pubblicatore da confermare per la cateogria di eventi " + categoriaSottoscrizione);
                    }
                }

                List<SottoscrizioneEsterna> listaSottoscrizioni = categoriaEventi.getListaSottoscrizioni();
                for (SottoscrizioneEsterna sot : listaSottoscrizioni) {
                    //Le sottoscrizioni in attesa di conferma dei pubblicatori dieventano automaticamente confermate nel momento in cui si
                    //sonfermano i pubblicatori manca solo annullare la scadenza
                    if ((!DAOSottoscrizioneEsternaFacade.isInAttesaConfermaPubblicatori(sot)) && sot.getScadenzaAttesa() != null) {
                        logger.debug("Conferma sottoscrizione del sottoscrittore " + sot.getSottoscrittore() + " per la categoria di eventi " + sot.getCategoriaEventi().getNome());
                        sot.setScadenzaAttesa(null);
                        daoSottoscrizioneEsterna.makePersistent(sot);
                    }
                }
                logger.debug("Confermate le sottoscrizioni della categoria di eventi " + categoriaEventi + " in attesa di conferma");


                if (categoriaEventi.isInAttesa()) {
                    logger.debug("La categoria di eventi " + categoriaSottoscrizione + " non e' confermata");
                    categoriaEventi.setInAttesa(false);
                    daoCategoriaEventiEsterna.makePersistent(categoriaEventi);
                    if (pubblicatoreConferma == null) {
                        pubblicatoreConferma = new PubblicatoreEsterno(gestoreEventi.getTipo(), gestoreEventi.getNome());
                    }

                    DAOPubblicatoreEsternoFacade.aggiungiPubblicatoreEsterno(categoriaEventi, pubblicatoreConferma);
                    logger.debug("Confermata la categoria di eventi " + categoriaEventi);
                    logger.debug("Aggiunto il gestore eventi " + gestoreEventi + " come pubblicatore della categoria di eventi " + categoriaEventi);
                }

            } else {
                logger.error("La categoria eventi " + categoriaSottoscrizione + " non esiste ");
                throw new SOAPFault("La categoria eventi " + categoriaSottoscrizione + " non esiste ");
            }


        } catch (DAOException ex) {
            throw new SOAPFault("Errore nella lettura dal db. " + ex);
        }
    }

    private List<PubblicatoreInterno> leggiPubblicatoriInterni(Node elOperation) throws SOAPFault {
        //Carica la lista dei pubblicatori da confermare
        List<Node> nodePubblicatori = cercaNodi(elOperation, "pubblicatore");

        List<PubblicatoreInterno> listaPubblicatori = new ArrayList<PubblicatoreInterno>();

        for (int i = 0; i < nodePubblicatori.size(); i++) {
            Node n = nodePubblicatori.get(i);
            if (n.getNodeName().equalsIgnoreCase("pubblicatore")) {
                PubblicatoreInterno s = new PubblicatoreInterno();
                s.setNome(leggiTestoNodo(n, "nomePubblicatore", "il nome del pubblicatore"));
                s.setTipo(leggiTestoNodo(n, "tipoPubblicatore", "il tipo del pubblicatore"));
                listaPubblicatori.add(s);
            }
        }
        return listaPubblicatori;
    }

    private List<PubblicatoreEsterno> leggiPubblicatorEsterni(Node elOperation) throws SOAPFault {
        //Carica la lista dei pubblicatori da confermare
        List<Node> nodePubblicatori = cercaNodi(elOperation, "pubblicatore");

        List<PubblicatoreEsterno> listaPubblicatori = new ArrayList<PubblicatoreEsterno>();

        for (int i = 0; i < nodePubblicatori.size(); i++) {
            Node n = nodePubblicatori.get(i);
            if (n.getNodeName().equalsIgnoreCase("pubblicatore")) {
                PubblicatoreEsterno s = new PubblicatoreEsterno();
                s.setNome(leggiTestoNodo(n, "nomePubblicatore", "il nome del pubblicatore"));
                s.setTipo(leggiTestoNodo(n, "tipoPubblicatore", "il tipo del pubblicatore"));
                listaPubblicatori.add(s);
            }
        }
        return listaPubblicatori;
    }

//    private String leggiNomeSottoscrittore(Node elOperation) throws SOAPFault {
//        return leggiTestoNodo(elOperation, "nomeSottoscrittore", "il nome del sottoscrittore");
//    }
//
//    private String leggiTipoSottoscrittore(Node elOperation) throws SOAPFault {
//        return leggiTestoNodo(elOperation, "tipoSottoscrittore", "il tipo del sottoscrittore");
//    }

    private String leggiCategoriaSottoscrizione(Node elOperation) throws SOAPFault {
        return leggiTestoNodo(elOperation, "categoriaSottoscrizione", "la categoria di sottoscrizione");
    }

    private List<Node> cercaNodi(Node nodoPadre, String string) {
        List<Node> nodi = new ArrayList<Node>();
        NodeList nodeList = nodoPadre.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node n = nodeList.item(i);
            if (n.getNodeName().equalsIgnoreCase(string)) {
                nodi.add(n);
            }
        }
        return nodi;
    }

    private Node cercaNodo(Node nodoPadre, String string) {
        NodeList nodeList = nodoPadre.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node n = nodeList.item(i);
            if (n.getNodeName().equalsIgnoreCase(string)) {
                return n;
            }
        }
        return null;
    }

    private String leggiTestoNodo(Node nodoPadre, String nomeNodo, String errore) throws SOAPFault {
        Node n = cercaNodo(nodoPadre, nomeNodo);
        if (n == null) {
            logger.error("La richiesta di informazioni sui pubblicatori deve contenere " + errore);
            throw new SOAPFault("La richiesta di informazioni sui pubblicatori deve contenere " + errore);
        }
        return n.getTextContent();
    }

    private void convalidaDati(IPubblicatore pubblicatore) throws SOAPFault {
        if (pubblicatore.getNome().equals("")) {
            logger.error("Il nome del pubblicatore non e' stato specificato");
            throw new SOAPFault("Il nome del pubblicatore non e' stato specificato");
        }

        if (pubblicatore.getTipo().equals("")) {
            logger.error("Il tipo del pubblicatore non e' stato specificato");
            throw new SOAPFault("Il tipo del pubblicatore non e' stato specificato");
        }

        return;
    }
}
