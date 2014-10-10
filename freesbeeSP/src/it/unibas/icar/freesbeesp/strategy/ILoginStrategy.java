package it.unibas.icar.freesbeesp.strategy;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public interface ILoginStrategy {

    public void login(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException;

}
