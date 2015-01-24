package it.unibas.icar.freesbee.processors;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import it.unibas.icar.freesbee.exception.FreesbeeException;
import it.unibas.icar.freesbee.modello.AccordoServizio;
import it.unibas.icar.freesbee.modello.Azione;
import it.unibas.icar.freesbee.modello.Configurazione;
import it.unibas.icar.freesbee.modello.CostantiEccezioni;
import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.modello.PortaApplicativa;
import it.unibas.icar.freesbee.modello.Servizio;
import it.unibas.icar.freesbee.modello.ServizioApplicativo;
import it.unibas.icar.freesbee.modello.Soggetto;
import it.unibas.icar.freesbee.persistenza.DBManager;
import it.unibas.icar.freesbee.utilita.CostantiBusta;
import java.util.List;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Singleton
public class ProcessorIdentificazioneErogatore implements Processor {

    private static Log logger = LogFactory.getLog(ProcessorIdentificazioneErogatore.class);
    @Inject
    private DBManager dbManager;

    public ProcessorIdentificazioneErogatore() {
    }

    public void process(Exchange exchange) throws Exception {
        //ContextStartup.aggiungiThread(this.getClass().getName());
        Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiBusta.MESSAGGIO);
        Configurazione configurazione = dbManager.getConfigurazione();
        String nomeErogatore = messaggio.getErogatore();
        String tipoErogatore = messaggio.getTipoErogatore();
        String nomeFruitore = messaggio.getFruitore();
        String tipoFruitore = messaggio.getTipoFruitore();
        String nomeServizio = messaggio.getServizio();
        String tipoServizio = messaggio.getTipoServizio();
        String stringaAzione = messaggio.getAzione();

        PortaApplicativa portaApplicativa = null;
        Soggetto soggettoErogatore = null;
        Servizio servizio = null;

        controllaProfiloCollaborazione(messaggio.getProfiloCollaborazione(), messaggio);
        if (configurazione.isInviaANICA()) {
            //LA PA E' COLLEGATA AI NOME DEI SOGGETTI E SERVIZI
            portaApplicativa = dbManager.getPortaApplicativa(nomeServizio, tipoServizio, nomeErogatore, tipoErogatore, stringaAzione);
        } else {
            Soggetto soggettoFruitore = dbManager.getSoggetto(nomeFruitore, tipoFruitore);
            if (soggettoFruitore == null) {
                messaggio.aggiungiEccezione(CostantiEccezioni.MITTENTE_SCONOSCIUTO);
                logger.error("Non esiste alcun soggetto con tipo " + tipoErogatore + " e nome " + nomeErogatore);
//                throw new FreesbeeException("Non esiste alcun soggetto con tipo " + tipoErogatore + " e nome " + nomeErogatore);
            }
            //LA PA E' COLLEGATA AD OGGETTI SERVIZIO-SOGGETTO
            soggettoErogatore = dbManager.getSoggetto(nomeErogatore, tipoErogatore);
            if (soggettoErogatore == null) {
                messaggio.aggiungiEccezione(CostantiEccezioni.DESTINATARIO_SCONOSCIUTO);
                logger.error("Non esiste alcuna soggetto con tipo " + tipoErogatore + " e nome " + nomeErogatore);
//                    throw new FreesbeeException("Non esiste alcun soggetto con tipo " + tipoErogatore + " e nome " + nomeErogatore);

                //Cerchiamo di risalire al soggetto corretto * PER LA QUALIFICAZIONE *
                List<Servizio> possibiliServizi = dbManager.getServizi(nomeServizio, tipoServizio);
                for (Servizio possServ : possibiliServizi) {
                    if (possServ.getFruitori().contains(soggettoFruitore)) {
                        Soggetto e = possServ.getErogatore();
                        messaggio.setErogatore(e.getNome());
                        logger.error("L'erogatore non era corretto. Un possibile erogatore per il servizio " + nomeServizio + " è " + e);
                    }
                }
            } else {
                servizio = dbManager.getServizio(nomeServizio, tipoServizio, soggettoErogatore);
                if (servizio == null) {
                    messaggio.aggiungiEccezione(CostantiEccezioni.SERVIZIO_NON_RICONOSCIUTO);
                    logger.error("Non esiste alcun servizio con tipo " + tipoServizio + " e nome " + nomeServizio);
//                        throw new FreesbeeException("Non esiste alcun servizio con tipo " + tipoServizio + " e nome " + nomeServizio);
                } else {
                    Azione azione = cercaAzione(servizio, stringaAzione, messaggio);
                    portaApplicativa = dbManager.getPortaApplicativaByServizio(servizio, azione);
                }
            }
        }

        if (portaApplicativa == null) {
            logger.error("Non esiste alcuna porta applicativa per il servizio " + tipoServizio + "-" + nomeServizio + " azione " + stringaAzione);
//                throw new FreesbeeException("Non esiste alcuna porta applicativa per il servizio " + tipoServizio + "-" + nomeServizio + " azione " + stringaAzione);
        } else {
            ServizioApplicativo servizioApplicativo = portaApplicativa.getServizioApplicativo();
            String connettore = servizioApplicativo.getConnettore();

            messaggio.setConnettoreServizioApplicativo(connettore);
            
            if (servizioApplicativo.isMutuaAutenticazione()) {
                messaggio.setMutuaAutenticazione(true);
            }
            
            if (logger.isDebugEnabled()) {if (logger.isDebugEnabled()) logger.debug("Il connettore del servizio da invocare è " + connettore);}
        }
    }

    private String controllaProfiloCollaborazione(String valoreProfiloCollaborazione, Messaggio messaggio) throws FreesbeeException {

        if (valoreProfiloCollaborazione.equalsIgnoreCase(AccordoServizio.PROFILO_ASINCRONO_ASIMMETRICO)) {
            return AccordoServizio.PROFILO_ASINCRONO_ASIMMETRICO;
        }
        if (valoreProfiloCollaborazione.equalsIgnoreCase(AccordoServizio.PROFILO_ASINCRONO_SIMMETRICO)) {
            return AccordoServizio.PROFILO_ASINCRONO_SIMMETRICO;
        }
        if (valoreProfiloCollaborazione.equalsIgnoreCase(AccordoServizio.PROFILO_ONEWAY)) {
            return AccordoServizio.PROFILO_ONEWAY;
        }
        if (valoreProfiloCollaborazione.equalsIgnoreCase(AccordoServizio.PROFILO_SINCRONO)) {
            return AccordoServizio.PROFILO_SINCRONO;
        }
        messaggio.aggiungiEccezione(CostantiEccezioni.PROFILO_COLLABORAZIONE_NON_VALIDO);
        logger.error("Il profilo di collaborazione " + valoreProfiloCollaborazione + " non è valido");
        messaggio.setProfiloCollaborazione("");
        throw new FreesbeeException("Il profilo di collaborazione " + valoreProfiloCollaborazione + " non è valido");
    }

    private Azione cercaAzione(Servizio servizio, String stringaAzione, Messaggio messaggio) throws FreesbeeException {
        if (stringaAzione == null || stringaAzione.trim().isEmpty()) {
            return null;
        }
        AccordoServizio as = servizio.getAccordoServizio();
        List<Azione> azioni = as.getAzioni();
        for (Azione az : azioni) {
            if (az.getNome().equalsIgnoreCase(stringaAzione)) {
                verificaAzioneNelServizio(messaggio, servizio, stringaAzione);
                return az;
            }
        }
        messaggio.aggiungiEccezione(CostantiEccezioni.AZIONE_NON_RICONOSCIUTA);
        throw new FreesbeeException("Non esiste alcuna azione " + stringaAzione + " nell'accordo di servizio " + as.getNome());
    }

    private void verificaAzioneNelServizio(Messaggio messaggio, Servizio servizio, String stringaAzione) throws FreesbeeException {
        if (servizio.getListaAzioni().isEmpty()) {
            return;
        }
        for (String azione : servizio.getListaAzioni()) {
            if (azione.equals(stringaAzione)) {
                return;
            }
        }
        messaggio.aggiungiEccezione(CostantiEccezioni.AZIONE_NON_RICONOSCIUTA);
        throw new FreesbeeException("Non esiste alcuna azione " + stringaAzione + " nel servizio " + servizio.getNome());
    }

    public DBManager getDbManager() {
        return dbManager;
    }

    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }
}
