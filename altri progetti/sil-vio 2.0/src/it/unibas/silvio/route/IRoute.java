package it.unibas.silvio.route;

import it.unibas.silvio.modello.Step;
import org.apache.camel.Exchange;

public interface IRoute {

    Step getStepCorrente(Exchange exchange);

}
