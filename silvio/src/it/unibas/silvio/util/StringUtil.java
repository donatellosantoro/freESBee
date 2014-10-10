package it.unibas.silvio.util;

public class StringUtil {

    public static boolean isVuota(String s) {
        return s == null || s.trim().isEmpty();
    }

    public static boolean isNonVuota(String s) {
        return !isVuota(s);
    }
}
