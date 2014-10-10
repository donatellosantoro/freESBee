package it.unibas.freesbeesp.test;

import java.io.InputStream;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.apache.cxf.helpers.XMLUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TestDiscoveryService extends TestCase {

    private org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog(this.getClass());

    public void testExtractIdP() {
        try {
            InputStream is = TestDiscoveryService.class.getResourceAsStream("/discoveryService.xml");
            assertNotNull(is);
            org.w3c.dom.Document docDS = XMLUtils.parse(is);
            NodeList nodeListSelect = docDS.getElementsByTagName("select");
            if (nodeListSelect != null) {
                for (int i = 0; i < nodeListSelect.getLength(); i++) {
                    Node nodeSelect = nodeListSelect.item(i);
                    Node attributoId = nodeSelect.getAttributes().getNamedItem("id");
                    if (attributoId != null && attributoId.getNodeValue().equalsIgnoreCase("originIdp")) {
                        NodeList nodeListOptions = nodeSelect.getChildNodes();
                        for (int j = 0; j < nodeListOptions.getLength(); j++) {
                            Node nodeOption = nodeListOptions.item(j);
                            String nodeName = nodeOption.getNodeName();
                            String nodeValue = nodeOption.getTextContent();
                            if (nodeName.equalsIgnoreCase("option") && nodeOption.getAttributes() != null) {
                                Node attributeValue = nodeOption.getAttributes().getNamedItem("value");
                                if (attributeValue != null && nodeValue != null) {

                                    logger.info("@nodeValue " + nodeValue.trim());
                                    logger.info("@attributeValue " + attributeValue.getNodeValue());
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Assert.fail(ex.getLocalizedMessage());
        }
    }
}
