package it.unibas.icar.freesbeesp.strategy;

public class LoginFactory {

    public static final String AUTENTICAZIONE_UTENTE = "UTENTE";
    public static final String AUTENTICAZIONE_AGENTE = "AGENTE";

    public static ILoginStrategy getInstance(String authType) {
        if (authType.equalsIgnoreCase(AUTENTICAZIONE_UTENTE)) {
            return new UserLogin();
        } else if (authType.equalsIgnoreCase(AUTENTICAZIONE_AGENTE)) {
            return new AgentLogin();
        } else {
            throw new IllegalArgumentException("Tipo di autenticazione sconosciuta " + authType);
        }
    }
}