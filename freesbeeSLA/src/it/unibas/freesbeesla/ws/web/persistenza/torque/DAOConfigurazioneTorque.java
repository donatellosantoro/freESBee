package it.unibas.freesbeesla.ws.web.persistenza.torque;

import it.unibas.freesbeesla.DatiConfigurazione;
import it.unibas.freesbeesla.tracciatura.modello.Configurazione;
import it.unibas.freesbeesla.tracciatura.modello.ConfigurazioneSP;
import it.unibas.freesbeesla.tracciatura.modello.dao.ConfigurazionePeer;
import it.unibas.freesbeesla.tracciatura.modello.dao.ConfigurazioneSPPeer;
import it.unibas.freesbeesla.tracciatura.persistenza.DAOException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.torque.TorqueException;
import org.apache.torque.util.Criteria;

public class DAOConfigurazioneTorque {

    private static Log logger = LogFactory.getLog(DAOConfigurazioneTorque.class);

    public DatiConfigurazione doSelectConfigurazione() throws DAOException {
        Configurazione configurazione = null;
        ConfigurazioneSP configurazioneSP = null;
        DatiConfigurazione datiConfigurazione = new DatiConfigurazione();
        List lista = null;
        List listaSP = null;
        try {
            
            Criteria criteriaConfigurazione = new Criteria();
            Criteria criteriaConfigurazioneSP = new Criteria();
            lista = ConfigurazionePeer.doSelect(criteriaConfigurazione); // SELECT sulla tabella configurazione
            listaSP = ConfigurazioneSPPeer.doSelect(criteriaConfigurazioneSP); // SELECT sulla tabella configurazione_sp
            if (lista.size() != 0 && listaSP.size() != 0) {
                configurazione = (Configurazione) lista.get(0); // recupera la configurazione selezionata
                configurazioneSP = (ConfigurazioneSP) listaSP.get(0);
                datiConfigurazione.setIndirizzoRegistroServizi(configurazione.getIndirizzoRegistroServizi());
                datiConfigurazione.setNomeSLA(configurazione.getNomeSLA());
                datiConfigurazione.setTipoSLA(configurazione.getTipoSLA());
                datiConfigurazione.setNomeServizioMonitoraggioSLA(configurazione.getNomeServizioMonitoraggioSla());
                datiConfigurazione.setAccordoServizioSLA(configurazione.getAccordoServizioSla());
                datiConfigurazione.setTipoServizioMonitoraggioSLA(configurazione.getTipoServizioMonitoraggioSla());
                datiConfigurazione.setPdMonitoraggioSLA(configurazione.getPdMonitoraggioSla());
                datiConfigurazione.setAccordoServizioStato(configurazione.getAccordoServizioStato());
                datiConfigurazione.setNomeServizioMonitoraggioStato(configurazione.getNomeServizioMonitoraggioStato());
                datiConfigurazione.setTipoServizioMonitoraggioStato(configurazione.getTipoServizioMonitoraggioStato());
                datiConfigurazione.setPdMonitoraggioStato(configurazione.getPdMonitoraggioStato());
                datiConfigurazione.setRisorsa(configurazioneSP.getRisorsa());
                datiConfigurazione.setRisorsaPdMonitoraggioSLA(configurazioneSP.getRisorsaPdMonitoraggioSla());
                datiConfigurazione.setRisorsaPdMonitoraggioStato(configurazioneSP.getRisorsaPdMonitoraggioStato());
                datiConfigurazione.setUrlFreesbeeSP(configurazioneSP.getUrlFreesbeeSp());
                return datiConfigurazione;
            }
            return null;
        } catch (TorqueException e) {
            e.printStackTrace();
            throw new DAOException(e);
        }
    }

    public void modificaConfigurazioneSLA(DatiConfigurazione datiConfigurazione) throws DAOException {

        try {
            List<Configurazione> listaConfigurazione = ConfigurazionePeer.doSelect(new Criteria());

            if (listaConfigurazione == null || listaConfigurazione.size() == 0) {
                throw new DAOException("Non esiste una configurazione da aggiornare ");
            } else {
                Configurazione conf = listaConfigurazione.get(0);
                Configurazione configurazione = new Configurazione();
                configurazione.setIndirizzoRegistroServizi(datiConfigurazione.getIndirizzoRegistroServizi());
                configurazione.setNomeSLA(datiConfigurazione.getNomeSLA());
                configurazione.setTipoSLA(datiConfigurazione.getTipoSLA());
                configurazione.setAccordoServizioSla(datiConfigurazione.getAccordoServizioSLA());
                configurazione.setNomeServizioMonitoraggioSla(datiConfigurazione.getNomeServizioMonitoraggioSLA());
                configurazione.setTipoServizioMonitoraggioSla(datiConfigurazione.getTipoServizioMonitoraggioSLA());
                configurazione.setPdMonitoraggioSla(datiConfigurazione.getPdMonitoraggioSLA());
                configurazione.setAccordoServizioStato(datiConfigurazione.getAccordoServizioStato());
                configurazione.setNomeServizioMonitoraggioStato(datiConfigurazione.getNomeServizioMonitoraggioStato());
                configurazione.setTipoServizioMonitoraggioStato(datiConfigurazione.getTipoServizioMonitoraggioStato());
                configurazione.setPdMonitoraggioStato(datiConfigurazione.getPdMonitoraggioStato());
                configurazione.setId(conf.getId());

                ConfigurazionePeer.doUpdate(configurazione);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException(e);
        }
    }

    public void modificaConfigurazioneSP(DatiConfigurazione datiConfigurazione) throws DAOException {

        try {
       
            List<ConfigurazioneSP> listaConfigurazione = ConfigurazioneSPPeer.doSelect(new Criteria());

            if (listaConfigurazione == null || listaConfigurazione.size() == 0) {
                throw new DAOException("Non esiste una configurazione SP da aggiornare ");
            } else {

                ConfigurazioneSP conf = listaConfigurazione.get(0);
                ConfigurazioneSP configurazione = new ConfigurazioneSP();
                configurazione.setUrlFreesbeeSp(datiConfigurazione.getUrlFreesbeeSP());
                configurazione.setRisorsa(datiConfigurazione.getRisorsa());
                configurazione.setRisorsaPdMonitoraggioSla(datiConfigurazione.getRisorsaPdMonitoraggioSLA());
                configurazione.setRisorsaPdMonitoraggioStato(datiConfigurazione.getRisorsaPdMonitoraggioStato());
                
                configurazione.setAutenticazione(conf.getAutenticazione());
                configurazione.setId(conf.getId());
                ConfigurazioneSPPeer.doUpdate(configurazione);
            }
        } catch (TorqueException e) {
            e.printStackTrace();
            throw new DAOException(e);
        }
    }
}
