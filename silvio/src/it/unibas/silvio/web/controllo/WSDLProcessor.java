package it.unibas.silvio.web.controllo;

import com.ibm.wsdl.PartImpl;
import com.ibm.wsdl.xml.WSDLReaderImpl;
import it.unibas.silvio.modello.AccordoDiCollaborazione;
import it.unibas.silvio.modello.Message;
import it.unibas.silvio.modello.Operation;
import it.unibas.silvio.modello.PortType;
import it.unibas.silvio.persistenza.SilvioException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.wsdl.Definition;
import javax.wsdl.OperationType;
import javax.wsdl.WSDLException;
import javax.wsdl.xml.WSDLReader;
import javax.xml.namespace.QName;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class WSDLProcessor {

    private static Log logger = LogFactory.getLog(WSDLProcessor.class);

    public static void elabora(AccordoDiCollaborazione accordo, String cartellaFiles) throws SilvioException {
        if (accordo.getWSDLErogatore() == null) {
            String errore = "Impossibile elaborare l'accordo di collaborazione. E' necessario almeno il WSDL dell'erogatore";
            logger.warn(errore);
            throw new SilvioException(errore);
        }
        WSDLReader reader = new WSDLReaderImpl();
        boolean asincrono = accordo.getWSDLFruitore() != null;
        try {
            Definition definitionErogatore = reader.readWSDL(cartellaFiles + accordo.getWSDLErogatore());
            creaListaPortType(definitionErogatore, PortType.EROGATORE, accordo, asincrono);
            if (asincrono) {
                Definition definitionFruitore = reader.readWSDL(cartellaFiles + accordo.getWSDLFruitore());
                creaListaPortType(definitionFruitore, PortType.FRUITORE, accordo, asincrono);
            }
        } catch (WSDLException ex) {
            String errore = "Impossibile elaborare i wsdl dell'accordo di collaborazione. " + ex;
            logger.warn(errore);
            throw new SilvioException(errore);
        }
    }

    @SuppressWarnings("unchecked")
    private static void creaListaPortType(Definition definition, String ruolo, AccordoDiCollaborazione accordo, boolean asincrono) {
        Map mappaPT = definition.getPortTypes();
        Set setPT = mappaPT.keySet();
        if (setPT.size() == 0) {
            logger.info("Non è stato trovato alcun PortType");
        }
        for (Iterator it = setPT.iterator(); it.hasNext();) {
            QName qnamePT = (QName) it.next();
            javax.wsdl.PortType pt = definition.getPortType(qnamePT);
            logger.info("Trovato nuovo PT " + ruolo + " : " + qnamePT.getLocalPart());
            PortType portType = new PortType();
            portType.setNome(qnamePT.getLocalPart());
            portType.setRuolo(ruolo);
            portType.setAccordoDiCollaborazione(accordo);
            List<Operation> listaOperations = creaListaOperations(pt.getOperations(), portType, accordo, asincrono);
            portType.setListaOperation(listaOperations);
            accordo.getListaPortType().add(portType);
        }
    }

    private static List<Operation> creaListaOperations(List<javax.wsdl.Operation> listaOperations, PortType portType, AccordoDiCollaborazione accordo, boolean asincrono) {
        List<Operation> nuovaListaOperation = new ArrayList<Operation>();
        for (javax.wsdl.Operation op : listaOperations) {
            logger.info("STYLE: " + op.getStyle());
            Operation operation = new Operation();
            operation.setNome(op.getName());
            operation.setPortType(portType);

            String profilo = accordo.getProfiloOperation().get(operation.getNome().toLowerCase());
            if (profilo != null) {
                operation.setProfiloDiCollaborazione(profilo);
            } else if (asincrono) {
                operation.setProfiloDiCollaborazione(Operation.ASINCRONO);
             } else {
                if (op.getStyle().equals(OperationType.ONE_WAY)) {
                    operation.setProfiloDiCollaborazione(Operation.ONE_WAY);
                } else if (op.getStyle().equals(OperationType.REQUEST_RESPONSE)) {
                    operation.setProfiloDiCollaborazione(Operation.SINCRONO);
                }
            }
            nuovaListaOperation.add(operation);
            creaListaMessage(op, operation);
        }
        return nuovaListaOperation;
    }

    private static void creaListaMessage(javax.wsdl.Operation op, Operation operation) {
        javax.wsdl.Message wsdlMessageIn = null;
        javax.wsdl.Message wsdlMessageOut = null;
        if (op.getInput() != null) {
            wsdlMessageIn = op.getInput().getMessage();
        }
        if (op.getOutput() != null) {
            wsdlMessageOut = op.getOutput().getMessage();
        }
        Map mappaFault = op.getFaults();

        if (wsdlMessageIn != null) {
            Message m = new Message();
            m.setNome(wsdlMessageIn.getQName().getLocalPart());
            m.setNamespace(wsdlMessageIn.getQName().getNamespaceURI());
            m.setOperation(operation);
            m.setTypes(extractType(wsdlMessageIn));
            operation.setMessageIn(m);

        }

        if (wsdlMessageOut != null) {
            Message m = new Message();
            m.setNome(wsdlMessageOut.getQName().getLocalPart());
            m.setNamespace(wsdlMessageOut.getQName().getNamespaceURI());
            m.setOperation(operation);
            m.setTypes(extractType(wsdlMessageOut));
            operation.setMessageOut(m);
        }

        if (mappaFault.size() > 0) {
            Message m = new Message();
            com.ibm.wsdl.FaultImpl messagesFault = (com.ibm.wsdl.FaultImpl) mappaFault.get(mappaFault.keySet().iterator().next());
            javax.wsdl.Message wsdlMessageFault = messagesFault.getMessage();
            m.setNome(wsdlMessageFault.getQName().getLocalPart());
            m.setNamespace(wsdlMessageFault.getQName().getNamespaceURI());
            m.setOperation(operation);
            operation.setMessageFault(m);
        }
    }

    private static String extractType(javax.wsdl.Message wsdlMessageIn) {
        String type = "";
        Map<String, PartImpl> mappaPart = wsdlMessageIn.getParts();
        Set<String> set = mappaPart.keySet();
        for (String chiave : set) {
            PartImpl impl = mappaPart.get(chiave);
            if (impl != null && impl.getElementName() != null && impl.getElementName().getLocalPart() != null) {
                type = impl.getElementName().getLocalPart();
            }
        }
        return type;
    }
}
