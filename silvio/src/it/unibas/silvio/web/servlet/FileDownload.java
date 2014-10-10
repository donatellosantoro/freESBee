package it.unibas.silvio.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileDownload extends HttpServlet {

    private org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog(this.getClass());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String nomeFile = httpRequest.getParameter("nomeFile");
        String mimeType = httpRequest.getParameter("mimeType");
        String contenuto = httpRequest.getParameter("contenuto");
        if (contenuto == null) {
            contenuto = "";
        }
        if (nomeFile == null) {
            nomeFile = "documento.txt";
        }
        if (mimeType == null) {
            mimeType = "text/plain";
        }
        httpResponse.setContentType(mimeType);
        httpResponse.getWriter().print(contenuto);
        httpResponse.setHeader("Content-Disposition", "attachment; filename=\"" + nomeFile + "\"");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Execute any sql script";
    }// </editor-fold>
}
