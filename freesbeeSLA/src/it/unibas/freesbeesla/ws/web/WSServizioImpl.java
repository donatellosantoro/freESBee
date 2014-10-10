package it.unibas.freesbeesla.ws.web;

import it.unibas.freesbee.client.cxf.sistematracciatura.ServiceTermStateType;
import it.unibas.freesbee.monitoraggio.calcolo.core.GuaranteeTermsValidator;
import it.unibas.freesbee.monitoraggio.dbwsa.dbutilita.DataSource;
import it.unibas.freesbee.monitoraggio.exception.daoexception.DAOException;
import it.unibas.freesbee.monitoraggio.exception.inf2exception.INF2Exception;
import it.unibas.freesbee.monitoraggio.libreria.Monitoraggio;
import it.unibas.freesbeesla.ws.web.stub.ClientRegistroServizi;
import it.unibas.freesbeesla.ConfigurazioneDataSource;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAllIdServiziSPCoop;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAllIdServiziSPCoopResponse;
import it.unibas.freesbeesla.tracciatura.modello.Service;
import it.unibas.freesbeesla.tracciatura.modello.SoggettoSLA;
import it.unibas.freesbeesla.tracciatura.ws.server.SOAPFault;
import it.unibas.freesbeesla.ws.web.persistenza.soap.DAOConfigurazione;
import it.unibas.freesbeesla.ws.web.persistenza.soap.ValidatoreWsag;
import it.unibas.freesbeesla.ws.web.persistenza.torque.DAOServizioTorque;
import it.unibas.freesbeesla.ws.web.persistenza.torque.DAOSoggettoSLATorque;
import it.unibas.freesbeesla.ws.web.utilita.UtilitaWS;
import it.unibas.icar.freesbee.modello.Servizio;
import it.unibas.icar.freesbee.modello.Soggetto;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.ServizioRS;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.SoggettoRSRisposta;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAllIdServiziSPCoopCorrelati;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetAllIdServiziSPCoopCorrelatiResponse;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetFruitoriServizioSpcoop;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetFruitoriServizioSpcoopResponse;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.IWSRegistroServizi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.activation.DataHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@javax.jws.WebService(serviceName = "ServiceServizio", portName = "ServiceServizio", targetNamespace = "http://web.ws.freesbeesla.unibas.it/", endpointInterface = "it.unibas.freesbeesla.ws.web.IWSServizio")
public class WSServizioImpl implements IWSServizio {

    private Log logger = LogFactory.getLog(this.getClass());
    private DAOServizioTorque daoServizio = new DAOServizioTorque();
    private DAOSoggettoSLATorque daoSoggetto = new DAOSoggettoSLATorque();

    //Ritorna i servizi monitorati da freesbeesla il cui erogatore appartiene al nica indifferentemente dal fruitore
    public List<Servizio> getServiziInf2Erogati() throws SOAPFault {
        try {
            logger.info("Operazione richiesta: getServiziInf2Erogati");
            List<Service> lista = daoServizio.selectAll();
            List<Servizio> listaServizi = new ArrayList<Servizio>();

            for (Service serv : lista) {
                //Se != null il soggetto erogatore si trova nel db di inf2 e quindi appartiene al nica
                if (daoSoggetto.selectByNomeSoggetto(serv.getInf2IdResponder()) != null) {
                    Servizio servizio = new Servizio();
                    servizio.setNome(serv.getInf2IdService());
                    Soggetto erogatore = new Soggetto();
                    erogatore.setNome(serv.getInf2IdResponder());
                    servizio.setErogatore(erogatore);
                    Soggetto fruitore = new Soggetto();
                    fruitore.setNome(serv.getInf2IdInitiator());
                    servizio.getFruitori().add(fruitore);
//                    servizio.setActive(serv.getInf2Active()); // TODO [Michele] verificare
////                    servizio.setStato(serv.getInf2IdState());
////                    servizio.setContatoreRichieste(serv.getInf2CountPendingRequest());

                    listaServizi.add(servizio);
                }
            }
            logger.info("Operazione richiesta: getServiziInf2Erogati " + listaServizi.size());
            return listaServizi;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new SOAPFault(ex.getMessage());
        }
    }

    //Ritorna i servizi monitorati da freesbeesla il cui fruitore appartiene al nica ma l'erogatore no
    public List<Servizio> getServiziInf2Fruiti() throws SOAPFault {
        try {
            logger.info("Operazione richiesta: getServiziInf2");
            List<Service> lista = daoServizio.selectAll();
            List<Servizio> listaServizi = new ArrayList<Servizio>();

            for (Service serv : lista) {
                //Se != null il soggetto fruitore si trova nel db di inf2 e quindi appartiene al nica
                if (daoSoggetto.selectByNomeSoggetto(serv.getInf2IdInitiator()) != null && !serv.getInf2IdInitiator().equals(serv.getInf2IdResponder())) {
                    //Escludo anche i servizi in cui l'eorgatore appartiena al nica perchè il servizio è
                    //monitorabile nella sezione servizi erogati
                    if (daoSoggetto.selectByNomeSoggetto(serv.getInf2IdResponder()) == null) {
                        Servizio servizio = new Servizio();
                        servizio.setNome(serv.getInf2IdService());
                        Soggetto erogatore = new Soggetto();
                        erogatore.setNome(serv.getInf2IdResponder());
                        servizio.setErogatore(erogatore);
                        Soggetto fruitore = new Soggetto();
                        fruitore.setNome(serv.getInf2IdInitiator());
                        servizio.getFruitori().add(fruitore);
//                        servizio.setActive(serv.getInf2Active()); // TODO [Michele] verificare
////                        servizio.setStato(serv.getInf2IdState());
////                        servizio.setContatoreRichieste(serv.getInf2CountPendingRequest());

                        listaServizi.add(servizio);
                    }
                }
            }

            return listaServizi;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new SOAPFault(ex.getMessage());
        }
    }

    //Restituisce i servizi erogati dal nomeErogatore
    private List<Servizio> getServiziErogatore(String nomeErogatore) throws SOAPFault {
        try {
            logger.info("Operazione richiesta: getServiziErogatore");
            DAOConfigurazione daoConfigurazione = new DAOConfigurazione();
            String url = daoConfigurazione.getDatiConfigurazione().getIndirizzoRegistroServizi();

            ClientRegistroServizi clientRegistroServizi = new ClientRegistroServizi(url);

            IWSRegistroServizi port = clientRegistroServizi.getPortRegisrtoServizi();


            List<Servizio> listaServizi = new ArrayList<Servizio>();
            List<ServizioRS> listaRS = new ArrayList<ServizioRS>();
            GetAllIdServiziSPCoop getAllIdServiziSPCoop = new GetAllIdServiziSPCoop();
            GetAllIdServiziSPCoopResponse responseServizi = port.getAllIdServiziSPCoop(getAllIdServiziSPCoop, clientRegistroServizi.getIndirizzoRichiesta());

            listaRS = responseServizi.getReturn();


            for (ServizioRS ser : listaRS) {

                if (ser.getSoggettoErogatore().getNome().equals(nomeErogatore)) {
                    logger.info("Nome Servizio: " + ser.getNome());
                    logger.info("Nome Erogatore: " + ser.getSoggettoErogatore().getNome());
//                    Servizio servizio = ser.trasformaServizio(); // TODO [Michele] cambiato
                    Servizio servizio = UtilitaWS.trasformaServizioRS(ser);
//                    servizio.setActive(new Long(0)); // TODO [Michele] verificare


                    GetFruitoriServizioSpcoop getFruitoriServizioSpcoop = new GetFruitoriServizioSpcoop();
                    getFruitoriServizioSpcoop.setServizio(ser);
                    GetFruitoriServizioSpcoopResponse getFruitoriServizioSpcoopResponse = port.getFruitoriServizioSpcoop(getFruitoriServizioSpcoop, clientRegistroServizi.getIndirizzoRichiesta());

                    for (int i = 0; i < getFruitoriServizioSpcoopResponse.getReturn().size(); i++) {
//                        servizio.getFruitori().add(getFruitoriServizioSpcoopResponse.getReturn().get(i).trasformaSoggetto()); // TODO [Michele] cambiato
                        Soggetto soggettoFruitore = UtilitaWS.trasformaSoggettoRSRisposta(getFruitoriServizioSpcoopResponse.getReturn().get(i));
                        servizio.getFruitori().add(soggettoFruitore);
                        logger.info("Nome Fruitore: " + servizio.getFruitori().get(i).getNome());
                    }


                    listaServizi.add(servizio);
                }
            }


            GetAllIdServiziSPCoopCorrelati getAllIdServiziSPCoopCorrelati = new GetAllIdServiziSPCoopCorrelati();
            GetAllIdServiziSPCoopCorrelatiResponse responseCorrelati = port.getAllIdServiziSPCoopCorrelati(getAllIdServiziSPCoopCorrelati, clientRegistroServizi.getIndirizzoRichiesta());
            listaRS = responseCorrelati.getReturn();


            for (ServizioRS ser : listaRS) {

                if (ser.getSoggettoErogatore().getNome().equals(nomeErogatore)) {
                    logger.debug("Nome Servizio Correlato: " + ser.getNome());
                    logger.debug("Nome Erogatore Correlato: " + ser.getSoggettoErogatore().getNome());
//                    Servizio servizio = ser.trasformaServizio(); // TODO [Michele] cambiato
                    Servizio servizio = UtilitaWS.trasformaServizioRS(ser);
//                    servizio.setActive(new Long(0)); // TODO [Michele] verificare
                    GetFruitoriServizioSpcoop getFruitoriServizioSpcoop = new GetFruitoriServizioSpcoop();
                    getFruitoriServizioSpcoop.setServizio(ser);
                    GetFruitoriServizioSpcoopResponse getFruitoriServizioSpcoopResponse = port.getFruitoriServizioSpcoop(getFruitoriServizioSpcoop, clientRegistroServizi.getIndirizzoRichiesta());
                    for (int i = 0; i < getFruitoriServizioSpcoopResponse.getReturn().size(); i++) {
//                        servizio.getFruitori().add(getFruitoriServizioSpcoopResponse.getReturn().get(i).trasformaSoggetto()); // TODO [Michele] cambiato
                        Soggetto soggettoFruitore = UtilitaWS.trasformaSoggettoRSRisposta(getFruitoriServizioSpcoopResponse.getReturn().get(i));
                        servizio.getFruitori().add(soggettoFruitore);
                        listaServizi.add(servizio);
                        logger.debug("Nome Fruitore Correlato: " + servizio.getFruitori().get(i).getNome());
                    }
                    listaServizi.add(servizio);
                }
            }



            return listaServizi;
//            return listaRS;

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new SOAPFault(ex.getMessage());
        }
    }

    //Restituisce i servizi erogati dal nomeErogatore
    private List<Servizio> getServiziFruitore(String nomeFruitore) throws SOAPFault {
        try {
            logger.info("Operazione richiesta: getServiziFruitore");
            DAOConfigurazione daoConfigurazione = new DAOConfigurazione();
            String url = daoConfigurazione.getDatiConfigurazione().getIndirizzoRegistroServizi();

            ClientRegistroServizi clientRegistroServizi = new ClientRegistroServizi(url);
            IWSRegistroServizi port = clientRegistroServizi.getPortRegisrtoServizi();

            List<Servizio> listaServizi = new ArrayList<Servizio>();
            List<ServizioRS> listaRS = new ArrayList<ServizioRS>();
            GetAllIdServiziSPCoop getAllIdServiziSPCoop = new GetAllIdServiziSPCoop();
            GetAllIdServiziSPCoopResponse responseServizi = port.getAllIdServiziSPCoop(getAllIdServiziSPCoop, clientRegistroServizi.getIndirizzoRichiesta());

            listaRS = responseServizi.getReturn();


            for (ServizioRS ser : listaRS) {

//                Servizio servizio = ser.trasformaServizio(); // TODO [Michele] cambiato
                Servizio servizio = UtilitaWS.trasformaServizioRS(ser);
//                servizio.setActive(new Long(0)); // TODO [Michele] verificare

                GetFruitoriServizioSpcoop getFruitoriServizioSpcoop = new GetFruitoriServizioSpcoop();
                getFruitoriServizioSpcoop.setServizio(ser);
                GetFruitoriServizioSpcoopResponse getFruitoriServizioSpcoopResponse = port.getFruitoriServizioSpcoop(getFruitoriServizioSpcoop, clientRegistroServizi.getIndirizzoRichiesta());

                for (int i = 0; i < getFruitoriServizioSpcoopResponse.getReturn().size(); i++) {
                    SoggettoRSRisposta soggettoRSFruitore = getFruitoriServizioSpcoopResponse.getReturn().get(i);
                    //non prendo quei servizi in cui erogatore e sfruitore sono lo stesso soggetto 
                    if (soggettoRSFruitore.getNome().equals(nomeFruitore) && !servizio.getErogatore().getNome().equals(nomeFruitore)) {
//                        servizio.getFruitori().add(soggettoFruitore.trasformaSoggetto()); // TODO [Michele] cambiato
//                        servizio.setErogatore(ser.getSoggettoErogatore().trasformaSoggetto()); // TODO [Michele] cambiato
                        Soggetto soggettoFruitore = UtilitaWS.trasformaSoggettoRSRisposta(soggettoRSFruitore);
                        servizio.getFruitori().add(soggettoFruitore);
                        Soggetto soggettoErogatore = UtilitaWS.trasformaSoggettoRS(ser.getSoggettoErogatore());
                        servizio.setErogatore(soggettoErogatore);
                        logger.debug("Nome Servizio: " + ser.getNome());
                        logger.debug("Nome Erogatore: " + ser.getSoggettoErogatore().getNome());
                        logger.debug("Nome Fruitore: " + servizio.getFruitori().get(i).getNome());
                        listaServizi.add(servizio);
                    }
                }
            }



            GetAllIdServiziSPCoopCorrelati getAllIdServiziSPCoopCorrelati = new GetAllIdServiziSPCoopCorrelati();
            GetAllIdServiziSPCoopCorrelatiResponse responseCorrelati = port.getAllIdServiziSPCoopCorrelati(getAllIdServiziSPCoopCorrelati, clientRegistroServizi.getIndirizzoRichiesta());
            listaRS = responseCorrelati.getReturn();


            for (ServizioRS ser : listaRS) {

                Servizio servizio = UtilitaWS.trasformaServizioRS(ser);
//                servizio.setActive(new Long(0)); // TODO [Michele] verificare

                GetFruitoriServizioSpcoop getFruitoriServizioSpcoop = new GetFruitoriServizioSpcoop();
                getFruitoriServizioSpcoop.setServizio(ser);
                GetFruitoriServizioSpcoopResponse getFruitoriServizioSpcoopResponse = port.getFruitoriServizioSpcoop(getFruitoriServizioSpcoop, clientRegistroServizi.getIndirizzoRichiesta());

                for (int i = 0; i < getFruitoriServizioSpcoopResponse.getReturn().size(); i++) {
                    SoggettoRSRisposta soggettoRSFruitore = getFruitoriServizioSpcoopResponse.getReturn().get(i);
                    if (soggettoRSFruitore.getNome().equals(nomeFruitore)) {
//                        servizio.getFruitori().add(soggettoRSFruitore.trasformaSoggetto()); // TODO [Michele] cambiato
//                        servizio.setErogatore(ser.getSoggettoErogatore().trasformaSoggetto()); // TODO [Michele] cambiato
                        Soggetto nuovoSoggettoFruitore = UtilitaWS.trasformaSoggettoRSRisposta(soggettoRSFruitore);
                        servizio.getFruitori().add(nuovoSoggettoFruitore);
                        Soggetto nuovoSoggettoErogatore = UtilitaWS.trasformaSoggettoRS(ser.getSoggettoErogatore());
                        servizio.setErogatore(nuovoSoggettoErogatore);
                        listaServizi.add(servizio);
                        logger.debug("Nome Servizio Correlato: " + ser.getNome());
                        logger.debug("Nome Erogatore Correlato: " + ser.getSoggettoErogatore().getNome());
                        logger.debug("Nome Fruitore Correlato: " + servizio.getFruitori().get(i).getNome());
                        listaServizi.add(servizio);
                    }
                }
            }



            return listaServizi;
//            return listaRS;

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new SOAPFault(ex.getMessage());
        }
    }

    //Ritorna i servizi presenti sul nica erogati dai soggetti presenti nel db di freesbeesla
    public List<Servizio> getServiziNicaNonMonitorati() throws SOAPFault {
        try {
            logger.info("Operazione richiesta: getServiziNicaNonMonitorati");
            List<SoggettoSLA> lista = daoSoggetto.selectAll();

            List<Servizio> listaServizi = new ArrayList<Servizio>();
            List<Servizio> listaReturn = new ArrayList<Servizio>();

            for (SoggettoSLA soggetto : lista) {
                List<Servizio> list = getServiziErogatore(soggetto.getNomeSoggetto());
                listaServizi.addAll(list);
                list = getServiziFruitore(soggetto.getNomeSoggetto());
                listaServizi.addAll(list);
            }

            List<Service> listaService = daoServizio.selectAll();
            Map inf2Service = new HashMap();
            for (Service ser : listaService) {
                inf2Service.put(ser.getInf2IdService() + ser.getInf2IdInitiator() + ser.getInf2IdResponder(), ser);
                logger.debug("mappa " + ser.getInf2IdService() + ser.getInf2IdInitiator() + ser.getInf2IdResponder());
            }

            Map<String, Servizio> mappaServizi = new HashMap<String, Servizio>();
            for (Servizio servizio : listaServizi) {
                StringBuilder stringBuilderChiave = new StringBuilder();
                stringBuilderChiave.append(servizio.getNome());
                stringBuilderChiave.append(servizio.getErogatore().getNome());
                for (int i = 0; i < servizio.getFruitori().size(); i++) {
                    stringBuilderChiave.append(servizio.getFruitori().get(i).getNome());
                }
                Service serviceTrovato = (Service) inf2Service.get(stringBuilderChiave.toString());
                Servizio servizioInserito = (Servizio) mappaServizi.get(stringBuilderChiave.toString());

                //Per compatibilità
//                    servizio.setStato(ServiceTermStateType.NOT_READY.value()); // TODO [Michele] verificare

                logger.debug("Chiave: " + stringBuilderChiave.toString());

                if (serviceTrovato == null && servizioInserito == null) {
                    listaReturn.add(servizio);
                    mappaServizi.put(stringBuilderChiave.toString(), servizio);
                }
            }
            logger.debug("Lista Servizi Nica Non Monitorati size: " + listaReturn.size());

            return listaReturn;

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new SOAPFault(ex.getMessage());
        }
    }

    public void updateServizioActive(Servizio servizio) throws SOAPFault {
        try {
            logger.info("Operazione richiesta: updateServizio");
            Service service = new Service();
            service.setInf2IdService(servizio.getNome());
            service.setInf2IdInitiator(servizio.getFruitori().get(0).getNome());
            service.setInf2IdResponder(servizio.getErogatore().getNome());
//            service.setInf2Active(servizio.getActive()); // TODO [Michele] verificare
            daoServizio.doUpdateActive(service);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new SOAPFault(ex.getMessage());
        }
    }

    public void addServizio(Servizio servizio, DataHandler attach) throws SOAPFault {
        try {
            logger.info("Operazione richiesta: addServizio");
            Service service = new Service();
            service.setInf2IdService(servizio.getNome());
            service.setInf2IdInitiator(servizio.getFruitori().get(0).getNome());
            service.setInf2IdResponder(servizio.getErogatore().getNome());
            service.setInf2IdAgreement("agreementId");
            service.setInf2IdState(ServiceTermStateType.READY_IDLE.value());
            if (attach == null) {
                logger.error("Attachment nullo.");
                throw new IllegalArgumentException("Errore. Attachment nullo");
            }
//           InputStream stream = new ByteArrayInputStream(attach);
//            logger.info("*********** " +new String(attach));
//            logger.info("*********** " + new String(attach));
//            try {
//                byte[] sms = new byte[10000];
//                attach.getInputStream().read(sms);
//                logger.info("*************** " + new String(sms));
//            } catch (Exception e) {
//                logger.info("*************** " + e);
//            }
//            InputStream streamRipulito = Ripulitore.ripulisci(attach.getInputStream());
            logger.error("Attachment Content Type:\n " + attach.getContentType());
            String slaObj = null;
            slaObj = ValidatoreWsag.getWSAG(attach.getInputStream());


            if (slaObj != null && !slaObj.equals("")) {
                service.setInf2SlaObject(slaObj);
                daoServizio.doInsert(service);
            } else {
                throw new Exception("Errore");
            }

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new SOAPFault(ex.getMessage());
        }
    }

    public List<String> getParametriSla(Servizio servizio) throws SOAPFault {
        logger.info("Operazione richiesta: getParametriSla");
        List<String> list = new ArrayList<String>();
        Monitoraggio monitoraggio = Monitoraggio.getInstance();
        GuaranteeTermsValidator gtValidator;
        try {

            ConfigurazioneDataSource conf = ConfigurazioneDataSource.getInstance();
            DataSource.configure(conf.getDriver(), conf.getUrl(), conf.getUsername(), conf.getPassword());

            logger.info("Operazione richiesta: getParametriSla");
            //GuaranteeTermsValidator gtVal = new GuaranteeTermsValidator(servizio.getNome(), servizio.getFruitori().get(0).getNome(), servizio.getErogatore().getNome());
            gtValidator = monitoraggio.convalidaSintassiWSAG(servizio.getNome(), servizio.getFruitori().get(0).getNome(), servizio.getErogatore().getNome());
            list = gtValidator.getListaNomiGt();
        } catch (INF2Exception ex) {
            logger.error(ex.getMessage());
            throw new SOAPFault(ex.getMessage());
        } catch (DAOException ex) {
            logger.error(ex.getMessage());
            throw new SOAPFault(ex.getMessage());
        }
        return list;
    }
}
