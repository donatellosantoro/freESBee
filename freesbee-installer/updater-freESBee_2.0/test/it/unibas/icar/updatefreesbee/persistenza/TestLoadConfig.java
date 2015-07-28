package it.unibas.icar.updatefreesbee.persistenza;

import java.io.File;
import junit.framework.TestCase;

public class TestLoadConfig extends TestCase {

    private String cartellaDati;

    @Override
    public void setUp() {
        cartellaDati = TestLoadConfig.class.getResource("/dati").getPath();
        cartellaDati = cartellaDati.replace("%20", " "); // modifico i caratteri di spazio
    }

    public void testLoadConfigFile() throws PersistenceException {
        LoadConfig loadConfig = LoadConfig.getInstance();
        loadConfig.loadConfig(cartellaDati, cartellaDati + File.separator + "temp", null, false);
        File tempFolder = new File(cartellaDati + File.separator + "temp");
        assertTrue("Tempfolder è una cartella", tempFolder.isDirectory());
        File[] sottocartelle = tempFolder.listFiles();
        assertNotNull("Sottocartelle non nullo", sottocartelle);
        assertTrue("Numero di cartelle 2", sottocartelle.length == 2);
        assertTrue("Prima sottocartella è una cartella", sottocartelle[0].isDirectory());
        assertEquals("Il nome della prima cartella è freesbee1", "freesbee1", sottocartelle[0].getName());
        assertTrue("Seconda sottocartella è una cartella", sottocartelle[1].isDirectory());
        assertEquals("Il nome della seconda cartella è freesbee2", "freesbee2", sottocartelle[1].getName());
        File[] properties1 = sottocartelle[0].listFiles();
        File[] properties2 = sottocartelle[1].listFiles();
        assertNotNull("File di properties 1 non nullo", properties1);
        assertNotNull("File di properties 2 non nullo", properties2);
        assertTrue("Numero di file di properties per 1 = 3", properties1.length == 3);
        assertTrue("Numero di file di properties per 2 = 3", properties2.length == 3);
        for (int i = 0; i < 3; i++) {
            File file1 = properties1[i];
            assertTrue("E' un file", file1.isFile());
            assertTrue("Il nome è corretto", verificaNome(file1));
            assertTrue("File non vuoto", file1.length() > 0);
            File file2 = properties2[i];
            assertTrue("E' un file", file2.isFile());
            assertTrue("Il nome è corretto", verificaNome(file2));
            assertTrue("File non vuoto", file2.length() > 0);
        }
        //assertTrue("Cartella temporanea cancellata", deleteDir(tempFolder));
    }

    private boolean verificaNome(File file) {
        String nome = file.getName();
        if (nome.equalsIgnoreCase("freesbee.properties")
                || nome.equalsIgnoreCase("log4j.properties")
                || nome.equalsIgnoreCase("hibernate.properties")) {
            return true;
        }
        return false;
    }

    public boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
}
