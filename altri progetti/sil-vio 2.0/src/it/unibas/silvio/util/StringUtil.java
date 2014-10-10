package it.unibas.silvio.util;

public class StringUtil {

    public static boolean diverso(String s1, String ... compareString){
        for (String s2 : compareString) {
            if(s1.equals(s2)){
                return false;
            }
        }
        return true;
    }

    public static boolean isNonVuota(String datiInput) {
        return !isVuota(datiInput);
    }

    public static boolean isVuota(String datiInput) {
        return datiInput==null || datiInput.trim().isEmpty();
    }

}
