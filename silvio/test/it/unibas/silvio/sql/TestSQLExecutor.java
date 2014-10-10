package it.unibas.silvio.sql;

import it.unibas.silvio.modello.Query;
import it.unibas.silvio.persistenza.SilvioException;
import it.unibas.silvio.transql.SQLExecutor;
import it.unibas.silvio.transql.SQLInsert;
import it.unibas.silvio.transql.SQLParser;
import it.unibas.silvio.transql.SQLParserException;
import java.util.List;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TestSQLExecutor extends TestCase {

    private Log logger = LogFactory.getLog(this.getClass());
    private String sql1 = "INSERT INTO paziente (nome) VALUES ('Winston')";
    private String sql2 = "INSERT INTO paziente(id, nome, cognome, medico) VALUES (${id.Utente}, 'Winston','Smith',${refId.Medico}) ${endStatement} INSERT INTO medico(id, nome, cognome) VALUES(${id.Medico},'xxx','xxx');";
    private String sql3 = "INSERT INTO utenti(id, nome, cognome, residenza, codiceasl, codicefiscale) VALUES (${id.Utente}, 'Winston','Smith','GF',${refId.Asl},'wsgf') ${endStatement} INSERT INTO asl(id, codice,nome) VALUES (${id.Asl},'10','ASL10') ${endStatement}";

    public void testCompletaQuery() {
        if(true){
            return;
        }
        /* PER ESEGUIRE QUESTO TEST BISOGNA AVER CREATO UNA BASE DI DATI CON POSTGRES*/
        Query query = new Query();
        query.setIndirizzoDB("localhost");
        query.setNomeDB("ap1");
        query.setNomeUtente("silvio");
        query.setPassword("**silvio**");
        query.setTipoDB("PostgreSQL");
        SQLExecutor sqlExec = new SQLExecutor(query);
        try {
            Assert.assertEquals("INSERT INTO paziente (nome) VALUES ('Winston')", sqlExec.completaInsert(SQLParser.parse(sql1).get(0)));

            List<SQLInsert> insert = SQLParser.parse(sql2);
            insert.get(1).setValoreId(101);
            insert.get(1).setEseguito(true);
            Assert.assertEquals("INSERT INTO paziente(id, nome, cognome, medico) VALUES (0, 'Winston','Smith',101)", sqlExec.completaInsert(insert.get(0)));

        } catch (SQLParserException ex) {
            Assert.fail(ex.toString());
        }
    }

    public void testEseguiQuery() {
        if(true){
            return;
        }
        /* PER ESEGUIRE QUESTO TEST BISOGNA AVER CREATO UNA BASE DI DATI CON POSTGRES*/
        Query query = new Query();
        query.setIndirizzoDB("localhost");
        query.setNomeDB("test");
        query.setNomeUtente("silvio");
        query.setPassword("**silvio**");
        query.setTipoDB("PostgreSQL");
        SQLExecutor sqlExec = new SQLExecutor(query);
        try {
            List<SQLInsert> listaInsert = SQLParser.parse(sql3);
            String risultato = sqlExec.esegui(listaInsert);
            logger.info("Risultato:\n" + risultato);
        } catch (SQLParserException ex) {
            Assert.fail(ex.toString());
        } catch (SilvioException ex) {
            Assert.fail(ex.toString());
        }
    }
}
