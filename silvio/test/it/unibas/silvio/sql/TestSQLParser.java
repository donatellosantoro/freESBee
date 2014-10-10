package it.unibas.silvio.sql;

import it.unibas.silvio.transql.SQLInsert;
import it.unibas.silvio.transql.SQLParser;
import it.unibas.silvio.transql.SQLParserException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TestSQLParser extends TestCase {

    private Log logger = LogFactory.getLog(this.getClass());
    private String sql1 = "INSERT INTO utente (nome, cognome) VALUES ('Winston','Smith')";
    private String sql1b = "INSErT INtO utente VALUES ('Winston','Smith')";
    private String sql1c = "INSERT INTO utente (nome, cognome) VALUES ('Win,s(ton','Sm)i'',th')";
    private String sql1d = "INSERT INTO utente (nome) VALUES ('Winston')";
    private String sql2 = "INSERT INTO utente(nome, cognome) VALUES ('Winston','Smith') ${endStatement} INSERT INTO prova(nome, cognome) VALUES ('xxx','xxx')";
    private String sql3 = "INSERT INTO utente (id, nome, cognome) VALUES (${id.Utente}, 'Winston','Smith') ${endStatement}";
    private String sql4 = "INSERT INTO utente(id, nome, cognome, medico) VALUES (${id.Utente}, 'Winston','Smith',${refId.Medico}) ${endStatement} INSERT INTO medico(id, nome, cognome) VALUES(${id.Medico},'xxx','xxx');";
    private String sql5 = "INSERT INTO utente(id, nome, cognome, medico) VALUES (${id.Utente}, 'Winston','Smith',${refId.Medico}) ${endStatement} INSERT INTO asl(id, codice,nome) VALUES (${id.Asl},'1','ASL1') ${endStatement} INSERT INTO medico(id, nome, cognome,asl) VALUES(${id.Medico},'xxx','xxx',${refId.Asl}) ${endStatement}";
    private String sqlFail1 = "INSERT INTO utente(id, nome, cognome, medico) VALUES (,'prova')";
    private String sqlFail2 = "INSER INTO utente(nome, cognome) VALUES ('prova','prova')";
    private String sqlFail3 = "INSERT INTO utente(nome, cognome VALUES ('prova','prova')";
    private String sqlFail4 = "INSERT INTO utente(id, id2) VALUES (${id.Utente},${id.Utente2})";
    private String sqlFail5 = "INSERT INTO utente(id, id2) VALUES (${id.Utente},${refId.Utente2})";
    private String sqlFailCyclic6 = "INSERT INTO utente(id, nome, cognome, medico) VALUES (${id.Utente}, 'Winston','Smith',${refId.Medico}) ${endStatement} INSERT INTO medico(id, nome, cognome, paziente) VALUES(${id.Medico},'xxx','xxx',${refId.Utente});";
    private String sql6 = "INSERT INTO ricetta (diagnosi, data, posizione_utente_ticket, importo_ticket, importo_totale, codice_struttura_erogatrice, codice_prestazione, codifica_nomenclatore, quantita, importo, id_prestazione, paziente) VALUES('','','','','','','','','','','',${refId.paziente}) ${endStatement}INSERT INTO paziente (id,nome, cognome, codice_fiscale, data_nascita, sesso, comune_residenza, codice_ssr) VALUES( ${id.paziente},'','','','','','','') ${endStatement}";
    private String sql7 = "INSERT INTO paziente (id,nome,cognome) VALUES (${id.paziente},'mario','')";
    private String sql8 = "INSERT INTO titolare (idtitolare, datarilevazione, codistatregione, iva_cf, denominazione, via, codistat_pvcomune, cap) VALUES( ${id.titolare},'2008-1-1','ab22','abcdefghilmn1234','','via dei fiori 1','BASPZ',   85100) ${endStatement}  INSERT INTO titolare (idtitolare, datarilevazione, codistatregione, iva_cf, denominazione, via, codistat_pvcomune, cap) VALUES( ${id.titolare},'2008-1-1','ab22','abcdefghilmn1234','','via dei fiori 1','BASPZ',   85100) ${endStatement}";
    private String sqlUpdate = "UPDATE paziente SET comune_residenza ='FrancavillaSulSinni' WHERE codice_fiscale ='bnchnrc'";

    public void testParsing() {
        try {
            Assert.assertEquals(1, SQLParser.parse(sql1).size());
            Assert.assertEquals(2, SQLParser.parse(sql2).size());
            Assert.assertEquals(1, SQLParser.parse(sql3).size());
            Assert.assertEquals(2, SQLParser.parse(sql4).size());
            Assert.assertEquals(3, SQLParser.parse(sql5).size());
        } catch (SQLParserException e) {
            Assert.fail(e.toString());
        }
    }

    public void testParsingQuery1() {
        try {
            List<SQLInsert> operations = SQLParser.parse(sql1);
            Assert.assertEquals(1, operations.size());
            SQLInsert op = operations.get(0);

            logger.info("SQLOperation " + op);

            Assert.assertEquals("utente", op.getNomeTabella());
            Map<String, String> parametri = op.getMappaParametri();
            Assert.assertEquals(2, parametri.size());
            Assert.assertEquals("'Winston'", parametri.get("nome"));
            Assert.assertEquals("'Smith'", parametri.get("cognome"));
        } catch (SQLParserException e) {
            Assert.fail(e.toString());
        }
    }

    public void testParsingQuery3() {
        try {
            List<SQLInsert> operations = SQLParser.parse(sql3);
            Assert.assertEquals(1, operations.size());
            SQLInsert op = operations.get(0);
            Assert.assertEquals("utente", op.getNomeTabella());
            Map<String, String> parametri = op.getMappaParametri();
            Assert.assertEquals(2, parametri.size());
            Assert.assertEquals("'Winston'", parametri.get("nome"));
            Assert.assertEquals("'Smith'", parametri.get("cognome"));
            Assert.assertEquals(0, op.getMappaRiferimenti().size());
            Assert.assertEquals("id", op.getNomeColonnaID());
            Assert.assertEquals("Utente", op.getIdPlaceHolder());
            Assert.assertEquals(0, op.getValoreId());
        } catch (SQLParserException e) {
            Assert.fail(e.toString());
        }
    }

    public void testParsingQuery1b() {
        try {
            List<SQLInsert> operations = SQLParser.parse(sql1b);
            Assert.assertEquals(1, operations.size());
            SQLInsert op = operations.get(0);
            Assert.assertEquals("utente", op.getNomeTabella());
            Map<String, String> parametri = op.getMappaParametri();
            Assert.assertEquals(0, op.getMappaParametri().size());
        } catch (SQLParserException e) {
            Assert.fail(e.toString());
        }
    }

    public void testParsingQuery1d() {
        try {
            List<SQLInsert> operations = SQLParser.parse(sql1d);
            Assert.assertEquals(1, operations.size());
            SQLInsert op = operations.get(0);
            Assert.assertEquals("utente", op.getNomeTabella());
            Map<String, String> parametri = op.getMappaParametri();
            Assert.assertEquals(1, op.getMappaParametri().size());
            Assert.assertEquals("'Winston'", parametri.get("nome"));
        } catch (SQLParserException e) {
            Assert.fail(e.toString());
        }
    }

    public void testParsingQuery1c() {
        try {
            List<SQLInsert> operations = SQLParser.parse(sql1c);
            Assert.assertEquals(1, operations.size());
            SQLInsert op = operations.get(0);
            Assert.assertEquals("utente", op.getNomeTabella());
            Map<String, String> parametri = op.getMappaParametri();
            Assert.assertEquals(2, parametri.size());
            Assert.assertEquals("'Win,s(ton'", parametri.get("nome"));
            Assert.assertEquals("'Sm)i'',th'", parametri.get("cognome"));
        } catch (SQLParserException e) {
            Assert.fail(e.toString());
        }
    }

    public void testParsingQuery2() {
        try {
            List<SQLInsert> operations = SQLParser.parse(sql2);
            Assert.assertEquals(2, operations.size());
            Assert.assertEquals("utente", operations.get(0).getNomeTabella());
            Assert.assertEquals("prova", operations.get(1).getNomeTabella());
            Assert.assertEquals("'xxx'", operations.get(1).getMappaParametri().get("nome"));
        } catch (SQLParserException e) {
            Assert.fail(e.toString());
        }
    }

    public void testParsingQuery4() {
        try {
            List<SQLInsert> operations = SQLParser.parse(sql4);
            Assert.assertEquals(2, operations.size());
            Assert.assertEquals("utente", operations.get(0).getNomeTabella());
            Assert.assertEquals("Utente", operations.get(0).getIdPlaceHolder());
            Assert.assertEquals("id", operations.get(0).getNomeColonnaID());
            Map<String, SQLInsert> riferimenti = operations.get(0).getMappaRiferimenti();
            Assert.assertEquals(1, riferimenti.size());
            Assert.assertTrue(riferimenti.containsKey("Medico"));
            Assert.assertEquals("medico", riferimenti.get("Medico").getNomeTabella());

            Assert.assertEquals("medico", operations.get(1).getNomeTabella());
            Assert.assertEquals("Medico", operations.get(1).getIdPlaceHolder());
            Assert.assertEquals(0, operations.get(1).getMappaRiferimenti().size());
        } catch (SQLParserException e) {
            Assert.fail(e.toString());
        }
    }

    public void testParsingQuery5() {
        try {
            List<SQLInsert> operations = SQLParser.parse(sql5);
            Assert.assertEquals(3, operations.size());
            Assert.assertEquals("utente", operations.get(0).getNomeTabella());
            Assert.assertEquals("Utente", operations.get(0).getIdPlaceHolder());
            Assert.assertEquals("id", operations.get(0).getNomeColonnaID());
            Map<String, SQLInsert> riferimenti = operations.get(0).getMappaRiferimenti();
            Assert.assertEquals(1, riferimenti.size());
            Assert.assertTrue(riferimenti.containsKey("Medico"));
            Assert.assertEquals("medico", riferimenti.get("Medico").getNomeTabella());

            Assert.assertEquals("asl", operations.get(1).getNomeTabella());
            Assert.assertEquals("Asl", operations.get(1).getIdPlaceHolder());
            Assert.assertEquals(0, operations.get(1).getMappaRiferimenti().size());

            Assert.assertEquals("medico", operations.get(2).getNomeTabella());
            Assert.assertEquals("Medico", operations.get(2).getIdPlaceHolder());
            Assert.assertEquals(1, operations.get(2).getMappaRiferimenti().size());
            Assert.assertTrue(operations.get(2).getMappaRiferimenti().containsKey("Asl"));
        } catch (SQLParserException e) {
            Assert.fail(e.toString());
        }
    }

    public void testParsingQuery6() {
        try {
            List<SQLInsert> operations = SQLParser.parse(sql6);
            Assert.assertEquals(2, operations.size());
        } catch (SQLParserException e) {
            Assert.fail(e.toString());
        }
    }

    public void testParsingQuery7() {
        try {
            List<SQLInsert> operations = SQLParser.parse(sql7);
            Assert.assertEquals(1, operations.size());
            String select = operations.get(0).generaSelectId();
            Assert.assertEquals("SELECT id FROM paziente WHERE nome = 'mario'", select);
        } catch (SQLParserException e) {
            Assert.fail(e.toString());
        }
    }

    public void testOrdinaQuery5() {
        try {
            List<SQLInsert> operations = SQLParser.parse(sql5);
            Collections.sort(operations);
            Assert.assertEquals(3, operations.size());
            Assert.assertEquals("asl", operations.get(0).getNomeTabella());
        } catch (SQLParserException e) {
            Assert.fail(e.toString());
        }
    }

    public void testQuery8() {
        try {
            List<SQLInsert> operations = SQLParser.parse(sql8);
            Collections.sort(operations);
        } catch (SQLParserException e) {
            Assert.fail(e.toString());
        }
    }

    public void testGeneraSelectId() {
        try {
            List<SQLInsert> operations = SQLParser.parse(sql3);
            SQLInsert sql = operations.get(0);
            Assert.assertEquals("SELECT id FROM utente WHERE nome = 'Winston' AND cognome = 'Smith'", sql.generaSelectId());
        } catch (SQLParserException e) {
            Assert.fail(e.toString());
        }
    }

    public void testParsingQueryUpdate() {
        try {
            List<SQLInsert> operations = SQLParser.parse(sqlUpdate);
            Assert.assertEquals(1, operations.size());
            SQLInsert op = operations.get(0);

            logger.info("SQLOperation " + op);
        } catch (SQLParserException e) {
            Assert.fail(e.toString());
        }
    }

    public void testSQLErrate() {
        try {
            SQLParser.parse(sqlFail2);
            Assert.fail("Il parser non ha riconosciuto una query errata");
        } catch (SQLParserException e) {
        }
        try {
            SQLParser.parse(sqlFail3);
            Assert.fail("Il parser non ha riconosciuto una query errata");
        } catch (SQLParserException e) {
        }
        try {
            SQLParser.parse(sqlFail1);
            Assert.fail("Il parser non ha riconosciuto una query errata");
        } catch (SQLParserException e) {
        }
        try {
            SQLParser.parse(sqlFail4);
            Assert.fail("Il parser non ha riconosciuto una query errata");
        } catch (SQLParserException e) {
        }
        try {
            SQLParser.parse(sqlFail5);
            Assert.fail("Il parser non ha riconosciuto una query errata");
        } catch (SQLParserException e) {
        }
        try {
            SQLParser.parse(sqlFailCyclic6);
            Assert.fail("Il parser non ha riconosciuto una query errata");
        } catch (SQLParserException e) {
        }
    }

    public void testFuoriStringa() {
        try {
            Assert.assertTrue(SQLParser.fuoriStringa("hello"));
            Assert.assertTrue(SQLParser.fuoriStringa("''"));
            Assert.assertTrue(SQLParser.fuoriStringa("'w'"));
            Assert.assertTrue(SQLParser.fuoriStringa("'w'xxx"));
            Assert.assertFalse(SQLParser.fuoriStringa("'w''xxx"));
            Assert.assertFalse(SQLParser.fuoriStringa("'"));
            Assert.assertFalse(SQLParser.fuoriStringa("'xxxx'xxx'"));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }
}
