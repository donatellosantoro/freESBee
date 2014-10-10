package it.unibas.silvio.persistenza.hibernate;

import it.unibas.silvio.modello.Dati;
import it.unibas.silvio.persistenza.IDAODati;

public class DAODatiHibernate extends DAOGenericoHibernate<Dati> implements IDAODati{    
    
    public DAODatiHibernate(){
        super(Dati.class);
    }  
    

}
