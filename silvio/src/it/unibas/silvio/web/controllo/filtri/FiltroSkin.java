package it.unibas.silvio.web.controllo.filtri;

import it.unibas.silvio.web.util.FileUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FiltroSkin implements Filter {

    private static Log logger = LogFactory.getLog(FiltroSkin.class);

    public void init(FilterConfig config) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        String servletPath = httpRequest.getServletPath();
        String nomeFile = servletPath;
        nomeFile = nomeFile.substring("/skin/".length(), nomeFile.length());
        logger.info("Richiesto il file " + nomeFile);

        String skinDir = (String) session.getAttribute("skinDir");
        if (skinDir == null || !skinDir.startsWith("file:///")) {
            logger.info("Non è stata specificata alcuna skin.");
            chain.doFilter(request, response);
            return;
        }
        skinDir = skinDir.substring("file:///".length(), skinDir.length());
        File skinFolder = new File(skinDir);
        if (!skinFolder.exists() || !skinFolder.isDirectory()) {
            logger.info("La cartella della skin " + skinDir + " non esiste.");
            chain.doFilter(request, response);
            return;
        }
        File fileProxy = new File(skinFolder.getPath() + File.separator + nomeFile);
        if (!fileProxy.exists()) {
            logger.info("Il file " + fileProxy + " non esiste.");
            chain.doFilter(request, response);
            return;
        }
        logger.info("Faccio il proxy sul file " + fileProxy.toString());
        InputStream is = new FileInputStream(fileProxy);
        FileUtil.copyStream(is, httpResponse.getOutputStream());
        is.close();
    }

    public void destroy() {
    }
}
