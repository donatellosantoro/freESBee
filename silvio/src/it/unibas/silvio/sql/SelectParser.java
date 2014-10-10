package it.unibas.silvio.sql;

import it.unibas.silvio.modello.DatoPrimitivo;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SelectParser {

    public static Log logger = LogFactory.getLog(SelectParser.class);

    public static String completaQuery(String query, List<DatoPrimitivo> datiIstanza) {        
        if (query == null) {
            return null;
        }
        for (DatoPrimitivo datoPrimitivo : datiIstanza) {
            query = aggiungiAttributo(query,datoPrimitivo);
        }
        if(query.contains("?")){
            return null;
        }
        return query;
    }

    public static List<DatoPrimitivo> parse(String query) {
        if (query == null || query.toLowerCase().contains("select *")) {
            return null;
        }
        List<DatoPrimitivo> lista = new ArrayList<DatoPrimitivo>();
        String[] selects = query.split("(?i) union ");
        for (String select : selects) {
            analizzaSelect(select, lista);
        }
        return lista;
    }

    private static String aggiungiAttributo(String query, DatoPrimitivo datoPrimitivo) {
        String nome = datoPrimitivo.getNome();
        String valore = datoPrimitivo.getValore();
        
        return query.replaceAll("(?i)" + nome + ".?=.?\\?", nome + " = '" + valore + "'");
    }

    private static void analizzaSelect(String select, List<DatoPrimitivo> lista) {
        logger.info("Analizzo la query: " + select);
        if (select == null || !select.toLowerCase().startsWith("select ") || !select.toLowerCase().contains("from")) {
            return;
        }
        int iFrom = select.toLowerCase().indexOf("from");
        String nomiDati = select.substring("select ".length(), iFrom - 1);
        logger.info("\tDati: '" + nomiDati + "'");
        String[] listaNomi = nomiDati.split(",");
        for (String nome : listaNomi) {
            DatoPrimitivo dato = new DatoPrimitivo(nome);
            lista.add(dato);
        }
    }

    public static String escludiWhere(String select) {
        String ripulita = "";
        //Divide la query in base ad UNION ottententdo le viarie subQuery
        String[] arraySelect = select.split("UNION");

        for (int i = 0; i < arraySelect.length; i++) {
            String subSelect;
            int indexWhere = getIndexWhere(arraySelect[i]);

            if (indexWhere != -1) {
                subSelect = arraySelect[i].substring(0, indexWhere).trim();
            } else {
                subSelect = arraySelect[i].trim();
            }

            if (i < arraySelect.length - 1) {
                ripulita += subSelect + " UNION ";
                logger.info(ripulita);
            } else {
                ripulita += subSelect;
                logger.info(ripulita);
            }

        }

        return ripulita;
    }

    private static int getIndexWhere(String select) {
        String selectNormalizzata = new String(select).toUpperCase();
        return selectNormalizzata.indexOf("WHERE");
    }


    public static List<DatoPrimitivo> estraiParametri(String select) {
        ArrayList<DatoPrimitivo> listaParametri = new ArrayList<DatoPrimitivo>();

        //Divide la query in base ad UNION ottententdo le viarie subQuery
        String[] arraySelect = select.split("UNION");

        for (int i = 0; i < arraySelect.length; i++) {
            String subSelect;
            //Trova l'indice da dove inizia la where 
            int indexWhere = getIndexWhere(arraySelect[i]);

            if (indexWhere != -1) {
                //Prende solo la parte della where fino alla fine della subQuery
                subSelect = arraySelect[i].substring(indexWhere);
               
                //Trova l'indice di dove finisce la where
                int indexFineWhere = subSelect.lastIndexOf('?');
                //Prende solo la clausola where eliminando la parola where
                subSelect = subSelect.substring(5, indexFineWhere + 1).trim();

                //Scompone la clausola where in base agli AND
                String[] arrayAnd = subSelect.split("(?i) AND");

                for (int g = 0; g < arrayAnd.length; g++) {

                    //Per ogni parte della where ricava i parametri
                    listaParametri.addAll(estrai(arrayAnd[g].trim()));

                }
            }

        }


        return listaParametri;
    }

    private static ArrayList<DatoPrimitivo> estrai(String subSelect) {
        ArrayList<DatoPrimitivo> listaParametri = new ArrayList<DatoPrimitivo>();
        while (subSelect.length() != 1) {
            //Trova l'indice del ?
            int index = subSelect.indexOf('=');
            //Elimina =? dalla stringa e i vari spazi
            String stringaParametro = subSelect.substring(0, index).trim();

            DatoPrimitivo dato = new DatoPrimitivo();
            dato.setNome(stringaParametro);
            listaParametri.add(dato);

            subSelect = subSelect.substring(subSelect.indexOf('?'));
        }

        return listaParametri;
    }
}
