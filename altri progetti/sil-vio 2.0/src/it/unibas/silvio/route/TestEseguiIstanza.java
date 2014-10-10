package it.unibas.silvio.route;

import it.unibas.silvio.modello.Istanza;
import it.unibas.silvio.modello.StepAvvioIstanza;
import it.unibas.silvio.modello.StepInterrogazioneBD;
import it.unibas.silvio.modello.StepInvioMessaggio;
import it.unibas.silvio.modello.StepTransazioneBD;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;

public class TestEseguiIstanza {
    private org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog(this.getClass().getName());
    private CamelContext context;

    public TestEseguiIstanza(CamelContext context) {
        this.context = context;
        esegui();
    }

    private void esegui(){
        try {
            Istanza istanzaFruitoreTest = creaIstanzaTestFruitore();
            RouteBuilder istanzaPTFruitore = new RouteAvvioIstanza((StepAvvioIstanza)istanzaFruitoreTest.getPrimoStep());
            context.addRoutes(istanzaPTFruitore);

            Istanza istanzaEcho = creaIstanzaTestEcho();
            RouteBuilder istanzaPTEcho = new RouteAvvioIstanza((StepAvvioIstanza)istanzaEcho.getPrimoStep());
            context.addRoutes(istanzaPTEcho);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private Istanza creaIstanzaTestEcho() {
        Istanza istanza = new Istanza();

        StepAvvioIstanza step1 = new StepAvvioIstanza("test-echo");
        step1.setIndirizzo("http://0.0.0.0:1234/echo");

        istanza.setPrimoStep(step1);
        step1.setProssimoStep(null);

        return istanza;
    }
    
    private Istanza creaIstanzaTestFruitore() {
        Istanza istanza = new Istanza();

        StepAvvioIstanza step1 = new StepAvvioIstanza("user-dati-anagrafici-paziente");
        step1.setIndirizzo("http://0.0.0.0:1234/test");
        StepInterrogazioneBD step2 = new StepInterrogazioneBD("bd-dati-anagrafici-paziente");
        StepInvioMessaggio step3 = new StepInvioMessaggio("soap-dati-sanitari-paziente");
        step3.setIndirizzo("http://localhost:1234/echo");
        StepTransazioneBD step4 = new StepTransazioneBD("bd-salva-dati-sanitari-paziente");

        istanza.setPrimoStep(step1);
        step1.setProssimoStep(step2);
        step2.setProssimoStep(step3);
        step3.setProssimoStep(step4);
        step4.setProssimoStep(null);

        return istanza;
    }


}
