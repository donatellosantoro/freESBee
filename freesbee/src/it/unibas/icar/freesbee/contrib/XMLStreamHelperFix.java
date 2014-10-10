package it.unibas.icar.freesbee.contrib;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class XMLStreamHelperFix implements XMLStreamConstants {

    private static Log logger = LogFactory.getLog(XMLStreamHelperFix.class);

    private XMLStreamHelperFix() {
    }

    /**
     * Copies the reader to the writer. The start and end document methods must
     * be handled on the writer manually.
     *
     * TODO: if the namespace on the reader has been declared previously to
     * where we are in the stream, this probably won't work.
     *
     * @param reader
     * @param writer
     * @throws XMLStreamException
     */
    public static void copy(XMLStreamReader reader, XMLStreamWriter writer) throws XMLStreamException {
        int read = 0; // number of elements read in
        int event = reader.getEventType();

        while (reader.hasNext()) {
            switch (event) {
                case XMLStreamConstants.START_ELEMENT:
                    read++;
                    writeStartElement(reader, writer);
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    writer.writeEndElement();
                    read--;
                    if (read <= 0) {
                        return;
                    }
                    break;
                case XMLStreamConstants.NAMESPACE:
                    break;
                case XMLStreamConstants.CHARACTERS:
                    String chars = reader.getText();
//                    chars = chars.replaceAll("&", "&amp;");
//                    chars = chars.replaceAll("<", "&lt;");
//                    chars = chars.replaceAll(">", "&gt;");
//                    chars = chars.replaceAll("'", "&apos;");
//                    chars = chars.replaceAll("\"", "&quot;");
//                    if (chars.contains("xmlns")) {
////                        chars = StringEscapeUtils.escapeXml(chars); //MOD
//                        chars = chars.replaceAll("&quot;", "\"");
//                        chars = chars.replaceAll("&lt;", "<");
//                        chars = chars.replaceAll("&gt;", ">");
//                        chars = chars.replaceAll("&amp;", "&");
//                    }
                    writer.writeCharacters(chars);
                    break;
                case XMLStreamConstants.CDATA:
                    writer.writeCData(reader.getText());
                    break;
                case XMLStreamConstants.START_DOCUMENT:
                case XMLStreamConstants.END_DOCUMENT:
                case XMLStreamConstants.ATTRIBUTE:
                default:
                    break;
            }
            event = reader.next();
        }
    }

    private static void writeStartElement(XMLStreamReader reader, XMLStreamWriter writer) throws XMLStreamException {
        String local = reader.getLocalName();
        String uri = reader.getNamespaceURI();
        String prefix = reader.getPrefix();
        if (prefix == null) {
            prefix = "";
        }
        if (uri == null) {
            uri = "";
        }

        String boundPrefix = writer.getPrefix(uri);
        boolean writeElementNS = false;
        if (boundPrefix == null || !prefix.equals(boundPrefix)) {
            writeElementNS = true;
        }

        // Write out the element name
        if (prefix.length() == 0) {

            writer.writeStartElement(local);
            writer.setDefaultNamespace(uri);

        } else {
            writer.writeStartElement(prefix, local, uri);
            writer.setPrefix(prefix, uri);
        }

        // Write out the namespaces
        for (int i = 0; i < reader.getNamespaceCount(); i++) {
            String nsURI = reader.getNamespaceURI(i);
            String nsPrefix = reader.getNamespacePrefix(i);
            if (nsPrefix == null) {
                nsPrefix = "";
            }

            if (nsPrefix.length() == 0) {
                writer.writeDefaultNamespace(nsURI);
            } else {
                writer.writeNamespace(nsPrefix, nsURI);
            }

            if (nsURI.equals(uri) && nsPrefix.equals(prefix)) {
                writeElementNS = false;
            }
        }

        // Check if the namespace still needs to be written.
        // We need this check because namespace writing works
        // different on Woodstox and the RI.
        if (writeElementNS) {
            if (prefix == null || prefix.length() == 0) {
                writer.writeDefaultNamespace(uri);
            } else {
                writer.writeNamespace(prefix, uri);
            }
        }

        // Write out attributes
        for (int i = 0; i < reader.getAttributeCount(); i++) {
            String ns = reader.getAttributeNamespace(i);
            String nsPrefix = reader.getAttributePrefix(i);
            if (ns == null || ns.length() == 0) {
                writer.writeAttribute(reader.getAttributeLocalName(i), reader.getAttributeValue(i));
            } else if (nsPrefix == null || nsPrefix.length() == 0) {
                writer.writeAttribute(reader.getAttributeNamespace(i), reader.getAttributeLocalName(i), reader.getAttributeValue(i));
            } else {
                writer.writeAttribute(reader.getAttributePrefix(i), reader.getAttributeNamespace(i), reader.getAttributeLocalName(i),
                        reader.getAttributeValue(i));
            }

        }
    }

    /**
     * Write a start element with the specified parameters
     *
     * @param writer
     * @param uri
     * @param local
     * @param prefix
     * @throws XMLStreamException
     */
    public static void writeStartElement(XMLStreamWriter writer, String uri, String local, String prefix) throws XMLStreamException {
        if (prefix == null) {
            prefix = "";
        }
        if (uri == null) {
            uri = "";
        }

        String boundPrefix = writer.getPrefix(uri);
        boolean writeElementNS = false;
        if (boundPrefix == null || !prefix.equals(boundPrefix)) {
            writeElementNS = true;
        }

        // Write out the element name
        if (prefix.length() == 0) {

            writer.writeStartElement(local);
            writer.setDefaultNamespace(uri);

        } else {
            writer.writeStartElement(prefix, local, uri);
            writer.setPrefix(prefix, uri);
        }

        // Check if the namespace still needs to be written.
        // We need this check because namespace writing works
        // different on Woodstox and the RI.
        if (writeElementNS) {
            if (prefix.length() == 0) {
                writer.writeDefaultNamespace(uri);
            } else {
                writer.writeNamespace(prefix, uri);
            }
        }
    }

    /**
     * Write a start element with the given QName. However, if a namespace has
     * already been bound to a prefix, use the existing one, else default to the
     * prefix in the QName (if specified). Else, a prefix is generated.
     *
     * @param writer
     * @param name
     * @throws XMLStreamException
     */
    public static void writeStartElement(XMLStreamWriter writer, QName name) throws XMLStreamException {
        String prefix = choosePrefix(writer, name, false);
        writeStartElement(writer, name.getNamespaceURI(), name.getLocalPart(), prefix);
    }

    /**
     *
     * @param out
     * @param name
     * @throws XMLStreamException
     */
    public static void writeTextQName(XMLStreamWriter out, QName name) throws XMLStreamException {
        String prefix = choosePrefix(out, name, true);
        if (XMLConstants.DEFAULT_NS_PREFIX.equals(prefix)) {
            out.writeCharacters(name.getLocalPart());
        } else {
            out.writeCharacters(prefix + ":" + name.getLocalPart());
        }
    }

    protected static String choosePrefix(XMLStreamWriter out, QName name, boolean declare) throws XMLStreamException {
        String uri = name.getNamespaceURI();
        // If no namespace
        if (uri == null || XMLConstants.NULL_NS_URI.equals(uri)) {
            if (!XMLConstants.NULL_NS_URI.equals(out.getNamespaceContext().getNamespaceURI(XMLConstants.DEFAULT_NS_PREFIX))) {
                out.setPrefix(XMLConstants.DEFAULT_NS_PREFIX, XMLConstants.NULL_NS_URI);
            }
            return XMLConstants.DEFAULT_NS_PREFIX;
            // Need to write a prefix
        } else {
            String defPrefix = name.getPrefix();
            // A prefix is specified
            if (defPrefix != null && !XMLConstants.DEFAULT_NS_PREFIX.equals(defPrefix)) {
                // if the uri is bound to the specified prefix, good, else
                if (!uri.equals(out.getNamespaceContext().getNamespaceURI(defPrefix))) {
                    // if there is a prefix bound to the uri, use it
                    if (out.getNamespaceContext().getPrefix(uri) != null) {
                        defPrefix = out.getNamespaceContext().getPrefix(uri);
                        // get prefix from the writer
                    } else if (out.getPrefix(uri) != null) {
                        defPrefix = out.getPrefix(uri);
                        // we need to bind the prefix
                    } else if (declare) {
                        out.setPrefix(defPrefix, uri);
                        out.writeNamespace(defPrefix, uri);
                    }
                }
                // No prefix specified
            } else {
                // if there is a prefix bound to the uri, use it
                if (out.getNamespaceContext().getPrefix(uri) != null) {
                    defPrefix = out.getNamespaceContext().getPrefix(uri);
                    // get prefix from the writer
                } else if (out.getPrefix(uri) != null) {
                    defPrefix = out.getPrefix(uri);
                    // we need to generate a prefix
                } else {
                    defPrefix = getUniquePrefix(out);
                    if (declare) {
                        out.setPrefix(defPrefix, uri);
                        out.writeNamespace(defPrefix, uri);
                    }
                }
            }
            return defPrefix;
        }
    }

    protected static String getUniquePrefix(XMLStreamWriter writer) {
        int n = 1;
        while (true) {
            String nsPrefix = "ns" + n;
            if (writer.getNamespaceContext().getNamespaceURI(nsPrefix) == null) {
                return nsPrefix;
            }
            n++;
        }
    }
}
