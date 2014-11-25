
package utility;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class Utilita {
    
    public static  String[] ALIMENTAZIONI={"Benzina","Diesel","Gpl","Metano","Elettrica","Ibrida"};
    
    public static  String[] USI={"Privato","Aziendale"};
    
    public static  String[] POSTI={"2","3","5","9"};
    
    public static  String[] CLASSI={"Autoveicolo","Macchina Agricola","Motoveicolo","Ciclomotore"};
    
    
 
    
 
    
    public static  Calendar getDataCorrente(){
        Calendar data=new GregorianCalendar();
        return data;
    }
    
}
