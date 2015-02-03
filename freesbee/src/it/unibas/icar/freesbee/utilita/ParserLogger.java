package it.unibas.icar.freesbee.utilita;

import it.unibas.icar.freesbee.modello.ConfigurazioneStatico;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.pattern.ClassNamePatternConverter;
import org.apache.log4j.pattern.DatePatternConverter;
import org.apache.log4j.pattern.FileLocationPatternConverter;
import org.apache.log4j.pattern.FullLocationPatternConverter;
import org.apache.log4j.pattern.LevelPatternConverter;
import org.apache.log4j.pattern.LineLocationPatternConverter;
import org.apache.log4j.pattern.LineSeparatorPatternConverter;
import org.apache.log4j.pattern.LiteralPatternConverter;
import org.apache.log4j.pattern.LoggerPatternConverter;
import org.apache.log4j.pattern.LoggingEventPatternConverter;
import org.apache.log4j.pattern.MessagePatternConverter;
import org.apache.log4j.pattern.MethodLocationPatternConverter;
import org.apache.log4j.pattern.NDCPatternConverter;
import org.apache.log4j.pattern.PatternParser;
import org.apache.log4j.pattern.PropertiesPatternConverter;
import org.apache.log4j.pattern.RelativeTimePatternConverter;
import org.apache.log4j.pattern.SequenceNumberPatternConverter;
import org.apache.log4j.pattern.ThreadPatternConverter;
import pl.otros.logview.LogData;
import pl.otros.logview.importer.InitializationException;
import pl.otros.logview.importer.LogImporterUsingParser;
import pl.otros.logview.parser.ParsingContext;
import pl.otros.logview.parser.log4j.Log4jPatternMultilineLogParser;
import pl.otros.logview.reader.ProxyLogDataCollector;

public class ParserLogger {

    public static final String ALL = "ALL";
    public static final String DEBUG = "FINE";
    public static final String INFO = "INFO";
    public static final String WARN = "WARNING";
    public static final String ERROR = "SEVERE";
    public static final String FATAL = "SEVERE";
    public static final String ERROR_PARSER = "Errore nel caricare il file di log: ";
    private static final String LOG4J_APPENDER_LOGFILE_FILE = "log4j.appender.logfile.File";
    private static final String LOG4J_APPENDER_LOGFILE_LAYOUT_CONVERSIONPATTERN = "log4j.appender.logfile.layout.ConversionPattern";
    private static ParserLogger singleton = new ParserLogger();
    private Properties properties = new Properties();
    private String logFilePath = "";
    private String conversionPattern = "";
    private String patternFull = "";
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
    
    private static Log logger = LogFactory.getLog(ParserLogger.class);

    public static ParserLogger getInstance() {
        return singleton;
    }

    private ParserLogger() {
        InputStream inStream = ConfigurazioneStatico.class.getResourceAsStream("/log4j.properties");
        try {
            properties.load(inStream);
            this.logFilePath = properties.getProperty(LOG4J_APPENDER_LOGFILE_FILE);
            this.conversionPattern = properties.getProperty(LOG4J_APPENDER_LOGFILE_LAYOUT_CONVERSIONPATTERN);
            this.patternFull = getPatternFull(this.conversionPattern);
            postInit();
        } catch (IOException ex) {
            logger.error("Si e' verificato un errore durante il caricamento del file log4j.properties .");
            if (logger.isDebugEnabled()) logger.error(ex);
        }
    }

    private void postInit() {
        if (this.logFilePath.contains("${")) {
            String tmp1 = this.logFilePath.substring(0, this.logFilePath.indexOf("}"));
            String path = this.logFilePath.substring(this.logFilePath.indexOf("}") + 1, this.logFilePath.length());
            tmp1 = tmp1.replace("${", "");
            String property = System.getProperty(tmp1);
            this.logFilePath = property + path;
        }
    }

    public String getLog(Date inizio, Date fine, String livello) {
        LogData[] logDatas = extractLogDatas();
        if (logDatas != null) {
            StringBuilder builder = new StringBuilder();
            for (LogData logData : logDatas) {
                if (logData.getDate().after(inizio) && logData.getDate().before(fine) && livello.equalsIgnoreCase(logData.getLevel().toString())) {
                    builder.append(getStringFromLogData(logData)).append("\n");
                }
            }
            return builder.toString().trim();
        }
        return ERROR_PARSER + logFilePath;
    }

    public String getLog(String livello) {
        LogData[] logDatas = extractLogDatas();
        if (logDatas != null) {
            StringBuilder builder = new StringBuilder();
            for (LogData logData : logDatas) {
                if (livello.equalsIgnoreCase(logData.getLevel().toString())) {
                    builder.append(getStringFromLogData(logData)).append("\n");
                }
            }
            return builder.toString().trim();
        }
        return ERROR_PARSER + logFilePath;
    }

    public String getLog(Date inizio, Date fine) {
        LogData[] logDatas = extractLogDatas();
        if (logDatas != null) {
            StringBuilder builder = new StringBuilder();
            for (LogData logData : logDatas) {
                if (logData.getDate().after(inizio) && logData.getDate().before(fine)) {
                    builder.append(getStringFromLogData(logData)).append("\n");
                }
            }
            return builder.toString().trim();
        }
        return ERROR_PARSER + logFilePath;
    }

    public DataHandler getLog() {
        File file = new File(this.logFilePath);
        DataHandler dataHandler = new DataHandler(new FileDataSource(file));
        return dataHandler;
    }
    
    
    public String getLogFilePath() {
        return logFilePath;
    }

    public String getConversionPattern() {
        return conversionPattern;
    }

    public String getPatternFull() {
        return patternFull;
    }

    private InputStream loadLog(String resource) throws IOException {
        InputStream is = new FileInputStream(resource);
        ByteArrayInputStream bais = new ByteArrayInputStream(IOUtils.toByteArray(is));
        return bais;
    }

    private String getPatternFull(String patternFromProperties) {
        String input = OptionConverter.convertSpecialChars(patternFromProperties);
        List converters = new ArrayList();
        List fields = new ArrayList();
        Map converterRegistry = null;

        PatternParser.parse(input, converters, fields, converterRegistry, PatternParser.getPatternLayoutRules());
        String formatFromConverters = getFormatFromConverters(converters);

        return formatFromConverters;
    }

    private String getStringFromLogData(LogData data) {
        StringBuilder builder = new StringBuilder();
        List<String> patterns = extractPatternList();
        for (String pattern : patterns) {
            builder.append(extractValue(data, pattern));
        }
        return builder.toString().trim();
    }

    private String getFormatFromConverters(List converters) {
        StringBuffer buffer = new StringBuffer();
        for (Iterator iter = converters.iterator(); iter.hasNext();) {
            LoggingEventPatternConverter converter = (LoggingEventPatternConverter) iter.next();
            if (converter instanceof DatePatternConverter) {
                buffer.append("TIMESTAMP");
            } else if (converter instanceof MessagePatternConverter) {
                buffer.append("MESSAGE");
            } else if (converter instanceof LoggerPatternConverter) {
                buffer.append("LOGGER");
            } else if (converter instanceof ClassNamePatternConverter) {
                buffer.append("CLASS");
            } else if (converter instanceof RelativeTimePatternConverter) {
                buffer.append("PROP(RELATIVETIME)");
            } else if (converter instanceof ThreadPatternConverter) {
                buffer.append("THREAD");
            } else if (converter instanceof NDCPatternConverter) {
                buffer.append("NDC");
            } else if (converter instanceof LiteralPatternConverter) {
                LiteralPatternConverter literal = (LiteralPatternConverter) converter;
                literal.format(null, buffer);
            } else if (converter instanceof SequenceNumberPatternConverter) {
                buffer.append("PROP(log4jid)");
            } else if (converter instanceof LevelPatternConverter) {
                buffer.append("LEVEL");
            } else if (converter instanceof MethodLocationPatternConverter) {
                buffer.append("METHOD");
            } else if (converter instanceof FullLocationPatternConverter) {
                buffer.append("PROP(locationInfo)");
            } else if (converter instanceof LineLocationPatternConverter) {
                buffer.append("LINE");
            } else if (converter instanceof FileLocationPatternConverter) {
                buffer.append("FILE");
            } else if (converter instanceof PropertiesPatternConverter) {
                buffer.append("PROP(PROPERTIES)");
            } else if (converter instanceof LineSeparatorPatternConverter) {
            }
        }
        return buffer.toString();
    }

    private LogData[] extractLogDatas() {
        try {
            Properties p = new Properties();
            p.put("type", "log4j");
            p.put("pattern", this.patternFull);
            p.put("dateFormat", "yyyy-MM-dd HH:mm:ss,SSS");
            Log4jPatternMultilineLogParser logParser = new Log4jPatternMultilineLogParser();
            InputStream in = loadLog(this.logFilePath);
            LogImporterUsingParser importerUsingParser = new LogImporterUsingParser(logParser);
            ParsingContext context = new ParsingContext();
            importerUsingParser.init(p);
            importerUsingParser.initParsingContext(context);

            ProxyLogDataCollector dataCollector = new ProxyLogDataCollector();
            importerUsingParser.importLogs(in, dataCollector, context);

            LogData[] logDatas = dataCollector.getLogData();
            return logDatas;
        } catch (IOException ioe) {
            logger.error("Si e' verificato un errore durante il caricamento del file log4j.properties .");
            if (logger.isDebugEnabled()) logger.error(ioe);
//            logger.error("Errore nell'inzializzare la libreria OtrosLogViewer: " + initializationException);
        } catch (InitializationException ie) {
            logger.error("Si e' verificato un errore durante l'inizializzazione della libreria di log.");
            if (logger.isDebugEnabled()) logger.error(ie);
        }
        return null;
    }

    private List<String> extractPatternList() {
        List<String> patterns = new ArrayList<String>();
        StringTokenizer tokenizer = new StringTokenizer(this.patternFull);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            patterns.add(token);
        }
        return patterns;
    }

    private String extractValue(LogData data, String pattern) {
        if (pattern.contains("TIMESTAMP")) {
            if (pattern.equals("TIMESTAMP")) {
                return dateFormat.format(data.getDate()) + " ";
            } else {
                String prefix = pattern.substring(0, pattern.indexOf("TIMESTAMP"));
                String suffix = pattern.replace(prefix, "");
                suffix = suffix.replace("TIMESTAMP", "");
                return prefix + dateFormat.format(data.getDate()) + suffix + " ";
            }
        }
        if (pattern.contains("MESSAGE")) {
            if (pattern.equals("MESSAGE")) {
                return data.getMessage() + " ";
            } else {
                String prefix = pattern.substring(0, pattern.indexOf("MESSAGE"));
                String suffix = pattern.replace(prefix, "");
                suffix = suffix.replace("MESSAGE", "");
                return prefix + data.getMessage() + suffix + " ";
            }
        }
        if (pattern.contains("LOGGER")) {
            if (pattern.equals("LOGGER")) {
                return data.getLoggerName() + " ";
            } else {
                String prefix = pattern.substring(0, pattern.indexOf("LOGGER"));
                String suffix = pattern.replace(prefix, "");
                suffix = suffix.replace("LOGGER", "");
                return prefix + data.getLoggerName() + suffix + " ";
            }
        }
        if (pattern.contains("CLASS")) {
            if (pattern.equals("CLASS")) {
                return data.getClazz() + " ";
            } else {
                String prefix = pattern.substring(0, pattern.indexOf("CLASS"));
                String suffix = pattern.replace(prefix, "");
                suffix = suffix.replace("CLASS", "");
                return prefix + data.getClazz() + suffix + " ";
            }
        }
        if (pattern.contains("THREAD")) {
            if (pattern.equals("THREAD")) {
                return data.getThread() + " ";
            } else {
                String prefix = pattern.substring(0, pattern.indexOf("THREAD"));
                String suffix = pattern.replace(prefix, "");
                suffix = suffix.replace("THREAD", "");
                return prefix + data.getThread() + suffix + " ";
            }
        }
        if (pattern.contains("NDC")) {
            if (pattern.equals("NDC")) {
                return data.getNDC() + " ";
            } else {
                String prefix = pattern.substring(0, pattern.indexOf("NDC"));
                String suffix = pattern.replace(prefix, "");
                suffix = suffix.replace("NDC", "");
                return prefix + data.getNDC() + suffix + " ";
            }
        }
        if (pattern.contains("LEVEL")) {
            if (pattern.equals("LEVEL")) {
                return data.getLevel().toString() + " ";
            } else {
                String prefix = pattern.substring(0, pattern.indexOf("LEVEL"));
                String suffix = pattern.replace(prefix, "");
                suffix = suffix.replace("LEVEL", "");
                return prefix + data.getLevel().toString() + suffix + " ";
            }
        }
        if (pattern.contains("METHOD")) {
            if (pattern.equals("METHOD")) {
                return data.getMethod() + " ";
            } else {
                String prefix = pattern.substring(0, pattern.indexOf("METHOD"));
                String suffix = pattern.replace(prefix, "");
                suffix = suffix.replace("METHOD", "");
                return prefix + data.getMethod() + suffix + " ";
            }
        }
        if (pattern.contains("LINE")) {
            if (pattern.equals("LINE")) {
                return data.getLine() + " ";
            } else {
                String prefix = pattern.substring(0, pattern.indexOf("LINE"));
                String suffix = pattern.replace(prefix, "");
                suffix = suffix.replace("LINE", "");
                return prefix + data.getLine() + suffix + " ";
            }
        }
        if (pattern.contains("PROP(RELATIVETIME)")) {
            return "";
        }
        if (pattern.contains("PROP(log4jid)")) {
            return "";
        }
        if (pattern.contains("PROP(locationInfo)")) {
            return "";
        }
        if (pattern.contains("PROP(PROPERTIES)")) {
            return "";
        }
        return pattern + " ";
    }
}
