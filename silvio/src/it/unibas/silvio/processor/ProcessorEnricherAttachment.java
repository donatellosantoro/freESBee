package it.unibas.silvio.processor;

import it.unibas.silvio.modello.Messaggio;
import it.unibas.silvio.modello.Query;
import it.unibas.silvio.persistenza.SilvioException;
import it.unibas.silvio.sql.ResultSetUtil;
import it.unibas.silvio.util.SilvioUtil;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ProcessorEnricherAttachment implements Processor {

    private org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog(this.getClass());
    private String tipo;

    public ProcessorEnricherAttachment(String tipo) {
        this.tipo = tipo;
    }

    public void process(Exchange exchange) throws Exception {
        Messaggio messaggio = (Messaggio) exchange.getProperty(tipo);
        List<String> listaPercorsiAttachment = new ArrayList<String>();

        Query query = messaggio.getQueryAttachment();
        List<String> listaSelectAttachment = messaggio.getSelectAttachment();
        for (String select : listaSelectAttachment) {
            List<String> percorsi = eseguiSelectAttachment(query, select);
            listaPercorsiAttachment.addAll(percorsi);
        }
        List<String> listaAttachment = SilvioUtil.getAttachmentList(exchange);
        listaAttachment.clear();
        listaAttachment.addAll(listaPercorsiAttachment);
    }

    public List<String> eseguiSelectAttachment(Query query, String select) throws SilvioException {
        logger.info("Eseguo la select " + select + " per estrarre il percorso del file da allegare");
        ResultSetUtil rsUtil = new ResultSetUtil();
        List<String> listaPercorsiAttachment = new ArrayList<String>();
        try {
            ResultSet rs = rsUtil.queryToRs(query, select);
            while (rs.next()) {
                String percorso = rs.getString(1);
                listaPercorsiAttachment.add(percorso);
            }
        } catch (Exception ex) {
            logger.error("Impossibile eseguire la query sul db per estrarre l'attachment. " + ex);
            throw new SilvioException("Impossibile eseguire la query sul db per estrarre l'attachment. " + ex);
        } finally {
            rsUtil.chiudiConnessioni();
        }
        return listaPercorsiAttachment;
    }
}
