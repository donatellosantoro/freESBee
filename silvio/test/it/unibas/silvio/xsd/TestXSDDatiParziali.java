package it.unibas.silvio.xsd;

import it.unibas.silvio.modello.Dati;
import it.unibas.silvio.modello.DatiCostanti;
import it.unibas.silvio.modello.DatiInterattivi;
import it.unibas.silvio.modello.DatiSQL;
import it.unibas.silvio.modello.DatoPrimitivo;
import it.unibas.silvio.modello.Query;
import it.unibas.silvio.xml.XSDUtil;
import it.unibas.silvio.xml.XmlException;
import it.unibas.silvio.xml.XmlJDomUtil;
import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TestXSDDatiParziali extends TestCase {

    private Log logger = LogFactory.getLog(this.getClass());

    public void testGeneraXSD() {
        try {
            /* PER ESEGUIRE QUESTO TEST BISOGNA AVER CREATO UNA BASE DI DATI CON POSTGRES
            org.jdom.Document xsdDocument = XSDUtil.datiToXSD(generaDatiTest());
            logger.info(XmlUtil.stampaXML(xsdDocument));
            XmlUtil.salvaXML("c:\\tmp\\test.xsd", xsdDocument);*/
            
            org.jdom.Document xsdDocument2 = XSDUtil.datiToXSD(generaDatiTest2());
            logger.info(XmlJDomUtil.stampaXML(xsdDocument2));
//            XmlUtil.salvaXML("c:\\tmp\\test2.xsd", xsdDocument2);            
            
            /* PER ESEGUIRE QUESTO TEST BISOGNA AVER CREATO UNA BASE DI DATI CON MYSQL
            org.jdom.Document xsdDocumentMySql = XSDUtil.datiToXSD(generaDatiTestMySql());
            logger.info(XmlUtil.stampaXML(xsdDocumentMySql));
//            XmlUtil.salvaXML("c:\\tmp\\testMySQL.xsd", xsdDocumentMySql);*/
        } catch (XmlException ex) {
            logger.error("Impossibile generare l'XSD " + ex);
            Assert.fail("Impossibile generare l'XSD " + ex);
        }
    }

    private Dati generaDatiTest() {
        Dati dati = new Dati();
        DatiSQL datiSQL = new DatiSQL();
        DatiCostanti datiCostanti = new DatiCostanti();
        DatiInterattivi datiInterattivi = new DatiInterattivi();
        dati.setDatiCostanti(datiCostanti);
        dati.setDatiInterattivi(datiInterattivi);
        dati.setDatiSQL(datiSQL);

        Query querySelect = new Query();
        querySelect.setQuery("select * from utenti join asl on utenti.codiceASL = asl.id");
        querySelect.setIndirizzoDB("localhost");
        querySelect.setNomeDB("test");
        querySelect.setTipoDB("PostgreSQL");
        querySelect.setNomeUtente("pguser");
        querySelect.setPassword("pguser");
        datiSQL.setSelect(querySelect);
        
        List<DatoPrimitivo> listaDatiCostanti = new ArrayList<DatoPrimitivo>();
        listaDatiCostanti.add(new DatoPrimitivo("REGIONE","BASILICATA"));
        listaDatiCostanti.add(new DatoPrimitivo("IDOPERATORE","123"));
        datiCostanti.setDatiCostanti(listaDatiCostanti);
        
        List<DatoPrimitivo> listaDatiInterattivi = new ArrayList<DatoPrimitivo>();
        listaDatiInterattivi.add(new DatoPrimitivo("DATA"));
        listaDatiInterattivi.add(new DatoPrimitivo("REGIONEDESTINATARIO"));
        datiInterattivi.setDatiInterattivi(listaDatiInterattivi);
        return dati;
    }

    private Dati generaDatiTest2() {
        Dati dati = new Dati();
        DatiSQL datiSQL = new DatiSQL();
        dati.setDatiSQL(datiSQL);

        Query querySelect = new Query();
        querySelect.setQuery("select utenti.data,utenti.nome from utenti join asl on utenti.codiceASL = asl.id");        
        querySelect.setIndirizzoDB("localhost");
        querySelect.setNomeDB("testx");
        querySelect.setTipoDB("PostgreSQL");
        querySelect.setNomeUtente("pguser");
        querySelect.setPassword("pguser");
        datiSQL.setSelect(querySelect);
        return dati;
    }

    private Dati generaDatiTestMySql() {
        Dati dati = new Dati();
        DatiSQL datiSQL = new DatiSQL();
        dati.setDatiSQL(datiSQL);

        Query querySelect = new Query();
        querySelect.setQuery("select * from utenti join asl on utenti.codiceASL = asl.id");        
        querySelect.setIndirizzoDB("localhost");
        querySelect.setNomeDB("testsilvio");
        querySelect.setTipoDB("MySQL");
        querySelect.setNomeUtente("test");
        querySelect.setPassword("test");
        datiSQL.setSelect(querySelect);
        return dati;
    }
}
