package it.unibas.silvio.sql;

import it.unibas.silvio.transql.SQLAttachmentParser;
import it.unibas.silvio.transql.SQLParserException;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TestSQLAttachment extends TestCase {

    private Log logger = LogFactory.getLog(this.getClass());
    private String sql00 = "SELECT * FROM doc WHERE doc.codice = 'DOC01'";
    private String sql01 = "SELECT * FROM doc WHERE doc.codice = 'DOC01' UNION select * FROM prova";
    private String sql1 = "SELECT * FROM doc WHERE doc.codice = 'DOC01' UNION SELECT.ATTACHMENT percorso FROM doc WHERE doc.codice = 'DOC01'";
    private String sql2 = "SELECT * FROM doc WHERE doc.codice = 'DOC01' UNION select * FROM prova UNION SELECT.ATTACHMENT percorso FROM doc WHERE doc.codice = 'DOC01'";
    private String sql3 = "SELECT * FROM doc WHERE doc.codice = 'DOC01' UNION select * FROM prova UNION SELECT.ATTACHMENT percorso FROM doc WHERE doc.codice = 'DOC01' UNION SELECT.ATTACHMENT percorso FROM doc WHERE doc.codice = 'DOC02'";
    private String sql4 = "SELECT * FROM doc WHERE doc.codice = 'DOC01' UNION select * FROM prova UNION SELECT.ATTACHMENT percorso FROM doc WHERE doc.codice = 'DOC01' UNION select * FROM prova2";
    private String sql5 = "SELECT.ATTACHMENT percorso FROM doc WHERE doc.codice = 'DOC01'";
    private String sql6 = "SELECT.ATTACHMENT percorso FROM doc WHERE doc.codice = 'DOC01' UNION SELECT.ATTACHMENT percorso FROM doc WHERE doc.codice = 'DOC02'";
    private String sql7 = "SELECT.ATTACHMENT percorso FROM doc WHERE doc.codice = 'DOC01' UNION SELECT.ATTACHMENT percorso, nome FROM doc WHERE doc.codice = 'DOC02'";
    private String sql8 = "SELECT.ATTACHMENT * FROM doc WHERE doc.codice = 'DOC02'";

    public void testSQLAttachment0() {
        try {
            SQLAttachmentParser selectAttachment = new SQLAttachmentParser();
            String select = selectAttachment.parse(sql00);
            Assert.assertEquals(sql00, select);
            Assert.assertTrue(selectAttachment.getSelectAttachment().isEmpty());

            selectAttachment = new SQLAttachmentParser();
            select = selectAttachment.parse(sql01);
            Assert.assertEquals(sql01, select);
            Assert.assertTrue(selectAttachment.getSelectAttachment().isEmpty());
        } catch (SQLParserException ex) {
            Assert.fail(ex.toString());
        }
    }

    public void testSQLAttachment1() {
        try {
            SQLAttachmentParser selectAttachment = new SQLAttachmentParser();
            String select = selectAttachment.parse(sql1);
            Assert.assertEquals("SELECT * FROM doc WHERE doc.codice = 'DOC01'", select);
            Assert.assertTrue(selectAttachment.getSelectAttachment().size() == 1);
            Assert.assertEquals("SELECT percorso FROM doc WHERE doc.codice = 'DOC01'", selectAttachment.getSelectAttachment().get(0));
        } catch (SQLParserException ex) {
            Assert.fail(ex.toString());
        }
    }

    public void testSQLAttachment2() {
        try {
            SQLAttachmentParser selectAttachment = new SQLAttachmentParser();
            String select = selectAttachment.parse(sql2);
            logger.warn(select);
            Assert.assertEquals("SELECT * FROM doc WHERE doc.codice = 'DOC01' UNION select * FROM prova", select);
            Assert.assertTrue(selectAttachment.getSelectAttachment().size() == 1);
            Assert.assertEquals("SELECT percorso FROM doc WHERE doc.codice = 'DOC01'", selectAttachment.getSelectAttachment().get(0));
        } catch (SQLParserException ex) {
            Assert.fail(ex.toString());
        }
    }

    public void testSQLAttachment3() {
        try {
            SQLAttachmentParser selectAttachment = new SQLAttachmentParser();
            String select = selectAttachment.parse(sql3);
            Assert.assertEquals("SELECT * FROM doc WHERE doc.codice = 'DOC01' UNION select * FROM prova", select);
            Assert.assertTrue(selectAttachment.getSelectAttachment().size() == 2);
            Assert.assertEquals("SELECT percorso FROM doc WHERE doc.codice = 'DOC01'", selectAttachment.getSelectAttachment().get(0));
            Assert.assertEquals("SELECT percorso FROM doc WHERE doc.codice = 'DOC02'", selectAttachment.getSelectAttachment().get(1));
        } catch (SQLParserException ex) {
            Assert.fail(ex.toString());
        }
    }

    public void testSQLAttachment4() {
        try {
            SQLAttachmentParser selectAttachment = new SQLAttachmentParser();
            String select = selectAttachment.parse(sql4);
            Assert.assertEquals("SELECT * FROM doc WHERE doc.codice = 'DOC01' UNION select * FROM prova UNION select * FROM prova2", select);
            Assert.assertTrue(selectAttachment.getSelectAttachment().size() == 1);
            Assert.assertEquals("SELECT percorso FROM doc WHERE doc.codice = 'DOC01'", selectAttachment.getSelectAttachment().get(0));
        } catch (SQLParserException ex) {
            Assert.fail(ex.toString());
        }
    }

    public void testSQLAttachment5() {
        try {
            SQLAttachmentParser selectAttachment = new SQLAttachmentParser();
            String select = selectAttachment.parse(sql5);
            Assert.assertNull(select);
            Assert.assertTrue(selectAttachment.getSelectAttachment().size() == 1);
            Assert.assertEquals("SELECT percorso FROM doc WHERE doc.codice = 'DOC01'", selectAttachment.getSelectAttachment().get(0));
        } catch (SQLParserException ex) {
            Assert.fail(ex.toString());
        }
    }

    public void testSQLAttachment6() {
        try {
            SQLAttachmentParser selectAttachment = new SQLAttachmentParser();
            String select = selectAttachment.parse(sql6);
            Assert.assertNull(select);
            Assert.assertTrue(selectAttachment.getSelectAttachment().size() == 2);
            Assert.assertEquals("SELECT percorso FROM doc WHERE doc.codice = 'DOC01'", selectAttachment.getSelectAttachment().get(0));
            Assert.assertEquals("SELECT percorso FROM doc WHERE doc.codice = 'DOC02'", selectAttachment.getSelectAttachment().get(1));
        } catch (SQLParserException ex) {
            Assert.fail(ex.toString());
        }
    }

    public void testSQLAttachment7() {
        try {
            SQLAttachmentParser selectAttachment = new SQLAttachmentParser();
            selectAttachment.parse(sql7);
            Assert.fail("Non e' stata segnalata una parser exception");
        } catch (SQLParserException ex) {
        }
    }

    public void testSQLAttachment8() {
        try {
            SQLAttachmentParser selectAttachment = new SQLAttachmentParser();
            selectAttachment.parse(sql8);
            Assert.fail("Non e' stata segnalata una parser exception");
        } catch (SQLParserException ex) {
        }
    }
}
