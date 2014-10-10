package it.unibas.silvio.erogatore;

import it.unibas.silvio.processor.ProcessorLog;
import it.unibas.silvio.util.CostantiCamel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class TransformerSoapSqlErogatore extends RouteBuilder{
    
    private Log logger = LogFactory.getLog(this.getClass());

    @Override
    public void configure() throws Exception {
        this.from(CostantiCamel.TRASFORMER_SOAP_SQL_EROGATORE)
                .process(new ProcessorLog(this.getClass()))
                .to(CostantiCamel.MESSAGE_STORE_DB_EROGATORE)
                .to(CostantiCamel.EVENT_DRIVER_CONSUMER_SBLOCCO_RISPOSTA);
    }

}
