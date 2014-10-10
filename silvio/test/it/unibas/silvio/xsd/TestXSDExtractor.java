package it.unibas.silvio.xsd;

import it.unibas.silvio.modello.Dati;
import it.unibas.silvio.xml.WSDLUtil;
import it.unibas.silvio.xml.XSDParser;
import it.unibas.silvio.xml.XSDUtil;
import it.unibas.silvio.xml.XmlException;
import it.unibas.silvio.xml.XmlJDomUtil;
import it.unibas.silvio.xml.XmlUtil;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;

public class TestXSDExtractor extends TestCase {

    private Log logger = LogFactory.getLog(this.getClass());

    public void testXSDExtract() {
        if (true) {
            return;
        }
        /* PER ESEGUIRE QUESTO TEST BISOGNA AVER IL FILE C:\\tmp\\testxsd\\Types2.2.xsd*/
        try {
            String fileType = "C:\\tmp\\testxsd\\Types2.2.xsd";
            Document schemaDocument = XmlJDomUtil.caricaXML(fileType, false);
            XSDParser.eliminaNamespace(schemaDocument);
            XmlJDomUtil.salvaXML("c:\\tmp\\testxsd\\typesripulito.xsd", schemaDocument);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Impossibile elaborare il file XSD estratto");
            Assert.fail("Impossibile elaborare il file XSD estratto");
        }
    }

    public void testXMLPulisciNS() {
        if (true) {
            return;
        }
        /* PER ESEGUIRE QUESTO TEST BISOGNA AVER IL FILE c:\\tmp\\testxsd\\Types2.2.xsd*/
        try {
            String fileType = "C:\\tmp\\testxsd\\Types2.2.xsd";
            Document schemaDocument = XmlJDomUtil.caricaXML(fileType, false);
            XSDParser.eliminaNamespace(schemaDocument);
            XmlJDomUtil.salvaXML("c:\\tmp\\testxsd\\typesripulito.xsd", schemaDocument);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Impossibile elaborare il file XSD estratto");
            Assert.fail("Impossibile elaborare il file XSD estratto");
        }
    }

    public void testEstraiNamespace() {
        String prefisso;
        prefisso = XSDParser.estraiPrefisso("xmlns:NS");
        Assert.assertEquals("NS", prefisso);
        prefisso = XSDParser.estraiPrefisso("xmlns:NS");
        Assert.assertEquals("NS", prefisso);
        prefisso = XSDParser.estraiPrefisso("xmlns");
        Assert.assertNull(prefisso);
    }

    public void testValida() {
        if (true) {
            return;
        }
        /* PER ESEGUIRE QUESTO TEST BISOGNA AVER IL FILE c:\\tmp\\idAss\\logicoErogatore.wsdl*/
        try {
            String stringUrl = "file:c:\\tmp\\idAss\\logicoErogatore.wsdl";
            org.w3c.dom.Element docSchema = WSDLUtil.estraiDocSchema(stringUrl, "xxx:richiesta_RichiestaRispostaAsincrona_SegnalazioneRicovero");
            String stringXSD = XmlUtil.stampaDocument(docSchema);
            ByteArrayInputStream isXSD = new ByteArrayInputStream(stringXSD.getBytes());
            XmlJDomUtil.caricaXML(new FileInputStream("c:\\tmp\\xsd\\exSegnalazioneRicovero.xml"), true, isXSD);
        } catch (XmlException ex) {
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Impossibile caricare il WSDL e il suo XSD" + ex);
            Assert.fail("Impossibile caricare il WSDL e il suo XSD" + ex);
        }
    }

    public void testExtractXSDfromWSDL() {
        if (true) {
            return;
        }
        /* PER ESEGUIRE QUESTO TEST BISOGNA AVER IL FILE c:\\tmp\\idAss\\logicoErogatore.wsdl*/
        try {
            String stringUrl = "file:c:\\tmp\\idAss\\logicoErogatore.wsdl";
//            org.w3c.dom.Element docSchema = WSDLUtil.estraiDocSchema(stringUrl, "richiesta_RichiestaRispostaSincrona_IdentificazioneAssistito");
            org.w3c.dom.Element docSchema = WSDLUtil.estraiDocSchema(stringUrl, null);
            XmlUtil.salvaDocument(docSchema, "c:\\tmp\\xsd\\IdAssistito.xsd");
            XmlUtil.salvaDocument(docSchema, "c:\\tmp\\xsd\\schemaRichiesta.xsd");

            Document docXSDRichiesta = XmlJDomUtil.caricaXML("c:\\tmp\\xsd\\IdAssistito.xsd", false);
            Document documentXSD = XSDUtil.datiAndRichiestaToXSD(new Dati(), docXSDRichiesta,"richiesta_RichiestaRispostaSincrona_IdentificazioneAssistito","schemaRichiesta.xsd");
            XmlJDomUtil.salvaXML("c:\\tmp\\xsd\\IdAssistitoDatiAndRichiestaToXSD.xsd", documentXSD);
        } catch (Exception ex) {
            logger.error("Impossibile caricare il WSDL e il suo XSD" + ex);
            Assert.fail("Impossibile caricare il WSDL e il suo XSD" + ex);
        }
    }

    public void testExtractXSDfromWSDL4() {
        if (true) {
            return;
        }
        /* PER ESEGUIRE QUESTO TEST BISOGNA AVER IL FILE c:\\tmp\\idAss\\logicoErogatore2.wsdl*/
        try {
            String stringUrl = "file:c:\\tmp\\idAss\\logicoErogatore2.wsdl";
            org.w3c.dom.Element docSchema = WSDLUtil.estraiDocSchema(stringUrl, "richiesta_RichiestaRispostaSincrona_IdentificazioneAssistito");
            XmlUtil.salvaDocument(docSchema, "c:\\tmp\\xsd\\IdAssistito2.xsd");
        } catch (Exception ex) {
            logger.error("Impossibile caricare il WSDL e il suo XSD" + ex);
            Assert.fail("Impossibile caricare il WSDL e il suo XSD" + ex);
        }
    }

    public void testExtractXSDfromWSDL2() {
        if (true) {
            return;
        }
        /* PER ESEGUIRE QUESTO TEST BISOGNA AVER IL FILE c:\\tmp\\qualificazione\\QualificazionePDD_ErogatoreLogico.wsdl*/
        try {
            String stringUrl = "file:c:\\tmp\\qualificazione\\QualificazionePDD_ErogatoreLogico.wsdl";
            org.w3c.dom.Element docSchema = WSDLUtil.estraiDocSchema(stringUrl, "richiesta_RichiestaRispostaSincrona_start");
            XmlUtil.salvaDocument(docSchema, "c:\\tmp\\xsd\\qualificazione.xsd");
        } catch (Exception ex) {
            logger.error("Impossibile caricare il WSDL e il suo XSD" + ex);
            Assert.fail("Impossibile caricare il WSDL e il suo XSD" + ex);
        }
    }

    public void testExtractXSDfromWSDL3() {
        if (true) {
            return;
        }
        /* PER ESEGUIRE QUESTO TEST BISOGNA AVER IL FILE c:\\tmp\\version\\Version.xml*/
        try {
            String stringUrl = "file:c:\\tmp\\version\\Version.xml";
            org.w3c.dom.Element docSchema = WSDLUtil.estraiDocSchema(stringUrl, null);
            XmlUtil.salvaDocument(docSchema, "c:\\tmp\\xsd\\version.xsd");
        } catch (Exception ex) {
            logger.error("Impossibile caricare il WSDL e il suo XSD" + ex);
            Assert.fail("Impossibile caricare il WSDL e il suo XSD" + ex);
        }
    }

    public void testExtractXSDfromWSDLAP3() {
        if (true) {
            return;
        }
        /* PER ESEGUIRE QUESTO TEST BISOGNA AVER IL FILE c:\\tmp\\ap3\\WS_ErogatoreLogico.wsdl*/
        try {
            String stringUrl = "file:c:\\tmp\\ap3\\WS_ErogatoreLogico.wsdl";
            org.w3c.dom.Element docSchema = WSDLUtil.estraiDocSchema(stringUrl, null);
            String schema = XmlUtil.stampaDocument(docSchema);
            
            XmlUtil.salvaDocument(docSchema, "c:\\tmp\\xsd\\AP3-schemaRichiesta.xsd");
            Document docXSDRichiesta = XmlJDomUtil.caricaXML(schema);
            Document documentXSD = XSDUtil.datiAndRichiestaToXSD(new Dati(), docXSDRichiesta,"variazione_ipa","AP3-schemaRichiesta.xsd");

            XmlJDomUtil.salvaXML("c:\\tmp\\xsd\\ap3.xsd", documentXSD);
        } catch (Exception ex) {
            logger.error("Impossibile caricare il WSDL e il suo XSD" + ex);
            Assert.fail("Impossibile caricare il WSDL e il suo XSD" + ex);
        }
    }

    public void testExtractXSDfromWSDLAP4() {
//        if (true) {
//            return;
//        }
        /* PER ESEGUIRE QUESTO TEST BISOGNA AVER IL FILE c:\\tmp\\ap4\\WS_ErogatoreLogico.wsdl*/
        try {
            String stringUrl = "file:c:\\tmp\\ap4\\WS_ErogatoreLogico.wsdl";
            org.w3c.dom.Element docSchema = WSDLUtil.estraiDocSchema(stringUrl, null);
            String schema = XmlUtil.stampaDocument(docSchema);

            Document docXSDRichiesta = XmlJDomUtil.caricaXML(schema);
            XmlJDomUtil.salvaXML("c:\\tmp\\xsd\\ap4.xsd", docXSDRichiesta);
            
            Document documentDatiAndRichiesta = XSDUtil.datiAndRichiestaToXSD(new Dati(), docXSDRichiesta,"richiesta_RichiestaRispostaSincrona_SchedaAnaProf","ap4.xsd");
            XmlJDomUtil.salvaXML("c:\\tmp\\xsd\\ap4DatiAndRichiesta.xsd", documentDatiAndRichiesta);
        } catch (Exception ex) {
            logger.error("Impossibile caricare il WSDL e il suo XSD" + ex);
            Assert.fail("Impossibile caricare il WSDL e il suo XSD" + ex);
        }
    }
}
