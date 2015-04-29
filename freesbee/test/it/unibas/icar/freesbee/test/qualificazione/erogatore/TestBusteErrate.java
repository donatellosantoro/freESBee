package it.unibas.icar.freesbee.test.qualificazione.erogatore;

import it.unibas.icar.freesbee.persistenza.hibernate.DAOUtilHibernate;
import it.unibas.icar.freesbee.test.qualificazione.UtilTest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import junit.framework.TestCase;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.service.jdbc.connections.internal.C3P0ConnectionProvider;
import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class TestBusteErrate extends TestCase {

//    protected Log logger = LogFactory.getLog(this.getClass());
    private static final Logger logger = LoggerFactory.getLogger(TestBusteErrate.class.getName());

    @Override
    public void tearDown() {
        DAOUtilHibernate.closeSessionFactory();
    }

    public void testBusta() {
        testBusta(02);
//        if (true) return;
//        for (int i = 2; i < 34; i++) {
//            System.out.println("Test " + i);
//            testBusta(i);
//        }
    }

    private void testBusta(int id) {
        String stringId = new DecimalFormat("00").format(id);
        String richiesta = UtilTest.leggiMessaggio("/messaggi/BusteErrate/" + stringId + "_REQ.xml", false);
        if (richiesta == null) {
            return;
        }
        logger.debug("RICHIESTA:\n" + richiesta);
        Document soapRichiesta = UtilTest.leggiSOAP(richiesta);
        String idMessaggioRichiesta = UtilTest.trovaNodoString("/soapenv:Envelope/soapenv:Header/eGov_IT:Intestazione/eGov_IT:IntestazioneMessaggio/eGov_IT:Messaggio/eGov_IT:Identificatore", soapRichiesta);
        if (idMessaggioRichiesta != null) {
            logger.info("Dobbiamo cancellare il messaggio " + idMessaggioRichiesta + " prima di eseguire il test");
            UtilTest.cancellaMessaggio(idMessaggioRichiesta);
        }

        String rispostaAttesa = UtilTest.leggiMessaggio("/messaggi/BusteErrate/" + stringId + "_RES.xml", false);
        logger.debug("RISPOSTA ATTESA:\n" + rispostaAttesa);
        Document soapRispostaAttesa = UtilTest.leggiSOAP(rispostaAttesa);

        String risposta = UtilTest.inviaPA(richiesta);
        logger.debug("RISPOSTA:\n" + risposta);
        Document soapRisposta = UtilTest.leggiSOAP(risposta);

        Node nodoEccAttese = UtilTest.trovaNodo("/soapenv:Envelope/soapenv:Header/eGov_IT:Intestazione/eGov_IT:ListaEccezioni", soapRispostaAttesa);
        Node nodoEccRicevute = UtilTest.trovaNodo("/soapenv:Envelope/soapenv:Header/eGov_IT:Intestazione/eGov_IT:ListaEccezioni", soapRisposta);

        List<String> eccezioniAttese = generaListaEccezioni(nodoEccAttese);
        List<String> eccezioniRicevute = generaListaEccezioni(nodoEccRicevute);

        for (String e : eccezioniAttese) {
            if (!eccezioniRicevute.contains(e)) {
                logger.warn("------------------");
                logger.warn("Eccezioni attese: ");
                for (String string : eccezioniAttese) {
                    logger.warn(string);
                }
                logger.warn("------------------");
                logger.warn("Eccezioni ricevute: ");
                for (String string : eccezioniRicevute) {
                    logger.warn(string);
                }
                Assert.fail("La risposta alla busta " + stringId + " dovrebbe contenere l'eccezione " + e);
            }
        }

        if (eccezioniAttese.size() != eccezioniRicevute.size()) {
            logger.warn("------------------");
            logger.warn("Eccezioni attese: ");
            for (String string : eccezioniAttese) {
                logger.warn(string);
            }
            logger.warn("------------------");
            logger.warn("Eccezioni ricevute: ");
            for (String string : eccezioniRicevute) {
                logger.warn(string);
            }
            Assert.fail("Controllare le eccezioni per la busta " + stringId);
        }


        String trasmissioneOrigine1Atteso = UtilTest.trovaNodoString("/soapenv:Envelope/soapenv:Header/eGov_IT:Intestazione/eGov_IT:ListaTrasmissioni/eGov_IT:Trasmissione/eGov_IT:Origine/eGov_IT:IdentificativoParte", soapRispostaAttesa);
        String trasmissioneOrigine1Ricevuto = UtilTest.trovaNodoString("/soapenv:Envelope/soapenv:Header/eGov_IT:Intestazione/eGov_IT:ListaTrasmissioni/eGov_IT:Trasmissione/eGov_IT:Origine/eGov_IT:IdentificativoParte", soapRisposta);
        Assert.assertEquals(trasmissioneOrigine1Atteso, trasmissioneOrigine1Ricevuto);

        String trasmissioneDestinazione1Atteso = UtilTest.trovaNodoString("/soapenv:Envelope/soapenv:Header/eGov_IT:Intestazione/eGov_IT:ListaTrasmissioni/eGov_IT:Trasmissione/eGov_IT:Destinazione/eGov_IT:IdentificativoParte", soapRispostaAttesa);
        String trasmissioneDestinazione1Ricevuto = UtilTest.trovaNodoString("/soapenv:Envelope/soapenv:Header/eGov_IT:Intestazione/eGov_IT:ListaTrasmissioni/eGov_IT:Trasmissione/eGov_IT:Destinazione/eGov_IT:IdentificativoParte", soapRisposta);
        Assert.assertEquals(trasmissioneDestinazione1Atteso, trasmissioneDestinazione1Ricevuto);

        String trasmissioneOraRegistrazione1Atteso = UtilTest.trovaNodoString("/soapenv:Envelope/soapenv:Header/eGov_IT:Intestazione/eGov_IT:ListaTrasmissioni/eGov_IT:Trasmissione/eGov_IT:OraRegistrazione", soapRispostaAttesa);
        String trasmissioneOraRegistrazione1Ricevuto = UtilTest.trovaNodoString("/soapenv:Envelope/soapenv:Header/eGov_IT:Intestazione/eGov_IT:ListaTrasmissioni/eGov_IT:Trasmissione/eGov_IT:OraRegistrazione", soapRisposta);
        Assert.assertEquals("Busta numero: " + stringId, trasmissioneOraRegistrazione1Atteso, trasmissioneOraRegistrazione1Ricevuto);

        String trasmissioneOrigine2Atteso = UtilTest.trovaNodoString("/soapenv:Envelope/soapenv:Header/eGov_IT:Intestazione/eGov_IT:ListaTrasmissioni/eGov_IT:Trasmissione[2]/eGov_IT:Origine/eGov_IT:IdentificativoParte", soapRispostaAttesa);
        String trasmissioneOrigine2Ricevuto = UtilTest.trovaNodoString("/soapenv:Envelope/soapenv:Header/eGov_IT:Intestazione/eGov_IT:ListaTrasmissioni/eGov_IT:Trasmissione[2]/eGov_IT:Origine/eGov_IT:IdentificativoParte", soapRisposta);
        Assert.assertEquals(trasmissioneOrigine2Atteso, trasmissioneOrigine2Ricevuto);

        String trasmissioneDestinazione2Atteso = UtilTest.trovaNodoString("/soapenv:Envelope/soapenv:Header/eGov_IT:Intestazione/eGov_IT:ListaTrasmissioni/eGov_IT:Trasmissione[2]/eGov_IT:Destinazione/eGov_IT:IdentificativoParte", soapRispostaAttesa);
        String trasmissioneDestinazione2Ricevuto = UtilTest.trovaNodoString("/soapenv:Envelope/soapenv:Header/eGov_IT:Intestazione/eGov_IT:ListaTrasmissioni/eGov_IT:Trasmissione[2]/eGov_IT:Destinazione/eGov_IT:IdentificativoParte", soapRisposta);
        Assert.assertEquals(trasmissioneDestinazione2Atteso, trasmissioneDestinazione2Ricevuto);

        String trasmissioneOrigine3Atteso = UtilTest.trovaNodoString("/soapenv:Envelope/soapenv:Header/eGov_IT:Intestazione/eGov_IT:ListaTrasmissioni/eGov_IT:Trasmissione[3]/eGov_IT:Origine/eGov_IT:IdentificativoParte", soapRispostaAttesa);
        String trasmissioneOrigine3Ricevuto = UtilTest.trovaNodoString("/soapenv:Envelope/soapenv:Header/eGov_IT:Intestazione/eGov_IT:ListaTrasmissioni/eGov_IT:Trasmissione[3]/eGov_IT:Origine/eGov_IT:IdentificativoParte", soapRisposta);
        Assert.assertEquals(trasmissioneOrigine3Atteso, trasmissioneOrigine3Ricevuto);

        String trasmissioneDestinazione3Atteso = UtilTest.trovaNodoString("/soapenv:Envelope/soapenv:Header/eGov_IT:Intestazione/eGov_IT:ListaTrasmissioni/eGov_IT:Trasmissione[3]/eGov_IT:Destinazione/eGov_IT:IdentificativoParte", soapRispostaAttesa);
        String trasmissioneDestinazione3Ricevuto = UtilTest.trovaNodoString("/soapenv:Envelope/soapenv:Header/eGov_IT:Intestazione/eGov_IT:ListaTrasmissioni/eGov_IT:Trasmissione[3]/eGov_IT:Destinazione/eGov_IT:IdentificativoParte", soapRisposta);
        Assert.assertEquals(trasmissioneDestinazione3Atteso, trasmissioneDestinazione3Ricevuto);
    }

    private List<String> generaListaEccezioni(Node nodoListaEccezioni) {
        List<String> l = new ArrayList<String>();
        if (nodoListaEccezioni == null) {
            return l;
        }
        for (int i = 0; i < nodoListaEccezioni.getChildNodes().getLength(); i++) {
            Node nodoEccezione = nodoListaEccezioni.getChildNodes().item(i);
            if (nodoEccezione.getNodeName().equals("eGov_IT:Eccezione")) {
                String codice = nodoEccezione.getAttributes().getNamedItem("codiceEccezione").getTextContent();
                String contestoCodifica = nodoEccezione.getAttributes().getNamedItem("contestoCodifica").getTextContent();
                String posizione = nodoEccezione.getAttributes().getNamedItem("posizione").getTextContent();
                String rilevanza = nodoEccezione.getAttributes().getNamedItem("rilevanza").getTextContent();
                l.add(codice + " " + contestoCodifica + " " + posizione + " " + rilevanza);
            }
        }

        return l;
    }
}
