package it.unibas.icar.freesbee.test.util;

import it.unibas.icar.freesbee.modello.Eccezione;
import it.unibas.icar.freesbee.modello.FactoryEccezioniEgov;
import junit.framework.Assert;
import junit.framework.TestCase;

public class TestFactoryEccezioneEgov extends TestCase{

    public void testEccezioni(){
        FactoryEccezioniEgov fac = FactoryEccezioniEgov.getInstance();
        Eccezione e = fac.getEccezione("106");
        Assert.assertEquals("106", e.getCodiceEccezione());
        Assert.assertEquals("EGOV_IT_106", e.convertiCodiceEccezione());
        Assert.assertEquals("ErroreIntestazioneMessaggioSPCoop", e.getContestoCodifica());
        Assert.assertEquals("Azione", e.getPosizione());
        Assert.assertEquals("GRAVE", e.getRilevanza());
    }

}
