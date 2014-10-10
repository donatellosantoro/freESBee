package it.unibas.icar.freesbeesp.sso.driver;

import it.unibas.icar.freesbeesp.exception.FreesbeeSPException;
import java.io.InputStream;

public interface IHtmlLogin {

    public InputStream login(String username, String password, String address) throws FreesbeeSPException;
}
