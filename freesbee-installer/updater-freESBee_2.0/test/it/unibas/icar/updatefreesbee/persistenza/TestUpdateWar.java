package it.unibas.icar.updatefreesbee.persistenza;

import java.io.File;
import junit.framework.TestCase;

public class TestUpdateWar extends TestCase {

    private String cartellaDati;
    private String cartellaFileConfig;
    private UpdateWar updateWar;

    @Override
    public void setUp() {
        cartellaDati = TestUpdateWar.class.getResource("/dati").getPath();
        cartellaDati = cartellaDati.replace("%20", " "); // modifico i caratteri di spazio
        cartellaFileConfig = cartellaDati + "/temp2";
        updateWar = new UpdateWar();
    }

    public void testUpdate() throws PersistenceException {
        File freesbee1 = new File(cartellaDati+"/freesbee1.war");
        long lastModified1 = freesbee1.lastModified();
        updateWar.update(cartellaDati, cartellaFileConfig);
        freesbee1 = new File(cartellaDati+"/freesbee1.war");
        long lastModified2 = freesbee1.lastModified();
        assertTrue("Ultima modifica più recente", lastModified2 > lastModified1);
    }
}
