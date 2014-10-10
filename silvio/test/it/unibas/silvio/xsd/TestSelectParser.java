package it.unibas.silvio.xsd;

import it.unibas.silvio.modello.DatoPrimitivo;
import it.unibas.silvio.sql.SelectParser;
import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import junit.framework.TestCase;

public class TestSelectParser extends TestCase {

    public String s1 = "SeLecT * from Utenti";
    public String s2 = "select utente.nome,utente.cognome FROM Utenti";
    public String s3 = "select utente.nome,utente.cognome from Utenti UNION select utente.citta,utente.comune from Utenti";
    public String s4 = "select utente.nome,utente.cognome from Utenti UNION select *";
    public String sParam1 = "SeLecT * from Utenti where utente.nome = ?";
    public String sParam2 = "select utente.nome,utente.cognome FROM Utenti WhErE utente.nome = ?";
    public String sParam3 = "select utente.nome,utente.cognome from Utenti WhErE utente.nome = ? UNION select utente.citta,utente.comune from Utenti WHERE utente.citta = ? AND utente.comune=?";
    public String sParam4 = "select utente.nome,utente.cognome from Utenti WhErE utente.nome = ? AND utente.cognome=? UNION select * from utenti where utente.citta = ?";
    public String sParam5 = "select utente.nome,utente.cognome from Utenti  UNION select * from utenti where utente.nome = ? and utente.cognome=?";
    public String sParam6 = "select utente.nome,utente.cognome from Utenti    group by utente.cognome  UNION   select * from utenti where utente.nome = ? and utente.cognome=?";

    public void testSelect() {
        List<DatoPrimitivo> dati = null;
        dati = SelectParser.parse(s1);
        Assert.assertTrue("Select generica", dati == null);

        dati = SelectParser.parse(s2);
        Assert.assertTrue("Select unica", dati.size() == 2);
        Assert.assertTrue("Select unica", dati.get(0).getNome().equals("utente.nome"));
        Assert.assertTrue("Select unica", dati.get(1).getNome().equals("utente.cognome"));

        dati = SelectParser.parse(s3);
        Assert.assertTrue("Select union", dati.size() == 4);
        Assert.assertTrue("Select unica", dati.get(0).getNome().equals("utente.nome"));
        Assert.assertTrue("Select unica", dati.get(1).getNome().equals("utente.cognome"));
        Assert.assertTrue("Select unica", dati.get(2).getNome().equals("utente.citta"));
        Assert.assertTrue("Select unica", dati.get(3).getNome().equals("utente.comune"));

        dati = SelectParser.parse(s4);
        Assert.assertTrue("Select union generica", dati == null);
    }

    public void testEscludiParametriSelect() {
        //DATA UNA QUERY SELECT, ESTRAE SOLO LA PARTE ESCLUDENDO IL WHERE
        String selectRipulita = SelectParser.escludiWhere(s1);
        Assert.assertEquals(s1, s1);

        selectRipulita = SelectParser.escludiWhere(sParam1);
        Assert.assertEquals("SeLecT * from Utenti", selectRipulita);

        selectRipulita = SelectParser.escludiWhere(sParam2);
        Assert.assertEquals("select utente.nome,utente.cognome FROM Utenti", selectRipulita);

        selectRipulita = SelectParser.escludiWhere(sParam3);
        Assert.assertEquals("select utente.nome,utente.cognome from Utenti UNION select utente.citta,utente.comune from Utenti", selectRipulita);

        selectRipulita = SelectParser.escludiWhere(sParam4);
        Assert.assertEquals("select utente.nome,utente.cognome from Utenti UNION select * from utenti", selectRipulita);

        selectRipulita = SelectParser.escludiWhere(sParam5);
        Assert.assertEquals("select utente.nome,utente.cognome from Utenti UNION select * from utenti", selectRipulita);

        //Prima e dopo la UNION c'è sempre un solo spazio perchè siamo noi che ricostruiamo la query
        selectRipulita = SelectParser.escludiWhere(sParam6);
        Assert.assertEquals("select utente.nome,utente.cognome from Utenti    group by utente.cognome UNION select * from utenti", selectRipulita);

    }

    public void testParametriSelect() {
        //DATA UNA QUERY PARAMETRICA, LA ELABORA E ESTRAE LA LISTA DEI PARAMETRI        
        List<DatoPrimitivo> dati = SelectParser.estraiParametri(s1);
        Assert.assertTrue("Select senza where", dati.size() == 0);

        dati = SelectParser.estraiParametri(sParam1);
        Assert.assertTrue("Select unica", dati.size() == 1);
        Assert.assertTrue("Select unica", dati.get(0).getNome().equals("utente.nome"));

        dati = SelectParser.estraiParametri(sParam2);
        Assert.assertTrue("Select unica", dati.size() == 1);
        Assert.assertTrue("Select unica", dati.get(0).getNome().equals("utente.nome"));

        dati = SelectParser.estraiParametri(sParam3);
        Assert.assertTrue("Select con union 1", dati.size() == 3);
        Assert.assertTrue("Select con union 1", dati.get(0).getNome().equals("utente.nome"));
        Assert.assertTrue("Select con union 1", dati.get(1).getNome().equals("utente.citta"));
        Assert.assertTrue("Select con union 1", dati.get(2).getNome().equals("utente.comune"));

        dati = SelectParser.estraiParametri(sParam4);
        Assert.assertTrue("Select con union 2", dati.size() == 3);
        Assert.assertTrue("Select con union 2", dati.get(0).getNome().equals("utente.nome"));
        Assert.assertTrue("Select con union 2", dati.get(1).getNome().equals("utente.cognome"));
        Assert.assertTrue("Select con union 2", dati.get(2).getNome().equals("utente.citta"));

        dati = SelectParser.estraiParametri(sParam5);
        Assert.assertTrue("Select con union 2", dati.size() == 2);
        Assert.assertTrue("Select con union 2", dati.get(0).getNome().equals("utente.nome"));
        Assert.assertTrue("Select con union 2", dati.get(1).getNome().equals("utente.cognome"));
    }
    
    public void testCompletaQueryParametriche(){
        List<DatoPrimitivo> datiIstanza = new ArrayList<DatoPrimitivo>();
        datiIstanza.add(new DatoPrimitivo("utente.nome", "winston"));
        datiIstanza.add(new DatoPrimitivo("utente.cognome", "smith"));
        datiIstanza.add(new DatoPrimitivo("utente.citta", "londra"));
        datiIstanza.add(new DatoPrimitivo("utente.comune", "londrax"));
        
        String select = SelectParser.completaQuery(sParam1,datiIstanza);
        Assert.assertEquals("SeLecT * from Utenti where utente.nome = 'winston'", select);
        
        select = SelectParser.completaQuery(sParam2,datiIstanza);
        Assert.assertEquals("select utente.nome,utente.cognome FROM Utenti WhErE utente.nome = 'winston'", select);
        
        select = SelectParser.completaQuery(sParam3,datiIstanza);
        Assert.assertEquals("select utente.nome,utente.cognome from Utenti WhErE utente.nome = 'winston' UNION select utente.citta,utente.comune from Utenti WHERE utente.citta = 'londra' AND utente.comune = 'londrax'", select);
        
        select = SelectParser.completaQuery("select * from utenti where codicefiscale = ?",datiIstanza);
        Assert.assertEquals(null, select);        
        
        select = SelectParser.completaQuery("select * from utenti where utente.nom = ?",datiIstanza);
        Assert.assertEquals(null, select);
        
        
    }
}
