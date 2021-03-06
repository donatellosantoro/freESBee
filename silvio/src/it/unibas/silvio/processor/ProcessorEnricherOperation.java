package it.unibas.silvio.processor;

import it.unibas.silvio.modello.IstanzaOperation;
import it.unibas.silvio.modello.IstanzaPortType;
import it.unibas.silvio.modello.Messaggio;
import it.unibas.silvio.modello.ParametriEseguiIstanza;
import it.unibas.silvio.persistenza.IDAOIstanzaPortType;
import it.unibas.silvio.persistenza.SilvioException;
import it.unibas.silvio.persistenza.hibernate.DAOIstanzaPortTypeHibernate;
import it.unibas.silvio.util.CostantiSilvio;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProcessorEnricherOperation implements Processor {

    private Log logger = LogFactory.getLog(this.getClass());
    private IDAOIstanzaPortType daoIstanzaPortType;

    public ProcessorEnricherOperation(IDAOIstanzaPortType daoIstanzaPortType) {
        this.daoIstanzaPortType = daoIstanzaPortType;
    }

    public void process(Exchange exchange) throws Exception {
        Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiSilvio.MESSAGGIO_RICHIESTA);
        String nomeOperazione = (String) exchange.getProperty(CostantiSilvio.NOME_OPERAZIONE);
        IstanzaPortType istanzaPortType = (IstanzaPortType) exchange.getProperty(CostantiSilvio.ISTANZA_PORTTYPE);
        IstanzaPortType istanzaPTCaricata = daoIstanzaPortType.findById(istanzaPortType.getId());
        logger.info("Richiesta l'operazione " + nomeOperazione);
        IstanzaOperation operation = istanzaPTCaricata.getOperation(nomeOperazione);
        if (operation == null) {
            throw new SilvioException("Non esiste nessuna operation " + nomeOperazione + " collegata al portType " + istanzaPortType.getNome());
        }
        messaggio.setIstanzaOperation(operation);
        operation.addMessaggio(messaggio);
        exchange.getIn().setHeader(CostantiSilvio.VALOREPROFILOCOLLABORAZIONE, operation.getOperation().getProfiloDiCollaborazione());
    }
}