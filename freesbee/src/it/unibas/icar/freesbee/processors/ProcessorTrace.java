package it.unibas.icar.freesbee.processors;

import it.unibas.icar.freesbee.utilita.MessageUtil;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProcessorTrace implements Processor {

    private static Log logger = LogFactory.getLog(ProcessorTrace.class.getName());
    public static final String IN = "IN";
    public static final String OUT = "OUT";
    private String tipoMessaggio;
    private String descrizione;
    public static Map<String, ProcessorTrace> cache = new HashMap<String, ProcessorTrace>();

    public static ProcessorTrace getInstance(String tipoMessaggio, String descrizione) {
        ProcessorTrace processor = cache.get(tipoMessaggio + descrizione);
        if (processor == null) {
            processor = new ProcessorTrace(tipoMessaggio, descrizione);
            cache.put(tipoMessaggio + descrizione, processor);
        }
        return processor;
    }

    public ProcessorTrace() {
    }

    private ProcessorTrace(String tipoMessaggio, String descrizione) {
        this.tipoMessaggio = tipoMessaggio;
        this.descrizione = descrizione;
    }

    public void process(Exchange exchange) throws Exception {
        //TODO [Michele]: Salva sul file i messaggi di ingresso / uscita
        if (!logger.isTraceEnabled()) return;
        
        try {
if (logger.isInfoEnabled()) logger.info(this.descrizione);
            Message message;
            if (tipoMessaggio.equals(IN)) {
                message = exchange.getIn();
if (logger.isInfoEnabled()) logger.info("RAW messaggio HTTP:" + estraiIntestazioniHTTP(message) + MessageUtil.getString(message));
            } else if (tipoMessaggio.equals(OUT)) {
                message = exchange.getOut();
if (logger.isInfoEnabled()) logger.info("RAW messaggio HTTP:" + estraiIntestazioniHTTP(message) + MessageUtil.getString(message));
            } else {
                throw new IllegalArgumentException("Messaggio " + tipoMessaggio + " sconosciuto");
            }
            
            if (message.getBody() == null) {
                return;
            }
            
//            if (logger.isTraceEnabled()) {logger.trace("Salvo il messaggio per " + tipoMessaggio + " - " + descrizione);}
//            if (logger.isTraceEnabled()) {logger.trace("Tipo body: " + message.getBody().getClass());}
            
//            InputStream intestazioniStream = new ByteArrayInputStream(stringaIntestazioni.getBytes());
//            InputStream bodyStream = MessageUtil.getStream(message);
//            InputStream stream = new SequenceInputStream(intestazioniStream, bodyStream);
//            int intSuffix = 0;
//            String suffix = ".txt";
//            String pathMessaggio = System.getProperty("user.home") + File.separator + "trace" + File.separator;
//            File dirs = new File(pathMessaggio);
//            dirs.mkdirs();
//            Date date = new Date();
//            DateFormat df = new SimpleDateFormat("yyyyMMdd-hhmmssSSS");
//            String fileName = df.format(date) + "-" + descrizione + suffix;
//            File traceFile = new File(pathMessaggio + fileName);
//            while (intSuffix < 10 && traceFile.exists()) {
//                intSuffix++;
//                suffix = "_" + intSuffix + ".txt";
//                fileName = df.format(date) + "-" + descrizione + suffix;
//                traceFile = new File(pathMessaggio + fileName + suffix);
//            }
//            if (logger.isTraceEnabled()) {logger.trace("Messaggio salvato in " + traceFile.toString());}
//            IOUtils.copy(stream, new FileOutputStream(traceFile));
//            bodyStream.reset();
        } catch (Exception e) {
            logger.error(e);
        }
    }

    private String estraiIntestazioniHTTP(Message message) {
//        StringBuilder intestazioni = new StringBuilder("#### START HTTP HEADERS ####\n");
        StringBuilder intestazioni = new StringBuilder("\n-------------------------------\n");
        for (String name : message.getHeaders().keySet()) {
            Object value = message.getHeader(name);
            if (value == null || !(value instanceof String)) {
                continue;
            }
            name = StringUtils.deleteWhitespace(name);
            intestazioni.append(name).append(": ").append(value.toString().trim()).append("\n");
        }
//        intestazioni.append("#### END HTTP HEADERS ####\n");
        intestazioni.append("-------------------------------\n");
        return intestazioni.toString();
    }
}
