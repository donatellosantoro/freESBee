package it.unibas.icar.freesbee.processors.strategy;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import it.unibas.icar.freesbee.modello.Configurazione;
import it.unibas.icar.freesbee.persistenza.DBManager;
import it.unibas.icar.freesbee.persistenza.IDAOAccordoServizio;
import it.unibas.icar.freesbee.persistenza.IDAOServizio;
import it.unibas.icar.freesbee.persistenza.IDAOSoggetto;

@Singleton
public class EnricherPortaDelegataFactory {

//    @Inject()
//    public EnricherPortaDelegataFactory(IDAOServizio daoServizio, IDAOSoggetto daoSoggetto, IDAOAccordoServizio daoAccordoServizio) {
//        this.daoServizio = daoServizio;
//        this.daoSoggetto = daoSoggetto;
//        this.daoAccordoServizio = daoAccordoServizio;
//    }
    @Inject
    private EnricherPortaDelegataSemplice enricherPortaDelegataSemplice;

    public IEnricherPortaDelegataStrategy getInstance(Configurazione configurazione) {
        assert (configurazione != null) : "E' stata richiesta un enricherPortaDelegata passando una configurazione nulla";
        if (configurazione.isInviaANICA()) {
            //E' PRESENTE UN NICA. QUINDI DOBBIAMO RICHIEDERE LE INFORMAZIONI AL REGISTRO DEI SERVIZI
            return new EnricherPortaDelegataRegistroServizi(configurazione.getConnettoreRegistroServizi(), !configurazione.isRegistroFreesbee());
        } else {
            //ALTRIMENTI LE LEGGIAMO DALLA NOSTRA BASE DI DATI
            return enricherPortaDelegataSemplice;
        }
    }

    public EnricherPortaDelegataSemplice getEnricherPortaDelegataSemplice() {
        return enricherPortaDelegataSemplice;
    }

    public void setEnricherPortaDelegataSemplice(EnricherPortaDelegataSemplice enricherPortaDelegataSemplice) {
        this.enricherPortaDelegataSemplice = enricherPortaDelegataSemplice;
    }
}
