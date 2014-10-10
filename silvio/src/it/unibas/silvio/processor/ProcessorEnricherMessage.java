package it.unibas.silvio.processor;

import it.unibas.silvio.modello.IstanzaOperation;
import it.unibas.silvio.modello.IstanzaPortType;
import it.unibas.silvio.modello.Messaggio;
import it.unibas.silvio.modello.ParametriEseguiIstanza;
import it.unibas.silvio.modello.ParametriMessaggioRicevuto;
import it.unibas.silvio.persistenza.IDAOIstanzaPortType;
import it.unibas.silvio.persistenza.SilvioException;
import it.unibas.silvio.persistenza.hibernate.DAOIstanzaPortTypeHibernate;
import it.unibas.silvio.util.CostantiSilvio;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProcessorEnricherMessage implements Processor {

    private Log logger = LogFactory.getLog(this.getClass());
    private IDAOIstanzaPortType daoIstanzaPortType;

    public ProcessorEnricherMessage(IDAOIstanzaPortType daoIstanzaPortType) {
        this.daoIstanzaPortType = daoIstanzaPortType;
    }

    public void process(Exchange exchange) throws Exception {
        Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiSilvio.MESSAGGIO_RICHIESTA);
        ParametriMessaggioRicevuto parametri = messaggio.getParametriMessaggioRicevuto();
        String nomeMessaggio = (String) exchange.getProperty(CostantiSilvio.NOME_MESSAGGIO);
        IstanzaPortType istanzaPortType = (IstanzaPortType) exchange.getProperty(CostantiSilvio.ISTANZA_PORTTYPE);
        IstanzaPortType istanzaPTCaricata = daoIstanzaPortType.findById(istanzaPortType.getId());
        logger.info("Richiesto il messaggio " + nomeMessaggio);
        logger.info("Istanza PortType: " + istanzaPortType.getId() + "@" + istanzaPTCaricata);
        if (nomeMessaggio == null) {
            nomeMessaggio = parametri.getNomeOperazione();
            logger.info("Nome messaggio: " + nomeMessaggio);
        }
        if (nomeMessaggio == null) {
            throw new SilvioException("Impossibile leggere il nome dell'operation ");
        }
        IstanzaOperation operation = istanzaPTCaricata.cercaOperationDaMessage(nomeMessaggio);
        if (operation == null) {
            throw new SilvioException("Non esiste nessuna operation " + nomeMessaggio + " collegata al portType " + istanzaPortType.getNome());
        }
        messaggio.setIstanzaOperation(operation);
        if (parametri.getIndirizzoRisposta() == null) {
            String indirizzoAscolto = messaggio.getIstanzaOperation().getIndirizzoRispostaAsincronoErogatore();
            parametri.setIndirizzoRisposta(indirizzoAscolto);
        }
        operation.addMessaggio(messaggio);
        exchange.getIn().setHeader(CostantiSilvio.VALOREPROFILOCOLLABORAZIONE, operation.getOperation().getProfiloDiCollaborazione());
        exchange.getIn().setHeader(CostantiSilvio.SBLOCCO_RICHIESTA_AUTOMATICA, operation.isRispostaAutomatica());
        logger.info("Risposta automatica: " + operation.isRispostaAutomatica());
    }
}