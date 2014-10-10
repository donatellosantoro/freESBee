package it.unibas.icar.freesbee.test.util;

import it.unibas.icar.freesbee.utilita.FreesbeeUtil;
import junit.framework.Assert;
import junit.framework.TestCase;

public class TestFreesbeeUtil extends TestCase{

    public void testQueryString(){
        String queryString = "query1=test&query2=prova";
        Assert.assertNull("Query string nulla", FreesbeeUtil.leggiQueryString(null, null));
        Assert.assertNull("Query string nulla", FreesbeeUtil.leggiQueryString(queryString, null));
        Assert.assertEquals("Query string query1","test", FreesbeeUtil.leggiQueryString(queryString, "query1"));
        Assert.assertEquals("Query string query2","prova", FreesbeeUtil.leggiQueryString(queryString, "query2"));
        Assert.assertEquals("Query string inesistente",null, FreesbeeUtil.leggiQueryString(queryString, "query3"));
        Assert.assertEquals("Query string errata",null, FreesbeeUtil.leggiQueryString("query1@test&query2=prova", "query1"));
    }

    public void testPulisciSoapAction(){
        Assert.assertEquals("prova", FreesbeeUtil.pulisciSoapAction("prova"));
        Assert.assertEquals("prova", FreesbeeUtil.pulisciSoapAction(" prova "));
        Assert.assertEquals("prova", FreesbeeUtil.pulisciSoapAction("\"prova\""));
        Assert.assertEquals("prova", FreesbeeUtil.pulisciSoapAction("\"prova\" "));
        Assert.assertEquals("pr\"ova\"", FreesbeeUtil.pulisciSoapAction("pr\"ova\""));
    }
}
