package it.unibas.freesbee.ge.modello;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.regex.Pattern;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import org.apache.cxf.helpers.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

@Entity
public class FiltroContenuto implements Serializable {

    private int id;
    private String regularExpression;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getRegularExpression() {
        return regularExpression;
    }

    public void setRegularExpression(String regularExpression) {
        this.regularExpression = regularExpression;
    }

    public static FiltroContenuto binding(String bodyMessaggio) throws Exception {
        FiltroContenuto filtroContenuto = new FiltroContenuto();
        Document doc = XMLUtils.parse(bodyMessaggio);
        Node nodo = doc.getDocumentElement().getElementsByTagName("regularExpression").item(0);
        String stringaMessaggio = "";
        if (nodo.getChildNodes().getLength() > 0) {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            Source src = new DOMSource(nodo.getFirstChild());
            String enc = null;
            if (src instanceof DOMSource && ((DOMSource) src).getNode() instanceof Document) {
                enc = ((Document) ((DOMSource) src).getNode()).getXmlEncoding();
            }
            XMLUtils.writeTo(src, os, -1, enc, "yes");
            stringaMessaggio = os.toString();
        } else {
            stringaMessaggio = nodo.getTextContent();
        }

        //Per verificare che l'espressione regolare sia corretta
        Pattern.compile(stringaMessaggio);
        filtroContenuto.setRegularExpression(stringaMessaggio);
        return filtroContenuto;
    }
}
