package it.unibas.icar.updatefreesbee.controllo;

import it.unibas.icar.updatefreesbee.modello.Updater;
import it.unibas.utilita.Console;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

public class Principale implements Observer {

    private PreparaWebapps webappsPreparer = new PreparaWebapps();

    public static void main(String[] args) {
        Principale p = new Principale();
        p.esegui();
    }

    private void esegui() {
        System.out.println("#############################################################");
        System.out.println("### ESEGUIRE L'APPLICAZIONE COME AMMINISTRATORE           ###");
        System.out.println("### EFFETTUARE UNA COPIA DI BACKUP DELLA CARTELLA WEBAPPS ###");
        System.out.println("#############################################################");
        System.out.println("");
        System.out.println("Inserire il percorso della cartella webapps di tomcat contenente freESBee:");
        String percorsoWebApps = leggiPercorsoWebApps();
        File f = new File(percorsoWebApps);
        webappsPreparer.esegui(f);
        File padre = f.getParentFile();
        String percorsoTemporaneo = padre.getPath() + File.separator + "temp" + File.separator + "freESBeeUpdate";
        System.out.println("L'operazione di aggiornamento richiede una cartella temporanea.");
        System.out.println("La cartella temporanea di default e' la seguente: " + percorsoTemporaneo);
        System.out.println("Vuoi cambiarla? (Y/N)");
        String risp = leggiRisposta();
        if (risp.equalsIgnoreCase("y")) {
            System.out.println("Inserisci il percorso dove salvare i file temporanei");
            percorsoTemporaneo = leggiPercorso();
            percorsoTemporaneo += File.separator + "freESBeeUpdate";
        }
        System.out.println("Vuoi salvare i file di configurazione? (Y/N)");
        risp = leggiRisposta();
        String percorsoFileConfig = "";
        if (risp.equalsIgnoreCase("y")) {
            System.out.println("Inserisci il percorso dove salvare i file di configurazione");
            percorsoFileConfig = leggiPercorso();
            percorsoFileConfig = controllaDiversi(percorsoFileConfig, percorsoTemporaneo);
        }
        System.out.println("Vuoi salvare i vecchi war? (Y/N)");
        risp = leggiRisposta();
        String percorsoVecchiWar = "";
        if (risp.equalsIgnoreCase("y")) {
            System.out.println("Inserisci il percorso dove salvare i war");
            percorsoVecchiWar = leggiPercorso();
            percorsoVecchiWar = controllaDiversi(percorsoVecchiWar, percorsoTemporaneo);
        }
        boolean salvaConfig = false;
        if (!percorsoFileConfig.equals("")) {
            salvaConfig = true;
        }
        boolean salvaWar = false;
        if (!percorsoVecchiWar.equals("")) {
            salvaWar = true;
        }
        Updater updater = new Updater(percorsoWebApps, percorsoTemporaneo, percorsoFileConfig, percorsoVecchiWar, salvaConfig, salvaWar);
        updater.addObserver(this);
        updater.update();
        System.out.println("");
        System.out.println("************************************");
    }

    private String leggiPercorsoWebApps() {
        String s = Console.leggiStringa();
        while ((!s.endsWith("webapps") && !s.endsWith("webapps/"))) {
            System.out.println("Errore. Inserire un percorso che termina con webapps");
            s = Console.leggiStringa();
        }
        File f = new File(s);
        while ((!f.exists()) && (!f.isDirectory())) {
            System.out.println("Errore. Inserire un percorso esistente");
            s = Console.leggiStringa();
            while (!s.endsWith("webapps")) {
                System.out.println("Errore. Inserire un percorso che termina con webapps");
                s = Console.leggiStringa();
            }
            f = new File(s);
        }
        return s;
    }

    private String leggiRisposta() {
        String s = Console.leggiStringa();
        while ((s == null) && (!s.equalsIgnoreCase("y")) && (!s.equalsIgnoreCase("n"))) {
            System.out.println("Errore. Inserire y o n");
            s = Console.leggiStringa();
        }
        return s;
    }

    private String leggiPercorso() {
        String s = Console.leggiStringa();
        File f = new File(s);
        while ((!f.exists()) && (!f.isDirectory())) {
            System.out.println("Errore.Inserire un percorso corretto");
            s = Console.leggiStringa();
            f = new File(s);
        }
        return s;
    }

    private String controllaDiversi(String percorsoFileConfig, String percorsoTemporaneo) {
        String nuovo = percorsoFileConfig;
        while (nuovo.equalsIgnoreCase(percorsoTemporaneo)) {
            nuovo = leggiPercorso();
        }
        return nuovo;
    }

    public void update(Observable o, Object arg) {
        if (o != null) {
            Updater u = (Updater) o;
            System.out.println(u.getStato());
        }
    }
}
