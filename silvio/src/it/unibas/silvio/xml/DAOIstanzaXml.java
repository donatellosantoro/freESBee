package it.unibas.silvio.xml;

import it.unibas.silvio.modello.DatoPrimitivo;
import it.unibas.silvio.modello.ParametriEseguiIstanza;
import it.unibas.silvio.modello.RispostaEseguiIstanza;
import java.util.ArrayList;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;

public class DAOIstanzaXml {

    public static ParametriEseguiIstanza elaboraFileConfigurazione(Document doc) {
        ParametriEseguiIstanza parametri = new ParametriEseguiIstanza();
        Element root = doc.getRootElement();
        if (root.getAttributeValue("test") != null) {
            parametri.setTest(Boolean.parseBoolean(root.getAttributeValue("test")));
        }
        if (root.getAttributeValue("correlazione") != null) {
            parametri.setCorrelazione(root.getAttributeValue("correlazione"));
        }
        parametri.setNomeOperazione(root.getChildText("nomeOperazione"));
        parametri.setIndirizzo(root.getChildText("indirizzoRichiesta"));
        if (root.getChildText("risorsa") != null) {
            parametri.setRisorsa(root.getChildText("risorsa"));
        }
        Element datiParziali = root.getChild("datiParziali");
        Element datiDB = datiParziali.getChild("datiDB");
        Element datiInterattivi = datiParziali.getChild("datiInterattivi");
        parametri.setDatiDB(leggiDati(datiDB));
        parametri.setDatiInterattivi(leggiDati(datiInterattivi));
        return parametri;
    }

    private static List<DatoPrimitivo> leggiDati(Element elementDati) {
        List<DatoPrimitivo> dati = new ArrayList<DatoPrimitivo>();
        if (elementDati != null) {
            List<Element> datiPrimitivi = elementDati.getChildren("dato");
            for (Element element : datiPrimitivi) {
                DatoPrimitivo datoPrimitivo = new DatoPrimitivo();
                datoPrimitivo.setNome(element.getAttributeValue("nome"));
                datoPrimitivo.setValore(element.getText());
                dati.add(datoPrimitivo);
            }
        }
        return dati;
    }

    public static Document creaDocumentRisposta(RispostaEseguiIstanza risposta) {
        Element rootElement = new Element("rispostaIstanza");
        Document rispostaIstanza = new Document(rootElement);
        if (risposta.isErrore()) {
            Element elementErrore = new Element("errore");
            Element messaggioErrore = new Element("messaggioErrore");
            messaggioErrore.setText(risposta.getMessaggioErrore());
            elementErrore.addContent(messaggioErrore);
            rootElement.addContent(elementErrore);
        } else {
            Element elementSuccesso = new Element("successo");
            if (!risposta.getTipoMessaggio().isEmpty()) {
                elementSuccesso.setAttribute("tipo", risposta.getTipoMessaggio());
            }
            Element idMessaggio = new Element("idMessaggioRichiesta");
            idMessaggio.setText(risposta.getIdMessaggio());
            elementSuccesso.addContent(idMessaggio);

            if (risposta.getIdMessaggioRisposta() != null && !risposta.getIdMessaggioRisposta().isEmpty()) {
                Element idMessaggioRisposta = new Element("idMessaggioRisposta");
                idMessaggioRisposta.setText(risposta.getIdMessaggioRisposta());
                elementSuccesso.addContent(idMessaggioRisposta);
            }

            if (risposta.getMessaggio() != null && !risposta.getMessaggio().isEmpty()) {
                Element elementMessaggioSuccesso = new Element("messaggio");
                elementMessaggioSuccesso.setText(risposta.getMessaggio());
                elementSuccesso.addContent(elementMessaggioSuccesso);
            }
            rootElement.addContent(elementSuccesso);
        }
        return rispostaIstanza;
    }
}
