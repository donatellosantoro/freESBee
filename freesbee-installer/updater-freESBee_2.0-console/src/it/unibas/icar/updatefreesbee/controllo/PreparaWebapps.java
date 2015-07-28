package it.unibas.icar.updatefreesbee.controllo;

import it.unibas.icar.updatefreesbee.Costanti;
import it.unibas.icar.updatefreesbee.persistenza.JarUtils;
import it.unibas.icar.updatefreesbee.persistenza.PersistenceException;
import it.unibas.utilita.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;

public class PreparaWebapps {

    public void esegui(File webappsFolder) {
        List<File> freesbeeApps = filtraFreesbee(webappsFolder);
        List<File> duplicati = cercaDuplicati(freesbeeApps);
        if (!duplicati.isEmpty()) {
            System.out.println("\n");
            System.out.println("Per poter aggiornare correttamente freESBee e' indispensabile che per ogni installazione sia presente o la cartella o il WAR, ma non entrambe");
            System.out.println("Per procedere quindi mantieni solo la versione che vuoi aggiornare (elimina il WAR o la cartella)");
            System.out.println("Questi sono i file in conflitto:");
            for (File duplicato : duplicati) {
                System.out.println("\t" + duplicato + " - versione: " + FreesbeeAnalyzer.getFreesbeeVersion(duplicato));
            }
            System.out.println("\n");
            System.exit(0);
        }
        System.out.println("\n");
        File fileBuild = new File("./varie/risorse/templateFolder/WEB-INF/classes/build.properties");
        String versioneAttuale = FreesbeeAnalyzer.loadBuildProperties(fileBuild);
        System.out.println("Sono state trovate le seguente installazioni di freESBee che verranno aggiornate alla versione " + versioneAttuale);
        System.out.println("Le cartelle saranno convertiti in un file WAR e poi eliminate...");
        for (File freesbeeApp : freesbeeApps) {
            System.out.println("\t" + freesbeeApp + " - versione: " + FreesbeeAnalyzer.getFreesbeeVersion(freesbeeApp));
        }
        System.out.println("\n");
        System.out.println("Vuoi continuare? (Y/N)");
        String risp = leggiRisposta();
        if (risp.equalsIgnoreCase("n")) {
            System.exit(0);
        }
        generaWar(freesbeeApps);
    }

    private List<File> filtraFreesbee(File webappsFolder) {
        List<File> result = new ArrayList<File>();
        for (int i = 0; i < webappsFolder.listFiles().length; i++) {
            File file = webappsFolder.listFiles()[i];
            if (FreesbeeAnalyzer.isFreesbee(file)) {
                result.add(file);
            }
        }
        return result;
    }

    private List<File> cercaDuplicati(List<File> freesbeeApps) {
        List<File> duplicates = new ArrayList<File>();
        for (int i = 0; i < freesbeeApps.size() - 1; i++) {
            for (int j = i + 1; j < freesbeeApps.size(); j++) {
                File filei = freesbeeApps.get(i);
                File filej = freesbeeApps.get(j);
                String namei = estraiFileName(filei);
                String namej = estraiFileName(filej);
                if (namei.equalsIgnoreCase(namej)) {
                    duplicates.add(filei);
                    duplicates.add(filej);
                }
            }
        }
        return duplicates;
    }

    private String estraiFileName(File file) {
        String name = file.getName();
        if (name.contains(".")) {
            name = name.substring(0, name.lastIndexOf("."));
        }
        return name;
    }

    private void generaWar(List<File> freesbeeApps) {
        for (File file : freesbeeApps) {
            FileOutputStream fos = null;
            try {
                if (!file.isDirectory()) {
                    continue;
                }
                System.out.println("La cartella " + file.getName() + " verra' convertita in un file WAR e poi eliminata...");
                fos = new FileOutputStream(file.toString() + Costanti.WAR);
                JarUtils.jar(fos, file.listFiles());
                FileUtils.deleteDirectory(file);
            } catch (Exception ex) {
                throw new PersistenceException(ex.getLocalizedMessage());
            } finally {
                try {
                    if (fos != null) {
                        fos.close();
                    }
                } catch (IOException ex) {
                }
            }
        }
    }

    private String leggiRisposta() {
        String s = Console.leggiStringa();
        while ((s == null) && (!s.equalsIgnoreCase("y")) && (!s.equalsIgnoreCase("n"))) {
            System.out.println("Errore. Inserire y o n");
            s = Console.leggiStringa();
        }
        return s;
    }
}
