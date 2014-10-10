/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.unibas.freesbee.test;

import it.unibas.freesbee.monitoraggio.dbwsa.DAORoundFunction;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Giuseppe De Vivo
 */
public class TestRoundFunction extends TestCase {
    
    private Log logger = LogFactory.getLog(this.getClass());
    
    public TestRoundFunction(String testName) {
        super(testName);
    }            

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void test_01() {
        List<Double> listaRisultato = new ArrayList<Double>();
        listaRisultato.add(230.23423);
        listaRisultato.add(459.4523452345);
        listaRisultato.add(349.34513451);
        listaRisultato.add(123245.4523452345);
        listaRisultato.add(45345.23412345);
        listaRisultato.add(5345.451345132);
        listaRisultato.add(4234.3451346245); 
        listaRisultato = DAORoundFunction.arrotondaRisultati(listaRisultato, 5);
        for(Double valore : listaRisultato) {
            logger.debug(valore);
        }
    }
    
    public void test_02() {
        List<Double> listaRisultato = new ArrayList<Double>();
        listaRisultato.add(230.23423);
        listaRisultato.add(459.4523452345);
        listaRisultato.add(349.34513451);
        listaRisultato.add(123245.4523452345);
        listaRisultato.add(45345.23412345);
        listaRisultato.add(5345.451345132);
        listaRisultato.add(4234.3451346245); 
        listaRisultato = DAORoundFunction.arrotondaRisultati(listaRisultato, 6);
        for(Double valore : listaRisultato) {
            logger.debug(valore);
        }
    }
    

}
