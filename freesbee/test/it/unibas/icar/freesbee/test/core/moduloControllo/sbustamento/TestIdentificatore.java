package it.unibas.icar.freesbee.test.core.moduloControllo.sbustamento;

import it.unibas.icar.freesbee.processors.ProcessorValidaBustaEGov;
import junit.framework.Assert;
import junit.framework.TestCase;

public class TestIdentificatore extends TestCase{

    private ProcessorValidaBustaEGov processor = new ProcessorValidaBustaEGov();

    public void testValidi(){
        Assert.assertTrue(processor.validaIdentificatore("XX_XXSPCoopIT_1234567_2010-11-23_15:54"));
        Assert.assertTrue(processor.validaIdentificatore("CNIPA_CNIPASPCoopIT_0000025_2010-11-23_15:54"));
    }
    public void testNonValidi(){
        Assert.assertFalse(processor.validaIdentificatore("prova"));
        Assert.assertFalse(processor.validaIdentificatore("XX__1234567_2010-11-23_15:54"));
        Assert.assertFalse(processor.validaIdentificatore("CNIPA_CNIPASPCoopIT_000o025_2010-11-23_15:54"));
        Assert.assertFalse(processor.validaIdentificatore("XX_xx_1234567__15:54"));
        Assert.assertFalse(processor.validaIdentificatore("XXXXSPCoopIT_1234567_2010-11-23_15:54"));
        Assert.assertFalse(processor.validaIdentificatore("XX_XXSPCoopIT1234567_2010-11-23_15:54"));
        Assert.assertFalse(processor.validaIdentificatore("XX_XXSPCoopIT_12345678_2010-11-23_15:54"));
        Assert.assertFalse(processor.validaIdentificatore("XX_XXSPCoopIT_1234567_2010-13-23_15:54"));
        Assert.assertFalse(processor.validaIdentificatore("XX_XXSPCoopIT_1234567_2010-11-23_25:54"));
        
    }

}
