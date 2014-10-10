package it.unibas.freesbee.ge.utilita;

import it.unibas.freesbee.ge.modello.Configurazione;
import it.unibas.freesbee.ge.modello.ConfigurazioniFactory;
import it.unibas.freesbee.ge.modello.EventoPubblicatoEsterno;
import it.unibas.freesbee.ge.modello.EventoPubblicatoInterno;
import it.unibas.freesbee.ge.persistenza.DAOException;
import it.unibas.freesbee.ge.persistenza.IDAOEventoPubblicatoEsterno;
import it.unibas.freesbee.ge.persistenza.IDAOEventoPubblicatoInterno;
import it.unibas.freesbee.ge.persistenza.IDAOStatoMessaggioEsterno;
import it.unibas.freesbee.ge.persistenza.IDAOStatoMessaggioInterno;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOUtilHibernate;
import java.util.GregorianCalendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DemoneRipulitura extends Thread {

    private Log logger = LogFactory.getLog(this.getClass());
    private IDAOEventoPubblicatoEsterno daoEventopubblicatoEsterno;
    private IDAOEventoPubblicatoInterno daoEventopubblicatoInterno;
    private IDAOStatoMessaggioEsterno daoStatoMessaggioEsterno;
    private IDAOStatoMessaggioInterno daoStatoMessaggioInterno;

    public DemoneRipulitura(IDAOEventoPubblicatoEsterno daoEventopubblicatoEsterno, IDAOEventoPubblicatoInterno daoEventopubblicatoInterno, IDAOStatoMessaggioEsterno daoStatoMessaggioEsterno, IDAOStatoMessaggioInterno daoStatoMessaggioInterno) {
        this.daoEventopubblicatoEsterno = daoEventopubblicatoEsterno;
        this.daoEventopubblicatoInterno = daoEventopubblicatoInterno;
        this.daoStatoMessaggioEsterno = daoStatoMessaggioEsterno;
        this.daoStatoMessaggioInterno = daoStatoMessaggioInterno;
        this.start();
    }

    @Override
    public void run() {
        logger.info("Avviata demone per la ripulitura dei messaggi scaduti.");

        while (true) {
            int eventiEliminati = 0;
            try {
                DAOUtilHibernate.beginTransaction();
                Configurazione configurazione = ConfigurazioniFactory.getConfigurazioneIstance();
                int scadenza = configurazione.getScadenzaMessaggi();

                List<EventoPubblicatoEsterno> elencoEventoPubblicatoEsterno = daoEventopubblicatoEsterno.findAll();
                List<EventoPubblicatoInterno> elencoEventoPubblicatoIntenro = daoEventopubblicatoInterno.findAll();

                for (EventoPubblicatoEsterno evento : elencoEventoPubblicatoEsterno) {
                    if (evento.getDataPubblicazione().getTime() + (scadenza * 1000) < new GregorianCalendar().getTimeInMillis()) {
                        java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.LONG);
                        logger.debug("Sto cancellando l'eventoPubblicato : ID " + evento.getId() + " con scadenza : " + dateFormat.format(new Date(evento.getDataPubblicazione().getTime() + (scadenza * 1000))));
                        daoStatoMessaggioEsterno.removeByEentoPubblicatoEsterno(evento);
                        daoEventopubblicatoEsterno.makeTransient(evento);
                        eventiEliminati++;
                    }
                }

                for (EventoPubblicatoInterno evento : elencoEventoPubblicatoIntenro) {
                    if (evento.getDataPubblicazione().getTime() + (scadenza * 1000) < new GregorianCalendar().getTimeInMillis()) {
                        java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.LONG);
                        logger.debug("Sto cancellando l'eventoPubblicato : ID " + evento.getId() + " con scadenza : " + dateFormat.format(new Date(evento.getDataPubblicazione().getTime() + (scadenza * 1000))));
                        daoStatoMessaggioInterno.removeByEentoPubblicatoInterno(evento);
                        daoEventopubblicatoInterno.makeTransient(evento);
                        eventiEliminati++;
                    }
                }
                DAOUtilHibernate.commit();

                logger.debug("Sono stati eliminati in totale: " + eventiEliminati + " eventi scaduti");
//                logger.debug("Mi metto in pausa per 3 minuti");
                // millisecondi(3 minuti) : 180000
                // millisecondi(6 ore) : 21600000
                // millisecondi(30 minuti) : 1800000

                sleep(21600000);
            } catch (InterruptedException ex) {
            } catch (DAOException ex) {
                logger.error("Impossibile cancellare gli eventi evasi scaduti. " + ex);
                DAOUtilHibernate.rollback();
            }
        }

    }
}
