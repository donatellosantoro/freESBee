package it.unibas.freesbee.ge.utilita;

import it.unibas.freesbee.ge.modello.SottoscrizioneEsterna;
import it.unibas.freesbee.ge.persistenza.DAOException;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrizioneEsterna;
import it.unibas.freesbee.ge.persistenza.facade.DAOSottoscrizioneEsternaFacade;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOUtilHibernate;
import java.util.GregorianCalendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DemoneRipulituraInAttesa extends Thread {

    private Log logger = LogFactory.getLog(this.getClass());
    private IDAOSottoscrizioneEsterna daoSottoscrizioneEsterna;

    public DemoneRipulituraInAttesa(IDAOSottoscrizioneEsterna daoSottoscrizioneEsterna) {
        this.daoSottoscrizioneEsterna = daoSottoscrizioneEsterna;
        this.start();
    }

    @Override
    public void run() {
        logger.info("Avviata demone per la ripulitura delle sottoscrizioni in attesa.");

        while (true) {
            try {
                DAOUtilHibernate.beginTransaction();
                List<SottoscrizioneEsterna> elencoSottoscrizioniInAttesa = daoSottoscrizioneEsterna.findAll();
                logger.info("numero sottoscrizioni " + elencoSottoscrizioniInAttesa.size());
                for (SottoscrizioneEsterna sottoscrizione : elencoSottoscrizioniInAttesa) {
                    logger.info("Sottoscrizione - Sottoscrittore " + sottoscrizione.getSottoscrittore() + " - Categoria di Eventi " + sottoscrizione.getCategoriaEventi());

                    Date dataAttuale = new GregorianCalendar().getTime();
                    if (sottoscrizione.getScadenzaAttesa() != null && dataAttuale.after(sottoscrizione.getScadenzaAttesa())) {
                        logger.info("Sto cancellando la sottoscrizione in attesa con  : ID " + sottoscrizione.getId());
                        DAOSottoscrizioneEsternaFacade.eliminaSottoscrizioneEsterna(sottoscrizione.getCategoriaEventi(), sottoscrizione.getSottoscrittore());
                    }
                }
                DAOUtilHibernate.commit();
                // millisecondi(3 minuti) : 180000
                // millisecondi(6 ore) : 21600000
                // millisecondi(30 minuti) : 1800000

                sleep(21600000);
            } catch (InterruptedException ex) {
                logger.error(ex);
            } catch (DAOException ex) {
                logger.error("Impossibile cancellare le sottoscrizioni in attesa. " + ex);
                DAOUtilHibernate.rollback();
            }
        }
    }
}
