package test.it.unibas.freesbee.ge.dao;

import it.unibas.freesbee.ge.modello.CategoriaEventiEsterna;
import it.unibas.freesbee.ge.modello.CategoriaEventiInterna;
import it.unibas.freesbee.ge.modello.GestoreEventi;
import it.unibas.freesbee.ge.modello.PubblicatoreEsterno;
import it.unibas.freesbee.ge.modello.PubblicatoreInterno;
import it.unibas.freesbee.ge.modello.Sottoscrittore;
import it.unibas.freesbee.ge.persistenza.DAOException;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiEsterna;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiInterna;
import it.unibas.freesbee.ge.persistenza.IDAOGestoreEventi;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreEsterno;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreInterno;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrittore;
import it.unibas.freesbee.ge.persistenza.facade.DAOPubblicatoreEsternoFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOPubblicatoreInternoFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOSottoscrizioneEsternaFacade;
import it.unibas.freesbee.ge.persistenza.facade.DAOSottoscrizioneInternaFacade;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCategoriaEventiEsternaHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCategoriaEventiInternaHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOGestoreEventiHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOPubblicatoreEsternoHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOPubblicatoreInternoHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOSottoscrittoreHibernate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DAOMock {

    private DAOMock() {
    }
    private static IDAOCategoriaEventiInterna daoCategoiraEventiInterna = new DAOCategoriaEventiInternaHibernate();
    private static IDAOPubblicatoreInterno daoPubblicatoreInterno = new DAOPubblicatoreInternoHibernate();
    private static IDAOSottoscrittore daoSottoscrittore = new DAOSottoscrittoreHibernate();
    private static IDAOCategoriaEventiEsterna daoCategoiraEventiEsterna = new DAOCategoriaEventiEsternaHibernate();
    private static IDAOPubblicatoreEsterno daoPubblicatoreEsterno = new DAOPubblicatoreEsternoHibernate();
    private static IDAOGestoreEventi daoGestoreEventi = new DAOGestoreEventiHibernate();
    private static Log logger = LogFactory.getLog(DAOMock.class);
    //File test
    public static final String COMUNICAZIONE_ESTERNA = "c://pubblicaEsternamente.xml";
    public static final String COMUNICAZIONE_ESTERNA_1 = "c://pubblicaEsternamente1.xml";
//    public static final String COMUNICAZIONE_INTERNA = "c://pubblicaInternamente.xml";
//    public static final String COMUNICAZIONE_INTERNA_1 = "c://pubblicaInternamente1.xml";
    public static final String CONSEGNA_1 = "c://consegna1.xml";
    public static final String CONSEGNA = "c://consegna.xml";
    public static final String NOTIFICA = "c://notifica.xml";
    //Pubblicatori interni
    public static final String NOME_SOGGETTO_A = "A";
    public static final String NOME_SOGGETTO_B = "B";
    public static final String NOME_SOGGETTO_C = "C";
    public static final String NOME_SOGGETTO_D = "D";
    public static final String NOME_SOGGETTO_E = "E";
    public static final String NOME_SOGGETTO_F = "F";
    //Sottoscrittori
    public static final String NOME_SOGGETTO_G = "G";
    public static final String NOME_SOGGETTO_H = "H";
    public static final String NOME_SOGGETTO_I = "I";
    public static final String NOME_SOGGETTO_L = "L";
    public static final String NOME_SOGGETTO_M = "M";
    public static final String NOME_SOGGETTO_N = "N";
    //Pubblicatori esterni
    public static final String NOME_SOGGETTO_O = "O";
    public static final String NOME_SOGGETTO_P = "P";
    public static final String NOME_SOGGETTO_Q = "Q";
    public static final String NOME_SOGGETTO_R = "R";
    public static final String NOME_SOGGETTO_X = "X";
    public static final String NOME_SOGGETTO_Y = "Y";
    public static final String NOME_SOGGETTO_K = "K";
    public static final String NOME_SOGGETTO_J = "J";
    //Sottoscrittori
    public static final String NOME_SOGGETTO_S = "S";
    public static final String NOME_SOGGETTO_T = "T";
    public static final String NOME_SOGGETTO_U = "U";
    public static final String NOME_SOGGETTO_V = "V";
    public static final String NOME_SOGGETTO_Z = "Z";
    public static final String NOME_SOGGETTO_W = "W";
    //GE
    public static final String NOME_SOGGETTO_GE = "GE";
    public static final String NOME_SOGGETTO_NON_GE = "NON_GE";
    //Tipo
    public static final String TIPO_SOGGETTO_SPC = "SPC";
    //SOTTOSCRITTORI GEControlProtocol
    public static final String NOME_SOTTOSCRITTORE_GE_CONSEGNA = "SOTTOSCRITTORE_C";
    public static final String NOME_SOTTOSCRITTORE_GE_NOTIFICA = "SOTTOSCRITTORE_N";
    //Categorie
    public static final String CATEGORIA_EVENTI_INTERNA_ATTIVA_0 = "Test0";
    public static final String CATEGORIA_EVENTI_INTERNA_NON_ATTIVA_1 = "Test1";
    public static final String CATEGORIA_EVENTI_INTERNA_ATTIVA_2 = "Test2";
    public static final String CATEGORIA_EVENTI_INTERNA_ATTIVA_3 = "Test3";
    public static final String CATEGORIA_EVENTI_ESTERNA_NON_CONFERMATA_NON_ATTIVA_4 = "Test4";
    public static final String CATEGORIA_EVENTI_ESTERNA_CONFERMATA_NON_ATTIVA_5 = "Test5";
    public static final String CATEGORIA_EVENTI_ESTERNA_NON_CONFERMATA_ATTIVA_6 = "Test6";
    public static final String CATEGORIA_EVENTI_ESTERNA_NON_CONFERMATA_ATTIVA_8 = "Test8";
    public static final String CATEGORIA_EVENTI_ESTERNA_NON_CONFERMATA_ATTIVA_11 = "Test11";
    public static final String CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_7 = "Test7";
    public static final String CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_9 = "Test9";
    public static final String CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_10 = "Test10";
    public static final String CATEGORIA_EVENTI_NON_ESISTENTE_27 = "Test27";
    //Categorie da confermare
    public static final String CATEGORIA_EVENTI_ESTERNA_NON_CONFERMATA_ATTIVA_30 = "Test30";
    public static final String CATEGORIA_EVENTI_ESTERNA_NON_CONFERMATA_ATTIVA_31 = "Test31";
    public static final String CATEGORIA_EVENTI_ESTERNA_NON_CONFERMATA_ATTIVA_33 = "Test33";
    public static final String CATEGORIA_EVENTI_ESTERNA_NON_CONFERMATA_ATTIVA_34 = "Test34";
    public static final String CATEGORIA_EVENTI_ESTERNA_NON_CONFERMATA_ATTIVA_35 = "Test35";
    public static final String CATEGORIA_EVENTI_ESTERNA_CONFERMATA_ATTIVA_32 = "Test32";
    //Tipo sottoscrizione
    public static final String TIPO_SOTTOSCRIZIONE_ERRATO = "NOTXX";
    public static final String TIPO_SOTTOSCRIZIONE_NOTIFICA_PICCOLO = "notifica";
    //Filtro contenuto
    public static final String ESPRESSIONE_REGOLARE_ERRATA = "[1]*[2";

    //CATEGORIA INTERNA
    public static void ripulisciCategoriaEventiInterna(String nomeCategoiraEventi) {
        try {
            CategoriaEventiInterna categoriaEventi = daoCategoiraEventiInterna.findByNome(nomeCategoiraEventi);
            if (categoriaEventi != null) {
                daoCategoiraEventiInterna.makeTransient(categoriaEventi);
            }
        } catch (Exception daoe) {
            logger.error("Errore nella ripulitura della categoria eventi " + nomeCategoiraEventi);
        }
    }

    public static void inserisciCategoriaEventiIntenra(String nomeCategoiraEventi, boolean attiva) {
        try {
            CategoriaEventiInterna categoriaEventi = new CategoriaEventiInterna();
            categoriaEventi.setNome(nomeCategoiraEventi);
            categoriaEventi.setAttiva(attiva);
            daoCategoiraEventiInterna.makePersistent(categoriaEventi);
        } catch (Exception daoe) {
            logger.error("Errore nell'inserimetno della categoria eventi " + nomeCategoiraEventi);
        }
    }

    //PUBBLICATORE INTERNO
    public static void ripulisciPubblicatoreInterno(String tipoPubblicatore, String nomePubblicatore, String nomeCategoira) {
        try {
            CategoriaEventiInterna categoriaEventi = daoCategoiraEventiInterna.findByNome(nomeCategoira);
            PubblicatoreInterno pubblicatoreInterno = daoPubblicatoreInterno.findByTipoNome(tipoPubblicatore, nomePubblicatore);
            if (categoriaEventi != null && pubblicatoreInterno != null) {
                DAOPubblicatoreInternoFacade.eliminaPubblicatoreInterno(categoriaEventi, pubblicatoreInterno);
            }
        } catch (DAOException daoe) {
            logger.error("Errore nella ripulitura del pubblicatore " + daoe.getMessage());
        }
    }

    public static void inserisciPubblicatoreInterno(String tipoPubblicatore, String nomePubblicatore, String nomeCategoira) {
        try {
            CategoriaEventiInterna categoriaEventi = daoCategoiraEventiInterna.findByNome(nomeCategoira);
            PubblicatoreInterno pubblicatoreInterno = daoPubblicatoreInterno.findByTipoNome(tipoPubblicatore, nomePubblicatore);
            if (categoriaEventi != null) {
                if (pubblicatoreInterno != null) {
                    DAOPubblicatoreInternoFacade.aggiungiPubblicatoreInterno(categoriaEventi, pubblicatoreInterno);
                } else {
                    PubblicatoreInterno pubblicatore = new PubblicatoreInterno(tipoPubblicatore, nomePubblicatore);
                    DAOPubblicatoreInternoFacade.aggiungiPubblicatoreInterno(categoriaEventi, pubblicatore);

                }
            }
        } catch (DAOException daoe) {
            logger.error("Errore nell'inserimento del pubblicatore " + daoe.getMessage());
        }
    }

    //SOTTOSCRITTORE INTERNO
    public static void ripulisciSottoscrittoreSottoscrizioneIntera(String tipoSottoscrittore, String nomeSottoscrittore, String nomeCategoira) {
        try {
            CategoriaEventiInterna categoriaEventi = daoCategoiraEventiInterna.findByNome(nomeCategoira);
            Sottoscrittore sottoscrittore = daoSottoscrittore.findByTipoNome(tipoSottoscrittore, nomeSottoscrittore);
            if (categoriaEventi != null && sottoscrittore != null) {
                DAOSottoscrizioneInternaFacade.eliminaSottoscrizioneInterna(categoriaEventi, sottoscrittore);
            }
        } catch (DAOException daoe) {
            logger.error("Errore nella ripulitura del sottoscrittore  " + daoe.getMessage());
        }
    }


    //CATEGORIA ESTERNA
    public static void ripulisciCategoriaEventiEsterna(String nomeCategoiraEventi) {
        try {
            CategoriaEventiEsterna categoriaEventi = daoCategoiraEventiEsterna.findByNome(nomeCategoiraEventi);
            if (categoriaEventi != null) {
                daoCategoiraEventiEsterna.makeTransient(categoriaEventi);
            }
        } catch (Exception daoe) {
            logger.error("Errore nella ripulitura della categoria eventi " + nomeCategoiraEventi);
        }
    }

    public static void inserisciCategoriaEventiEsterna(String nomeCategoiraEventi, boolean inAttesa, boolean attiva) {
        try {
            CategoriaEventiEsterna categoriaEventi = new CategoriaEventiEsterna();
            categoriaEventi.setNome(nomeCategoiraEventi);
            categoriaEventi.setInAttesa(inAttesa);
            categoriaEventi.setAttiva(attiva);
            daoCategoiraEventiEsterna.makePersistent(categoriaEventi);
        } catch (Exception daoe) {
            logger.error("Errore nell'inserimetno della categoria eventi " + nomeCategoiraEventi);
        }
    }

    //SOTTOSCRITTORE ESTERNO
    public static void ripulisciSottoscrittoreSottoscrizioneEsterna(String tipoSottoscrittore, String nomeSottoscrittore, String nomeCategoira) {
        try {
            CategoriaEventiEsterna categoriaEventi = daoCategoiraEventiEsterna.findByNome(nomeCategoira);
            Sottoscrittore sottoscrittore = daoSottoscrittore.findByTipoNome(tipoSottoscrittore, nomeSottoscrittore);
            if (categoriaEventi != null && sottoscrittore != null) {
                DAOSottoscrizioneEsternaFacade.eliminaSottoscrizioneEsterna(categoriaEventi, sottoscrittore);
            }
        } catch (DAOException daoe) {
            logger.error("Errore nella ripulitura del sottoscrittore  " + daoe.getMessage());
        }
    }

    //PUBBLICATORE ESTERNO
    public static void ripulisciPubblicatoreEsterno(String tipoPubblicatore, String nomePubblicatore, String nomeCategoira) {
        try {
            CategoriaEventiEsterna categoriaEventi = daoCategoiraEventiEsterna.findByNome(nomeCategoira);
            PubblicatoreEsterno pubblicatoreEsterno = daoPubblicatoreEsterno.findByTipoNome(tipoPubblicatore, nomePubblicatore);
            if (categoriaEventi != null && pubblicatoreEsterno != null) {
                DAOPubblicatoreEsternoFacade.eliminaPubblicatoreEsterno(categoriaEventi, pubblicatoreEsterno);
            }
        } catch (DAOException daoe) {
            logger.error("Errore nella ripulitura del pubblicatore " + daoe.getMessage());
        }
    }

    public static void inserisciPubblicatoreEsterno(String tipoPubblicatore, String nomePubblicatore, String nomeCategoira) {
        try {
            CategoriaEventiEsterna categoriaEventi = daoCategoiraEventiEsterna.findByNome(nomeCategoira);
            PubblicatoreEsterno pubblicatoreEsterno = new PubblicatoreEsterno(tipoPubblicatore, nomePubblicatore);
            if (categoriaEventi != null && pubblicatoreEsterno != null) {
                DAOPubblicatoreEsternoFacade.aggiungiPubblicatoreEsterno(categoriaEventi, pubblicatoreEsterno);
            }
        } catch (DAOException daoe) {
            logger.error("Errore nell'inserimento del pubblicatore " + daoe.getMessage());
        }
    }

//    public static void ripulisciPubblicatoreEsternoNonConfermato(String tipoPubblicatore, String nomePubblicatore) {
//        try {
//
//            PubblicatoreEsterno pubblicatoreEsterno = daoPubblicatoreEsterno.findByTipoNome(tipoPubblicatore, nomePubblicatore);
//            if (pubblicatoreEsterno != null) {
//                daoPubblicatoreEsterno.makeTransient(pubblicatoreEsterno);
//            }
//        } catch (DAOException daoe) {
//            logger.error("Errore nella ripulitura del pubblicatore " + daoe.getMessage());
//        }
//    }

    //GE
    public static void inserisciGestoreEventi(String tipo, String nome) {
        try {
            GestoreEventi ge = new GestoreEventi(tipo, nome);
            daoGestoreEventi.makePersistent(ge);
        } catch (DAOException daoe) {
            logger.error("Errore nell'inserimento del gestore eventi " + daoe.getMessage());
        }
    }

    public static void ripulisciGestoreEventi(String tipo, String nome) {
        try {
            GestoreEventi ge = daoGestoreEventi.findByTipoNome(tipo, nome);
            if (ge != null) {
                daoGestoreEventi.makeTransient(ge);
            }
        } catch (DAOException daoe) {
            logger.error("Errore nella ripulitura del gestore eventi " + daoe.getMessage());
        }
    }

    public static String creaMessaggioPubblicaInterno(String messaggio, String categoria, String tipoGE, String nomeGE) throws DAOException {
        String messaggioPubblica = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:ge=\"http://ge.freesbee.unibas.it/\"><soap:Header/><soap:Body>";
        messaggioPubblica += "<ge:pubblicaMessaggio><messaggioSoap>" + messaggio + "</messaggioSoap>";
        messaggioPubblica += "<nomePubblicatore>" + nomeGE + "</nomePubblicatore>";
        messaggioPubblica += "<tipoPubblicatore>" + tipoGE + "</tipoPubblicatore>";
        messaggioPubblica += "<categoriaEventi>" + categoria + "</categoriaEventi>";
        messaggioPubblica += "</ge:pubblicaMessaggio> </soap:Body></soap:Envelope>";
        return messaggioPubblica.trim();
    }
}
