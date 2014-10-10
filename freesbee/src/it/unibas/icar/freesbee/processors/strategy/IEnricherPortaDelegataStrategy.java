package it.unibas.icar.freesbee.processors.strategy;

import it.unibas.icar.freesbee.exception.FreesbeeException;
import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.modello.PortaDelegata;
import java.util.List;

public interface IEnricherPortaDelegataStrategy {

    public void arricchisciAzioneDinamica(PortaDelegata portaDelegata) throws FreesbeeException;

//    public void arricchisciPortaDelegataDinamica(PortaDelegata portaDelegata) throws FreesbeeException;

    public void arricchisciMessaggio(PortaDelegata portaDelegata, Messaggio messaggio) throws FreesbeeException;

    public void arricchisciPortaDelegataDinamica(PortaDelegata portaDelegata, String queryFruitore, String queryTipoFruitore, String queryErogatore, String queryTipoErogatore, String queryServizio, String queryTipoServizio, String queryAzione)  throws FreesbeeException;
    
}
