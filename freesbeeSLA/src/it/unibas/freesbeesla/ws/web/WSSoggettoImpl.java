package it.unibas.freesbeesla.ws.web;

import it.unibas.freesbeesla.ws.web.stub.ClientRegistroServizi;
import it.unibas.freesbeesla.tracciatura.modello.SoggettoSLA;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.SoggettoRSRisposta;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAllIdSoggettiSPCoop;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAllIdSoggettiSPCoopResponse;
import it.unibas.freesbeesla.tracciatura.ws.server.SOAPFault;
import it.unibas.freesbeesla.ws.web.persistenza.soap.DAOConfigurazione;
import it.unibas.freesbeesla.ws.web.persistenza.torque.DAOSoggettoSLATorque;
import it.unibas.freesbeesla.ws.web.utilita.UtilitaWS;
import it.unibas.icar.freesbee.modello.Soggetto;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.AccordoServizio;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.ServizioRSRisposta;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.SoggettoRS;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAccordoServizio;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAccordoServizioResponse;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.IWSRegistroServizi;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetSoggettoSPCoop;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetSoggettoSPCoopResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@javax.jws.WebService(serviceName = "ServiceSoggetto", portName = "ServiceSoggetto", targetNamespace = "http://web.ws.freesbeesla.unibas.it/", endpointInterface = "it.unibas.freesbeesla.ws.web.IWSSoggetto")
public class WSSoggettoImpl implements IWSSoggetto {

    private Log logger = LogFactory.getLog(this.getClass());
    private DAOSoggettoSLATorque daoSoggetto = new DAOSoggettoSLATorque();

    //Ritorna i soggetti presenti sul nica ma non presenti nel db di freesbeesla e afferenti al nica (cioè non quelli ottentui dalla sincronizzazione)
    public List<Soggetto> getSoggettiNica() throws SOAPFault {
        try {
            logger.info("Operazione richiesta: getSoggettiNica");
            DAOConfigurazione daoConfigurazione = new DAOConfigurazione();
            String url = daoConfigurazione.getDatiConfigurazione().getIndirizzoRegistroServizi();

            ClientRegistroServizi clientRegistroServizi = new ClientRegistroServizi(url);
            IWSRegistroServizi port = clientRegistroServizi.getPortRegisrtoServizi();

            GetAllIdSoggettiSPCoop richiesta = new GetAllIdSoggettiSPCoop();
            GetAllIdSoggettiSPCoopResponse response = port.getAllIdSoggettiSPCoop(richiesta, clientRegistroServizi.getIndirizzoRichiesta());


            List<Soggetto> lista = new ArrayList<Soggetto>();
            List<SoggettoRSRisposta> list = response.getReturn();


            List<SoggettoSLA> listaSoggetti = daoSoggetto.selectAll();

            Map idInf2 = new HashMap();
            for (SoggettoSLA sog : listaSoggetti) {
                idInf2.put(sog.getId(), sog.getId());
            }

            for (SoggettoRSRisposta sogr : list) {
                if (!sogr.getDescrizione().contains("[sync]")) {
                    //E' necessario per recuperare il codie del soggetto
                    GetSoggettoSPCoop richiestaSoggetto = new GetSoggettoSPCoop();
//                    SoggettoRS soggettoRS = new SoggettoRS(sogr.getTipo(), sogr.getNome()); // TODO [Michele] cambiato
                    SoggettoRS soggettoRS = new SoggettoRS();
                    soggettoRS.setTipo(sogr.getTipo());
                    soggettoRS.setNome(sogr.getNome());
                    richiestaSoggetto.setSoggetto(soggettoRS);
                    GetSoggettoSPCoopResponse rispostaSoggetto = port.getSoggettoSPCoop(richiestaSoggetto, clientRegistroServizi.getIndirizzoRichiesta());
                    SoggettoRSRisposta sogettoRSR = rispostaSoggetto.getReturn();
                    if (idInf2.get(sogettoRSR.getId()) == null) {
//                        lista.add(sogettoRSR.trasformaSoggetto()); // TODO [Michele] cambiato
                        lista.add(UtilitaWS.trasformaSoggettoRS(soggettoRS));
                    }
                }
            }

            logger.debug("Dimensione listaSoggettiNica " + lista.size());

            return lista;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new SOAPFault(ex.getMessage());
        }
    }

    public List<Soggetto> getSoggettiInf2() throws SOAPFault {
        try {
            logger.info("Operazione richiesta: getSoggettiInf2");

            List<SoggettoSLA> lista = daoSoggetto.selectAll();
            List<Soggetto> listaSoggetto = new ArrayList<Soggetto>();

            for (SoggettoSLA sog : lista) {
                Soggetto soggetto = new Soggetto();
                soggetto.setId(sog.getId());
                soggetto.setNome(sog.getNomeSoggetto());
                listaSoggetto.add(soggetto);
            }


            return listaSoggetto;

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new SOAPFault(ex.getMessage());
        }
    }

    public List<Soggetto> getSoggettiSLA(String nomeAccordo, String servizio, String tipo) throws SOAPFault {
        try {
            logger.info("Operazione richiesta: getSoggettiSLA");
            logger.info("Accordo di Serivzio: " + nomeAccordo);
            logger.info("Serivzio: " + tipo + " + " + servizio);
            List<Soggetto> lista = new ArrayList<Soggetto>();

            DAOConfigurazione daoConfigurazione = new DAOConfigurazione();
            String url = daoConfigurazione.getDatiConfigurazione().getIndirizzoRegistroServizi();

            ClientRegistroServizi clientRegistroServizi = new ClientRegistroServizi(url);
            IWSRegistroServizi port = clientRegistroServizi.getPortRegisrtoServizi();

            GetAccordoServizio richiesta = new GetAccordoServizio();
            AccordoServizio accordoCercato = new AccordoServizio();
            accordoCercato.setNome(nomeAccordo);

            richiesta.setAccordoServizio(accordoCercato);

            GetAccordoServizioResponse response = port.getAccordoServizio(richiesta, clientRegistroServizi.getIndirizzoRichiesta());
            List<ServizioRSRisposta> listaServizi = response.getReturn().getServizi();


            logger.debug("Dimensione listaServizi " + listaServizi.size());
            for (ServizioRSRisposta ser : listaServizi) {
                logger.info("servizio: " + ser.getNome());
                logger.info("tipo: " + ser.getTipo());
                if (ser.getNome().equals(servizio) && ser.getTipo().equals(tipo)) {
//                lista.add(ser.getErogatore().trasformaSoggetto()); // TODO [Michele] cambiato
                    lista.add(UtilitaWS.trasformaSoggettoRS(ser.getErogatore()));
                }
            }

            logger.debug("Dimensione listaSoggettiSLA " + lista.size());

            return lista;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new SOAPFault(ex.getMessage());
        }
    }

    public void addSoggettoInf2(Soggetto richiesta) throws SOAPFault {
        try {
            logger.info("Operazione richiesta: addSoggettoInf2");
            SoggettoSLA soggetto = new SoggettoSLA();
            soggetto.setId(richiesta.getId());
            soggetto.setNomeSoggetto(richiesta.getNome());
            daoSoggetto.doInsert(soggetto);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new SOAPFault(ex.getMessage());
        }
    }

    public void removeSoggettoInf2(Soggetto richiesta) throws SOAPFault {
        try {
            logger.info("Operazione richiesta: removeSoggettoInf2");
            SoggettoSLA soggetto = new SoggettoSLA();
            soggetto.setId(richiesta.getId());
            soggetto.setNomeSoggetto(richiesta.getNome());
            daoSoggetto.doDelete(soggetto);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new SOAPFault(ex.getMessage());
        }
    }
}
